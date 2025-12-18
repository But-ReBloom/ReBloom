package com.but.rebloom.domain.auth.usecase;

import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.domain.auth.domain.Provider;
import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.GoogleLoginAuthorizeCodeRequest;
import com.but.rebloom.domain.auth.dto.response.GoogleUserInfoResponse;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.exception.WrongVerifiedCodeException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleOAuthUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 유저 업적 설정
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // yml 속성 추출
    @Value("${spring.security.oauth2.client.registration.google.client-id:}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret:}")
    private String clientSecret;

    // 인증 코드 추출
    public User execute(GoogleLoginAuthorizeCodeRequest request) {
        String accessToken = getAccessToken(request.getAuthorizationCode(), request.getRedirectUri());
        GoogleUserInfoResponse googleUser = getUserInfo(accessToken);

        return userRepository.findByUserEmail(googleUser.getEmail())
                .orElseGet(() -> {
                    User newUser = userRepository.save(User.builder()
                            .userEmail(googleUser.getEmail())
                            .userId(java.util.UUID.randomUUID().toString())
                            .userName(googleUser.getName())
                            .userPassword("")
                            .userProvider(Provider.GOOGLE)
                            .build());
                    
                    // 구글 신규 유저에게 초기 업적 생성
                    userRepository.flush();
                    defaultUserAchievementUseCase.createDefaultUserAchievement(googleUser.getEmail());
                    
                    // 업적 성공 처리
                    String signupAchievementTitle = "시작이 반이다.";
                    defaultUserAchievementUseCase.updateUserAchievementToSuccess(googleUser.getEmail(), signupAchievementTitle);
                    
                    return newUser;
                });
    }

    // Jwt랑 비슷하게 이해
    private String getAccessToken(String code, String redirectUri) {
        String tokenUrl = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String.class);

        return extractAccessToken(response.getBody());
    }

    // Google 엔드 포인트 호출
    private GoogleUserInfoResponse getUserInfo(String accessToken) {
        String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<GoogleUserInfoResponse> response = restTemplate.exchange(
                userInfoUrl,
                HttpMethod.GET,
                request,
                GoogleUserInfoResponse.class
        );

        return response.getBody();
    }

    // 이것도 Jwt 비슷하게 이해
    private String extractAccessToken(String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            throw new WrongVerifiedCodeException("액세스 토큰 오류");
        }
    }
}
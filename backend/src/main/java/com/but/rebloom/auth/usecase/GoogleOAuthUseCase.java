package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.response.GoogleUserInfoResponse;
import com.but.rebloom.auth.jwt.JwtTokenProvider;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.WrongVerifiedCodeException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleOAuthUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 토큰 생성
    private final JwtTokenProvider jwtTokenProvider;

    // yml 속성 추출
    @Value("${spring.security.oauth2.client.registration.google.client-id:}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret:}")
    private String clientSecret;

    // 인증 코드 추출
    public User execute(String authorizationCode) {
        String accessToken = getAccessToken(authorizationCode);
        GoogleUserInfoResponse googleUser = getUserInfo(accessToken);

        return userRepository.findByUserEmailAndProvider(googleUser.getEmail(), Provider.GOOGLE)
                .orElseGet(() -> userRepository.save(User.builder()
                        .userEmail(googleUser.getEmail())
                        .userName(googleUser.getName())
                        .userPassword("")
                        .provider(Provider.GOOGLE)
                        .build()));
    }

    // Jwt랑 비슷하게 이해
    private String getAccessToken(String code) {
        String tokenUrl = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "code=" + code +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&redirect_uri=" + "http://localhost:8080/auth/login/google" +
                "&grant_type=authorization_code";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
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
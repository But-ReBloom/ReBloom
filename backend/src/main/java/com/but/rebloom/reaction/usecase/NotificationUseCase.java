package com.but.rebloom.reaction.usecase;

import com.but.rebloom.reaction.dto.request.CommentNotificationRequest;
import com.but.rebloom.reaction.dto.request.HeartNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationUseCase {
    private final JavaMailSender mailSender;

    public void sendCommentNotification(CommentNotificationRequest commentNotificationRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(commentNotificationRequest.getOwnerEmail());
            message.setSubject("[ReBloom] 내 글에 새로운 댓글이 달렸습니다.");
            message.setText(String.format(
                    "%s님, \n%s님이 회원님의 게시글에 댓글을 남겼습니다.\n댓글 내용: %s\n지금 바로 ReBloom에서 확인해보세요!",
                    commentNotificationRequest.getOwnerUserId(),
                    commentNotificationRequest.getCommenterUserId(),
                    commentNotificationRequest.getCommentContent())
            );
            mailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("전송 중 오류: " + e.getMessage());
        }
    }

    public void sendHeartNotification(HeartNotificationRequest heartNotificationRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(heartNotificationRequest.getOwnerEmail());
            message.setSubject("[ReBloom] 내 글에 좋아요가 달렸습니다.");
            message.setText(String.format(
                    "%s님, \n%s님이 회원님의 게시글에 좋아요를 남겼습니다.",
                    heartNotificationRequest.getOwnerUserId(),
                    heartNotificationRequest.getLikerUserId())
            );
            mailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("전송 중 오류: " + e.getMessage());
        }
    }
}

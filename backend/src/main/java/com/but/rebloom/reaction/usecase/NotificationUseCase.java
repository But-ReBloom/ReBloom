package com.but.rebloom.reaction.usecase;

import com.but.rebloom.common.usecase.EmailSenderUseCase;
import com.but.rebloom.reaction.dto.request.CommentNotificationRequest;
import com.but.rebloom.reaction.dto.request.HeartNotificationRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationUseCase extends EmailSenderUseCase {

    // 부모 생성자 호출
    public NotificationUseCase(JavaMailSender mailSender) {
        super(mailSender);
    }

    public void sendCommentNotification(CommentNotificationRequest commentNotificationRequest) {
        String subject = "[ReBloom] 내 글에 새로운 댓글이 달렸습니다.";
        String text = String.format(
                "%s님, \n%s님이 회원님의 게시글에 댓글을 남겼습니다.\n댓글 내용: %s\n지금 바로 ReBloom에서 확인해보세요!",
                commentNotificationRequest.getOwnerUserId(),
                commentNotificationRequest.getCommenterUserId(),
                commentNotificationRequest.getCommentContent()
        );
        sendEmail(commentNotificationRequest.getOwnerEmail(), subject, text);
    }

    public void sendHeartNotification(HeartNotificationRequest heartNotificationRequest) {
        String subject = "[ReBloom] 내 글에 좋아요가 달렸습니다.";
        String text = String.format(
                "%s님, \n%s님이 회원님의 게시글에 좋아요를 남겼습니다.",
                heartNotificationRequest.getOwnerUserId(),
                heartNotificationRequest.getLikerUserId()
        );
    }
}

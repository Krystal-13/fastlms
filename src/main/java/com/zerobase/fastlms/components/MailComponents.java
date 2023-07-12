package com.zerobase.fastlms.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailComponents {

    private final JavaMailSender javaMailSender;

    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;
        MimeMessagePreparator msg = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
        };

        try {
            javaMailSender.send(msg);
            result = true;

        } catch (Exception e) {
            log.info("[MailSender Failed] ::: " + e.getMessage());
        }

        return result;

    }

}

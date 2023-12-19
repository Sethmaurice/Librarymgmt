package com.example.Librarymgmt.securityy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceConfig {
    @Autowired
    private JavaMailSender mailsender;

    public void sendEmail(String toemail, String subject, String body) throws MessagingException {

        MimeMessage message = mailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setSubject(subject);
        helper.setTo(toemail);
        helper.setText(body, true);
        mailsender.send(message);
    }

    public void sendCitizenEmail(String toemail, String subject, String username)
            throws MessagingException {
        String message = "<body>\r\n" + //
                "  <h1>\r\n" + //
                "   Water access Rwanda \r\n" + //
                "  </h1>\r\n" + //
                "  <div>\r\n" + //
                "    Hello,<b>" + username + "</b>\r\n" + //
                "  </div>\r\n" + //
                "  <div>\r\n" + //
                "    You have registered successfully \r\n" + //
                "  </div>\r\n" + //

                "  <div>\r\n" + //
                " <br> Thank you. </b>\r\n" + //
                "  </div>\r\n" + //
                "</body>";
        this.sendEmail(toemail, subject, message);
    }



}
package com.demo.aws.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.demo.aws.ses.dto.request.EmailRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private AmazonSimpleEmailService mailClient;

    public String sendEmail(EmailRequestDto dto) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(dto.getRecipientEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content().withCharset("UTF-8").withData(htmlTemplate(dto.getHtmlMessage())))
                                    .withText(new Content().withCharset("UTF-8").withData(dto.getTextMessage())))
                            .withSubject(new Content().withCharset("UTF-8").withData(dto.getSubject())))
                    .withSource(dto.getSenderEmail());

            String id = mailClient.sendEmail(request).getMessageId();

            log.info("method=sendEmail, id={}", id);

            return id;
        } catch (Exception ex) {
            log.info("method=sendEmail, exception={}", ex.getMessage());
            return "Failed";
        }
    }

    private String htmlTemplate(String text) {
        return "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\n" +
                "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\n" +
                "    <div style=\"border-bottom:1px solid #eee\">\n" +
                "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">Your Brand</a>\n" +
                "    </div>\n" +
                "    <p style=\"font-size:1.1em\">Hi,</p>\n" +
                "    <p>Thank you for choosing Your Brand. Use the following OTP to complete your Sign Up procedures. OTP is valid for 5 minutes</p>\n" +
                "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + text + "</h2>\n" +
                "    <p style=\"font-size:0.9em;\">Regards,<br />Your Brand</p>\n" +
                "    <hr style=\"border:none;border-top:1px solid #eee\" />\n" +
                "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\n" +
                "      <p>Your Brand Inc</p>\n" +
                "      <p>1600 Amphitheatre Parkway</p>\n" +
                "      <p>California</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>  ";
    }
}

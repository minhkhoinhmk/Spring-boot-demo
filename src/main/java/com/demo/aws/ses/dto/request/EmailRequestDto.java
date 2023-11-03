package com.demo.aws.ses.dto.request;

import lombok.Data;

@Data
public class EmailRequestDto {
    private String senderEmail;

    private String recipientEmail;

    private String subject;

    private String htmlMessage;

    private String textMessage;
}

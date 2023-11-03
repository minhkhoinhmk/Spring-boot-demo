package com.demo.aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws.access-key}")
    private String access_key;

    @Value("${aws.secret-key}")
    private String secret_key;

    private AWSCredentials awsCredentials() {
        AWSCredentials credentials = new BasicAWSCredentials(access_key, secret_key);
        return credentials;
    }

    @Bean
    public AmazonSimpleEmailService emailClientBuilder() {
        AmazonSimpleEmailService mailClient = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .withRegion(Regions.EU_NORTH_1)
                .build();
        return mailClient;
    }
}

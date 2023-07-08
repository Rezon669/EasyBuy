package com.easybuy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.app.controller.LoginController;

import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.GetIdentityVerificationAttributesRequest;
import software.amazon.awssdk.services.ses.model.GetIdentityVerificationAttributesResponse;
import software.amazon.awssdk.services.ses.model.IdentityVerificationAttributes;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import software.amazon.awssdk.services.ses.model.VerifyEmailAddressRequest;

@Service
public class EmailService {

    private final SesClient sesClient;
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired
    public EmailService(SesClient sesClient) {
        this.sesClient = sesClient;
    }

    public void sendEmail(String fromEmail, String toEmail, String subject, String body) {
        Destination destination = Destination.builder().toAddresses(toEmail).build();
        Content emailSubject = Content.builder().data(subject).build();
        Content emailBody = Content.builder().data(body).build();
        Body emailContent = Body.builder().text(emailBody).build();
        Message emailMessage = Message.builder().subject(emailSubject).body(emailContent).build();
        SendEmailRequest request = SendEmailRequest.builder()
                .source(fromEmail)
                .destination(destination)
                .message(emailMessage)
                .build();
        SendEmailResponse response = sesClient.sendEmail(request);
        logger.info("Email sent! Message ID: " + response.messageId());
    }
    

	/*public void getEmailStatus(String email) {
		  AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .withRegion("us-east-1") // Replace with your desired region
	                .build();

	        // Email address to check identity status
	        String emailAddress = "user@example.com";

	        // Check identity status
	        GetIdentityVerificationAttributesRequest request = new GetIdentityVerificationAttributesRequest()
	                .withIdentities(emailAddress);
	        GetIdentityVerificationAttributesResult result = client.getIdentityVerificationAttributes(request);

	        // Retrieve identity verification attributes
	        IdentityVerificationAttributes attributes = result.getVerificationAttributes()
	                .get(emailAddress);

	        if (attributes != null) {
	            String verificationStatus = attributes.getVerificationStatus();
	            System.out.println("Verification Status: " + verificationStatus);
	        } else {
	            System.out.println("Email address not found in identity verification records.");
	        }
	    }*/
	

}



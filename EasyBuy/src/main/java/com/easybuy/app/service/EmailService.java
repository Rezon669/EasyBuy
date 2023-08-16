package com.easybuy.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	
	
	 private final JavaMailSender mailSender;

	    @Autowired
	    public EmailService(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }

	    public void sendEmail(String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(text);
	        mailSender.send(message);
	    }
	
	/*
	 * private final SesClient sesClient; private static final Logger logger =
	 * LogManager.getLogger(EmailService.class);
	 * 
	 * @Autowired public EmailService(SesClient sesClient) { this.sesClient =
	 * sesClient; }
	 * 
	 * public void sendEmail(String fromEmail, String toEmail, String subject,
	 * String body) { Destination destination =
	 * Destination.builder().toAddresses(toEmail).build(); Content emailSubject =
	 * Content.builder().data(subject).build(); Content emailBody =
	 * Content.builder().data(body).build(); Body emailContent =
	 * Body.builder().text(emailBody).build(); Message emailMessage =
	 * Message.builder().subject(emailSubject).body(emailContent).build();
	 * SendEmailRequest request = SendEmailRequest.builder() .source(fromEmail)
	 * .destination(destination) .message(emailMessage) .build(); SendEmailResponse
	 * response = sesClient.sendEmail(request);
	 * logger.info("Email sent! Message ID: " + response.messageId()); }
	 */
    

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



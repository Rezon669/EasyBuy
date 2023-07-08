package com.easybuy.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.VerifyEmailAddressRequest;
import software.amazon.awssdk.services.ses.model.VerifyEmailAddressResponse;

@Service
public class EmailVerificationService {

    private static SesClient sesClient;

    @Autowired
    public EmailVerificationService(SesClient sesClient) {
        this.sesClient = sesClient;
    }

   

	public static boolean verifyEmail(String emailid) {
        VerifyEmailAddressRequest request = VerifyEmailAddressRequest.builder()
                .emailAddress(emailid)
                .build();

        VerifyEmailAddressResponse response = sesClient.verifyEmailAddress(request);

        return response.sdkHttpResponse().isSuccessful();
    }

}

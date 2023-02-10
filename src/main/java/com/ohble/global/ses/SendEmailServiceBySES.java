package com.ohble.global.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.ohble.global.ses.mailform.EmailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailServiceBySES {

    private final EmailForm emailForm;

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void sendAdminAuthContent(String receivers, String payload) {
        Destination destination = new Destination().withToAddresses(receivers);
        Message message = new Message()
                .withSubject(createContent(emailForm.setAdminAuthFormSubject()))
                .withBody(new Body()
                        .withHtml(createContent(emailForm.buildAdminAuthForm(payload))));

        SendEmailRequest buildingRequest = new SendEmailRequest()
                .withSource("sugousw@gmail.com")
                .withDestination(destination)
                .withMessage(message);

        amazonSimpleEmailService.sendEmail(buildingRequest);
    }

    private Content createContent(String text) {
        return new Content()
                .withCharset("UTF-8")
                .withData(text);
    }
}

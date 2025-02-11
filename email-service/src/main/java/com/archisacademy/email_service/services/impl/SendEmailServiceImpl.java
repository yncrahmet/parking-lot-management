package com.archisacademy.email_service.services.impl;

import com.archisacademy.email_service.ApiResponse.ApiResponse;
import com.archisacademy.email_service.dtos.request.SendEmailRequest;
import com.archisacademy.email_service.dtos.response.SendEmailResponse;
import com.archisacademy.email_service.model.Email;
import com.archisacademy.email_service.modelmapper.ModelMapperService;
import com.archisacademy.email_service.repositories.EmailRepository;
import com.archisacademy.email_service.services.SendEmailService;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Value("${spring.mail.username}") private String sender;

    Logger logger = LoggerFactory.getLogger(SendEmailServiceImpl.class);

    private final JavaMailSender javaMailSender;
    private final ModelMapperService modelMapperService;
    private final EmailRepository emailRepository;

    public SendEmailServiceImpl(JavaMailSender javaMailSender, ModelMapperService modelMapperService, EmailRepository emailRepository) {
        this.javaMailSender = javaMailSender;
        this.modelMapperService = modelMapperService;
        this.emailRepository = emailRepository;
    }

    @Override
    public ApiResponse<SendEmailResponse> sendEmail(SendEmailRequest request) {

        Email email = modelMapperService.request().map(request,Email.class);
        logger.info("Email Request mapped to {}", email);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(sender);
            helper.setTo(email.getToEmail());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), true);

            if (request.getAttachmentPath() != null) {
                File attachmentFile = new File(request.getAttachmentPath());
                if (attachmentFile.exists() && attachmentFile.isFile()) {
                    helper.addAttachment("attachment", attachmentFile);
                    logger.info("Attachment mapped to {}", request.getAttachmentPath());
                } else {
                    logger.warn("Attachment file does not exist or is not a valid file: {}", request.getAttachmentPath());
                }
            }
            logger.info("Incoming information was assigned to MimeMessageHelper fields to be sent.");

            javaMailSender.send(message);
            logger.info("Email sent.");

            Email savedEmail = emailRepository.save(email);
            logger.info("Email saved to database.");

            SendEmailResponse response = modelMapperService.response().map(savedEmail, SendEmailResponse.class);
            logger.info("Email information for the response was mapped.");

            return new ApiResponse<>(true, "Email sent", response);
        } catch (Exception e) {
            logger.error("Error sending email", e);
            return new ApiResponse<>(false, "Email could not be sent");
        }

    }

}

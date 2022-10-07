package com.adm.restaurante.service.impl;

import com.adm.restaurante.dto.EmailTemplateDto;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.exceptions.InternalServerErrorException;
import com.adm.restaurante.exceptions.NotFoundException;
import com.adm.restaurante.repository.NotificationRepository;
import com.adm.restaurante.service.EmailService;
import com.adm.restaurante.entity.NotificationEntity;
import com.sun.mail.smtp.SMTPTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificationRepository notificationRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);


    @Override
    public String processSendEmail(String receiver, String templateCode, String currentName, String codeReserva) throws BookingExceptions{
        final EmailTemplateDto email = findTemplateAndReplaceName(templateCode, currentName, codeReserva);
        this.sendEmail(receiver,email.getSubject(), email.getTemplate());
        return "EMAIL_SEND";
    }

    @Override
    public String processSendEmail2(String receiver, String templateCode, String currentName) throws BookingExceptions, MessagingException {
        final EmailTemplateDto email = findTemplateAndReplaceName2(templateCode, currentName);
        this.sendCorreo("alexpruebaapp@gmail.com", "vvxbyfdfsbnknkpu",receiver,email.getSubject(), email.getTemplate());
        return "EMAIL_SEND";
    }

    private void sendEmail(final String receiver, final String subject, final String template)throws BookingExceptions {

        final MimeMessage email = mailSender.createMimeMessage();
        final MimeMessageHelper content;

        try {
            content = new MimeMessageHelper(email, true, "UTF-8");
            content.setSubject(subject);
            content.setTo(receiver);
            content.setText(template, true);

        }catch ( MessagingException  e) {
            LOGGER.error("INTERNAL SERVER ERROR", e);
            throw new RuntimeException("Problemas com o envio de e-mail.", e);
        }
        mailSender.send(email);

    }

    private void sendCorreo (String username, String password,final String receiver, final String subject, final String template)throws BookingExceptions, AddressException, MessagingException {
        Map<String, String> config = new HashMap<>();
        username = config.get("smtp.user");
        password = config.get("smtp.password");
        String host = config.get("smtp.host");
        int port = Integer.parseInt(config.get("smtp.port"));

        //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";


        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        final MimeMessage email = mailSender.createMimeMessage();
        final MimeMessageHelper content;

        try {
            content = new MimeMessageHelper(email, true);
            content.setSubject(subject);
            content.setTo(receiver);
            content.setText(template, true);


        }catch ( Exception e) {
            LOGGER.error("INTERNAL SERVER ERROR", e);
            throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ENVIAR_CORREO");
        }

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.gmail.com", username, password);
        t.sendMessage(email,email.getAllRecipients());
        t.close();
        //mailSender.send(email);

    }



    private EmailTemplateDto findTemplateAndReplaceName(String templateCode, final String currentName, String codeReserva) throws BookingExceptions {

        final NotificationEntity notification = notificationRepository.findByTemplateCode(templateCode)
                .orElseThrow(() -> new NotFoundException("TEMPLATE_NOT_CODE", "CODE_TEMPLATE_NOT_FOUND"));

        final EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
        emailTemplateDto.setSubject(notification.getTemplateCode());
        emailTemplateDto.setTemplate(notification.getTemplate().replaceAll("\\{" + "name" + "\\}", currentName).replaceAll("\\{" + "codReserva" + "\\}", codeReserva));

        return emailTemplateDto;
    }

    private EmailTemplateDto findTemplateAndReplaceName2(String templateCode, final String currentName) throws BookingExceptions {

        final NotificationEntity notification = notificationRepository.findByTemplateCode(templateCode)
                .orElseThrow(() -> new NotFoundException("TEMPLATE_NOT_CODE", "CODE_TEMPLATE_NOT_FOUND"));

        final EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
        emailTemplateDto.setSubject(notification.getTemplateCode());
        emailTemplateDto.setTemplate(notification.getTemplate().replaceAll("\\{" + "name" + "\\}", currentName));

        return emailTemplateDto;
    }




}

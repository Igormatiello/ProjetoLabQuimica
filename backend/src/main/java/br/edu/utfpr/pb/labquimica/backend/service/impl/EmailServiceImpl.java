package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.ConfEmail;
import br.edu.utfpr.pb.labquimica.backend.service.ConfEmailService;
import br.edu.utfpr.pb.labquimica.backend.service.EmailService;
import br.edu.utfpr.pb.labquimica.backend.utils.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSenderImpl javaMailSender;
    private static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class.getName());
    private final ConfEmailService confEmailService;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl javaMailSender,
                            ConfEmailService confEmailService) {
        this.javaMailSender = javaMailSender;
        this.confEmailService = confEmailService;
    }

    @Override
    public void enviar(Email email) {
        var config = confEmailService.findConfig();
        if (config != null) {
            new Thread(() -> this.sendEmail(config, email)).start();
        }
    }

    @Override
    public void enviarSemThread(Email email) {
        var config = confEmailService.findConfig();
        if (config != null) {
            this.sendEmail(config, email);
        }
    }

    @Override
    public void enviarToEmailPadrao(Email email) {
        ConfEmail config = confEmailService.findConfig();
        if (config != null) {
            email.setPara(config.getEmailRecebimento());
            new Thread(() -> this.sendEmail(config, email)).start();
        }
    }

    @Override
    public void enviarToEmailPadraoSemThread(Email email) {
        ConfEmail config = confEmailService.findConfig();
        if (config != null) {
            email.setPara(config.getEmailRecebimento());
            this.sendEmail(config, email);
        }
    }

    public void sendEmail(ConfEmail confEmail, Email email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(confEmail.getUsuario(), confEmail.getRemetente());
            helper.setReplyTo(confEmail.getUsuario());

            if (email.getPara() != null && !email.getPara().equals("")) {
                helper.setBcc(email.getPara());
            } else if (email.getParaList() != null && email.getParaList().size() > 0) {
                helper.setBcc(email.getParaList().toArray(new String[0]));
            } else {
                throw new Exception("Nenhum email encontrado para envio.");
            }

            helper.setSubject(email.getTitulo());
            helper.setText(email.getConteudo(), true);

            for (Map.Entry<String, byte[]> entry : email.getFileMap().entrySet()) {
                helper.addAttachment(entry.getKey(), new ByteArrayResource(entry.getValue()));
            }
            setPropertiesEmail(confEmail, javaMailSender);
            javaMailSender.send(message);
            LOGGER.log(Level.INFO, "Email enviado com sucesso: de -> " + confEmail.getUsuario() + " para -> " + email.getPara());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao enviar email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setPropertiesEmail(ConfEmail confEmail, JavaMailSenderImpl javaMailSender) {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", confEmail.getHost());
        properties.setProperty("mail.smtp.port", confEmail.getPorta().toString());
        properties.setProperty("mail.smtp.ssl.trust", confEmail.getHost());
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setUsername(confEmail.getUsuario());
        javaMailSender.setPassword(confEmail.getSenha());
    }
}

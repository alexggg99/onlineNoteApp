package agashchuk.SystemSpecificPackage.service;

import agashchuk.SystemSpecificPackage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    private static Logger logger = LoggerFactory.getLogger(MailService.class.getName());

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendActivatiionCode(User user) {
        logger.info("sending mail start");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Online noteapp. User activation");
        mailMessage.setText(user.getActivationCode());
        mailMessage.setFrom("admin@onlinenoteapp.com");
        try {
            javaMailSender.send(mailMessage);
            user.setActivationCode(null);
        } catch (MailException ex) {
            logger.warn("Exception occurred when mailing: " + ex.getMessage());
        } catch (Exception ex) {
            logger.warn("Exception occurred: " + ex.getMessage());
        }
        logger.info("sending mail end");
    }
}

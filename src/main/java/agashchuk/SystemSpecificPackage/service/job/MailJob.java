package agashchuk.SystemSpecificPackage.service.job;

import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.model.UserState;
import agashchuk.SystemSpecificPackage.service.MailService;
import agashchuk.SystemSpecificPackage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@Component
@Transactional
public class MailJob {

    private static final Logger log = LoggerFactory.getLogger(MailJob.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 0/5 * 1/1 * *") //every 5 minutes
    public void reportCurrentTime() {
        for(User user : userService.findUsersForActivation(UserState.Blocked)) {
            mailService.sendActivatiionCode(user);
        }
    }

}

package ua.kiev.prog.oauth2.loginviagoogle.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ua.kiev.prog.oauth2.loginviagoogle.dto.TaskToNotifyDTO;

import java.util.Objects;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ApplicationContext applicationContext;

    public void sendMessage(TaskToNotifyDTO task) {
        SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);

        String text = String.format(Objects.requireNonNull(message.getText()), task.getDate(), task.getText());

        message.setText(text);
        message.setTo(task.getEmail());

        emailSender.send(message);
    }
}
package faceit.tz.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

@Component
public class EmailSender {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(String emailTo, String subject, Map<String, Object> templateModel) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context thymeleafContext = new Context(LocaleContextHolder.getLocale());
        thymeleafContext.setVariables(templateModel);

        String htmlBody = templateEngine.process("mail-template", thymeleafContext);

        helper.setFrom(emailFrom);
        helper.setTo(emailTo);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        javaMailSender.send(message);
    }

}

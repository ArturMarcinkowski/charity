package pl.coderslab.charity.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@PropertySource("classpath:application.properties")
public class SendEmail {

    @Value("${email.username}")
    private final String username = "Artur.Marcinkowski.Serwer@gmail.com";
    @Value("${email.password}")
    private final String password = "onion69+";

    public String passwordChange(String email, String key) {
        String message = send(email,
                "Zmiana hasła",
                "Aby zmienić hasło wejdź na poniższy link:\n"
                        + "localhost:8080/password-reset?key=" + key + "#password-form");


        if (message == null) {
            return "Wysłano email zmiany hasła.\nSprawdź swoją skrzynkę pocztową";
        }
        return message;
    }

    public String registerConfirm(String email, String key) {
        String message = send(email,
                "Aktywacja konta charity.com",
                "Witaj. \n" +
                        "Aby aktywować swoje konto wejdź na stronę: " +
                        "localhost:8080/account-activate?key=" + key + "#password-form");

        if (message == null) {
            return "Wysłano link aktywacyjny konta.\nSprawdź swoją skrzynkę pocztową";
        }
        return message;
    }


    public String send(String email, String title, String text) {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("localhost"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(title);
            message.setText(text);
            Transport.send(message);
            return null;

        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
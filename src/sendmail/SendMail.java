/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendmail;

import java.util.Properties;
/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;*/
import javax.mail.*;
import javax.mail.internet.*;
/*
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

/**
 *
 * @author rambabu
 */
public class SendMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final String username = "xyas@gmail.com";
        final String password = "password23";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("xyas@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("dualsl@gmail.com"));
            message.setSubject("Testing Send Mail");
            message.setText("Hi! Hello!! and Namaste!!!,\n\n Congratulation you just learn to send mail using javax.mail api");
            Transport.send(message);
            System.out.println("Your email has been sent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

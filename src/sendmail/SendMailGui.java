/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendmail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rambabu
 */
public class SendMailGui extends JFrame {

    private JTextField txtSenderEmail, txtRcvEmail, txtSub;
    private JPasswordField txtPwd;
    private JTextArea txtMessage;

    private JLabel lblSndEmail, lblPwd, lblRcvEmail, lblSub, lblMessage;

    private JButton bttnSend, bttnReset;
    private GridLayout gridLayout;
    private JPanel panel;

    public SendMailGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        txtSenderEmail = new JTextField();
        txtRcvEmail = new JTextField();
        txtPwd = new JPasswordField();
        txtMessage = new JTextArea();
        txtSub = new JTextField();

        lblSndEmail = new JLabel("Sender Email");
        lblPwd = new JLabel("Password");
        lblRcvEmail = new JLabel("Reciever Email");
        lblMessage = new JLabel("Message");
        lblSub = new JLabel("Subject");

        bttnReset = new JButton("Reset");
        bttnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtMessage.setText("");
                txtSenderEmail.setText("");
                txtPwd.setText("");
                txtRcvEmail.setText("");
                txtSub.setText("");
            }
        });

        bttnSend = new JButton("Send Mail");
        bttnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 String senderEmail = txtSenderEmail.getText();
                 String password = new String(txtPwd.getPassword());
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
               
         
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, password);
                    }
                });
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(senderEmail));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(txtRcvEmail.getText()));
                    message.setSubject(txtSub.getText());
                    message.setText(txtMessage.getText());
                    Transport.send(message);
                    JOptionPane.showMessageDialog(null,"Your email has been sent successfully");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

        //setLayout(new FlowLayout());
        panel = new JPanel();
        gridLayout = new GridLayout(0, 2);
        panel.setLayout(gridLayout);
        panel.setPreferredSize(new Dimension(300, 300));

        panel.add(lblSndEmail);
        panel.add(txtSenderEmail);

        panel.add(lblPwd);
        panel.add(txtPwd);

        panel.add(lblRcvEmail);
        panel.add(txtRcvEmail);

        panel.add(lblSub);
        panel.add(txtSub);

        panel.add(lblMessage);
        panel.add(txtMessage);

        panel.add(bttnReset);
        panel.add(bttnSend);

        add(panel);

        setSize(300, 300);
        //setPreferredSize(new Dimension(300,300));
        setVisible(true);
    }

    public static void main(String str[]) {
        SendMailGui sm = new SendMailGui();
    }
}

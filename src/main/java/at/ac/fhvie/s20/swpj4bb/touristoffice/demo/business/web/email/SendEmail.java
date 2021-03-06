package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.email;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
  public static void sendEmail(final String emailaddress) {
    //EmailID of recipient needs to be mentioned
    String to = emailaddress;

    //EmailID of sender needs to be mentioned
    String from = "touristoffice.noe@gmail.com";

    //For sending email through gmails smtp
    String host = "smtp.gmail.com";

    //Get system properties
    Properties properties = System.getProperties();

    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    //Get the Session object.// and pass username and password
    Session session = Session.getInstance(properties, new Authenticator() {

      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("touristoffice.noe@gmail.com", "!noetouristoffice2");

      }

    });

    //For debugging SMTP issues
    session.setDebug(true);

    try {
      //To create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      //To set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      //To set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      //To set Subject: header field
      message.setSubject("Tourist Office Hotel Data");

      Multipart multipart = new MimeMultipart();

      MimeBodyPart attachmentPart = new MimeBodyPart();

      MimeBodyPart textPart = new MimeBodyPart();

      try {
        File dataFile = new File("src/main/resources/hotelData/hotels.sql");
        FileOutputStream fos = new FileOutputStream("src/main/resources/hotelData/hotels.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        FileInputStream fis = new FileInputStream(dataFile);
        ZipEntry zipEntry = new ZipEntry(dataFile.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
          zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();

        attachmentPart.attachFile("src/main/resources/hotelData/hotels.zip");
        textPart.setText("Dear Recipient,\n\n" +
            "please find the backup file attached to this email.\n\n" +
            "Best regards,\n" +
            "Your IT-Team");
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

      } catch (IOException e) {

        e.printStackTrace();

      }

      message.setContent(multipart);

      System.out.println("sending...");
      // Sending message
      Transport.send(message);
      System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }

  }

}
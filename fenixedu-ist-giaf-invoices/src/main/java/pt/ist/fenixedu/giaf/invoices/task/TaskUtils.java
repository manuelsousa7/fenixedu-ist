package pt.ist.fenixedu.giaf.invoices.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.fenixedu.academic.FenixEduAcademicConfiguration;
import org.fenixedu.academic.util.ConnectionManager;
import org.fenixedu.bennu.GiafInvoiceConfiguration;
import org.fenixedu.messaging.core.domain.MessagingSystem;

public class TaskUtils {

    static final String EMAIL_ADDRESSES_TO_SEND_DATA_FILENAME  = "/afs/ist.utl.pt/ciist/fenix/fenix015/ist/giaf_sync_errors_to.txt";
    static final String EMAIL_ADDRESSES_BCC_SEND_DATA_FILENAME = "/afs/ist.utl.pt/ciist/fenix/fenix015/ist/giaf_sync_errors_bcc.txt";

    static void sendSapReport(final String filename, final byte[] byteArray, final String subject, final String body)
            throws AddressException, MessagingException {
        send(filename, byteArray, subject, body, GiafInvoiceConfiguration.getConfiguration().clientSapLogErrorsMails(),
                GiafInvoiceConfiguration.getConfiguration().clientSapLogErrorsMailsBcc());
    }

    private static void send(final String filename, final byte[] byteArray, final String subject, final String body,
            String emailAddressesFilePath, String emailAddressesBccFilePath)
            throws MessagingException {
        final Properties properties = new Properties();
        properties.put("mail.smtp.host", FenixEduAcademicConfiguration.getConfiguration().getMailSmtpHost());
        properties.put("mail.smtp.name", FenixEduAcademicConfiguration.getConfiguration().getMailSmtpName());
        properties.put("mailSender.max.recipients", FenixEduAcademicConfiguration.getConfiguration().getMailSenderMaxRecipients());
        properties.put("mail.debug", "false");
        final Session session = Session.getDefaultInstance(properties, null);

        final org.fenixedu.messaging.core.domain.Sender sender = MessagingSystem.systemSender();

        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender.getAddress()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fileContent(emailAddressesFilePath)));
        message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(fileContent(emailAddressesBccFilePath)));
        message.setSubject(subject);
        message.setText(body);

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        DataSource source = new ByteArrayDataSource(byteArray, "application/vnd.ms-excel");
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    private static String fileContent(final String filename) {
        try {
            return Files.readAllLines(new File(filename).toPath()).iterator().next();
        } catch (final IOException e) {
            throw new Error(e);
        }
    }

}

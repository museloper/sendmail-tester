package java.mail;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public class SmtpMailSender {
    public static void main(String[] args) {
        // sendmail 서버 정보
        String smtpHost = "";
        String smtpPort = "25";

        // 보내는 사람
        String from = ""; 

        // 받는 사람 리스트
        String[] recipients = {"", "", ""};
        String to = String.join(",", recipients);

        // 이메일 형식 선택 (true: HTML, false: 텍스트)
        boolean isHtml = true;

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");

        Session session = Session.getInstance(props);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
            );

            // 메일 제목
            message.setSubject("메일 전송 테스트 2025/03/20");

            // 메일 내용
            if (isHtml) {
                String templatePath = "template/template.html";
                String htmlContent = readHtmlTemplate(templatePath);
                message.setContent(htmlContent, "text/html; charset=UTF-8");
            } else {
                String textContent = "";
                message.setText(textContent);
            }

            Transport.send(message);

            System.out.println(isHtml ? "HTML 메일 전송 완료" : "텍스트 메일 전송 완료");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String readHtmlTemplate(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

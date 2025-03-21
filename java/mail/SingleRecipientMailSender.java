package java.mail;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SingleRecipientMailSender {
    public static void main(String[] args) {
        // sendmail 서버 정보
        String smtpHost = "";
        String smtpPort = "25";

        // 보내는 사람
        String from = "";

        // 받는 사람 리스트 (TO)
        String toFilePath = "recipients/to.txt";

        // 이메일 형식 선택 (true: HTML, false: 텍스트)
        boolean isHtml = true;
        String htmlContent = null;

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");

        Session session = Session.getInstance(props);

        // 템플릿 파일 읽기
        if (isHtml) {
            String templatePath = "template/template.html";
            htmlContent = readHtmlTemplate(templatePath);
        }

        try {
            List<String> recipientsTO = readRecipientsFromFile(toFilePath);

            if (recipientsTO.isEmpty()) {
                System.out.println("받는 사람이 없습니다.");
                return;
            }

            for (String recipient : recipientsTO) {
                // 빈 값은 스킵
                if (recipient.isBlank()) continue;

                try {
                    MimeMessage message = new MimeMessage(session);

                    // 보내는 사람
                    message.setFrom(new InternetAddress(from));

                    // 받는 사람
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

                    // 메일 제목
                    message.setSubject("메일 전송 테스트 2025/03/20");

                    // 메일 내용
                    if (isHtml) {
                        message.setContent(htmlContent, "text/html; charset=UTF-8");
                    } else {
                        String textContent = "";
                        message.setText(textContent);
                    }

                    // 메일 발송
                    Transport.send(message);
                    System.out.println(recipient + " ➡️ 메일 전송 성공!");

                } catch (MessagingException e) {
                    System.err.println(recipient + " ➡️ 메일 전송 실패!");
                    e.printStackTrace();
                }

                // 스팸 방지나 서버 보호용
                Thread.sleep(1000);
            }

            System.out.println("모든 메일 전송 완료");

        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    private static List<String> readRecipientsFromFile(String filePath) {
        try {
            List<String> recipients = Files.readAllLines(Paths.get(filePath));
            return recipients.stream()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .toList();
        } catch (Exception e) {
            System.err.println(filePath + " 파일을 읽는 도중 오류가 발생했습니다.");
            e.printStackTrace();
            return List.of();
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

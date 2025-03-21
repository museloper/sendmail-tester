### 자바 메일 발송

```sh
# java 파일 패키지 java.mail 삭제 후 실행

# 디렉토리 구조(예시)
/home
  ├── template
  │    └── template.html
  ├── recipients
  │    ├── to.txt
  │    ├── cc.txt
  │    └── bcc.txt
  ├── loop_sendmail.sh
  ├── sendmail.sh
  ├── SingleRecipientMailSender.java
  ├── SmtpMailSender.java
  ├── jakarta.mail-2.0.1.jar
  ├── jakarta.mail-api-2.0.1.jar
  └── jakarta.activation-2.0.1.jar
```

<br>

### `java-cli`

```sh
#!/bin/sh
# sh sendmail.sh

# 메일 일괄 발송(예시)

rm -rf SmtpMailSender.class &&

javac -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SmtpMailSender.java &&
java -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SmtpMailSender
```

<br>

```sh
#!/bin/sh
# sh loop_sendmail.sh

# 메일 개별 발송(예시)

rm -rf SingleRecipientMailSender.class &&

javac -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SingleRecipientMailSender.java &&
java -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SingleRecipientMailSender
```
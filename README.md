### 자바 컴파일 및 실행

```sh
# 패키지 java.mail 삭제 후 실행

# 디렉토리 구조(예시)
/home
  ├── template
  │    └── template.html
  ├── SmtpMailSender.java
  ├── jakarta.mail-2.0.1.jar
  ├── jakarta.mail-api-2.0.1.jar
  └── jakarta.activation-2.0.1.jar

# java-cli(예시)
javac -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SmtpMailSender.java
java -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SmtpMailSender
```
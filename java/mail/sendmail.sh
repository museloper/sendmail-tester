#!/bin/sh

rm -rf SmtpMailSender.class &&

javac -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SmtpMailSender.java &&
java -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SmtpMailSender
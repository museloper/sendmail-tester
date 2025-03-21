#!/bin/sh

rm -rf SingleRecipientMailSender.class &&

javac -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SingleRecipientMailSender.java &&
java -cp ".:/home/jakarta.mail-2.0.1.jar:/home/jakarta.mail-api-2.0.1.jar:/home/jakarta.activation-2.0.1.jar" SingleRecipientMailSender
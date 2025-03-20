import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

# 서버 정보
smtp_server = ""
smtp_port = 25

# 보내는 사람
from_addr = ""

# 받는 사람 리스트 
to_addrs = [
    ""
  , ""
  , ""
]

# 메일 제목
mail_title = "메일 전송 테스트 2025/03/20"

# 참조
cc_addrs = []

# 숨은 참조
bcc_addrs = []

# 최종 수신자
all_recipients = to_addrs + cc_addrs + bcc_addrs

# 메일 메세지 작성
msg = MIMEMultipart('alternative')
msg['From'] = from_addr
msg['To'] = ", ".join(to_addrs)
msg['Cc'] = ", ".join(cc_addrs)
msg['Subject'] = mail_title

html_file_path = "template/mail_template.html"

with open(html_file_path, 'r', encoding='utf-8') as file:
  html_content = file.read()

part = MIMEText(html_content, 'html')

msg.attach(part)

with smtplib.SMTP(smtp_server, smtp_port) as server:
  server.sendmail(from_addr, all_recipients, msg.as_string())

print("메일 전송 완료")
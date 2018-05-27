package br.com.xlabi.service.geral;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class EmailSend {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	public MimeMessageHelper getFrom(MimeMessage mimeMessage, String email, String assuntoEmail, List<File> files)
			throws MessagingException {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		message.setTo(email);
		message.setFrom("contato@xlabi.com.br");
		message.setSubject(assuntoEmail);
		message.setBcc("adrielcordazzo@gmail.com");

		if (files != null) {
			for (File f : files) {
				if (f != null)
					message.addAttachment(f.getName(), f);
			}
		}

		return message;
	}

	
	public boolean createEmail(final String email, final List<File> files, final String subjectEmail,
			final String mensagem) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) {
				MimeMessageHelper message;
				try {
					String assuntoEmail = subjectEmail;
					message = new MimeMessageHelper(mimeMessage, true);
					message.setTo(email);
					message.setFrom("contato@xlabi.com.br");
					message.setSubject(assuntoEmail);

					if (files != null) {
						for (File f : files) {
							if (f != null)
								message.addAttachment(f.getName(), f);
						}
					}

					Map<String, Object> data = new HashMap<String, Object>();
					data.put("assunto", assuntoEmail);
					data.put("comentario", mensagem);

					@SuppressWarnings("deprecation")

					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mail/teste.html", data);
					message.setText(text, true);

				} catch (MessagingException e) {
					e.printStackTrace();
					System.out.println("Erro ao enviar e-mail");
				}
			}
		};
		this.mailSender.send(preparator);
		return true;
	}
}

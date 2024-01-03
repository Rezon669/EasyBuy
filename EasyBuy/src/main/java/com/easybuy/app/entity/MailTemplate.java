package com.easybuy.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class MailTemplate {

	@Id
	@GeneratedValue

	private int templateid;
	private String templatename;
	private String emailsubject;
	private String emailbody;

	public int getTemplateid() {
		return templateid;
	}
	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public String getEmailsubject() {
		return emailsubject;
	}
	public void setEmailsubject(String emailsubject) {
		this.emailsubject = emailsubject;
	}
	public String getEmailbody() {
		return emailbody;
	}
	public void setEmailbody(String emailbody) {
		this.emailbody = emailbody;
	}
	
}

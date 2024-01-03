package com.easybuy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easybuy.app.entity.MailTemplate;

public interface MailTemplateRepository extends JpaRepository<MailTemplate, String> {

	MailTemplate findBytemplatename(String templatename);
}

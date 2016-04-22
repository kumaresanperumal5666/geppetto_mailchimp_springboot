package com.geppetto.mailchimp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geppetto.mailchimp.dto.EmailData;
import com.geppetto.mailchimp.service.EmailService;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 25-August-2015
 *         </p>
 *
 */

@Controller
@RequestMapping(value = "/email")
public class EmailController {

	private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@RequestMapping(value = "/sendSMTPEmail", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public boolean sendSMTPEmail(@RequestBody EmailData emailData) throws Exception {
		LOG.debug("Send SMTP email method has been initialized in the controller layer!");
		return this.emailService.sendSMTPEmail(emailData);
	}
}
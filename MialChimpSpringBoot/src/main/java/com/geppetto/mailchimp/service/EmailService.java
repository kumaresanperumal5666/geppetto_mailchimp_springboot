package com.geppetto.mailchimp.service;

import com.geppetto.mailchimp.dto.EmailData;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 25-August-2015
 *         </p>
 *
 */

public interface EmailService {

	public boolean sendSMTPEmail(EmailData emailData) throws Exception;
}
package com.geppetto.mailchimp.support.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecwid.mailchimp.MailChimpObject;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

public class CampaignOptions extends MailChimpObject {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(CampaignOptions.class);

	@Field
	public String title, list_id, subject, from_name, from_email, to_name;

	public CampaignOptions() {
	}

	public CampaignOptions(String title, String list_id, String subject, String from_name, String from_email,
			String to_name) {
		this.title = title;
		this.list_id = list_id;
		this.subject = subject;
		this.from_name = from_name;
		this.from_email = from_email;
		this.to_name = to_name;
		LOG.debug("Campaign options values has been initiated in the support layer!");
	}
}
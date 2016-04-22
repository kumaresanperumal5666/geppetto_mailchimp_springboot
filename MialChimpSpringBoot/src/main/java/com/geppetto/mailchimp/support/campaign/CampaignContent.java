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

public class CampaignContent extends MailChimpObject {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(CampaignContent.class);

	@Field
	public String html, text;

	public CampaignContent() {
	}

	public CampaignContent(String html, String text) {
		this.html = html;
		this.text = text;
		LOG.debug("Campaign content values has been initiated in the support layer!");
	}
}
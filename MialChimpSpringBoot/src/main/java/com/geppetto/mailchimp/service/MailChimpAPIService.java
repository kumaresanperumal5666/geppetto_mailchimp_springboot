package com.geppetto.mailchimp.service;

import java.util.ArrayList;

import com.ecwid.mailchimp.MailChimpException;
import com.ecwid.mailchimp.method.v1_3.template.TemplatesResult;
import com.ecwid.mailchimp.method.v2_0.lists.BatchSubscribeResult;
import com.ecwid.mailchimp.method.v2_0.lists.MembersResult;
import com.geppetto.mailchimp.dto.Campaign;
import com.geppetto.mailchimp.dto.Subscriber;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

public interface MailChimpAPIService {

	public TemplatesResult findAllTemplates(String apiKey) throws MailChimpException;

	public MembersResult findAllSubscribers(String apiKey, String listId) throws MailChimpException;

	public BatchSubscribeResult subscribeTheList(String apiKey, String listId, ArrayList<Subscriber> subscribers)
			throws MailChimpException;

	public String createCampaign(Campaign campaign) throws MailChimpException;

	public boolean sendTestEmail(String apiKey, String campaignId, ArrayList<String> emailIds)
			throws MailChimpException;

	public boolean sendCampaign(String apiKey, String campaignId, long campaignSno) throws MailChimpException;

	public boolean deleteCampaign(String apiKey, String campaignId) throws MailChimpException;

	public String generateText(String apiKey, String type, String content) throws MailChimpException;
}
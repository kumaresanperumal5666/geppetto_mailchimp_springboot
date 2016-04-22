package com.geppetto.mailchimp.serviceimpl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.MailChimpException;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignCreateMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignDeleteMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignSendNowMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignSendTestMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignType;
import com.ecwid.mailchimp.method.v1_3.helper.GenerateTextFromHtmlMethod;
import com.ecwid.mailchimp.method.v1_3.template.TemplatesMethod;
import com.ecwid.mailchimp.method.v1_3.template.TemplatesMethodTypes;
import com.ecwid.mailchimp.method.v1_3.template.TemplatesResult;
import com.ecwid.mailchimp.method.v2_0.lists.BatchSubscribeInfo;
import com.ecwid.mailchimp.method.v2_0.lists.BatchSubscribeMethod;
import com.ecwid.mailchimp.method.v2_0.lists.BatchSubscribeResult;
import com.ecwid.mailchimp.method.v2_0.lists.Email;
import com.ecwid.mailchimp.method.v2_0.lists.MembersMethod;
import com.ecwid.mailchimp.method.v2_0.lists.MembersResult;
import com.geppetto.mailchimp.dto.Campaign;
import com.geppetto.mailchimp.dto.Subscriber;
import com.geppetto.mailchimp.service.CampaignService;
import com.geppetto.mailchimp.service.MailChimpAPIService;
import com.geppetto.mailchimp.support.campaign.CampaignContent;
import com.geppetto.mailchimp.support.campaign.CampaignOptions;
import com.geppetto.mailchimp.support.list.MergeVars;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Service
public class MailChimpAPIServiceImpl implements MailChimpAPIService {

	private static final Logger LOG = LoggerFactory.getLogger(MailChimpAPIServiceImpl.class);

	@Autowired
	private CampaignService campaignService;

	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	@Override
	public TemplatesResult findAllTemplates(String apiKey) throws MailChimpException {

		TemplatesResult templatesResult = new TemplatesResult();
		try {
			LOG.debug("Find all mailchimp templates method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			TemplatesMethod templatesMethod = new TemplatesMethod();
			TemplatesMethodTypes templatesMethodTypes = new TemplatesMethodTypes();

			templatesMethodTypes.user = true;
			templatesMethodTypes.gallery = false;
			templatesMethodTypes.base = false;

			templatesMethod.apikey = apiKey;
			templatesMethod.types = templatesMethodTypes;

			templatesResult = mailChimpClient.execute(templatesMethod);

			if (templatesResult.user.size() > 0) {
				LOG.debug("Mailchimp templates are found!. Total no.of templates is: " + templatesResult.user.size());
			} else {
				LOG.debug("Mailchimp templates are not found!");
			}

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return templatesResult;
	}

	@Override
	public MembersResult findAllSubscribers(String apiKey, String listId) throws MailChimpException {

		MembersResult membersResult = new MembersResult();
		try {
			LOG.debug("Find all mailchimp subscribers method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			MembersMethod membersMethod = new MembersMethod();

			membersMethod.apikey = apiKey;
			membersMethod.id = listId;

			membersResult = mailChimpClient.execute(membersMethod);

			if (membersResult.total > 0) {
				LOG.debug("Mailchimp subscribers are found!. Total no. of subscribers is: " + membersResult.total);
			} else {
				LOG.debug("Mailchimp subscribers are not found!");
			}

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return membersResult;
	}

	@Override
	public BatchSubscribeResult subscribeTheList(String apiKey, String listId, ArrayList<Subscriber> subscribers)
			throws MailChimpException {

		BatchSubscribeResult batchSubscribeResult = new BatchSubscribeResult();
		try {
			LOG.debug("Mailchimp subscription method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			BatchSubscribeMethod batchSubscribeMethod = new BatchSubscribeMethod();

			ArrayList<BatchSubscribeInfo> batchSubscribers = new ArrayList<BatchSubscribeInfo>();

			for (Subscriber subscriber : subscribers) {
				BatchSubscribeInfo batchSubscribeInfo = new BatchSubscribeInfo();
				batchSubscribeInfo.email = new Email();
				batchSubscribeInfo.email.email = subscriber.getEmailAddress();
				batchSubscribeInfo.email_type = "html";
				batchSubscribeInfo.merge_vars = new MergeVars(subscriber.getFirstName(), subscriber.getLastName(),
						subscriber.getEmailAddress());

				batchSubscribers.add(batchSubscribeInfo);
			}

			LOG.debug("No. Of Subscribers is: " + batchSubscribers.size());

			batchSubscribeMethod.apikey = apiKey;
			batchSubscribeMethod.id = listId;
			batchSubscribeMethod.batch = batchSubscribers;
			batchSubscribeMethod.double_optin = false;
			batchSubscribeMethod.update_existing = true;

			batchSubscribeResult = mailChimpClient.execute(batchSubscribeMethod);

			LOG.debug("Batch subscribe request has been Initiated successfully!");
			LOG.debug("Now checking the subscription status..");
			LOG.debug("New subscribers added count is: " + batchSubscribeResult.add_count);
			LOG.debug("Existing subscribers updated count is: " + batchSubscribeResult.update_count);
			LOG.debug("Error while adding/updating count is: " + batchSubscribeResult.error_count);

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return batchSubscribeResult;
	}

	@Override
	public String createCampaign(Campaign campaign) throws MailChimpException {

		String campaignId = null;
		try {
			LOG.debug("Create campaign in MailChimp method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			CampaignCreateMethod campaignCreateMethod = new CampaignCreateMethod();

			campaignCreateMethod.apikey = campaign.getApiKey();
			campaignCreateMethod.type = CampaignType.valueOf(campaign.getCampaignType());
			campaignCreateMethod.options = new CampaignOptions(campaign.getCampaignTitle(), campaign.getListId(),
					campaign.getEmailSubject(), campaign.getFromName(), campaign.getFromEmail(),
					campaign.getToNameType());
			campaignCreateMethod.content = new CampaignContent(campaign.getEmailTemplate().getModifiedCode(),
					campaign.getEmailTemplate().getExtractedCode());

			campaignId = mailChimpClient.execute(campaignCreateMethod);

			if (campaignId != null && campaignId != "") {
				LOG.debug("Campaign was created successfully in mailchimp!. The Created Campaign ID is: " + campaignId);
			} else {
				LOG.debug("Campaign creation was failed due to some technical issues!");
			}

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return campaignId;
	}

	@Override
	public boolean sendTestEmail(String apiKey, String campaignId, ArrayList<String> emailIds)
			throws MailChimpException {

		boolean emailSent = false;
		try {
			LOG.debug("Send test email method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			CampaignSendTestMethod campaignSendTestMethod = new CampaignSendTestMethod();

			campaignSendTestMethod.apikey = apiKey;
			campaignSendTestMethod.cid = campaignId;
			campaignSendTestMethod.test_emails = emailIds;
			campaignSendTestMethod.send_type = "html";

			emailSent = mailChimpClient.execute(campaignSendTestMethod);

			if (emailSent) {
				LOG.debug("Test email was sent successfully!");
			} else {
				LOG.debug("Test email was sending failed due to some technical issues!");
			}

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return emailSent;
	}

	@Override
	public boolean sendCampaign(String apiKey, String campaignId, long campaignSno) throws MailChimpException {

		boolean campaignSent = false;
		try {
			LOG.debug("Send campaign in MailChimp method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			CampaignSendNowMethod campaignSendNowMethod = new CampaignSendNowMethod();

			campaignSendNowMethod.apikey = apiKey;
			campaignSendNowMethod.cid = campaignId;

			campaignSent = mailChimpClient.execute(campaignSendNowMethod);

			if (campaignSent) {
				LOG.debug("Campaign was sent successfully!");
				this.campaignService.updateEmailStatus(campaignSno, campaignSent);
			} else {
				LOG.debug("Campaign sending was failed due to some technical issues!");
				this.campaignService.updateEmailStatus(campaignSno, campaignSent);
			}

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return campaignSent;
	}

	@Override
	public boolean deleteCampaign(String apiKey, String campaignId) throws MailChimpException {

		boolean deleteCampaign = false;
		try {
			LOG.debug("Delete campaign in MailChimp method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			CampaignDeleteMethod campaignDeleteMethod = new CampaignDeleteMethod();

			campaignDeleteMethod.apikey = apiKey;
			campaignDeleteMethod.cid = campaignId;

			deleteCampaign = mailChimpClient.execute(campaignDeleteMethod);

			if (deleteCampaign) {
				LOG.debug("Campaign was deleted successfully!");
			} else {
				LOG.debug("Campaign deletion was failed due to some technical issues!");
			}

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return deleteCampaign;
	}

	@Override
	public String generateText(String apiKey, String type, String content) throws MailChimpException {

		String generatedText = null;
		try {
			LOG.debug("Generate text from html method has been initialized in the service layer!");

			MailChimpClient mailChimpClient = new MailChimpClient();
			GenerateTextFromHtmlMethod generateTextFromHtmlMethod = new GenerateTextFromHtmlMethod();

			generateTextFromHtmlMethod.apikey = apiKey;
			generateTextFromHtmlMethod.type = type;
			generateTextFromHtmlMethod.content = content;

			generatedText = mailChimpClient.execute(generateTextFromHtmlMethod);

			if (generatedText != null && generatedText != "") {
				LOG.debug("Text was generated successfully!");
			} else {
				LOG.debug("Text generation was failed due to some technical issues!");
			}

			mailChimpClient.close();
		} catch (Exception e) {
			LOG.debug("Exception in service layer: " + e.getMessage());
		}
		return generatedText;
	}
}
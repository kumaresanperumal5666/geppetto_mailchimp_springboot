package com.geppetto.mailchimp.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecwid.mailchimp.method.v1_3.template.TemplatesResult;
import com.ecwid.mailchimp.method.v2_0.lists.BatchSubscribeResult;
import com.ecwid.mailchimp.method.v2_0.lists.MembersResult;
import com.geppetto.mailchimp.dto.SubscribeData;
import com.geppetto.mailchimp.service.MailChimpAPIService;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Controller
@RequestMapping(value = "/mailchimpapi")
public class MailChimpAPIController {

	private static final Logger LOG = LoggerFactory.getLogger(MailChimpAPIController.class);

	@Autowired
	private MailChimpAPIService mailChimpAPIService;

	public void setMailChimpAPIService(MailChimpAPIService mailChimpAPIService) {
		this.mailChimpAPIService = mailChimpAPIService;
	}

	@RequestMapping(value = "/findAllMailChimpTemplates", method = RequestMethod.GET)
	@ResponseBody
	public TemplatesResult findAllMailChimpTemplates(@RequestParam("api_key") String apiKey) throws Exception {
		LOG.debug("Find all mailchimp subscribers method has been initialized in the controller layer!");
		return this.mailChimpAPIService.findAllTemplates(apiKey);
	}

	@RequestMapping(value = "/findAllMailChimpSubscribers", method = RequestMethod.GET)
	@ResponseBody
	public MembersResult findAllMailChimpSubscribers(@RequestParam("api_key") String apiKey,
			@RequestParam("list_id") String listId) throws Exception {
		LOG.debug("Find all mailchimp subscribers method has been initialized in the controller layer!");
		return this.mailChimpAPIService.findAllSubscribers(apiKey, listId);
	}

	@RequestMapping(value = "/subscribeTheList", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public BatchSubscribeResult subscribeTheList(@RequestBody SubscribeData subscribeData) throws Exception {
		LOG.debug("Mailchimp subscription method has been initialized in the controller layer!");
		return this.mailChimpAPIService.subscribeTheList(subscribeData.getApiKey(), subscribeData.getListId(),
				subscribeData.getSubscribers());
	}

	@RequestMapping(value = "/sendTestEmail", method = RequestMethod.GET)
	@ResponseBody
	public Boolean sendTestEmail(@RequestParam("api_key") String apiKey, @RequestParam("campaign_id") String campaignId,
			@RequestParam("emailIds") ArrayList<String> emailIds) throws Exception {
		LOG.debug("Send test email method has been initialized in the controller layer!");
		return this.mailChimpAPIService.sendTestEmail(apiKey, campaignId, emailIds);
	}

	@RequestMapping(value = "/sendCampaign", method = RequestMethod.GET)
	@ResponseBody
	public Boolean sendCampaign(@RequestParam("api_key") String apiKey, @RequestParam("campaign_id") String campaignId,
			@RequestParam("campaign_sno") long campaignSno) throws Exception {
		LOG.debug("Send campaign in MailChimp method has been initialized in the controller layer!");
		return this.mailChimpAPIService.sendCampaign(apiKey, campaignId, campaignSno);
	}
}
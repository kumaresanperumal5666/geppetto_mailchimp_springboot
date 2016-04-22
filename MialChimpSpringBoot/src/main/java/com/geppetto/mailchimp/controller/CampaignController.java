package com.geppetto.mailchimp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geppetto.mailchimp.dto.Campaign;
import com.geppetto.mailchimp.service.CampaignService;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Controller
@RequestMapping(value = "/campaign")
public class CampaignController {

	private static final Logger LOG = LoggerFactory.getLogger(CampaignController.class);

	@Autowired
	private CampaignService campaignService;

	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	@RequestMapping(value = "/findAllCampaigns", method = RequestMethod.GET)
	@ResponseBody
	public List<Campaign> findAllCampaigns() throws Exception {
		LOG.debug("Find all campaigns method has been initialized in the controller layer!");
		return this.campaignService.findAllCampaigns();
	}

	@RequestMapping(value = "/findCampaign", method = RequestMethod.GET)
	@ResponseBody
	public Campaign findCampaign(@RequestParam("campaignSno") long campaignSno) throws Exception {
		LOG.debug("Find campaign method has been initialized in the controller layer!");
		return this.campaignService.findCampaign(campaignSno);
	}

	@RequestMapping(value = "/createCampaign", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Campaign createCampaign(@RequestBody Campaign campaign) throws Exception {
		LOG.debug("Create campaign method has been initialized in the controller layer!");

		campaign.setCreatedBy(9999);
		campaign.setUpdatedBy(9999);
		return this.campaignService.createCampaign(campaign);
	}

	@RequestMapping(value = "/updateCampaign", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Campaign updateCampaign(@RequestBody Campaign campaign) throws Exception {
		LOG.debug("Update campaign method has been initialized in the controller layer!");

		campaign.setUpdatedBy(9999);
		return this.campaignService.updateCampaign(campaign);
	}

	@RequestMapping(value = "/deleteCampaign", method = RequestMethod.GET)
	@ResponseBody
	public boolean deleteCampaign(@RequestParam("campaignSno") long campaignSno) throws Exception {
		LOG.debug("Delete campaign method has been initialized in the controller layer!");
		return this.campaignService.deleteCampaign(campaignSno);
	}
}
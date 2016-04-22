package com.geppetto.mailchimp.service;

import java.util.ArrayList;

import com.geppetto.mailchimp.dto.Campaign;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

public interface CampaignService {

	public ArrayList<Campaign> findAllCampaigns() throws Exception;

	public Campaign findCampaign(long campaignSno) throws Exception;

	public Campaign createCampaign(Campaign campaign) throws Exception;

	public Campaign updateCampaign(Campaign campaign) throws Exception;

	public boolean deleteCampaign(long campaignSno) throws Exception;

	public boolean updateEmailStatus(long campaignSno, boolean emailStatus) throws Exception;
}
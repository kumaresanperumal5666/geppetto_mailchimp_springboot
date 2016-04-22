package com.geppetto.mailchimp.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.geppetto.mailchimp.dao.CampaignDao;
import com.geppetto.mailchimp.dto.Campaign;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Repository
@Transactional
public class CampaignDaoImpl implements CampaignDao {

	private static final Logger LOG = LoggerFactory.getLogger(CampaignDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${findAllCampaigns}")
	private String findAllCampaigns;

	@Value("${findCampaign}")
	private String findCampaign;

	@Value("${createCampaign}")
	private String createCampaign;

	@Value("${updateCampaign}")
	private String updateCampaign;

	@Value("${deleteCampaign}")
	private String deleteCampaign;

	@Value("${updateEmailStatus}")
	private String updateEmailStatus;

	@Value("${lastIndex}")
	private String lastIndex;

	@Override
	@Transactional
	public ArrayList<Campaign> findAllCampaigns() throws Exception {
		try {
			LOG.debug("Find all campaigns method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(findAllCampaigns, "CampaignResultSet");
			@SuppressWarnings("unchecked")
			List<Campaign> campaignsList = sql.getResultList();

			if (campaignsList.size() > 0) {
				LOG.debug("All campaigns are found!. Total no. of campaigns is: " + campaignsList.size());
			} else {
				LOG.debug("Campaigns are not found!");
			}

			return (ArrayList<Campaign>) campaignsList;
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public Campaign findCampaign(long campaignSno) throws Exception {
		try {
			LOG.debug("Find campaign method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(findCampaign, Campaign.class).setParameter("campaign_sno",
					campaignSno);
			Campaign campaign = (Campaign) sql.getSingleResult();

			if (campaign != null) {
				LOG.debug("The campaign was found!");
			} else {
				LOG.debug("The campaign was not found!");
			}

			return campaign;
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public Campaign createCampaign(Campaign campaign) throws Exception {
		try {
			LOG.debug("Create campaign method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(createCampaign)
					.setParameter("campaign_id", campaign.getCampaignId())
					.setParameter("campaign_title", campaign.getCampaignTitle())
					.setParameter("campaign_label", campaign.getCampaignLabel())
					.setParameter("campaign_description", campaign.getCampaignDescription())
					.setParameter("campaign_type", campaign.getCampaignType())
					.setParameter("email_subject", campaign.getEmailSubject())
					.setParameter("from_name", campaign.getFromName())
					.setParameter("from_email", campaign.getFromEmail())
					.setParameter("to_name_type", campaign.getToNameType())
					.setParameter("email_type", campaign.getEmailType())
					.setParameter("email_status", campaign.isEmailStatus())
					.setParameter("template_id", campaign.getEmailTemplate().getTemplateSno())
					.setParameter("api_key", campaign.getApiKey()).setParameter("list_id", campaign.getListId())
					.setParameter("created_by", campaign.getCreatedBy())
					.setParameter("created_date", campaign.getCreatedDate())
					.setParameter("updated_by", campaign.getUpdatedBy())
					.setParameter("updated_date", campaign.getUpdatedDate());
			sql.executeUpdate();

			sql = entityManager.createNativeQuery(lastIndex);
			BigInteger campaignSno = (BigInteger) sql.getSingleResult();

			campaign.setCampaignSno(Long.valueOf(campaignSno.toString()));

			if (campaign.getCampaignSno() != 0L) {
				LOG.debug("Campaign was created successfully!. Created Campaign's ID is: " + campaign.getCampaignSno());
			} else {
				LOG.debug("Campaign creation was failed!");
			}
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return campaign;
	}

	@Override
	@Transactional
	public Campaign updateCampaign(Campaign campaign) throws Exception {
		try {
			LOG.debug("Update campaign method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(updateCampaign)
					.setParameter("campaign_id", campaign.getCampaignId())
					.setParameter("campaign_title", campaign.getCampaignTitle())
					.setParameter("campaign_label", campaign.getCampaignLabel())
					.setParameter("campaign_description", campaign.getCampaignDescription())
					.setParameter("campaign_type", campaign.getCampaignType())
					.setParameter("email_subject", campaign.getEmailSubject())
					.setParameter("from_name", campaign.getFromName())
					.setParameter("from_email", campaign.getFromEmail())
					.setParameter("to_name_type", campaign.getToNameType())
					.setParameter("email_type", campaign.getEmailType())
					.setParameter("email_status", campaign.isEmailStatus())
					.setParameter("template_id", campaign.getEmailTemplate().getTemplateSno())
					.setParameter("api_key", campaign.getApiKey()).setParameter("list_id", campaign.getListId())
					.setParameter("created_by", campaign.getCreatedBy())
					.setParameter("created_date", campaign.getCreatedDate())
					.setParameter("updated_by", campaign.getUpdatedBy())
					.setParameter("updated_date", campaign.getUpdatedDate())
					.setParameter("campaign_sno", campaign.getCampaignSno());
			sql.executeUpdate();

			LOG.debug("Campaign was updated successfully!. Updated Campaign's ID is: " + campaign.getCampaignSno());

		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return campaign;
	}

	@Override
	@Transactional
	public boolean deleteCampaign(long campaignSno) throws Exception {
		try {
			LOG.debug("Delete campaign method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(deleteCampaign).setParameter("campaign_sno", campaignSno);

			int result = sql.executeUpdate();

			if (result == 1) {
				LOG.debug("Campaign was deleted successfully!. Deleted Campaign's ID is: " + campaignSno);
				return true;
			} else {
				LOG.debug("Campaign deletion was failed!");
				return false;
			}
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateEmailStatus(long campaignSno, boolean emailStatus) throws Exception {
		try {
			LOG.debug("Update email status method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(updateEmailStatus).setParameter("email_status", emailStatus)
					.setParameter("campaign_sno", campaignSno);

			int result = sql.executeUpdate();

			if (result == 1) {
				LOG.debug("Email status was updated successfully!.  Updated email status of Campaign's ID is: "
						+ campaignSno);
				return true;
			} else {
				LOG.debug("Email status updation was failed!");
				return false;
			}
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return false;
	}
}
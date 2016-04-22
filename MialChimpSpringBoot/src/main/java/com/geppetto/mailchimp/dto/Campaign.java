package com.geppetto.mailchimp.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Entity
@Table(name = "campaigns")
@SqlResultSetMapping(name = "CampaignResultSet", entities = { @EntityResult(entityClass = Campaign.class) })
public class Campaign {

	public Campaign() {
	}

	public Campaign(long campaignSno, String campaignId, String campaignTitle, String campaignLabel,
			String campaignDescription, String campaignType, String emailSubject, String fromName, String fromEmail,
			String toNameType, String emailType, boolean emailStatus, String apiKey, String listId, long createdBy,
			Date createdDate, long updatedBy, Date updatedDate) {
		this.campaignSno = campaignSno;
		this.campaignId = campaignId;
		this.campaignTitle = campaignTitle;
		this.campaignLabel = campaignLabel;
		this.campaignDescription = campaignDescription;
		this.campaignType = campaignType;
		this.emailSubject = emailSubject;
		this.fromName = fromName;
		this.fromEmail = fromEmail;
		this.toNameType = toNameType;
		this.emailType = emailType;
		this.emailStatus = emailStatus;
		this.apiKey = apiKey;
		this.listId = listId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "campaign_sno")
	private long campaignSno;

	@Column(name = "campaign_id")
	private String campaignId;

	@Column(name = "campaign_title")
	private String campaignTitle;

	@Column(name = "campaign_label")
	private String campaignLabel;

	@Column(name = "campaign_description")
	private String campaignDescription;

	@Column(name = "campaign_type")
	private String campaignType;

	@Column(name = "email_subject")
	private String emailSubject;

	@Column(name = "from_name")
	private String fromName;

	@Column(name = "from_email")
	private String fromEmail;

	@Column(name = "to_name_type")
	private String toNameType;

	@Column(name = "email_type")
	private String emailType;

	@Column(name = "email_status")
	private boolean emailStatus;

	@OneToOne
	@JoinColumn(name = "template_id")
	private Template emailTemplate;

	@Column(name = "api_key")
	private String apiKey;

	@Column(name = "list_id")
	private String listId;

	@Column(name = "created_by")
	private long createdBy;

	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(name = "updated_by")
	private long updatedBy;

	@Column(name = "updated_date")
	@Temporal(TemporalType.DATE)
	private Date updatedDate;

	public long getCampaignSno() {
		return campaignSno;
	}

	public void setCampaignSno(long campaignSno) {
		this.campaignSno = campaignSno;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignTitle() {
		return campaignTitle;
	}

	public void setCampaignTitle(String campaignTitle) {
		this.campaignTitle = campaignTitle;
	}

	public String getCampaignLabel() {
		return campaignLabel;
	}

	public void setCampaignLabel(String campaignLabel) {
		this.campaignLabel = campaignLabel;
	}

	public String getCampaignDescription() {
		return campaignDescription;
	}

	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToNameType() {
		return toNameType;
	}

	public void setToNameType(String toNameType) {
		this.toNameType = toNameType;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public boolean isEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(boolean emailStatus) {
		this.emailStatus = emailStatus;
	}

	public Template getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(Template emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Campaign [campaignSno=" + campaignSno + ", campaignId=" + campaignId + ", campaignTitle="
				+ campaignTitle + ", campaignLabel=" + campaignLabel + ", campaignDescription=" + campaignDescription
				+ ", campaignType=" + campaignType + ", emailSubject=" + emailSubject + ", fromName=" + fromName
				+ ", fromEmail=" + fromEmail + ", toNameType=" + toNameType + ", emailType=" + emailType
				+ ", emailStatus=" + emailStatus + ", emailTemplate=" + emailTemplate + ", apiKey=" + apiKey
				+ ", listId=" + listId + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}
}
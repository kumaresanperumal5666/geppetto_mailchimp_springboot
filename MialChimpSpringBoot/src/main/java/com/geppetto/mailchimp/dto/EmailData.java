package com.geppetto.mailchimp.dto;

import java.util.Arrays;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 26-August-2015
 *         </p>
 *
 */

public class EmailData {
	private String[] receipiants;
	private String emailSubject;
	private String fromName;
	private String fromEmail;
	private long baseTemplateId;
	private long templateSno;
	private String bodyHeader;
	private String bodySubject;
	private String bodyFooter;
	private String bodyContent;

	public String[] getReceipiants() {
		return receipiants;
	}

	public void setReceipiants(String[] receipiants) {
		this.receipiants = receipiants;
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

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public long getBaseTemplateId() {
		return baseTemplateId;
	}

	public void setBaseTemplateId(long baseTemplateId) {
		this.baseTemplateId = baseTemplateId;
	}

	public long getTemplateSno() {
		return templateSno;
	}

	public void setTemplateSno(long templateSno) {
		this.templateSno = templateSno;
	}

	public String getBodyHeader() {
		return bodyHeader;
	}

	public void setBodyHeader(String bodyHeader) {
		this.bodyHeader = bodyHeader;
	}

	public String getBodySubject() {
		return bodySubject;
	}

	public void setBodySubject(String bodySubject) {
		this.bodySubject = bodySubject;
	}

	public String getBodyFooter() {
		return bodyFooter;
	}

	public void setBodyFooter(String bodyFooter) {
		this.bodyFooter = bodyFooter;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	@Override
	public String toString() {
		return "EmailData [receipiants=" + Arrays.toString(receipiants) + ", emailSubject=" + emailSubject
				+ ", fromName=" + fromName + ", fromEmail=" + fromEmail + ", baseTemplateId=" + baseTemplateId
				+ ", templateSno=" + templateSno + ", bodyHeader=" + bodyHeader + ", bodySubject=" + bodySubject
				+ ", bodyFooter=" + bodyFooter + ", bodyContent=" + bodyContent + "]";
	}
}
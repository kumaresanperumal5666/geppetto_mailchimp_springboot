package com.geppetto.mailchimp.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "templates")
@SqlResultSetMapping(name = "TemplateResultSet", classes = {
		@ConstructorResult(targetClass = Template.class, columns = {
				@ColumnResult(name = "template_sno", type = Long.class),
				@ColumnResult(name = "base_template_id", type = Long.class),
				@ColumnResult(name = "template_name", type = String.class),
				@ColumnResult(name = "body_header", type = String.class),
				@ColumnResult(name = "body_subject", type = String.class),
				@ColumnResult(name = "body_content", type = String.class),
				@ColumnResult(name = "body_footer", type = String.class),
				@ColumnResult(name = "source_code", type = String.class),
				@ColumnResult(name = "modified_code", type = String.class),
				@ColumnResult(name = "extracted_code", type = String.class),
				@ColumnResult(name = "created_by", type = Long.class),
				@ColumnResult(name = "created_date", type = Date.class),
				@ColumnResult(name = "updated_by", type = Long.class),
				@ColumnResult(name = "updated_date", type = Date.class) }) })
public class Template {

	public Template() {
	}

	public Template(long templateSno, long baseTemplateId, String templateName, String bodyHeader, String bodySubject,
			String bodyContent, String bodyFooter, String sourceCode, String modifiedCode, String extractedCode,
			long createdBy, Date createdDate, long updatedBy, Date updatedDate) {
		this.templateSno = templateSno;
		this.baseTemplateId = baseTemplateId;
		this.templateName = templateName;
		this.bodyHeader = bodyHeader;
		this.bodySubject = bodySubject;
		this.bodyContent = bodyContent;
		this.bodyFooter = bodyFooter;
		this.sourceCode = sourceCode;
		this.modifiedCode = modifiedCode;
		this.extractedCode = extractedCode;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "template_sno")
	private long templateSno;

	@Column(name = "base_template_id")
	private long baseTemplateId;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "body_header", columnDefinition = "TEXT")
	private String bodyHeader;

	@Column(name = "body_subject", columnDefinition = "TEXT")
	private String bodySubject;

	@Column(name = "body_content", columnDefinition = "TEXT")
	private String bodyContent;

	@Column(name = "body_footer", columnDefinition = "TEXT")
	private String bodyFooter;

	@Column(name = "source_code", columnDefinition = "TEXT")
	private String sourceCode;

	@Column(name = "modified_code", columnDefinition = "TEXT")
	private String modifiedCode;

	@Column(name = "extracted_code", columnDefinition = "TEXT")
	private String extractedCode;

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

	public long getTemplateSno() {
		return templateSno;
	}

	public void setTemplateSno(long templateSno) {
		this.templateSno = templateSno;
	}

	public long getBaseTemplateId() {
		return baseTemplateId;
	}

	public void setBaseTemplateId(long baseTemplateId) {
		this.baseTemplateId = baseTemplateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getBodyFooter() {
		return bodyFooter;
	}

	public void setBodyFooter(String bodyFooter) {
		this.bodyFooter = bodyFooter;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getModifiedCode() {
		return modifiedCode;
	}

	public void setModifiedCode(String modifiedCode) {
		this.modifiedCode = modifiedCode;
	}

	public String getExtractedCode() {
		return extractedCode;
	}

	public void setExtractedCode(String extractedCode) {
		this.extractedCode = extractedCode;
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
		return "Template [templateSno=" + templateSno + ", baseTemplateId=" + baseTemplateId + ", templateName="
				+ templateName + ", bodyHeader=" + bodyHeader + ", bodySubject=" + bodySubject + ", bodyContent="
				+ bodyContent + ", bodyFooter=" + bodyFooter + ", sourceCode=" + sourceCode + ", modifiedCode="
				+ modifiedCode + ", extractedCode=" + extractedCode + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
}
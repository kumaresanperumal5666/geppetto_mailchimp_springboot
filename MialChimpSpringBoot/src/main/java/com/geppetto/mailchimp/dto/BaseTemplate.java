package com.geppetto.mailchimp.dto;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Entity
@Table(name = "base_templates")
@SqlResultSetMapping(name = "BaseTemplateResultSet", classes = {
		@ConstructorResult(targetClass = BaseTemplate.class, columns = {
				@ColumnResult(name = "template_sno", type = Long.class),
				@ColumnResult(name = "template_name", type = String.class),
				@ColumnResult(name = "source_code", type = String.class) }) })
public class BaseTemplate {

	public BaseTemplate() {
	}

	public BaseTemplate(long templateSno, String templateName, String sourceCode) {
		this.templateSno = templateSno;
		this.templateName = templateName;
		this.sourceCode = sourceCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "template_sno")
	private long templateSno;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "source_code", columnDefinition = "TEXT")
	private String sourceCode;

	public long getTemplateSno() {
		return templateSno;
	}

	public void setTemplateSno(long templateSno) {
		this.templateSno = templateSno;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Override
	public String toString() {
		return "BaseTemplate [templateSno=" + templateSno + ", templateName=" + templateName + ", sourceCode="
				+ sourceCode + "]";
	}
}
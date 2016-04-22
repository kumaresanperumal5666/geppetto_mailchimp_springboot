package com.geppetto.mailchimp.service;

import java.util.ArrayList;

import com.geppetto.mailchimp.dto.BaseTemplate;
import com.geppetto.mailchimp.dto.Template;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

public interface TemplateService {

	public ArrayList<BaseTemplate> findAllBaseTemplates() throws Exception;

	public BaseTemplate findBaseTemplate(long templateSno) throws Exception;

	public ArrayList<Template> findAllTemplates() throws Exception;

	public Template findTemplate(long templateSno) throws Exception;

	public Template createTemplate(Template template, String apiKey) throws Exception;

	public Template updateTemplate(Template template, String apiKey) throws Exception;

	public boolean deleteTemplate(long templateSno) throws Exception;
}
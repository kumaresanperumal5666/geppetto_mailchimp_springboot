package com.geppetto.mailchimp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geppetto.mailchimp.dto.BaseTemplate;
import com.geppetto.mailchimp.dto.Template;
import com.geppetto.mailchimp.service.TemplateService;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Controller
@RequestMapping(value = "/template")
public class TemplateController {

	private static final Logger LOG = LoggerFactory.getLogger(TemplateController.class);

	@Autowired
	private TemplateService templateService;

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	@RequestMapping(value = "/findAllTemplates", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findAllTemplates() throws Exception {
		LOG.debug("Find all templates method has been initialized in the controller layer!");
		Map<String, Object> allTemplates = new HashMap<String, Object>();

		List<BaseTemplate> baseTemplates = this.templateService.findAllBaseTemplates();
		allTemplates.put("baseTemplates", baseTemplates);
		List<Template> customTemplates = this.templateService.findAllTemplates();
		allTemplates.put("customTemplates", customTemplates);

		return allTemplates;
	}
}
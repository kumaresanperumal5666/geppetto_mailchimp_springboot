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

import com.geppetto.mailchimp.dao.TemplateDao;
import com.geppetto.mailchimp.dto.BaseTemplate;
import com.geppetto.mailchimp.dto.Template;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Repository
@Transactional
public class TemplateDaoImpl implements TemplateDao {

	private static final Logger LOG = LoggerFactory.getLogger(TemplateDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${findAllBaseTemplates}")
	private String findAllBaseTemplates;

	@Value("${findBaseTemplate}")
	private String findBaseTemplate;

	@Value("${findAllTemplates}")
	private String findAllTemplates;

	@Value("${findTemplate}")
	private String findTemplate;

	@Value("${createTemplate}")
	private String createTemplate;

	@Value("${updateTemplate}")
	private String updateTemplate;

	@Value("${deleteTemplate}")
	private String deleteTemplate;

	@Value("${lastIndex}")
	private String lastIndex;

	@Override
	@Transactional
	public ArrayList<BaseTemplate> findAllBaseTemplates() throws Exception {
		try {
			LOG.debug("Find all base templates method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(findAllBaseTemplates, "BaseTemplateResultSet");
			@SuppressWarnings("unchecked")
			List<BaseTemplate> baseTemplatesList = sql.getResultList();

			if (baseTemplatesList.size() > 0) {
				LOG.debug("All the base templates are found!. Total no. of base templates is: "
						+ baseTemplatesList.size());
			} else {
				LOG.debug("Base templates are not found!");
			}
			return (ArrayList<BaseTemplate>) baseTemplatesList;
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public BaseTemplate findBaseTemplate(long templateSno) throws Exception {
		try {
			LOG.debug("Find base template method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(findBaseTemplate, BaseTemplate.class)
					.setParameter("template_sno", templateSno);
			BaseTemplate baseTemplate = (BaseTemplate) sql.getSingleResult();

			if (baseTemplate != null) {
				LOG.debug("The base template was found!");
			} else {
				LOG.debug("The base template was not found!");
			}

			return baseTemplate;
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public ArrayList<Template> findAllTemplates() throws Exception {
		try {
			LOG.debug("Find all templates method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(findAllTemplates, "TemplateResultSet");
			@SuppressWarnings("unchecked")
			List<Template> templatesList = sql.getResultList();

			if (templatesList.size() > 0) {
				LOG.debug("All the templates are found!. Total no. of templates is: " + templatesList.size());
			} else {
				LOG.debug("Templates are not found!");
			}
			return (ArrayList<Template>) templatesList;
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public Template findTemplate(long templateSno) throws Exception {
		try {
			LOG.debug("Find template method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(findTemplate, Template.class).setParameter("template_sno",
					templateSno);
			Template template = (Template) sql.getSingleResult();

			if (template != null) {
				LOG.debug("The template was found!");
			} else {
				LOG.debug("The template was not found!");
			}

			return template;
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public Template createTemplate(Template template) throws Exception {
		try {
			LOG.debug("Create template method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(createTemplate)
					.setParameter("base_template_id", template.getBaseTemplateId())
					.setParameter("template_name", template.getTemplateName())
					.setParameter("body_header", template.getBodyHeader())
					.setParameter("body_subject", template.getBodySubject())
					.setParameter("body_content", template.getBodyContent())
					.setParameter("body_footer", template.getBodyFooter())
					.setParameter("source_code", template.getSourceCode())
					.setParameter("modified_code", template.getModifiedCode())
					.setParameter("extracted_code", template.getExtractedCode())
					.setParameter("created_by", template.getCreatedBy())
					.setParameter("created_date", template.getCreatedDate())
					.setParameter("updated_by", template.getUpdatedBy())
					.setParameter("updated_date", template.getUpdatedDate());
			sql.executeUpdate();

			sql = entityManager.createNativeQuery(lastIndex);
			BigInteger templateSno = (BigInteger) sql.getSingleResult();

			template.setTemplateSno(Long.valueOf(templateSno.toString()));

			if (template.getTemplateSno() != 0L) {
				LOG.debug(
						"Template was created successfully!. Template Campaign's ID is: " + template.getTemplateSno());
			} else {
				LOG.debug("Template creation was failed!");
			}
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return template;
	}

	@Override
	@Transactional
	public Template updateTemplate(Template template) throws Exception {
		try {
			LOG.debug("Update template method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(updateTemplate)
					.setParameter("base_template_id", template.getBaseTemplateId())
					.setParameter("template_name", template.getTemplateName())
					.setParameter("body_header", template.getBodyHeader())
					.setParameter("body_subject", template.getBodySubject())
					.setParameter("body_content", template.getBodyContent())
					.setParameter("body_footer", template.getBodyFooter())
					.setParameter("source_code", template.getSourceCode())
					.setParameter("modified_code", template.getModifiedCode())
					.setParameter("extracted_code", template.getExtractedCode())
					.setParameter("created_by", template.getCreatedBy())
					.setParameter("created_date", template.getCreatedDate())
					.setParameter("updated_by", template.getUpdatedBy())
					.setParameter("updated_date", template.getUpdatedDate())
					.setParameter("template_sno", template.getTemplateSno());
			sql.executeUpdate();

			LOG.debug("Template was updated successfully!. Updated Template's ID is: " + template.getTemplateSno());

		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deleteTemplate(long templateSno) throws Exception {
		try {
			LOG.debug("Delete template method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(deleteTemplate).setParameter("template_sno", templateSno);

			int result = sql.executeUpdate();

			if (result == 1) {
				LOG.debug("Template was deleted successfully!. Deleted Template's ID is: " + templateSno);
				return true;
			} else {
				LOG.debug("Template deletion was failed!");
				return false;
			}
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return false;
	}
}
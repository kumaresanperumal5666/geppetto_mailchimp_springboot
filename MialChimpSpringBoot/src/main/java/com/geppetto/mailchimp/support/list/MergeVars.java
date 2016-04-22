package com.geppetto.mailchimp.support.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecwid.mailchimp.MailChimpObject;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

public class MergeVars extends MailChimpObject {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(MergeVars.class);

	@Field
	public String FNAME, LNAME, EMAIL;

	public MergeVars() {
	}

	public MergeVars(String fname, String lname, String email) {
		this.FNAME = fname;
		this.LNAME = lname;
		this.EMAIL = email;
		LOG.debug("Merge vars values has been initiated in the support layer!");
	}
}
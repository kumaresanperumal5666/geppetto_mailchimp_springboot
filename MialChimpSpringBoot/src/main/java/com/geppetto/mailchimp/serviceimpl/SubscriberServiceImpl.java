package com.geppetto.mailchimp.serviceimpl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geppetto.mailchimp.dao.SubscriberDao;
import com.geppetto.mailchimp.dto.Subscriber;
import com.geppetto.mailchimp.service.SubscriberService;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Service
@Transactional
public class SubscriberServiceImpl implements SubscriberService {

	private static final Logger LOG = LoggerFactory.getLogger(SubscriberServiceImpl.class);

	@Autowired
	private SubscriberDao subscriberDao;

	public void setSubscriberDao(SubscriberDao subscriberDao) {
		this.subscriberDao = subscriberDao;
	}

	@Override
	@Transactional
	public ArrayList<Subscriber> findAllSubscribers() throws Exception {
		LOG.debug("Find all subscribers method has been initialized in the service layer!");
		return this.subscriberDao.findAllSubscribers();
	}

	@Override
	@Transactional
	public Subscriber findSubscriber(long subscriberSno) throws Exception {
		LOG.debug("Find subscriber method has been initialized in the service layer!");
		return this.subscriberDao.findSubscriber(subscriberSno);
	}

	@Override
	@Transactional
	public Subscriber createSubscriber(Subscriber subscriber) throws Exception {
		LOG.debug("Create subscriber method has been initialized in the service layer!");
		return this.subscriberDao.createSubscriber(subscriber);
	}

	@Override
	@Transactional
	public Subscriber updateSubscriber(Subscriber subscriber) throws Exception {
		LOG.debug("Update subscriber method has been initialized in the service layer!");
		return this.subscriberDao.updateSubscriber(subscriber);
	}

	@Override
	@Transactional
	public boolean deleteSubscriber(long subscriberSno) throws Exception {
		LOG.debug("Delete subscriber method has been initialized in the service layer!");
		return this.subscriberDao.deleteSubscriber(subscriberSno);
	}
}
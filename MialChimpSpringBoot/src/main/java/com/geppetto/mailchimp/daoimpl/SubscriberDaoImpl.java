package com.geppetto.mailchimp.daoimpl;

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

import com.geppetto.mailchimp.dao.SubscriberDao;
import com.geppetto.mailchimp.dto.Subscriber;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

@Repository
@Transactional
public class SubscriberDaoImpl implements SubscriberDao {

	private static final Logger LOG = LoggerFactory.getLogger(SubscriberDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${findAllSubscribers}")
	private String findAllSubscribers;

	@Override
	@Transactional
	public ArrayList<Subscriber> findAllSubscribers() throws Exception {
		try {
			LOG.debug("Find all subscribers method has been initialized in the dao layer!");

			Query sql = entityManager.createNativeQuery(findAllSubscribers, "SubscriberResultSet");
			@SuppressWarnings("unchecked")
			List<Subscriber> subscribersList = sql.getResultList();

			if (subscribersList.size() > 0) {
				LOG.debug("All subscribers are found!. Total no. of subscribers is: " + subscribersList.size());
			} else {
				LOG.debug("Subscribers are not found!");
			}
			return (ArrayList<Subscriber>) subscribersList;
		} catch (Exception e) {
			LOG.debug("Exception in dao layer: " + e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public Subscriber findSubscriber(long subscriberSno) throws Exception {
		LOG.debug("Find subscriber method has been initialized in the dao layer!");
		return null;
	}

	@Override
	@Transactional
	public Subscriber createSubscriber(Subscriber subscriber) throws Exception {
		LOG.debug("Create subscriber method has been initialized in the dao layer!");
		return null;
	}

	@Override
	@Transactional
	public Subscriber updateSubscriber(Subscriber subscriber) throws Exception {
		LOG.debug("Update subscriber method has been initialized in the dao layer!");
		return null;
	}

	@Override
	@Transactional
	public boolean deleteSubscriber(long subscriberSno) throws Exception {
		LOG.debug("Delete subscriber method has been initialized in the dao layer!");
		return false;
	}
}
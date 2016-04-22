package com.geppetto.mailchimp.dao;

import java.util.ArrayList;

import com.geppetto.mailchimp.dto.Subscriber;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

public interface SubscriberDao {

	public ArrayList<Subscriber> findAllSubscribers() throws Exception;

	public Subscriber findSubscriber(long subscriberSno) throws Exception;

	public Subscriber createSubscriber(Subscriber subscriber) throws Exception;

	public Subscriber updateSubscriber(Subscriber subscriber) throws Exception;

	public boolean deleteSubscriber(long subscriberSno) throws Exception;
}
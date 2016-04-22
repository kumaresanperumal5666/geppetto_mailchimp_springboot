package com.geppetto.mailchimp.dto;

import java.util.ArrayList;

/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 *
 */

public class SubscribeData {
	private String apiKey;
	private String listId;
	private ArrayList<Subscriber> subscribers;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public ArrayList<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(ArrayList<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public String toString() {
		return "SubscribeData [apiKey=" + apiKey + ", listId=" + listId + ", subscribers=" + subscribers + "]";
	}
}
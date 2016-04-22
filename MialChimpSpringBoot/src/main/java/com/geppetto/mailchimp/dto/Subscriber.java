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
@Table(name = "subscribers")
@SqlResultSetMapping(name = "SubscriberResultSet", classes = {
		@ConstructorResult(targetClass = Subscriber.class, columns = {
				@ColumnResult(name = "subscriber_sno", type = Long.class),
				@ColumnResult(name = "first_name", type = String.class),
				@ColumnResult(name = "last_name", type = String.class),
				@ColumnResult(name = "email_address", type = String.class) }) })
public class Subscriber {

	public Subscriber() {
	}

	public Subscriber(long subscriberSno, String firstName, String lastName, String emailAddress) {
		this.subscriberSno = subscriberSno;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subscriber_sno")
	private long subscriberSno;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_address")
	private String emailAddress;

	public long getSubscriberSno() {
		return subscriberSno;
	}

	public void setSubscriberSno(long subscriberSno) {
		this.subscriberSno = subscriberSno;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Subscriber [subscriberSno=" + subscriberSno + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + "]";
	}
}
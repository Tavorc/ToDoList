package com.tavor.samples.model;

import javax.persistence.*;
/**
 * class Item
 */
@Entity
@Table(name = "item")
public class Item {	
	
@Id @GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "idItem")
private int id;
		
@Column(name = "description")
private String description ;
	   
@Column (name = "mail")
private String mail;

public Item() {}

		/**
		   * constructor Item
		   * @param userName
		   * @param title
		   * @param description
		   */
public Item(String description,String mail) {
	super();
	setDescription(description);
	setMail(mail);
}
/**
 * get the id of item
 * @return id
 */
public int getId() {
	return id;
}
/**
 * set id item
 * @param id
 */
public void setId(int id) {
	this.id = id;
}
/**
 * get the description of the item
 * @return description
 */
public String getDescription() {
	return description;
}
/**
 * set description of the item
 * @param description
 */
public void setDescription(String description) {
	this.description = description;
}
/**
 * get mail of user
 * @return mail
 */
public String getMail() {
	return mail;
}
/**
 * set mail of user
 * @param mail
 */
public void setMail(String mail) {
	this.mail = mail;
}
}

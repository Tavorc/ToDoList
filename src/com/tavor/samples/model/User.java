package com.tavor.samples.model;

import javax.persistence.*;

@Entity
@Table(name = "USER")
/**
 * class User
 */
public class User {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name = "idUser")
	private int idUser;
	
	@Column(name = "mail",unique=true)
	private String mail;
	
	@Column(name = "password")
	private String password;
	/**
	 * constructor of user
	 * @param mail
	 * @param password
	 */
public User( String mail, String password) {
	this.mail = mail;
	this.password = password;
}
public User() {
}
/**
 * get the id of user
 * @return idUser
 */
public int getIdUser() {
	return idUser;
}
/**
 * set id user
 * @param idUser
 */
public void setIdUser(int idUser) {
	this.idUser = idUser;
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
/**
 *  get password of user
 * @return password
 */
public String getPassword() {
	return password;
}
/**
 * set password of user
 * @param password
 */
public void setPassword(String password) {
	this.password = password;
}
}

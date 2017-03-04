package com.tavor.samples.model;

import java.util.List;

public interface IToDoListDAO {
	/**
	 * add item to table item
	 * @param item
	 * @throws ItemException
	 */
	public void addItem(Item item) throws ItemException;
	/**
	 * deletebitem from table
	 * @param mail
	 * @param idItem
	 * @throws ItemException
	 */
	public void deleteItem(String mail,int idItem)throws ItemException;
	/**
	 * update item
	 * @param itemId
	 * @param updatedItem
	 * @throws ItemException
	 */
	public void updateItem(int itemId,Item updatedItem) throws ItemException;
	/**
	 * return all the items
	 * @param mail
	 * @return
	 * @throws ItemException
	 */
    public List<Item> getAllItems(String mail) throws ItemException;
    /**
     * add user to table user
     * @param user
     * @return
     * @throws UserException
     */
	public User addUser(User user) throws UserException;
	/**
	 * return user that login from table
	 * @param mail
	 * @param password
	 * @return
	 * @throws UserException
	 */
	public User getUser(String mail,String password) throws UserException;
}

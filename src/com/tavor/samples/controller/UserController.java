package com.tavor.samples.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;

import com.tavor.samples.model.HibernateToDoListDAO;
import com.tavor.samples.model.Iconstant;
import com.tavor.samples.model.Item;
import com.tavor.samples.model.ItemException;
import com.tavor.samples.model.User;
import com.tavor.samples.model.UserException;
/**
 * Servlet class UserController
 */
@WebServlet("/UserController/*")
public class UserController extends HttpServlet implements Iconstant{
	private static final long serialVersionUID = 1L;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController()
    {
        super();
        dispatcher=null;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		StringBuffer strBuffer = request.getRequestURL();
		String url = strBuffer.toString();
		this.session = request.getSession(true);
		//check if the user is already login or not
		User user = (User) session.getAttribute(CURRENT_USER);
		if (user == null)
		{
			if(url.endsWith(SIGNUP))
			{
				linkDispachter(request, response, SIGNUP_PATH);
			} 
			else
			{
				linkDispachter(request, response, LOGIN_PATH);
			}
		}
		else 
		{
			linkDispachter(request, response, url);
		}
	}
	/**
	 * this function call to the appropriate function
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.session = request.getSession(true);
		StringBuffer strBuffer = request.getRequestURL();
		String url = strBuffer.toString();
		String requestUrl = null;
		//check the url and call to the appropriate function
		if(url.endsWith(NEW_SIGNUP))
		{
			requestUrl=signUp(request,response);
		}
		if(url.endsWith(LOGIN))
		{
			requestUrl=login(request,response);
		}
		else if(url.endsWith(ADD_ITEM)){
			requestUrl = addItem(request, response);
		}
		else if(url.endsWith(REMOVE_ITEM))
		{
			requestUrl=removeItem(request, response);
		}
		else if (url.endsWith(EDIT_ITEM)) {
        	requestUrl = editItem(request, response);
        }	
		goForward(request, response, requestUrl);
	}
	/**
	 * this method called from the doPost method
	 * create new user
	 * @param request
	 * @param response
	 * @return
	 */
	private String signUp(HttpServletRequest request, HttpServletResponse response)
	{
		String mail=request.getParameter(USER_MAIL);
		String password=request.getParameter(PASSWORD);
		String url=null;
		if(!authenticationInput(mail,password))
		{
			request.setAttribute(RESULT,MISSING_INPUT);
			url=SIGNUP_JSP;
		}
		else
		{
			User newUser=new User(mail,password);
			User existUser=null;
			try{
			existUser = HibernateToDoListDAO.getInstance().addUser(newUser);
				if (existUser != null)
				{
					session.setAttribute(CURRENT_USER, existUser);
					url = MENU_JSP;
					request.setAttribute(USER_MAIL, mail);
				}
				else{
					url =SIGNUP_JSP;
				}
			}
			catch(UserException e){
				request.setAttribute(RESULT, e.getMessage());
				url = SIGNUP_JSP;	
			}
		}
		return url;
	}
	/**
	 * this method called from the doPost method
	 * check the detail of the user and pass to menu of the app
	 * @param request
	 * @param response
	 * @return url
	 */
	private String login(HttpServletRequest request, HttpServletResponse response)
	{
		String mail=request.getParameter(USER_MAIL);
		String password=request.getParameter(PASSWORD);
		String url = null;
		if(!authenticationInput(mail,password))
		{
			request.setAttribute(RESULT,MISSING_INPUT);
			url=LOGIN_JSP;
		}
		else{
			User currentUser=null;
			try{
				currentUser=HibernateToDoListDAO.getInstance().getUser(mail, password);
				//check if the user already sign up
				if(currentUser !=null && password.equals(currentUser.getPassword()))
				{
					session.setAttribute(CURRENT_USER, currentUser);
					url=MENU_JSP;
				}
				else{
					request.setAttribute(RESULT, INVALID_INPUT);
					url=LOGIN_JSP;
				}
			}
			catch(UserException e){
				request.setAttribute(RESULT, e.getMessage());
				url=LOGIN_JSP;
			}
		}
		return url;
	}
/**
 * this method called from the doPost method
 * add new item to the list
 * @param request
 * @param response
 * @return url
 */
	private String addItem(HttpServletRequest request, HttpServletResponse response)
	{
		String url=null;
		String description=request.getParameter(DESCRIPTION);
		if(!authenticationInput(description))
		{
			request.setAttribute(RESULT,"You have to write task");
			url=ADD_ITEM_JSP;
		}
		else
		{
			User currentUser=(User)session.getAttribute(CURRENT_USER);
			String mail=currentUser.getMail();
			Item item=new Item(description,mail);
			try{
				HibernateToDoListDAO.getInstance().addItem(item);
				request.setAttribute(RESULT, "Task : "  + description + " - was Added");
			}
			catch (ItemException e) {
				request.setAttribute(RESULT, e.getMessage());
				url = ADD_ITEM_JSP;
			}
			//add item to the list
			List<Item> itemList=getItemList(mail);
			if(itemList!=null)
			{
				request.setAttribute(ALL_ITEMS, itemList);
				url = ITEMS_LIST_JSP;
			}
			else{
				request.setAttribute(RESULT, LIST_ERROR);
				url=MENU_JSP;
			}
		}
		return url;
	}
	/**
	 * this method called from the doPost method
	 * remove item from the list
	 * @param request
	 * @param response
	 * @return url
	 */
	private String removeItem(HttpServletRequest request, HttpServletResponse response)
	{
		String url=null;
		String numberItem=request.getParameter("number");
		if(!authenticationInput(numberItem))
		{
			request.setAttribute(RESULT, "You don't write any number of task");
			url=REMOVE_ITEM_JSP;
		}
		else{
			User user=(User) session.getAttribute(CURRENT_USER);
			String mail = user.getMail();
			HashMap<Integer, Integer> idItems = (HashMap<Integer, Integer>) session.getAttribute(HASH);
			int idItem=0;
			try{
				idItem=Integer.parseInt(numberItem);
			}
			catch(NumberFormatException e)
			{
				request.setAttribute("RESULT", "ID field must be a Number");
				url = REMOVE_ITEM_JSP;
				return url;
			}
			//check if the hash map contain the id that the user write
			if(idItems.containsKey(idItem))
			{
				idItem=idItems.get(idItem);
				try{
					HibernateToDoListDAO.getInstance().deleteItem(mail, idItem);
					request.setAttribute(RESULT, "The Item was deleted");
				}
				catch(ItemException e)
				{
					request.setAttribute(RESULT, e.getMessage());
					url=REMOVE_ITEM_JSP;
				}
				//update the list of items
				List itemList=getItemList(mail);
				if (itemList != null) {
					request.setAttribute(ALL_ITEMS, itemList);
					url = ITEMS_LIST_JSP;
				} else {
					request.setAttribute(RESULT, LIST_ERROR);
					url = MENU_JSP;
				}
			}
			else{
				request.setAttribute(RESULT, "The Item Number not Exist");
				url = REMOVE_ITEM_JSP;
			}
		}
		return url;
	}
	/**
	 * this method called from the doPost method
	 * edit item from the list according to id item
	 * @param request
	 * @param response
	 * @return url
	 */
	private String editItem(HttpServletRequest request, HttpServletResponse response)
	{
		String url=null;
		String id=request.getParameter("id");
		String description=request.getParameter("descriptionItem");
		if(!authenticationInput(id,description))
		{
			request.setAttribute(RESULT, "You don't fill the fields");
			url=EDIT_ITEM_JSP;
		}
		else
		{
			User user=(User) session.getAttribute(CURRENT_USER);
			String mail=user.getMail();
			HashMap<Integer, Integer> idItems = (HashMap<Integer, Integer>) session.getAttribute(HASH);
			int idItem=0;
			try
			{
				idItem=Integer.parseInt(id);
			}
			catch(NumberFormatException e)
			{
				request.setAttribute("RESULT", "ID field must be a Number");
				url = EDIT_ITEM_JSP;
				return url;
			}
			//check if the hash map contain the id that the user write
			if ( idItems.containsKey(idItem))
			{
				idItem = idItems.get(idItem);
				try
				{
					Item updatedItem=new Item(description,mail);
					HibernateToDoListDAO.getInstance().updateItem(idItem, updatedItem);
					request.setAttribute(RESULT, "Item was updated");
				}
				catch(ItemException e)
				{
					request.setAttribute(RESULT, e.getMessage());
					url = EDIT_ITEM_JSP;
				}
				//update the list of items
				List<Item> listItem=getItemList(mail);
				if(listItem !=null)
				{
					request.setAttribute(ALL_ITEMS, listItem);
					url = ITEMS_LIST_JSP;
				}
				else{
					request.setAttribute(RESULT, "The list is empty");
					url = MENU_JSP;
				}
			}
			else
			{
				request.setAttribute(RESULT, "The item is not exist!!");
				url = EDIT_ITEM_JSP;
			}
		}
		return url;
	}
	/**
	 * select the action according to the end of url address
	 * @param request
	 * @param response
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void linkDispachter(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException
	{
		User currentUser = (User) session.getAttribute(CURRENT_USER);
		//move to login page
		if (url.endsWith(LOGOUT))
		{
		session.invalidate();
		goForward(request, response, LOGIN_JSP);
		}
		//move to sign up page
		else if (url.endsWith(SIGNUP)){
			goForward(request, response,SIGNUP_JSP);
		}
		//move to login page
		else if (url.endsWith(LOGIN)){
			goForward(request, response,LOGIN_JSP);
		}
		//move to about page
		else if (url.endsWith(ABOUT)){
		goForward(request, response, ABOUT_JSP);
	 	}
		//move to list items page
		else if(url.endsWith(LIST)){
		 	String mail=currentUser.getMail();
		 	List<Item> items=getItemList(mail);
		 	if(items!=null)
		 	{
		 		request.setAttribute(ALL_ITEMS, items);
		 		goForward(request, response, ITEMS_LIST_JSP);
		 	}
		 	else{
		 		request.setAttribute(ERROR, "Error - Can't get task list");
				goForward(request, response, ERROR_JSP);
		 	}
		}
		//move to add item page
	    else if(url.endsWith(ADD_ITEM))
	    {
			goForward(request, response, ADD_ITEM_JSP);
	    }
		//move to remove item page
	    else if(url.endsWith(REMOVE_ITEM))
	    {
			goForward(request, response, REMOVE_ITEM_JSP);
		}
		//move to edit item page
	    else if (url.endsWith(EDIT_ITEM))
	    {
			goForward(request, response, EDIT_ITEM_JSP);
		}
		//move to menu page
	    else
	    {
			goForward(request, response, MENU_JSP);
		}
	}
	/**
	 * pass to the requested page
	 * @param request
	 * @param response
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goForward(HttpServletRequest request,HttpServletResponse response, String url) throws ServletException, IOException
	{
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		return;
	}
	/**
	 * return list of items
	 * @param mail
	 * @return list<item>
	 */
	private List<Item> getItemList(String mail){
		List<Item> items = null;
		try 
		{
			items = HibernateToDoListDAO.getInstance().getAllItems(mail);
		} 
		catch (HibernateException e)
		{
			items = null;			
		}
		return items;
	}
	/**
	 * check if the string is not empty
	 * @param string
	 * @return flag
	 */
	private boolean authenticationInput(String ... string)
	{
		boolean flag=true;
		for(String check: string )
		{
			if(check.isEmpty()) flag=false;
		}
		return flag;
	}
}

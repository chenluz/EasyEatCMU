package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.Photo;
import model.Model;
import model.PhotoDAO;
import model.UserDAO;
/**
 * Course: 15-637
 * Assignment: HW4
 * @author Yin Xu
 * Last modified: 03/01/2013
 */
public class BrowseAction extends Action{
	
	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	
    public BrowseAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "browse.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());
			
			int max = photoDAO.getCount();
			Photo[] photoList = new Photo[max];
			for(int i=0;i<max;i++){
				photoList[i] = photoDAO.read(i+1);
			}

			request.setAttribute("photoList",photoList);

	        return "browse.jsp";
	    	
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}

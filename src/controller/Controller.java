package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.*;
import databeans.Photo;
import databeans.User;
/**
 * Course: 15-637
 * Assignment: HW4
 * @author Yin Xu
 * Last modified: 03/01/2013
 */

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    public void init() throws ServletException {
    	
        Model model = new Model(getServletConfig());

        Action.add(new ChangePwdAction(model));
        Action.add(new ImageAction(model));
        Action.add(new ListAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new ManageAction(model));
        Action.add(new MoveDownAction(model));
        Action.add(new MoveUpAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new RemoveAction(model));
        Action.add(new UploadAction(model));
        Action.add(new ViewAction(model));
        Action.add(new BrowseAction(model));
        Action.add(new SearchAction(model));
        
        // add initial users and items to the tables.       
        int numOfRec = 0;      
        try {
			numOfRec = model.getUserDAO().getCount();
		} catch (RollbackException e) {
			e.printStackTrace();
		}
        // init Users
        if(numOfRec==0){
        for (int i=0;i<3;i++){
        	addUser(model,"user"+i+"@gmail.com","Mike"+i,"Brain"+i,"mb"+i,"123");
        }
        // init items       
        byte[] pic1 = picToBytes("luger.jpg");
        addItem(model,pic1,"Luger","mb0","Hand gun","1000");
        byte[] pic2 = picToBytes("m1918.jpg");
        addItem(model,pic2,"m1918","mb0","Submachine gun","1000");
        byte[] pic3 = picToBytes("MauserK98.jpg");
        addItem(model,pic3,"Mauser K98","mb0","Rifle","1000");
        byte[] pic4 = picToBytes("MG34.jpg");
        addItem(model,pic4,"MG34","mb1","Machine gun","1000");
        byte[] pic5 = picToBytes("MP40.jpg");
        addItem(model,pic5,"MP40","mb1","Submachine gun","1000");
        byte[] pic6 = picToBytes("MP44.jpg");
        addItem(model,pic6,"MP44","mb1","Submachine gun","1000");
        byte[] pic7 = picToBytes("PPS.jpg");
        addItem(model,pic7,"PPS","mb2","Submachine gun","1000");
        byte[] pic8 = picToBytes("Tomson.jpg");
        addItem(model,pic8,"Tomson","mb2","Submachine gun","1000");
        byte[] pic9 = picToBytes("unknown.jpg");
        addItem(model,pic9,"unknown","mb2","Machine gun","1000");
        
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user = (User) session.getAttribute("user");
        String      action = getActionName(servletPath);

        // System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

        if (action.equals("register.do") || action.equals("login.do")) {
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
        
        if (user == null) {
        	// If the user hasn't logged in, direct him to the login page
			//return Action.perform("login.do",request);
        	if(action.equals("image.do")||action.equals("view.do")||action.equals("search.do")||action.equals("list.do")){
        		return Action.perform(action, request);
        	}
        	return Action.perform("browse.do",request);
        }

      	// Let the logged in user run his chosen action
		return Action.perform(action,request);
    }

    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}
    	
    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	if (nextPage.equals("image")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
    /*
     * Add a new user
     */
    private void addUser(Model model,String email,String firstName,String lastName, String userName,
    		String password){
    	
    	UserDAO userDAO = model.getUserDAO();
    	User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
    	//userDAO.create(user);
        try {
			userDAO.createAutoIncrement(user);
		} catch (RollbackException e) {
			e.printStackTrace();
		}
    }
    /*
     * Convert picture to Byte array
     */
	private byte[] picToBytes(String picName) {
		InputStream image = getServletContext().getResourceAsStream(picName);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			BufferedImage originalImage=ImageIO.read(image);
			ImageIO.write(originalImage, "jpg", baos );

		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] imageInByte = baos.toByteArray();
		return imageInByte;
	}
    /*
     * Add a new item
     */
    private void addItem(Model model,byte[] picByte,String caption,String owner,String des,String price){
    	PhotoDAO photoDAO = model.getPhotoDAO();
    	Photo photo = new Photo();  // id & position will be set when created
		photo.setBytes(picByte);		
		photo.setCaption(caption);
		photo.setContentType(null);
		photo.setOwner(owner);
		photo.setDescription(des);
		photo.setPrice(price);
		photo.setDate(new Date());
		try {
			photoDAO.create(photo);
		} catch (RollbackException e) {
			e.printStackTrace();
		}
    }
}

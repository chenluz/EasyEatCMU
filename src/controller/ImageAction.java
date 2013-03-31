package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.PhotoDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Photo;
import formbeans.IdForm;

/**
 * Course: 15-637
 * Assignment: HW4
 * @author Yin Xu
 * Last modified: 03/01/2013
 */
public class ImageAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private PhotoDAO photoDAO;

    public ImageAction(Model model) {
    	photoDAO = model.getPhotoDAO();
	}

    public String getName() { return "image.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	IdForm form = formBeanFactory.create(request);
        	
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "error.jsp";
            }

        	Photo p = photoDAO.read(form.getIdAsInt());
    		if (p != null) request.setAttribute("photo",p);
    		
    		// Note: "/image" is mapped (in the web.xml file) to the ImageServlet
    		// which looks at the "photo" request attribute and sends back the bytes.
    		// If there is no "photo" attribute, it sends back HTTP Error 404 (resource not found).
    		return "image";
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}

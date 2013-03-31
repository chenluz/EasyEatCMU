package formbeans;

import java.util.ArrayList;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;
/**
 * Course: 15-637
 * Assignment: HW4
 * @author Yin Xu
 * Last modified: 03/01/2013
 */
public class UploadPhotoForm extends FormBean {
	private String button     = "";
	private String caption    = "";
	private FileProperty file = null;
	
	private String description = "";
	private String price = "";
	
	public static int FILE_MAX_LENGTH = 1024 * 1024;
	
	public String       getButton()         { return button;         }
	public FileProperty getFile()           { return file;           }
	public String       getCaption()        { return caption;        }
	
	public String       getDescription()    { return description;    }
	public String       getPrice()          { return price;          }
	

	public void setButton(String s)         { button      = s;        }
	public void setCaption(String s)        { caption     = trimAndConvert(s,"<>\""); }
	public void setFile(FileProperty file)  { this.file   = file;     }
	
	public void setDescription(String d)    { this.description = d;   }
	public void setPrice(String p)          { this.price = p;         }
	
	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();
		
		if (file == null || file.getFileName().length() == 0) {
			errors.add("You must provide a file");
			return errors;
		}

		if (file.getBytes().length == 0) {
			errors.add("Zero length file");
		}
		
		// Description can't be null
		if (description == null || description.length() == 0){
			errors.add("You must provide description");
		}
		// Price must be numeric
		try {
			double priceNum = Double.parseDouble(price);
			System.out.println(priceNum);
		} catch (NumberFormatException e) {
			errors.add("Price must be a number");
		}
		
		
		return errors;
	}
}

package databeans;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.genericdao.PrimaryKey;
/**
 * Course: 15-637
 * Assignment: HW4
 * @author Yin Xu
 * Last modified: 03/01/2013
 */
@PrimaryKey("id")
public class Photo implements Comparable<Photo> {
	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
			".jpg", ".gif", ".JPG"
	} ));
	
	private String description = null;
	private String price       = null;
	private Date date          = null;

	private int    id          = -1;
	
	private byte[] bytes       = null;
	private String caption     = null;
	private String contentType = null;
	private String owner       = null;
	private int    position    = 0;
	
	public int compareTo(Photo other) {
		// Order first by owner, then by position
		if (owner == null && other.owner != null) return -1;
		if (owner != null && other.owner == null) return 1;
		int c = owner.compareTo(other.owner);
		if (c != 0) return c;
		return position - other.position;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Photo) {
			Photo other = (Photo) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public byte[] getBytes()       { return bytes;       }
    public String getCaption()     { return caption;     }
    public String getContentType() { return contentType; }
    public int    getId()          { return id;          }
    public String getOwner()       { return owner;       }
    public int    getPosition()    { return position;    }
    public String getDescription() { return description; }
    public String getPrice()       { return price;       }
    public Date   getDate()        { return date;        }
    
    public void setBytes(byte[] a)        { bytes = a;        }
    public void setCaption(String s)      { caption = s;      }
    public void setContentType(String s)  { contentType = s;  }
    public void setId(int x)              { id = x;           }
    public void setOwner(String userName) { owner = userName; }
    public void setPosition(int p)        { position = p;     }
    public void setDescription(String d)  { description = d;  }
    public void setPrice(String p)        { price = p;        }
    public void setDate(Date d)           { date = d;         }
    
    public String toString() {
    	return "Photo("+id+")";
    }
}

/**   
 * @Package: charset 
 * @author: Young   
 * @date: 2018年5月12日 上午11:56:50 
 */
package charset;

import java.io.UnsupportedEncodingException;

/** 
 * @ClassName: getEncoding 
 * @Description: TODO
 * @author: Young
 * @date: 2018年5月12日 上午11:56:50  
 */
public class getStringEncoding {

	public String getEncoding(String str){  
        String encoding = "UTF-8";  
        try {  
            if (str.equals(new String(str.getBytes(),encoding))) {  
                return encoding;  
            }  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        encoding = "GBK";  
        try {  
            if (str.equals(new String(str.getBytes(),encoding))) {  
                return encoding;  
            }  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        encoding = "ISO-8859-1";  
        try {  
            if (str.equals(new String(str.getBytes(),encoding))) {  
                return encoding;  
            }  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        encoding = "GB2312";  
        try {  
            if (str.equals(new String(str.getBytes(),encoding))) {  
                return encoding;  
            }  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        return null;  
    }  
}

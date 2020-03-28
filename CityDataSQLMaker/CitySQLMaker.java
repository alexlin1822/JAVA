
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*  Alex Created by 4/15/2017 
 * For separate city data from whole city weather file. 10000 each file.       */
public class CitySQLMaker {

	public static void main(String[] args) throws FileNotFoundException   //入口函数
	 {
		/*if (args[0]=="") {return;}*/
		
		String sCurPath=System.getProperty("user.dir");
		String JSONFileName=args[0];
		
		JSONFileName=sCurPath+'\\'+JSONFileName;
		System.out.println(JSONFileName);
		
		
		File f = new File(JSONFileName);

		
		if(f.isFile() && !f.isDirectory()) { 
		    // do something
			
	        List<String> strList = new ArrayList<String>();
	        try 
	        {
	            BufferedReader bufReader = new BufferedReader(new FileReader(JSONFileName));
	            while(true)
	            {
	                String lineStr = bufReader.readLine();
	                if(lineStr != null)
	                {
	                    strList.add(lineStr);
	                }
	                else
	                {
	                    break;
	                }
	            }
	            bufReader.close();
	        }
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        
	        int j=0;
	        
	        FileOutputStream fos=new FileOutputStream(sCurPath+'\\'+Integer.toString(j)+".sql"); 
	        
	        //输出读取的结果
	        for (int i = 0; i < strList.size(); i++) {
	        	
	        	String sCur=strList.get(i);
	        	
	        	if (j<((i/10000)+1)) {
	        		if(fos!=null) {	
	        			try {  	
	        				fos.close();
	  	                } catch (Exception e) {  
		                    // TODO Auto-generated catch block  
		                    e.printStackTrace();  
		                }  
	        		}
	        		
	        		j++;
	        		
	        		
	                try {  
	                    fos = new FileOutputStream(sCurPath+'\\'+Integer.toString(j)+".sql");  
	                } catch (Exception e) {  
	                    // TODO Auto-generated catch block  
	                    e.printStackTrace();  
	                }  
	        	}
	        	
	        	sCur=sCur.replaceAll("cityloc", "cityloc"+Integer.toString(j));
	        	sCur=sCur+"\n";
	        	
	        	try {
	        		fos.write(sCur.getBytes());	
	        	} catch (Exception e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
                  

	        }
			
			System.out.println("File Here");
		}else
		{
			System.out.println("File not Here");
		}
		
	 }

}

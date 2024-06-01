package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropertiesFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pro=new Properties();
		pro.load(fis);
		
		String data = pro.getProperty(key);
		return data;
		
	}
	public void writedataFromProperties() throws Throwable {
		
		FileInputStream fis=new FileInputStream("./src//test//resources//pro.properties");
		
		Properties pro=new Properties();
		pro.setProperty("username", "Ram");
		pro.setProperty("password", "kumar");
		
		FileOutputStream fos=new FileOutputStream("./src//test//resources//pro.properties");
		pro.store(fos, "Updated properties"); // Write properties to file
		
	}

}

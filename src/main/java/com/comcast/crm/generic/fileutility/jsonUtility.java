package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class jsonUtility {
	
	public String getDataFromJsonFile(String key) throws Throwable {
		
		FileReader fileR = new FileReader("D:\\AutomationPrograms\\ComcastCRMGUIFrameWORK1\\configAppData\\appCommonData.json");
		
		//Step-1 parse json physical file in to java object using jsonparse class
		JsonParser parser = new JsonParser();
		Object obj = parser.parse(fileR);//paese that json file into actual java object
		JSONPObject map=(JSONPObject)obj;//convert in to hashmap 
		
		//String data=(String)map.get(key);
		//return data;
		return "";
	   
	}

}

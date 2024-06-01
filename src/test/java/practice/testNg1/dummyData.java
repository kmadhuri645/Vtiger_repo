package practice.testNg1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import org.testng.annotations.Test;

//package practice.test;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
public class dummyData {
//
//	public static void main(String[] args) {
//		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance(); // Get the current date
//		cal.add(Calendar.DAY_OF_MONTH, 30); // Add days
//		String reqDate = sim.format(cal.getTime()); // Format the date
//		System.out.println(reqDate);
//	}
//}
	
@Test
public void writedataFromExcel() throws Throwable {
	
	FileInputStream fis=new FileInputStream("./src//test//resources//pro.properties");
	
	Properties pro=new Properties();
	pro.setProperty("username", "Ram");
	pro.setProperty("password", "kumar");
	
	FileOutputStream fos=new FileOutputStream("./src//test//resources//pro.properties");
	pro.store(fos, "Updated properties"); // Write properties to file
	
	
	
}
}
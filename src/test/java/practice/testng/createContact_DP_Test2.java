package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class createContact_DP_Test2 {
	
	@Test(dataProvider = "getDate")
	public void createContactTest(String firstName , String lastName ,Long phoneNumber) {
		System.out.println("FirstName :"+firstName+", LastName :"+lastName+", PhoneNumber:"+phoneNumber);
	}
	
	@DataProvider
	public Object[][] getDate() {
		Object[][] objArr=new Object[3][3];
		
		objArr[0][0]="deepak";                //Row And Column
		objArr[0][1]="hr";
		objArr[0][2]=9999999999l;
		
		objArr[1][0]="sam";
		objArr[1][1]="sh";
		objArr[1][2]=88888888888l;
		
		objArr[2][0]="Jhon";
		objArr[2][1]="smith";
		objArr[2][2]=77777777777l;
		
		return objArr;
	}

}

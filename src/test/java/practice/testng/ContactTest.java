package practice.testng;

import org.testng.annotations.Test;

public class ContactTest {
	
	@Test(priority = -1)
	public void createContactTest() {
		System.out.println("execute createContact");
	}

	@Test(priority = 0)
	public void modifyContactTest() {
		System.out.println("execute modifyContactTest");
	}

	@Test
	public void deleteContactTest() {
		System.out.println("execute deleteContactTest");
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//instead of using priority we have to use all testcase indendentaly
	@Test
	public void createContactTest1() {
		System.out.println("execute createContact with==>HDFC ");
	}

	@Test
	public void modifyContactTest1() {
		//System.out.println("create contact icici");///craeting a contact it GUI takes lots of time  istead you have to use jdbc
		System.out.println("execute query insert contact in DB===> ICICI");
		System.out.println("execute modifyContactTest---ICICI==>ICICI_1");
	}

	@Test
	public void deleteContactTest1() {
		//System.out.println("create upi contact");
		System.out.println("execute query insert contact in DB==> UPI");
		System.out.println("execute deleteContactTest UPI");
	}
}

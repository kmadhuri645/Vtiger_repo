package practice.demo;

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.crm.generic.basetest.BaseClass;

/**
 * test class for Contact module
 * @author madhuri
 *
 */

public class SearchContactTest extends BaseClass{
	/**
	 * Scenario:login()==>navigateContact===>createcontact()==verify
	 */
	@Test
	public void searchcontactTest() {
		/* step 1: login to app */
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("usename", "password");
		
		
	}

}

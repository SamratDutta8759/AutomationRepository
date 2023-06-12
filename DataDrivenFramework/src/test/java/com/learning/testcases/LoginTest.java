package com.learning.testcases;

import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learning.base.TestBase;
import com.learning.utilities.TestUtils;

public class LoginTest extends TestBase {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "getExcelData")
	public void LoginTest(HashMap<String,String> testData) {
		if (TestUtils.isTestRunnable("LoginTest", excel)) {
			String data = testData.get("registration_Username");
			type("registration_username", testData.get("registration_Username"));
			type("registration_password", testData.get("registration_Password"));
			type("login_username", testData.get("login_Username"));
			click("login_button");

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean iselementpresent = isElementPresent("logout_button");
			Assert.assertTrue(iselementpresent);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new SkipException("LoginTest Skipped as runmode set to no");
		}
	}
}

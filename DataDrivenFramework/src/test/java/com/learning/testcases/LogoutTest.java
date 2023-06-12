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

public class LogoutTest extends TestBase {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "getExcelData")
	public void LogoutTest(HashMap<String,String> testData) {
		if (TestUtils.isTestRunnable("LogoutTest", excel)) {
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
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			click("logout_button");

			boolean iselementpresent = isElementPresent("login_button");

			Assert.assertTrue(iselementpresent);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			throw new SkipException("LogOutTest has been skipped as runode is set to no");
	}

	@DataProvider
	public Object[][] getData() {
		String sheetname = "LoginTest";
		int rows = excel.getRowCount(sheetname);
		int cols = excel.getColumnCount(sheetname);
		Object[][] data = new Object[rows - 1][cols];

		for (int rownum = 2; rownum <= rows; rownum++) {
			for (int colnum = 0; colnum < cols; colnum++) {
				data[rownum - 2][colnum] = excel.getCellData(sheetname, colnum, rownum);
			}
		}

		return data;
	}
}

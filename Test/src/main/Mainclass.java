package main;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.functions.tests;


public class Mainclass {
//	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
	
		tests.firefoxInitiation();
		tests.login();
		tests.profileActivity();
		tests.myServices();
		tests.logout();
		
	}

}

package glue;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.Homescreen;

public class Shared
{
	//Declare Global objects
	public AndroidDriver driver;
	public DesiredCapabilities dc;
	public WebDriverWait wait;
	public Homescreen hs;
	public Scenario s;
	public Properties p;
	public URL u;
	public TouchAction ta;
	
	@Before
	public void beforeMethod(Scenario s) throws Exception
	{
		//Assign Scenario class object to local Scenario object
		this.s=s;
		//Activate properties file
		File f=new File("E:\\Automation\\AutomationNested\\com.bddcucumber.gui.calcapp\\src\\test\\resources\\properties\\config.properties");
		FileInputStream fi=new FileInputStream(f);
		p=new Properties();
		p.load(fi);
		//Start Appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		//Address of appium server
		u=new URL(p.getProperty("appium_server_url"));	
	}
	
	@After
	public void afterMethod() throws Exception
	{
		//Stop Appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		//Mandatory wait for next scenario to execute(for current appium to close)
		Thread.sleep(3000);
	}
}

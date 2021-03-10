package glue;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.ElementOption;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.Homescreen;

public class GlueCode1
{
	//Declared shared class object
	public Shared sh;
	
	//Constructor method
	public GlueCode1(Shared sh)
	{
		this.sh=sh;
	}
	
	@Given("^launch calculator app$")
	public void method1() throws Exception
	{
		//Desired Capabilities
		sh.dc=new DesiredCapabilities();
		sh.dc.setCapability(CapabilityType.BROWSER_NAME,"");
		sh.dc.setCapability(MobileCapabilityType.DEVICE_NAME,sh.p.getProperty("device_name"));
		sh.dc.setCapability(MobileCapabilityType.PLATFORM_NAME,sh.p.getProperty("platform_name"));
		sh.dc.setCapability(MobileCapabilityType.VERSION,sh.p.getProperty("platform_version"));
		sh.dc.setCapability("appPackage",sh.p.getProperty("app_package"));
		sh.dc.setCapability("appActivity",sh.p.getProperty("app_activity"));
		//Object creation
		while(2>1)
		{
			try
			{
				sh.driver=new AndroidDriver(sh.u,sh.dc);
				break;
			}
			catch(Exception ex)
			{
			}
		}
		//Create page class Objects
		sh.hs=new Homescreen(sh.driver);
		sh.ta=new TouchAction(sh.driver);
		//Create wait object
		sh.wait=new WebDriverWait(sh.driver,20);
		sh.wait.until(ExpectedConditions.visibilityOf(sh.hs.clear));
	}
	
	@When("^perform \"(.*)\" with two numbers like \"(.*)\" and \"(.*)\"$")
	public void method2(String op,String x,String y) throws Exception
	{
		try
		{
			//Enter input 1
			for(int i=0;i<x.length();i++)
			{
				char c=x.charAt(i);
				if(c=='-')
				{
					sh.hs.clickSubtraction();
				}
				else if(c=='0')
				{
					sh.ta.tap(ElementOption.point(540,2400)).perform();
				}
				else
				{
					sh.driver.findElement(By.xpath("//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_0"+c+"']")).click();
				}
			}
			
			//Click for Mathematical Operation
			if(op.equalsIgnoreCase("addition"))
			{
				sh.hs.clickAddition();
			}
			else if(op.equalsIgnoreCase("subtraction"))
			{
				sh.hs.clickSubtraction();
			}
			else if(op.equalsIgnoreCase("division"))
			{
				sh.hs.clickDivision();
			}
			else if(op.equalsIgnoreCase("multiplication"))
			{
				sh.hs.clickMultiplication();
			}
			
			//Enter input 2
			for(int i=0;i<y.length();i++)
			{
				char c=y.charAt(i);
				if(c=='-')
				{
					sh.hs.clickSubtraction();
				}
				else if(c=='0')
				{
					sh.ta.tap(ElementOption.point(540,2400)).perform();
				}
				else
				{
					sh.driver.findElement(By.xpath("//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_0"+c+"']")).click();
				}
			}
			//Click Equal
			sh.hs.clickEqual();
			//wait for result to display
			Thread.sleep(3000);
		}
		catch(Exception ex)
		{
			sh.s.write(ex.getMessage());
		}
	}
	
	@Then("^validate \"(.*)\" operation for \"(.*)\" and \"(.*)\"$")
	public void method3(String op,String x,String y)
	{
		//Validations
		int a=Integer.parseInt(x);
		int b=Integer.parseInt(y);
		String result=sh.hs.getResult();
		if(op.equalsIgnoreCase("subtraction") && Integer.parseInt(result)==(a-b))
		{
			sh.s.write("Subtraction test passed");
		}
		else if(op.equalsIgnoreCase("addition") && Integer.parseInt(result)==(a+b))
		{
			sh.s.write("Addition test passed");
		}
		else if(op.equalsIgnoreCase("multiplication") && Integer.parseInt(result)==(a*b))
		{
			sh.s.write("Multiplication test passed");
		}
		else if(op.equalsIgnoreCase("division"))
		{
			if(b==0)
			{
				String toast=sh.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast"))).getText();
				sh.s.write(toast);
			}
			else if(b!=0 && Double.parseDouble(result)==(a/b))
			{
				sh.s.write("Division test passed");
			}
			else
			{
				byte[] bytes=sh.driver.getScreenshotAs(OutputType.BYTES);
				sh.s.embed(bytes,op+" test failed");
				Assert.assertTrue(false);
			}
		}
		else
		{
			byte[] bytes=sh.driver.getScreenshotAs(OutputType.BYTES);
			sh.s.embed(bytes,op+" test failed");
			Assert.assertTrue(false);
		}
	}
	
	@When("^close app$")
	public void method4()
	{
		sh.driver.closeApp();
	}
}
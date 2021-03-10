package pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.ElementOption;

public class Homescreen
{
	//Declare driver
	public AndroidDriver driver;
	public TouchAction ta;
	
	//Locators
	@AndroidFindBy(xpath="//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_div']")
	public MobileElement div;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_mul']")
	public MobileElement mul;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_sub']")
	public MobileElement sub;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_add']")
	public MobileElement add;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_clear']")
	public MobileElement clear;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.sec.android.app.popupcalculator:id/bt_backspace']")
	public MobileElement backspace;
	
	@AndroidFindBy(xpath="//*[@content-desc='Calculator input field']")
	public MobileElement result;
	
	//Constructor method
	public Homescreen(AndroidDriver driver)
	{
		this.driver=driver;
		AppiumFieldDecorator ad=new AppiumFieldDecorator(driver);
		PageFactory.initElements(ad,this);
	}
	
	//Operational methods
	public void clickDivision()
	{
		div.click();
	}
	
	public void clickMultiplication()
	{
		mul.click();
	}
	
	public void clickSubtraction()
	{
		sub.click();
	}
	
	public void clickAddition()
	{
		add.click();
	}
	
	public void clickBackspace()
	{
		backspace.click();
	}
	
	public void clickClear()
	{
		clear.click();
	}

	public void clickEqual()
	{
		ta=new TouchAction(driver);
		ta.tap(ElementOption.point(1250,2400)).perform();
	}
	
	public String getResult()
	{
		String res=result.getAttribute("text");
		if(!Character.isDigit(res.charAt(0)))
		{
			res=res.replace(""+res.charAt(0),"-");
		}
		return(res);
	}
}

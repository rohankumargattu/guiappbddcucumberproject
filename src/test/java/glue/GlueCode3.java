package glue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class GlueCode3 
{
	//Declared shared class object
	public Shared sh;
	
	//Constructor method
	public GlueCode3(Shared sh)
	{
		this.sh=sh;
	}
	
	@When("^perform operation as maps and validate result$")
	public void method6(DataTable dt) throws Exception
	{
		List<Map<String,String>> data=dt.asMaps();
		for(Map<String, String> m:data)
		{
			String op=m.get("operation");
			String x=m.get("input1");
			String y=m.get("input2");
			try
			{
				//Enter input 1
				for(int j=0;j<x.length();j++)
				{
					char c=x.charAt(j);
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
				for(int j=0;j<y.length();j++)
				{
					char c=y.charAt(j);
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
			Thread.sleep(3000);
			sh.hs.clickClear();
		}
	}
}

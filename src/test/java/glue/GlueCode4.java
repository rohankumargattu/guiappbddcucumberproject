package glue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class GlueCode4
{
	//Declared shared class object
	public Shared sh;
	
	//Constructor method
	public GlueCode4(Shared sh)
	{
		this.sh=sh;
	}
	
	@When("^perform arithmetic operations by reading data from excel$")
	public void method7() throws Exception
	{
		File f=new File("arithmeticoperations.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sheet=wb.getSheet("Sheet1");
		int nour=sheet.getPhysicalNumberOfRows();
		int nouc=sheet.getRow(0).getLastCellNum();
		//Give headings to results in excel file
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		Date dt=new Date();
		String rname=sf.format(dt);
		sheet.getRow(0).createCell(nouc).setCellValue(rname);
		//Data Driven from 2nd row(index=1)
		for(int i=1;i<nour;i++)
		{
			//Read data from excel
			DataFormatter df=new DataFormatter();
			String op=df.formatCellValue(sheet.getRow(i).getCell(0));
			String x=df.formatCellValue(sheet.getRow(i).getCell(1));
			String y=df.formatCellValue(sheet.getRow(i).getCell(2));
			
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
		
		//Autofit
		sheet.autoSizeColumn(nouc);
		
		//Save data back to excel
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		fi.close();
		fo.close();
		wb.close();	
	}
}

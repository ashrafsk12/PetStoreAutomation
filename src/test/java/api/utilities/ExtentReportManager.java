package api.utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener	
{
	public ExtentSparkReporter sparkReporter;  //UI of the Report 
	
	public ExtentReports extent;     //populate common info on the Report
	
	public ExtentTest test;      //creating  test case entries on the report and update the status of the test methods
	
	String repName;
	
	public void onStart(ITestContext context)
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp

		repName = "Test-Report-" + timestamp + ".html";
		
		sparkReporter = new ExtentSparkReporter (".\\reports\\"+repName);//specify location where you want to create Report
		
		sparkReporter.config().setDocumentTitle("RestAssured Automation Report");   //Title of report
		
		sparkReporter.config().setReportName("Pet Store Users API");     // name of the report
		
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);  // this statement will basically combine the UI along with the populated information 
		extent.setSystemInfo("User Name", System.getProperty("user.name"));  //Tester Name
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		
		extent.setSystemInfo("Browser Name", "GOOGLE CHROME");
		
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		 test=extent.createTest(result.getName());  //create new entry in the report
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.PASS, "Test case Passed is:"+result.getName()); //update the status pass/fail/skip
	}
		 
		 /* by using this extent  we are creating a new entry because as soon as my  

test method is got passed that entry we have to  create in the report so this particular statement  

create a test will create an entry in the report and what is the name it will give the name of the  

method so whichever method is got passed that method name should be updated in the report,but how we will get this method name whichever is

got passed how we will get the name of that method here this is the parameter we have  to choose so on test success,
	this parameter(ITestResult and variable is result) contains actually is whatever the test method is got passed this will capture  
the information or details about the pass test  method so this (result) contains all the details about the passed methods.
*/
	  
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());  //create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case Failed is:"+result.getName()); //update the status pass/fail/skip
		test.log(Status.FAIL,"Test case failed caused is:"+result.getThrowable()) ;
	}
	/* if any  test got failed so we are creating a new entry  

in the report with that same name and updating the  status as a failed and whenever the test methods  

got failed we will get some exception right we'll  get some error messages and those messages also we can log into the report additionally for that I 

 have written this particular statement so (result) get name will give you name of the test method  from the same result object get a throwable this  

method will give you the error message*/
	public void onTestSkipped(ITestResult result) 
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,"Test case is Skipped is:"+result.getName());
	  }
	/*again we are creating the new entry in the report  with this test name and also updating the status as a skipped along with the name of the test 

method*/
	public void onFinish(ITestContext context)
	{
		extent.flush();
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;

		File extentReport = new File(pathofExtentReport);

		try {

		Desktop.getDesktop().browse (extentReport.toURI()); //this method will open this report on the browser automatically,
		
		// we no need to open the report manually

		} 
		catch (IOException e) 
		{
		e.printStackTrace();

		}
	}
	
	/*so once you have done all these things and the final method is onfinish is must and should  if you do not have this method whatever you have  

done before nothing will take care in the report so  only onfinish method will update the whole thing in the report so here inside this we have to call  

one method called flush from extend object so what this flush method will do is this write all the  test information from the standard repositories  

to their output view,means whatever the things we  have created so far everything will be updated in  

the report,finally on finish this action to be  performed,so this is mandatory method 
*/

}

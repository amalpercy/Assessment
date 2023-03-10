package package14;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.ITestListener;

public class Listeners1 implements ITestListener {
	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub
	}

	public void onTestSuccess(ITestResult result) 
	{
		Reporter.log(" Test passed: " + result.getName());
		
	}

	public void onTestFailure(ITestResult result) 
	{
		Reporter.log("Test failed: "+ result.getName());

	}

	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub

	}
}


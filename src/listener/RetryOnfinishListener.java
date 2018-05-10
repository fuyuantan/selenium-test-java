package listener;

import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import utils.TestNGRetry;

public class RetryOnfinishListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		if (tr.getMethod().getRetryAnalyzer() != null) {
			TestNGRetry testRetryAnalyzer = (TestNGRetry) tr.getMethod().getRetryAnalyzer();

			if (testRetryAnalyzer.getRetryCount() < testRetryAnalyzer.getMaxRetryCount()) {
				tr.setStatus(ITestResult.FAILURE);
				Reporter.setCurrentTestResult(tr);
			}
		}
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
		while (listOfFailedTests.hasNext()) {
			ITestResult failedTest = listOfFailedTests.next();
			ITestNGMethod method = failedTest.getMethod();
			if (testContext.getFailedTests().getResults(method).size() > 1) {
				listOfFailedTests.remove();
			} else {
				if (testContext.getPassedTests().getResults(method).size() > 0) {
					listOfFailedTests.remove();
				}
			}
		}
	}
}

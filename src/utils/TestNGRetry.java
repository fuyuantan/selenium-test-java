package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestNGRetry implements IRetryAnalyzer {
	
	private static int retryCount = 1;
	private static int maxRetryCount = 2;
	
	public int getRetryCount() {
		return retryCount;
	}
	
	public int getMaxRetryCount() {
		return maxRetryCount;
	}

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}

}

package listeners;

import org.testng.*;
import utils.SimpleLogger;

import java.util.Arrays;

public class TestLoggerListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        String testClassName = context.getCurrentXmlTest().getName();
        SimpleLogger.initialize(testClassName);
        SimpleLogger.log("INFO", "Test suite started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        SimpleLogger.log("INFO", "Executing test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        SimpleLogger.log("PASSED", "Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        SimpleLogger.log("FAILED", "Test failed: " + result.getName());
        SimpleLogger.log("ERROR", "Reason: " + result.getThrowable());
        SimpleLogger.setTestFailed(true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        SimpleLogger.log("SKIPPED", "Test skipped: " + result.getName());
        SimpleLogger.log("INFO", "Reason: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        SimpleLogger.log("INFO", "Test suite finished: " + context.getName());
        SimpleLogger.finalizeLogger();
    }
}

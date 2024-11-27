package listeners;

import org.testng.*;
import utils.SimpleLogger;

import java.util.Arrays;

public class TestLoggerListener extends TestListenerAdapter {
    private boolean hasFailures = false;
    private boolean hasSkips = false;

    @Override
    public void onStart(ITestContext context) {
        String testClassName = context.getAllTestMethods()[0].getTestClass().getName();
        SimpleLogger.initialize(testClassName);
        SimpleLogger.log("INFO", "Test suite started: " + context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        SimpleLogger.log("PASSED", "Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        hasFailures = true;
        SimpleLogger.log("FAILED", "Test failed: " + result.getName());
        SimpleLogger.log("ERROR", "Reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        hasSkips = true;
        SimpleLogger.log("SKIPPED", "Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        String finalStatus = hasFailures ? "FAILED" : (hasSkips ? "SKIPPED" : "PASSED");
        SimpleLogger.log("INFO", "Test suite finished: " + context.getName());
        SimpleLogger.finalizeLogger(finalStatus);
    }
}

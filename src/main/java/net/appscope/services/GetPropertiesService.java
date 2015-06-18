package net.appscope.services;

import net.appscope.models.TestData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by eugene.sinyavski on 17/06/15.
 */
public class GetPropertiesService {

    private static FileInputStream fis;
    private static Properties properties;

    public static TestData getTestData() throws IOException {
        fis = new FileInputStream("src/main/resources/global.properties");
        properties = new Properties();
        properties.load(fis);
        TestData testData = new TestData();
        testData.setPlatformName(properties.getProperty("platformName"));
        testData.setPlatformVersion(properties.getProperty("platformVersion"));
        testData.setAppDir(properties.getProperty("appDir"));
        testData.setAppName(properties.getProperty("appName"));
        testData.setTestAppName(properties.getProperty("testAppName"));
        testData.setDeviceName(properties.getProperty("deviceName"));
        testData.setUdid(properties.getProperty("udid"));
        testData.setBundleId(properties.getProperty("bundleId"));
        testData.setLogsLocation(properties.getProperty("logsLocation"));
        testData.setScreensLocation(properties.getProperty("screensLocation"));
        testData.setAppiumServerURL(properties.getProperty("appiumServerURL"));
        testData.setNewCommandTimeout(properties.getProperty("newCommandTimeout"));
        testData.setEmail(properties.getProperty("email"));
        return testData;
    }

}

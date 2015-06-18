package net.appscope;

import io.appium.java_client.ios.IOSDriver;
import net.appscope.models.TestData;
import net.appscope.services.GetPropertiesService;
import net.appscope.utils.CommandExecutor;
import net.appscope.utils.TakeScreenshot;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by eugene.sinyavski on 17/06/15.
 */
public class BaseTest {

    public static TestData testData;
    public static IOSDriver driver;
    public static final Logger log = Logger.getLogger(BaseTest.class.getName());
    public By switchRecording = By.xpath("//UIASwitch[@name='Recording']");
    public By settingsLink = By.name("Settings");
    public By versionRow = By.name("Version");
    public By textField = By.xpath("//UIATextField");
    public By goLink = By.name("GO");
    public By recordingLink = By.name("Recording");
    public By uploadLink = By.name("Upload");
    public By nextLink = By.name("Next");
    public By emailField = By.xpath("//UIATextField[1]");
    public By subjectField = By.xpath("//UIATextField[2]");
    public By doneLink = By.name("Done");


    @BeforeTest
    public void setUp() throws Exception {
        try {
            testData = GetPropertiesService.getTestData();
            log.info("Uninstalling current app version from device");
            CommandExecutor.execute("ideviceinstaller -U " + testData.getBundleId());
            log.info("Installing new app version to device");
            CommandExecutor.execute("ideviceinstaller -i " + testData.getAppDir() + testData.getAppName());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", testData.getDeviceName());
            capabilities.setCapability("platformName", testData.getPlatformName());
            capabilities.setCapability("platformVersion", testData.getPlatformVersion());
            capabilities.setCapability("udid", testData.getUdid());
            capabilities.setCapability("bundleId", testData.getBundleId());
            capabilities.setCapability("newCommandTimeout", testData.getNewCommandTimeout());
            File appDir = new File(testData.getAppDir());
            File app = new File(appDir, testData.getAppName());
            capabilities.setCapability("ipa", app.getAbsolutePath());
            log.info("Creation iOS driver session");
            driver = new IOSDriver(new URL(testData.getAppiumServerURL()), capabilities);
            TakeScreenshot.takeScreenShot(driver, "launchedApp", testData.getScreensLocation());
            log.info("App is launched");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}

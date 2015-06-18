package net.appscope.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

/**
 * Created by eugene.sinyavski on 6/17/2015.
 */
public class TakeScreenshot {
    public static void takeScreenShot(IOSDriver driver, String screenName, String screensLocation) {
        try {
            File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(f, new File(screensLocation + screenName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

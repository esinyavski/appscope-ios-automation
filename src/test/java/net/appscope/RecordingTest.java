package net.appscope;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by eugene.sinyavski on 01/06/15.
 */
public class RecordingTest extends BaseTest {

    @Test
        public void testCases() throws InterruptedException {

        try {
            driver.findElement(switchRecording).click();
            driver.findElement(settingsLink).click();
            driver.findElement(versionRow).click();
            for (int timeout = 30; timeout <= 600; timeout += 30) {
                log.info("Start recording with timeout " + timeout);
                driver.findElement(textField).sendKeys("vnd.appscopetest://?timeout=" + timeout + "&fps=24");
                driver.findElement(goLink).click();
                Thread.sleep(timeout*1000);
                driver.findElement(settingsLink).click();
                driver.findElement(recordingLink).click();
                driver.findElement(uploadLink).click();
                driver.findElement(nextLink).click();
                WebElement email = driver.findElement(emailField);
                email.clear();
                email.sendKeys(testData.getEmail());
                driver.findElement(subjectField).sendKeys("record with timeout " + timeout);
                driver.findElement(doneLink).click();
                log.info("Start uploading record with timeout " + timeout);
                Thread.sleep(10000);
                driver.findElement(recordingLink).click();
                driver.findElement(switchRecording).click();
                driver.findElement(settingsLink).click();
                driver.findElement(versionRow).click();
                if (timeout == 600) {
                    timeout = 0;
                }

            }
        } catch (Exception e) {
            log.error("The test is failed with an error: " + e);
        }

    }

}

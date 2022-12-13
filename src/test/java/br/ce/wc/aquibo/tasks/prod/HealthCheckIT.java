package br.ce.wc.aquibo.tasks.prod;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HealthCheckIT {


    @Test
    public void healthCheckt() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.112:4444/wd/hub") , new ChromeOptions());
        try {
            driver.navigate().to("http://192.168.100.112:9999/tasks");
            driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
            String version = driver.findElement(By.id("version")).getText();
            System.out.println(version);
            Assert.assertTrue(version.startsWith("build"));
        }finally {
            driver.quit();
        }
    }

}

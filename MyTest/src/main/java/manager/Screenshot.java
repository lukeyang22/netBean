/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import org.openqa.selenium.*;
//import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.UIManager;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author lyang
 */
public class Screenshot {
     String destination=""; UIManager uimanager = new UIManager();
    public void getScreenshot(WebDriver driver,String screenshotname) throws Exception{
         String dateName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
         TakesScreenshot ts = (TakesScreenshot) driver;
         FileReader reader = new FileReader("src/main/java/config/config.properties");
         Properties properties = new Properties();
         properties.load(reader);
         File source=ts.getScreenshotAs(OutputType.FILE);
         destination ="C:/Users/luluk/Desktop/Screenshot/"+screenshotname+dateName+".png";
         File finalDestination = new File(destination);
         FileUtils.copyFile(source, finalDestination);
    }
    public void getScreenshot2(WebDriver driver, String w,String screenshotname) throws Exception{
        String dateName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
        WebElement element =driver.findElement(By.xpath(w));
        File scrFile = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/Users/luluk/Desktop/Screenshot/"+screenshotname+dateName+".png"));
    }
    public void getScreenshot3(WebDriver driver,String screenshotname, String w) throws Exception{
        String dateName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
        Actions actions = new Actions(driver);
        WebElement ele = driver.findElement(By.xpath("/html/body/div/main/div[1]/div/form/div[1]/label"));
        actions.moveToElement(ele).click().build().perform();
        TakesScreenshot ts = (TakesScreenshot) driver;
        FileReader reader = new FileReader("src/main/java/config/config.properties");
        Properties properties = new Properties();
        properties.load(reader);

        File source=ts.getScreenshotAs(OutputType.FILE);
        destination ="C:/Users/luluk/Desktop/Screenshot/"+screenshotname+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
    }
    public String addScreenshot(){
         return destination;
    }
}


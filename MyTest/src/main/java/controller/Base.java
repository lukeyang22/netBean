/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.safari.SafariDriver;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import manager.Screenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;
//import our own classes
import manager.*;
import org.openqa.selenium.support.ui.Select;
import util.*;
import webelement.PageObject;
import static org.openqa.selenium.By.*;

/**
 *
 * @author adm
 */
public class Base {
    private StringBuffer verificationErrors = new StringBuffer();
	private WebDriver driver;  private String webElement="";
	//Constructor	
        public Base() {
            super();     
        }

        //Call methods
        TestManager testManager = new TestManager();UIManager uimanager = new UIManager();Compare compare = new Compare();Load wait =new Load();Screenshot st= new Screenshot(); PageObject pg=new PageObject();

        protected void testBrowser(String bs) throws MalformedURLException, InterruptedException {
            String browser="";
            switch(bs){

                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*"); options.addArguments("--remote-debugging-port=9999");
                    driver = new ChromeDriver(options); break;
                case "firefox":
                    FirefoxOptions optionsFF = new FirefoxOptions();
                    //optionsFF.addArguments("--remote-allow-origins=*");
                    driver = new FirefoxDriver(optionsFF);break;
                case "edge":
                    EdgeOptions optionsE = new EdgeOptions();
                    optionsE.addArguments("--remote-allow-origins=*");
                    driver = new EdgeDriver(optionsE);break;

                default:
                    SafariOptions optionsS = new SafariOptions();
                    driver = new SafariDriver(optionsS);break;
            }            
            String baseUrl = "";
            try{baseUrl=testManager.setProperty("url");}catch(Exception e){System.err.print(e);}
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.get(baseUrl); driver.manage().window().maximize();wait.For(2);
        }
        protected void clickButton(String w, String txt ){
            if(driver.findElement(xpath(uimanager.FindElement(w))).getText().equals(txt))
            {
                driver.findElement(xpath(uimanager.FindElement(w))).click();
            }
            else {System.err.println("Could not find the button");}
        }
        protected void clickButton2(String w){
            driver.findElement(xpath(uimanager.FindElement(w))).click();
        }
        protected void enterText(String w, String txt){
           driver.findElement(xpath(uimanager.FindElement(w))).clear();
           driver.findElement(xpath(uimanager.FindElement(w))).sendKeys(txt);
        }
        protected void dropList(String w, String valueTxt){
            Select drplist = new Select(driver.findElement(xpath(uimanager.FindElement(w))));
            drplist.selectByVisibleText(valueTxt);
        }
        protected  void alertAccept(){
            driver.switchTo().alert().accept();
        }
        protected void BrowserClose(){
            driver.quit();
        }

    protected String verifyTitleText(String page, Integer i){
        String[]Text = pg.Table(page, i).split(";");        
        String r="";
        if(Text[0].equalsIgnoreCase(driver.findElement(xpath(Text[1])).getText()))
            r="true";
        else{ r="false";
            Actions action=new Actions(driver);
            WebElement web =driver.findElement(xpath(Text[1]));
            action.moveToElement(web).build().perform();
        }
        return r;
    }
    protected String verifyTableText(String table, Integer i){
        String[]Text = pg.Table(table, i).split(";");       
        String r="";
        if(Text[0].equalsIgnoreCase(driver.findElement(xpath(Text[1])).getAttribute("innerHTML")))
            r="true";
        else r="false";
        return r;
    }
    protected String verifyTableTextInfo(String table, Integer i){
        String[]Text = pg.Table(table, i).split(";");
        return "Expeted:"+Text[0]+"----Actual:"+driver.findElement(xpath(Text[1])).getAttribute("innerHTML").trim();
    }
    protected String verifyTitleTextInfo(String table, Integer i){
        String[]Text = pg.Table(table, i).split(";");
        return "Expeted:"+Text[0]+"----Actual:"+driver.findElement(xpath(Text[1])).getText();
    }
    protected WebDriver getdriver(){
        return driver=this.driver;
    }
    protected String screenshotName(String screenshotname) throws Exception{
        String dateName = new SimpleDateFormat("yyyyMMddhh").format(new Date()); String sn="";
        FileReader reader = new FileReader("src/main/java/config/config.properties");
        Properties properties = new Properties();
        properties.load(reader);
        sn=properties.getProperty("propertyScreenshot") +screenshotname+dateName+".png";
        return sn;
    }
    String DataTime="";
    public String getDateTime(){
        return DataTime = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
    }

    String rpLocation ="";
    public String getReportPath(){
        try{
            rpLocation = testManager.setProperty("report");
        }catch(Exception e){};
        return rpLocation;
    }
    public void clickLink(String lText){
       try{ driver.findElement(By.linkText(lText)).click();}catch(NoSuchElementException e){System.err.println(e);}
    }
    public String getText(String w){
        return driver.findElement(By.xpath(uimanager.FindElement(w))).getText();
    }       
}

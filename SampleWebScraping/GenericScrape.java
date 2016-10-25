package SampleWebScraping;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * GenericScrape.java
 * @author Han Liao
 * @version 1.0
 * 
 * Yo! This is the generic scraping class for which you can edit and alter 
 * for whatever purpose you think best.
 * You can follow along this in this class as well.
 */
public class GenericScrape {
    private static ArrayList<String> list = new ArrayList<String>();    //HOLDS ALL STRING URL
    private static ArrayList<String> list2 = new ArrayList<String>();   //HOLDS ALL DOWNLOAD LINKS
    private static final String URL = "https://www.youtube.com/playlist?list=PLVMZDcH6_O6ghEtIbTmrmHRNc6zVWdwYU";   //Modifiable playlist link
    private static final String URL2 = "http://youtubemp3.to/";     //DO NOT MODIFY
    
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "C://Users//Han Liao//Downloads//Libraries//chromedriver_win32//chromedriver.exe"); //MUST DEFINE PATH TO THE CHROMEDRIVER
        WebDriver driver = new ChromeDriver();  //Create an instance of the driver analogy to the remote control
                 
        driver.navigate().to(URL);  //Go to URL   
        
        List<WebElement> all_videos = driver.findElements(By.cssSelector("a[href *= 'watch']")); //Find all instant of blah blah
        
        //Adds the string of each video to the list 
        for(int i = 0; i < all_videos.size(); i++) {
            String result = all_videos.get(i).getAttribute("href");
            if( result != null) {
                list.add(result);
            }
            else {
                //Do nothing
            }
        }
        
        //Will explain too hard to comment on 
        for(int i = 3; i < list.size(); i += 2) {
            driver.navigate().to(URL2);
            driver.findElement(By.cssSelector("input[name = 'videoURL'")).sendKeys(list.get(i));         
            driver.findElement(By.cssSelector("input[type = 'submit'")).click();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String result = driver.findElement(By.cssSelector("a[class = 'button green-bg icon-right'")).getAttribute("href");
            list2.add(result);
        }
        
        //As the effort of the program increases, the more the comments turn into penis.
        for(int i = 0; i < list2.size(); i ++) {
            driver.navigate().to(list2.get(i));
        }        
    }
}
    

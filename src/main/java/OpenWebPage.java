import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenWebPage {

    WebDriver driver = null;

   public WebDriver openPage(String url)
   {

       WebDriverManager.chromedriver().setup();

       ChromeOptions options = new ChromeOptions();

       options.addArguments("start-maximized");
       options.addArguments("enable-automation");
       options.addArguments("--no-sandbox");
       options.addArguments("--disable-infobars");
       options.addArguments("--disable-dev-shm-usage");
       options.addArguments("--disable-browser-side-navigation");
       options.addArguments("--disable-gpu");
       driver = new ChromeDriver(options);
       driver.get(url);

       return driver;
   }


}

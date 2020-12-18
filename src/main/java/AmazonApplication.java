import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static util.UtilityStrings.*;

public class AmazonApplication {

    private static WebDriver driver;
    private static OpenWebPage openWebPage = new OpenWebPage();
    private static AmazonOptions amazonOptions = new AmazonOptions();

    /*
     *
     * READ THE README BEFORE USE
     *
     *
     * READ THE README BEFORE USE
     *
     *
     * READ THE README BEFORE USE
     *
     * READ :
     *
     * */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HELLO WORLD: ");

        String currentUrl = AMAZON_PS5_CONSOLE;

        driver = openWebPage.openPage(currentUrl);

        Thread.sleep(30000);

        while(driver.findElements(By.cssSelector(AMAZON_PLACE_ORDER_CSS)).isEmpty() )
        {
            //Couldn't get to the place order screen with in 20 seconds, restart the process
            driver.get(currentUrl);

            Thread.sleep(500);

            driver = amazonOptions.buyNow(driver);

            driver = amazonOptions.placeOrder(driver);
        }

        System.out.println("ORDER WOULD HAVE BEEN PLACE AT THIS POINT");

        //Sleep for 8 hours to leave message
        //Top stop this sleep hit the big red button on the top of the IDE or simply exit out of the IDE
        int minute = 60000;
        int hour = 60;

        Thread.sleep(8 * minute * hour);

        driver.close();

    }
}

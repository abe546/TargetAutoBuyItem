import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Instant;

import static util.UtilityStrings.*;

public class AmazonOptions
{

    public WebDriver buyNow(WebDriver driver) throws InterruptedException {

        int waitCount =0;

        while(waitCount < 20 && driver.findElements(By.id(AMAZON_BUY_NOW_BUTTON_ID)).isEmpty())
        {
            Thread.sleep(500);

            if(waitCount == 19)
            {
                System.out.println("Couldn't find buy now button, out of stock. Refreshing page to try again "+ Instant.now());
                waitCount = 0;
                //refresh the page and try again
                driver.navigate().refresh();
            }

            waitCount++;
        }

        WebElement buyNowButton = driver.findElement(By.id(AMAZON_BUY_NOW_BUTTON_ID));

        buyNowButton.click();

        return driver;
    }

    public WebDriver placeOrder(WebDriver driver) throws InterruptedException {

        int waitCount =0;

        while(waitCount < 40 && driver.findElements(By.cssSelector(AMAZON_PLACE_ORDER_CSS)).isEmpty())
        {
            Thread.sleep(500);

            driver.switchTo().frame(AMAZON_TURBO_IFRAME_ID);

            boolean alternateButtonEmpty = true; //Default to true for empty

            //Try alternate place order button
            alternateButtonEmpty = driver.findElements(By.id(AMAZON_TURBO_CHECKOUT_PLACE_ORDER_ID)).isEmpty();

            if(!alternateButtonEmpty)
            {
                //not empty
                WebElement placeOrderButton = driver.findElement(By.id(AMAZON_TURBO_CHECKOUT_PLACE_ORDER_ID));
                Thread.sleep(500);
                placeOrderButton.click();
                Thread.sleep(500);

                driver.switchTo().defaultContent();

                return driver;
            }

            if(waitCount == 36)
            {
                System.out.println("Couldn't find place order within 20 seconds");
                return  driver;
            }

            waitCount++;
        }

        WebElement placeOrderButton = driver.findElement(By.cssSelector(AMAZON_PLACE_ORDER_CSS));

        placeOrderButton.click();

        driver.switchTo().defaultContent();

        return driver;

    }
}

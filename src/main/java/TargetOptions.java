import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static util.UserCredentials.TARGET_CC_CVV_SECRET;
import static util.UserCredentials.TARGET_MASTER_PASSWORD;
import static util.UserCredentials.TARGET_MASTER_USERNAME;
import static util.UtilityStrings.*;

public class TargetOptions {

    private static Logger logger = Logger.getLogger(TargetOptions.class);
    private WebDriverWait wait;
    public WebDriver pickUpFromStore(WebDriver driver) throws InterruptedException {
        //Set max timeout to be 20 seconds

        int waitCount =0;

        //We will loop indefinitely, forever, until the pick up item button is available
        while (waitCount <20 && driver.findElements(By.cssSelector(TARGET_ORDER_PICKUP_CSS)).isEmpty())
        {
            if(waitCount == 19)
            {
                System.out.println("Couldn't find pick up button, out of stock. Refreshing page to try again "+ Instant.now());
                waitCount = 0;
                //refresh the page and try again
                driver.navigate().refresh();
            }

            Thread.sleep(750);

            waitCount++;
        }

        WebElement pickUpButton = driver.findElement(By.cssSelector(TARGET_ORDER_PICKUP_CSS));

        pickUpButton.click();

        return driver;
    }

    public WebDriver viewCartAndCheckOut(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, 10);

        WebElement viewCartCheckOut;

        //Try to get checkoutButton for 5 seconds
        int waitCount =0;
        while(driver.findElements(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS)).isEmpty() && waitCount < 20)
        {
            logger.info("ATTEMPTING TO WAIT FOR CHECKOUT BUTTON");
            Thread.sleep(500);
            waitCount++;
        }


        if(driver.findElements(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS)).isEmpty()) {

            //View cart not available, attempt to decline coverage AND THEN click checkout

            wait.until(presenceOfElementLocated(By.cssSelector(TARGET_DECLINE_COVERAGE)));

            if(driver.findElements(By.cssSelector(TARGET_DECLINE_COVERAGE)).isEmpty())
            {
                logger.error("VIEW CART NOT AVAILABLE AND DECLINE NOT PRESENT ERROR");
                throw new IllegalArgumentException("NO VIEW CART, NO DECLINE BUTTON");
            }

            WebElement declineCoverage = driver.findElement(By.cssSelector(TARGET_DECLINE_COVERAGE));

            declineCoverage.click();

            wait.until(presenceOfElementLocated(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS)));
        }

        viewCartCheckOut = driver.findElement(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS));

        viewCartCheckOut.click();

        return driver;
    }

    public WebDriver clickQuantity(WebDriver driver)
    {
        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.cssSelector(TARGET_QUANTITY_DROP_DOWN_CSS)));

        //Get maximum size of quantity options
        List<WebElement> qtyDropDownInfo = driver
                .findElement(By.xpath(TARGET_QUANTITY_DROP_DOWN_XPATH))
                .findElements(By.tagName(TARGET_QUANTITY_OPTION_TAG));

        if(qtyDropDownInfo.isEmpty())
        {
            //ISSUE
            logger.error("EMPTY QUANTIY INFO - "+TARGET_QUANTITY_DROP_DOWN_XPATH + TARGET_QUANTITY_OPTION_TAG);

            //TODO add remediation step
        }

        WebElement qtyDropDown = driver.findElement(By.cssSelector(TARGET_QUANTITY_DROP_DOWN_CSS));

        if(qtyDropDown == null)
        {
            //issue
            logger.error("NOT ABLE TO INTERACT WITH TARGET_VIEW_CART_AND_CHECKOUT_CSS - "+TARGET_QUANTITY_DROP_DOWN_CSS);

            //TODO add remediation step
        }

        qtyDropDown.click();
        //Click the last list entry
        qtyDropDownInfo.get(qtyDropDownInfo.size()-1).click();

        return driver;
    }

    public WebDriver clickCheckOutCart(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.cssSelector(TARGET_CHECKOUT_CART_CSS)));

        WebElement checkOutButton = driver.findElement(By.cssSelector(TARGET_CHECKOUT_CART_CSS));

        Thread.sleep(500);

        checkOutButton.click();

        return driver;
    }

    public WebDriver enterUserNamePasswordLogin(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.id(TARGET_USERNAME_ID)));

        WebElement userNameTextField = driver.findElement(By.id(TARGET_USERNAME_ID));

        Thread.sleep(500);

        userNameTextField.click();

        Thread.sleep(500);

        for(char character : TARGET_MASTER_USERNAME.toCharArray()) {
            userNameTextField.sendKeys(""+character);
            Thread.sleep(250);
        }

        WebElement passwordTextField = driver.findElement(By.id(TARGET_PASSWORD_ID));

        Thread.sleep(500);

        passwordTextField.click();

        Thread.sleep(500);

        for(char character : TARGET_MASTER_PASSWORD.toCharArray()) {
            passwordTextField.sendKeys(""+character);
            Thread.sleep(250);
        }

        WebElement loginButton = driver.findElement(By.id(TARGET_LOGIN_ID));

        Thread.sleep(1000);

        loginButton.click();

        return driver;
    }

    public WebDriver selectPayPalAsPaymentOption(WebDriver driver) {

        //This should be done manually and loaded to the automated browser context

        return driver;
    }

    public WebDriver inputCreditCardCvv(WebDriver driver) throws InterruptedException {

        int waitCount =0;

        while(waitCount < 20 && driver.findElements(By.id(TARGET_CC_CVV_ID)).isEmpty())
        {
            Thread.sleep(500);

            if(waitCount == 19)
            {
                //Its possibly that ccv is cached, since it did not pop up as an option
                //So we will return and assume it is working, if not then Target may force
                //textFields of a credit card to be populated. This must be done manually.
                return driver;
            }

            waitCount++;
        }

        WebElement ccCvv = driver.findElement(By.id(TARGET_CC_CVV_ID));

        Thread.sleep(500);

        ccCvv.click();

        Thread.sleep(500);

        for(char character : TARGET_CC_CVV_SECRET.toCharArray()) {
            ccCvv.sendKeys(""+character);
            Thread.sleep(250);
        }

        Thread.sleep(500);

        return driver;
    }

    public WebDriver placeOrder(WebDriver driver) throws InterruptedException {

        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.xpath(TARGET_PLACE_ORDER_XPATH)));

        WebElement placeOrder = driver.findElement(By.xpath(TARGET_PLACE_ORDER_XPATH));

        Thread.sleep(1000);

        placeOrder.click();

        return driver;
    }
}

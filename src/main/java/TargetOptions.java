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
    private String BASE_URL = null;
    private WebDriverWait wait;

    public TargetOptions() {

    }

    public TargetOptions(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public WebDriver pickUpFromStore(WebDriver driver) throws InterruptedException {

        ifErrorExistsClickOkAndRefesh(driver);

        //Set max timeout to be 20 seconds

        int waitCount = 0;

        //We will loop indefinitely, forever, until the pick up item button is available
        while (waitCount < 20 && driver.findElements(By.cssSelector(TARGET_ORDER_PICKUP_CSS)).isEmpty()) {
            if (waitCount == 19) {
                System.out.println("Couldn't find pick up button, out of stock. Refreshing page to try again " + Instant.now());
                waitCount = 0;
                //refresh the page and try again
                driver.navigate().refresh();
            }

            Thread.sleep(750);

            waitCount++;
        }

        WebElement pickUpButton = driver.findElement(By.cssSelector(TARGET_ORDER_PICKUP_CSS));

        pickUpButton.click();
        logger.info("PICKING UP FROM STORE : "+Instant.now());
        while(ifErrorExistsClickOkAndRefesh(driver))
        {
            pickUpButton.click();
            logger.info("PICKING UP FROM STORE : "+Instant.now());
            //Sleep 8 seconds
            Thread.sleep(8000);
        }

        return driver;
    }

    public WebDriver viewCartAndCheckOut(WebDriver driver) throws InterruptedException {

        wait = new WebDriverWait(driver, 10);

        WebElement viewCartCheckOut;

        //Try to get checkoutButton for 5 seconds
        int waitCount = 0;
        while (driver.findElements(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS)).isEmpty() && waitCount < 20) {
            logger.info("ATTEMPTING TO WAIT FOR CHECKOUT BUTTON");
            Thread.sleep(500);
            waitCount++;
        }


        if (driver.findElements(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS)).isEmpty()) {

            //View cart not available, attempt to decline coverage AND THEN click checkout

            wait.until(presenceOfElementLocated(By.cssSelector(TARGET_DECLINE_COVERAGE)));

            if (driver.findElements(By.cssSelector(TARGET_DECLINE_COVERAGE)).isEmpty()) {
                logger.error("VIEW CART NOT AVAILABLE AND DECLINE NOT PRESENT ERROR");
                throw new IllegalArgumentException("NO VIEW CART, NO DECLINE BUTTON");
            }

            WebElement declineCoverage = driver.findElement(By.cssSelector(TARGET_DECLINE_COVERAGE));

            declineCoverage.click();
            logger.info("DECLINING COVERAGE : "+Instant.now());
            wait.until(presenceOfElementLocated(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS)));
        }

        viewCartCheckOut = driver.findElement(By.cssSelector(TARGET_VIEW_CART_AND_CHECKOUT_CSS));

        viewCartCheckOut.click();
        logger.info("VIEW CART AND CHECKOUT : "+Instant.now());

        return driver;
    }

    public WebDriver clickQuantity(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.cssSelector(TARGET_QUANTITY_DROP_DOWN_CSS)));

        //Get maximum size of quantity options
        List<WebElement> qtyDropDownInfo = driver
                .findElement(By.xpath(TARGET_QUANTITY_DROP_DOWN_XPATH))
                .findElements(By.tagName(TARGET_QUANTITY_OPTION_TAG));

        while (qtyDropDownInfo.isEmpty()) {
            //Wait five seconds, then refresh
            driver.navigate().refresh();

            Thread.sleep(1000);

            wait = new WebDriverWait(driver, 5);

            wait.until(presenceOfElementLocated(By.cssSelector(TARGET_QUANTITY_DROP_DOWN_CSS)));

            qtyDropDownInfo = driver
                    .findElement(By.xpath(TARGET_QUANTITY_DROP_DOWN_XPATH))
                    .findElements(By.tagName(TARGET_QUANTITY_OPTION_TAG));
        }

        WebElement qtyDropDown = driver.findElement(By.cssSelector(TARGET_QUANTITY_DROP_DOWN_CSS));

        if (qtyDropDown == null) {
            //issue
            logger.error("NOT ABLE TO INTERACT WITH TARGET_VIEW_CART_AND_CHECKOUT_CSS - " + TARGET_QUANTITY_DROP_DOWN_CSS);

            //TODO add remediation step
        }

        qtyDropDown.click();
        //Click the last list entry
        qtyDropDownInfo.get(qtyDropDownInfo.size() - 1).click();
        logger.info("SELECTING QUANTITY : "+Instant.now());

        return driver;
    }

    public WebDriver clickCheckOutCart(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.cssSelector(TARGET_CHECKOUT_CART_CSS)));

        WebElement checkOutButton = driver.findElement(By.cssSelector(TARGET_CHECKOUT_CART_CSS));

        Thread.sleep(500);

        checkOutButton.click();
        logger.info("CLICKING CHECKOUT BUTTON : "+Instant.now());

        int count = 0;

        while (!driver.findElements(By.cssSelector(TARGET_CHECKOUT_CART_CSS)).isEmpty()) {
            checkOutButton.click();
            logger.info("CLICKING CHECKOUT BUTTON : "+Instant.now());
            //Click every 250 milliseconds
            Thread.sleep(250);
            count++;

            if (count == 120) //If we've been clicking checkout cart for 30 seconds refresh
            {
                driver.navigate().refresh();
                count = 0;
            }
        }

        int count =0;

        while (!driver.findElements(By.cssSelector(TARGET_CHECKOUT_CART_CSS)).isEmpty())
        {
            checkOutButton.click();
            //Click every 250 milliseconds
            Thread.sleep(250);
            count++;

            if(count == 120) //If we've been clicking checkout cart for 30 seconds refresh
            {
                driver.navigate().refresh();
                count = 0;
            }
        }

        return driver;
    }

    public WebDriver enterUserNamePasswordLogin(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.id(TARGET_USERNAME_ID)));

        WebElement userNameTextField = driver.findElement(By.id(TARGET_USERNAME_ID));

        Thread.sleep(500);

        userNameTextField.click();

        Thread.sleep(500);

        for (char character : TARGET_MASTER_USERNAME.toCharArray()) {
            userNameTextField.sendKeys("" + character);
            Thread.sleep(250);
        }

        WebElement passwordTextField = driver.findElement(By.id(TARGET_PASSWORD_ID));

        Thread.sleep(500);

        passwordTextField.click();

        Thread.sleep(500);

        for (char character : TARGET_MASTER_PASSWORD.toCharArray()) {
            passwordTextField.sendKeys("" + character);
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

        int waitCount = 0;

        while (waitCount < 10 && driver.findElements(By.id(TARGET_CC_CVV_ID)).isEmpty()) {
            Thread.sleep(500);

            if (waitCount == 9) {
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
        logger.info("CLICKING CVV BOX : "+Instant.now());

        Thread.sleep(500);

        logger.info("CVV SENDING KEYS : "+Instant.now());

        for (char character : TARGET_CC_CVV_SECRET.toCharArray()) {
            ccCvv.sendKeys("" + character);
            Thread.sleep(250);
        }

        logger.info("CVV SENT KEYS : "+Instant.now());

        Thread.sleep(500);

        return driver;
    }

    public WebDriver placeOrder(WebDriver driver) throws InterruptedException {

        wait = new WebDriverWait(driver, 20);

        wait.until(presenceOfElementLocated(By.xpath(TARGET_PLACE_ORDER_XPATH)));

        WebElement placeOrder = driver.findElement(By.xpath(TARGET_PLACE_ORDER_XPATH));

        Thread.sleep(1000);

        logger.info("CLICKING PLACE ORDER : "+Instant.now());
        placeOrder.click();
        logger.info("CLICKED PLACE ORDER : "+Instant.now());
        Thread.sleep(5000);
        int count = 0;
        while (!driver.findElements(By.xpath(TARGET_PLACE_ORDER_XPATH)).isEmpty()) {
            //As long as place order button is visible, click it every 250 milliseconds
            logger.info("CLICKING PLACE ORDER : "+Instant.now());
            placeOrder.click();
            logger.info("CLICKED PLACE ORDER : "+Instant.now());
            Thread.sleep(500);

            //After 5 minutes of clicking, refresh the page
            if (count == 600) {
                count = 0;
                driver.navigate().refresh();
            }

        }

        int count =0;
        while (!driver.findElements(By.xpath(TARGET_PLACE_ORDER_XPATH)).isEmpty())
        {
            //As long as place order button is visible, click it every 250 milliseconds
            placeOrder.click();
            Thread.sleep(250);

            //After 5 minutes of clicking, refresh the page
            if(count == 1200)
            {
                count =0;
                driver.navigate().refresh();
            }

        }

        return driver;
    }

    /**
     * If an error button exists click OK
     *
     * @param driver
     * @return
     */
    public boolean ifErrorExistsClickOkAndRefesh(WebDriver driver) throws InterruptedException {
        if (!driver.findElements(By.xpath(TARGET_CONTINUE_ERROR_BUTTON_XPATH)).isEmpty()) {
            logger.info("ERROR ENCOUNTERED, REFRESHING");
            WebElement errorButton = driver.findElement(By.xpath(TARGET_CONTINUE_ERROR_BUTTON_XPATH));

            errorButton.click();

            Thread.sleep(500);
            logger.info("REFRESHING");
            driver.navigate().refresh();

            return true; //There was an error
        }

        return false; //No error
    }
}

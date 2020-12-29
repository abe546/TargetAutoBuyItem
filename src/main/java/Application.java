import org.openqa.selenium.WebDriver;

import static util.UserCredentials.TARGET_CC_CVV_SECRET;
import static util.UtilityStrings.FUNYONS_URL;
import static util.UtilityStrings.PS5_DIGITAL_URL;
import static util.UtilityStrings.PS5_PHYSICAL_URL;

public class Application {

    private static WebDriver driver;
    private static OpenWebPage openWebPage = new OpenWebPage();
    private static TargetOptions targetOptions;

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
        System.out.println("CURRENT CVV : " + TARGET_CC_CVV_SECRET);
        String currentUrl = PS5_DIGITAL_URL;

        targetOptions = new TargetOptions(currentUrl);
        driver = openWebPage.openPage(currentUrl);
        Thread.sleep(30000);//30 second sleep
        for(int i =0; i < 3; i++){
        driver.get(currentUrl);

        //Forced to manually login for security purposed (Target forces 401 otherwise)


        driver = targetOptions.pickUpFromStore(driver);

        driver = targetOptions.viewCartAndCheckOut(driver);

        driver = targetOptions.clickQuantity(driver);

        driver = targetOptions.clickCheckOutCart(driver);

        //https://stackoverflow.com/questions/63095739/cant-login-to-target-com-with-selenium
        //driver = targetOptions.enterUserNamePasswordLogin(driver);
        //driver = targetOptions.selectPayPalAsPaymentOption(driver);

        driver = targetOptions.inputCreditCardCvv(driver);

        /*
         * THE FINAL STEP, PLACE ORDER, THIS WILL PLACE AN ORDER FOR THE MAXIMUM ALLOWED AMOUNT OF ITEMS
         */

        driver = targetOptions.placeOrder(driver);

        System.out.println("ORDER WOULD HAVE BEEN PLACE AT THIS POINT");
    }

        driver.close();

    }
}

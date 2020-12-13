import org.openqa.selenium.WebDriver;

import static util.UtilityStrings.FUNYONS_URL;
import static util.UtilityStrings.PS5_CONTROLLER_URL;
import static util.UtilityStrings.PS5_DIGITAL_URL;

public class Application {

    private static WebDriver driver;
    private static OpenWebPage openWebPage = new OpenWebPage();
    private static TargetOptions targetOptions = new TargetOptions();

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

        driver = openWebPage.openPage(PS5_DIGITAL_URL);

        //Forced to manually login for security purposed (Target forces 401 otherwise)
        Thread.sleep(30000);//2 Minute sleep

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

        //Sleep for 8 hours to leave message
        //Top stop this sleep hit the big red button on the top of the IDE or simply exit out of the IDE
        int minute = 60000;
        int hour = 60;

        Thread.sleep(8 * minute * hour);

        driver.close();

    }
}

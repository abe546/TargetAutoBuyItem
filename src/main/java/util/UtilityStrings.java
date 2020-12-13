package util;

public class UtilityStrings {

    //Latest version taken from : https://chromedriver.storage.googleapis.com/index.html
    public static final String CHROME_DRIVER_VERSION = "88.0.4324.27";

    //URLS OF INTEREST

    public static final String FUNYONS_URL = "https://www.target.com/p/funyuns-onion-flavored-rings-6oz/-/A-13326808#lnk=sametab";
    public static final String PS5_CONTROLLER_URL = "https://www.target.com/p/dualsense-wireless-controller-for-playstation-5/-/A-81114477";
    public static final String PS5_DIGITAL_URL = "https://www.target.com/p/playstation-5-digital-edition-console/-/A-81114596";
    //Target options
    //Order pick up button
    public static final String TARGET_ORDER_PICKUP_CSS = "*[data-test=\"orderPickupButton\"]";
    //View cart and check out
    public static final String TARGET_VIEW_CART_AND_CHECKOUT_CSS = "*[data-test=\"addToCartModalViewCartCheckout\"]";
    //Outer quantity option
    public static final String TARGET_QUANTITY_DROP_DOWN_CSS = "*[data-test=\"cartItem-qty\"]";
    public static final String TARGET_QUANTITY_DROP_DOWN_XPATH ="//*[@data-test='cartItem-qty']";
    //Quantity Of Items options
    public static final String TARGET_QUANTITY_OPTION_TAG = "option";
    //Check out car
    public static final String TARGET_CHECKOUT_CART_CSS = "*[data-test=\"checkout-button\"]";
    //User name text section
    public static final String TARGET_USERNAME_ID = "username";
    //Password text section
    public static final String TARGET_PASSWORD_ID = "password";
    //Login button
    public static final String TARGET_LOGIN_ID = "login";
    //Decline coverage option
    public static final String TARGET_DECLINE_COVERAGE ="*[data-test=\"espModalContent-declineCoverageButton\"]";
    //CC CVV Text Field
    public static final String TARGET_CC_CVV_ID = "creditCardInput-cvv";
    //Place order button
    public static final String TARGET_PLACE_ORDER_XPATH = "//*[@data-test='placeOrderButton']";
}

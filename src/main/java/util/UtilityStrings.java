package util;

public class UtilityStrings {

    //Latest version taken from : https://chromedriver.storage.googleapis.com/index.html
    public static final String CHROME_DRIVER_VERSION = "88.0.4324.27";

    //URLS OF INTEREST

    public static final String FUNYONS_URL = "https://www.target.com/p/funyuns-onion-flavored-rings-6oz/-/A-13326808#lnk=sametab";
    public static final String PRINGLES_URL = "https://www.target.com/p/pringles-original-flavored-potato-crisps-chips-5-2oz/-/A-13053936#lnk=sametab";
    public static final String PS5_CONTROLLER_URL = "https://www.target.com/p/dualsense-wireless-controller-for-playstation-5/-/A-81114477";
    public static final String PS5_DIGITAL_URL = "https://www.target.com/p/playstation-5-digital-edition-console/-/A-81114596";
    public static final String PS5_PHYSICAL_URL = "https://www.target.com/p/playstation-5-console/-/A-81114595";
    public static final String PS5_DEMON_SOULS = "https://www.amazon.com/Demons-Souls-PlayStation-5/dp/B08FC5TTBF/ref=sr_1_1?dchild=1&keywords=demon+souls+playstation+5&qid=1608259741&sr=8-1";
    public static final String AMAZON_TEST = "https://www.amazon.com/gp/product/B07K98GCXM/ref=ppx_yo_dt_b_asin_title_o02_s01?ie=UTF8&psc=1";
    public static final String AMAZON_PS5_CONSOLE = "https://www.amazon.com/PlayStation-5-Console/dp/B08FC5L3RG/ref=mp_s_a_1_13?dchild=1&keywords=playstation+5+console&qid=1608258786&sr=8-13";
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



    //Amazon Options
    public static final String AMAZON_BUY_NOW_BUTTON_ID = "buy-now-button";
    public static final String AMAZON_PLACE_ORDER_CSS = "#submitOrderButtonId .a-button-input";
    public static final String AMAZON_TURBO_CHECKOUT_PLACE_ORDER_ID = "turbo-checkout-pyo-button";
    public static final String AMAZON_TURBO_IFRAME_ID = "turbo-checkout-iframe";
}

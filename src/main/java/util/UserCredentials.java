package util;

public class UserCredentials {

    //Target credentials
    public static final String TARGET_MASTER_USERNAME = System.getenv("TARGET_MASTER_USERNAME");
    public static final String TARGET_MASTER_PASSWORD = System.getenv("TARGET_MASTER_PASSWORD");
    public static final String TARGET_CC_CVV_SECRET = System.getenv("TARGET_CVV");
}

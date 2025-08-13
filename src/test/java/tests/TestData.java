package tests;

import config.ApiProvider;

public class TestData {

    protected String userPath = "/users/";
    protected String userRegister = "/register";
    protected String userLogin = "/login";

    protected String userPass = "pistol";
    protected String userEmail = ApiProvider.getApiConfig().userEmail();
    protected String userPassLogin = ApiProvider.getApiConfig().userPassLogin();


    protected int existingUserId = 2;
    protected int newUserId = 713;


    protected static final String apiKeyName = "x-api-key";
    protected static final String apiKey = "reqres-free-v1";
}

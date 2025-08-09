package tests;

import config.ApiProvider;

public class TestData {
    // Константы для эндпоинтов
    protected String userPath = "/users/";
    protected String userRegister = "/register";
    protected String userLogin = "/login";

    // Тестовые данные из конфигурации
    protected String userPass = "pistol";  // Пароль для регистрации (из документации API)
    protected String userEmail = ApiProvider.getApiConfig().userEmail();
    protected String userPassLogin = ApiProvider.getApiConfig().userPassLogin();

    // Общие тестовые данные
    protected int existingUserId = 2;
    protected int newUserId = 713;

    // Для авторизации
    protected static final String apiKeyName = "x-api-key";
    protected static final String apiKey = "reqres-free-v1";
}

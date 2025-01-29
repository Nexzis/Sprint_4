package praktikum;

public class EnvConfig {

    // Адрес тестового сайта
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final int DELAY_TIME = 5;

    // Метод задержки
    public static void delay(int time) throws InterruptedException {
        Thread.sleep(time);
    }
}

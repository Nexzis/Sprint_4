package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.AboutRentPage;
import praktikum.pages.MainPage;
import praktikum.pages.OrderModalPage;
import praktikum.pages.WhoIsTheScooterForPage;

@RunWith(Parameterized.class)

public class orderPlacementTest {
    @Rule
    public DriverRule factory = new DriverRule();
    private final String placeOfOrderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final int metroStation;
    private final String phoneNumber;
    private final String whenDelivery;
    private final String rentTime;


    public orderPlacementTest(String placeOfOrderButton, String name, String surname, String address, int metroStation, String phoneNumber, String whenDelivery, String rentTime) {
        this.placeOfOrderButton = placeOfOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.whenDelivery = whenDelivery;
        this.rentTime = rentTime;

    }

    // Параметризация данных
    @Parameterized.Parameters
    public static Object[][] getTestData()  {
        return new Object[][] {
                {"header", "Роман", "Никулин", "Дом Никулина", 2 , "89992224411", "011" , "трое суток" },
                {"header", "Анна", "Кошечкина", "Улица академика Янгеля", 4 , "89992224422", "009" , "двое суток" },
                {"middle", "Юлия", "Костина", "Аннино", 33 , "89991114411", "003" , "сутки" },
                {"middle", "Александр", "Небритов", "Улица Янгеля", 23 , "89992228811", "015" , "пятеро суток" },
        };
    }


    @Test
    public void checkOrderPlacement() throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // Настройка WebDriverManager
        WebDriver driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        // скроем куки
        mainPage.confirmCookie();
        // Получим указание, по какой кнопке сделаем тест и кликнем на неё
        mainPage.getOrderButtonAndClick(placeOfOrderButton);
        WhoIsTheScooterForPage whoIsTheScooterForPage = new WhoIsTheScooterForPage(driver);
        // заполним поля имя, фамилия, адрес, станция метро, номер телефона
        whoIsTheScooterForPage.setName(name);
        whoIsTheScooterForPage.setSurname(surname);
        whoIsTheScooterForPage.setAddress(address);
        whoIsTheScooterForPage.setMetroStation(metroStation);
        whoIsTheScooterForPage.setPhoneNumber(phoneNumber);
        // кликнем далее
        whoIsTheScooterForPage.clickNextButton();

        AboutRentPage aboutRentPage = new AboutRentPage(driver);
        // заполним обязательные поля для заказа
        // когда доставить, время аренды
        aboutRentPage.setWhenDelivery(whenDelivery);
        aboutRentPage.setRentTime(rentTime);
        // кликнем заказать
        aboutRentPage.clickOrderButton();

        OrderModalPage orderModalPage = new OrderModalPage(driver);
        // кликнем подтвердить заказ
        orderModalPage.confirmOrder();
        // проверим что заказ появился
        orderModalPage.checkOrderConfirmed();
    }
}

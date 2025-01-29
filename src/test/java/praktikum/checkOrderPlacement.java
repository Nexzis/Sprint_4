package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.AboutRent;
import praktikum.pages.MainPage;
import praktikum.pages.OrderModal;
import praktikum.pages.WhoIsTheScooterFor;

@RunWith(Parameterized.class)

public class checkOrderPlacement {
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


    public checkOrderPlacement(String placeOfOrderButton,String name, String surname, String address, int metroStation, String phoneNumber, String whenDelivery, String rentTime) {
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
    public void checkOrder() throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // Настройка WebDriverManager
        WebDriver driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        // скроем куки
        mainPage.confirmCookie();
        // Получим указание, по какой кнопке сделаем тест и кликнем на неё
        mainPage.getOrderButtonAndClick(placeOfOrderButton);
        WhoIsTheScooterFor whoIsTheScooterFor = new WhoIsTheScooterFor(driver);
        // заполним поля имя, фамилия, адрес, станция метро, номер телефона
        whoIsTheScooterFor.setName(name);
        whoIsTheScooterFor.setSurname(surname);
        whoIsTheScooterFor.setAddress(address);
        whoIsTheScooterFor.setMetroStation(metroStation);
        whoIsTheScooterFor.setPhoneNumber(phoneNumber);
        // кликнем далее
        whoIsTheScooterFor.clickNextButton();

        AboutRent aboutRent = new AboutRent(driver);
        // заполним обязательные поля для заказа
        // когда доставить, время аренды
        aboutRent.setWhenDelivery(whenDelivery);
        aboutRent.setRentTime(rentTime);
        // кликнем заказать
        aboutRent.clickOrderButton();

        OrderModal orderModal = new OrderModal(driver);
        // кликнем подтвердить заказ
        orderModal.confirmOrder();
        // проверим что заказ появился
        orderModal.checkOrderConfirmed();
    }
}

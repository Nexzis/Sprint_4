package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class WhoIsTheScooterForPage {
    private final WebDriver driver;
    // Локатор кнопки Далее
    private static final By nextButton = By.className("Button_Middle__1CSJM");

    // Локатор поля с именем
    private static final By name = By.xpath(".//input[contains(@placeholder, '* Имя')]");

    // Локатор поля с фамилией
    private static final By surname = By.xpath(".//input[contains(@placeholder, '* Фамилия')]");

    // Локатор поля с адресом
    private static final By address = By.xpath(".//input[contains(@placeholder, '* Адрес: куда привезти заказ')]");

    // Локатор поля с комбобоксом станций метро
    private static final By metroCombobox = By.className("select-search__input");

    // Локатор поля с номером телефона
    private static final By phoneNumber = By.xpath(".//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]");


    public WhoIsTheScooterForPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод заполнения имени
    public void setName(String name) {
        driver.findElement(this.name).click();
        driver.findElement(this.name).sendKeys(name);
    }

    // Метод заполнения фамилии
    public void setSurname(String surname) {
        driver.findElement(this.surname).click();
        driver.findElement(this.surname).sendKeys(surname);
    }

    // Метод заполнения адреса
    public void setAddress(String address) {
        driver.findElement(this.address).click();
        driver.findElement(this.address).sendKeys(address);
    }

    // Метод заполнения станции метро
    public void setMetroStation(int stationIndex) {
        driver.findElement(metroCombobox).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DELAY_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("select-search__row")));
        driver.findElement(By.xpath(".//li/button[@value='" + stationIndex + "']")).click();
    }

    // Метод заполнения номера телефона
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(this.phoneNumber).click();
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
    }

    // Метод клика по кнопке далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}

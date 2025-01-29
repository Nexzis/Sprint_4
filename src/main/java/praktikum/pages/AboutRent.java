package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class AboutRent {
    private final WebDriver driver;

    public AboutRent(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор комбобокса "когда привезти самокат"
    static By whenDeliveryCombobox = By.xpath(".//input[contains(@placeholder, '* Когда привезти самокат')]");

    // Локатор комбобокса "срок аренды"
    static By rentTimeCombobox = By.xpath(".//*[contains(@class, 'Dropdown-root')]"); //By.className("Dropdown-root Order_FilledDate__1pb8n");

    // Локатор кнопки заказать
    static By orderButton = By.xpath(".//*[contains(@class, 'Button_Middle__1CSJM') and text() = 'Заказать']"); //By.className("Dropdown-root Order_FilledDate__1pb8n");

    // Метод для установки даты доставки
    public void setWhenDelivery(String deliveryDate) {
        driver.findElement(whenDeliveryCombobox).click();
        By whenDelivery = By.xpath(".//*[contains(@class, 'react-datepicker__day--" + deliveryDate + "')]");
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DELAY_TIME))
                .until(ExpectedConditions.elementToBeClickable(whenDelivery));
        driver.findElement(whenDelivery).click();
    }

    // Метод для установки времени аренды
    public void setRentTime(String rentTime) {
        driver.findElement(rentTimeCombobox).click();
        By timeOfRent = By.xpath(".//*[contains(@class , 'Dropdown-option') and text() ='" + rentTime + "']");
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DELAY_TIME))
                .until(ExpectedConditions.elementToBeClickable(timeOfRent));
        driver.findElement(timeOfRent).click();
    }

    // Метод для клика на кнопку заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
}



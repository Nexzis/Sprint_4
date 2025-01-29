package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderModal {
    private final WebDriver driver;

    public OrderModal(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для кнопки Да в окне заказа
    static By yesButton = By.xpath(".//*[contains(@class, 'Button_Middle__1CSJM') and text() = 'Да']");

    // Метод для подтверждения заказа
    public void confirmOrder() {
        driver.findElement(yesButton).click();
    }

    // Локатор окна заказ оформлен
    static By orderConfirmed = By.className("Order_NextButton__1_rCA");

    // Метод для проверки появления окна "Заказ оформлен"
    public void checkOrderConfirmed() {

        if (driver.findElements(orderConfirmed).isEmpty()) {
            throw new AssertionError("Окно 'Заказ оформлен' не появилось в DOM!");
        }
    }

}
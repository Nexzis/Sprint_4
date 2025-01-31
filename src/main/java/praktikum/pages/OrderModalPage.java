package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderModalPage {
    private final WebDriver driver;
    // Локатор окна заказ оформлен
    private static final By orderConfirmed = By.className("Order_NextButton__1_rCA");
    // Локатор для кнопки Да в окне заказа
    private static final By yesButton = By.xpath(".//*[contains(@class, 'Button_Middle__1CSJM') and text() = 'Да']");


    public OrderModalPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для подтверждения заказа
    public void confirmOrder() {
        driver.findElement(yesButton).click();
    }

    // Метод для проверки появления окна "Заказ оформлен"
    public void checkOrderConfirmed() {
        if (driver.findElements(orderConfirmed).isEmpty()) {
            throw new AssertionError("Окно 'Заказ оформлен' не появилось в DOM!");
        }
    }

}
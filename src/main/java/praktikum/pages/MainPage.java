package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для кнопки принять куки
    static By cookieButton = By.id("rcc-confirm-button");

    //Метод которым подтверждаем куки
    public void confirmCookie() {
        driver.findElement(cookieButton).click();
    }

    // Метод для получения локатора вопроса
    public By getQuestionLocator(int index) {
        return By.id("accordion__heading-" + index);
    }

    // Метод для получения локатора описания
    public By getDescriptionLocator(int index) {
        return By.id("accordion__panel-" + index);
    }

    // Метод для скролла до элемента и клика по нему
    public void scrollToElementAndClick(By locator) {
        WebElement targetElement = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
        targetElement.click();
    }

    // Метод для ожидания появления описания вопросов FAQ
    public void waitForLoadFAQDescription(By descriptionLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DELAY_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(descriptionLocator));
    }

    // Локатор кнопки в хэдере
    static By orderHeaderButton = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button");

    // Метод клика по кнопке заказать в шапке
    public By clickOrderHeaderButton() {
        driver.findElement(orderHeaderButton).click();
        return orderHeaderButton;
    }

    // Локатор кнопки в хэдере
    static By orderMiddleButton = By.className("Button_Middle__1CSJM");

    // Метод клика по кнопке заказать в середине
    public By clickOrderMiddleButton() {
        scrollToElementAndClick(orderMiddleButton);
        return orderMiddleButton;
    }

    // Метод для получения типа теста (по клику в хэдэре или в середине страницы)
    public Object getOrderButtonAndClick(String place) {
        if (place == "middle") {
            return clickOrderMiddleButton();
        } else {
            return clickOrderHeaderButton();
        }
    }

}

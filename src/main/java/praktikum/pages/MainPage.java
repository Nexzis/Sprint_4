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

    // Локатор для кнопки принять куки
    private static final By cookieButton = By.id("rcc-confirm-button");
    // Локатор кнопки в хэдере
    private static final By orderHeaderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]/button");
    // Локатор кнопки в центре сайта
    private static final By orderMiddleButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод, которым подтверждаем куки
    public void confirmCookie() {
        driver.findElement(cookieButton).click();
    }

    // Метод для получения локатора вопроса
    public String getQuestionLocator(int index) {
        return "accordion__heading-" + index;
    }

    // Метод для получения локатора описания
    public String getDescriptionLocator(int index) {
        return "accordion__panel-" + index;
    }

    // Метод для скролла до элемента и клика по нему
    public void scrollToElement(By locator) {
        WebElement targetElement = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
    }

    // Метод для ожидания появления описания вопросов FAQ
    public void waitForLoadFAQDescription(By descriptionLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DELAY_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(descriptionLocator));
    }

    // Метод клика по полю с FAQ вопросом
    public void clickFAQQuestion(By locator) {
        driver.findElement(locator).click();
    }

    // Метод клика по кнопке заказать в шапке
    public void clickOrderHeaderButton() {
        driver.findElement(orderHeaderButton).click();
    }

    // Метод клика по кнопке заказать в середине
    public void clickOrderMiddleButton() {
        driver.findElement(orderMiddleButton).click();
    }

    // Метод для получения типа теста (по клику в хэдэре или в середине страницы)
    public void getOrderButtonAndClick(String place) {
        if (place == "middle") {
            scrollToElement(orderMiddleButton);
            clickOrderMiddleButton();
        } else {
            clickOrderHeaderButton();
        }
    }

}

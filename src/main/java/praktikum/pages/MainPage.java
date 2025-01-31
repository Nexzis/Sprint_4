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
    // Локатор вопроса FAQ
    private static final String questionFAQ = "accordion__heading-";
    // Локатор описания вопроса FAQ
    private static final String descriptionFAQ = "accordion__panel-";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод, которым подтверждаем куки
    public void confirmCookie() {
        driver.findElement(cookieButton).click();
    }

    // Метод для получения локатора вопроса
    public By getQuestionLocator(int index) {
        return By.id(questionFAQ + index);
    }

    // Метод для получения локатора описания
    public By getDescriptionLocator(int index) {
        return By.id(descriptionFAQ + index);
    }

    // Метод для скролла до элемента
    public void scrollToElement(By locator) {
        WebElement targetElement = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
    }

    // Клик по вопросу FAQ
    public void clickFAQQuestion(int index) {
        driver.findElement(getQuestionLocator(index)).click();
    }

    // Метод для ожидания появления описания вопросов FAQ
    public void waitForLoadFAQDescription(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DELAY_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(getDescriptionLocator(index)));
    }

    // Получение текста описания FAQ
    public String getFAQDescriptionText(int index) {
        return driver.findElement(getDescriptionLocator(index)).getText();
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

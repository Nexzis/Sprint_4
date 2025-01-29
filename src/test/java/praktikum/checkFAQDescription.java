package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class checkFAQDescription {
    @Rule
    public DriverRule factory = new DriverRule();

    private final int index;
    private final String expectedText;

    public checkFAQDescription(int index, String expectedText) {
        this.index = index;
        this.expectedText = expectedText;
    }

    // Параметризация данных
    @Parameterized.Parameters
    public static Object[][] getTestData()  {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }


    @Test
    public void testFAQ() {
        WebDriverManager.chromedriver().setup(); // Настройка WebDriverManager
        WebDriver driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        // скроем куки
        mainPage.confirmCookie();

        // Получение локаторов вопроса и описания
        By questionLocator = mainPage.getQuestionLocator(index);
        By descriptionLocator = mainPage.getDescriptionLocator(index);

        // Скролл до элемента и клик по нему
        mainPage.scrollToElementAndClick(questionLocator);

        // Ожидание загрузки текста ответа
        mainPage.waitForLoadFAQDescription(descriptionLocator);

        // Проверяем текст
        String actualText = driver.findElement(descriptionLocator).getText();
        assertEquals("Текст не соответствует ожидаемому " + expectedText, expectedText, actualText);
    }
}

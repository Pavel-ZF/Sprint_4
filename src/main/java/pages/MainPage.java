package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class MainPage {

    private final WebDriver driver;

    /*Блок кода с необходимыми локаторами*/
    // Кнопка согласия на обработку куки
    private final By cookieBtn = By.id("rcc-confirm-button");
    // Блок с вопросами FAQ
    public static By blockFAQ = By.xpath(".//div[@class='Home_FAQ__3uVm4']");
    //Элемент аккордеон с вопросом
    private final By questionBtn = By.xpath(".//*[@id=\"accordion__heading-0\"]");

    // Контейнер с текстом вопроса в FAQ
    private final By text = By.xpath(".//*[@id=\"accordion__panel-0\"]/p");
    // Кнопка "Заказать" в шапке
    private final By orderBtnInHeader = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Кнопка "Заказать" в теле
    private final By orderBtnInBody = By.xpath(".//div[4]/div[2]/div[5]/button");

    //конструктор класса MainPage
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

        //1. Кликнуть по кнопке согласия на обработку Coockie
        public void clickCookieBtn() {
            driver.findElement(cookieBtn).click();

        }

        //2. скроллим до элемента и кликаем по аккардеону в FAQ
        public void clickQuestionButton () {
            WebElement element = driver.findElement(blockFAQ);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(questionBtn).click();

        }
        //3. Ожидаем раскрытия элемента и проверяем что текст 1 ответа соответствует ОР
        public void checkTextFaq() {

            new WebDriverWait(driver, 10).until(driver -> (driver.findElement(text).getText() != null
                    && !driver.findElement(text).getText().isEmpty()
            ));

            String questionTextFaq = driver.findElement(text).getText();
            String actual = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
            assertEquals(actual, questionTextFaq);

        }

        /* Блок методов для теста кнопок Заказать */
        /*
        //Клик по кнопке "Заказать" в шапке
        public MainPage clickOrderBtnInHeader() {
            driver.findElement(orderBtnInHeader).click();
            return this;}
        //Клик по кнопке "Заказать" в теле
        public MainPage orderBtnInBody() {
        //кликаем на элемент
        river.findElement(orderBtnInBody).click();
            return this;} */

    // Получение элемнтов типа кнопка "Заказать" с ГС и выбор ее
       public void getBtnsOrderList(int clickBtn) {
           WebElement element = driver.findElement(orderBtnInBody);
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
           List<WebElement> btnsOrderListArray = driver.findElements(By.xpath(".//button[contains(@class,'Button_Button__ra12g')]"));
           btnsOrderListArray.get(clickBtn).click();
        }


    }




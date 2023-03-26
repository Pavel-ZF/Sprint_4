package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage {

    private final WebDriver driver;

    /*Блок кода с необходимыми локаторами*/
    // Кнопка согласия на обработку куки
    private final By cookieBtn = By.id("rcc-confirm-button");

    //Элемент аккордеон с вопросом
    private final By questionBtn = By.xpath(".//*[@id=\"accordion__heading-0\"]");

    private final By[] faqQuestionList = {By.xpath(".//*[@id='accordion__heading-0']"),
            By.xpath(".//*[@id='accordion__heading-1']"),
            By.xpath(".//*[@id='accordion__heading-2']"),
            By.xpath(".//*[@id='accordion__heading-3']"),
            By.xpath(".//*[@id='accordion__heading-4']"),
            By.xpath(".//*[@id='accordion__heading-5']"),
            By.xpath(".//*[@id='accordion__heading-6']"),
            By.xpath(".//*[@id='accordion__heading-7']")};


    //Массив ответов
   // private final By[] faqAnsw = new  By.ByCssSelector[]{new  By.ByCssSelector(".accordion__button")};

    private final By[] faqAnswerList = {By.xpath(".//*[@id='accordion__panel-0']"),
            By.xpath(".//*[@id='accordion__panel-1']"),
            By.xpath(".//*[@id='accordion__panel-2']"),
            By.xpath(".//*[@id='accordion__panel-3']"),
            By.xpath(".//*[@id='accordion__panel-4']"),
            By.xpath(".//*[@id='accordion__panel-5']"),
            By.xpath(".//*[@id='accordion__panel-6']"),
            By.xpath(".//*[@id='accordion__panel-7']")};

    //конструктор класса MainPage
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //1. Кликнуть по кнопке согласия на обработку Coockie
    public void clickCookieBtn() {
            driver.findElement(cookieBtn).click();
        }

    //2. Получение текста вопроса и ответа на него в элементах аккардеон блока FAQ
    public boolean clickQuestionButton(String question, String answer) {
        int j = 0;
        //проверяем что текст вопроса соответствует
        for (int i = 0; i < faqQuestionList.length; i++) {
            WebElement element = driver.findElement(faqQuestionList[i]); //Находим элемент с индексом
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);//Cкролл до него
            if (question.equals(driver.findElement(faqQuestionList[i]).getText())) { //Получить текст вопроса с элемнта
                driver.findElement(faqQuestionList[i]).click();//Кликаем на аккардеон
                //проверяем что текст ответа соответствует
                if (answer.equals(driver.findElement(faqAnswerList[i]).getText())) {//Получить текст ответа
                    j = i; // Присваиваем переменной J значение переменной I
                }
            }
        }
        return driver.findElement(faqAnswerList[j]).isDisplayed();// Проверяем что блок с ответом видно
    }
    //Получение элемнтов типа кнопка "Заказать" с ГС и клик по 1 из них (значение кнопки передаем в параметрах в тесте)
       public void getBtnsOrderList(int clickBtn) {
           List<WebElement> btnsOrderListArray = driver.findElements(By.xpath(".//button[text()='Заказать']"));
           btnsOrderListArray.get(clickBtn).click();
        }
    }




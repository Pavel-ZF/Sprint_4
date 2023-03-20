import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)//Аннотация говорящая что тест будет параметризированным

public class RentalFormTest { //Тестовый клсс (класс в котором будет тест)
    private WebDriver driver; // Объевляем вебдрайвер
    /* Параметры со страницы OrderPage */
    @Parameterized.Parameter(0) // Передаем сюда индекс кнопки Заказать на ГС. 1 или 3
    public int ordBtn;
    @Parameterized.Parameter(1) // Передаем сюда параметр поля Имя
    public String name;
    @Parameterized.Parameter(2) // Передаем сюда параметр поля Фамилия
    public String familyname;
    @Parameterized.Parameter(3) // Передаем сюда параметр поля Адресс
    public String address;
    @Parameterized.Parameter(4) // Передаем сюда параметр поля Станция
    public String station;
    @Parameterized.Parameter(5) // Передаем сюда параметр поля Телефон
    public String phone;

    /* Параметры для формы "Про аренду" */
    // @Parameterized.Parameter(5) // Передаем сюда параметр поля Дата начала аренды
    // public String date;
    @Parameterized.Parameter(6) // Передаем сюда параметр поля Период аренды
    public String period;
    @Parameterized.Parameter(7) // Передаем сюда параметр чек-бокса Цвет самоката
    public String colour;
    @Parameterized.Parameter(8) // Передаем сюда параметр поля Комментарий
    public String comment;

    @Parameterized.Parameters(name = "{index}: данные для заказа" )
    public static Object[][] userData() {
        return new Object[][] {
                { 0, "ян", "ли","г. Москва, ул. Центральный проезд  Ленина, д. 049", "Спор", "89999999911", "сутки", "серый", "а" },
                { 2, "АБДУРАХМАНГАДЖИ", "КЕИХАНАИКУКАУАКАХИХУЛИХЕЕКАХАУНАЕЛЕ","ДОМОЙ", "рум", "+799966997713", "семеро суток", "черный", "Давай скорей, нету мочи ждать, я хочу кататься, хочу я кайфовать, с ветерком вдоль речки смело прокачусь, ездить не умею, но сегодня научусь" }
        };
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Test
    //Тест на оформление заказа
    public void checkClickOrderButtonUp() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.getBtnsOrderList(ordBtn);
        //заполняем поля страницы "Для кого самокат"
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setCustomerName(name);//Указали имя
        objOrderPage.setCustomerFamilyname(familyname);//Указали фамилию
        objOrderPage.setAddress(address);//Указали адрес
        objOrderPage.clickMetroStationInput();//клик по полю "Станция метро"
        objOrderPage.searchMetroStation(station);//Указали станцию
        objOrderPage.setPhone(phone);//Указали телефон
        objOrderPage.clickBtnNext();//клик по кнопке "Далее" для перехода на форму "Про аренду"
        objOrderPage.isContainerRentalFormVisible();//Проверяем что открылась форма "Про аренду"
        //заполняем поля страницы "Про аренду"
        //OrderPage2 orderPage2 = new OrderPage2(driver);
        objOrderPage.setTodayDateInInput(); // высставляем в качестве даты получения самоката сегодняшнюю дату
        //objOrderPage.setTodayDateInCalendar();
        objOrderPage.setRentalPeriod(period); // указываем период аренды
        objOrderPage.clickSetColour(colour); // указываем цвет
        objOrderPage.setComment(comment); // указываем коммент
        objOrderPage.clickBtnOrder(); //клик по кнопке "Заказать"
        objOrderPage.clickBtnYesConfirmationOrderPopup();// клик по кнопке "Да" в попапе подтверждения заказа
        objOrderPage.isSuccessfulOrderPopupVisible(); // Проверка видимости попапа успешного заказа
    }

    @After
    public void teardown() {
       driver.quit();
    }

}


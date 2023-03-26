import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;

@RunWith(Parameterized.class)//Аннотация говорящая что тест будет параметризированным

public class ForWhomScooterFormTest { //Тестовый клсс (класс в котором будет тест)
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

        @Parameterized.Parameters(name = "{index}: данные для заказа в форме Для кого самокат" )
        public static Object[][] userData() {
            return new Object[][] {
                    { 0, "ян", "ли","г. Москва, ул. Центральный проезд  Ленина, д. 049", "Спор", "89999999911"},
                    { 1, "АБДУРАХМАНГАДЖИ", "КЕИХАНАИКУКАУАКАХИХУЛИХЕЕКАХАУНАЕЛЕ","ДОМОЙ", "рум", "+799966997713"}
            };
        }

    @Before
   public void setup() {
            driver = WebDriverFactory.get();
    }

    @Test
        //Тест на оформление заказа
        public void checkClickOrderButtonUp() throws InterruptedException {
            MainPage ObjMainPage = new MainPage(driver);
            ObjMainPage.clickCookieBtn();
            ObjMainPage.getBtnsOrderList(ordBtn);
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
        }

      @After
      public void teardown() {
       driver.quit();
       }

    }


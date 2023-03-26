package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderPage {

    private final WebDriver driver;

    //поле "Имя"
    private final By customerName = By.xpath(".//*[@placeholder='* Имя']");

    //поле "Фамилия"
    private final By customerFamilyname = By.xpath(".//*[@placeholder='* Фамилия']");

    //поле  "Адрес"
    private final By address = By.xpath(".//*[@placeholder='* Адрес: куда привезти заказ']");

    //список станция метро
    private final By metroStationInput = By.xpath(".//*[@placeholder='* Станция метро']");

    //выпадающий список станций метро
    private final By stationList = By.xpath(".//*[@class='select-search__input']");

    //поле Телефон
    private final By phone = By.xpath(".//*[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка "Далее"
    private final By btnNext = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final By containerRentalForm = By.xpath(".//div[@class='Order_Header__BZXOb']");

    // Поле "Когда привезти самокат"
    private final By deliveryDateInput = By.xpath(".//*[@placeholder='* Когда привезти самокат']");

    //сегодняшняя дата в календаре поля "Когда привезти самокат"
    private final By deliveryDateCalendar = By.xpath(".//div[contains(@class,'react-datepicker__day--today')]");

    //поле "Срок аренды"
    private final By rentalPeriodInput = By.xpath(".//*[@class='Dropdown-arrow']");

    //выпадающий список Срок аренды
    private final By[] periodListItems = new By[]{By.xpath(".//*[@class='Dropdown-option' and text()='сутки']"), By.xpath(".//*[@class='Dropdown-option' and text()='двое суток']"), By.xpath(".//*[@class='Dropdown-option' and text()='трое суток']"), By.xpath(".//*[@class='Dropdown-option' and text()='четверо суток']"), By.xpath(".//*[@class='Dropdown-option' and text()='пятеро суток']"), By.xpath(".//*[@class='Dropdown-option' and text()='шестеро суток']"), By.xpath(".//*[@class='Dropdown-option' and text()='семеро суток']")};

    //чекбокс серый цвет
    private final By checkboxGreyColour = By.xpath(".//*[@id='grey']");
    //чекбокс  черный цвет
    private final By checkboxBlackColour = By.xpath(".//*[@id='black']");

    //поле Комментарий
    private final By comment = By.xpath(".//*[@placeholder='Комментарий для курьера']");

    //кнопка Заказать
    private final By btnOrder = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //кнопка Заказать

    /* элементы попапов подтверждения заказа */
    // Попап подтверждения заказа
    private final By confirmationOrderPopup = By.xpath(".//*[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    // Кнопка "Да" подтверждения заказа
    private final By btnYesConfirmationOrderPopup = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    // Попап успешно оформленного заказа с его данными
    private static final By successOrderPopup = By.xpath(".//*[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    //конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Заполнения поля Имя
    public void setCustomerName(String custName) {
        driver.findElement(customerName).sendKeys(custName);
    }

    //Заполнения поля Фамилия
    public void setCustomerFamilyname(String custFamilyname) {
        driver.findElement(customerFamilyname).sendKeys(custFamilyname);
    }

    //Заполнения поля Адрес
    public void setAddress(String custAddr) {
        driver.findElement(address).sendKeys(custAddr);
    }

    //Клика по полю Станция метро
    public void clickMetroStationInput() {
        driver.findElement(metroStationInput).click();
    }

    //Клика по значению в выпадающем списке станции метро
    public void searchMetroStation(String station) throws InterruptedException {
        driver.findElement(metroStationInput).click();
        Thread.sleep(500);
        driver.findElement(stationList).sendKeys(station, Keys.ARROW_DOWN, Keys.ENTER);
    }

    //Заполнения поля Телефон
    public void setPhone(String custPhone) {
        driver.findElement(phone).sendKeys(custPhone);
    }

    //Клик по кнопке Далее
    public void clickBtnNext() {
        driver.findElement(btnNext).click();
    }

    // Проверка что форма "Про аренду" отображается
    public void isContainerRentalFormVisible() {
        driver.findElement(containerRentalForm).isDisplayed();
    }

    //Метод для указания сегодняшней даты в поле "Когда привезти самокат" текстом
    public void setTodayDateInInput() {
        driver.findElement(deliveryDateInput).click(); //кликаем в поле "Когда привезти самокат
        Date dateNow = new Date(); //Получаем текущую дату
        SimpleDateFormat formatForTodayDate = new SimpleDateFormat("dd.MM.yyyy");// Полечаем сегодняшнюю дату
        String todayDate = formatForTodayDate.format(dateNow);//Передаем значение сегодняшней даты в переменную
        driver.findElement(deliveryDateInput).sendKeys(todayDate); // Вводим сегодняшнюю дату в поле текстом
    }

    //Метод для выбора сегодняшней даты в календаре поля "Когда привезти самокат"
    public void setTodayDateInCalendar() {
        driver.findElement(deliveryDateCalendar).click();
    }

    //Клик по полю Срок аренды
    public void setRentalPeriod(String period) throws InterruptedException {
        this.driver.findElement(this.rentalPeriodInput).click();
       for(int i = 0; i <= 6; ++i) {
            String text = this.driver.findElement(this.periodListItems[i]).getText();
            if (period.equals(text)) {
                this.driver.findElement(this.periodListItems[i]).click();
                break;
            }
        }
    }

    //клик по чекбоксу цвета
    public void clickSetColour(String colour) {
        //ищем нужный цвет
        if (colour.equals("серый")) {
            driver.findElement(checkboxGreyColour).click();
        } else {
            driver.findElement(checkboxBlackColour).click();
        }
    }

    //указываем комментарий
    public void setComment(String custComm) {
        driver.findElement(comment).sendKeys(custComm);
    }

    //клик по кнопке Заказать
    public void clickBtnOrder() {
        driver.findElement(btnOrder).click();
    }
    // Клик по кнопке подтверждения
    public void clickBtnYesConfirmationOrderPopup() {
        if (this.driver.findElement(this.confirmationOrderPopup).isDisplayed()) {
            this.driver.findElement(this.btnYesConfirmationOrderPopup).click();
        }
    }
    // Проверка что попап отображается
    public void isSuccessfulOrderPopupVisible() {
        driver.findElement(successOrderPopup).isDisplayed(); }
}


import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class FaqTest {

    private WebDriver driver;


    @Before
    public void setup() {

        driver = WebDriverFactory.get();
    }
 @Test
public void checkFaqButtonIsOpen() {
     MainPage objMainPage= new MainPage(driver);
     objMainPage.clickCookieBtn();
     objMainPage.clickQuestionButton();
     objMainPage.checkTextFaq();
}

    @After
    public void teardown() {
        driver.quit();
    }
}

package helpDesk;

import core.BaseSeleniumTest;
import helpers.TestValues;
import org.testng.Assert;
import org.testng.annotations.Test;
import readProperties.ConfigProvider;

import static helpers.StringModifier.getUniqueString;

public class HelpDeskTest extends BaseSeleniumTest {

    @Test
    public void checkTicket() {
        String title = getUniqueString(TestValues.TEST_TITLE);

        TicketPage ticketPage = new MainPage().createTicket(title, TestValues.TEST_BODY, TestValues.TEST_EMAIL)
                .openLoginPage()
                .auth(ConfigProvider.ADMIN_LOGIN, ConfigProvider.ADMIN_PASSWORD)
                .findTicket(title);

        // - Checking the ticket title
        Assert.assertTrue(ticketPage.getTitle().contains(title));
        // - Checking the ticket description
        Assert.assertEquals(ticketPage.getBody(), TestValues.TEST_BODY);
        // - Checking ticket email
        Assert.assertEquals(ticketPage.getEmail(), TestValues.TEST_EMAIL);
    }
}

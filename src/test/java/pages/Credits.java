package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Credits {

    // locators
    private SelenideElement
            linkCreditForAnyPurpose = $(".container").$(byText("Кредит на любые цели"));

    public void clickCreditForAnyPurpose() {
        linkCreditForAnyPurpose.click();
    }

}

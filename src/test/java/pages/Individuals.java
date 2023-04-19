package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Individuals {

    // locators
    private SelenideElement
            bannerCredits = $(".b-page-head").$(byText("Кредиты"));

    public void clickCredits() {
        bannerCredits.click();
    }

}

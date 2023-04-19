package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditForAnyPurpose {

    // variables
    private String
            attributeName = "aria-valuenow";

    // locators
    private SelenideElement
            buttonCloseCookiesWindow = $(".cookie-consent__submit-button"),
            buttonAnnuityPayment = $(".menu-switch__item"),
            sliderLoanAmount = $(".ant-slider-handle"),
            loanPeriodWindow = $$("[inputmode='numeric']").get(1),
            preliminaryCalculation = $(".loan-calculator-result__value");

    public void closeCookiesWindow() {
        buttonCloseCookiesWindow.click();
    }

    public void chooseAnnuityPayment() {
        buttonAnnuityPayment.click();
    }

    public void choosingLoanAmount(int loanAmount) {
        String s;   // variable for storing the value of the slider element
        s = sliderLoanAmount.getAttribute(attributeName);
        String s1;  // auxiliary variable for calculation
        s1 = determinePositionSlider(s);
        System.out.println(s1);
        int sm;
        sm = Integer.parseInt(s1);
        while (sm != loanAmount) {
            if (sm < loanAmount) {
                sliderLoanAmount.sendKeys(Keys.ARROW_RIGHT);
                //slider.sendKeys(Keys.ARROW_RIGHT);
                s = sliderLoanAmount.getAttribute(attributeName);
                s1 = determinePositionSlider(s);
                sm = Integer.parseInt(s1);
            } else {
                sliderLoanAmount.sendKeys(Keys.ARROW_LEFT);
                s = sliderLoanAmount.getAttribute(attributeName);
                s1 = determinePositionSlider(s);
                sm = Integer.parseInt(s1);
            }
        }
    }

    public void choosingLoanPeriod(String loanPerion) {
        loanPeriodWindow.clear();
        loanPeriodWindow.sendKeys(loanPerion);
    }

    public void checkValuePreliminaryCalculationField(String preliminaryCalculation) {
        //preliminaryCalculation.shouldHave(text(loanPeriodWindow));
        this.preliminaryCalculation.shouldHave(text(preliminaryCalculation));
    }

    // we will select only numbers from the field by removing formatting characters
    public String determinePositionSlider(String s) {
        String s1 = "";
        char sT;
        int j = 0, a;
        a = s.length();
        while (j < a) {
            sT = s.charAt(j);
            if (sT == '0' || sT == '1' || sT == '2' || sT == '3' || sT == '4' || sT == '5' || sT == '6' || sT == '7' ||
                    sT == '8' || sT == '9') {
                s1 = s1 + sT;
            }
            j++;
        }
        return s1;
    }

}

import org.junit.jupiter.api.*;
import pages.CreditForAnyPurpose;
import pages.Credits;
import pages.HomePage;
import pages.Individuals;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RshbTests extends TestBase {

    HomePage homePage = new HomePage();
    Individuals individuals = new Individuals();
    Credits credits = new Credits();
    CreditForAnyPurpose creditForAnyPurpose = new CreditForAnyPurpose();

    // variables
    private int
            loanAmount = 2010000;

    private String
            loanPeriod = "60",
            preliminaryCalculation = "55 400 ₽";

    @Test
    @DisplayName("Credit calculation test")
    void creditCalculationTest() {

        // data entry
        step("- Зайти на сайт rshb.ru", () -> {
            open(baseUrl);
        });

        step("- Кликнуть «Частным лицам»", () -> {
            homePage.clickIndividuals();
        });

        step("- Кликнуть «Кредиты»", () -> {
            individuals.clickCredits();
        });

        step("- Кликнуть «Кредит на любые цели»", () -> {
            credits.clickCreditForAnyPurpose();
        });

        step("- Закрыть окно cookies", () -> {
            creditForAnyPurpose.closeCookiesWindow();
        });

        step("- тип платежа «Аннуитентный»", () -> {
            creditForAnyPurpose.chooseAnnuityPayment();
        });

        step("- выбор суммы кредита", () -> {
            creditForAnyPurpose.choosingLoanAmount(loanAmount);
        });

        step("- Срок кредита 60 м", () -> {
            creditForAnyPurpose.choosingLoanPeriod(loanPeriod);
        });

        // checking the correctness of the entered value
        step("- проверить что значение поля 'Предварительный расчет' составляет 55 400 ₽", () -> {
            creditForAnyPurpose.checkValuePreliminaryCalculationField(preliminaryCalculation);
        });
    }

    @Test
    @DisplayName("Checking the link to farmers")
    void checkingLinkFarmers() {
        step("- Проверка ссылки «Фермерам»", () -> {
            homePage.clickLinkFarmers();
        });
    }

    @Test
    @DisplayName("Checking the link to small business")
    void checkingLinkSmallBusiness() {
        step("- Проверка ссылки «Малому бизнесу и ИП»", () -> {
            homePage.clickLinkSmallBusiness();
        });
    }

    @Test
    @DisplayName("Checking a false link number one")
    void checkingFalseLink01() {
        step("- Проверка ложной ссылки №1", () -> {
            homePage.clickLinkFail1();
        });
    }

    @Test
    @DisplayName("Checking a false link number two")
    void checkingFalseLink02() {
        step("- Проверка ложной ссылки №1", () -> {
            homePage.clickLinkFail2();
        });
    }

}

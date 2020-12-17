package com.incytes.patient;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA184E {
    @Test
    public void c_test() {
        PatientPA182G pa182g = new PatientPA182G();
        pa182g.InvitationOnEmail("En");
        Main main = new Main("En");
        Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.clickInvitation();
        switchTo().window(2);
        Main.Registration reg = main.new Registration();
        reg.setAll("", Main.password, Main.password, "fdgs", "rvsgs", "2000/01/01", "Belarus", "safafafsa").wRegistration().cRegistration().clickLogin();
        reg.wwRegistration().cwRegistration().clickCheckBox();
        reg.clickGetStarted();
        Main.Registration.Survey survey = reg.new Survey();
        survey.eRadioButtons().first().click();
        survey.eCheckBoxes().last().click();
        survey.clickSubmit();
        Main.Login.Home home = main.new Login().new Home();
        home.eGrid_MyResults().hover();
        home.eAllResults_MyResults().shouldHave(CollectionCondition.size(1)).first().shouldHave(Condition.text("Your results 50"));
        home.eX_MyResults().shouldBe(Condition.visible).lastChild().shouldHave(Condition.text("Time"));
        home.eY_MyResults().shouldBe(Condition.visible).lastChild().shouldHave(Condition.text("Survey Score"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
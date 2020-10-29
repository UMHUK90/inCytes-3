package com.incytes.patient;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA184D {
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
        home.clickCompleteNow_Survey(); //2
        completeSurvey(survey);
        home.clickCompleteNow_Survey(); //3
        completeSurvey(survey);
        home.clickCompleteNow_Survey(); //4
        completeSurvey(survey);
        home.eGrids_MyResults().shouldHave(CollectionCondition.size(4));
        home.clickCompleteNow_Survey(); //5
        completeSurvey(survey);
        home.ePoints_MyResults().shouldHaveSize(5);
        home.eLine_MyResults().shouldBe(Condition.visible);
    }
    public void completeSurvey(Main.Registration.Survey survey){
        survey.eRadioButtons().first().click();
        survey.eCheckBoxes().last().click();
        survey.clickSubmit();
    }
}
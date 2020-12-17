package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA187B_Divergence {
    @Test
    public void c_test() {
        PatientPA182G pa182g = new PatientPA182G();
        pa182g.InvitationOnEmail("Ru");
        Main main = new Main("Ru");
        Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.clickInvitation();
        switchTo().window(2);
        Main.Registration reg = main.new Registration();
        reg.setAll("", Main.password, Main.password, "Максим", "Максимыч", "1995/02/17", "Belarus", "123413213123").wRegistration().cRegistration().clickLogin();
        reg.eTextOfCheckBox1().shouldHave(Condition.text("Политика конфиденциальности"));
        reg.eTextOfCheckBox2().shouldHave(Condition.text("Политика конфиденциальности медицинской организации"));
        reg.wwRegistration().clickCheckBox();
        reg.clickGetStarted();
        Main.Registration.Survey survey = reg.new Survey();
        survey.eRadioButtons().first().click();
        survey.eCheckBoxes().last().click();
        survey.clickSubmit();
        Main.Login.Home home = main.new Login().new Home();
        home.ePatientName().shouldHave(Condition.text("Максим"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
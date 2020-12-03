package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA184G_Divergence_Unfinished {
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
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
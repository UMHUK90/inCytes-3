package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA187H {
    @Test
    public void c_test() {
        PatientPA182G pa182g = new PatientPA182G();
        pa182g.InvitationOnEmail("De");
        Main main = new Main("De");
        Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.toInvitation();
        String text = "Sie wurden eingeladen, auf Ihr personalisiertes Patientenportal zuzugreifen,";
        if(invitation.eLastCheckBox().is(Condition.exist))invitation.eLastText().shouldHave(Condition.text(text));
        else invitation.eFullMessage().$$("p").first().shouldHave(Condition.text(text));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
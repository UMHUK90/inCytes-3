package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA187K_Fail {
    @Test
    public void c_test() {
        PatientPA182G pa182g = new PatientPA182G();
        pa182g.InvitationOnEmail("Sp");
        Main main = new Main("Sp");
        Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.toInvitation();
        back();
        String text = "Вам было предложено получить ";
        if(invitation.eLastCheckBox().is(Condition.exist))invitation.eLastText().shouldHave(Condition.text(text));
        else invitation.eFullMessage().$$("p").first().shouldHave(Condition.text(text));
        //Problems:
        //1: The message is in the Italian language, but title is in Spanish
    }
}
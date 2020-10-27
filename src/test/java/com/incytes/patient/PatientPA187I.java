package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA187I {
    @Test
    public void c_test() {
        PatientPA182G pa182g = new PatientPA182G();
        pa182g.InvitationOnEmail("It");
        Main main = new Main("It");
        Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.toInvitation();
        String text = "Sei stato invitato ad accedere il portale personalizzato del paziente, in cui sia ";
        if(invitation.eLastCheckBox().is(Condition.exist))invitation.eLastText().shouldHave(Condition.text(text));
        else invitation.eFullMessage().$$("p").first().shouldHave(Condition.text(text));
    }
}
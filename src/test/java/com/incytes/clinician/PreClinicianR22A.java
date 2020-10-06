package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class PreClinicianR22A {
    @Test
    public void c_test() {
            org.selenide.examples.Clinician.Main main = new org.selenide.examples.Clinician.Main("En");
            org.selenide.examples.Clinician.Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
            int count = Integer.parseInt(file.getText());
            org.selenide.examples.Clinician.Main.Login login = main.new Login();
            login.open();
            login.setAll("qwertyuiop17091709+1@yandex.by", "261090inCytes!").wLogin().signIn();
            org.selenide.examples.Clinician.Main.Login.DashBoard dashBoard = login.new DashBoard();
            dashBoard.clickProfile();
            org.selenide.examples.Clinician.Main.Login.DashBoard.Profile profile = dashBoard.new Profile();
            profile.clickInvite();
            profile.writeEmail("qwertyuiop17091709+" + count + "@yandex.by");
            profile.checkingInvitationForm().clickSendInvitation().isInvitationSent();
            org.selenide.examples.Clinician.Main.newTab();
            switchTo().window(1);
            org.selenide.examples.Clinician.Main.GetInvitationWithYandex yandex = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
            yandex.clickInvitation();
            switchTo().window(2);
            org.selenide.examples.Clinician.Main.Registration reg = main.new Registration();
            reg.setAll("Anton", "Sharifov", "", "261090inCytes!", "261090inCytes!").wRegistration().cRegistration();
            reg.clickNext();
            file.writeText(String.valueOf(count + 1), false);
            profile.eI_Invite().shouldBe(visible);
    }
}

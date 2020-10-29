package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianR22A {
    @Test
    public void c_test() {
            Main main = new Main("En");
            Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
            int count = Integer.parseInt(file.getText());
            Main.Login login = main.new Login();
            login.open();
            login.setAll("andrew.grabovskiy@gmail.com", "261090inCytes").wLogin().signIn();
            Main.Login.DashBoard dashBoard = login.new DashBoard();
            dashBoard.clickProfile();
            Main.Login.DashBoard.Profile profile = dashBoard.new Profile();
            profile.clickInvite();
            profile.writeEmail("qwertyuiop17091709+" + count + "@yandex.by");
            profile.checkingInvitationForm().clickSendInvitation().isInvitationSent();
            Main.newTab();
            switchTo().window(1);
            Main.GetInvitationWithYandex yandex = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
            yandex.clickInvitation();
            switchTo().window(2);
            Main.Registration reg = main.new Registration();
            reg.setAll("Anton", "Sharifov", "", "261090inCytes!", "261090inCytes!").wRegistration().cRegistration();
            reg.clickNext();
            file.writeText(String.valueOf(count + 1), false);
            profile.eI_Invite().shouldBe(visible);
            close();
    }
}
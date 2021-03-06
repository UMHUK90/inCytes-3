package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianR23A {
    @Test
    public void c_test() {
            Main main = new Main("En");
            Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
            int count = Integer.parseInt(file.getText());
            Main.Login login = main.new Login();
            login.open();
            login.setAll("andrew.grabovskiy@gmail.com", "261090inCytes").wLogin().signIn();
            Main.Login.DashBoard dashBoard = login.new DashBoard();
            dashBoard.clickCircles();
            Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
            circles.clickLastCircle();
            Main.Login.DashBoard.Circles.Circle circle = circles.new Circle();
            circle.clickInvite();
            circle.writeEmail_Invite("qwertyuiop17091709+" + count + "@yandex.by");
            circle.clickSendInvitation_Invite();
            circle.notHaveErrors();
            circle.haveCircleMember("qwertyuiop17091709+" + count + "@yandex.by");
            Main.newTab();
            switchTo().window(1);
            Main.GetInvitationWithYandex yandex = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
            yandex.clickInvitation();
            switchTo().window(2);
            Main.Registration reg = main.new Registration();
            reg.setAll("NewName", "NewLastName", "", "261090inCytes!", "261090inCytes!").wRegistration().cRegistration().clickNext();
            file.writeText(String.valueOf(count+1), false);
            dashBoard.isThisLanguage("En");
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
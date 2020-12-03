package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;

public class ClinicianCS094W_Fail {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.clickAddCase();
        Main.Login.DashBoard.Cases.NewCase newCase = cases.new NewCase();
        newCase.clickUseExistingPatient();
        newCase.setAll("qwertyuiop17091709+243@yandex.by", "", "", "", "", "", "Common Protocol").writeAll().checkAll();
        newCase.clickCreateCase();
        newCase.eForm().shouldNotBe(Condition.visible);
        newCase.eSharedWithCircles().shouldNotBe(Condition.visible);
        dashBoard.clickCases();
        cases.eItems().first().shouldHave(Condition.text("qwertyuiop17091709+243@yandex.by"));
        Main.GetInvitationWithYandex yandex = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        yandex.toInvitation();
        Selenide.back();
        Date date = new Date();
        String time2 = yandex.time.replace(":", "").length() == 4 ? yandex.time.substring(3) : yandex.time.substring(1);
        if(date.getHours() != Integer.parseInt(yandex.time.replace(":", "").substring(0, 1)) && date.getMinutes()+1 < Integer.parseInt(time2)) throw new ElementNotVisibleException("Hours = " + date.getHours() +" - " + yandex.time.replace(":", "").substring(0, 1) + " " + "Minutes = " + String.valueOf(date.getMinutes()-1) +" - " + time2);
        //The invitation didn't come on e-mail
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;

public class ClinicianCS094N {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.clickAddCase();
        Main.Login.DashBoard.Cases.NewCase newCase = cases.new NewCase();
        newCase.setAll("qwertyuiop17091709+" + count + "@yandex.by", "2000/11/05", "", "", "", "", "Common protocol").writeAll().checkAll();
        newCase.clickCreateCase();
        file.writeText(String.valueOf(count+1), false);
        newCase.eSharedWithCircles().shouldNotBe(Condition.visible);
        dashBoard.clickCases();
        cases.eItems().first().shouldHave(Condition.text("qwertyuiop17091709+" + count + "@yandex.by"));
        dashBoard.clickPatients();
        Main.Login.DashBoard.Patients patients = dashBoard.new Patients();
        patients.eListOfPatients().first().shouldHave(Condition.text("qwertyuiop17091709+" + count + "@yandex.by"));
        Main.GetInvitationWithYandex yandex = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        yandex.toInvitation();
        Selenide.back();
        Date date = new Date();
        String time2 = yandex.time.replace(":", "").length() == 4 ? yandex.time.substring(3) : yandex.time.substring(1);
        if(date.getHours() != Integer.parseInt(yandex.time.replace(":", "").substring(0, 1)) && date.getMinutes()+1 < Integer.parseInt(time2)) throw new ElementNotVisibleException("Hours = " + date.getHours() +" - " + yandex.time.replace(":", "").substring(0, 1) + " " + "Minutes = " + String.valueOf(date.getMinutes()-1) +" - " + time2);
        //if(date.getHours() != Integer.parseInt(yandex.time.substring(0, 2)) && date.getMinutes()+1 < Integer.parseInt(yandex.time.substring(3))) throw new ElementNotVisibleException("Hours = " + date.getHours() +" - " + yandex.time.substring(0, 2) + " " + "Minutes = " + String.valueOf(date.getMinutes()-1) +" - " + yandex.time.substring(3));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

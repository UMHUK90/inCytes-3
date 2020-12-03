package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianCS094O {
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
        newCase.setAll("qwertyuiop17091709+243@yandex.by", "", "", "", "", "", "missing readonly-content").writeAll().checkAll();
        newCase.clickCreateCase();
        $(byText("No available cases for this Circle. Visit the Circle details page to add more cases.")).shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

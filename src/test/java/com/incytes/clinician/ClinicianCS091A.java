package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianCS091A {
    @Test
    public void method(){
        Configuration.timeout = 5000;
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("qwertyuiop17091709+543@yandex.by", Main.password + "!").wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.eSearch().shouldBe(Condition.visible);
        cases.eAddCase().shouldBe(Condition.visible);
        $(byText("CASE")).shouldBe(Condition.visible);
        $(byText("DATE OF BIRTH")).shouldBe(Condition.visible);
        $(byText("TREATMENT DATE")).shouldBe(Condition.visible);
        $(byText("UPDATED")).shouldBe(Condition.visible);
        $(byText("PROGRESS")).shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

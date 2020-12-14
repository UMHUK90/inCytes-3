package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRO310A {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickReports();
        Main.Login.DashBoard.Reports reports = dashBoard.new Reports();
        reports.eTitle().shouldHave(Condition.text("Reports Overview")).shouldBe(Condition.visible);
        reports.eBuildReport().shouldBe(Condition.visible);
        reports.clickMoreOptions(reports.eListOfSavedReports().first().shouldBe(Condition.visible));
        reports.eRemoveReport().shouldBe(Condition.visible);
        reports.eNameReport().shouldBe(Condition.visible);
        reports.eName_ChartSection().shouldBe(Condition.visible);
        reports.eEditReport().shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
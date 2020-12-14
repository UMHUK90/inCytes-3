package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRO329A {
    @Test
    public void method(){
        String text = Main.randomText(5);
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickReports();
        Main.Login.DashBoard.Reports reports = dashBoard.new Reports();
        reports.clickEditReport();
        Main.Login.DashBoard.Reports.ReportBuilder reportBuilder = reports.new ReportBuilder();
        reportBuilder.eGraph().$("circle", 4).shouldBe(Condition.visible);
        Main.newTab();
        Selenide.switchTo().window(1);
        Selenide.open("https://qa.incytesdata-dev.com/report/23/456");
        Main.eBottomMessage().shouldHave(Condition.text("Failed to load report. Please try again"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

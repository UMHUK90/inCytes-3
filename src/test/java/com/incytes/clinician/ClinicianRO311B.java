package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianRO311B {
    @Test
    public void method(){
        String text = "fsdfgs";
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickReports();
        Main.Login.DashBoard.Reports reports = dashBoard.new Reports();
        reports.clickBuildReport();
        reports.writeNameReport(text);
        reports.eReportName_Build().shouldHave(Condition.value(text));
        reports.clickXClose_Build();
        reports.eReportName_Build().shouldNotBe(Condition.visible);
        $(byText(text)).shouldNot(Condition.exist);
        $(byValue(text)).shouldNot(Condition.exist);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
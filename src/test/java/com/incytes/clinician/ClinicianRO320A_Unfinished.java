package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class ClinicianRO320A_Unfinished {
    @Test
    public void method(){
        String text = Main.randomText(5);
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickReports();
        Main.Login.DashBoard.Reports reports = dashBoard.new Reports();
        reports.clickBuildReport();
        reports.writeNameReport(text);
        reports.eReportName_Build().shouldHave(Condition.value(text));
        reports.clickCreateReport_Build();
        reports.eReportName_Build().shouldNotBe(Condition.visible);
        Main.Login.DashBoard.Reports.ReportBuilder reportBuilder = reports.new ReportBuilder();
        reportBuilder.eTitle().shouldHave(text("Report Builder"));
        reportBuilder.eBackToReports().shouldBe(Condition.visible);
        //Button "Save Report" doesn't exist
        reportBuilder.eTitle().shouldBe(Condition.visible);
        reportBuilder.clickCirclesButton();
        reportBuilder.selectCircle_Circle("Common Protocol");
        reportBuilder.clickUpdateReport_Circle();
        //Population doesn't exist
        //ListOfCohorts doesn't exist in new Report
        reportBuilder.eAddCohortButton().shouldBe(Condition.visible);
        reportBuilder.eCasesCount().shouldHave(text("0"));
        reportBuilder.eDataPointsCount().shouldHave(text("0"));
        //ListOfCircles doesn't exist
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
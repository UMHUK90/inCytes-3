package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRO321F {
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
        reportBuilder.clickCirclesButton();
        reportBuilder.eInputCircle_Circle().click();
        int size = reportBuilder.eListOfCircles().size();
        reportBuilder.eListOfCircles().first().click();
        reportBuilder.clickXcloseButton_Circle();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        if(circles.eListOfCircles().size() != size) throw new ElementNotVisibleException(size + " - " + circles.eListOfCircles().size());
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
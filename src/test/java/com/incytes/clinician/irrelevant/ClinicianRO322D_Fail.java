package com.incytes.clinician.irrelevant;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.clinician.Main;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class ClinicianRO322D_Fail {
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
        Main.Login.DashBoard.Reports.ReportBuilder reportBuilder = reports.new ReportBuilder();
        reportBuilder.clickCirclesButton();
        reportBuilder.selectCircle_Circle("Common Protocol");
        reportBuilder.clickUpdateReport_Circle();
        reportBuilder.clickYAxisButton();
        reportBuilder.clickSubmitButton_YAxis();
        Main.eBottomMessage().shouldHave(text("Select scoring group from the list and try again"));
        //"Select scoring group from the list and try again" text doesn't appears
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
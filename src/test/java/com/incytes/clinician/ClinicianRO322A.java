package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianRO322A {
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
        reportBuilder.eXCloseButton_YAxis().shouldBe(Condition.visible);
        reportBuilder.eTitle_YAxis().shouldHave(Condition.text("Y Axis"));
        $(byText("Some Options:")).shouldBe(Condition.visible);
        $(byText("Prom scores (KOOS, VAS, etc)")).shouldBe(Condition.visible);
        reportBuilder.eSubmit_YAxis().shouldBe(Condition.visible);
        reportBuilder.eInputScoringGroup_YAxis().shouldBe(Condition.visible);
    }
}

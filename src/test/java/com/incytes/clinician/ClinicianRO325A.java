package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;

public class ClinicianRO325A {
    @Test
    public void method(){
        String text = Main.randomText(5);
        Main main = new Main("Fr");
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
        reportBuilder.selectScoringGroup_YAxis("Common");
        reportBuilder.clickSubmitButton_YAxis();
        reportBuilder.clickAddCohortButton();
        reportBuilder.selectCohort_Cohort("À quelle fréquence toussez-vous?");
        reportBuilder.eCheckBoxes_Cohort().last().click();
        reportBuilder.clickAddCohortFilter_Cohort();
        reportBuilder.clickDone_Cohort();
        reportBuilder.eGraph().$("svg").$("g").$("circle", 4).shouldBe(Condition.visible);
        reportBuilder.eGraph().$("svg").$("g").$("circle", 4).hover();
        $$(byText("Cohort B")).find(Condition.visible).shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianRO327D {
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
        reportBuilder.selectCohort_Cohort("Sliding Scale");
        reportBuilder.selectListAnswer_Cohort(0, 0);
        reportBuilder.eInputsOfQuestions().get(0).setValue("123");
        reportBuilder.eButtonsOfList_Cohort().get(1).click();
        $(byText("Moins que")).shouldBe(Condition.visible);
        $(byText("Plus grand que")).shouldBe(Condition.visible);
        $(byText("Égal")).shouldBe(Condition.visible);
        Main.clickOutSide(dashBoard.eProfile(), -50, -50);
        reportBuilder.clickAddCohortFilter_Cohort();
        reportBuilder.clickDone_Cohort();
        if(reportBuilder.eListOfCohorts().length() != 3) throw new ElementNotVisibleException(String.valueOf(reportBuilder.eListOfCohorts().length()));
    }
}

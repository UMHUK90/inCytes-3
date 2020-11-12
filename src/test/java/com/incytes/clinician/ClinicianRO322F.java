package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.text;

public class ClinicianRO322F {
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
        reportBuilder.eInputScoringGroup_YAxis().click();
        List<SelenideElement> list = reportBuilder.eListOfScoringGroups();
        Main.clickOutSide(dashBoard.eProfile(), -50, -50);
        reportBuilder.clickXcloseButton_YAxis();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        circles.clickOnCircle(circles.eListOfCircles().find(text("Common Protocol")));
        Main.Login.DashBoard.Circles.Circle circle = circles.new Circle();
        circle.toListOfScoringGroups();
        boolean isExist = false;
        for(SelenideElement element : list) {
            for (SelenideElement element1 : circle.eListOfScoringGroups()) {
                if(element1.has(text(element.getText()))) isExist = true;
            }
            if(!isExist || list.size() != circle.eListOfScoringGroups().size()) throw new ElementNotVisibleException("Element not exist");
        }
    }
}

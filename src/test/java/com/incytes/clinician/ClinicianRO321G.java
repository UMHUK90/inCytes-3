package com.incytes.clinician;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ClinicianRO321G {
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
        String[] list = new String[reportBuilder.eListOfCircles().size()];
        for (int step = 0; step < reportBuilder.eListOfCircles().size(); step++) { list[step] = reportBuilder.eListOfCircles().get(step).getText();}
        reportBuilder.eListOfCircles().first().click();
        reportBuilder.clickUpdateReport_Circle();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        String textOfCircle = circles.eListOfCircles().first().waitUntil(visible, 5000).getText();
        circles.clickOnCircle(circles.eListOfCircles().first());
        Main.Login.DashBoard.Circles.Circle circle = circles.new Circle();
        circle.clickEditCircle();
        circle.clickArchive_EditCircle();
        circle.clickSaveButton_EditCircle();
        dashBoard.clickReports();
        reports.selectReport(reports.eListOfSavedReports().findBy(text(text)));
        //Report graph can't appeared when Yaxis is not selected
        reports.clickEditReport();
        reportBuilder.clickCirclesButton();
        reportBuilder.eInputCircle_Circle().click();
        reportBuilder.eListOfCircles().shouldHave(CollectionCondition.size(list.length-1));
        Main.clickOutSide(reportBuilder.eXCloseButton_Circle(), 0, 0);
        reportBuilder.clickXcloseButton_Circle();
        dashBoard.clickCircles();
        circles.selectTypeOfCircles(5);
        circles.clickOnCircle(circles.eListOfCircles().findBy(text(textOfCircle)));
        circle.clickEditCircle();
        circle.clickArchive_EditCircle();
        circle.clickSaveButton_EditCircle();
        dashBoard.clickReports();
        reports.selectReport(reports.eListOfSavedReports().findBy(text(text)));
        reports.clickEditReport();
        reportBuilder.clickCirclesButton();
        reportBuilder.eInputCircle_Circle().click();
        reportBuilder.eListOfCircles().shouldHave(CollectionCondition.size(list.length));
    }
}

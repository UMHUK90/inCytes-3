package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRO322G {
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
        char[] mass = { 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int number = 0;
        for (SelenideElement element:reportBuilder.eListOfScoringGroups()){
            for(int i = 0; i < mass.length; i++) if(element.getText().toCharArray()[0] == mass[i]) if(i < number) throw new ElementNotVisibleException("Error with alphabet"); else number = i;
        }
        }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
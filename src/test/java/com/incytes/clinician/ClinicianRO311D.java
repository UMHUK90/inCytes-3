package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRO311D {
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
        reports.writeNameReport(Main.randomText(300));
        if(reports.eReportName_Build().getValue().length() != 255) throw new ElementNotVisibleException(String.valueOf(reports.eReportName_Build().getValue().length()));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
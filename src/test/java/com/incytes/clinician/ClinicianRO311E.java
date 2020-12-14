package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRO311E {
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
        reports.clickCreateReport_Build();
        Main.haveRequired(1);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
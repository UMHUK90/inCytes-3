package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

public class ClinicianRO311C {
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
        Main.clickOutSide(dashBoard.eProfile(), -50, -50);
        Selenide.sleep(500);
        reports.eReportName_Build().shouldBe(Condition.visible);
    }
}
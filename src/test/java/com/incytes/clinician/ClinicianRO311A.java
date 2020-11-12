package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class ClinicianRO311A {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickReports();
        Main.Login.DashBoard.Reports reports = dashBoard.new Reports();
        reports.clickBuildReport();
        reports.eXClose_Build().shouldBe(Condition.visible);
        reports.eTitle_Build().shouldBe(Condition.visible);
        reports.eReportName_Build().shouldBe(Condition.visible);
        reports.eCreateReport_Build().shouldBe(Condition.visible);
    }
}

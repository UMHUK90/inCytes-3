package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class ClinicianCSD160A {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.toCaseDetails(cases.eItems().first());
        Main.Login.DashBoard.Cases.CaseDetail caseDetail = cases.new CaseDetail();
        caseDetail.eTitle().shouldHave(Condition.text("Case Detail")).shouldBe(Condition.visible);
    }
}

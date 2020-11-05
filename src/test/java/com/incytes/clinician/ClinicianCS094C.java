package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class ClinicianCS094C {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.clickAddCase();
        Main.Login.DashBoard.Cases.NewCase newCase = cases.new NewCase();
        newCase.clickUseExistingPatient();
        newCase.ePatientIdentity().shouldBe(Condition.visible);
        newCase.eSponsoredBy().shouldBe(Condition.visible);
        newCase.eSharedWithCircles().shouldBe(Condition.visible);
        newCase.setAll("qwertyuiop17091709+243@yandex.by", "", "", "", "", "", "").writeAll();
    }
}

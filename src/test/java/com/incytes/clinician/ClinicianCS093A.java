package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class ClinicianCS093A {
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
        newCase.eUseExistingPatient().shouldBe(Condition.visible);
        newCase.eEmail().shouldBe(Condition.visible);
        newCase.eDateOfBirth().shouldBe(Condition.visible);
        newCase.clickShowOptionalFields();
        newCase.eFirstName().waitUntil(Condition.visible, 10000);
        newCase.eLastName().shouldBe(Condition.visible);
        newCase.eCountry().shouldBe(Condition.visible);
        newCase.ePhone().shouldBe(Condition.visible);
        newCase.eSponsoredBy().shouldBe(Condition.visible);
        newCase.eSharedWithCircles().shouldBe(Condition.visible);
    }
}

package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianCS094D {
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
        newCase.ePatientIdentity().setValue("qwertuiop-234325@yandex.ry");
        $(byText("No Entries Found")).shouldBe(Condition.visible);
    }
}

package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

public class ClinicianCS094L_Fail_Unfinished {
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
        newCase.eDateOfBirth().setValue("2077/10/10");
        Selenide.sleep(5000000);
    }
}

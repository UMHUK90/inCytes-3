package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianCS095C {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.clickMoreOptions(cases.eItems().first());
        cases.eClose().shouldBe(Condition.visible);
        cases.eEditCase().shouldBe(Condition.visible);
        Main.clickOutSide(dashBoard.eProfile(), -50, -50);
        cases.eClose().shouldNotBe(Condition.visible);
        cases.eEditCase().shouldNotBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

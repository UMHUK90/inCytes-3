package com.incytes.clinician.irrelevant;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.incytes.clinician.Main;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianCS092A_Fail {
    @Test
    public void method(){
        Configuration.timeout = 5000;
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("qwertyuiop17091709+543@yandex.by", Main.password + "!").wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.eSearch().setValue(Main.randomText(7000));
        if(cases.eSearch().getValue().toCharArray().length != 255) throw new ElementNotVisibleException(String.valueOf(cases.eSearch().getValue().toCharArray().length));
        // The search field is without limitation
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

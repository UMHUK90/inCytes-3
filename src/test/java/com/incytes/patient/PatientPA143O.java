package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;

public class PatientPA143O {
    @Test
    public void c_test() {
        PatientPA143M_Fail pa143m = new PatientPA143M_Fail();
        Main main = new Main();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutClosing(pa143m::c_test);
        Main.Login login = main.new Login();
        Main.Login.Home home = login.new Home();
        Main.clickOutSide(home.eChangePassword(), -50, -50);
        home.ePhone_Information().shouldNotBe(visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
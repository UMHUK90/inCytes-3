package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianR11A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("Андрей","","","","").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
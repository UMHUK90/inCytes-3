package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA180B_Fail {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.email = "andrew";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
        reg.email = "and" + ' ' + "rew";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
        reg.email = "@@";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
        reg.email = "Русский";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA188C {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.checkForgotPasswordForm();
        login.clickSubmit_forgotPassword();
        Main.haveRequired(1);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
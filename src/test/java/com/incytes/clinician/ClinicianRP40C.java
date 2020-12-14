package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP40C {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
        login.clickSignUp_forgotPassword();
        Main.Registration reg = main.new Registration();
        reg.wRegistration();
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

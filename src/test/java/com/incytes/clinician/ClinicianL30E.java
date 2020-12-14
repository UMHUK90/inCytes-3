package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianL30E {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.wLogin();
        login.forgotPassword();
        login.backToLoginShouldBe_forgotPassword();
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

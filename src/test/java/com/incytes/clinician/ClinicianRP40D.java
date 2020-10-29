package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP40D {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
        login.clickSubmit_forgotPassword();
        Main.haveRequired(1);
        login.haveEmailError_forgotPassword();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

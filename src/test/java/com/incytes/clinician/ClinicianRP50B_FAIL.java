package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP50B_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword("qwertyuiop17091709+345@yandex.by");
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        //Error with BUTTON - 19
        login.eConfirmPassword_resetPassword().waitUntil(Condition.exist,5000);
        login.clickReset_resetPassword();
        Main.haveRequired(3);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

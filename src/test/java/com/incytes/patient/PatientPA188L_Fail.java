package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA188L_Fail {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.checkForgotPasswordForm();
        login.writeEmail_forgotPassword("qwertyuiop17091709+2@yandex.by");
        login.clickSubmit_forgotPassword();
        Main.eBottomMessage().shouldHave(Condition.text("A verification code has been successfully sent to your email"));
        login.ePassword().setValue(Main.randomText(5) + "7 A");
        login.clickReset_resetPassword();
        Main.muiError(2, 0).shouldHave(Condition.text("Spaces are not allowed"));
        //2 - Error in Buttons with all tests in Code_resetPassword
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
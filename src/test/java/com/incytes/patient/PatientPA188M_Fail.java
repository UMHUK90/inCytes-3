package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA188M_Fail {
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
        login.writePassword_resetPassword(Main.password);
        login.writeConfirmPassword_resetPassword(Main.randomText(6) + "A6");
        login.clickReset_resetPassword();
        Main.muiError(1, 0).shouldHave(Condition.text("Passwords must match"));
        //2 - Error in Buttons with all tests in Code_resetPassword
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
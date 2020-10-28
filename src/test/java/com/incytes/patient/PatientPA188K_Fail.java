package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class PatientPA188K_Fail {
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
        login.ePassword().setValue(Main.randomText(5) + "7A");
        login.clickReset_resetPassword();
        Main.muiError(2, 0).shouldHave(Condition.text("8 character minimum. Must contain at least 1 upper case, 1 lower case, 1 number."));
        //2 - Error in Buttons with all tests in Code_resetPassword
    }
}
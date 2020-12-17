package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA188D {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.checkForgotPasswordForm();
        String text = Main.randomText(254);
        login.writeEmail_forgotPassword(text);
        login.clickSubmit_forgotPassword();
        login.eEmail().shouldHave(Condition.value(text));
        Main.eBottomMessage().shouldHave(Condition.text("Unable to send a confirmation code to the email address provided"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class RP41C {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword("qwertyuiop17091709+5@yandex.by");
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        login.eMessage_forgotPassword().shouldHave(Condition.text("That email address is not registered in our system, please try again."));
    }
}

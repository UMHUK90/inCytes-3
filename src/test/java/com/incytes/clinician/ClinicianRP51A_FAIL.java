package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP51A_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        String email = "qwertyuiop17091709+348@yandex.by";
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword(email);
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        login.eConfirmPassword_resetPassword().waitUntil(Condition.visible, 5000);
        login.writePassword_resetPassword(Main.randomText(97) + "7H").clickReset_resetPassword();
        if(login.ePassword().getValue().toCharArray().length != 99) throw new ElementNotVisibleException("Error in password - " + login.ePassword().getValue().toCharArray().length);
        Main.muiError(2);
        //Error with BUTTON - 17
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

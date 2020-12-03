package com.incytes.clinician.irrelevant;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.clinician.Main;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP52A_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        String email = "qwertyuiop17091709+349@yandex.by";
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword(email);
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        login.eConfirmPassword_resetPassword().waitUntil(Condition.visible, 5000);
        login.writePassword_resetPassword(Main.randomText(5) + "7 H");
        login.writeConfirmPassword_resetPassword(Main.randomText(5) + "7 H").clickReset_resetPassword();
        if(login.ePassword().getValue().toCharArray().length != 8) throw new ElementNotVisibleException("Error in password - " + login.ePassword().getValue().toCharArray().length);
        if(login.eConfirmPassword_resetPassword().getValue().toCharArray().length != 8) throw new ElementNotVisibleException("Error in password - " + login.eConfirmPassword_resetPassword().getValue().toCharArray().length);
        Main.muiError(3, 2).shouldHave(Condition.text("Passwords must match"));
        //Error with BUTTON - 17
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

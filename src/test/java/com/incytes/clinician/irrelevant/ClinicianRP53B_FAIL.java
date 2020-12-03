package com.incytes.clinician.irrelevant;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.clinician.Main;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP53B_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        String email = "qwertyuiop17091709+350@yandex.by";
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword(email);
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        login.writeAccessCode_resetPassword(Main.randomText(16));
        login.writePassword_resetPassword(Main.password);
        login.writeConfirmPassword_resetPassword(Main.password).clickReset_resetPassword();
        if(login.eAccessCode_resetPassword().getValue().toCharArray().length != 16) throw new ElementNotVisibleException("Error in password - " + login.eAccessCode_resetPassword().getValue().toCharArray().length);
        Main.muiError(1,0).shouldHave(Condition.text("Invalid verification code"));
        //Error with BUTTON - 17

        //1 - Ввёл неверный код, но верные пароли, послало на страницу логина.
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

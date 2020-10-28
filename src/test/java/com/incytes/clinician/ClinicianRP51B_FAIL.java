package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.Test;

public class ClinicianRP51B_FAIL {
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
        login.writePassword_resetPassword(Main.randomText(5) + "7H").clickReset_resetPassword();
        if(login.ePassword().getValue().toCharArray().length != 7) throw new ElementNotVisibleException("Error in password - " + login.ePassword().getValue().toCharArray().length);
        Main.muiError(3, 1).shouldHave(Condition.text("8 character minimum. Must contain at least 1 upper case, 1 lower case, 1 number"));
        //Error with BUTTON - 17
    }
}

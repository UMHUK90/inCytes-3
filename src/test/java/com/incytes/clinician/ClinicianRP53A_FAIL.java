package com.incytes.clinician;

import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.Test;

public class ClinicianRP53A_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        String email = "qwertyuiop17091709+349@yandex.by";
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword(email);
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        login.writeAccessCode_resetPassword(Main.randomText(16));
        if(login.eAccessCode_resetPassword().getValue().toCharArray().length != 16) throw new ElementNotVisibleException("Error in password - " + login.eAccessCode_resetPassword().getValue().toCharArray().length);
        //Error with BUTTON - 17
    }
}

package com.incytes.clinician;

import org.testng.annotations.Test;

public class RP40D {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
        login.clickSubmit_forgotPassword();
        Main.haveRequired(1);
        login.haveEmailError_forgotPassword();
    }
}

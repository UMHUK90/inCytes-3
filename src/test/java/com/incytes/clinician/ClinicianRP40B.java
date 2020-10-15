package com.incytes.clinician;

import org.testng.annotations.Test;

public class ClinicianRP40B {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
        login.clickBackToLogin_forgotPassword();
        login.wLogin();
    }
}

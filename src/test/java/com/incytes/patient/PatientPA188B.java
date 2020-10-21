package com.incytes.patient;

import org.testng.annotations.Test;

public class PatientPA188B {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.checkForgotPasswordForm();
        login.clickBackToLogin_forgotPassword();
        login.isVisible();
    }
}
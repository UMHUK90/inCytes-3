package com.incytes.patient;

import clinician.*;
import org.testng.annotations.Test;

public class PatientPA188A {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.checkForgotPasswordForm();
    }
}
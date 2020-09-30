package com.incytes.clinician;

import org.testng.annotations.Test;

public class ClinicianL30A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.isVisible();
        login.signIn();
    }
}

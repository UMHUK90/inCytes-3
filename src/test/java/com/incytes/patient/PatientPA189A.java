package com.incytes.patient;

import org.testng.annotations.Test;

public class PatientPA189A {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.signIn();
        Main.haveRequired(2);
    }
}
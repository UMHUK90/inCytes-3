package com.incytes.patient;

import org.testng.annotations.Test;

public class PatientPA143B {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.clickSave_changePassword();
        Main.haveRequired(3);
    }
}
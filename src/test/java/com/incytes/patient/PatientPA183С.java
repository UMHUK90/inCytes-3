package com.incytes.patient;

import org.testng.annotations.Test;

public class PatientPA183С {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.checkHome();
    }
}
package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ClinicianR14A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("","","","261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090in","").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
        close();
    }
}
package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.close;

public class ClinicianR11B {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("éèêëçñøðåæœēčŭ","","","","").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
        close();
    }
}
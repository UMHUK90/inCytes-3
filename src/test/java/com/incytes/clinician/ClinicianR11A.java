package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ClinicianR11A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("Андрей","","","","").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
    }
}

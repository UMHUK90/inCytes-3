package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ClinicianRP40A {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
    }
}

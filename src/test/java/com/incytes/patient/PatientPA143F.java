package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;


public class PatientPA143F {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        Main.Login.Portal portal = login.new Portal();
        portal.clickChangePassword();
        portal.setPassword("261090inCytes", "261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090!", "261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090!").wPassword();
        $(name("newPassword")).shouldHave(value("261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090!"));
        $(name("confirmPassword")).shouldHave(value("261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090!"));
    }
}

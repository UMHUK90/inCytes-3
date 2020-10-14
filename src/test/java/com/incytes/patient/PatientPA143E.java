package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PatientPA143E {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        Main.Login.Portal portal = login.new Portal();
        portal.clickChangePassword();
        portal.setPassword("261090inCytes", "261090inCytes", "261090inCyted").wPassword().clickSavePassword();
        $$(".Mui-error").findBy(exactText("Passwords must match")).shouldBe(visible);
    }
}

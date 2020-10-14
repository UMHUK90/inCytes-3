package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PatientPA143D {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        Main.Login.Portal portal = login.new Portal();
        portal.clickChangePassword();
        portal.setPassword("261090inCytes", "261090 inCytes", "261090 inCytes").wPassword().clickSavePassword();
        $$(".Mui-error").findBy(exactText("Spaces are not allowed")).shouldBe(visible);
    }
}

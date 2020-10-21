package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class PatientPA189B {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.setAll(Main.randomText(25) + "@yandex.ru", Main.password).wLogin().isVisible();
        login.signIn();
        Main.eBottomMessage().shouldHave(Condition.text("Your email or password is incorrect"));
    }
}
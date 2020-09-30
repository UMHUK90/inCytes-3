package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;

public class ClinicianL32A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.isVisible();
        login.setAll("", "261090inCytes!261090inCytes!261090inCytes!261090inCytes!261090inCytes!261090inCytes!261090inCytes!!").wLogin();
        $(name("password")).shouldHave(value("261090inCytes!261090inCytes!261090inCytes!261090inCytes!261090inCytes!261090inCytes!261090inCytes!!"));
    }
}

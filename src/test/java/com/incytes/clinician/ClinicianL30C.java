package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;

public class ClinicianL30C {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy@gmail.com", "261090inCytes").wLogin().signIn();
        $("h4").shouldHave(text("Добро пожаловать в InСytes!"));
        close();
    }
}

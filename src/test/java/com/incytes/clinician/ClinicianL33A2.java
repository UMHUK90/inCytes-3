package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ClinicianL33A2 {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.isVisible().setAll("#@#%$5263gfdFd","261090inCytes").wLogin().signIn();
        $$("p").findBy(text("Адрес электронной почты или пароль неверны, пожалуйста")).shouldBe(visible);
    }
}
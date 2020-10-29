package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.close;

public class ClinicianR12A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        $$(".MuiButton-label").findBy(text("УЖЕ ЗАРЕГИСТРИРОВАНЫ?")).shouldBe(visible);
        reg.setAll("", "Грабовский", "", "", "").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
        close();
    }
}
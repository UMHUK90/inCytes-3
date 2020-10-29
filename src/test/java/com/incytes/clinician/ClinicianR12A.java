package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

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
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
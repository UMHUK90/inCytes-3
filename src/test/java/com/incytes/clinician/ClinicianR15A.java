package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ClinicianR15A {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("","","","261090inCytes","261090inCytes!").wRegistration().cRegistration();
        reg.clickNext();
        $$(".MuiFormHelperText-filled").findBy(text("Passwords must match")).shouldBe(visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
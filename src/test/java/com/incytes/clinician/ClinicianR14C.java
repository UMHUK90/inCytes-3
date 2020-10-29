package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ClinicianR14C {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("","","","261090 inCytes","").wRegistration().cRegistration();
        reg.clickNext();
        $$(".MuiFormHelperText-filled").findBy(text("Spaces are not allowed")).shouldBe(visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
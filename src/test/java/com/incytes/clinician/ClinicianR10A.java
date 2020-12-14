package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR10A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.setAll("Andrew", "Grabovskiy", "andrew.grabovskiy+patient3a@gmail.com", "261090inCytes", "261090inCytes").wRegistration();
        reg.clickTCradio();
        reg.haveNextButton();
        $(".MuiButton-label", 1).shouldBe(visible); /* "Already Have an Account?" button  */
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
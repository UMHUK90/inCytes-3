package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR10D_Partial {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("Andrew", "Grabovskiy", "andrew.grabovskiy+clinician1aa@gmail.com", "261090inCytes", "261090inCytes").wRegistration().clickNext();
        $$("h4").findBy(text("Код подтверждения")).shouldBe(visible);
    }
}

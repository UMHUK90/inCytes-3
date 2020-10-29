package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR21A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        open("https://alpha.incytesdata-dev.com/auth/register/confirmation" + "?email=andrew.grabovskiy@gmail.com");
        $$("input").findBy(attribute("placeholder", "Код подтверждения")).shouldBe(visible);
        $(byAttribute("placeholder", "Код подтверждения")).setValue("1234567890123456");
        $(byAttribute("placeholder", "Код подтверждения")).shouldHave(value("1234567890123456"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
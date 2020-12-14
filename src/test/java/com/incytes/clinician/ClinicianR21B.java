package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR21B {
    @Test
    public void c_test() {
        open("https://alpha.incytesdata-dev.com/auth/register/confirmation" + "?email=andrew.grabovskiy@gmail.com");
        $$("input").findBy(attribute("placeholder", "Код подтверждения")).shouldBe(visible);
        $(byAttribute("placeholder", "Код подтверждения")).setValue("123456");
        $(byAttribute("placeholder", "Код подтверждения")).shouldHave(value("123456"));
        $$(".MuiButton-label").findBy(text("SUBMIT")).click();
        $$("p").findBy(text("Не удалось подтвердить ваш адрес электронной почты")).shouldBe(visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
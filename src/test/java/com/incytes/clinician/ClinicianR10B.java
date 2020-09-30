package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR10B {
    public static String siteurl = "https://alpha.incytesdata-dev.com/",
            password = "261090inCytes",
            existingPassword = password,
            newpassword = "261090inCytes";

    public void clickNextButton() {
        $(byAttribute("type", "submit")).click();
    }
    public void clickTCradio() {
        $(byAttribute("type", "checkbox")).click();
    }

    @Test
    public void c_test() {
    open(siteurl + "auth/register");
    $$(".MuiButton-label").findBy(text("УЖЕ ЕСТЬ УЧЁТНАЯ ЗАПИСЬ?")).shouldBe(visible);
    clickTCradio();
    clickNextButton();
    $$(byText("Required")).shouldHave(size(5));
    }
}

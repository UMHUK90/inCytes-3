package com.incytes.clinician;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR22C_REDO {
    @Test
    public void c_test() {
        Main main = new Main("Fr");
        open("https://qa.incytesdata-dev.com/auth/register/");
        $("a").shouldHave(text("Termes et conditions")).click();
        switchTo().window(1);
        $(".MuiTypography-h3").shouldHave(text("Termes et conditions"));
        $$("a").findBy(text("www.incytesapp.com")).click();
        $$("p").findBy(text("Built by clinicians for clinicians")).shouldBe(visible);
    }
}

package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianD171B {
    @Test
    public void c_test() {
        Main main = new Main("Fr");
        open("https://alpha.incytesdata-dev.com/auth/register/");
        $("a").shouldHave(text("Termes et conditions")).click();
        switchTo().window(1);
        $(".MuiTypography-h3").shouldHave(text("Termes et conditions"));
        $$("a").findBy(text("www.incytesapp.com")).click();
        $$("p").findBy(text("Built by clinicians for clinicians")).shouldBe(visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

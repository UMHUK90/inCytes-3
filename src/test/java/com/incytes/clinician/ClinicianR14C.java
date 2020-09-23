package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class ClinicianR14C {
    public static String siteurl = "https://alpha.incytesdata-dev.com/",
            password = "261090inCytes",
            existingPassword = password,
            newpassword = "261090inCytes";


    public void clickTCradio() {
        $(byAttribute("type", "checkbox")).click();
    }
    public void clickNextButton() {
        $(xpath("//*[@id=\"root\"]/div/form/div/div[9]/div/button")).click();
    }

    @Test
    public void c_test() {
        open(siteurl + "auth/register");
        $$(".MuiButton-label").findBy(text("УЖЕ ЕСТЬ УЧЁТНАЯ ЗАПИСЬ?")).shouldBe(visible);
        $(name("password")).setValue("261090 inCytes").shouldBe(visible);
        $(name("password")).shouldHave(value("261090 inCytes"));
        clickTCradio();
        clickNextButton();
        $$(".MuiFormHelperText-filled").findBy(text("Spaces are not allowed")).shouldBe(visible);
    }
}

package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class ClinicianR12D {
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
        $(name("firstName")).setValue("Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group TestA").shouldBe(visible);
        $(name("firstName")).shouldHave(value("Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group TestA"));
        clickTCradio();
        clickNextButton();
        $$(byText("Required")).shouldHave(size(4));
    }
}

package com.incytes.patientSE;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA180B_Fail {
    public static String siteurl = "https://qa-patient-enrollment.incytesdata-dev.com/",
                         password = "261090inCytes";

    public void clickSignInButton() {
        $(xpath("//*[@id=\"root\"]/div/form/div/div/div[8]/button")).click();
    }

    @Test
    public void c_test() {
        open(siteurl + "auth/register");
        $(name("email")).setValue("and" + ' ' + "rew");
        clickSignInButton();
        $$("p.Mui-error").shouldHave(size(3));
        $(name("email")).setValue("andrew");
        clickSignInButton();
        $$("p.Mui-error").shouldHave(size(3));
        $(name("email")).setValue("@@");
        clickSignInButton();
        $$("p.Mui-error").shouldHave(size(3));
        $(name("email")).setValue("Русский");
        clickSignInButton();
        $$("p.Mui-error").shouldHave(size(3));
    }
}

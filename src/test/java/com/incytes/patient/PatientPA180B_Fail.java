package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.xpath;

public class PatientPA180B_Fail {
    public static String siteurl = "https://qa-patient.incytesdata-dev.com/",
                         password = "261090inCytes";

    public void clickSignInButton() {
        $(xpath("//*[@id=\"root\"]/div/form/div/div/div[8]/button")).click();
    }

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.email = "andrew";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
        reg.email = "and" + ' ' + "rew";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
        reg.email = "@@";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
        reg.email = "Русский";
        reg.wRegistration();
        reg.clickLogin();
        Main.muiError(3);
    }
}

package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA143B {
    public static String siteurl = "https://qa-patient.incytesdata-dev.com/";
    public static String password = "261090inCytes";

    public void EnterEmail() {
        $(name("email")).setValue("andrew.grabovskiy+patient32@gmail.com");
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void clickSignInButton() {
        $(xpath("//*[@id='root']/div/form/div/div/div[6]/button")).click();
    }
    public void clickChangePasswordButton() {
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/div[1]/div/button[3]")).click();
    }
    public void clickSaveButton() {
        $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div/div[5]/button")).click();
    }

    @Test
    public void c_test() {
        open(siteurl + "auth/login");
        EnterEmail();
        EnterPassword();
        clickSignInButton();
        $$(".MuiTypography-h5").findBy(text("Отчёт о лечении"));
        clickChangePasswordButton();
        clickSaveButton();
        $$(byText("Required")).shouldHave(size(3));
    }
}

package com.incytes.patientSE;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA143A {
    public static String siteurl = "https://qa-patient-enrollment.incytesdata-dev.com/";
    public static String password = "261090inCytes",
            newpassword = "261090inCytes";
    public void clickSignInButton() {
        $(xpath("//*[@id='root']/div/form/div/div/div[8]/button")).click();
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void clickChangePasswordButton() {
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).waitUntil(visible, 10000).click();
    }
    public void clickSaveButton() {
        $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div/div[5]/button")).click();
    }

    @Test
    public void c_test() {
        open(siteurl + "auth/login");
        $(name("email")).setValue("andrew.grabovskiy+patient32@gmail.com");
        EnterPassword();
        clickSignInButton();
        $$(".MuiTypography-h5").findBy(text("Отчёт о лечении"));
        clickChangePasswordButton();
        $(name("existingPassword")).shouldBe(visible);
        $(name("newPassword")).shouldBe(visible);
        $(name("confirmPassword")).shouldBe(visible);
    }
}
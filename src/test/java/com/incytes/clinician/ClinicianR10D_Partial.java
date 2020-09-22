package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class ClinicianR10D_Partial {
    public static String siteurl = "https://alpha.incytesdata-dev.com/",
            password = "261090inCytes",
            existingPassword = password,
            newpassword = "261090inCytes";

    public void EnterFirstName() {
        $(name("firstName")).setValue("Andrew").shouldBe(visible);
    }
    public void EnterLastName() {
        $(name("lastName")).setValue("Grabovskiy").shouldBe(visible);
    }
    public void EnterEmail() {
        $(name("email")).setValue("andrew.grabovskiy+clinician1a@gmail.com");
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void EnterVerifyPassword() {
        $(name("verifyPassword")).setValue(password);
    }

    public void clickTCradio() {
        $(byAttribute("type", "checkbox")).click();
    }
    public void clickNextButton() {
        $(xpath("//*[@id=\"root\"]/div/form/div/div[9]/div/button")).click();
    }
    public void clickChangePasswordButton() {
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).click();
    }
    public void EnterConfirmPassword() {
        $(name("confirmPassword")).setValue(newpassword);
    }
    public void clickSaveButton() {
        $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div/div[5]/button")).click();
    }

    @Test
    public void c_test() {
        open(siteurl + "auth/register");
        $$(".MuiButton-label").findBy(text("УЖЕ ЕСТЬ УЧЁТНАЯ ЗАПИСЬ?")).shouldBe(visible);
        EnterFirstName();
        EnterLastName();
        EnterEmail();
        EnterPassword();
        EnterVerifyPassword();
        clickTCradio();
        clickNextButton();
        $$("h4").findBy(text("Код подтверждения")).shouldBe(visible);
    }
}

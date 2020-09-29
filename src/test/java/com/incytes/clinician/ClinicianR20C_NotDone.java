package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class ClinicianR20C_NotDone {
    public static String siteurl = "https://alpha.incytesdata-dev.com/",
            email = "qwertyuiop17091709@yandex.by",
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
        $(name("email")).setValue(email);
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
        $("span.MuiButton-label").shouldHave(text("SUBMIT")).click();
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
        EnterFirstName();
        EnterLastName();
        EnterEmail();
        EnterPassword();
        EnterVerifyPassword();
        clickTCradio();
        clickNextButton();
        $$("h4").findBy(text("Код подтверждения")).shouldBe(visible);
        $$("h4").findBy(text("Код подтверждения был успешно отправлен на вашу электронную почту. Пожалуйста, введите код в соответствующее поле.")).shouldBe(visible);
        $$("input").findBy(attribute("placeholder", "Код подтверждения")).shouldBe(visible);
        $$("span.MuiButton-label").findBy(text("SUBMIT")).shouldBe(visible);
        $$("span.MuiButton-label").findBy(text("Отправить код подтверждения ещё раз")).shouldBe(visible).click();
        $(byAttribute("var", "body1")).shouldHave(text("Код был отправлен на ваш электронный адрес"));
    }
}
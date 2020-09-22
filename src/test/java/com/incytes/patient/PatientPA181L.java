package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA181L {
    public static String siteurl = "https://qa-patient.incytesdata-dev.com/",
                         password = "261090inCytes",
                         existingPassword = password,
                         newpassword = "261090inCytes";

    public void EnterEmail() {
        $(name("email")).setValue("andrew.grabovskiy+patient3a@gmail.com");
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void EnterConfirmPassword() {
        $(name("confirmPassword")).setValue(password);
    }
    public void clickSignUpButton() {
        $(xpath("//*[@id=\"root\"]/div/form/div/div/div[8]/button")).click();
    }
    public void clickChangePasswordButton() {
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).click();
    }
    public void clickSaveButton() {
        $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div/div[5]/button")).click();
    }

    @Test
    public void c_test() {
        open(siteurl + "auth/register");
        EnterEmail();
        EnterPassword();
        EnterConfirmPassword();
        clickSignUpButton();
        $(".MuiTypography-h3").shouldHave(text("Добро пожаловать! Ваш аккаунт почти готов."));
        $(name("firstName")).setValue("Андрей");
        $(name("firstName")).shouldHave(value("Андрей"));
        $(name("lastName")).setValue("Грабовский");
        $(name("lastName")).shouldHave(value("Грабовский"));
        $(name("birthDate")).setValue("19450509");
        $(name("birthDate")).shouldHave(value("19450509"));
        $(name("countryName")).setValue("Belarus");
        $(name("countryName")).shouldHave(value("Belarus"));
        $(name("phoneNumber")).setValue("1234567890123456");
        $(name("phoneNumber")).shouldHave(value("1234567890123456"));
        $("button.Mui-disabled").shouldBe(visible);
        $(xpath("//*[@id=\"root\"]/form/div/div[4]/div/div[5]/div/div/div/label/span[1]/span[1]/input")).click();
        $(xpath("//*[@id=\"root\"]/form/div/div[4]/div/div[7]/div/div/div/label/span[1]/span[1]/input")).click();
        $("button").shouldHave(attribute("style", "background-color: rgb(0, 122, 255)")).click();
        $("MuiButton-label").shouldHave(text("Сохранить"));
    }
}

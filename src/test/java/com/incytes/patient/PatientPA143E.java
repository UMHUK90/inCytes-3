package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA143E {
    public static String siteurl = "https://qa-patient.incytesdata-dev.com/",
                         password = "261090inCytes",
                         existingPassword = password,
                         newpassword = "261090inCytes",
                         newpassword2 = "261090inCyted",
                         invalidpassword = "26inCyt";

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
    public void EnterExistingPassword() {
        $(name("existingPassword")).setValue(existingPassword);
    }
    public void EnterInvalidPassword() {
        $(name("newPassword")).setValue(newpassword);
    }
    public void EnterInvalidConfirmPassword() {
        $(name("confirmPassword")).setValue(newpassword2);
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
        EnterExistingPassword();
        EnterInvalidPassword();
        EnterInvalidConfirmPassword();
        clickSaveButton();
        $$(".Mui-error").findBy(exactText("Passwords must match")).shouldBe(visible);
    }
}

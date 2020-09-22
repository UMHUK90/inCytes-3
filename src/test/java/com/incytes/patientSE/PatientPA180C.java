package com.incytes.patientSE;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA180C {
    public static String siteurl = "https://qa-patient-enrollment.incytesdata-dev.com/",
                         password = "261090inCytes",
                         existingPassword = password,
                         invalidpassword = "26inCyt";

    public void EnterEmail() {
        $(name("email")).setValue("andrew.grabovskiy+patient32@gmail.com");
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void clickSignInButton() {
        $(xpath("//*[@id='root']/div/form/div/div/div[8]/button")).click();
    }
    public void clickChangePasswordButton() {
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).click();
    }
    public void EnterExistingPassword() {
        $(name("existingPassword")).setValue(existingPassword);
    }
    public void EnterInvalidPassword() {
        $(name("newPassword")).setValue(invalidpassword);
    }
    public void EnterInvalidConfirmPassword() {
        $(name("confirmPassword")).setValue(invalidpassword);
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
        $(byText("8 character minimum. Must contain at least 1 upper case, 1 lower case, 1 number."));
    }
}

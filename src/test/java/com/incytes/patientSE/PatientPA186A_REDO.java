package com.incytes.patientSE;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA186A_REDO {
    public static String baseUrl = "https://qa-patient.incytesdata-dev.com/",
                         password = "261090inCytes",
                         existingPassword = password,
                         newpassword = "261090inCytes";
    public void EnterEmail() {
        $(name("email")).setValue("andrew.grabovskiy+patient11@gmail.com");
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void clickSignInButton() {
        $(xpath("//*[@id=\"root\"]/div/form/div/div/div[6]/button")).click();
    }
    public void clickChangePasswordButton() {
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).click();
    }
    public void clickSaveButton() {
        $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div/div[5]/button")).click();
    }


    @Test
    public void c_test() {
        open(baseUrl + "auth/login");
        EnterEmail();
        EnterPassword();
        clickSignInButton();
        open("https://qa-patient.incytesdata-dev.com/survey/126/31/69/42");
        $$("p").findBy(text("Failed to get survey, try later.")).waitUntil(visible, 10000);
    }
}

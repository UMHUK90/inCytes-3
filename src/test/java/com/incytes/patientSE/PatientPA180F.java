package com.incytes.patientSE;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA180F {
    public static String siteurl = "https://qa-patient-enrollment.incytesdata-dev.com/",
                         password = "261090inCytes",
                         newpassword = "261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090inCytes261090!";

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
    public void EnterNewPassword() {
        $(name("newPassword")).setValue(newpassword);
    }
    public void EnterConfirmPassword() {
        $(name("confirmPassword")).setValue(newpassword);
    }

    @Test
    public void c_test() {
        open(siteurl + "auth/login");
        EnterEmail();
        EnterPassword();
        clickSignInButton();
        $$(".MuiTypography-h5").findBy(text("Отчёт о лечении"));
        clickChangePasswordButton();
        EnterNewPassword();
        EnterConfirmPassword();
        $(name("newPassword")).shouldHave(value(newpassword));
        $(name("confirmPassword")).shouldHave(value(newpassword));
    }
}

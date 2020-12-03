package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA184A {
    public static String baseUrl = "https://alpha-patient.incytesdata-dev.com/",
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
        $$("p").findBy(exactText("О ГРАФИКЕ"));
        $$("p").findBy(exactText("MY RESULTS")).click();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

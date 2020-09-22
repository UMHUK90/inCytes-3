package com.incytes.patientSE;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA185A {
    public static String baseUrl = "https://qa-patient-enrollment.incytesdata-dev.com/",
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
        System.setProperty("chromeoptions.prefs","intl.accept_languages=ru");
        open(baseUrl + "auth/login");
        EnterEmail();
        EnterPassword();
        clickSignInButton();
        $$("p").findBy(exactText("О ГРАФИКЕ"));
        $$("p").findBy(text("РУ - Scoring Group Test #6 Description - Single Questions Only."));
        $$("img").findBy(attribute("src", "https://d1bw0wydsbyssf.cloudfront.net/photos/8df7312d-6250-448f-801c-fdaac85d9feb.png")).waitUntil(visible, 10000);;
        $$("h4").findBy(exactText("Lip injection РУ"));
        $$("p").findBy(exactText("Губы внедрение описание купить недорого"));
        $$("img").findBy(attribute("src", "https://d1bw0wydsbyssf.cloudfront.net/photos/c75ab6e6-50a2-4ce7-8ed2-4befcf967d5b.png")).waitUntil(visible, 10000);;
        $$("h4").findBy(exactText("Knee Pain in Russian"));
        $$("p").findBy(exactText("Knee Pain in Russian"));
        close();
    }
}

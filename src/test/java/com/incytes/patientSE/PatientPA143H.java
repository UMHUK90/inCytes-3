package com.incytes.patientSE;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA143H {
    public static String siteurl = "https://qa-patient-enrollment.incytesdata-dev.com/",
                        password = "261090inCytes",
                        newpassword = "261090inCytes";

    @Test
    public void c_test() {
        open(siteurl + "auth/login");
        $(name("email")).setValue("andrew.grabovskiy+patient32@gmail.com");
        $(name("password")).setValue(password);
        $(xpath("//*[@id='root']/div/form/div/div/div[8]/button")).click();
        $$(".MuiTypography-h5").findBy(text("Отчёт о лечении"));
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).click();
        $(name("existingPassword")).setValue(password);
        $(name("newPassword")).setValue(newpassword);
        $(name("confirmPassword")).setValue(newpassword);
        $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div/div[5]/button")).click();
        $(byText("Успешно обновлено")).waitUntil(visible, 10000);
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/div[1]/div/button[4]")).click();
        $(name("email")).setValue("andrew.grabovskiy+patient32@gmail.com");
        $(name("password")).setValue(newpassword);
        $(xpath("//*[@id='root']/div/form/div/div/div[6]/button")).click();
    }
}
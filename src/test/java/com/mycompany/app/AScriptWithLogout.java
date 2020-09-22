package com.mycompany.app;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AScriptWithLogout {
    public static int num = 25911;
    @Test(invocationCount = 3000) // invocationCount attribute defines the number of test runs

    public void b_createCaseAndAnswerIt() {
        num++;
        String email = "andrew.grabovskiy+apatient" + num + "@gmail.com";
        open("https://qa.incytesdata-dev.com/auth/login");
        $(By.name("email")).waitUntil(visible, 10000).setValue("andrew.grabovskiy+8@gmail.com");
        $(By.name("password")).waitUntil(visible, 10000).setValue("261090inCytes");
        $(By.name("loginButton")).click();
        $("#casesLink").waitUntil(visible, 20000).click();
        $("button.MuiButton-contained").waitUntil(visible, 10000).click();
        $(By.name("email")).setValue(email);
        $("#formatted-text-mask-input").setValue("2015/10/10");
        $("#sponsorId").setValue("circle");
        $("#react-autowhatever-1--item-0").waitUntil(visible, 10000).click();
        $("button.MuiButton-textSizeLarge").waitUntil(visible, 10000).click();
        $(".MuiCircularProgress-svg").waitUntil(visible, 10000);
        $(".MuiCircularProgress-svg").waitUntil(hidden, 10000);
        $(By.xpath("//*[@id=\"137\"]")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[1]/div/div/div[3]/div/div/fieldset/div/div/div[1]/div[1]/label")).waitUntil(visible, 20000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[2]/div/div/div[3]/div/div/fieldset/div/div/div[1]/div[1]/label")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[2]/button")).shouldBe(visible).click();
        $(byText("Результаты успешно сохранены")).waitUntil(visible, 10000);
        sleep(1000);
        $(By.xpath("//*[@id=\"138\"]")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[1]/div/div/div[3]/div/div/fieldset/div/div/div[3]/div[1]/label")).waitUntil(visible, 20000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[2]/div/div/div[3]/div/div/fieldset/div/div/div[1]/div[1]/label")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[2]/button")).shouldBe(visible).click();
        $(byText("Результаты успешно сохранены")).waitUntil(visible, 10000);
        sleep(1000);
        $(By.xpath("//*[@id=\"139\"]")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[1]/div/div/div[3]/div/div/fieldset/div/div/div[1]/div[1]/label")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[2]/div/div/div[3]/div/div/fieldset/div/div/div[3]/div[1]/label")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[2]/button")).shouldBe(visible).click();
        $(byText("Результаты успешно сохранены")).waitUntil(visible, 10000);
        sleep(1000);
        $(By.xpath("//*[@id=\"140\"]")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[1]/div/div/div[3]/div/div/fieldset/div/div/div[2]/div[1]/label")).waitUntil(visible, 20000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[2]/div/div/div[3]/div/div/fieldset/div/div/div[3]/div[1]/label")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[2]/button")).shouldBe(visible).click();
        $(byText("Результаты успешно сохранены")).waitUntil(visible, 10000);
        sleep(1000);
        $(By.xpath("//*[@id=\"141\"]")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[1]/div/div/div[3]/div/div/fieldset/div/div/div[1]/div[1]/label")).waitUntil(visible, 20000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[2]/div/div/div[3]/div/div/fieldset/div/div/div[2]/div[1]/label")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[2]/button")).shouldBe(visible).click();
        $(byText("Результаты успешно сохранены")).waitUntil(visible, 10000);
        sleep(1000);
        $(By.xpath("//*[@id=\"142\"]")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[1]/div/div/div[3]/div/div/fieldset/div/div/div[2]/div[1]/label")).waitUntil(visible, 20000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[2]/div/div/div[3]/div/div/fieldset/div/div/div[2]/div[1]/label")).waitUntil(visible, 10000).click();
        $(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div/section/div/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[2]/form/div/div[2]/button")).shouldBe(visible).click();
        $(byText("Результаты успешно сохранены")).waitUntil(visible, 10000);
        $(withText("Выйти")).click();
    }
}
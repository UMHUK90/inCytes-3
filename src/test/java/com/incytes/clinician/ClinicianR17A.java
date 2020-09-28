package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR17A {
    @Test
    public void OpenTwoTabs(){
        org.selenide.examples.Main main = new org.selenide.examples.Main();
        int count = 14; // 14
        org.selenide.examples.Main.Registration reg = main.new Registration("https://alpha.incytesdata-dev.com/auth/register");
        reg.open();
        reg.setAll("Dmitry", "Polsky", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!", "261090inCytes!");
        reg.wRegistration().cRegistration().clickNext();
        org.selenide.examples.Main.newTab();
        switchTo().window(1);
        open("https://passport.yandex.by/auth");
        $(By.name("login")).setValue("qwertyuiop17091709@yandex.by");
        $(byAttribute("type", "submit")).click();
        $(byAttribute("type", "password")).setValue("cilaCILA17097938");
        $(byAttribute("type", "submit")).click();
        if($(byAttribute("type", "tel")).exists()) {$(byAttribute("type", "tel")).setValue("+375298746833");
        $(byAttribute("type", "submit")).click();}
        $(".user-account__name").click(); //
        $(byAttribute("href", "https://mail.yandex.by")).click();
        $$(byText("Your verification code to inCytes™")).first().click();
        String code;
        if(!$(".mail-Message-Body-Content").exists()) code = $(".mail-MessageSnippet-Item_firstline").closest("span").getText().replace("Your verification code is ", "").replace(".", "").replace("Your verification code to inCytes™", "").substring(String.valueOf(Math.abs(count)).length()+1);
        else code = $(".mail-Message-Body-Content").getText().replace("Your verification code is ", "").replace(".", "").replace("Your verification code to inCytes™","").strip().substring(String.valueOf(Math.abs(count)).length()+1);
        switchTo().window(0);
        reg.submitCode(code);
        $$("h3").findBy(Condition.text("Sign in")).shouldBe(Condition.visible);
        sleep(10000);
    }
}

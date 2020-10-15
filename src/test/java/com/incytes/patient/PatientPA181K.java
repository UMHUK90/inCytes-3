package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

public class PatientPA181K {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.setAll("qwertyuiop17091709+79@yandex.by", Main.password, Main.password, "Андрей", "Грабовский", "1945/05/09", "Belarus", "1234567890123456").wRegistration().cRegistration().clickLogin();
        $(".MuiTypography-h3").shouldHave(text("Добро пожаловать! Ваш аккаунт почти готов."));
        reg.wwRegistration().cwRegistration();
        reg.eGetStarted().shouldHave(attribute("style", "width: 100px; color: rgb(129, 129, 131);"));
        reg.clickCheckBox();
        reg.eGetStarted().shouldHave(attribute("style", "width: 100px; color: rgb(255, 255, 255);"));
    }
}

package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PatientPA181L {
    @Test
    public void c_test() {
        patient.Main main = new patient.Main("Ru");
        patient.Main.Registration reg = main.new Registration();
        reg.open();
        reg.setAll("andrew.grabovskiy+patient3a@gmail.com", patient.Main.password, patient.Main.password, "Андрей", "Грабовский", "1945/05/09", "Belarus", "1234567890123456").wRegistration().cRegistration().clickLogin();
        $(".MuiTypography-h3").shouldHave(text("Добро пожаловать! Ваш аккаунт почти готов."));
        reg.wwRegistration().cwRegistration();
        reg.clickCheckBox();
        reg.getStarted().shouldHave(attribute("style", "width: 300px; color: white; background-color: rgb(0, 122, 255);"));
        reg.getStarted().shouldHave(text("Продолжить"));
    }
}

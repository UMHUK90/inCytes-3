package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PatientPA181L {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        reg.open();
        reg.setAll("qwertyuiop17091709+79@yandex.by", Main.password, Main.password, "Андрей", "Грабовский", "1945/05/09", "Belarus", "1234567890123456").wRegistration().cRegistration().clickLogin();
        $(".MuiTypography-h3").shouldHave(text("Добро пожаловать! Ваш аккаунт почти готов."));
        reg.wwRegistration().cwRegistration();
        reg.clickCheckBox();
        reg.eGetStarted().shouldHave(attribute("style", "width: 300px; color: white; background-color: rgb(0, 122, 255);"));
        reg.eGetStarted().shouldHave(text("Продолжить"));
    }
}

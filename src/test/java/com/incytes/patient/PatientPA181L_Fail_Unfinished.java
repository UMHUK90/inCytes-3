package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;

public class PatientPA181L_Fail_Unfinished {
    @Test
    public void c_test() {
        PatientPA181B pa181b = new PatientPA181B();
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutException(pa181b::c_test);
        Main.setLang("En");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        reg.open();
        reg.setAll("qwertyuiop17091709+" + (count-1) + "@yandex.by", Main.password, Main.password, "Андрей", "Грабовский", "1945/05/09", "Belarus", "1234567890123456").wRegistration().cRegistration().clickLogin();
        //$(".MuiTypography-h3").shouldHave(text("Добро пожаловать! Ваш аккаунт почти готов."));
        reg.wwRegistration().cwRegistration();
        reg.clickCheckBox();
        reg.eGetStarted().shouldHave(attribute("style", "width: 100px; color: rgb(255, 255, 255);"));
        //reg.eGetStarted().shouldHave(text("Продолжить"));
        reg.clickGetStarted();
        Main.currentPage().contains("survey");
        clinician.Main main_clinician = new clinician.Main("Ru");
        clinician.Main.Login login_clinician = main_clinician.new Login();
        login_clinician.open().setAll("andrew.grabovskiy+1@gmail.com", "261090inCytes").wLogin().signIn();
        sleep(500000000);


    }
}

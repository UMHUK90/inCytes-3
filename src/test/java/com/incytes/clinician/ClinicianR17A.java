package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianR17A {
    @Test
    public void OpenTwoTabs(){
        Main main = new Main("En");
        Main.FileTXT file = main.new FileTXT("C:\\Users\\qwert\\new-app\\src\\main\\java\\org\\selenide\\examples\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration("https://qa.incytesdata-dev.com/auth/register");
        reg.open();
        reg.setAll("Dmitry", "Polsky", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!", "261090inCytes!");
        reg.wRegistration().cRegistration().clickNext();
        file.writeTest(String.valueOf(count+1), false);
        Main.newTab();
        switchTo().window(1);
        Main.GetCodeWithYandex getcode = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        String code = getcode.fastCode();
        switchTo().window(0);
        reg.submitCode(code);
        $$("h3").findBy(Condition.text("Sign in")).shouldBe(Condition.visible);
    }
}

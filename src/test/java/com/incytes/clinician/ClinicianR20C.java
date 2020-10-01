package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ClinicianR20C {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration("https://qa.incytesdata-dev.com/auth/register");
        reg.open();
        reg.setAll("Dmitry", "Polsky", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!", "261090inCytes!");
        reg.wRegistration().cRegistration().clickNext();
        file.writeText(String.valueOf(count+1), false);
        reg.resendCode(); /* Clicking resend button */
        Main.newTab();
        switchTo().window(1);
        Main.GetCodeWithYandex getcode = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        String code = getcode.fastCode();
    }
}
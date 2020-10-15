package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.enabled;

public class ClinicianRP40Efail {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword("qwertyuiop17091709+1@yandex.by");
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        Selenide.sleep(5000000);
        login.eEmail().shouldNotBe(enabled);
        Main.GetCodeWithYandex getcode = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        String code = getcode.fastCode();

        if(code.isEmpty()) new Exception("Код не пришёл");
    }
}

package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class PatientPA188E_Fail {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.checkForgotPasswordForm();
        login.writeEmail_forgotPassword("qwertyuiop17091709+250@yandex.by");
        login.eEmail().shouldHave(Condition.value("qwertyuiop17091709+250@yandex.by"));
        login.clickSubmit_forgotPassword();
        login.checkResetPasswordForm();
        login.eEmail().shouldHave(Condition.value("qwertyuiop17091709+250@yandex.by"));
        Main.newTab();
        Selenide.switchTo().window(1);
        Main.GetCodeWithYandex invitation = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.by", "cilaCILA17097938", "");
        String code = invitation.fastCode();
        if(code.isEmpty() && Integer.getInteger(String.valueOf(invitation.time.toCharArray()[3]) + invitation.time.toCharArray()[4]) > LocalDateTime.now().getMinute()-1) System.exit(-1);
        System.out.println(code);
        System.out.println(invitation.time);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
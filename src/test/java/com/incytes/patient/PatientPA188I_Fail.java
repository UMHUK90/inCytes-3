package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA188I_Fail {
    @Test
    public void c_test() {
        Main main = new Main("En", "qa");
        Main.Login login = main.new Login().open();
        login.forgotPassword();
        login.checkForgotPasswordForm();
        login.writeEmail_forgotPassword("qwertyuiop17091709+398@yandex.by");
        login.clickSubmit_forgotPassword();
        login.checkResetPasswordForm();
        Main.newTab();
        switchTo().window(1);
        Main.GetCodeWithYandex invitation = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        String code = invitation.fastCode();
        if(code.isEmpty()) throw new ElementNotVisibleException("НЕТ КОДА");
        switchTo().window(0);
        login.writeCode_resetPassword(code + "d");
        login.ePassword().setValue(Main.password);
        login.eConfirmPassword_resetPassword().setValue(Main.password);
        login.clickReset_resetPassword();
        Selenide.sleep(50000000);
        Main.muiError(1,0).shouldHave(Condition.text("Invalid verification code"));
        //1 - 29 - app.confirmPassword.wrongCode</p> Text
        //2 - Error in Buttons with all tests in Code_resetPassword
    }
}
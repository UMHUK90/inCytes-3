package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA188H_Fail {
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
        login.writeCode_resetPassword(code);
        login.ePassword().setValue(Main.password);
        login.eConfirmPassword_resetPassword().setValue(Main.password);
        login.clickReset_resetPassword();
        Main.eBottomMessage().shouldHave(Condition.text("app.confirmPassword.success"));
        //1 - There is nothing the same things that the test have at the end
        //2 - Error in Buttons with all tests in Code_resetPassword
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
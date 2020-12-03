package com.incytes.clinician.irrelevant;

import com.codeborne.selenide.Selenide;
import com.incytes.clinician.Main;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP50D_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        String email = "qwertyuiop17091709+347@yandex.by";
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword(email);
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        //Error with BUTTON - 17
        Main.newTab();
        Selenide.switchTo().window(1);
        Main.GetCodeWithYandex getcode = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        String code = getcode.fastCode();
        if(code.isEmpty()) throw new ElementNotVisibleException("НЕТ КОДА");
        Selenide.switchTo().window(0);
        login.writeAccessCode_resetPassword(code);
        login.writePassword_resetPassword(Main.password + "!");
        login.writeConfirmPassword_resetPassword(Main.password + "!");
        login.clickReset_resetPassword();
        //Main.eBottomMessage().shouldHave(Condition.text("Password was successfully reset."));
        // MESSAGE NOT APPEARED - 29
        login.setAll(email, Main.password);
        login.wLogin().signIn();
        Main.eBottomMessage().shouldBe();
        login.ePassword().setValue(Main.password + "!");
        login.ePassword().shouldNotBe();
        Selenide.close();
        login.open().forgotPassword();
        login.writeEmail_forgotPassword(email);
        login.clickSubmit_forgotPassword();
        Main.newTab();
        Selenide.switchTo().window(1);
        code = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833").fastCode();
        Selenide.switchTo().window(0);
        login.writeAccessCode_resetPassword(code);
        login.writePassword_resetPassword(Main.password);
        login.writeConfirmPassword_resetPassword(Main.password);
        login.clickReset_resetPassword();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

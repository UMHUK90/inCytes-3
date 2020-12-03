package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianRP42B_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword("qwertyuiop17091709+2@yandex.by");
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        //Error with BUTTON - 17
        com.incytes.patient.Main.eBottomMessage().shouldHave(Condition.text("A verification code has been successfully sent to your email"));
        //NOTHING appeared at the bottom of the page! - 19
        Main.newTab();
        switchTo().window(1);
        Main.GetCodeWithYandex getcode = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        int code = Integer.parseInt(getcode.fastCode());
        if(code == 0) throw new ElementNotVisibleException("НЕТ КОДА");
        $(".mail-MessageSnippet-Item_subject").shouldHave(Condition.text("Your verification code to inCytes™"));
        $(".js-message-snippet-firstline").shouldHave(Condition.text("Your verification code is"));
        getcode.eLastSender().shouldHave(Condition.text("no-reply@verificationemail.com"));
        getcode.clickLastCheckBox();
        getcode.eDelete().click();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

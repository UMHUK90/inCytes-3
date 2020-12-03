package com.incytes.clinician.irrelevant;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.clinician.Main;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianRP50A_FAIL {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open().forgotPassword().forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword("qwertyuiop17091709+342@yandex.by");
        login.diplayedEmail_forgotPassword();
        login.clickSubmit_forgotPassword();
        //Error with BUTTON - 19
        login.checkResetPasswordForm();
        login.eNote_resetPassword().shouldHave(Condition.text("A verification code has been successfully sent to your email. Please input the code below"));
        login.eEmail().shouldBe(Condition.disabled);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

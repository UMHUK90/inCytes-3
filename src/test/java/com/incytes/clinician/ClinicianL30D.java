package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianL30D {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        $("h3").shouldHave(text("Сброс пароля"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

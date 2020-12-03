package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA189C {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.setAll(Main.randomText(25), Main.password).wLogin().isVisible();
        login.signIn();
        Main.eBottomMessage().shouldHave(Condition.text("Your email or password is incorrect"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
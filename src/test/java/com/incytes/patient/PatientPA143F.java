package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA143F {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+QApatient2@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.setAllPassword_changePassword("", Main.randomText(97)+"G9", "").writeAll_changePassword().checkAll_changePassword();
        home.clickSave_changePassword();
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
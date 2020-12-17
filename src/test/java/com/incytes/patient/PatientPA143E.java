package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA143E {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+QApatient2@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.setAllPassword_changePassword("", Main.password, Main.randomText(6) + "G5").writeAll_changePassword().checkAll_changePassword();
        home.clickSave_changePassword();
        Main.muiError(2,1).shouldHave(Condition.text("Passwords must match"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
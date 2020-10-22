package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

public class PatientPA143K {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.setAllPassword_changePassword(Main.password, Main.password+"!", Main.password+"!").writeAll_changePassword().checkAll_changePassword();
        home.clickX_changePassword();
        Selenide.closeWebDriver();
        login.open().wLogin().signIn();
        home.eChangePassword().shouldBe(Condition.visible);
    }
}
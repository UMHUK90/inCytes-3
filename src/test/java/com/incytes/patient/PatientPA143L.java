package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA143L {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+QApatient2@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.setAllPassword_changePassword(Main.password, Main.password+"!", Main.password+"!").writeAll_changePassword().checkAll_changePassword();
        Selenide.actions().moveToElement(home.eChangePassword(), -50, -50).click().build().perform();
        Main.clickOutSide(home.eChangePassword(), -50, -50);
        home.eSave_changePassword().shouldNotBe(Condition.visible);
        Selenide.closeWebDriver();
        login.open().wLogin().signIn();
        home.eChangePassword().shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
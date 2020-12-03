package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA143D {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.setAllPassword_changePassword("", Main.randomText(5) + " G5", "").writeAll_changePassword().checkAll_changePassword();
        home.clickSave_changePassword();
        Main.muiError(3,1).shouldHave(Condition.text("Spaces are not allowed"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
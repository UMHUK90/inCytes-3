package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA143I {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.setAllPassword_changePassword(Main.password, Main.randomText(7)+"5", Main.password).writeAll_changePassword().checkAll_changePassword();
        home.clickSave_changePassword();
        Main.muiError(2,0).shouldHave(Condition.text("8 character minimum. Must contain at least 1 upper case, 1 lower case, 1 number."));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
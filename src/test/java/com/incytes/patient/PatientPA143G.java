package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class PatientPA143G {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickChangePassword();
        home.setAllPassword_changePassword(Main.randomText(5)+"G5", Main.password, Main.password).writeAll_changePassword().checkAll_changePassword();
        home.clickSave_changePassword();
        Main.eBottomMessage().shouldHave(Condition.text("Action failed, Please try again"));
    }
}
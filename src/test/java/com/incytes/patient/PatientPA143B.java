package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PatientPA143B {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        Main.Login.Portal portal = login.new Portal();
        portal.clickChangePassword();
        portal.clickSavePassword();
        $$(byText("Required")).shouldHave(size(3));
    }
}

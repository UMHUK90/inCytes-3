package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class PatientPA143H {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        Main.Login.Portal portal = login.new Portal();
        portal.clickChangePassword();
        portal.setPassword("261090inCytes", "261090inCytes", "261090inCytes").wPassword().clickSavePassword();
        $(byText("Updated successfully")).waitUntil(visible, 6000);
        portal.clickLogout();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        portal.isThisLanguage("En");
        sleep(2000);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA187A {
    @Test
    public void c_test() {
        Main main = new Main("It");
        Main.Login login = main.new Login();
        login.open().setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password + "!").wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.eEmail_Managing().click();
        home.eEmail_Managing().shouldHave(Condition.attribute("style", "cursor: pointer; margin-top: 3px; word-break: break-word;"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
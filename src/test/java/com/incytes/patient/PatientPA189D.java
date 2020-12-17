package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA189D {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.randomText(25)+"1A").wLogin().isVisible();
        login.signIn();
        Main.eBottomMessage().shouldHave(Condition.text("Your email or password is incorrect"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
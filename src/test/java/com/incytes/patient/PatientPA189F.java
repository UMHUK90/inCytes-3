package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA189F {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password.substring(4, Main.password.length()-2) + " ").wLogin().isVisible();
        login.signIn();
        Main.muiError(1,0).shouldHave(Condition.text("Spaces are not allowed"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
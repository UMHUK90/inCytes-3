package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA189A {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.signIn();
        Main.haveRequired(2);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA187C {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open().setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password + "!").wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.checkHome();
        home.eTitles_Treatment().first().shouldHave(Condition.text("Tanzania"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
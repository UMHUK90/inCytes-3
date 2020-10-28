package com.incytes.patient;

import com.codeborne.selenide.Condition;
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
}
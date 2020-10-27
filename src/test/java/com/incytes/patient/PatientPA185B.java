package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class PatientPA185B {
    @Test
    public void c_test() {
        Main main = new Main("Sp");
        Main.Login login = main.new Login();
        login.open().setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password + "!").wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.checkHome();
        home.eTextBodies_Treatment().first().shouldHave(Condition.text("Descripción del tratamiento: Tenedor. Muchos fósiles de homínidos importantes se han en"));
        home.eTitles_Treatment().first().shouldHave(Condition.text("Nombre del tratamiento: Cuchillo. Tanzanía F"));
        home.eTextBodies_Indication().first().shouldHave(Condition.text("Tenedor"));
        home.eTitles_Indication().first().shouldHave(Condition.text("Cuchillo"));
    }
}
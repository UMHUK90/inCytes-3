package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA185D {
    @Test
    public void c_test() {
        Main main = new Main("It");
        Main.Login login = main.new Login();
        login.open().setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password + "!").wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.checkHome();
        home.eTextBodies_Treatment().first().shouldHave(Condition.text("Descrizione Del Trattamento: Forcella. Molti importanti fossili di ominidi sono stati trovati in Tanzania, come i"));
        home.eTitles_Treatment().first().shouldHave(Condition.text("Nome Del Trattamento: Coltello. Tanzania F"));
        home.eTextBodies_Indication().first().shouldHave(Condition.text("Il dominio tedesco iniziò nella Tanzania cont"));
        home.eTitles_Indication().first().shouldHave(Condition.text("Indicazione."));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA185C {
    @Test
    public void c_test() {
        Main main = new Main("Fr");
        Main.Login login = main.new Login();
        login.open().setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password + "!").wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.checkHome();
        home.eTextBodies_Treatment().first().shouldHave(Condition.text("Description Du Traitement: Fourche. De nombreux fossiles d'hominidés importants ont "));
        home.eTitles_Treatment().first().shouldHave(Condition.text("Nom Du Traitement: Couteau. La Tanzanie F"));
        home.eTextBodies_Indication().first().shouldHave(Condition.text("Fourche"));
        home.eTitles_Indication().first().shouldHave(Condition.text("Couteau"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
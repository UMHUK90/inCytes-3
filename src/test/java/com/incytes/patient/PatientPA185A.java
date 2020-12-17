package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA185A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open().setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password + "!").wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.checkHome();
        home.eTextBodies_Treatment().first().shouldHave(Condition.text("Описание лечения: в Танзании было найдено много важ"));
        home.eTitles_Treatment().first().shouldHave(Condition.text("Лечение. Танзания"));
        home.eTextBodies_Indication().first().shouldHave(Condition.text("Описание показаний: немецкое правление началось в материковой Танзании в конце 19 века, когда Германия образовала немецкую"));
        home.eTitles_Indication().first().shouldHave(Condition.text("Показатель."));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
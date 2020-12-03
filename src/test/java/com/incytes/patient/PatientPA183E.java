package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class PatientPA183E {
    Main main = new Main();
    Main.Login login = main.new Login();
    Main.Login.Home home = login.new Home();
    @Test
    public void c_test() {
        start("En","Chart key", "ABOUT GRAPH","MORE", "Treatment Description: Many importan", "Indication description: German rule began in mainland Tanzania durin");
        start("Fr","Chart key", "À PROPOS DE GRAPH","MORE", "Description Du Traitement: Fourche", "Fourche");
        start("De","Chart key", "ÜBER DIE SCHAUBILD","MORE", "Treatment Description: Many importan", "Die deutsche Herrschaft begann auf dem tansanischen Festland im späten 19. Jahrhundert, als Deutschland das deutsche Ostafrika gründete. Nach dem Ersten Weltkrieg fol");
        start("It","Chart key", "INFORMAZIONI SU GRAPH","MORE", "Descrizione Del Trattamento: Forcella", "Il dominio tedesco iniziò nella Tanzania continent");
        start("Ru","Chart key", "О ГРАФИКЕ","MORE", "Описание лечения: в Танзании было найдено много ва", "Описание показаний: немецкое правление началось в материковой Танзании в конце 19 век");
        start("Sp","Chart key", "ACERCA DE GRAPH","MORE", "Descripción del tratamiento: Tenedor. Muchos fósiles de homínidos importantes se han encontrado en T", "Tenedor");
    }
    private void start(String lang, String text1, String text2, String text3, String text4, String text5){
        Main.setLang(lang);
        login.open().setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password + "!").wLogin().signIn();
        $(".MuiTypography-root", 24).waitUntil(Condition.exist, 5000);
        home.eChartKey_MyResults().shouldHave(Condition.text(text1));
        home.eAboutGraphButton_MyResults().shouldHave(Condition.text(text2));
        home.eTextBodies_Treatment().first().shouldHave(Condition.text(text4));
        home.eTextBodies_Indication().first().shouldHave(Condition.text(text5));
        Selenide.close();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
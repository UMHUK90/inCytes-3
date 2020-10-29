package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class ClinicianR14B {
    @Test
    void opening(){
        Main main = new Main("En");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.setAll("", "", "Tkkjdsg@gmail.com", "Alpha77", "").clickNext();
        $("p.Mui-error").shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
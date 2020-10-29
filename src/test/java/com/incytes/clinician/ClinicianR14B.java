package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;

public class ClinicianR14B {
    @Test
    void opening(){
        Main main = new Main("En");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.setAll("", "", "Tkkjdsg@gmail.com", "Alpha77", "").clickNext();
        $("p.Mui-error").shouldBe(Condition.visible);
        close();
    }
}
package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianR16A {
    @Test
    public void opening(){
        Main main = new Main("En");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.setAll("NewNameForYou", "Your_s LastName", "andrew.grabovskiy+8@gmail.com", "Alpha54p", "Alpha54p").wRegistration().cRegistration();
        reg.clickNext();
        $(byText( "Account creation failed. Please try again. If this problem persists, please contact us at incytes@rgnmed.com")).shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
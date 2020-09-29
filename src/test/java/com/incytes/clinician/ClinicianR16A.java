package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR16A {
    @Test
    public void opening(){
        Main main = new Main("En");
        Main.Registration reg = main.new Registration("https://alpha.incytesdata-dev.com/auth/register");
        reg.open();
        reg.setAll("NewNameForYou", "Your_s LastName", "andrew.grabovskiy+8@gmail.com", "Alpha54p", "Alpha54p").wRegistration().cRegistration();
        reg.clickNext();
        $(byText( "Account creation failed. Please try again. If this problem persists, please contact us at incytes@rgnmed.com")).shouldBe(Condition.visible);
    }
}

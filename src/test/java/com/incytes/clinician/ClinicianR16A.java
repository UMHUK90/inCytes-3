package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR16A {
    @Test
    public void opening(){
        open("https://alpha.incytesdata-dev.com/auth/register");
        $(By.name("firstName")).setValue("NewNameForYou");
        $(By.name("lastName")).setValue("Your_s LastName");
        $(By.name("email")).setValue("andrew.grabovskiy+8@gmail.com");
        $(By.name("password")).setValue("Alpha54p");
        $(By.name("verifyPassword")).setValue("Alpha54p");

        //second step (checking for correct page)
        $(By.name("firstName")).shouldHave(Condition.value("NewNameForYou"));
        $(By.name("lastName")).shouldHave(Condition.value("Your_s LastName"));
        $(By.name("email")).shouldHave(Condition.value("andrew.grabovskiy+8@gmail.com"));
        $(By.name("password")).shouldHave(Condition.value("Alpha54p"));
        $(By.name("verifyPassword")).shouldHave(Condition.value("Alpha54p"));

        //next steps
        $(byAttribute("type", "checkbox")).click();
        $(".MuiButton-label").click();
        $(byText( "Account creation failed. Please try again. If this problem persists, please contact us at incytes@rgnmed.com")).shouldBe(Condition.visible);
    }
}

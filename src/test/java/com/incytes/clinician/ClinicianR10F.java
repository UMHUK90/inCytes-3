package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR10F {
    @Test
    public void method() {
        Main main = new Main("Fr");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.clickTerms();
        switchTo().window(1);
        Main.Registration.TermsAndConditions terms = reg.new TermsAndConditions();
        terms.eHeading().shouldHave(Condition.exactText("Termes et conditions"));
        terms.firstText().shouldHave(text("™"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
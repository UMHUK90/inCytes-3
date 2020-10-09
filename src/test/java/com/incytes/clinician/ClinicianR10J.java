package com.incytes.clinician;


import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianR10J {
    @Test
    public void method() {
        Main main = new Main("Sp");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.clickTerms();
        switchTo().window(1);
        Main.Registration.TermsAndConditions terms = reg.new TermsAndConditions();
        terms.eHeading().shouldHave(Condition.exactText("Términos y Condiciones"));
        terms.firstText().shouldHave(text("™"));
    }
}

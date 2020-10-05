package com.incytes.clinician;


import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianR10J {
    @Test
    public void method() {
        org.selenide.examples.Clinician.Main main = new org.selenide.examples.Clinician.Main("Sp");
        org.selenide.examples.Clinician.Main.Registration reg = main.new Registration();
        reg.open();
        reg.clickTerms();
        switchTo().window(1);
        org.selenide.examples.Clinician.Main.Registration.TermsAndConditions terms = reg.new TermsAndConditions();
        terms.eHeading().shouldHave(Condition.exactText("Términos y Condiciones"));
        terms.firstText().shouldHave(text("™"));
    }
}

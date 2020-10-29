package com.incytes.clinician;


import com.codeborne.selenide.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR10E {
    @Test
    public void method() {
        Main main = new Main("En");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.clickTerms();
        switchTo().window(1);
        Main.Registration.TermsAndConditions terms = reg.new TermsAndConditions();
        terms.eHeading().shouldHave(Condition.exactText("Terms and Conditions"));
        terms.firstText().shouldHave(text("™"));
        close();
    }
}
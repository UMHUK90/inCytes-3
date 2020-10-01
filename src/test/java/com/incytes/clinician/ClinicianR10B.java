package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR10B {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        $(".MuiButton-label", 1).shouldBe(visible); /* "Already Have an Account?" button  */
        reg.clickNext();
        $$(byText("Required")).shouldHave(size(5));
    }
}

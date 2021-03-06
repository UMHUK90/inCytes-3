package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
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
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianСO61E {
    @Test
    public void method(){
        String text = Main.randomText(5);
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        circles.clickCreateCircle();
        circles.eCircleName_Creation().setValue(text);
        circles.clickPHI();
        $(byText("PHI Circles share protected health information on the health status and identity of Circle patients. For more information, please review our Terms and Conditions.")).shouldBe(Condition.visible);
        circles.clickNonPHI();
        $(byText("Non-PHI Circles share aggregated, de-identified clinical data. For more information, please review our")).shouldBe(Condition.visible);
        circles.eTermsAndConditions_Creation().click();
        Selenide.switchTo().window(1);
        Main.Login.DashBoard.Circles.TermsAndConditions termsAndConditions = circles.new TermsAndConditions();
        termsAndConditions.eHeading().shouldHave(text("Terms And Conditions")).shouldBe(visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

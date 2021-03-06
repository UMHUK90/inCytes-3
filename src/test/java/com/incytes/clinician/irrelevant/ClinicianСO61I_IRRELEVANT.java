package com.incytes.clinician.irrelevant;

import com.codeborne.selenide.Selenide;
import com.incytes.clinician.Main;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianСO61I_IRRELEVANT {
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
        circles.clickSponsored();
        $(byText("Available")).shouldBe(visible);
        $(byText("Cases on plan")).shouldBe(visible);
        circles.eInputCases_Creation_Cases().setValue("300").shouldHave(value("300"));
        Main.clickOutSide(dashBoard.eProfile(), -50, -50);
        Main.muiError(1, 0).shouldHave(text("Must be less than"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

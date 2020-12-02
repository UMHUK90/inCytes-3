package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianСO61H {
    @Test
    public void method(){
        String text = Main.randomText(5);
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+5@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        circles.clickCreateCircle();
        circles.clickSponsored();
        $(byText("Available")).shouldBe(visible);
        $(byText("Cases on plan")).shouldBe(visible);
        circles.eInputCases_Creation_Cases().setValue("1").shouldHave(value("1"));
        circles.clickSponsored();
        circles.eInputCases_Creation_Cases().shouldNotBe(visible);
        circles.clickSponsored();
        circles.eInputCases_Creation_Cases().shouldHave(value(""));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

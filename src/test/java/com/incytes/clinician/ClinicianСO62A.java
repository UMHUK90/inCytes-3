package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ClinicianСO62A {
    @Test
    public void method(){
        String text = Main.randomText(5);
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        circles.eSearch().setValue("Common Protocol");
        circles.eListOfCircles().find(text("Common Protocol")).shouldBe(visible);
        circles.eSearch().setValue("Andrew6");
        circles.eListOfCircles().find(text("Andrew6")).shouldBe(visible);
        circles.eSearch().setValue("andrew.grabovskiy+6@gmail.com");
        circles.eListOfCircles().find(text("andrew.grabovskiy+6@gmail.com")).shouldBe(visible);
        circles.eSearch().setValue("Grabovskiy6");
        circles.eListOfCircles().find(text("Grabovskiy6")).shouldBe(visible);
        circles.eSearch().setValue("Test Protocol");
        circles.eListOfCircles().first().shouldBe(visible);
    }
}

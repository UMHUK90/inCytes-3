package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class ClinicianСO62C {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        String text = "hel 0 0 11/13/20 andrew.grabovskiy";
        circles.eSearch().setValue(text);
        for (String str:text.split(" ")) circles.eListOfCircles().first().shouldHave(text("str"));
    }
}

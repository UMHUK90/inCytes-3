package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class ClinicianСO61B {
    @Test
    public void method(){
        String text = Main.randomText(248) + "2◌́ ◌̛Ƕ";
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        circles.clickCreateCircle();
        circles.eCircleName_Creation().setValue(text).shouldHave(Condition.value(text));
        System.out.println(text.length());
    }
}

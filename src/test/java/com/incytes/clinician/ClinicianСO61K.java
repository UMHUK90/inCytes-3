package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ClinicianСO61K {
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
        circles.eObservationalProtocol_Creation().setValue("Common");
        circles.eListOfProtocols_Creation().find(text("Common Protocol")).shouldBe(visible);
        circles.clickCreateCircle_Creation();
        Main.haveRequired(2);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

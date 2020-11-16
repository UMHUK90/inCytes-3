package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianСO61С {
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
        circles.clickNonPHI();
        $(byText("Non-PHI Circles share aggregated, de-identified clinical data. For more information, please review our")).shouldBe(Condition.visible);
        circles.eProtocol_Creation().setValue("Common Protocol");
        circles.eListOfProtocols_Creation().find(text("Common Protocol")).click();
        circles.clickCreateCircle_Creation();
        circles.eNonPHI_Creation().shouldBe(Condition.not(Condition.visible));
        circles.eListOfCircles().last().shouldHave(text(text)).find(byTitle("Non-PHI"));
    }
}

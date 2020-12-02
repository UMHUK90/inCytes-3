package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianСO61A {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCircles();
        Main.Login.DashBoard.Circles circles = dashBoard.new Circles();
        circles.clickCreateCircle();
        circles.eTitle_Creation().shouldHave(Condition.text("Create Circle"));
        circles.eIndication_Creation();
        circles.ePHI_Creation().shouldBe(Condition.enabled);
        circles.eProtocol_Creation().shouldBe(Condition.enabled);
        circles.eTreatment_Creation().shouldBe(Condition.enabled);
        circles.eNonPHI_Creation().shouldBe(Condition.enabled);
        circles.eCircleName_Creation().shouldBe(Condition.enabled);
        circles.eSponsored_Creation().shouldBe(Condition.enabled);
        circles.eCreateCircle_Creation().shouldBe(Condition.enabled);
        circles.eTermsAndConditions_Creation().shouldBe(Condition.enabled);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}

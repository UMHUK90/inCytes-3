package com.incytes.patient.dirty;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.patient.Main;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class PatientDTPP_0002 {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("dqapatient2@yandex.ru", "261090inCytes", "+375295760648");
        invitation.clickInvitation();
        Main.Login login = main.new Login();
        switchTo().window(1);
        login.open().setAll("dqapatient2@yandex.ru", Main.password).wLogin().signIn();
        $(".MuiButton-containedSecondary").shouldBe(Condition.visible);
        $(".MuiCircularProgress-root").waitUntil(Condition.hidden, 8000);
        $(By.name("answers.multiple._1[1995]")).click();
        $(By.name("answers.single._3")).click();
        $(By.name("answers.single._5")).click();
        $(By.name("answers.single._7")).click();
        $(By.name("answers.single._10")).click();
        $(By.name("answers.single._14")).click();
        //$(".MuiButton-containedSecondary").click();
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

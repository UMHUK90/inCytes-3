package com.incytes.patient.dirty;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.patient.Main;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class PatientDTPP_0001 {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("dqapatient2@yandex.ru", "261090inCytes", "+375295760648");
        invitation.clickInvitation();
        Main.Login login = main.new Login();
        switchTo().window(1);
        login.open().setAll("dqapatient2@yandex.ru", Main.password).wLogin().signIn();
        $(".MuiButton-containedSecondary").shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA143A {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div/div[1]/div/button[3]/span[1]")).click();
        $(name("existingPassword")).shouldBe(visible);
        $(name("newPassword")).shouldBe(visible);
        $(name("confirmPassword")).shouldBe(visible);

        /*
        Main main = new Main();
        Main.FileTXT file = main.new FileTXT("D:\\Path\\alpha_patient_count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.SetRegFirstWindow("andrew.grabovskiy+alphapatient" + count + "@gmail.com","261090inCytes", "261090inCytes");
        reg.wRegistration().cRegistration().clickNext1();
        file.writeText(String.valueOf(count+1), false);
        reg.SetRegSecondWindow("Andrew" + count, "Grabovskiy" + count, "19901028", "Belarus", "7987654321");
        reg.wwRegistration().cwRegistration().clickNext2();
        Main.newTab();
        switchTo().window(1);
        open("https://alpha-patient.incytesdata-dev.com/");
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).click();
        $(name("existingPassword")).shouldBe(visible);
        $(name("newPassword")).shouldBe(visible);
        $(name("confirmPassword")).shouldBe(visible);
        */
    }
}
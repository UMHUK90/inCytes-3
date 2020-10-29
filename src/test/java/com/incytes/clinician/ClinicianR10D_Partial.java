package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR10D_Partial {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("Andrew", "Grabovskiy", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes", "261090inCytes").wRegistration().clickNext();
        $$("h4").findBy(text("Код подтверждения")).shouldBe(visible);
        file.writeText(String.valueOf(count+1), false);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
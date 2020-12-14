package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR20A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration("https://alpha.incytesdata-dev.com/auth/register");
        reg.open();
        reg.setAll("Dmitry", "Polsky", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!", "261090inCytes!");
        reg.wRegistration().cRegistration().clickNext();
        file.writeText(String.valueOf(count+1), false);
        $$("h4").findBy(text("Код подтверждения")).shouldBe(visible);
        $$("h4").findBy(text("Код подтверждения был успешно отправлен на вашу электронную почту. Пожалуйста, введите его ниже, чтобы закончить регистрацию.")).shouldBe(visible);
        $$("input").findBy(attribute("placeholder", "Код подтверждения")).shouldBe(visible);
        $$("span.MuiButton-label").findBy(text("SUBMIT")).shouldBe(visible);
        $$("span.MuiButton-label").findBy(text("Отправить код подтверждения ещё раз")).shouldBe(visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
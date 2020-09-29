package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class ClinicianR20A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration("https://qa.incytesdata-dev.com/auth/register");
        reg.open();
        reg.setAll("Dmitry", "Polsky", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!", "261090inCytes!");
        reg.wRegistration().cRegistration().clickNext();
        file.writeTest(String.valueOf(count+1), false);
        $$("h4").findBy(text("Код подтверждения")).shouldBe(visible);
        $$("h4").findBy(text("Код подтверждения был успешно отправлен на вашу электронную почту. Пожалуйста, введите код в соответствующее поле.")).shouldBe(visible);
        $$("input").findBy(attribute("placeholder", "Код подтверждения")).shouldBe(visible);
        $$("span.MuiButton-label").findBy(text("SUBMIT")).shouldBe(visible);
        $$("span.MuiButton-label").findBy(text("Отправить код подтверждения ещё раз")).shouldBe(visible);
    }
}

package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR20B {
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
        $$("span.MuiButton-label").findBy(text("Отправить код подтверждения ещё раз")).shouldBe(visible);
        $(".MuiButton-label").click();
        $$(byText("Required")).shouldHave(size(1));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
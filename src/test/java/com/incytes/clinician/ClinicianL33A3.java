package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.close;

public class ClinicianL33A3 {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.isVisible().setAll("andrew.grabovskiy@gmail.com","261090inCytes!!").wLogin().signIn();
        $$("p").findBy(text("Введён неверный адрес или пароль, пожалуйста, повторите попытку. Новые пользователи должны сначала зарегистрироваться.")).shouldBe(visible);
        close();
    }
}

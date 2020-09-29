package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class Main {
    /** Открывает новое окно */
    public static void newTab() { Selenide.executeJavaScript("window.open()"); }
    public class Registration {
        private String address;

        //All
        private String firstName = "";
        private String lastName = "";
        private String email = "";
        private String password = "";
        private String verifyPassword = "";

        public Registration (String address) {
            this.address = address;
            Selenide.open(address);
        }

        @Test     /** Открывает ссылку в настоящем окне */
        public void open() {
            Selenide.open(address);
        }

        /** Устанавливает параметры для регистрации */
        public Registration setAll (String firstName, String lastName, String email, String password, String verifyPassword) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.verifyPassword = verifyPassword;
            return this;
        }

        @Test     /** Вводит записанные данные на страницу регистрации / в случае их отсутствия введутся пустые строки */
        public Registration wRegistration() {
            $(By.name("firstName")).setValue(firstName);
            $(By.name("lastName")).setValue(lastName);
            $(By.name("email")).setValue(email);
            $(By.name("password")).setValue(password);
            $(By.name("verifyPassword")).setValue(verifyPassword);
            return this;
        }

        @Test       /** Проверяет на присутствие введённых данных (можно пропустить) */
        public Registration cRegistration() {
            $(By.name("firstName")).shouldHave(Condition.value(firstName));
            $(By.name("lastName")).shouldHave(Condition.value(lastName));
            $(By.name("email")).shouldHave(Condition.value(email));
            $(By.name("password")).shouldHave(Condition.value(password));
            $(By.name("verifyPassword")).shouldHave(Condition.value(verifyPassword));
            return this;
        }
        @Test /** Соглашается с лицензией и отправляет данные */
        public void clickNext() {
            $(byAttribute("type", "checkbox")).click();
            $(".MuiButton-label").click();
        }
        @Test     /** Вводит код и отправляет */
        public void submitCode (String code) {
            $(".MuiInputBase-input").setValue(code);
            $(".MuiButton-label").click();
        }
    }
}

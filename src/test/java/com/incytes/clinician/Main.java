package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;

/** Главный класс (контейнер) */
public class Main {
    public Main(String language){
        setLang(language);
    }
    public Main(){
        setLang("En");
    }

    /** Открывает новое окно */
    public static void newTab(){ Selenide.executeJavaScript("window.open()"); }
    /** Устанавливает язык браузера */
    public static void setLang(String language){
        switch (language){
            case "En": language = "en";
            break;
            case "Fr": language = "fr";
            break;
            case "Ru": language = "ru";
            break;
            case "Sp": language = "es";
            break;
            case "It" : language = "it";
            default: language = "en";
            break;
        }
        System.setProperty("chromeoptions.prefs","intl.accept_languages=" + language);
    }
    /** Работа с тескстовым файлом */
    public class FileTXT{
        private String path;
        public FileTXT(String path){
            this.path = path;
        }
        /** Возвращает текст из файла */
        public String getText(){
            String str = "";
            try(FileReader reader = new FileReader(path))
            {
                int c;
                while((c=reader.read())!=-1){
                    str += (char)c;
                }
            }
            catch(IOException ex){ System.out.println(ex.getMessage()); }
            return str;
        }
        /** Записывает текст в файл (Значение, false(перезаписывать файл), true(дозаписывает в конец файла)) */
        public void writeText(String str, Boolean append){
            try(FileWriter writer = new FileWriter(path, append)) { writer.write(str); }
            catch(IOException ex){ System.out.println(ex.getMessage()); }
        }
    }
    // Работа с регистрацией
    public class Registration {
        private String address = "https://alpha.incytesdata-dev.com/auth/register";
        public Registration(){}
        public Registration(String empty){} // Для уже написанных тестов
        //All
        private String firstName = "", lastName = "", email = "", password = "", verifyPassword = "";

             /** Открывает ссылку в настоящем окне */
        public void open() {
            Selenide.open(address);
        }

        /** Устанавливает параметры для регистрации */
        public Registration setAll(String firstName, String lastName, String email, String password, String verifyPassword) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.verifyPassword = verifyPassword;
            return this;
        }
             /** Вводит записанные данные на страницу регистрации / в случае их отсутствия введутся пустые строки */
        public Registration wRegistration() {
            $(name("firstName")).setValue(firstName);
            $(name("lastName")).setValue(lastName);
            $(name("email")).setValue(email);
            $(name("password")).setValue(password);
            $(name("verifyPassword")).setValue(verifyPassword);
            return this;
        }

               /** Проверяет на присутствие введённых данных (можно пропустить) */
        public Registration cRegistration() {
            $(name("firstName")).shouldHave(Condition.value(firstName));
            $(name("lastName")).shouldHave(Condition.value(lastName));
            $(name("email")).shouldHave(Condition.value(email));
            $(name("password")).shouldHave(Condition.value(password));
            $(name("verifyPassword")).shouldHave(Condition.value(verifyPassword));
            return this;
        }
         /** Соглашается с лицензией и отправляет данные */
        public void clickNext() {
            $(byAttribute("type", "checkbox")).click();
            $(".MuiButton-label").click();
        }
             /** Вводит код и отправляет */
        public void submitCode(String code) {
            $(".MuiInputBase-input").setValue(code);
            $(".MuiButton-label").click();
        }
        public void resendCode() {
            $("span.MuiButton-label", 1).click();
            $(byAttribute("var","body1")).waitUntil(visible, 6000);
        }
    }
    /** Класс предназначен для работы с формой входа */
    public class Login{
        private String address = "https://alpha.incytesdata-dev.com/auth/login";
        private String email = "", password = "";
        /** Открывает страницу входа */
        public void open(){
            Selenide.open(address);
        }
        /** Устанавливает для класса email и password */
        public Login setAll(String email, String password){
            this.email = email;
            this.password = password;
            return this;
        }
        /** Вводит пароль и логин в форму */
        public Login wLogin(){
            $(name("email")).setValue(email);
            $(name("password")).setValue(password);
            return this;
        }
        /** Проверяет видны ли элементы формы */
        public Login isVisible(){
            $(".MuiTypography-root").shouldBe(visible);
            $(name("email")).shouldBe(visible);
            $(name("password")).shouldBe(visible);
            $(".MuiButtonBase-root", 0).shouldBe(visible);
            $(".MuiButton-label", 1).shouldBe(visible);
            $(".MuiButton-label", 2).shouldBe(visible);
            return this;
        }
        /** Производит вход */
        public void signIn(){
            $(".MuiButton-label", 2).click();
        }
        /** Производит переход н астраницу подтверждения пароля */
        public void forgotPassword(){
            $(".MuiButtonBase-root", 0).click();
        }
    }
    /** Предназначен для получения кода для верификации */
    public class GetCodeWithYandex{
        private String email, password, phone, code;
        /** Возвращает код (Если он уже получен, иначе вернётся пустая строка)*/
        public String getCode() { if(code != null) return code; return "";}
        public GetCodeWithYandex(String email, String password, String phone){
            this.email = email;
            this.password = password;
            this.phone = phone;
            open("https://passport.yandex.by/auth");
        }
        /** Возвращает код с Яндекса*/
        public String fastCode(){
            enter();
            return lastCode();
        }
        private void enter(){
            $(name("login")).setValue(email);
            $(byAttribute("type", "submit")).click();
            $(byAttribute("type", "password")).setValue(password);
            $(byAttribute("type", "submit")).click();
            if($(byAttribute("type", "tel")).exists()) {$(byAttribute("type", "tel")).setValue(phone);
                $(byAttribute("type", "submit")).click();}
        }
        private String lastCode(){
            $(".user-account__name").click(); //
            $(byAttribute("href", "https://mail.yandex.by")).click();
            $$(byText("Your verification code to inCytes™")).first().click();
            String code;
            if(!$(".mail-Message-Body-Content").exists()) code = $(".mail-MessageSnippet-Item_firstline").closest("span").getText().substring(64).replace(".", "");
            else code = $(".mail-Message-Body-Content").getText().substring(64).replace(".", "");
            this.code = code;
            return code;
        }
    }
}

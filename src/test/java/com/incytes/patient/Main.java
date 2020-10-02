package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;

/** Главный класс (контейнер) */
public class Main {
    private String baddress = "https://qa-patient.incytesdata-dev.com/";
    public Main(String language){
        setLang(language);
    }
    public Main(){
        setLang("En");
    }

    public static void muiError(int size){
        $$("p.Mui-error").shouldHave(size(size));
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

        //Элементы Регистрации
        public SelenideElement firstName(){ return $(name("firstName")); }
        public SelenideElement lastName(){ return  $(name("lastName"));}
        public SelenideElement email(){ return $(name("email")); }
        public SelenideElement password(){ return $(name("password")); }
        public SelenideElement confirmPassword(){ return $(name("confirmPassword")); }
        public SelenideElement birthDate(){ return $(name("birthDate")); }
        public SelenideElement countryName(){ return $(name("countryName")); }
        public SelenideElement phoneNumber(){ return $(name("phoneNumber")); }
        public SelenideElement checkBox1(){return $(byAttribute("type", "checkbox"), 0);}
        public SelenideElement checkBox2(){return $(byAttribute("type", "checkbox"), 1);}
        public SelenideElement getStarted(){ return $("button"); }
        public SelenideElement login(){return $(".MuiButton-sizeLarge");}


        private String address = baddress + "auth/register/";
        public Registration(){}
        public Registration(String empty){} // Для уже написанных тестов
        //All
        public String firstName = "", lastName = "", email = "", password = "", verifyPassword = "", date = "", country = "", phone = "";

             /** Открывает ссылку в настоящем окне */
             public void open(){Selenide.open(address);}
        public void open(String path) {
            Selenide.open(address + path);
        }

        /** Устанавливает параметры для регистрации */
        public Registration setAll(String email, String password, String verifyPassword, String firstName, String lastName, String date, String country, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.verifyPassword = verifyPassword;
            this.date = date;
            this.country = country;
            this.phone = phone;
            return this;
        }
             /** Вводит записанные данные на страницу регистрации / в случае их отсутствия введутся пустые строки */
        public Registration wRegistration() {
            email().setValue(email);
            password().setValue(password);
            confirmPassword().setValue(verifyPassword);
            return this;
        }
        public Registration wwRegistration(){
            firstName().setValue(firstName);
            lastName().setValue(lastName);
            birthDate().setValue(date);
            countryName().setValue(country);
            $(".MuiListItem-root").click();
            phoneNumber().setValue(phone);
            return this;
        }
               /** Проверяет на присутствие введённых данных (можно пропустить) */
        public Registration cRegistration() {
            email().shouldHave(Condition.value(email));
            password().shouldHave(Condition.value(password));
            confirmPassword().shouldHave(Condition.value(verifyPassword));
            return this;
        }
        public Registration cwRegistration() {
            firstName().shouldHave(value(firstName)).shouldBe(visible);
            lastName().shouldHave(value(lastName)).shouldBe(visible);
            birthDate().shouldHave(value(date)).shouldBe(visible);
            countryName().shouldHave(value(country)).shouldBe(visible);
            phoneNumber().shouldHave(value(phone)).shouldBe(visible);
            checkBox1().should(exist);
            checkBox2().should(exist);
            getStarted().shouldBe(visible);
            return this;
        }
         /** Соглашается с лицензией и отправляет данные */
        public void clickLogin() {
            login().click();
        }
        public void clickNext(){
            checkBox1().click();
            checkBox2().click();
            getStarted().click();
        }
        public void clickCheckBox(){
            checkBox1().click();
            checkBox2().click();
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
    //Тестовый класс
    public static class Verification{
        /** Открывает страницу с вставкой кода */
        public static void open(String email){
             Selenide.open("https://alpha.incytesdata-dev.com/auth/register/confirmation?email=" + email);
        }
        /** Записывает код */
        public void writeCode(String code){
            $(".MuiInputBase-input").setValue(code);
        }
        /** Отправляет код */
        public void submitCode(){
            $(".MuiButton-label").click();
        }
        /** Видно ли поле для кода */
        public void inputIsVisible(){
            $(".MuiInputBase-input").shouldBe(visible);
        }
        /** Видна ли кнопка для отправки кода */
        public void submitIsVisible(){
            $(".MuiButton-label").shouldBe(visible);
        }
        public void clickResend(){
            $(".MuiButton-label", 1).click();
        }
        public void resendIsVisible(){
            $(".MuiButton-label", 1).shouldBe(visible);
        }
    }
    public static String password = "261090inCytes";
}

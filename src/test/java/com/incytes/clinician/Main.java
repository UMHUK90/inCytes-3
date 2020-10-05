package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;

/** Главный класс (контейнер) */
public class Main {
    private String baddress = "https://qa.incytesdata-dev.com/";
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
            break;
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
        //Элементы страницы
        public SelenideElement eFirstName(){ return $(name("firstName"));}
        public SelenideElement eLastName(){ return $(name("lastName"));}
        public SelenideElement eEmail(){ return $(name("email"));}
        public SelenideElement ePassword(){ return $(name("password"));}
        public SelenideElement eVerifyPassword(){ return $(name("verifyPassword"));}
        public SelenideElement eNext(){ return $(".MuiButton-label");}
        public SelenideElement eCheckBox(){ return $(byAttribute("type", "checkbox"));}
        public SelenideElement eInputCode(){return $(".MuiInputBase-input");}
        public SelenideElement eSubmitCode(){ return $(".MuiButton-label");}
        public SelenideElement eHaveAnAccount(){return $(".MuiButton-fullWidth", 1);}
        public SelenideElement eTermsAndConditions(){return $(".MuiTypography-body2");}

        private String address = baddress + "auth/register";
        public Registration(){}
        public Registration(String empty){} // Для уже написанных тестов
        //All
        public String firstName = "", lastName = "", email = "", password = "", verifyPassword = "";

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
            eFirstName().setValue(firstName);
            eLastName().setValue(lastName);
            eEmail().setValue(email);
            ePassword().setValue(password);
            eVerifyPassword().setValue(verifyPassword);
            return this;
        }

               /** Проверяет на присутствие введённых данных (можно пропустить) */
        public Registration cRegistration() {
            eFirstName().shouldHave(Condition.value(firstName));
            eLastName().shouldHave(Condition.value(lastName));
            eEmail().shouldHave(Condition.value(email));
            ePassword().shouldHave(Condition.value(password));
            eVerifyPassword().shouldHave(Condition.value(verifyPassword));
            return this;
        }
         /** Соглашается с лицензией и отправляет данные */
        public void clickNext() {
            eCheckBox().click();
            eNext().click();
        }
             /** Вводит код и отправляет */
        public void submitCode(String code) {
            eInputCode().setValue(code);
            eSubmitCode().click();
        }
        public void resendCode() {
            $("span.MuiButton-label", 1).click();
            $(byAttribute("var","body1")).waitUntil(visible, 6000);
        }
        public void haveNextButton() {
            eNext().shouldBe(visible);
        }
        public void clickTCradio() {
            eCheckBox().click();
        }
        public void clickHaveAnAccount(){ eHaveAnAccount().click();}
        public void haveAnAccount(){ eHaveAnAccount().shouldBe(visible); }
        public void haveFourRequired(){  $$(byText("Required")).shouldHave(size(4)); }
        public void clickTerms(){ eTermsAndConditions().click(); }

        public class TermsAndConditions{
            public SelenideElement eHeading(){return $(".MuiTypography-h3");}
            public ElementsCollection eLinks(){ return $$("a"); }
            public SelenideElement firstText(){return $(".MuiTypography-body2");}
        }
    }
    /** Класс предназначен для работы с формой входа */
    public class Login{
        //Элементы страницы
        public SelenideElement eEmail(){return $(name("email"));}
        public SelenideElement ePassword(){return $(name("password"));}
        public SelenideElement eSignIn(){return $(".MuiButton-label", 2) ;}
        public SelenideElement eForgotPassword(){return $(".MuiButtonBase-root", 0);}
        public SelenideElement eSignUp(){return $(".MuiButton-label", 1);}


        private String address = baddress + "auth/login";
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
            eEmail().setValue(email);
            ePassword().setValue(password);
            return this;
        }
        /** Проверяет видны ли элементы формы */
        public Login isVisible(){
            $(".MuiTypography-root").shouldBe(visible);
            eEmail().shouldBe(visible);
            ePassword().shouldBe(visible);
            eForgotPassword().shouldBe(visible);
            eSignUp().shouldBe(visible);
            eSignIn().shouldBe(visible);
            return this;
        }
        /** Производит вход */
        public void signIn(){
            eSignIn().click();
        }
        /** Производит переход н астраницу подтверждения пароля */
        public void forgotPassword(){
            eForgotPassword().click();
        }
    }
    /** Предназначен для получения кода для верификации */
    public class GetCodeWithYandex{

        public SelenideElement eLastSender(){ return $(".mail-MessageSnippet-FromText"); }
        public SelenideElement eLastTitle(){ return $(".mail-MessageSnippet-Item_body"); }
        public SelenideElement eLastText(){ return $(".mail-MessageSnippet-Item_firstline"); }

        private String email, password, phone, code;
        /** Возвращает код (Если он уже получен, иначе вернётся пустая строка)*/
        public String getCode() { if(code != null) return code; return "";}
        public GetCodeWithYandex checkMessage(){
            enter();
            eLastSender().shouldHave(text("no-reply@verificationemail.com"));
            eLastTitle().shouldHave(text("Your verification code to inCytes™")); // Не соответствует требованиям
            eLastText().shouldHave(text("Your verification code is"));
            return this;
        }
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
            $(".user-account__name").click(); //
            $(byAttribute("href", "https://mail.yandex.by")).click();
        }
        private String lastCode(){
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
            open("https://qa.incytesdata-dev.com/auth/register/confirmation?email=" + email);
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
}

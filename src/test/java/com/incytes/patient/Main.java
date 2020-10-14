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
import static org.openqa.selenium.By.xpath;

/** Главный класс (контейнер) */
public class Main {
    private String baddress = "https://alpha-patient.incytesdata-dev.com/";
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
            case "It": language = "it";
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
    //All

    // Работа с регистрацией
    public class Registration {

        //Элементы Регистрации
        public SelenideElement eFirstName(){ return $(name("firstName")); }
        public SelenideElement eLastName(){ return  $(name("lastName"));}
        public SelenideElement eEmail(){ return $(name("email")); }
        public SelenideElement ePassword(){ return $(name("password")); }
        public SelenideElement eConfirmPassword(){ return $(name("confirmPassword")); }
        public SelenideElement eBirthDate(){ return $(name("birthDate")); }
        public SelenideElement eCountryName(){ return $(name("countryName")); }
        public SelenideElement ePhoneNumber(){ return $(name("phoneNumber")); }
        public SelenideElement eCheckBox1(){return $(byAttribute("type", "checkbox"), 0);}
        public SelenideElement eCheckBox2(){return $(byAttribute("type", "checkbox"), 1);}
        public SelenideElement eGetStarted(){ return $("button"); }
        public SelenideElement eLogin(){return $(".MuiButton-sizeLarge");}


        private String address = baddress + "auth/register/";
        public Registration(){}
        public Registration(String empty){} // Для уже написанных тестов

             /** Открывает ссылку в настоящем окне */
             public void open(){Selenide.open(address);}
        public void open(String path) {
            Selenide.open(address + path);
        }
        //All
        public String firstName = "", lastName = "", email = "", password = "", verifyPassword = "", date = "", country ="", phone = "";
        /** Устанавливает параметры для регистрации */
        public Registration setAll(String email, String password, String verifyPassword, String firstName, String lastName, String date, String country, String phone) {
            this.email = email;
            this.password = password;
            this.verifyPassword = verifyPassword;
            this.firstName = firstName;
            this.lastName = lastName;
            this.date = date;
            this.country = country;
            this.phone = phone;
            return this;
        }
        public Registration SetRegFirstWindow(String email, String password, String verifyPassword) {
            this.email = email;
            this.password = password;
            this.verifyPassword = verifyPassword;
            return this;
        }
        public Registration SetRegSecondWindow(String firstName, String lastName, String date, String country, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.date = date;
            this.country = country;
            this.phone = phone;
            return this;
        }
             /** Вводит записанные данные на страницу регистрации / в случае их отсутствия введутся пустые строки */
        public Registration wRegistration() {
            eEmail().setValue(email);
            ePassword().setValue(password);
            eConfirmPassword().setValue(verifyPassword);
            return this;
        }
        public Registration wwRegistration(){
            eFirstName().setValue(firstName);
            eLastName().setValue(lastName);
            eBirthDate().setValue(date);
            eCountryName().setValue(country);
            ePhoneNumber().setValue(phone);
            $(".MuiListItem-root").click();
            return this;
        }
               /** Проверяет на присутствие введённых данных (можно пропустить) */
        public Registration cRegistration() {
            eEmail().shouldHave(Condition.value(email));
            ePassword().shouldHave(Condition.value(password));
            eConfirmPassword().shouldHave(Condition.value(verifyPassword));
            return this;
        }
        public Registration cwRegistration() {
            eFirstName().shouldHave(value(firstName)).shouldBe(visible);
            eLastName().shouldHave(value(lastName)).shouldBe(visible);
            eBirthDate().shouldHave(value(date)).shouldBe(visible);
            eCountryName().shouldHave(value(country)).shouldBe(visible);
            ePhoneNumber().shouldHave(value(phone)).shouldBe(visible);
            eCheckBox1().should(exist);
            eCheckBox2().should(exist);
            eGetStarted().shouldBe(visible);
            return this;
        }
         /** Соглашается с лицензией и отправляет данные */
        public void clickLogin() {
            eLogin().click();
        }
        public void clickNext1(){

        }
        public void clickNext2(){
            eCheckBox1().click();
            eCheckBox2().click();
            eGetStarted().click();
        }
        public void clickCheckBox(){
            eCheckBox1().click();
            eCheckBox2().click();
        }
    }

    public class Login {
        public SelenideElement eEmail(){return $(name("email"));}
        public SelenideElement ePassword(){return $(name("password"));}
        public SelenideElement eLogin(){return $(".MuiButton-sizeLarge");}
        public SelenideElement eSignIn(){return $(".MuiButton-label", 1) ;}
        public SelenideElement eForgotPassword(){return $(".MuiButtonBase-root", 0);}
        private String address = baddress + "auth/login";
        public String email = "", password = "", newPassword = "", confirmNewPassword = "";
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
        /** Проверяет видны ли элементы формы */
        public Login isVisible(){
            $(".MuiTypography-root").shouldBe(visible);
            eEmail().shouldBe(visible);
            ePassword().shouldBe(visible);
            eSignIn().shouldBe(visible);
            eForgotPassword().shouldBe(visible);
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
        /** Вводит пароль и логин в форму */
        public Login wLogin(){
            eEmail().setValue(email);
            ePassword().setValue(password);
            return this;
        }
        public class Portal {
            public void isThisLanguage(String language){
                switch (language){
                    case "En": $(".MuiTypography-h5").shouldHave(text("Patient Portal"));
                        break;
                    case "Fr": $(".MuiTypography-h4").shouldHave(text("Bienvenue chez inCytes!"));
                        break;
                    case "Ru": $(".MuiTypography-h4").shouldHave(text("Добро пожаловать в InСytes!"));
                        break;
                    case "Sp": $(".MuiTypography-h4").shouldHave(text("¡Bienvenido a inCytes!"));
                        break;
                    case "It" : $(".MuiTypography-h4").shouldHave(text("Benvenuto in inCytes!"));
                        break;
                    case "Ge" : $(".MuiTypography-h4").shouldHave(text("German!"));
                        break;
                }}
            public SelenideElement eChangePassword(){ return $(xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div/div[1]/div/button[3]/span[1]")); }
            public SelenideElement eExistingPassword(){ return $(name("existingPassword")); }
            public SelenideElement eNewPassword(){ return $(name("newPassword")); }
            public SelenideElement eConfirmNewPassword(){ return $(name("confirmPassword")); }
            public SelenideElement eSavePassword(){ return $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div[5]/button")); }
            public SelenideElement eLogout(){ return $(xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div/div[1]/div/button[4]/span[1]")); }

            public void clickChangePassword(){ eChangePassword().click(); }
            public void clickSavePassword(){ eSavePassword().click(); }
            public void clickLogout(){ eLogout().click(); }
            public String email = "", password = "", newPassword = "", confirmNewPassword = "";
            public Portal setPassword(String password, String newPassword, String confirmNewPassword) {
                this.password = password;
                this.newPassword = newPassword;
                this.confirmNewPassword = confirmNewPassword;
                return this;
            }
            public Portal wPassword() {
                eExistingPassword().setValue(password);
                eNewPassword().setValue(newPassword);
                eConfirmNewPassword().setValue(confirmNewPassword);
                return this;
            }
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
             Selenide.open("https://qa.incytesdata-dev.com/auth/register/confirmation?email=" + email);
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

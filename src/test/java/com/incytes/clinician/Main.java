package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Main {
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
        public void writeTest(String str, Boolean append){
            try(FileWriter writer = new FileWriter(path, append)) { writer.write(str); }
            catch(IOException ex){ System.out.println(ex.getMessage()); }
        }
    }
    /** Открывает новое окно */
    public static void newTab(){ Selenide.executeJavaScript("window.open()"); }
    public class Registration {
        private String adress;

        //All
        private String firstName = "";
        private String lastName = "";
        private String email = "";
        private String password = "";
        private String verifyPassword = "";

        public Registration(String adress) {
            this.adress = adress;
            Selenide.open(adress);
        }

             /** Открывает ссылку в настоящем окне */
        public void open() {
            Selenide.open(adress);
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
            $(By.name("firstName")).setValue(firstName);
            $(By.name("lastName")).setValue(lastName);
            $(By.name("email")).setValue(email);
            $(By.name("password")).setValue(password);
            $(By.name("verifyPassword")).setValue(verifyPassword);
            return this;
        }

               /** Проверяет на присутствие введённых данных (можно пропустить) */
        public Registration cRegistration() {
            $(By.name("firstName")).shouldHave(Condition.value(firstName));
            $(By.name("lastName")).shouldHave(Condition.value(lastName));
            $(By.name("email")).shouldHave(Condition.value(email));
            $(By.name("password")).shouldHave(Condition.value(password));
            $(By.name("verifyPassword")).shouldHave(Condition.value(verifyPassword));
            return this;
        }
         /** Соглашается с лицензией и отправляет данные */
        public void clickNext() {
            $(byAttribute("type", "checkbox")).click();
            $(".MuiButton-label").click();
        }
             /** Вводит код и отправляет */
        public void submitCode(String code){
            $(".MuiInputBase-input").setValue(code);
            $(".MuiButton-label").click();
        }
    }
    public class GetCodeWithYandex{
        private String email;
        private String password;
        private String phone;
        private String code;
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
            $(By.name("login")).setValue(email);
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

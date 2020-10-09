package com.incytes.clinician;

import com.codeborne.selenide.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static org.openqa.selenium.By.name;

/** Главный класс (контейнер) */
public class Main {
    private String baddress = "https://alpha.incytesdata-dev.com/";
    public Main(String language){
        setLang(language);
    }
    public Main(){
        setLang("En");
    }
    public static void close(){
        closeWebDriver();
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
            case "Ge" : language = "ge";
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
            if(!eEmail().is(disabled)) eEmail().setValue(email);
            ePassword().setValue(password);
            eVerifyPassword().setValue(verifyPassword);
            return this;
        }

               /** Проверяет на присутствие введённых данных (можно пропустить) */
        public Registration cRegistration() {
            eFirstName().shouldHave(Condition.value(firstName));
            eLastName().shouldHave(Condition.value(lastName));
            if(!eEmail().is(disabled)) eEmail().shouldHave(Condition.value(email));
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
        public SelenideElement ebackToLogin_forgotPassword() { return $("a"); }
        public void backToLoginShouldBe_forgotPassword(){ ebackToLogin_forgotPassword().shouldHave(text("Back to Login")); }

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
        public class DashBoard{

            public void isThisLanguage(String language){
                switch (language){
                case "En": $(".MuiTypography-h4").shouldHave(text("Welcome to inCytes!"));
                break;
                case "Fr": $(".MuiTypography-h4").shouldHave(text("Bienvenue chez inCytes!"));
                break;
                case "Ru": $(".MuiTypography-h4").shouldHave(text("Добро пожаловать в InСytes!"));
                break;
                case "Sp": $(".MuiTypography-h4").shouldHave(text("¡Bienvenido a inCytes!"));
                break;
                case "It" : $(".MuiTypography-h4").shouldHave(text("Benvenuto in inCytes!"));
                break;
            }}

            public SelenideElement eSearch(){ return $(".MuiGrid-align-items-xs-center", 0).parent().parent().parent(); }
            public SelenideElement eNewCase(){ return $(".MuiGrid-align-items-xs-center", 1).parent().parent().parent(); }
            public SelenideElement eReports(){ return $(".MuiTypography-body2", 4).parent().parent().parent(); }
            public SelenideElement eCircles(){ return $(".MuiTypography-body2", 5).parent().parent().parent(); }
            public SelenideElement ePatients(){ return $(".MuiTypography-body2", 6).parent().parent().parent(); }
            public SelenideElement eSupport(){ return $(".MuiGrid-align-items-xs-center", 2).parent().parent().parent(); }
            public SelenideElement eAccount(){ return $(".MuiGrid-align-items-xs-center", 3).parent().parent().parent(); }
            public SelenideElement eProfile(){ return $(".MuiGrid-align-items-xs-center", 4).parent().parent().parent(); }
            public SelenideElement eLogout(){ return $(".MuiGrid-align-items-xs-center", 5).parent().parent().parent(); }

            public void clickSearch(){ eSearch().click(); }
            public void clickNewCase(){ eNewCase().click(); }
            public void clickReports(){ eReports().click(); }
            public void clickCircles(){ eCircles().click(); }
            public void clickPatients(){ ePatients().click(); }
            public void clickSupport(){ eSupport().click(); }
            public void clickAccount(){ eAccount().click(); }
            public void clickProfile(){ eProfile().click(); }
            public void clickLogout(){ eLogout().click(); }

            public class Profile{
                public SelenideElement eEDIT(){ return $(".MuiTypography-body1", 0); }
                public SelenideElement eChangePassword(){ return $(".MuiTypography-body1", 1); }
                public SelenideElement eInvite(){ return $(".MuiTypography-body1", 5); }
                public SelenideElement eI_Invite(){ return $((".MuiFilledInput-input")); }
                public SelenideElement eSendInvitation(){ return $(".MuiButton-label"); }
                public SelenideElement eInvitationSent() { return $((".MuiSnackbarContent-message"));}

                public void clickEDIT(){ eEDIT().click(); }
                public void clickChangePassword(){ eChangePassword().click(); }
                public void clickInvite(){ eInvite().click(); }

                String text;
                public Profile writeEmail(String text){ eI_Invite().setValue(text); this.text = text; return  this; }
                public Profile clickSendInvitation(){ eSendInvitation().click(); return this;}
                public Profile checkingInvitationForm(){
                    if(text != null) eI_Invite().shouldHave(value(text)).shouldBe(visible);
                    eSendInvitation().shouldBe(visible);
                    return this;
                }
                public Profile isInvitationSent(){ eInvitationSent().shouldBe(visible); return this; }
            }
            public class Circles{
                public SelenideElement eAllCircles(){ return $(".MuiInput-formControl"); }
                public SelenideElement eSearch(){ return $(".MuiInputBase-inputAdornedStart"); }
                public SelenideElement eCreateCircle(){ return $(".MuiButton-contained"); }
                public SelenideElement eCircleName_Creation(){ return $(".MuiFilledInput-input"); }
                public SelenideElement eNonPHI_Creation(){ return $(byAttribute("name", "phi"));}
                public SelenideElement ePHI_Creation(){ return $(byAttribute("name", "phi"), 1);}
                public SelenideElement eSponsored_Creation(){ return $(byAttribute("type", "checkbox"));}
                public SelenideElement eProtocol_Creation(){ return $(".MuiFilledInput-input", 1); }
                public SelenideElement eTreatment_Creation(){ return $(".MuiFilledInput-input", 2); }
                public SelenideElement eIndication_Creation(){ return $(".MuiFilledInput-input", 3); }
                public SelenideElement eLastCircle(){ return $(byAttribute("style", "display: block; text-decoration: none; font-weight: bold; font-size: 17px; line-height: 20px; letter-spacing: 0.16px; color: rgb(1, 16, 32); margin-bottom: 5px;")); }

                public Boolean isNoCircles(){ if($(".MuiTypography-h4").has(text("MuiTypography-h4"))) return true; return false; }
                public void writeCircleName(String name){ eCircleName_Creation().setValue(name); }
                public void clickNonPHI(){ eNonPHI_Creation().click(); }
                public void clickPHI(){ ePHI_Creation().click(); }
                public void clickSponsored(){eSponsored_Creation().click();}
                public void writeProtocol(String name){ eProtocol_Creation().setValue(name); }
                public void writeTreatment(String name){ eTreatment_Creation().setValue(name); }
                public void writeIndication(String name){ eIndication_Creation().setValue(name); }
                public void clickLastCircle(){ eLastCircle().click(); }
                public class Circle{
                    public SelenideElement eInvite() { return $(".MuiIconButton-label", 1); }
                    public SelenideElement eEmail_Invite(){ return $(".MuiFilledInput-input"); }
                    public ElementsCollection eErrors_Invite(){ return $$(".Mui-error"); }
                    public ElementsCollection eCircleMembers() { return $$(".MuiTableRow-root"); }
                    //public SelenideElement eSponsor_Invite(){ return $(""); }
                    public SelenideElement eSendInvitation_Invite(){ return $(byAttribute("type", "submit")); }

                    public void clickInvite() { eInvite().click();}
                    public void writeEmail_Invite(String email){ eEmail_Invite().setValue(email); }
                    public void clickSendInvitation_Invite(){ eSendInvitation_Invite().click(); }
                    public void notHaveErrors(){ eErrors_Invite().shouldHave(size(0)); }
                    public void haveCircleMember(String email){ eCircleMembers().findBy(text(email)).exists(); }
                }
            }
        }
    }
    /** Предназначен для получения кода для верификации */
    public class GetCodeWithYandex{

        public SelenideElement eLastSender(){ return $(".mail-MessageSnippet-FromText"); }
        public SelenideElement eLastTitle(){ return $(".mail-MessageSnippet-Item_body"); }
        public SelenideElement eLastText(){ return $(".mail-MessageSnippet-Item_firstline"); }
        public SelenideElement eLastCheckBox(){ return $(".mail-MessageSnippet-Checkbox-Nb", 1); }
        public SelenideElement eForward(){ return $(".ns-view-toolbar-button-forward"); }

        public void clickLastTitle(){eLastTitle().click();}
        public void clickLastCheckBox(){ eLastCheckBox().click(); }
        public void clickForward(){ eForward().click(); }

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
    public class GetInvitationWithYandex extends GetCodeWithYandex {

        public SelenideElement eForwardLink() {
            return $$("a").findBy(attribute("data-cke-saved-href"));
        }

        public SelenideElement eSimpleLink() {
            return $(".daria-goto-anchor");
        }

        public void clickForwardLink() {
            eForwardLink().click();
        }

        public GetInvitationWithYandex(String email, String password, String phone) {
            super(email, password, phone);
        }

        public void clickInvitation() {
            super.enter();
            if ($(".mail-MessageSnippet-Item_threadExpand").parent().getText().equals($(".mail-MessageSnippet-Item_subjectWrapper").getText())) {
                int size;
                while(true) if($$(".mail-MessageSnippet-Checkbox-Nb").size() > 0) { size = $$(".mail-MessageSnippet-Checkbox-Nb").size(); break;}
                super.clickLastTitle();
                while (true) {
                    if ($$(".mail-MessageSnippet-Checkbox-Nb").size() > size && eLastCheckBox().is(enabled)) {
                        super.clickLastCheckBox();
                        break;
                    }
                    sleep(50);
                }
                while(true) { if(eLastCheckBox().has(attribute("id"))) { super.clickForward(); break; } sleep(50); }
                clickForwardLink();
            } else {
                super.clickLastTitle();
                while (true) {
                    if (eSimpleLink().is(enabled)) {
                        eSimpleLink().click();
                        break;
                    }
                    sleep(50);
                }
            }
        }
    }
    //Тестовый класс
    public static class Verification{
        /** Открывает страницу с вставкой кода */
        public static void open(String email){
            open("https://alpha.incytesdata-dev.com/auth/register/confirmation?email=" + email);
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

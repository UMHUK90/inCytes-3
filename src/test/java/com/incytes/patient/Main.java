package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

/** Главный класс (контейнер) */
public class Main {
    public interface IMethod{
        @Test
        void method();
    }
    private String baddress = "https://alpha-patient.incytesdata-dev.com/";
    public Main(String language){
        setLang(language);
    }
    public Main(){
        setLang("En");
    }
    public static String currentPage(){
        return WebDriverRunner.url();
    }
    public static void clickOutSide(SelenideElement elementOutSide, int x, int y){ Selenide.actions().moveToElement(elementOutSide, x, y).click().build().perform(); }
    public static String randomText(int count){
        String text = "";
        Random rand = new Random();
        char[] mass = { 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for(int step = 0; step < count; step++) text+= mass[rand.nextInt(26)];
        return text;
    }
    public static void muiError(int size){
        $$("p.Mui-error").shouldHave(size(size));
    }
    public static SelenideElement eBottomMessage(){ return $(".MuiSnackbarContent-root"); }
    public static SelenideElement muiError(int size, int number){
        $$("p.Mui-error").shouldHave(size(size));
        return $("p.Mui-error", number);
    }
    public static void haveRequired(int size){  $$(byText("Required")).shouldHave(size(size)); }
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
    public class MultipleMethods{
        private class List<T>{
            Value[] mass = new Value[0];
            public void Add(T value){
                Value[] instead = new Value[mass.length+1];
                for(int step = 0; step < mass.length; step++) instead[step] = mass[step];
                mass = instead;
                mass[mass.length-1] = new Value(value);
            }
            public int length(){
                return mass.length;
            }
            public T getValue(int number){
                return (T) mass[number].get();
            }
            class Value<T>{
                T value;
                public Value(T value){
                    this.value = value;
                }
                public T get(){
                    return value;
                }
            }
        }
        private List<String> list = new List<>();
        public MultipleMethods open(IMethod method, String name){
            try {
                method.method();
            } catch (Throwable e) {
                list.Add(name + "   -   " + e);
            }
            Selenide.closeWebDriver();
            return this;
        }
        public MultipleMethods openWithoutException(IMethod method){ method.method(); Selenide.closeWebDriver(); return this;}
        public MultipleMethods openWithoutClosing(IMethod method){ method.method(); return this; }
        public void GetExceptions(){
            if(list.length() > 0) try {
                new Exception("Произошли ошибки в тестах");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            for(int step = 0; step < list.length(); step++) System.out.println(list.getValue(step) + "\n------------------------------------------------------\n");
            if(list.length() > 0) System.exit(-1);
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
        public SelenideElement eEnumCountiesFirst(){ return $(".MuiListItem-gutters");}
        public SelenideElement eTextOfCheckBox1(){ return $(".MuiTypography-root", 3); }
        public SelenideElement eTextOfCheckBox2(){ return $(".MuiTypography-root", 5); }
        public SelenideElement eBlueMarker(int count, int number){$$(".MuiSvgIcon-colorSecondary").shouldBe(size(count)); return $(".MuiSvgIcon-colorSecondary", number); }
        public SelenideElement eBlueMarker(){ return $(".MuiSvgIcon-colorSecondary");}
        public void eBlueMarker(int count){ $$(".MuiSvgIcon-colorSecondary").shouldBe(size(count));}
        public void clickGetStarted(){ eGetStarted().click(); }

        private String address = baddress + "auth/register/";
        public Registration(){}
        public Registration(String empty){} // Для уже написанных тестов

             /** Открывает ссылку в настоящем окне */
             public void open(){Selenide.open(address);}
        public void open(String path) {
            Selenide.open(path);
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
            if(!email.isEmpty()) eEmail().setValue(email);
            ePassword().setValue(password);
            eConfirmPassword().setValue(verifyPassword);
            return this;
        }
        public Registration wwRegistration(){
            eFirstName().setValue(firstName);
            eLastName().setValue(lastName);
            eBirthDate().setValue(date);
            if(!country.isEmpty()) {
                for (int step = 0; step < country.length(); step++) {
                    eCountryName().sendKeys(Keys.chord(Keys.COMMAND, String.valueOf(country.toCharArray()[step])));
                    sleep(20);
                }
                eEnumCountiesFirst().click();
            }
            ePhoneNumber().setValue(phone);
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
        public void clickNext2(){
            clickCheckBox();
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
        public SelenideElement eTitle_forgotPassword(){ return $(".MuiTypography-body1"); }
        public SelenideElement eBackToLogin_forgotPassword(){ return $(".MuiButton-label");  }
        public SelenideElement eSubmit_forgotPassword(){ return $(".MuiButton-label", 1);  }
        public SelenideElement eAccessCode_ResetPassword(){ return $(byName("code")); }
        public SelenideElement eConfirmPassword_resetPassword(){ return $(byName("confirmPassword")); }
        public SelenideElement eResetPassword_resetPassword(){ return eSubmit_forgotPassword();}
        public SelenideElement eTitle_resetPassword(){ return eTitle_forgotPassword(); }

        public void checkResetPasswordForm(){
            eResetPassword_resetPassword().shouldBe(visible);
            eConfirmPassword_resetPassword().shouldBe(visible);
            eTitle_resetPassword().shouldBe(visible);
            eAccessCode_ResetPassword().shouldBe(visible);
        }
        public void checkForgotPasswordForm(){
            eTitle_forgotPassword().shouldBe(visible);
            eEmail().shouldBe(visible);
            eBackToLogin_forgotPassword().shouldBe(visible);
            eSubmit_forgotPassword().shouldBe(visible);
        }
        public void clickBackToLogin_forgotPassword(){ eBackToLogin_forgotPassword().click(); }
        public void clickSubmit_forgotPassword(){ eSubmit_forgotPassword().click(); }
        public void writeEmail_forgotPassword(String email){ eEmail().setValue(email); }
        private String address = baddress + "auth/login";
        public String email = "", password = "", newPassword = "", confirmNewPassword = "";
        /** Открывает страницу входа
         * @return*/
        public Login open(){
            Selenide.open(address);
            return this;
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
        public class Home{
            public SelenideElement eChangePassword(){ return $(".MuiSvgIcon-root", 2); }
            public SelenideElement eInformation(){ return $(".MuiSvgIcon-root", 1);}
            public SelenideElement eLogOut(){ return $(".MuiSvgIcon-root", 3);}
            public SelenideElement eHome(){ return $(".MuiSvgIcon-root", 0);}
            public SelenideElement eExistingPassword_changePassword(){ return $(byName("existingPassword")); }
            public SelenideElement eNewPassword_changePassword(){ return $(".MuiInputBase-fullWidth", 1).lastChild(); }
            public SelenideElement eConfirmPassword_changePassword(){ return $(".MuiInputBase-fullWidth", 2).lastChild(); }
            public SelenideElement eSave_changePassword(){ return $(".MuiButton-text", 5); }
            public SelenideElement eX_changePassword(){ return $(".MuiIconButton-root"); }
            public SelenideElement eCaseInformationForm(){ return $(".MuiGrid-direction-xs-column", 3); }
            public SelenideElement eDate_Information(){ return $(".MuiGrid-root.MuiGrid-item", 31).lastChild(); }
            public SelenideElement eTitle_Information(){ return $(".MuiTypography-root.MuiTypography-body1", 11); }
            public SelenideElement eClinicianName_Information(){ return $(".MuiTypography-root.MuiTypography-body2", 1); }
            public SelenideElement ePhone_Information(){ return $(".MuiTypography-root.MuiTypography-body2", 2); }

            String existingPassword = "";
            String newPassword = "";
            String confirmPassword = "";
            public Home clickInformation(){ eInformation().click(); return this; }
            public Home clickLogOut(){ eLogOut().click(); return this; }
            public Home clickHome() { eHome().click(); return this; }
            public void clickX_changePassword(){ eX_changePassword().click(); }
            public void clickChangePassword(){ eChangePassword().click(); }
            public void clickSave_changePassword(){ eSave_changePassword().click();}
            public Home writeExistingPassword_changePassword(String existingPassword){ eExistingPassword_changePassword().setValue(existingPassword); return this;}
            public Home writeNewPassword_changePassword(String newPassword){ eNewPassword_changePassword().setValue(newPassword); return this;}
            public Home writeConfirmPassword_changePassword(String confirmPassword){ eConfirmPassword_changePassword().setValue(confirmPassword); return this;}
            public Home setAllPassword_changePassword(String existingPassword, String newPassword, String confirmPassword){
                this.existingPassword = existingPassword;
                this.newPassword = newPassword;
                this.confirmPassword = confirmPassword;
                return this;
            }
            public Home writeAll_changePassword(){
                writeExistingPassword_changePassword(existingPassword);
                writeNewPassword_changePassword(newPassword);
                writeConfirmPassword_changePassword(confirmPassword);
                return this;
            }
            public Home checkAll_changePassword(){
                eExistingPassword_changePassword().shouldHave(value(existingPassword)).shouldBe(visible);
                eNewPassword_changePassword().shouldHave(value(newPassword)).shouldBe(visible);
                eConfirmPassword_changePassword().shouldHave(value(confirmPassword)).shouldBe(visible);
                eSave_changePassword().shouldBe(visible);
                eX_changePassword().shouldBe(visible);
                return this;
            }
            public void openInNewTab(){
                Main.newTab();
                Selenide.switchTo().window(1);
                Selenide.open("https://alpha-patient.incytesdata-dev.com/");
                while(true) if($$(".MuiSvgIcon-root").size() == 7) break; else sleep(50);
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
        public String time;
        private String lastCode(){
            time = $(".mail-MessageSnippet-Item_dateText").getAttribute("title").substring(18);
            return $(".js-message-snippet-firstline").getText().substring(26);
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

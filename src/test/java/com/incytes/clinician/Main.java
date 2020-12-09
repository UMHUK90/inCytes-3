package com.incytes.clinician;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
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

/** Главный класс (контейнер) */
public class Main {
    public interface IMethod{
        @Test
        void method();
    }
    public class List<T>{
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
    public abstract class TermsAndConditions_abstract{
        public SelenideElement eHeading(){return $(".MuiTypography-h3");}
        public ElementsCollection eLinks(){ return $$("a"); }
        public SelenideElement firstText(){return $(".MuiTypography-body2");}
    }
    private String baddress = "https://qa.incytesdata-dev.com/";
    public Main(String language){
        setLang(language);
    }
    public Main(){
        setLang("En");
    }
    public static void close(){
        Selenide.close();
    }
    /** Открывает новое окно */
    public static void newTab(){ Selenide.executeJavaScript("window.open()");}
    public static void haveRequired(int size){  $$(byText("Required")).shouldHave(size(size)); }
    public static String currentPage(){
        return WebDriverRunner.url();
    }
    public static SelenideElement eBottomMessage(){ return $(".MuiSnackbarContent-root"); }
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
    public static SelenideElement muiError(int size, int number){
        $$("p.Mui-error").shouldHave(size(size));
        return $("p.Mui-error", number);
    }

    public static void clickOutSide(SelenideElement elementOutSide, int x, int y){ Selenide.actions().moveToElement(elementOutSide, x, y).click().build().perform(); }
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
            case "De": language = "de";
            break;
            default: language = "en";
            break;
        }
        System.setProperty("chromeoptions.prefs","intl.accept_languages=" + language);
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
            Selenide.close();
            return this;
        }
        public MultipleMethods openWithoutException(IMethod method){ method.method(); Selenide.close(); return this;}
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
            $("button", 1).click();
            $(byAttribute("var","body1")).waitUntil(visible, 12000);
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
        public class TermsAndConditions extends TermsAndConditions_abstract{}
    }
    /** Класс предназначен для работы с формой входа */
    public class Login{
        private String eemail = "";
        //Элементы страницы
        public SelenideElement eEmail(){return $(name("email"));}
        public SelenideElement ePassword(){return $(name("password"));}
        public SelenideElement eSignIn(){return $(".MuiButton-label", 2) ;}
        public SelenideElement eForgotPassword(){return $(".MuiButtonBase-root", 0);}
        public SelenideElement eSignUp(){return $(".MuiButton-label", 1);}
        public SelenideElement eBackToLogin_forgotPassword() { return $("a"); }
        public SelenideElement eTitle_forgotPassword(){ return $(".MuiTypography-h3"); }
        public SelenideElement eSubmit_forgotPassword(){ return $(".MuiButton-label", 1); }
        public SelenideElement eSignUp_forgotPassword(){ return $(".MuiButton-label", 0); }
        public SelenideElement eInvalid_forgotPassoword() { return $(".MuiFormHelperText-filled"); }
        public SelenideElement eMessage_forgotPassword() { return $(".MuiTypography-body1"); }
        public SelenideElement eInvitation(){ return $(".MuiTypography-root"); }
        public SelenideElement eTitle_resetPassword(){ return $("h3");}
        public SelenideElement eNote_resetPassword(){ return $("h6");}
        public SelenideElement eConfirmPassword_resetPassword(){ return $(byName("confirmPassword")); }
        public SelenideElement eAccessCode_resetPassword(){ return $(byName("code"));}
        public SelenideElement eBackToLogin_resetPassword(){ return eBackToLogin_forgotPassword().parent();}
        public SelenideElement eResetPassword_resetPassword(){ return $(".MuiButtonBase-root");}

        public Login writePassword_resetPassword(String password){ ePassword().setValue(password); return this;}
        public Login writeConfirmPassword_resetPassword(String password){ eConfirmPassword_resetPassword().setValue(password); return this; }
        public Login writeAccessCode_resetPassword(String code){ eAccessCode_resetPassword().setValue(code); return this;}
        public void clickBackToLogin_resetPassword(){ eResetPassword_resetPassword().click(); }
        public void clickReset_resetPassword(){eResetPassword_resetPassword().click();}

        public Login checkResetPasswordForm(){
            eTitle_resetPassword().shouldBe(visible);
            eNote_resetPassword().shouldBe(visible);
            eConfirmPassword_resetPassword().shouldBe(visible);
            eAccessCode_resetPassword().shouldBe(visible);
            eBackToLogin_forgotPassword().shouldBe(visible);
            eResetPassword_resetPassword().shouldBe(visible);
            return this;
        }
        public void clickBackToLogin_forgotPassword(){ eBackToLogin_forgotPassword().click(); }
        public void clickSignUp_forgotPassword(){ eSignUp_forgotPassword().click(); }
        public void clickSubmit_forgotPassword(){ eSubmit_forgotPassword().click(); }
        public void haveInvalid_forgotPassword(){ eInvalid_forgotPassoword().shouldBe(visible); }
        public void writeEmail_forgotPassword(String email){ eemail = email; eEmail().setValue(email); }
        public void diplayedEmail_forgotPassword(){ eEmail().shouldHave(value(eemail)); }
        public void haveEmailError_forgotPassword(){ $(".MuiInputLabel-animated").shouldHave(cssValue("color", "rgba(244, 67, 54, 1)")); }
        public void backToLoginShouldBe_forgotPassword(){ eBackToLogin_forgotPassword().shouldHave(text("Back to Login")); }
        public void CodeHasBeenSent(){ eInvalid_forgotPassoword().shouldHave(text("Verification code has been sent to your email")); }
        public Login forgotPasswordIsCorrect(){
            eBackToLogin_forgotPassword().shouldHave(attribute("href", "https://qa.incytesdata-dev.com/auth/login"));
            eTitle_forgotPassword().shouldBe(visible);
            eEmail().shouldBe(visible);
            eSubmit_forgotPassword().shouldBe(visible);
            eSignUp_forgotPassword().shouldBe(visible);
            return this;
        }
        private String address = baddress + "auth/login";
        private String email = "", password = "";
        /** Открывает страницу входа */
        public Login open(){
            Selenide.open(address);return this;
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
        public Login forgotPassword(){
            eForgotPassword().click();
            return this;
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
            public SelenideElement eOpenCasesCount(){ return $(".MuiTypography-root.MuiTypography-body1", 3); }
            public SelenideElement eTotalPatientsCount(){ return $(".MuiTypography-root.MuiTypography-body1", 5); }
            public SelenideElement eTotalCasesCount(){ return $(".MuiTypography-root.MuiTypography-body1", 7); }
            public SelenideElement eGraph(){ return $(".grid"); }
            public SelenideElement eClosedCasesCount(){ return $(".MuiTypography-root.MuiTypography-body1", 9); }
            public SelenideElement eTitle(){ return $("svg"); }
            public SelenideElement eSearch(){ return $(".MuiGrid-align-items-xs-center", 0).parent().parent().parent(); }
            public SelenideElement eNewCase(){ return $(".MuiSvgIcon-root", 2).parent(); }
            public SelenideElement eDashBoard(){ return $(".MuiTypography-root.MuiTypography-body2", 2); }
            public SelenideElement eCases(){ return $("#casesLink").parent().waitUntil(attribute("style", "pointer-events: inherit;"), 5000); }
            public SelenideElement eTasks(){ return $("#mytasksLink").parent().waitUntil(attribute("style", "pointer-events: inherit;"), 5000); }
            public SelenideElement eReports(){ return $(".MuiTypography-body2", 4).parent().parent().parent().waitUntil(attribute("style", "pointer-events: inherit;"), 5000); }
            public SelenideElement eCircles(){ return $("#circlesLink").parent().waitUntil(attribute("style", "pointer-events: inherit;"), 5000); }
            public SelenideElement ePatients(){ return $(".MuiTypography-body2", 7).parent().parent().parent(); }
            public SelenideElement eSupport(){ return $(".MuiGrid-align-items-xs-center", 2).parent().parent().parent(); }
            public SelenideElement eAccount(){ return $(".MuiGrid-align-items-xs-center", 3).parent().parent().parent(); }
            public SelenideElement eProfile(){ return $(".MuiGrid-align-items-xs-center", 4).parent().parent().parent(); }
            public SelenideElement eLogout(){ return $(".MuiGrid-align-items-xs-center", 5).parent().parent().parent(); }
            public SelenideElement eMyAlerts(){ return $(".MuiPaper-root.MuiPaper-elevation2", 1); }
            public ElementsCollection eListOfTasks(){ return $(".MuiTableBody-root", 0).$$(".MuiTableRow-root"); }
            public ElementsCollection eListOfAlerts(){ return $(".MuiTableBody-root", 1).$$(".MuiTableRow-root");}
            public SelenideElement eBuySubscription(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-contained"); }
            public SelenideElement eTitle_Subscription(){ return $("h3"); }
            public SelenideElement eXClose_Subscription(){ return $(".MuiIconButton-root"); }
            public SelenideElement eText_Subscription(){return $("h5");}
            public SelenideElement eCheckOut_Subscription(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-text"); }
            /** It's necessary to use the switchTo.ParentFrame after this method**/
            public SelenideElement eCardNumber_Card(){ Selenide.switchTo().frame($(byTitle("Secure card payment input frame"))); SelenideElement element = $(".Input", 0); return element; }
            /** It's necessary to use the switchTo.ParentFrame after this method**/
            public SelenideElement eCardDate_Card(){ Selenide.switchTo().frame($(byTitle("Secure card payment input frame"))); SelenideElement element = $(".Input", 1); return element; }
            /** It's necessary to use the switchTo.ParentFrame after this method**/
            public SelenideElement eCVC_Card() { Selenide.switchTo().frame($(byTitle("Secure card payment input frame"))); SelenideElement element = $(".Input", 2); return element; }
            /** It's necessary to use the switchTo.ParentFrame after this method**/
            public SelenideElement eZIP_Card(){ Selenide.switchTo().frame($(byTitle("Secure card payment input frame"))); SelenideElement element = $(".Input", 3); return element; }
            public SelenideElement eSaveCard_Card(){ return eCheckOut_Subscription(); }
            public SelenideElement ePay_Paying(){ return eCheckOut_Subscription(); }
            public void clickSaveCard_Card(){ eSaveCard_Card().click(); }
            public void writeCardNumber_Card(String number, String date, String CVC, String ZIP) { eCardNumber_Card().setValue(number);Selenide.switchTo().parentFrame(); eCardDate_Card().setValue(date); Selenide.switchTo().parentFrame();eCVC_Card().setValue(CVC); Selenide.switchTo().parentFrame();eZIP_Card().setValue(ZIP); Selenide.switchTo().parentFrame();}
            public void saveCard_Card(){ eSaveCard_Card().click(); }
            public void clickX_Subscription(){ eXClose_Subscription().click(); }
            public void clickCheckOut_Subscription(){ eCheckOut_Subscription().click(); }
            public void clickBuySubscription(){ eBuySubscription().click(); }
            public void clickSearch(){ eSearch().click(); }
            public void clickNewCase(){ eNewCase().click(); }
            public void clickReports(){ eReports().click(); }
            public void clickCircles(){ eCircles().click(); }
            public void clickPatients(){ ePatients().click(); }
            public void clickSupport(){ eSupport().click(); }
            public void clickAccount(){ eAccount().click(); }
            public void clickProfile(){ eProfile().click(); }
            public void clickLogout(){ eLogout().click(); }
            public void clickCases(){ eCases().click(); }
            public abstract class NewCase_abstract{
                private String email = "";
                private String date = "";
                private String firstName = "";
                private String lastName = "";
                private String country = "";
                private String phone = "";
                private String protocol = "";
                private String shared = "";
                public SelenideElement eUseExistingPatient(){ return $(".MuiTypography-subtitle2"); }
                public SelenideElement ePatientIdentity(){ return $("#patientId"); }
                public SelenideElement eDateOfBirth(){ return $(By.name("birthDate")); }
                public SelenideElement eShowOptionalFields(){ return $(".MuiButton-textSecondary"); }
                public SelenideElement eFirstName(){ return $(By.name("firstName")); }
                public SelenideElement eLastName(){ return $(By.name("lastName")); }
                public SelenideElement eCountry(){ return $(By.name("countryName")); }
                public SelenideElement ePhone(){ return $(By.name("phoneNumber")); }
                public SelenideElement eSponsoredBy(){ return $("#sponsorId"); }
                public SelenideElement eSharedWithCircles(){ return $("#sharedCircles"); }
                public SelenideElement eCreateCase(){ return $(".MuiButton-textSizeLarge"); }
                public SelenideElement eForm(){ return $(".MuiDialogContent-root"); }
                public SelenideElement eEmail(){ return Login.this.eEmail(); }
                public SelenideElement eXButton(){ return $(".MuiTypography-root.MuiTypography-h6").lastChild(); }

                public void clickXButton(){ eXButton().click(); }
                public void clickCreateCase(){ eCreateCase().click(); }
                public void clickUseExistingPatient(){ eUseExistingPatient().click(); }
                public void clickShowOptionalFields(){ eShowOptionalFields().click(); }
                public NewCase_abstract setAll(String email, String date, String firstName, String lastName, String country, String phone, String protocol){
                    this.email = email;
                    this.date = date;
                    this.firstName = firstName;
                    this.lastName = lastName;
                    this.country = country;
                    this.phone = phone;
                    this.protocol = protocol;
                    return this;
                }
                public NewCase_abstract setSharedWith(String shared){
                    this.shared = shared;
                    return this;
                }
                public NewCase_abstract writeAll(){
                    if(eEmail().is(visible)){
                        eEmail().setValue(email);
                        eDateOfBirth().setValue(date);
                    }
                    else {
                        ePatientIdentity().setValue(email);
                        while(true) if($(byAttribute("role", "tooltip")).waitUntil(visible, 5000).$("div").$("ul").$$("li").toArray().length == 1) { $(byAttribute("role", "tooltip")).$("div").$("ul").shouldHave(text(email)).click(); break;}
                    }
                    if(!firstName.isEmpty() || !lastName.isEmpty() || !country.isEmpty() || !phone.isEmpty()) {
                        if(!eFirstName().is(visible)) clickShowOptionalFields();
                        eFirstName().setValue(firstName);
                        eLastName().setValue(lastName);
                        eCountry().setValue(country);
                        ePhone().setValue(phone);
                    }
                    eSponsoredBy().setValue(protocol);
                    if(!protocol.isEmpty()) {
                        while(true) if($(byAttribute("role", "tooltip")).waitUntil(visible, 5000).$("div").$("ul").$$("li").toArray().length == 1) { $(byAttribute("role", "tooltip")).$("div").$("ul").$("li").waitUntil(text(protocol), 5000).parent().click(); break;}
                    }
                    eSharedWithCircles().setValue(shared);
                    if(!shared.isEmpty()) {
                        while(true) if($(byAttribute("role", "tooltip")).waitUntil(visible, 5000).$("div").$("ul").$$("li").toArray().length == 1) {  $(byAttribute("role", "tooltip")).$("div").$("ul").waitUntil(enabled, 5000).shouldHave(text(shared)).click(); break;}
                    }
                    return this;
                }
                public NewCase_abstract checkAll(){
                    if(eEmail().is(exist)){
                        eEmail().shouldHave(value(email));
                        eDateOfBirth().shouldHave(value(date));
                    }
                    else {
                        ePatientIdentity().shouldHave(value(email));
                    }
                    if(!firstName.isEmpty() || !lastName.isEmpty() || !country.isEmpty() || !phone.isEmpty()) {
                        eFirstName().shouldHave(value(firstName));
                        eLastName().shouldHave(value(lastName));
                        eCountry().shouldHave(value(country));
                        ePhone().shouldHave(value(phone));
                    }
                    eSponsoredBy().shouldHave(value(protocol));
                    return this;
                }
            }
            public class NewCase extends NewCase_abstract{}
            public class Profile{
                public SelenideElement eEDIT(){ return $(".MuiTypography-body1", 0); }
                public SelenideElement eChangePassword(){ return $(".MuiTypography-body1", 1); }
                public SelenideElement eInvite(){ return $(".MuiTypography-body1", 5); }
                public SelenideElement eI_Invite(){ return $((".MuiFilledInput-input")); }
                public SelenideElement eSendInvitation(){ return $(".MuiButton-label"); }
                public SelenideElement eInvitationSent() { return $((".MuiSnackbarContent-message"));}
                public ElementsCollection eListOfTeamMembers(){ return $(".MuiTableBody-root").$$(".MuiTableRow-root"); }
                public SelenideElement eEmail_TeamMember(){ return $$("ul").last().$$("li").first(); }
                public SelenideElement eResend_TeamMember(){ return $$("ul").last().$$("li").get(1); }
                public SelenideElement eRemove_TeamMember(){ return $$("ul").last().$$("li").last(); }
                public void clickEmail_TeamMember(){ eEmail_TeamMember().click(); }
                public void clickResend_TeamMember(){ eResend_TeamMember().click(); }
                public void clickRemove_TeamMember(){ eRemove_TeamMember().click(); }
                public void clickOptions_TeamMember(SelenideElement teamMember){ teamMember.find("button").click(); }
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
            public class Patients{
                public SelenideElement eSearch(){ return $(".MuiInputBase-inputAdornedStart"); }
                public SelenideElement eAddPatient(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-contained"); }
                public ElementsCollection eListOfPatients(){ return $(".MuiTableBody-root").$$(".MuiTableRow-root"); }


            }
            public class Circles{
                public class TermsAndConditions extends TermsAndConditions_abstract {}
                public SelenideElement eAllCircles(){ return $(".MuiInput-formControl"); }
                public SelenideElement eSearch(){ return $(".MuiInputBase-inputAdornedStart"); }
                public SelenideElement eCreateCircle(){ return $(".MuiButton-contained").waitUntil(visible, 8000); }
                public SelenideElement eCircleName_Creation(){ return $(".MuiFilledInput-input"); }
                public SelenideElement eNonPHI_Creation(){ return $(byAttribute("name", "phi"));}
                public SelenideElement ePHI_Creation(){ return $(byAttribute("name", "phi"), 1);}
                public SelenideElement eSponsored_Creation(){ return $(byAttribute("type", "checkbox"));}
                public SelenideElement eProtocol_Creation(){ return $(".MuiFilledInput-input", 1); }
                public SelenideElement eTreatment_Creation(){ return $(".MuiFilledInput-input", 2); }
                public SelenideElement eIndication_Creation(){ return $(".MuiFilledInput-input", 3); }
                public SelenideElement eSelectMenu(){ return $(".MuiSelect-selectMenu"); }
                public ElementsCollection eListOfSelectMenu(){ int size = $$(".MuiList-root.MuiMenu-list.MuiList-padding").size(); return $(".MuiList-root.MuiMenu-list.MuiList-padding", size-1).$$("li");}
                public SelenideElement eLastCircle(){ return eListOfCircles().first().find(byAttribute("style", "display: block; text-decoration: none; font-weight: 700; font-size: 17px; line-height: 20px; letter-spacing: 0.16px; color: rgb(1, 16, 32); margin-bottom: 5px;")); }
                public ElementsCollection eListOfCircles(){ $(".MuiTableBody-root").$$("tr").first().waitUntil(enabled, 5000); return $(".MuiTableBody-root").$$("tr"); }
                public SelenideElement eTitle_Creation(){ return $(".MuiTypography-h3"); }
                public ElementsCollection eListOfProtocols_Creation(){ return $$("ul").find(visible).$$("li"); }
                public SelenideElement eCreateCircle_Creation(){ return $(".MuiButton-sizeLarge"); }
                public SelenideElement eInputCases_Creation_Cases(){  return $(byName("sponsoredCases"));}
                public SelenideElement eObservationalProtocol_Creation(){ return  $("#protocolId");}
                public void clickCreateCircle_Creation(){ eCreateCircle_Creation().click(); }
                public SelenideElement eTermsAndConditions_Creation(){ return $(byAttribute("href", "/terms")); }
                public void clickCreateCircle(){ eCreateCircle().click();}
                public void clickSelectMenu(){  eSelectMenu().click();}
                public void selectTypeOfCircles(int type){ eSelectMenu().click(); eListOfSelectMenu().get(4).waitUntil(enabled, 10000); eListOfSelectMenu().get(type-1).click(); }
                public Boolean isNoCircles(){ if($(".MuiTypography-h4").has(text("MuiTypography-h4"))) return true; return false; }
                public void writeCircleName(String name){ eCircleName_Creation().setValue(name); }
                public void clickNonPHI(){ eNonPHI_Creation().click(); }
                public void clickPHI(){ ePHI_Creation().click(); }
                public void clickSponsored(){eSponsored_Creation().click();}
                public void writeProtocol(String name){ eProtocol_Creation().setValue(name); }
                public void writeTreatment(String name){ eTreatment_Creation().setValue(name); }
                public void writeIndication(String name){ eIndication_Creation().setValue(name); }
                public void clickOnCircle(SelenideElement circle){ circle.find(byAttribute("style", "display: block; text-decoration: none; font-weight: bold; font-size: 17px; line-height: 20px; letter-spacing: 0.16px; color: rgb(1, 16, 32); margin-bottom: 5px;")).waitUntil(enabled, 5000).click(); }
                public void clickLastCircle(){ eLastCircle().click(); }
                public class Circle{
                    public SelenideElement eInvite() { return $(".MuiIconButton-label", 2); }
                    public SelenideElement eEmail_Invite(){ return $(".MuiFilledInput-input"); }
                    public ElementsCollection eErrors_Invite(){ return $$(".Mui-error"); }
                    public ElementsCollection eCircleMembers() { return $$(".MuiTableRow-root"); }
                    //public SelenideElement eSponsor_Invite(){ return $(""); }
                    public SelenideElement eSendInvitation_Invite(){ return $(byAttribute("type", "submit")); }
                    public SelenideElement eEditCircle(){ return $(byAttribute("aria-label", "more")); }
                    public SelenideElement eNameOfScoringGroup(){ return $(".MuiSelect-root.MuiSelect-select.MuiSelect-selectMenu.MuiInputBase-input.MuiInput-input"); }
                    public void toListOfScoringGroups(){ eNameOfScoringGroup().click(); }
                    public ElementsCollection eListOfScoringGroups(){ return $(".MuiList-root.MuiMenu-list.MuiList-padding").$$("li"); }
                    public SelenideElement eGraph(){ return $("#chart").$("svg"); }

                    //EditCircle
                    String circleName = "", description = "", country = "", phone = "", streetAddress = "", city = "", state = "", postalCode = "";
                    boolean isArchive;
                    public SelenideElement eTitle_EditCircle(){ return $(".MuiTypography-root.MuiTypography-h3", 1); }
                    public SelenideElement eCircleName_EditCircle(){ return $(byName("name")); }
                    public SelenideElement eDescription_EditCircle(){ return $(byName("description")); }
                    public SelenideElement eCountry_EditCircle(){ return $(byName("countryName")); }
                    public SelenideElement ePhone_EditCircle(){ return $(byName("phoneNumber")); }
                    public SelenideElement eStreetAddress_EditCircle(){ return $(byName("streetAddress")); }
                    public SelenideElement eCity_EditCircle(){ return $(byName("city")); }
                    public SelenideElement eState_EditCircle(){ return $(byName("state")); }
                    public SelenideElement ePostalCode_EditCircle(){ return $(byName("postalCode")); }
                    public SelenideElement eArchive_EditCircle(){ return $(byName("isArchived")); }
                    public SelenideElement eSaveButton_EditCircle(){ return $(".MuiButton-sizeLarge"); }

                    public void clickArchive_EditCircle(){ eArchive_EditCircle().click(); }
                    public void clickSaveButton_EditCircle(){ eSaveButton_EditCircle().click(); }
                    public Circle setAll_EditCircle(String circleName, String description, String country, String phone, String streetAddress, String city,String state, String postalCode, boolean isArchive ){
                        this.circleName = circleName;
                        this.description = description;
                        this.country = country;
                        this.phone = phone;
                        this.streetAddress = streetAddress;
                        this.city = city;
                        this.postalCode = postalCode;
                        this.state = state;
                        this.isArchive = isArchive;
                        return this;
                    }
                    public Circle setAll_EditCircle(){
                        if(!circleName.isEmpty()) eCircleName_EditCircle().setValue(circleName);
                        if(!description.isEmpty()) eDescription_EditCircle().setValue(description);
                        if(!country.isEmpty()) eCountry_EditCircle().setValue(country);
                        if(!phone.isEmpty()) ePhone_EditCircle().setValue(phone);
                        if(!streetAddress.isEmpty()) eStreetAddress_EditCircle().setValue(streetAddress);
                        if(!city.isEmpty()) eCity_EditCircle().setValue(city);
                        if(!postalCode.isEmpty()) ePostalCode_EditCircle().setValue(postalCode);
                        if(!state.isEmpty()) eState_EditCircle().setValue(state);
                        if((isArchive && !Boolean.getBoolean(eArchive_EditCircle().getValue())) || (!isArchive && Boolean.getBoolean(eArchive_EditCircle().getValue()))) eArchive_EditCircle().click();
                        return this;
                    }
                    public Circle checkAll(){
                        if(!circleName.isEmpty()) eCircleName_EditCircle().shouldHave(text(circleName));
                        if(!description.isEmpty()) eDescription_EditCircle().shouldHave(text(description));
                        if(!country.isEmpty()) eCountry_EditCircle().shouldHave(text(country));
                        if(!phone.isEmpty()) ePhone_EditCircle().shouldHave(text(phone));
                        if(!streetAddress.isEmpty()) eStreetAddress_EditCircle().shouldHave(text(streetAddress));
                        if(!city.isEmpty()) eCity_EditCircle().shouldHave(text(city));
                        if(!postalCode.isEmpty()) ePostalCode_EditCircle().shouldHave(text(postalCode));
                        if(!state.isEmpty()) eState_EditCircle().shouldHave(text(state));
                        eArchive_EditCircle().shouldHave(value(String.valueOf(isArchive)));
                        return this;
                    }


                    public void clickInvite() { eInvite().click();}
                    public void clickEditCircle(){ eEditCircle().click(); }
                    public void writeEmail_Invite(String email){ eEmail_Invite().setValue(email); }
                    public void clickSendInvitation_Invite(){ eSendInvitation_Invite().click(); }
                    public void notHaveErrors(){ eErrors_Invite().shouldHave(size(0)); }
                    public void haveCircleMember(String email){ eCircleMembers().findBy(text(email)).exists(); }
                }
            }
            public class Cases{
                public SelenideElement eAddCase(){ return $(".MuiButton-contained").waitUntil(visible,10000); }
                public SelenideElement eSearch(){ return $(".MuiInputBase-inputAdornedStart"); }
                public SelenideElement eTitle(){ return $("#headerText"); }
                public SelenideElement eMoreOptions(SelenideElement item){ return item.find(".MuiButtonBase-root.MuiIconButton-root"); }
                public ElementsCollection eItems(){ return $(".MuiTableBody-root").$$(".MuiTableRow-root"); }
                public SelenideElement eClose(){ return $(".MuiListItem-gutters"); }
                public SelenideElement eEditCase(){ return $(".MuiListItem-gutters", 1); }
                public SelenideElement eCloseCase_CLosing(){ return $(".MuiButton-sizeLarge"); }
                public void clickCloseCase_Closing(){ eCloseCase_CLosing().click();}
                public void clickAddCase() { eAddCase().click(); }
                public void toCaseDetails(SelenideElement item){ item.find("a").parent().click(); }
                public void clickMoreOptions(SelenideElement item){ eMoreOptions(item).click(); }
                public void clickCloseCase(){ eClose().click(); }
                public void clickEditCase(){ eEditCase().click(); }
                public void clickSearch() { eSearch().click(); }
                public class NewCase extends NewCase_abstract{}
                public class CaseDetail{
                    public SelenideElement eTitle(){ return $(".MuiTypography-root.MuiTypography-h3"); }
                    public SelenideElement eCloseCase_CLosing(){ return Cases.this.eCloseCase_CLosing(); }
                    public SelenideElement eArchiveCase(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-outlined"); }
                    public SelenideElement eCloseCase(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-outlined", 1); }
                    public SelenideElement eEditCase(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-outlined", 2); }
                    public ElementsCollection eListOfAlerts(){ return $(".MuiTableBody-root").$$("MuiTableRow-root"); }
                    public SelenideElement eMoreOptions(SelenideElement alert){ return alert.find(".MuiButtonBase-root.MuiIconButton-root"); }
                    public SelenideElement ePatientProgressForm(){ return $(".MuiPaper-root.MuiPaper-elevation2"); }
                    public SelenideElement eArchive_Archiving(){ return $(".MuiButton-sizeLarge"); }
                    public void clickArchiveCase(){ eArchiveCase().click(); }
                    public void clickCLoseCase(){ eCloseCase().click(); }
                    public void clickEditCase(){ eEditCase().click(); }
                    public void clickMoreOptions(SelenideElement alert){ eMoreOptions(alert).click(); }
                    public void clickArchive_Archiving(){ eArchive_Archiving().click(); }
                    public void clickCloseCase_Closing(){ eCloseCase_CLosing().click();}
                }
            }
            public class Reports{
                public SelenideElement eTitle(){ return $(".MuiTypography-root.MuiTypography-h3"); }
                public SelenideElement eBuildReport(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-contained"); }
                public SelenideElement eSavedReportsForm(){ return $(".MuiPaper-rounded"); }
                public SelenideElement eKOOSForm(){ return $(".MuiPaper-rounded", 1);}
                public ElementsCollection eListOfSavedReports(){ return $$(".MuiGrid-root.MuiGrid-container.MuiGrid-align-items-xs-flex-start.MuiGrid-justify-xs-space-between"); }
                public SelenideElement eMoreOptions(SelenideElement report){ return report.find(".MuiButtonBase-root.MuiIconButton-root"); }
                public SelenideElement eNameReport(){ return $(".MuiListItem-button"); }
                public SelenideElement eRemoveReport(){ return $(".MuiListItem-button", 1); }
                public SelenideElement eEditReport(){ return $(byAttribute("style", "color: rgb(19, 104, 236);")).waitUntil(exist, 5000).waitUntil(enabled, 5000); }
                public SelenideElement eReportName_Build(){ return $(byName("name")); }
                public SelenideElement eCreateReport_Build(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-text.MuiButton-textSizeLarge.MuiButton-sizeLarge"); }
                public SelenideElement eXClose_Build(){ return $(".MuiButtonBase-root.MuiIconButton-root", 3); }
                public SelenideElement eTitle_Build(){ return $(".MuiTypography-alignLeft"); }
                public SelenideElement eChartSection(){ return $(".MuiPaper-rounded", 1); }
                public SelenideElement eName_ChartSection(){ return eChartSection().find(".MuiTypography-body1"); }
                public SelenideElement eRemove_Removing(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-text.MuiButton-textSizeLarge.MuiButton-sizeLarge"); }
                public void clickRemove_Removing(){ eRemove_Removing().click(); }
                public void clickXClose_Build(){ eXClose_Build().click(); }
                public void clickBuildReport(){ eBuildReport().click(); }
                public void selectReport(SelenideElement report){ report.find("h6").click(); }
                public void clickMoreOptions(SelenideElement report) { eMoreOptions(report).click(); }
                public void clickEditReport(){ Selenide.sleep(2000); eEditReport().click(); }
                public void clickNameReport(){ eNameReport().click(); }
                public void clickRemoveReport(){ eRemoveReport().click(); }
                public void clickCreateReport_Build(){ eCreateReport_Build().click(); }
                public void writeNameReport(String name){ eReportName_Build().setValue(name); }
                public class ReportBuilder{
                    public SelenideElement eTitle(){ return $(".MuiTypography-root.MuiTypography-h3"); }
                    public SelenideElement eBackToReports(){ return $(byAttribute("href", "/reports")); }
                    public SelenideElement eCirclesButton(){ $(".MuiButtonBase-root.MuiButton-root.MuiButton-text", 2).waitUntil(enabled, 5000); return $(".MuiButtonBase-root.MuiButton-root.MuiButton-text").waitUntil(enabled, 5000); }
                    public SelenideElement eYAxisButton(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-text", 1); }
                    public SelenideElement ePopulationButton(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-text", 2); }
                    public SelenideElement eAddCohortButton(){ return $(byAttribute("style", "padding: 15px; border: 1px dashed rgb(0, 122, 255); border-radius: 5px; cursor: pointer;")); }
                    public SelenideElement eCasesCount(){ return $(byAttribute("style", "font-size: 64px; font-weight: bold; color: rgb(188, 193, 201);"), 1); }
                    public SelenideElement eDataPointsCount(){ return $(byAttribute("style", "font-size: 64px; font-weight: bold; color: rgb(188, 193, 201);"), 2); }
                    public SelenideElement eCohortACasesCount(){ return $(byAttribute("style", "font-size: 64px; font-weight: bold; color: rgb(188, 193, 201);"), 3); }
                    public SelenideElement eCohortBCasesCount(){ return $(byAttribute("style", "font-size: 64px; font-weight: bold; color: rgb(188, 193, 201);"), 4); }
                    public List<SelenideElement> eListOfCohorts(){ List<SelenideElement> elements = new List<SelenideElement>(); for(int step = 0; step < $(byAttribute("style", "justify-content: space-between;")).parent().parent().$$(".MuiGrid-root.MuiGrid-item").size(); step++) if($(byAttribute("style", "justify-content: space-between;")).parent().parent().$$(".MuiGrid-root.MuiGrid-item").get(step).lastChild().lastChild().lastChild().find("button").exists()) elements.Add($(byAttribute("style", "justify-content: space-between;")).parent().parent().$$(".MuiGrid-root.MuiGrid-item").get(step)); return elements;}
                    public SelenideElement eGraph(){ return $(byAttribute("style", "flex: 0.9 1 0%;")); }
                    public SelenideElement eYAxis(){ return $(".yAxisGroup"); }
                    public SelenideElement eNameOfCurrentCircle(){ return $(".MuiTypography-root.MuiTypography-body1", 1); }
                    public SelenideElement eNameOfCurrentScoringGroup(){ return $(".MuiTypography-root.MuiTypography-body1", 3); }

                    //AddCohort
                    public SelenideElement eTitle_Cohort(){ return eTitle_YAxis(); }
                    public ElementsCollection eCheckBoxes_Cohort(){ return $$(byAttribute("type", "checkbox")); }
                    public SelenideElement eAddCohortFiler_Cohort(){ return $(".MuiButton-sizeSmall"); }
                    public void clickAddCohortFilter_Cohort(){ eAddCohortFiler_Cohort().click(); }
                    public ElementsCollection eButtonsOfList_Cohort(){ return $$(byAttribute("aria-haspopup", "listbox")); }
                    public ElementsCollection eListOfAnswers(){ return $$(".MuiList-root.MuiMenu-list.MuiList-padding").find(visible).$$("li"); }
                    public ElementsCollection eInputsOfQuestions(){ Selenide.sleep(500); if($$("input").filter(attribute("maxlength", "255")).first().exists()) return $$("input").filter(attribute("maxlength", "255")); else if($$("input").filter(attribute("maxlength", "10")).first().exists()) return $$("input").filter(attribute("maxlength", "10")); else return $$("input").filter(attribute("style", "color: white; background-color: rgb(36, 48, 65); padding-left: 10px;"));}
                    public void selectListAnswer_Cohort(int numberOfButton, int numberOfAnswer){ eButtonsOfList_Cohort().get(numberOfButton+1).waitUntil(enabled, 5000).click(); eListOfAnswers().get(numberOfAnswer).waitUntil(enabled, 5000).click(); }
                    public SelenideElement eInput_Cohort(){ return $("#react-autosuggest-popper"); }
                    public SelenideElement eDone_Cohort(){ return $$(".MuiButton-sizeSmall").last(); }
                    public ElementsCollection eListOfCohorts_Cohort(){ return $$("ul").filter(attribute("role", "listbox")).first().$$("li"); }
                    public void selectCohort_Cohort(String cohort){ eInput_Cohort().setValue(cohort); eListOfCohorts_Cohort().find(text(cohort)).waitUntil(enabled, 5000).click(); }
                    public void clickDone_Cohort(){ eDone_Cohort().click(); }
                    public SelenideElement eXCloseButton_Cohort(){ return $(".MuiButtonBase-root.MuiIconButton-root", 4); }
                    public SelenideElement eAddTab_Cohort(){ return $(".MuiButtonBase-root.MuiTab-root.MuiTab-textColorPrimary"); }
                    public void clickXCloseButton_Cohort(){ eXCloseButton_Cohort().click(); }
                    //CircleForm
                    public SelenideElement eInputCircle_Circle(){ return $(".MuiFormControl-root.MuiFormControl-fullWidth"); }
                    public SelenideElement eUpdateReport_Circle(){ return $(".MuiButton-sizeLarge"); }
                    public SelenideElement eXCloseButton_Circle(){ return $(".MuiButtonBase-root.MuiIconButton-root", 2).waitUntil(enabled, 5000); }
                    public SelenideElement eTitle_Circle(){ return $(".MuiTypography-root.MuiTypography-h3.MuiTypography-alignLeft"); }
                    public void clickUpdateReport_Circle(){ eUpdateReport_Circle().click(); }
                    public void clickXcloseButton_Circle(){ eXCloseButton_Circle().waitUntil(enabled, 5000).click();}
                    public ElementsCollection eListOfCircles(){ $$(".MuiList-root.MuiMenu-list.MuiList-padding").findBy(visible).$$("li").first().waitUntil(enabled, 5000); return $$(".MuiList-root.MuiMenu-list.MuiList-padding").findBy(visible).$$("li"); }
                    public void selectCircle_Circle(String circle){ eInputCircle_Circle().click();
                        eListOfCircles().findBy(text(circle)).waitUntil(enabled, 5000).click();
                    }

                    //YaxisForm
                    public SelenideElement eInputScoringGroup_YAxis(){ return eInputCircle_Circle(); }
                    public SelenideElement eSubmit_YAxis(){ return eUpdateReport_Circle(); }
                    public SelenideElement eXCloseButton_YAxis(){ return eXCloseButton_Circle(); }
                    public SelenideElement eTitle_YAxis(){ return eTitle_Circle(); }
                    public void clickSubmitButton_YAxis(){ clickUpdateReport_Circle(); }
                    public void clickXcloseButton_YAxis(){ clickXcloseButton_Circle(); }
                    public void selectScoringGroup_YAxis(String scoringGroup){ selectCircle_Circle(scoringGroup); }
                    public ElementsCollection eListOfScoringGroups(){ return eListOfCircles(); }

                    public void clickAddCohortButton(){ eAddCohortButton().waitUntil(enabled, 5000).click(); }
                    public void clickYAxisButton(){ eYAxisButton().click(); }
                    public void clickBackToReports(){ eBackToReports().click(); }
                    public void clickCirclesButton(){ eCirclesButton().waitUntil(enabled, 10000).click(); }
                    public void clickPopulationButton(){ ePopulationButton().click(); }
                }
            }
        }
    }
    /** Предназначен для получения кода для верификации */
    public class GetCodeWithYandex{

        public SelenideElement eLastSender(){ return $(".mail-MessageSnippet-FromText"); }
        public SelenideElement eLastTitle(){ return $(".mail-MessageSnippet-Item_body"); }
        public SelenideElement eLastText(){ return $(".mail-MessageSnippet-Item_firstline"); }
        public SelenideElement eLastCheckBox(){ return $(".mail-MessageSnippet-Checkbox-Nb", 0); }
        public SelenideElement eForward(){ return $(".ns-view-toolbar-button-forward"); }
        public SelenideElement eDelete(){ return $(".js-toolbar-item-delete"); }
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
            $(name("login")).waitUntil(visible, 10000).setValue(email);
            $(byAttribute("type", "submit")).click();
            $(byAttribute("type", "password")).setValue(password);
            $(byAttribute("type", "submit")).click();
            if($(byAttribute("type", "tel")).exists()) {$(byAttribute("type", "tel")).setValue(phone);
                $(byAttribute("type", "submit")).click();}
            $(".user-account__name").click(); //
            $(byAttribute("href", "https://mail.yandex.by")).click();
        }
        public String time;
        protected String lastCode(){
            time = $(".mail-MessageSnippet-Item_dateText").getText();
            return $(".js-message-snippet-firstline").getText().substring(26).replace(".", "");
        }
    }
    public class GetInvitationWithYandex extends GetCodeWithYandex {
        public SelenideElement eFullMessage(){ return $(".js-message-body-content.mail-Message-Body-Content"); }
        public SelenideElement eForwardLink() {
            return $$("a").findBy(attribute("data-cke-saved-href"));
        }

        public SelenideElement eSimpleLink() {
            return $(".daria-goto-anchor");
        }

        public void clickForwardLink() {
            eForwardLink().waitUntil(visible,10000).click();
        }

        public GetInvitationWithYandex(String email, String password, String phone) {
            super(email, password, phone);
        }

        public void clickInvitation() {
            toInvitation();
            //if(eLastCheckBox().is(visible)) clickForwardLink();
            eSimpleLink().click();
        }
        public void toInvitation(){
            super.enter(); //
            super.lastCode();
            if(!$(".js-message-snippet-subject").waitUntil(visible, 5000).$$("span").get(2).getText().isEmpty()){
                $(".mail-MessageSnippet-Content").click();
                int x = $$(".mail-MessageSnippet-Wrap").size();
                while(true) if($$(".mail-MessageSnippet-Wrap").size() > x) break; else sleep(50);
                $(".toggles-svgicon-on-unread", 1).click();
            }
            else{
                $(".mail-MessageSnippet-Content").click();
            }
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
    public static String password = "261090inCytes";
}

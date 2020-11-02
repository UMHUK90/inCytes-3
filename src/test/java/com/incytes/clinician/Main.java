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
    private String baddress = "https://qa.incytesdata-dev.com/";
    public Main(String language){
        setLang(language);
    }
    public Main(){
        setLang("En");
    }
    public static void close(){
        Selenide.closeWebDriver();
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
            Selenide.closeWebDriver();
            return this;
        }
        public MultipleMethods openWithoutException(IMethod method){ method.method(); Selenide.closeWebDriver(); return this;}
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

        public class TermsAndConditions{
            public SelenideElement eHeading(){return $(".MuiTypography-h3");}
            public ElementsCollection eLinks(){ return $$("a"); }
            public SelenideElement firstText(){return $(".MuiTypography-body2");}
        }
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

            public SelenideElement eSearch(){ return $(".MuiGrid-align-items-xs-center", 0).parent().parent().parent(); }
            public SelenideElement eNewCase(){ return $(".MuiSvgIcon-root", 1).parent(); }
            public SelenideElement eReports(){ return $(".MuiTypography-body2", 4).parent().parent().parent(); }
            public SelenideElement eCircles(){ return $(".MuiTypography-body2", 5).parent().parent().parent(); }
            public SelenideElement ePatients(){ return $(".MuiTypography-body2", 6).parent().parent().parent(); }
            public SelenideElement eSupport(){ return $(".MuiGrid-align-items-xs-center", 2).parent().parent().parent(); }
            public SelenideElement eAccount(){ return $(".MuiGrid-align-items-xs-center", 3).parent().parent().parent(); }
            public SelenideElement eProfile(){ return $(".MuiGrid-align-items-xs-center", 4).parent().parent().parent(); }
            public SelenideElement eLogout(){ return $(".MuiGrid-align-items-xs-center", 5).parent().parent().parent(); }
            public SelenideElement eCases(){ return $(".MuiTypography-body2", 3); }
            public SelenideElement eMyAlerts(){ return $(".MuiPaper-root.MuiPaper-elevation2", 1); }
            public ElementsCollection eListOfAlerts(){ return $(".MuiTableBody-root", 1).$$(".MuiTableRow-root");}
            public SelenideElement eBuySubscription(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-contained"); }
            public SelenideElement eTitle_Subscription(){ return $("h3"); }
            public SelenideElement eXClose_Subscription(){ return $(".MuiIconButton-root"); }
            public SelenideElement eText_Subscription(){return $("h5");}
            public SelenideElement eCheckOut_Subscription(){ return $(".MuiButtonBase-root.MuiButton-root.MuiButton-text"); }
            public SelenideElement eCardNumber_Card(){ Selenide.switchTo().frame(0).switchTo().frame($(byTitle("Secure card payment input frame"))); SelenideElement element = $(".InputElement.is-empty.Input.Input--empty", 0); Selenide.switchTo().parentFrame(); return element; }
            public SelenideElement eCardDate_Card(){ Selenide.switchTo().frame(1); SelenideElement element = $(".InputElement.is-empty.Input.Input--empty", 1); Selenide.switchTo().frame(1); return element; }
            public SelenideElement eCVC_Card() { Selenide.switchTo().frame(1); SelenideElement element = $(".InputElement.is-empty.Input.Input--empty", 2); Selenide.switchTo().frame(1); return element; }
            public SelenideElement eZIP_Card(){ Selenide.switchTo().frame(1); SelenideElement element = $(".InputElement.is-complete.Input"); Selenide.switchTo().frame(1); return element; }
            public SelenideElement eSaveCard_Card(){ return eCheckOut_Subscription(); }
            public SelenideElement ePay_Paying(){ return eCheckOut_Subscription(); }
            public void clickSaveCard_Card(){ eSaveCard_Card().click(); }
            public void writeCardNumber_Card(String number, String date, String CVC, String ZIP) { eCardNumber_Card().setValue(number); eCardDate_Card().setValue(date); eCVC_Card().setValue(CVC); eZIP_Card().setValue(ZIP);}
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
                public SelenideElement eUseExistingPatient(){ return $(".MuiTypography-subtitle2"); }
                public SelenideElement ePatientIdentity(){ return $("#patientId"); }
                public SelenideElement eDateOfBirth(){ return $(By.name("birthDate")); }
                public SelenideElement eShowOptionalFields(){ return $(".MuiButton-textSecondary"); }
                public SelenideElement eFirstName(){ return $(By.name("firstName")); }
                public SelenideElement eLastName(){ return $(By.name("lastName")); }
                public SelenideElement eCountry(){ return $(By.name("countryName")); }
                public SelenideElement ePhone(){ return $(By.name("phoneNumber")); }
                public SelenideElement eSponsoredBy(){ return $("#sponsorId"); }
                public SelenideElement eSharedWithCircles(){ return $(".#sharedCircles"); }
                public SelenideElement eCreateCase(){ return $(".MuiButton-textSizeLarge"); }
                public SelenideElement eForm(){ return $(".MuiDialogContent-root"); }

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
                public NewCase_abstract writeAll(){
                    if(eEmail().is(exist)){
                        eEmail().setValue(email);
                        eDateOfBirth().setValue(date);
                    }
                    else {
                        ePatientIdentity().setValue(email);
                        while(true) if($(byAttribute("role", "tooltip")).waitUntil(visible, 5000).$("div").$("ul").$$("li").toArray().length == 1) { $(byAttribute("role", "tooltip")).$("div").$("ul").click(); break;}
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
                        while(true) if($(byAttribute("role", "tooltip")).waitUntil(visible, 5000).$("div").$("ul").$$("li").toArray().length == 1) { $(byAttribute("role", "tooltip")).$("div").$("ul").click(); break;}
                    }
                    return this;
                }
                public NewCase_abstract checkAll(){
                    if(!eFirstName().is(visible))eEmail().shouldHave(value(email));
                    eDateOfBirth().shouldHave(value(date));
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
            public class Cases{
                public SelenideElement eAddCase(){ return $(".MuiButton-contained"); }
                public SelenideElement eSearch(){ return $(".MuiInputBase-inputAdornedStart"); }
                public SelenideElement eTitle(){ return $("#headerText"); }
                public ElementsCollection eItems(){ return $(".MuiTableBody-root").$$(".MuiTableRow-root"); }
                public void clickAddCase() { eAddCase().click(); }
                public void clickSearch() { eSearch().click(); }
                public class NewCase extends NewCase_abstract{}
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
        private String lastCode(){
            time = $(".mail-MessageSnippet-Item_dateText").getAttribute("title").substring(18);
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

package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class ClinicianR20B {
    public static String siteurl = "https://alpha.incytesdata-dev.com/",
            email = "viikysia.zaxarowa1989+clinician123@gmail.com",
            password = "261090inCytes",
            existingPassword = password,
            newpassword = "261090inCytes";

    public void EnterEmail() {
        $(name("email")).setValue(email);
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void clickTCradio() {
        $(byAttribute("type", "checkbox")).click();
    }
    public void clickNextButton() {
        $("span.MuiButton-label").shouldHave(text("SUBMIT")).click();
    }
    public void clickChangePasswordButton() {
        $(xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/div[2]/div/button[3]/span[1]")).click();
    }
    public void EnterConfirmPassword() {
        $(name("confirmPassword")).setValue(newpassword);
    }
    public void clickSaveButton() {
        $(xpath("/html/body/div[3]/div[3]/div/div[2]/form/div/div/div/div[5]/button")).click();
    }

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration("https://qa.incytesdata-dev.com/auth/register");
        reg.open();
        reg.setAll("Dmitry", "Polsky", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!", "261090inCytes!");
        reg.wRegistration().cRegistration().clickNext();
        file.writeText(String.valueOf(count+1), false);
        $$("span.MuiButton-label").findBy(text("Отправить код подтверждения ещё раз")).shouldBe(visible);
        $$(byText("Required")).shouldHave(size(1));
    }
}
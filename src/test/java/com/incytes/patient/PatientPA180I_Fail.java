package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class PatientPA180I_Fail {
    public static String siteurl = "https://qa-patient.incytesdata-dev.com/",
                         password = "261090inCytes",
                         existingPassword = password,
                         newpassword = "261090inCytes";

    public void EnterEmail() {
        $(name("email")).setValue("andrew.grabovskiy+patient3a@gmail.com");
    }
    public void EnterPassword() {
        $(name("password")).setValue(password);
    }
    public void clickSignInButton() {
        $(xpath("//*[@id='root']/div/form/div/div/div[8]/button")).click();
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
        open(siteurl + "auth/register" + "?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgEahnJR/2mr5zCThWNvjP4yAAAAzjCBywYJKoZIhvcNAQcGoIG9MIG6AgEAMIG0BgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDAr6R0ndDK/8VnyiSAIBEICBhrClQS+TOfauhGqUw8qX6ApzpxlWq4vS43H+XkFcPPQUI26UL/+M1XuzfFkua8MDMmG48UyKnu3+KzapA7Xb6OwBtHN7qzO0WarXI38q4pspfY86J4068nhWyQTJUKD/fojG7po+bzYMFTvUFEbeOau4Cz+66IUb+aAoqnZLxYv1IWcZ3hG4");
        EnterEmail();
        EnterPassword();
        EnterConfirmPassword();
        clickSignInButton();
        $(byText("app.registration.duplicateUser")).waitUntil(visible, 10000);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

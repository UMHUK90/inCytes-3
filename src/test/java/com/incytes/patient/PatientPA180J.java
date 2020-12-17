package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PatientPA180J {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open("?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgEahnJR/2mr5zCThWNvjP4yAAAAzjCBywYJKoZIhvcNAQcGoIG9MIG6AgEAMIG0BgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDAr6R0ndDK/8VnyiSAIBEICBhrClQS+TOfauhGqUw8qX6ApzpxlWq4vS43H+XkFcPPQUI26UL/+M1XuzfFkua8MDMmG48UyKnu3+KzapA7Xb6OwBtHN7qzO0WarXI38q4pspfY86J4068nhWyQTJUKD/fojG7po+bzYMFTvUFEbeOau4Cz+66IUb+aAoqnZLxYv1IWcZ3hG4");
        reg.setAll("andrew.grabovskiy+patient32a@gmail.com", Main.password, Main.password, "", "", "", "", "").wRegistration();
        reg.clickLogin();
        $("p").shouldHave(text("Ваш лечащий врач приглашает вас в inCytes"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}

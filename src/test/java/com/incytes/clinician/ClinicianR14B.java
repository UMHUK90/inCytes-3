package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR14B {
    @Test
    void opening(){
        /*String[] characters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String str = new String();
        Random rand = new Random();
        boolean[] checking = new boolean[3];
        for(int step = characters.length; step > 0; step--){
            str = rand.nextInt() == 1 ? characters[rand.nextInt(26)] : rand.nextInt(10);
        }*/
        open("https://alpha.incytesdata-dev.com/auth/register");
        $(By.name("password")).setValue("Alpha77");
        $(By.name("email")).setValue("Tkkjdsg@gmail.com");
        $(byAttribute("type", "checkbox")).click();
        $(".MuiButton-label").click();
        $("p.Mui-error").shouldBe(Condition.visible);
    }
}

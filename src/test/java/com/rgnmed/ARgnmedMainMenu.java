package com.rgnmed;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ARgnmedMainMenu {
    @Test(invocationCount = 1) // invocationCount attribute defines the number of test runs
    public void b_test() {
        open("https://www.rgnmed.com/");
        $(".hero__slide__text__top").waitUntil(visible, 10000).shouldHave(text("We co-invest in establishing, operating and expanding"));
        $(".menu-hamburger").click();
        $(".menu-item-101").shouldBe(visible).click();
    }
}
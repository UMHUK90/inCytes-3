package com.incytes.clinician;

import com.codeborne.selenide.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianD171A {
    @Test
    public void method(){
        Configuration.timeout = 15000;
        ClinicianR17A pa182g = new ClinicianR17A();
        Main main = new Main();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutException(pa182g::OpenTwoTabs);
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Login login = main.new Login().open();
        login.setAll("qwertyuiop17091709+" + (count-1) + "@yandex.by", Main.password + "!").wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickBuySubscription();
        dashBoard.eCheckOut_Subscription().shouldBe(Condition.visible);
        dashBoard.eXClose_Subscription().shouldBe(Condition.visible);
        dashBoard.eText_Subscription().shouldHave(Condition.text("Subscriptions are month to month, payable in advance, and cancellable at any time."));
        dashBoard.eTitle_Subscription().shouldHave(Condition.text("Purchase Subscription"));
        dashBoard.clickCheckOut_Subscription();
        dashBoard.eXClose_Subscription().shouldBe(Condition.visible);
        dashBoard.eSaveCard_Card().shouldBe(Condition.visible);
        $(byText("Add a credit card to your account.")).shouldBe(Condition.visible);
        $("img").shouldBe(Condition.visible);
        $(byText("By clicking below I authorize inCytes™ to store and bill my card for new subscriptions and cases.")).shouldBe(Condition.visible);
        dashBoard.writeCardNumber_Card("4111 1111 1111 1111", "10/25", "111", "11111"); //какая карта
        dashBoard.clickSaveCard_Card();
        $("h3").shouldHave(Condition.text("Confirm Changes"));
        dashBoard.eXClose_Subscription().shouldBe(Condition.visible);
        $(byText("Updating total users available")).shouldBe(Condition.visible);
        $(byText("25$")).shouldBe(Condition.visible);
        $(byText("new monthly cost")).shouldBe(Condition.visible);
        $(byText("You are adding 0 cases. Select the pay button to pay with visa ending in")).shouldBe(Condition.visible);
        dashBoard.ePay_Paying().shouldBe(Condition.visible).click();
        Main.eBottomMessage().shouldHave(Condition.text("Payment Successful"));
        dashBoard.eBuySubscription().shouldHave(Condition.text("BUY CASES")).shouldBe(Condition.visible);
        dashBoard.clickCircles();
    }
}

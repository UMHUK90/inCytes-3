package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianD171B_Fail {
    @Test
    public void method(){
        Configuration.timeout = 25000;
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
        Selenide.sleep(2000);
        dashBoard.clickCheckOut_Subscription();
        dashBoard.eXClose_Subscription().shouldBe(Condition.visible);
        dashBoard.eSaveCard_Card().shouldBe(Condition.visible);
        $(byText("Add a credit card to your account.")).shouldBe(Condition.visible);
        $("img").shouldBe(Condition.visible);
        $(byText("By clicking below I authorize inCytes™ to store and bill my card for new subscriptions and cases.")).shouldBe(Condition.visible);
        dashBoard.clickSaveCard_Card();
        Main.eBottomMessage().shouldHave(Condition.text("Your card number is incomplete."));
        dashBoard.eSaveCard_Card().shouldBe(Condition.visible);
        Selenide.sleep(5000000);
        // 30 - A white random window in fast click
        // 38... - Don't appears and nothing is happen
    }
}

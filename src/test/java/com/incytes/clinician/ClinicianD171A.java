package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ClinicianD171A {
    @Test
    public void OpenTwoTabs() {
        Main main = new Main("En");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.setAll("Dmitry", "Polsky", "qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!", "261090inCytes!");
        reg.wRegistration().cRegistration().clickNext();
        file.writeText(String.valueOf(count+1), false);
        Main.newTab();
        switchTo().window(1);
        Main.GetCodeWithYandex getcode = main.new GetCodeWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        String code = getcode.fastCode();
        switchTo().window(0);
        reg.submitCode(code);
        Main.Login login = main.new Login();
        login.isVisible();
        login.setAll("qwertyuiop17091709+" + count + "@yandex.by", "261090inCytes!").wLogin().signIn();
    }
}

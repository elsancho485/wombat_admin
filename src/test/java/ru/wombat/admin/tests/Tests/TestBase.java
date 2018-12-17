package ru.wombat.admin.tests.Tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeSuite;
import ru.wombat.admin.tests.DataAndHelpers.UserData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestBase extends UserData {

    @BeforeSuite
    public void login() { //Логинка
        Configuration.browser = "chrome";
        String login = "grades_admin@smedialink.com";
        String password = "iac5jiB8Y";
        open("http://0.0.0.0:3000/login");
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Все метрики в одной системе!'])[1]/following::button[1]")).click();
        switchTo().window(1);
        sleep(5000);
//        try {
//            $(By.id("passp-field-login")).setValue(login).pressEnter();
//            $(By.id("passp-field-passwd")).setValue(password).pressEnter();
//        } catch (Throwable e) {
//            $(By.name("login")).setValue(login);
//            $(By.name("passwd")).setValue(password).pressEnter();
//        }
        $(By.name("login")).setValue(login);
        boolean passwordField = $(By.name("passwd")).isDisplayed();
        if (passwordField) {
            $(By.name("passwd")).setValue(password).pressEnter();
        } else{
            $(By.id("passp-field-login")).pressEnter();
            $(By.id("passp-field-passwd")).setValue(password).pressEnter();
        }

        $(By.id("nb-2")).click();
        switchTo().window(0);
        sleep(1000);
        $(By.id("menu__users")).click();
        }

    public void goToAddUserForm() { //Переход к форме создания сотрудника
        $(By.className("button__src-shared-AddButton-__3G-")).click();
    }

    public void goToEditUserData() { //Переход к форме редактирования сотрудника
        sleep(500);
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Активные'])[1]/following::p[1]")).click();
    }

    public void archivateUser() { //Отправка сотрудника из 1 ячейки в архив
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пользователь'])[1]/following::img[2]")).click();
        $(byText("Да")).click();
        sleep(5000);
    }

    public void upgradeUser() { //Повышение грейда для сотрудника из 1 ячекйи на один грейд
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пользователь'])[1]/following::img[1]")).click();
        sleep(5000);
        $(By.cssSelector("div[class^='Select Select--single has-value']")).click();
        $$(By.cssSelector("div[class^='Select-menu-outer']")).findBy(text("G" + String.valueOf(getNextGrade()))).click();
        $(By.id("salaryBonus")).clear();
        $(By.id("salaryBonus")).setValue("12345");
        $(byText("Отправить")).click();
    }

    public void checkingUpgradeUser() { //Проверка на то,что в первой ячейке грейд увелился на 1
        $(By.cssSelector("div[class^='grade__src-users-components-UsersListItem-__etq']")).shouldHave(text("G" + String.valueOf(getNextGrade())));
    }

    public void searchCreatedUserInList() { //Ищем имя и фамилию, созданного юзера в первой строке списка
        $(By.className("name__src-users-components-UsersListItem-__1eu")).waitUntil(text(firstname().toUpperCase() + " " + lastname().toUpperCase()), 50000);
    }

    public void searchUserNameInEditFormBefore() { //Поиск имени и фамилии в форме редактирования сотрудника до совершения действия с ним
        $(By.className("name__src-users-components-UsersListItem-__1eu")).waitUntil(visible, 3000);
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Активные'])[1]/following::p[1]")).click();
        getFirstNameInEditForm();
        getLastNameInEditForm();
        $(By.className("closeButton__src-users-components-ReEditUserForm-__20P")).click();
    }

    public void searchUserNameInEditFormAfter() { //Поиск имени и фамилии в форме редактирования сотрудника после совершения действия с ним
        $(By.className("name__src-users-components-UsersListItem-__1eu")).waitUntil(visible, 3000);
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Активные'])[1]/following::p[1]")).click();
        getFirstNameInEditForm();
        getLastNameInEditForm();
        $(By.className("name__src-users-components-UsersListItem-__1eu")).shouldNotHave(value(getFirstNameInEditForm() + getLastNameInEditForm()));
        $(By.className("closeButton__src-users-components-ReEditUserForm-__20P")).click();
    }

    public void searchForEmptyFieldsError() { // Поиск ошбибки о пустом поле
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='обязательно'])[1]/following::div[2]"));
        sleep(1000);
    }

    public void searchForExistingEmailError() { // Поиск ошибки о том, что email занят
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='обязательно для заполнения'])[1]/following::div[16]"));
        sleep(1000);
    }

    public void closeAddUserForm() { //Клик по кнопке закрытия формы добавления сотрудника
        $(By.className("closeButton__src-users-components-NewUserForm-__Isr")).click();
    }

}

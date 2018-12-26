package ru.wombat.admin.tests.Tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.sun.jdi.IntegerValue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeSuite;
import ru.wombat.admin.tests.DataAndHelpers.UserData;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestBase extends UserData {

    private String nameForArhive;

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
        } else {
            $(By.id("passp-field-login")).pressEnter();
            $(By.id("passp-field-passwd")).setValue(password).pressEnter();
        }

        $(By.id("nb-2")).click();
        switchTo().window(0);
        sleep(1000);
        $(By.id("menu__users")).click();
    }


    public void goToAddUserForm() { //Переход к форме создания сотрудника
        $(By.cssSelector("div[class^='button__src-shared-AddButton-__3G-']")).find(byText("+")).click();
    }

    public void goToEditUserData() { //Переход к форме редактирования сотрудника
//        sleep(5000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 10000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).click();
    }

    public void archivateUser() { //Отправка сотрудника из 1 ячейки в архив
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пользователь'])[1]/following::img[2]")).click();
        $(byText("Да")).click();
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 5000);
    }

    public void exitArchivateForm() { //Выход из формы архивирования сотрудника
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пользователь'])[1]/following::img[2]")).click();
        $(byText("Нет")).click();
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 5000);
    }

    public void upgradeUser() { //Повышение грейда для сотрудника из 1 ячекйи на один грейд
//        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пользователь'])[1]/following::img[1]")).click();
//        sleep(5000);
        $(By.cssSelector("div[class^='Select Select--single has-value']")).waitUntil(visible, 5000);
        $(By.cssSelector("div[class^='Select Select--single has-value']")).click();
        $(By.cssSelector("div[class^='Select-menu']")).find(byText("G" + String.valueOf(getNextGrade()))).click();
        $(By.id("salaryBonus")).clear();
        $(By.id("salaryBonus")).setValue("12345");
    }

    public void submitUserUpgrade() {
        $(byText("Отправить")).click();
        $(By.cssSelector("div[class^='modal__src-users-components-UsersTransfer-__zVA']")).waitUntil(disappear, 20000);
    }

    public void goToUpgradeUserForm() {
//        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пользователь'])[1]/following::img[1]")).click();
        $(By.cssSelector("img[src='/assets/img/upgrade.94ebb9d2d73ee7dbf0705581ecd96ddc.svg']")).click();
        $(By.cssSelector("div[class^='Select Select--single has-value']")).waitUntil(visible, 5000);
    }

    public void closeUpgradeForm() {
        $(byText("Отмена")).click();
        $(By.cssSelector("div[class^='modal__src-users-components-UsersTransfer-__zVA']")).waitUntil(disappear, 20000);
    }

//    public void checkingUpgradeUser() { //Проверка на то,что в первой ячейке грейд увелился на 2
//        sleep(3000);
//        $(By.cssSelector("div[class^='grade__src-users-components-UsersListItem-__etq']")).shouldHave(text("G" + String.valueOf(getNextGrade())));
//    }

    public void searchCreatedUserInList() { //Ищем имя и фамилию, созданного юзера в первой строке списка
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 10000);
        $(By.className("name__src-users-components-UsersListItem-__1eu")).waitUntil(text(firstname().toUpperCase() + " " + lastname().toUpperCase()), 50000);
    }

    public void searchUncreaterUserInList() { //Проверка списка на то, что 1 ячейка осталось в прежнем состоянии
        $(By.className("name__src-users-components-UsersListItem-__1eu")).shouldHave(text(getUserNameFromList()));
    }

    public void searchUserNameBefore() { //Поиск имени и фамилии сотрудника в списке до совершения действия с ним
        sleep(5000);
        $(By.className("name__src-users-components-UsersListItem-__1eu")).waitUntil(visible, 10000);
        String nameWithSubtext = $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).getText();
        nameForArhive = nameWithSubtext.split("Разработчик")[0];
    }

    public void searchUserNameAfter() { //Поиск имени и фамилии в форме редактирования сотрудника после совершения действия с ним
        sleep(3000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 5000);
        sleep(5000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).shouldNotHave(text(nameForArhive));
    }

    public void searchUserNameAfterExitArchiveForm() { //Поиск имени и фамилии в форме редактирования сотрудника после совершения действия с ним
        sleep(3000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 10000);
        sleep(10000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).shouldHave(text(nameForArhive));
    }

    public void searchForEmptyFieldsError() { // Поиск ошбибки о пустом поле
        $(By.cssSelector("div[class^='errors__src-shared-forms-__2sW']")).find(byText("обязательно"));
    }

    public void searchForExistingEmailError() { // Поиск ошибки о том, что email занят
        $(By.cssSelector("div[class^='rrt-text']")).waitUntil(visible, 5000);
    }

    public void closeAddUserForm() { //Клик по кнопке закрытия формы добавления сотрудника
        $(By.className("closeButton__src-users-components-NewUserForm-__Isr")).click();
    }

    public void changeDisplayingToGrid() { // Смена отображения списка юзеров на грид
        $(By.className("toggleViewIcon__src-users-components-UsersFilters-__1YL")).click();
        $(By.className("cell__src-users-components-UsersGrid-__1Dw")).waitUntil(visible, 10000);
    }

    public void changeDisplayingToList() { //Смена отображения с грида на список
        $(By.className("toggleViewIcon__src-users-components-UsersFilters-__1YL")).click();
        sleep(5000);
    }

    public void searchUser() { //Поиск юзера по имени и фамилии
        getUserNameFromList();
        $(By.name("rrf.userFilters.name")).setValue(getUserNameFromList());
//        sleep(3000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 10000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).shouldHave(text(getUserNameFromList()));
        for (int i = 0; i < 20; i++) {
            $(By.name("rrf.userFilters.name")).sendKeys(Keys.BACK_SPACE);
        }
    }

    public void clickOnPaginationButtonInList() { //Клик по кнопке подгрузки списка
        $(By.cssSelector("div[class='nextPageButton__src-shared-NextPageButton-__3Yd']")).click();
        sleep(4000);
    }

    public int checkingSizeOfListBeforePagination() { //Подсчет размера списка до подгрузки следующей страницы
        $(By.cssSelector("div[class='grade__src-users-components-UsersListItem-__etq']")).waitUntil(visible, 5000);
        int sizeBeforePagination = $$(By.cssSelector("div[class='grade__src-users-components-UsersListItem-__etq']")).size();
        return sizeBeforePagination;
    }


    public int checkingSizeOfListAfterPagination() { //Подсчет размера списка после подгрузки списка
        int sizeAfterChecking = $$(By.cssSelector("div[class='grade__src-users-components-UsersListItem-__etq']")).size();
        return sizeAfterChecking;
    }

    public void closeEditUserForm() { //Закрытие формы редактирования юзера
        $(By.className("closeButton__src-users-components-ReEditUserForm-__20P")).waitUntil(visible, 5000);
        $(By.className("closeButton__src-users-components-ReEditUserForm-__20P")).click();
        sleep(5000);
//        $(By.className("closeButton__src-users-components-ReEditUserForm-__20P")).waitUntil(disappear, 5000);
    }

    public void checkingCacheAddUserForm() { //Проверка того,что поля в форме создания пустые
        $(By.name("rrf.user.firstName")).shouldBe(empty);
        $(By.name("rrf.user.lastName")).shouldBe(empty);
        $(By.name("rrf.user.email")).shouldBe(empty);
        $(By.name("rrf.user.phone")).shouldBe(empty);
        $(By.name("rrf.user.telegram")).shouldBe(empty);
        $(By.name("rrf.user.worksSince")).shouldBe(empty);
        $(By.name("rrf.user.birthDate")).shouldBe(empty);
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[8]/following::input[1]")).shouldBe(empty);
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[9]/following::input[1]")).shouldBe(empty);
    }

    public void goToArchivateUsersList() { //Переход к списку архивированных юзеров
        $$(By.cssSelector("div[class^='Select-value']")).findBy(text("Активные")).click();
        $("div[class^='Select-menu']").find(byText("Архив")).click();
    }

    public void searchUserNameInArchivateUserList() { //Поиск имени и фамилии в форме редактирования сотрудника после совершения действия с ним
//        sleep(3000);
//        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 5000);
//        sleep(5000);
        $(By.name("rrf.userFilters.name")).setValue(nameForArhive);
//        sleep(3000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 10000);
        sleep(5000);
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).shouldHave(text(nameForArhive));
        for (int i = 0; i < 20; i++) {
            $(By.name("rrf.userFilters.name")).sendKeys(Keys.BACK_SPACE);
        }
    }

    public void goToActiveUsersListFromArchive() { //Переход к списку активных юзеров
        $$(By.cssSelector("div[class^='Select-value']")).find(text("Архив")).waitUntil(enabled, 5000).click();
        $("div[class^='Select-menu']").find(byText("Активные")).waitUntil(enabled, 5000).click();
    }

    public void goToActiveUsersListFromAllUsersList() {
        $$(By.cssSelector("div[class^='Select-value']")).find(text("Все")).waitUntil(enabled, 10000).click();
        $("div[class^='Select-menu']").find(byText("Активные")).waitUntil(enabled, 10000).click();
    }

    public void searchUsersByGrade() { //Поиск юзеров по грейду
        $(By.cssSelector("div[class^='grade__src-users-components-UsersFilters-__1jp']")).click();
        $("div[class^='Select-menu']").find(byText("G1")).click();
    }

    public void checkingUserListByGrade() { //Проверка на то, что грейды в списке соотвествуют поиску
        sleep(5000);
        $(By.cssSelector("div[class^='grade__src-users-components-UsersListItem-__etq']")).waitUntil(exist, 20000).shouldHave(text("G1"));
    }

    public void goToAllGradesUsers() { //Переход к списку всех юзеров
        $(By.cssSelector("div[class^='grade__src-users-components-UsersFilters-__1jp']")).click();
        $("div[class^='Select-menu']").find(byText("Все")).click();
    }

    public void goToRecoveryForm() { //Переход к  форме восстановления юзера
        $(By.cssSelector("img[src^='/assets/img/archiveGrey.e1162d0ac53af40e2b3b93b50451bf08.svg']")).click();
    }

    public void recoveryUser() { //Действия с формой восстановления юзера
        $(byText("Да")).click();
        $(By.cssSelector("div[class^='ReactModal__Content ReactModal__Content--after-open substrate__src-users-components-UserModalAlert-__WkU']")).waitUntil(disappear, 10000);
    }

    public void searchUserAfterRecovery() { //Поиск восстановленного юзера в списке
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).shouldHave(text(nameForArhive));
    }

    public void cancelRecoveryUser() { //Отмена восстановления юзера
        $(byText("Нет")).click();
        $(By.cssSelector("div[class^='ReactModal__Content ReactModal__Content--after-open substrate__src-users-components-UserModalAlert-__WkU']")).waitUntil(disappear, 10000);
    }

    public void goToAllProfessionUsersScreen() { //Переход к спику юзеров по всем профессиям
        $("form[class^='form__src-app-components-Header-__3EW']").find(byText("Разработчик")).click();
        $("div[class^='Select-menu']").find(byText("Все")).click();
    }

    public void checkingNonDisplayingAddUserButton() {//Проверка на то,что нет кнопки добавления юзера, если в филиал/профессия стоит "Все"
        $(By.cssSelector("div[class^='button__src-shared-AddButton-__3G-']")).shouldNotBe();
    }

    public void goToDeveloperUsers() { //Переход к списку разработчиков
        $("form[class^='form__src-app-components-Header-__3EW']").find(byText("Все")).click();
        $("div[class^='Select-menu']").find(byText("Разработчик")).click();
    }

    public void searchUsersByTeam() { //Поиск юзеров по команде
        $(By.name("rrf.userFilters.group")).setValue("Dev Team");
    }

    public void checkingTeam() { // Проверка результата поиска по команле
        $(By.cssSelector("div[class='team__src-users-components-UserGroups-__1pO']")).waitUntil(visible, 10000);
        $(By.cssSelector("div[class='team__src-users-components-UserGroups-__1pO']")).shouldHave(text("Dev Team"));
    }

    public void checkingErrorAboutEmptyFieldsEditUserForm() { //Проверка на отображение ошибки о пустом поле при редактировании юзера
        $(By.cssSelector("div[class='caption__src-shared-forms-__2hP']")).shouldBe(visible);
    }

    public void checkingUserInfo() { //Проверка информации о юзере после его создания(имя,фамилия, грейд, команда)
        sleep(5000);
        searchCreatedUserInList();
        $("img[src^='/assets/img/defaultUserpic.9fcad495b4ff095188a700daa7e75c3a.svg']").shouldNotBe();
        $("div[class^='grade__src-users-components-UsersListItem-__etq']").shouldHave(text("G4"));
        checkingTeam();
    }

    public void searchCreationUserInGrid() { // Поиск созданного юзера при гриде
        sleep(5000);
        $("li[class^='cell__src-users-components-UsersGrid-__1Dw']").waitUntil(text(firstname().toUpperCase() + " " + lastname().toUpperCase()), 500000);
    }

    public void clickOnPaginationButtonInGrid() { //Клик по кнопке подгрузки списка в гриде
        $("div[class^='nextPageButton__src-shared-NextPageButton-__3Yd grid__src-shared-NextPageButton-__27k']").click();
        sleep(5000);
    }

    public int checkingSizeOfGridBeforePagination() { //Подсчет размера списка до подгрузки следующей страницы
        $(By.cssSelector("div[class='userpic__src-users-components-UsersGrid-__Nag']")).waitUntil(visible, 5000);
        int sizeBeforePagination = $$(By.cssSelector("div[class='userpic__src-users-components-UsersGrid-__Nag']")).size();
        return sizeBeforePagination;
    }


    public int checkingSizeOfGridAfterPagination() { //Подсчет размера списка после подгрузки списка
        int sizeAfterChecking = $$(By.cssSelector("div[class='userpic__src-users-components-UsersGrid-__Nag']")).size();
        return sizeAfterChecking;
    }

    public void goToEditFormInGrid() { //Переход к форме редактирования юзера в гриде
        $("li[class^='cell__src-users-components-UsersGrid-__1Dw']").click();
    }

    public void goToRemoteEmployeeList() { //Переход к списку удаленных сотрудников
        $("form[class^='form__src-app-components-Header-__3EW']").find(byText("Краснодар")).click();
        $("div[class^='Select-menu-outer']").find(byText("Удаленный сотрудник")).click();
    }

    public void goToKrasnodarEmployeeListFromRemoteUsers() { //Переход к списку сотрудников из Краснодара
        $("form[class^='form__src-app-components-Header-__3EW']").find(byText("Удаленный сотрудник")).click();
        $("div[class^='Select-menu-outer']").find(byText("Краснодар")).click();
    }

    public void goToKrasnodarEmployeeListFromAllUsers() { //Переход к списку сотрудников из Краснодара
        $("form[class^='form__src-app-components-Header-__3EW']").find(byText("Все")).click();
        $("div[class^='Select-menu-outer']").find(byText("Краснодар")).click();
    }

    public void goToAllFilialsUsersList() { //Переход к списку юзеров из всех филиалов
        $("form[class^='form__src-app-components-Header-__3EW']").find(byText("Краснодар")).click();
        $("div[class^='Select-menu-outer']").find(byText("Все")).click();
    }

    public void checkingCityOfRemoteEmployee() { //Проверка на то, что в форме редактирования отображается "Удаленный сотрудник" в поле город,у удаленных сотрудников
        $(By.cssSelector("div[class^='Select-control']")).shouldHave(text("Удаленный сотрудник"));
    }

    public void checkingCityOfKrasnodarEmployee() { //Проверка на то, что в форме редактирования отображается "Краснодар" в поле город,у сотрудников из Краснодара
        $(By.cssSelector("div[class^='Select-control']")).shouldHave(text("Краснодар"));
    }

    public void checkingErrorAboutNegativeSalary() { //Проверка на отображении ошибки при попытке ввести отрицательную зарплату при апргейде юзера
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Зарплата'])[1]/following::label[1]")).shouldBe();
    }

    public void upgradeWithNegativeSalary() { //Заполнение формы апгрейда юзера с отрицательной зарплатой
        $(By.cssSelector("div[class^='Select Select--single has-value']")).waitUntil(visible, 5000);
        $(By.cssSelector("div[class^='Select Select--single has-value']")).click();
        $$(By.cssSelector("div[class^='Select-menu-outer']")).findBy(text("G" + String.valueOf(getNextGrade()))).click();
        $(By.id("salaryBonus")).clear();
        $(By.id("salaryBonus")).setValue("-12345");
        $(byText("Отправить")).click();
    }

    public void checkingNonDisplayingCreateUserInArchiveList() {
        $(By.cssSelector("div[class^='name__src-users-components-UsersListItem-__1eu']")).waitUntil(visible, 10000);
        $(By.className("name__src-users-components-UsersListItem-__1eu")).shouldNotHave(text(firstname().toUpperCase() + " " + lastname().toUpperCase()));
    }

    public void goToAllUsersScreen() {
        $$(By.cssSelector("div[class^='Select-value']")).findBy(text("Активные")).click();
        $("div[class^='Select-menu']").find(byText("Все")).click();
    }

    public int getSalaryCountField() {
       int salaryBefore = Integer.parseInt($(By.cssSelector("input[name^='salary']")).getValue());
       return salaryBefore;
    }

    public int checkingSalaryCountFields() {
        int salaryAfter = Integer.parseInt($(By.cssSelector("input[name^='salary']")).getValue());
        return salaryAfter;
    }

    public void checkingErrorAboutExistingEmailEditUser() {
        $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='обязательно для заполнения'])[1]/following::div[24]")).waitUntil(visible, 5000);
    }

}

package ru.wombat.admin.tests.Tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AddUserTest extends TestBase {

    @Test(priority = 1)
    public void adminCanOpenAndCloseAddUserFormTest() {
        goToAddUserForm();
        closeAddUserForm();
    }

    @Test(priority = 2)
    public void adminCanCloseAddFormWithFiledFieldsTest() {
        getUserNameFromList();
        goToAddUserForm();
        fillAddUserForm();
        closeAddUserForm();
        searchUncreaterUserInList();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(priority = 3)
    public void errorAboutExistingEmailAddUserTest() {
        goToAddUserForm();
        fillAddUserFormWithExistingEmail();
        submitUserCreation();
        searchlError();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(priority = 4)
    public void emptyFieldsErrorDisplayingAddUserTest() {
        goToAddUserForm();
        submitUserCreation();
        searchForEmptyFieldsError();
        closeAddUserForm();
    }

    @Test(priority = 5)
    public void displayingErrorAboutFieldsWithSpaceTest() {
        goToAddUserForm();
        fillAddUserFormWithSpace();
        submitUserCreation();
        searchForEmptyFieldsError();
        closeAddUserForm();
    }

    @Test(priority = 6)
    public void displayingDatePickerFormInAddUserFormTest() {
        goToAddUserForm();
        clickOnWorksSinceField();
        checkingDatePickerDisplaying();
        clickOnBirthDateField();
        checkingDatePickerDisplaying();
        closeAddUserForm();
    }

    @Test(priority = 7)
    public void cleanCacheAddUserFormDuringCloseFormTest() {
        goToAddUserForm();
        fillAddUserForm();
        closeAddUserForm();
        goToAddUserForm();
        checkingCacheAddUserForm();
        closeAddUserForm();
    }

    @Test(priority = 9)
    public void cleanCacheAddUserFormDuringAddUserTest() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        $(By.cssSelector("form[class^='form__src-users-components-NewUserForm-__xSP']")).waitUntil(disappear, 20000);
        goToAddUserForm();
        checkingCacheAddUserForm();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 11)
    public void adminCanAddNewUserTest() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
    }

    @Test(priority = 12)
    public void checkingUserInfoAfterCreationTest() {
        sleep(5000);
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
        checkingUserInfoAfterCreate();
    }

    @Test(priority = 13)
    public void displayingCreatedUserInAllUsersListTest() {
        goToAllUsersScreen();
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
        sleep(5000);
        goToActiveUsersListFromAllUsersList();
    }

}
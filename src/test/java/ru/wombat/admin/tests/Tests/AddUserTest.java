package ru.wombat.admin.tests.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class AddUserTest extends TestBase {

    @Test(priority = 1)
    public void adminCanOpenAndCloseAddUserForm() {
        goToAddUserForm();
        closeAddUserForm();
    }

    @Test(priority = 2)
    public void adminCanSearchUserByUsername() {
        searchUser();
    }

    @Test(priority = 3)
    public void aminCanChangeUserDisplayingToGrid() {
        changeDisplaying();
    }

    @Test(priority = 4)
    public void adminCanCloseAddFormWithFiledFields() {
        getUserNameFromList();
        goToAddUserForm();
        fillAddUserForm();
        closeAddUserForm();
        searchUncreaterUserInList();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5)
    public void adminCanAddNewUser() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6)
    public void adminCanEditUserData() {
        goToEditUserData();
        editFirstAndLastName();
        searchCreatedUserInList();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 7)
    public void adminCanUpgradeUser() {
        getGradeNumberBeforeUpgrade();
        upgradeUser();
        getGradeNumberAfterUpgrade();
        checkingUpgradeUser();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(priority = 8)
    public void errorAboutExistingEmail() {
        goToAddUserForm();
        fillAddUserFormWithExistingEmail();
        submitUserCreation();
        searchForExistingEmailError();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(priority = 9)
    public void emptyFieldsErrorDisplaying() {
        goToAddUserForm();
        submitUserCreation();
        searchForEmptyFieldsError();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 10)
    public void adminCanArchivateUser() {
        searchUserNameInEditFormBefore();
        archivateUser();
        searchUserNameInEditFormAfter();
    }

    @Test
    public void paginationTest() {
        int before = checkingSizeOfListBeforePagination();
        clickOnPaginationButton();
        int after = checkingSizeOfListAfterPagination();
        Assert.assertEquals(before +10, after);
    }

}
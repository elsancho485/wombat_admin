package ru.wombat.admin.tests.Tests;

import org.testng.annotations.Test;

public class AddUserTest extends TestBase {

    @Test(priority = 1)
    public void adminCanAddNewUser() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
    }

    @Test(priority = 5)
    public void emptyFieldsErrorDisplaying() {
        goToAddUserForm();
        submitUserCreation();
        searchForEmptyFieldsError();
        closeAddUserForm();
    }

    @Test(priority = 4)
    public void errorAboutExistingEmail() {
        goToAddUserForm();
        fillAddUserFormWithExistingEmail();
        submitUserCreation();
        searchForExistingEmailError();
        closeAddUserForm();
    }

    @Test(priority = 2)
    public void adminCanEditUserData() {
        goToEditUserData();
        editFirstAndLastName();
        searchCreatedUserInList();
    }

    @Test(priority = 6)
    public void adminCanArchivateUser() {
        searchUserNameInEditFormBefore();
        archivateUser();
        searchUserNameInEditFormAfter();
    }

    @Test(priority = 3)
    public void adminCanUpgradeUser() {
        getGradeNumberBeforeUpgrade();
        upgradeUser();
        getGradeNumberAfterUpgrade();
        checkingUpgradeUser();
    }
}
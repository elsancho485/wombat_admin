package ru.wombat.admin.tests.Tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class AddUserTest extends TestBase {

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void adminCanAddNewUser() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(priority = 5)
    public void emptyFieldsErrorDisplaying() {
        goToAddUserForm();
        submitUserCreation();
        searchForEmptyFieldsError();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(priority = 4)
    public void errorAboutExistingEmail() {
        goToAddUserForm();
        fillAddUserFormWithExistingEmail();
        submitUserCreation();
        searchForExistingEmailError();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void adminCanEditUserData() {
        goToEditUserData();
        editFirstAndLastName();
        searchCreatedUserInList();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6)
    public void adminCanArchivateUser() {
        searchUserNameInEditFormBefore();
        archivateUser();
        searchUserNameInEditFormAfter();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void adminCanUpgradeUser() {
        getGradeNumberBeforeUpgrade();
        upgradeUser();
        getGradeNumberAfterUpgrade();
        checkingUpgradeUser();
    }
}
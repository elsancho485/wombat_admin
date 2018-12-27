package ru.wombat.admin.tests.Tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class ArchiveAndRecoveryTests extends TestBase {

    @Test(priority = 12)
    public void exitFromArchivateUserFormTest() {
        searchUserNameBefore();
        exitArchivateForm();
        searchUserNameAfterExitArchiveForm();
    }

    @Test(priority = 13)
    public void closeRecoveryForm() {
        goToArchivateUsersList();
        searchUserNameBefore();
        goToRecoveryForm();
        cancelRecoveryUser();
        goToActiveUsersListFromArchive();
        searchUserNameAfterClose();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 8)
    public void adminCanArchivateUserTest() {
        searchUserNameBefore();
        archivateUser();
        searchUserNameAfter();
    }

    @Test(priority = 14)
    public void displayingArchivateUserInArchiveUsers() {
        searchUserNameBefore();
        archivateUser();
        goToArchivateUsersList();
        searchUserNameInArchivateUserList();
        goToActiveUsersListFromArchive();
    }

    @Test(priority = 15)
    public void recoveryUserTest() {
        goToArchivateUsersList();
        searchUserNameBefore();
        goToRecoveryForm();
        recoveryUser();
        goToActiveUsersListFromArchive();
        searchUserAfterRecovery();
    }

}

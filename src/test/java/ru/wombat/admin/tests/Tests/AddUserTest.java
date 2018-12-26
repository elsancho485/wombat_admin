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
    public void adminCanOpenAndCloseAddUserForm() {
        goToAddUserForm();
        closeAddUserForm();
    }

    @Test(priority = 2)
    public void adminCanCloseEditUserForm() {
        goToEditUserData();
        closeEditUserForm();
    }

    @Test(priority = 3)
    public void adminCanSearchUserByUsername() {
        searchUser();
    }

    @Test(priority = 4)
    public void adminCanChangeUserDisplayingToGridAndToList() {
        changeDisplayingToGrid();
        changeDisplayingToList();
    }


    @Test(priority = 5)
    public void adminCanCloseAddFormWithFiledFields() {
        getUserNameFromList();
        goToAddUserForm();
        fillAddUserForm();
        closeAddUserForm();
        searchUncreaterUserInList();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6)
    public void adminCanAddNewUserTest() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 7)
    public void adminCanEditUserDataTest() {
        goToEditUserData();
        editFirstAndLastName();
        searchCreatedUserInList();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 8)
    public void adminCanUpgradeUserTest() {
        int before = getGradeNumber();
        goToUpgradeUserForm();
        upgradeUser();
        submitUserUpgrade();
        int after = getGradeNumber();
        Assert.assertEquals(before + 1 , after);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(priority = 9)
    public void errorAboutExistingEmailAddUserTest() {
        goToAddUserForm();
        fillAddUserFormWithExistingEmail();
        submitUserCreation();
        searchForExistingEmailError();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(priority = 10)
    public void emptyFieldsErrorDisplayingAddUserTest() {
        goToAddUserForm();
        submitUserCreation();
        searchForEmptyFieldsError();
        closeAddUserForm();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 11)
    public void adminCanArchivateUserTest() {
        searchUserNameBefore();
        archivateUser();
        searchUserNameAfter();
    }


    @Test(priority = 12)
    public void recoveryUserTest() {
        goToArchivateUsersList();
        searchUserNameBefore();
        goToRecoveryForm();
        recoveryUser();
        goToActiveUsersListFromArchive();
        searchUserAfterRecovery();
    }

    @Test(priority = 13)
    public void closeRecoveryForm() {
        goToArchivateUsersList();
        searchUserNameBefore();
        goToRecoveryForm();
        cancelRecoveryUser();
        goToActiveUsersListFromArchive();
        searchUserNameAfter();
    }

    @Test(priority = 14)
    public void paginationTestInList() {
        int before = checkingSizeOfListBeforePagination();
        clickOnPaginationButtonInList();
        int after = checkingSizeOfListAfterPagination();
        Assert.assertEquals(before + 10, after);
    }

    @Test(priority = 15)
    public void cacheAddUserFormDuringCloseFormTest() {
        goToAddUserForm();
        fillAddUserForm();
        closeAddUserForm();
        goToAddUserForm();
        checkingCacheAddUserForm();
        closeAddUserForm();
    }

    @Test(priority = 16)
    public void cacheAddUserFormDuringAddUserTest() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        $(By.cssSelector("form[class^='form__src-users-components-NewUserForm-__xSP']")).waitUntil(disappear, 20000);
        goToAddUserForm();
        checkingCacheAddUserForm();
        closeAddUserForm();
    }

    @Test(priority = 17)
    public void displayingArchivateUserInArchiveUsers() {
        searchUserNameBefore();
        archivateUser();
        goToArchivateUsersList();
        searchUserNameInArchivateUserList();
        goToActiveUsersListFromArchive();
    }

    @Test(priority = 18)
    public void exitFromArchivateUserFormTest() {
        searchUserNameBefore();
        exitArchivateForm();
        searchUserNameAfterExitArchiveForm();
    }

    @Test(priority = 19)
    public void exitFromUpgradeFormTest() {
        goToUpgradeUserForm();
        closeUpgradeForm();
    }

    @Test(priority = 20)
    public void searchUsersByGradeTest() {
        searchUsersByGrade();
        checkingUserListByGrade();
        goToAllGradesUsers();
    }

    @Test(priority = 21)
    public void displayingErrorAboutFieldsWithSpaceTest() {
        goToAddUserForm();
        fillAddUserFormWithSpace();
        submitUserCreation();
        searchForEmptyFieldsError();
        closeAddUserForm();
    }

    @Test(priority = 22)
    public void nonDisplayingAddUserButtonOnAllProfessionsUsersScreenTest(){
        goToAllProfessionUsersScreen();
        checkingNonDisplayingAddUserButton();
        goToDeveloperUsers();
    }

    @Test(priority = 23)
    public void searchUsersByTeamTest() {
        searchUsersByTeam();
        checkingTeam();
    }

    @Test(priority = 24)
    public void emptyFieldsErrorEditUserTest() {
        goToEditUserData();
        clearFieldsEditUserForm();
        checkingErrorAboutEmptyFieldsEditUserForm();
        closeEditUserForm();
    }

    @Test(priority = 25)
    public void checkingUserInfoAfterCreationTest() {
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
        checkingUserInfo();
    }

    @Test(priority = 26)
    public void creationUserInGridTest() {
        changeDisplayingToGrid();
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreationUserInGrid();
        changeDisplayingToList();
    }

    @Test(priority = 27)
    public void paginationInGridTest() {
        changeDisplayingToGrid();
        int before = checkingSizeOfGridBeforePagination();
        clickOnPaginationButtonInGrid();
        int after =checkingSizeOfGridAfterPagination();
        Assert.assertEquals(before + 10, after);
        changeDisplayingToList();
    }

    @Test(priority = 28)
    public void openAndCloseAddUserFormInGrid() {
        changeDisplayingToGrid();
        goToAddUserForm();
        closeAddUserForm();
        changeDisplayingToList();
    }

    @Test(priority = 29)
    public void closeEditUserFormInGrid() {
        changeDisplayingToGrid();
        goToEditFormInGrid();
        closeEditUserForm();
        changeDisplayingToList();
    }

    @Test(priority = 30)
    public void checkingRemoteEmployeeTest() {
        goToRemoteEmployeeList();
        goToEditUserData();
        checkingCityOfRemoteEmployee();
        closeEditUserForm();
        goToKrasnodarEmployeeListFromRemoteUsers();
    }

    @Test(priority = 31)
    public void checkingKrasnodarEmployeeTest() {
        goToEditUserData();
        checkingCityOfKrasnodarEmployee();
        closeEditUserForm();
    }

    @Test(priority = 32)
    public void errorAboutNegativeSalaryTest() {
        goToUpgradeUserForm();
        upgradeWithNegativeSalary();
        checkingErrorAboutNegativeSalary();
        closeUpgradeForm();
    }

    @Test(priority = 33)
    public void nonDisplayingAddUserButtonOnAllFilialsUsersScreenTest() {
        goToAllFilialsUsersList();
        checkingNonDisplayingAddUserButton();
        goToKrasnodarEmployeeListFromAllUsers();
    }

    @Test(priority = 34)
    public void displayingCreatedUserInAllUsersListTest() {
        goToAllUsersScreen();
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreatedUserInList();
        goToActiveUsersListFromAllUsersList();
    }


    @Test(priority = 35)
    public void nonDisplayingCreatedUserInArchivateListTest() {
        goToArchivateUsersList();
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        checkingNonDisplayingCreateUserInArchiveList();
        goToActiveUsersListFromArchive();
    }

    @Test(priority = 36)
    public void salaryCorrectCountTest() {
        goToUpgradeUserForm();
        int before = getSalaryCountField();
        upgradeUser();
//        sleep(5000);
        int after = checkingSalaryCountFields();
        Assert.assertEquals(before + 12345, after);
        closeUpgradeForm();
    }
    
}
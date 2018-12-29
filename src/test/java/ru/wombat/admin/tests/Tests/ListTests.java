package ru.wombat.admin.tests.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ListTests extends TestBase {

    @Test(priority = 26)
    public void paginationTestInListTest() {
        int before = checkingSizeOfListBeforePagination();
        clickOnPaginationButtonInList();
        int after = checkingSizeOfListAfterPagination();
        Assert.assertEquals(before + 10, after);
    }

    @Test(priority = 27)
    public void checkingRemoteEmployeeTest() {
        goToRemoteEmployeeList();
        goToEditUserData();
        checkingCityOfRemoteEmployee();
        closeEditUserForm();
        goToKrasnodarEmployeeListFromRemoteUsers();
    }

    @Test(priority = 28)
    public void checkingKrasnodarEmployeeTest() {
        goToEditUserData();
        checkingCityOfKrasnodarEmployee();
        closeEditUserForm();
    }

    @Test(priority = 29)
    public void nonDisplayingAddUserButtonOnAllProfessionsUsersScreenTest(){
        goToAllProfessionUsersScreen();
        checkingNonDisplayingAddUserButton();
        goToDeveloperUsers();
    }

    @Test(priority = 30)
    public void nonDisplayingAddUserButtonOnAllFilialsUsersScreenTest() {
        goToAllFilialsUsersList();
        checkingNonDisplayingAddUserButton();
        goToKrasnodarEmployeeListFromAllUsers();
    }

//    @Test(priority = 29)
//    public void nonDisplayingCreatedUserInArchivateListTest() {
//        goToArchivateUsersList();
//        goToAddUserForm();
//        fillAddUserForm();
//        submitUserCreation();
//        checkingNonDisplayingCreateUserInArchiveList();
//        goToActiveUsersListFromArchive();
//    }
}

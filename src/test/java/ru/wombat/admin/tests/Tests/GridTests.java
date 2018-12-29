package ru.wombat.admin.tests.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GridTests extends TestBase {

    @Test(priority = 21)
    public void adminCanChangeUserDisplayingToGridAndToListTest() {
        changeDisplayingToGrid();
        changeDisplayingToList();
    }

    @Test(priority = 22)
    public void paginationInGridTest() {
        changeDisplayingToGrid();
        int before = checkingSizeOfGridBeforePagination();
        clickOnPaginationButtonInGrid();
        int after =checkingSizeOfGridAfterPagination();
        Assert.assertEquals(before + 10, after);
        changeDisplayingToList();
    }

    @Test(priority = 23)
    public void closeEditUserFormInGridTest() {
        changeDisplayingToGrid();
        goToEditFormInGrid();
        closeEditUserForm();
        changeDisplayingToList();
    }

    @Test(priority = 24)
    public void openAndCloseAddUserFormInGridTest() {
        changeDisplayingToGrid();
        goToAddUserForm();
        closeAddUserForm();
        changeDisplayingToList();
    }

    @Test(priority = 25)
    public void creationUserInGridTest() {
        changeDisplayingToGrid();
        goToAddUserForm();
        fillAddUserForm();
        submitUserCreation();
        searchCreationUserInGrid();
        changeDisplayingToList();
    }

}

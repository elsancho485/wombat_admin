package ru.wombat.admin.tests.Tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.sleep;

public class EditUserTests extends TestBase {

    @Test(priority = 16)
    public void adminCanCloseEditUserForm() {
        goToEditUserData();
        closeEditUserForm();
    }

    @Test(priority = 17)
    public void emptyFieldsErrorEditUserTest() {
        goToEditUserData();
        clearFieldsEditUserForm();
        checkingErrorAboutEmptyFieldsEditUserForm();
        closeEditUserForm();
    }

    @Test(priority = 18)
    public void displayingErrorWithSpace() {
        goToEditUserData();
        fillEditUserFormWithSpace();
        searchlError();
        closeEditUserForm();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 19)
    public void adminCanEditUserDataTest() {
        goToEditUserData();
        fillEditUserForm();
        searchCreatedUserInList();
    }

    @Test(priority = 10)
    public void checkingUserInfoAfterEditTest() {
        sleep(5000);
        goToEditUserData();
        fillEditUserForm();
        searchCreatedUserInList();
        checkingUserInfoAfterEdit();
    }

}

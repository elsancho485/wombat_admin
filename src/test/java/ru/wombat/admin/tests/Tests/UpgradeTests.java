package ru.wombat.admin.tests.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class UpgradeTests extends TestBase {

    @Test(priority = 34)
    public void exitFromUpgradeFormTest() {
        goToUpgradeUserForm();
        closeUpgradeForm();
    }

    @Test(priority = 35)
    public void errorAboutNegativeSalaryTest() {
        goToUpgradeUserForm();
        upgradeWithNegativeSalary();
        checkingErrorAboutNegativeSalary();
        closeUpgradeForm();
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

    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 37)
    public void adminCanUpgradeUserTest() {
        int before = getGradeNumber();
        goToUpgradeUserForm();
        upgradeUser();
        submitUserUpgrade();
        int after = getGradeNumber();
        Assert.assertEquals(before + 1 , after);
    }

}

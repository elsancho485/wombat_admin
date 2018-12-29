package ru.wombat.admin.tests.Tests;

import org.testng.annotations.Test;

public class SearchUserTests extends TestBase {

    @Test(priority = 31)
    public void adminCanSearchUserByUsernameTest() {
        searchUser();
    }

    @Test(priority = 32)
    public void searchUsersByGradeTest() {
        searchUsersByGrade();
        checkingUserListByGrade();
        goToAllGradesUsers();
    }

    @Test(priority = 33)
    public void searchUsersByTeamTest() {
        searchUsersByTeam();
        checkingTeam();
    }
}

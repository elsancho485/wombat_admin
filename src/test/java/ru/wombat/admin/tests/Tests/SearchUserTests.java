package ru.wombat.admin.tests.Tests;

import org.testng.annotations.Test;

public class SearchUserTests extends TestBase {

    @Test(priority = 30)
    public void adminCanSearchUserByUsername() {
        searchUser();
    }

    @Test(priority = 31)
    public void searchUsersByGradeTest() {
        searchUsersByGrade();
        checkingUserListByGrade();
        goToAllGradesUsers();
    }

    @Test(priority = 32)
    public void searchUsersByTeamTest() {
        searchUsersByTeam();
        checkingTeam();
    }
}

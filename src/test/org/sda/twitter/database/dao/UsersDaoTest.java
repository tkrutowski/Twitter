package org.sda.twitter.database.dao;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersDaoTest {

    UsersDao usersDao = new UsersDao();

    @Test
    public void should_return_true_when_valid() {
        //when
        String name = "Tomek";
        String pass = "maslo";

        //given
        boolean result = usersDao.hasUser(name, pass);

        //then
        Assert.assertTrue(result);
    }

    @Test
    public void should_return_false_when_invalid() {
        //when
        String name = "test";
        String pass = "test";

        //given
        boolean result = usersDao.hasUser(name, pass);

        //then
        Assert.assertFalse(result);
    }


    @Test
    public void should_return_value_bigger_than_zero_if_db_is_not_empty() {

        //given
        int result = usersDao.findAll().size();

        //then
        Assert.assertTrue(result>0);
    }
}
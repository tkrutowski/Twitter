package org.sda.twitter.database.configuration;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatasourceConfigurationTest {
    @Test
    public void shouldReturnConnectionInstance() throws SQLException {
        // given
        DatasourceConfiguration underTest = DatasourceConfiguration.getInstance();

        // when
        Connection instance = underTest.getConnection();

        // then
        Assert.assertNotNull(instance);
    }
}
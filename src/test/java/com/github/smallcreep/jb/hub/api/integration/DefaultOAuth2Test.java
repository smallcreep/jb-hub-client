package com.github.smallcreep.jb.hub.api.integration;

import java.util.Properties;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Case for {@link DefaultOAuth2}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultOAuth2Test {

    /**
     * System properties.
     */
    private Properties properties;

    /**
     * Test Case setUp.
     * @throws Exception If fails
     */
    @Before
    public void setUp() throws Exception {
        this.properties = System.getProperties();
    }

    /**
     * Test Case tearDown.
     * @throws Exception If fails
     */
    @After
    public void tearDown() throws Exception {
        System.setProperties(this.properties);
    }

    /**
     * Get header return header with Base64 encode client id and secret
     * from properties.
     * @throws Exception If fails
     */
    @Test
    public void headerBaseEncode() throws Exception {
        System.setProperty("failsafe.hub.service", "client_id");
        System.setProperty("failsafe.hub.secret", "client_secret");
        MatcherAssert.assertThat(
            new DefaultOAuth2().header(),
            CoreMatchers.equalTo(
                "Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ="
            )
        );
    }

    /**
     * Equals and HashCode check.
     * @throws Exception If fails
     */
    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(DefaultOAuth2.class).verify();
    }
}

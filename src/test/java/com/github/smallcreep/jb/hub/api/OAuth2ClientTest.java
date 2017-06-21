package com.github.smallcreep.jb.hub.api;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link OAuth2Client}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class OAuth2ClientTest {

    /**
     * Get header return header with Base64 encode client id and secret.
     * @throws Exception If fails
     */
    @Test
    public void headerBaseEncode() throws Exception {
        MatcherAssert.assertThat(
            new OAuth2Client.Simple(
                "client_id",
                "client_secret"
            ).header(),
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
        EqualsVerifier.forClass(OAuth2Client.Simple.class).verify();
    }
}
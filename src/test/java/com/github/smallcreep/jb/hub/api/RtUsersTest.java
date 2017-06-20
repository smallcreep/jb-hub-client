package com.github.smallcreep.jb.hub.api;

import java.net.URI;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link RtUsers}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtUsersTest {

    /**
     * Equals and HashCode check.
     * @throws Exception If fails
     */
    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(RtUsers.class).verify();
    }

    /**
     * Return the same hub.
     * @throws Exception If fails
     */
    @Test
    public void sameHub() throws Exception {
        final Hub hub = new RtHub(
            new URI("hub.com"),
            "token"
        );
        MatcherAssert.assertThat(
            new RtUsers(
                hub
            ).hub(),
            CoreMatchers.equalTo(
                hub
            )
        );
    }
}

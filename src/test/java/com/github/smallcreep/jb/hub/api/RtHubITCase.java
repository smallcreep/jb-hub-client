package com.github.smallcreep.jb.hub.api;

import com.github.smallcreep.jb.hub.api.integration.DefaultAuthByPass;
import javax.json.JsonObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Integration test for github.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtHubITCase {

    /**
     * Get self, from hub.
     * @throws Exception If fails
     */
    @Test
    public void getSelf() throws Exception {
        final JsonObject json = new DefaultAuthByPass().hub()
            .users()
            .self()
            .json();
        MatcherAssert.assertThat(
            json.getString("name"),
            Matchers.equalTo("root")
        );
        MatcherAssert.assertThat(
            json.getString("login"),
            Matchers.equalTo("root")
        );
    }
}

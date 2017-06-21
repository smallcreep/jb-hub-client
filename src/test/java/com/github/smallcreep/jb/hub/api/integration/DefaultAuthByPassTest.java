package com.github.smallcreep.jb.hub.api.integration;

import com.github.smallcreep.jb.hub.api.RtHub;
import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import java.net.HttpURLConnection;
import java.util.Properties;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Case for {@link DefaultAuthByPass}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultAuthByPassTest {

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
        this.properties = (Properties) System.getProperties().clone();
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
     * Check {@link DefaultAuthByPass#hub()} return Hub.
     * @throws Exception If fails
     */
    @Test
    public void returnHub() throws Exception {
        System.setProperty("failsafe.hub.service", "client_id");
        System.setProperty("failsafe.hub.secret", "client_secret");
        System.setProperty("failsafe.hub.username", "user");
        System.setProperty("failsafe.hub.password", "pass");
        System.setProperty("failsafe.hub.scopes", "first,second");
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple(
                    HttpURLConnection.HTTP_OK,
                    "{\"token_type\":\"Bearer\", "
                        + "\"access_token\": \"token123\"}"
                )
            ).start();
        System.setProperty("failsafe.hub.uri", container.home().toString());
        MatcherAssert.assertThat(
            new DefaultAuthByPass().hub(),
            Matchers.equalTo(
                new RtHub(
                    container.home(),
                    "Bearer token123"
                )
            )
        );
        container.stop();
    }

}

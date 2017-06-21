package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.manifests.Manifests;
import java.net.URI;
import javax.ws.rs.core.HttpHeaders;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test Case for {@link RtHub}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtHubTest {

    /**
     * RtHub return Request with UserAgent.
     * @throws Exception If fails
     */
    @Test
    public void userAgent() throws Exception {
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple("hello, world!")
            ).start();
        new RtHub(
            container.home()
        ).entry().fetch();
        container.stop();
        MatcherAssert.assertThat(
            container.take().headers(),
            Matchers.hasEntry(
                Matchers.equalTo(HttpHeaders.USER_AGENT),
                Matchers.hasItem(
                    String.format(
                        "jb-hub-api-client %s %s %s",
                        Manifests.read("Hub-Version"),
                        Manifests.read("Hub-Build"),
                        Manifests.read("Hub-Date")
                    )
                )
            )
        );
    }

    /**
     * RtHub return Request with path.
     * @throws Exception If fails
     */
    @Test
    public void path() throws Exception {
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple("hello, world!")
            ).start();
        new RtHub(
            container.home()
        ).entry().fetch();
        container.stop();
        MatcherAssert.assertThat(
            container.take().uri(),
            CoreMatchers.equalTo(
                new URI("/hub/api/rest")
            )
        );
    }

    /**
     * RtHub return Request with Authorization token.
     * @throws Exception If fails
     */
    @Test
    public void authorizationToken() throws Exception {
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple("hello, world!")
            ).start();
        new RtHub(
            container.home(),
            "token"
        ).entry().fetch();
        container.stop();
        MatcherAssert.assertThat(
            container.take().headers(),
            Matchers.hasEntry(
                Matchers.equalTo(HttpHeaders.AUTHORIZATION),
                Matchers.hasItem(
                    "token"
                )
            )
        );
    }

    /**
     * Equals and HashCode check.
     * @throws Exception If fails
     */
    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(RtHub.class).verify();
    }

    /**
     * Users get return correct users.
     * @throws Exception If fails
     */
    @Test
    public void usersGet() throws Exception {
        final Hub hub = new RtHub(
            new URI("hub.com"),
            "token"
        );
        MatcherAssert.assertThat(
            hub.users(),
            CoreMatchers.equalTo(
                new RtUsers(
                    hub
                )
            )
        );
    }
}

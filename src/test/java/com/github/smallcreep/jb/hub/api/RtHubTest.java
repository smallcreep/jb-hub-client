package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.manifests.Manifests;
import javax.ws.rs.core.HttpHeaders;
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
                Matchers.equalTo("User-Agent"),
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
}

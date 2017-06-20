package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.Request;
import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.JdkRequest;
import java.net.HttpURLConnection;
import java.net.URI;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link RtUser}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtUserTest {

    /**
     * Return the same user.
     * @throws Exception If fails
     */
    @Test
    public void sameUser() throws Exception {
        final Request req = new JdkRequest("");
        final RtUsers users = new RtUsers(
            req,
            new RtHub(
                req
            )
        );
        MatcherAssert.assertThat(
            new RtUser(
                req,
                users,
                "me"
            ).users(),
            CoreMatchers.equalTo(
                users
            )
        );
    }

    /**
     * RtUser return Request with path.
     * @throws Exception If fails
     */
    @Test
    public void pathFromUsers() throws Exception {
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple(HttpURLConnection.HTTP_OK, "{\"body\":\"hi\"}")
            ).start();
        new RtHub(
            container.home()
        ).users().self().json();
        container.stop();
        MatcherAssert.assertThat(
            container.take().uri(),
            CoreMatchers.equalTo(
                new URI("/hub/api/rest/users/me")
            )
        );
    }

    /**
     * RtUser return Request with path.
     * @throws Exception If fails
     */
    @Test
    public void path() throws Exception {
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple(HttpURLConnection.HTTP_OK, "{\"body\":\"hi\"}")
            ).start();
        new RtUser(
            new JdkRequest(
                container.home()
            ),
            new RtUsers(
                new JdkRequest(
                    container.home()
                ),
                new RtHub(
                    new JdkRequest(
                        container.home()
                    )
                )
            ),
            "101"
        ).json();
        container.stop();
        MatcherAssert.assertThat(
            container.take().uri(),
            CoreMatchers.equalTo(
                new URI("/101")
            )
        );
    }
}

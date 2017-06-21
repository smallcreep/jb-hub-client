package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.mock.MkQuery;
import com.jcabi.manifests.Manifests;
import java.net.HttpURLConnection;
import java.net.URI;
import javax.ws.rs.core.HttpHeaders;
import org.cactoos.list.IterableAsList;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test Case for {@link Auth}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AuthTest {

    /**
     * Check {@link Auth} generate correct request.
     * @throws Exception If fails
     */
    @Test
    public void correctRequestParameters() throws Exception {
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple(
                    HttpURLConnection.HTTP_OK,
                    "{\"token_type\":\"Bearer\", "
                        + "\"access_token\": \"token123\"}"
                )
            ).start();
        new Auth.AuthByPass(
            container.home(),
            new OAuth2Client.Simple(
                "client_id",
                "client_secret"
            ),
            "user",
            "pass",
            new IterableAsList<>(
                "first",
                "second"
            )
        ).hub();
        container.stop();
        final MkQuery take = container.take();
        MatcherAssert.assertThat(
            take.uri(),
            Matchers.equalTo(new URI("/hub/api/rest/oauth2/token"))
        );
        MatcherAssert.assertThat(
            take.body(),
            Matchers.equalTo(
                "password=pass&grant_type=password&"
                    + "username=user&scope=first+second"
            )
        );
        MatcherAssert.assertThat(
            take.headers(),
            Matchers.allOf(
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
                ),
                Matchers.hasEntry(
                    Matchers.equalTo(HttpHeaders.CONTENT_TYPE),
                    Matchers.hasItem(
                        "application/x-www-form-urlencoded"
                    )
                ),
                Matchers.hasEntry(
                    Matchers.equalTo(HttpHeaders.AUTHORIZATION),
                    Matchers.hasItem(
                        "Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ="
                    )
                )
            )
        );
    }

    /**
     * Check {@link Auth#hub()} return Hub.
     * @throws Exception If fails
     */
    @Test
    public void returnHub() throws Exception {
        final MkContainer container = new MkGrizzlyContainer()
            .next(
                new MkAnswer.Simple(
                    HttpURLConnection.HTTP_OK,
                    "{\"token_type\":\"Bearer\", "
                        + "\"access_token\": \"token123\"}"
                )
            ).start();
        MatcherAssert.assertThat(
            new Auth.AuthByPass(
                container.home(),
                new OAuth2Client.Simple(
                    "client_id",
                    "client_secret"
                ),
                "user",
                "pass",
                new IterableAsList<>(
                    "first",
                    "second"
                )
            ).hub(),
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

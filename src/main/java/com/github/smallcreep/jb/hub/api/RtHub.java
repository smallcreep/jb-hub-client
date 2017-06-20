package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.wire.AutoRedirectingWire;
import com.jcabi.manifests.Manifests;
import java.net.URI;
import javax.ws.rs.core.HttpHeaders;

/**
 * JetBrains Hub client entry point.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/Resources-for-Developers.html">HUB API</a>
 * @since 0.1
 */
public final class RtHub implements Hub {

    /**
     * Version of us.
     */
    private static final String USER_AGENT = String.format(
        "jb-hub-api-client %s %s %s",
        Manifests.read("Hub-Version"),
        Manifests.read("Hub-Build"),
        Manifests.read("Hub-Date")
    );

    /**
     * REST req.
     */
    private final Request req;

    /**
     * Public ctor, for anonymous access to Hub.
     *
     * @param uri URI Hub
     */
    public RtHub(final URI uri) {
        this(
            new ApacheRequest(uri)
        );
    }

    /**
     * Public ctor, for authentication with OAuth2 token Hub.
     *
     * @param uri URI Hub
     * @param token OAuth token
     */
    public RtHub(
        final URI uri,
        final String token) {
        this(
            new ApacheRequest(uri).header(
                HttpHeaders.AUTHORIZATION,
                token
            )
        );
    }

    /**
     * Public ctor, with a custom req.
     *
     * @param req Request to start from
     */
    public RtHub(
        final Request req) {
        this.req = req.header(HttpHeaders.USER_AGENT, RtHub.USER_AGENT)
            .through(AutoRedirectingWire.class)
            .uri()
            .path("/api/v4")
            .back();
    }

    @Override
    public Request entry() {
        return this.req;
    }

    @Override
    public Users users() {
        return null;
    }
}

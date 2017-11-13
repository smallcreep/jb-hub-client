/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Ilia Rogozhin (ilia.rogozhin@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.smallcreep.jb.hub.api;

import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.wire.AutoRedirectingWire;
import com.jcabi.manifests.Manifests;
import java.net.URI;
import javax.ws.rs.core.HttpHeaders;
import lombok.EqualsAndHashCode;

/**
 * JetBrains Hub client entry point.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/Resources-for-Developers.html">HUB API</a>
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
@EqualsAndHashCode(of = "req")
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
            .path("/hub/api/rest")
            .back();
    }

    @Override
    public Request entry() {
        return this.req;
    }

    @Override
    public Users users() {
        return new RtUsers(this.req, this);
    }

    @Override
    public Projects projects() {
        return null;
    }
}

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

import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.wire.AutoRedirectingWire;
import com.jcabi.manifests.Manifests;
import java.io.IOException;
import java.net.URI;
import javax.json.JsonObject;
import javax.ws.rs.core.HttpHeaders;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.text.FormattedText;

/**
 * Authorization by user and password in Jetbrains Hub.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/Resource-Owner-Password-Credentials.html">Resource Owner Password Credentials</a>
 * @since 0.1
 */
public interface Auth {

    /**
     * Get Hub with authorization.
     * @return Hub
     * @throws IOException If fails
     */
    Hub hub() throws IOException;

    /**
     * Authorization by user and password in Jetbrains Hub.
     */
    final class AuthByPass implements Auth {

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
         * Basic HUB URI.
         */
        private final URI uri;

        /**
         * Ctor.
         * @param uri Hub URI
         * @param client Client
         * @param username Username
         * @param password Password
         * @param scopes Scopes
         *
         * @throws IOException If fails
         * @checkstyle ParameterNumberCheck (8 lines)
         */
        public AuthByPass(
            final URI uri,
            final OAuth2Client client,
            final String username,
            final String password,
            final Iterable<String> scopes
        ) throws IOException {
            this(
                uri,
                new ApacheRequest(uri)
                    .header(HttpHeaders.AUTHORIZATION, client.header())
                    .body()
                    .formParams(
                        new MapOf<String, String>(
                            new MapEntry<>(
                                "grant_type",
                                "password"
                            ),
                            new MapEntry<>(
                                "username",
                                username
                            ),
                            new MapEntry<>(
                                "password",
                                password
                            ),
                            new MapEntry<>(
                                "scope",
                                String.join(" ", scopes)
                            )
                        )
                    )
                    .back()
            );
        }

        /**
         * Public ctor, with a custom req.
         *
         * @param uri Hub URI
         * @param req Request to start from
         */
        public AuthByPass(
            final URI uri,
            final Request req) {
            this.uri = uri;
            this.req = req.header(HttpHeaders.USER_AGENT, AuthByPass.USER_AGENT)
                .through(AutoRedirectingWire.class)
                .uri()
                .path("/hub/api/rest/oauth2/token")
                .back()
                .method(Request.POST)
                .header(
                    HttpHeaders.CONTENT_TYPE,
                    "application/x-www-form-urlencoded"
                );
        }

        @Override
        public Hub hub() throws IOException {
            final JsonObject response = new RtJson(this.req).fetch();
            return new RtHub(
                this.uri,
                new FormattedText(
                    "%s %s",
                    response.getString("token_type"),
                    response.getString("access_token")
                ).asString()
            );
        }
    }
}

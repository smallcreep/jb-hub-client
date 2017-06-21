package com.github.smallcreep.jb.hub.api;

import java.io.IOException;
import java.util.Base64;
import org.cactoos.text.FormattedText;

/**
 * OAuth2 client header.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://tools.ietf.org/html/rfc6749#section-2.3.1">Client Password</a>
 * @since 0.1
 */
public final class OAuth2Client {

    /**
     * Service Id.
     */
    private final String service;

    /**
     * Service secret.
     */
    private final String secret;

    /**
     * Ctor.
     * @param service Service Id
     * @param secret Service secret
     */
    public OAuth2Client(final String service, final String secret) {
        this.service = service;
        this.secret = secret;
    }

    /**
     * Get Authorization header.
     * @return Authorization header
     * @throws IOException If fails
     */
    public String header() throws IOException {
        return new FormattedText(
            "Basic %s",
            Base64.getEncoder().encodeToString(
                new FormattedText(
                    "%s:%s",
                    service,
                    secret
                ).asString().getBytes("utf-8")
            )
        ).asString();
    }

}

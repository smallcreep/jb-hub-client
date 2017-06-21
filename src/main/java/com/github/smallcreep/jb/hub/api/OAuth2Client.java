package com.github.smallcreep.jb.hub.api;

import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import java.io.IOException;
import java.util.Base64;
import lombok.EqualsAndHashCode;
import org.cactoos.text.FormattedText;

/**
 * OAuth2 client header.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://tools.ietf.org/html/rfc6749#section-2.3.1">Client Password</a>
 * @since 0.1
 */
public interface OAuth2Client {

    /**
     * Get Authorization header.
     * @return Authorization header
     * @throws IOException If fails
     */
    String header() throws IOException;

    /**
     * Basic client.
     */
    @EqualsAndHashCode(of = {"service", "secret"})
    @Loggable(Loggable.DEBUG)
    @Immutable
    final class Simple implements OAuth2Client {

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
        public Simple(final String service, final String secret) {
            this.service = service;
            this.secret = secret;
        }

        @Override
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
}

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

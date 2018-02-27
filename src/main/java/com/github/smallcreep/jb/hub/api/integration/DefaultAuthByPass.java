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

package com.github.smallcreep.jb.hub.api.integration;

import com.github.smallcreep.jb.hub.api.Auth;
import com.github.smallcreep.jb.hub.api.Hub;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.cactoos.iterable.IterableOf;

/**
 * Authorization by pass with info from System properties.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultAuthByPass implements Auth {

    /**
     * Origin Auth.
     */
    private final Auth origin;

    /**
     * Ctor.
     * @throws IOException If fails
     * @throws URISyntaxException If fails
     */
    public DefaultAuthByPass() throws IOException, URISyntaxException {
        this("http://127.0.0.1:8080");
    }

    /**
     * Ctor.
     * @param uri Hub Uri
     * @throws IOException If fails
     * @throws URISyntaxException If fails
     */
    public DefaultAuthByPass(final String uri)
        throws IOException, URISyntaxException {
        this.origin = new AuthByPass(
            new URI(uri),
            new DefaultOAuth2(),
            "admin",
            "123456",
            new IterableOf<>(
                "0-0-0-0-0"
            )
        );
    }

    @Override
    public Hub hub() throws IOException {
        return this.origin.hub();
    }
}

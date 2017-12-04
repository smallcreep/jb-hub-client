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
import com.jcabi.http.request.JdkRequest;
import java.net.URI;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link RtUsers}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtUsersTest {

    /**
     * Equals and HashCode check.
     * @throws Exception If fails
     */
    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(RtUsers.class).verify();
    }

    /**
     * Return the same hub.
     * @throws Exception If fails
     */
    @Test
    public void sameHub() throws Exception {
        final Hub hub = new RtHub(
            new URI("hub.com"),
            "token"
        );
        MatcherAssert.assertThat(
            new RtUsers(
                hub
            ).hub(),
            CoreMatchers.equalTo(
                hub
            )
        );
    }

    /**
     * Self get return correct user.
     * @throws Exception If fails
     */
    @Test
    public void selfGet() throws Exception {
        final Request req = new JdkRequest("");
        final RtUsers users = new RtUsers(
            req,
            new RtHub(
                req
            )
        );
        MatcherAssert.assertThat(
            users.self(),
            CoreMatchers.equalTo(
                new RtUser(
                    req.uri().path("/users").back(),
                    users,
                    "me"
                )
            )
        );
    }

    /**
     * Guest get return correct user.
     * @throws Exception If fails
     */
    @Test
    public void guestGet() throws Exception {
        final Request req = new JdkRequest("");
        final RtUsers users = new RtUsers(
            req,
            new RtHub(
                req
            )
        );
        MatcherAssert.assertThat(
            users.guest(),
            CoreMatchers.equalTo(
                new RtUser(
                    req.uri().path("/users").back(),
                    users,
                    "guest"
                )
            )
        );
    }
}

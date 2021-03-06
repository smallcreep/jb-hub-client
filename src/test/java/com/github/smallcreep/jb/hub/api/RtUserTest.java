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
import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.JdkRequest;
import java.net.HttpURLConnection;
import java.net.URI;
import nl.jqno.equalsverifier.EqualsVerifier;
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
                new MkAnswer.Simple(
                    HttpURLConnection.HTTP_OK,
                    "{\"body\":\"hi\"}"
                )
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
                new MkAnswer.Simple(
                    HttpURLConnection.HTTP_OK,
                    "{\"body\":\"hi\"}"
                )
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

    /**
     * Check #equals() and #hashCode() methods.
     * @throws Exception If fails
     */
    @Test
    public void equalsCorrect() throws Exception {
        EqualsVerifier.forClass(RtUser.class).verify();
    }
}

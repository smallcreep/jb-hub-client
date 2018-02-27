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

import com.github.smallcreep.jb.hub.api.RtHub;
import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import java.net.HttpURLConnection;
import java.util.Properties;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Case for {@link DefaultAuthByPass}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultAuthByPassTest {

    /**
     * System properties.
     */
    private Properties properties;

    /**
     * Test Case setUp.
     * @throws Exception If fails
     */
    @Before
    public void setUp() throws Exception {
        this.properties = (Properties) System.getProperties().clone();
    }

    /**
     * Test Case tearDown.
     * @throws Exception If fails
     */
    @After
    public void tearDown() throws Exception {
        System.setProperties(this.properties);
    }

    /**
     * Check {@link DefaultAuthByPass#hub()} return Hub.
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
            new DefaultAuthByPass(container.home().toString()).hub(),
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

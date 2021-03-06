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

import java.util.Properties;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Case for {@link DefaultOAuth2}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultOAuth2Test {

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
     * Get header return header with Base64 encode client id and secret
     * from properties.
     * @throws Exception If fails
     */
    @Test
    public void headerBaseEncode() throws Exception {
        System.setProperty("failsafe.hub.service", "client_id");
        System.setProperty("failsafe.hub.secret", "client_secret");
        MatcherAssert.assertThat(
            new DefaultOAuth2().header(),
            CoreMatchers.equalTo(
                "Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ="
            )
        );
    }

    /**
     * Equals and HashCode check.
     * @throws Exception If fails
     */
    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(DefaultOAuth2.class).verify();
    }
}

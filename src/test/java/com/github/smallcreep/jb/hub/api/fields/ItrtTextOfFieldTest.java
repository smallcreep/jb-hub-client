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

package com.github.smallcreep.jb.hub.api.fields;

import com.github.smallcreep.jb.hub.api.Field;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.cactoos.Text;
import org.cactoos.TextHasString;
import org.cactoos.iterable.IterableOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test Case for {@link ItrtTextOfField}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class ItrtTextOfFieldTest {

    /**
     * Iterator has correct values.
     * @throws Exception If fails
     */
    @Test
    public void checkValues() throws Exception {
        final String first = "first";
        final String second = "second";
        MatcherAssert.assertThat(
            "Iterator sort to texts doesn't has correct values.",
            () -> new ItrtTextOfField(
                new IterableOf<Field>(
                    () -> first,
                    () -> second
                ).iterator()
            ),
            Matchers.contains(
                new TextHasString(
                    first
                ),
                new TextHasString(
                    second
                )
            )
        );
    }

    /**
     * Iterator throws {@link NoSuchElementException}.
     *
     * @throws Exception If fails
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratorThrowsNoSuchElement() throws Exception {
        final String first = "first";
        final Iterator<Text> iterator = new ItrtTextOfField(
            new IterableOf<Field>(
                () -> first
            ).iterator()
        );
        MatcherAssert.assertThat(
            iterator.next(),
            new TextHasString(
                first
            )
        );
        iterator.next();
    }
}

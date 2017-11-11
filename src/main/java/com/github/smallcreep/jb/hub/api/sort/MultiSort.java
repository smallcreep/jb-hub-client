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

package com.github.smallcreep.jb.hub.api.sort;

import com.github.smallcreep.jb.hub.api.Sort;
import com.github.smallcreep.misc.iterable.IterableScalarStringToText;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;
import org.cactoos.text.JoinedText;
import org.cactoos.text.TextOf;

/**
 * Sort by Multi Sort values.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class MultiSort implements Sort {

    /**
     * Origin Sorts.
     */
    private final Iterable<Scalar<String>> origins;

    /**
     * Ctor.
     * @param origins Origin Sorts
     */
    public MultiSort(final Sort... origins) {
        this(new IterableOf<>(origins));
    }

    /**
     * Ctor.
     * @param origins Origin Sorts
     */
    private MultiSort(final Iterable<Scalar<String>> origins) {
        this.origins = origins;
    }

    @Override
    public String value() throws Exception {
        return new JoinedText(
            new TextOf(", "),
            new IterableScalarStringToText(this.origins)
        ).asString();
    }
}

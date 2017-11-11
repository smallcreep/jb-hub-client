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

package com.github.smallcreep.misc.iterable;

import com.github.smallcreep.misc.iterator.IteratorScalarStringToText;
import java.util.Iterator;
import org.cactoos.Scalar;
import org.cactoos.Text;

/**
 * Transform {@link Iterable} {@link Scalar} to {@link Iterable} {@link Text}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class IterableScalarStringToText implements Iterable<Text> {

    /**
     * Origin iterable.
     */
    private final Iterable<Scalar<String>> origin;

    /**
     * Ctor.
     * @param origin Origin iterable
     */
    public IterableScalarStringToText(final Iterable<Scalar<String>> origin) {
        this.origin = origin;
    }

    @Override
    public Iterator<Text> iterator() {
        return new IteratorScalarStringToText(this.origin.iterator());
    }
}

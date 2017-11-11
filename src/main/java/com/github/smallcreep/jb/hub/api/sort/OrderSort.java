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

import com.github.smallcreep.jb.hub.api.Field;
import com.github.smallcreep.jb.hub.api.Sort;
import org.cactoos.text.FormattedText;

/**
 * Sort by Sort origin value in a set order.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
final class OrderSort implements Sort {

    /**
     * Origin sort.
     */
    private final Sort origin;

    /**
     * Order.
     */
    private final String order;

    /**
     * Ctor.
     * @param field Field name
     * @param order Order
     */
    OrderSort(final String field, final String order) {
        this(new Field.Simple(field), order);
    }

    /**
     * Ctor.
     * @param field Field
     * @param order Order
     */
    OrderSort(final Field field, final String order) {
        this(new DefaultSort(field), order);
    }

    /**
     * Ctor.
     * @param origin Origin sort
     * @param order Order
     */
    OrderSort(final Sort origin, final String order) {
        this.origin = origin;
        this.order = order;
    }

    @Override
    public String value() throws Exception {
        return new FormattedText(
            "%s:%s",
            this.origin.value(),
            this.order
        ).asString();
    }
}

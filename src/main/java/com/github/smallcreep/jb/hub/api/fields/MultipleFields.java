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
import com.github.smallcreep.jb.hub.api.Fields;
import org.cactoos.iterable.IterableOf;
import org.cactoos.text.JoinedText;
import org.cactoos.text.TextOf;

/**
 * Show Multiple fields.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class MultipleFields implements Fields {

    /**
     * Origin Fields.
     */
    private final Iterable<Field> origins;

    /**
     * Ctor.
     * @param fields Origin Fields
     */
    public MultipleFields(final Field... fields) {
        this(new IterableOf<>(fields));
    }

    /**
     * Ctor.
     * @param fields Origin Fields
     */
    public MultipleFields(final Iterable<Field> fields) {
        this.origins = fields;
    }

    @Override
    public String value() throws Exception {
        return new JoinedText(
            new TextOf(","),
            new ItrbTextOfField(this.origins)
        ).asString();
    }
}

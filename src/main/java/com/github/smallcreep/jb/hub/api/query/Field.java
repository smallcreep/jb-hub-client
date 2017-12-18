package com.github.smallcreep.jb.hub.api.query;

import com.github.smallcreep.jb.hub.api.Query;
import org.cactoos.text.FormattedText;

/**
 * Simple query field.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class Field implements Query {

    /**
     * Field name.
     */
    private final String name;

    /**
     * Field value.
     */
    private final String value;

    /**
     * Ctor.
     * @param name Field name
     * @param value Field value
     */
    public Field(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String value() throws Exception {
        return new FormattedText("%s:%s", this.name, this.value).asString();
    }
}

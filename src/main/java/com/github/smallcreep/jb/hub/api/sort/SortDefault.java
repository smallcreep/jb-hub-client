package com.github.smallcreep.jb.hub.api.sort;

import com.github.smallcreep.jb.hub.api.Sort;

/**
 * Sort by field name in a default order.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class SortDefault implements Sort {

    /**
     * Field name.
     */
    private final String field;

    /**
     * Ctor.
     * @param field Field name
     */
    public SortDefault(final String field) {
        this.field = field;
    }

    @Override
    public String value() throws Exception {
        return this.field;
    }
}

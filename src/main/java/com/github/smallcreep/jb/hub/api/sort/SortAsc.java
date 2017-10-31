package com.github.smallcreep.jb.hub.api.sort;

import com.github.smallcreep.jb.hub.api.Sort;

/**
 * Sort by Sort origin value in a asc order.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class SortAsc implements Sort {

    /**
     * Order Sort.
     */
    private final Sort order;

    /**
     * Ctor.
     * @param field Field name
     */
    public SortAsc(final String field) {
        this(new SortDefault(field));
    }

    /**
     * Ctor.
     * @param origin Origin sort
     */
    public SortAsc(final Sort origin) {
        this.order = new SortOrder(origin, "asc");
    }

    @Override
    public String value() throws Exception {
        return this.order.value();
    }
}

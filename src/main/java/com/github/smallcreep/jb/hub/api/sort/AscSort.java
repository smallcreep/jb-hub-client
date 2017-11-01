package com.github.smallcreep.jb.hub.api.sort;

import com.github.smallcreep.jb.hub.api.Sort;

/**
 * Sort by Sort origin value in a asc order.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class AscSort implements Sort {

    /**
     * Order Sort.
     */
    private final Sort order;

    /**
     * Ctor.
     * @param field Field name
     */
    public AscSort(final String field) {
        this(new DefaultSort(field));
    }

    /**
     * Ctor.
     * @param origin Origin sort
     */
    public AscSort(final Sort origin) {
        this.order = new OrderSort(origin, "asc");
    }

    @Override
    public String value() throws Exception {
        return this.order.value();
    }
}

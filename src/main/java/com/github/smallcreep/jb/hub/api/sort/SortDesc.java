package com.github.smallcreep.jb.hub.api.sort;

import com.github.smallcreep.jb.hub.api.Sort;
/**
 * Sort by Sort origin value in a desc order.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class SortDesc implements Sort {

    /**
     * Order Sort.
     */
    private final Sort order;

    /**
     * Ctor.
     * @param field Field name
     */
    public SortDesc(final String field) {
        this(new SortDefault(field));
    }

    /**
     * Ctor.
     * @param origin Origin sort
     */
    public SortDesc(final Sort origin) {
        this.order = new SortOrder(origin, "desc");
    }

    @Override
    public String value() throws Exception {
        return this.order.value();
    }
}

package com.github.smallcreep.jb.hub.api.sort;

import com.github.smallcreep.jb.hub.api.Sort;
import org.cactoos.text.FormattedText;

/**
 * Sort by Sort origin value in a set order.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
final class SortOrder implements Sort {

    /**
     * Origin Sort.
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
    SortOrder(final String field, final String order) {
        this(new SortDefault(field), order);
    }

    /**
     * Ctor.
     * @param origin Origin sort
     * @param order Order
     */
    SortOrder(final Sort origin, final String order) {
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

package com.github.smallcreep.jb.hub.api.query;

import com.github.smallcreep.jb.hub.api.Query;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.StickyIterable;
import org.cactoos.text.JoinedText;

/**
 * Or expression for search.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class Or implements Query {

    /**
     * Queries.
     */
    private final Iterable<Query> queries;

    /**
     * Ctor.
     * @param queries Queries
     */
    public Or(final Query... queries) {
        this(new IterableOf<>(queries));
    }

    /**
     * Ctor.
     * @param queries Queries
     */
    public Or(final Iterable<Query> queries) {
        this.queries = new StickyIterable<>(queries);
    }

    @Override
    public String value() throws Exception {
        return new JoinedText(
            " or ",
            new Mapped<>(
                Query::value,
                this.queries
            )
        ).asString();
    }
}

package com.github.smallcreep.jb.hub.api;

/**
 * Searchable interface use for search elements.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Type of self searchable request
 * @see <a href="https://www.jetbrains.com/help/hub/Query-Syntax.html">Query Syntax</a>
 * @since 0.2.0
 */
public interface Searchable<T extends Searchable> {

    /**
     * Search.
     * @param query Query
     * @return Self
     */
    T search(Query query);
}

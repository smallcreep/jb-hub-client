package com.github.smallcreep.jb.hub.api.req;

import com.github.smallcreep.jb.hub.api.Sort;
import com.jcabi.http.Request;

/**
 * Request with sorted.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class OrderedRequest extends AbstractWrapRequest {

    /**
     * Ctor.
     * @param req Original request
     * @param sort Sort
     */
    public OrderedRequest(final Request req, final Sort sort) {
        super(() -> req
            .uri()
            .queryParam("orderBy", sort.value())
            .back());
    }
}

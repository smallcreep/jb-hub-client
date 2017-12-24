package com.github.smallcreep.jb.hub.api.req;

import com.github.smallcreep.jb.hub.api.Query;
import com.jcabi.http.Request;

/**
 * Request with query.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class QueryableRequest extends AbstractWrapRequest {

    /**
     * Ctor.
     * @param req Original request
     * @param query Query
     */
    public QueryableRequest(final Request req, final Query query) {
        super(() -> req.uri()
            .queryParam("query", query.value())
            .back()
        );
    }
}

package com.github.smallcreep.jb.hub.api.req;

import com.github.smallcreep.jb.hub.api.Fields;
import com.jcabi.http.Request;

/**
 * Subset request.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class SubsetRequest extends AbstractWrapRequest {

    /**
     * Ctor.
     * @param req Original request
     * @param fields Fields
     */
    public SubsetRequest(final Request req, final Fields fields) {
        super(() -> req.uri()
            .queryParam("fields", fields.value())
            .back()
        );
    }
}

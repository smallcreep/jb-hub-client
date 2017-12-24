package com.github.smallcreep.jb.hub.api.req;

import com.jcabi.http.Request;

/**
 * Request with skipped elements.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class SkippedRequest extends AbstractWrapRequest {

    /**
     * Ctor.
     * @param req Original request
     * @param max Skipped count
     */
    public SkippedRequest(final Request req, final int max) {
        super(() -> req.uri()
            .queryParam("top", max)
            .back()
        );
    }
}

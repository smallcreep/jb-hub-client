package com.github.smallcreep.jb.hub.api.iterable;

import com.jcabi.http.Request;
import java.util.Iterator;
import javax.json.JsonObject;

/**
 * Pagination for response.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class RtPagination implements Iterable<JsonObject> {

    /**
     * Request.
     */
    private final Request req;

    /**
     * Name objects array.
     */
    private final String name;

    /**
     * Ctor.
     * @param req Request
     * @param name Name objects array
     */
    public RtPagination(final Request req, final String name) {
        this.req = req;
        this.name = name;
    }

    @Override
    public Iterator<JsonObject> iterator() {
        return new RtPaginationIterator(this.req, this.name);
    }
}

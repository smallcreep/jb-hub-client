package com.github.smallcreep.jb.hub.api.iterable;

import com.github.smallcreep.jb.hub.api.PaginationResponse;
import com.github.smallcreep.misc.Optional;
import com.jcabi.http.Request;
import com.jcabi.http.Response;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.response.RestResponse;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Iterator from Responses with Json Array, and next links.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
final class RtPaginationIterator implements Iterator<JsonObject> {

    /**
     * Array response.
     */
    private final AtomicReference<JsonArray> json =
        new AtomicReference<>(null);

    /**
     * Current element's number.
     */
    private final AtomicInteger number = new AtomicInteger(0);

    /**
     * Current response.
     */
    private final AtomicReference<Response> response =
        new AtomicReference<>(null);

    /**
     * Current element.
     */
    private final AtomicReference<JsonObject> element =
        new AtomicReference<>(null);

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
    RtPaginationIterator(final Request req, final String name) {
        this.req = req;
        this.name = name;
    }

    @Override
    public boolean hasNext() {
        boolean has = true;
        if (this.element.get() == null) {
            try {
                this.element.set(this.json());
                // @checkstyle IllegalCatchCheck (1 line)
            } catch (final Exception exception) {
                has = false;
            }
        }
        return has;
    }

    @Override
    public JsonObject next() {
        if (this.hasNext()) {
            return this.element.getAndSet(null);
        }
        throw new NoSuchElementException();
    }

    /**
     * Get json project.
     * @return Project json
     * @throws Exception If fails
     */
    private JsonObject json() throws Exception {
        if (this.json.get() == null) {
            this.json.set(this.fetch());
        } else {
            if (!(this.json.get().size() > this.number.get())) {
                this.number.set(0);
                this.json.set(this.fetch());
            }
        }
        return this.json
            .get()
            .getJsonObject(this.number.getAndIncrement());
    }

    /**
     * Get json Array projects.
     * @return Array projects
     * @throws Exception If fails
     */
    private JsonArray fetch() throws Exception {
        if (this.response.get() != null) {
            final Optional<Request> next = this.response
                .get()
                .as(PaginationResponse.class)
                .next();
            if (next.has()) {
                this.response.set(
                    next.value().fetch()
                );
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.response.set(this.req.fetch());
        }
        return this.response.get()
            .as(RestResponse.class)
            .assertStatus(HttpURLConnection.HTTP_OK)
            .as(JsonResponse.class)
            .json()
            .readObject()
            .getJsonArray(name);
    }
}

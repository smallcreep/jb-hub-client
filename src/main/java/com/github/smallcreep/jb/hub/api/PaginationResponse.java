package com.github.smallcreep.jb.hub.api;

import com.github.smallcreep.misc.Optional;
import com.jcabi.http.Request;
import com.jcabi.http.Response;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.response.RestResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Jetbrains Hub response with pagination link.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class PaginationResponse implements Response {

    /**
     * Encapsulated response.
     */
    private final Response response;

    /**
     * Public ctor.
     * @param resp Response
     */
    public PaginationResponse(final Response resp) {
        this.response = resp;
    }

    /**
     * Follow to next page.
     * @return Request to next page.
     * @throws IOException If fails
     */
    public Optional<Request> next() throws IOException {
        return this.follow("next");
    }

    /**
     * Follow to previous page.
     * @return Request to previous page.
     * @throws IOException If fails
     */
    public Optional<Request> previous() throws IOException {
        return this.follow("prev");
    }

    /**
     * Follow link.
     * @param pointer Page's pointer
     * @return The same object
     * @throws IOException If fails
     */
    private Optional<Request> follow(final String pointer) throws IOException {
        final String link = this.response
            .as(JsonResponse.class)
            .json()
            .readObject()
            .getString(pointer, "");
        final Optional<Request> request;
        if (link.isEmpty()) {
            request = new Optional.Empty<>();
        } else {
            request = new Optional.Single<>(
                new RestResponse(this).jump(URI.create(link))
            );
        }
        return request;
    }

    @Override
    public Request back() {
        return this.response.back();
    }

    @Override
    public int status() {
        return this.response.status();
    }

    @Override
    public String reason() {
        return this.response.reason();
    }

    @Override
    public Map<String, List<String>> headers() {
        return this.response.headers();
    }

    @Override
    public String body() {
        return this.response.body();
    }

    @Override
    public byte[] binary() {
        return this.response.binary();
    }

    @Override
    public <T extends Response> T as(final Class<T> type) {
        return this.response.as(type);
    }
}

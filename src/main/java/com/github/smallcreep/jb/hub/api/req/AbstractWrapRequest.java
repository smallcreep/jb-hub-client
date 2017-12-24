package com.github.smallcreep.jb.hub.api.req;

import com.jcabi.http.Request;
import com.jcabi.http.RequestBody;
import com.jcabi.http.RequestURI;
import com.jcabi.http.Response;
import com.jcabi.http.Wire;
import java.io.IOException;
import java.io.InputStream;
import org.cactoos.Scalar;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Abstract request.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
abstract class AbstractWrapRequest implements Request {

    /**
     * Request.
     */
    private final UncheckedScalar<Request> req;

    /**
     * Ctor.
     * @param req Origin request
     */
    AbstractWrapRequest(final Scalar<Request> req) {
        this(new StickyScalar<>(req));
    }

    /**
     * Ctor.
     * @param req Origin request
     */
    private AbstractWrapRequest(final StickyScalar<Request> req) {
        this.req = new UncheckedScalar<>(req);
    }

    @Override
    public RequestURI uri() {
        return this.req.value().uri();
    }

    @Override
    public RequestBody body() {
        return this.req.value().body();
    }

    @Override
    public RequestBody multipartBody() {
        return this.req.value().multipartBody();
    }

    @Override
    public Request header(final String name, final Object value) {
        return this.req.value().header(name, value);
    }

    @Override
    public Request reset(final String name) {
        return this.req.value().reset(name);
    }

    @Override
    public Request method(final String method) {
        return this.req.value().method(method);
    }

    @Override
    public Request timeout(final int connect, final int read) {
        return this.req.value().timeout(connect, read);
    }

    @Override
    public Response fetch() throws IOException {
        return this.req.value().fetch();
    }

    @Override
    public Response fetch(final InputStream stream) throws IOException {
        return this.req.value().fetch(stream);
    }

    @Override
    public <T extends Wire> Request through(
        final Class<T> type, final Object... args) {
        return this.req.value().through(type, args);
    }
}

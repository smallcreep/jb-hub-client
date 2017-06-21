package com.github.smallcreep.jb.hub.api;

import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import com.jcabi.http.Request;
import java.io.IOException;
import javax.json.JsonObject;
import lombok.EqualsAndHashCode;

/**
 * JetBrains Hub User API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
@EqualsAndHashCode(of = {"request", "users"})
final class RtUser implements User {

    /**
     * RESTful request.
     */
    private final Request request;

    /**
     * Owner.
     */
    private final Users users;

    /**
     * Ctor.
     * @param request Request
     * @param users Owner users
     * @param id User Id
     */
    RtUser(
        final Request request,
        final Users users,
        final String id
    ) {
        this.users = users;
        this.request = request.uri()
            .path("/" + id)
            .back();
    }

    @Override
    public JsonObject json() throws IOException {
        return new RtJson(request).fetch();
    }

    @Override
    public Users users() {
        return this.users;
    }
}

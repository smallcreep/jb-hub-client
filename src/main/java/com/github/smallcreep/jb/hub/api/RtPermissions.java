package com.github.smallcreep.jb.hub.api;

import com.github.smallcreep.jb.hub.api.iterable.RtPagination;
import com.github.smallcreep.jb.hub.api.req.OrderedRequest;
import com.github.smallcreep.jb.hub.api.req.QueryableRequest;
import com.github.smallcreep.jb.hub.api.req.SkippedRequest;
import com.github.smallcreep.jb.hub.api.req.SubsetRequest;
import com.jcabi.http.Request;
import java.util.Iterator;
import javax.json.JsonObject;
import org.cactoos.iterable.Mapped;

/**
 * JetBrains Hub Permissions.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Permissions_Get-All-Permissions.html">JetBrains Hub Permissions</a>
 * @since 0.2.0
 */
final class RtPermissions implements Permissions {

    /**
     * Origin request.
     */
    private final Request origin;

    /**
     * Projects request.
     */
    private final Request req;

    /**
     * Ctor.
     * @param req Origin request
     */
    RtPermissions(final Request req) {
        this(
            req,
            req.uri()
                .path("/permissions")
                .back()
        );
    }

    /**
     * Ctor.
     * @param origin Origin request
     * @param req Projects request
     */
    private RtPermissions(final Request origin, final Request req) {
        this.origin = origin;
        this.req = req;
    }

    @Override
    public Permissions max(final int max) {
        return new RtPermissions(
            this.origin,
            new SkippedRequest(
                this.req,
                max
            )
        );
    }

    @Override
    public Permissions search(final Query query) {
        return new RtPermissions(
            this.origin,
            new QueryableRequest(
                this.req,
                query
            )
        );
    }

    @Override
    public Permissions sort(final Sort sort) {
        return new RtPermissions(
            this.origin,
            new OrderedRequest(
                this.req,
                sort
            )
        );
    }

    @Override
    public Permissions fields(final Fields fields) {
        return new RtPermissions(
            this.origin,
            new SubsetRequest(
                this.req,
                fields
            )
        );
    }

    @Override
    public Iterator<Permission> iterator() {
        return new Mapped<JsonObject, Permission>(
            JsPermission::new,
            new RtPagination(this.req, "permissions")
        ).iterator();
    }
}

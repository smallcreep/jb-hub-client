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
 * Projects JetBrains Hub.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 * @checkstyle ClassDataAbstractionCouplingCheck (3 lines)
 */
final class RtProjects implements Projects {

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
    RtProjects(final Request req) {
        this(
            req,
            req.uri()
                .path("/projects")
                .back()
        );
    }

    /**
     * Ctor.
     * @param origin Origin request
     * @param req Projects request
     */
    private RtProjects(final Request origin, final Request req) {
        this.origin = origin;
        this.req = req;
    }

    @Override
    public Project project(final String id) {
        return new RtProject(this.req, id);
    }

    @Override
    public Projects max(final int max) {
        return new RtProjects(
            this.origin,
            new SkippedRequest(
                this.req,
                max
            )
        );
    }

    @Override
    public Projects sort(final Sort sort) {
        return new RtProjects(
            this.origin,
            new OrderedRequest(
                this.req,
                sort
            )
        );
    }

    @Override
    public Projects fields(final Fields fields) {
        return new RtProjects(
            this.origin,
            new SubsetRequest(
                this.req,
                fields
            )
        );
    }

    @Override
    public Iterator<Project> iterator() {
        return new Mapped<JsonObject, Project>(
            JsProject::new,
            new RtPagination(this.req, "projects")
        ).iterator();
    }

    @Override
    public Projects search(final Query query) {
        return new RtProjects(
            this.origin,
            new QueryableRequest(
                this.req,
                query
            )
        );
    }
}

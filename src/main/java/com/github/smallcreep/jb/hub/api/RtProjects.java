package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.Request;
import java.util.Iterator;

/**
 * Projects JetBrains Hub.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
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
        return null;
    }

    @Override
    public Projects max(final int max) {
        return new RtProjects(
            this.origin,
            this.req
                .uri()
                .queryParam("top", max)
                .back()
        );
    }

    @Override
    public Projects sort(final Sort sort) throws Exception {
        return new RtProjects(
            this.origin,
            this.req
                .uri()
                .queryParam("orderBy", sort.value())
                .back()
        );
    }

    @Override
    public Projects fields(final Fields fields) throws Exception {
        return new RtProjects(
            this.origin,
            this.req
                .uri()
                .queryParam("fields", fields.value())
                .back()
        );
    }

    @Override
    public Iterator<Project> iterator() {
        return null;
    }
}

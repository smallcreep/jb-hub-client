package com.github.smallcreep.jb.hub.api;

import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import com.jcabi.http.Request;
import lombok.EqualsAndHashCode;

/**
 * JetBrains Hub User API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Users_Get-User.html">User API</a>
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
@EqualsAndHashCode(of = {"request", "hub"})
public final class RtUsers implements Users {

    /**
     * RESTful request.
     */
    private final Request request;

    /**
     * Hub owner.
     */
    private final Hub hub;

    /**
     * Ctor.
     * @param hub Hub
     */
    public RtUsers(final Hub hub) {
        this(hub.entry(), hub);
    }

    /**
     * Ctor.
     * @param request Entry request
     * @param hub Hub
     */
    public RtUsers(final Request request, final Hub hub) {
        this.request = request;
        this.hub = hub;
    }

    @Override
    public Hub hub() {
        return this.hub;
    }

    @Override
    public User self() {
        return null;
    }
}

package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.Request;

/**
 * JetBrains Hub client entry point.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/Resources-for-Developers.html">HUB API</a>
 * @since 0.1
 */
public interface Hub {

    /**
     * RESTful request, an entry point to the Hub API.
     *
     * @return Request
     */
    Request entry();

    /**
     * Get Users.
     * @return Users.
     * @see Users
     */
    Users users();
}

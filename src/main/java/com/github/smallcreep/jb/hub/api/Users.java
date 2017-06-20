package com.github.smallcreep.jb.hub.api;

/**
 * JetBrains Hub Users API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Users.html">Users API</a>
 * @since 0.1
 */
public interface Users {

    /**
     * Get logged-in user.
     * @return User
     */
    User self();

}

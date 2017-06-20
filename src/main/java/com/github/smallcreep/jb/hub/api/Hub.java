package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.Request;

/**
 * JetBrains Hub client entry point.
 *
 * <p><a href="https://www.jetbrains.com/help/hub/Resources-for-Developers.html">More info </a>
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Hub {

    Request entry();

    Users users();
}

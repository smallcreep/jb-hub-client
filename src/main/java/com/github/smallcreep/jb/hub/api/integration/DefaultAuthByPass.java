package com.github.smallcreep.jb.hub.api.integration;

import com.github.smallcreep.jb.hub.api.Auth;
import com.github.smallcreep.jb.hub.api.Hub;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import org.cactoos.list.ArrayAsIterable;

/**
 * Authorization by pass with info from System properties.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultAuthByPass implements Auth {

    /**
     * Origin Auth.
     */
    private final Auth origin;

    /**
     * Ctor.
     * @throws IOException If fails
     * @throws URISyntaxException If fails
     */
    public DefaultAuthByPass() throws IOException, URISyntaxException {
        this.origin = new AuthByPass(
            new URI(System.getProperty("failsafe.hub.uri")),
            new DefaultOAuth2(),
            System.getProperty("failsafe.hub.username"),
            System.getProperty("failsafe.hub.password"),
            new ArrayAsIterable<>(
                System.getProperty("failsafe.hub.scopes").split(",")
            )
        );
    }

    @Override
    public Hub hub() throws IOException {
        return this.origin.hub();
    }
}

package com.github.smallcreep.jb.hub.api.integration;

import com.github.smallcreep.jb.hub.api.OAuth2Client;
import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import java.io.IOException;
import lombok.EqualsAndHashCode;

/**
 * Client with id and secret from System properties.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
@EqualsAndHashCode(of = "origin")
@Loggable(Loggable.DEBUG)
public final class DefaultOAuth2 implements OAuth2Client {

    /**
     * Origin client.
     */
    private final OAuth2Client origin;

    /**
     * Ctor.
     */
    public DefaultOAuth2() {
        this.origin = new OAuth2Client.Simple(
            System.getProperty("failsafe.hub.service"),
            System.getProperty("failsafe.hub.secret")
        );
    }

    @Override
    public String header() throws IOException {
        return this.origin.header();
    }
}

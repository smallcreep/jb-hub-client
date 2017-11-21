package com.github.smallcreep.json;

import java.io.IOException;
import java.io.UncheckedIOException;
import javax.json.JsonObject;

/**
 * JsonReadable that doesn't throw checked {@link Exception}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class UncheckedJsonReadable implements JsonReadable {

    /**
     * Original json.
     */
    private final JsonReadable origin;

    /**
     * Ctor.
     * @param origin Original json
     */
    public UncheckedJsonReadable(final JsonReadable origin) {
        this.origin = origin;
    }

    @Override
    public JsonObject json() {
        try {
            return this.origin.json();
        } catch (final IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }
}

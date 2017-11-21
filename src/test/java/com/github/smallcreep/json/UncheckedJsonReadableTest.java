package com.github.smallcreep.json;

import java.io.IOException;
import java.io.UncheckedIOException;
import org.junit.Test;

/**
 * Test Case for {@link UncheckedJsonReadable}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class UncheckedJsonReadableTest {

    /**
     * Check rethrows checked to unchecked Exception.
     *
     * @throws Exception If fails
     */
    @Test(expected = UncheckedIOException.class)
    public void rethrowsCheckedToUncheckedException() throws Exception {
        new UncheckedJsonReadable(
            () -> {
                throw new IOException("intended");
            }
        ).json();
    }
}

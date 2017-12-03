package com.github.smallcreep.misc;

import org.cactoos.ScalarHasValue;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link Optional}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class OptionalTest {

    /**
     * Check Single Optional return true if call method {@link Optional#has()}.
     * @throws Exception If fails
     */
    @Test
    public void singleAlwaysHasObject() throws Exception {
        MatcherAssert.assertThat(
            "Single optional doesn't have object!",
            new Optional.Single<>(
                "Single Optional"
            ).has(),
            CoreMatchers.equalTo(true)
        );
    }

    /**
     * Check Empty Optional return false if call method {@link Optional#has()}.
     * @throws Exception If fails
     */
    @Test
    public void emptyAlwaysDoesNotHaveObject() throws Exception {
        MatcherAssert.assertThat(
            "Empty optional have object!",
            new Optional.Empty<>().has(),
            CoreMatchers.equalTo(false)
        );
    }

    /**
     * Check Single Optional return the same encapsulated object.
     * @throws Exception If fails
     */
    @Test
    public void singleReturnObject() throws Exception {
        final String object = "Single Optional";
        MatcherAssert.assertThat(
            "Single optional doesn't have encapsulated object!",
            new Optional.Single<>(
                object
            ),
            new ScalarHasValue<>(
                object
            )
        );
    }

    /**
     * Check Single Optional return the same encapsulated object.
     * @throws Exception If fails
     */
    @Test(expected = UnsupportedOperationException.class)
    public void emptyReturnException() throws Exception {
        new Optional.Empty<>().value();
    }

}

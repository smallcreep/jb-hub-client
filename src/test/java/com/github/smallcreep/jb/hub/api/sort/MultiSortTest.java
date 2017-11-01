package com.github.smallcreep.jb.hub.api.sort;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link MultiSort}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class MultiSortTest {

    /**
     * Check multi sort return fields comma separated.
     *
     * @throws Exception If fails
     */
    @Test
    public void orderMultiFields() throws Exception {
        MatcherAssert.assertThat(
            "Multi sort doesn't return fields comma separated!",
            new MultiSort(
                new DefaultSort("first"),
                new DefaultSort("second")
            ),
            new ScalarHasValue<>("first, second")
        );
    }
}

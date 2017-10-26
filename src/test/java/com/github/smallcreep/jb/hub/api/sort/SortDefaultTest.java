package com.github.smallcreep.jb.hub.api.sort;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link SortDefault}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class SortDefaultTest {

    /**
     * Check default sort return encapsulated field name.
     *
     * @throws Exception If fails
     */
    @Test
    public void defaultSortField() throws Exception {
        MatcherAssert.assertThat(
            "Default sort doesn't return encapsulated field name!",
            new SortDefault("first"),
            new ScalarHasValue<>("first")
        );
    }
}

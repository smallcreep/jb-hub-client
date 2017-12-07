package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.request.FakeRequest;
import java.net.URI;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test Case for {@link PaginationResponse}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class PaginationResponseTest {

    /**
     * Follow to next link if this link presented in response.
     * @throws Exception If fails
     */
    @Test
    public void followToNextLink() throws Exception {
        final PaginationResponse response =
            new PaginationResponse(
                new FakeRequest().withBody(
                    "{ \"next\": \"https://localhost/test/next\" }"
                ).uri().set(new URI("http://localhost/test")).back().fetch()
            );
        MatcherAssert.assertThat(
            response.next().value().uri().get(),
            Matchers.equalTo(new URI("https://localhost/test/next"))
        );
    }

    /**
     * Return Empty Optional if response doesn't have next link.
     * @throws Exception If fails
     */
    @Test
    public void emptyNextLink() throws Exception {
        final PaginationResponse response =
            new PaginationResponse(
                new FakeRequest().withBody(
                    "{}"
                ).uri().set(new URI("http://localhost/test")).back().fetch()
            );
        MatcherAssert.assertThat(
            response.next().has(),
            Matchers.equalTo(false)
        );
    }

    /**
     * Follow to previous link if this link presented in response.
     * @throws Exception If fails
     */
    @Test
    public void followToPreviousLink() throws Exception {
        final PaginationResponse response =
            new PaginationResponse(
                new FakeRequest().withBody(
                    "{ \"prev\": \"https://localhost/test/previous\" }"
                ).uri().set(new URI("http://localhost/test")).back().fetch()
            );
        MatcherAssert.assertThat(
            response.previous().value().uri().get(),
            Matchers.equalTo(new URI("https://localhost/test/previous"))
        );
    }

    /**
     * Return Empty Optional if response doesn't have previous link.
     * @throws Exception If fails
     */
    @Test
    public void emptyPreviousLink() throws Exception {
        final PaginationResponse response =
            new PaginationResponse(
                new FakeRequest().withBody(
                    "{}"
                ).uri().set(new URI("http://localhost/test")).back().fetch()
            );
        MatcherAssert.assertThat(
            response.previous().has(),
            Matchers.equalTo(false)
        );
    }
}

package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.ApacheRequest;
import java.net.HttpURLConnection;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test Case for {@link RtJson}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtJsonTest {

    /**
     * RtJson can fetch HTTP request.
     *
     * @throws Exception if there is any problem
     */
    @Test
    public void sendHttpRequest() throws Exception {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(HttpURLConnection.HTTP_OK, "{\"body\":\"hi\"}")
        ).start();
        final RtJson json = new RtJson(new ApacheRequest(container.home()));
        MatcherAssert.assertThat(
            json.fetch().getString("body"),
            Matchers.equalTo("hi")
        );
        container.stop();
    }

}

package com.github.smallcreep.jb.hub.api;

import com.github.smallcreep.jb.hub.api.fields.MultipleFields;
import com.github.smallcreep.jb.hub.api.fields.NestedField;
import com.github.smallcreep.jb.hub.api.fields.SubFields;
import com.github.smallcreep.jb.hub.api.integration.DefaultAuthByPass;
import com.github.smallcreep.jb.hub.api.sort.AscSort;
import com.github.smallcreep.json.ObjectHasJson;
import javax.json.Json;
import org.cactoos.iterable.IterableOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Integration Test Case for {@link Projects}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 * @checkstyle ClassDataAbstractionCouplingCheck (3 lines)
 */
public final class ProjectsITCase {

    /**
     * Integration test case for projects,
     * check Sort, Partial request, Fields Syntax.
     * @throws Exception If fails
     * @checkstyle MethodLengthCheck (3 lines)
     */
    @Test
    @Ignore
    public void integrationCheckProjects() throws Exception {
        final Iterable<Project> projects = new DefaultAuthByPass()
            .hub()
            .projects()
            .max(1)
            .sort(
                new AscSort(
                    "key"
                )
            )
            .fields(
                new MultipleFields(
                    new Field.Simple(
                        "key"
                    ),
                    new Field.Simple(
                        "id"
                    ),
                    new NestedField(
                        new Field.Simple(
                            "team"
                        ),
                        new Field.Simple(
                            "type"
                        )
                    ),
                    new SubFields(
                        new Field.Simple(
                            "resources"
                        ),
                        new Field.Simple(
                            "id"
                        ),
                        new Field.Simple(
                            "key"
                        )
                    )
                )
            );
        final Iterable<ObjectHasJson> expected = new IterableOf<>(
            new ObjectHasJson(
                Json.createObjectBuilder()
                    .add(
                        "id",
                        "ed76cfc7-9ac2-41a5-9c85-13df3051db08"
                    )
                    .add(
                        "key",
                        "AP"
                    )
                    .add(
                        "resources",
                        Json.createArrayBuilder()
                            .add(
                                Json.createObjectBuilder()
                                    .add(
                                        "id",
                                        "87141cd1-ee49-47d8-85d2-50c6f8e3b32d"
                                    ).add(
                                    "key",
                                    "AP"
                                )
                            )
                    )
                    .add(
                        "team",
                        Json.createObjectBuilder()
                            .add(
                                "type",
                                "projectTeam"
                            )
                    )
                    .build()
            ),
            new ObjectHasJson(
                Json.createObjectBuilder()
                    .add(
                        "id",
                        "0"
                    )
                    .add(
                        "key",
                        "GLBL"
                    )
                    .add(
                        "resources",
                        Json.createArrayBuilder()
                            .add(
                                Json.createObjectBuilder()
                                    .add(
                                        "id",
                                        "c1c6bd95-ecd9-4efa-a58b-f85749ce400d"
                                    )
                                    .add(
                                        "key",
                                        "All Users"
                                    )
                            )
                            .add(
                                Json.createObjectBuilder().add(
                                    "id",
                                    "f95dc4bf-d370-43b6-90d2-02726a7a9c4d"
                                ).add(
                                    "key",
                                    "Registered Users"
                                )
                            )
                            .add(
                                Json.createObjectBuilder().add(
                                    "id",
                                    "df267d39-ca72-436f-9eb5-cf5722a09009"
                                ).add(
                                    "key",
                                    "Reporters"
                                )
                            )
                            .add(
                                Json.createObjectBuilder().add(
                                    "id",
                                    "4ed5d23e-af4e-419f-abe6-ce02cfd06b24"
                                ).add(
                                    "key",
                                    "workflow adsterra-team"
                                )
                            )
                            .add(
                                Json.createObjectBuilder().add(
                                    "id",
                                    "2f12da75-a7d6-437f-ac8f-70f47f8fb0bf"
                                ).add(
                                    "key",
                                    "workflow adsterra Team"
                                )
                            )
                    )
                    .add(
                        "team",
                        Json.createObjectBuilder().add(
                            "type",
                            "projectTeam"
                        )
                    )
                    .build()
            ),
            new ObjectHasJson(
                Json.createObjectBuilder()
                    .add(
                        "id",
                        "a5d71886-3b1e-40eb-a266-2f53e331a576"
                    )
                    .add(
                        "key",
                        "SP"
                    )
                    .add(
                        "resources",
                        Json.createArrayBuilder()
                            .add(
                                Json.createObjectBuilder()
                                    .add(
                                        "id",
                                        "4724cf34-7f48-422b-a0ee-586c372bd682"
                                    )
                                    .add(
                                        "key",
                                        "SP"
                                    )
                            )
                    )
                    .add(
                        "team",
                        Json.createObjectBuilder().add(
                            "type",
                            "projectTeam"
                        )
                    )
                    .build()
            ),
            new ObjectHasJson(
                Json.createObjectBuilder()
                    .add(
                        "id",
                        "77a3ea4f-9748-4e97-a7e7-d8bc8a8b88ed"
                    )
                    .add(
                        "key",
                        "TYA"
                    )
                    .add(
                        "resources",
                        Json.createArrayBuilder()
                            .add(
                                Json.createObjectBuilder()
                                    .add(
                                        "id",
                                        "4774a74d-9515-4f5c-af99-37e4da426208"
                                    )
                                    .add(
                                        "key",
                                        "TYA"
                                    )
                            )
                            .add(
                                Json.createObjectBuilder()
                                    .add(
                                        "id",
                                        "d8a6bec7-8fd1-4916-8475-a946c07ac8e2"
                                    )
                                    .add(
                                        "key",
                                        "adminTestYoutrackApi"
                                    )
                            )
                    )
                    .add(
                        "team",
                        Json.createObjectBuilder().add(
                            "type",
                            "projectTeam"
                        )
                    )
                    .build()
            )
        );
        MatcherAssert.assertThat(
            "Integration request for get projects doesn't correct!",
            projects,
            Matchers.contains(
                expected
            )
        );
    }
}

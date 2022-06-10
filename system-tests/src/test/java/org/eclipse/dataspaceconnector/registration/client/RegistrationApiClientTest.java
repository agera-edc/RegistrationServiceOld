package org.eclipse.dataspaceconnector.registration.client;

import org.eclipse.dataspaceconnector.registration.cli.RegistrationServiceCli;
import org.eclipse.dataspaceconnector.registration.client.api.RegistryApi;
import org.eclipse.dataspaceconnector.registration.client.models.Participant;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.dataspaceconnector.registration.client.IntegrationTestUtils.createParticipant;
import static org.junit.jupiter.api.Assertions.assertEquals;

// @IntegrationTest
public class RegistrationApiClientTest {
    ApiClient apiClient = ApiClientFactory.createApiClient(API_URL);
    RegistryApi api = new RegistryApi(apiClient);

    @Test
    void listParticipants() {
        RegistrationServiceCli app = new RegistrationServiceCli();
        CommandLine cmd = new CommandLine(app);

        var strings = new ArrayList<String>();
        strings.add("participants");
        strings.add("list");
        int exitCode = cmd.execute(strings.toArray(String[]::new));
        assertEquals(0, exitCode);

        assertThat(api.listParticipants())
                .containsExactly(participant);
    }
}

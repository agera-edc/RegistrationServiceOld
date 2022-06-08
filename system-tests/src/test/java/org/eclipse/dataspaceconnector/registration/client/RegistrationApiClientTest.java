package org.eclipse.dataspaceconnector.registration.client;

import org.eclipse.dataspaceconnector.registration.cli.CheckSum;
import org.eclipse.dataspaceconnector.registration.client.api.RegistryApi;
import org.eclipse.dataspaceconnector.registration.client.models.Participant;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("InstantiationOfUtilityClass")
public class RegistrationApiClientTest {
    static final String API_URL = "http://localhost:8181/api";

    ApiClient apiClient = ApiClientFactory.createApiClient(API_URL);
    RegistryApi api = new RegistryApi(apiClient);
    Participant participant = TestUtils.createParticipant();

    @Test
    void listParticipants() {
        CheckSum app = new CheckSum();
        CommandLine cmd = new CommandLine(app);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        var strings = new ArrayList<String>();
        strings.add("register");
        strings.addAll(List.of("-s", API_URL));
        strings.addAll(List.of("-n", Objects.requireNonNull(participant.getName())));
        strings.addAll(List.of("-u", Objects.requireNonNull(participant.getUrl())));
        Objects.requireNonNull(participant.getSupportedProtocols()).forEach(p -> strings.addAll(List.of("-p", p)));
        int exitCode = cmd.execute(strings.toArray(String[]::new));
        assertEquals(0, exitCode);
        assertEquals("", sw.toString());

// white box testing

        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() ->
                        assertThat(api.listParticipants())
                                .containsExactly(participant));

    }
}

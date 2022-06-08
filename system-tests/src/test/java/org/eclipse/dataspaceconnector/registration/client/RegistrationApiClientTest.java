package org.eclipse.dataspaceconnector.registration.client;

import com.github.javafaker.Faker;
import org.eclipse.dataspaceconnector.registration.api.TestUtils;
import org.eclipse.dataspaceconnector.registration.api.model.Participant;
import org.eclipse.dataspaceconnector.registration.cli.CheckSum;
import org.eclipse.dataspaceconnector.registration.client.api.RegistryApi;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("InstantiationOfUtilityClass")
public class RegistrationApiClientTest {
    static final Faker FAKER = new Faker();
    static final String API_URL = "http://localhost:8181/api";

    ApiClient apiClient = ApiClientFactory.createApiClient(API_URL);
    RegistryApi api = new RegistryApi(apiClient);
    Participant participant = TestUtils.createParticipant().build();

    @Test
    void listParticipants() {
        CheckSum app = new CheckSum();
        CommandLine cmd = new CommandLine(app);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

// black box testing
        var strings = new ArrayList<String>();
        strings.add("register");
        strings.addAll(List.of("-s", API_URL));
        strings.addAll(List.of("-n", participant.getName()));
        participant.getSupportedProtocols().forEach(p -> strings.addAll(List.of("-u", p)));
        int exitCode = cmd.execute(strings.toArray(String[]::new));
        assertEquals(0, exitCode);
        assertEquals("", sw.toString());

// white box testing

        await().atMost(Duration.ofSeconds(30))
                .untilAsserted(() ->
                        assertThat(api.listParticipants())
                                .usingRecursiveFieldByFieldElementComparator()
                                .co
                                .containsExactly(participant)

    }
}
/*

@CommandLine.Command(name = "register")
public class Register implements Callable<Integer> {

    @CommandLine.Option(names = "-s", required = true, description = "Registration service URL")
    String service;

    @CommandLine.Option(names = "-n", required = true, description = "name")
    String name;

    @CommandLine.Option(names = "-u", required = true, description = "URL")
    String url;

    @CommandLine.Option(names = "-p", required = true, description = "Supported protocols")
    String[] protocols;

    @Override
    public Integer call() {
        ApiClient apiClient = ApiClientFactory.createApiClient(service);
        RegistryApi api = new RegistryApi(apiClient);

        var participant = new Participant();
        participant.name(name);
        participant.url(url);
        participant.supportedProtocols(Arrays.asList(protocols));

        api.addParticipant(participant);
        return 0;
    }
}

 */

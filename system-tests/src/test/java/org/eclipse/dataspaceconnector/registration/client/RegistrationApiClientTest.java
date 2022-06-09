package org.eclipse.dataspaceconnector.registration.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.dataspaceconnector.registration.cli.RegistrationServiceCli;
import org.eclipse.dataspaceconnector.registration.client.models.Participant;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationApiClientTest {
    static final String API_URL = "http://localhost:8181/api";

    @Test
    void listParticipants() throws Exception {
        RegistrationServiceCli app = new RegistrationServiceCli();
        CommandLine cmd = new CommandLine(app);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        int exitCode = cmd.execute("-s", API_URL, "participants", "list");
        assertEquals(0, exitCode);

        String s = sw.toString();
        var participants = new ObjectMapper().readValue(s, new TypeReference<List<Participant>>() {
        });
        assertThat(participants).hasSize(3);
        assertThat(participants).extracting(Participant::getName).contains("consumer-eu");
    }
}

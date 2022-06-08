package org.eclipse.dataspaceconnector.registration.api;

import com.github.javafaker.Faker;
import org.eclipse.dataspaceconnector.registration.api.model.Participant;
import org.eclipse.dataspaceconnector.spi.monitor.ConsoleMonitor;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.dataspaceconnector.registration.api.TestUtils.createParticipant;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegistrationServiceTest {

    Monitor monitor = new ConsoleMonitor();
    InMemoryParticipantStore participantStore = mock(InMemoryParticipantStore.class);
    RegistrationService service = new RegistrationService(monitor, participantStore);
    Participant participant = createParticipant().build();

    @Test
    void listParticipants_empty() {
        assertThat(service.listParticipants()).isEmpty();
    }

    @Test
    void listParticipants() {
        when(participantStore.listParticipants()).thenReturn(List.of(participant));
        assertThat(service.listParticipants()).containsExactly(participant);
    }

    @Test
    void addParticipant() {
        service.addParticipant(participant);
        verify(participantStore).addParticipant(participant);
    }
}
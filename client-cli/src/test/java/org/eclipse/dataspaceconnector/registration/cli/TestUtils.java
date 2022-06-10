package org.eclipse.dataspaceconnector.registration.cli;

import com.github.javafaker.Faker;
import org.eclipse.dataspaceconnector.registration.client.models.Participant;

public class TestUtils {
    static final Faker FAKER = new Faker();

    private TestUtils() {
    }

    public static Participant createParticipant() {
        return new Participant()
                .name(FAKER.lorem().characters())
                .url(FAKER.internet().url())
                .addSupportedProtocolsItem(FAKER.lorem().word())
                .addSupportedProtocolsItem(FAKER.lorem().word());
    }
}
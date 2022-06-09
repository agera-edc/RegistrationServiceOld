package org.eclipse.dataspaceconnector.registration.cli;

import org.eclipse.dataspaceconnector.registration.client.ApiClient;
import org.eclipse.dataspaceconnector.registration.client.ApiClientFactory;
import org.eclipse.dataspaceconnector.registration.client.api.RegistryApi;
import org.eclipse.dataspaceconnector.registration.client.models.Participant;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Arrays;
import java.util.concurrent.Callable;

@Command(name = "register")
class RegisterParticipantCommand implements Callable<Integer> {
    @CommandLine.ParentCommand
    private ParticipantsCommand parent;

    @Option(names = "-n", required = true, description = "name")
    private String name;

    @Option(names = "-u", required = true, description = "URL")
    private String url;

    @Option(names = "-p", required = true, description = "Supported protocols")
    private String[] protocols;

    @Override
    public Integer call() {
        ApiClient apiClient = ApiClientFactory.createApiClient(parent.parent.service);
        RegistryApi api = new RegistryApi(apiClient);

        var participant = new Participant();
        participant.name(name);
        participant.url(url);
        participant.supportedProtocols(Arrays.asList(protocols));

        api.addParticipant(participant);
        return 0;
    }
}

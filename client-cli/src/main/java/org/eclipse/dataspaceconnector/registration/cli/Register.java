package org.eclipse.dataspaceconnector.registration.cli;

import org.eclipse.dataspaceconnector.registration.client.ApiClient;
import org.eclipse.dataspaceconnector.registration.client.ApiClientFactory;
import org.eclipse.dataspaceconnector.registration.client.api.RegistryApi;
import org.eclipse.dataspaceconnector.registration.client.models.Participant;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Arrays;
import java.util.concurrent.Callable;

@Command(name = "register")
public class Register implements Callable<Integer> {

    @Option(names = "-s", required = true, description = "Registration service URL")
    String service;

    @Option(names = "-n", required = true, description = "name")
    String name;

    @Option(names = "-u", required = true, description = "URL")
    String url;

    @Option(names = "-p", required = true, description = "Supported protocols")
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

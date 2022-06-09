package org.eclipse.dataspaceconnector.registration.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.dataspaceconnector.registration.client.ApiClient;
import org.eclipse.dataspaceconnector.registration.client.ApiClientFactory;
import org.eclipse.dataspaceconnector.registration.client.api.RegistryApi;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.*;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

@Command(name = "list")
class ListParticipantsCommand implements Callable<Integer> {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @ParentCommand
    private ParticipantsCommand parent;

    @Spec
    private CommandSpec spec;

    @Override
    public Integer call() throws Exception {
        ApiClient apiClient = ApiClientFactory.createApiClient(parent.parent.service);
        RegistryApi api = new RegistryApi(apiClient);
        PrintWriter out = spec.commandLine().getOut();
        MAPPER.writeValue(out, api.listParticipants());
        out.println();
        return 0;
    }
}

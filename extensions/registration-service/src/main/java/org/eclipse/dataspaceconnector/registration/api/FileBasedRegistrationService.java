package org.eclipse.dataspaceconnector.registration.api;

import org.eclipse.dataspaceconnector.registration.api.model.Participant;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.types.TypeManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of registration service interface that fetches participants list from json files.
 */
public class FileBasedRegistrationService implements RegistrationService {

    private final Path nodeJsonDir;
    private final String nodeJsonPrefix;
    private final TypeManager typeManager;
    private final Monitor monitor;

    /**
     * Constructs an instance of {@link FileBasedRegistrationService}
     * @param nodeJsonDir    directory containing source JSON files
     * @param nodeJsonPrefix prefix to filter source JSON files on
     * @param typeManager    type manager service
     * @param monitor        monitor service
     */
    public FileBasedRegistrationService(Path nodeJsonDir, String nodeJsonPrefix, TypeManager typeManager, Monitor monitor) {
        this.nodeJsonDir = nodeJsonDir;
        this.nodeJsonPrefix = nodeJsonPrefix;
        this.typeManager = typeManager;
        this.monitor = monitor;
    }

    @Override
    public List<Participant> listParticipants() {
        try {
            var files = Files.find(nodeJsonDir, 1,
                    (path, attrs) -> path.toFile().getName().startsWith(nodeJsonPrefix));
            return files
                    .map(this::mapToParticipant)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            monitor.severe("Listing all participants failed.", e);
            throw new EdcException(e);
        }
    }

    private Participant mapToParticipant(Path path){
        try {
            return typeManager.readValue(Files.readString(path), Participant.class);
        } catch (IOException e) {
            monitor.severe(String.format("Error while reading participant from path %s", path), e);
            throw new EdcException(e);
        }
    }
}

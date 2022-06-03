package org.eclipse.dataspaceconnector.registration.api;

import org.eclipse.dataspaceconnector.registration.api.model.Participant;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.types.TypeManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Registration service implementation fetching participants list from json files.
 */
public class FileBasedRegistrationService implements RegistrationService {

    private final Path nodeJsonDir;
    private final String nodeJsonPrefix;
    private final TypeManager typeManager;

    public FileBasedRegistrationService(Path nodeJsonDir, String nodeJsonPrefix, TypeManager typeManager) {
        this.nodeJsonDir = nodeJsonDir;
        this.nodeJsonPrefix = nodeJsonPrefix;
        this.typeManager = typeManager;
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
            throw new EdcException(e);
        }
    }

    private Participant mapToParticipant(Path path){
        try {
            return typeManager.readValue(Files.readString(path), Participant.class);
        } catch (IOException e) {
            throw new EdcException(e);
        }
    }
}

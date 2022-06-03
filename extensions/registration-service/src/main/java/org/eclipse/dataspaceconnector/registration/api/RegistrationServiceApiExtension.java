package org.eclipse.dataspaceconnector.registration.api;

import org.eclipse.dataspaceconnector.registration.api.health.HealthApiController;
import org.eclipse.dataspaceconnector.spi.WebService;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.types.TypeManager;

import java.nio.file.Path;
import java.util.Objects;

/**
 * EDC extension to boot the services used by the Registration Service.
 */
public class RegistrationServiceApiExtension implements ServiceExtension {
    @Inject
    private WebService webService;

    @Override
    public void initialize(ServiceExtensionContext context) {
        var nodeJsonPath = Path.of(Objects.requireNonNull(System.getenv("NODES_JSON_DIR"), "Env var NODES_JSON_DIR is null"));
        var nodeJsonPrefix = Objects.requireNonNull(System.getenv("NODES_JSON_FILES_PREFIX"), "Env var NODES_JSON_FILES_PREFIX is null");

        webService.registerResource(new HealthApiController());

        TypeManager typeManager = context.getTypeManager();
        RegistrationService service = new FileBasedRegistrationService(nodeJsonPath, nodeJsonPrefix, typeManager);
        webService.registerResource(new RegistrationApiController(service));
    }
}

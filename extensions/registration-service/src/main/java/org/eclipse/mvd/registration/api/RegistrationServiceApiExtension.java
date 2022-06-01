package org.eclipse.mvd.registration.api;

import org.eclipse.dataspaceconnector.spi.WebService;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.mvd.registration.api.enrollment.EnrollmentApiController;

public class RegistrationServiceApiExtension implements ServiceExtension {
    @Inject
    private WebService webService;

    @Override
    public void initialize(ServiceExtensionContext context) {
        var controller = new EnrollmentApiController();
        webService.registerResource(controller);
    }
}

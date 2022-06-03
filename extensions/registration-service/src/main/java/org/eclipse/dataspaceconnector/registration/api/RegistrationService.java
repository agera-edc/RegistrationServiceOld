package org.eclipse.dataspaceconnector.registration.api;

import org.eclipse.dataspaceconnector.registration.api.model.Participant;

import java.util.List;

/**
 * Interface for registration service operations.
 */
public interface RegistrationService {

    /**
     * Lists all dataspace participants.
     *
     * @return list of dataspace participants.
     */
    List<Participant> listParticipants();
}

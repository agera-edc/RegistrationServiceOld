package org.eclipse.dataspaceconnector.registration.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "registration-service-cli", mixinStandardHelpOptions = true,
        description = "Client utility for MVD registration service.",
        subcommands = {
                Register.class
        })
@SuppressWarnings("InstantiationOfUtilityClass")
public class RegistrationServiceCli {
    public static void main(String... args) {
        int exitCode = new CommandLine(new RegistrationServiceCli()).execute(args);
        System.exit(exitCode);
    }
}

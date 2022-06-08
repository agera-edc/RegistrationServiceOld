package org.eclipse.dataspaceconnector.registration.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum 4.0",
         description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.",
subcommands = {
        Register.class
})
public class CheckSum {
    public static void main(String... args) {
        int exitCode = new CommandLine(new CheckSum()).execute(args);
        System.exit(exitCode);
    }
}

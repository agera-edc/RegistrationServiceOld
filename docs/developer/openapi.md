# Generating the OpenApi Spec

It is possible to generate an OpenApi spec in the form of a `*.yaml` file by invoking a Gradle
task.

The file is at `resources/openapi/yaml/registration-service.yaml`.

## Generate OpenAPI `yaml` file

To re-generate the YAML file, invoke 
```shell
./gradlew clean resolve
```
package com.dev.scaffolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "scaffold",
    mixinStandardHelpOptions = true,
    version = "1.0.0",
    description = "Genera la estructura de Clean Architecture para proyectos Java."
)

public class ArchScaffolderApp implements Callable<Integer> {

    @Option(names = {"-n", "--name"}, description = "Nombre del proyecto", required = true)
    private String projectName;

    @Option(names = {"-p", "--path"}, description = "Ruta destino", defaultValue = ".")
    private String targetPath;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ArchScaffolderApp()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        Path baseDir = Paths.get(targetPath, projectName);
        
        // Estructura de Clean Architecture
        List<String> directories = List.of(
            "domain/model",
            "domain/repository",
            "domain/usecase",
            "application/dto",
            "infrastructure/entrypoints/api",
            "infrastructure/adapters/postgres"
        );

        System.out.printf("Construyendo scaffolding para '%s' en: %s%n%n", 
                projectName, baseDir.toAbsolutePath());

        try {
            for (String dir : directories) {
                Path fullPath = baseDir.resolve(dir);
                Files.createDirectories(fullPath);
                
                Path gitkeep = fullPath.resolve(".gitkeep");
                if (Files.notExists(gitkeep)) {
                    Files.createFile(gitkeep);
                }
                System.out.println("  [✔] Creado: " + dir);
            }
            System.out.println("\nEstructura generada exitosamente");
            return 0;
        } catch (IOException e) {
            System.err.println("Error al crear directorios: " + e.getMessage());
            return 1;
        }
    }
}
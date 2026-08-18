# Clean Architecture CLI Generator

A lightweight command-line interface tool built in **Java** to instantly scaffold backend project structures following **Clean Architecture** principles. 

This tool eliminates the repetitive task of creating directory trees and base files, allowing developers to focus strictly on domain logic and use cases from minute one.

## Features
*   **Instant Scaffolding:** Generates the complete folder structure in milliseconds.
*   **Clean Architecture Ready:** Pre-configured paths for Domain, Application, and Infrastructure layers.
*   **VCS Friendly:** Automatically generates `.gitkeep` files so empty architectural directories can be committed to version control immediately.
*   **Maven/Java Centric:** Designed with standard Java project layouts in mind.

## Architecture Structure

The CLI generates the following robust separation of concerns:

```mermaid
graph TD
    subgraph Infrastructure Layer
        API[entrypoints/api]
        JPA[adapters/jpa]
    end

    subgraph Application Layer
        DTO[application/dto]
    end

    subgraph Domain Layer
        UC[domain/usecase]
        REPO[domain/repository]
        MOD[domain/model]
    end

    API --> DTO
    API --> UC
    JPA -. implements .-> REPO
    UC --> REPO
    UC --> MOD
    REPO --> MOD
    
    classDef domain fill:#e1f5fe,stroke:#03a9f4,stroke-width:2px;
    classDef app fill:#f3e5f5,stroke:#9c27b0,stroke-width:2px;
    classDef infra fill:#fff3e0,stroke:#ff9800,stroke-width:2px;
    
    class MOD,REPO,UC domain;
    class DTO app;
    class API,JPA infra;

# 4Paws

## Diagramas UML
### Diagrama de Sequência

```mermaid
sequenceDiagram
    actor Admin
    actor Tutor
    actor Recepcionista
    actor Veterinário
    participant Sistema

    Admin ->> Sistema: Cadastra Serviços
    Admin ->> Sistema: Cadastra Veterinários
    Admin ->> Sistema: Cadastra Recepcionista
    Tutor ->> Recepcionista: Entra em contato
    Recepcionista ->> Tutor: Solicita informações
    Tutor ->> Recepcionista: Fornece informações
    Recepcionista ->> Sistema: Cadastra Tutor
    Recepcionista ->> Sistema: Cadastra Pet
    Recepcionista ->> Sistema: Cadastra Consulta

    Veterinário ->> Tutor: Consulta Pet
    Veterinário ->> Sistema: Registra observações sobre a Consulta
```

### Diagrama de Caso de Uso
![4PawsCasoDeUso](./docs//images/4PawsCasoDeUso.svg)

```mermaid
classDiagram
    class Usuario {
        +UUID id
        +String email
        +String senha
        +String nome
        +String cpf
        +Cargo cargo
    }

    class Tutor {
        +String telefone
        +Endereco endereco
        +List~Pet~ pets
    }

    class Veterinario {
        +Especialidade especialidade
        +long crmv
        +UF UF
    }

    class UF {
        <<enumeration>>
        PB
        PE
        SP
        ...
    }

    class Especialidade {
        <<enumeration>>
        CIRURGIA
        PATOLOGIA
        CARDIOLOGIA
        NEFROLOGIA_E_UROLOGIA
        NUTRICAO_E_NUTROLOGIA
        ...
    }

    class Recepcionista {

    }

    class Admin {

    }

    class Pet {
        +UUID id
        +String nome
        +double peso
        +Sexo sexo
        +String raça
        +Especie especie
        +LocalDateTime dataDeNascimento
        +Tutor tutor
    }

    class Especie {
        <<enumeration>>
        CANINA
        FELINA
    }

    class Sexo {
        <<enumeration>>
        FEMEA
        MACHO
    }    

    class Servico {
        +UUID id
        +String nome
        +double preço
    }

    class Consulta {
        +UUID id
        +LocalDateTime data
        +List~Veterinarios~ veterinarios
        +Pet pet
        +String observacoes
        +Servico servico
    }

    class Endereco {
        +UUID id
        +String bairro
        +long numero
        +String rua
    }

    class Cargo {
        +int id
        +String nome
    }

    class Pagamento {
        +Servico servico
        +double desconto
        +double precoFinal
    }

    Usuario <|-- Tutor
    Usuario <|-- Veterinario
    Usuario <|-- Recepcionista
    Usuario "1" --> "1" Cargo : tem

    Pet "1" --> "1" Sexo : tem
    Pet "1" --> "1" Especie : é de uma
    Pet "1" --> "1" Consulta : pertence

    Tutor "1" --> "1" Endereco: tem
    Tutor "1" --> "0..*" Pet : possui

    Consulta "1" --> "1" Servico : realiza
    Recepcionista --> Consulta: cadastra
    Recepcionista --> Tutor: cadastra
    Admin --> Servico: cadastra

    Veterinario "1" --> "1" Especialidade: tem
    Veterinario "1" --> "1" UF: tem
    Consulta "1" --> "1..*" Veterinario : é feita

    Pagamento "1" --> "1" Servico : paga um
```

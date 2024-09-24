# Documentação do Deploy no Railway

###### Geração do arquivo JAR
```shell
mvn clean
mvn install
```

###### Criação do arquivo Procfile
[Procfile na raiz do projeto](../Procfile)
```text
web: java -jar target/4paws-0.0.1-SNAPSHOT.jar
```

### Erros encontrados

#### [Solved] Erro ao fazer Deploy no Railway

###### Error log:
Java, release version 21 is not supported.

###### Solução:

```text
Adicionar variável de ambiente:
NIXPACKS_JDK_VERSION:21
```

Referências:

- https://nixpacks.com/docs/providers/java
- https://help.railway.app/questions/java-release-version-21-is-not-supporte-b3d67ab7


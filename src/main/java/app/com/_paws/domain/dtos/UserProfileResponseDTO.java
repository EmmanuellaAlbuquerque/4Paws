package app.com._paws.domain.dtos;

public record UserProfileResponseDTO (

    String email,

    String name,

    String cpf,

    String role

) implements IUserProfile {}

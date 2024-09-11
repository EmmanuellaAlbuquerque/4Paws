package app.com._paws.domain;

import app.com._paws.domain.dtos.IUserProfile;

public record UserProfileResponseDTO (

    String email,

    String name,

    String cpf,

    String role

) implements IUserProfile {}

package desafio.dio.service;


import desafio.dio.domain.model.User;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);



}

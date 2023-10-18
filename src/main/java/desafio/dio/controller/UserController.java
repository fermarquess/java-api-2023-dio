package desafio.dio.controller;

import desafio.dio.domain.model.User;
import desafio.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    // técnica de emcapulamento para não expor mais do que o necessário na interface de uso, vide toda implementação ter ficado no UserServiceImpl
    private final UserService userService;

    //construtor
    public UserController(UserService userService){
        this.userService = userService;
    }

    //expondo um Get, para recuperar pelo id
    @GetMapping("/{id}")
    public ResponseEntity<User> findbyId(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);

    }

}

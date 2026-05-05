package com.helpHands.helpHands.service;

import com.helpHands.helpHands.domain.user.User;
import com.helpHands.helpHands.mapper.UserMapper;
import com.helpHands.helpHands.repository.UserRepository;
import com.seuprojeto.model.UserRequest;
import com.seuprojeto.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserResponse create(UserRequest request) {
        User user = mapper.toEntity(request);
        user = repository.save(user);
        return mapper.toResponse(user);
    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return mapper.toResponse(user);
    }

    public UserResponse update(Long id, UserRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        mapper.updateEntity(user, request);
        user = repository.save(user);

        return mapper.toResponse(user);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }

}

package com.akkulov.service;

import com.akkulov.model.UserEntity;
import com.akkulov.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  /**
   * Сохранить пользователя и его сообщение.
   *
   * @param user пользователь и его сообщение
   */
  public UserEntity saveUser(UserEntity user) {
    return userRepository.save(user);
  }
}

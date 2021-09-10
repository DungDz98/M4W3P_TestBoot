package spring.service;

import spring.model.User;

public interface IUserService extends IGeneralService<User> {
    User findByUsername(String username);
    User getPrincipal();
}

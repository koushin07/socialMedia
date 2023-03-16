package com.socmed.socmed.modules.role;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoleService(RoleRepository roleRepository) {


    public List<Role> roles() {
        return roleRepository.findAll();
    }


}

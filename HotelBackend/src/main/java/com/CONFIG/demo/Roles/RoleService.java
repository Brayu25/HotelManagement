package com.CONFIG.demo.Roles;
import com.CONFIG.demo.Shared.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public EntityResponse add(Role role) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Role savedRole = roleRepository.save(role);
            entityResponse.setMessage("role created successfully");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setEntity(savedRole);
        }catch (Exception e){
            log.error("An error has occurred while trying to create a ROLE! {} ", e);
        }
        return entityResponse;
    }

    public EntityResponse findById(Long id) {
        EntityResponse res = new EntityResponse<>();
        try {
            Optional<Role> existing = roleRepository.findById(id);
            if (existing.isPresent()){
                res.setMessage("role found successfully");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(existing);
            }else {
                res.setMessage("not found");
                res.setStatusCode(HttpStatus.NO_CONTENT.value());
                res.setEntity("");
            }

        }catch (Exception e){
            log.error("Error e {}",e);
        }
        return res;
    }
}

package es.mastercloud.practice2.service;

import es.mastercloud.practice2.mapper.AppUserMapper;
import es.mastercloud.practice2.models.dao.AppUser;
import es.mastercloud.practice2.models.dto.AppUserDTO;
import es.mastercloud.practice2.models.dto.InAppUserDTO;
import es.mastercloud.practice2.service.repository.AppUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUsers;
    @Autowired
    private AppUserMapper appUserMapper;

    public AppUserDTO save(InAppUserDTO appUser){
        return appUserMapper.toAppUserDto(appUsers.save(appUserMapper.toAppUserFromInAppUserDTO(appUser)));
    }

    public Page<AppUserDTO> findAll(Pageable page){
        return appUsers.findAll(page).map(appUser -> appUserMapper.toAppUserDto(appUser));
    }

    public AppUserDTO findById(long id){
        return appUserMapper.toAppUserDto(appUsers.findById(id).orElseThrow());
    }

    public AppUserDTO replace(AppUser updatedAppUser){
        appUsers.findById(updatedAppUser.getId()).orElseThrow();
        return appUserMapper.toAppUserDto(appUsers.save(updatedAppUser));
    }

    public void deleteById(long id){
        appUsers.deleteById(id);
    }
}

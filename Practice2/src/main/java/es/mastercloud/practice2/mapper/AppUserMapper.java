package es.mastercloud.practice2.mapper;

import es.mastercloud.practice2.models.dao.AppUser;
import es.mastercloud.practice2.models.dto.AppUserDTO;
import es.mastercloud.practice2.models.dto.IdAppUserDTO;
import es.mastercloud.practice2.models.dto.InAppUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserDTO toAppUserDto(AppUser user);
    IdAppUserDTO toIdAppUserDto(AppUser user);
    AppUser toAppUserFromInAppUserDTO(InAppUserDTO appUserDTO);
}

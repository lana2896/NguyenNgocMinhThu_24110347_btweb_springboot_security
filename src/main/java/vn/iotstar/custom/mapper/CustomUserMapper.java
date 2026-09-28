package vn.iotstar.custom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import vn.iotstar.custom.dto.CustomUserDTO;
import vn.iotstar.custom.entity.CustomUser;

@Mapper(componentModel = "spring")
public interface CustomUserMapper {

	@Mapping(target = "roleName", source = "role.name")
	CustomUserDTO toDTO(CustomUser user);

}

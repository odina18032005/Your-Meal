package uz.pdp.website_yourmeal.mapper;

import org.mapstruct.Mapper;
import uz.pdp.website_yourmeal.dto.UserDto;
import uz.pdp.website_yourmeal.model.User;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}

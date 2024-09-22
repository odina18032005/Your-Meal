package uz.pdp.website_yourmeal.mapper;

import org.mapstruct.Mapper;
import uz.pdp.website_yourmeal.dto.AddressDto;
import uz.pdp.website_yourmeal.model.Address;

@Mapper
public interface AddressMapper {
    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}

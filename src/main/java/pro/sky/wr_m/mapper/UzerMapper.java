package pro.sky.wr_m.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pro.sky.wr_m.dto.UzerDTO;
import pro.sky.wr_m.entity.UzerEntity;

@Mapper(componentModel = "spring")
public interface UzerMapper {

    UzerMapper INSTANCE = Mappers.getMapper(UzerMapper.class);

    UzerDTO toDTO(UzerEntity uzerEntity);

    UzerEntity toEntity(UzerDTO uzerDTO);

}

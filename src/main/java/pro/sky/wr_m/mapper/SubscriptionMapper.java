package pro.sky.wr_m.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pro.sky.wr_m.dto.SubscriptionDTO;
import pro.sky.wr_m.entity.SubscriptionEntity;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionDTO toDTO(SubscriptionEntity subscriptionEntity);

    SubscriptionEntity toEntity(SubscriptionDTO subscriptionDTO);

}

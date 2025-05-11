package pro.sky.wr_m.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.wr_m.dto.UzerDTO;
import pro.sky.wr_m.entity.UzerEntity;
import pro.sky.wr_m.exception.InvalidUserException;
import pro.sky.wr_m.exception.UserNotFoundException;
import pro.sky.wr_m.mapper.UzerMapper;
import pro.sky.wr_m.repository.UzerRepository;
import pro.sky.wr_m.service.UzerService;

@Service
@Data
@RequiredArgsConstructor
public class UzerServiceImpl implements UzerService {

    private final UzerRepository uzerRepository;

    /**
     * Method to read information about user by id
     *
     * @param userId user's id
     * @return user data
     */
    @Override
    public UzerDTO findUserById(Long userId) {
        UzerEntity uzerEntity = uzerRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь отсутствует в базе данных"));
        return UzerMapper.INSTANCE.toDTO(uzerEntity);
    }

    /**
     * Method to create information about new user
     *
     * @param userDTO new user data
     * @return new user data
     */
    @Override
    public UzerDTO createUser(UzerDTO userDTO) {
        UzerEntity uzerEntity = UzerMapper.INSTANCE.toEntity(userDTO);
        uzerEntity.setId(null);                         // Будет сгенерирован новый id
        uzerRepository.save(uzerEntity);
        return UzerMapper.INSTANCE.toDTO(uzerEntity);
    }

    /**
     * Method to update information about user
     *
     * @param userDTO user updated info
     * @param userId  user's id
     * @return user updated info
     */
    @Override
    public UzerDTO updateUser(UzerDTO userDTO, Long userId) {
        UzerEntity uzerEntity = uzerRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь отсутствует в базе данных"));
        if (uzerEntity.getId() == userId) {
            uzerRepository.save(UzerMapper.INSTANCE.toEntity(userDTO));
            return userDTO;
        } else {
            throw new InvalidUserException("Ошибка в идентификаторе пользователя");
        }
    }

    /**
     * Method to delete information about user
     *
     * @param userId user's id
     * @return user deleted info
     */
    @Override
    public UzerDTO deleteUser(Long userId) {
        UzerEntity uzerEntity = uzerRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь отсутствует в базе данных"));
        uzerRepository.deleteById(userId);
        return UzerMapper.INSTANCE.toDTO(uzerEntity);
    }

}

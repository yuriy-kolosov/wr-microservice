package pro.sky.wr_m.service;

import pro.sky.wr_m.dto.UzerDTO;

public interface UzerService {

    UzerDTO findUserById(Long userId);

    UzerDTO createUser(UzerDTO user);

    UzerDTO updateUser(UzerDTO user, Long userId);

    UzerDTO deleteUser(Long userId);


}

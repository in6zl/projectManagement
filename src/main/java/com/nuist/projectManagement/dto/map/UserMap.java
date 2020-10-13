package com.nuist.projectManagement.dto.map;

import com.nuist.projectManagement.domain.User;
import com.nuist.projectManagement.dto.UserDTO;
import com.nuist.projectManagement.util.DateMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @Author zl
 * @Date 2020/10/13 14:39
 */
@Mapper(uses = DateMapperUtil.class, componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMap extends BaseMapper<User, UserDTO>{

}

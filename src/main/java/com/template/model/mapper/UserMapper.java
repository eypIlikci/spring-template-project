package com.template.model.mapper;

import com.template.model.dto.request.UserRegisterRequest;
import com.template.model.dto.response.UserResponse;
import com.template.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({

    })
    User map(UserRegisterRequest request);
    @Mappings({

    })
    UserResponse map(User user);
}

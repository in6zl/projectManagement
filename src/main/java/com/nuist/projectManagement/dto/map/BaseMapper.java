package com.nuist.projectManagement.dto.map;

public interface BaseMapper<E, D> {
    E toEntity(D d);
    D toDTO(E e);
}

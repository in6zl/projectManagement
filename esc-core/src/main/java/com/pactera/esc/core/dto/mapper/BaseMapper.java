package com.pactera.esc.core.dto.mapper;

public interface BaseMapper<E, D> {
    E toEntity(D d);
    D toDTO(E e);
}

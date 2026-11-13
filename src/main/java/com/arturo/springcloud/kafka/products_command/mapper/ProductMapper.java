package com.arturo.springcloud.kafka.products_command.mapper;

import com.arturo.springcloud.kafka.products_command.entity.Product;
import com.arturo.springcloud.kafka.products_command.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // 1. De Entity a DTO (solo toma id, name y price; ignora las fechas automáticamente)
    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> products);

    // 2. De DTO a Entity (mapea id, name y price; deja las fechas en null)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Product toEntity(ProductDto dto);

    // 3. Actualizar una Entity existente (conserva sus fechas originales)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(ProductDto dto, @MappingTarget Product product);
}

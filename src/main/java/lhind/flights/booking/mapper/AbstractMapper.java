package lhind.flights.booking.mapper;

public abstract class AbstractMapper<ENTITY, DTO> {
    public abstract ENTITY toEntity(DTO dto);
    public abstract DTO toDto(ENTITY entity);
}

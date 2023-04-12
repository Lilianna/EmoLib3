package be.technobel.emolibspring.converter;

import be.technobel.emolibspring.constants.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ConvertStatusCommonEnum implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEnum attribute) {
        if (attribute == null)
            return null;

        return switch (attribute) {
            case INACTIVE -> 0;
            default -> 1;
        };
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        return switch (dbData) {
            case 0 -> StatusEnum.INACTIVE;
            default -> StatusEnum.ACTIVE;
        };
    }

}
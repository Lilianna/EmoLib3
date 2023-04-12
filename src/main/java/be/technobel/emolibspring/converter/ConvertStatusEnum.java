package be.technobel.emolibspring.converter;

import be.technobel.emolibspring.constants.StatusRoomEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ConvertStatusEnum implements AttributeConverter<StatusRoomEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusRoomEnum attribute) {
        if (attribute == null)
            return null;

        return switch (attribute) {
            case CANCELED -> 2;
            case APPROVED -> 3;
            case REJECTED -> 4;
            case DELETED -> 5;
            default -> 1;
        };
    }

    @Override
    public StatusRoomEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        return switch (dbData) {
            case 2 -> StatusRoomEnum.CANCELED;
            case 3 -> StatusRoomEnum.APPROVED;
            case 4 -> StatusRoomEnum.REJECTED;
            case 5 -> StatusRoomEnum.DELETED;
            default -> StatusRoomEnum.WAITING;
        };
    }

}
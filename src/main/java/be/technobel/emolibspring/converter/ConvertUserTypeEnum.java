package be.technobel.emolibspring.converter;

import be.technobel.emolibspring.constants.UserTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ConvertUserTypeEnum implements AttributeConverter<UserTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserTypeEnum attribute) {
        if (attribute == null)
            return null;

        return switch (attribute) {
            case MANAGER -> 1;
            case DONOR -> 2;
            default -> 3;
        };
    }

    @Override
    public UserTypeEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        return switch (dbData) {
            case 1 -> UserTypeEnum.MANAGER;
            case 2 -> UserTypeEnum.DONOR;
            default -> UserTypeEnum.BORROWER;
        };
    }

}
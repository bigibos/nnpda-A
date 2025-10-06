package cz.upce.fei.nnpda.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValidEnum, CharSequence> {

    private List<String> acceptedValues;
    private boolean ignoreCase;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        ignoreCase = constraintAnnotation.ignoreCase();
        Enum<?>[] enumConstants = constraintAnnotation.enumClass().getEnumConstants();
        initializeAcceptedValues(enumConstants);
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean valid = checkIfValueTheSame(acceptedValues, value.toString());

        // ✨ Pokud hodnota není validní, přidejme výčet do message
        if (!valid) {
            String enumList = String.join(", ", acceptedValues);

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    context.getDefaultConstraintMessageTemplate()
                            .replace("{enumValues}", enumList)
            ).addConstraintViolation();
        }

        return valid;
    }

    protected boolean checkIfValueTheSame(List<String> acceptedValues, String value) {
        for (String acceptedValue : acceptedValues) {
            if (ignoreCase && acceptedValue.equalsIgnoreCase(value)) {
                return true;
            } else if (acceptedValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    protected void initializeAcceptedValues(Enum<?>... enumConstants) {
        if (enumConstants == null || enumConstants.length == 0) {
            acceptedValues = Collections.emptyList();
        } else {
            acceptedValues = Stream.of(enumConstants)
                    .map(Enum::name)
                    .collect(Collectors.toList());
        }
    }
}

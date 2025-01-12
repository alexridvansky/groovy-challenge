package validator.implementation

import messages.UserProfileUpdate
import messages.UserProfileUpdatePayload
import validator.ValidationResult
import validator.Validator
import validator.ValidatorBuilder

import java.time.Instant
import java.time.ZoneId
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class UserProfileUpdateValidator implements Validator<UserProfileUpdate> {

    @Override
    ValidationResult validate(UserProfileUpdate message) {
        def validatorBuilder = new ValidatorBuilder<UserProfileUpdatePayload>()
                .add("User ID must be greater than 0",
                        { it.userId },
                        { it > 0 })
                .add("Name must not be null or empty",
                        { it.name },
                        { it?.trim() })
                .add("Birth date must be valid and user cannot be older than 100 years",
                        { it.birthDate },
                        { birthDate ->
                            def maxAge = 100
                            def now = LocalDate.now()
                            def birthDateAsLocalDate = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate()
                            ChronoUnit.YEARS.between(birthDateAsLocalDate, now) <= maxAge
                        })
                .add("Gender must be 'male' or 'female'",
                        { it.gender },
                        { it in ['male', 'female'] })
                .add("InterestedIn must be 'men', 'women', or 'both'",
                        { it.interestedIn },
                        { it in ['men', 'women', 'both'] })

        return validatorBuilder.build().validate(message.data)
    }
}

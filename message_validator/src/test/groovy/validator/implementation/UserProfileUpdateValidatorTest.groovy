package validator.implementation

import messages.UserProfileUpdate
import messages.UserProfileUpdatePayload
import spock.lang.Specification

import java.time.ZonedDateTime

class UserProfileUpdateValidatorTest extends Specification {

    def "UserProfileUpdate validation should pass for valid data"() {
        given:
        def payload = new UserProfileUpdatePayload(userId: 1, name: "John", birthDate: System.currentTimeMillis() - (25 * 365 * 24 * 60 * 60 * 1000L), lastSeen: System.currentTimeMillis(), gender: "male", interestedIn: "women")
        def message = new UserProfileUpdate(payload)
        def validator = new UserProfileUpdateValidator()

        when:
        def result = validator.validate(message)

        then:
        result.success()
        result.errors.isEmpty()
    }

    def "UserProfileUpdate validation should fail for invalid data"() {
        given: "Id is 0, has no name, user is 105 years old, has unknown gender and is interested in aliens"
        def birthdate = ZonedDateTime.now().minusYears(105).toInstant().toEpochMilli()
        def payload = new UserProfileUpdatePayload(userId: 0, name: "", birthDate: birthdate, lastSeen: System.currentTimeMillis(), gender: "unknown", interestedIn: "aliens")
        def message = new UserProfileUpdate(payload)
        def validator = new UserProfileUpdateValidator()

        when: "Validating the message with invalid data"
        def result = validator.validate(message)

        then: "Validation should fail with several errors"
        !result.success()
        result.errors.size() == 5
        print(result.errors)
    }
}
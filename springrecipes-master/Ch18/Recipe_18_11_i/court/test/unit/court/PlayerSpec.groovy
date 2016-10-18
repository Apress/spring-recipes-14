package court

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Player)
class PlayerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test Non Empty Player"() {
        given: "A valid player is constructed"
            def player = new Player(name:'James',phone:'120-1111')
            mockForConstraintsTests(Player, [player])
        when: "validate is called"
            def result = player.validate();
        then: "it should be valid"
            assertTrue player.validate()
    }

    void testEmptyName() {
        given: "A player without a name is constructed"
            def player = new Player(name:'',phone:'120-1111')
            mockForConstraintsTests(Player, [player])
        when: "validate is called"
            def result = player.validate();
        then: "The name should be rejected"
            assertFalse result
            assertEquals 'Name cannot be null', 'nullable',player.errors['name']
    }

    void testEmptyPhone() {
        given: "A player without a phone is constructed"
            def player = new Player(name:'James',phone:'')
            mockForConstraintsTests(Player, [player])
        when: "validate is called"
            def result = player.validate()
        then: "The phone number should be rejected."
            assertFalse result
            assertEquals 'Phone cannot be null', 'nullable', player.errors['phone']
    }

}

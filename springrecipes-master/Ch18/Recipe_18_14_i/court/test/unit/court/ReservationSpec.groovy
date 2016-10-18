package court

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void testReservation() {
        given:
            def calendar = Calendar.instance
            calendar.with {
                clear()
                set MONTH, OCTOBER
                set DATE, 15
                set YEAR, 2009
                set HOUR, 15
                set MINUTE, 00
            }

            def validDateReservation = calendar.getTime()
            def reservation = new Reservation(
                    sportType:'Tennis', courtName:'Main',
                    date:validDateReservation,player:new Player(name:'James',phone:'120-1111'))
            mockForConstraintsTests(Reservation, [reservation])

        expect:
            assertTrue reservation.validate()
    }

    void testOutOfRangeDateReservation() {
        given:
            def calendar = Calendar.instance
            calendar.with {
                clear()
                set MONTH, OCTOBER
                set DATE, 15
                set YEAR, 2009
                set HOUR, 23
                set MINUTE, 00
            }

            def invalidDateReservation = calendar.getTime()
            def reservation = new Reservation(
                    sportType:'Tennis',courtName:'Main',
                    date:invalidDateReservation,player:new Player(name:'James',phone:'120-1111'))
            mockForConstraintsTests(Reservation, [reservation])

        expect:
            assertFalse reservation.validate()
            assertEquals 'Reservation date is out of range', 'invalid.weekdayHour',
            reservation.errors['date']
    }

    void testOutOfRangeSportTypeReservation() {
        given:
            def calendar = Calendar.instance
            calendar.with {
                clear()
                set MONTH, OCTOBER
                set DATE, 15
                set YEAR, 2009
                set HOUR, 15
                set MINUTE, 00
            }

            def validDateReservation = calendar.getTime()
            def reservation = new Reservation(
                    sportType:'Baseball',courtName:'Main',
                    date:validDateReservation,player:new Player(name:'James',phone:'120-1111'))
            mockForConstraintsTests(Reservation, [reservation])

        expect:
            assertFalse reservation.validate()
            assertEquals 'SportType is not valid', 'inList',reservation.errors['sportType']
    }
}

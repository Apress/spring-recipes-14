package court

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ReservationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Reservation.list(params), model:[reservationInstanceCount: Reservation.count()]
    }

    def show(Reservation reservationInstance) {
        respond reservationInstance
    }

    def create() {
        respond new Reservation(params)
    }

    @Transactional
    def save(Reservation reservationInstance) {
        if (reservationInstance == null) {
            notFound()
            return
        }

        if (reservationInstance.hasErrors()) {
            respond reservationInstance.errors, view:'create'
            return
        }

        reservationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
                redirect reservationInstance
            }
            '*' { respond reservationInstance, [status: CREATED] }
        }
    }

    def edit(Reservation reservationInstance) {
        respond reservationInstance
    }

    @Transactional
    def update(Reservation reservationInstance) {
        if (reservationInstance == null) {
            notFound()
            return
        }

        if (reservationInstance.hasErrors()) {
            respond reservationInstance.errors, view:'edit'
            return
        }

        reservationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Reservation.label', default: 'Reservation'), reservationInstance.id])
                redirect reservationInstance
            }
            '*'{ respond reservationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Reservation reservationInstance) {

        if (reservationInstance == null) {
            notFound()
            return
        }

        reservationInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Reservation.label', default: 'Reservation'), reservationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

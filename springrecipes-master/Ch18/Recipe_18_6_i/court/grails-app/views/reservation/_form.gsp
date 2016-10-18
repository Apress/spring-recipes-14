<%@ page import="court.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'sportType', 'error')} required">
	<label for="sportType">
		<g:message code="reservation.sportType.label" default="Sport Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="sportType" from="${reservationInstance.constraints.sportType.inList}" required="" value="${reservationInstance?.sportType}" valueMessagePrefix="reservation.sportType"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="reservation.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${reservationInstance?.date}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'courtName', 'error')} required">
	<label for="courtName">
		<g:message code="reservation.courtName.label" default="Court Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="courtName" required="" value="${reservationInstance?.courtName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'player', 'error')} required">
	<label for="player">
		<g:message code="reservation.player.label" default="Player" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="player" name="player.id" from="${court.Player.list()}" optionKey="id" required="" value="${reservationInstance?.player?.id}" class="many-to-one"/>

</div>


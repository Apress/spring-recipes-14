<%@ page import="court.Player" %>



<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="player.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${playerInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'phone', 'error')} required">
	<label for="phone">
		<g:message code="player.phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phone" required="" value="${playerInstance?.phone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'reservations', 'error')} ">
	<label for="reservations">
		<g:message code="player.reservations.label" default="Reservations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${playerInstance?.reservations?}" var="r">
    <li><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservation" action="create" params="['player.id': playerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservation.label', default: 'Reservation')])}</g:link>
</li>
</ul>


</div>


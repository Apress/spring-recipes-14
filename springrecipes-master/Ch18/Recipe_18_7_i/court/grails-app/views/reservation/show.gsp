
<%@ page import="court.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-reservation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reservation">
			
				<g:if test="${reservationInstance?.sportType}">
				<li class="fieldcontain">
					<span id="sportType-label" class="property-label"><g:message code="reservation.sportType.label" default="Sport Type" /></span>
					
						<span class="property-value" aria-labelledby="sportType-label"><g:fieldValue bean="${reservationInstance}" field="sportType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="reservation.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${reservationInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.courtName}">
				<li class="fieldcontain">
					<span id="courtName-label" class="property-label"><g:message code="reservation.courtName.label" default="Court Name" /></span>
					
						<span class="property-value" aria-labelledby="courtName-label"><g:fieldValue bean="${reservationInstance}" field="courtName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.player}">
				<li class="fieldcontain">
					<span id="player-label" class="property-label"><g:message code="reservation.player.label" default="Player" /></span>
					
						<span class="property-value" aria-labelledby="player-label"><g:link controller="player" action="show" id="${reservationInstance?.player?.id}">${reservationInstance?.player?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:reservationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${reservationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

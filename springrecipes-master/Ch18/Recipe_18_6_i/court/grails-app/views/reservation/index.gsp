
<%@ page import="court.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-reservation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="sportType" title="${message(code: 'reservation.sportType.label', default: 'Sport Type')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'reservation.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="courtName" title="${message(code: 'reservation.courtName.label', default: 'Court Name')}" />
					
						<th><g:message code="reservation.player.label" default="Player" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservationInstanceList}" status="i" var="reservationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reservationInstance.id}">${fieldValue(bean: reservationInstance, field: "sportType")}</g:link></td>
					
						<td><g:formatDate date="${reservationInstance.date}" /></td>
					
						<td>${fieldValue(bean: reservationInstance, field: "courtName")}</td>
					
						<td>${fieldValue(bean: reservationInstance, field: "player")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reservationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>


<table>
 <g:each in="${reservationInstanceList}" status="i" var="reservationInstance">
       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td><g:link action="show" id="${reservationInstance.id}">
               ${fieldValue(bean:reservationInstance, field:'id')}</g:link></td>
           <td>${fieldValue(bean:reservationInstance, field:'sportType')}</td>
           <td>${fieldValue(bean:reservationInstance, field:'date')}</td>
           <td>${fieldValue(bean:reservationInstance, field:'courtName')}</td>
           <td>${fieldValue(bean:reservationInstance, field:'player')}</td>
         </tr>
  </g:each>
</table>

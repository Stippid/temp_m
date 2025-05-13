<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>AMENDMENT IN RELIEF PROGRAM</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6></div>
	    			<div class="col-md-12">
						<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered">
							<thead style="background-color: #9c27b0; color: white;">
								<tr>
									<th >SUS No</th>
								    <th >UNIT NAME</th>
									<th >AUTH LETTER NO</th>
									<th >DATE</th>
									<th >PERIOD (From)</th>
									<th >PERIOD (To)</th>
									<th align="center">ACTION</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${getAmendmentReportList}" varStatus="num" >
									<tr>
										<td>${item[1]}</td>
										<td>${item[2]}</td>
										<td>${item[3]}</td>
										<td>${item[4]}</td>
										<td>${item[5]}</td>
										<td>${item[6]}</td>
										<td>${item[7]}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>	
				</div>
			</div>
    <c:url value="editamendment" var="editurl" />
	<form:form action="${editurl}"  method="post" id="editForm" name="editForm" modelAttribute="editId">
		<input type="hidden" name="editId" id="editId" value="0"/>
	</form:form>
	</div>
</div>
<script type="text/javascript">
	${UpdateFunction}
</script>


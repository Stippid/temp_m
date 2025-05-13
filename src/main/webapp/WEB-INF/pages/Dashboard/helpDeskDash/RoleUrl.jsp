<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 	
%>
<!doctype html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>MISO</title>
<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script> 
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<style type="text/css">
	.btn-group-sm > .btn, .btn-sm {
   		font-size: 12px;
   		line-height: 1.5;
	}
	.glyphicon-refresh::before {
   		content: "\e031";
	}
	.glyphicon {
	    font-family: 'Glyphicons Halflings';
   		font-style: normal;
   		font-weight: 400;
   		line-height: 1;
	}
	.cue_text .col-md-12{
	  justify-content: center;
	}
</style>
</head>
<body>
  <form:form name="role_mst" id="role_mst" action="role_mstAction" method='POST' commandName="role_mstCMD">
	<div class="container" align="center">
       	<div class="card">
         		<div class="card-header"> <h5>Role Master</h5></div>
         	<div class="card-body" >
		 		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" style="font-weight: bold;">
						<thead style="background-color: #9c27b0; color: white;">
							<tr>
								<th style="font-size: 15px ;">Ser No</th>	
								<th style="font-size: 15px ;">Role Name</th>	
								<th style="font-size: 15px ;">Role Type</th>	
								<!-- <th style="font-size: 15px ;">Access Level</th>	
								<th style="font-size: 15px ;">Sub Access Level</th>	
								<th style="font-size: 15px ;">Staff Level</th>		 -->
								<!-- <th style="font-size: 15px; width: 200px" class='lastCol'>Action</th> -->
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;">${num.index+1}</td>
										<td style="font-size: 15px;">${item.role}</td>
										<td style="font-size: 15px;">${item.role_type}</td>
										<%-- <td style="font-size: 15px;">${item.access_lvl}</td>
										<td style="font-size: 15px;">${item.sub_access_lvl}</td>	
										<td style="font-size: 15px;">${item.staff_lvl}</td>		 --%>
										<%-- <td style="font-size: 15px; width: 200px">${item.id}</td>	 --%>				
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
         </div>
	</div>
  </form:form>
   <c:url value="role_report" var="role_reportUrl" />
		<form:form action="${role_reportUrl}" method="post" id="searchForm" name="searchForm" >
		</form:form> 
</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
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
  <form:form name="TicketStatusNew" id="TicketStatusNew" action="" method='POST' commandName="TicketStatusNewCMD">
	<div class="container" align="center">
       	<div class="card">
         		<div class="card-header"> <h5>${ticket_status} Tickets</h5></div>
         	<div class="card-body" >
		 		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead style="background-color: #9c27b0; color: white;font-size: 15px">
							<tr>
								<th width="8%;">Ser No</th>
								<th width="10%;">Ticket Id</th>
								<th width="10%;">Module</th>
								<th>Issue Summary</th>
								<th width="10%;">Date</th>
								<th width="15%;">Help Topic</th>
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<th>${num.index+1}</th>
										<th>${item.ticket}</th>
										<th>${item.module_name}</th>
										<th>${item.issue_summary}</th>
										<th>${item.created_on}</th>
										<th>${item.help_topic}</th>
										
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
         </div>
	</div>
  </form:form>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script> 
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 

	
%>

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
    		<div class="card-header"> <h5>Logged In Users</h5></div>
         	<div class="card-body" >
		 		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="LoggedinReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead style="background-color: #9c27b0; color: white;">
							<tr>
								<th style="width: 10%;">Ser No</th>	
								<th style="width: 25%;">Loggedin Users</th>	
								<th style="width: 50%;">Unit Name</th>	
								<th style="width: 15%;">SUS No</th>	
							</tr>
						</thead> 
						<tbody style="font-weight: bold;">
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="width: 10%;">${num.index+1}</td>
										<td style="width: 25%;">${item.login_name}</td>
										<td style="width: 50%;">${item.unit_name}</td>
										<td style="width: 15%;">${item.user_sus_no}</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
         </div>
	</div>
  </form:form>
   <c:url value="loggedin_report" var="loggedin_reportUrl" />
		<form:form action="${loggedin_reportUrl}" method="post" id="searchForm" name="searchForm" >
		</form:form> 
</body>
</html>
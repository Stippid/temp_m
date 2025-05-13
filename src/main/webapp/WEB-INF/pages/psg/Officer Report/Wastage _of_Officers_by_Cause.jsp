<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<form:form name="cause_of_wastage" id="cause_of_wastage" action="cause_of_wastageAction" method="post" class="form-horizontal" commandName=""> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>WASTAGE OF OFFICERS BY CAUSE FOR THE</h5> <h6 class="enter_by">Reported On: ${date}</h6></div>
				<div class="card-body card-block">

					<%-- <div class="row" align="center">
						<div class="col-md-12" style="align:center">
							<label for="inputproject">Reported On:</label> <label
								id="current_date">${date}</label>
						</div>
					</div> --%>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Cause of Wastage </label>
								</div>
								<div class="col-md-8">
									  <input type="text" name="cause_of_wastage" id="cause_of_wastage" class="form-control-sm form-control" maxlength="10">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From Date </label>
								</div>
							<div class="col-md-8">
					            <input type="text" name="from_date" id="from_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
								onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
					          </div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Date </label>
								</div>
								<div class="col-md-8">
					            <input type="text" name="to_date" id="to_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
								onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
					          </div>
								
							</div>
						</div>
					</div>
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="Wastage_of_officer_report_url" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
					<input type="button" id="pbutton" class="btn btn-success btn-sm" value="PDF" onclick="getPDF()"> <br>
				</div>
			</div>
			</div>
	</div>
	<div class="container">
	<div class="col-md-12" id="getSearch_wastage" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_wastage "
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
						<tr>
							<th style="font-size: 15px; text-align: center; width:5%;">Ser No.</th>
							<th style="font-size: 15px; text-align: center;">CAUSE OF WASTAGE</th>
							<th style="font-size: 15px; text-align: center;width:15%;">NO OF OFFRS</th>	

						</tr>
					</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center; width:5%;">${num.index+1}</td> 
							<td style="font-size: 15px;width:5px;">${item[0]}</td>
							<td style="font-size: 15px; text-align: center; width:15%;">${item[1]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</form:form>

<c:url value="GetSearch_Wastage_of_officer_By_Cause" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"	name="searchForm" modelAttribute="command1">
	<input type="hidden" name="command1" id="command1" value="0">
	<input type="hidden" name="from_date1" id="from_date1" value="0">
	<input type="hidden" name="to_date1" id="to_date1" value="0">
</form:form>

<c:url value="search_report_Wastage_of_officer" var="reportUrl" />
<form:form action="${reportUrl}" method="post" id="searchviewForm" name="searchviewForm">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	</form:form>

<Script>
$(document).ready(function() {
 
	jQuery(function($){ //on document.ready  
		 datepicketDate('from_date');
		 datepicketDate('to_date');
		});
});

function Search(){	
	
	$("#command1").val($("#command").val()) ;	
	$("#from_date1").val($("#from_date").val()) ;	
	$("#to_date1").val($("#to_date").val()) ;	
	document.getElementById('searchForm').submit();

}
function getPDF()
{   
	document.getElementById('typeReport1').value='pdfL';
	document.getElementById('searchviewForm').submit();
}

</script>

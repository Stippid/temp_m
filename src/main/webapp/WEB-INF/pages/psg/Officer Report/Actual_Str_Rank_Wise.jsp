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
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<form:form name="Str_of_Regimental_ofc" id="Str_of_Regimental_ofc" action="Str_of_Regimental_ofcAction" method="post" class="form-horizontal" commandName=""> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header">
	    		<h5>ACTUAL STR OF OFFRS BY  ARM/SERVICES AND RANK WISE </h5> 
	    		<h6 class="enter_by">Reported On: ${date}</h6>
	    		</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Arm Desc  </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="Arm_Desc" name="Arm_Desc"
										class="form-control autocomplete" 
										autocomplete="off">
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
					<a href="rank_wise_report_url" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
					<input type="button" id="pbutton" class="btn btn-success btn-sm" value="PDF" onclick="getPDF();"> <br>
				</div>
			</div>
			</div>
	</div>
	
<div id="viewpage" class="container">	
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter"
				class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
				<thead>
						<tr>
							<th style="font-size: 15px; text-align: center;">Ser No</th>
							<th style="font-size: 15px; text-align: center;">PARENT ARM</th>
							<th style="font-size: 15px; text-align: center;">ARM DESC</th>
							<th style="font-size: 15px; text-align: center;">FM</th>
							<th style="font-size: 15px; text-align: center;">GEN </th>
							<th style="font-size: 15px; text-align: center;">LT</th>
							<th style="font-size: 15px; text-align: center;">MAJGEN </th>
							<th style="font-size: 15px; text-align: center;">BRIG</th>
							<th style="font-size: 15px; text-align: center;">COL</th>
							<th style="font-size: 15px; text-align: center;">COLTS</th>
							<th style="font-size: 15px; text-align: center;">LTCOL </th>
							<th style="font-size: 15px; text-align: center;">MAJ</th>
							<th style="font-size: 15px; text-align: center;">CAPT </th>
							<th style="font-size: 15px; text-align: center;">LT</th>
							<th style="font-size: 15px; text-align: center;">TOTAL</th>
							<th style="font-size: 15px; text-align: center;">PERCENTAGE</th>

						</tr>
					</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center;">${num.index+1}</td> 
							<td style="font-size: 15px;text-align: center;">${item[0]}</td>
							<td style="font-size: 15px;">${item[1]}</td>
							<td style="font-size: 15px; text-align: center;">${item[2]}</td>
							<td style="font-size: 15px; text-align: center;">${item[3]}</td>
							<td style="font-size: 15px; text-align: center;">${item[4]}</td>
							<td style="font-size: 15px; text-align: center;">${item[5]}</td>
							<td style="font-size: 15px; text-align: center;">${item[6]}</td>
							<td style="font-size: 15px; text-align: center;">${item[7]}</td>
							<td style="font-size: 15px; text-align: center;">${item[8]}</td>
							<td style="font-size: 15px; text-align: center;">${item[9]}</td>
							<td style="font-size: 15px; text-align: center;">${item[10]}</td>
							<td style="font-size: 15px; text-align: center;">${item[11]}</td>
							<td style="font-size: 15px; text-align: center;">${item[12]}</td>
							<td style="font-size: 15px; text-align: center;">${item[13]}</td>
							<td style="font-size: 15px; text-align: center;">${item[14]}</td>
							
					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</form:form>

<c:url value="GetSearch_Str_Rank_Wise" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"	name="searchForm" modelAttribute="command1">
	<input type="hidden" name="Arm_Desc1" id="Arm_Desc1" value="0">
	<input type="hidden" name="from_date1" id="from_date1" value="0">
	<input type="hidden" name="to_date1" id="to_date1" value="0">
</form:form>

<c:url value="search_report_Str_Rank_Wise" var="reportUrl" />
<form:form action="${reportUrl}" method="post" id="searchviewForm" name="searchviewForm">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	</form:form>

<Script>

$(document).ready(function() {
	jQuery(function($){ //on document.ready  
		 datepicketDate('from_date');
		 datepicketDate('to_date');
		});
	
	colAdj("getSearch_Letter");
});
function Search()
{	
	
	$("#Arm_Desc1").val($("#Arm_Desc").val()) ;	
	$("#from_date1").val($("#from_date").val()) ;	
	$("#to_date1").val($("#to_date").val()) ;	
	document.getElementById('searchForm').submit();
}
	
function getPDF()
{   
	
	document.getElementById('typeReport1').value='pdfL';
	alert(document.getElementById('typeReport1').value)
	document.getElementById('searchviewForm').submit();
}

</script>

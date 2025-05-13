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

<style>

table thead {
    font-size: 12px;
    background-color: #9c27b0;
    color: white;
    display: table;
    width: calc(100% - 16px) !important;
}

tbody {
    overflow-y: scroll;
    display: block;
    /* width: fit-content; */
    height: 200px;
    overflow-x: hidden;
}

</style>
<form:form name="Str_of_Regimental_ofc" id="Str_of_Regimental_ofc" action="Str_of_Regimental_ofcAction" method="post" class="form-horizontal" commandName=""> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header">
	    		<h5>HELD STR OF ARMY PERSONNEL IN UNITS AND ESTS OF CT </h5> 
	    		<h5>PART -1 UNITS/FMN ON GROUND</h5>
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
					<a href="str_of_army_personnel_report_url" class="btn btn-success btn-sm">Clear</a>
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
			<table id="getSearch_Letter "
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
						<tr>
							<th style="font-size: 15px; text-align: center;width:164px;">Ser No</th>
							<th style="font-size: 15px; text-align: center;width:300px;">ARM CODE</th>
							<th style="font-size: 15px; text-align: center; width: 59%;">ARM DESC</th>
							<th style="font-size: 15px; text-align: center;width: 164px;">TOTAL</th>
			
				

						</tr>
					</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center; width:164px;">${num.index+1}</td> 
							<td style="font-size: 15px;text-align: center;width:300px;">${item[0]}</td>
							<td style="font-size: 15px;width: 59%;">${item[1]}</td>
							<td style="font-size: 15px; text-align: center;width:164px;">${item[2]}</td>
					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</form:form>

<c:url value="GetSearch_Army_Personnel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"	name="searchForm" modelAttribute="command1">
	<input type="hidden" name="Arm_Desc1" id="Arm_Desc1" value="0">
	<input type="hidden" name="from_date1" id="from_date1" value="0">
	<input type="hidden" name="to_date1" id="to_date1" value="0">
</form:form>

<c:url value="search_report_Army_Personnel" var="reportUrl" />
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

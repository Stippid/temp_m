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

<form:form name="Parent_Arm" id="Parent_Arm" action="Parent_ArmAction"
	method="post" class="form-horizontal" commandName="">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>PARENT ARM REPORT</h5>
					<h6 class="enter_by">Reported On: ${date}</h6>
				</div>
				<div class="card-body card-block">
				
				<%-- <div class="row" align="center"> 
 						<div class="col-md-6" style="align-content: center;"> 
							<label for="inputproject">Reported On:</label> <label 
								id="current_date" >${date}</label> 
						</div> 
					</div> --%>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Arm No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="arm_desc" name="arm_desc" class="form-control autocomplete" autocomplete="off">
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
					<a href="PARENT_ARM_URL" class="btn btn-success btn-sm">Clear</a> <input
						type="button" class="btn btn-info btn-sm" onclick="Search();"
						value="Search">
						<input type="button" id="pbutton" class="btn btn-success btn-sm" value="PDF" onclick="getPDF()"> <br>
				</div>
			</div>
		</div>
	</div>
<div class="container" >	
	<div class="col-md-12" >
		<div class="card-header">
		<div class="container" >
			<h5>ARM DESCRIPTION</h5>
		</div>
	</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter "
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th style="text-align: center;width:144px;">Ser No</th>
						<th style="text-align: center;width: 81%;">Arm Description</th>
						<th style="text-align: center;width:164px;">Strength</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;">Data
								Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item1" items="${list}" varStatus="num">
						<tr>
							<%-- <td style="font-size: 15px; text-align: center; width:164px;">${num.index+1}</td> --%>
							 <td style="font-size: 15px; text-align: center; width:144px;">${num.index+1}</td>
							<td style="font-size: 15px;width: 81%;">${item1[1]}</td>
							<td style="font-size: 15px; text-align: center;width:164px;">${item1[2]}</td>
						</tr>
					</c:forEach>
					<tr><td style="font-size: 15px; text-align: center; " colspan="2">TOTAL</td>
					<th style="text-align: center;">${armsum}</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</div>
<div class="container" id="viewpage">	
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="card-header">
			<h5>GORKHA</h5>
		</div>
	</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter "
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th style="text-align: center; width:164px;">Ser No</th>
						<th style="text-align: center;width: 81%;">Arm Description</th>
						<th style="text-align: center; width:164px;">Strength</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list2.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="11">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item2" items="${list2}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center; width:164px;">${item2[0]}</td>
							<td style="font-size: 15px;width: 81%;">${item2[1]}</td>
							<td style="font-size: 15px; text-align: center;width:164px;">${item2[2]}</td>
						</tr>
					</c:forEach>
					<tr><td style="font-size: 15px; text-align: center; " colspan="2">TOTAL</td>
					<th style="text-align: center;">${gosum}</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="container" id="viewpage">	
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="card-header">
			<h5>INFANTRY</h5>
		</div>
	</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getSearch_Letter "
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr>
						<th style="text-align: center; width:164px;">Ser No</th>
						<th style="text-align:center;width: 81%;">Arm Description</th>
						<th style="text-align: center; width:164px;">Strength</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list3.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="11">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item4" items="${list3}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center;width:164px;">${item4[0]}</td>
							<td style="font-size: 15px;width: 81%; ">${item4[1]}</td>
							<td style="font-size: 15px; text-align: center;width:164px;">${item4[2]}</td>
						</tr>
					</c:forEach>
					<tr><td style="font-size: 15px; text-align: center; " colspan="2">TOTAL</td>
					<th style="text-align: center;">${insum}</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</form:form>


<c:url value="Get_Search_Parent_ArmReport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"	name="searchForm" modelAttribute="arm_desc1">
	<input type="hidden" name="arm_desc1" id="arm_desc1" value="0">
	<input type="hidden" name="str1" id="str1" value="0">
</form:form>

<c:url value="search_pdf_parent_arm" var="reportUrl" />
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

jQuery(function($){ 
	 datepicketDate('present_return_dt');
	 datepicketDate('last_return_dt');
	});

function Search(){	

	document.getElementById('searchForm').submit();

}

function getPDF()
{   
	$("#arm_desc1").val($("#arm_desc").val()) ;	
	//$("#from_date1").val($("#from_date").val()) ;	
	//$("#to_date1").val($("#to_date").val()) ;	
	document.getElementById('typeReport1').value='pdfL';
	document.getElementById('searchviewForm').submit();
}
</script>





<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/print_OLAPReport.js"></script>
<<form:form id="KLP_MatrixForm">
<div class="animated fadeIn">
   	<div class="container" align="center">
   		<div class="card">
   			<div class="card-header"><h5>KLP MATRIX REPORT</h5></div> 
        		<div class="card-body">
          			<div class="col-md-12">
					<div class="col-md-6">
         				<div class="row form-group">
               				<div class="col col-md-4">
                 				<label class=" form-control-label">Select Formation</label>
               				</div>
               				<div class="col-12 col-md-8">
                				<select id="formation" name="formation" class="form-control-sm form-control" >
					            	<option value="">--Select--</option>
                                   	<c:forEach var="item" items="${formation}" varStatus="num" >
               							<option value="${item.form_code_control}"  name="${item.level_in_hierarchy}" >${item.unit_name}</option>
               						</c:forEach>
               					</select>
               					<input type="hidden" id="level_in_hierarchy" name="level_in_hierarchy"/>
               				</div>
            			</div>
        			</div>
        			<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
                 				<label class=" form-control-label">Station</label>
               				</div>
               				<div class="col-12 col-md-8" id="head2">
								<input id="station" name="station" class="form-control-sm form-control"/>
							</div>
						</div>
					</div>
        		</div>
        	</div>	
			<div class="card-footer" align="center">
				<a href="klp_matrix_report" class="btn btn-success btn-sm" >Clear</a>  
            	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();" > 
            </div> 
		</div>
	</div>
</div>
</form:form>
<div class="animated fadeIn" id="printableArea">
	<div class="card-body">
		<table class="col-md-12">
			<tr>
		   		<td align="left">
		   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
		   		</td>
		   		<td align="center">
		   			<h5><u><b>RESTRICTED</b></u></h5>
		   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
		   			<br>
		   			<h5 style="text-decoration: underline;">STATION : <span id="station_div"></span></h5>
		   			<br>
		   			<span style="float:left;font-weight:bold;text-decoration: underline;">UNITS WITH APPROVED KLP</span>
		   		</td>
		   		<td align="right">
		   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
		   		</td>
			</tr>
		</table>
	</div>
	<div class="card-body">
		<div class="col-md-12">
  				<div  class="watermarked" data-watermark="" id="divwatermark" >
			<span id="ip"></span>
			<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				<thead style="background-color: #9c27b0; color: white;font-size: 13px;text-align: center;">
					<tr>
						<th rowspan="2" width="5%">Ser No</th>
						<th rowspan="2" width='20%'>Fmn/Unit</th>
						<th rowspan="2" width="5%">No of Units</th>
						<th rowspan="2" width='20%'>Imdt Higher Fmn</th>			
						<th rowspan="2" width='15%'>WE/PE/Interim Est</th>
						<th colspan="4" width="20%">Str</th>
						<th rowspan="2" width='15%'>Remarks</th>	
					</tr>
					<tr>
						<th width='5%'>Offrs</th>
						<th width='5%'>JCOs</th>
						<th width='5%'>OR</th>
						<th width='5%'>Civs</th>
					</tr>
				</thead>
				<tbody style="font-size: 12px;font-weight: bold;" align="left"></tbody>
			</table>
			</div>
		</div>
	</div>
	<div class="card-body">
		<table class="col-md-11" >
			<%-- <tr>
		   		<td align="right" >
		   			<h5><u><b>Report Generated On <span id="t_date"> ${t}</span></b></u><br></h5>			   			
		   		</td>
		   </tr> --%>
			<tr>
				<td align="center">
		   			<h5><u><b>RESTRICTED</b></u><br></h5>
		   		</td>
			</tr>
		</table>
	</div>
</div>
<div class="animated fadeIn" id="btnhide" style="display: none;">
	<div class="col-md-12" align="center">				
		<input type="button" id="btnhide" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">		
	</div>	
</div>
<script>
$(document).ready(function() {
	$("#btnhide").hide();
	$("#printableArea").hide();
	/* if('${list.size()}' == 0 ){
		$("#btnhide").hide();
		$("table#SearchReport").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
		$("table#SearchReport1").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
	}else{
		$("#btnhide").show();
		$("#printableArea").show();
	} */
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	
});
</script>
<script>
/* function Search(){
	var report_type=$("#type_of_report").val();
	var ddlYears=$("#ddlYears").val();
    if ($("#type_of_report").val() == "0") {
		alert("Please Enter Type of report.");
		return false;
	}
    $("input#type_of_report1").val($("#type_of_report").val());
    $("input#ddlYears1").val($("#ddlYears").val());
    $("#WaitLoader").show();
    document.getElementById('search1').submit();
} */
function Search(){
	$("#btnhide").hide();
	$("#printableArea").hide();
	$("#WaitLoader").show();
	var level_in_hierarchy= $("select#formation").find('option:selected').attr("name");
	$("#level_in_hierarchy").val(level_in_hierarchy);
	var formation = $("#formation").val();
	var station = $("#station").val();
	if(formation == ""){
		alert("Please select Formation");
		$("#formation").focus();
		$("#WaitLoader").hide();
		return false;
	}else if(station == ""){
		alert("Please select Station");
		$("#to_date").focus();
		$("#WaitLoader").hide();
		return false;
	}else{
		$("#SearchReport tbody").empty();
		var form = $('#KLP_MatrixForm')[0];
		var data = new FormData(form);
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "getKLP_MATRIXList?"+key+"="+value,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				$("#WaitLoader").hide();
				if(data.length == 0){
					var markup = "<tr><td align='center' width='100%;' colspan='10' style='color:red;'>Data Not Available</td></tr>";
					$("#SearchReport tbody").append(markup);
				}else{
					if(data[0].error != undefined){
						alert(data[0].error);
					}else{
						$("#station_div").text(station);
						var markup = "";
						for(var i=0;i<data.length;i++){
							markup += "<tr>"+
							"<td align='center' width='5%'>"+(i+1)+"</td>"+
							"<td width='20%'>"+data[i].unit_name +"</td>"+
							"<td align='center' width='5%'>"+(i+1)+"</td>"+
							"<td width='20%'>"+data[i].imdt+"</td>"+
							"<td width='15%'>"+data[i].wepe_pers_no+"</td>"+
							"<td align='center' width='5%'>"+data[i].officer+"</td>"+
							"<td align='center' width='5%'>"+data[i].jco+"</td>"+
							"<td align='center' width='5%'>"+data[i].or+"</td>"+
							"<td align='center' width='5%'>-</td>"+
							"<td align='center' width='15%'>-</td>"+
							"</tr>";
						}
						$("#SearchReport tbody").append(markup);
						$("#btnhide").show();
						$("#printableArea").show();
					}
				}
			}
		});
		return false;
	}
}
</script>
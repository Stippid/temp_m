<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


 
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<form:form action="daily_disease_svl_hospital_assignAction" id="ms_hos_assign"  method="post" class="form-horizontal" commandName="daily_disease_svl_hospital_assignCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>ASSIGNMENTS OF DAILY DISEASE SVL MEDICAL UNITS</h5>
		             <h6>
					    <span style="font-size: 12px; color: red">(To be entered by MISO)</span>
					 </h6>
		      </div> 		      
		     <div class="card-body card-block"> 
		      <div class="row">  
		          <div class="col-md-12">
		          <div class="col-md-8">
		          <div class="row form-group">
		              <div class="col-md-3" style="text-align: left;"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>User Name</label>
		              </div>
		              <div class="col-md-9"> 
					        <select name="user_id" id="user_id" class="form-control-sm form-control">	
           						<option value="-1">--Select the Value--</option>
           						 <c:if test="${not empty ml_1}">
           						  
           						<c:forEach var="j" begin="0" end="${fn:length(ml_1)-1}">
           						   <c:set var="datap" value="${fn:split(ml_1[j],':')}"/>
           						   <c:if test="${empty datap[1]}">
           						   </c:if> 
           						   
           						   <c:if test="${not empty datap[1]}">
           						      <option value="${datap[1]}" name="${datap[0]}">${datap[0]}</option>
           						   </c:if>
           						 
							    </c:forEach> 
							    </c:if>           								
					      </select>
		              </div>
		              </div>
		              </div>
		          </div>
		          </div>
		      </div>
		      <input type="hidden" id="app" name="app" />
		      <div class="form-control card-footer" align="center" >
            	   <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Get Hospital List" onclick="gethosplist();">
            	    <input type="submit" class="btn btn-success btn-sm" id="btn_save" value="Save"  onclick="return validate()" >
            	     <a	href="mnh_hospital_assign" type="reset" class="btn btn-primary btn-sm"  onclick="btn_clc();" > Clear </a> 
              </div>
              
           
              
              <div class="card-header" style="border: 1px solid rgba(0,0,0,.125);text-align: left;display: none;" id="d_reg2"> <strong>List Of MED Units</strong></div>
              <div class="card-body card-block" id="d_reg" style="display: none;"><!--  style="display: none;"-->
            	    <div class="col-md-12 row form-group" style="background-color:mistyrose;padding:7px;margin-left: 1px;">
            	        <div class="col-md-5">
            	             <b><input type=checkbox id='nSelAll' name='tregn' onclick="callsetall(this);">&nbsp;Select all (<b></b><span id="sregn" style='font-size:14px;'>0</span>)</b>&nbsp;&nbsp;
            	             <input id="nrInputa" type="text" placeholder="Search Data..." size="20">
					    </div>   
					    <div class="col-md-5" align="right">
					    
					         <b>MED Units Assign-<span id="tregn" style='font-size:14px;'>0</span></b>
					    </div>
				    </div>
				<div class="col-md-12" style="margin-top: 20px;">
					<select id="srctable" name="srctable" class="form-control"
						size="15" style="width: 100%;" multiple></select>

					<div class="col-md-4">
						<div class="col col-md-0" style="text-align: center;">
							<img src="js/miso/images/r_arrow.png" id="btnRight" width="40px;"
								height="40px;"><br>
							<br> <img src="js/miso/images/l_arrow.png" id="btnLeft"
								width="40px;" height="40px;">
						</div>
					</div>

				

					
					<select id="tartable" name="sus_tar" size="15" class="form-control" style="width: 100%;" multiple></select>
				</div>
			</div>
              
          </div>
     	 </div>
  
</form:form>


<script>

$(document).ready(function() {
	


	
	

}); 
	function btn_clc() {

		location.reload(true);
	}



	function drawregn(j) {
		var ii = 0;
		$("#srctable").empty();
		$("#tartable").empty();

		var a = [];
		var enc = j[j.length - 1].substring(0, 16);
		for (var i = 0; i < j.length; i++) {
			a[i] = dec(enc, j[i]);

		}
		for (var i = 0; i < a.length; i++) {
			var options;
			datap = a[i].split(":");
			if (datap[1] != null) {
				
				
				options += '<option value="'+ datap[0]+" -- "+datap[1]+'">'+ datap[0]+" -- "+datap[1]+ '</option>'; 
				$("select#srctable").html(options);
			}
			ii = ii + 1;
			
		}
		$("#sregn").text(ii - 1);
		getAssignData();
	}

	

	function gethosplist() {

		if ($("#user_id").val() == "-1") {
			alert("Please Select User Name");
			$("#user_id").focus();
			return false;
		}

		$.post("getMNHUnitAndSusNoList_daily_dsurve?" + key + "=" + value, {}, function(j) {
			if (j == "") {

				$("#srctable").empty();
				$("#d_reg").show();
				$("#d_reg2").show();
				$("#ch_d2").show();
				$("#tartable").show();
				getAssignData();

			} else {
			
				$("#d_reg").show();
				$("#d_reg2").show();
				$("#ch_d2").show();
				$("#tartable").show();
				drawregn(j);
			}
		});

	}

	
	function getAssignData() {
		var user_id1 = $("#user_id").val();
		$.post("getMNHASSIGNList_daily_dsurve?" + key + "=" + value, {user_id : user_id1}, function(j) {

			if (j == "" && $("#sregn").text() == 0) {
				$("#tartable").empty();
				alert("Data Not Available");

				$("#d_reg").hide();
				$("#d_reg2").hide();
				$("#ch_d2").hide();
				$("#tartable").hide();

			} else {
				$("#d_reg").show();
				$("#d_reg2").show();
				$("#ch_d2").show();
				$("#tartable").show();

				Assign_text(j);
				
			}

		});
	}
	function Assign_text(j) {
		//
		$("#tartable").empty();
		//$("#srctable").empty();

		var a = [];
      
		var trc_sus_no = "";
		var trc_unit_name = "";
		for (var i = 0; i < j.length; i++) {
			var options;
			options += '<option value="'+ j[i][0]+" -- "+j[i][1]+'">'+ j[i][0]+" -- "+j[i][1]+ '</option>'; 
			 $("select#tartable").html(options);
			
			$("#tregn").text(i - 1);
			

		}
		
		
	}

$('#btnRight').click(function(e) {
    var selectedOpts = $('#srctable option:selected');
    if (selectedOpts.length == 0) {
        alert("Nothing to Move.");
        e.preventDefault();
    }
    $('#tartable').append($(selectedOpts).clone());
    $(selectedOpts).remove();
    e.preventDefault();
});
$('#btnLeft').click(function(e) {
    var selectedOpts = $('#tartable option:selected');
    if (selectedOpts.length == 0) {
        alert("Nothing to Move.");
        e.preventDefault();
    }
   	$('#srctable').append($(selectedOpts).clone());
    $(selectedOpts).remove();
    e.preventDefault();
});


function validate(){
	document.getElementById("tartable").multiple = true;
	var x = document.getElementById("tartable").length;
	var sus_no = document.getElementById("tartable");

	for(var i =0;i<x;i++){
		
		var sus_no_split=sus_no.options[i].value;
		sus_no_split = sus_no_split.split("--")
		if(i == 0)
		{
			var str1 = sus_no_split[1];
		}
		else
		{
			var str1 = str1 + ',' + sus_no_split[1];	 
		}
	    document.getElementById("app").value = str1; 
	}	
	
	

	return true;
}


</script>


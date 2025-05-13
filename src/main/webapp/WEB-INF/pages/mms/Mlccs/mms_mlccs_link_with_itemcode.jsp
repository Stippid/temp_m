<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css">

<body class="mmsbg">
	<form:form action="mms_link_itemcode" id="mms_link_itemcodeFORM" name="mms_link_itemcodeFORM" method="post" class="form-horizontal" commandName="mms_link_itemcodeCMD">
		<div class="">
			<div class="container" align="center">
				<div class="card">
			
					 <div class="card-header mms-card-header">
		                  <b>LINKING OF CENSUS NO WITH ITEM CODE</b>
		             </div> 
					
					<div class="card-body card-block" style="text-align: left;">
						<div class="col-md-12 row form-group">
							<div class="col-md-3">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Census No</label>
							</div>
							<div class="col-md-3">
								<input type="text" id="census_no" name="census_no" placeholder="Search..."  maxlength="9"
								class="form-control-sm form-control" autocomplete="off" tabindex="1"/>
							</div>
						</div>


						<div class="col-md-12 row form-group" style="margin-top: -5px;">
							<div class="col-md-3">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Nomenclature</label>
							</div>
							<div class="col-md-9">
								<textarea id="nomen" name="nomen" placeholder="Search..." maxlength="255"
								class="form-control-sm form-control" autocomplete="off"></textarea>
							</div>
						</div>
						
						<div class="col-md-12 row form-group" id="b_data">	   
  						      <div class="col-md-7" align="right">
                					<input type="button" id="btn_d1" class="btn btn-primary btn-sm" value="Fetch Details" onclick="add_eqpt();">	
							  </div>
  						 </div>
						
						<div class="col-md-12 row form-group" style="margin-top: -5px;display: none;" id="b_data1">
							<div class="col-md-3">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Cat/Part No</label>
							</div>
							<div class="col-md-9">
								<input type="text" id="cat_part_no" name="cat_part_no"  maxlength="30"
								class="form-control-sm form-control" readonly="readonly">
							</div>

						</div>

						<div class="col-md-12 row form-group" style="margin-top: -5px;display: none;" id="b_data2">
							<div class="col-md-3">
								<label class=" form-control-label"><strong style="color: red;">* </strong>PRF Group</label>
							</div>
							<div class="col-md-9">
								<input type="text" id="prf_group" name="prf_group" class="form-control-sm form-control" readonly="readonly" /> 
								<input type="hidden" id="prf_code" name="prf_code" class="form-control" maxlength="8"/>
							</div>

						</div>


						<div class="col-md-12 row form-group" style="margin-top: -5px;display: none;" id="b_data3">
							<div class="col-md-3">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Linked Item Code</label>
							</div>
							<div class="col-md-6">
								<select id="item_type" name="item_type" class="form-control-sm form-control" disabled="disabled" tabindex="2">
								     <option selected value="-1">--Select the Value--</option>    
								</select>
								<input class="form-control" type="hidden" id="item_code" name="item_code" autocomplete="off" /> 
							</div>
						</div>


					</div>

					<div class="card-footer" align="center">
						<input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" /> 
						<input type="submit" class="btn btn-success btn-sm" value="Update" onclick="return isvalidData();" id="btn_save" style="display: none;"> 
						<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
					</div>
				</div>
			</div>
		</div>
	</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script>
$("#census_no").keyup(function(){
	var y = this.value;
	
	$().Autocomplete2('POST','getmms_census_noList',census_no,{census:y,nPara:"CENSUS"},'getMMSCensusToNomen',nomen);
	
});

$("#nomen").keyup(function(){
	var y = this.value;
	
	$().Autocomplete2('POST','getmmsDistinctNomenList',nomen,{y:y},'getMMSNomenToCensus',census_no);
	
});

function btn_clc(){
	location.reload(true);
}

function getItem(it,it2){	
	var mode=$("input#nrflowcontrol").val();
	var itemcode=$("select#item_code").val();
	var val;

	if(it != ""){
		val = it;
	}else{
		val = $("select#prf_code").val();
	}
	
	if(val != "" && val != null){

		
	
                $.post("getmms_item_codeList?"+key+"="+value, {
                    val : val
            }, function(j) {
                  
            }).done(function(j) {
            	if(j.length <= 0 || j == null || j == ''){
    				alert("No Search Result Found for Item Code");
    	 			$("#prf_code").focus();
    	 		}else{
    	 			var options = '';
    				
    				var a = [];
    				var enc = j[j.length-1].substring(0,16);
    				for(var i = 0; i < j.length; i++){
    					a[i] = dec(enc,j[i]);
    				}
    				
    				var data=a;
    				var datap;
    				
    				for(var i = 0; i < data.length-1; i++){
    					datap=data[i].split(":");
    					if (datap[0] == itemcode) {
    						options += '<option value="'+datap[0]+'" name="' + datap[0]+ '" selected>' + datap[1]+ '</option>';
    					}else{
    						options += '<option value="'+datap[0]+'" name="' + datap[0]+ '">' + datap[1]+ '</option>';
    					}
    					
    				}
    				$("select#item_type").html(options);
    				
    				if(it2 != ""){
    					$("select#item_type").val(it2);
    				}
    	 		}
    			
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
	 }	 
}


function add_eqpt(){
	var cen = $("#census_no").val();
	var nom = $("#nomen").val();
	
	if(cen == "" || nom == ""){
		alert("Please Enter Census No or Nomenclature.");
		return false;
	}
	
	if(cen != "" && nom != ""){
		$("#b_data").hide();
		$("#b_data1").show();
		$("#b_data2").show();
		$("#b_data3").show();
		$("#btn_save").show();
		$("#census_no").attr('disabled',true);
		$("#nomen").attr('disabled',true);
		$("#item_type").attr('disabled',false);
		
		var nPara = "CENSUS";
		
	
	
		
		           $.post("getmmsMlccsList?"+key+"="+value, {census:cen, nPara:nPara}, function(j) {

		        }).done(function(j) {
		        	var a = [];
					var enc = j[j.length-1].substring(0,16);
					for(var i = 0; i < j.length; i++){
						a[i] = dec(enc,j[i]);
					}
					var data=a[0].split(",");
					var datap;
					for(var i = 0; i < data.length-1; i++){
						datap=data[i].split(":");
						$("#nomen").val(datap[20]);
						$("#cat_part_no").val(datap[5]);
						$("#prf_code").val(datap[3]);
						$("#item_code").val(datap[4]);
					}
		            
		            $.post("getPRFNameByPRFCode?"+key+"="+value, {y:datap[3]}, function(j) {
		            }).done(function(j) {
		                    
		            	var enc = j[j.length-1].substring(0,16);
						var a = dec(enc,j[0]);
						$("#prf_group").val(a);
					});

					getItem(datap[3],datap[4]);
		                        
		            }).fail(function(xhr, textStatus, errorThrown) {
		       
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
	}
}


function isvalidData(){
	
	 if($("select#item_type").val()=="select" ||$("select#item_type").val()=="0" ||$("select#item_type").val()==null || $("select#item_type").val()=="null"){
		 alert("Please Enter selected Item.");
		 $("select#item_type").focus();
		 return false;
	 } 
	 
	 $("#census_no").attr('disabled',false);
	 $("#nomen").attr('disabled',false);
	 return true;
}	 
</script>

<script>
$(document).ready(function() {
	$('select#item_type').change(function() {		
		var a = $(this).val();
		document.getElementById("item_code").value=a;					
	});
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}catch (e){
		// TODO: handle exception
	}
});
</script>		
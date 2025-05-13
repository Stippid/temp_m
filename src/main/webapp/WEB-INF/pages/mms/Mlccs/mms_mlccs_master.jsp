<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 

<body class="mmsbg">
<form:form action="mms_mlccs_masterAction" method="post" id="mms_mlccs_masterFORM" name="mms_mlccs_masterFORM" class="form-horizontal" commandName="mms_mlccs_masterCMD"> 
     <input type="hidden" id="nrflowcontrol" name="nrflowcontrol" class="form-control">
     <div class="">
	      <div class="container" align="center">
	           <div class="card">
	           
	          		<div class="card-header mms-card-header">
				           <b>Master List of Controlled and Census Stores (MLCCS)</b>
				    </div> 
				    
				    <div class="card-body card-block">
				         <div class="col-md-12 row form-group" id="b_data">	   
  						      <div class="col-md-6" align="center">
                					<input type="button" id="btn_d1" class="btn btn-primary btn-sm" value="Add New Eqpt" onclick="add_eqpt();">	
							  </div>
							   
							  <div class="col-md-6" align="center">
                					<input type="button" id="btn_d2" class="btn btn-primary btn-sm" value="Modify Census" onclick="mod_census();">	
							  </div>       
  						 </div>
  						  
				         <div class="col-md-12 row form-group" id="b_data2" style="display: none;">	            					
	              				<div class="col-md-3" style="text-align: left;">
                  					<label class="form-control-label"><strong style="color: red;">* </strong>COS Section</label>
                				</div>
                				<div class="col-md-3">
                					<input type ="hidden" id="idhid" name="idhid">
                					<input type="hidden" name="nPara" id="nPara"/>
                  					<input type="text" id="cos_sec" name="cos_sec" maxlength="10" class="form-control-sm form-control"
                  					 placeholder="Search..." autocomplete="off"  required/>
								</div>
								
								<div class="col-md-3">
                					<input type="button" id="new" class="btn btn-primary btn-sm" value="Generate Census No" onclick="NewCensusNo();saveData();">	
							   </div>
	  				      </div>	
	  				      
	  				      <div class="col-md-12 row form-group" id="b_data3" style="display: none;">		 
              					<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Census No</label>
                				</div>
                				<div class="col-md-3">
                  					<input type="text" id="census_no" name="census_no" placeholder="Search..." class="form-control-sm form-control"
                  					 autocomplete="off" maxlength="9"  required/>
                  					<input type="hidden" id="hidText" name="hidText">
								</div>		
								
								<div class="col-md-3">
                  			        <input type="button" id="modifyid" class="btn btn-primary btn-sm" onclick="Modifycheck();" value="Modify"> 
                		        </div>  
		  				  </div>  
		  				  
		  				  <div class="col-md-12 row form-group" id="b_data4" style="display: none;margin-top: -10px;">	
				                <div class="col-md-3" style="text-align: left;">
				                  	<label class="form-control-label"><strong style="color: red;">* </strong>Nomenclature</label> 
				                </div>
				                <div class="col-md-9">
				                  <input type="text" id="nomen" name="nomen" class="form-control-sm form-control" autocomplete="off" maxlength="255" required/>
				                </div>
  						  </div> 	
				    </div>
				    
	          		<div class="card-body card-block" id="c_data" style="display: none;margin-top: -30px;">
						    <div class="col-md-12 row form-group">	
                				<div class="col-md-3" style="text-align: left;">
                  					<label class="form-control-label"><strong style="color: red;">* </strong>Auth/Letter No</label>
                				</div>
                				<div class="col-md-3">
                					<input type="text" class="form-control-sm form-control" id="auth_lett_no" name="auth_lett_no" autocomplete="off" placeholder="Enter Auth/Letter No..." maxlength="50"  required/>
                				</div>
                				
                				<div class="col-md-3" style="text-align: left;">
					                <label class="form-control-label"><strong style="color: red;">* </strong>Date</label>
					            </div>
								<div class="col-md-3">
						            <input type="date" id ="auth_date" name ="auth_date" class="form-control-sm form-control" maxlength="10" required > 
						        </div>	
	  					    </div>
	  					    
	  					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>PRF Group</label>
                				</div>
                					
                				<div class="col-md-9">
                  					
                  					<select name="prf_code" id="prf_code" class="form-control-sm form-control" required>	
	           							<option value="-1">--Select the Value--</option>
	           							<c:forEach var="item" items="${a_2}">
	           								<option value="${item.prf_group_code}" name="${item.prf_group_code}">${item.prf_group}</option>	
	           							</c:forEach>                  								
							        </select> 	
								</div>
  						    </div>  
  							
  							<div class="col-md-12 row form-group" style="margin-top: -10px;">	
				                <div class="col-md-3" style="text-align: left;">
				                  	<label class="form-control-label"><strong style="color: red;">* </strong>Item Code</label> 
				                </div>
				                <div class="col-md-9">
				                  
				                    <select name="item_code" id="item_code" class="form-control-sm form-control" required>	
	           							<option value="-1">--Select the Value--</option>
	           							<c:forEach var="item" items="${a_3}">
	           								<option value="${item.item_code}" name="${item.item_code}">${item.item_type}</option>	
	           							</c:forEach>                  								
							        </select>
				                </div>      
  					        </div> 
	  					
	  					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		 
	                			<div class="col-md-3" style="text-align: left;">
	                  				<label class="form-control-label"><strong style="color: red;">* </strong>Cat/Part No</label>
	                			</div>
	                			<div class="col-md-3">
	                  				<input type="text" id="cat_part_no" name="cat_part_no" class="form-control-sm form-control" 
	                  				maxlength="30" required autocomplete="off" placeholder="Enter Cat/Part No..." required/>
	                			</div>
	                									
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Accounting Unit</label>
                				</div>
                				<div class="col-md-3">
                					<select name="au" id="au" class="form-control-sm form-control" required>	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_1}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>				
	  					    </div>   
	  					
  					        <div class="col-md-12 row form-group" style="margin-top: -10px;">	
				                <div class="col-md-3" style="text-align: left;">
				                  	<label class="form-control-label"><strong style="color: red;">* </strong>Brief Description</label> 
				                </div>
				                <div class="col-md-9">
				                    <textarea id="brief_desc" name="brief_desc" placeholder="Enter Brief Description..." class="form-control-sm form-control"
				                     autocomplete="off" maxlength="255" required></textarea>
				                </div>    
  					        </div>
  					        
	  						<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Item Status</label>
                				</div>
                				<div class="col-md-3">
                  					<select name="item_status" id="item_status" class="form-control-sm form-control" required>	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_2}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>
  													
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Item Category</label>
                				</div>
                				<div class="col-md-3">
                					<select name="item_category" id="item_category" class="form-control-sm form-control" required>	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_3}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>	
	  					    </div>  
	  					
	  					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		 
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Class of Eqpt</label>
                				</div>
                				<div class="col-md-3">
                  					<select name="class_category" id="class_category" class="form-control-sm form-control" required>	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_4}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>
								
  								<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Country of Origin</label>
                				</div>
                				<div class="col-md-3">
                  					<input type="text" id="origin_country" name="origin_country" placeholder="Search..." class="form-control-sm form-control" maxlength="15" autocomplete="off"/>
								</div>
	  					    </div>  
	  					
	  						<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
	  							<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Nodal Dte</label>
                				</div>
                				<div class="col-md-3">
                  					<select name="dte_category" id="dte_category" class="form-control-sm form-control" >	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_5}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>
  								
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Eqpt Category</label>
                				</div>
                				<div class="col-md-3">
                  					<select name="dte_eqpt_category" id="dte_eqpt_category" class="form-control-sm form-control" >	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_6}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>
	  					    </div>  
	  					    
	  						<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Incl in AIH</label>
                				</div>
                				<div class="col-md-3">
                  					<select name="eqpt_priority" id="eqpt_priority" class="form-control-sm form-control" data-toggle="tooltip" title="Select YES to include in All India Holding Report.">	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_7}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>
  														
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Year of Induction</label>
                				</div>
                				<div class="col-md-3">
                					<input id="induc_year" name="induc_year" class="form-control-sm form-control" maxlength="4" placeholder="yyyy" onkeypress="return isNumberKey(event, this);"   autocomplete="off"/> 
								</div>	
	  					    </div>  
	  					
	  					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		 
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Digest Category</label>
                				</div>
                				<div class="col-md-3">
                  					<select name="digest_category" id="digest_category" class="form-control-sm form-control" >	
	           							<option value="">--Select--</option>
	           							<c:forEach var="item" items="${ml_8}">
	           								<option value="${item[0]}">${item[1]}</option>	
	           							</c:forEach>                  								
							        </select>
								</div>
  											
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Cost (Rs.)</label>
                				</div>
                				<div class="col-md-3">
                					<input type="text" id="cost" name="cost" class="form-control-sm form-control" onkeypress="return isNumberKey(event, this);" 
                					autocomplete="off" maxlength="10"  placeholder="Enter Cost..."/> 
								</div>		
	  					    </div>  
	  					
	  					    <div class="col-md-12 row form-group" style="margin-top: -10px;">		 			
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Manufacturing Agency</label>
                				</div>
                				<div class="col-md-3">
                					<input type="text" id="manuf_agency" name="manuf_agency" class="form-control-sm form-control" maxlength="100" autocomplete="off" placeholder="Enter Man. Agency..."/> 		
								</div>
  								
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">AHSP Agency</label>
                				</div>
                				<div class="col-md-3">
                  					<input type="text" id="ahsp_agency" name="ahsp_agency" placeholder="Enter AHSP Agency..." class="form-control-sm form-control" maxlength="100"  autocomplete="off"/>
								</div>	
	  					    </div>     
	  					
	  						<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">NATO Stock No(NSN)</label>
                				</div>
                				<div class="col-md-3">
                  					<input type="text" id="nato_stk_no" name="nato_stk_no" placeholder="Enter No..." class="form-control-sm form-control" maxlength="50"  autocomplete="off">
								</div>
  													
                				<div class="col-md-3" style="text-align: left;">
                  					<label for="text-input" class="form-control-label" style="margin-left: 13px;">Def Catalogue No(DCAN)</label>
                				</div>
                				<div class="col-md-3">
                					<input type="text" id="def_cat_no_dcan" name="def_cat_no_dcan"  class="form-control-sm form-control" autocomplete="off" maxlength="50"  placeholder="Enter No..."/>			
								</div>			
	  					    </div>  
	  					
	  					    <div class="col-md-12 row form-group" style="margin-top: -10px;">	
				                <div class="col-md-3" style="text-align: left;">
				                  	<label class="form-control-label" style="margin-left: 13px;">Remarks</label> 
				                </div>
				                <div class="col-md-9">
				                    <textarea id="spl_remarks" name="spl_remarks" placeholder="Enter Your Remarks..." maxlength="255" class="form-control-sm form-control" autocomplete="off"></textarea>
				                </div>
  					        </div>                             
	  		        </div>
	  		
	  		        <div class="card-footer" align="center" style="margin-top: -15px;">
						<input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" />   
	              		<input id="BtnSave" type="submit" class="btn btn-success btn-sm"  value="Save" onclick="return saveValidation();" style="display: none;">
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
var g_a = "a";
$("#origin_country").keyup(function(){
	var y = this.value;

	$().Autocomplete2('POST','getCountryName',origin_country,{y:y},null);
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
                            var options = '<option value="' + "Select" + '">'+ "--Select--" + '</option>';
                            $("select#item_code").html(options);
                             $("#prf_code").focus();
                     }else{
                             var options = '<option value="' + "Select" + '">'+ "--Select--" + '</option>';
                            
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
                            $("select#item_code").html(options);
                            
                            var w2 = '${a_1[0]}'.split(":");
                            if(w2 != ""){
                                    $("#item_code").val(w2[4]);
                            }
                            
                            if(it2 != ""){
                                    $("#item_code").val(it2);
                            }
                     }
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
	 }	 
}

function getPrf(pf){	
	var mode=$("input#nrflowcontrol").val();
	var prfcode=$("select#prf_code").val();
	var cos;
	var nPara;
	
	if(pf != ""){
		cos=pf;
		nPara="PRF";
	}else{
		cos=$("#cos_sec").val();
		nPara="COS";
	}

	

            $.post("getmms_prf_groupList1?"+key+"="+value, {
            	cos_sec : cos,nPara : nPara
        }, function(j) {
              
        }).done(function(j) {
        	if(j.length <= 0 || j == null || j == ''){
    			alert("No Search Result Found for PRF Group");
     			$("#prf_code").focus();
     		}else{
     			var options = '<option value="' + "" + '">'+ "--Select--" + '</option>';
     			
     			var a = [];
     			var enc = j[j.length-1].substring(0,16);
     			for(var i = 0; i < j.length; i++){
     				a[i] = dec(enc,j[i]);
     			}
     			
     			var data=a;
     			var datap;
     			
     			for(var i = 0; i < data.length-1; i++){
     				datap=data[i].split(":");
     				if (datap[0] == prfcode) {
     					options += '<option value="'+datap[0]+'" name="' + datap[0]+ '" selected>' + datap[1]+ '</option>';
     				}else{
     					options += '<option value="'+datap[0]+'" name="' + datap[0]+ '">' + datap[1]+ '</option>';
     				}
     				
     			}
     			$("select#prf_code").html(options); 
     			
     			if(pf != ""){
     				$("#prf_code").val(pf);
     			}
     		}
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	
	
	
}




function DataReadOnly(){
	var cos_sec = $("#cos_sec").val();
	var census_no = $("#census_no").val();

	if(cos_sec != "" || census_no != ""){
		$("#cos_sec").attr('readonly',true);
		$("#census_no").attr('readonly',true);
	}else{
		$("#cos_sec").attr('readonly',false);
		$("#census_no").attr('readonly',false);
	} 
} 

function add_eqpt(){
	$("#cos_sec").keyup(function(){
		var y = this.value;
		
		$().Autocomplete2('POST','getmms_cos_sectionList',cos_sec,{y:y});
	}); 
	
	$("#b_data2").show();
	$("#b_data3").hide();
	$("#b_data4").show();
	$("#nomen").attr("placeholder", "Enter Nomenclature").val("");
}

function mod_census(){
	$("#census_no").keyup(function(){
		var census = this.value;
	
		$().Autocomplete2('POST','getmms_census_noList',census_no,{census:census,nPara:"CENSUS"},'getMMSCensusToNomen',nomen);
	}); 
	$("#nomen").keyup(function(){
		var y = this.value;
	
		$().Autocomplete2('POST','getmmsDistinctNomenList',nomen,{y:y},'','','1');
	});
	
	$("#b_data2").hide();
	$("#b_data3").show();
	$("#b_data4").show();
	$("#nomen").attr("placeholder", "Search...").val("");
}

function NewCensusNo(){	
	$("#census_no").keyup(function(){
		var census = this.value;
	
		$().Autocomplete2('POST','getmms_census_noList',census_no,{census:census,nPara:"CENSUS"},'getMMSCensusToNomen',nomen);
	}); 
	
	$("#cos_sec").attr('disabled',false);
	$("#census_no").attr('disabled',true);
	$("#nomen").attr('disabled',false);
	
	$("#nrflowcontrol").val("NEW");
	
	if($("#cos_sec").val() == ""){
		alert("Please Fill COS Section and Again Press Generate Census Button");
		$("#cos_sec").focus();
		return false;
	}
	
	var cos=$("input#cos_sec").val();
	
	$.ajaxSetup({
		async: false
	});
	

	
	
	
            $.post("getNewCensusNo?"+key+"="+value, {
            	cos:cos
        }, function(j) {
              

        }).done(function(j) {
        
        	var enc = j[1].substring(0,16);
    		var a = dec(enc,j[0]);
    		$("#census_no").val(a);
    		
    		$("#c_data").show();
    		$("#b_data").hide();
    		$("#b_data3").show();
    		$("#new").hide();
    		$("#modifyid").hide();
    		$("#BtnSave").show();
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	 

	$("#cos_sec").attr('disabled',true);
	getPrf('');
}

function Modifycheck(censusVal){
	$("#cos_sec").attr('disabled',true);
	$("#census_no").attr('disabled',false);
	$("#nomen").attr('disabled',false);
	
	$("#hidText").val("");
	var census_no1;
	
	var nom = $("#nomen").val();
	if($("#census_no").val() == "" && nom != ""){
		
	
                $.post("getMMSNomenToCensus?"+key+"="+value, {
                	y:nom
            }, function(j) {
              
            }).done(function(j) {
            	if(j.length <= 0 || j == null || j == ''){
    				alert("Census No Not Found");
    	 			$("#nomen").focus();
    	 		}else{
    	 			var enc = j[j.length-1].substring(0,16);
    	 			var a = dec(enc,j[0]);
    	 			$("#census_no").val(a);
    	 		}
        }).fail(function(xhr, textStatus, errorThrown) {
        });

	}
	
	
 	if($("#census_no").val() == "" && censusVal == null && $("#nomen").val() == ""){
 		alert("Please Fill Census No");
		$("input#census_no").focus();
		return false;
	}else{	 
		if(censusVal != "" && censusVal != null ){
			$("input#census_no").val(censusVal);
	        census_no1=$("input#census_no").val();
	    }else{
	    	census_no1=$("input#census_no").val();
		} 
		

		
                 $.post("getmmsMlccsList?"+key+"="+value, {
                	 census : census_no1, nPara : "CENSUS"
             }, function(j) {
                    

             }).done(function(j) {
            	 if(j.length <= 0 || j == null || j == ''){
     				alert("No Search Result Found");
     	 		}else{
     	 			$("#c_data").show();
     				$("#b_data").hide();
     				$("#b_data2").show();
     				$("#new").hide();
     				$("#modifyid").hide();
     				$("#BtnSave").show();
     				
     	 			var a = [];
     	 			var enc = j[j.length-1].substring(0,16);
     	 			for(var i = 0; i < j.length; i++){
     	 				a[i] = dec(enc,j[i]);
     	 			}
     	 			
     	 			var data=a[0].split(",");
     	 			var datap;
     	 			for ( var i = 0; i < data.length-1; i++) {
     	 				datap=data[i].split(":");
     	 				$("#cos_sec").val(datap[0]);
     	 				getPrf(datap[3]);
     	 				$("#auth_lett_no").val(datap[1]);
     					$("#auth_date").val(datap[2]);
     					getItem(datap[3],datap[4]);
     					$("#cat_part_no").val(datap[5]);
     					$("select#au").val(datap[6]);
     					$("textarea#brief_desc").val(datap[7]);
     					$("select#item_status").val(datap[8]);
     					$("select#item_category").val(datap[9]);
     					$("select#class_category").val(datap[10]);
     					$("#origin_country").val(datap[11]);
     					$("#manuf_agency").val(datap[12]);
     					$("#ahsp_agency").val(datap[13]);
     					$("#nato_stk_no").val(datap[14]);
     					$("#def_cat_no_dcan").val(datap[14]);
     					$("#induc_year").val(datap[15]);
     					$("textarea#spl_remarks").val(datap[16]);
     					$("select#dte_category").val(datap[17]);
     					$("select#dte_eqpt_category").val(datap[18]);
     					$("select#eqpt_priority").val(datap[19]);
     					$("#nomen").val(datap[20]);
     					$("#cost").val(datap[21]);
     					$("#digest_category").val(datap[22]); 
     					
     	 			}			
     	 		}
     			$("input#BtnSave").val("Update");
     			$("#census_no").attr('disabled',true);
             }).fail(function(xhr, textStatus, errorThrown) {
             });
	}
	
}



function saveData(){
	$("input#hidText").val("Save");
}

function getCensusNo(cosSecVal,idVal){
	$("input#cos_sec").val(cosSecVal);
	

            $.post("getmmsNewReqList?"+key+"="+value, {
            	id:idVal
        }, function(j) {
        	
        }).done(function(j) {
        	getPrf('');
    		
    		$("input#prf_group").val(j[0].prf_group);
    		$("select#prf_code").val(j[0].prf_code);
    		$("input#auth_lett_no").val(j[0].auth_lett_no);
    		$("input#auth_date").val(j[0].auth_date);
    		$("input#cat_part_no").val(j[0].cat_part_no);
    		$("input#nomen").val(j[0].nomen);
    		$("textarea#brief_desc").val(j[0].brief_desc);
    		$("select#au").val(j[0].au);
    		$("select#item_status").val(j[0].item_status);
    		$("select#item_category").val(j[0].item_category);
    		$("textarea#spl_remarks").val(j[0].spl_remarks);
    		
    		getItem('','');
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
	 
            NewCensusNo();	  	
        }
	
function saveValidation(){
	
	
	var dd = $("#nPara").val();
	
	
 	if($("input#cost").val() == ""){
		$("input#cost").val("0");	
	} 
	
	if($("#cos_sec").val() == ""){
		alert("Please Select Cos Section No");
		$("input#cos_sec").focus();
		return false;
	} 
	
	if($("#census_no").val() == ""){
		alert("Please Select Census No");
		$("input#census_no").focus();
		return false;
	}
	
	if($("#nomen").val() == ""){
		alert("Please Select the nomenclature");
		$("input#nomen").focus();
		return false;
	} 
	
	if($("#nomen").val() != "" && (dd == "" || dd == "APP")){
		var nomen1 = $("#nomen").val();
		var c;
		
	
	   	
	 
                $.post("checkDetailsOfNomen?"+key+"="+value, {
                	nomen1 : nomen1
            }).done(function(j) {
            	c = j;
            	
    			if(j == nomen1){
    				alert("Nomenclature is already exist. Changes Denied.");
    		 		$("#nomen").focus();
    		 		return false;
    		 	}
                
        }).fail(function(xhr, textStatus, errorThrown) {
        });
		if(c == nomen1){
			return false;
		}
	}  
	
	if($("input#auth_lett_no").val() == ""){
		alert("Please Enter the Auth Lette No");
		$("input#auth_lett_no").focus();
		return false;
	}
	
	if($("input#auth_date").val() == ""){
		alert("Please Select the Date");
		$("input#auth_date").focus();
		return false;
	}
	
	if($("select#prf_code").val() == ""){
		alert("Please Select PRF Group");
		$("select#prf_code").focus();
		return false;
	} 
	
	 if($("select#item_code").val() == "Select"){
		alert("Please Select Item Code");
		$("select#item_code").focus();
		return false;
	} 
	
	 if($("input#cat_part_no").val() == ""){
		alert("Please Enter the Cat Part No");
		$("input#cat_part_no").focus();
		return false;
	}
	
	if($("select#au").val() == ""){
		alert("Please Select the Accounting Units");
		$("select#au").focus();
		return false;
	}
	
	if($("textarea#brief_desc").val() == ""){
		alert("Please Enter the Description");
		$("textarea#brief_desc").focus();
		return false;
	}
	
	if($("select#item_status").val() == ""){
		alert("Please select the Item Status");
		$("select#item_status").focus();
		return false;
	}
	
	if($("select#item_category").val() == ""){
		alert("Please select the Item Category");
		$("select#item_category").focus();
		return false;
	}
	
	if($("select#class_category").val() == ""){
		alert("Please select the Class category");
		$("select#class_category").focus();
		return false;
	}  
 
	var d = new Date();
	var year = d.getFullYear();
	if($("#induc_year").val() > year){
		alert("Year of Induction Should Not be Greater than Current Year");
		$("#induc_year").focus();
		return false;
	}
	
	$("#cos_sec").attr('disabled',false);
	$("#census_no").attr('disabled',false);
	return true;
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode < 48 || charCode >57)) {
		return false;
	}else{
		if (evt.target.value.search(/\./) > -1 && charCode == 46) {
			return false;
		}
	}
	return true;
}

</script>

<script>
$(document).ready(function() {
	var d = new Date();
	var year = d.getFullYear();
	$("#induc_year").val(year);
	
    $("#nPara").val('${nPara1}');
   
    var nParaVal = '${nPara1}';
    var cosSecVal = '${cos_sechid1}';
    var idVal = '${cosid1}';
    var censusVal = '${census_nohid1}';

	if(nParaVal != "APP" && nParaVal != "VIEW"){
    
		$('#au').prop('selectedIndex',1);
		$('#item_status').prop('selectedIndex',1);
	}
 
    $('#nomen').change(function(){
    	var nomen = $("#nomen").val();
    	$("#brief_desc").val(nomen);	
    });
    
    $('#prf_code').change(function(){
    	var prf = this.value;
    	getItem('','');
    });
    
    if(nParaVal == "APP"){
    	
		 $("#idhid").val(idVal);
		 
		 $("#c_data").show();
		 $("#b_data").hide();
		 $("#b_data2").show();
		 $("#b_data3").show();
		 $("#b_data4").show();
		 $("#new").hide();
		 $("#modifyid").hide();
		 $("#BtnSave").show();
		 
		 if(cosSecVal != ""){
		
			            $.post("getNewCensusNo?"+key+"="+value, {
			            	cos:cosSecVal
			        }, function(j) {
			              

			        }).done(function(j) {
			        	var enc = j[1].substring(0,16);
						var a = dec(enc,j[0]);
						$("#census_no").val(a);
			                
			        }).fail(function(xhr, textStatus, errorThrown) {
			        });
				 
			 
			 
			 getPrf('${a_5[0].prf_code}');
			 $("#prf_group").val('${a_5[0].prf_group}');
			 $("select#prf_code").val('${a_5[0].prf_code}');
			 getItem('${a_5[0].prf_code}','');
			 $("#auth_lett_no").val('${a_5[0].auth_lett_no}');
			 $("#auth_date").val('${a_5[0].auth_date}');
			 $("input#cat_part_no").val('${a_5[0].cat_part_no}');
			 $("input#nomen").val('${a_5[0].nomen}');
			 $("textarea#brief_desc").val('${a_5[0].brief_desc}');
			 $("select#au").val('${a_5[0].au}');
			 $("select#item_status").val('${a_5[0].item_status}');
			 $("select#item_category").val('${a_5[0].item_category}');
			 $("textarea#spl_remarks").val('${a_5[0].spl_remarks}');
			 $("#census_no").val('${a_4[0]}');
			 $("#census_no").attr('disabled',true);
			 $("#cos_sec").val(cosSecVal);
			 $("#cos_sec").attr('disabled',true);
			 $("#nrflowcontrol").val("NEW");
		 }else{
			 alert("This Entry is not allowed");
			 $("#BtnSave").hide();
		 }
		 
	 }
	 else if(nParaVal == "VIEW"){
		 $("#modifyid").hide();
		 $("#new").hide();
		 Modifycheck(censusVal);
	 }
	 else if(nParaVal == "MODE"){
		$("#hidText").val("");
		
		$("#c_data").show();
		$("#b_data").hide();
		$("#b_data2").show();
		$("#b_data3").show();
		$("#b_data4").show();
		$("#new").hide();
		$("#modifyid").hide();
		$("#BtnSave").show();
		
		var data='${a_1[0]}'.split(":");
		$("#cos_sec").attr('disabled',true);
		$("#cos_sec").val(data[0]);
		$("#census_no").val('${census_nohid1}');
		$("#census_no").attr('disabled',true);
		$("#nomen").val(data[20]);
		$("#auth_lett_no").val(data[1]);
		$("#auth_date").val(data[2]);
		getPrf(data[3]);
		$("#prf_code").val(data[3]);
		getItem(data[3],data[4]);
		$("#cat_part_no").val(data[5]);
		$("#au").val(data[6]);
		$("#brief_desc").val(data[7]);
		$("#item_status").val(data[8]);
		$("#item_category").val(data[9]);
		$("#class_category").val(data[10]);
		$("#origin_country").val(data[11]);
		$("#dte_category").val(data[17]);
		$("#dte_eqpt_category").val(data[18]);
		$("#eqpt_priority").val(data[19]);
		$("#induc_year").val(data[15]);
		$("#digest_category").val(data[22]);
		$("#cost").val(data[21]);
		$("#manuf_agency").val(data[12]);
		$("#ahsp_agency").val(data[13]);
		$("#nato_stk_no").val(data[14]);
		$("#def_cat_no_dcan").val(data[23]);
		$("#spl_remarks").val(data[16]);
	 }
    
     try{
    	 if(window.location.href.includes("msg=")){
    		 var url = window.location.href.split("?msg")[0];
		     window.location = url;
	     } 
	 }catch (e) {
		 // TODO: handle exception
	 }		 
}); 
</script>
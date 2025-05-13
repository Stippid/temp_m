<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<form:form action="locnrsEditAction" commandName="locnrsEditCMD" method="post" class="form-horizontal"> 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"> <h5>UPDATE LOC AND NRS</h5> </div>
	          			<div class="card-body card-block">
							<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Loc</label>
                					</div>
                					<div class="col-12 col-md-8">
                						<input type="hidden" id="id" name="id"  class="form-control" value="${locnrsEditCMD.id}">
                  					     <input type="text" id="code_value" name="code_value" maxlength="400" value="${locnrsEditCMD.code_value}" class="form-control" style="text-transform:uppercase" >
                					</div>
              					</div>
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Trn Type</label>
                					</div>
                					<div class="col-12 col-md-8" >
                  						<select name="mod_desc" id="mod_desc" class="form-control-sm form-control" onchange='CheckColors();' readonly="readonly">
                  							<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getTerrainCode}" varStatus="num" >
                  								<option value="${item}">${item}</option>
                  							</c:forEach> 
							            </select>
							         </div>
							      </div>
						         <div class="row form-group">
						             <div class="col col-md-8" >
								          <input type="text" name="color" id="color" class="form-control" style="display:none; margin-left: 147px;"/>
   										  <input type="button" id="addColor" onclick="addValue()" value="Add" style="display:none;margin-left: 256px;">
               						</div>
               					</div>
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">Is NRS & Loc Same</label>
                					</div>
                					
                					<div class="col col-md-6">
						                 <div class="form-check-inline form-check">
						                 	   <label for="inline-radio1" class="form-check-label ">
						                 	   		<input type="hidden" name="checkvalue" id="checkvalue">
						                            <input type="radio" id="inline-radio1" name="inline-radios" value="Yes" class="form-check-input" onclick=" xyz();" disabled>Yes
						                       </label>&nbsp;&nbsp;&nbsp;
						                       <label for="inline-radio2" class="form-check-label ">
						                              <input type="radio" id="inline-radio2" name="inline-radios" value="No" class="form-check-input" onclick=" xyz();" disabled>No
						                       </label>
						                 </div>
						            </div>
              					</div> 
              					<div class="row form-group"  id="nrs_code1">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">NRS Code</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="nrs_codetxt" maxlength="5" name="nrs_codetxt"  value="${locnrsEditCMD.nrs_code}" class="form-control"  style="text-transform:uppercase" readonly="readonly" >
                					</div>
              					</div>
              					<div class="row form-group"  id="nrs_code2" style="display:none;">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>NRS Code</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<select name="nrs_codesel" id="nrs_codesel" class="form-control-sm form-control"  >
                  							<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getprantList2}" varStatus="num" >
                  								<option value="${item.code}" name="${item.code_value}" >${item.code} - ${item.code_value}</option>
                  							</c:forEach>
                  						</select>
                					</div>
              					</div> 
						   </div>
						   <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Loc Code</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="code" name="code"  value="${locnrsEditCMD.code}" class="form-control" maxlength="5" style="text-transform:uppercase"  onkeypress="return validateNumber(event, this)" readonly="readonly">
                					</div>
              					</div>
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Type of Loc</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<select name="type_loc" id="type_loc" value="${locnrsEditCMD.type_loc}" class="form-control-sm form-control" readonly="readonly" >
                  							<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getprantList1}" varStatus="num" >
                  								<option value="${item.codevalue}">${item.label}</option>
                  							</c:forEach>
                  						</select>
                					</div>
              					</div>
              					<div class="row form-group">
						             <div class="col col-md-8" >
								          <input type="text" name="color1" id="color1" class="form-control" style="display:none; margin-left: 147px;"/>
   										  <input type="button" id="addColor1" onclick="addValue1()" value="Add" style="display:none;margin-left: 256px;">
               						</div>
               					</div>
              					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">NRS</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="nrs" name="nrs" maxlength="100" class="form-control"  style="text-transform:uppercase" readonly="readonly">
                					</div>
              					</div>
	   						</div> 
						</div>
					</div>
					<input type="hidden" name="status_record1" id="status_record1">
					<div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return checkLocValidation();">
	              	</div> 
	        	</div>
			</div>
</form:form>
<script>
	$(document).ready(function() {
	
		$("select#mod_desc").val('${locnrsEditCMD.mod_desc}');
		$("select#type_loc").val('${locnrsEditCMD.type_loc}');
		$("select#nrs_codesel").val('${locnrsEditCMD.nrs_code}');
		$("#status_record1").val('${status_record}');
		

		var loc1 = '${locnrsEditCMD.code}';
       	var nrs1 = '${locnrsEditCMD.nrs_code}';
       	if(loc1 == nrs1){
    	   	$("input[name=inline-radios][value='Yes']").prop('checked', true);   
    	   	var str = $("#code_value").val();
			var str1 = $("#code").val();
		   	$("#nrs").val(str);
		   	$("#nrs_codetxt").val(str1);
    	   	$("div#nrs_code1").show();	
 	    	$("div#nrs_code2").hide();
 	    	$("#checkvalue").val("Yes");
 	  	}else{
 	  		$("#checkvalue").val("No");
    		$("div#nrs_code2").show();
	    	$("div#nrs_code1").hide();	
    	   	$("input[name=inline-radios][value='No']").prop('checked', true);
    	   	$("#nrs").val($("select#nrs_codesel").find('option:selected').attr("name"));
    	}
		
       	$('input[name=inline-radios]').change(function(){
       		var value = $( 'input[name=inline-radios]:checked' ).val();
  			if(value == "Yes"){
  				$("#checkvalue").val("Yes");
  				var str = $("#code_value").val();
  				var str1 = $("#code").val();
  		   	    $("#nrs").val(str);
  		   		$("#nrs_codetxt").val(str1);
  		   		$("input#code_value").keydown(function(){$("input#nrs").val(this.value);});
  		   		$("input#code").keydown(function(){
       		    	$("input#nrs_codetxt").val(this.value);
       		  	});
  		   	}else{
  		   		$("#checkvalue").val("No");
  				$("#nrs").val("");
  				$("#nrs_codesel").val("0");
  			} 
  			
  		}); 
      var status = $("#status_record1").val();debugger;
      if(status === "0"){
    	  document.getElementById('mod_desc').removeAttribute('readonly');
    	  document.getElementById('type_loc').removeAttribute('readonly');
    	  document.getElementById('nrs').removeAttribute('readonly');
    	  document.getElementById('nrs_codetxt').removeAttribute('readonly');
    	  document.getElementById('inline-radio1').disabled = false;
    	  document.getElementById('inline-radio2').disabled = false;
    	}

       	
       	
	});
    
	$("select#nrs_codesel").change(function() {
		$("#nrs").val($(this).find('option:selected').attr("name"));
	}); 
       
   </script>
<script>
	function validateNumber(evt) {
    	var charCode = (evt.which) ? evt.which : evt.keyCode;
    	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
    		return false;
    	} else {
        	if (charCode == 46) {
        	    return false;
        	}
    	}
    	return true;
	}
    function myFunction() {
  		var str = document.getElementById("code_value").value;
  	  	var res = str.split("", 1);
  	  	document.getElementById("code").value = res;
  	}
   	function CheckColors(){
  	    var element = document.getElementById("mod_desc");
  	    var element2 = document.getElementById("color");
  	    var element3 = document.getElementById("addColor");
  	     if(element.value =='Others'){
  	       element2.style.display='block';
  	       element3.style.display='block';
  	     }
  	     else {
  	       element2.style.display='none';
  	       element3.style.display='none';
  	      }
  	  
   	}
	function addValue(){
  		var textToAdd  = document.getElementById("color").value;
  	    var x					 = document.getElementById("mod_desc");
  	    var option 		 = document.createElement("option");
  	    option.text    = textToAdd;
  	    x.add(option);
	}
  	function xyz()
  	{
  	   	var r = $('input:radio[name=inline-radios]:checked').val();
  	    if (r == 'Yes'){	
  	    	$("div#nrs_code1").show();	
  	    	$("div#nrs_code2").hide();
  	    }
  	    else{
  	    	$("div#nrs_code2").show();
  	    	$("div#nrs_code1").hide();	
  	    	
  	    }
	}
  	function blockSpecialChar(e){
  		var k;
  	    document.all ? k = e.keyCode : k = e.which;
  	    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 32  || k == 8 || k == 95);
	}  	
  	
  	function checkLocValidation() {
		if($("input#code_value").val()==""){
			alert("Please Enter LOC")
			$("input#code_value").focus();
			return false;
		}
		if($("input#code").val()==""){
			alert("Please Enter LOC Code")
			$("input#code").focus();
			return false;
		}
		if($("input#code").val().length != "5"){
			alert("Please Enter 5 Digit LOC Code")
			$("input#code").focus();
			return false;
		}
		if($("select#mod_desc").val()=="0"){
			alert("Please Select Trn Type")
			$("input#mod_desc").focus();
			return false;
		}
		if($("select#type_loc").val()=="0"){
			alert("Please Select TYPE LOC")
			$("input#type_loc").focus();
			return false;
		}
		return true;
	}
</script>
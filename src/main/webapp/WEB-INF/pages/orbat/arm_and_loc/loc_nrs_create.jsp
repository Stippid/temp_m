<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<form:form action="createLocAction" commandName="createLocCMD" method="post"  class="form-horizontal">
	<div class="animated fadeIn">
		<div class="container" align="center">
	    	<div class="row" >
	    		<div class="card">
	          		<div class="card-header"> <h5>CREATE LOC AND NRS</h5> </div>
          			<div class="card-body">
            			<div class="col-md-12">	
							<div class="col-md-6">
								<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Loc</label>
                					</div>
                					<div class="col-12 col-md-8">
                  					     <input type="text" id="code_value" name="code_value" maxlength="400" class="form-control" style="text-transform:uppercase" onkeyup='myFunction();' onkeypress="return blockSpecialChar(event);" autocomplete="off" required="required">
                					</div>
              					</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Loc Code</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="code" name="code"  class="form-control" maxlength="5" style="text-transform:uppercase" onkeyup='checkLOCCode();' onkeypress="return validateNumber(event, this);" required="required">
                					</div>
              					</div>
							</div>
						</div>
						<div class="col-md-12">	
							<div class="col-md-6">
								<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Trn Type</label>
                					</div>
                					<div class="col-12 col-md-8" >
                  						<select name="mod_desc" id="mod_desc" class="form-control-sm form-control" onchange='CheckColors();'>
                  							<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getTerrainCode}" varStatus="num" >
                  								<option value="${item}">${item}</option>
                  							</c:forEach> 
                  						</select>
							         </div>
							     </div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Type of Loc</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<select name="type_loc" id="type_loc" class="form-control-sm form-control" >
                  							<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getprantList1}" varStatus="num" >
                  								<option value="${item.codevalue}">${item.label}</option>
                  							</c:forEach> 
                  						</select>
                					</div>
              					</div>
							</div>
						</div>
						<div class="col-md-12">	
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-5">
										<label for="text-input" class=" form-control-label">Is NRS & Loc Same</label>
									</div>
									<div class="col col-md-6">
										<div class="row form-group">
											<div class="col col-md-2">
												<label class=" form-control-label">Yes</label>
											</div>
											<div class="col-12 col-md-3">
												<input type="hidden" name="checkvalue" id="checkvalue">
												<input type="radio" id="inline-radio1" name="inline-radios" value="Yes" class=" form-control form-check-input" onclick=" xyz();">
											</div>
											<div class="col col-md-2">
												<label class=" form-control-label">No</label>
											</div>
											<div class="col-12 col-md-3">
												<input type="radio" id="inline-radio2" name="inline-radios" value="No" class="form-control form-check-input" onclick=" xyz();">
											</div>
										</div>
									</div>
								</div>
							</div>							
							<div class="col-md-6">
								<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">NRS</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="nrs" name="nrs" maxlength="100" class="form-control"  style="text-transform:uppercase" >
                					</div>
              					</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group"  id="nrs_code2" style="display:none;">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>NRS Code</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<select name="nrs_codesel" id="nrs_codesel" class="form-control-sm form-control">
                  							<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getprantList2}" varStatus="num" >
                  								<option value="${item.code}" name="${item.code_value}" >${item.code} - ${item.code_value}</option>
                  							</c:forEach>
                  						</select>
                					</div>
              					</div>
							</div>
						</div>	
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group"  id="nrs_code1">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">NRS Code</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="nrs_codetxt" name="nrs_codetxt" maxlength="5" class="form-control"  style="text-transform:uppercase" readonly="readonly">
                					</div>
              					</div>
              				</div>
						</div>	
						<div class="col-md-5">
					         <div class="row form-group">
					             <div class="col col-md-8" >
							          <input type="text" name="color" id="color" class="form-control" style="display:none; margin-left: 147px;"/>
									  <input type="button" id="addColor" onclick="addValue()" value="Add" style="display:none;margin-left: 256px;">
          						</div>
           					</div>
					   	</div>
						<div class="col-md-5">
         					<div class="row form-group">
					             <div class="col col-md-8" >
							          <input type="text" name="color1" id="color1" class="form-control" style="display:none; margin-left: 147px;"/>
									  <input type="button" id="addColor1" onclick="addValue1()" value="Add" style="display:none;margin-left: 256px;">
           						</div>
           					</div>
  					    </div>
					</div>
					<div class="card-footer" align="center"  class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return checkLocValidation();">
	              	</div> 
           		</div>
				
        	</div>
		</div>
	</div>
</form:form>
<script>
	$(document).ready(function() {
    	$('input[name=inline-radios]').change(function(){
   	   		var value = $( 'input[name=inline-radios]:checked' ).val();
   			if(value == "Yes"){
   				var str = $("#code_value").val();
   				var str1 = $("#code").val();
   		   	    $("#nrs").val(str);
   		   		$("#nrs_codetxt").val(str1);
   		   		$("input#code_value").keydown(function(){
   	        	    $("input#nrs").val(this.value);
   	        	});
   		   		$("input#code").keydown(function(){
        			$("input#nrs_codetxt").val(this.value);
        		});
   			}else{
   				$("#nrs").val("");
   				$("#nrs_codesel").val("0");
   			}  
   		});
	});
       
    $("select#nrs_codesel").change(function() {
		var code = $(this).find('option:selected').attr("name");    
		$("#nrs").val(code);
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
   	  	if(res == " "){
	  		$("#code_value").val("");
	  		$("#code").val("");
	  	}else{
   	  		document.getElementById("code").value = res;
	  	}
   	}
    
    function checkLOCCode(){
    	var code_value = document.getElementById("code_value").value;
    	var code = document.getElementById("code").value;
    	var r = $('input:radio[name=inline-radios]:checked').val();
		
    	if (r == 'Yes'){
			$("#nrs_codetxt").val(code);
			$("#nrs").val(code_value);
	  	}
    	
    	if(code == ""){
    		$("#code_value").val("");
    		$("#nrs").val("");
    	}
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
      	var x		= document.getElementById("mod_desc");
      	var option  = document.createElement("option");
      	option.text   = textToAdd;
		x.add(option);
	      
    }
    	    
    	    
	function xyz()
	{
		var r = $('input:radio[name=inline-radios]:checked').val();
		if (r == 'Yes'){
			$("#checkvalue").val("Yes");
			$("div#nrs_code1").show();	
			$("div#nrs_code2").hide();
		}
		else{
			$("#checkvalue").val("No");
			$("div#nrs_code2").show();
			$("div#nrs_code1").hide();	
		}
	}
	function blockSpecialChar(e){
		var k;
     	document.all ? k = e.keyCode : k = e.which;
     	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 32 || k == 8 || k == 95);
    }  	
	
	 /*  function blockSpecialChar(e){
	        var k;
	        document.all ? k = e.keyCode : k = e.which;
	        return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 40 || k== 41 || k== 38 || k == 45 || k == 44);
	     } */
	
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
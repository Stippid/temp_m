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

<form:form name="" id="serachByidetityForm" action="searchByIdentityUrl" method="post" class="form-horizontal" commandName=""> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Search Officer/JCO-OR/Civilian</h5> </div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Service Category</label>
						                </div>
						                <div class="col-md-8">
						                  <select name="service_category" id="service_category" onchange="fn_service_category_change();" class="form-control-sm form-control">
											<option value="0">--Select--</option>
												<c:forEach var="item" items="${getServiceCategoryList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> ID Card No</label>
						                </div>
						                <div class="col-md-8">					                 
						                 	<input type="text" id="id_card_no" name="id_card_no" class="form-control autocomplete" autocomplete="off" maxlength="12">
						                </div>
						            </div>
	              				</div>	              				
	              			</div>	
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"> </strong>Personal No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" >
											</div>
										</div>
									</div>	              					              				              				
	              			</div>	              				              			
            			</div>									
				<div class="form-control card-footer" align="center">
					<a	href="searchByIdentityUrl" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
					<i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" value="Search"	onclick="fn_searchData();">
					<input type="button" id="pbutton" class="btn btn-info btn-sm" value="PDF" onclick="getPDF()"> <br>
				</div>		
	        </div>	        	
		</div>
	</div>
</form:form>

<div class="container" id="divPrint" style="display: none;">
	<div class="watermarked" data-watermark="" id="divwatermark">
		<div class="card">
	    	<div class="card-header" style="text-align: center"><u><h6>IDENTITY CARD DETAILS</h6></u> </div>
	          	<div class="card-body card-block">
	          		<div class="row">	          			
	          			<div class="col-md-3">
							<img id="identity_image_preview" alt="Image"  src="js/img/No_Image.jpg" width="200" height="200" />
	          			</div>
	          				<div class="col-md-9">
								<div class="col-md-12">	              						              													
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;"> </strong>No</label>
									</div>
									<div class="col-md-8">
										<label type="text" id="id_no" name="id_no"></label>
									</div>																			
	              				</div>	
	              				<div class="col-md-12">	              						              														
									 <div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;"> </strong>Rank</label>
									 </div>
									 <div class="col-md-8">
										<label type="text" id="rank" name="rank"></label>
									</div>	              				              				
	              				</div>	     
	              				<div class="col-md-12">	              					
	              					<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;"> </strong>Name</label>
									</div>
									<div class="col-md-8">
										<label type="text" id="name" name="name"></label>
									</div>	              				              				
	              				</div>	     
           						<div class="col-md-12">	              					
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;"> </strong>Date of Birth</label>
									</div>
									<div class="col-md-8">
										<label id="date_of_birth" name="date_of_birth"	></label>
									</div>	              				              				
           						</div>	          
	          			</div>
	          		</div>
			</div>
		</div>
	</div>
</div>

<c:url value="searchByIdentityPrint" var="reportUrl" />
	<form:form action="${reportUrl}" method="post" id="printForm" name="printForm">
		<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
		<input type="hidden" name="service_categoryPrint" id="service_categoryPrint" value="0" />
		<input type="hidden" name="personnel_noPrint" id="personnel_noPrint" value="0" />
		<input type="hidden" name="id_card_noPrint" id="id_card_noPrint" value="0" />	
	</form:form>

<script>
$(document).ready(function() {
		
	if('${size}' != 0 && '${size}' != ''){
		$("#divPrint").show();
		$('#id_no').text('${list[0].id_card_no}');
		$('#rank').text('${list[0].description}');
		$('#name').text('${list[0].name}');
		$('#date_of_birth').text(convertDateFormate('${list[0].date_of_birth}'));
		var i_id='${list[0].id}'
		$('#identity_image_preview').attr("src", "censusIdentityConvertpath?i_id="+i_id+" ");
	} 
	else{
		$("#divPrint").hide();
	}
	var sr_cat = '${ser_cat}';
	var id_no = '${id_no}';
	var pr_no = '${pr_no}';
	
	if(sr_cat != ""){		
		$("#service_category").val(sr_cat);
	}
	if(pr_no != ""){		
		$("#personnel_no").val(pr_no);
	}
	if(id_no != ""){		
		$("#id_card_no").val(id_no);
	}   
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}
});

$("input#id_card_no").keypress(function(){	
		
	if($("select#service_category").val() == "0"){
		alert("Please Select Service Category");
		$("select#service_category").focus();
		return false;
	}	
	
	var getCard_no_Officer_List;
	var getCard_noToPersonal_no;
	if ($("select#service_category").val() == "1") {
			getCard_no_Officer_List = "getCard_no_Officer_List";
			getCard_noToPersonal_no = "getCardNotoPersonalNo_Officer";
		}	
	if ($("select#service_category").val() == "2") {
		getCard_no_Officer_List = "getCard_no_Jcos_List";
		getCard_noToPersonal_no = "getCardNotoPersonalNo_JCOs";
	}
	
	var id_card_no = this.value;
		 var susNoAuto=$("#id_card_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: getCard_no_Officer_List+"?"+key+"="+value,
		        data: {card_no:id_card_no},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}		        	   	          
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Card No");
		        	  document.getElementById("card_no").value="";
		        	  $("#personnel_no").val("");
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  var id_card_no = ui.item.value;		        	
	              	$.post(getCard_noToPersonal_no+"?"+key+"="+value,{id_card_no: id_card_no}, function(j) {
			      		var length = j.length-1;
	                    var enc = j[length].substring(0,16);
	                    $("#personnel_no").val(dec(enc,j[0]));		                 
	            });
		      } 	     
		}); 			
	});


$("input#personnel_no").keypress(function() {
	
	if($("select#service_category").val() == "0"){
		alert("Please Select Service Category");
		$("select#service_category").focus();
		return false;
	}
	var getpersonnel_noList;
	var getPersonal_noToCard_No;
	if ($("select#service_category").val() == "1") {
			getpersonnel_noList = "getpersonnel_noListApproved";
			getPersonal_noToCard_No = "getPersonalNotoCardNo_Officer";
		}	
	if ($("select#service_category").val() == "2") {
		getpersonnel_noList = "getpersonnel_noListApproved_JCOS";
		getPersonal_noToCard_No = "getPersonalNotoCardNo_JCOs";
	}

	var personel_no = this.value;
	var susNoAuto = $("#personnel_no");
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url: getpersonnel_noList+"?"+key+"="+value,
				data : {personel_no : personel_no},				
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					response(susval);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter Approved Personal No");
				document.getElementById("personnel_no").value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		 select: function( event, ui ) {
	    	  var personnel_no = ui.item.value;		        	
             	$.post(getPersonal_noToCard_No+"?"+key+"="+value,{personnel_no: personnel_no}, function(j) {
		      		var length = j.length-1;
                   var enc = j[length].substring(0,16);
                   $("#id_card_no").val(dec(enc,j[0]));	 
           });
	    } 
	});
});
</script>
<script type="text/javascript">

function fn_personel_no_keyPress(){
	
	var personel_no = $("#personnel_no").val();
		 var susNoAuto=$("#personnel_no");
		 if($("#service_category").val()=='1'){
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved?"+key+"="+value,
		        data: {personel_no:personel_no},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
			        	   	          
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 
	}
		 else if($("#service_category").val()=='2'){								
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getArmy_noListApproved?"+key+"="+value,
			        data: {army_no:army_no},
			          success: function( data ) {
			        	 var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	   	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Personal No");
			        	  document.getElementById("personnel_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	
			      } 	     
			}); 							
		 }
	 else{
		 $("#personnel_no").autocomplete({	
		  source:[],
            delay: 200,
            minLength: 3,                
            select: function (event, ui) {                    
               ui.item.value = "";  // it will clear field 
                return false;
            }
	      });	
	 }
}


function fn_service_category_change(){
	 document.getElementById("personnel_no").value="";
	 document.getElementById("id_card_no").value="";
}

function fn_searchData(){	
	var service_category=$('#service_category').val();
	var personnel_no=$('#personnel_no').val();
	var id_card_no=$('#id_card_no').val();
	if(service_category=='0'){
		alert('Please Select Service Category ');
		$('#service_category').focus();
		return false;
	}
	if(personnel_no=='' && id_card_no==''){
		alert('Please Enter Personal No Or Id Card No');
		$('#id_card_no').focus();
		return false;
	}	
	$('#serachByidetityForm').submit();	
}


function getPDF()
{   
	var service_category=$('#service_category').val();
	var personnel_no=$('#personnel_no').val();
	var id_card_no=$('#id_card_no').val();	
	$('#service_categoryPrint').val(service_category);
	$('#personnel_noPrint').val(personnel_no);
	$('#id_card_noPrint').val(id_card_no);	
	document.getElementById('typeReport1').value='pdfL';
	document.getElementById('printForm').submit();
}

</script>


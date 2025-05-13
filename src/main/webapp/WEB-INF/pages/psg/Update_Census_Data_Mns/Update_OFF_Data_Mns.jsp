<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!-- <script src="js/common/commonmethod.js" type="text/javascript"></script> -->

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

 
<style>
.sticky {
    position: fixed;
    top: 0;
    z-index: 1000;
    padding-right: 35px;
}
.sticky + .subcontent {
  padding-top: 330px;
}
</style>
<style>
.selectBox {
	position: relative;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}
.go-top {
  right:1em;
  bottom:2em;
  color:#FAFAFA;
  text-decoration:none;
  background:#F44336;
  padding:5px;
  border-radius:5px;
  border:1px solid #e0e0e0;
  position:fixed;
  font-size: 180%;
  

}

label {
    word-break: break-word;
}


textarea {
    text-transform: none!important;
}


</style>
<script>

$(window).scroll(function(){
    if($(this).scrollTop() > 100){
      $('.go-top').fadeIn(200);      
    } else {
      $('.go-top').fadeOut(300);
    }
  });
  $('.go-top').click(function() {
    event.preventDefault();
    
    $('html , body').animate({scrollTop: 0}, 1000);
  });
// window.onscroll = function() {myFunction()};

// function myFunction() {
// 	navbar = document.getElementById("Personnel_no_form");
//   if (window.pageYOffset >= 110) {
//     navbar.classList.add("sticky");
//   } else {
//     navbar.classList.remove("sticky");
//   }
// }


function Cancel(){
  	
	$("#personnel_no1").val($("#personnel_noV").val()) ;
	$("#status1").val($("#statusV").val()) ;
	$("#rank1").val($("#rankV").val()) ;
	$("#comm_id").val($("#comm_idV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	$("#cr_by1").val($("#cr_byV").val()) ;
	$("#cr_date1").val($("#cr_dateV").val()) ;
	document.getElementById('searchForm').submit();
}

 


</script>
<c:url value="GetSearch_UpdateOfficerMnsData" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="cr_date1" id="cr_date1"  />
	    <input type="hidden" name="cr_by1" id="cr_by1"  />
	
	</form:form> 
	

<div class="container-fluid" align="center">
<form  id="Personnel_no_form" >
 		<div class="animated fadeIn">
	    	<div class="" align="center">
		<div class="card">	
			<div class="panel-group" id="accordion">
				<div class="panel panel-default" id="insp_div1">
					
					<div class="panel-heading">
						<h4 class="panel-title main_title" id="insp_div5">
						<b>UPDATE DATA OF MNS OFFR 
						<!-- <input type="text" pattern="^[a-zA-Z0-9]+$" />
						 <input type="text" name="count"  onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"> --> </b>
						</h4>
					</div>
							<!-- <div class="col-md-12" style="padding-top: 20px;">	              					
	              				<div class="col-md-6">
	              						<div class="row form-group">
						               <div class="col-md-6">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
						                </div>
						               <div class="col-md-6">
						                    <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  > 						                   
						               		<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  > 						                   
						                    <input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
						                </div>
						                 <input type="button" class="btn btn-success btn-sm" id="btn1" value="Process" onclick="return personal_number();">
						            </div> 
	              				</div> 
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Name</label>
						                </div>
						                <div class="col-md-8">
						                 <label class=" form-control-label" id="cadet_name"></label>		
						                 </div>
						                 <div class="col-md-4">
						                    <label class=" form-control-label"> Rank</label>
						                </div>
						                <div class="col-md-8">
						                 <label class=" form-control-label" id="p_rank"></label>	
						                 </div>
						                <div class="col-md-4">
						                    <label class=" form-control-label"> Date of Birth</label>
						                </div>
						                <div class="col-md-8">
						                 <label class=" form-control-label" id="dob"></label>		
						                 </div>
						            </div>
	              				</div>
	              			</div>	 -->
	              			
	              					<div class="col-md-12" style="padding-top: 5px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label">
												<strong style="color: red;">* </strong>Personal No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  > 						                   
							               		<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  > 						                   
							                    <input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
							                     <input type="hidden" id="status" name="status" value="0" class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>

									<div class="col-md-6" id="process_btn">
									     <input type="button" class="btn btn-success btn-sm" id="btn1" value="Process" onclick="return personal_number();">
									</div>
								</div> 
	              				<!-- janki -->
										<input type="hidden" id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="rankV" name="rankV" class="form-control autocomplete"  > 
						                 <input type=hidden id="comm_idV" name="comm_idV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
						                   <input type="hidden" id="cr_byV" name="cr_byV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="cr_dateV" name="cr_dateV" class="form-control autocomplete"  > 
											
	              		<div class="card-footer" align="right" class="form-control" id="back_bt" style="display: none;">
							<a href="Search_Report_3008Url" class="btn btn-info btn-sm" >Back</a> 
			            </div> 	
			            
			            <div class="card-footer" align="right" class="form-control" id="back_bt_div" style="display: none;">
							<!-- <a href="Search_UpdateOfficerDataUrl" class="btn btn-primary btn-sm" >Back</a>  -->
							<input type="button"   class="btn btn-info btn-sm" onclick="Cancel();" value="Back">   
			            </div> 
					 			
					</div>
				</div>
		</div>
	</div>
	</div>
</form>

<!-- START CDA ACCOUNT NO -->
<div id = "div_update_data" style="display: none;" class="subcontent">
<div id="maindetailsdiv">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="app_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="app_final" onclick="divN(this)"
								><b>RECORD</b></a>
						</h4>
					</div>
					<div id="collapse1app" class="panel-collapse collapse">
			                <div class="card-body card-block"><br>
			               
							  
							 
							<div class="row">

								<div class="col-md-12">	              					
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Name</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="cadet_name"></label>
						                   <label class=" form-control-label" id="dob" style="display: none;"></label>
						                   <input type="hidden" class=" form-control-label" id="dob_date" >
						                   <input type="hidden" class=" form-control-label" id="comm_date" >
						                   <label class=" form-control-label" id="marital_status_p" style="display: none;"></label>
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Rank</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_rank"></label>
						                     <input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off" >		
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Appointment</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="p_app"></label>
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Date of Appointment</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_app_dt"></label>		
						                </div>
						            </div>
	              				</div> 
	              			</div>          
	              		  <div class="col-md-12">	              					
	              	
	              			   <div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Date of TOS</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_tos"></label>		
						                      <input type="hidden" class=" form-control-label" id="tos_date" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> SUS No</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="app_sus_no"></label>
						                </div>
						            </div>
	              				</div>
	              				  	<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Unit Name</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="app_unit_name"></label>		
						                </div>
						            </div>
	              				</div>	              					
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Command</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="p_cmd"></label>
						                </div>
						            </div>
	              				</div>	
	              			</div>     	

	              				  <div class="col-md-12">
	              						<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> ID Card No</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="p_id_no"></label>
						                </div>
						            </div>
	              				</div>  
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"> Religion</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="p_religion"></label>		
						                </div>
						            </div>
	              				</div> 
	              				  <div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label">  Arm/Service</label>
						                </div>
						                <div class="col-md-8">
						                     <label class=" form-control-label" id="app_parent_arm"></label>		
						                </div>
						            </div>
	              				</div>
	              			
	              				<div class="col-md-3">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Serving Status</label>
						                </div>
						                <div class="col-md-8">
						                  <label class=" form-control-label" id="p_serving_status"></label>
						                </div>
						            </div>
	              				</div>  
	              				             				
	              			</div>
	              					  <div class="col-md-12">
	              				<div class="col-md-3" style="display: none;" id = "reg_div">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Regiment</label>
						                </div>
						                <div class="col-md-8">
						                  <label id="p_regt" class=" form-control-label"></label>
						                   <select name="regt" id="inter_arm_regt_val" class="form-control"  style="display: none;" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getRegtList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 		
						                </div>
						            </div>
	              				</div>	
	              				</div>	
	              			
	              			 	</div> 
						</div>
					</div>
				</div>
			</div>
			</div>
</div>
<div id="moredetailsdiv">
<!-- Start Change of Rank  -->
	 <form id="form_ChangeOfRank">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_final" onclick="divN(this)"
								><b>CHANGE IN RANK</b></a>
						</h4>
					</div>
					<div id="collapse1a" class="panel-collapse collapse">
			                <div class="card-body card-block"><br>
			                <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('rank');" />
							<div class="row">
					 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Authority  </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="r_authority" name="authority" class="form-control autocomplete" 
						                   autocomplete="off" onkeyup="return specialcharecter(this)" maxlength="100"> 
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority </label>
						                </div>
						                <div class="col-md-8">
						                    <input type="hidden" id="ch_r_id" name="ch_r_id" value="0" class="form-control" />  
						                  <input type="text" name="r_date_of_authority" id="r_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              		 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Rank Type </label>
						                </div>
						                <div class="col-md-8">
										          <select name="rank_type" id="rank_type" class="form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getOFFTypeofRankList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select> 
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Rank </label>
						                </div>
						                <div class="col-md-8">
						                          <select name="rank" id="rank" class="form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select> 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Date of Rank </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" name="date_of_rank" id="date_of_rank" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
						  <div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_ChngofRank_save();"> 	
			            	</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	 <!-- END Change of Rank  -->
	 <!-- START CHANGE OF NAME DETAIL -->
	    <form id="form_change_of_name">
		<div class="card">
			<div class="panel-group" id="accordion5">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion5" href="#" id="b_final" onclick="divN(this)"
								><b>CHANGE IN NAME</b></a>
						</h4>
					</div>
					<div id="collapse1b" class="panel-collapse collapse">
						  <div class="card-body card-block">
			            <br>
			             <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Name');" /> 
					<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Authority </label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" id="na_authority" name="authority" class="form-control autocomplete" 
						               autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)"> 
						               <input type="hidden" id="name_id" name="name_id" value="0" class="form-control autocomplete" autocomplete="off">
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority </label>
						                </div>
						               <div class="col-md-8">
						                <input type="text" name="date_of_authority" id="na_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              						              				            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="name" name="name" class="form-control autocomplete" autocomplete="off" 
						                   maxlength="50" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"> 
				                	  </div>
						            </div>
	              				</div>	 
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Change in Name</label>
						                </div>
						               <div class="col-md-8">
						                <input type="text" name="change_in_name_date" id="change_in_name_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               </div>
						            </div>
	              				</div>
	              				
	              				             				
	              			</div>	
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Change_of_name_save_fn();">
			           
			            </div> 	
						</div>
					<!-- 	  <div class="card-footer" align="left" class="form-control">
								<p><u><b>Note:</b></u> Name should be as per Commission Offr Information. </p> 	
			            	</div>  -->
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END CHANGE OF NAME DETAIL -->
	<!-- START CHANGE OF APPOINTMENT DETAIL -->
	    <form id="form_change_of_appointment">
		<div class="card">
			<div class="panel-group" id="accordion41">
				<div class="panel panel-default" id="c_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="c_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion41" href="#" id="c_final" onclick="divN(this)"
								><b>CHANGE IN APPOINTMENT</b></a>
						</h4>
					</div>
					<div id="collapse1c" class="panel-collapse collapse">
					     <div class="card-body card-block">
			           
								   <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Appointment');" /> 
								
							<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label for="Authority"> <strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="appt_authority" name="appt_authority" class="form-control"  
						                   autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">	
								           <input type="hidden" id="appoint_id" name="appoint_id" value="0" class="form-control"  autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label for="Date Autho"><strong style="color: red;">* </strong> Date of Authority </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="appt_date_of_authority" id="appt_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Appointment </label>
						                </div>
						                <div class="col-md-8">
										   <select  id="appointment" name="appointment" class="form-control" onchange="appointment_att();" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeofAppointementList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              			    <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Appointment </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_appointment" id="date_of_appointment" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			 <div class="card-body" style="display: none;" id = "Note">
		          		<p align="left" style="color:#ff0730fc; font-weight: bold;" >Note : Please Enter X-List SUS No Or  X-List Unit Name Or  X-List Location.  </p>					            		           
	        		</div>
	              			<div class="col-md-12" style="display: none;" id = "att_dapu">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
											style="color: red;">* </strong> X-List SUS No</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="parent_sus_no" name="parent_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" >  
						                 
						                </div>
						            </div>
	              				</div>
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> X-List Unit Name</label>
						                </div>
						                <div class="col-md-8">
						              		  <input type="text" id="parent_unit" name="parent_unit" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12" style="display: none;" id = "att_dapu1">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> X-List Location</label>
						                </div>
						                <div class="col-md-8">
						              		  <input type="text" id="x_list_loc" name="x_list_loc" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
						<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" name="save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick=" validate_appointment_save();" />		              
			            </div>  
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END CHANGE OF APPOINTEMENT DETAIL -->
	<!-- START IDENTY CARD DETAIL -->
	<form id="form_identity_card">
		<div class="card">
			<div class="panel-group" id="accordion43">
				<div class="panel panel-default" id="d_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="d_div5">
							<a class="droparrow collapsed " data-toggle="collapse"
								data-parent="#accordio43" href="#" id="d_final" onclick="divN(this)"
								><b>CHANGE IN IDENTITY CARD</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
					<div class="card-body card-block">
					<br>
					   <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('idenity_card');" /> 
						<div class="row">
							<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>ID Card no</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="id_card_no" name="id_card_no" class="form-control autocomplete"
						                   autocomplete="off" maxlength="10" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" >
						                </div>
						            </div>
	              				</div>	              				
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Issuing Date</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="issue_dt" id="issue_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>	
	              			</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Issuing Authority </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" id="issue_authority" name="issue_authority" class="form-control autocomplete" autocomplete="off" maxlength="256" onkeyup="return specialcharecter(this)">
						                 <input type="hidden" id="issue_authority_sus" name="issue_authority_sus" class="form-control autocomplete" autocomplete="off" maxlength="10">
						                <input type="hidden" id="card_id" name="card_id" value="0" class="form-control"  autocomplete="off" >
						                </div>
						            </div>
	              				</div>	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Visible Identification Marks</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" id="id_marks" name="id_marks" class="form-control autocomplete"
						                 autocomplete="off" maxlength="50" onkeypress="return onlyAlphaNumeric(event);">
						                </div>
						            </div>
	              				</div>	   		
	              			</div>
	                 		<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Hair Colour</label>
						                </div>
						                <div class="col-md-8">
											  <select name="hair_colour" id="hair_colour" class="form-control" onchange="idcard_other();"  >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getHair_ColourList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 						                    
						                </div>
						            </div>
	              				</div>	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Eye Colour</label>
						                </div>
						                <div class="col-md-8">
						                  <select name="eye_colour" id="eye_colour" class="form-control" onchange="ideys_other();"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getEye_ColourList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>	              					              		
	              			</div>		
	              				<div class="col-md-12">
								<div class="col-md-6" id = "other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  Hair Colour Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="hair_other" name="hair_other" class="form-control autocomplete" autocomplete="off" 
					                	onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "other_eye" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Eye Colour Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="eye_other" name="eye_other" class="form-control autocomplete" autocomplete="off" 
					                	onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>	
	              			
	              				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Photograph </label>
										</div>
										<div class="col-md-8">
											<input type="file" id="identity_image" name="identity_image"  accept="image/*" onchange="document.getElementById('identity_image_preview').src = window.URL.createObjectURL(this.files[0])"> 
										<label class=" form-control-label" style="color: red;"><strong
												style="color: red;">Note:</strong>Dress No. 4 : Dress Gen Duty Summer.' PASSPORT SIZE PHOTO</label>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<img id="identity_image_preview" alt="Officer Image"  src="js/img/No_Image.jpg" width="100" height="100" />
										<input type="date" id="image_updated_date" name="image_updated_date" style="display: none">
										<input type="text" id="pre_identity_id" name="pre_identity_id" value="0" style="display: none">
									</div>
								</div>
							</div>
							
						</div>
						<div class="card-footer" align="center">
								<input type="button" name="save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick=" validate_identity_card_save();" /> 
						   
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- START START TA STATUS -->
	<form id="form_ta_status" style="display:none">
		<div class="card">
			<div class="panel-group" id="accordion404">
				<div class="panel panel-default" id="ta_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="ta_div5">
							<a class="droparrow collapsed " data-toggle="collapse"
								data-parent="#accordio404" href="#" id="ta_status_div" onclick="divN(this)"
								><b>TA STATUS</b></a>
						</h4>
					</div>
					<div id="collapse_ta" class="panel-collapse collapse">
					<div class="card-body card-block">
					<br>
					<input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('TA_STATUS');"/> 
					<div class="row">
				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Authority </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="ta_authority" name="ta_authority" class="form-control autocomplete" autocomplete="off"
						                  onkeyup="return specialcharecter(this)" maxlength="100">
						                   <input type="hidden" id="ta_id" name="ta_id" value="0" class="form-control autocomplete" autocomplete="off" >
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority </label>
						                </div>
						               <div class="col-md-8">
						                 <input type="text" name="ta_date_of_authority" id="ta_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               
						               </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              						              				            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Ta Status </label>
						                </div>
						                <div class="col-md-8">
					                	<select name="ta_status" id="ta_status" class="form-control" onchange="fn_other();">
											<option value="-1">--Select--</option>
											<c:forEach var="item" items="${gettastatusList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
					                	  </div>
						            </div>
	              				</div>	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Ta Status  </label>
						                </div>
						               <div class="col-md-8">
						                 <input type="text" name="ta_date" id="ta_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               
						               </div>
						            </div>
	              				</div>	   
	              				
	              					             				
	              			</div>
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_ta_save_fn();">		              
			            </div> 	
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END TA STATUS -->
	
	<!-- END IDENTY CARD DETAIL -->
	<!-- START RELIGION DETAIL -->
	<form id="form_religion">
		<div class="card">
			<div class="panel-group" id="accordion6">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion6" href="#" id="e_final" onclick="divN(this)"
								><b>CHANGE IN RELIGION</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
					  <div class="card-body card-block">
			            <br>
			            
			             <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('religion');" />
					<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Authority </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="rel_authority" name="authority" class="form-control autocomplete" autocomplete="off"
						                  onkeyup="return specialcharecter(this)" maxlength="100">
						                   <input type="hidden" id="rel_id" name="rel_id" value="0" class="form-control autocomplete" autocomplete="off" >
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority </label>
						                </div>
						               <div class="col-md-8">
						                 <input type="text" name="date_of_authority" id="rel_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               
						               </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              						              				            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Religion </label>
						                </div>
						                <div class="col-md-8">
					                	<select name="religion" id="religion" class="form-control" onchange="fn_other();">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getReligionList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
					                	  </div>
						            </div>
	              				</div>	 
	              				
	              					<div class="col-md-6" id = "other_div" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Religion Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="religion_other" name="religion_other" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>	             				
	              			</div>
	              			
                            <div class="col-md-12">	    
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Change in Religion</label>
						                </div>
						               <div class="col-md-8">
						                <input type="text" name="change_in_rel_date" id="change_in_rel_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						               </div>
						            </div>
	              				</div>
	              		</div>
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Religion_save_fn();">		              
			            </div> 	
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END RELIGION DETAIL -->
	<!-- START MARITAL EVENTS DETAIL -->
	  <form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion2">
				<div class="panel panel-default" id="f_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="f_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion2" href="#" id="f_final" onclick="divN(this)"
								><b>CHANGE IN MARITAL STATUS</b></a>
						</h4>
					</div>
					<div id="collapse1f" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('marital_status');">
						</div>
						
							<div class="row" >

                            <div class="col-md-12" id="marr_quali_radioDiv" style="margin-top: 10px; width: 100%; margin-bottom: 16px;">
									<label class=" form-control-label" style="margin-right: 70px;"><h4>Event</h4></label> &nbsp
									<input type="radio" style="margin-right: 5px; transform: scale(1.3);"
    
										id="marr_quali_radio1" name="marr_quali_radio" value="yes"
										onchange="marrqualificationFn();">&nbsp <label for="yes">Change Marital Status</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
									<input type="radio" id="marr_quali_radio2" name="marr_quali_radio" style="margin-right: 5px; transform: scale(1.3);"
										value="no" onchange="marrqualificationFn();" >&nbsp
									<label for="no">Add/Update Spouse Qualification</label><br>

								</div>
                                
						<div class="col-md-12" id="marital_eventDiv">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Marital Event</label>
									</div>
									<div class="col-md-8">
									<select name="marital_event" id="marital_event" class="form-control"  onchange="marital_eventchange();" >
									                        <option value="0">--Select--</option>
															<c:forEach var="item" items="${getMarital_eventList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
										</select>  
										<label class="form-control-label" id="maritaleventlbl" style="display: none;"></label>
			
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display: none;" >
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Marital Status</label>
									</div>
									<div class="col-md-8">
								<select name="marital_status" id="marital_status" class="form-control"   >
									                        <option value="0">--Select--</option>
															<c:forEach var="item" items="${getMarital_statusList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
															</select> 
									</div>
								</div>
							</div> 
						</div>
<div id="marriage_remarriageDiv" style="display:none">		
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
										<input type="hidden" id="marr_ch_id" name="marr_ch_id"  value="0" class="form-control autocomplete" autocomplete="off" >											
										 <input type="text" id="marriage_authority" name="marriage_authority" class="form-control autocomplete" autocomplete="off" 
										  onkeyup="return specialcharecter(this)" maxlength="100">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
											 <input type="text" name="marriage_date_of_authority" id="marriage_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
											</div>
										</div>
									</div>
								</div>	
								<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Spouse Name</label>
						</div>
						<div class="col-md-8">
						<input type="text" id="maiden_name" name="maiden_name" class="form-control autocomplete" autocomplete="off" 
						        maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Marriage</label>
						</div>
						<div class="col-md-8">
						<input type="text" name="marriage_date" id="marriage_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						</div>
					</div>
				</div>
			</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Spouse Place of Birth</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="marriage_place_of_birth" name="marriage_place_of_birth" 
								class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">				         
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Spouse Nationality</label>
							</div>
							<div class="col-md-8">
					<select name="marriage_nationality" id="marriage_nationality" 
					onchange="fn_otherShowHide(this,'Spouse_nationalityDiv','spouseNationality_other')"
					class="form-control"   >
							                        <option value="0">--Select--</option>
													<c:forEach var="item" items="${getNationalityList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select> 
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="Spouse_nationalityDiv" style="display:none">
					 
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Other Nationality</label>
							</div>
							<div class="col-md-8">
				       	<input type="text" id="spouseNationality_other" name="spouseNationality_other"
												class="form-control autocomplete" autocomplete="off"
											 onkeypress="return onlyAlphabets(event);" maxlength="50"
												oninput="this.value = this.value.toUpperCase()">
					
							</div>
						</div>
					</div>
				</div>
				
							<div class="col-md-12">
								<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse Date of Birth</label>
											</div>
											<div class="col-md-8">
											<input type="text" name="marriage_date_of_birth" id="marriage_date_of_birth" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Spouse Aadhaar Card No</label>
											</div>
											<div class="col-md-8">
									  <input type="text" id="marriage_adhar_no" name="marriage_adhar_no" class="form-control autocomplete" 
									      autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);" >
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong>Spouse PAN Card No</label>
											</div>
											<div class="col-md-8">
									  <input type="text" id="pan_card" name="pan_card" class="form-control autocomplete" 
									  autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()" >
											</div>
										</div>
									</div>
									<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>In Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="spouse_ser_radio1"
											name="spouse_ser_radio" value="Yes"
											onchange="spouse_ServiceFn();">&nbsp <label
											for="yes">Yes</label>&nbsp <input type="radio"
											id="spouse_ser_radio2" name="spouse_ser_radio" value="No"
											onchange="spouse_ServiceFn();" checked="checked">&nbsp
										<label for="no">No</label><br>


									</div>
								</div>
							</div>
									
								</div>
								<div id="spouse_inserviceDiv" class="col-md-12"
								style="display: none">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Services</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_service" id="spouse_service"
												class="form-cont rol-sm form-control"
												onchange="Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',this)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>


										</div>
									</div>
					 			</div>
								<div class="col-md-6" id="spouseSer_otherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="spouseSer_other" name="spouseSer_other"
                                                 class="form-control autocomplete" autocomplete="off"
                                                 onkeypress="return onlyAlphaNumeric(event);" maxlength="50"
                                                 oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
								
								<div class="col-md-6" id="spouse_personalDiv">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Personal No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="spouse_personal_no"
												name="spouse_personal_no" class="form-control autocomplete" maxlength="9"
												autocomplete="off"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>

							</div>
								<div class="col-md-12" id="spouse_rank_armservice" style="display:none">    					
	              			    <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Rank</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="spouse_rank" id="spouse_rank" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              				
	              				 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Parent Arm/Service </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="spouse_arm_service" id="spouse_arm_service" class="form-control-sm form-control"   onchange="chgarm(this,'regiment');" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getParentArmList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              			</div>
							
<!-- 							 bisag v2 03072023(Observation) -->

					<div class="col-md-12" id="spouse_proffDiv" >
							<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong>Spouse Profession</label>
							</div>
							<div class="col-md-8">
					<select name="spouse_profession" id="spouse_profession" class="form-control"   >
							                        <option value="0">--Select--</option>
													<c:forEach var="item" items="${getProfession}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select> 
							</div>
						</div>
					</div>
					</div>
							
							<div class="col-md-12" id="divorceDiv" style="display:none">				
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" id="divorce_widow_widower_dtlbl"><strong
														style="color: red;">* </strong>Date of Divorce</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="divorce_date" id="divorce_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12" id="seperateDiv" style="display:none">				
										<div class="col-md-6" id="separated_from_dtDiv" style="display:none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" ><strong
														style="color: red;">* </strong>Date of Separation</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="separated_from_dt" id="separated_from_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
										<div class="col-md-6" id="separated_to_dtDiv" style="display:none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label" ><strong
														style="color: red;"> </strong>Separation To Date</label>
												</div>
												<div class="col-md-8">
												<input type="text" name="separated_to_dt" id="separated_to_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
												</div>
											</div>
										</div>
									</div>
							
						</div>								
								
						      
								<div class="col-md-12" id="spouse_quali_radioDiv" style=" width: 100%;">
									<label class=" form-control-label" style="margin-right: 100px;"><h4> Qualification Details of Spouse</h4><strong
														style="color: red;">Note:-Only Highest qualification should be fill.</strong></label> 
								<input type="radio"
										id="spouse_quali_radio1" name="spouse_quali_radio" value="yes"
										onchange="spouse_quali_radioFn();">&nbsp <label for="yes">Yes</label>&nbsp
									<input type="radio" id="spouse_quali_radio2" name="spouse_quali_radio"
										value="no" onchange="spouse_quali_radioFn();" checked="checked">&nbsp
									<label for="no">No</label><br>
								</div>
								
							<div id="spouse_Qualifications" style="margin-top: 20px; display:none">
							
							
								
								<div class="col-md-12" id="spouse_quali_labelDiv" style=" width: 100%; display: none">
									<label class=" form-control-label" style="margin-right: 100px;"><h4> Qualification Details of Spouse</h4><strong
														style="color: red;">Note:-Only Highest qualification should be fill.</strong></label> 							
								</div>
								
											<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Qualification Type</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_quali_type" id="spouse_quali_type"
															class=" form-control"
															onchange="fn_qualification_typeChange(this,'spouse_quali','quali_degree_spouse','spouse_specialization')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getQualificationTypeList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>

														</select> <input type="hidden" id="spouse_qualification_ch_id"
															name="spouse_qualification_ch_id" value="0"
															class="form-control autocomplete" autocomplete="off">
													</div>
												</div>

											</div>
											<div class="col-md-6" id="academyQuali">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Examination Passed</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_quali" id="spouse_quali"
															class=" form-control"
															onchange="fn_ExaminationTypeChange(this,'quali_degree_spouse','spouse_specialization');fn_otherShowHide(this,'other_div_examSpouse','exam_otherSpouse')">
															<option value="0">--Select--</option>

														</select>
													</div>
												</div>

											</div>

										</div>

										<div class="col-md-12">
											<div class="col-md-6" id="other_div_examSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Examination Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="exam_otherSpouse" onkeypress="return onlyAlphabets(event);" maxlength="50"
															name="exam_otherSpouse" class="form-control autocomplete"
															autocomplete="off">
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Degree Acquired</label>
													</div>
													<div class="col-md-8">
														<select name="quali_degree_spouse" id="quali_degree_spouse"
															class=" form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiDegSpouse','quali_deg_otherSpouse')">

															<option value="0">--Select--</option>

														</select>
													</div>
												</div>

											</div>
											<div class="col-md-6" id="specializationDiv">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Specialization</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_specialization"
															id="spouse_specialization" class="form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiSpecSpouse','quali_spec_otherSpouse')">
															<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSpecializationList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
														</select>
													</div>
												</div>

											</div>


										</div>
										<div class="col-md-12">
											<div class="col-md-6" id="other_div_qualiDegSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Degree Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="quali_deg_otherSpouse"
															name="quali_deg_otherSpuse" onkeypress="return onlyAlphabets(event);" maxlength="50"
															class="form-control autocomplete" autocomplete="off">
													</div>
												</div>
											</div>

											<div class="col-md-6" id="other_div_qualiSpecSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Specialization Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="quali_spec_otherSpouse"
															name="quali_spec_otherSpouse" onkeypress="return onlyAlphabets(event);" maxlength="50"
															class="form-control autocomplete" autocomplete="off">

													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Year of Passing</label>
													</div>
													<div class="col-md-8">

														<input type="text" id="spouse_yrOfPass"
															name="spouse_yrOfPass" class="form-control autocomplete"
															autocomplete="off" onkeypress="return isNumber(event)"
															maxlength="4" />
													</div>
												</div>

											</div>

											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Div/Grade</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_div_class" id="spouse_div_class"
															class="form-control-sm form-control" onchange="fn_otherShowHide(this,'other_div_classSpouse','class_otherSpouse')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getDivclass}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>

											</div>


										</div>

										<div class="col-md-12" id="other_div_classSpouse"
											style="display: none;">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Div/Grade Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="class_otherSpouse" name="class_otherSpouse"
															class="form-control autocomplete" autocomplete="off" 
															onkeypress="return onlyAlphabets(event);" maxlength="50">
													</div>
												</div>
											</div>

										</div>

										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Subjects</label>
													</div>
													<div class="col-md-8">
														<div class="multiselect">

															<div class="selectBox" onclick="showCheckboxesSpouse()">
																<select name="spouse_sub_quali" id="spouse_sub_quali"
																	class="form-control-sm form-control">
																	<option>Select Subjects</option>
																</select>
																<div class="overSelect"></div>
															</div>
															<div id="spouse_checkboxes" class="checkboxes"
																style="max-height: 200px; overflow: auto;">
																<input type="text" id="spouse_searchSubject"
																	name="spouse_searchSubject"
																	placeholder="Search Subjects"
																	class="form-control autocomplete" autocomplete="off">
																<c:forEach var="item" items="${getSubject}"
																	varStatus="num">
																	<label for="one" class="spouse_subjectlist"> <input
																		type="checkbox" name="spouse_multisub"
																		value="${item[0]}" /> ${item[1]}
																	</label>
																</c:forEach>
															</div>

														</div>
													</div>

												</div>
											</div>
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Institute & Place</label>
													</div>
													<div class="col-md-8">

														<input type="text" id="spouse_institute_place"
															name="spouse_institute_place"
															class="form-control autocomplete" autocomplete="off"
															onkeypress="return onlyAlphabets(event);" maxlength="50"
															oninput="this.value = this.value.toUpperCase()">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;"></strong>Selected Subject</label>
													</div>
													<div class="col-md-8">
														<div class="row">

															<div id="spouse_quali_sub_list"
																style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

															</div>

														</div>

													</div>
												</div>

											</div>


										</div>
										


							</div>
								</div>
									
							</div>
								<div class="card-footer" align="center" class="form-control"> 
										<input type="button" id="mrgbtn_save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return married_save_fn();" >
										<input type="button" id="mrgqualibtn_save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return spouse_qualification_save_fn();" style="display: none">		              
							   </div>
						</div>
					</div>
				</div>
			</div>
<!-- 		</div> -->
	</form>
	<!-- END MARITAL EVENTS DETAIL -->
		<!--  START DETAILS OF CHILDRENS -->
		<div class="card">
			<div class="panel-group" id="accordion3">
				<div class="panel panel-default" id="g_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="g_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion3" href="#" id="g_final" onclick="divN(this)"
								><b>UPDATE CHILD DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1g" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('update_child_dtl');">
						</div>
								<div class="card-body-header">
									<h5></h5>
								</div>
								<table id="m_children_details_table" class="table-bordered" style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th><strong style="color: red;">* </strong>Name</th>
										<th><strong style="color: red;">* </strong>Date of Birth</th>
										<th>If Specially Abled Child </th>
										<th><strong style="color: red;">* </strong>Relationship</th>
										<th>If Adopted Child </th>
										<th><strong style="color: red;"> </strong>Adoption Date </th>
										<th><strong style="color: red;"> </strong>Aadhaar Card No </th>
										<th>PAN Card No </th>
										<th>Service/Ex-Service</th>
										<th><strong style="color: red;"></strong>Services</th>
										<th><strong style="color: red;"> </strong>Personal No.</th>
										<th>Other Service</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="m_children_detailstbody" style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_children1">
										<td class="child_srno" readonly>1</td>
										<td>
										<input type="text" id="sib_name1" name="sib_name1"  class="form-control autocomplete" autocomplete="off" 
										maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">
										</td>
										<td>
										 <input type="text" name="sib_date_of_birth1" id="sib_date_of_birth1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</td>
										<td>
										<input type="checkbox" id="sib_type1" name="sib_type1" value="Yes">
										</td>
										<td>
										<select name="sib_relationship1" id="sib_relationship1" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										</td>
										<td>
										<input type="checkbox" id="sib_adopted1" name="sib_adopted1" value="Yes" onclick="adoption(1);">
										</td>
										<td>
										<input type="text" name="sib_adop_date1" id="sib_adop_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" readonly="readonly" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</td>
										<td>
										<input type="text" id="aadhar_no1" name="aadhar_no1"  class="form-control autocomplete" autocomplete="off" 
										         maxlength="14" onkeypress="return isAadhar(this,event);">
										</td>
										<td>
										<input type="text" id="pan_no1" name="pan_no1"  class="form-control autocomplete" autocomplete="off" 
										          maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">
										</td>
										<td style="display: none;">
										<input type="text" id="sib_ch_id1" name="sib_ch_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>
										<td>
										<input type="checkbox" id="child-ex1" name="child-ex1" value="Yes" onclick="childCB(1);">
										</td>
										<td><select name="child_service1" id="child_service1" onchange="otherfunction(1)"
												class="form-control-sm form-control"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select></td>
											<td><input type="text" id="child_personal_no1"
												name="child_personal_no1" class="form-control autocomplete"
												autocomplete="off" maxlength="9"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_child_ser1"
												name="other_child_ser1" class="form-control autocomplete"
												autocomplete="off" maxlength="50"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
										<td>
										<a class="btn btn-primary btn-sm" value="SAVE" title="Submit for Approval" id="m_children_details_save1"
										   onclick="m_children_details_save_fn(1);"><i class="fa fa-save"></i>
										</a> 
										<a style="display: none;" id="m_children_details_add1" class="btn btn-success btn-sm" 
										value="ADD" title="ADD" onclick="m_children_details_add_fn(1);">
										<i class="fa fa-plus"></i>
										</a>
										<a style="display: none;" id="m_children_details_remove1" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
										onclick="m_children_details_remove_fn(1);"><i class="fa fa-trash"></i>
										</a>
										</td>
									</tr>
								</tbody>
							</table>
							</div>
						</div>
						
							  
					</div>
				</div>
			</div>
		</div>
		<!-- END DETAILS OF CHILDRENS -->
		<!-- START NOK -->
		<form id="form_nok_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion10">
				<div class="panel panel-default" id="h_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="h_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion10" href="#" id="h_final" onclick="divN(this)"
								><b>CHANGE IN NOK</b></a>
						</h4>
					</div>
					<div id="collapse1h" class="panel-collapse collapse">
						<div class="card-body card-block">
						<div align="left">
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_nok');">
						</div>
						<br>
							<div class="row">
							<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">*</strong> Authority</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" 
						                   autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)"  > 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">*</strong> Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <%--  <input type="date" id="date_authority" name="date_authority"  max="${date}" min='1899-01-01' class="form-control autocomplete" autocomplete="off" maxlength="10"> --%> 						                     
						                  <input type="text" name="date_authority" id="date_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Name of NOK</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_name" name="nok_name" class="form-control autocomplete"
						                    autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"> 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Relation</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="nok_id" name="nok_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                 
						                 <select name="nok_relation" id="nok_relation" class="form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getRelationList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>											
											</select> 						                     
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">
		           			<label class=" form-control-label"  style="margin-left:10px;"><h4> NOK's Address</h4></label>
		          		 </div> 
		          		 		<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="nok_country" id="nok_country" class="form-control"   onchange="fn_nok_Country();">
						                         <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="nok_state" id="nok_state" class="form-control"  onchange="fn_nok_state();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              			</div>
	              			</div>
	              				<div class="col-md-12">
								<div class="col-md-6" id = "nok_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="ctry_other" name="ctry_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="st_other" name="st_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              			 <div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                <select name="nok_district" id="nok_district" class="form-control"  onchange="fn_nok_district();" >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="nok_tehsil" id="nok_tehsil" class="form-control"   onchange="fn_nok_tehsil();" >
					                		 <option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "nok_other_dist" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="dist_other" name="dist_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nok_tehsil_other" name="nok_tehsil_other" class="form-control autocomplete" 
					                	autocomplete="off" 	onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="nok_village" name="nok_village" class="form-control autocomplete" 
											autocomplete="off"  onkeypress="return onlyAlphabets(event);" maxlength="50">			               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_pin" name="nok_pin" class="form-control autocomplete" autocomplete="off" 
						                   onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			  			<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="nok_rail" name="nok_rail" class="form-control autocomplete" autocomplete="off"
						                  maxlength="100" 	onkeypress="return onlyAlphaNumeric(event);"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="nok_rural">
							 <input type="radio" id="nok_rural" name="nok_rural_urban" value="rural" > Rural</label> 
					    	 <label for="nok_urban" >
							 <input type="radio" id="nok_urban" name="nok_rural_urban" value="urban" > Urban</label> 
					    	 <label for="nok_semi_urban">
							 <input type="radio" id="nok_semi_urban" name="nok_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div>	
	              				</div>
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Nok Mobile No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nok_mobile_no" name="nok_mobile_no" class="form-control autocomplete" 
						                   autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="10"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
					    <div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_save_nok_details();">		              
			           
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END NOK -->
	
	<!-- START ADDRESS -->
	<form id="form_addr_details_form">
		<div class="card">
			<div class="panel-group" id="accordion9">
				<div class="panel panel-default" id="i_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="i_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion9" href="#" id="i_final" onclick="divN(this)"
								><b>CHANGE IN ADDRESS</b></a>
						</h4>
					</div>
					<div id="collapse1i" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br>
						 <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('change_in_address');">
							<div class="row" >
									<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">*</strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" name="authority" id="addr_authority" class="form-control autocomplete" 
						                 autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)" >					               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">*</strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="date_authority" id="addr_date_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>	
							
							<label class=" form-control-label" style="margin-left:10px;"><h4> Present Domicile</h4></label>
							<div class="col-md-12">	
		 				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					               						                   
					                <select name="pre_country" id="pre_country" class="form-control"   onchange="fn_pre_domicile_Country()">
						                          <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
					                </div>
					                <div class="col-md-8">
						                 <input type="hidden" id="addr_ch_id" name="addr_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                     
					               			<select name="pre_domicile_state" id="pre_domicile_state" class="form-control" onchange="fn_pre_domicile_state();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "add_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_country_other" name="pre_country_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "add_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_state_other" name="pre_domicile_state_other" 
					                	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              			<div class="col-md-12">	
              			 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="pre_domicile_district" id="pre_domicile_district" class="form-control"  onchange="fn_pre_domicile_district();" >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>						               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
					                </div>
					                <div class="col-md-8">
					               			<select name="pre_domicile_tesil" id="pre_domicile_tesil" class="form-control"  onchange="fn_pre_domicile_tesil();" >
						                        <option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</SELECT> 						               				               
					                </div>
					            </div>
              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "add_other_dt" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_district_other" name="pre_domicile_district_other"
					                	 class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "add_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_tesil_other" name="pre_domicile_tesil_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              		
	              			<label class=" form-control-label" style="margin-left:10px;"><h4> Permanent Address</h4></label>
	              					<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="per_home_addr_country" id="per_home_addr_country" class="form-control"  onchange="fn_per_home_addr_Country();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="per_home_addr_state" id="per_home_addr_state" class="form-control"  onchange="fn_per_home_addr_state();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              			</div>
	              			</div>
	              			<div class="col-md-12">
								<div class="col-md-6" id = "per_home_addr_country_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_country_others" name="per_home_addr_country_others"
					                	 class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_state_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_state_others" name="per_home_addr_state_others" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                <select name="per_home_addr_district" id="per_home_addr_district" class="form-control"   onchange="fn_per_home_addr_district();">
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="per_home_addr_tehsil" id="per_home_addr_tehsil" class="form-control" onchange="fn_per_home_addr_tehsil();"  >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              			<div class="col-md-12">
								<div class="col-md-6" id = "per_home_addr_district_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_district_others" name="per_home_addr_district_others" 
					                	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_tehsil_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_tehsil_others" name="per_home_addr_tehsil_others" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="per_home_addr_village" name="per_home_addr_village" 	onkeypress="return onlyAlphabets(event);" 
											class="form-control autocomplete" autocomplete="off"  maxlength="50">				               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="per_home_addr_pin" name="per_home_addr_pin" class="form-control autocomplete" 
						                   autocomplete="off"  onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              					<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="per_home_addr_rail" name="per_home_addr_rail" 	onkeypress="return onlyAlphaNumeric(event);" 
						                 class="form-control autocomplete" autocomplete="off" maxlength="100"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="lbl_per_home_addr_rural">
							 <input type="radio" id="per_home_addr_rural" name="per_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_per_home_addr_urban" >
							 <input type="radio" id="per_home_addr_urban" name="per_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_per_home_addr_semi_urban">
							 <input type="radio" id="per_home_addr_semi_urban" name="per_home_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div> 	
	              				</div>
	              		<div class="col-md-12">	  
	            			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Border area</label>
						                </div>
						                <div class="col-md-8">
						                	 <label for="border_area">
							 <input type="radio" id="border_area" name="border_area" value="yes"   >Yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="border_area1" name="border_area" value="no" > No</label>
						</div>
						                </div>
						            </div>
	              				</div>
	              			
						           		<div class="col-md-12"></div>
		 
			  <div class="col-md-12">
			 	<label class=" form-control-label"  style="margin-left:10px;"><h4> Present Address</h4></label>
			 </div> 
			 	<div class="col-md-12" style="font-size: 15px;">	
           		<input type="checkbox" id="check_address" name="check_address" onclick="copy_address()">
               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
            </div>
			 	 <div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					               						                   
					                <select name="pers_addr_country" id="pers_addr_country" class="form-control" onchange="fn_pers_addr_Country();"  >
						                          <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="pers_addr_state" id="pers_addr_state" class="form-control"  onchange="fn_pers_addr_state();" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              			</div>
	              			</div>
	              			<div class="col-md-12">
								<div class="col-md-6" id = "pers_addr_country_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_country_other" name="pers_addr_country_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_state_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_state_other" name="pers_addr_state_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              			 <div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						                <select name="pers_addr_district" id="pers_addr_district" class="form-control"  onchange="fn_pers_addr_district();" >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="pers_addr_tehsil" id="pers_addr_tehsil" class="form-control"   onchange="fn_pers_addr_tehsil();">
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              			<div class="col-md-12">
							<div class="col-md-6" id = "pers_addr_district_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_district_other" name="pers_addr_district_other" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_tehsil_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_tehsil_other" name="pers_addr_tehsil_other" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
	              					<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="pers_addr_village" name="pers_addr_village" 	onkeypress="return onlyAlphabets(event);" 
											class="form-control autocomplete" autocomplete="off"  maxlength="50">
				               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="pers_addr_pin" name="pers_addr_pin" class="form-control autocomplete" 
						                   autocomplete="off" onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="pers_addr_rail" name="pers_addr_rail" 	onkeypress="return onlyAlphaNumeric(event);"
						                 class="form-control autocomplete" autocomplete="off"  maxlength="100"> 						                     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="pers_addr_rural">
							 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div>	
	              				</div>
	              				
	              				<div id="spouse_addressMaindiv" style="display: none">
							<div class="col-md-12" style="font-size: 15px;">
								<input type="checkbox" id="check_spouse_address" name="check_spouse_address"
									onclick="spouse_addressFn()"> <label for="text-input"
									class=" form-control-label"
									style="color: #dd1a3e; margin-left: 10px;"> IS FAMILY STAYING IN SF ACCN </label>
							</div>
							<div id="spouse_addressInnerdiv" style="display: none">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_addr_Country" id="spouse_addr_Country"
												onchange="fn_spouse_addr_Country();"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> STATE</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_addr_state" id="spouse_addr_state"
												onchange="fn_spouse_addr_state();"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
                                <div class="col-md-12">
								<div class="col-md-6" id = "spouse_addr_Country_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_country_other" name="spouse_addr_country_other" 
					                	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "spouse_addr_state_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_state_other" name="spouse_addr_state_other" class="form-control autocomplete"
					                	 autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> DISTRICT</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_addr_district" id="spouse_addr_district"
												onchange="fn_spouse_addr_district();"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedDistrictName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row foInteger.parseInt(rm-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> TEHSIL</label>
										</div>
										<div class="col-md-8">
											<select name="spouse_addr_tehsil" id="spouse_addr_tehsil"
												class="form-control-sm form-control" onchange="fn_spouse_addr_tehsil();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCityName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6" id = "spouse_addr_district_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_district_other" name="spouse_addr_district_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "spouse_addr_tehsil_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_tehsil_other" name="spouse_addr_tehsil_other" class="form-control autocomplete" 
					                	autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Village/Town/City</label>
										</div>
										<div class="col-md-8">
											<%--  <select name="pers_addr_village" id="pers_addr_village" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getVillageList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> --%>
											<input type="text" id="spouse_addr_village" maxlength="50"
												name="spouse_addr_village" class="form-control autocomplete" 	onkeypress="return onlyAlphaNumeric(event);">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Pin</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="spouse_addr_pin" name="spouse_addr_pin"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return isNumber(event)" maxlength="6">
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Nearest Railway Station</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="spouse_addr_rail" name="spouse_addr_rail"
												class="form-control autocomplete" autocomplete="off" maxlength="100"
													onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-6">
						     <label for="spouse_addr_rural">
							 <input type="radio" id="spouse_addr_rural" name="spouse_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="spouse_addr_urban" >
							 <input type="radio" id="spouse_addr_urban" name="spouse_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="spouse_addr_semi_urban">
							 <input type="radio" id="spouse_addr_semi_urban" name="spouse_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
<!-- 						                 <div class="popup" onclick="myFunction()"> -->
<!-- 												<i class="fa fa-question-circle" -->
<!-- 													style="font-size: 28px; color: black"></i> <span -->
<!-- 													class="popuptext" id="myPopup">Urban :An urban area -->
<!-- 													is The region surrounding a city. Rural :Rural areas are -->
<!-- 													The opposite of urban areas, Rural areas Rural areas often -->
<!-- 													called The COUNTRY. Semi Urban : Semi Urban are large -->
<!-- 													residential areas that surround main cities. </span> -->
<!-- 											</div> -->
						            </div>
	              				</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Stn HQ SUS No</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="Stn_HQ_sus_no" name="Stn_HQ_sus_no" class="form-control autocomplete" autocomplete="off"  maxlength="8" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Stn HQ Name</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="Stn_HQ_unit_name" name="Stn_HQ_unit_name" class="form-control autocomplete" autocomplete="off" maxlength="100"  >
					                	 </div>
						            </div>
	              				</div>
	              					</div>
</div>
</div>
	              					
							</div>
						<div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_changeaddress_details_save();">		              
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END ADDRESS -->
	<!-- START CONTACT DETAILS -->
   <form id="form_cda_acnt_no">
		<div class="card">
			<div class="panel-group" id="accordion1">
				<div class="panel panel-default" id="j_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="j_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion1" href="#" id="j_final" onclick="divN(this)"
								><b>CHANGE IN CONTACT DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1j" class="panel-collapse collapse">
			            <div class="card-body card-block">
			            <br>
						   <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('contact_details');" /> 
							<div class="row">
	              		<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Gmail/Others</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="cda_id" name="cda_id" value="0" class="form-control autocomplete" autocomplete="off"  >
						                   <input type="text" id="gmail" name="gmail" class="form-control autocomplete" autocomplete="off" 
						                   onchange="validateEmail(this);" maxlength="64"> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>NIC Mail</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="nic_mail" name="nic_mail" class="form-control autocomplete" autocomplete="off" 
						                   maxlength="64" onchange="validateEmail(this);"> 
						                </div>
						            </div>
	              				</div>
	              				
	              			</div>
	              			
	              				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Mobile No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="mobile_no" name="mobile_no" maxlength="10" class="form-control autocomplete"  
						                   onkeypress="return isNumber(event)" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_cda_acnt_no();">		              
			            </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	<!-- END CONTACT DETAILS -->
	<!-- START LANGUAGE DETAIL -->
		<div class="card">
			<div class="panel-group" id="accordion15">
				<div class="panel panel-default" id="k_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="k_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion15" href="#" id="k_final" onclick="divN(this)"
								><b>ADD LANGUAGE</b></a>
						</h4>
					</div>
					<div id="collapse1k" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div><input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Add_language');" />
										<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="language_authority" name="language_authority" 	
						                 class="form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">

						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" name="language_date_of_authority" id="language_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div></br>
	              			</br>
	              			</br>
						<div class="card-header"><h5>Indian Language</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
									<table id="language_table" class="table-bordered " style="margin-top:10px;width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
							<th>Sr No</th>
							<th><strong style="color: red;">* </strong>Indian Language</th> 
							<th>Other Indian Language</th> 
							<th><strong style="color: red;">* </strong>Language Standard</th>
							<th>Other Language Standard</th>
							<th>Action</th>
					   </tr>
					</thead>
				   <tbody data-spy="scroll" id="langtbody" style="min-height:46px; max-height:650px; text-align: center;">
						<tr id="tr_lang1">
							<td class="lang_srno">1</td>
							
							<td>
							 <select  id="language1" name="language1" class="form-control autocomplete"  onchange="onLanguage(1)" >
							   <option value="0">--Select--</option>
								<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
								</select>
							</td>
							<td>
			 <input type="text" id="other_language1" name="other_language1" class="form-control autocomplete"  maxlength="50"
			 readonly autocomplete="off" onkeypress="return onlyAlphabets(event);">
						</td>
							<td>
							  <select  id="lang_std1" name="lang_std1" class="form-control autocomplete" onchange="onLanguage_std(1)" >
							   <option value="0">--Select--</option>
								<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
								</select>
							</td>
								<td>
			 <input type="text" id="other_lang_std1" name="other_lang_std1" class="form-control autocomplete"  readonly
			  autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
						</td>
						<td style="display:none;"><input type="text" id="lang_ch_id1" name="lang_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
							<td>
							<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "language_save1" onclick="language_save_fn(1);" ><i class="fa fa-save"></i></a>
							   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "language_add1" onclick="language_add_fn(1);" ><i class="fa fa-plus"></i></a>
							    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "language_remove1" onclick="language_remove_fn(1);"><i class="fa fa-trash"></i></a> 
							</td>
							</tr>
				   </tbody> 
						</table>
							<div class="card-header"><h5>Foreign Language</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
					<table id="foregin_language_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
								<thead style=" color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th><strong style="color: red;">* </strong>Language</th> 
										<th>Other Language</th>
										<th><strong style="color: red;">* </strong>Language Standard</th>
										<th>Other Language Standard</th>
										<th><strong style="color: red;">* </strong>Language Proficiency</th>
										<th>Other Language Proficiency</th>
										<th><strong style="color: red;">* </strong>Passing Year</th>
										<th>Action</th>
								   </tr>
								</thead>
							   <tbody data-spy="scroll" id="flangtbody" style="min-height:46px; max-height:650px; text-align: center;">
									<tr id="tr_flang1">
										<td class="flang_srno">1</td>
										
										<td>
										 <select  id="flanguage1" name="flanguage1" class="form-control autocomplete" onchange="onF_Language(1)" >
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</select>
										</td>
										<td>
			                       <input type="text" id="f_other_language1" name="f_other_language1" class="form-control autocomplete" 
			                         readonly autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
						         </td>
										<td>
										  <select  id="flang_std1" name="flang_std1" class="form-control autocomplete"  onchange="onF_Language_std(1)">
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</select>
										</td>
										<td>
			                         <input type="text" id="f_other_lang_std1" name="f_other_lang_std1" class="form-control autocomplete" 
			                         maxlength="50" readonly autocomplete="off" onkeypress="return onlyAlphabets(event);">
						                  </td>
										<td>
										   <select  id="lang_prof1" name="lang_prof1" class="form-control autocomplete"  onchange="onF_Language_prof(1)">
										  <option value="0">--Select--</option>
											<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										 </select>
										</td>
										<td>
			                         <input type="text" id="f_other_prof1" name="f_other_prof1" class="form-control autocomplete" 
			                          readonly autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
						                  </td>
										<td>
										  <input type="text" id="exam_pass1" name="exam_pass1" class="form-control autocomplete" 
										   autocomplete="off"maxlength="4" onkeypress="return isNumber(event)">
										</td>
										<td style="display:none;"><input type="text" id="flang_ch_id1" name="flang_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
										<td>
										<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "foregin_language_save1" onclick="foregin_language_save_fn(1);" ><i class="fa fa-save"></i></a>
										   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "foregin_language_add1" onclick="foregin_language_add_fn(1);" ><i class="fa fa-plus"></i></a>
										    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "foregin_language_remove1" onclick="foregin_language_remove_fn(1);"><i class="fa fa-trash"></i></a> 
										</td>
										</tr>
							   </tbody> 
									</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	<!-- END LANGUAGE DETAIL -->







		<!-- START QUALIFICATION DETAIL -->
	<form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion16">
				<div class="panel panel-default" id="l_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="l_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion16" href="#" id="l_final" onclick="divN(this)"
								><b>UPDATE QUALIFICATION</b></a>
						</h4>
						
					</div>
					<div id="collapse1l" class="panel-collapse collapse">
					
					
						<div class="card-body card-block"><br>
					<div align="left">
					   <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Update_Qualification');" />
					</div>
	        	<div class="row" >
	        	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="qualification_authority" name="qualification_authority" onkeyup="return specialcharecter(this)"
						                 class="form-control autocomplete" autocomplete="off" maxlength="100" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="qualification_date_of_authority" id="qualification_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Qualification Type</label>
							</div>
							<div class="col-md-8">
								<select name="quali_type" id="quali_type"
									class=" form-control"
									onchange="fn_qualification_typeChange(this,'quali','quali_degree','specialization')">
								<option value="0" >--Select--</option>
									<c:forEach var="item" items="${getQualificationTypeList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

								</select> <input type="hidden" id="qualification_ch_id"
									name="qualification_ch_id" value="0"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>

					</div>

					<div class="col-md-6" id="academyQuali">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Examination Passed</label>
							</div>
							<div class="col-md-8">
								<select name="quali" id="quali"
									class=" form-control"
									onchange="fn_ExaminationTypeChange(this,'quali_degree','specialization');fn_other_exam();">
									<option value="0">--Select--</option>
									
								</select>
							</div>
						</div>

					</div>
				</div>
                    <div class="col-md-12">
						<div class="col-md-6" id ="other_div_exam" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Examination Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="exam_other" name="exam_other" class="form-control autocomplete" 
					                	maxlength="50" onkeypress="return onlyAlphabets(event);"  autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>	   
                        </div>
				<div class="col-md-12">
				

					<div class="col-md-6" id="courseNamediv" >
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Degree Acquired</label>
							</div>
							<div class="col-md-8">
								<select name="quali_degree" id="quali_degree"
									class=" form-control" onchange="fn_otherShowHide(this,'other_div_qualiDeg','quali_deg_other')">

									<option value="0">--Select--</option>
									
									
								</select>
							</div>
						</div>

					</div>
					<div class="col-md-6" id="specializationDiv" >
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Specialization</label>
							</div>
							<div class="col-md-8">
								<select name="specialization" id="specialization"
									class="form-control" onchange="fn_otherShowHide(this,'other_div_qualiSpec','quali_spec_other')">
									<option value="0">--Select--</option>
								<c:forEach var="item" items="${getSpecializationList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
								</select>
							</div>
						</div>

					</div>

				</div>
				 <div class="col-md-12">
						<div class="col-md-6" id ="other_div_qualiDeg" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Degree Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="quali_deg_other" name="quali_deg_other" 
					                	maxlength="50" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>	   
	              				
	              				<div class="col-md-6" id ="other_div_qualiSpec" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Specialization Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="quali_spec_other" name="quali_spec_other" 
					                	maxlength="50" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>
                        </div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Year of Passing</label>
							</div>
							<div class="col-md-8">

								<input type="text" id="yrOfPass" name="yrOfPass"
									class="form-control autocomplete" autocomplete="off"
									onkeypress="return isNumber(event)" maxlength="4" />
							</div>
						</div>

					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Div/Grade</label>
							</div>
							<div class="col-md-8">
								<select name="div_class" id="div_class"
									class=" form-control"  onchange="fn_other_class();">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getDivclass}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</div>
				</div>
				
					<div class="col-md-12" id ="other_div_class" style="display: none;" >
				       <div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Div/Grade Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="class_other" name="class_other" 
					                	maxlength="50" onkeypress="return onlyAlphabets(event);" 
					                	class="form-control autocomplete" autocomplete="off" >
					                	 </div>
						            </div>
	              		</div>	   
					
				</div>		

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Subjects</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="showCheckboxes()">
										<select name="sub_quali" id="sub_quali"
											class=" form-control">
											<option>Select Subjects</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="quali_sub_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Subjects">
										</div>
										<div>
											<c:forEach var="item" items="${getSubject}" varStatus="num">
												<label for="one" class="quali_subjectlist"> <input
													type="checkbox" name="multisub" value="${item[0]}" />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>

								</div>
							</div>

						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Institute & Place</label>
							</div>
							<div class="col-md-8">
								<div class="row">
									<input type="text" id="institute_place" name="institute_place"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" maxlength="100" 
										oninput="this.value = this.value.toUpperCase()">
								</div>

							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Selected Subject</label>
							</div>
							<div class="col-md-8">
								<div class="row">

									<div id="quali_sub_list"
										style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

									</div>
								</div>
							</div>
						</div>





					</div>


				</div>
				<!--  bisag v1 220822 (Added as per miso sir guidance) -->
					<div class="col-md-12">
			<div class="col-md-6">
						<div class="row form-group">
							
									<strong style="color: red;">Note:-<span style="text-decoration:underline">PROFESSIONAL COURSES-</span> THOSE COURSE WHICH PROVIDES YOU WITH PRACTICAL SKILL, MAKING YOU JOB READY AFTER COMPLETION OF COURSE.</strong>
							
			<strong style="color: red;">Note:-<span style="text-decoration:underline">ACADEMIC COURSES-</span> GIVES YOU A STRONG ACADEMIC FOUNDATION ON A PARTICULAR SUBJECT BUT NOT NECESSARIALY MAKING YOU JOB READY.</strong>					
								
							</div>

						</div>
						
							<div class="col-md-6">
							</div>
					</div>
                   
				

		</div>
			<div class="card-footer" align="center" class="form-control">
					 <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="qualification_save_fn();">
			</div>
			 <table id="qualificationtable"
			class="table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th style="text-align: center; width: 10%;">Ser No</th>
					<th style="text-align: center;">Authority</th>
					<th style="text-align: center;">Date of Authority</th>	
					<th style="text-align: center;">Qualification Type</th>
					<th style="text-align: center;">Examination Passed</th>	
								
					<th style="text-align: center;">Degree Acquired</th>
					
					<th style="text-align: center;">Specialization</th>
					
					<th style="text-align: center;">Year of Passing</th>
					<th style="text-align: center;">Subjects</th>
					<th style="text-align: center;">Div/Grade</th>
					
					<th style="text-align: center;">Institute & Place</th>
					
			        
					<th class="hide-action" style="text-align: center;">Action</th>
				</tr>
			</thead>
			<tbody id="qualificationtbody">

			</tbody>
		</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END QUALIFICATION DETAIL -->
	<!-- START Promotional Exam -->
	
	<div class="card">
			<div class="panel-group" id="accordion99">
				<div class="panel panel-default" id="m_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="m_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion99" href="#" id="m_final" onclick="divN(this)"
								style="color: #90EE90;"><b>PROMOTIONAL EXAM</b></a>
						</h4>
					</div>
					<div id="collapse1m" class="panel-collapse collapse">

						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
							
							<br>
							<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('promotional_exam');" />
								<div class="card-body-header">
									<h5></h5>
								</div>
								   <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="promo_exam_authority" name="promo_exam_authority" class="form-control autocomplete" 
						                 autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="promo_exam_date_of_authority" id="promo_exam_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
		          		  <table id="promo_exam_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
							<th>Sr No</th>
							<th><strong style="color: red;">* </strong>Exam</th> 
							<th>Other Exam</th> 
							<th><strong style="color: red;">* </strong>Date of Passing</th>
							<th>Action</th>
					   </tr>
					</thead>
				   <tbody data-spy="scroll" id="promo_examtbody" style="min-height:46px; max-height:650px; text-align: center;">
						<tr id="tr_promo_exam1">
							<td class="proex_srno">1</td>
							
							<td>
							<select name="promo_exam1" id="promo_exam1" class="form-control-sm form-control" onchange="onPro_exam(1)">
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${getExamList}" varStatus="num">
			 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
			 								</c:forEach> 
 								</select> 
							</td>
							<td>
						 <input type="text" id="exam_other1" name="exam_other1" class="form-control autocomplete" 
						 maxlength="50" onkeypress="return onlyAlphabets(event);" readonly autocomplete="off" >
							
							</td>
							<td>
							  <input type="month" id="promo_exam_dop1" max="${month}" name="promo_exam_dop1"  class="form-control autocomplete" autocomplete="off" />
							</td>

						<td style="display:none;"><input type="text" id="promo_exam_ch_id1" name="promo_exam_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
							<td>
							<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "promo_exam_save1" onclick="promo_exam_save_fn(1);" ><i class="fa fa-save"></i></a>
							   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "promo_exam_add1" onclick="promo_exam_add_fn(1);" ><i class="fa fa-plus"></i></a>
							    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "promo_exam_remove1" onclick="promo_exam_remove_fn(1);"><i class="fa fa-trash"></i></a> 
							</td>
							</tr>
				   </tbody> 
						</table>
							</div>
						<!-- </div> -->
						
						
					</div>
				</div>
			</div>
		</div>
		<!-- END Promotional Exam -->		
		<!-- START Army Course -->
		<div class="card">

			<div class="panel-group" id="accordion98">
				<div class="panel panel-default" id="cc_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="cc_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion98" href="#" id="cc_final" onclick="divN(this)"
								style="color: #90EE90;"><b>ARMY COURSE</b></a>
						</h4>
					</div>
					<div id="collapse1cc" class="panel-collapse collapse">

						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div>
								<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('army_course');" />
								  <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="army_course_authority" name="army_course_authority" 
						                  class="form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="army_course_date_of_authority" id="army_course_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
						<table id="army_course_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
								<thead style=" color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th><strong style="color: red;">* </strong>Course Name</th> 
										<th><strong style="color: red;">* </strong>Course Name Abbreviation</th> 
										<th><strong style="color: red;">* </strong>Course Institute</th> 
										<th>Course Institute Other</th> 
										<th><strong style="color: red;">* </strong>Div/Grade</th>
										<th>Div/Grade Other</th>
										<th><strong style="color: red;">* </strong>Course Type</th>
										<th><strong style="color: red;">* </strong>Start Date</th>
										<th><strong style="color: red;">* </strong>Date of Completion</th>
										<th>Action</th>
								   </tr>
								</thead>
							   <tbody data-spy="scroll" id="army_coursetbody" style="min-height:46px; max-height:650px; text-align: center;">
									<tr id="tr_army_course1">
										<td class="army_course_srno">1</td>
										
										<td>
										 <input type="text" id="army_course_name1" name="army_course_name1" onkeypress="AutoCompleteCourse(this,1,0);"  
										  class="form-control autocomplete" autocomplete="off" maxlength="20" >
										</td>
										<td>
										 <input type="text" id="army_course_abbreviation1" name="army_course_abbreviation1" 
										 onkeypress="AutoCompleteCourse(this,1,1);"   class="form-control autocomplete" autocomplete="off" maxlength="20">
										</td>
										
										<td>
										  <select  id="army_course_institute1" name="army_course_institute1" class="form-control autocomplete"  onchange="onArmyCourseInstiChange(1)">
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getArmyCourseInstitute}" varStatus="num">
			 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
			 								</c:forEach> 
			 								</select>
										</td>
											<td>
										  <input type="text" id="army_course_institute_ot1" name="army_course_institute_ot1" maxlength="50"
										  class="form-control autocomplete"  readonly autocomplete="off" onkeypress="return onlyAlphabets(event);">
										</td>
										<td>
										  <select  id="army_course_div_grade1" name="army_course_div_grade1" class="form-control autocomplete"  onchange="onDivGrade(1)">
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getDivclass}" varStatus="num">
			 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
			 								</c:forEach> 
			 								</select>
										</td>
										<td>
										  <input type="text" id="ar_course_div_ot1" name="ar_course_div_ot1" class="form-control autocomplete"  
										  readonly autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" maxlength="50">
										</td>
										<td>
										   <select  id="army_course_type1" name="army_course_type1" class="form-control autocomplete" >
										  <option value="0">--Select--</option>
											<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">
		 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
		 									</c:forEach>
										 </select>
										</td>
										<td style="display: none">
										  <input type="text" id="course_type_ot1" name="course_type_ot1" class="form-control autocomplete" 
										  maxlength="50" readonly autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)">
										</td>
										<td>
										<input type="text" name="army_course_start_date1" id="army_course_start_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</td>
										<td>
										<input type="text" name="army_course_date_of_completion1" id="army_course_date_of_completion1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</td>
										<td style="display:none;"><input type="text" id="army_course_ch_id1" name="army_course_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
										<td>
										<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "army_course_save1" onclick="army_course_save_fn(1);" ><i class="fa fa-save"></i></a>
										   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "army_course_add1" onclick="army_course_add_fn(1);" ><i class="fa fa-plus"></i></a>
										    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "army_course_remove1" onclick="army_course_remove_fn(1);"><i class="fa fa-trash"></i></a> 
										</td>
										</tr>
							   </tbody> 
									</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
		<!-- END Army Course -->
	<!-- START BPET DETAIL -->
	<form id="forminspupdate" style="display: none">
		<div class="card">
			<div class="panel-group" id="accordion42">
				<div class="panel panel-default" id="p_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title yellow" id="p_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion42" href="#" id="p_final" onclick="divN(this)"
								><b>UPDATE BPET DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1p" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
									<h5></h5>
								</div>
								<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('bpet_details');" />
								<table id="bpet_table" class="table-bordered "
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th>Sr No</th>
											<th><strong style="color: red;">* </strong>BPET Result</th>
											<th>Other BPET Result</th>
											<th><strong style="color: red;">* </strong>BPET QTR</th>
											<th><strong style="color: red;">* </strong>Year</th>
											<th><strong style="color: red;">* </strong>Conducted at Unit</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="bpettbody"
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<tr id="tr_bpet1">
											<td class="bpet_srno">1</td>
											<td>
											<select name="BPET_result1" id="BPET_result1" onchange="onBeptResult(1)"
												class="form-control">
													 <option value="-1">--Select--</option>
	                                             <c:forEach var="item" items="${getBPET_result}" varStatus="num">
	                                                     <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
	                                             </c:forEach>
											</select></td>
											<td>
										  <input type="text" id="bept_result_others1" name="bept_result_others1" class="form-control autocomplete" 
										   maxlength="50" readonly autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)">
										</td>
											<td><select name="BPET_qe1" id="BPET_qe1"
												class="form-control">
													 <option value="-1">--Select--</option>
	                                             <c:forEach var="item" items="${getBPET_event_qe}" varStatus="num">
	                                                     <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
	                                             </c:forEach>
											</select></td>
											<td>
											<input type="text"id="bpet_year1" name="year1"	class="form-control autocomplete"
											 autocomplete="off" maxlength="4" 
											onkeypress="return isNumber(event);">
											</td>
											<td>
											<input type="text" id="b_unit_name1" name="unit_name1"  maxlength="100"
											class="form-control autocomplete" onkeypress="BPET_UnitAuto(this);" autocomplete="off">
											<input type="hidden" id="unit_sus_no1" name="unit_sus_no1" class="form-control autocomplete" 
											autocomplete="off">
											</td>
											<td style="display: none;">
											<input type="text" id="id1" name="id1" value="0" class="form-control autocomplete" autocomplete="off">
											</td>
											<td>
											<a class="btn btn-primary btn-sm" value="SAVE" title="Submit for Approval" id="bpet_save1" onclick="bpet_save_fn(1);">
												<i class="fa fa-save"></i></a>
												<a style="display: none;" class="btn btn-success btn-sm" value="ADD" title="ADD" id="bpet_add1"
												onclick="bpet_add_fn(1);"><i class="fa fa-plus"></i></a>
												<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="bpet_remove1"
												onclick="bpet_remove_fn(1);"><i class="fa fa-trash"></i></a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END BPET DETAIL -->
	<!-- START FIRING STANDERD -->
	<form id="forminspupdate" style="display: none">
		<div class="card">
			<div class="panel-group" id="accordion19">
				<div class="panel panel-default" id="q_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title yellow" id="q_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion19" href="#" id="q_final" onclick="divN(this)"
								><b>UPDATE FIRING STANDARD</b></a>
						</h4>
					</div>
					<div id="collapse1q" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
							<br>
							<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('firing_standard');" />
									<table id="fire_std_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">
								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 20%;"><strong style="color: red;">* </strong>Firing Grade</th>
										<th style="width: 20%;">Other Firing Grade</th>
										<th style="width: 20%;"><strong style="color: red;">* </strong>Firing Event QTR</th>
										<th style="width: 20%;"><strong style="color: red;">* </strong>Year</th>
										<th style="width: 20%;"><strong style="color: red;">* </strong>Conducted at Unit</th>
										<th style="width: 20%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="fire_stdtbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_fire_std1">
										<td class="fire_srno" style="width: 2%;">1</td>
										<td style="width: 20%;"><select name="firing_grade1" id="firing_grade1" onchange="onFiringGrade(1)"
											class="form-control">
												<option value="-1">--Select--</option>
                                             <c:forEach var="item" items="${getFiring_grade}" varStatus="num">
                                                     <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                             </c:forEach>
										</select></td>
										<td style="width: 10%;">
										  <input type="text" id="ot_firing_grade1" name="ot_firing_grade1" class="form-control autocomplete" 
										  maxlength="50" readonly autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)">
										</td>
										<td style="width: 20%;">
										<select name="firing_event_qe1" id="firing_event_qe1" class="form-control">
                                             <option value="-1">--Select--</option>
                                             <c:forEach var="item" items="${getBPET_event_qe}" varStatus="num">
                                                     <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                             </c:forEach>
                                     </select>	</td>
										<td style="width: 20%;">
										<input type="text"id="year1" name="year1"	class="form-control autocomplete" 
										autocomplete="off" maxlength="4"  onkeypress="return isNumber(event);">
										</td>
										<td style="width: 20%;">
										<input type="text" id="firing_unit_name1" name="firing_unit_name1" class="form-control autocomplete"
										 onkeypress="Firing_UnitAuto(this);" autocomplete="off" maxlength="100">
										<input type="hidden" id="firing_unit_sus_no1" name="firing_unit_sus_no1" class="form-control autocomplete" autocomplete="off">
										</td>
										<td style="display: none;">
										<input type="text" id="fire_id1" name="fire_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>
										<td style="width: 20%;">
										<a class="btn btn-primary btn-sm" value="SAVE" title="Submit for Approval" id="fire_std_save1" onclick="fire_std_save_fn(1);">
											<i class="fa fa-save"></i></a>
											<a style="display: none;" class="btn btn-success btn-sm" value="ADD" title="ADD" id="fire_std_add1"
											onclick="fire_std_add_fn(1);"><i class="fa fa-plus"></i></a>
											<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="fire_remove1"
											onclick="fire_remove_fn(1);"><i class="fa fa-trash"></i></a>
										</td>
									</tr>
								</tbody>
							</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END FIRING STANDERD -->
	<!-- START FILED SERVICE -->
	<form id="forminspupdate" style="display: none">
		<div class="card">
			<div class="panel-group" id="accordion45">
				<div class="panel panel-default" id="r_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="r_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion45" href="#" id="r_final" onclick="divN(this)"
								><b>Known Allergy</b></a>
						</h4>
					</div>
					<div id="collapse1r" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
							<br>
							 <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('known_allergy');" />
							
								<table id="allergic_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
					
						<tr>
							<th style="width: 2%;">Sr No</th>
							<th style="width: 10%;"><strong style="color: red;">* </strong>Allergy</th> 
							
							<th style="width: 10%;">Action</th>
					   </tr>
					</thead>
				   		<tbody data-spy="scroll" id="allergictbody" style="min-height: 46px; max-height: 650px; text-align: center;">
							<tr id="tr_allergic1">
								<td style="width: 2%;" class="aller_srno">1</td>
								<td><input type="text" id="allergic1" name="allergic1" maxlength="100"onkeypress="return onlyAlphaNumeric(event);"
									class="form-control autocomplete" autocomplete="off">
								</td>
							<td style="display:none;"><input type="text" id="allergic_ch_id1" name="allergic_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
							<td style="width: 10%;">
							<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save1" onclick="allergic_save_fn(1);" ><i class="fa fa-save"></i></a>
							   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add1" onclick="allergic_add_fn(1);" ><i class="fa fa-plus"></i></a>
							    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove1" onclick="allergic_remove_fn(1);"><i class="fa fa-trash"></i></a> 
							</td>
							</tr>
				   </tbody> 
						</table>
							</div>
							<div class="card-footer" align="right">
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END FILED SERVICE -->
	   <!-- START MEDICAL -->
	<form id="madical_category_form">
		<div class="card">
			<div class="panel-group" id="accordion32">
				<div class="panel panel-default" id="s_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="s_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion32" href="#" id="s_final" onclick="divN(this)"
								><b>CHANGE IN MEDICAL</b></a>
						</h4>
					</div>
					<div id="collapse1s" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br><input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Change_Medicle');" />
							<div class="row">
										<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-6">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Authority</label>
						</div>
						<div class="col-md-6">
<!-- 					 <input type="hidden" id="med_cat_ch_id" name="med_cat_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                      -->
						
							<input type="text" id="madical_authority"  maxlength="100"
								name="madical_authority" class="form-control autocomplete" onkeyup="return specialcharecter(this)"
								autocomplete="off">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-6">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Date of Authority</label>
						</div>
						<div class="col-md-6">
<!-- 							<input type="date" id="madical_date_of_authority" -->
<%-- 								name="madical_date_of_authority" max="${date}" min='1899-01-01' --%>
<!-- 								class="form-control autocomplete" autocomplete="off"> -->
								
			 								 <input type="text" name="madical_date_of_authority" id="madical_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              

						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="mad_classification_count" name="mad_classification_count" value="NIL">
				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-12" style="font-size: 15px; margin:10px; ">
								<input type="checkbox" id="check_1bx" name="check_1bx"
									onclick="oncheck_1bx()" value="1BX"> <label for="text-input"
									class=" form-control-label"
									style="margin-left: 10px;"><strong> SHAPE 1BX </strong></label>
							</div>
					</div>
						<input type="hidden" id="shape_1bx_id" name="shape_1bx_id"  value="0" class="form-control autocomplete" autocomplete="off" >
				</div>
					<div class="col-md-12" id="shape1bxdiv" style="display:none;">
				
			<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>From Date</label>
						</div>
						<div class="col-md-8">
<%-- 						<input type="date" id="1bx_from_date" name="1bx_from_date" max="${date_without_time}" class="form-control autocomplete" autocomplete="off"> --%>

			 											 <input type="text" name="1bx_from_date" id="1bx_from_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>To Date</label>
						</div>
						<div class="col-md-8">
<!-- 							<input type="date" id="1bx_to_date" name="1bx_to_date" class="form-control autocomplete" autocomplete="off"> -->
							 <input type="text" name="1bx_to_date" id="1bx_to_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Diagnosis</label>
						</div>
						<div class="col-md-8">
							<input type="text" name="1bx_diagnosis_code" id="1bx_diagnosis_code" class="form-control-sm form-control" autocomplete="off"
								placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">
						</div>
					</div>
				</div>
			</div>
			
				<div  id="shapediv" style="width: -webkit-fill-available;">				
							
			<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
			 <strong style="color: red;">* </strong><b>S - Psychological & Congnitive</b></div>
			<div class="">
	<table id="s_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass1">Diagnosis</th>	
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass1" >Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="s_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_sShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="s_status1" id="s_status1" 
																class="form-control-sm form-control" onchange="statusChange(1,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="sShape_value1" id="sShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'s')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
			 											 <input type="text" name="s_from_date1" id="s_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="s_to_date1" id="s_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style="display:none; "  class="diagnosisClass11" >
														 <input type="text" name="_diagnosis_code11" id="_diagnosis_code11" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="s_diagnosis_code21" id="s_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="s_diagnosis_code31" id="s_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="s_diagnosis_code41" id="s_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="s_diagnosis_code51" id="s_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="s_diagnosis_code61" id="s_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="sShape_ch_id1" name="sShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass11">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add1" onclick="sShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					<br/>
				
		<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
		 <strong style="color: red;">* </strong><b>H - Hearing</b>
		
		
		 </div>
				<div>
	<table id="h_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass2">Diagnosis</th>		
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass2">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="h_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_hShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="h_status1" id="h_status1" 
																class="form-control-sm form-control" onchange="statusChange(2,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="hShape_value1" id="hShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'h')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
													<td style="">
			 											 <input type="text" name="h_from_date1" id="h_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="h_to_date1" id="h_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style=" display:none;"  class="diagnosisClass21">
														 <input type="text" name="_diagnosis_code21" id="_diagnosis_code21" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="h_diagnosis_code21" id="h_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="h_diagnosis_code31" id="h_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="h_diagnosis_code41" id="h_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="h_diagnosis_code51" id="h_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="h_diagnosis_code61" id="h_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="hShape_ch_id1" name="hShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass21">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add1" onclick="hShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
				<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
				
				<strong style="color: red;">* </strong><b>A - Appendages</b>
				 </div>		
					<div>
	<table id="a_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass3">Diagnosis</th>	
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass3">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="a_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_aShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="a_status1" id="a_status1" 
																class="form-control-sm form-control" onchange="statusChange(3,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="aShape_value1" id="aShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'a')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
														<td style="">
			 											 <input type="text" name="a_from_date1" id="a_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="a_to_date1" id="a_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style=" display:none; "  class="diagnosisClass31">
														 <input type="text" name="_diagnosis_code31" id="_diagnosis_code31" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="a_diagnosis_code21" id="a_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="a_diagnosis_code31" id="a_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="a_diagnosis_code41" id="a_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="a_diagnosis_code51" id="a_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="a_diagnosis_code61" id="a_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="aShape_ch_id1" name="aShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass31">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add1" onclick="aShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> 
					<strong style="color: red;">* </strong><b>P - Physical Capacity</b>
					</div>	
					<div>
	<table id="p_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none; " class="diagnosisClass4">Diagnosis</th>	
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass4">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="p_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="p_status1" id="p_status1" 
																class="form-control-sm form-control" onchange="statusChange(4,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="pShape_value1" id="pShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'p')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
													<td style="">
			 											 <input type="text" name="p_from_date1" id="p_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="p_to_date1" id="p_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style="display:none; "  class="diagnosisClass41">
														 <input type="text" name="_diagnosis_code41" id="_diagnosis_code41" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="p_diagnosis_code21" id="p_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="p_diagnosis_code31" id="p_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="p_diagnosis_code41" id="p_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="p_diagnosis_code51" id="p_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="p_diagnosis_code61" id="p_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="pShape_ch_id1" name="pShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass41">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add1" onclick="pShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					<br/>
					
	<div class="card-header-inner" style="text-align: center;margin-bottom:20px;">
	<strong style="color: red;">* </strong><b>E - Eye Sight</b>
	
	 </div>			
					<div>
	<table id="e_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass5">Diagnosis</th>		
<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
													<th style="display:none;" class="addbtClass5">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="e_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eShape1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="e_status1" id="e_status1" 
																class="form-control-sm form-control" onchange="statusChange(5,1,this.options[this.selectedIndex].value)">
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">
																	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                 <td style="">
														<select name="eShape_value1" id="eShape_value1" 
																class="form-control-sm form-control" onchange="onShapeValueChange(this,'e')">
																<option value="0">--Select--</option>																
															</select>
														</td>
						                             
													<td style="">
			 											 <input type="text" name="e_from_date1" id="e_from_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
			 											 </td>
						                               
							                        <td style="">
						                              <input type="text" name="e_to_date1" id="e_to_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						              
						                               </td>
						                                <td style="display:none;" class="diagnosisClass51">
														 <input type="text" name="_diagnosis_code51" id="_diagnosis_code51" class="form-control-sm form-control" autocomplete="off" 
														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                              
														   </td>
<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
<!-- 														 <input type="text" name="e_diagnosis_code21" id="e_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
<!-- 														 <input type="text" name="e_diagnosis_code31" id="e_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
<!-- 														 <input type="text" name="e_diagnosis_code41" id="e_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
																		                              
														   
<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
<!-- 														 <input type="text" name="e_diagnosis_code51" id="e_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
<!-- 														 <input type="text" name="e_diagnosis_code61" id="e_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
						                             
						                             <td style="display:none;"><input type="text" id="eShape_ch_id1" name="eShape_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%;display:none;" class="addbtClass51">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add1" onclick="eShape_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
				<br/>
				
				<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>C - Climate and Terrain Restrictions</strong></div>
				<div style="width: -webkit-fill-available;">
	<table id="c_cmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="cCopClass">Other</th>  													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass1">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_cmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_cCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_cvalue1" id="c_cvalue1" onchange="onCCopeChange(this,1); onCopeChangebt(this,1,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getcCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
						                             <td style="display:none;" class="cCopClass1" >
														 <input type="text" name="c_cother1" id="c_cother1" class="form-control-sm form-control" autocomplete="off" >						                              
														   </td>
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_cdescription1" id="c_cdescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="cCope_ch_id1" name="cCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass11">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add1" onclick="cCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					<br/>
					
					<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>O - Degree of Medical Observation Required</strong></div>
									<div style="width: -webkit-fill-available;">
	<table id="c_omadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass2">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_omadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_oCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_ovalue1" id="c_ovalue1" onchange="onCopeChangebt(this,2,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getoCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
						                             
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_odescription1" id="c_odescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="oCope_ch_id1" name="oCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass21">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add1" onclick="oCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>P - Physical Capability Limitations</strong></div>
										<div style="width: -webkit-fill-available;">
	<table id="c_pmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass3">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_pmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_pCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_pvalue1" id="c_pvalue1" onchange="onCopeChangebt(this,3,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${getpCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                
						                             
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_pdescription1" id="c_pdescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="pCope_ch_id1" name="pCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass31">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add1" onclick="pCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>	
					

<br/>
<div class="card-header-inner" style="text-align: center;margin-bottom:20px;"> <strong>E - Exclusive Limitations</strong></div>
			<div style="width: -webkit-fill-available;">
	<table id="c_emadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="eCopClass">SubValue</th> 
													<th style="display:none;" class="eCop_otherClass">Other</th> 													
<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display:none;" class="CopaddbtClass4">Action</th>
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_emadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 <tr id="tr_eCope1">
													<td  style="width: 2%;">1</td>
													<td style="">
													<select name="c_evalue1" id="c_evalue1" onchange="onECopeChange(this,1); onCopeChangebt(this,4,1);"
																class="form-control-sm form-control" >
<!-- 																<option value="0">--Select--</option>																 -->
																<c:forEach var="item" items="${geteCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                                <td style="display:none;" class="eCopClass1">
													<select name="c_esubvalue1" id="c_esubvalue1" onchange="onECopeSubChange(this,1)"
																class="form-control-sm form-control" >
																<option value="0">--Select--</option>																
																<c:forEach var="item" items="${getesubCopeList}" varStatus="num">
																	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																</c:forEach>
															</select>
						                               </td>
						                             <td style="display:none;" class="eCop_otherClass1" >
														 <input type="text" name="c_esubvalueother1" id="c_esubvalueother1" class="form-control-sm form-control" autocomplete="off" >						                              
														   </td>
														
<!-- 						                                <td style="" > -->
<!-- 														 <input type="text" name="c_edescription1" id="c_edescription1" class="form-control-sm form-control" autocomplete="off"  -->
<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
<!-- 														   </td> -->
														   
						                             
						                             <td style="display:none;"><input type="text" id="eCope_ch_id1" name="eCope_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						                               
													<td style="width: 10%; display:none;" class="CopaddbtClass41">
													   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add1" onclick="eCope_add_fn(1);" ><i class="fa fa-plus"></i></a>
													</td>
													</tr>
										   </tbody> 
									</table>
					</div>
					</div>
					
							</div>
	     <div class="card-footer" align="center" class="form-control"> 
		        <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return medical_cat_save();">		              
		 </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<input type="hidden" id="sShape_count" name="sShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="sShapeOld_count" name="sShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="hShape_count" name="hShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="hShapeOld_count" name="hShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="aShape_count" name="aShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="aShapeOld_count" name="aShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="pShape_count" name="pShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pShapeOld_count" name="pShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="eShape_count" name="eShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eShapeOld_count" name="eShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="cCope_count" name="cCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="cCopeOld_count" name="cCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="oCope_count" name="oCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="oCopeOld_count" name="oCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="pCope_count" name="pCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pCopeOld_count" name="pCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="eCope_count" name="eCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eCopeOld_count" name="eCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
		
	</form>
	<!-- END MEDICAL -->
	<!-- START FOREIGN COUNTRY -->
	<form id="forminspupdate" style="display: none">
		<div class="card">
			<div class="panel-group" id="accordion18">
				<div class="panel panel-default" id="t_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="t_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion18" href="#" id="t_final" onclick="divN(this)"
								><b>UPDATE VISIT TO FOREIGN COUNTRY</b></a>
						</h4>
					</div>
					<div id="collapse1t" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
							<div class="card-body card-block" id="total_table">
								<br>
								<input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Update_Visit_Foriegn');" />
							<table id="foreign_country_visit" class="table-bordered " style="margin-top:10px;">
								<thead style=" color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;"><strong style="color: red;">* </strong>Country Visited</th> 
										<th style="width: 10%;">Country Visited Other</th>
										<th style="width: 10%;"><strong style="color: red;">* </strong>From</th>
										<th style="width: 10%;"><strong style="color: red;">* </strong>To</th>
										<th style="width: 10%;">Duration</th>
										<th style="width: 10%;"><strong style="color: red;">* </strong>Purpose of Visit</th>
										<th style="width: 10%;">Purpose of Visit Other</th>
										<th style="width: 10%;">Action</th>
								   </tr>
								</thead>
							   <tbody data-spy="scroll" id="foregin_Country_tbody" style="min-height:46px; max-height:650px; text-align: center;">
									<tr id="tr_foregin_country1">
										<td class="fcon_srno" style="width: 2%;">1</td>
										<td style="width: 10%;">
										    <select name="country1" id="country1" class="form-control"  onchange="onContryVisited(1)" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getForeign_country}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
										</td>
										<td style="width: 10%;">
										  <input type="text" id="foregin_Country_ot1" name="foregin_Country_ot1" class="form-control autocomplete" 
										   readonly autocomplete="off"maxlength="50" onkeypress="return onlyAlphabets(event);">
										</td>
										<td style="width: 10%;">
										 <input type="text" name="from_dt1" id="from_dt1" maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');calcDate22(1);validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</td>
										<td style="width: 5%;">
										 <input type="text" name="to_dt1" id="to_dt1" maxlength="10"   onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');calcDate22(1);validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</td>
										<td style="width: 10%;">
										  <input type="text" id="period1" name="period1" class="form-control autocomplete" autocomplete="off"
										   maxlength="4" onkeypress="isNumber(event)" readonly="readonly">
										</td>
										<td style="width: 5%;">
											<select name="purpose_visit1" id="purpose_visit1" class="form-control"   onchange="onPurposeVisited(1)" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getForiegnCountryList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
										</td>
										<td style="width: 10%;">
										  <input type="text" id="purpose_visit_ot1" name="purpose_visit_ot1" class="form-control autocomplete"  
										  readonly autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">
										</td>
									<td style="display:none;"><input type="text" id="foregin_country_ch_id1" name="foregin_country_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
										<td style="width: 10%;">
										<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "country_save1" onclick="UPforeign_country_save_fn(1);" ><i class="fa fa-save"></i></a>
										   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "country_add1" onclick="UPforeign_country_add_fn(1);" ><i class="fa fa-plus"></i></a>
										    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "country_remove1" onclick="UPforeign_country_remove_fn(1);"><i class="fa fa-trash"></i></a> 
										</td>
										</tr>
				   					</tbody> 
								</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END FOREIGN COUNTRY -->
	 <!-- START AWARD & MEDAL -->
	<form id="forminspupdate">
                <div class="card">
                        <div class="panel-group" id="accordion14">
                                <div class="panel panel-default" id="u_div1">
                                        <div class="panel-heading">
                                                <h4 class="panel-title main_title lightred" id="u_div5">
                                                        <a class="droparrow collapsed" data-toggle="collapse"
                                                                data-parent="#accordion14" href="#" id="u_final" onclick="divN(this)"
                                                                ><b>UPDATE AWARDS & MEDAL</b></a>
                                                </h4>
                                        </div>
                                        <div id="collapse1u" class="panel-collapse collapse">
                                                <!-- <div class="card-body card-block"> -->
                                                        <div class="card-body card-block" id="total_table"><br>
                                                              <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('update_awards_medal');">							
                                                        <table id="awardsNmedal_table" class="table-bordered " style="margin-top:10px;">
                                                                <thead style=" color: white; text-align: center;">
                                                                        <tr>
                                                                                <th style="width: 2%;">Sr No</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Authority</th>                                                
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Date of Authority</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Category</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Award/Medal</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Date of Award</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Unit</th>
                                                                                <th style="width: 10%;"><strong style="color: red;"> </strong>BDE</th>
                                                                                <th style="width: 10%;"><strong style="color: red;"></strong>DIV/Sub Area</th>
                                                                                <th style="width: 10%;"><strong style="color: red;"></strong>Corps/Area</th>
                                                                                <th style="width: 10%;"><strong style="color: red;">* </strong>Command</th>                                                                                                                                                       
                                                                                
                                                                                <th style="width: 10%;">Action</th>
                                                                   </tr>
                                                                </thead>
                                                           <tbody data-spy="scroll" id="awardsNmedal_tbody" style="min-height:46px; max-height:650px; text-align: center;">
                                                                        <tr id="tr_awardsNmedal1">
                                                                                <td class="anm_srno" style="width: 2%;">1</td>
                                                                                <td style="width: 10%;">
                                                                                  <input type="text" id="awardsNmedal_authority1" name="awardsNmedal_authority1" class="form-control autocomplete" 
                                                                                  autocomplete="off" 	onkeyup="return specialcharecter(this)" maxlength="100">
                                                                                 </td>
                                                                                  <td style="width: 10%;">
                                                                                    <input type="text" name="awardsNmedal_date_of_authority1" id="awardsNmedal_date_of_authority1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
																					onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
																					onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
                                                                                   
                                                                                   </td>
                                                                                <td style="width: 10%;">
                                                                                        <select name="Category_81" id="Category_81" onchange="getMedalDescList(this)" class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getMedalList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                        </td>
                                                                                        <td style="width: 10%;">
                                                                                        <select name="select_desc1" id="select_desc1" class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                        </select>
                                                                                        </td>
                                                                                        <td style="width: 10%;">
                                                                                     <input type="text" name="date_of_award1" id="date_of_award1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
																					onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
																					onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
                                                                                   </td>
                                                                                   <td style="width: 10%;">
                                                                                  <input type="text" id="awardsNmedal_unit1" name="awardsNmedal_unit1" class="form-control autocomplete" 
                                                                                     onkeypress="unitData(this)" autocomplete="off" maxlength="255">
                                                                                </td>
                                                                                <td style="width: 10%;">
                                                                                <select name="awardsNmedal_bde1" id="awardsNmedal_bde1" class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                 <td style="width: 10%;">
                                                                                <select name="awardsNmedal_div_subarea1" id="awardsNmedal_div_subarea1"  class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_DivList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                   <td style="width: 10%;">
                                                                                   
                                                                                <select name="awardsNmedal_corps_area1" id="awardsNmedal_corps_area1"  class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                <td style="width: 10%;">
                                                                                <select name="awardsNmedal_command1" id="awardsNmedal_command1"  class="form-control">
                                                                                                        <option value="0">--Select--</option>
                                                                                                        <c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">
                                                                                                                <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
                                                                                                        </c:forEach>
                                                                                        </select>
                                                                                </td>
                                                                                
                                                                                <td style="display:none;"><input type="text" id="awardsNmedal_ch_id1" name="awardsNmedal_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
                                                                                <td style="width: 10%;">
                                                                                <a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "awardsNmedal_save1" onclick="awardsNmedal_save_fn(1);" ><i class="fa fa-save"></i></a>
                                                                                   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "awardsNmedal_add1" onclick="awardsNmedal_add_fn(1);" ><i class="fa fa-plus"></i></a>
                                                                                    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "awardsNmedal_remove1" onclick="awardsNmedal_remove_fn(1);"><i class="fa fa-trash"></i></a> 
                                                                                </td>
                                                                                </tr>
                                                           </tbody> 
                                                                                </table>
                                                        </div>
                                                <!-- </div> -->
                                        </div>
                                </div>
                        </div>
                </div>
        </form>
        <!-- END AWARD & MEDAL -->
        <!-- START BTLE & PHY -->
	    <form id="form_battle_physical_casuality">
		<div class="card">
			<div class="panel-group" id="accordion13">
				<div class="panel panel-default" id="v_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="v_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion13" href="#" id="v_final" onclick="divN(this)"
								><b>UPDATE BATTLE & PHYSICAL CASUALTY</b></a>
						</h4>
					</div>
					<div id="collapse1v" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br>
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('battle_physical');" /> 
							<div class="row" >
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority(Unit Letter/Medical Auth)</label>
											</div>
											<div class="col-md-8">
										 <input type="hidden" id="batnpc_ch_id" name="batnpc_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                     
												<input type="text" id="batnpc_authority"	onkeyup="return specialcharecter(this)"  maxlength="100"
													name="batnpc_authority" class="form-control autocomplete"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
										    <input type="text" name="batnpc_date_of_authority" id="batnpc_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
											</div>
										</div>
									</div>
								</div>
								<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>Casualty Details</strong></div>
								<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Casualty</label>
													</div>
													<div class="col-md-8">
															<input type="text" name="date_of_casuality" id="date_of_casuality" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
															onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
															onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label">Time of Casualty</label>
													</div>
													<div class="col-md-8">						
														<input type="time" id="time_of_casuality"
															name="time_of_casuality" class="form-control autocomplete" autocomplete="off">
													</div>
												</div>
											</div>
										</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong>Recommended For</label>
												</div>
												<div class="col-md-8" id="classification_of_casualityDIV">
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong>Nature of  Casualty</label>
												</div>	
											<div class="col-md-8" id="nature_of_casualityDiv" onclick="onNatureCasuality()">
											  </div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
											<div class="col-md-6">
<!-- 												<div class="row form-group"> -->
<!-- 													<div class="col-md-4"> -->
<!-- 														<label class=" form-control-label">Name of Operation</label> -->
<!-- 													</div> -->
<!-- 													<div class="col-md-8">						 -->
<!-- 														<input type="text" id="name_of_operation" -->
<!-- 															name="name_of_operation" class="form-control autocomplete" onkeypress="getNameOfOperation(this);" -->
<!-- 															autocomplete="off"> -->
<!-- 													</div> -->
<!-- 												</div> -->
											</div>
											<div class="col-md-6" id="missingdiv" style="display:none">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label">If Missing</label>
													</div>
													<div class="col-md-8">
															<select name="missing_desc" id="missing_desc"
															class="form-control" >
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getMissingList}" varStatus="num">
																<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
									<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>Location Where The Casualty Occurred</strong></div>
														<div class="col-md-12">
																<div class="col-md-6">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label">On duty or otherwise (Specify)</label>
																		</div>
																		<div class="col-md-8" onclick="onDutyOthers();">
																			<input type="radio" id="onduty1" name="onduty" value="YES" >
																				  <label for="Yes">Yes</label>
																					<input type="radio" id="onduty2" name="onduty" value="NO" >
																					<label for="No">No</label>
																					<input type="radio" id="onduty3" name="onduty" value="OTHERS" >
																					<label for="Others">Others</label>
																		</div>
																	</div>
																</div>
																<div class="col-md-6" id="onduityotherdiv" style="display: none;">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label">Others</label>
																		</div>
																		<div class="col-md-8">
																			<input type="text" id="onduityother" maxlength="50" onkeypress="return onlyAlphabets(event);"
																			name="onduityother" class="form-control autocomplete"
																			autocomplete="off" >
																		</div>
																	</div>
																</div>
														</div>
															<div class="col-md-12">
																<div class="col-md-6">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
																		</div>
																		<div class="col-md-8">
																			<select name="batnpc_country" id="batnpc_country"
																				class="form-control" onchange="onCountryChange(this);oncontyselect();">
																				<option value="0">--Select--</option>
																				<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
																					<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</div>
																<div class="col-md-6">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label">STATE/UT</label>
																		</div>
																		<div class="col-md-8">
																			<select name="batnpc_state" id="batnpc_state"
																				class="form-control" onchange="onStateChange(this)">
																				<option value="0">--Select--</option>
																			</select>
																		</div>
																	</div>
																</div>
														</div>
															<div class="col-md-12">
																<div class="col-md-6" id="country_other_div" style="display: none;">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label">COUNTRY OTHER</label>
																		</div>
																		<div class="col-md-8">
																		<input type="text" id="country_other" maxlength="50" onkeypress="return onlyAlphabets(event);"
																					 	name="country_other" class="form-control autocomplete"
																						autocomplete="off">
																		</div>
																	</div>
																</div>
																<div class="col-md-6" style="display: none;" id= "state_other_div">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label">STATE/UT OTHER</label>
																		</div>
																		<div class="col-md-8">
																			<input type="text" id=state_other maxlength="50" onkeypress="return onlyAlphabets(event);"
																						name="state_other" class="form-control autocomplete"
																						autocomplete="off">
																		</div>
																	</div>
																</div>
														</div>
															<div class="col-md-12">
																		<div class="col-md-6">
																			<div class="row form-group">
																				<div class="col-md-4">
																					<label class=" form-control-label">DISTRICT</label>
																				</div>
																				<div class="col-md-8">
																					<select name="batnpc_district" id="batnpc_district"
																						class="form-control" onchange="onDistrictChange(this)">
																						<option value="0">--Select--</option>
																					</select>
																				</div>
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="row form-group">
																				<div class="col-md-4">
																					<label class=" form-control-label">TEHSIL</label>
																				</div>
																				<div class="col-md-8">
																					<select name="batnpc_tehsil" id="batnpc_tehsil"  onchange="onTehsilChange(this)"
																						class="form-control" >
																						<option value="0">--Select--</option>
																					</select>
																				</div>
																			</div>
																		</div>
																	</div>
																		<div class="col-md-12">
																<div class="col-md-6" id="district_other_div" style="display: none;">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label">DISTRICT OTHER</label>
																		</div>
																		<div class="col-md-8">
																		<input type="text" id="district_other" maxlength="50" onkeypress="return onlyAlphabets(event);"
																			name="district_other" class="form-control autocomplete" autocomplete="off">
																		</div>
																	</div>
																</div>
																<div class="col-md-6" style="display: none;" id= "tehsil_other_div">
																	<div class="row form-group">
																		<div class="col-md-4">
																			<label class=" form-control-label">TEHSIL OTHER</label>
																		</div>
																		<div class="col-md-8">
																			<input type="text" id=tehsil_other maxlength="50" onkeypress="return onlyAlphabets(event);"
																						name="tehsil_other" class="form-control autocomplete"
																						autocomplete="off">
																		</div>
																	</div>
																</div>
														</div>
																
										<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label">Village/Town/City</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="batnpc_village" maxlength="50" onkeypress="return onlyAlphabets(event);"
														name="batnpc_village" class="form-control autocomplete" 
														autocomplete="off">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong style="color: red;">* </strong>Pin</label>
														</div>
														<div class="col-md-8">						
															<input type="text" id="batnpc_pin" pattern="^[0-9]*$"
																name="batnpc_pin" class="form-control autocomplete"
																autocomplete="off" maxlength="6" onkeypress="return isNumber(event)">
														</div>
													</div>
												</div>
											</div>
																				
																				
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Exact Place/Area/Post</label>
									</div>
									<div class="col-md-8">						
										<input type="text" id="batnpc_exact_place" onkeypress="return onlyAlphabets(event);"
											name="batnpc_exact_place" class="form-control autocomplete" maxlength="50"
											autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6" id="otherindcontydiv1" style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Name of Peace Keeping Mission / Trg Team / Expedition</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mission_expedition" maxlength="100" onkeypress="return onlyAlphabets(event);"
											name="mission_expedition" class="form-control autocomplete"
											autocomplete="off" >
										</div>
									</div>
								</div>
							
						</div> 
							<div class="col-md-12" id="otherindcontydiv2" style="display: none;">
													<div class="col-md-6">
														<div class="row form-group">
															<div class="col-md-4">
																<label class=" form-control-label">Name of Area /Post /Village /Town /Hospital </label>
															</div>
															<div class="col-md-8">
																<input type="text" id="area_post_town" maxlength="100" onkeypress="return onlyAlphabets(event);"
																name="area_post_town" class="form-control autocomplete"
																autocomplete="off" >
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row form-group">
															<div class="col-md-4">
																<label class=" form-control-label">Sector / Bde</label>
															</div>
															<div class="col-md-8">
																<input type="text" id="Sector_bde" maxlength="50" onkeypress="return onlyAlphabets(event);"
																name="Sector_bde" class="form-control autocomplete"
																autocomplete="off" >
															</div>
														</div>
													</div>
											</div>
																				
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong style="color: red;">* </strong>Name of Operation</label>
													</div>
													<div class="col-md-8">						
														<input type="text" id="name_of_operation" maxlength="200" 
															name="name_of_operation" class="form-control autocomplete" onkeypress="getNameOfOperation(this);"
															autocomplete="off">
													</div>
												</div>
											</div>
											<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label">Sector</label>
												</div>	
											<div class="col-md-8" >
											<input type="text" id="btnpc_sector" onkeypress="return onlyAlphabets(event);"
															name="btnpc_sector" class="form-control autocomplete" 
															autocomplete="off" maxlength="20">
											  </div>
											</div>
										</div>
										</div>
										<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label">Field Area</label>
												</div>	
											<div class="col-md-8">
											 <input type="radio" id="Peace" name="field_services" value="peace" >
											  <label for="Peace">Peace</label>
											  <input type="radio" id="Fd" name="field_services" value="Fd" >
											  <label for="Fd">Fd</label>
											  <input type="radio" id="CI" name="field_services" value="CI" >
											  <label for="CI">CI</label>
											  <input type="radio" id="HAA" name="field_services" value="HAA" >
											  <label for="HAA">HAA</label>
											  <input type="radio" id="Siachen" name="field_services" value="Siachen" >
											  <label for="Siachen">Siachen</label>
											  </div>
											</div>
										</div>
										<div class="col-md-6">
																		<div class="row form-group">
																		<div class="col-md-4">
																				<label class=" form-control-label"><strong style="color: red;">* </strong>Whether on</label>
																			</div>	
																		<div class="col-md-8">
																		 <input type="radio" id="whether_on1" name="whether_on" value="IB" >
																		  <label for="IB">IB</label>
																			<input type="radio" id="whether_on2" name="whether_on" value="LC" >
																			<label for="LC">LC</label>
																			<input type="radio" id="whether_on3" name="whether_on" value="LAC" >
																			<label for="LAC">LAC</label>
																			<input type="radio" id="whether_on4" name="whether_on" value="AGPL" >
																			<label for="AGPL">AGPL</label>
																			<input type="radio" id="whether_on5" name="whether_on" value="OTHERS" >
																			<label for="OTHERS">OTHERS</label>
																		  </div>
																		</div>
																	</div>
										
									</div>
<!-- 																<div class="col-md-12"> -->
<!-- 																			<div class="col-md-6"> -->
<!-- 																				<div class="row form-group"> -->
<!-- 																					<div class="col-md-4"> -->
<!-- 																						<label class=" form-control-label">Unit</label> -->
<!-- 																					</div> -->
<!-- 																					<div class="col-md-8">						 -->
<!-- 																						<input type="text" id="batnpc_unit" -->
<!-- 																							name="batnpc_unit" class="form-control autocomplete" -->
<!-- 																							autocomplete="off" onkeypress="unitData(this)"> -->
<!-- 																					</div> -->
<!-- 																				</div> -->
<!-- 																			</div> -->
<!-- 																		</div> -->
																		
																<div class="col-md-12">
																			<div class="col-md-6">
																				<div class="row form-group">
																					<div class="col-md-4">
																						<label class=" form-control-label"><strong style="color: red;">* </strong>BDE</label>
																					</div>
																					<div class="col-md-8">
																						<select name="batnpc_bde" id="batnpc_bde"
																							class="form-control">
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">
																								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																							</c:forEach>
																						</select>
																					</div>
																				</div>
																			</div>
																			<div class="col-md-6">
																				<div class="row form-group">
																					<div class="col-md-4">
																						<label class=" form-control-label"><strong style="color: red;">* </strong>DIV/Sub Area</label>
																					</div>
																					<div class="col-md-8">
																						<select name="batnpc_div_subarea" id="batnpc_div_subarea"
																							class="form-control">
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${getPSG_DivList}" varStatus="num">
																								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																							</c:forEach>
																						</select>
																					</div>
																				</div>
																			</div>																			
																		</div>
																		
																<div class="col-md-12">																		
																		<div class="col-md-6">
																			<div class="row form-group">
																				<div class="col-md-4">
																					<label class=" form-control-label"><strong style="color: red;">* </strong>Corps/Area</label>
																				</div>
																				<div class="col-md-8">
																					<select name="batnpc_corps_area" id="batnpc_corps_area"
																						class="form-control" >
																						<option value="0">--Select--</option>
																						<c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">
																							<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																		
																		<div class="col-md-6">
																			<div class="row form-group">
																				<div class="col-md-4">
																					<label class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
																				</div>
																				<div class="col-md-8">
																					<select name="batnpc_command" id="batnpc_command"
																						class="form-control" >
																						<option value="0">--Select--</option>
																						<c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">
																							<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																						</c:forEach>
																					</select>
																				</div>
																			</div>
																		</div>
																	</div>
																	
																	
																	<div class="col-md-12">
																						<div class="col-md-6">
																							<div class="row form-group">
																								<div class="col-md-4">
																									<label class=" form-control-label">Hospital Name</label>
																								</div>
																								<div class="col-md-8">
																									<input type="text" id="hospital_name" onkeypress="return onlyAlphaNumeric(event);"
																									name="hospital_name" class="form-control autocomplete" maxlength="100"
																									autocomplete="off" >
																								</div>
																							</div>
																						</div>
																						<div class="col-md-6">
																							<div class="row form-group">
																								<div class="col-md-4">
																									<label class=" form-control-label">Hospital Location</label>
																								</div>
																								<div class="col-md-8">
																									<input type="text" id="hospital_location" onkeypress="return onlyAlphaNumeric(event);"
																									name="hospital_location" class="form-control autocomplete" maxlength="100"
																									autocomplete="off" >
																								</div>
																							</div>
																						</div>
																				</div>
										
										
										
<!-- 							<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>Cause of Casualty </strong></div> -->
							<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label">Cause of Casualty</label>
												</div>	
											<div class="col-md-8" >
											<select name="cause_of_casuality_1" id="cause_of_casuality_1"
													class="form-control" onchange="onCauses1()">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCausesOfCasuality}" varStatus="num">
														<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
													</c:forEach>
												</select>
											  </div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<select name="cause_of_casuality_2" id="cause_of_casuality_2"
													class="form-control" onchange="onCauses2()" style="display: none">
													<option value="0">--Select--</option>
												</select>
												</div>
												<div class="col-md-4">	
												<select name="cause_of_casuality_3" id="cause_of_casuality_3"
													class="form-control" style="display: none">
													<option value="0">--Select--</option>
												</select>					
												</div>
											</div>
										</div>
										
									</div>
							<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label">Brief decription of circumstances leading to casualty</label>
												</div>	
											<div class="col-md-8" >
											<textarea id="circumstances" 	onkeypress="return onlyAlphaNumeric(event);"
															name="circumstances" class="form-control autocomplete" 
															autocomplete="off" maxlength="1000"></textarea>
											  </div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong><strong>Diagnosis</strong>
													</label>
												</div>
												<div class="col-md-8">	
												<strong style="color: red;">(As Specified in Medical Report. Use option  'Other' if required)</strong>					
													<input type="text" id="batnpc_diagnosis"
														name="batnpc_diagnosis" class="form-control autocomplete"
														autocomplete="off" onkeypress="AutoCompleteDiagnosis(this)" onchange="DiagnoseChanged();">
												</div>
											</div>
										</div>
										
									</div>
									<div class="col-md-12">
									<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label">In Aid to Civ Auth/Power</label>
												</div>	
											<div class="col-md-8">
											 <input type="radio" id="aid_to_civ1" name="aid_to_civ" value="YES" >
											  <label for="Yes">Yes</label>
											  <input type="radio" id="aid_to_civ2" name="aid_to_civ" value="NO" >
											  <label for="No">No</label>
											  </div>
											</div>
										</div>
														
										<div class="col-md-6" id="diagnosisotdiv" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Diagnosis Others</label>
												</div>
												<div class="col-md-8" >						
													<input type="text" id="diagnosis_others" maxlength="50" onkeypress="return onlyAlphabets(event);"
														name="diagnosis_others" class="form-control autocomplete"
														autocomplete="off" >
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong>Whether NOK Informed or Not</label>
												</div>	
											<div class="col-md-8" onclick="isInformed();">
											 <input type="radio" id="nok_informed1" name="nok_informed" value="YES" >
											  <label for="Yes">Yes</label>
											  <input type="radio" id="nok_informed2" name="nok_informed" value="NO" >
											  <label for="No">No</label>
											  </div>
											</div>
										</div>
										<div class="col-md-6" id="dt_infodiv" style="display: none">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label">Date of Informing</label>
													</div>
													<div class="col-md-8">
															<input type="text" name="date_of_informing" id="date_of_informing" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
															onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
															onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
													</div>
												</div>
											</div>
										
									</div>
									<div class="col-md-12" id="timeandmd_infodiv" style="display: none">
											
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label">Time of Informing</label>
													</div>
													<div class="col-md-8">						
														<input type="time" id="time_of_informing"
															name="time_of_informing" class="form-control autocomplete" autocomplete="off">
													</div>
												</div>
											</div>
											<div class="col-md-6">
											<div class="row form-group">
											<div class="col-md-4">
													<label class=" form-control-label">Method of Informing</label>
												</div>	
											<div class="col-md-8" >
											<input type="text" id="methodofinforming" maxlength="100" onkeypress="return onlyAlphabets(event);"
															name="methodofinforming" class="form-control autocomplete" 
															autocomplete="off">
											  </div>
											</div>
										</div>
										</div>
												 
							
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Category of Casualty</label>
										</div>
										<div class="col-md-8">
											<select name="cause_of_casuality" id="cause_of_casuality"
												class="form-control" onchange="getDescList(this); onCauseOfCasuality(this)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCauseOfCasualityList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
			                 </div> 
			                 <div id="batnpc_battle_desc" style="display:none;width: -webkit-fill-available;">
									<div class="col-md-12" id="battle_descdiv" >
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Description</label>
												</div>
												<div class="col-md-8">
													<select name="battle_desc" id="battle_desc"
														class="form-control" onchange="onBattleDesc(this)">
														<option value="0">--Select--</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="battle_desc_othersdiv" style="display:none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Others</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="battle_desc_others" maxlength="50" onkeypress="return onlyAlphabets(event);"
														name="battle_desc_others" class="form-control autocomplete"
														autocomplete="off">
												</div>
											</div>
										</div>
									</div> 
									<div class="col-md-12" id="battleothersdiv" style="display:none;">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Others</label>
												</div>
												<div class="col-md-8">						
													<input type="text" id="others_battle" maxlength="50" onkeypress="return onlyAlphabets(event);"
														name="others_battle" class="form-control autocomplete"
														autocomplete="off">
												</div>
											</div>
										</div>
									</div>
								</div> 
									<div id="batnpc_physical_desc" style="display:none;width: -webkit-fill-available;">
											<div class="col-md-12" id="physical_descdiv">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label">Description</label>
														</div>
														<div class="col-md-8">
															<select name="physical_desc" id="physical_desc"
																class="form-control" onchange="onphysicalDesc(this)">
																<option value="0">--Select--</option>
																
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-6" id="physical_desc_othersdiv" style="display:none">
													<div class="row form-group" >
														<div class="col-md-4">
															<label class=" form-control-label">Others</label>
														</div>
														<div class="col-md-8">						
															<input type="text" id="physical_desc_others" maxlength="50" onkeypress="return onlyAlphabets(event);"
																name="physical_desc_others" class="form-control autocomplete"
																autocomplete="off">
														</div>
													</div>
												</div>
											</div> 
											<div class="col-md-12" id="physicalothersdiv" style="display:none">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label">Others</label>
														</div>
														<div class="col-md-8">						
															<input type="text" id="others_physical" maxlength="50" onkeypress="return onlyAlphabets(event);"
																name="others_physical" class="form-control autocomplete"
																autocomplete="off">
														</div>
													</div>
												</div>
											</div>
											</div>
											
														
<!-- 																			<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>Geographical Location</strong></div> -->
																					
							</div>
								<div class="card-footer" align="center" class="form-control"> 
									<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="return validate_batnpc_save();">		              
							    </div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END BTLE & PHY ---->
	<!-- START DISCIPLINE -->
	<form id="form_disipline" style="display: none">
		<div class="card">
			<div class="panel-group" id="accordion44">
				<div class="panel panel-default" id="w_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="w_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion44" href="#" id="w_final" onclick="divN(this)"
								><b>UPDATE Discipline</b></a>
						</h4>
					</div>
					<div id="collapse1w" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br>
						
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('discipline');" />
							<div class="row">
							<%-- 	 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Army No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="army_no1" name="army_no" class="form-control autocomplete" autocomplete="off" > 
						                  
						                </div>
						            </div>
	              				</div>	            				
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Rank</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="rank" id="rank_d" class="form-control"   >
														<option value="-1">--Select--</option>
														<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select> 
						                </div>
						            </div>
	              				</div>	          				
	              			</div> --%>
	              			  <div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> *</strong> Authority</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="disi_authority" name="disi_authority" class="form-control autocomplete"
											 autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> *</strong> Date of Authority</label>
										</div>
										<div class="col-md-8">
										<input type="text" name="disi_authority_date" id="disi_authority_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
							</div>
	              				 	              					
	              		<!-- 		<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  Name </label>
						                </div>
						                <div class="col-md-8">
						                      <input type="text" id="name_dis" name="name" class="form-control autocomplete"  autocomplete="off" maxlength="10" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"> 
						                </div>
						            </div>
	              				</div>	 -->            				
	              				<div class="col-md-12">	                 				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Army Act Sec</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="hidden" id="disi_id" name="disi_id" value="0" class="form-control" />
						                <select name="army_act_sec" id="army_act_sec" class="form-control">
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getArmyAct_Sec}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Sub Clause</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="sub_clause" id="sub_clause" class="form-control">
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getSub_Clause}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
	              				</div>
	              			<div class="col-md-12">	 
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Trialed By </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="trialed_by" id="trialed_by" class="form-control">
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getDisc_Trialed}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Punishment Awarded </label>
						                </div>
						                <div class="col-md-8">
						                    <textarea  name="description" id="description1"   onkeypress="return onlyAlphabets(event);"
						                    class="form-control" maxlength="200" autocomplete="off"></textarea> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Entry</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="type_of_entry" id="type_of_entry" class="form-control" onchange="onTypeofEntry();">
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getdisciplinetypeentry}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
										 </div>
						            </div>
						            <div class="row form-group" id="type_of_entry_otherDiv" style="display: none">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Type of Entry Other</label>
						                </div>
						                <div class="col-md-8">						                    
											<input type="text" name="type_of_entry_other" id="type_of_entry_other"  
											style="display: none;" class="form-control" maxlength="10" autocomplete="off"
											 onkeypress="return onlyAlphabets(event);"/>
						                </div>
						            </div>
	              				</div>	
	              				 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>  Award Date </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="award_dt" id="award_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>                				
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete"  autocomplete="off" maxlength="255"> 
						                <input type="hidden" id="dis_sus" name="dis_sus" class="form-control autocomplete" autocomplete="off" maxlength="10">
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              		
							</div>
							  <div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Discipline_save();"> 	
			            	</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END DISCIPLINE -->
	<!-- 	START INTER ARM -->
	<form id="forminspupdate" style="display: none">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="x_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="x_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion21" href="#" id="x_final" onclick="divN(this)"
								><b>INTER ARM/SERVICE TRANSFER</b></a>
						</h4>
					</div>
					<div id="collapse1x" class="panel-collapse collapse">
								<div class="card-body card-block">
								<br>	  
								
								<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('inter_arm_service_transfer');" />           			
	              	  <div class="row">		
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="inter_arm_authority" name="authority" class="form-control autocomplete" 
						                   autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)" > 						                   
											<input type="hidden" id="p_id" name="p_id" class="form-control autocomplete" value="0" autocomplete="off" maxlength="50"> 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" name="date_of_authority" id="inter_arm_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              				</div>
	              			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> From Old Arm/Service </label>
										</div>
										<div class="col-md-8">
										<label class=" form-control-label" id="inter_arm_old_arm_service"></label> 
  											<select name="old_arm_service" id="inter_arm_old_arm_service_val" class="form-control"  style="display: none;" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getParentArmList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 		
										</div>
									</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Old Regt (INF)</label>
						                </div>
						                <div class="col-md-8">
						                <label class=" form-control-label" id="inter_arm_old_regt"></label> 
											<select name="old_regt" id="inter_arm_old_regt_val" class="form-control" style="display: none;"  >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getRegtList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 		
						                </div>
						            </div>
	              				</div>
	              			</div>	              			
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">*</strong> To Present Arm/Service</label>
						                </div>
						                <div class="col-md-8">
						                      <select name="parent_arm_service" id="inter_arm_parent_arm_service" class="form-control"  onchange="chgarm(this,'inter_arm_regt');"  >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getParentArmList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 						                   
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong> New Regt (INF) </label>
						                </div>
						                <div class="col-md-8">
						                     <select name="regt" id="inter_arm_regt" class="form-control"  disabled="disabled">
						                        <option value="0">--Select--</option>
												
											</select> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>	              				
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> With Effect From </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" name="with_effect_from" id="inter_arm_with_effect_from" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div> 
	              	</div> 
	              	<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_inter_arm_transfer_save();"> 	              
			            </div>  
	              			</div>	
					</div>
				</div>
			</div>
		</div>
	</form>
<!-- 	END INTER ARM -->
		<!-- START CHANGE OF COMMISSION DETAIL -->
	    <form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion7">
				<div class="panel panel-default" id="y_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="y_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion7" href="#" id="y_final" onclick="divN(this)"
								><b>SSC TO PERMT COMMISSION</b></a>
						</h4>
					</div>
					<div id="collapse1y" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br>
						<input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('permt_commission');" />
						
                          <div class="row">
			                  <div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Authority</label>
										</div>
										<div class="col-md-8">
										<input type="hidden" id="coc_ch_id" name="coc_ch_id" value="0" class="form-control autocomplete"
										autocomplete="off"  >
											<input type="text" id="coc_authority" name="authority" class="form-control autocomplete"
											 autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> *</strong> Date of Authority</label>
										</div>
										<div class="col-md-8">
										<input type="text" name="date_of_authority" id="coc_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;"> *</strong>New Personal No</label>
								</div>
						 	<div class="col-md-10">
								    <div class="row form-group">
								    <div class="col-md-4">
									<select id="persnl_no1" name="persnl_no1" class="form-control">
										<option value="-1">--Select--</option>
									<option value="NR" name="NR">NR</option>
									<option value="NS" name="NS">NS</option>
									</select>
									</div>
									<div class="col-md-4">
									<input type="text" id="persnl_no2" name="persnl_no2" onkeyup="onChangePerNo(this);" class="form-control" autocomplete="off" placeholder="Enter No..." maxlength="5" onkeypress="return isNumber0_9Only(event)">
								</div>
								<div class="col-md-4">
									<input type="text" id="persnl_no3" name="persnl_no3" class="form-control" autocomplete="off"  maxlength="10" readonly="readonly" >
								</div>
									</div>
								</div> 
					      </div> 
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Previous Commission (Type)</label>
										</div>
										
										    <input type="hidden" id="coc_old_previous_commission" name="coc_old_previous_commission"  class="form-control autocomplete" autocomplete="off" >
											<input type="hidden" id="coc_old_personal_no" name="coc_old_personal_no"  class="form-control autocomplete" autocomplete="off" >													
										
										<div class="col-md-8" id="coc_previous_commission">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> *</strong> Type of Commission Granted</label>
										</div>
										<div class="col-md-8">
											<select name="type_of_commission_granted" id="type_of_commission_granted" class="form-control"   >
																  <option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeOfCommissionList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
													</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Date of Permanent Commission</label>
										</div>
										<div class="col-md-8">
										<input type="text" name="date_of_permanent_commission" id="coc_date_of_permanent_commission" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Date of Seniority</label>
										</div>
										<div class="col-md-8">
										   <input type="text" name="date_of_seniority" id="coc_date_of_seniority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Date From Which Change in Seniority is Effective</label>
										</div>
										<div class="col-md-8">
										   <input type="text" name="eff_date_of_seniority" id="eff_coc_date_of_seniority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
							</div>
                          </div>
                          <div  id = "coc_bt_div"> 
								<div  class="card-footer" align="center" class="form-control" >
									<input id="btnsave" type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_coc_save_fn()">		              
								</div>
								</div>
								 <div class="card-footer" align="left" class="form-control">
	                      <!--  <p style="color: red;"><u><b>Note:</b></u> Previous Type of commission SSC then Submit for approval show. </p>  	 -->
			           </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END CHANGE OF COMMISSION DETAIL -->
	<!-- START EXTENSION OF COMMISSION DETAIL -->
	    <form id="forminspupdate" style="display: none">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="z_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="z_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="z_final" onclick="divN(this)"
								><b>EXTENSION OF SSC</b></a>
						</h4>
					</div>
					<div id="collapse1z" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br>
						
						 <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Extension_of_SSC');" /> 
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Authority</label>
										</div>
										<div class="col-md-8">
										<input type="hidden" id="eoc_ch_id" name="eoc_ch_id" value="0" class="form-control autocomplete"	autocomplete="off">
											<input type="text" id="eoc_authority" name="authority" 
											maxlength="100"  
											class="form-control autocomplete" autocomplete="off"  onkeyup="return specialcharecter(this)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Date of Authority</label>
										</div>
										<div class="col-md-8">
										    <input type="text" name="date_of_authority" id="eoc_authority_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> From</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="fromdt" id="eoc_from" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> To</label>
										</div>
										<div class="col-md-8">
										 <input type="text" name="todt" id="eoc_to" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
										</div>
									</div>
								</div>
							</div>
							</div>
						<div id ="eoc_bt_div">
                        <div class="card-footer" align="center" class="form-control">
							 <input id="btnsave" type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_eoc_save_fn()">		              
			            </div>
			            </div>
			             <div class="card-footer" align="left" class="form-control">
	                   <!--   <p style="color: red;"><u><b>Note:</b></u> Previous Type of commission SSC then Submit for approval show. </p> -->  	
			            </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END EXTENSION OF COMMISSION DETAIL -->
	<!-- START SECONDMENT DETAIL -->
 <form id="form_secondment" style="display:none">
		<div class="card">
			<div class="panel-group" id="accordion17">
				<div class="panel panel-default" id="aa_div16">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="aa_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion17" href="#" id="aa_final" onclick="divN(this)"
								><b>SECONDMENT</b></a>
						</h4>
					</div>
					<div id="collapse1aa" class="panel-collapse collapse">
			           <div class="card-body card-block">
			            <br>
			            <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Secondment');" /> 
							<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Authority</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="sec_authority" name="authority" 
						                   maxlength="100" 	onkeyup="return specialcharecter(this)" class="form-control autocomplete" 
						                   autocomplete="off"  > 
						                   <input type="hidden" id="sec_id" name="sec_id" value="0" class="form-control"  autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" name="date_of_authority" id="sec_date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Seconded To</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="seconded_to" id="seconded_to" class="form-control">
												<option value="-1">--Select--</option>
												<c:forEach var="item" items="${getSeconded_To}" varStatus="num">
													<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              			    <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Secondment With Effect From</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" name="secondment_effect" id="secondment_effect" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
							</div>
						<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_secondment();">		              
			            </div>  
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END SECONDMENT DETAIL -->
	 	  <!-- START NON EFFECTIVE DETAIL -->
	    <form id="form_non_effective">
		<div class="card">
			<div class="panel-group" id="accordion41">
				<div class="panel panel-default" id="bb_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="bb_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion41" href="#" id="bb_final" onclick="divN(this)"
								><b>NON EFFECTIVE STATUS</b></a>
						</h4>
					</div>
					<div id="collapse1bb" class="panel-collapse collapse">
			           <div class="card-body card-block">
			            <br>
			            
			              <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Non_Effective_Status');" />
								<div class="row">
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="non_ef_authority" name="non_ef_authority" value="" 
						                  class="form-control"  autocomplete="off" maxlength="100" 	onkeyup="return specialcharecter(this)">	
										  <input type="hidden" id="nf_id" name="nf_id" value="0" class="form-control"  autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Authority</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_authority_non_ef" id="date_of_authority_non_ef" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                   <label for="cause"> <strong style="color: red;">* </strong>Cause of Non Effective <span class="star_design"></span></label>
						                </div>
						                <div class="col-md-8">
						                	<select  id="cause_of_non_effective" name="cause_of_non_effective" class="form-control"   >      <!-- onchange="retire_add_fn(this);" -->
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getOFFR_Non_EffectiveList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              			    <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label for="Date Non"><strong style="color: red;">* </strong> Date of Non Effective <span class="star_design"></span></label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_non_effective" id="date_of_non_effective" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			 	<div class="col-md-12" style="font-size: 15px;">	
					           		<input type="checkbox" id="check_per_address" name="check_per_address" onclick="per_address()">
					               	<label for="text-input" class=" form-control-label" style="color: #dd1a3e; margin-left:10px;">  Same as Permanent Address </label>
					            </div>
					     
	              			<div class="col-md-12">
				           			<label class=" form-control-label"  style="margin-left:10px;"><h4> Address  After Service in Army (Permanent Address)</h4></label>
				          		</div>  
				   
				   <!--  country-->
	              		<div class="col-md-12">	
						<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                <select name="per_home_addr_country" id="perm_home_addr_country" class="form-control"  
					                onchange="fn_perm_home_addr_Country(),onPermHomeAddrCountry()" >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
									</select> 
					                </div>
					            </div>
              				</div>
              				
              				<div class="col-md-6">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE</label>
						                </div>
						                <div class="col-md-8">
						                	<select name="per_home_addr_state" id="perm_home_addr_state" class="form-control"   onchange="fn_prem_domicile_state(),onPermHomeAddrState()">
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              			</div>
	              			</div>
	              			
	              			<!-- //country and STATE OTHERs -->
	              		<div class="col-md-12">	
							  <div class="col-md-6" id = "Ot_perm_hm_addr_con_div" style="display: none;">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER COUNTRY</label>
					                </div>
					                <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_country_other" name="per_home_addr_country_other"  
					                  class="form-control autocomplete" autocomplete="off"  maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6" id = "Ot_perm_hm_addr_state_div" style="display: none;">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER STATE</label>
						                </div>
						               <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_state_other" name="per_home_addr_state_other" 
					                  class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
						            </div>
	              			</div>
	              	</div>
	              	
	              	
	              	<!-- DISTRICT -->
	              				<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT</label>
						                </div>
						                <div class="col-md-8">
						               <select name="per_home_addr_district" id="perm_home_addr_district" class="form-control"  
						                onchange="fn_prem_domicile_district(),onPermHomeAddrDis()">
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>  
										 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="per_home_addr_tehsil" id="perm_home_addr_tehsil" class="form-control" onchange="onPermHomeAddrTeh();"  >
					                		<option value="0">--Select--</option>
											<c:forEach var="item" items="${getMedCityName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              				<!-- //DISTRICT and TEHSIL others -->
	              	<div class="col-md-12">	
						<div class="col-md-6" id = "Ot_perm_hm_addr_dis_div" style="display: none;">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER DISTRICT</label>
					                </div>
					                <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_district_other" name="per_home_addr_district_other"   
					                   class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
					            </div>
              				</div>
              				<div class="col-md-6" id = "Ot_perm_hm_addr_teh_div" style="display: none;">
              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>OTHER TEHSIL</label>
						                </div>
						               <div class="col-md-8">
					                  <input type="text" id="perm_home_addr_tehsil_other" name="per_home_addr_tehsil_other" 
					                   class="form-control autocomplete" autocomplete="off"maxlength="50" onkeypress="return onlyAlphabets(event);">				               
					                </div>
						            </div>
	              			</div>
	              	</div>
	              			
	              		<!-- ------------------- -->		
	              		
	              				<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Village/Town/City</label>
						                </div>
						                <div class="col-md-8">
											<input type="text" id="perm_home_addr_village" name="per_home_addr_village" 
											onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off"  maxlength="50">				               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pin</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="perm_home_addr_pin" name="per_home_addr_pin" 
						                   class="form-control autocomplete" autocomplete="off"  onkeypress="return isNumber0_9Only(event)" maxlength="6"> 						                   
						                </div>
						            </div>
	              				</div>
	              			</div>
	              					<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Nearest Railway Station</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="perm_home_addr_rail" name="per_home_addr_rail" onkeypress="return onlyAlphabets(event);"
						                 class="form-control autocomplete" autocomplete="off" maxlength="100"> 						                     
						                <input type="hidden" id="non_addr_ch_id" name="non_addr_ch_id" class="form-control autocomplete" autocomplete="off" value="0">
						               <input type="hidden" id="addr_pending_ch_id" name="addr_pending_ch_id" class="form-control autocomplete" autocomplete="off" value="0">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-8">
						     <label for="lbl_per_home_addr_rural">
							 <input type="radio" id="perm_home_addr_rural" name="per_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_per_home_addr_urban" >
							 <input type="radio" id="perm_home_addr_urban" name="per_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_per_home_addr_semi_urban">
							 <input type="radio" id="perm_home_addr_semi_urban" name="per_home_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div> 	
	              				</div>
	              		<div class="col-md-12">	  
	            			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Border Area</label>
						                </div>
						                <div class="col-md-8">
						                	 <label for="border_area">
							 <input type="radio" id="per_border_area" name="border_area" value="yes"   >Yes</label> 
					    	 <label for="border_area1"> 
				      		 <input type="radio" id="per_border_area1" name="border_area" value="no" > No</label>
						</div>
						                </div>
						            </div>
	              				</div>
							</div>
						<div class="card-footer" align="center" class="form-control">
		              		 <input type="button" name="save" class="btn btn-primary btn-sm" value="Submit for Approval" onclick=" non_effect_save();" /> 	              
			            </div>  
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END NON EFFECTIVE DETAIL -->

	<!-- START DESERTER -->
	    <form id="forminspupdate">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="dd_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="dd_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="dd_final" onclick="divN(this)"
								><b>DESERTER</b></a>
						</h4>
					</div>
					<div id="collapse1dd" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br>
						
						 <input type="button" name="save" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('Deserter');" /> 
						<div class="row">
								<div class="col-md-12">
					<%-- 	<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Declared / Recovered Deserter</label>
								</div>
								<div class="col-md-8">
								
									<select name="deserter" id="deserter" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDclredRcvrdList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div> --%>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Arms Type</label>
								</div>
								<div class="col-md-8">
								<input type="hidden" id="des_ch_id" name="des_ch_id" value="0" class="form-control autocomplete"	autocomplete="off">
									<select name="arm_type" id="arm_type" class="form-control"  onchange="ArmType();"  >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getARM_TYPE}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
								<div class="col-md-12"  id="div_weapon"  style="display:none ;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Weapon</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="arm_type_weapon" name="arm_type_weapon" class="form-control autocomplete" onkeyup="return specialcharecter(this)"
									 autocomplete="off" maxlength="100" >      <!-- onkeypress="return onlyAlphabets(event);" -->
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
					
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Date of Desertion</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="dt_desertion" id="dt_desertion" maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 85%; display: inline;" onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>

						<div class="col-md-6" id="div_dt_rec"  style="display:none ;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Recovered</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="dt_recovered" id="dt_recovered" maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 85%; display: inline;" onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>
					</div>
					
							<div class="col-md-12" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Cause of Desertion</label>
								</div>
								<div class="col-md-8">
									<select name="desertion_cause" id="desertion_cause" class="form-control" onchange="DesertionCause();" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCauseOfDeserter}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="div_arms_rec"  style="display:none ;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Recovered With Arms or Not</label>
								</div>
								<div class="col-md-8">
									<select name="recovered_arms" id="recovered_arms" class="form-control"  >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getARM_TYPE}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
				<!-- 		<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Class of Indl</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="indl_class" name="indl_class" class="form-control autocomplete" autocomplete="off">	 
								</div>
							</div>
						</div> -->
					</div>
					
							<div id="div_cause" class="col-md-12"  style="display:none ;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Others</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="ot_desertion_cause" name="ot_desertion_cause" class="form-control autocomplete" 
									maxlength="50" onkeypress="return onlyAlphabets(event);" autocomplete="off">
								</div>
							</div>
						</div>
				</div>
					
							</div>
						
                        <div class="card-footer" align="center" class="form-control">
							 <input id="btnsave" type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Deserter_save_fn();">		              
			            </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END DESERTER -->
	<!-- START CSD -->
	<form id="forminspupdate1" style="display:none">
		<div class="card">
			<div class="panel-group" id="accordion8">
				<div class="panel panel-default" id="ee_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="ee_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion8" href="#" id="ee_final" onclick="divN(this)"
								><b>CSD</b></a>
						</h4>
					</div>
					<div id="collapse1ee" class="panel-collapse collapse">
						<div class="card-body card-block">
						<br><input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('canteen_p');" />
						
						<input type="hidden" id="father_name" name="father_name" class="form-control autocomplete" autocomplete="off">
						<input type="hidden" id="mother_name" name="mother_name" class="form-control autocomplete" autocomplete="off">
						<input type="hidden" id="father_dob" name="father_dob" class="form-control autocomplete" autocomplete="off">
						<input type="hidden" id="mother_dob" name="mother_dob" class="form-control autocomplete" autocomplete="off">
						<table id="CSD_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
					<thead style=" color: white; text-align: center;">
						<tr>
							<th style="width: 2%;">Sr No</th>
							<th style="width: 10%;"><strong style="color: red;">* </strong>Category</th>
							<th style="width: 10%;">Name</th>  
							<th style="width: 10%;">Date of Birth</th>  
							<th style="width: 10%;"><strong style="color: red;">* </strong>Type Of Card</th>  
							<th style="width: 10%;"><strong style="color: red;">* </strong>Card No</th>  
							
							<th style="width: 10%;">Action</th>
					   </tr>
					</thead>
				   	<tbody data-spy="scroll" id="CSDtbody" style="min-height: 46px; max-height: 650px; text-align: center;">
						<tr id="tr_csd1">
								<td class="CSD_srno" style="width: 2%;">1</td>
							<td style="width: 10%;">
							 <select  id="relation1" name="relation1" onchange="getNameAndDob(1);" class="form-control autocomplete" >
							    <option value="0">--Select--</option>
								<c:forEach var="item" items="${getCSDCategoryList}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
								</select>
							</td>
							
							<td id="iname1">
							<label id="namelb1"></label>
							<input type="hidden" id="name1" name="name1" class="form-control autocomplete" autocomplete="off"
							maxlength="50" onkeypress="return onlyAlphabets(event);" ></td>
							
							<td id="sname1" style="width: 10%;display: none;">
							 <select  id="Dname1" name="name1" onchange="getDob(1);" class="form-control autocomplete" >
							    <option value="0">--Select--</option>
								</select>
							</td>
							
							<td>
							<label id="date_of_birthlb1"></label>
						 		<input type="hidden" name="date_of_birth1" id="date_of_birth1" maxlength="10"  autocomplete="off" >
							</td>
							<td>
								<label for="type_of_card_grocery1"><input type="radio" class="from-control" id="type_of_card_grocery1" 
								 name= "type_of_card1" value="grocery"> Grocery </label>
								<label for="type_of_card_liquor1"><input type="radio" class="from-control" id="type_of_card_liquor1" name="type_of_card1" value="liquor"> Liquor </label>
							</td> 
							<td><input type="text" id="card_no1" name="card_no1" maxlength="25" class="form-control autocomplete" 
							   autocomplete="off"  onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"></td>
							<td style="display:none;"><input type="text" id="CSD_Card_ch_id1" name="CSD_Card_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
							<td style="width: 10%;">
								<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "CSD_save1" onclick="CSD_save_fn(1);" ><i class="fa fa-save"></i></a>
							    <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "CSD_Card_Add1" onclick="CSD_Card_Add_fn(1);" ><i class="fa fa-plus"></i></a>
							    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "CSD_Card_Remove1" onclick="CSD_Card_Remove_fn(1);"><i class="fa fa-trash"></i></a> 
							</td>
						</tr>
				   </tbody> 
						</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END CSD -->
	<!--  change in TNAI NO start -->
				<form id="form_change_of_tnaino">
					<div class="card">
						<div class="panel-group" id="accordion5">
							<div class="panel panel-default" id="b_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title blue" id="b_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion5" href="#" id="tni_final"
											onclick="divN(this)"><b>CHANGE IN TNAI NO</b></a>
									</h4>
								</div>
								<div id="collapsetn" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br> <input type="button" name="save"
											class="btn btn-primary btn-sm" value="View History"
											onclick="Pop_Up_History('TNAI');" />
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>Authority
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="tnai_authority" name="tnai_authority"
																class="form-control autocomplete" autocomplete="off"
																maxlength="100" onkeyup="return specialcharecter(this)">
															<input type="hidden" id="name_id" name="name_id"
																value="0" class="form-control autocomplete"
																autocomplete="off">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>Date of Authority
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" name="date_of_authority"
																id="tnai_date_of_authority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>TNAI NO
															</label>
														</div>
														<div class="col-md-8">
														 <input type="hidden" id="ch_tnai_id" name="ch_tnai_id" value="0" class="form-control" />
															<input type="text" id="tnaino" name="tnaino"
																class="form-control autocomplete" autocomplete="off">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>Date of Change in Tnai no
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" name="change_in_name_date"
																id="change_in_tnaino_date" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);">
														</div>
													</div>
												</div>


											</div>
										</div>
										<div class="card-footer" align="center" class="form-control">
											<input type="button" class="btn btn-primary btn-sm"
												value="Submit for Approval" onclick="validate_Change_of_tnaino_save_fn();">

										</div>
									</div>
									<!-- 	  <div class="card-footer" align="left" class="form-control">
								<p><u><b>Note:</b></u> Name should be as per Commission Offr Information. </p> 	
			            	</div>  -->
								</div>
							</div>
						</div>
					</div>
				</form>
				<!--  change TNAI NO end -->
</div>
</div>
</div>
<a href="#" class="go-top fa fa-arrow-up" style="display:none"></a>
<script type="text/javascript">

function divN(obj){
	var id = obj.id;
	 var sib_id = $("#"+id).parent().parent().next().attr('id');
	var hasC=$("#"+sib_id).hasClass("show");
		$(".panel-collapse").removeClass("show");
		 $('.droparrow').each(function(i, obj) {
			 $("#"+obj.id).attr("class", "droparrow collapsed");
			 });
	
		
		if (hasC) {	
		$("#"+id).addClass( " collapsed");		 
		}  else {				
			$("#"+sib_id).addClass("show");	
			$("#"+id).removeClass("collapsed");
	    }
		
		if (obj.id == "a_final") {
			if(!hasC){
				getChangeOfRank();
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				get_change_of_name();
			}
		}
		else if (obj.id == "c_final") {
			if(!hasC){
				get_Appointment();
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
				getIdentity_Card();
			}
		}
		else if (obj.id == "e_final") {
			if(!hasC){
				get_religion();
			}
		}
		else if (obj.id == "ta_status_div") {
		     if(!hasC){
		         get_ta_status();
		     }
		}
		else if (obj.id == "f_final") {
			if(!hasC){
				
				$("#spouse_quali_radioDiv").hide();
				getfamily_marriageDetails();
				getSpouseQualificationData();
			}
		}
		else if (obj.id == "g_final") {
			if(!hasC){
				m_children_Details();
				colAdj("m_children_details_table");
			}
		}
		else if (obj.id == "h_final") {
			if(!hasC){
				get_nokaddress_details();
			}
		}
		else if (obj.id == "i_final") {
			if(!hasC){
				get_changeaddress_details();
			}
		}
		else if (obj.id == "j_final") {
			if(!hasC){
				get_cda_acnt_no();
			}
		}
		else if (obj.id == "k_final") {
			if(!hasC){
				get_language_details();
				colAdj("language_table");         
				colAdj("foregin_language_table");
			}
		}
		else if (obj.id == "l_final") {
			if(!hasC){
				getQualifications();
			}
		}
		else if (obj.id == "m_final") {
			if(!hasC){
				get_promo_exam_details();         
				colAdj("promo_exam_table");
			}
		}
		
		else if (obj.id == "p_final") {
			if(!hasC){
				bpet_Details();
				colAdj("bpet_table");
				
			}
		}
		else if (obj.id == "q_final") {
			if(!hasC){
				fire_Details();       
				colAdj("fire_std_table");
			}
		}
		else if (obj.id == "r_final") {
			if(!hasC){
				get_allergic_details();        
				colAdj("allergic_table");
			}
		}
		else if (obj.id == "s_final") {
			if(!hasC){
				//get1BXShapeDetails();
// 				colAdj("s_madtable");
// 				colAdj("h_madtable");
// 				colAdj("a_madtable");
// 				colAdj("p_madtable");
// 				colAdj("e_madtable");
// 				colAdj("c_cmadtable");         
// 				colAdj("c_omadtable");
// 				colAdj("c_pmadtable");
// 				colAdj("c_emadtable");
				
				getsShapeDetails();
				gethShapeDetails();
				getaShapeDetails();
				getpShapeDetails();
				geteShapeDetails();
				getcCopeDetails();
				getoCopeDetails();
				getpCopeDetails();
				geteCopeDetails();
				
				
			}
		}
		else if (obj.id == "t_final") {
			if(!hasC){
				getUPForeign_CountriesDetails(); 
				colAdj("foreign_country_visit");
			}
		}
		else if (obj.id == "u_final") {
			if(!hasC){
				getawardsNmedals();
				colAdj("awardsNmedal_table");
			}
		}
		else if (obj.id == "v_final") {
			if(!hasC){
				get_Battle_and_Physical_Casuality_details(); 
				
			}
		}
		else if (obj.id == "w_final") {
			if(!hasC){
				get_change_of_Discipline();
				
			}
		}
		else if (obj.id == "x_final") {
			if(!hasC){
				getInterArm();
				
			}
		}
		else if (obj.id == "y_final") {
			if(!hasC){
				getcoc();
				
			}
		}
		else if (obj.id == "z_final") {
			if(!hasC){
				geteoc();
				
			}
		}
		else if (obj.id == "aa_final") {
			if(!hasC){
				get_Secondment();
			}
		}
		else if (obj.id == "bb_final") {
			if(!hasC){
				getNon_effective();
				get_non_eff_address_details();
				get_changeaddress_details();
			}
		}
		else if (obj.id == "cc_final") {
			if(!hasC){
				get_army_course_details();  
				colAdj("army_course_table");
			}
		}
		
		else if (obj.id == "dd_final") {
			if(!hasC){
				get_deserter();
			}
		}
		
		else if (obj.id == "ee_final") {
			if(!hasC){
				get_CSD_details();
			}
		}
		else if (obj.id == "tni_final") {
			if(!hasC){
				getChangeOfTnai();
			}
		}
		addMaxLengthToInput(auth_length);
}
</script>

<script>
//*************************************MAIN Personel Number Onchange*****************************//
var curr_marital_status=0;
var serving_status="SERVING";
function personal_number()
{

	$("#submit_data").show();
	if($("input#personnel_no").val().trim()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	else{
		$("#div_update_data").show();
	}

	 var personnel_no = $("input#personnel_no").val();
	//alert("hello---" + personnel_no);
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		 if(j!=""){
	    	$("#comm_id").val(j[0][0]);
	    	//alert("hii--" + j[0][8]);
	    	$('#inter_arm_old_arm_service_val').val(j[0][8]);
			$('#inter_arm_old_arm_service').text($( "#inter_arm_old_arm_service_val option:selected" ).text());
	    	if(j[0][9] != 0){
	    		$('#inter_arm_old_regt_val').val(j[0][9]);
				$('#inter_arm_old_regt').text($( "#inter_arm_old_regt_val option:selected" ).text());
	    	}
	    	else {
	    		$('#inter_arm_old_regt').text("");
	    	}
	    	
			//$("select#inter_arm_old_regt").val(j[0][9]);
	    	var comm_id =j[0][0]; 
	    	 $.post('GetCensusDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    			  $('#census_id').val(k[0][1]); 
	    			  $('#div_cda_acnt_no').show(); 
	    			  curr_marital_status=k[0][13]; 
	    	
                   /*      if(curr_marital_status!=8)
	    			  {
	    				  $("#marr_quali_radioDiv").remove();
	    			  }else{
	    				  $('#marital_eventDiv').hide();
	    			  }
 */

	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  
	    			   $("#cadet_name").text(k[0][2]);
	    			   $('#father_name').val(k[0][19]);
	    			   $("#father_dob").val(ParseDateColumncommission(k[0][20]));
	    			   $('#mother_name').val(k[0][21]);
	    			   $("#mother_dob").val(ParseDateColumncommission(k[0][22]));
	    			 	if(k[0][11]==null){
	    		    		$("#dob").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#dob").text(convertDateFormate(k[0][11]));  
	    		    		 $("#dob_date").val(ParseDateColumncommission(k[0][11]));
	    		    	}
	    			 	$("#comm_date").val(ParseDateColumncommission(k[0][12]));
	    			    $("#marital_status_p").text(k[0][13]);
	    		    	$("#p_rank").text(k[0][3]);
	    		    	$("#hd_p_rank").val(k[0][3]);
	    		    	
	    		    	$("#p_app").text(k[0][4]);
	    		    	if(k[0][5]==null){
	    		    		$("#p_app_dt").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_app_dt").text(convertDateFormate(k[0][5]));  
	    		    	}
	    		    	if(k[0][6]==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0][6]));  
	    		    	}
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0][6]));
	    		    	$("#app_sus_no").text(k[0][7]);
	    		    	$("#p_id_no").text(k[0][8]);
	    		    	$("#p_religion").text(k[0][9]);
	    		    	$("#app_parent_arm").text(k[0][10]);
	    		    	///bisag 030723 v2 (new screen)
	    		    	if(k[0][24] == '20'){
	    		    		$("#form_ta_status").show();
	    		    		}

	    		    	$("#p_cmd").text(k[0][16]);
	    		    	 if(k[0][10] == "GORKHA" || k[0][10] == "INFANTRY"){
	    		 			$("#reg_div").show();
	    		 		}
	    		 	  else
	    		 		  {
	    		 		 $("#reg_div").hide();
	    		 		  }
	    		    	
	    		    	/* $('#inter_arm_regt_val').val(k[0][17]);
	    				$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text()); */
	    		    	if(k[0][17]!=0){
	    		    		$('#inter_arm_regt_val').val(k[0][17]);
	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    	}
	    		    	else{
	    		    		$('#p_regt').text("");
	    		    	}
	    		    	
	    		    	var sus_no =k[0][7];
// 	    		    	$("#dis_sus").val(k[0][7]);
	    		    	
	    		    	getunit(sus_no);
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                //var json = JSON.parse(data);
	    		                //...

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                   //alert("unit---" + dec(enc,j[0]));
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
// 	    		    	                   $("#unit_name").val(dec(enc,j[0]));
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		 }
	   });
	 		$.ajaxSetup({
					async : false
				});
	 		
	    	 $.post('GetServingStatus?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    		    	$("#p_serving_status").text(k[0][2]);
	    		    	if(k[0][2]!=null && k[0][2]!="" && k[0][2]!=undefined)
	    		    		serving_status=k[0][2];
	    		    
	    		    	$.post("getOFFR_Non_EffectiveList?" + key + "=" + value, {
	    		    		serving_status : serving_status
	    				})
	    				.done(
	    						function(j) {

	    							var options = '<option   value="0">' + "--Select--"
	    									+ '</option>';
	    							for (var i = 0; i < j.length; i++) {

	    								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	    										+ j[i][1] + '</option>';

	    							}

	    							$("select#cause_of_non_effective").html(options);
	    							
	    						}).fail(function(xhr, textStatus, errorThrown) {
	    				});
	    		 }
	   });
	 }
  }); 
	 $("input#personnel_no").attr('readonly', true);
}
$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApprovedForMns?"+key+"="+value,
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
});

function fn_getUnitnamefromSus(sus_no,e){
	
	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		var l=data.length-1;
		  var enc = data[l].substring(0,16);    	   	 
	 		e.value=dec(enc,data[0]);
		}).fail(function(xhr, textStatus, errorThrown) {
});
}

$("input#issue_authority").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#issue_authority");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getIssueingAuthList?"+key+"="+value,
		        data: {unit_name:unit_name},
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
		        	  alert("Please Enter Approved Issue Authority");
		        	  document.getElementById("issue_authority").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#issue_authority_sus").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 		     
					
});
//*************************************END Personel Number Onchange*****************************//
</script>
<script>
//*************************************Start Children Details*****************************//



		////Children Add
		chi = 1;
		chi_srno = 1;
		function m_children_details_add_fn(q) {
			if ($('#m_children_details_add' + q).length) {
				$("#m_children_details_add" + q).hide();
			}
			if (q != 0)
			chi_srno += 1;
			chi = q + 1;
			$("table#m_children_details_table").append('<tr id="tr_children'+chi+'">'
				+ '<td class="child_srno">'
				+ chi_srno
				+ '</td>'
				+ '<td style="width: 10%;"> <input type="sib_name'+chi+'" id="sib_name'+chi+'" class="form-control"  autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"></td>'
				+ '<td style="width: 10%;">'
				+' <input type="text" name="sib_date_of_birth'+chi+'" id="sib_date_of_birth'+chi+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				+ '<td style="width: 10%;"> <input type="checkbox" id="sib_type'+chi+'" name="sib_type'+chi+'" value="Yes">' 
				+ '</td>'
				+ '<td style="width: 10%;"> <select name="sib_relationship'+chi+'" id="sib_relationship'+chi+'" class="form-control"   >'
				+ '<option value="0">--Select--</option>'
				+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
				+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
				+ '</c:forEach></select></td>'
				+ '<td style="width: 10%;"> <input type="checkbox" id="sib_adopted'+chi+'" name="sib_adopted'+chi+'" value="Yes" onclick="adoption('+chi+ ');"></td>'
				+ '<td style="width: 10%;">'
				/* <input type="date" id="sib_adop_date'+chi+'" name="sib_adop_date'+chi+'" class="form-control autocomplete" autocomplete="off"  maxlength="10"  max="${date}" min="1899-01-01" readonly="readonly"></td>' */
				+' <input type="text" name="sib_adop_date'+chi+'" id="sib_adop_date'+chi+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" readonly="readonly" style="width: 85%;display: inline;" '
				+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
				+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
				+ '</td>'
				+'<td style="width: 10%;">'
				+'<input type="text" id="aadhar_no'+chi+'" name="aadhar_no'+chi+'"  class="form-control autocomplete" autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);">'
				+'</td>'
				+'<td style="width: 10%;">'
				+'<input type="text" id="pan_no'+chi+'" name="pan_no'+chi+'"  class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">'
				+'</td>'
				+ '<td style="display:none;"><input type="text" id="sib_ch_id'+chi+'" name="sib_ch_id'+chi+'"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
				
				+ '<td style="width: 5%;"><input type="checkbox" ' + '	id="child-ex' + chi + '" name="child-ex' + chi + '" ' + '	value="Yes"  ' + '	onclick="childCB(' + chi + ');"></td> '
				+ '<td style="width: 10%;"> <select name="child_service' + chi + '" id="child_service' + chi + '" class="form-control-sm form-control"  onchange="otherfunction(' + chi + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '  maxlength="15"	id="child_personal_no' + chi + '" name="child_personal_no' + chi + '" ' + '	class="form-control" autocomplete="off" ></td> '
				+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + chi + '" name="other_child_ser' + chi + '" ' + '	class="form-control" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
				
				+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "m_children_details_save'
				+ chi
				+ '" onclick="m_children_details_save_fn('
				+ chi
				+ ');" ><i class="fa fa-save"></i></a>'
				+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "m_children_details_add'
				+ chi
				+ '" onclick="m_children_details_add_fn('
				+ chi
				+ ');" ><i class="fa fa-plus"></i></a>'
				+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "m_children_details_remove'
				+ chi
				+ '" onclick="m_children_details_remove_fn('
				+ chi
				+ ');"><i class="fa fa-trash"></i></a>'
				+ '</td></tr>');
			datepicketDate('sib_date_of_birth'+chi);
			datepicketDate('sib_adop_date'+chi);
			childCB(chi);
			otherfunction(chi);
		}
		//remove
		function m_children_details_remove_fn(R){
			if (R == '1') {
				alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
			}
			else{
			var rc = confirm("Are You Sure! You Want To Delete");
			 if(rc){
			var sib_ch_id=$('#sib_ch_id'+R).val();
			  $.post('m_children_details_delete_action?' + key + "=" + value, {sib_ch_id:sib_ch_id }, function(data){ 
					  
					   if(data=='1'){
						   $("tr#tr_children"+R).remove(); 
							 if(R==chi){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#m_children_details_add'+i).length )         
								 {
									 $("#m_children_details_add"+i).show();
									 temp=false;
									 chi=i;
									 break;
								 }}
								 
								 if(temp){
									 m_children_details_add_fn(0);
									}
					  			 }
							 $('.child_srno').each(function(i, obj) {
									obj.innerHTML=i+1;
									chi_srno=i+1;
									 });
									 alert("Data Deleted Successfully");
					   }
						 else{
								 
							 alert("Data not Deleted ");
						 }
					   
			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			  		});
			 }
			}
		}
		//save children
		function m_children_details_save_fn(ps) {
			
			var sib_name = $('#sib_name' + ps).val();
			var sib_date_of_birth = $('#sib_date_of_birth' + ps).val();
			//var sib_type = $('#sib_type' + ps).val();
			if ($('#sib_type' + ps).is(":checked")){
				sib_type = "Yes";
			}
			else{
				sib_type = "No";
			}
			
			
			var aadhar_no = $('#aadhar_no' + ps).val().split('-').join('');
			var pan_no = $('#pan_no' + ps).val();
			
			var sib_ch_id = $('#sib_ch_id' + ps).val();
			var comm_id = $('#comm_id').val();
			var census_id = $('#census_id').val();
			
			var child_ser = $("select#child_service"+ps).val();
			var child_pers_no = $("#child_personal_no"+ps).val();
			if($('#child-ex' + ps).is(":checked")){
				var child_ex = "Yes";
			}else{
				var child_ex = "";
			}
			var other_child_ser = $("#other_child_ser"+ps).val();
			
			 if (sib_name.trim()=="") {
					alert("Please Enter Name");
					$('#sib_name' + ps).focus();
					return false;
				}
			  if (sib_date_of_birth == "DD/MM/YYYY" || sib_date_of_birth.trim()=="") {
					alert("Please Enter Date of Birth");
					$('#sib_date_of_birth' + ps).focus();
					return false;
				} 
			  var currentdate=new Date();
				if(getformatedDate(sib_date_of_birth) > currentdate){
					   alert("Enter Valid Date of Birth ");			  
					   $("input#sib_date_of_birth"+ps).focus();
					   return false;
					   
					}
				if(getformatedDate(sib_date_of_birth) <= getformatedDate($('#dob_date').val())){
					alert("Date of Birth of Child Should Not Greater Than Date of Birth His Father or Mother");
					$("input#sib_date_of_birth"+ps).focus();
					return false;
				}
				
				 var sib_relationship = $('#sib_relationship' + ps).val();
			 if (sib_relationship == 0) {
					alert("Please Select Relationship");
					$('#sib_relationship' + ps).focus();
					return false;
				}
			
				var sib_adop_date = $('#sib_adop_date' + ps).val();
				if ($('#sib_adopted' + ps).is(":checked")){
					sib_adopted = "Yes";
					  if (sib_adop_date == "DD/MM/YYYY" || sib_adop_date.trim()=="") {
							alert("Please Enter Adoption Date");
							$('#sib_adop_date' + ps).focus();
							return false;
						} 
					  if(getformatedDate(sib_date_of_birth) > getformatedDate(sib_adop_date)){
						   alert("Adoption Date Should Be Greater Or Equal To Date of Birth ");			  
						   $("input#sib_adop_date"+ps).focus();
						   return false;
						   
						}
				}
				else{
					sib_adopted = "No";
				}
				 if (aadhar_no.trim()!="" && aadhar_no.length < 12) {
						alert("Please Enter Valid Aadhaar Card No");
						$('#aadhar_no' + ps).val("");
						$('#aadhar_no' + ps).focus();
						return false;
					}
			 
				
			
				var child = $( "#child_service"+ps+" option:selected" ).text();
				if($('#child-ex' + ps).is(":checked")){
					if(child_ser == 0){
						alert("Please Select Child Service");
						$("select#child_service"+ps).focus();
						return false;
					}
					if(child_ser == 1){
					if(child_pers_no.trim()==""){
						alert("Please Enter Child Personal No");
						$("#child_personal_no"+ps).focus();
						return false;
					}
					}
					if(child_pers_no.trim()!=''){
						if(child_pers_no.trim().length < 5 || child_pers_no.trim().length >15){
						alert("Please Enter Valid Child Personal No");
						$("#child_personal_no"+ps).focus();
						return false;
						}
					}
					if (child == "OTHER"){
							if($('#other_child_ser' + ps).val().trim()==""){
								alert("Please Enter Other Child Service");
							$('#other_child_ser' + ps).focus();
							return false;
							}
						}
				}	
				else{
					child="";	
				}
	

			$.post('m_children_details_action?' + key + "=" + value, {
				sib_name : sib_name,
				sib_date_of_birth : sib_date_of_birth,sib_type:sib_type,
				sib_relationship : sib_relationship,sib_adopted:sib_adopted,sib_adop_date:sib_adop_date,aadhar_no:aadhar_no,pan_no:pan_no,
				sib_ch_id : sib_ch_id,
				comm_id : comm_id,census_id:census_id,
				child_ser:child_ser,child_pers_no:child_pers_no,child_ex:child_ex,other_child_ser:other_child_ser,child:child
			}, function(data) {

				if (data == "update")
					alert("Data Updated Successfully");
				else if (parseInt(data) > 0) {
					$('#sib_ch_id' + ps).val(data);
					$("#m_children_details_add" + ps).show();
					$("#m_children_details_remove" + ps).show();
					alert("Data Saved Successfully")
				} else
					alert(data);
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
			});
		}
		
		//get children
			
	function m_children_Details() {
			var p_id = $('#census_id').val();
			var comm_id = $('#comm_id').val();
			var i = 0;
			$.post('getm_children_detailsData?' + key + "=" + value,{p_id : p_id,comm_id:comm_id},function(j) {
				var v = j.length;
				if (v != 0) {
					$('#m_children_detailstbody').empty();
					for (i; i < v; i++) {
						x = i + 1;
						var dob = ParseDateColumncommission(j[i].date_of_birth);
						if(j[i].date_of_adpoted != null){
							var adob = ParseDateColumncommission(j[i].date_of_adpoted);
							}
							else
								var adob ="";
						$("table#m_children_details_table").append('<tr id="tr_children'+x+'">'
							+ '<td class="child_srno">'
							+ x
							+ '</td>'
							+ '<td style="width: 10%;"> <input type="sib_name'+x+'" id="sib_name'+x+'"  value="'+j[i].name+'" class="form-control"  maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">'
							+ '<td style="width: 10%;">'
							+' <input type="text" name="sib_date_of_birth'+x+'" id="sib_date_of_birth'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+dob+'">'
							+ '</td>'
							+ '<td style="width: 10%;"> <input type="checkbox" id="sib_type'+x+'" name="sib_type'+x+'" >'
							+ '</td>'
							+ '<td style="width: 10%;"> <select name="sib_relationship'+x+'" id="sib_relationship'+x+'" class="form-control"   >'
							+ '<option value="0">--Select--</option>'
							+ '<c:forEach var="item" items="${getChild_RelationshipList}" varStatus="num">'
							+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
							+ '</c:forEach></select></td>'
							+ '<td style="width: 10%;"> <input type="checkbox" id="sib_adopted'+x+'" name="sib_adopted'+x+'" value="Yes" onclick="adoption('+ x+ ');"></td>'
							+ '<td style="width: 10%;">'
							/* <input type="date" id="sib_adop_date'+x+'" name="sib_adop_date'+x+'" value="'+adob+'" class="form-control autocomplete" autocomplete="off"  maxlength="10"  max="${date}" min="1899-01-01" readonly="readonly"></td>' */
							+' <input type="text" name="sib_adop_date'+x+'" id="sib_adop_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" readonly="readonly" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+adob+'">'
							+ '</td>'
							+'<td style="width: 10%;">'
							+'<input type="text" id="aadhar_no'+x+'" name="aadhar_no'+x+'" value="'+j[i].aadhar_no+'" class="form-control autocomplete" autocomplete="off" maxlength="14" onkeypress="return isAadhar(this,event);">'
							+'</td>'
							+'<td style="width: 10%;">'
							+'<input type="text" id="pan_no'+x+'" name="pan_no'+x+'"  value="'+j[i].pan_no+'" class="form-control autocomplete" autocomplete="off" maxlength="10" onchange="isPAN(this);" oninput="this.value = this.value.toUpperCase()">'
							+'</td>'
							+ '<td style="display:none;"><input type="text" id="sib_ch_id'+x+'" name="sib_ch_id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'

							+ '<td style="width: 5%;"><input type="checkbox" ' + '	id="child-ex' + x + '" name="child-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="childCB(' + x + ');"></td> '
							+ '<td style="width: 10%;"> <select name="child_service' + x + '" id="child_service' + x + '" class="form-control-sm form-control"  onchange="otherfunction(' + x + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '	maxlength="15" id="child_personal_no' + x + '" name="child_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].child_personal_no + '" ></td> '
							+ '<td style="width: 10%;"><input type="text" ' + '	id="other_child_ser' + x + '" name="other_child_ser' + x + '" ' + '	class="form-control" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
							
							
							+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "m_children_details_save'+ x + '" onclick="m_children_details_save_fn('+ x+ ');" ><i class="fa fa-save"></i></a>'
							+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "m_children_details_add'+ x+ '" onclick="m_children_details_add_fn('+ x+ ');" ><i class="fa fa-plus"></i></a>'
                          + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "m_children_details_remove'+ x+ '" onclick="m_children_details_remove_fn('+ x + ');"><i class="fa fa-trash"></i></a>'
							+ '<td style="display:none;"><input type="text" id="status'+x+'" name="status'+x+'"   value="'+j[i].status+'"  class="form-control autocomplete" autocomplete="off" ></td>'
							+ '</td></tr>');
						$('#sib_relationship' + x).val(j[i].relationship);
						$('#sib_type' + x).val(j[i].type);
						$('#child_service' + x).val(j[i].child_service);
						$('#other_child_ser' + x).val(j[i].other_child_ser);
						if(j[i].if_child_ser =="Yes"){
							$("input[type=checkbox][name='child-ex"+x+"']").prop("checked",true);
						}
						if(j[i].type =="Yes"){
							$("input[type=checkbox][name='sib_type"+x+"']").prop("checked",true);
						}
						
						if(j[i].adoted =="Yes"){
							$("input[type=checkbox][name='sib_adopted"+x+"']").prop("checked",true);
							$('#sib_adop_date' + x).attr('readonly', false);
						}
						datepicketDate('sib_date_of_birth'+x);
						datepicketDate('sib_adop_date'+x);
						isAadhar(document.getElementById('aadhar_no'+x))
						childCB(x);
						otherfunction(x);
						
					}
					chi = v;
					chi_srno = v;
					$('#m_children_details_add' + chi).show();
				}
			});
		}
		function childCB(a) {
			if ($('#child-ex'+a).is(":checked"))
			{
				$('#child_service' + a).attr('readonly', false);
				$('#child_personal_no' + a).attr('readonly', false);
			}
			else{
//		 		$('#ser-ex' + a).attr('readonly', true);
				$('#child_service' + a).attr('readonly', true);
				$('#child_personal_no' + a).attr('readonly', true);
				$('#other_child_ser' + a).attr('readonly', true);
				$('#child_service' + a).val(0);
				$('#child_personal_no' + a).val("");
				$('#other_child_ser' + a).val("");
			}
			
		}
		function otherfunction(k){
			//26-01-1994
			var child = $( "#child_service"+k+" option:selected" ).text();
			if ($( "#child_service"+k ).val() == "0" || !($('#child-ex'+k).is(":checked"))){
				$('#other_child_ser' + k).attr('readonly', true);
				$('#child_personal_no' + k).attr('readonly', true);
				$('#child_personal_no' + k).val('');
			}
			else if (child == "OTHER"){
				$('#other_child_ser' + k).attr('readonly', false);
				$('#child_personal_no' + k).attr('readonly', true);
				$('#child_personal_no' + k).val('');
			}
			else if (child != "OTHER"){
				$('#other_child_ser' + k).attr('readonly', true);
				$('#child_personal_no' + k).attr('readonly', false);
				$('#other_child_ser' + k).val('');
			}
			
		}
		
		function adoption(a) {
// 			var nk=$("input[type=checkbox][name='sib_adopted"+a+"']").val();
// 			//var nk =$('#sib_adopted' + a).val();
// 			if(nk = "Yes"){
// 				//alert("11111");
// 				$('#sib_adop_date' + a).attr('readonly', false);
// 			}
// 			else{
// 				$('#sib_adop_date' + a).attr('readonly', true);
// 			}
			if ($('#sib_adopted'+a).is(":checked"))
			{
				$('#sib_adop_date' + a).attr('readonly', false);
			}
			else{
				$('#sib_adop_date' + a).attr('readonly', true);
				$('#sib_adop_date' + a).val("");
			}
		}
		//*************************************End Children Details*****************************//
</script>
<script>
//*************************************Start CDA ACCOUNT NO & CONTACT DETAIL*****************************//
function isNumber0_9Only(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}
function validate_cda_acnt_no()
{
	 if ($("input#gmail").val().trim()=="") {
			alert("Please Enter Gmail ");
			$("input#gmail").focus();
			return false;
		}
	 if(!validateEmail(document.getElementById('gmail'))){
		 $("input#gmail").focus();
			return false;
	 }
	 if ($("input#nic_mail").val().trim()=="") {
			alert("Please Enter NIC Mail ");
			$("input#nic_mail").focus();
			return false;
		}
	 if(!validateEmail(document.getElementById('nic_mail'))){
		 $("input#nic_mail").focus();
			return false;
	 }
	 if ($("input#mobile_no").val().trim()=="" || $("input#mobile_no").val().trim().length<10 || $("input#mobile_no").val().trim().length>10) {
			alert("Please Enter Mobile No ");
			$("input#mobile_no").focus();
			return false;
		}
	    var formvalues=$("#form_cda_acnt_no").serialize();
		var census_id=$("#census_id").val();	
		var cda_id=$("#cda_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&census_id="+census_id+"&cda_id="+cda_id+"&comm_id="+comm_id;
		 $.post('cda_acnt_noaction?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#cda_id").val(data);	
		        	  alert("Data Saved Successfully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}

function get_cda_acnt_no(){
	var census_id=$("#census_id").val();
	var comm_id=$("#comm_id").val();
	$.post('cda_acnt_no_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id}, function(j){
		var v=j.length;
		if(v!=0){
			$("#cda_account_no").val(j[0].cda_account_no);
			$("#gmail").val(j[0].gmail);
			$("#nic_mail").val(j[0].nic_mail);
			$("#mobile_no").val(j[0].mobile_no);
			$("#cda_id").val(j[0].id);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}


//*************************************end CDA ACCOUNT NO & CONTACT DETAIL*****************************//
</script>
<script>	
//************************************* START NON EFFECTIVE DETAIL*****************************//
function getNon_effective(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var hd_rank=$('#hd_p_rank').val();
	 var i=0;
	 var lengthV=0;
	 $.ajaxSetup({
         async : false
 });
	  $.post('getNon_effective?' + key + "=" + value, {p_id:p_id,comm_id:comm_id }, function(j){
			var v=j.length;
			lengthV=v;
			if(v!=0){
				$('#nf_id').val(j[i].id);
				$("#check_per_address").prop("checked", true);
				 $("#non_ef_authority").val(j[i].non_ef_authority);
				 $("#date_of_authority_non_ef").val(ParseDateColumncommission(j[i].date_of_authority_non_ef));
				 $("#cause_of_non_effective").val(j[i].cause_of_non_effective);
				 $("#date_of_non_effective").val(ParseDateColumncommission(j[i].date_of_non_effective));
			}
	  });
/* 	  if(lengthV==0){
	  $.post('getNon_effective_date?' + key + "=" + value, {comm_id:comm_id,hd_rank:hd_rank }, function(j){
			var v=j.length;
			if(v!=0){
				if(j[0][7]!=null){
					$("#date_of_non_effective").val(ParseDateColumncommission(j[0][7]));
				}
				else{
					$("#date_of_non_effective").val("");
				}
				
				 
			}
	  });
	  } */
}

function get_non_eff_address_details(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();
	
	if($('#nf_id').val()!="" && $('#nf_id').val()!="0"){
	$.post('address_details_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id }, function(j){
		var v=j.length;
		if(v!=0){
			$("#perm_home_addr_country").val(j[0].permanent_country);
			fn_perm_home_addr_Country();
			$("#perm_home_addr_state").val(j[0].permanent_state);
			fn_prem_domicile_state();
			$("#perm_home_addr_district").val(j[0].permanent_district);
			fn_prem_domicile_district();
			$("#perm_home_addr_village").val(j[0].permanent_village);
			$("#perm_home_addr_tehsil").val(j[0].permanent_tehsil);
			$("#perm_home_addr_pin").val(j[0].permanent_pin_code);
			$("#perm_home_addr_rail").val(j[0].permanent_near_railway_station);
			 var permanent= j[0].permanent_rural_urban_semi;
            if(permanent == "rural"){
                    $("#perm_home_addr_rural").prop("checked", true);
             } 
            if(permanent == "urban"){
                    $("#perm_home_addr_urban").prop("checked", true);
             }
            if(permanent == "semi_urban")
            {
                    $("#perm_home_addr_semi_urban").prop("checked", true);
             }  
            var br= j[0].permanent_border_area;
            if(br == "yes"){
                    $("#per_border_area").prop("checked", true);
             } 
            if(br == "no"){
                    $("#per_border_area1").prop("checked", true);
             } 
          
            $("#perm_home_addr_country_other").val(j[0].per_home_addr_country_other);
			$("#perm_home_addr_state_other").val(j[0].per_home_addr_state_other);
			$("#perm_home_addr_district_other").val(j[0].per_home_addr_district_other);
			$("#perm_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			
			onPermHomeAddrCountry();
				onPermHomeAddrState();
				onPermHomeAddrDis();
				onPermHomeAddrTeh();
			$("#non_addr_ch_id").val(j[0].id);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
}


function non_effect_save(){
	
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var nf_id=$('#nf_id').val();
	var comm_date=$('#comm_date').val(); 
	 
	var formdata=$('#form_non_effective').serialize();
	 
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
 
	if ($("input#non_ef_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#non_ef_authority").focus();
			return false;
		}
		 if ($("input#date_of_authority_non_ef").val().trim()=="" || $("input#date_of_authority_non_ef").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Authority ");
			$("input#date_of_authority_non_ef").focus();
			return false;
		} 
		 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#date_of_authority_non_ef").val())) {
				alert("Authority Date should be Greater than Commission Date");
				return false;
		  }

		if ($("select#cause_of_non_effective").val() == 0) {
			alert("Please Select Cause of Non Effective ");
			$("#cause_of_non_effective").focus();
			return false;
		}
		 if ($("input#date_of_non_effective").val().trim()=="" || $("input#date_of_non_effective").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Non Effective ");
			$("input#date_of_non_effective").focus();
			return false;
		} 

		if ($("select#perm_home_addr_country").val() == "0") {
			alert("Please select Country");
			$("select#perm_home_addr_country").focus();
			return false;
		}
		var text9 = $("#perm_home_addr_country option:selected").text();
		if(text9 == "OTHERS" && $("input#perm_home_addr_country_other").val().trim()==""){
			alert("Please Enter Country Other");
			$("input#perm_home_addr_country_other").focus();
			return false;
		}
		if ($("select#perm_home_addr_state").val() == "0") {
			alert("Please Select State");
			$("select#perm_home_addr_state").focus();
			return false;
		}
		var text10 = $("#perm_home_addr_state option:selected").text();
		if(text10 == "OTHERS" && $("input#perm_home_addr_state_other").val().trim()==""){
			alert("Please Enter State Other");
			$("input#perm_home_addr_state_other").focus();
			return false;
		}
		
		if ($("select#perm_home_addr_district").val() == "0") {
			alert("Please Select  District");
			$("select#perm_home_addr_district").focus();
			return false;
		}
		var text11 = $("#perm_home_addr_district option:selected").text();
		if(text11 == "OTHERS" && $("input#perm_home_addr_district_other").val().trim()==""){
			alert("Please Enter District Other");
			$("input#perm_home_addr_district_other").focus();
			return false;
		}
		if ($("select#perm_home_addr_tehsil").val() == "0") {
			alert("Please Enter Tehsil");
			$("select#perm_home_addr_tehsil").focus();
			return false;
		}
		var text12 = $("#perm_home_addr_tehsil option:selected").text();
		if(text12 == "OTHERS" && $("input#perm_home_addr_tehsil_other").val().trim()==""){
			alert("Please Enter Tehsil Other");
			$("input#perm_home_addr_tehsil_other").focus();
			return false;
		}
		if ($("input#perm_home_addr_village").val().trim()=="") {
			alert("Please Enter Village/Town/City");
			$("input#perm_home_addr_village").focus();
			return false;
		}
		if ($("input#perm_home_addr_pin").val().trim()=="") {
			alert("Please Enter Pin");
			$("input#perm_home_addr_pin").focus();
			return false;
		}
		if ($("input#perm_home_addr_pin").val().trim().length!=6) {
			alert("Please Enter Valid Pin");
			$("input#perm_home_addr_pin").focus();
			return false;
		}
		if ($("input#perm_home_addr_rail").val().trim()=="") {
			alert("Please Enter Nearest Railway Station");
			$("input#perm_home_addr_rail").focus();
			return false;
		}
		var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
		if (a == undefined) {
			alert("Please select Is Rural /Urban/Semi Urban ");
			return false;
		}
		var b = $('input:radio[name=border_area]:checked').val();
		if (b == undefined) {
			alert("Please select Border Area ");
			return false;
		}

		$.post('non_effectiveAction?' + key + "=" + value, formdata,
				function(data) {
			//alert(data);
					if (data.error==undefined){
					
						$('#nf_id').val(data.nf_id);
						$('#non_addr_ch_id').val(data.add_id);
						$("#addr_ch_id").val(data.add_id);
						
						if(data.msg!=undefined)
							alert("Data Saved/Update Successfully "+data.msg)
						else
							alert("Data Saved/Update Successfully")
					} else
						alert(data.error);

				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}


function per_address() {
	 
	
	var census_id = $("#census_id").val();
	var comm_id = $("#comm_id").val();
	if ($("#check_per_address").prop('checked') == true) {
		//$("#perm_add_div").show();
		$.post('getPerAddressDataStatus1?' + key + "=" + value, {
			census_id : census_id,
			comm_id : comm_id
		}, function(j) {
			if (j != "") {
	 
				$("#perm_home_addr_country").val(j[0].permanent_country);
				fn_perm_home_addr_Country();
				$("#perm_home_addr_state").val(j[0].permanent_state);
				fn_prem_domicile_state();
				$("#perm_home_addr_district").val(j[0].permanent_district);
				fn_prem_domicile_district();
				$("#perm_home_addr_village").val(j[0].permanent_village);
				$("#perm_home_addr_tehsil").val(j[0].permanent_tehsil);
				$("#perm_home_addr_pin").val(j[0].permanent_pin_code);
				$("#perm_home_addr_rail").val(j[0].permanent_near_railway_station);
				
				if(j[0].per_home_addr_country_other!=null )
					$("#perm_home_addr_country_other").val(j[0].per_home_addr_country_other);
					
				if(j[0].per_home_addr_state_other!=null)
					$("#perm_home_addr_state_other").val(j[0].per_home_addr_state_other);
				if(j[0].per_home_addr_district_other!=null)
					$("#perm_home_addr_district_other").val(j[0].per_home_addr_district_other);	
				if(j[0].per_home_addr_tehsil_other!=null)
					$("#perm_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			
				var permanent = j[0].permanent_rural_urban_semi;
				if (permanent == "rural") {
					$("#perm_home_addr_rural").prop("checked", true);
				}
				if (permanent == "urban") {
					$("#perm_home_addr_urban").prop("checked", true);
				}
				if (permanent == "semi_urban") {
					$("#perm_home_addr_semi_urban").prop("checked", true);
				}
				var br = j[0].permanent_border_area;
				if (br == "yes") {
					$("#per_border_area").prop("checked", true);
				}
				if (br == "no") {
					$("#per_border_area1").prop("checked", true);
				}
			}
			onPermHomeAddrCountry();
			onPermHomeAddrState();
			onPermHomeAddrDis();
			onPermHomeAddrTeh();
			 
		});
	}
	else{
		$("#perm_home_addr_country").val(0);
		fn_perm_home_addr_Country();
		$("#perm_home_addr_state").val(0);
		fn_prem_domicile_state();
		$("#perm_home_addr_district").val(0);
		fn_prem_domicile_district();
		$("#perm_home_addr_village").val('');
		$("#perm_home_addr_tehsil").val(0);
		$("#perm_home_addr_pin").val('');
		$("#perm_home_addr_rail").val('');
		//26-01-1994
		$("#perm_home_addr_rural").prop("checked", false);
		$("#perm_home_addr_urban").prop("checked", false);
		$("#perm_home_addr_semi_urban").prop("checked", false);
		$("#per_border_area").prop("checked", false);
		$("#per_border_area1").prop("checked", false);
		
		onPermHomeAddrCountry();
		onPermHomeAddrState();
		onPermHomeAddrDis();
		onPermHomeAddrTeh();
	}

}
	
	function fn_prem_domicile_state() {
		var state_id = $('select#perm_home_addr_state').val();

		$
				.post("getDistrictListFormState1?" + key + "=" + value, {
					state_id : state_id
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';

							}

							$("select#perm_home_addr_district").html(options);
							fn_prem_domicile_district();
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	//janki
		function onPermHomeAddrCountry() {
		var perm_home_addr_country = $("#perm_home_addr_country option:selected").text();
		 
		if(perm_home_addr_country == "OTHERS"){
			$("#Ot_perm_hm_addr_con_div").show();
			
		}
		else{
			$("#Ot_perm_hm_addr_con_div").hide();
			$("#perm_home_addr_country_other").val("");
		 	
		}
	}

	function onPermHomeAddrState() {

		var perm_home_addr_state = $("#perm_home_addr_state option:selected").text();
		
		if(perm_home_addr_state == "OTHERS"){
			$("#Ot_perm_hm_addr_state_div").show();
			
		}
		else{
			$("#Ot_perm_hm_addr_state_div").hide();
			$("#perm_home_addr_state_other").val("");
		}
	}	
	function onPermHomeAddrDis() {
		var per_home_addr_district2 = $("#perm_home_addr_district option:selected").text();
		
		if(per_home_addr_district2 == "OTHERS"){
			
			$("#Ot_perm_hm_addr_dis_div").show();
		}
		else{
			$("#Ot_perm_hm_addr_dis_div").hide();
			$("#perm_home_addr_district_other").val("");
		 	
		}
		onPermHomeAddrTeh();
	}	
	function onPermHomeAddrTeh() {

		var perm_home_addr_tehsil = $("#perm_home_addr_tehsil option:selected").text();
		if(perm_home_addr_tehsil == "OTHERS"){
			$("#Ot_perm_hm_addr_teh_div").show();
			
		}
		else{
			$("#Ot_perm_hm_addr_teh_div").hide();
			$("#perm_home_addr_tehsil_other").val("");
		 	
		}
	}
	////////////////////////////////////
	
	
	function fn_prem_domicile_district() {
		var Dist_id = $('select#perm_home_addr_district').val();

		$
				.post("getTehsilListFormDistrict1?" + key + "=" + value, {
					Dist_id : Dist_id
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';

							}

							$("select#perm_home_addr_tehsil").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
				});
		onPermHomeAddrCountry();
		onPermHomeAddrState();
		onPermHomeAddrDis();
		onPermHomeAddrTeh();
	}

	function fn_perm_home_addr_Country() {
		var contry_id = $('select#perm_home_addr_country').val();

		$
				.post("getStateListFormcon1?" + key + "=" + value, {
					contry_id : contry_id
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';

							}

							$("select#perm_home_addr_state").html(options);
							fn_prem_domicile_state();
							
							onPermHomeAddrCountry();
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	//************************************* End NON EFFECTIVE DETAIL*****************************//
</script>
<script>
//************************************* START RELIGION DETAIL*****************************//
function fn_other() {
	var text = $("#religion option:selected").text();
	if(text == "OTHERS"){
		$("#other_div").show();
	}
	else{
		$("#other_div").hide();
		$("#religion_other").val("");
	}
}

function validate_Religion_save_fn(){
	
	if ($("input#rel_authority").val().trim()=="") {
		alert("Please Enter Authority");
		$("input#rel_authority").focus();
		return false;
	}
 if ($("input#rel_date_of_authority").val() == "DD/MM/YYYY" || $("input#rel_date_of_authority").val().trim()=="") {
		alert("Please Enter Date of Authority ");
		$("input#rel_date_of_authority").focus();
		return false;
	}
 if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#rel_date_of_authority").val())) {
		alert("Authority Date should be Greater than Commission Date");
		$("input#rel_date_of_authority").focus();
		return false;
}
 if ($("select#religion").val() == "0") {
		alert("Please Select Religion ");
		$("#religion").focus();
		return false;
	}
 if ($("input#change_in_rel_date").val() == "DD/MM/YYYY" || $("input#change_in_rel_date").val().trim()=="") {
		alert("Please Enter Date ");
		$("input#change_in_rel_date").focus();
		return false;
	}
	
	
var change_in_rel_date=$('#change_in_rel_date').val();


  var rel_ot=$('#religion_other').val();
  var rel_o = $("#religion option:selected").text();
	if (rel_o == "OTHERS" && rel_ot.trim()=="") {
		alert("Please Enter Other Religion");
		$("#religion_other").focus();
		return false;
	} 
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var religion=$('#religion').val();
	var rel_id=$('#rel_id').val();
	var comm_date=$('#comm_date').val();
	var formdata=$('#form_religion').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
	   $.post('religion_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#rel_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}

function get_religion(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('religion_GetData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id}, function(j){
			var v=j.length;
			
			if(v!=0){
				$('#rel_id').val(j[i].id);
				 $("#rel_authority").val(j[i].authority);
				 $("#rel_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#religion").val(j[i].religion);
				 $("#change_in_rel_date").val(ParseDateColumncommission(j[i].change_in_rel_date));
					var text = $("#religion option:selected").text();
					if(text == "OTHERS"){
						$('#religion_other').val(j[0].other);
						$("#other_div").show();
					}
					else{
						$("#other_div").hide();
					}
			}
	  });
}	
//************************************* End RELIGION DETAIL*****************************//
</script>

<script>
//************************************* START CHANGE OF NAME DETAIL*****************************//
function get_change_of_name(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val();
	 var i=0;
	  $.post('change_of_name_GetData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#name_id').val(j[i].id);
				 $("#na_authority").val(j[i].authority);
				 $("#na_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#name").val(j[i].name);
				 $("#change_in_name_date").val(ParseDateColumncommission(j[i].change_in_name_date));      
			}
	  });
}	

function validate_Change_of_name_save_fn(){
	 if ($("input#na_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#na_authority").focus();
			return false;
		}
	 	 if ($("input#na_date_of_authority").val() == "DD/MM/YYYY" || $("input#na_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#na_date_of_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#na_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 	if ($("input#name").val().trim()=="") {
			alert("Please Enter Name ");
			$("input#name").focus();
			return false;
		}

	 	if ($("input#change_in_name_date").val() == "DD/MM/YYYY" || $("input#change_in_name_date").val().trim()=="") {
	 				alert("Please Select Date of Change in Name ");
	 				$("input#change_in_name_date").focus();
	 				return false;
	 			} 
	 			
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var name=$('#name').val();
	var name_id=$('#name_id').val();
	var comm_date=$('#comm_date').val();
	var change_in_name_date=$('#change_in_name_date').val();
	var formdata=$('#form_change_of_name').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
	   $.post('change_of_nameaction?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#name_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************end Change of name*****************************//
</script>

<script>
//*************************************START Change of RANK*****************************//

function validate_ChngofRank_save(){
	
	
	debugger;
	  if ($("#r_authority").val().trim()=="") {
			alert("Please Enter Authority.");
			$("#r_authority").focus();
			return false;
	 }
	 if ($("#r_date_of_authority").val() == "DD/MM/YYYY" || $("#r_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority.");
			$("#r_date_of_authority").focus();
			return false;
	 } 
	 
     if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#r_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			$("#r_date_of_authority").focus();
			return false;
	  }
		
	 if ($("#rank_type").val() == "0") {
			alert("Please Select Rank Type.");
			$("#rank_type").focus();
			return false;
	 } 
	 if ($("#rank").val() == "0") {
			alert("Please Select Rank.");
			$("#rank").focus();
			return false;
	 } 
	 if ($("#date_of_rank").val() == "DD/MM/YYYY" || $("#date_of_rank").val().trim()=="") {
			alert("Please Enter Date of Rank.");
			$("#date_of_rank").focus();
			return false;
	 }  
	 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#date_of_rank").val())) {
			alert("Date of Rank should be greater Or equal to Commissioning Date");
			$("#date_of_rank").focus();
			return false;
	  }
	var authority=$('#r_authority').val();
	var date_of_authority=$('#r_date_of_authority').val();
	var rank_type=$('#rank_type').val();
	var rank=$('#rank').val();
	var comm_date=$('#comm_date').val();
	var date_of_rank=$('#date_of_rank').val();
	var ch_r_id=$('#ch_r_id').val();
	var census_id_cr=$('#census_id').val();
	var comm_id_cr=$('#comm_id').val();
	
	   $.post('Change_of_Rank_action?' + key + "=" + value, {authority:authority,date_of_authority:date_of_authority,
		   rank_type:rank_type,rank:rank,date_of_rank:date_of_rank,comm_date:comm_date,census_id_cr:census_id_cr,comm_id_cr:comm_id_cr,ch_r_id:ch_r_id}, function(data){
		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#ch_r_id').val(data);
	        	  alert("Data Saved Successfully")
	          } else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			alert("fail to fetch")
	  		});
}

function getChangeOfRank(){
var ch_id=$('#census_id').val(); 
var comm_id=$('#comm_id').val();
var i=0;
 $.post('getChangeOfRankData?' + key + "=" + value, {ch_id:ch_id,comm_id:comm_id}, function(j){
	 if(j!=""){
		 $('#ch_r_id').val(j[0].id);
			$('#r_authority').val(j[0].authority);
			var dtA =ParseDateColumncommission(j[0].date_of_authority);		
			$('#r_date_of_authority').val(dtA);	
			$('#rank_type').val( j[0].rank_type );		
			 $('#rank').val( j[0].rank );	
			var dtR =ParseDateColumncommission(j[0].date_of_rank);
			$('#date_of_rank').val(dtR);	
	 }
 });
}  
//*************************************END Change of RANK*****************************//
</script>

<script>
//*************************************START AWARD AND MEDAL *****************//

//awardsNmedal

anm=1;
anm_srno=1;
function awardsNmedal_add_fn(q){
    if( $('#awardsNmedal_add'+q).length )         
    {
                   $("#awardsNmedal_add"+q).hide();
    }
    anm=q+1;
    
    if(q!=0)
            anm_srno+=1;
   
   $("table#awardsNmedal_table").append('<tr id="tr_awardsNmedal'+anm+'">'
   +'<td class="anm_srno">'+anm_srno+'</td>'
   +'<td style="width: 10%;">'
   +'<input type="text" id="awardsNmedal_authority'+anm+'" name="awardsNmedal_authority'+anm+'" 	onkeyup="return specialcharecter(this)" class="form-control autocomplete" autocomplete="off" maxlength="100" ></td>'
  /*  +'<td style="width: 10%;"><input type="date" id="awardsNmedal_date_of_authority'+anm+'" name="awardsNmedal_date_of_authority'+anm+'" class="form-control autocomplete" autocomplete="off" maxlength="10" min="1899-01-01" max="${date}">'
   +'</td>' */
	+'<td style="width: 10%;">' 
	+' <input type="text" name="awardsNmedal_date_of_authority'+anm+'" id="awardsNmedal_date_of_authority'+anm+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
	+ '</td>'
   +'<td style="width: 10%;"><select name="Category_8'+anm+'" id="Category_8'+anm+'" onchange="getMedalDescList(this)" class="form-control"><option value="0">--Select--</option>'
   +'<c:forEach var="item" items="${getMedalList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
   +'<td style="width: 10%;"><select name="select_desc'+anm+'" id="select_desc'+anm+'" class="form-control"><option value="0">--Select--</option></select>'
	+'<td style="width: 10%;">' 
	+' <input type="text" name="date_of_award'+anm+'" id="date_of_award'+anm+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
	+ '</td>'
   
   +'<td style="width: 10%;"><input type="text" id="awardsNmedal_unit'+anm+'" name="awardsNmedal_unit'+anm+'" onkeypress="unitData(this)" class="form-control autocomplete" autocomplete="off" maxlength="255"></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_bde'+anm+'" id="awardsNmedal_bde'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_div_subarea'+anm+'" id="awardsNmedal_div_subarea'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_DivList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_corps_area'+anm+'" id="awardsNmedal_corps_area'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
   +'<td style="width: 10%;"><select name="awardsNmedal_command'+anm+'" id="awardsNmedal_command'+anm+'"  class="form-control">'
   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">'
   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'                
                                                  
  
   +'<td style="display:none;"><input type="text" id="awardsNmedal_ch_id'+anm+'" name="awardsNmedal_ch_id'+anm+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
   +'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "awardsNmedal_save'+anm+'" onclick="awardsNmedal_save_fn('+anm+');" ><i class="fa fa-save"></i></a>'
   +'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "awardsNmedal_add'+anm+'" onclick="awardsNmedal_add_fn('+anm+');" ><i class="fa fa-plus"></i></a>'
   +'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "awardsNmedal_remove'+anm+'" onclick="awardsNmedal_remove_fn('+anm+');"><i class="fa fa-trash"></i></a>' 
   +'</td></tr>');
   
   datepicketDate('awardsNmedal_date_of_authority'+anm);
   datepicketDate('date_of_award'+anm);
}

function awardsNmedal_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
        var rc = confirm("Are You Sure! You Want To Delete");
         if(rc){
         var awardsNmedal_ch_id=$('#awardsNmedal_ch_id'+R).val();
          $.post('awardsNmedal_delete_action?' + key + "=" + value, {awardsNmedal_ch_id:awardsNmedal_ch_id }, function(data){ 
                           if(data=='1'){
                                         $("tr#tr_awardsNmedal"+R).remove(); 
                                         if(R==anm){
                                                 R = R-1;
                                                 var temp=true;
                                                 for(i=R;i>=1;i--){
                                                 if( $('#awardsNmedal_add'+i).length )         
                                                 {
                                                         $("#awardsNmedal_add"+i).show();
                                                         temp=false;
                                                         anm=i;
                                                         break;
                                                 }}
                                                 if(temp){
                                                         awardsNmedal_add_fn(0);
                                                        }
                                         }
                                         $('.anm_srno').each(function(i, obj) {
                                                 
                                                        obj.innerHTML=i+1;
                                                        anm_srno=i+1;
                                                         });
                                                         alert("Data Deleted Successfully");
                           }
                                 else{
                                         alert("Data not Deleted ");
                                 }
                           
                                   }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                          });
         }
	}
}
function awardsNmedal_save_fn(qs){
                var Category_8=$('#Category_8'+qs).val();
                var select_desc=$('#select_desc'+qs).val();
                var date_of_award=$('#date_of_award'+qs).val();
                var awardsNmedal_unit=$('#awardsNmedal_unit'+qs).val();
                var awardsNmedal_bde=$('#awardsNmedal_bde'+qs).val();
                var awardsNmedal_div_subarea=$('#awardsNmedal_div_subarea'+qs).val();
                var awardsNmedal_corps_area=$('#awardsNmedal_corps_area'+qs).val();
                var awardsNmedal_command=$('#awardsNmedal_command'+qs).val();
                var awardsNmedal_ch_id=$('#awardsNmedal_ch_id'+qs).val();
                var q_id=$('#census_id').val();
                var com_id=$('#comm_id').val();
                var anm_authority=$('#awardsNmedal_authority'+qs).val();
                var anm_doa=$('#awardsNmedal_date_of_authority'+qs).val();
                if (anm_authority.trim()=="") {
                    alert("Please Enter Authority");
                    $('#awardsNmedal_authority'+qs).focus();
                    return false;
               }
                if (anm_doa.trim()=="" || anm_doa == "DD/MM/YYYY") {
                        alert("Please Enter Date of Authority");
                        $("input#awardsNmedal_date_of_authority"+qs).focus();
                        return false;
                } 
                
                if (Category_8 == "0") {
                    alert("Please Select Category");
                    $("#Category_8"+qs).focus();
                    return false;
                 }
                if (select_desc == "0") {
                    alert("Please Select Award/Medal");
                    $("#select_desc"+qs).focus();
                    return false;
                 }
                if (date_of_award.trim()=="" || date_of_award == "DD/MM/YYYY") {
                        alert("Please Enter Date of Award");
                        $("#date_of_award"+qs).focus();
                        return false;
                } 
              
                if (awardsNmedal_unit.trim()=="") {
                        alert("Please Enter Unit");
                        $("#awardsNmedal_unit"+qs).focus();
                        return false;
                }
                /* if (awardsNmedal_bde == "0") {
                    alert("Please Select BDE");
                    $("#awardsNmedal_bde"+qs).focus();
                    return false;
                }
                if (awardsNmedal_div_subarea == "0") {
                    alert("Please Select DIV/Sub Area");
                    $("#awardsNmedal_div_subarea"+qs).focus();
                    return false;
                 }
            
                if (awardsNmedal_corps_area == "0") {
                    alert("Please Select Corps/Area");
                    $("#awardsNmedal_corps_area"+qs).focus();
                    return false;
                 } */
                if (awardsNmedal_command == "0") {
                    alert("Please Select Command");
                    $("#awardsNmedal_command"+qs).focus();
                    return false;
                }
          
          
             
                $.post('awardsNmedal_action?' + key + "=" + value, {Category_8:Category_8,select_desc:select_desc,date_of_award:date_of_award,awardsNmedal_unit:awardsNmedal_unit,awardsNmedal_bde:awardsNmedal_bde,awardsNmedal_div_subarea:awardsNmedal_div_subarea,awardsNmedal_corps_area:awardsNmedal_corps_area,awardsNmedal_command:awardsNmedal_command,awardsNmedal_ch_id:awardsNmedal_ch_id,q_id:q_id,com_id:com_id,anm_authority:anm_authority,anm_doa:anm_doa }, function(data){           
                          if(data=="update")
                                  alert("Data Updated Successfully");
                          else if(parseInt(data)>0){
                                 $("#awardsNmedal_ch_id"+qs).val(data);
                                 $("#awardsNmedal_add"+qs).show();
                                 $("#awardsNmedal_remove"+qs).show();
                                  alert("Data Saved Successfully")
                          }
                          else
                                  alert(data)
                                           }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                                  });
}
function getawardsNmedals(){
    var q_id=$('#census_id').val(); 
    var comm_id=$('#comm_id').val();
    var i=0;
     $.post('getawardsNmedalData?' + key + "=" + value, {q_id:q_id,comm_id:comm_id}, function(j){
                   var v=j.length;
                   if(v!=0){
                           $('#awardsNmedal_tbody').empty(); 
           for(i;i<v;i++){
                   am=i+1;
                   var dauth=ParseDateColumncommission(j[i].date_of_authority);
                   var doa=ParseDateColumncommission(j[i].date_of_award);
             $("table#awardsNmedal_table").append('<tr id="tr_awardsNmedal'+am+'">'
                                   +'<td class="anm_srno">'+am+'</td>'
                                   +'<td style="width: 10%;">'
                                   +'<input type="text" id="awardsNmedal_authority'+am+'" name="awardsNmedal_authority'+am+'" 	onkeyup="return specialcharecter(this)" value="'+j[i].authority+'"  class="form-control autocomplete" autocomplete="off" maxlength="100"></td>'
                                 	+'<td style="width: 10%;">' 
                					+' <input type="text" name="awardsNmedal_date_of_authority'+am+'" id="awardsNmedal_date_of_authority'+am+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
                					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+dauth+'">'
                					+ '</td>'
                                   +'<td style="width: 10%;"><select name="Category_8'+am+'" id="Category_8'+am+'" onchange="getMedalDescList(this)" class="form-control"><option value="0">--Select--</option>'
                                   +'<c:forEach var="item" items="${getMedalList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
                                   +'<td style="width: 10%;"><select name="select_desc'+am+'" id="select_desc'+am+'" class="form-control"><option value="0">--Select--</option></select>'
		                            +'<td style="width: 10%;">' 
		           					+' <input type="text" name="date_of_award'+am+'" id="date_of_award'+am+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
		           					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		           					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+doa+'">'
		           					+ '</td>'
                                   +'<td style="width: 10%;"><input type="text" id="awardsNmedal_unit'+am+'" name="awardsNmedal_unit'+am+'" value="'+j[i].unit+'" onkeypress="unitData(this)" class="form-control autocomplete" autocomplete="off" maxlength="255"></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_bde'+am+'" id="awardsNmedal_bde'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_BdeList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_div_subarea'+am+'" id="awardsNmedal_div_subarea'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_DivList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_corps_area'+am+'" id="awardsNmedal_corps_area'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CorpsList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
                                   +'<td style="width: 10%;"><select name="awardsNmedal_command'+am+'" id="awardsNmedal_command'+am+'"  class="form-control">'
                                   +'<option value="0">--Select--</option><c:forEach var="item" items="${getPSG_CommandList}" varStatus="num">'
                                   +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'                
                                   
                                   +'<td style="display:none;"><input type="text" id="awardsNmedal_ch_id'+am+'" name="awardsNmedal_ch_id'+am+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
                                   +'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "awardsNmedal_save'+am+'" onclick="awardsNmedal_save_fn('+am+');" ><i class="fa fa-save"></i></a>'
                                   +'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "awardsNmedal_add'+am+'" onclick="awardsNmedal_add_fn('+am+');" ><i class="fa fa-plus"></i></a>'
                                   +'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "awardsNmedal_remove'+am+'" onclick="awardsNmedal_remove_fn('+am+');"><i class="fa fa-trash"></i></a>'
                                   +'</td></tr>');
		             datepicketDate('awardsNmedal_date_of_authority'+am);
		   		     datepicketDate('date_of_award'+am);
              
             $.ajaxSetup({
                           async : false
                   });
             $('#Category_8'+am).val( j[i].category_8 );
             getMedalDescList(document.getElementById('Category_8'+am))
             $('#select_desc'+am).val( j[i].select_desc );
             $('#awardsNmedal_bde'+am).val( j[i].bde );
             $('#awardsNmedal_div_subarea'+am).val( j[i].div_subarea );
             $('#awardsNmedal_corps_area'+am).val( j[i].corps_area );
             $('#awardsNmedal_command'+am).val( j[i].command );
           }
           anm=v;
           anm_srno=v;
           $("#awardsNmedal_add"+anm).show();
           }
     });
} 
//*************************************END AWARD AND MEDAL*********************//
</script>
<script>
//*************************************START LANGUAGE DETAILS*********************//
//language
lang=1;
flang=1;
lang_srno=1;
flang_srno=1;
function language_add_fn(q){
	 if( $('#language_add'+q).length )         
	 {
			$("#language_add"+q).hide();
	 }
	lang=q+1;
	if(q!=0)
		lang_srno+=1;
	$("table#language_table").append('<tr id="tr_lang'+lang+'">'
	+'<td class="lang_srno">'+lang_srno+'</td>'
	+'<td style="width: 10%;">'
	+'<select  id="language'+lang+'" name="language'+lang+'" onchange="onLanguage('+lang+')" class="form-control autocomplete"  >'
	  +' <option value="0">--Select--</option>'
	   +'<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">'
		+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
		+'</c:forEach>'
		+'</select>'
		+'</td>'	
		+'<td style="width: 10%;">'
		+'<input type="text" id="other_language'+lang+'" name="other_language'+lang+'" class="form-control " autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);" ></td>'
	+'<td style="width: 10%;"> '
	+'<select name="lang_std'+lang+'" id="lang_std'+lang+'" onchange="onLanguage_std('+lang+')" class="form-control" >'
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
	+'	</c:forEach>'
	+'</select>'
	+'</td> '
	+'<td style="width: 10%;">'
	+'<input type="text" id="other_lang_std'+lang+'" name="other_lang_std'+lang+'" class="form-control " maxlength="50" onkeypress="return onlyAlphabets(event);" autocomplete="off"  ></td>'
/* 	+'<td style="width: 10%;">'
	+'<input type="text" id="language_authority'+lang+'" name="language_authority'+lang+'" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
	+'<td style="width: 10%;"><input type="date" id="language_date_of_authority'+lang+'"  min="1899-01-01" max="${date}" name="language_date_of_authority'+lang+'" class="form-control autocomplete" autocomplete="off" maxlength="10">'
	+'</td>' */
	+'<td style="display:none;"><input type="text" id="lang_ch_id'+lang+'" name="lang_ch_id'+lang+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "language_save'+lang+'" onclick="language_save_fn('+lang+');" ><i class="fa fa-save"></i></a>'
	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "language_add'+lang+'" onclick="language_add_fn('+lang+');" ><i class="fa fa-plus"></i></a>'
	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "language_remove'+lang+'" onclick="language_remove_fn('+lang+');"><i class="fa fa-trash"></i></a>' 
	+'</td></tr>');
}

function language_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var lang_ch_id=$('#lang_ch_id'+R).val();
	  $.post('update_offr_language_delete_action?' + key + "=" + value, {lang_ch_id:lang_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_lang"+R).remove(); 
							 if(R==lang){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#language_add'+i).length )         
								 {
									 $("#language_add"+i).show();
									 temp=false;
									 lang=i;
									 break;
								 }}
						 
							 if(temp){
								 language_add_fn(0);
								}
			  			 }
							 $('.lang_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									lang_srno=i+1;
									 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}
function language_save_fn(ps){
    var language=$('#language'+ps).val();
    var other_lan=$('#other_language'+ps).val();        
    var lang_std=$('#lang_std'+ps).val();
    var other_lan_std=$('#other_lang_std'+ps).val();
    var lang_ch_id=$('#lang_ch_id'+ps).val();
    var p_id=$('#census_id').val();
    var com_id=$('#comm_id').val();
    var lang_authority=$('#language_authority').val();
    var lang_doa=$('#language_date_of_authority').val();
    var comm_date=$('#comm_date').val();
    
    if (lang_authority.trim()=="" || lang_authority == "DD/MM/YYYY") {
            alert("Please Enter Authority");
            $('#language_authority').focus();
            return false;
    }
    if (lang_doa.trim()=="" || lang_doa == "DD/MM/YYYY") {
            alert("Please Enter Date of Authority");
            $("input#language_date_of_authority").focus();
            return false;
    } 
     if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#language_date_of_authority").val())) {
                    alert("Authority Date should be Greater than Commission Date");
                    return false;
      }
    if (language == "0") {
            alert("Please select Language");
            $("select#language"+ps).focus();
            return false;
    }        
     var country_sel = $("#language"+ps+" option:selected").text();
            if (country_sel == "OTHERS" && other_lan.trim()=="") {
                    alert("Please Enter Other Language");
                    $('#other_language'+ps).focus();
                    return false;
            } 
    if (lang_std == "0") {
            alert("Please select Language Standard");
            $("select#lang_std"+ps).focus();
            return false;
    }
     var country_sel = $("#lang_std"+ps+" option:selected").text();
            if (country_sel == "OTHERS" && other_lan_std.trim()=="") {
                    alert("Please Enter Other Language Standard");
                    $('#other_lang_std'+ps).focus();
                    return false;
            } 
    
      $.post('update_offr_language_detail_action?' + key + "=" + value, {language:language,other_lan:other_lan,lang_std:lang_std,other_lan_std:other_lan_std,lang_ch_id:lang_ch_id,p_id:p_id,com_id:com_id,lang_authority:lang_authority,lang_doa:lang_doa,comm_date:comm_date }, function(data){
              if(data=="update")
                      alert("Data Updated Successfully");
              else if(parseInt(data)>0){
                     $('#lang_ch_id'+ps).val(data);
                     $("#language_add"+ps).show();
                     $("#language_remove"+ps).show();
                      alert("Data Saved Successfully")
              }
              else
                      alert(data)
                               }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                      });
}

function get_language_details(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('update_offr_getlanguagedetailsData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){
				
		xl=0;
		xf=0;
		for(i;i<v;i++){
			if(j[i].language!="0"){
				xl=xl+1;
				if(xl==1){
					$('#langtbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				$("table#language_table").append('<tr id="tr_lang'+xl+'">'
				+'<td class="lang_srno">'+xl+'</td>'
				+'<td style="width: 10%;"> '
				+'<select  id="language'+xl+'" name="language'+xl+'" value="'+j[i].language+'"  onchange="onLanguage('+xl+')" class="form-control autocomplete"  >'
				  +' <option value="0">--Select--</option>'
				   +'<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">'
					+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
					+'</c:forEach>'
					+'</select>'
					+'</td>'
				+'<td style="width: 10%;"> <input type="text" id="other_language'+xl+'" name="other_language'+xl+'" value="'+j[i].other_language+'" readonly class="form-control autocomplete" maxlength="50" onkeypress="return onlyAlphabets(event);" autocomplete="off" ></td>'		
				+'<td style="width: 10%;"> '
				+'<select name="lang_std'+xl+'" id="lang_std'+xl+'"  onchange="onLanguage_std('+xl+')" class="form-control" >'
				+' <option value="0">--Select--</option>'
				+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
				+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
				+'	</c:forEach>'
				+'</select> </td> '
				+'<td style="width: 10%;"> <input type="text" id="other_lang_std'+xl+'" name="other_lang_std'+xl+'" readonly value="'+j[i].other_lang_std+'" class="form-control autocomplete" maxlength="50" onkeypress="return onlyAlphabets(event);"autocomplete="off" ></td>'		
			/* 	+'<td style="width: 10%;">'
				+'<input type="text" id="language_authority'+xl+'" name="language_authority'+xl+'" value="'+j[i].authority+'" class="form-control autocomplete" autocomplete="off" maxlength="50"></td>'
				+'<td style="width: 10%;"><input type="date" id="language_date_of_authority'+xl+'"  min="1899-01-01" max="${date}" value="'+dauth+'" name="language_date_of_authority'+xl+'" class="form-control autocomplete" autocomplete="off" maxlength="10">'
				+'</td>' */
				+'<td style="display:none;"><input type="text" id="lang_ch_id'+xl+'" name="lang_ch_id'+xl+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
				+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "language_save'+xl+'" onclick="language_save_fn('+xl+');" ><i class="fa fa-save"></i></a>'
				+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "language_add'+xl+'" onclick="language_add_fn('+xl+');" ><i class="fa fa-plus"></i></a>'
				+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "language_remove'+xl+'" onclick="language_remove_fn('+xl+');"><i class="fa fa-trash"></i></a>' 
				+'</td></tr>');
				 $('#lang_std'+xl).val(j[i].lang_std );
				 $('#language'+xl).val(j[i].language );
				 onLanguage(xl);
				 onLanguage_std(xl);
				 $('#language_authority').val(j[i].authority );
				 $('#language_date_of_authority').val(dauth );
			}
			else{
				xf=xf+1;
				if(xf==1){
					$('#flangtbody').empty(); 
				}
				$("table#foregin_language_table").append('<tr id="tr_flang'+xf+'">'
						+'<td class="flang_srno">'+xf+'</td>'
						+'<td style="width: 10%;"> <select  id="flanguage'+xf+'" name="flanguage'+xf+'" value="'+j[i].foreign_language+'" onchange="onF_Language('+xf+')" class="form-control autocomplete"  >'
						+'   <option value="0">--Select--</option>'
					    +'	<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">'
						+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'	</c:forEach>'
						+'	</select></td>'
						+'<td style="width: 10%;"> <input type="text" id="f_other_language'+xf+'" name="f_other_language'+xf+'" value="'+j[i].f_other_language+'" class="form-control autocomplete" maxlength="50"  readonly onkeypress="return onlyAlphabets(event);" autocomplete="off" ></td>'		
						+'<td style="width: 10%;"> '
						+'<select name="flang_std'+xf+'" id="flang_std'+xf+'"  onchange="onF_Language_std('+xf+')"  class="form-control" >'
						+' <option value="0">--Select--</option>'
						+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
						+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
						+'	</c:forEach>'
						+'</select> </td> '
						+'<td style="width: 10%;"> <input type="text" id="f_other_lang_std'+xf+'" name="f_other_lang_std'+xf+'" value="'+j[i].f_other_lang_std+'" class="form-control autocomplete" readonly maxlength="50" onkeypress="return onlyAlphabets(event);"autocomplete="off" ></td>'		
						+'<td style="width: 10%;"> <select name="lang_prof'+xf+'" id="lang_prof'+xf+'" onchange="onF_Language_prof('+xf+')"  class="form-control"   >'
						+' <option value="0">--Select--</option>'
						+'	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">'
						+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'	</c:forEach>'
						+' </select></td> '
						+'<td style="width: 10%;"> <input type="text" id="f_other_prof'+xf+'" name="f_other_prof'+xf+'" value="'+j[i].f_other_prof+'" class="form-control autocomplete" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly autocomplete="off" ></td>'			
						+'<td style="width: 10%;"> <input type="text" id="exam_pass'+xf+'" name="exam_pass'+xf+'" value="'+j[i].f_exam_pass+'" onkeypress="return isNumber(event)" class="form-control autocomplete" autocomplete="off" maxlength="4"></td>'		
						+'<td style="display:none;"><input type="text" id="flang_ch_id'+xf+'" name="flang_ch_id'+xf+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "foregin_language_save'+xf+'" onclick="foregin_language_save_fn('+xf+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "foregin_language_add'+xf+'" onclick="foregin_language_add_fn('+xf+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "foregin_language_remove'+xf+'" onclick="foregin_language_remove_fn('+xf+');"><i class="fa fa-trash"></i></a>' 
						+'</td></tr>');
				
				$('#flang_std'+xf).val(j[i].lang_std );
				 $('#lang_prof'+xf).val(j[i].f_lang_prof );    
				 $('#flanguage'+xf).val(j[i].foreign_language );
				 onF_Language(xf);
				 onF_Language_std(xf);
				 onF_Language_prof(xf);
			}
		}
		if(xl!=0){
			lang=xl;
			lang_srno=xl;
			$("#language_add"+xl).show();
		}
		if(xf!=0)
			flang=xf;
			flang_srno=xf;
		$("#foregin_language_add"+xf).show();
			}
	  });
}
function onLanguage(ind){
	var country_sel = $("#language"+ind+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#other_language'+ind).val("");
		$('#other_language'+ind).attr('readonly', true);
	}
	else
		$('#other_language'+ind).attr('readonly', false);
}
function onLanguage_std(indl){
	var country_sel = $("#lang_std"+indl+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#other_lang_std'+indl).val("");
		$('#other_lang_std'+indl).attr('readonly', true);
	}
	else
		$('#other_lang_std'+indl).attr('readonly', false);
}

function onF_Language(ind){
	var country_sel = $("#flanguage"+ind+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#f_other_language'+ind).val("");
		$('#f_other_language'+ind).attr('readonly', true);
	}
	else
		$('#f_other_language'+ind).attr('readonly', false);
}
function onF_Language_std(indl){
	var country_sel = $("#flang_std"+indl+" option:selected").text();
	if(country_sel != "OTHERS"){
		$('#f_other_lang_std'+indl).val("");
		$('#f_other_lang_std'+indl).attr('readonly', true);
	}
	else
		$('#f_other_lang_std'+indl).attr('readonly', false);
}
function onF_Language_prof(indl){

	var country_sel = $("#lang_prof"+indl+" option:selected").text();
	
	if(country_sel != "OTHERS"){
		$('#f_other_prof'+indl).val("");
		$('#f_other_prof'+indl).attr('readonly', true);
	}
	else
		$('#f_other_prof'+indl).attr('readonly', false);
}
////foregin_language
function foregin_language_add_fn(q){
	 if( $('#foregin_language_add'+q).length )       
	 {
			$("#foregin_language_add"+q).hide();
	 }
	flang=q+1;
	if(q!=0)
		flang_srno+=1;
	$("table#foregin_language_table").append('<tr id="tr_flang'+flang+'">'
	+'<td class="flang_srno">'+flang_srno+'</td>'
	+'<td style="width: 10%;"> <select id="flanguage'+flang+'" name="flanguage'+flang+'" onchange="onF_Language('+flang+')" class="form-control autocomplete"  >'
	+'   <option value="0">--Select--</option>'
    +'	<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
	+'	</c:forEach>'
	+'	</select></td>'
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_language'+flang+'" name="f_other_language'+flang+'" class="form-control autocomplete" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly autocomplete="off"  ></td>'
	+'<td style="width: 10%;"> '
	+'<select name="flang_std'+flang+'" id="flang_std'+flang+'" onchange="onF_Language_std('+flang+')" class="form-control" >'
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
	+'	</c:forEach>'
	+'</select> </td> '
	
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_lang_std'+flang+'" name="f_other_lang_std'+flang+'" class="form-control autocomplete" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly autocomplete="off"  ></td>'

	+'<td style="width: 10%;"> <select name="lang_prof'+flang+'" id="lang_prof'+flang+'" onchange="onF_Language_prof('+flang+')" class="form-control"   >'	
	+' <option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">'
	+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
	+'	</c:forEach>'
	+' </select></td> '
	+'<td style="width: 10%;">'
	+'<input type="text" id="f_other_prof'+flang+'" name="f_other_prof'+flang+'" class="form-control autocomplete" maxlength="50" onkeypress="return onlyAlphabets(event);" maxlength="50" readonly autocomplete="off"  ></td>'
	+'<td style="width: 10%;"> <input type="text" id="exam_pass'+flang+'" name="exam_pass'+flang+'" class="form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="return isNumber(event)"></td>'		
	+'<td style="display:none;"><input type="text" id="flang_ch_id'+flang+'" name="flang_ch_id'+flang+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "foregin_language_save'+flang+'" onclick="foregin_language_save_fn('+flang+');" ><i class="fa fa-save"></i></a>'
	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "foregin_language_add'+flang+'" onclick="foregin_language_add_fn('+flang+');" ><i class="fa fa-plus"></i></a>'
	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "foregin_language_remove'+flang+'" onclick="foregin_language_remove_fn('+flang+');"><i class="fa fa-trash"></i></a>' 
	+'</td></tr>');
}

function foregin_language_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var lang_ch_id=$('#flang_ch_id'+R).val();
	  $.post('update_offr_language_delete_action?' + key + "=" + value, {lang_ch_id:lang_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_flang"+R).remove(); 
							 if(R==flang){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#foregin_language_add'+i).length )         
								 {
									 $("#foregin_language_add"+i).show();
									 
									 temp=false;
									 flang=i;
									 break;
								 }}
								 
							 if(temp){
								 foregin_language_add_fn(0);
								}
			  			 }
							 
							 $('.flang_srno').each(function(i, obj) {
									obj.innerHTML=i+1;
									flang_srno=i+1;
									 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}

function foregin_language_save_fn(ps){
	var language=$('#flanguage'+ps).val();
	var f_ot_language=$('#f_other_language'+ps).val();
	var lang_std=$('#flang_std'+ps).val();
	var f_ot_std=$('#f_other_lang_std'+ps).val();
	var lang_prof=$('#lang_prof'+ps).val();
	var f_ot_prof=$('#f_other_prof'+ps).val();

	var exam_pass=$('#exam_pass'+ps).val();
	var lang_authority=$('#language_authority').val();
	var lang_doa=$('#language_date_of_authority').val();
	var lang_ch_id=$('#flang_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	if (lang_authority.trim()=="" || lang_authority == "DD/MM/YYYY") {
		alert("Please Enter Authority");
		$('#language_authority').focus();
		return false;
	}
	if (lang_doa.trim()=="" || lang_doa == "DD/MM/YYYY") {
		alert("Please Enter Date of Authority");
		$("input#language_date_of_authority").focus();
		return false;
	} 
	 if (language == "0") {
			alert("Please select Language");
			$("select#flanguage"+ps).focus();
			return false;
		}
		 var country_sel = $("#flanguage"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && f_ot_language.trim()=="") {
			alert("Please Enter Other Language");
			$('#f_other_language'+ps).focus();
			return false;
		} 
	 if (lang_std == "0") {
			alert("Please select Language standard");
			$("select#flang_std"+ps).focus();
			return false;
		}	
	 var country_sel = $("#flang_std"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && f_ot_std.trim()=="") {
			alert("Please Enter Other Language Standard");
			$('#f_other_lang_std'+ps).focus();
			return false;
		} 
	 if (lang_prof == "0") {
			alert("Please select Language Proficiency");
			$("select#lang_prof"+ps).focus();
			return false;
		}
	 var country_sel = $("#lang_prof"+ps+" option:selected").text();
		if (country_sel == "OTHERS" && f_ot_prof.trim()=="") {
			alert("Please Enter Other Language Proficiency");
			$('#f_other_prof'+ps).focus();
			return false;
		} 
	 if (exam_pass.trim()=="") {
			alert("Please Enter The Exam pass");
			$("input#exam_pass"+ps).focus();
			return false;
		}
	  $.post('update_offr_foreginlanguage_detail_action?' + key + "=" + value, {language:language,lang_prof:lang_prof,f_ot_language:f_ot_language,f_ot_std:f_ot_std,f_ot_prof:f_ot_prof,exam_pass:exam_pass,lang_authority:lang_authority,lang_doa:lang_doa,lang_std:lang_std,lang_ch_id:lang_ch_id,p_id:p_id,com_id:com_id}, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#flang_ch_id'+ps).val(data);
	        	 $("#foregin_language_add"+ps).show();
	        	 $("#foregin_language_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
//*************************************END LANGUAGE DETAILS*********************//
</Script>

<script>
//*************************************Start Secondment DETAIL*****************************//
function get_Secondment(){
		 var p_id=$('#census_id').val(); 
		 var comm_id=$('#comm_id').val(); 
		 var i=0;
		  $.post('get_Secondment?' + key + "=" + value, {p_id:p_id,comm_id:comm_id }, function(j){
				var v=j.length;
				if(v!=0){
					 $('#sec_id').val(j[i].id);
					 $("#sec_authority").val(j[i].authority);
				 	 $("#sec_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
					 $("#seconded_to").val(j[i].seconded_to);
					 $("#secondment_effect").val(ParseDateColumncommission(j[i].secondment_effect));
				}
		  });
	}	
function validate_secondment()
{
        var comm_id=$('#comm_id').val();
        var census_id=$('#census_id').val();
        var status=$('#status').val();
        var sec_id=$('#sec_id').val();
         var comm_date=$('#comm_date').val();

        var formdata=$('#form_secondment').serialize();
        formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;        
        
        if ($("input#sec_authority").val().trim()=="") {
                alert("Please Enter Authority");
                $("input#sec_authority").focus();
                return false;
        } 
        if ($("input#sec_date_of_authority").val().trim()=="" || $("input#sec_date_of_authority").val() == "DD/MM/YYYY") {
                alert("Please Enter Date of Authority");
                $("input#sec_date_of_authority").focus();
                return false;
        } 
    if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#sec_date_of_authority").val())) {
        alert("Authority Date should be Greater than Commission Date");
        return false;
    }

        if ($("select#seconded_to").val() == -1) {
                alert("Please Select Seconded To");
                $("select#seconded_to").focus();
                return false;
        }
        if ($("input#secondment_effect").val().trim()=="" || $("input#secondment_effect").val() == "DD/MM/YYYY") {
                alert("Please Enter Secondment With Effect From");
                $("input#secondment_effect").focus();
                return false;
        }
        if(getformatedDate($("input#comm_date").val()) > getformatedDate($("input#secondment_effect").val())) {
   			alert("Secondment With Effect From Date should be Greater than Commission Date");
   			$("#secondment_effect").focus();
   			return false;
   	  } 
        
        
           $.post('Second_mentAction?' + key + "=" + value, formdata, function(data){ 
        	  
                  if(data=="update")
                          alert("Data Updated Successfully");
                  else if(parseInt(data)>0){
                         $('#sec_id').val(data);
                          alert("Data Saved Successfully")
                  }
                  else
                          alert(data);
                                      
                                   }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                          });        
}
//************************************* End Secondment DETAIL*****************************//
</script>

 <script>
//***************FOREIGN  COUNTRY************//
function onContryVisited(ind){
	var country_sel = $("#country"+ind+" option:selected").text();
	if(country_sel != "Other"){
		$('#foregin_Country_ot'+ind).val("");
		$('#foregin_Country_ot'+ind).attr('readonly', true);
	}
	else
		$('#foregin_Country_ot'+ind).attr('readonly', false);
}

function onPurposeVisited(pup){
	var purpose_sel = $("#purpose_visit"+pup+" option:selected").text();
	if(purpose_sel != "OTHER"){
		$('#purpose_visit_ot'+pup).val("");
		$('#purpose_visit_ot'+pup).attr('readonly', true);
	}
	else
		$('#purpose_visit_ot'+pup).attr('readonly', false);
}

//GET FOREIGN COUNTRY DETAILS
function getUPForeign_CountriesDetails(){
	 var f_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('getUPForeginCountryData?' + key + "=" + value, {f_id:f_id,comm_id:comm_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#foregin_Country_tbody').empty(); 
		for(i;i<v;i++){
			f=i+1;
			  var fd=ParseDateColumncommission(j[i].from_dt);				
			  var td=ParseDateColumncommission(j[i].to_dt);
		  $("table#foreign_country_visit").append('<tr id="tr_foregin_country'+f+'">'
					+'<td class="fcon_srno">'+f+'</td>'
					+'<td style="width: 10%;"><select name="country'+f+'" id="country'+f+'" onchange="onContryVisited('+f+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
					+'<td style="width: 10%;"> <input type="text" id="foregin_Country_ot'+f+'" name="foregin_Country_ot'+f+'"maxlength="50" onkeypress="return onlyAlphabets(event);" value="' + j[i].other_country + '"  class="form-control autocomplete" autocomplete="off" ></td>'
					+'<td style="width: 10%;">' 
					+' <input type="text" name="from_dt'+f+'" id="from_dt'+f+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
					+ '</td>'	
					/* +'<td style="width: 10%;"> <input type="date" id="to_dt'+f+'" name="to_dt'+f+'" min="1899-01-01" max="${date}" value="' + td+ '" onchange="calcDate22('+f+')" class="form-control autocomplete" autocomplete="off" maxlength="10"></td>' */	
					+'<td style="width: 10%;">' 
					+' <input type="text" name="to_dt'+f+'" id="to_dt'+f+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
					+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
					+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
					+ '</td>'
					+'<td style="width: 10%;"> <input type="text" id="period'+f+'" name="period'+f+'" value="' + j[i].period + '" readonly="readonly" class="form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="isNumber0_9Only(event)"></td>'
					+'<td style="width: 10%;"><select name="purpose_visit'+f+'" onchange="onPurposeVisited('+f+')" id="purpose_visit'+f+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
					+'<td style="width: 10%;"> <input type="text" id="purpose_visit_ot'+f+'" name="purpose_visit_ot'+f+'" onkeypress="return onlyAlphaNumeric(event);" value="' + j[i].other_purpose_visit + '"  class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
					+'<td style="display:none;"><input type="text" id="foregin_country_ch_id'+f+'" name="foregin_country_ch_id'+f+'"  value="' + j[i].id + '" value="0" class="form-control autocomplete" autocomplete="off" ></td>'
					+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "country_save'+f+'" onclick="UPforeign_country_save_fn('+f+');" ><i class="fa fa-save"></i></a>'
					+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "country_add'+f+'" onclick="UPforeign_country_add_fn('+f+');" ><i class="fa fa-plus"></i></a>'
					+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "country_remove'+f+'" onclick="UPforeign_country_remove_fn('+f+');"><i class="fa fa-trash"></i></a>' 
					+'</td></tr>');
		  $("#country"+f).val(j[i].country)
		  onContryVisited(f);
		  $("#purpose_visit"+f).val(j[i].purpose_visit)
		  onPurposeVisited(f);
		  datepicketDate('from_dt'+f);
		  datepicketDate('to_dt'+f);
		}
		fcon=v;
		fcon_srno=v;
		$("#country_add"+fcon).show();
			}
	  });
} 
  fcon=1;
  fcon_srno=1;
  //ADD FUNCTION
  function UPforeign_country_add_fn(q){
	  if( $('#country_add'+q).length )       
  	 {
  			$("#country_add"+q).hide();
  	 }
  	 fcon=q+1;
  	if(q!=0)
  		fcon_srno+=1;
  	$("table#foreign_country_visit").append('<tr id="tr_foregin_country'+fcon+'">'
  	+'<td class="fcon_srno">'+fcon_srno+'</td>'
  	+'<td style="width: 10%;"><select name="country'+fcon+'" id="country'+fcon+'" onchange="onContryVisited('+fcon+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
  	+'<td style="width: 10%;"> <input type="text" id="foregin_Country_ot'+fcon+'" name="foregin_Country_ot'+fcon+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
  /* 	+'<td style="width: 10%;"> <input type="date" id="from_dt'+fcon+'" min="1899-01-01" max="${date}" name="from_dt'+fcon+'" onchange="calcDate22('+fcon+')" class="form-control autocomplete" autocomplete="off" maxlength="10"></td>' */	
	+'<td style="width: 10%;">' 
	+' <input type="text" name="from_dt'+fcon+'" id="from_dt'+fcon+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >'
	+ '</td>'
  /* 	+'<td style="width: 10%;"> <input type="date" id="to_dt'+fcon+'" min="1899-01-01" max="${date}" name="to_dt'+fcon+'" onchange="calcDate22('+fcon+')" class="form-control autocomplete" autocomplete="off" maxlength="10"></td>' */	
  	+'<td style="width: 10%;">' 
	+' <input type="text" name="to_dt'+fcon+'" id="to_dt'+fcon+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22('+fcon+');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >'
	+ '</td>'
  	+'<td style="width: 10%;"> <input type="text" id="period'+fcon+'" name="period'+fcon+'" class="form-control autocomplete" readonly="readonly" autocomplete="off" maxlength="4" onkeypress="isNumber0_9Only(event)"></td>'
  	+'<td style="width: 10%;"><select name="purpose_visit'+fcon+'" onchange="onPurposeVisited('+fcon+')" id="purpose_visit'+fcon+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
	+'<td style="width: 10%;"> <input type="text" id="purpose_visit_ot'+fcon+'" onchange="onPurposeVisited('+fcon+')" name="purpose_visit_ot'+fcon+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
  	+'<td style="display:none;"><input type="text" id="foregin_country_ch_id'+fcon+'" name="foregin_country_ch_id'+fcon+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
  	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "country_save'+fcon+'" onclick="UPforeign_country_save_fn('+fcon+');" ><i class="fa fa-save"></i></a>'
  	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "country_add'+fcon+'" onclick="UPforeign_country_add_fn('+fcon+');" ><i class="fa fa-plus"></i></a>'
  	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "country_remove'+fcon+'" onclick="UPforeign_country_remove_fn('+fcon+');"><i class="fa fa-trash"></i></a>' 
  	+'</td></tr>');
  	 datepicketDate('to_dt'+fcon); 
  	 datepicketDate('from_dt'+fcon); 
  	
  }
  //SAVE FOREIGN COUNTRY DETAILS
  function UPforeign_country_save_fn(ps){
		 
		
			var country=$('#country'+ps).val();
			var other_ct=$('#foregin_Country_ot'+ps).val();
			var period=$('#period'+ps).val();
			var from_dt=$('#from_dt'+ps).val();
			var to_dt=$('#to_dt'+ps).val();
			var purpose_visit=$('#purpose_visit'+ps).val();
			var other_purpose_visit=$('#purpose_visit_ot'+ps).val();
			var foregin_country_ch_id=$('#foregin_country_ch_id'+ps).val();
			var f_id=$('#census_id').val();
			var comm_id=$('#comm_id').val();
			if(country == "0") {
				alert("Please Select Country Visited");
				$("select#country" + ps).focus();
				return false;
			}
			
			var country_sel = $("#country"+ps+" option:selected").text();
			if (country_sel == "Other" && other_ct.trim()=="") {
				alert("Please Enter Other Country");
				$('#foregin_Country_ot'+ps).focus();
				return false;
			}
			
			if(from_dt.trim()=="" || from_dt == "DD/MM/YYYY") {
				alert("Please Enter From Date");
				$("input#from_dt" + ps).focus();
				return false;
			}
			if(getformatedDate($("input#dob_date").val()) >= getformatedDate(from_dt)) {
				alert("From Date should be Greater than Date of Birth");
				$("input#from_dt" + ps).focus();
				return false;
			}
			if(to_dt.trim()=="" || to_dt == "DD/MM/YYYY") {
				alert("Please Enter To Date");
				$("input#to_dt" + ps).focus();
				return false;
			}
			var currentdate = new Date();
			if(getformatedDate(to_dt) > currentdate) {
				alert("Enter Valid To Date");
				$("input#to_dt" + ps).focus();
				return false;
			}
			if(getformatedDate(from_dt) > getformatedDate(to_dt)) {
				alert("Invalid Date Range");
				$("input#to_dt" + ps).focus();
				return false;
			}
			if(period.trim()=="") {
				alert("Please Enter Valid From And To Date");
				$("input#period" + ps).focus();
				return false;
			}
			if(purpose_visit.trim()=="0") {
				alert("Please Select Purpose To Visit");
				$("#purpose_visit" + ps).focus();
				return false;
			}
			
			var pup_sel = $("#purpose_visit"+ps+" option:selected").text();
			if (pup_sel == "OTHER" && other_purpose_visit.trim()=="") {
				alert("Please Enter Other Purpose Visit");
				$('#purpose_visit_ot'+ps).focus();
				return false;
			}
			 $.post('UPforegin_country_action?' + key + "=" + value, {country:country,other_ct:other_ct,period:period,
				   from_dt:from_dt,to_dt:to_dt,purpose_visit:purpose_visit,other_purpose_visit:other_purpose_visit,
				   foregin_country_ch_id:foregin_country_ch_id,f_id:f_id,comm_id:comm_id,country_sel:country_sel,pup_sel:pup_sel}, function(data){	 
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $("#foregin_country_ch_id"+ps).val(data);
	        	 $("#country_add"+ps).show();
	        	 $("#country_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	}

	//DELETE FOREIGN COUNTRY
  function UPforeign_country_remove_fn(R){
	  if (R == '1') {
			alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
		}
		else{
  	var rc = confirm("Are You Sure! You Want To Delete");
  	 if(rc){
  	var foregin_country_ch_id=$('#foregin_country_ch_id'+R).val();
  	  $.post('UPforegin_country_delete_action?' + key + "=" + value, {foregin_country_ch_id:foregin_country_ch_id }, function(data){ 
  			   if(data=='1'){
  					 $("tr#tr_foregin_country"+R).remove(); 
  					 if(R==fcon){
  						 R = R-1;
  						 var temp=true;
  						 for(i=R;i>=1;i--){
  						 if( $('#country_add'+i).length )         
  						 {
  							 $("#country_add"+i).show();
  							 temp=false;
  							 fcon=i;
  							 break;
  						 }}
  						 if(temp){
  							 UPforeign_country_add_fn(0);
  							}
  					 }
  					 $('.fcon_srno').each(function(i, obj) {
  							obj.innerHTML=i+1;
  							fcon_srno=i+1;
  							 });
  							 alert("Data Deleted Successfully");
  			   }
  				 else{
  					 alert("Data not Deleted ");
  				 }
  	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
  	  		});
  	 }
		}
  	 }
  function calcDate22(f) {
		if($('#from_dt' + f).val().trim() != "" && $('#from_dt' + f).val() != "DD/MM/YYYY" && $('#to_dt' + f).val().trim() != "" && $('#to_dt' + f).val() != "DD/MM/YYYY") {
			if(getformatedDate($('#from_dt' + f).val()) > getformatedDate($('#to_dt' + f).val())) {
				alert("Invalid Date Range");
				$('#period' + f).val('');
				$("input#to_dt" + f).focus();
				return false;
			}
			var startDate = getformatedDate($('#from_dt' + f).val());
			var endDate = getformatedDate($('#to_dt' + f).val());
			
			
			 var startYear = startDate.getFullYear();
			    var february = (startYear % 4 === 0 && startYear % 100 !== 0) || startYear % 400 === 0 ? 29 : 28;
			    var daysInMonth = [31, february, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

			    var yearDiff = endDate.getFullYear() - startYear;
			    var monthDiff = endDate.getMonth() - startDate.getMonth();
			    if (monthDiff < 0) {
			        yearDiff--;
			        monthDiff += 12;
			    }
			    var dayDiff = endDate.getDate() - startDate.getDate();
			    if (dayDiff < 0) {
			        if (monthDiff > 0) {
			            monthDiff--;
			        } else {
			            yearDiff--;
			            monthDiff = 11;
			        }
			        dayDiff += daysInMonth[startDate.getMonth()];
			    }
		    
			var message;
			   if ( (yearDiff == 0) && (monthDiff == 0) && (dayDiff == 0) )  {
				message = "0 years 0 months 1 days";
			} else {
				message = yearDiff + " years "
				message += monthDiff + " months "
				message += dayDiff + " days"
			}
			$('#period' + f).val(message);
		}
	}
  //*****************END FOREIGN COUNTRY DETAILS************************//
  </script>
  <script>
//*****************START INTER ARM  DETAILS************************//

//  function chgarm(){
// 	 /*  if($("#inter_arm_parent_arm_service").val() == "0700" || $("#inter_arm_parent_arm_service").val() == "0800"){ */
// 		  if($("#inter_arm_parent_arm_service").val() == "0800"){
			
// 			$("#inter_arm_regt").attr('disabled',false);
// 		}
// 	  else
// 		  {
// 		  $("#inter_arm_regt").attr('disabled',true);
// 		  $("#inter_arm_regt").val("0");
// 		  }
	  
//   }
  
function validate_inter_arm_transfer_save(){

	if ($("input#inter_arm_authority").val().trim()=="") {
	    alert("Please Enter Authority");
		$("input#inter_arm_authority").focus();
		return false;
	} 
		 if ($("input#inter_arm_date_of_authority").val().trim()=="" || $("input#inter_arm_date_of_authority").val() == "DD/MM/YYYY") {
		 alert("Please Enter Date of Authority");
			$("input#inter_arm_date_of_authority").focus();
			return false;
		} 
			if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#inter_arm_date_of_authority").val())) {
				alert("Authority Date should be Greater than Commission Date");
				$("input#inter_arm_date_of_authority").focus();
				return false;
		  }
// 		if ($("input#old_arm_service").val().trim()=="") {
// 			 alert("Please Enter Old Arm Services");
// 				$("input#old_arm_service").focus();
// 				return false;
// 			} 
// 		if ($("input#old_regt").val().trim()=="") {
// 			 alert("Please Enter Old Regt");
// 				$("input#old_regt").focus();
// 				return false;
// 			} 
	 	 if ($("select#inter_arm_parent_arm_service").val() == "0") {
		 alert("Please Select To Present Arm/Services");
			$("select#inter_arm_parent_arm_service").focus();
			return false;
		} 
	 	 
// 	 	if ($("select#regt").val() == "0") {
// 			 alert("Please Select Regt");
// 				$("select#regt").focus();
// 				return false;
// 			} 
// 	 	if ($("input#with_effect_from").val().trim()=="") {
// 			 alert("Please Select Date");
// 				$("input#with_effect_from").focus();
// 				return false;
// 			} 
   var inter_arm_regt=$('#inter_arm_regt').val();
 	var inter_arm_parent_arm_serviceV = $("#inter_arm_parent_arm_service"+" option:selected").text();
 	
 	if(($("#inter_arm_parent_arm_service").val() == "0700" || $("#inter_arm_parent_arm_service").val() == "0800") && inter_arm_regt == "0") {
		alert("Please Select Inter Arm Regt");
		$('#inter_arm_regt').focus();
		return false;
	}
 	if ($("input#inter_arm_with_effect_from").val().trim()=="" || $("input#inter_arm_with_effect_from").val() == "DD/MM/YYYY") {
		 alert("Please Enter With Effect From");
			$("input#inter_arm_with_effect_from").focus();
			return false;
		} 
 	 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#inter_arm_with_effect_from").val())) {
			alert("With Effect From Date should be Greater than Commission Date");
			$("#inter_arm_with_effect_from").focus();
			return false;
	  }
 
 	
 	var authority1=$('#inter_arm_authority').val();
 	var date_of_authority1=$('#inter_arm_date_of_authority').val();
 	var old_arm_service1=$('#inter_arm_old_arm_service').val();
 	var old_regt1=$('#inter_arm_old_regt').val();
 	var parent_arm_service1=$('#inter_arm_parent_arm_service').val();
 	var with_effect_from1=$('#inter_arm_with_effect_from').val();
 	var regt1=$('#inter_arm_regt').val();
 	var pre_ch_id=$('#p_id').val();
 	var census_id=$('#census_id').val();
 	var comm_id=$('#comm_id').val();
 
	   $.post('Inter_arm_action?' + key + "=" + value, {authority1:authority1, date_of_authority1:date_of_authority1,
		   old_arm_service1:old_arm_service1,old_regt1:old_regt1,parent_arm_service1:parent_arm_service1,
		   with_effect_from1:with_effect_from1,regt1:regt1,pre_ch_id:pre_ch_id,census_id:census_id,comm_id:comm_id}, function(data){
		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
				$('#p_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch");
 	  		});
}

function getInterArm(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('getInterArm?' + key + "=" + value, {p_id:p_id,comm_id:comm_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#pre_cadeytbody').empty(); 
		for(i;i<v;i++){
			x=i+1;
 			var fd=ParseDateColumncommission(j[i].date_of_authority);				
			var td=ParseDateColumncommission(j[i].with_effect_from);
			$('#inter_arm_parent_arm_service').val(j[i].parent_arm_service);	
			chgarm(document.getElementById("inter_arm_parent_arm_service"),'inter_arm_regt');
			$('#inter_arm_authority').val(j[i].authority);
			$('#inter_arm_date_of_authority').val(fd);
			$('#inter_arm_old_arm_service').val(j[i].old_arm_service);
			$('#inter_arm_old_regt').val(j[i].old_regt);
			$('#inter_arm_with_effect_from').val(td);
 			$('#inter_arm_regt').val(j[i].regt);
			$('#p_id').val(j[i].id);
		}
	}
});
}
//*****************END INTER ARM  DETAILS************************//
</script>

<script>
//***************** START CHANGE OF COMMISSION DETAILS************************//
function validate_coc_save_fn(){
    var authority=$('#coc_authority').val();
    var date_of_authority=$('#coc_date_of_authority').val();
    var persnl_no1=$('#persnl_no1').val();
    var persnl_no2=$('#persnl_no2').val();
    var persnl_no3=$('#persnl_no3').val();
    var date_of_permanent_commission=$('#coc_date_of_permanent_commission').val();
    var previous_commission=$('#coc_old_previous_commission').val();
    var old_personal_no=$('#coc_old_personal_no').val();
    var type_of_commission_granted=$('#type_of_commission_granted').val();
    var date_of_seniority=$('#coc_date_of_seniority').val();
    var eff_date_of_seniority=$('#eff_coc_date_of_seniority').val();
    var coc_ch_id=$('#coc_ch_id').val();
    var cen_id=$('#census_id').val();
    var comm_id=$('#comm_id').val();
    var comm_date=$('#comm_date').val();
    
     if ($("input#coc_authority").val().trim()=="") {
              alert("Please Enter Authority");
                     $("input#coc_authority").focus();
                     return false;
             } 
              if ($("input#coc_date_of_authority").val().trim()=="" || $("input#coc_date_of_authority").val() == "DD/MM/YYYY") {
              alert("Please Enter Date of Authority");
                     $("input#coc_date_of_authority").focus();
                     return false;
             } 
             if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#coc_date_of_authority").val())) {
                     alert("Authority Date should be Greater than Commission Date");
                     return false;
       }
       
             if ($("select#persnl_no1").val() == "-1") {
                     alert("Please Enter New Personal No");
                            $("#persnl_no1").focus();
                            return false;
                    }
             if ($("input#persnl_no2").val().trim()=="") {
                     alert("Please Enter New Personal No");
                            $("input#persnl_no2").focus();
                            return false;
                    }
             
             var result=Personal_no_already_exist(coc_ch_id);
         	if(result == false)
         	{
         		$("select#persnl_no1").focus();
         		return false;
         		
         	} 
              if ($("select#type_of_commission_granted").val() == "0") {
                       alert("Please Select Type of Commission Granted");
                              $("select#type_of_commission_granted").focus();
                              return false;
                     } 
             if ($("input#coc_date_of_permanent_commission").val().trim()=="" || $("input#coc_date_of_permanent_commission").val() == "DD/MM/YYYY") {
                      alert("Please Enter Permanent Commission Date");
                            $("input#coc_date_of_permanent_commission").focus();
                             return false;
                    } 
             if(getformatedDate($("input#comm_date").val()) > getformatedDate($("input#coc_date_of_permanent_commission").val())) {
       			alert("Date of Permanent Commission should be Greater than Commission Date");
       			$("#coc_date_of_permanent_commission").focus();
       			return false;
       	  } 
     
     if ($("input#coc_date_of_seniority").val().trim()=="" || $("input#coc_date_of_seniority").val() == "DD/MM/YYYY") {
                     alert("Please Enter Date Of Seniority");
                             $("input#coc_date_of_seniority").focus();
                            return false;
             } 
                     if ($("input#eff_coc_date_of_seniority").val().trim()=="" || $("input#eff_coc_date_of_seniority").val() == "DD/MM/YYYY") {
                             alert("Please Enter Date From Which Change in Seniority is Effective");
                                     $("input#eff_coc_date_of_seniority").focus();
                                    return false;
                     } 

                      
              
       $.post('coc_action?' + key + "=" + value, {authority:authority,date_of_authority:date_of_authority,persnl_no1:persnl_no1,persnl_no2:persnl_no2,persnl_no3:persnl_no3,
               date_of_permanent_commission:date_of_permanent_commission,previous_commission:previous_commission,type_of_commission_granted:type_of_commission_granted,
               date_of_seniority:date_of_seniority,coc_ch_id:coc_ch_id,cen_id:cen_id,comm_id:comm_id,eff_date_of_seniority:eff_date_of_seniority,comm_date:comm_date,old_personal_no:old_personal_no}, function(data){
                  
              if(data=="update")
                      alert("Data Updated Successfully");
              else if(parseInt(data)>0){
                     $('#coc_ch_id').val(data);
                      alert("Data Saved Successfully")
              }
              else
                      alert(data);
                               }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                      });
}
function getcoc(){
	 var n_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('getcocData?' + key + "=" + value, {n_id:n_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				var authDt=ParseDateColumncommission(j[0].date_of_authority);				
				var commDt=ParseDateColumncommission(j[0].date_of_permanent_commission);
				var senDt=ParseDateColumncommission(j[0].date_of_seniority);
				$('#coc_authority').val(j[0].authority);
				$('#coc_date_of_authority').val(authDt);
				var pr_no=j[0].new_personal_no.split(/\d/)[0];
				$("#persnl_no1").val(pr_no);
				$("#persnl_no2").val(j[0].new_personal_no.substring(pr_no.length, pr_no.length+5));
				$("#persnl_no3").val(j[0].new_personal_no.substr(-1));
				$('#coc_date_of_permanent_commission').val(commDt);
				
				//$('#coc_previous_commission').val(j[0].previous_commission);
				$('#type_of_commission_granted').val(j[0].type_of_commission_granted);
				$('#eff_coc_date_of_seniority').val(ParseDateColumncommission(j[0].eff_date_of_seniority));
				$('#coc_ch_id').val(j[0].id);
				$('#census_id').val(j[0].census_id);
				$('#comm_id').val(j[0].comm_id);
			}
		
	  });
	  
	  //function getcocDataStatus1() {
		
		  $.post('getcocDataStatus1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			  if(j!=""){
				 
				  var str = j[0][0];
				  var res = str.substring(0, 3).toUpperCase();
				  $('#coc_previous_commission').text(j[0][0]);
				  $('#coc_date_of_seniority').val(ParseDateColumncommission(j[0][1]));
				  $('#coc_old_personal_no').val(j[0][2]);
				  $('#coc_old_previous_commission').val(j[0][3]);
				  
				  /* if(res=="SSC"){
					  $("div#coc_bt_div").show();
				  }
				  else{
					  $("div#coc_bt_div").hide();
				  } */
			  }
		  });
	//}
	 
}
function onChangePerNo(obj)
{
	var data = obj.value;

	if(data.length == 5)
	{
		var suffix="";
		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
		
		summation = parseInt( parseInt(summation) % 11);
	
		if(summation == 0)
		{
			suffix="A";
		}
		if(summation == 1)
		{
			suffix="F";
		}
		if(summation == 2)
		{
			suffix="H";
		}
		if(summation == 3)
		{
			suffix="K";
		}
		if(summation == 4)
		{
			suffix="L";
		}
		if(summation == 5)
		{
			suffix="M";
		}
		if(summation == 6)
		{
			suffix="N";
		}
		if(summation == 7)
		{
			suffix="P";
		}
		if(summation == 8)
		{
			suffix="W";
		}
		if(summation == 9)
		{
			suffix="X";
		}
		if(summation == 10)
		{
			suffix="Y";
		}
		 $.ajaxSetup({
             async : false
    	 });
		$("#persnl_no3").val(suffix);
		 $.ajaxSetup({
             async : false
    	 });
 		
		
	}
}

function Personal_no_already_exist(id)
{

	
	 var persnl_no1 = $("select#persnl_no1").val();
	 if(persnl_no1 == "-1")
	 {
		 alert("Please Select Personal Number Prefix.")
		 return false;
	 }
	 var persnl_no2 = $("input#persnl_no2").val();
	 if(persnl_no2.length != 5 )
	 {
		 alert("Please Enter Personal Number.")
		 return false;
	 }
	 var persnl_no3 = $("input#persnl_no3").val();
	 if(persnl_no3.length != 1 )
	 {
		 alert("Please Enter Personal Number.")
		 return false;
	 }
	 var personnel_no = persnl_no1 + persnl_no2 + persnl_no3;
	 
	 
	 var data_result=true; 
	
     $.post("getPersonnelNoAlreadyExist?"+key+"="+value, {personnel_no : personnel_no,id:id}).done(function(j) {
		 	if(j == false){
				alert("Personal No already Exist.")
				$("select#persnl_no1").val("-1");
				$("input#persnl_no2").val("");
				$("input#persnl_no3").val("");
				data_result =  false;
			} 
		}); 
	 
	return data_result;
}
//***************** END CHANGE OF COMMISSION DETAILS************************//
</script>

<script>
//***************** START EXTENSION OF COMMISSION DETAILS************************//
function validate_eoc_save_fn(){
    var authority=$('#eoc_authority').val();
    var date_of_authority=$('#eoc_authority_date').val();
    var from=$('#eoc_from').val();
    var to=$('#eoc_to').val();
    var eoc_ch_id=$('#eoc_ch_id').val();
    var cen_id=$('#census_id').val();
    var comm_id=$('#comm_id').val();
    var comm_date=$('#comm_date').val();
    
    if ($("input#eoc_authority").val().trim() == "") {
             alert("Please Enter Authority");
                    $("input#eoc_authority").focus();
                    return false;
            } 
             if ($("input#eoc_authority_date").val().trim() == "" || $("input#eoc_authority_date").val() == "DD/MM/YYYY") {
             alert("Please Enter Date of Authority");
                    $("input#eoc_authority_date").focus();
                    return false;
            } 
                if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#eoc_authority_date").val())) {
             alert("Authority Date should be Greater than Commission Date");
               return false;
             }

             if ($("input#eoc_from").val().trim() == "" || $("input#eoc_from").val() == "DD/MM/YYYY") {
                     alert("Please Select From");
                            $("input#eoc_from").focus();
                            return false;
                    }  
             if ($("input#eoc_to").val().trim() == "" || $("input#eoc_to").val() == "DD/MM/YYYY") {
                     alert("Please Select To");
                            $("input#eoc_to").focus();
                            return false;
                    }  
             
             if(getformatedDate(from) > getformatedDate(to)) {
                            alert("To Date should always be greater than From Date");
                            $("input#eoc_to").focus();
                            return false;
                    } 
             
             if(getformatedDate($("input#comm_date").val()) > getformatedDate($("input#eoc_from").val())) {
        			alert("From Date should be Greater than Commission Date");
        			$("#eoc_from").focus();
        			return false;
        	  } 
              if(getformatedDate($("input#comm_date").val()) > getformatedDate($("input#eoc_to").val())) {
         			alert("To Date should be Greater than Commission Date");
         			$("#eoc_to").focus();
         			return false;
         	  } 
       $.post('eoc_action?' + key + "=" + value, {authority:authority,date_of_authority:date_of_authority,to:to
               ,from:from,eoc_ch_id:eoc_ch_id,cen_id:cen_id,comm_id:comm_id,comm_date:comm_date}, function(data){
              if(data=="update")
                      alert("Data Updated Successfully");
              else if(parseInt(data)>0){
                     $('#eoc_ch_id').val(data);
                     alert("Data Saved Successfully")
              }
              else
                      alert(data);
                               }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                      });
}

	function geteoc(){
		 var n_id=$('#census_id').val(); 
		 var comm_id=$('#comm_id').val();
		  $.post('geteocData?' + key + "=" + value, {n_id:n_id,comm_id:comm_id}, function(j){
				if(j!=""){
					var authDt=ParseDateColumncommission(j[0].date_of_authority);				
 					var toDt=ParseDateColumncommission(j[0].todt);
 					var fromDt=ParseDateColumncommission(j[0].fromdt);
					var authority=$('#eoc_authority').val(j[0].authority);
					var date_of_authority=$('#eoc_authority_date').val(authDt);
					var from=$('#eoc_from').val(fromDt);
					var to=$('#eoc_to').val(toDt);
					var eoc_ch_id=$('#eoc_ch_id').val(j[0].id);
					var cen_id=$('#census_id').val(j[0].census_id);
					var comm_id=$('#comm_id').val(j[0].comm_id);
				}
			});
		  
		  
		  $.post('getcocDataStatus1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			  if(j!=""){
				 
				  var str = j[0][0];
				  var res = str.substring(0, 3);
				  $('#coc_previous_commission').text(j[0][0]);
				  //$('#coc_date_of_seniority').val(formateDate(j[0][1]));
				  
				  /* if(res=="SSC"){
					  $("div#eoc_bt_div").show();
				  }
				  else{
					  $("div#eoc_bt_div").hide();
				  } */
			  }
				
		  });
	}
	function formateDate(value){
		var date = new Date(value);
		var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
		return formattedDate;
	}
	//***************** END EXTENSION OF COMMISSION DETAILS************************//
</script>
  


<script>
//***************** START MARRIED DETAILS************************//
	function checkDivorcedate(obj,marriage_date) {
			
			if(marriage_date.value !="")
			{
				var id = obj.id;
				var myDate = document.getElementById(id).value;
				var Date1 = marriage_date.value;
				if ((Date.parse(myDate) < Date.parse(Date1))) {
					alert('Divorce Date should not be before Marriage Date');
					$("input#divorce_date").focus();
					$("input#divorce_date").val("");
				}
			}
		}
	function marrqualificationFn(){
		
			
			  if($('#marr_quali_radio1').is(':checked')) {
				  $('#marital_eventDiv').show();
				  $('#spouse_Qualifications').hide();
				  $('#mrgbtn_save').show();
				  $('#mrgqualibtn_save').hide();
			  }
			  else if($('#marr_quali_radio2').is(':checked')) { 
				  $('#marital_eventDiv').hide();
				  $('#marital_event').val(0);
				  $('#marriage_remarriageDiv').hide();
				  $('#spouse_Qualifications').show();
				  $('#mrgbtn_save').hide();
				  $('#mrgqualibtn_save').show();
				  
			  }
				
	}

	function married_save_fn(ps){
		
	debugger;
		 var marital_event=$('#marital_event').val();		
	
		if (marital_event!="2" && marital_event!="5" && marital_event!="6" ) {	
		 $("#divorce_date").val("");
		}
    
     var marital_status=$('#marital_status').val();
     var maiden_name=$('#maiden_name').val();
     var marriage_date_of_birth=$('#marriage_date_of_birth').val();
     var marriage_place_of_birth=$('#marriage_place_of_birth').val();
     var marriage_nationality=$('#marriage_nationality').val();
     var marriage_nationality_other=$('#spouseNationality_other').val();
     var marriage_date=$('#marriage_date').val();
     var marriage_adhar_no=$('#marriage_adhar_no').val().split('-').join('');
     var pan_card=$('#pan_card').val();
     var marr_ch_id=$('#marr_ch_id').val();
     var divorce_date=$('#divorce_date').val();
     var marriage_authority=$('#marriage_authority').val();
     var marriage_date_of_authority=$('#marriage_date_of_authority').val();
     var comm_id=$('#comm_id').val();        
     var p_id=$('#census_id').val();
     var ser_radio = $('input:radio[name=spouse_ser_radio]:checked').val();
     var spouse_personal_no=$("#spouse_personal_no").val();
     var spouseSer_other=$("#spouseSer_other").val();
     var spouse_service=$("#spouse_service").val();
     var comm_date=$('#comm_date').val();  
     
     var separated_from_dt=$("#separated_from_dt").val();
     var separated_to_dt=$('#separated_to_dt').val(); 
     var rank = $('#spouse_rank').val();
     var armservice =$('#spouse_arm_service').val();
  // bisag v2 03072023(Observation)
     var spouse_profession=$("#spouse_profession").val();
     
     if (marital_event == null || marital_event=="0") {
             alert("Please Select Marital Event");
             $('#marital_event').focus()
             return false;
     }
     
     
     if (marriage_authority == null || marriage_authority.trim()=="") {
         alert("Please Enter Authority");
         $('#marriage_authority').focus()
         return false;
	   }
	   if (marriage_date_of_authority == null || marriage_date_of_authority=="" || marriage_date_of_authority=="DD/MM/YYYY") {
	           alert("Please Enter Date of Authority");
	           $('#marriage_date_of_authority').focus()
	           return false;
	   }
	   if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#marriage_date_of_authority").val())) {
         alert("Authority Date should be Greater than Commission Date");
         return false;
		}
     
     if(marital_event == 1 || marital_event == 3){
			
  	   
    			if(curr_marital_status==13){
				   		if (separated_to_dt != null && separated_to_dt=="" && separated_to_dt=="DD/MM/YYYY") {
				             
				   		if(getformatedDate(separated_from_dt) >= getformatedDate(separated_to_dt)) {
				               alert("Separation To Date should be Greater than Separation From Date");
				               $("input#separated_to_dt").focus();
				               return false;
				       	}
				   		}
				}
    			else{
		               if (maiden_name == null || maiden_name.trim()=="")  {
		                       alert("Please Enter Spouse Name");
		                       $('#maiden_name').focus()
		                       return false;
		               }
		               if (marriage_date == null || marriage_date=="" || marriage_date=="DD/MM/YYYY") {
		                       alert("Please Select Date of Marriage");
		                       $('#marriage_date').focus()
		                       return false;
		               }
		               if (marriage_place_of_birth == null || marriage_place_of_birth.trim()=="") {
		                       alert("Please Enter Place Of Birth");
		                       $('#marriage_place_of_birth').focus()
		                       return false;
		               }
		               if (marriage_nationality == null || marriage_nationality=="0") {
		                       alert("Please Select Nationality");
		                       $('#marriage_nationality').focus()
		                       return false;
		               }
		               if($("#marriage_nationality option:selected").text()==other) {
		                     if(marriage_nationality_other == null || marriage_nationality_other==""){          
		                            alert( "Please Enter Nationality Other");
		                               
		                               $("input#spouseNationality_other").focus();
		                               return false;
		                          }
		             }
		               if (marriage_date_of_birth == null || marriage_date_of_birth=="" || marriage_date_of_birth=="DD/MM/YYYY") {
		                       alert("Please Select Date of Birth");
		                       $('#marriage_date_of_birth').focus()
		                       return false;
		               }
		               
		               if(getformatedDate(marriage_date_of_birth) > getformatedDate(marriage_date)) {
		                       alert("Marriage Date should be Greater than Date of Birth");
		                       $('#marriage_date').focus()
		                       return false;
		               }
		               if(calculate_age(document.getElementById('marriage_date_of_birth'), document.getElementById('marriage_date')) && calculate_age(document.getElementById('dob_date'), document.getElementById('marriage_date'))) {} else {
		                       return false;
		               }
		               if (marriage_adhar_no == null || marriage_adhar_no=="" ||  marriage_adhar_no.length < 12) {
		                       alert("Please Enter Aadhaar Number");
		                       $('#marriage_adhar_no').focus()
		                       return false;
		               }
	
		               if(ser_radio=="Yes"){
		                       if(spouse_service==null || spouse_service=='0'){
		                               alert("Please Select Spouse In Service");
		                               $('#spouse_service').focus()
		                               return false;
		                       }
		                       else{
		                               if(spouse_service=='4'){
		                                       if(spouseSer_other==null || spouseSer_other==""){                        
		                                       alert("Please Enter Other Service");
		                                       $('#spouseSer_other').focus()
		                                       return false;
		                                       }
		                               }
		                                else if(spouse_service=='1'){
		                                        if(spouse_personal_no==null || spouse_personal_no==""){                        
		                                                alert("Please Enter Spouse Personal No.");
		                                                $('#spouse_personal_no').focus()
		                                                return false;
		                                                }
		                                        }
		                               if(spouse_personal_no.trim()!=''){
		                                       if(spouse_personal_no.trim().length < 5 || spouse_personal_no.trim().length >15){
		                                       alert("Please Enter Valid Spouse Personal No");
		                                       $("#spouse_personal_no").focus();
		                                       return false;
		                                       }
		                               }
		                       }
		               }
		       }
     }
   
     if( marital_event=='2' || marital_event=='5' || marital_event=='6'){
             if (divorce_date == null || divorce_date=="" || divorce_date=="DD/MM/YYYY") {
                     alert("Please Select "+$("#divorce_widow_widower_dtlbl").text());
                     $('#divorce_date').focus()
                     return false;
             }
             if(getformatedDate(marriage_date) >= getformatedDate(divorce_date)) {
                     alert($("#divorce_widow_widower_dtlbl").text()+" should be Greater than Marriage Date");
                     $("input#divorce_date").focus();
                     return false;
             }
     }
     
     if(marital_event=='4'){
     	if(curr_marital_status==8){
	        	if (separated_from_dt == null || separated_from_dt=="" || separated_from_dt=="DD/MM/YYYY") {
	                alert("Please Enter Date of Separation");
	                $('#separated_from_dt').focus()
	                return false;
	        		}
	        	if(getformatedDate(marriage_date) >= getformatedDate(separated_from_dt)) {
                 alert("Date of Separation should be Greater than Marriage Date");
                 $("input#separated_from_dt").focus();
                 return false;
         	}
     	}

    }
     
      $.post('update_family_marriage_action?' + key + "=" + value, {separated_to_dt:separated_to_dt,separated_from_dt:separated_from_dt,curr_marital_status:curr_marital_status,maiden_name:maiden_name,marriage_date_of_birth:marriage_date_of_birth,
                marriage_place_of_birth:marriage_place_of_birth,marriage_nationality:marriage_nationality,marriage_date:marriage_date,marriage_adhar_no:marriage_adhar_no,pan_card:pan_card,
                marr_ch_id:marr_ch_id,divorce_date:divorce_date,marriage_authority:marriage_authority,marriage_date_of_authority:marriage_date_of_authority
                ,marriage_nationality_other:marriage_nationality_other,comm_date:comm_date,marital_event:marital_event,p_id:p_id,comm_id:comm_id ,marital_status:marital_status,ser_radio:ser_radio,spouse_service:spouse_service,spouseSer_other:spouseSer_other,spouse_personal_no:spouse_personal_no,spouse_profession:spouse_profession,rank:rank,armservice:armservice}, function(data){
                        if(data=="update"){
                                
                                if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3' || $('#marital_status').val() == '8'){
                              		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
                              		if(a.toLowerCase() == "yes"){
                              			spouse_qualification_save_fn();
                              		}
                              		else{
                              		  remove_spouse_qualificationFn();	
                              		alert("Spouse Data Updated Successfully");
                              		}
                    			}
                    			else{
                    				alert("Spouse Data Updated Successfully");
                    			}
                        }
                        else if(parseInt(data)>0){
                      $('#marr_ch_id').val(data);
                      
                      if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3' ||  $('#marital_status').val() == '8'){
                  		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
                  		if(a.toLowerCase() == "yes"){
                  			spouse_qualification_save_fn();
                  		}
                  		else{
                    		  remove_spouse_qualificationFn();	
                    		 alert("Spouse Data Saved Successfully");
                    		}
        			}
        			else{
        				alert("Spouse Data Saved Successfully");
        			}
                      
                       
               }
               else
                       alert(data);
                                }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                       });
}
//spouse



function fn_other_spouse_exam() {
        var text = $("#spouse_quali option:selected").text();
        if(text == "OTHERS"){
                $("#other_spouse_exam").show();
        }
        else{
                $("#other_spouse_exam").hide();
        }
}

function fn_other_spouse_div() {
    var text = $("#spouse_div_class option:selected").text();
    if(text == "OTHERS"){
            $("#other_spouse_div").show();
    }
    else{
            $("#other_spouse_div").hide();
    }
}

/* function getfamily_marriageDetails(){//
	 var p_id=$('#census_id').val();
debugger;
	 var comm_id=$('#comm_id').val(); 
	 var marital_event=$('#marital_event').val();
	  $.post('update_getfamily_marriageData?' + key + "=" + value, {p_id:p_id,marital_event:marital_event,comm_id:comm_id }, function(j){
			var v=j.length;
		if(v!=0){
			
if(curr_marital_status==8 && j[0].status==0){
			$("#marr_quali_radio1").prop("checked", true);
			$("#marr_quali_radio2").prop("disabled", true);
			  $('#spouse_Qualifications').hide();
			  $('#mrgbtn_save').show();
			  $('#mrgqualibtn_save').hide();
}
			if($('#marital_event').val()=="0"){
					$('#marital_event').val(j[0].type_of_event);
					$('#marital_status').val(j[0].marital_status);
			}
			$('#maiden_name').val(j[0].maiden_name);
			$('#marriage_date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
			$('#marriage_place_of_birth').val(j[0].place_of_birth);
			$('#marriage_nationality').val(j[0].nationality);
			$('#marriage_date').val(ParseDateColumncommission(j[0].marriage_date));
			$('#marriage_adhar_no').val(j[0].adhar_number);
			$('#pan_card').val(j[0].pan_card);
			$('#marr_ch_id').val(j[0].id);	
			if(j[0].status==0 ){
			$('#marriage_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));			
			$('#marriage_authority').val(j[0].authority);
			}
			$('#separated_from_dt').val(ParseDateColumncommission(j[0].separated_form_dt));
			$('#separated_to_dt').val(ParseDateColumncommission(j[0].separated_to_dt));
			
			if(j[0].if_spouse_ser=="Yes"){
				$('#spouse_ser_radio1').prop('checked', true);
			}
			else{
				$('#spouse_ser_radio2').prop('checked', true);
				// bisag v2 03072023(Observation)
				$('#spouse_profession').val(j[0].spouse_profession);
			}
			if(j[0].spouse_personal_no!=null){
				$('#spouse_personal_no').val(j[0].spouse_personal_no);
			}
			if(j[0].spouse_service!=null){
				$('#spouse_service').val(j[0].spouse_service);
			}
			if(j[0].other_spouse_ser!=null){
				$('#spouseSer_other').val(j[0].other_spouse_ser);
			}
			if(j[0].other_spouse_ser!=null){
				$('#spouseSer_other').val(j[0].other_spouse_ser);
			}
			if(j[0].other_nationality!=null){
				$('#spouseNationality_other').val(j[0].other_nationality);
			}
			if(j[0].spouse_rank!=null){
				$('#spouse_rank').val(j[0].spouse_rank);
			}
			if(j[0].spouse_arm_service!=null){
				$('#spouse_arm_service').val(j[0].spouse_arm_service);
			}
			isAadhar(document.getElementById('marriage_adhar_no'));
			spouse_ServiceFn();
			Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',document.getElementById('spouse_service'))
			fn_otherShowHide(document.getElementById('marriage_nationality'),'Spouse_nationalityDiv','spouseNationality_other');
			if(j[0].divorce_date!=null && j[0].divorce_date!="" && j[0].divorce_date!="DD/MM/YYYY")
				$('#divorce_date').val(ParseDateColumncommission(j[0].divorce_date));
			if(j[0].status==1 || j[0].type_of_event==2 || j[0].type_of_event==4 || j[0].type_of_event==5 || j[0].type_of_event==6 || (j[0].type_of_event==1 && curr_marital_status==13)){
				$('#maiden_name').prop('disabled', true);
				$('#marriage_date_of_birth').prop('disabled', true);
				$("#marriage_date_of_birth").datepicker( "option", "disabled", true );
				$('#marriage_place_of_birth').prop('disabled', true);
				$("#marriage_place_of_birth").datepicker( "option", "disabled", true );
				$('#marriage_nationality').prop('disabled', true);
				$('#spouseNationality_other').prop('disabled', true);
				
				$('#marriage_date').prop('disabled', true);
				$("#marriage_date").datepicker( "option", "disabled", true );
				$('#marriage_adhar_no').prop('disabled', true);
				$('#pan_card').prop('disabled', true);
				$('#marr_ch_id').prop('disabled', true);			
//				$('#marriage_date_of_authority').prop('disabled', true);	
//				$("#marriage_date_of_authority").datepicker( "option", "disabled", true );
//				$('#marriage_authority').prop('disabled', true);
				
				
				$('#spouse_service').prop('disabled', true);
				$('input:radio[name=spouse_ser_radio]').prop('disabled', true);			
				$('#spouseSer_other').prop('disabled', true);		
				$('#spouse_personal_no').prop('disabled', true);
			}
			//$('#marital_event').prop('disabled', true);
			$('#marital_status').prop('disabled', true);
			$("#marriage_remarriageDiv").show();
			if($('#marital_event').val()=="1"){
				if(curr_marital_status==13){
					 $("#seperateDiv").show();
	        		$("#separated_from_dtDiv").show();
	        		$("#separated_to_dtDiv").show();
	        		$('#separated_from_dt').prop('disabled', true);
	        		$("#separated_from_dt").datepicker( "option", "disabled", true );
	        	}
			}
				
			
			if($('#marital_event').val()=="2" || $('#marital_event').val()=="5" || $('#marital_event').val()=="6"){			
			$("#divorceDiv").show();
			$("#spouse_quali_radioDiv").hide();
			 // bisag v2 03072023(Observation)
			$("#spouse_proffDiv").hide();
			
			if($('#marital_event').val()=="2"){
				$("#divorce_widow_widower_dtlbl").text("Date of Divorced")
			}
			if($('#marital_event').val()=="5" || $('#marital_event').val()=="6"){
				$("#divorce_widow_widower_dtlbl").text("Date of Death")
			}
			
			
			
			}
			
			else if($('#marital_event').val()=='4'){
				 $("#seperateDiv").show();
				// bisag v2 03072023(Observation)
				$("#spouse_proffDiv").hide();
			 
		        	if(curr_marital_status==8){
		        		if(j[0].status==1){
		        			$("#separated_from_dt").val('');
		        			$("#separated_to_dt").val('');
		        		}
		        		
		        	
		        		$("#separated_from_dtDiv").show();
		        		$("#separated_to_dtDiv").hide();		        		
		        	}
		        	
			        		
		        	}
		       
		
			else{
				$("#spouse_quali_radioDiv").show();
			}
			
			if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
				$("#spouse_quali_radioDiv").show();
			}
			else{
				$("#spouse_quali_radioDiv").hide();
			}
			
			if(curr_marital_status==0 || curr_marital_status==10){
				$("#marital_event option[value='2']").remove();
				$("#marital_event option[value='3']").remove();
				$("#marital_event option[value='4']").remove();
				$("#marital_event option[value='5']").remove();
				$("#marital_event option[value='6']").remove();
				}
			else if(curr_marital_status==8){
				$("#marital_event option[value='1']").remove();
				$("#marital_event option[value='3']").remove();
				}
			else if(curr_marital_status==13){
//				$("#marital_event option[value='1']").remove();
				$("#marital_event option[value='3']").remove();
				$("#marital_event option[value='4']").remove();
			}
			else {
				$("#marital_event option[value='1']").remove();
				$("#marital_event option[value='2']").remove();
				$("#marital_event option[value='4']").remove();
				$("#marital_event option[value='5']").remove();
				$("#marital_event option[value='6']").remove();
			}
			
			}
		else{
		if(curr_marital_status==0 || curr_marital_status==10){
			$("#marital_event option[value='2']").remove();
			$("#marital_event option[value='3']").remove();
			$("#marital_event option[value='4']").remove();
			$("#marital_event option[value='5']").remove();
			$("#marital_event option[value='6']").remove();
			}
		else if(curr_marital_status==8){
			$("#marital_event option[value='1']").remove();
			$("#marital_event option[value='3']").remove();
			}
		else if(curr_marital_status==13){
//			$("#marital_event option[value='1']").remove();
			$("#marital_event option[value='3']").remove();
			$("#marital_event option[value='4']").remove();
		}
		else {
			$("#marital_event option[value='1']").remove();
			$("#marital_event option[value='2']").remove();
			$("#marital_event option[value='4']").remove();
			$("#marital_event option[value='5']").remove();
			$("#marital_event option[value='6']").remove();
		}
		}
	  });
} */


//manav

function getfamily_marriageDetails(){

	debugger; 
	var p_id=$('#census_id').val();	 
	 var comm_id=$('#comm_id').val(); 
	 var marital_event=$('#marital_event').val();
	  $.post('check_mrg_detl?' + key + "=" + value, {p_id:p_id, comm_id:comm_id}, function(j) {
		    var mrg_dtl = j;
		   if(mrg_dtl>0){			 
		debugger;
				  $.post('update_getfamily_marriageData?' + key + "=" + value, {p_id:p_id,marital_event:marital_event,comm_id:comm_id }, function(j){
						var v=j.length;
					if(v!=0){
						
			if(curr_marital_status==8 && j[0].status==0){
						$("#marr_quali_radio1").prop("checked", true);
						$("#marr_quali_radio2").prop("disabled", true);
						  $('#spouse_Qualifications').hide();
						  $('#mrgbtn_save').show();
						  $('#mrgqualibtn_save').hide();
			}
						if($('#marital_event').val()=="0"){
								$('#marital_event').val(j[0].type_of_event);
								$('#marital_status').val(j[0].marital_status);
						}
						$('#maiden_name').val(j[0].maiden_name);
						$('#marriage_date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
						$('#marriage_place_of_birth').val(j[0].place_of_birth);
						$('#marriage_nationality').val(j[0].nationality);
						$('#marriage_date').val(ParseDateColumncommission(j[0].marriage_date));
						$('#marriage_adhar_no').val(j[0].adhar_number);
						$('#pan_card').val(j[0].pan_card);
						$('#marr_ch_id').val(j[0].id);	
						if(j[0].status==0 ){
						$('#marriage_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));			
						$('#marriage_authority').val(j[0].authority);
						}
						$('#separated_from_dt').val(ParseDateColumncommission(j[0].separated_form_dt));
						$('#separated_to_dt').val(ParseDateColumncommission(j[0].separated_to_dt));
						
						if(j[0].if_spouse_ser=="Yes"){
							$('#spouse_ser_radio1').prop('checked', true);
						}
						else{
							$('#spouse_ser_radio2').prop('checked', true);
							// bisag v2 03072023(Observation)
							$('#spouse_profession').val(j[0].spouse_profession);
						}
						if(j[0].spouse_personal_no!=null){
							$('#spouse_personal_no').val(j[0].spouse_personal_no);
						}
						if(j[0].spouse_service!=null){
							$('#spouse_service').val(j[0].spouse_service);
						}
						if(j[0].other_spouse_ser!=null){
							$('#spouseSer_other').val(j[0].other_spouse_ser);
						}
						if(j[0].other_spouse_ser!=null){
							$('#spouseSer_other').val(j[0].other_spouse_ser);
						}
						if(j[0].other_nationality!=null){
							$('#spouseNationality_other').val(j[0].other_nationality);
						}
						if(j[0].spouse_rank!=null){
							$('#spouse_rank').val(j[0].spouse_rank);
						}
						if(j[0].spouse_arm_service!=null){
							$('#spouse_arm_service').val(j[0].spouse_arm_service);
						}
						isAadhar(document.getElementById('marriage_adhar_no'));
						spouse_ServiceFn();
						Spouse_ServiceOtherFn('spouseSer_otherDiv','spouse_personalDiv',document.getElementById('spouse_service'))
						fn_otherShowHide(document.getElementById('marriage_nationality'),'Spouse_nationalityDiv','spouseNationality_other');
						if(j[0].divorce_date!=null && j[0].divorce_date!="" && j[0].divorce_date!="DD/MM/YYYY")
							$('#divorce_date').val(ParseDateColumncommission(j[0].divorce_date));
						if(j[0].status==1 || j[0].type_of_event==2 || j[0].type_of_event==4 || j[0].type_of_event==5 || j[0].type_of_event==6 || (j[0].type_of_event==1 && curr_marital_status==13)){
							$('#maiden_name').prop('disabled', true);
							$('#marriage_date_of_birth').prop('disabled', true);
							$("#marriage_date_of_birth").datepicker( "option", "disabled", true );
							$('#marriage_place_of_birth').prop('disabled', true);
							$("#marriage_place_of_birth").datepicker( "option", "disabled", true );
							$('#marriage_nationality').prop('disabled', true);
							$('#spouseNationality_other').prop('disabled', true);
							
							$('#marriage_date').prop('disabled', true);
							$("#marriage_date").datepicker( "option", "disabled", true );
							$('#marriage_adhar_no').prop('disabled', true);
							$('#pan_card').prop('disabled', true);
							$('#marr_ch_id').prop('disabled', true);			
//							$('#marriage_date_of_authority').prop('disabled', true);	
//							$("#marriage_date_of_authority").datepicker( "option", "disabled", true );
//							$('#marriage_authority').prop('disabled', true);
							
							
							$('#spouse_service').prop('disabled', true);
							$('input:radio[name=spouse_ser_radio]').prop('disabled', true);			
							$('#spouseSer_other').prop('disabled', true);		
							$('#spouse_personal_no').prop('disabled', true);
						}
						//$('#marital_event').prop('disabled', true);
						$('#marital_status').prop('disabled', true);
						$("#marriage_remarriageDiv").show();
						if($('#marital_event').val()=="1"){
							if(curr_marital_status==13){
								 $("#seperateDiv").show();
				        		$("#separated_from_dtDiv").show();
				        		$("#separated_to_dtDiv").show();
				        		$('#separated_from_dt').prop('disabled', true);
				        		$("#separated_from_dt").datepicker( "option", "disabled", true );
				        	}
						}
							
						
						if($('#marital_event').val()=="2" || $('#marital_event').val()=="5" || $('#marital_event').val()=="6"){			
						$("#divorceDiv").show();
						$("#spouse_quali_radioDiv").hide();
						 // bisag v2 03072023(Observation)
						$("#spouse_proffDiv").hide();
						
						if($('#marital_event').val()=="2"){
							$("#divorce_widow_widower_dtlbl").text("Date of Divorced")
						}
						if($('#marital_event').val()=="5" || $('#marital_event').val()=="6"){
							$("#divorce_widow_widower_dtlbl").text("Date of Death")
						}
						
						
						
						}
						
						else if($('#marital_event').val()=='4'){
							 $("#seperateDiv").show();
							// bisag v2 03072023(Observation)
							$("#spouse_proffDiv").hide();
						 
					        	if(curr_marital_status==8){
					        		if(j[0].status==1){
					        			$("#separated_from_dt").val('');
					        			$("#separated_to_dt").val('');
					        		}
					        		
					        	
					        		$("#separated_from_dtDiv").show();
					        		$("#separated_to_dtDiv").hide();		        		
					        	}
					        	
						        		
					        	}
					       
					
						else{
							$("#spouse_quali_radioDiv").show();
						}
						
						if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
							$("#spouse_quali_radioDiv").show();
						}
						else{
							$("#spouse_quali_radioDiv").hide();
						}
						
						if(curr_marital_status==0 || curr_marital_status==10){
							$("#marital_event option[value='2']").remove();
							$("#marital_event option[value='3']").remove();
							$("#marital_event option[value='4']").remove();
							$("#marital_event option[value='5']").remove();
							$("#marital_event option[value='6']").remove();
							}
						else if(curr_marital_status==8){
							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='3']").remove();
							}
						else if(curr_marital_status==13){
//							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='3']").remove();
							$("#marital_event option[value='4']").remove();
						}
						else {
							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='2']").remove();
							$("#marital_event option[value='4']").remove();
							$("#marital_event option[value='5']").remove();
							$("#marital_event option[value='6']").remove();
						}
						
						}
					else{		
						if(curr_marital_status==0 || curr_marital_status==10){
							$("#marital_event option[value='2']").remove();
							$("#marital_event option[value='3']").remove();
							$("#marital_event option[value='4']").remove();
							$("#marital_event option[value='5']").remove();
							$("#marital_event option[value='6']").remove();
							}
						else if(curr_marital_status==8){
							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='3']").remove();
							}
						else if(curr_marital_status==13){
//							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='3']").remove();
							$("#marital_event option[value='4']").remove();
						}
						else {
							$("#marital_event option[value='1']").remove();
							$("#marital_event option[value='2']").remove();
							$("#marital_event option[value='4']").remove();
							$("#marital_event option[value='5']").remove();
							$("#marital_event option[value='6']").remove();
						}
					
						

					}
				  }); 
		   }else {
			   debugger;
		        var status=0;		  
			   $.post('getUpdatedmrgdtl?' + key + "=" + value, {p_id:p_id, comm_id:comm_id,app_status:status}, function(j) {   
				   var v=j.length;
				   if(v!=0){
					   $('#maiden_name').val(j[0].maiden_name);
						$('#marriage_date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
						$('#marriage_place_of_birth').val(j[0].place_of_birth);
						$('#marriage_nationality').val(j[0].nationality);
						$('#marriage_date').val(ParseDateColumncommission(j[0].marriage_date));
						$('#marriage_adhar_no').val(j[0].adhar_number);
						$('#pan_card').val(j[0].pan_card);
						$('#marr_ch_id').val(j[0].id);	
						if(j[0].status==0 ){
						$('#marriage_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));			
						$('#marriage_authority').val(j[0].authority);
						}
						$('#separated_from_dt').val(ParseDateColumncommission(j[0].separated_form_dt));
						$('#separated_to_dt').val(ParseDateColumncommission(j[0].separated_to_dt));
						$('#divorce_date').val(ParseDateColumncommission(j[0].divorce_date));
						if(j[0].if_spouse_ser=="Yes"){
							$('#spouse_ser_radio1').prop('checked', true);
						}
						else{
							$('#spouse_ser_radio2').prop('checked', true);
							// bisag v2 03072023(Observation)
							$('#spouse_profession').val(j[0].spouse_profession);
						}
						if(j[0].spouse_personal_no!=null){
							$('#spouse_personal_no').val(j[0].spouse_personal_no);
						}
						if(j[0].spouse_service!=null){
							$('#spouse_service').val(j[0].spouse_service);
						}
						if(j[0].other_spouse_ser!=null){
							$('#spouseSer_other').val(j[0].other_spouse_ser);
						}
						if(j[0].other_spouse_ser!=null){
							$('#spouseSer_other').val(j[0].other_spouse_ser);
						}
						if(j[0].other_nationality!=null){
							$('#spouseNationality_other').val(j[0].other_nationality);
						}
						if(j[0].spouse_rank!=null){
							$('#spouse_rank').val(j[0].spouse_rank);
						}
						if(j[0].spouse_arm_service!=null){
							$('#spouse_arm_service').val(j[0].spouse_arm_service);
						}
					
					
				   
				   }
		       });
				$.post('census_mrg_detl?' + key + "=" + value, {p_id:p_id, comm_id:comm_id}, function(j) {
				    var maritalStatus = j;			    
				    $("#marriage_remarriageDiv").show();
				    $("#spouse_quali_radioDiv").show();   
				    $("#marr_quali_radioDiv").remove();
				    curr_marital_status=maritalStatus;
				    $("#marital_status").val(maritalStatus);
				    document.getElementById('marital_event').style.display = 'none';
				    document.getElementById('maritaleventlbl').style.display = 'block';				  
				
				    if(maritalStatus == 13){				    	
				    	
				    	 $("#divorceDiv").hide();
				    	  $("#spouse_quali_radioDiv").hide();
				    	 $("#divorce_date").val("");	
				    	 $("#seperateDiv").show();
				    	 $("#separated_from_dtDiv").show();
				    	 $("#separated_to_dtDiv").show();
				    	 $("#separated_to_dt").val('');	
				    	 $('#marital_event').val(4);
				    	  document.getElementById('maritaleventlbl').textContent ="Separeted";
				    	
				    }else if (maritalStatus == 11){
				    	$("#divorceDiv").show();
				    	 $("#divorce_date").val("");	
				    	$("#seperateDiv").hide();
				    	$("#separated_from_dtDiv").hide();
				    	$("#separated_to_dtDiv").hide();
				    	  $("#spouse_quali_radioDiv").hide();
				    	 $("#separated_to_dt").val('');			    	
				    	$("#divorce_widow_widower_dtlbl").text("Date of Death")
				    	 $('#marital_event').val(5);
				    	document.getElementById('maritaleventlbl').textContent ="Widow";
				    }else if (maritalStatus == 12){
				    	$("#divorceDiv").show();
				    	 $("#divorce_date").val("");	
				    	$("#seperateDiv").hide();
				    	$("#separated_from_dtDiv").hide();
				    	$("#separated_to_dtDiv").hide();				    	
				    	  $("#spouse_quali_radioDiv").hide();
				    	 $("#separated_to_dt").val('');			    	
				    	$("#divorce_widow_widower_dtlbl").text("Date of Death")
				    	 $('#marital_event').val(6);
				    	document.getElementById('maritaleventlbl').textContent ="Widower";
				    }
				    else if (maritalStatus == 9){
				    	$("#divorceDiv").show();
				    	 $("#divorce_date").val("");	
				    	$("#seperateDiv").hide();
				    	$("#separated_from_dtDiv").hide();
				    	$("#separated_to_dtDiv").hide();
				    	  $("#spouse_quali_radioDiv").hide();
				    	 $("#separated_to_dt").val('');	
				    	 document.getElementById('maritaleventlbl').textContent ="Divorce";
				    	 $('#marital_event').val(2);
				    	//$("#divorce_widow_widower_dtlbl").text("Date of Death")
				    }    else if (maritalStatus == 8){
				    	
				    	 $("#divorceDiv").hide();
				    	 $("#divorce_date").val("");	
				    	 $("#seperateDiv").hide();
				    	 $("#separated_from_dtDiv").hide();
				    	 $("#separated_to_dtDiv").hide();
				    	 $("#separated_to_dt").val('');		
				    	 document.getElementById('maritaleventlbl').textContent ="Married";
				    	 $('#marital_event').val(1);				    	
				    	//$("#divorce_widow_widower_dtlbl").text("Date of Death")
				    }else if (maritalStatus == 10){				    
				    	     var marital_event = $('#marital_event').val();
				    	     if(marital_event == 2){
				    	    	 $("#divorceDiv").show();
						    	 $("#divorce_date").val("");	
						    	$("#seperateDiv").hide();
						    	$("#separated_from_dtDiv").hide();
						    	$("#separated_to_dtDiv").hide();
						    	 $("#separated_to_dt").val('');	
						    	 document.getElementById('maritaleventlbl').textContent ="Divorce";
				    	     }else if (marital_event == 4){

						    	 $("#divorceDiv").hide();
						    	 $("#divorce_date").val("");	
						    	 $("#seperateDiv").show();
						    	 $("#separated_from_dtDiv").show();
						    	 $("#separated_to_dtDiv").show();
						    	 $("#separated_to_dt").val('');							    
						    	  document.getElementById('maritaleventlbl').textContent ="Separeted";
				    	     }else if (marital_event == 5){

				    	    	 $("#divorceDiv").show();
						    	 $("#divorce_date").val("");	
						    	$("#seperateDiv").hide();
						    	$("#separated_from_dtDiv").hide();
						    	$("#separated_to_dtDiv").hide();
						    	 $("#separated_to_dt").val('');			    	
						    	$("#divorce_widow_widower_dtlbl").text("Date of Death")
				    	     }else if (marital_event == 6){
				    	    	 $("#divorceDiv").show();
						    	 $("#divorce_date").val("");	
						    	$("#seperateDiv").hide();
						    	$("#separated_from_dtDiv").hide();
						    	$("#separated_to_dtDiv").hide();
						    	 $("#separated_to_dt").val('');			    	
						    	$("#divorce_widow_widower_dtlbl").text("Date of Death")
				    	     }
				    	     else if (marital_event == 1 || marital_event == 3){
				    	    	 $("#divorceDiv").hide();
						    	 $("#divorce_date").val("");	
						    	 $("#seperateDiv").hide();
						    	 $("#separated_from_dtDiv").hide();
						    	 $("#separated_to_dtDiv").hide();
						    	 $("#separated_to_dt").val('');		
						    	 document.getElementById('maritaleventlbl').textContent ="Married";
				    	     }
				    	    document.getElementById('marital_event').style.display = 'block';
						    document.getElementById('maritaleventlbl').style.display = 'none';
				    }

				   
				  });
		   }
		
			
		  });
	  
	 
	 
	 

}





function spouse_ServiceFn() {
	var a = $('input:radio[name=spouse_ser_radio]:checked').val();
	if(a.toLowerCase() == "yes") {
		$("#spouse_inserviceDiv").show();
//		$("#spouseSerDiv").hide();
		// bisag v2 03072023(Observation)
		$("#spouse_proffDiv").hide();
	} else {
//		$("#father_proffDiv").show();
		$("#spouse_inserviceDiv").hide();
		$("#spouse_personal_no").val("");
		$("#spouseSer_other").val("");
		$("#spouse_service").val("0");
		// bisag v2 03072023(Observation)
		$("#spouse_proffDiv").show();
	}
}
/* function Spouse_ServiceOtherFn(divId, perId, obj) {
	if(obj.options[obj.selectedIndex].text == 'OTHER') {
		$("#" + divId).show();
		$("#" + perId).hide();
		$("#spouse_personal_no").val("");
	} else {		
		$("#" + divId).hide();
		$("#" + perId).show();
		$("#spouseSer_other").val("");
	}
} */

 function Spouse_ServiceOtherFn(divId, perId, obj) {
	
	if(obj.options[obj.selectedIndex].text == 'OTHER') {
		$("#" + divId).show();
		$("#" + perId).hide();
		document.getElementById('spouse_rank_armservice').style.display = 'none';
		$("#spouse_personal_no").val("");
	} else {		

		$("#" + divId).hide();
		$("#" + perId).show();		
		document.getElementById('spouse_rank_armservice').style.display = 'none';         
		if(obj.options[obj.selectedIndex].text == 'ARMY'){
			document.getElementById('spouse_rank_armservice').style.display = 'block';
		}
		$("#spouseSer_other").val("");
	}
} 

function spouse_qualification_save_fn() {
	
debugger;
	var spouse_id = $("#marr_ch_id").val();
	var dateofBirthyear = $("#marriage_date_of_birth").val().split('/')[2];
	var currentdate = new Date();
	var currentyear = currentdate.getFullYear();
	var type = $('#spouse_quali_type').val();
	var examination_pass = $('#spouse_quali').val();
	var exam_other_qua=$('#exam_otherSpouse').val();
 	var degree_other=$('#quali_deg_otherSpouse').val();
	var spec_other=$('#quali_spec_otherSpouse').val();
	var class_other_qua=$('#class_otherSpouse').val();
	var passing_year = $('#spouse_yrOfPass').val();
	var degree = $('#quali_degree_spouse').val();
	var specialization = $('#spouse_specialization').val();
	var div_class = $('#spouse_div_class').val();
	var institute = $('#spouse_institute_place').val();
	var qualification_ch_id = $('#spouse_qualification_ch_id').val();
	var q_id = $('#census_id').val();
	var com_id = $('#comm_id').val();
	var subjectvar = $('input[name="spouse_multisub"]:checkbox:checked').map(function() {
		return this.value;
	}).get();
	var subject = subjectvar.join(",");


		

	if(type == null || type == "0") {
		alert("Please Select Qualification Type");
		$("#spouse_quali_type").focus();
		return false;
	}

	if(examination_pass == null || examination_pass == "0") {
		alert("Please Select Examination Pass");
		$("#spouse_quali").focus();
		return false;
	}

	  if($("#spouse_quali option:selected").text()==other) {
	     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
	     		alert( "Please Enter Examination Other ");
				
				$("input#exam_otherSpouse").focus();
				return false;
			   }
	      }
	  
	if(degree == null || degree == "0") {
		alert("Please Select The Degree Acquired");
		$("#quali_degree_spouse").focus();
		return false;
	}
	if($("#quali_degree_spouse option:selected").text()==other) {
     	 if(degree_other == null || degree_other.trim()==""){ 	       
     		alert( "Please Enter Degree Other ");
			$("input#quali_deg_otherSpouse").focus();
			return false;
		   }
      }
	if(specialization == null || specialization == "0") {
		alert("Please Select The Specialization");
		$("#spouse_specialization").focus();
		return false;
	}
	





 
 if($("#spouse_specialization option:selected").text()==other) {
  	 if(spec_other == null || spec_other.trim()==""){ 	 
   
			alert( "Please Enter Specialization Other ");
			$("input#quali_spec_otherSpouse").focus();
			return false;
		   }
   }
 

if(passing_year.trim()=="") {
	alert("Please Enter Year of Passing");
	$("input#spouse_yrOfPass").focus();
	return false;
}
if(dateofBirthyear!=null){
if(passing_year.trim() != "") {
	if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
		alert("Please Enter Valid Year of Passing");
		$("input#spouse_yrOfPass").focus();
		return false;
	}
}}

if(div_class == "0") {
	alert("Please Select The Div/Grade");
	$("#spouse_div_class").focus();
	return false;
}
if(div_class=="4") {
	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 

		alert( "Please Enter Div/Grade Other ");
		$("input#class_otherSpouse").focus();
		return false;
	   }
}
if(subject.trim()=="") {
	alert("Please Select The Subject");
	$("select#spouse_sub_quali").focus();
	return false;
}
if(institute.trim()=="") {
	alert("Please Enter The Institute & place");
	$("#spouse_institute_place").focus();
	return false;
}
	$.post('updated_spouse_qualification_action?' + key + "=" + value, {
		type: type,
		examination_pass: examination_pass,
		passing_year: passing_year,
		div_class: div_class,
		subject: subject,
		institute: institute,
		qualification_ch_id: qualification_ch_id,
		q_id: q_id,
		spouse_id:spouse_id,
		degree: degree,
		specialization: specialization,
		com_id: com_id,
		dateofBirthyear: dateofBirthyear,
		exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,		
		degree_other:degree_other,spec_other:spec_other
	}, function(data) {
		if(parseInt(data) > 0) {
			
			
			
			if(($('#marital_event').val()=='1' && curr_marital_status==10)  || $('#marital_event').val()=='3'){
				$("#spouse_quali_radioDiv").show();
				$("#spouse_quali_radio1").prop("checked", true);
				$("#spouse_Qualifications").show();
			}
			else{
				$("#spouse_quali_radioDiv").hide();
			}
			
			if(curr_marital_status==8){
				$("#marr_quali_radio2").prop("checked", true);
				$("#marr_quali_radio1").prop("disabled", true);
			}
			
			alert("Data Saved/Updated Successfully")
			$('#spouse_qualification_ch_id').val(data);
		} else alert(data)
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	}

function remove_spouse_qualificationFn(){
	var comm_id=$('#comm_id').val();	
	  $.post('updated_family_marriage_delete_action?' + key + "=" + value, {comm_id:comm_id,qali_status:1}, function(data){ 
	                   if(data=='1'){
	                	   
	                	   $('#spouse_qualification_ch_id').val(0);
	                	  
// 	                       alert("Data Deleted Successfully");
// 	                       $("#marr_save").hide();    
// 	                       $("#marriage_remarriageDiv").hide();
// 	               		$("#divorceDiv").hide();
// 	               		$("#divorce_date").val("");
// 	               		$("#marital_status").val('0');
// //	                		$("#spouse_quali_radioDiv").hide();              		
// 	               		$('#MaritalForm').hide();


	                   }	
	                   
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  });
}

function getSpouseQualificationData() {
	debugger;
	var q_id = $('#census_id').val();
	var i = 0;
	$.post('getSpouseQualificationData?' + key + "=" + value, {
		q_id: q_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#spouse_yrOfPass').val(j[0].passing_year);
			$('#spouse_div_class').val(j[0].div_class);
			$('#spouse_institute_place').val(j[0].institute);
			$('#spouse_qualification_ch_id').val(j[0].id);
			$("input[type=checkbox][name='spouse_multisub']").prop("checked", false);
			var subjectslist = j[0].subject.split(',');
			for(k = 0; k < subjectslist.length; k++) {
				$("input[type=checkbox][name='spouse_multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
				$("#spouse_sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
			}
			$('#spouse_quali_sub_list').empty()
			$("input[type='checkbox'][name='spouse_multisub']").each(function() {
				if(this.checked) {
					$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn(" + this.value + ")'></i><span> <br>");
				}
			});
			$("#spouse_checkboxes").show();
			$('#spouse_quali_type').val(j[0].type);
			var typethisT = document.getElementById('spouse_quali_type');
			fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
			
			if(j[0].examination_pass != null) {
				$('#spouse_quali').val(j[0].examination_pass);
				var qualithisT = document.getElementById('spouse_quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree_spouse','spouse_specialization');
			}
			
			if(j[0].degree != null) {
				$('#quali_degree_spouse').val(j[0].degree);
				fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
				
			}
			if(j[0].specialization != null) {
			$('#spouse_specialization').val(j[0].specialization);
			 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
			}
			
			
			
			
		
			
			
			var examother="";
			var classother="";
			var deg_other="";
			var spec_other="";
			
			if(j[i].exam_other!=null)
	  			examother=j[i].exam_other;
	  	    if(j[i].class_other!=null)
	  	    	classother=j[i].class_other;
	  	    
	  	  if(j[i].degree_other!=null)
	  		deg_other=j[i].degree_other;
	  	   if(j[i].specialization_other!=null)
	  	    	spec_other=j[i].specialization_other;
	  	    
	  	
	 	  	$('#exam_otherSpouse').val(examother);
	 	
	 		 $('#class_otherSpouse').val(classother);
	 	 
	 		 $('#quali_deg_otherSpouse').val(deg_other);
	 	
	 		 $('#quali_spec_otherSpouse').val(spec_other);
	 		 
	 		 fn_otherShowHide(document.getElementById('spouse_div_class'),'other_div_classSpouse','class_otherSpouse');
//	 		 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
//	 		 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
//	 		 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
	 	 
			 $('#spouse_Qualifications').show();
			  if( curr_marital_status==10  || curr_marital_status==9 || curr_marital_status==11 || curr_marital_status==12 ){
					$("#spouse_quali_radioDiv").show();
					$("#spouse_quali_radio1").prop("checked", true);
					 $('#spouse_quali_labelDiv').hide();
				}
			  else if(curr_marital_status==8){
					$("#marr_quali_radio2").prop("checked", true);
					$("#marr_quali_radio1").prop("disabled", true);
					//$('#marriage_remarriageDiv').hide();
					  $('#mrgbtn_save').hide();
						$("#spouse_quali_radioDiv").hide();
					  $('#spouse_quali_labelDiv').show();
					  $('#mrgqualibtn_save').show();
				}
				
		}
	});
}


function fn_qualification_typeChange(obj,set_id,sec_id,th_id) {
	var q_type = obj.value;
	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(q_type!='0'){
		$.post('GetExaminatonPassed?' + key + "=" + value, {
			q_type: q_type
			
		}, function(j) {
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
		});
	}
	
	var qualithisT = document.getElementById(set_id);
	fn_ExaminationTypeChange(qualithisT,sec_id,th_id);
	
	
	
}

function fn_ExaminationTypeChange(obj,set_id,sec_id) {
	var e_pass =  obj.value;

// 	var qualification = $("select#quali_type").val();
	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(e_pass!='0'){
		$.post('GetExaminatonPassedDegree?' + key + "=" + value, {
			e_pass: e_pass
			
		}, function(j) {
			
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
			
		});
	}
	

	 fn_other_exam();	
	 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
	 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
	 
	 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
	 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
	 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
}



var other="OTHERS";
function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	if(thisText==other){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
}
function marital_eventchange(){
	

	var marital_eventvalue=$('#marital_event').val();
	if(marital_eventvalue=='1' || marital_eventvalue=='3'){
		$("#marriage_remarriageDiv").show();
		$("#divorceDiv").hide();
 		$("#divorce_date").val("");		
		$("#marital_status").val('8');
		$("#spouse_quali_radioDiv").show();
		if(curr_marital_status==13){
			$("#seperateDiv").show();
 			$("#separated_from_dtDiv").show();
 			$("#separated_to_dtDiv").show();
 			$("#separated_to_dt").val('');
 			getfamily_marriageDetails();
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
		
	}
	else if(marital_eventvalue=='2'){
		$("#marital_status").val('9');
		getfamily_marriageDetails();
		$("#divorceDiv").show();
		$("#divorce_widow_widower_dtlbl").text("Date of Divorce")
		$("#spouse_quali_radioDiv").hide();
		if(curr_marital_status==13){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
	}
	else if( marital_eventvalue=='4'){
		$("#marital_status").val('13');
		getfamily_marriageDetails();
		$("#spouse_quali_radioDiv").hide();
		$("#divorceDiv").hide();
		$("#seperateDiv").show();
 		$("#separated_from_dtDiv").show();
	}
	else if( marital_eventvalue=='5'){
		$("#marital_status").val('11');
		getfamily_marriageDetails();
		$("#spouse_quali_radioDiv").hide();
		$("#divorceDiv").show();
		$("#divorce_widow_widower_dtlbl").text("Date of Death")
		if(curr_marital_status==13){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
// 		$("#divorce_date").val("");
	}
	else if( marital_eventvalue=='6'){
		$("#marital_status").val('12');
		getfamily_marriageDetails();
		$("#spouse_quali_radioDiv").hide();
		$("#divorceDiv").show();
		$("#divorce_widow_widower_dtlbl").text("Date of Death")
		if(curr_marital_status==13){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
		}
		if(curr_marital_status==8){
			$("#seperateDiv").hide();
 			$("#separated_from_dtDiv").hide();
 			$("#separated_to_dtDiv").hide();
 			$("#separated_to_dt").val('');
 			$("#separated_from_dt").val('');
		}
// 		$("#divorce_date").val("");
	}
	else{
		$("#marriage_remarriageDiv").hide();
		$("#divorceDiv").hide();
 //		$("#divorce_date").val("");
		$("#marital_status").val('0');
		$("#spouse_quali_radioDiv").hide();
	}
	
	if((marital_eventvalue==1 && curr_marital_status==10)  || marital_eventvalue==3){
		$("#spouse_quali_radioDiv").show();
	}
	else{
		$("#spouse_quali_radioDiv").hide();
	}
	
	
	}
	




function fn_setSpecializationSpouse(query){
	
	$.post("getSpecialization?"+key+"="+value, {query:query}).done(function(j) {
		 	
		 	
	 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
			
					options += '<option   value="' + j[i] + '" name="'+j[i]+'" >'+ j[i] + '</option>';
				
			}
			
			$("select#spouse_specialization").html(options);
			
		}).fail(function(xhr, textStatus, errorThrown) {
});
}


function spouse_quali_radioFn(){
	
	var a = $('input:radio[name=spouse_quali_radio]:checked').val();
	if(a.toLowerCase() == "yes"){	
		$("#spouse_Qualifications").show();
		}
	else{	
		$("#spouse_Qualifications").hide();
		}
	}
	
function showCheckboxesSpouse() {

	 $("#spouse_checkboxes").toggle();
	$("#spouse_searchSubject").val(''); 
	 
	 $('.spouse_subjectlist').each(function () {       
	 $(this).show()
	})
	}
	
	
$("input[type='checkbox'][name='spouse_multisub']").click(function() {
    // access properties using this keyword
    var num=0;
    $('#spouse_quali_sub_list').empty()
    $("input[type='checkbox'][name='spouse_multisub']").each(function () {  
        if ( this.checked ) {
        	$('#spouse_quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn("+this.value+")'></i><span> <br>");
            num=num+1;
            
            
        }
        
    });
    if(num!=0)
        $("#spouse_sub_quali option:first").text('Subjects('+num+')');
    else
        $("#spouse_sub_quali option:first").text("Select Subjects");
});

function removeSpouseSubFn(value){
	$("input[type='checkbox'][name='spouse_multisub'][value='" + value + "']").attr('checked', false);
	
	 var num=0;
	 $('#spouse_quali_sub_list').empty()
	    $("input[type='checkbox'][name='spouse_multisub']").each(function () {  
	        if ( this.checked ) {
	        	$('#spouse_quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn("+this.value+")'></i><span> <br>");
	            num=num+1;
	            
	            
	        }
	        
	    });
	    if(num!=0)
	        $("#spouse_sub_quali option:first").text('Subjects('+num+')');
	    else
	        $("#spouse_sub_quali option:first").text("Select Subjects");
	
}
//***************** END MARRIED DETAILS************************//
</script>
<script>
//***************** START FIRING STANDARD DETAILS************************//

	// unit name
	/*  $("input#unit_posted_to").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_posted_to");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
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
				        	  alert("Please Enter Approved Unit Name.");
				        	  document.getElementById("unit_posted_to").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
				    
					            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#unit_sus_no").val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		}); */
		
		
	 function Firing_UnitAuto(obj) {
			
			var check_id = obj.id;
			var k = check_id.replace('firing_unit_name','');
			//var sub_cat_id = $("#sub_cat_id").val();
			// Nomen AutoComplete
			var wepetext=$("#"+obj.id);
		    wepetext.autocomplete({
		        source: function( request, response ) {
		          $.ajax({
		          type: 'POST',
		          url: "PSG_CADET_getTargetUnitsNameActiveList?"+key+"="+value,
		          data: {unit_name:$("#"+obj.id).val()},
		            success: function( data ) { 
		                      var codeval = [];
		                      var length = data.length-1;
		                      var enc = data[length].substring(0,16);
		                          for(var i = 0;i<data.length;i++){
		                                  codeval.push(dec(enc,data[i]));
		                          } 
		                          var dataCountry1 = codeval.join("|");
		                          var myResponse = []; 
		                      var autoTextVal=wepetext.val();
		                                  $.each(dataCountry1.toString().split("|"), function(i,e){
		                                          var newE = e.substring(0, autoTextVal.length);
		                                  if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
		                                            myResponse.push(e);
		                                          } 
		                          });                        
		                          response( myResponse );  
		            }
		          });
		        },
		        minLength: 1,
		        autoFill: true,
		        change: function(event, ui) {
		               if (ui.item) { 
		                    return true;                        
		            }  
		               else {
		   				alert("Please Enter Condacted at Unit ");			
		   				wepetext.val("");
		   				wepetext.focus();
		   				return false;
		   			}
		        }, 
		        select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#firing_unit_sus_no"+k).val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 
		      });
		} 


function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}
function Checkyear(obj){
    var d = new Date();
    var year = d.getFullYear() - 10;
    if(obj.value > year ){
            $("#"+obj.id).focus();
            alert("Please Enter The Valid Year");
            $("#"+obj.id).val("");
    }
}
/////////////////////For data get fire standard
function fire_Details() {
	var census_id = $('#census_id').val();
	var comm_id = $('#comm_id').val();
	var i = 0;
	$.post('getfire_detailsData?' + key + "=" + value,{census_id : census_id,comm_id:comm_id},function(j) {
		
		var v = j.length;
		if (v != 0) {
			$('#fire_stdtbody').empty();
			for (i; i < v; i++) {
				x = i + 1;
				$("table#fire_std_table").append('<tr id="tr_fire_std'+x+'">'
					+'<td class="fire_srno">'+x+'</td>'
					+ '<td style="width: 10%;">'
					+ '<select name="firing_grade'+x+'" id="firing_grade'+x+'" class="form-control" onchange="onFiringGrade('+x+')">'
					+'<option value="-1">--Select--</option>'
					+'<c:forEach var="item" items="${getFiring_grade}" varStatus="num">'
                    +' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
                    +' </c:forEach>'
					+'</select> </td>'
					
					+'<td style="width: 10%;"> <input type="text" id="ot_firing_grade'+x+'" name="ot_firing_grade'+x+'" value="'+j[i].ot_firing_grade+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
					
					
					+ '<td style="width: 10%;">'
					+ '<select name="firing_event_qe'+x+'" id="firing_event_qe'+x+'" class="form-control"   >'
					+'<option value="-1">--Select--</option>'
					+'<c:forEach var="item" items="${getFiring_event_qe}" varStatus="num">'
                    +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
                    +'</c:forEach>'
					+ '</select> </td>'
					+ '<td style="width: 10%;">'
					+ '<input type="text" id="year'+x+'" name="year'+x+'" value="'+j[i].year+'" class="form-control" autocomplete="off"  maxlength="4"  onkeypress="return isNumberPointKey(event);">'
					+ '</td>'
					+ '<td style="width: 10%;">'
					+'<input type="text" id="firing_unit_name'+x+'" name="firing_unit_name'+x+'" class="form-control autocomplete" onkeypress="Firing_UnitAuto(this);" autocomplete="off">'
					+'<input type="hidden" id="firing_unit_sus_no'+x+'" name="firing_unit_sus_no'+x+'" value="'+j[i].firing_unit_sus_no+'" class="form-control autocomplete" autocomplete="off">'
					+ '</td>'
					+ '<td style="display:none;"><input type="text" id="fire_id'+x+'" name="fire_id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "fire_std_save'+ x + '" onclick="fire_std_save_fn('+ x+ ');" ><i class="fa fa-save"></i></a>'
					+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "fire_std_add'+ x+ '" onclick="fire_std_add_fn('+ x+ ');" ><i class="fa fa-plus"></i></a>'
                    + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "fire_remove'+ x+ '" onclick="fire_remove_fn('+ x + ');"><i class="fa fa-trash"></i></a>'
					+ '<td style="display:none;"><input type="text" id="status'+x+'" name="status'+x+'"   value="'+j[i].status+'"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '</td></tr>');
				$('#firing_event_qe'+x).val(j[i].firing_event_qe);
				$('#firing_grade'+x).val(j[i].firing_grade);
				onFiringGrade(x);
				var sus_no =j[i].firing_unit_sus_no;
				fn_getUnitnamefromSus(sus_no, document.getElementById("firing_unit_name"+x));
				
			 /*  	var sus_no =j[i].firing_unit_sus_no;
		    	getunit(sus_no);
		    	function getunit(val) {	
		            $.post("getTargetUnitNameList?"+key+"="+value, {
		            	sus_no : sus_no
		        }, function(j) {
		                //var json = JSON.parse(data);
		                //...
		        }).done(function(j) {
		    				   var length = j.length-1;
		    	                   var enc = j[length].substring(0,16); 
		    	                   $('#firing_unit_name'+x).val(dec(enc,j[0])); 
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
		    } */ 
			}
			fs = v;
			fire_srno = v;
			$('#fire_std_add' + fs).show();
		}
	});
}

function onFiringGrade(ind){
	var firing_grade = $("#firing_grade"+ind+" option:selected").text();
	 
	if(firing_grade != "OTHERS"){
		$('#ot_firing_grade'+ind).val("");
		$('#ot_firing_grade'+ind).attr('readonly', true);
	}
	else
		$('#ot_firing_grade'+ind).attr('readonly', false);
}



//////////////////fire save function ///////////
function fire_std_save_fn(ps){
	var d = new Date();	
	var cyear = d.getFullYear();
	if ($("select#firing_grade"+ps).val() == "-1") {
	alert("Please Select Firing Grade");
	$("#firing_grade"+ps).focus();
	return false;
	}
	var ot_firing_grade=$('#ot_firing_grade'+ps).val();
	var firing_gradeV = $("#firing_grade"+ps+" option:selected").text();
	if (firing_gradeV == "OTHERS" && ot_firing_grade.trim()=="") {
		alert("Please Enter Other Firing Grade");
		$('#ot_firing_grade'+ps).focus();
		return false;
	}
	
	if ($("select#firing_event_qe"+ps).val() == "-1") {
	alert("Please Select Firing Event QTR");
	$("#firing_event_qe"+ps).focus();
	return false;
	}
	if($("#year"+ps).val().trim()=="" || $("#year"+ps).val().trim()=="0"){
	alert("Please Enter The Year");
	$("#year"+ps).focus();
	return false;
	}	
	if($("#year"+ps).val() > cyear){
	alert("Future Year is not allowed");
	$("#year"+ps).focus();
	 return false;
	}  
	if($("#year"+ps).val() == cyear && fire_validate($("select#firing_event_qe"+ps).val())==0){
	alert("Future Month is not allowed");
	$("#firing_event_qe"+ps).focus();
	 return false;
	} 
	
	var firing_unit_sus_no=$('#firing_unit_sus_no'+ps).val()
	    
		if($("#firing_unit_name"+ps).val().trim()==""){
			alert("Please Enter Conducted At Unit");
			$("#firing_unit_name"+ps).focus();
			return false;
		}
		if(firing_unit_sus_no.trim()==""){
			alert("Please Enter Valid Conducted At Unit");
			$("#firing_unit_name"+ps).focus();
			return false;
		}
		
	var firing_grade=$('#firing_grade'+ps).val();
	
	var firing_event_qe=$('#firing_event_qe'+ps).val();
	var year=$('#year'+ps).val();
	
	var fire_id=$('#fire_id'+ps).val();
	var census_id=$('#census_id').val();
	var com_id=$('#comm_id').val();

// 	if (firing_grade == "-1") {
// 	alert("Please Enter Firing Grade");
// 	$("input#firing_grade").focus();
// 	return false;
// 	}

	

// 	if (firing_event_qe == "-1") {
// 	alert("Please Select Firing Event  QTR");
// 	$("input#firing_event_qe").focus();
// 	return false;
// 	}
// 	if(year.trim()==""){
// 	alert("Please Enter The Year");
// 	return false;
// 	}	
// 	if(firing_unit_sus_no.trim()==""){
// 	alert("Please Enter Conducted at Unit");
// 	return false;
// 	}	
// 	if($('#year'+ps).val() == cyear && fire_validate($('#firing_event_qe'+ps).val())==0){
// 		alert("Future Month is not allowed"); 		
// 		 return false;
// 	}  	

// 	if($('#firing_unit_name'+ps).val().trim()==""){
// 			alert("Please Enter Conducted at Unit"); 		
// 			 return false;
// 	} 

	$.post('fire_std_action?' + key + "=" + value, {firing_grade:firing_grade,ot_firing_grade:ot_firing_grade,firing_event_qe:firing_event_qe,
	   year:year,firing_unit_sus_no:firing_unit_sus_no,fire_id:fire_id,census_id:census_id,com_id:com_id }, function(data){
	      if(data=="update")
	    	  alert("Data Updated Successfully");
	      else if(parseInt(data)>0){
	    	 $('#fire_id'+ps).val(data);
	    	  alert("Data Saved Successfully")
	    	  $("#fire_std_add"+ps).show();
	        	 $("#fire_remove"+ps).show();
	      }
	      else
	    	  alert(data);
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	}
////FIRE add//////////
fs=1;
fire_srno=1;
function fire_std_add_fn(q){
if( $('#fire_std_add'+q).length )        
{
	$("#fire_std_add"+q).hide();
}
if(q!=0)
 fire_srno+=1;
fs=q+1;
$("table#fire_std_table").append('<tr id="tr_fire_std'+fs+'">'
+'<td class="fire_srno">'+fire_srno+'</td>'
+'<td style="width: 10%;"> '
+'<select name="firing_grade'+fs+'" id="firing_grade'+fs+'" class="form-control" onchange="onFiringGrade('+fs+')"   >'
+'<option value="-1">--Select--</option>'
+'<c:forEach var="item" items="${getFiring_grade}" varStatus="num">'
+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
+'</c:forEach>'
+'</select></td>'

+'<td style="width: 10%;"> <input type="text" id="ot_firing_grade'+fs+'" name="ot_firing_grade'+fs+'" maxlength="50" onkeypress="return onlyAlphabets(event);"readonly  class="form-control autocomplete" autocomplete="off" ></td>'


+'<td style="width: 10%;"> '
+'<select name="firing_event_qe'+fs+'" id="firing_event_qe'+fs+'" class="form-control" >'
+'<option value="-1">--Select--</option>'
+'<c:forEach var="item" items="${getFiring_event_qe}" varStatus="num">'
+' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
+'</c:forEach>'
+'</select></td>'
+'<td style="width: 10%;">	<input type="text" id="year'+fs+'" name="year'+fs+'" value="0" class="form-control autocomplete"   autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
+'</td>'
+ '<td style="width: 10%;">'
+'<input type="text" id="firing_unit_name'+fs+'" name="firing_unit_name'+fs+'" class="form-control autocomplete" onkeypress="Firing_UnitAuto(this);" autocomplete="off">'
+'<input type="hidden" id="firing_unit_sus_no'+fs+'" name="firing_unit_sus_no'+fs+'" class="form-control autocomplete" autocomplete="off">'
+ '</td>'
+'<td style="display:none;"><input type="text" id="fire_id'+fs+'" name="fire_id'+fs+'"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "fire_std_save'+fs+'" onclick="fire_std_save_fn('+fs+');" ><i class="fa fa-save"></i></a>'
+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "fire_std_add'+fs+'" onclick="fire_std_add_fn('+fs+');" ><i class="fa fa-plus"></i></a>'
+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "fire_remove'+fs+'" onclick="fire_remove_fn('+fs+');"><i class="fa fa-trash"></i></a>' 
+'</td></tr>');
}

//////////////////fire remove function ///////
function fire_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	var fire_id=$('#fire_id'+R).val();
	  $.post('fire_delete_action?' + key + "=" + value, {fire_id:fire_id }, function(data){ 
			   if(data=='1'){
				   $("tr#tr_fire_std"+R).remove(); 
					 if(R==fs){
						 R = R-1;
						 var temp=true;
						 for(i=R;i>=1;i--){
						 if( $('#fire_std_add'+i).length )         
						 {
							 $("#fire_std_add"+i).show();
							 temp=false;
							 fs=i;
							 break;
						 }}
						 if(temp){
							 fire_std_add_fn(0);
							}
			  			 }
					 $('.fire_srno').each(function(i, obj) {
						 
							obj.innerHTML=i+1;
							fire_srno=i+1;
							 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}
//***************** END FIRING STANDARD DETAILS************************//
</script>

<script>
//***************** START BPET DETAILS************************//

	 function BPET_UnitAuto(obj) {
			
			var check_id = obj.id;
			var k = check_id.replace('b_unit_name','');
			//var sub_cat_id = $("#sub_cat_id").val();
			// Nomen AutoComplete
			var wepetext=$("#"+obj.id);
		    wepetext.autocomplete({
		        source: function( request, response ) {
		          $.ajax({
		          type: 'POST',
		          url: "PSG_CADET_getTargetUnitsNameActiveList?"+key+"="+value,
		          data: {unit_name:$("#"+obj.id).val()},
		            success: function( data ) { 
		                      var codeval = [];
		                      var length = data.length-1;
		                      var enc = data[length].substring(0,16);
		                          for(var i = 0;i<data.length;i++){
		                                  codeval.push(dec(enc,data[i]));
		                          } 
		                          var dataCountry1 = codeval.join("|");
		                          var myResponse = []; 
		                      var autoTextVal=wepetext.val();
		                                  $.each(dataCountry1.toString().split("|"), function(i,e){
		                                          var newE = e.substring(0, autoTextVal.length);
		                                  if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
		                                            myResponse.push(e);
		                                          } 
		                          });                        
		                          response( myResponse );  
		            }
		          });
		        },
		        minLength: 1,
		        autoFill: true,
		        change: function(event, ui) {
		               if (ui.item) { 
		                    return true;                        
		            }  
		               else {
		   				alert("Please Enter Condacted at Unit ");			
		   				wepetext.val("");
		   				wepetext.focus();
		   				return false;
		   			}
		        }, 
		        select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#unit_sus_no"+k).val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 
		      });
		} 

function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}
function onBeptResult(ind){
	var BPET_result = $("#BPET_result"+ind+" option:selected").text();
	if(BPET_result != "OTHERS"){
		$('#bept_result_others'+ind).val("");
		$('#bept_result_others'+ind).attr('readonly', true);
	}
	else
		$('#bept_result_others'+ind).attr('readonly', false);
}
function bpet_save_fn(ps){
	var d = new Date();
	var cyear = d.getFullYear(); 
	if ($("select#BPET_result"+ps).val() == "-1") {
		alert("Please Select BPET Result");
		$("#BPET_result"+ps).focus();
		return false;
	}
	var bept_result_others=$('#bept_result_others'+ps).val();
	var BPET_resultV = $("#BPET_result"+ps+" option:selected").text();
	if (BPET_resultV == "OTHERS" && bept_result_others.trim()=="") {
		alert("Please Enter Other BPET Results");
		$('#bept_result_others'+ps).focus();
		return false;
	}
 	if ($("select#BPET_qe"+ps).val() == "-1") {
		alert("Please Select BPET QTR");
		$("#BPET_qe"+ps).focus();
		return false;
	}
 	if($("#bpet_year"+ps).val().trim()=="" || $("#bpet_year"+ps).val().trim()=="0"){
		alert("Please Enter The Year");
		$("#bpet_year"+ps).focus();
		return false;
	}	 
    if($("#bpet_year"+ps).val() > cyear){
		alert("Future Year cannot be allowed");
		$("#bpet_year"+ps).focus();
		return false;
	} 
    if($("#bpet_year"+ps).val() == cyear && fire_validate($("select#BPET_qe"+ps).val())== 0){
		alert("Future Month is not allowed");
		$("#BPET_qe"+ps).focus();
		 return false;
	} 
    var unit_sus_no=$('#unit_sus_no'+ps).val();
    
	if($("#b_unit_name"+ps).val().trim()==""){
		alert("Please Enter Conducted At Unit");
		$("#b_unit_name"+ps).focus();
		return false;
	}
	if(unit_sus_no.trim()==""){
		alert("Please Enter Valid Conducted At Unit");
		$("#b_unit_name"+ps).focus();
		return false;
	}
	
	var BPET_result=$('#BPET_result'+ps).val();
	
	
	var BPET_qe=$('#BPET_qe'+ps).val();
	var year=$('#bpet_year'+ps).val();
	
	var id=$('#id'+ps).val();
	var census_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	if (BPET_result == "-1") {
		alert("Please Enter BPET Result");
		$("input#BPET_result").focus();
		return false;
	}
	

	
	
//  	if (BPET_qe == "-1") {
// 		alert("Please Select BPET QTR");
// 		$("input#BPET_qe").focus();
// 		return false;
// 	}
//  	if(year.trim()==""){
// 		alert("Please Enter The Year");
// 		return false;
// 	}	 
//     if($('#bpet_year'+ps).val() > cyear){
// 		alert("Future Year cannot be allowed");
// 		return false;
// 	}
//     if($('#bpet_year'+ps).val() == cyear && fire_validate($('#BPET_qe'+ps).val())==0){
//  		alert("Future Month is not allowed"); 		
//  		 return false;
//  	}  
    
//     if($('#b_unit_name'+ps).val().trim()==""){
//  		alert("Please Enter Conducted at Unit"); 		
//  		 return false;
//  	}  
	   $.post('BPET_action?' + key + "=" + value, {BPET_result : BPET_result ,bept_result_others:bept_result_others,BPET_qe : BPET_qe,
		   year:year,unit_sus_no:unit_sus_no,id : id,census_id :census_id,com_id:com_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#id'+ps).val(data);
	        	  alert("Data Saved Successfully")
	        	  $("#bpet_add"+ps).show();
		        	 $("#bpet_remove"+ps).show();
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function bpet_Details() {
	var census_id = $('#census_id').val();
	var comm_id = $('#comm_id').val();
	var i = 0;
	$.post('getbpet_Data?' + key + "=" + value,{census_id : census_id,comm_id:comm_id},function(j) {
		var v = j.length;
		if (v != 0) {
			$('#bpettbody').empty();
			for (i; i < v; i++) {
				x = i + 1;
				$("table#bpet_table").append('<tr id="tr_bpet'+x+'">'
					+'<td class="bpet_srno">'+x+'</td>'
					+ '<td style="width: 10%;">'
					+ '<select name="BPET_result'+x+'" id="BPET_result'+x+'" onchange="onBeptResult('+x+')" class="form-control">'
					+'<option value="-1">--Select--</option>'
                    +'<c:forEach var="item" items="${getBPET_result}" varStatus="num">'
                    +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
                   +' </c:forEach>'
					+'</select> </td>'
					+'<td style="width: 10%;">' 
					+'<input type="text" id="bept_result_others'+x+'" name="bept_result_others'+x+'" maxlength="50" onkeypress="return onlyAlphabets(event);" value="' + j[i].bept_result_others + '"  class="form-control autocomplete" autocomplete="off" >'
					+'</td>'
					
					
					+ '<td style="width: 10%;">'
					+ '<select name="BPET_qe'+x+'" id="BPET_qe'+x+'" class="form-control">'
					+' <option value="-1">--Select--</option>'
                    +'<c:forEach var="item" items="${getBPET_event_qe}" varStatus="num">'
                    +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
                    +' </c:forEach>'
					+ '</select> </td>'
					+ '<td style="width: 10%;">'
					+ '<input type="text" id="bpet_year'+x+'" name="year'+x+'" value="'+j[i].year+'" class="form-control"  autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
					+ '</td>'
					+'<td style="width: 20%;">'
					+'<input type="text" id="b_unit_name'+x+'"" name="unit_name'+x+'"" class="form-control autocomplete" onkeypress="BPET_UnitAuto(this);" autocomplete="off">'
					+'<input type="hidden" id="unit_sus_no'+x+'" name="unit_sus_no'+x+'" value="'+j[i].unit_sus_no+'"  class="form-control autocomplete" autocomplete="off">'
					+'</td>'
					+ '<td style="display:none;"><input type="text" id="id'+x+'" name="id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "bpet_save'+ x + '" onclick="bpet_save_fn('+ x+ ');" ><i class="fa fa-save"></i></a>'
					+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "bpet_add'+ x+ '" onclick="bpet_add_fn('+ x+ ');" ><i class="fa fa-plus"></i></a>'
                    + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "bpet_remove'+ x+ '" onclick="bpet_remove_fn('+ x + ');"><i class="fa fa-trash"></i></a>'
					+ '<td style="display:none;"><input type="text" id="status'+x+'" name="status'+x+'"   value="'+j[i].status+'"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '</td></tr>');
				$('#BPET_result'+x).val(j[i].bpet_result);
				$('#BPET_qe'+x).val(j[i].bpet_qe);
				onBeptResult(x);
				var sus_no =j[i].unit_sus_no;
				fn_getUnitnamefromSus(sus_no, document.getElementById("b_unit_name"+x));
				
			/*   	var sus_no =j[i].unit_sus_no;
		    	getunit(sus_no);
		    	function getunit(val) {	
		            $.post("getTargetUnitNameList?"+key+"="+value, {
		            	sus_no : sus_no
		        }, function(j) {
		                //var json = JSON.parse(data);
		                //...
		        }).done(function(j) {
		    				   var length = j.length-1;
		    	                   var enc = j[length].substring(0,16); 
		    	                   $('#unit_name'+x).val(dec(enc,j[0])); 
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
		    }  */
			}
			bpet = v;
			bpet_srno = v;
			$('#bpet_add' + bpet).show();
		}
	});
}
bpet =1;
bpet_srno=1;
function bpet_add_fn(q){
	 if( $('#bpet_add'+q).length )        
	 {
			$("#bpet_add"+q).hide();
	 }
	 if(q!=0)
		 bpet_srno+=1;
	 bpet=q+1;
	$("table#bpet_table").append('<tr id="tr_bpet'+bpet+'">'
	+'<td class="bpet_srno">'+bpet_srno+'</td>'
	+'<td style="width: 10%;"> '
	+'<select name="BPET_result'+bpet+'" id="BPET_result'+bpet+'" class="form-control"  onchange="onBeptResult('+bpet+')" >'
	+'<option value="-1">--Select--</option>'
    +'<c:forEach var="item" items="${getBPET_result}" varStatus="num">'
    +'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
    +'</c:forEach>'
	+'</select></td>'
  	+'<td style="width: 10%;"> <input type="text" id="bept_result_others'+bpet+'" name="bept_result_others'+bpet+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'

	+'<td style="width: 10%;"> '
	+'<select name="BPET_qe'+bpet+'" id="BPET_qe'+bpet+'" class="form-control"   >'
	+' <option value="-1">--Select--</option>'
    +' <c:forEach var="item" items="${getBPET_event_qe}" varStatus="num">'
    +' <option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
    +'</c:forEach>'
	+'</select></td>'
	+'<td style="width: 10%;">	<input type="text" id="bpet_year'+bpet+'" name="year'+bpet+'" value="0" class="form-control autocomplete"   autocomplete="off" maxlength="4" onkeypress="return isNumberPointKey(event);">'
	+'</td>'
	+'<td style="width: 20%;">'
	+'<input type="text" id="b_unit_name'+bpet+'"" name="unit_name'+bpet+'"" class="form-control autocomplete" onkeypress="BPET_UnitAuto(this);" autocomplete="off">'
	+'<input type="hidden" id="unit_sus_no'+bpet+'" name="unit_sus_no'+bpet+'" class="form-control autocomplete" autocomplete="off">'
	+'</td>'
	+'<td style="display:none;"><input type="text" id="id'+bpet+'" name="id'+bpet+'"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
	+'<td style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "bpet_save'+bpet+'" onclick="bpet_save_fn('+bpet+');" ><i class="fa fa-save"></i></a>'
	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "bpet_add'+bpet+'" onclick="bpet_add_fn('+bpet+');" ><i class="fa fa-plus"></i></a>'
	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "bpet_remove'+bpet+'" onclick="bpet_remove_fn('+bpet+');"><i class="fa fa-trash"></i></a>' 
	+'</td></tr>');
}
//////////////////BPET remove function ///////
function bpet_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	var id=$('#id'+R).val();
	  $.post('bpet_delete_action?' + key + "=" + value, {id:id }, function(data){ 
			   if(data=='1'){
				   $("tr#tr_bpet"+R).remove(); 
					 if(R==bpet){
						 R = R-1;
						 var temp=true;
						 for(i=R;i>=1;i--){
						 if( $('#bpet_add'+i).length )         
						 {
							 $("#bpet_add"+i).show();
							 temp=false;
							 bpet=i;
							 break;
						 }}
						 if(temp){
							 bpet_add_fn(0);
							}
			  			 }
					 $('.bpet_srno').each(function(i, obj) {
							obj.innerHTML=i+1;
							bpet_srno=i+1;
							 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}
//***************** END BPET DETAILS************************//
</script>
<script>
//***************** START IDENTY CARD  DETAILS************************//

function idcard_other() {
	var text = $("#hair_colour option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#other_id").show();
	}
	else{
		$("#other_id").hide();
		$("#hair_other").val("");
	}
}
function ideys_other() {
	var text = $("#eye_colour option:selected").text();
	if(text == "OTHERS"){
		$("#other_eye").show();
	}
	else{
		$("#other_eye").hide();
		$("#eye_other").val("");
	}
}

function getIdentity_Card(){
	 var p_id=$('#census_id').val();
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('getIdentity_Card?' + key + "=" + value, {p_id:p_id,comm_id:comm_id }, function(j){
			var v=j.length;
			if(v!=0){
				if(j[i].status==0){
				$('#card_id').val(j[i].id);
				 $("#id_card_no").val(j[i].id_card_no);
				 $("#issue_dt").val(ParseDateColumncommission(j[i].issue_dt));
				 $("#issue_authority_sus").val(j[i].issue_authority);
				 fn_getUnitnamefromSus(j[i].issue_authority, document.getElementById("issue_authority"));
				 $("#id_marks").val(j[i].id_marks);
				 $("#hair_colour").val(j[i].hair_colour);
				 $("#eye_colour").val(j[i].eye_colour);
				  $('#identity_image_preview').attr("src", "censusIdentityConvertpath?i_id="+j[i].id+" ");
				  $("#image_updated_date").val(ParseDateColumn(j[i].image_updated_date));
				}
				else{
					  $('#identity_image_preview').attr("src", "censusIdentityConvertpath?i_id="+j[i].id+" ");
					  $("#image_updated_date").val(ParseDateColumn(j[i].image_updated_date));
					  $("#pre_identity_id").val(j[i].id);
				}
				
				var text = $("#hair_colour option:selected").text();
				if(text == "OTHERS"){
					$('#hair_other').val(j[i].hair_other);
					$("#other_id").show();
				}
				else{
					$("#other_id").hide();
				}
				
				var text = $("#eye_colour option:selected").text();
				if(text == "OTHERS"){
					$('#eye_other').val(j[i].eye_other);
					$("#other_eye").show();
				}
				else{
					$("#other_eye").hide();
				}
			}
	  });
}	


function validate_identity_card_save(){

	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var card_id=$('#card_id').val();
	var comm_date=$('#comm_date').val();
 	if($("#id_card_no").val().trim()==""){
		alert("Please Enter ID Card No");
		$("#id_card_no").focus();
		return false;
	}
	
	if($("#issue_dt").val() == "DD/MM/YYYY" || $("#issue_dt").val().trim()==""){
		alert("Please Enter Issue Date");
		$("#issue_dt").focus();
		return false;
	} 
	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#issue_dt").val())) {
		alert("Issue Date should be Greater than Commission Date");
		$("#issue_dt").focus();
		return false;
  }
	
	if($("#issue_authority").val().trim()==""){
		alert("Please Enter Issue Authority");
		$("#issue_authority").focus();
		return false;
	}
	
	
	
	if($("#id_marks").val().trim()==""){
		alert("Please Enter  Visible Identification Marks");
		$("#id_marks").focus();
		return false;
	}
	
	if($("#hair_colour").val() == 0){
		alert("Please Select Hair Colour");
		$("#hair_colour").focus();                 
		return false;
	}
	if($("select#hair_colour").val() == 7 &&  $("#hair_other").val().trim()=="" ){
		alert("Please Enter Hair Colour Other");
		$("#hair_other").focus();
		return false;
	} 
	
	if($("#eye_colour").val() == 0){
		alert("Please Select Eye Colour");
		$("#eye_colour").focus();
		return false;
	} 
	
	
	if($("select#eye_colour").val() == 28 &&  $("#eye_other").val().trim()=="" ){
		alert("Please Enter Eye Colour Other");
		$("#eye_other").focus();
		return false;
	}

	
	 var file = document.getElementById("identity_image"); 
	  var preDate = document.getElementById('image_updated_date').value;
	  // Below one is The single line logic to calculate The no. of years...
	  var years = new Date(new Date() - new Date(preDate)).getFullYear() - 1970;
	  
	  
	if(years>=8){
	  if(file.files.length == 0){
			  alert("Please Upload Current Photograph");			
				return false;        
	  }
	}
	  
	  
	 var form_data = new FormData(document.getElementById("form_identity_card"));		
		form_data.append("comm_id",comm_id);
		form_data.append("census_id",census_id);
		form_data.append("comm_date",comm_date);
   
		   $.ajax({
		        url: 'Identity_CardAction?_csrf='+value,
		        type: "POST",
		        data: form_data,
		        enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false
		      }).done(function(data) {
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#card_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
		      }).fail(function(jqXHR, textStatus) 
	 	  				{
	 	  					alert("fail to fetch")
	  		});
}
//***************** END IDENTY CARD  DETAILS************************//
</script>
<script>
//*************************************START ADDRESS DATA *****************************//

function spouse_addressFn(){
	if($("#check_spouse_address").is(':checked'))
	    $("#spouse_addressInnerdiv").show();  // checked
	else{
	    $("#spouse_addressInnerdiv").hide();
	    $("#spouse_addr_Country").val("0");
	    $("#spouse_addr_state").val("0");
	    $("#spouse_addr_district").val("0");
	    $("#spouse_addr_tehsil").val("0");
	    $("#spouse_addr_village").val("");
	    $("#spouse_addr_pin").val("");
	    $("#spouse_addr_rail").val("");
	    $("#spouse_addr_rural").prop('checked', false);
	    $("#spouse_addr_urban").prop('checked', false);
	    $("#spouse_addr_semi_urban").prop('checked', false);
	    $("#Stn_HQ_sus_no").val("");
	    $("#Stn_HQ_unit_name").val("");
	 	    
	}
}
function validate_changeaddress_details_save(){
// 	 if ($("input#addr_authority").val().trim()=="") {
// 		alert("Please Enter The Authority");
// 		$("input#addr_authority").focus();
// 		return false;
// 	}
// 	 if ($("input#addr_date_authority").val() == "DD/MM/YYYY" || $("input#addr_date_authority").val().trim()=="") {
// 		alert("Please Enter The Date of Authority");
// 		$("input#addr_date_authority").focus();
// 		return false;
// 	}  
	/*  if ($("input#addr_date_authority").val() != "DD/MM/YYYY" && $("input#addr_date_authority").val()!="") {
		
	 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#addr_date_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 } */

	if ($("select#pre_country").val() == "0") {
		alert("Please Select Present Domicile Country");
		$("select#pre_country").focus();
		return false;
	}
   var text1 = $("#pre_country option:selected").text();
	
	if(text1 == "OTHERS" && $("input#pre_country_other").val().trim()==""){
		alert("Please Enter Present Domicile Country Other");
		$("input#pre_country_other").focus();
		return false;
	}
	
	 if ($("select#pre_domicile_state").val() == "0") {
		alert("Please Select Present Domicile State");
		$("select#pre_domicile_state").focus();
		return false;
	} 
	 var text2 = $("#pre_domicile_state option:selected").text();
		
		if(text2 == "OTHERS" && $("input#pre_domicile_state_other").val().trim()==""){
			alert("Please Enter Present Domicile State Other");
			$("input#pre_domicile_state_other").focus();
			return false;
		}
	if ($("select#pre_domicile_district").val() == "0") {
		alert("Please Select Present Domicile District");
		$("select#pre_domicile_district").focus();
		return false;
	} 
	var text3 = $("#pre_domicile_district option:selected").text();
	
	if(text3 == "OTHERS" && $("input#pre_domicile_district_other").val().trim()==""){
		alert("Please Enter Present Domicile District Other");
		$("input#pre_domicile_district_other").focus();
		return false;
	}
	if ($("select#pre_domicile_tesil").val() == "0") {
		alert("Please Select Present Domicile Tehsil");
		$("select#pre_domicile_tesil").focus();
		return false;
	}
  var text4 = $("#pre_domicile_tesil option:selected").text();
	
	if(text4 == "OTHERS" && $("input#pre_domicile_tesil_other").val().trim()==""){
		alert("Please Enter Present Domicile Tehsil Other");
		$("input#pre_domicile_tesil_other").focus();
		return false;
	}
	
	if ($("select#per_home_addr_country").val() == "0") {
		alert("Please Select The Permanent Address Country");
		$("select#per_home_addr_country").focus();
		return false;
	}
	 var text5 = $("#per_home_addr_country option:selected").text();
		
		if(text5 == "OTHERS" && $("input#per_home_addr_country_others").val().trim()==""){
			alert("Please Enter Permanent Address Country Other");
			$("input#per_home_addr_country_others").focus();
			return false;
		}
		
	if ($("select#per_home_addr_state").val() == "0") {
		alert("Please Select Permanent Address State");
		$("select#per_home_addr_state").focus();
		return false;
	}
	 var text6 = $("#per_home_addr_state option:selected").text();
		
		if(text6 == "OTHERS" && $("input#per_home_addr_state_others").val().trim()==""){
			alert("Please Enter Permanent Address State Other");
			$("input#per_home_addr_state_others").focus();
			return false;
		}
	if ($("select#per_home_addr_district").val() == "0") {
		alert("Please Select Permanent Address District");
		$("select#per_home_addr_district").focus();
		return false;
	}
	 var text7 = $("#per_home_addr_district option:selected").text();
		
		if(text7 == "OTHERS" && $("input#per_home_addr_district_others").val().trim()==""){
			alert("Please Enter Permanent Address District Other");
			$("input#per_home_addr_district_others").focus();
			return false;
		}
	if ($("select#per_home_addr_tehsil").val() == "0") {
		alert("Please Select Permanent Address Tehsil");
		$("select#per_home_addr_tehsil").focus();
		return false;
	}
	var text8 = $("#per_home_addr_tehsil option:selected").text();
	
	if(text8 == "OTHERS" && $("input#per_home_addr_tehsil_others").val().trim()==""){
		alert("Please Enter Permanent Address Tehsil Other");
		$("input#per_home_addr_tehsil_others").focus();
		return false;
	}
	if ($("input#per_home_addr_village").val().trim()=="") {
		alert("Please Enter Permanent Address Village/Town/City");
		$("input#per_home_addr_village").focus();
		return false;
	}
	if ($("input#per_home_addr_pin").val().trim()=="" || $("input#per_home_addr_pin").val().length<6 || $("input#per_home_addr_pin").val().length>6) {
		alert("Please Enter Permanent Address Pin");
		$("input#per_home_addr_pin").focus();
		return false;
	}
	if ($("input#per_home_addr_rail").val().trim()=="") {
		alert("Please Enter Permanent Address Nearest Railway Station");
		$("input#per_home_addr_rail").focus();
		return false;
	}
	 var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
		if(a == undefined){	
			alert("Please select The Permanent Address  Rural /Urban/Semi Urban ");
			return false;
			}
		var b = $('input:radio[name=border_area]:checked').val();
		if(b == undefined){	
			alert("Please select The Permanent Address Border area ");
			return false;
			}
		if ($("select#pers_addr_country").val() == "0") {
			alert("Please Select Present Address Country");
			$("select#pers_addr_country").focus();
			return false;
		}
		var text9 = $("#pers_addr_country option:selected").text();
		
		if(text9 == "OTHERS" && $("input#pers_addr_country_other").val().trim()==""){
			alert("Please Enter Present Address Country Other");
			$("input#pers_addr_country_other").focus();
			return false;
		}
		if ($("select#pers_addr_state").val() == "0") {
			alert("Please select Present Address State");
			$("select#pers_addr_state").focus();
			return false;
		}
  var text10 = $("#pers_addr_state option:selected").text();
		
		if(text10 == "OTHERS" && $("input#pers_addr_state_other").val().trim()==""){
			alert("Please Enter Present Address State Other");
			$("input#pers_addr_state_other").focus();
			return false;
		}
		if ($("select#pers_addr_district").val() == "0") {
			alert("Please select Present Address District");
			$("select#pers_addr_district").focus();
			return false;
		}
var text11 = $("#pers_addr_district option:selected").text();
		
		if(text11 == "OTHERS" && $("input#pers_addr_district_other").val().trim()==""){
			alert("Please Enter Present Address District Other");
			$("input#pers_addr_district_other").focus();
			return false;
		}
		if ($("select#pers_addr_tehsil").val() == "0") {
			alert("Please Select Present Address Tehsil");
			$("select#pers_addr_tehsil").focus();
			return false;
		}
var text11 = $("#pers_addr_tehsil option:selected").text();
		
		if(text11 == "OTHERS" && $("input#pers_addr_tehsil_other").val().trim()==""){
			alert("Please Enter Present Address Tehsil Other");
			$("input#pers_addr_tehsil_other").focus();
			return false;
		}
	if ($("input#pers_addr_village").val().trim()=="") {
		alert("Please Enter Present Address Village/Town/City");
		$("input#pers_addr_village").focus();
		return false;
	}
	if ($("input#pers_addr_pin").val().trim()=="" || $("input#pers_addr_pin").val().length<6  || $("input#pers_addr_pin").val().length>6) {
		alert("Please Enter Present Address Pin");
		$("input#pers_addr_pin").focus();
		return false;
	}
	if ($("input#pers_addr_rail").val().trim()=="") {
		alert("Please Enter Present Address Nearest Railway Station");
		$("input#pers_addr_rail").focus();
		return false;
	}
	var c = $('input:radio[name=pers_addr_rural_urban]:checked').val();
	if(c == undefined){	
		alert("Please select The  Present Address Rural /Urban/Semi Urban ");
		return false;
		}
	
	if($("#check_spouse_address").is(':checked')){
		
		if ($("select#spouse_addr_Country").val() == "0") {
			alert("Please select SF ACCN Address Country");
			$("select#spouse_addr_Country").focus();
			return false;
		}	
var text12 = $("#spouse_addr_Country option:selected").text();
		
		if(text12 == "OTHERS" && $("input#spouse_addr_country_other").val().trim()==""){
			alert("Please Enter SF ACCN Address Country Other");
			$("input#spouse_addr_country_other").focus();
			return false;
		}
		if ($("select#spouse_addr_state").val() == "0") {
			alert("Please select SF ACCN Address State");
			$("select#spouse_addr_state").focus();
			return false;
		}
var text13 = $("#spouse_addr_state option:selected").text();
		
		if(text13 == "OTHERS" && $("input#spouse_addr_state_other").val().trim()==""){
			alert("Please Enter SF ACCN Address State Other");
			$("input#spouse_addr_state_other").focus();
			return false;
		}
		if ($("select#spouse_addr_district").val() == "0") {
			alert("Please select SF ACCN Address District");
			$("select#spouse_addr_district").focus();
			return false;
		}
var text14 = $("#spouse_addr_district option:selected").text();
		
		if(text14 == "OTHERS" && $("input#spouse_addr_district_other").val().trim()==""){
			alert("Please Enter SF ACCN Address District Other");
			$("input#spouse_addr_district_other").focus();
			return false;
		}
		if ($("select#spouse_addr_tehsil").val() == "0") {
			alert("Please Select SF ACCN Address Tehsil");
			$("select#spouse_addr_tehsil").focus();
			return false;
		}
var text15 = $("#spouse_addr_tehsil option:selected").text();
		
		if(text15 == "OTHERS" && $("input#spouse_addr_tehsil_other").val().trim()==""){
			alert("Please Enter SF ACCN Address Tehsil Other");
			$("input#spouse_addr_tehsil_other").focus();
			return false;
		}
		if ($("input#spouse_addr_village").val().trim()=="") {
			alert("Please Enter SF ACCN Address Village/Town/City");
			$("input#spouse_addr_village").focus();
			return false;
		}	
		if ($("input#spouse_addr_pin").val().trim()=="" || $("input#spouse_addr_pin").val().length<6 || $("input#spouse_addr_pin").val().length>6)  {
			alert("Please Enter valid SF ACCN Address Pin");
			$("input#spouse_addr_pin").focus();
			return false;
		}	
		if ($("input#spouse_addr_rail").val().trim()=="") {
			alert("Please Enter SF ACCN Address Nearest Railway Station");
			$("input#spouse_addr_rail").focus();
			return false;
		}
		var spouserus = $('input:radio[name=spouse_addr_rural_urban]:checked').val();
		if(spouserus == undefined) {
			alert("Please select SF ACCN Address Is  Rural /Urban/Semi Urban");
			return false;
		}
		
		if ($("input#Stn_HQ_sus_no").val().trim()=="") {
			alert("Please Enter SF ACCN Address Stn HQ SUS No");
			$("input#Stn_HQ_sus_no").focus();
			return false;
		}
	}
	
	/* if ($("select#retire_country").val() == "0") {
		alert("Please Select The  Country");
		$("select#retire_country").focus();
		return false;
	}
	if ($("select#retire_state").val() == "0") {
		alert("Please Select State");
		$("select#retire_state").focus();
		return false;
	}
	if ($("select#retire_district").val() == "0") {
		alert("Please Select District");
		$("select#retire_district").focus();
		return false;
	}
	if ($("select#retire_tehsil").val() == "0") {
		alert("Please Enter Tehsil");
		$("select#retire_tehsil").focus();
		return false;
	}
	if ($("select#retire_village").val() == "0") {
		alert("Please Select  Village/Town/City");
		$("select#retire_village").focus();
		return false;
	}
	if ($("input#retire_pin").val().trim()=="") {
		alert("Please Enter Pin");
		$("input#retire_pin").focus();
		return false;
	}
	if ($("input#retire_rail").val().trim()=="") {
		alert("Please Enter  Nearest Railway Station");
		$("input#retire_rail").focus();
		return false;
	}
	var d = $('input:radio[name=retire_rural_urban]:checked').val();
	if(d == undefined){	
		alert("Please select The  Rural /Urban/Semi Urban ");
		return false;
		} */
	var formvalues=$("#form_addr_details_form").serialize();
	var census_id=$("#census_id").val();	
	var addr_ch_id=$("#addr_ch_id").val();	
		var comm_id=$("#comm_id").val();
		var comm_date=$("#comm_date").val();
		var marital_status = $('#marital_status_p').text();
		formvalues+="&census_id="+census_id+"&addr_ch_id="+addr_ch_id+"&comm_id="+comm_id+"&marital_status="+marital_status+"&comm_date="+comm_date;	

		 $.post('changeaddress_details_action?' + key + "=" + value,formvalues, function(data){
			 if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	  $("#addr_ch_id").val(data);	
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function get_changeaddress_details(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();	
	if($("#marital_status_p").text() == '8'){
		$("#spouse_addressMaindiv").show();
	}
	else{
		$("#spouse_addressMaindiv").hide();
	}
	$.post('address_details_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id }, function(j){
		var v=j.length;
		if(v!=0){
			$("#pre_country").val(j[0].pre_country);
			fn_pre_domicile_Country();
			$("#pre_domicile_state").val(j[0].pre_state);
			fn_pre_domicile_state();
			$("#pre_domicile_district").val(j[0].pre_district);
			fn_pre_domicile_district();
			$("#pre_domicile_tesil").val(j[0].pre_tesil);
			$("#addr_authority").val(j[0].authority);
			$('#addr_date_authority').val(ParseDateColumncommission(j[0].date_authority)); 
			$("#per_home_addr_country").val(j[0].permanent_country);
			fn_per_home_addr_Country();
			$("#per_home_addr_state").val(j[0].permanent_state);
			fn_per_home_addr_state();
			$("#per_home_addr_district").val(j[0].permanent_district);
			fn_per_home_addr_district();
			$("#per_home_addr_village").val(j[0].permanent_village);
			$("#per_home_addr_tehsil").val(j[0].permanent_tehsil);
			$("#per_home_addr_pin").val(j[0].permanent_pin_code);
			$("#per_home_addr_rail").val(j[0].permanent_near_railway_station);
			 var permanent= j[0].permanent_rural_urban_semi;
           if(permanent == "rural"){
                   $("#per_home_addr_rural").prop("checked", true);
            } 
           if(permanent == "urban"){
                   $("#per_home_addr_urban").prop("checked", true);
            }
           if(permanent == "semi_urban")
           {
                   $("#per_home_addr_semi_urban").prop("checked", true);
            }  
           var br= j[0].permanent_border_area;
           if(br == "yes"){
                   $("#border_area").prop("checked", true);
            } 
           if(br == "no"){
                   $("#border_area1").prop("checked", true);
            } 
           $("#pers_addr_country").val(j[0].present_country);
           fn_pers_addr_Country();
           $("#pers_addr_state").val(j[0].present_state);
           fn_pers_addr_state();
           $("#pers_addr_district").val(j[0].present_district);
           fn_pers_addr_district();
			$("#pers_addr_village").val(j[0].present_village);
			$("#pers_addr_tehsil").val(j[0].present_tehsil);
			$("#pers_addr_pin").val(j[0].present_pin_code);
			$("#pers_addr_rail").val(j[0].present_near_railway_station);
			var present= j[0].present_rural_urban_semi;
	            if(present == "rural"){
	                    $("#pers_addr_rural").prop("checked", true);
	            } 
	            if(present == "urban")
	            {
	                    $("#pers_addr_urban").prop("checked", true);
	             }
	            if(present == "semi_urban")
	            {
	                    $("#pers_addr_semi_urban").prop("checked", true);
	             }  
	            var text6 = $("#pre_country option:selected").text();
				if(text6 == "OTHERS"){
					$("#pre_country_other").val(j[0].pre_country_other);
					$("#add_other_id").show();
				}
				else{
					$("#add_other_id").hide();
				}
				
				var text7 = $("#pre_domicile_state option:selected").text();
				if(text7 == "OTHERS"){
					$('#pre_domicile_state_other').val(j[0].pre_domicile_state_other);
					$("#add_other_st").show();
				}
				else{
					$("#add_other_st").hide();
				}
				var text8 = $("#pre_domicile_district option:selected").text();
				if(text8 == "OTHERS"){
					$("#pre_domicile_district_other").val(j[0].pre_domicile_district_other);
					$("#add_other_dt").show();
				}
				else{
					$("#add_other_dt").hide();
				}
				var text9 = $("#pre_domicile_tesil option:selected").text();
				if(text9 == "OTHERS"){
					$("#pre_domicile_tesil_other").val(j[0].pre_domicile_tesil_other);
					$("#add_other_te").show();
				}
				else{
					$("#add_other_te").hide();
				}
				var text10 = $("#per_home_addr_country option:selected").text();
				if(text10 == "OTHERS"){
					$("#per_home_addr_country_others").val(j[0].per_home_addr_country_other);
					$("#per_home_addr_country_other_hid").show();
				}
				else{
					$("#per_home_addr_country_other_hid").hide();
				}
				var text11 = $("#per_home_addr_state option:selected").text();
				if(text11 == "OTHERS"){
					$("#per_home_addr_state_others").val(j[0].per_home_addr_state_other);
					$("#per_home_addr_state_other_hid").show();
				}
				else{
					$("#per_home_addr_state_other_hid").hide();
				}
				var text12 = $("#per_home_addr_district option:selected").text();
				if(text12 == "OTHERS"){
					$("#per_home_addr_district_others").val(j[0].per_home_addr_district_other);
					$("#per_home_addr_district_other_hid").show();
				}
				else{
					$("#per_home_addr_district_other_hid").hide();
				}
				var text13 = $("#per_home_addr_tehsil option:selected").text();
				if(text13 == "OTHERS"){
					$("#per_home_addr_tehsil_others").val(j[0].per_home_addr_tehsil_other);
					$("#per_home_addr_tehsil_other_hid").show();
				}
				else{
					$("#per_home_addr_tehsil_other_hid").hide();
				}
				var text14 = $("#pers_addr_country option:selected").text();
				if(text14 == "OTHERS"){
					$("#pers_addr_country_other").val(j[0].pers_addr_country_other);
					$("#pers_addr_country_other_hid").show();
				}
				else{
					$("#pers_addr_country_other_hid").hide();
				}
				var text15 = $("#pers_addr_state option:selected").text();
				if(text15 == "OTHERS"){
					$("#pers_addr_state_other").val(j[0].pers_addr_state_other);
					$("#pers_addr_state_other_hid").show();
				}
				else{
					$("#pers_addr_state_other_hid").hide();
				}
				var text16 = $("#pers_addr_district option:selected").text();
				if(text16 == "OTHERS"){
					$("#pers_addr_district_other").val(j[0].pers_addr_district_other);
					$("#pers_addr_district_other_hid").show();
				}
				else{
					$("#pers_addr_district_other_hid").hide();
				}
				var text17 = $("#pers_addr_tehsil option:selected").text();
				if(text17 == "OTHERS"){
					$("#pers_addr_tehsil_other").val(j[0].pers_addr_tehsil_other);
					$("#pers_addr_tehsil_other_hid").show();
				}
				else{
					$("#pers_addr_tehsil_other_hid").hide();
				}
				
				
				
				
				
			$("#addr_ch_id").val(j[0].id);
			$("#addr_pending_ch_id").val(j[0].id);
			if($("#marital_status_p").text() == '8' && j[0].spouse_country != 0){
				$("#check_spouse_address").attr("checked", true);
				spouse_addressFn();
				$("#spouse_addr_Country").val(j[0].spouse_country);
				$("#spouse_addr_country_other").val(j[0].spouse_addr_country_other);
				fn_spouse_addr_Country();
				$("#spouse_addr_state").val(j[0].spouse_state);
				$("#spouse_addr_state_other").val(j[0].spouse_addr_state_other);
				fn_spouse_addr_state();
				$("#spouse_addr_district").val(j[0].spouse_district);
				$("#spouse_addr_district_other").val(j[0].spouse_addr_district_other);
				fn_spouse_addr_district();
				$("#spouse_addr_tehsil").val(j[0].spouse_tehsil);
				fn_spouse_addr_tehsil();
				$("#spouse_addr_tehsil_other").val(j[0].spouse_addr_tehsil_other);
				$("#spouse_addr_village").val(j[0].spouse_village);
				$("#spouse_addr_pin").val(j[0].spouse_pin_code);
				$("#spouse_addr_rail").val(j[0].spouse_near_railway_station);
				$("#Stn_HQ_sus_no").val(j[0].stn_hq_sus_no);
				var sus_no = j[0].stn_hq_sus_no;			      	
				 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#Stn_HQ_unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
	        	 });
				 $("input[name=spouse_addr_rural_urban][value=" + j[0].spouse_rural_urban_semi + "]").prop('checked', true);

			}
			
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}
function copy_address(){
    if($("#check_address").prop('checked') == true){                
            $("#pers_addr_village").val($("#per_home_addr_village").val());
            $("#pers_addr_country").val($("#per_home_addr_country").val());
            $("#pers_addr_country_other").val($("#per_home_addr_country_others").val());
            fn_pers_addr_Country();
            $("#pers_addr_state").val($("#per_home_addr_state").val());
            $("#pers_addr_state_other").val($("#per_home_addr_state_others").val());
            
            fn_pers_addr_state();
            $("#pers_addr_district").val($("#per_home_addr_district").val());
            $("#pers_addr_district_other").val($("#per_home_addr_district_others").val());
            fn_pers_addr_district();
            $("#pers_addr_tehsil").val($("#per_home_addr_tehsil").val());
            $("#pers_addr_tehsil_other").val($("#per_home_addr_tehsil_others").val());
            fn_pers_addr_tehsil();
            $("#pers_addr_pin").val($("#per_home_addr_pin").val());
            $("#pers_addr_rail").val($("#per_home_addr_rail").val());
            
            $("input[name=pers_addr_rural_urban]").prop('checked', false);
   		 var value= $('input:radio[name=per_home_addr_rural_urban]:checked').val()
   		            $("input[name=pers_addr_rural_urban][value=" + value + "]").prop('checked', true);
    }
    else{
            $("#pers_addr_village").val("");
            $("#pers_addr_tehsil").val("0");
            $("#pers_addr_district").val("0");
            $("#pers_addr_state").val("0");
            $("#pers_addr_district_other").val("");
            $("#pers_addr_country_other").val("");
            $("#pers_addr_state_other").val("");
            $("#pers_addr_tehsil_other").val("");
            $("#pers_addr_pin").val("");
            $("#pers_addr_rail").val("");
            $("#pers_addr_country").val("0");
           // $('input[type="radio"]').prop('checked', false); 
            $("input[name=pers_addr_rural_urban]").attr('checked', false);
    }
}


function fn_pre_domicile_state() {
	var text = $("#pre_domicile_state option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_st").show();
	}
	else{
		$("#add_other_st").hide();
		$("#pre_domicile_state_other").val("");
}
	 var state_id =$('select#pre_domicile_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_district").html(options);
	 				fn_pre_domicile_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_district() {
	
	var text = $("#pre_domicile_district option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_dt").show();
	}
	else{
		$("#add_other_dt").hide();
		$("#pre_domicile_district_other").val("");
}
	 var Dist_id = $('select#pre_domicile_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_tesil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_pre_domicile_tesil() {
	
	var text = $("#pre_domicile_tesil option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_te").show();
	}
	else{
		$("#add_other_te").hide();
		$("#pre_domicile_tesil_other").val("");
}
}
function fn_per_home_addr_Country() {
	var text = $("#per_home_addr_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#per_home_addr_country_other_hid").show();
	}
	else{
		$("#per_home_addr_country_other_hid").hide();
		$("#per_home_addr_country_others").val("");
	}

	 var contry_id = $('select#per_home_addr_country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_state").html(options);
	 				fn_per_home_addr_state();
	 				fn_per_home_addr_district();
	 				fn_per_home_addr_tehsil();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_state() {
	var text = $("#per_home_addr_state option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#per_home_addr_state_other_hid").show();
	}
	else{
		$("#per_home_addr_state_other_hid").hide();
		$("#per_home_addr_state_others").val("");
}

	 var state_id =$('select#per_home_addr_state').val(); 

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 			
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_district").html(options);
	 				fn_per_home_addr_district();
	 				onPermHomeAddrState();
	 			     
	 				
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pre_domicile_Country() {
	var text = $("#pre_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#add_other_id").show();
	}
	else{
		$("#add_other_id").hide();
		$("#pre_country_other").val("");
	}
	 var contry_id = $('select#pre_country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pre_domicile_state").html(options);
	 				fn_pre_domicile_state();
	 				fn_pre_domicile_district();
	 				fn_pre_domicile_tesil(); 
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_per_home_addr_district() {
	
	var text = $("#per_home_addr_district option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#per_home_addr_district_other_hid").show();
	}
	else{
		$("#per_home_addr_district_other_hid").hide();
		$("#per_home_addr_district_others").val("");
}
	 var Dist_id = $('select#per_home_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#per_home_addr_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_per_home_addr_tehsil() {
var text = $("#per_home_addr_tehsil option:selected").text();
//alert("hello");
if(text == "OTHERS"){
	$("#per_home_addr_tehsil_other_hid").show();
}
else{
	$("#per_home_addr_tehsil_other_hid").hide();
	$("#per_home_addr_tehsil_others").val("");
}
}
function fn_pers_addr_Country() {
	var text = $("#pers_addr_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#pers_addr_country_other_hid").show();
	}
	else{
		$("#pers_addr_country_other_hid").hide();
		$("#pers_addr_country_other").val("");
	}

	 var contry_id = $('select#pers_addr_country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_state").html(options);
					fn_pers_addr_state();
					fn_pers_addr_district();
					fn_pers_addr_tehsil();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_pers_addr_state() {
	var text = $("#pers_addr_state option:selected").text();
	
	if(text == "OTHERS"){
		$("#pers_addr_state_other_hid").show();
	}
	else{
		$("#pers_addr_state_other_hid").hide();
		$("#pers_addr_state_other").val("");
	}

	 var state_id = $('select#pers_addr_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#pers_addr_district").html(options);
					fn_pers_addr_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_pers_addr_district() {
var text = $("#pers_addr_district option:selected").text();
	
	if(text == "OTHERS"){
		$("#pers_addr_district_other_hid").show();
	}
	else{
		$("#pers_addr_district_other_hid").hide();
		$("#pers_addr_district_other").val("");
	}


	 var Dist_id = $('select#pers_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#pers_addr_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}
function fn_pers_addr_tehsil() {
	var text = $("#pers_addr_tehsil option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_tehsil_other_hid").show();
		}
		else{
			$("#pers_addr_tehsil_other_hid").hide();
			$("#pers_addr_tehsil_other").val("");
		}
}
function fn_spouse_addr_Country() {
	var text = $("#spouse_addr_Country option:selected").text();
	
	if(text == "OTHERS"){
		$("#spouse_addr_Country_hid").show();
	}
	else{
		$("#spouse_addr_Country_hid").hide();
		$("#spouse_addr_country_other").val("");
	}

	 var contry_id = $('select#spouse_addr_Country').val();

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_state").html(options);
					fn_spouse_addr_state();
					fn_spouse_addr_district();
					fn_spouse_addr_tehsil()
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}


function fn_spouse_addr_state() {
var text = $("#spouse_addr_state option:selected").text();
	
	if(text == "OTHERS"){
		$("#spouse_addr_state_hid").show();
	}
	else{
		$("#spouse_addr_state_hid").hide();
		$("#spouse_addr_state_other").val("");
	}

	 var state_id = $('select#spouse_addr_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 					

	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 				
	 				}
	 				
	 				$("select#spouse_addr_district").html(options);
					fn_spouse_addr_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}

function fn_spouse_addr_district() {
var text = $("#spouse_addr_district option:selected").text();
	
	if(text == "OTHERS"){
		$("#spouse_addr_district_hid").show();
	}
	else{
		$("#spouse_addr_district_hid").hide();
		$("#spouse_addr_district_other").val("");
	}

	 var Dist_id = $('select#spouse_addr_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#spouse_addr_tehsil").html(options);
	 				fn_spouse_addr_tehsil();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
	 		 	
}
function fn_spouse_addr_tehsil() {
	var text = $("#spouse_addr_tehsil option:selected").text();
		
		if(text == "OTHERS"){
			$("#spouse_addr_tehsil_hid").show();
		}
		else{
			$("#spouse_addr_tehsil_hid").hide();
			$("#spouse_addr_tehsil_other").val("");
		}
}

$("#Stn_HQ_sus_no").keypress(function(){
	var sus_no = this.value;
	var susNoAuto=$("#Stn_HQ_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "HQ_getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Station HQ SUS No.");
	        	  document.getElementById("Stn_HQ_unit_name").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#Stn_HQ_unit_name").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

$("#Stn_HQ_unit_name").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#Stn_HQ_unit_name");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getallUnitNameListStnHQ?"+key+"="+value,
		        data: {unit_name:unit_name},
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
		        	  alert("Please Enter Approved Station HQ Unit Name.");
		        	  document.getElementById("Stn_HQ_sus_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	 var unit_name = ui.item.value;
		    
			            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#Stn_HQ_sus_no").val(dec(enc,data[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
		      } 	     
		}); 			
});


//*************************************End ADDRESS DATA *****************************//
</script>
<script>
//*************************************Start Nok *****************************//

function fn_nok_Country() {
	
	var text = $("#nok_country option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#nok_other_id").show();
	}
	else{
		$("#nok_other_id").hide();
		$("#ctry_other").val("");
		$("#nok_other_te").hide();
	}
	

		 var contry_id =$('select#nok_country').val();

		 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
		 		 				 	
		 		 			 	
		 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
		 				for ( var i = 0; i < j.length; i++) {
		 				
		 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		 					
		 				}
		 				
		 				$("select#nok_state").html(options);
						fn_nok_state();
						fn_nok_district();
		 			}).fail(function(xhr, textStatus, errorThrown) {
		 	});
	}
	
	
	
function fn_nok_state() {
	
	var text = $("#nok_state option:selected").text();
	if(text == "OTHERS"){
		$("#nok_other_st").show();
	}
	else{
		$("#nok_other_st").hide();
		$("#st_other").val("");
	}	

	 var state_id = $('select#nok_state').val();

	 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#nok_district").html(options);
					fn_nok_district();
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}	

function fn_nok_district() {
	
	var text = $("#nok_district option:selected").text();
	//alert("hello");
	if(text == "OTHERS"){
		$("#nok_other_dist").show();
	}
	else{
		$("#nok_other_dist").hide();
		$("#dist_other").val("");
	}
	
	 var Dist_id = $('select#nok_district').val();

	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#nok_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}
function fn_nok_tehsil() {
	
	var text = $("#nok_tehsil option:selected").text();
	
	if(text == "OTHERS"){
		$("#nok_other_te").show();
	}
	else{
		$("#nok_other_te").hide();
		$("#nok_tehsil_other").val("");
	}
}	

function validate_save_nok_details(){
//  	if ($("input#authority").val().trim()=="") {
// 		alert("Please Enter Authority");
// 		$("input#authority").focus();
// 		return false;
// 	}
//  	if ($("input#date_authority").val() == "DD/MM/YYYY") {
// 		alert("Please Enter Date of Authority");
// 		$("input#date_authority").focus();
// 		return false;
// 	}  
	
	if($("input#date_authority").val() != "DD/MM/YYYY" && $("input#date_authority").val() != ""){
	if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#date_authority").val())) {
		alert("Authority Date should be Greater than Commission Date");
		return false;
  }
	}
	if ($("input#nok_name").val().trim()=="") {
		alert("Please Enter NOK Name");
		$("input#nok_name").focus();
		return false;
	}
	if ($("select#nok_relation").val() == "0") {
		alert("Please Enter NOK Relation");
		$("select#nok_relation").focus();
		return false;
	}
	if ($("select#nok_country").val() == "0") {
		alert("Please Select  Country");
		$("select#nok_country").focus();
		return false;
	}
  var text1 = $("#nok_country option:selected").text();
	
	if(text1 == "OTHERS" && $("input#ctry_other").val().trim()==""){
		alert("Please Enter  Country Other");
		$("input#ctry_other").focus();
		return false;
	}
	if ($("select#nok_state").val() == "0") {
		alert("Please Select  Nok State");
		$("select#nok_state").focus();
		return false;
	}
  var text2 = $("#nok_state option:selected").text();
	
	if(text2 == "OTHERS" && $("input#st_other").val().trim()==""){
		alert("Please Enter State Other");
		$("input#st_other").focus();
		return false;
	}
	if ($("select#nok_district").val() == "0") {
		alert("Please Select Nok District");
		$("select#nok_district").focus();
		return false;
	}
	 var text3 = $("#nok_district option:selected").text();
		
		if(text3 == "OTHERS" && $("input#dist_other").val().trim()==""){
			alert("Please Enter District Other");
			$("input#dist_other").focus();
			return false;
		}
	if ($("select#nok_tehsil").val() == "0" ) {
		alert("Please Select Tehsil");
		$("select#nok_tehsil").focus();
		return false;
	}
	var text5 = $("#nok_tehsil option:selected").text();
	
	if(text5 == "OTHERS" && $("input#nok_tehsil_other").val().trim()==""){
		alert("Please Enter Tehsil Other");
		$("input#nok_tehsil_other").focus();
		return false;
	}
	if ($("input#nok_village").val().trim()=="") {
		alert("Please Enter  Village/Town/City");
		$("input#nok_village").focus();
		return false;
	}
	if ($("input#nok_pin").val().trim()==""  || $("input#nok_pin").val().trim().length<6 || $("input#nok_pin").val().trim().length>6) {
		alert("Please Enter  Nok Pin");
		$("input#nok_pin").focus();
		return false;
	}
	if ($("input#nok_rail").val().trim()=="") {
		alert("Please Enter  Nearest Railway Station");
		$("input#nok_rail").focus();
		return false;
	}
	var n = $('input:radio[name=nok_rural_urban]:checked').val();
	if(n == undefined){	
		alert("Please select Is  Rural /Urban/Semi Urban");
		return false;
		}
	if ($("input#nok_mobile_no").val().trim()=="" || $("input#nok_mobile_no").val().trim().length<10  || $("input#nok_mobile_no").val().trim().length>10) {
		alert("Please Enter  NOK Mobile No");
		$("input#nok_mobile_no").focus();
		return false;
	}
   var formvalues=$("#form_nok_addr_details_form").serialize();
   var comm_date=$('#comm_date').val();
	var census_id=$("#census_id").val();
	var comm_id =$("#comm_id").val();
	var nok_id=$("#nok_id").val();		
		formvalues+="&census_id="+census_id+"&nok_id="+nok_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
		 $.post('nok_details_action?' + key + "=" + value,formvalues, function(data){
			 if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	  $("#nok_id").val(data);	
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function get_nokaddress_details(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();	
	$.post('nok_details_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id }, function(j){
		var v=j.length;
			if(v!=0){
				//////nok//////
				$("#authority").val(j[0].authority);
				$('#date_authority').val(ParseDateColumncommission(j[0].date_authority)); 
				$("#nok_name").val(j[0].nok_name);
				$("#nok_relation").val(j[0].nok_relation);
				$("#nok_country").val(j[0].nok_country);
				fn_nok_Country();
				$("#nok_state").val(j[0].nok_state);
				fn_nok_state();
				$("#nok_district").val(j[0].nok_district);
				fn_nok_district();
				$("#nok_tehsil").val(j[0].nok_tehsil);
				$("#nok_village").val(j[0].nok_village);
				$("#nok_pin").val(j[0].nok_pin);
				$("#nok_rail").val(j[0].nok_near_railway_station);
				/* $("#ctry_other").val(j[0].ctry_other);
				$("#st_other").val(j[0].st_other);
				$("#dist_other").val(j[0].dist_other);
				$("#tehsil_other").val(j[0].tehsil_other); */
				$("#nok_mobile_no").val(j[0].nok_mobile_no);
				 var nok= j[0].nok_rural_urban_semi;
		            if(nok == "rural"){
		                    $("#nok_rural").prop("checked", true);
		             } 
		            if(nok == "urban"){
		                    $("#nok_urban").prop("checked", true);
		             }
		            if(nok == "semi_urban")
		            {
		                    $("#nok_semi_urban").prop("checked", true);
		             }  
		            
		            var text6 = $("#nok_country option:selected").text();
					if(text6 == "OTHERS"){
						$("#ctry_other").val(j[0].ctry_other);
						$("#nok_other_id").show();
					}
					else{
						$("#nok_other_id").hide();
					}
					
					var text7 = $("#nok_state option:selected").text();
					if(text7 == "OTHERS"){
						$('#st_other').val(j[0].st_other);
						$("#nok_other_st").show();
					}
					else{
						$("#nok_other_st").hide();
					}
					var text8 = $("#nok_district option:selected").text();
					if(text8 == "OTHERS"){
						$("#dist_other").val(j[0].dist_other);
						$("#nok_other_dist").show();
					}
					else{
						$("#nok_other_dist").hide();
					}
					var text9 = $("#nok_tehsil option:selected").text();
					if(text9 == "OTHERS"){
						$("#nok_tehsil_other").val(j[0].tehsil_other);
						$("#nok_other_te").show();
					}
					else{
						$("#nok_other_te").hide();
					}
				$("#nok_id").val(j[0].id);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	} 
///state
// 	   $('select#nok_state').change(function() {
//            var state_id = this.value;
//                                     $.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
//                                             var options = '<option   value="0">'+ "--Select--" + '</option>';
//                                            for ( var i = 0; i < j.length; i++) {
//                                                            options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
//                                            }
//                                            $("select#nok_district").html(options);
//                                    }).fail(function(xhr, textStatus, errorThrown) {
//                    });
//   });
//   ///country
// 	   $('select#nok_Country').change(function() {
//            var contry_id = this.value;
//                                     $.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
//                                             var options = '<option   value="0">'+ "--Select--" + '</option>';
//                                            for ( var i = 0; i < j.length; i++) {
//                                                            options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
//                                            }
//                                            $("select#nok_state").html(options);
//                                    }).fail(function(xhr, textStatus, errorThrown) {
//                    });
//   });
//   ///district
// 	   $('select#nok_district').change(function() {
// 	         var Dist_id = this.value;
// 	                                  $.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
// 	                                          var options = '<option   value="0">'+ "--Select--" + '</option>';
// 	                                         for ( var i = 0; i < j.length; i++) {
// 	                                                         options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
// 	                                         }
// 	                                         $("select#nok_tehsil").html(options);
// 	                                 }).fail(function(xhr, textStatus, errorThrown) {
// 	                 });
// 	});
  
	   
  //tehsil
	 /*   $('select#nok_tehsil').change(function() {
	         var teh_id = this.value;
	                                  $.post("getvillageListFormteh1?"+key+"="+value, {teh_id:teh_id}).done(function(j) {
	                                          var options = '<option   value="0">'+ "--Select--" + '</option>';
	                                         for ( var i = 0; i < j.length; i++) {
	                                                         options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	                                         }
	                                         $("select#nok_village").html(options);
	                                 }).fail(function(xhr, textStatus, errorThrown) {
	                 });
	}); */
	 //*************************************END Nok *****************************//
</script>
<script>
//************************************* START BTLE & PHY *****************************//
///new changes
function onNatureCasuality() {
	if ($("#nature_of_casuality4").prop("checked")) {
		$('#missingdiv').show();
		}
	else{
		$('#missingdiv').hide();
		$('#missing_desc').val('0');
	}
}

function onDutyOthers(){
	if ($("#onduty3").prop("checked")) {
		$('#onduityotherdiv').show();
		}
	else{
		$('#onduityotherdiv').hide();
		$('#onduityother').val("");
	}
}

function oncontyselect(){
	if ($("#batnpc_country option:selected").text()!='INDIA' && $("#batnpc_country").val()!='0') {
		$('#otherindcontydiv1').show();
		$('#otherindcontydiv2').show();
		$('#batnpc_state').val("0");
		$('#batnpc_state').attr('readonly', true);
		$('#batnpc_district').val("0");
		$('#batnpc_district').attr('readonly', true);
		$('#batnpc_tehsil').val("0");
		$('#batnpc_tehsil').attr('readonly', true);
		$('#batnpc_village').val("");
		$('#batnpc_village').attr('readonly', true);
		$('#batnpc_pin').val("");
		$('#batnpc_pin').attr('readonly', true);
		$('#batnpc_exact_place').val("");
		$('#batnpc_exact_place').attr('readonly', true);
		}
	else{
		$('#otherindcontydiv1').hide();
		$('#otherindcontydiv2').hide();
		$('#batnpc_state').attr('readonly', false);
		$('#batnpc_district').attr('readonly', false);
		$('#batnpc_tehsil').attr('readonly', false);
		$('#batnpc_village').attr('readonly', false);
		$('#batnpc_pin').attr('readonly', false);
		$('#batnpc_exact_place').attr('readonly', false);
	}
}

function isInformed(){
	if ($("#nok_informed1").prop("checked")) {
		$('#dt_infodiv').show();
		$('#timeandmd_infodiv').show();
		}
	else{
		$('#dt_infodiv').hide();
		$('#timeandmd_infodiv').hide();
		$('#date_of_informing').val("");
		$('#time_of_informing').val("");
		$('#methodofinforming').val("");
	}
	
}
function DiagnoseChanged(){
	if ($("#batnpc_diagnosis").val()=="Others") {
		$('#diagnosisotdiv').show();
		}
	else{
		$('#diagnosisotdiv').hide();
		$('#diagnosis_others').val("");
	}
}

function onCauses1(){
	var cause1_id = $('#cause_of_casuality_1').val();
	$.post('getCauses2Url?' + key + "=" + value, {id:cause1_id }, function(j){
		$('#cause_of_casuality_3').hide();
   	 	$('#cause_of_casuality_3').val("0");
		 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
             var length = j.length;
             if(length != 0){   
            	 $('#cause_of_casuality_2').show();
	             for ( var i = 0; i < length; i++) {                  
                           options += '<option value="' + j[i][1]+ '" name="'+j[i][0]+'" >'+ j[i][0] + '</option>';
	             }  
	             $("select#cause_of_casuality_2").html(options);
             }
             else{
            	 $('#cause_of_casuality_2').hide();
            	 $('#cause_of_casuality_2').val("0");
             }
             
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

function onCauses2(){
	var cause1_id = $('#cause_of_casuality_1').val();
	var cause2_id = $('#cause_of_casuality_2').val();
	$.post('getCauses3Url?' + key + "=" + value, {id1:cause1_id,id2:cause2_id }, function(j){
		 
		 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
             var length = j.length;
             if(length != 0){   
            	 $('#cause_of_casuality_3').show();
	             for ( var i = 0; i < length; i++) {                  
                           options += '<option value="' + j[i][1]+ '" name="'+j[i][0]+'" >'+ j[i][0] + '</option>';
	             }  
	             $("select#cause_of_casuality_3").html(options);
             }
             else{
            	 $('#cause_of_casuality_3').hide();
            	 $('#cause_of_casuality_3').val("0");
             }
             
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}

////////////////
function validate_batnpc_save(){
		if ($("#batnpc_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#batnpc_authority").focus();
			return false;
		}
	 	if ($("#batnpc_date_of_authority").val().trim()=="" || $("#batnpc_date_of_authority").val() == "DD/MM/YYYY" ) {
			alert("Please Enter Date of Authority");
			$("input#batnpc_date_of_authority").focus();
			return false;
		} 
	 	
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#batnpc_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			$("input#batnpc_date_of_authority").focus();
			return false;
	  }
	 	
	 	if ($("#date_of_casuality").val().trim()==""  || $("#date_of_casuality").val() == "DD/MM/YYYY" ) {
			alert("Please Select Date of Casualty");
			$("input#date_of_casuality").focus();
			return false;
		} 
	 	if(getformatedDate($("input#dob_date").val()) >= getformatedDate($("#date_of_casuality").val())) {
			alert("Date of Casuality should be Greater than Date of Birth");
			$("input#date_of_casuality").focus();
			return false;
		}
	
		if ($('input[name="classification_of_casuality"]:checked').length == 0) {
			alert("Please Select Recommended For");
			$("input#classification_of_casuality").focus();
			return false;
		}
		if ($('input[name="nature_of_casuality"]:checked').length == 0) {
			alert("Please Select Nature of Casualty");
			$("input#nature_of_casuality").focus();
			return false;
		}
	
		if ($("#batnpc_country").val() == "0") {
			alert("Please Select Country");
			$("select#batnpc_country").focus();
			return false;
		}
		
		var country_other_sel = $("#batnpc_country option:selected").text();
		if(country_other_sel == "OTHERS"){
			if ($("#country_other").val().trim()=="") {
				alert("Please Enter Type of Country Other");
				$("#country_other").focus();
				return false;
		 }
		}
		
		if($("#batnpc_pin").val().length>0 && $("#batnpc_pin").val().length!=6){
			alert("Please Enter Pin");
			$("#batnpc_pin").focus();
			return false;
		}
		
		
		
		if ($("#name_of_operation").val().trim()=="" && $('input[name="classification_of_casuality"]:checked').val()!="physical_casuality") {
			alert("Please Enter Name of Operation");
			$("input#name_of_operation").focus();
			return false;
		}
		var whether_on_val = $("input[name='whether_on']:checked").val();
		if (whether_on_val == "") {
			alert("Please Select Whether On");
			$("input#whether_on").focus();
			return false;
		}
		
		if ($("#batnpc_bde").val() == "0") {
			alert("Please Select BDE");
			$("select#batnpc_bde").focus();
			return false;
		}
		if ($("#batnpc_div_subarea").val() == "0") {
			alert("Please Select DIV/Sub Area");
			$("select#batnpc_div_subarea").focus();
			return false;
		}
		if ($("#batnpc_corps_area").val() == "0") {
			alert("Please Select Corps/Area");
			$("select#batnpc_corps_area").focus();
			return false;
		}

		if ($("#batnpc_command").val() == "0") {
			alert("Please Select Command");
			$("select#batnpc_command").focus();
			return false;
		}
		
		if ($("#batnpc_diagnosis").val().trim()=="" && $('input[name="classification_of_casuality"]:checked').val()!="physical_casuality") {
			alert("Please Enter Diagnosis");
			$("input#batnpc_diagnosis").focus();
			return false;
		}
		var whether_on_val = $("input[name='whether_on']:checked").val();
		if (whether_on_val == "") {
			alert("Please Select Whether On");
			$("input#whether_on").focus();
			return false;
		}
// 		if ($("#batnpc_exact_place").val().trim()=="") {
// 			alert("Please Enter Place/Area/Post");
// 			$("input#batnpc_exact_place").focus();
// 			return false;
// 		}
		
// 		if ($("#batnpc_unit").val().trim()=="") {
// 			alert("Please Enter Unit");
// 			$("input#batnpc_unit").focus();
// 			return false;
// 		}
		
		
		
// 		if ($("#batnpc_state").val() == "0") {
// 			alert("Please Select State/UT");
// 			$("select#batnpc_state").focus();
// 			return false;
// 		}
// 		var state_other_sel = $("#batnpc_state option:selected").text();
// 		if(state_other_sel == "OTHERS"){
// 			if ($("#state_other").val().trim()=="") {
// 				alert("Please Enter State Other");
// 				$("#state_other").focus();
// 				return false;
// 		 }
// 		}
// 		if ($("#batnpc_district").val() == "0") {
// 			alert("Please Select District");
// 			$("select#batnpc_district").focus();
// 			return false;
// 		}
// 		var district_other_sel = $("#batnpc_district option:selected").text();
// 		if(district_other_sel == "OTHERS"){
// 			if ($("#district_other").val().trim()=="") {
// 				alert("Please Enter District Other");
// 				$("#district_other").focus();
// 				return false;
// 		 }
// 		}
// 		if ($("#batnpc_tehsil").val() == "0") {
// 			alert("Please Select Tehsil");
// 			$("select#batnpc_tehsil").focus();
// 			return false;
// 		}
// 		var tehsil_other_sel = $("#batnpc_tehsil option:selected").text();
// 		if(tehsil_other_sel == "OTHERS"){
// 			if ($("#tehsil_other").val().trim()=="") {
// 				alert("Please Enter Tehsil Other");
// 				$("#tehsil_other").focus();
// 				return false;
// 		 }
// 		}
// 		if ($("#batnpc_village").val().trim()=="") {
// 			alert("Please Select Village/Town/City");
// 			$("select#batnpc_village").focus();
// 			return false;
// 		}
// 		if ($("#batnpc_pin").val().trim()=="") {
// 			alert("Please Enter Pin");
// 			$("input#batnpc_pin").focus();
// 			return false;
// 		}
	//validation Ends
	  var formvalues=$("#form_battle_physical_casuality").serialize();
		var census_id=$("#census_id").val();	
		var comm_id=$('#comm_id').val();	
		var comm_date=$('#comm_date').val();
		var app_unit_name = $('#app_unit_name').text();
		formvalues+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date+"&app_unit_name="+app_unit_name;	
	   $.post('battle_physical_casualityAction?' + key + "=" + value,formvalues, function(data){
		   if(data=="update"){
			   alert("Data Updated Successfully");
		   }
		   else if(parseInt(data)>0){
	        	 $('#batnpc_ch_id').val(data);	        	 
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}
function get_Battle_and_Physical_Casuality_details(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();	
	bindRadioBt();
	bindRadioButton();
	$.post('Battle_and_Physical_Casuality_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id }, function(j){
		var v=j.length;
		if(v!=0){
			$.ajaxSetup({
				async : false
			});
			$("#batnpc_authority").val(j[0].authority);
			if(j[0].date_of_authority != null){
				$("#batnpc_date_of_authority").val(ParseDateColumncommission(j[0].date_of_authority));
				}
			$("#batnpc_ch_id").val(j[0].id);
			
			if(j[0].classification_of_casuality != null){
				$('input:radio[name=classification_of_casuality]').val([j[0].classification_of_casuality]);
				Casuality_radio();
				}
			if(j[0].nature_of_casuality != null){
				$('input:radio[name=nature_of_casuality]').val([j[0].nature_of_casuality]);
				}
			onNatureCasuality();
			$("#name_of_operation").val(j[0].name_of_operation);
			if(j[0].date_of_casuality != null){
			$("#date_of_casuality").val(ParseDateColumncommission(j[0].date_of_casuality));
				}
			////new
			
			$("#cause_of_casuality").val(j[0].cause_of_casuality);
			$("#missing_desc").val(j[0].missing_desc);
			if(j[0].time_of_casuality != ""){
				$("#time_of_casuality").val(j[0].time_of_casuality);
				}
			if(j[0].onduty != null){
				$('input:radio[name=onduty]').val([j[0].onduty]);
				}
			onDutyOthers();
			$("#onduityother").val(j[0].onduityother);
			$("#mission_expedition").val(j[0].mission_expedition);
			$("#area_post_town").val(j[0].area_post_town);
			$("#Sector_bde").val(j[0].sector_bde);
			$("#btnpc_sector").val(j[0].sector);
			if(j[0].field_services != null){
				$('input:radio[name=field_services]').val([j[0].field_services]);
				}
			$("#hospital_name").val(j[0].hospital_name);
			$("#hospital_location").val(j[0].hospital_location);
			$("#cause_of_casuality_1").val(j[0].cause_of_casuality_1);
			onCauses1();
			$("#cause_of_casuality_2").val(j[0].cause_of_casuality_2);
			onCauses2();
			$("#cause_of_casuality_3").val(j[0].cause_of_casuality_3);
			$("#circumstances").val(j[0].circumstances);
			$("#batnpc_diagnosis").val(j[0].diagnosis);
			DiagnoseChanged();
			$("#diagnosis_others").val(j[0].diagnosis_others);
			if(j[0].nok_informed != null){
				$('input:radio[name=nok_informed]').val([j[0].nok_informed]);
				}
			if(j[0].aid_to_civ != null){
				$('input:radio[name=aid_to_civ]').val([j[0].aid_to_civ]);
				}
			isInformed();
			if(j[0].date_of_informing != null){
				$("#date_of_informing").val(ParseDateColumncommission(j[0].date_of_informing));
					}
			if(j[0].time_of_informing != ""){
				$("#time_of_informing").val(j[0].time_of_informing);
				}
			$("#methodofinforming").val(j[0].methodofinforming);
			
			///
			$("#cause_of_casuality").val(j[0].cause_of_casuality);
			getDescList(document.getElementById('cause_of_casuality'));
			onCauseOfCasuality(document.getElementById('cause_of_casuality'));
			if(j[0].classification_of_casuality == 'physical_casuality' && j[0].cause_of_casuality != "0"){
				if(j[0].cause_of_casuality != 'OTHERS'){
					$("#physical_desc").val(j[0].description);
					onphysicalDesc(document.getElementById('physical_desc'));
					if(j[0].description == 'OTHERS'){
						$("#physical_desc_others").val(j[0].desc_others);
						}
					}
				else {
					$("#others_physical").val(j[0].description);
					}
				}
			if(j[0].classification_of_casuality == 'battle_casuality' && j[0].cause_of_casuality != "0"){
				if(j[0].cause_of_casuality != 'OTHERS'){
					$("#battle_desc").val(j[0].description);
					onBattleDesc(document.getElementById('battle_desc'));
					if(j[0].description == 'OTHERS'){
						$("#battle_desc_others").val(j[0].desc_others);
						}
					}
				else {
					$("#others_battle").val(j[0].description);
					}
				}
			
			$("#batnpc_exact_place").val(j[0].exact_place);
			if(j[0].whether_on != null){
				$('input:radio[name=whether_on]').val([j[0].whether_on]);
				}
			$("#batnpc_command").val(j[0].command);
			$("#batnpc_corps_area").val(j[0].corps_area);
			$("#batnpc_div_subarea").val(j[0].div_subarea);
			$("#batnpc_bde").val(j[0].bde);
			$("#batnpc_unit").val(j[0].unit);
			
			$("#batnpc_country").val(j[0].country);
			onCountryChange(document.getElementById('batnpc_country'));
			oncontyselect();
			$("#batnpc_state").val(j[0].state);
			
			onStateChange(document.getElementById('batnpc_state'));
			$("#batnpc_district").val(j[0].district);
			onDistrictChange(document.getElementById('batnpc_district'));
			$("#batnpc_tehsil").val(j[0].tehsil);
			onTehsilChange(document.getElementById('batnpc_tehsil'));
			$("#batnpc_village").val(j[0].village);
			if(j[0].pin != "0"){
				$("#batnpc_pin").val(j[0].pin);
				}
			$("#country_other").val(j[0].country_other);
			$("#state_other").val(j[0].state_other);
			$("#district_other").val(j[0].district_other);
			//alert("a---" + j[0].tehsil_other);
			$("input#tehsil_other").val(j[0].tehsil_other);
		
			
			
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}							
//************************************* END BTLE & PHY *****************************//
</script>


<script>
var classification='1';
$("#mad_classification").change(function(){
	
	classification=this.value;
	setDiagnosis();
	
});

function onShapeValueChange(e,label){
	//26-01-1994
// 	if(e.value=="1"){
		
// 		$("#"+label+"_from_date1").datepicker( "option", "disabled", true );
// 		$("#"+label+"_to_date1").datepicker( "option", "disabled", true );
// 	}
// 	else{
// 		$("#"+label+"_from_date1").datepicker( "option", "disabled", false );
// 		$("#"+label+"_to_date1").datepicker( "option", "disabled", false );
// 	}
}

function oncheck_1bx(){
	
	if ($("#check_1bx").is(':checked')) {
		$("#shape1bxdiv").show();
		$("#shapediv").hide();
		}
	else{
		$("#shape1bxdiv").hide();
		$("#shapediv").show();
	}
}

function onCopeChangebt(e,cope,ind){
	if(ind==1){
		if(e.value == 0 || (cope!= 4 && cope!= 2&& e.value == 1)){
			$('.CopaddbtClass'+cope+ind).hide();
			$('.CopaddbtClass'+cope).hide();
			
		}
		else{
			$('.CopaddbtClass'+cope+ind).show();
			$('.CopaddbtClass'+cope).show();
			
		}
	}
}
function onCCopeChange(e,ind){	
	if(e.value != '2 [c]'){
		$('.cCopClass'+ind).hide();
		$('.cCopClass').hide();
		
	}
	else{
		$('.cCopClass'+ind).show();
		$('.cCopClass').show();
		
	}
	var styleC = $(".cCopClass").css("display");
	for(var i = 1; i<=cCope; i++){
		var	st = $("#c_cvalue"+i).val();
		 
		 if(st == '2 [c]'){
			 $('.cCopClass').show();
				 $('#c_cother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
		 styleC = $(".cCopClass").css("display");
		if(i == cCope && styleC != "none"  ){
			 if(st == '2 [c]'){
				 $('#c_cother'+i).show();
					$('.cCopClass'+i).show();
			 }
			 else {
				 $('#c_cother'+i).hide();
					$('.cCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=cCope; i++){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
	}
	
}

function onECopeChange(e,ind){
	if(e.value == '1'){
		$('.eCopClass'+ind).show();
		$('.eCopClass').show();
	}
	else{
		$('.eCopClass'+ind).hide();
		$('.eCopClass').hide();
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
		$('#c_esubvalue'+ind).val(0);
	}
	
	var styleC = $(".eCopClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_evalue"+i).val();
		 if(st == '1'){
			 $('.eCopClass').show();
				 $('#c_esubvalue'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
		 styleC = $(".eCopClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == '1'){
				 $('#c_esubvalue'+i).show();
					$('.eCopClass'+i).show();
			 }
			 else {
				 $('#c_esubvalue'+i).hide();
					$('.eCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
	}
	
	onECopeSubChange(e,ind);
	
}

function onECopeSubChange(e,ind){
	if(e.value == 'k'){
		$('.eCop_otherClass'+ind).show();
		$('.eCop_otherClass').show();
	}
	else{
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
	}
	
	var styleC = $(".eCop_otherClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_esubvalue"+i).val();
		 
		 if(st == 'k'){
			 $('.eCop_otherClass').show();
				 $('#c_esubvalueother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
		 styleC = $(".eCop_otherClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == 'k'){
				 $('#c_esubvalueother'+i).show();
					$('.eCop_otherClass'+i).show();
			 }
			 else {
				 $('#c_esubvalueother'+i).hide();
					$('.eCop_otherClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
	}
}

function setDiagnosis(){
// 	if(classification=='1'){
// 		$('.diagnosisClass1').hide();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
		
// 	}
// 	if(classification=='2'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='3'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='4'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').show();
// 		$('.diagnosisClass4').show();
// 		$('.diagnosisClass5').show();
// 		$('.diagnosisClass6').show();
// 	}
}

function statusChange(Shape,position,Shape_status){
	
// 	if(classification=='1' && (Shape_status==2  || Shape_status==3)){
// 		 $("select#s_status"+position).val('1');
// 		 alert("Please First Change Medical Classification ");	
// 	}
// 	else{
// 	
	//26-01-1994
// 	if( Shape_status==1 || Shape_status==0){
// 		$('.diagnosisClass'+Shape+position).hide();
// 		$('.diagnosisClass'+Shape).hide();
// 		$('.addbtClass'+Shape+position).hide();
// 		$('.addbtClass'+Shape).hide();
		
// 	}else{
// 		$('.diagnosisClass'+Shape+position).show();
// 		$('.diagnosisClass'+Shape).show();
// 		$('.addbtClass'+Shape+position).show();
// 		$('.addbtClass'+Shape).show();
// 	}
// 	var shapeVal = 0;
// 	var stlab;
// 	if(Shape==1){
// 		shapeVal = sShape;
// 		stlab = "s_status";
// 	 }
// 	 else  if(Shape==2){
// 		 shapeVal = hShape;
// 		 stlab = "h_status";
// 	 }
// 	else  if(Shape==3){
// 		shapeVal = aShape;
// 		stlab = "a_status";
// 	}
// 	else  if(Shape==4){
// 		shapeVal = pShape;
// 		stlab = "p_status";
// 	}
// 	else  if(Shape==5){
// 		shapeVal = eShape;
// 		stlab = "e_status";
// 	}
	
// 	for(var i = 1; i<=shapeVal; i++){
		 
// 		var	st = $("#"+stlab+i).val();
// 		 var styleC = $(".diagnosisClass"+Shape).css("display");
// 		 var styleCval = $(".diagnosisClass"+Shape+i).css("display");
		 
// 		 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape).show();
// 			 //if(styleCval == "none"){
// 				 $('#_diagnosis_code'+Shape+i).show();
// 			 //}
// 		 }
// 		 else if(i == shapeVal && styleC == "none"  ){
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 $('.diagnosisClass'+Shape+i).hide();
// 		 }
		 
// 		 if(i == shapeVal && styleC != "none"  ){
// 			 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape+i).show();
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 }
// 			 else {
// 				 $('.diagnosisClass'+Shape+i).show();
// 				 $('#_diagnosis_code'+Shape+i).hide();
// 			}
// 		 }
		 
	 
// 	}

var slable ="";
	ShapeCount = 1;
	if(Shape == 1) {
		slable = "s";
		ShapeCount = sShape;
			}
			 else if(Shape == 2) {
				 slable = "h";
				 ShapeCount = hShape;
			} else if(Shape == 3) {
				slable = "a";
				ShapeCount = aShape;
			} else if(Shape == 4) {
				slable = "p";
				ShapeCount = pShape;
			} else if(Shape == 5) {
				slable = "e";
				ShapeCount = eShape;
			}
	
	if((Shape_status == 1 || Shape_status == 0) ) {
		$("#" + slable + "_to_date"+ position).datepicker("option", "disabled", true);
		$("#" + slable + "_to_date"+ position).val("");
		if ( position==1) {
			$('.diagnosisClass' + Shape + position).hide();
			
			if (ShapeCount ==1) {
				$('.addbtClass' + Shape + position).hide();
		 		$('.addbtClass' + Shape).hide();
		 		$('.diagnosisClass' + Shape).hide();
			}
	 		
		}
		else{
			$('.diagnosisClass' + Shape + position).css("visibility","hidden");
	 		$('.addbtClass' + Shape + position).show();
	 		$('.addbtClass' + Shape).show();
		}
		
	}else {
		$("#" + slable + "_to_date"+ position).datepicker("option", "disabled", false);
		if ( position==1) {
			$('.diagnosisClass' + Shape + position).show();
			$('.diagnosisClass' + Shape).show();
			$('.diagnosisClass' + Shape + position).css("visibility","visible");
		}
		else{
			$('.diagnosisClass' + Shape + position).css("visibility","visible");
		}
		$('.addbtClass' + Shape + position).show();
		$('.addbtClass' + Shape).show();
	}
	
	 $.post('getShapevalueUrl?' + key + "=" + value, {Shape_status:Shape_status }, function(j){
		 
		 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
             var length = j.length;
             if(length != 0){           
		             for ( var i = 0; i < length; i++) {                  
		                             options += '<option value="' + j[i][1]+ '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		             }  
		             if(Shape == 1) {
						    if(Shape_status == 1){
						    	$("select#sShape_value" + position).html(options);
								$("select#sShape_value" + position).val(1);
							}else{
						        $("select#sShape_value" + position).html(options);
							}
					}
					 else if(Shape == 2) {
						    if(Shape_status == 1){
						        $("select#hShape_value" + position).html(options);
								$("select#hShape_value" + position).val(1);
							}else{
						        $("select#hShape_value" + position).html(options);
							}
					} else if(Shape == 3) {
						   if(Shape_status == 1){
							     $("select#aShape_value" + position).html(options);
								$("select#aShape_value" + position).val(1);
							}else{
						        $("select#aShape_value" + position).html(options);
							}
					} else if(Shape == 4) {
						   if(Shape_status == 1){
							   $("select#pShape_value" + position).html(options);
								$("select#pShape_value" + position).val(1);
							}else{
						        $("select#pShape_value" + position).html(options);
							}
					} else if(Shape == 5) {
						  if(Shape_status == 1){
							  $("select#eShape_value" + position).html(options);
								$("select#eShape_value" + position).val(1);
							}else{
						        $("select#eShape_value" + position).html(options);
							}
					}
		            
             }
		 
		 
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	 
// 	}
}

function AutoCompleteDiagnosis(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getMNHDistinctICDList?" + key + "=" + value,
				data : {
					a : code,b:"ALL"
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					susval.push("Others");

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
				alert("Please Enter Approved Diagnosis ");			
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}





sShape=1;
function sShape_add_fn(q){
	 if( $('#sShape_add'+q).length )        
	 {
			$("#sShape_add"+q).hide();
			 $("#sShape_remove"+q).hide();	

	 }
	 sShape=sShape+1;

	 $("input#sShape_count").val(sShape);
	 
	$("table#s_madtable").append('<tr id="tr_sShape'+sShape+'">'
	+'<td  style="width: 2%;">'+sShape+'</td>'
	+'<td>'
	+'<select name="s_status'+sShape+'" id="s_status'+sShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(1,'+sShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="sShape_value'+sShape+'" id="sShape_value'+sShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="s_from_date'+sShape+'" name="s_from_date'+sShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="s_to_date'+sShape+'" name="s_to_date'+sShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	+'<td style="""> '
	+' <input type="text" name="s_from_date'+sShape+'" id="s_from_date'+sShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="s_to_date'+sShape+'" id="s_to_date'+sShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'

	
	+'  <td style="visibility:hidden; "  class="diagnosisClass1'+sShape+'">'
	+' <input type="text" name="_diagnosis_code1'+sShape+'" id="_diagnosis_code1'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="s_diagnosis_code2'+sShape+'" id="s_diagnosis_code2'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="s_diagnosis_code3'+sShape+'" id="s_diagnosis_code3'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="s_diagnosis_code4'+sShape+'" id="s_diagnosis_code4'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="s_diagnosis_code5'+sShape+'" id="s_diagnosis_code5'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="s_diagnosis_code6'+sShape+'" id="s_diagnosis_code6'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="sShape_ch_id'+sShape+'" name="sShape_ch_id'+sShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+sShape+'" onclick="sShape_add_fn('+sShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sShape_remove'+sShape+'" onclick="sShaperemove_fn('+sShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	 
	 datepicketDate('s_from_date'+sShape);
	 datepicketDate('s_to_date'+sShape);
	 $( "#s_to_date"+sShape ).datepicker( "option", "maxDate", null);
	 statusChange(1, sShape, $("#s_status" + sShape).val());
// 	if(q!=0){
// 		var preShape=sShape-1;
// 			$("#sShape_value"+preShape+" > option").clone().appendTo("#sShape_value"+sShape);
// 			$("#s_status"+sShape).val($("#s_status"+preShape).val());
// 			$("#sShape_value"+sShape).val($("#sShape_value"+preShape).val());
// 			statusChange(1,sShape,$("#s_status"+preShape).val());
// 			$("#s_status"+sShape+" option[value='1']").remove();
// 			$("#s_status"+preShape+" option[value='1']").remove();
// 			$("#s_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function sShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_sShape"+R).remove(); 
						 R = R-1;
					 $("#sShape_add"+R).show();
					 $("#sShape_remove"+R).show();	
					 $("input#sShape_count").val(R);
					 sShape=sShape-1;
// 						 if(sShape == 1){
// 							var s_val = $("#s_status"+sShape).val();
// 							var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 							 var lis2 = $("#s_status"+sShape).html();
// 							 $('#s_status'+sShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 							 $("#s_status"+sShape).val(s_val);
// 							}
			
	 }

	 }
	 

hShape=1;
function hShape_add_fn(q){
	 if( $('#hShape_add'+q).length )        
	 {
			$("#hShape_add"+q).hide();
			 $("#hShape_remove"+q).hide();	

	 }
	 hShape=hShape+1;
	
	
	 $("input#hShape_count").val(hShape);
	 
	$("table#h_madtable").append('<tr id="tr_hShape'+hShape+'">'
	+'<td  style="width: 2%;">'+hShape+'</td>'
	+'<td>'
	+'<select name="h_status'+hShape+'" id="h_status'+hShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(2,'+hShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="hShape_value'+hShape+'" id="hShape_value'+hShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="h_from_date'+hShape+'" name="h_from_date'+hShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="h_to_date'+hShape+'" name="h_to_date'+hShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
	+'<td style="""> '
	+' <input type="text" name="h_from_date'+hShape+'" id="h_from_date'+hShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="h_to_date'+hShape+'" id="h_to_date'+hShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	
	+'  <td style="visibility:hidden; "  class="diagnosisClass2'+hShape+'">'
	+' <input type="text" name="_diagnosis_code2'+hShape+'" id="_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="h_diagnosis_code2'+hShape+'" id="h_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="h_diagnosis_code3'+hShape+'" id="h_diagnosis_code3'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="h_diagnosis_code4'+hShape+'" id="h_diagnosis_code4'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="h_diagnosis_code5'+hShape+'" id="h_diagnosis_code5'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="h_diagnosis_code6'+hShape+'" id="h_diagnosis_code6'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="hShape_ch_id'+hShape+'" name="hShape_ch_id'+hShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+hShape+'" onclick="hShape_add_fn('+hShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "hShape_remove'+hShape+'" onclick="hShaperemove_fn('+hShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	 datepicketDate('h_from_date'+hShape);
	 datepicketDate('h_to_date'+hShape);
	 $( "#h_to_date"+hShape ).datepicker( "option", "maxDate", null);
	 statusChange(2,hShape,$("#h_status"+hShape).val());
// 	if(q!=0){
// 		var preShape=hShape-1;
// 			$("#hShape_value"+preShape+" > option").clone().appendTo("#hShape_value"+hShape);
// 			$("#h_status"+hShape).val($("#h_status"+preShape).val());
// 			$("#hShape_value"+hShape).val($("#hShape_value"+preShape).val());
// 			statusChange(2,hShape,$("#h_status"+preShape).val());
// 			$("#h_status"+hShape+" option[value='1']").remove();
// 			$("#h_status"+preShape+" option[value='1']").remove();
// 			$("#h_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function hShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_hShape"+R).remove(); 
						 R = R-1;
					 $("#hShape_add"+R).show();
					 $("#hShape_remove"+R).show();
					 $("input#hShape_count").val(R);
					 hShape=hShape-1;
// 					 if(hShape == 1){
// 						var s_val = $("#h_status"+hShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#h_status"+hShape).html();
// 						 $('#h_status'+hShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#h_status"+hShape).val(s_val);
// 						}
	 }

	 }


aShape=1;
function aShape_add_fn(q){
	 if( $('#aShape_add'+q).length )        
	 {
			$("#aShape_add"+q).hide();
			 $("#aShape_remove"+q).hide();	

	 }
	 aShape=aShape+1;
	 $("input#aShape_count").val(aShape);
	 
	$("table#a_madtable").append('<tr id="tr_aShape'+aShape+'">'
	+'<td  style="width: 2%;">'+aShape+'</td>'
	+'<td>'
	+'<select name="a_status'+aShape+'" id="a_status'+aShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(3,'+aShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="aShape_value'+aShape+'" id="aShape_value'+aShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="a_from_date'+aShape+'" name="a_from_date'+aShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="a_to_date'+aShape+'" name="a_to_date'+aShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	+'<td style="""> '
	+' <input type="text" name="a_from_date'+aShape+'" id="a_from_date'+aShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="a_to_date'+aShape+'" id="a_to_date'+aShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass3'+aShape+'">'
	+' <input type="text" name="_diagnosis_code3'+aShape+'" id="_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="a_diagnosis_code2'+aShape+'" id="a_diagnosis_code2'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="a_diagnosis_code3'+aShape+'" id="a_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="a_diagnosis_code4'+aShape+'" id="a_diagnosis_code4'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="a_diagnosis_code5'+aShape+'" id="a_diagnosis_code5'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="a_diagnosis_code6'+aShape+'" id="a_diagnosis_code6'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="aShape_ch_id'+aShape+'" name="aShape_ch_id'+aShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+aShape+'" onclick="aShape_add_fn('+aShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "aShape_remove'+aShape+'" onclick="aShaperemove_fn('+aShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	 datepicketDate('a_from_date'+aShape);
	 datepicketDate('a_to_date'+aShape);
	 $( "#a_to_date"+aShape ).datepicker( "option", "maxDate", null);
	 statusChange(3,aShape,$("#a_status"+aShape).val());
	 
// 	if(q!=0){
// 		var preShape=aShape-1;
// 			$("#aShape_value"+preShape+" > option").clone().appendTo("#aShape_value"+aShape);
// 			$("#a_status"+aShape).val($("#a_status"+preShape).val());
// 			$("#aShape_value"+aShape).val($("#aShape_value"+preShape).val());
// 			statusChange(3,aShape,$("#a_status"+preShape).val());
// 			$("#a_status"+aShape+" option[value='1']").remove();
// 			$("#a_status"+preShape+" option[value='1']").remove();
// 			$("#a_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}


function aShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_aShape"+R).remove(); 
						 R = R-1;
					 $("#aShape_add"+R).show();
					 $("#aShape_remove"+R).show();	
					 $("input#aShape_count").val(R);
					 aShape=aShape-1;
// 					 if(aShape == 1){
// 						var s_val = $("#a_status"+aShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#a_status"+aShape).html();
// 						 $('#a_status'+aShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#a_status"+aShape).val(s_val);
// 						}
	 }

	 }
	 


pShape=1;
function pShape_add_fn(q){
	 if( $('#pShape_add'+q).length )        
	 {
			$("#pShape_add"+q).hide();
			 $("#pShape_remove"+q).hide();	

	 }
	 pShape=pShape+1;
	 $("input#pShape_count").val(pShape);
	
	 
	$("table#p_madtable").append('<tr id="tr_pShape'+pShape+'">'
	+'<td  style="width: 2%;">'+pShape+'</td>'
	+'<td>'
	+'<select name="p_status'+pShape+'" id="p_status'+pShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(4,'+pShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="pShape_value'+pShape+'" id="pShape_value'+pShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="p_from_date'+pShape+'" name="p_from_date'+pShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="p_to_date'+pShape+'" name="p_to_date'+pShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
	+'<td style="""> '
	+' <input type="text" name="p_from_date'+pShape+'" id="p_from_date'+pShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="p_to_date'+pShape+'" id="p_to_date'+pShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass4'+pShape+'">'
	+' <input type="text" name="_diagnosis_code4'+pShape+'" id="_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="p_diagnosis_code2'+pShape+'" id="p_diagnosis_code2'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="p_diagnosis_code3'+pShape+'" id="p_diagnosis_code3'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="p_diagnosis_code4'+pShape+'" id="p_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="p_diagnosis_code5'+pShape+'" id="p_diagnosis_code5'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="p_diagnosis_code6'+pShape+'" id="p_diagnosis_code6'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="pShape_ch_id'+pShape+'" name="pShape_ch_id'+pShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+pShape+'" onclick="pShape_add_fn('+pShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pShape_remove'+pShape+'" onclick="pShaperemove_fn('+pShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	 datepicketDate('p_from_date'+pShape);
	 datepicketDate('p_to_date'+pShape);
	 $( "#p_to_date"+pShape ).datepicker( "option", "maxDate", null);
	 statusChange(4,pShape,$("#p_status"+pShape).val());
	 
// 	if(q!=0){
// 		var preShape=pShape-1;
// 			$("#pShape_value"+preShape+" > option").clone().appendTo("#pShape_value"+pShape);
// 			$("#p_status"+pShape).val($("#p_status"+preShape).val());
// 			$("#pShape_value"+pShape).val($("#pShape_value"+preShape).val());
// 			statusChange(4,pShape,$("#p_status"+preShape).val());
// 			$("#p_status"+pShape+" option[value='1']").remove();
// 			$("#p_status"+preShape+" option[value='1']").remove();
// 			$("#p_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function pShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_pShape"+R).remove(); 
						 R = R-1;
					 $("#pShape_add"+R).show();
					 $("#pShape_remove"+R).show();	
					 $("input#pShape_count").val(R);
					 pShape=pShape-1;
// 					 if(pShape == 1){
// 						var s_val = $("#p_status"+pShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#p_status"+pShape).html();
// 						 $('#p_status'+pShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#p_status"+pShape).val(s_val);
// 						}
	 }

	 }

eShape=1;
function eShape_add_fn(q){
	 if( $('#eShape_add'+q).length )        
	 {
			$("#eShape_add"+q).hide();
			 $("#eShape_remove"+q).hide();	
	 }
	 eShape=eShape+1;
	 $("input#eShape_count").val(eShape);
	 
	$("table#e_madtable").append('<tr id="tr_eShape'+eShape+'">'
	+'<td  style="width: 2%;">'+eShape+'</td>'
	+'<td>'
	+'<select name="e_status'+eShape+'" id="e_status'+eShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(5,'+eShape+',this.options[this.selectedIndex].value)">'
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="eShape_value'+eShape+'" id="eShape_value'+eShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	
// 	+'	<td style="">'
// 	+'		<input type="date" id="e_from_date'+eShape+'" name="e_from_date'+eShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="e_to_date'+eShape+'" name="e_to_date'+eShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
	+'<td style="""> '
	+' <input type="text" name="e_from_date'+eShape+'" id="e_from_date'+eShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="e_to_date'+eShape+'" id="e_to_date'+eShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass5'+eShape+'">'
	+' <input type="text" name="_diagnosis_code5'+eShape+'" id="_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="e_diagnosis_code2'+eShape+'" id="e_diagnosis_code2'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="e_diagnosis_code3'+eShape+'" id="e_diagnosis_code3'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="e_diagnosis_code4'+eShape+'" id="e_diagnosis_code4'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="e_diagnosis_code5'+eShape+'" id="e_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="e_diagnosis_code6'+eShape+'" id="e_diagnosis_code6'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="eShape_ch_id'+eShape+'" name="eShape_ch_id'+eShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+eShape+'" onclick="eShape_add_fn('+eShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eShape_remove'+eShape+'" onclick="eShaperemove_fn('+eShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	
	 datepicketDate('e_from_date'+eShape);
	 datepicketDate('e_to_date'+eShape);
	 $( "#e_to_date"+eShape ).datepicker( "option", "maxDate", null);
	 statusChange(5,eShape,$("#e_status"+eShape).val());
	 
	 
// 	if(q!=0){
// 		var preShape=eShape-1;
// 			$("#eShape_value"+preShape+" > option").clone().appendTo("#eShape_value"+eShape);
// 			$("#e_status"+eShape).val($("#e_status"+preShape).val());
// 			$("#eShape_value"+eShape).val($("#eShape_value"+preShape).val());
// 			statusChange(5,eShape,$("#e_status"+preShape).val());
// 			$("#e_status"+eShape+" option[value='1']").remove();
// 			$("#e_status"+preShape+" option[value='1']").remove();
// 			$("#e_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function eShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_eShape"+R).remove(); 
						 R = R-1;
					 $("#eShape_add"+R).show();
					 $("#eShape_remove"+R).show();
					 $("input#eShape_count").val(R);
					 eShape=eShape-1;
// 					 if(eShape == 1){
// 						var s_val = $("#e_status"+eShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#e_status"+eShape).html();
// 						 $('#e_status'+eShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#e_status"+eShape).val(s_val);
// 						}
	 }
	 
	 
}
 
	 
	 
	 
	 
	 
	 
	 
	 
	 cCope=1;
	 function cCope_add_fn(q){
	 	 if( $('#cCope_add'+q).length )        
	 	 {
	 			$("#cCope_add"+q).hide();
	 			 $("#cCope_remove"+q).hide();	
	 	 }
	 	 cCope=cCope+1;
	 	 $("input#cCope_count").val(cCope);
	 	 
	 	$("table#c_cmadtable").append('<tr id="tr_cCope'+cCope+'">'
	 	+'<td  style="width: 2%;">'+cCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_cvalue'+cCope+'" id="c_cvalue'+cCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+cCope+')">'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="cCopClass'+cCope+'" >'
		+'<input type="text" name="c_cother'+cCope+'" id="c_cother'+cCope+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
		+' </td>'
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_cdescription'+cCope+'" id="c_cdescription'+cCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+cCope+'" name="cCope_ch_id'+cCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+cCope+'" onclick="cCope_add_fn('+cCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "cCope_remove'+cCope+'" onclick="cCoperemove_fn('+cCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	
	 	var thisT = document.getElementById('c_cvalue'+cCope)
	 	onCCopeChange(thisT,cCope);
	 	$("#c_cvalue"+cCope+" option[value='0']").remove();
	 	$("#c_cvalue"+cCope+" option[value='1']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='0']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='1']").remove();
	 	
	 }

	 function cCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_cCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#cCope_add"+R).show();
	 					 $("#cCope_remove"+R).show();
	 					 $("input#cCope_count").val(R);
	 					cCope=cCope-1;
	 					if(cCope == 1){
							var s_val = $("#c_cvalue"+cCope).val();
							var lis1 = '<option value="${getcCopeList[0][1]}" name="${getcCopeList[0][0]}">${getcCopeList[0][0]}</option>'
							var lis2 = '<option value="${getcCopeList[1][1]}" name="${getcCopeList[1][0]}">${getcCopeList[1][0]}</option>'
							var lis3 = $("#c_cvalue"+cCope).html();
							 $('#c_cvalue'+cCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_cvalue"+cCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_cvalue'+cCope)
	 				 	onCCopeChange(thisT,cCope);
	 	 }

	 }
	 
	
	 
	 oCope=1;
	 function oCope_add_fn(q){
	 	 if( $('#oCope_add'+q).length )        
	 	 {
	 			$("#oCope_add"+q).hide();
	 			 $("#oCope_remove"+q).hide();	
	 	 }
	 	 oCope=oCope+1;
	 	 $("input#oCope_count").val(oCope);
	 	 
	 	$("table#c_omadtable").append('<tr id="tr_oCope'+oCope+'">'
	 	+'<td  style="width: 2%;">'+oCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_ovalue'+oCope+'" id="c_ovalue'+oCope+'" '
	 	+'	class="form-control-sm form-control"  >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_odescription'+oCope+'" id="c_odescription'+oCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+oCope+'" name="oCope_ch_id'+oCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+oCope+'" onclick="oCope_add_fn('+oCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "oCope_remove'+oCope+'" onclick="oCoperemove_fn('+oCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	$("#c_ovalue"+oCope+" option[value='0']").remove();
		$("#c_ovalue"+(oCope-1)+" option[value='0']").remove();
	 	
	 }

	 function oCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_oCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#oCope_add"+R).show();
	 					 $("#oCope_remove"+R).show();
	 					 $("input#oCope_count").val(R);
	 					oCope=oCope-1;
	 					if(oCope == 1){
							var s_val = $("#c_ovalue"+oCope).val();
							var lis1 = '<option value="${getoCopeList[0][1]}" name="${getoCopeList[0][0]}">${getoCopeList[0][0]}</option>'
							var lis2 = $("#c_ovalue"+oCope).html();
							 $('#c_ovalue'+oCope).empty().append(""+lis1+lis2);
							 $("#c_ovalue"+oCope).val(s_val);
							}
	 	 }

	 }
	 
	 pCope=1;
	 function pCope_add_fn(q){
	 	 if( $('#pCope_add'+q).length )        
	 	 {
	 			$("#pCope_add"+q).hide();
	 			 $("#pCope_remove"+q).hide();	
	 	 }
	 	 pCope=pCope+1;
	 	 $("input#pCope_count").val(pCope);
	 	 
	 	$("table#c_pmadtable").append('<tr id="tr_pCope'+pCope+'">'
	 	+'<td  style="width: 2%;">'+pCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_pvalue'+pCope+'" id="c_pvalue'+pCope+'" '
	 	+'	class="form-control-sm form-control"  >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_pdescription'+pCope+'" id="c_pdescription'+pCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+pCope+'" name="pCope_ch_id'+pCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+pCope+'" onclick="pCope_add_fn('+pCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pCope_remove'+pCope+'" onclick="pCoperemove_fn('+pCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	$("#c_pvalue"+pCope+" option[value='0']").remove();
	 	$("#c_pvalue"+pCope+" option[value='1']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='0']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='1']").remove();
	 	
	 }

	 function pCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_pCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#pCope_add"+R).show();
	 					 $("#pCope_remove"+R).show();
	 					 $("input#pCope_count").val(R);
	 					pCope=pCope-1;
	 					if(pCope == 1){
							var s_val = $("#c_pvalue"+pCope).val();
							var lis1 = '<option value="${getpCopeList[0][1]}" name="${getpCopeList[0][0]}">${getpCopeList[0][0]}</option>'
							var lis2 = '<option value="${getpCopeList[1][1]}" name="${getpCopeList[1][0]}">${getpCopeList[1][0]}</option>'
							var lis3 = $("#c_pvalue"+pCope).html();
							 $('#c_pvalue'+pCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_pvalue"+pCope).val(s_val);
							}
	 	 }

	 }
	 
	 
	 eCope=1;
	 function eCope_add_fn(q){
	 	 if( $('#eCope_add'+q).length )        
	 	 {
	 			$("#eCope_add"+q).hide();
	 			 $("#eCope_remove"+q).hide();	
	 	 }
	 	 eCope=eCope+1;
	 	 $("input#eCope_count").val(eCope);
	 	 
	 	$("table#c_emadtable").append('<tr id="tr_eCope'+eCope+'">'
	 	+'<td  style="width: 2%;">'+eCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_evalue'+eCope+'" id="c_evalue'+eCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onECopeChange(this,'+eCope+')" >'
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="eCopClass'+eCope+'">'
	 	+'<select name="c_esubvalue'+eCope+'" id="c_esubvalue'+eCope+'" onchange="onECopeSubChange(this,'+eCope+')"'
	 	+'			class="form-control-sm form-control" >'
	 	+'			<option value="0">--Select--</option>'																
	 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
	 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'			</c:forEach></select>   </td>'
	 	+'<td style="display:none;" class="eCop_otherClass'+eCope+'" >'
	 	+'	 <input type="text" name="c_esubvalueother'+eCope+'" id="c_esubvalueother'+eCope+'" class="form-control-sm form-control" autocomplete="off" >'						                              
	 	+'	   </td>'
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_edescription'+eCope+'" id="c_edescription'+eCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
	 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+eCope+'" name="eCope_ch_id'+eCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+eCope+'" onclick="eCope_add_fn('+eCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eCope_remove'+eCope+'" onclick="eCoperemove_fn('+eCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
	 	+'</tr>');
	 	
	 	$("#c_evalue"+eCope+" option[value='0']").remove();
		$("#c_evalue"+(eCope-1)+" option[value='0']").remove();
		var thisT = document.getElementById('c_evalue'+eCope)
	 	onECopeChange(thisT,eCope);
	 	
	 }

	 function eCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_eCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#eCope_add"+R).show();
	 					 $("#eCope_remove"+R).show();
	 					 $("input#eCope_count").val(R);
	 					eCope=eCope-1;
	 					if(eCope == 1){
							var s_val = $("#c_evalue"+eCope).val();
							var lis1 = '<option value="${geteCopeList[0][1]}" name="${geteCopeList[0][0]}">${geteCopeList[0][0]}</option>'
							var lis2 = $("#c_evalue"+eCope).html();
							 $('#c_evalue'+eCope).empty().append(""+lis1+lis2);
							 $("#c_evalue"+eCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_evalue'+eCope)
	 				 	onECopeChange(thisT,eCope);
	 	 }

	 }
	 
	 
	 function medical_cat_save(){
		 var count_classi = 0;
		 
		 
		 if($("#madical_authority").val().trim()==""){
				alert("Please Enter Authority");
				$("#madical_authority").focus();				
				return false;
			}
		 
		 if($("input#madical_date_of_authority").val().trim()=="" || $("#madical_date_of_authority").val().trim()=="DD/MM/YYYY"){
				alert("Please Enter Date of Authority");
				$("#madical_date_of_authority").focus();
				return false;
			}
		

		 	var comm_date=$("#comm_date").val();
			if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#madical_date_of_authority").val())) {
				alert("Authority Date should be Greater than Commission Date");
				$("#madical_date_of_authority").focus();
				return false;
		    }
		 
		 if ($("#check_1bx").prop('checked')){
				if($("input#1bx_from_date").val().trim()=="" || $("input#1bx_from_date").val().trim()=="DD/MM/YYYY"){
					alert("Please Enter From Date");
					$("#1bx_from_date").focus();
					return false;
				}
				if($("input#1bx_to_date").val().trim()=="" || $("input#1bx_to_date").val().trim()=="DD/MM/YYYY"){
					alert("Please Enter To Date");
					$("#1bx_to_date").focus();
					return false;
				}
				if(getformatedDate($("input#1bx_from_date").val()) > getformatedDate($("#1bx_to_date").val())) {
					alert("To Date should be Greater than Or Equal to From Date");
					$("#1bx_to_date").focus();
					
					return false;
			    }
				if($("#1bx_diagnosis_code").val().trim()==""){
						alert("Please Enter Diagnosis");
						$("#1bx_diagnosis_code").focus();				
						return false;
					}
				
			  }
			else{
				
				$("#1bx_from_date").val("");
				$("#1bx_to_date").val("");
				$("#1bx_diagnosis_code").val("");	
				
				for(var j=1; j<=sShape; j++){
					if($("#s_status"+j).val()=='0'){
						alert("Please Select S-Shape Status In "+j+" Row");
						$("#s_status"+j).focus();
						return false;
					}
					
					if($("#sShape_value"+j).val()=='0'){
						alert("Please Select S-Shape Value In "+j+" Row");
						$("#sShape_value"+j).focus();
						return false;
					}
					if($("#s_status"+j).val() == "1" && $("#sShape_value"+j).val()=="1B"){
						if($("input#s_to_date"+j).val().trim()!="" && $("input#s_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#s_from_date"+j).val().trim()=="" || $("input#s_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter S-Shape From Date In "+j+" Row");
								$("#s_from_date"+j).focus();
								return false;
							}
						}
						if($("input#s_from_date"+j).val().trim()!="" && $("input#s_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#s_to_date"+j).val().trim()=="" || $("input#s_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter S-Shape To Date In "+j+" Row");
								$("#s_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#s_status"+j).val() != "0" && $("#s_status"+j).val() != "1")
						count_classi++;
					
					if($("#s_status"+j).val() != "1"){
						if($("input#s_from_date"+j).val().trim()=="" || $("input#s_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter S-Shape From Date In "+j+" Row");
							$("#s_from_date"+j).focus();
							return false;
						}
						if($("input#s_to_date"+j).val().trim()=="" || $("input#s_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter S-Shape To Date In "+j+" Row");
							$("#s_to_date"+j).focus();
							return false;
						}
					}	
				
					if(($("#s_status"+j).val() == "1" && $("#sShape_value"+j).val()=="1B" && $("input#s_from_date"+j).val().trim()!="" 
							&& $("input#s_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#s_to_date"+j).val().trim()!="" 
							&& $("input#s_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#s_status"+j).val() != "1"){
						if(getformatedDate($("input#s_from_date"+j).val()) > getformatedDate($("#s_to_date"+j).val())) {
							alert("S-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#s_to_date"+j).focus();
							
							return false;
					    }
					}
					
			// 		if($("#s_status"+j).val() != "1"){			
			// 			if($("#_diagnosis_code1"+j).val().trim()==""){
			// 				alert("Please Enter S-Shape Diagnosis "+j+" Row");
			// 				$("#_diagnosis_code1"+j).focus();				
			// 				return false;
			// 			}
			// 		}
				}
				for(var j=1; j<=hShape; j++){
					if($("#h_status"+j).val()=='0'){
						alert("Please Select H-Shape Status In "+j+" Row");
						$("#h_status"+j).focus();
						return false;
					}
					
					if($("#hShape_value"+j).val()=='0'){
						alert("Please Select H-Shape Value In "+j+" Row");
						$("#hShape_value"+j).focus();
						return false;
					}
					if($("#h_status"+j).val() == "1" && $("#hShape_value"+j).val()=="1B"){
						if($("input#h_to_date"+j).val().trim()!="" && $("input#h_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#h_from_date"+j).val().trim()=="" || $("input#h_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter H-Shape From Date In "+j+" Row");
								$("#h_from_date"+j).focus();
								return false;
							}
						}
						if($("input#h_from_date"+j).val().trim()!="" && $("input#h_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#h_to_date"+j).val().trim()=="" || $("input#h_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter H-Shape To Date In "+j+" Row");
								$("#h_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#h_status"+j).val() != "0" && $("#h_status"+j).val() != "1")
						count_classi++;
					
					if($("#h_status"+j).val() != "1"){
						if($("input#h_from_date"+j).val().trim()=="" || $("input#h_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter H-Shape From Date In "+j+" Row");
							$("#h_from_date"+j).focus();
							return false;
						}
						if($("input#h_to_date"+j).val().trim()=="" || $("input#h_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter H-Shape To Date In "+j+" Row");
							$("#h_to_date"+j).focus();
							return false;
						}
					}
					
					if(($("#h_status"+j).val() == "1" && $("#hShape_value"+j).val()=="1B" && $("input#h_from_date"+j).val().trim()!="" 
						&& $("input#h_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#h_to_date"+j).val().trim()!="" 
						&& $("input#h_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#h_status"+j).val() != "1"){
						if(getformatedDate($("input#h_from_date"+j).val()) > getformatedDate($("#h_to_date"+j).val())) {
							alert("H-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#h_to_date"+j).focus();
							return false;
					    }
					}
					
					
					
				}
				for(var j=1; j<=aShape; j++){
					if($("#a_status"+j).val()=='0'){
						alert("Please Select A-Shape Status In "+j+" Row");
						$("#a_status"+j).focus();
						return false;
					}
					
					if($("#aShape_value"+j).val()=='0'){
						alert("Please Select A-Shape Value In "+j+" Row");
						$("#aShape_value"+j).focus();
						return false;
					}
					if($("#a_status"+j).val() == "1" && $("#aShape_value"+j).val()=="1B"){
						if($("input#a_to_date"+j).val().trim()!="" && $("input#a_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#a_from_date"+j).val().trim()=="" || $("input#a_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter A-Shape From Date In "+j+" Row");
								$("#a_from_date"+j).focus();
								return false;
							}
						}
						if($("input#a_from_date"+j).val().trim()!="" && $("input#a_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#a_to_date"+j).val().trim()=="" || $("input#a_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter A-Shape To Date In "+j+" Row");
								$("#a_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#a_status"+j).val() != "0" && $("#a_status"+j).val() != "1")
						count_classi++;
					
					if($("#a_status"+j).val() != "1"){
						if($("input#a_from_date"+j).val().trim()=="" || $("input#a_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter A-Shape From Date In "+j+" Row");
							$("#a_from_date"+j).focus();
							return false;
						}
						if($("input#a_to_date"+j).val().trim()=="" || $("input#a_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter A-Shape To Date In "+j+" Row");
							$("#a_to_date"+j).focus();
							return false;
						}
					}
					if(($("#a_status"+j).val() == "1" && $("#aShape_value"+j).val()=="1B" && $("input#a_from_date"+j).val().trim()!="" 
						&& $("input#a_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#a_to_date"+j).val().trim()!="" 
						&& $("input#a_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#a_status"+j).val() != "1"){
						if(getformatedDate($("input#a_from_date"+j).val()) > getformatedDate($("#a_to_date"+j).val())) {
							alert("A-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#a_to_date"+j).focus();
							return false;
					    }
					}	
				}
				for(var j=1; j<=pShape; j++){
					if($("#p_status"+j).val()=='0'){
						alert("Please Select P-Shape Status In "+j+" Row");
						$("#p_status"+j).focus();
						return false;
					}
					
					if($("#pShape_value"+j).val()=='0'){
						alert("Please Select P-Shape Value In "+j+" Row");
						$("#pShape_value"+j).focus();
						return false;
					}
					if($("#p_status"+j).val() == "1" && $("#pShape_value"+j).val()=="1B"){
						if($("input#p_to_date"+j).val().trim()!="" && $("input#p_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#p_from_date"+j).val().trim()=="" || $("input#p_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter P-Shape From Date In "+j+" Row");
								$("#p_from_date"+j).focus();
								return false;
							}
						}
						if($("input#p_from_date"+j).val().trim()!="" && $("input#p_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#p_to_date"+j).val().trim()=="" || $("input#p_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter P-Shape To Date In "+j+" Row");
								$("#p_to_date"+j).focus();
								return false;
							}
						}
					}
					if($("#p_status"+j).val() != "0" && $("#p_status"+j).val() != "1")
						count_classi++;
					if($("#p_status"+j).val() != "1"){
						if($("input#p_from_date"+j).val().trim()=="" || $("input#p_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter P-Shape From Date In "+j+" Row");
							$("#p_from_date"+j).focus();
							return false;
						}
						if($("input#p_to_date"+j).val().trim()=="" || $("input#p_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter P-Shape To Date In "+j+" Row");
							$("#p_to_date"+j).focus();
							return false;
						}
					}
					
					if(($("#p_status"+j).val() == "1" && $("#pShape_value"+j).val()=="1B" && $("input#p_from_date"+j).val().trim()!="" 
						&& $("input#p_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#p_to_date"+j).val().trim()!="" 
						&& $("input#p_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#p_status"+j).val() != "1"){
						
						if(getformatedDate($("input#p_from_date"+j).val()) > getformatedDate($("#p_to_date"+j).val())) {
							alert("P-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#p_to_date"+j).focus();
							return false;
					    }
					}
				}
				for(var j=1; j<=eShape; j++){
					
					if($("#e_status"+j).val()=='0'){
						alert("Please Select E-Shape Status In "+j+" Row");
						$("#e_status"+j).focus();
						return false;
					}
					
					if($("#eShape_value"+j).val()=='0'){
						alert("Please Select E-Shape Value In "+j+" Row");
						$("#eShape_value"+j).focus();
						return false;
					}
					if($("#e_status"+j).val() == "1" && $("#eShape_value"+j).val()=="1B"){
						if($("input#e_to_date"+j).val().trim()!="" && $("input#e_to_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#e_from_date"+j).val().trim()=="" || $("input#e_from_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter E-Shape From Date In "+j+" Row");
								$("#e_from_date"+j).focus();
								return false;
							}
						}
						
						if($("input#e_from_date"+j).val().trim()!="" && $("input#e_from_date"+j).val().trim()!="DD/MM/YYYY"){
							if($("input#e_to_date"+j).val().trim()=="" || $("input#e_to_date"+j).val().trim()=="DD/MM/YYYY"){
								alert("Please Enter E-Shape To Date In "+j+" Row");
								$("#e_to_date"+j).focus();
								return false;
							}
						}
					}
					
					if($("#e_status"+j).val() != "0" && $("#e_status"+j).val() != "1")
						count_classi++;
					if($("#e_status"+j).val() != "1"){
						if($("input#e_from_date"+j).val().trim()=="" || $("input#e_from_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter E-Shape From Date In "+j+" Row");
							$("#e_from_date"+j).focus();
							return false;
						}
						if($("input#e_to_date"+j).val().trim()=="" || $("input#e_to_date"+j).val().trim()=="DD/MM/YYYY"){
							alert("Please Enter E-Shape To Date In "+j+" Row");
							$("#e_to_date"+j).focus();
							return false;
						}
					}
					if(($("#e_status"+j).val() == "1" && $("#eShape_value"+j).val()=="1B" && $("input#e_from_date"+j).val().trim()!="" 
						&& $("input#e_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#e_to_date"+j).val().trim()!="" 
						&& $("input#e_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#e_status"+j).val() != "1"){
						
					
						if(getformatedDate($("input#e_from_date"+j).val()) > getformatedDate($("#e_to_date"+j).val())) {
							alert("E-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
							$("#e_to_date"+j).focus();
							return false;
					    }
					}	
				}
				
				for(var j=1; j<=cCope; j++){
						if($("#c_cvalue"+j).val()=="2 [c]"){
							if($("#c_cother"+j).val().trim()==""){
								alert("Please Enter C-Cope Other In "+j+" Row");
								$("#c_cother"+j).focus();
								return false;
							}
						}
				}
				
				for(var j=1; j<=eCope; j++){
					
					if($("#c_evalue"+j).val()=="1"){
						if($("#c_esubvalue"+j).val().trim()=="0"){
							alert("Please Select E-Cope Sub Value In "+j+" Row");
							$("#c_esubvalue"+j).focus();
							return false;
						}
						if($("#c_esubvalueother"+j).val().trim()=="" && $("#c_esubvalue"+j).val().trim()=="k"){
							alert("Please Enter E-Cope Sub Other In "+j+" Row");
							$("#c_esubvalueother"+j).focus();
							return false;
						}
					}
				}
				
				
			}
			if(count_classi >= 3){
				$("#mad_classification_count").val("Z");
			}
			else if(count_classi == 2){
				$("#mad_classification_count").val("Y");
			}
			else if(count_classi == 1){
				$("#mad_classification_count").val("X");
			}
			else
				$("#mad_classification_count").val("NIL");
			
		    var formvalues=$("#madical_category_form").serialize();
		
			var census_id=$("#census_id").val();	
			var comm_id=$('#comm_id').val();			
			formvalues+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;
			
		
			
		   $.post('medical_categoryAction?' + key + "=" + value,formvalues, function(data){
			      
		      
		             	 
		        	 if(data=='1'){
		        		 alert('Data Save/Update Successfully');
		        	
// 		        		 get1BXShapeDetails();
		        	  getsShapeDetails();
		        	  gethShapeDetails();
		        	  getaShapeDetails();
		        	  getpShapeDetails();
		        	  geteShapeDetails();
		        	  getcCopeDetails();
		        	  getoCopeDetails();
		        	  getpCopeDetails();
		        	  geteCopeDetails();
		        	 }
		        	 else{
		        		 alert(data);
		        	 }
		         
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
	}
	 function getsShapeDetails(){	
		 var p_id=$('#census_id').val(); 	
		 var Shape='S'
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				
				
			if(v!=0){	
				 
				$('#s_madtable').show(); 
				$('#s_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
//	 				  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
						$("table#s_madtable").append('<tr id="tr_sShape'+x+'">'
							+'<td class="sShape_srno" style="width: 2%;">'+x+'</td>'
							+'<td>'
							
							+'<select name="s_status'+x+'" id="s_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(1,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="sShape_value'+x+'" id="sShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'s\')">'
							+'		</select>	</td>'
//								+'	<td style="">'
//								+'		<input type="date" id="s_from_date'+x+'" name="s_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//								+'		 </td>'	   
//								+'<td style="">'
//								+'		<input type="date" id="s_to_date'+x+'" name="s_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//								+'   </td>'
							
							+'<td style=""> '
							+' <input type="text" name="s_from_date'+x+'" id="s_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="s_to_date'+x+'" id="s_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'
							
							+'  <td style="visibility:hidden; "  class="diagnosisClass1'+x+'">'
							+' <input type="text" name="_diagnosis_code1'+x+'" id="_diagnosis_code1'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="sShape_ch_id'+x+'" name="sShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass1'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+x+'" onclick="sShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							

							+'</tr>');
						
						 datepicketDate('s_from_date'+x);
						 datepicketDate('s_to_date'+x);
						 $( "#s_to_date"+x ).datepicker( "option", "maxDate", null);
						 
						$("#s_status"+x).val(j[i][0]);
							$.ajaxSetup({
	  							async : false
	  						});

							statusChange(1,x,j[i][0]);
							
							$("#sShape_value"+x).val(j[i][1]); 
//	 						if(x>1){
//	 							$("#s_status"+x+" option[value='1']").remove();
//	 							$("#s_status"+(x-1)+" option[value='1']").remove();
								
//	 						}
//	 						else {
								var thisT = document.getElementById('sShape_value'+x);
								onShapeValueChange(thisT,'s');
//	 						}
							 
							
							
						
			}
				sShape=v;
				$("input#sShape_count").val(v);
				$("input#sShapeOld_count").val(v);
				$("#sShape_add"+v).show(); 
				$("#madical_authority").val(j[i-1][6]); 
				$("#madical_date_of_authority").val(ParseDateColumncommission(j[i-1][7])); 
				$('#mad_classification_count').val(j[i-1][8]);
				
				if(j[i-1][11]!=null && j[i-1][11]!="" && j[i-1][12]!=null && j[i-1][12]!="" && j[i-1][13]!=null && j[i-1][13]!=""){
					$("#check_1bx").prop("checked", true);
					oncheck_1bx();
					 fd=ParseDateColumncommission(j[i-1][11]);
				  	 td=ParseDateColumncommission(j[i-1][12]);
					$("#1bx_from_date").val(fd);
					$("#1bx_to_date").val(td);
					$("#1bx_diagnosis_code").val(j[i-1][13]);
				}
//	  			$('#mad_classification').attr('readonly', true);
				
				setDiagnosis();
				}
			
			
				
		  });
	}


	function gethShapeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='H';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				
			if(v!=0){	
				$('#h_madtable').show(); 
					$('#h_madtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;		
		
						 var fd="DD/MM/YYYY";
						 var td="DD/MM/YYYY";
						 
						 if(j[i][2]!=null && j[i][2]!=""){
							 fd=ParseDateColumncommission(j[i][2]);
//	 					  	 td=ParseDateColumncommission(j[i][3]);
						 }
						 if(j[i][3] != null && j[i][3] != "") {
								td = ParseDateColumncommission(j[i][3]);
							}
						 if (j[i][0]==1) {
							 td="";
							 }
						
							$("table#h_madtable").append('<tr id="tr_hShape'+x+'">'
								+'<td style="width: 2%;">'+x+'</td>'
								+'<td>'
								
								+'<select name="h_status'+x+'" id="h_status'+x+'" '
								+'	class="form-control-sm form-control"  onchange="statusChange(2,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style="">'
								+'<select name="hShape_value'+x+'" id="hShape_value'+x+'" '
								+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'h\')">'
								+'		</select>	</td>'
//	 							+'	<td style="">'
//	 							+'		<input type="date" id="h_from_date'+x+'" name="h_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//	 							+'		 </td>'	   
//	 							+'<td style="">'
//	 							+'		<input type="date" id="h_to_date'+x+'" name="h_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	 							+'   </td>'
								
								
								+'<td style=""> '
								+' <input type="text" name="h_from_date'+x+'" id="h_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
								+'</td>'
								+'<td style=""> '
								+' <input type="text" name="h_to_date'+x+'" id="h_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
								+'</td>'
								+'  <td style="visibility:hidden; "  class="diagnosisClass2'+x+'">'
								+' <input type="text" name="_diagnosis_code2'+x+'" id="_diagnosis_code2'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
								+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
								+'   </td>'

								+' <td style="display:none;"><input type="text" id="hShape_ch_id'+x+'" name="hShape_ch_id'+x+'" value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
								+'<td style="width: 10%;" class="addbtClass2'+x+'">'
								+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+x+'" onclick="hShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
								+'</td>'
								+'</tr>');
							
							datepicketDate('h_from_date'+x);
							 datepicketDate('h_to_date'+x);
							 $( "#h_to_date"+x ).datepicker( "option", "maxDate", null);

							 
							$("#h_status"+x).val(j[i][0]);
							$.ajaxSetup({
	 							async : false
	 						});
							
							statusChange(2,x,j[i][0]);
							

							$("#hShape_value"+x).val(j[i][1]); 
//	 						if(x>1){
//	 							$("#h_status"+x+" option[value='1']").remove();
//	 							$("#h_status"+(x-1)+" option[value='1']").remove();
								
//	 						}
//	 						else {
								var thisT = document.getElementById('hShape_value'+x);
								onShapeValueChange(thisT,'h');
//	 						}
							 
							
							
						
			}
				hShape=v;
				$("input#hShape_count").val(v);
				$("input#hShapeOld_count").val(v);
				$("#hShape_add"+v).show();
				
				setDiagnosis();
				}
				
		  });
	}



	function getaShapeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='A';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				
			if(v!=0){	
				classification=j[0][13];
				$('#a_madtable').show(); 
					$('#a_madtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;		
		
						 var fd="DD/MM/YYYY";
						 var td="DD/MM/YYYY";
						 
						 if(j[i][2]!=null && j[i][2]!=""){
							 fd=ParseDateColumncommission(j[i][2]);
//	 					  	 td=ParseDateColumncommission(j[i][3]);
						 }
						 if(j[i][3] != null && j[i][3] != "") {
								td = ParseDateColumncommission(j[i][3]);
							}
						 if (j[i][0]==1) {
							 td="";
							 }
						
							$("table#a_madtable").append('<tr id="tr_aShape'+x+'">'
								+'<td style="width: 2%;">'+x+'</td>'
								+'<td>'
								+'<select name="a_status'+x+'" id="a_status'+x+'" '
								+'	class="form-control-sm form-control"  onchange="statusChange(3,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style="">'
								+'<select name="aShape_value'+x+'" id="aShape_value'+x+'" '
								+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'a\')">'
								+'		</select>	</td>'
//	 							+'	<td style="">'
//	 							+'		<input type="date" id="a_from_date'+x+'" name="a_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//	 							+'		 </td>'	   
//	 							+'<td style="">'
//	 							+'		<input type="date" id="a_to_date'+x+'" name="a_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	 							+'   </td>'
								+'<td style=""> '
								+' <input type="text" name="a_from_date'+x+'" id="a_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
								+'</td>'
								+'<td style=""> '
								+' <input type="text" name="a_to_date'+x+'" id="a_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
								+'</td>'
								
								+'  <td style="visibility:hidden; "  class="diagnosisClass3'+x+'">'
								+' <input type="text" name="_diagnosis_code3'+x+'" id="_diagnosis_code3'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
								+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
								+'   </td>'
								+' <td style="display:none;"><input type="text" id="aShape_ch_id'+x+'" name="aShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
								+'<td style="width: 10%;" class="addbtClass3'+x+'" >'
								+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+x+'" onclick="aShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
								+'</td>'
								+'</tr>');
							
							datepicketDate('a_from_date'+x);
							 datepicketDate('a_to_date'+x);
							 $( "#a_to_date"+x ).datepicker( "option", "maxDate", null);

							 
							$("#a_status"+x).val(j[i][0]);
							$.ajaxSetup({
								async : false
							});

							statusChange(3,x,j[i][0]);
							
							$("#aShape_value"+x).val(j[i][1]); 
//	 						if(x>1){
//	 							$("#a_status"+x+" option[value='1']").remove();
//	 							$("#a_status"+(x-1)+" option[value='1']").remove();
								
//	 						}
//	 						else {
								var thisT = document.getElementById('aShape_value'+x);
								onShapeValueChange(thisT,'a');
//	 						}
							 
							
							
						
			}
				aShape=v;
				$("input#aShape_count").val(v);
				$("input#aShapeOld_count").val(v);
				$("#aShape_add"+v).show(); 		
				
				setDiagnosis();
				}
				
		  });
	}



	function getpShapeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='P';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				
			if(v!=0){	
				$('#p_madtable').show(); 
					$('#p_madtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;		
		
						 var fd="DD/MM/YYYY";
						 var td="DD/MM/YYYY";
						 
						 if(j[i][2]!=null && j[i][2]!=""){
							 fd=ParseDateColumncommission(j[i][2]);
//	 					  	 td=ParseDateColumncommission(j[i][3]);
						 }
						 if(j[i][3] != null && j[i][3] != "") {
								td = ParseDateColumncommission(j[i][3]);
							}
						 if (j[i][0]==1) {
							 td="";
							 }
						
							$("table#p_madtable").append('<tr id="tr_pShape'+x+'">'
								+'<td style="width: 2%;">'+x+'</td>'
								+'<td>'
								+'<select name="p_status'+x+'" id="p_status'+x+'" '
								+'	class="form-control-sm form-control"  onchange="statusChange(4,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style="">'
								+'<select name="pShape_value'+x+'" id="pShape_value'+x+'" '
								+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'p\')">'
								+'		</select>	</td>'
//	 							+'	<td style="">'
//	 							+'		<input type="date" id="p_from_date'+x+'" name="p_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//	 							+'		 </td>'	   
//	 							+'<td style="">'
//	 							+'		<input type="date" id="p_to_date'+x+'" name="p_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	 							+'   </td>'		
								+'<td style=""> '
								+' <input type="text" name="p_from_date'+x+'" id="p_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
								+'</td>'
								+'<td style=""> '
								+' <input type="text" name="p_to_date'+x+'" id="p_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
								+'</td>'

								+'  <td style="visibility:hidden; "  class="diagnosisClass4'+x+'">'
								+' <input type="text" name="_diagnosis_code4'+x+'" id="_diagnosis_code4'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
								+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
								+'   </td>'
								+' <td style="display:none;"><input type="text" id="pShape_ch_id'+x+'" name="pShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
								+'<td style="width: 10%;" class="addbtClass4'+x+'">'
								+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+x+'" onclick="pShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
								+'</td>'
								+'</tr>');
							
							datepicketDate('p_from_date'+x);
							 datepicketDate('p_to_date'+x);
							 $( "#p_to_date"+x ).datepicker( "option", "maxDate", null);

							 
							$("#p_status"+x).val(j[i][0]);
							$.ajaxSetup({
								async : false
							});

							statusChange(4,x,j[i][0]);
							$.ajaxSetup({
								async : false
							});

							$("#pShape_value"+x).val(j[i][1]); 
//	 						if(x>1){
//	 							$("#p_status"+x+" option[value='1']").remove();
//	 							$("#p_status"+(x-1)+" option[value='1']").remove();
								
//	 						}
//	 						else {
								var thisT = document.getElementById('pShape_value'+x);
								onShapeValueChange(thisT,'p');
//	 						}
							 
							
							
						
			}
				pShape=v;
				$("input#pShape_count").val(v);
				$("input#pShapeOld_count").val(v);
				$("#pShape_add"+v).show(); 	
				setDiagnosis();
				}
				
		  });
	}


	function geteShapeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='E';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				 
			if(v!=0){	
				$('#e_madtable').show(); 
					$('#e_madtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;		
		
						 var fd="DD/MM/YYYY";
						 var td="DD/MM/YYYY";
						 
						 if(j[i][2]!=null && j[i][2]!=""){
							 fd=ParseDateColumncommission(j[i][2]);
//	 					  	 td=ParseDateColumncommission(j[i][3]);
						 }
						 if(j[i][3] != null && j[i][3] != "") {
								td = ParseDateColumncommission(j[i][3]);
							}
						 if (j[i][0]==1) {
							 td="";
							 }
						
							$("table#e_madtable").append('<tr id="tr_eShape'+x+'">'
								+'<td style="width: 2%;">'+x+'</td>'
								+'<td>'
								+'<select name="e_status'+x+'" id="e_status'+x+'" '
								+'	class="form-control-sm form-control"  onchange="statusChange(5,'+x+',this.options[this.selectedIndex].value)">'
								+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
								+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
								+'	</c:forEach>'
								+'		</select>'
								+'  </td>'
								+'   <td style="">'
								+'<select name="eShape_value'+x+'" id="eShape_value'+x+'" '
								+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'e\')">'
								+'		</select>	</td>'
//	 							+'	<td style="">'
//	 							+'		<input type="date" id="e_from_date'+x+'" name="e_from_date'+x+'" max="${date_without_time}" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
//	 							+'		 </td>'	   
//	 							+'<td style="">'
//	 							+'		<input type="date" id="e_to_date'+x+'" name="e_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	 							+'   </td>'


								+'<td style=""> '
								+' <input type="text" name="e_from_date'+x+'" id="e_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
								+'</td>'
								+'<td style=""> '
								+' <input type="text" name="e_to_date'+x+'" id="e_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
								+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
								+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
								+'</td>'
								
								+'  <td style="visibility:hidden; "  class="diagnosisClass5'+x+'">'
								+' <input type="text" name="_diagnosis_code5'+x+'" id="_diagnosis_code5'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
								+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
								+'   </td>'
								+' <td style="display:none;"><input type="text" id="eShape_ch_id'+x+'" name="eShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
								+'<td style="width: 10%;" class="addbtClass5'+x+'">'
								+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+x+'" onclick="eShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
								+'</td>'
								+'</tr>');
							
							datepicketDate('e_from_date'+x);
							 datepicketDate('e_to_date'+x);
							 $( "#e_to_date"+x ).datepicker( "option", "maxDate", null);

							 
							$("#e_status"+x).val(j[i][0]);
							$.ajaxSetup({
								async : false
							});

							statusChange(5,x,j[i][0]);
							$.ajaxSetup({
								async : false
							});

							$("#eShape_value"+x).val(j[i][1]); 
//	 						if(x>1){
//	 							$("#e_status"+x+" option[value='1']").remove();
//	 							$("#e_status"+(x-1)+" option[value='1']").remove();
								
//	 						}
//	 						else {
								var thisT = document.getElementById('eShape_value'+x);
								onShapeValueChange(thisT,'e');
//	 						}
							 
							
							
						
			}
				eShape=v;
				$("input#eShape_count").val(v);
				$("input#eShapeOld_count").val(v);
				$("#eShape_add"+v).show(); 
				setDiagnosis();
				}
				
		  });
	}



	function getcCopeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='C_C';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				 
			if(v!=0){	
				
				$('#c_cmadtable').show(); 
					$('#c_cmadtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;												
						cCope=x;
						$("table#c_cmadtable").append('<tr id="tr_cCope'+x+'">'
							 	+'<td  style="width: 2%;">'+x+'</td>'
							 	+'<td>'
							 	
							 	+'<select name="c_cvalue'+x+'" id="c_cvalue'+x+'" '
							 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,1,'+x+');">'
							 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
							 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
							 	+'	</c:forEach>'
							 	+'		</select>'
							 	+'  </td>'
							 	+'<td style="display:none;" class="cCopClass'+x+'" >'
								+'<input type="text" name="c_cother'+x+'" id="c_cother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
								+' </td>'
							 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+x+'" name="cCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							 	+'<td style="width: 10%;" class="CopaddbtClass11" >'
							 	+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+x+'" onclick="cCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							 	+'</td>'
							 	+'</tr>');
						
							$("#c_cvalue"+x).val(j[i][1]); 
							var thisT = document.getElementById('c_cvalue'+x);
						 	onCCopeChange(thisT,x);
							if(x==1){onCopeChangebt(thisT,1,x);}
							
							if(x>1){
							 	$("#c_cvalue"+x+" option[value='0']").remove();
							 	$("#c_cvalue"+x+" option[value='1']").remove();
								$("#c_cvalue"+(x-1)+" option[value='0']").remove();
								$("#c_cvalue"+(x-1)+" option[value='1']").remove();
							}
			
			}
				cCope=v;
				$("input#cCope_count").val(v);
				$("input#cCopeOld_count").val(v);
				$("#cCope_add"+v).show(); 														
				}
				
		  });
	}


	function getoCopeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='C_O';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				 
			if(v!=0){	
				
				$('#c_omadtable').show(); 
					$('#c_omadtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;												
						oCope=x;
						$("table#c_omadtable").append('<tr id="tr_oCope'+x+'">'
							 	+'<td  style="width: 2%;">'+x+'</td>'
							 	+'<td>'
							 	+'<select name="c_ovalue'+x+'" id="c_ovalue'+x+'" '
							 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,'+x+');">'
							 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
							 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
							 	+'	</c:forEach>'
							 	+'		</select>'
							 	+'  </td>'	 	
							 	
							 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+x+'" name="oCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							 	+'<td style="width: 10%;" class="CopaddbtClass21" >'
							 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+x+'" onclick="oCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							 	+'</td>'
							 	+'</tr>');
						$.ajaxSetup({
							async : false
						});
							$("#c_ovalue"+x).val(j[i][1]); 
							
							var thisT = document.getElementById('c_ovalue'+x)
							if(x==1){onCopeChangebt(thisT,2,x);}
							
							if(x>1){
							$("#c_ovalue"+x+" option[value='0']").remove();
							$("#c_ovalue"+(x-1)+" option[value='0']").remove();
							}
			
			}
				oCope=v;
				$("input#oCope_count").val(v);
				$("input#oCopeOld_count").val(v);
				$("#oCope_add"+v).show(); 														
				}
				
		  });
	}

	function getpCopeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='C_P';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
				var v=j.length;
				 
			if(v!=0){	
				
				$('#c_pmadtable').show(); 
					$('#c_pmadtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;												
						pCope=x;
						$("table#c_pmadtable").append('<tr id="tr_pCope'+x+'">'
							 	+'<td  style="width: 2%;">'+x+'</td>'
							 	+'<td>'
							 	+'<select name="c_pvalue'+x+'" id="c_pvalue'+x+'" '
							 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,'+x+');">'
							 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
							 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
							 	+'	</c:forEach>'
							 	+'		</select>'
							 	+'  </td>'	 	
							 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+x+'" name="pCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							 	+'<td style="width: 10%;" class="CopaddbtClass31">'
							 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+x+'" onclick="pCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							 	+'</td>'
							 	+'</tr>');
						$.ajaxSetup({
							async : false
						});
							$("#c_pvalue"+x).val(j[i][1]); 
							
							var thisT = document.getElementById('c_pvalue'+x)
							if(x==1){onCopeChangebt(thisT,3,x);}
							
							if(x>1){
							$("#c_pvalue"+x+" option[value='0']").remove();
							$("#c_pvalue"+x+" option[value='1']").remove();
							$("#c_pvalue"+(x-1)+" option[value='0']").remove();
							$("#c_pvalue"+(x-1)+" option[value='1']").remove();
							}
			
			}
				pCope=v;
				$("input#pCope_count").val(v);
				$("input#pCopeOld_count").val(v);
				$("#pCope_add"+v).show(); 														
				}
				
		  });
	}


	function geteCopeDetails(){
		
		 var p_id=$('#census_id').val(); 
		
		 var Shape='C_E';
		 var i=0;
		 var comm_id = $('#comm_id').val();	
	     var meddtlfillin3008 ='0';
		  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008}, function(j){
				var v=j.length;
				 
			if(v!=0){	
				
				$('#c_emadtable').show(); 
					$('#c_emadtbody').empty(); 
					for(i;i<v;i++){			
						x=i+1;												
						eCope=x;
						$("table#c_emadtable").append('<tr id="tr_eCope'+x+'">'
							 	+'<td  style="width: 2%;">'+x+'</td>'
							 	+'<td>'
							 	+'<select name="c_evalue'+x+'" id="c_evalue'+x+'" '
							 	+'	class="form-control-sm form-control"  onchange="onECopeChange(this,'+x+'); onCopeChangebt(this,4,'+x+');">'
							 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
							 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
							 	+'	</c:forEach>'
							 	+'		</select>'
							 	+'  </td>'	 	
							 	+'<td style="display:none;" class="eCopClass'+x+'">'
							 	+'<select name="c_esubvalue'+x+'" id="c_esubvalue'+x+'" onchange="onECopeSubChange(this,'+x+')"'
							 	+'			class="form-control-sm form-control" >'
							 	+'			<option value="0">--Select--</option>'																
							 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
							 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
							 	+'			</c:forEach></select>   </td>'
							 	+'<td style="display:none;" class="eCop_otherClass'+x+'" >'
							 	+'	 <input type="text" name="c_esubvalueother'+x+'" id="c_esubvalueother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" >'						                              
							 	+'	   </td>'
							 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+x+'" name="eCope_ch_id'+x+'" value="'+j[i][5]+'"  class="form-control autocomplete" autocomplete="off" ></td>'   
							 	+'<td style="width: 10%;" class="CopaddbtClass41">'
							 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+x+'" onclick="eCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							 	+'</td>'
							 	+'</tr>');
						$.ajaxSetup({
							async : false
						});
							$("#c_evalue"+x).val(j[i][1]); 
							$("#c_esubvalue"+x).val(j[i][9]);
							var thisT = document.getElementById('c_evalue'+x)
							onECopeChange(thisT,x);
							if(x==1){onCopeChangebt(thisT,4,x);}
							
							if(x>1){
							$("#c_evalue"+x+" option[value='0']").remove();
							$("#c_evalue"+(x-1)+" option[value='0']").remove();
							}
					
			}
				eCope=v;
				$("input#eCope_count").val(v);
				$("input#eCopeOld_count").val(v);
				$("#eCope_add"+v).show(); 														
				}
				
		  });
	}


//************************************* END MEDICAL *****************************//
 </script> 
  <script>
//***************** START QUALIFICATION DETAILS************************//
//qualification
  
 
  function fn_other_exam() {
  	var text = $("#quali option:selected").text();
  	if(text == "OTHERS"){
  		$("#other_div_exam").show();
  	}
  	else{
  		$("#other_div_exam").hide();
  		$("#exam_other").val("");
  	}
  }


  function fn_other_class() {
  	var text = $("#div_class option:selected").text();
  	if(text == "OTHERS"){
  	
  		$("#other_div_class").show();
  		
  	}
  	else
  	{
  		$("#other_div_class").hide();
  		$("#class_other").val('');
  	}
  }
 
	 function qualification_save_fn(qs) {
			var dateofBirthyear =$("#dob").text().split('-')[2];
			var currentdate = new Date();
			var currentyear = currentdate.getFullYear();
			var type = $('#quali_type').val();
			var examination_pass = $('#quali').val();
			var exam_other_qua=$('#exam_other').val();
		  	var degree_other=$('#quali_deg_other').val();
			var spec_other=$('#quali_spec_other').val();
			var class_other_qua=$('#class_other').val();
			
			var passing_year = $('#yrOfPass').val();
			var degree = $('#quali_degree').val();
			var specialization = $('#specialization').val();
			var div_class = $('#div_class').val();
			var institute = $('#institute_place').val();
			var qualification_ch_id = $('#qualification_ch_id').val();
			var q_id = $('#census_id').val();
			var com_id = $('#comm_id').val();
			 var qual_authority=$('#qualification_authority').val();
				var qual_doa=$('#qualification_date_of_authority').val();
				 if(qual_authority == null || qual_authority.trim()==""){ 
						alert("Please Enter Authority");
						$("input#qualification_authority").focus();
						return false;
					}
				 
				 if(qual_doa == null || qual_doa.trim()=="" || qual_doa == "DD/MM/YYYY"){ 
						alert("Please Enter Date of Authority");
						$("input#qualification_date_of_authority").focus();
						return false;
					} 
			var subjectvar = $('input[name="multisub"]:checkbox:checked').map(function() {
				return this.value;
			}).get();
			var subject = subjectvar.join(",");
			

			if(type == null || type == "0") {
				alert("Please Select Qualification Type");
				$("#quali_type").focus();
				return false;
			}
		
			if(examination_pass == null || examination_pass == "0") {
				alert("Please Select Examination Pass");
				$("#quali").focus();
				return false;
			}
		
			if($("#quali option:selected").text()==other) {
		     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
		     		alert( "Please Enter Examination Other ");
					
					$("input#exam_other").focus();
					return false;
				   }
		      }
			
			if(degree == null || degree == "0") {
				alert("Please Select The Degree Acquired");
				$("#quali_degree").focus();
				return false;
			}
			  if($("#quali_degree option:selected").text()==other) {
			      	 if(degree_other == null || degree_other.trim()==""){ 	       
			      		alert( "Please Enter Degree Other ");
			 			$("input#quali_deg_other").focus();
			 			return false;
			 		   }
			       }
			if(specialization == null || specialization == "0") {
				alert("Please Select The Specialization");
				$("#specialization").focus();
				return false;
			}
			
		

		
		
	      
	    
	      if($("#specialization option:selected").text()==other) {
	       	 if(spec_other == null || spec_other.trim()==""){ 	 
	        
	  			alert( "Please Enter Specialization Other ");
	  			$("input#quali_spec_other").focus();
	  			return false;
	  		   }
	        }
	      
	      
		if(passing_year.trim()=="") {
			alert("Please Enter Year of Passing");
			$("input#yrOfPass").focus();
			return false;
		}
		if(passing_year.trim() != "") {
			if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
				alert("Please Enter Valid Year of Passing");
				$("input#yrOfPass").focus();
				return false;
			}
		}
		if(div_class == "0") {
			alert("Please Select Div/Grade");
			$("#div_class").focus();
			return false;
		}
		if(div_class=="4") {
	    	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 
	     
				alert( "Please Enter Div/Grade Other ");
				$("input#class_other").focus();
				return false;
			   }
	     }
		if(subject.trim()=="") {
			alert("Please Select The Subject");
			$("select#sub_quali").focus();
			return false;
		}
		if(institute.trim()=="") {
			alert("Please Enter Institute & place");
			$("#institute_place").focus();
			return false;
		}
			$.post('update_offr_qualification_action?' + key + "=" + value, {
				type: type,
				examination_pass: examination_pass,
				passing_year: passing_year,
				div_class: div_class,
				subject: subject,
				institute: institute,
				qualification_ch_id: qualification_ch_id,
				q_id: q_id,
				degree: degree,
				specialization: specialization,
				com_id: com_id,exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,
				degree_other:degree_other,spec_other:spec_other,
				dateofBirthyear: dateofBirthyear,
				qual_authority:qual_authority,
				qual_doa:qual_doa
			}, function(data) {
				if(parseInt(data) > 0) {
					alert("Data Saved Successfully")
				} else alert(data)
				$('#quali').val('0');		
				$('#yrOfPass').val('');
				$('#quali_degree').val('0');
				$('#specialization').val('0');
				$('#div_class').val('0');
				$('#institute_place').val('');
				$('#qualification_ch_id').val('0');
				$("input[type=checkbox][name='multisub']").prop("checked", false);
				
				$("#sub_quali option:first").text('Select Subjects');
				$("#quali_sub_list").empty();
				var typethisT = document.getElementById('quali_type');
				fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
				var qualithisT = document.getElementById('quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
			
				fn_other_class();
				getQualifications();
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
			});
		}
	 function getQualifications() {
			var q_id = $('#census_id').val();
			var i = 0;
			$.post('getQualificationData?' + key + "=" + value, {
				q_id: q_id
			}, function(j) {
				var v = j.length;
				$('#qualificationtbody').empty();
				if(v != 0) {
					
					for(i; i < v; i++) {
						qu = i + 1;
						var examother="--";
						var classother="--";
						var deg_other="--";
						var spec_other="--";
						var exampass = "--";				
						var deg_name = "--";
						var specialization = "--";
						if(j[i].exp_name != null) exampass = j[i].exp_name;
					
						if(j[i].d_name != null) deg_name = j[i].d_name;
						if(j[i].spce_name != null) specialization = j[i].spce_name;
						
						if(j[i].exam_other!=null){
							examother=j[i].exam_other;
							exampass=j[i].exam_other;
						}
				  	    
				  	  if(j[i].degree_other!=null){
				  		deg_name=j[i].degree_other;
				  		deg_other=j[i].degree_other;
				  	  }
				  	   if(j[i].specialization_other!=null)
				  		   {
				  		 specialization=j[i].specialization_other;
				  		spec_other=j[i].specialization_other;
				  		   }
				  	    
//		 				var qulitype = $('#quali_type option[value="' + j[i].type + '"]').text();
						var divclass = $('#div_class option[value="' + j[i].div_class + '"]').text();
						 if(j[i].class_other!=null){
					  	    	classother=j[i].class_other;
					  	    	divclass=j[i].class_other;
						 }
						var subjectslist = j[i].subject.split(',');
						var subjects = "";
						for(k = 0; k < subjectslist.length; k++) {
							if(k != 0) subjects += ",";
							subjects += $("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").parent().text();
						}
						var Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Qualification ?') ){editQualificationData(" + j[i].id + "," + j[i].type + ",'" +  j[i].examination_pass + "'," + j[i].passing_year + ",'" + j[i].degree + "','" + j[i].specialization + "'," + j[i].div_class + ",'" + j[i].subject + "','" + j[i].institute + "','" + examother + "','" + classother + "','" + deg_other + "','" + spec_other + "','" + j[i].authority + "','" + ParseDateColumncommission(j[i].date_of_authority) + "')}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						var Delete = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Qualification ?') ){deleteQualificationData(" + j[i].id + ")}else{ return false;}\"";
						f1 = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>";
						var action = f + f1;
						$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px;text-align: center ;width: 10%;">' + qu + '</td> ' +  '<td style="font-size: 15px;text-align: center ;width: 10%;">' + j[i].authority + '</td> ' +  '<td style="font-size: 15px;text-align: center ;width: 10%;">' + convertDateFormate(j[i].date_of_authority) + '</td> ' + '<td style="font-size: 15px;text-align: center">' + j[i].label + '</td>	' + '<td style="font-size: 15px;text-align: center">' + exampass + '</td> ' 
						 + '<td style="font-size: 15px;text-align: center">' + deg_name + '</td>' +   '<td style="font-size: 15px;text-align: center">' + specialization + '</td>' +
						'<td style="font-size: 15px;text-align: center">' + j[i].passing_year + '</td>' + '<td style="font-size: 15px;text-align: center">' + subjects + '</td>' + '<td style="font-size: 15px;text-align: center">' 
						+ divclass + '</td>'  + '<td style="font-size: 15px;text-align: center">' + j[i].institute + '</td>' 
						 + '<td class="hide-action" style="font-size: 15px; text-align: center;">' + action + '</td>' + '</tr>');
					}
				} else {
					$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px; text-align: center; color: red;" colspan="12">Data Not Available</td>' + '</tr>');
				}
			});
		}

		function editQualificationData(id, type, exampass, passing_year, degree,  specialization, div_class, subject, institute,examother,classother,deg_other,spec_other,authority,doa) {
			console.log(exampass+" "+degree+" "+specialization);
			$("#qualification_authority").val(authority);
			$("#qualification_date_of_authority").val(doa);
			$('#yrOfPass').val(passing_year);
			$('#div_class').val(div_class);
			$('#institute_place').val(institute);
			$('#qualification_ch_id').val(id);
			$("input[type=checkbox][name='multisub']").prop("checked", false);
			var subjectslist = subject.split(',');
			for(k = 0; k < subjectslist.length; k++) {
				$("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
				$("#sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
			}
			$('#quali_sub_list').empty();
			$("input[type='checkbox'][name='multisub']").each(function() {
				if(this.checked) {
					$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
				}
			});
			$('#quali_type').val(type);
			var typethisT = document.getElementById('quali_type');
			fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
			if(exampass != '--') {
				
				$('#quali').val(exampass);
				var qualithisT = document.getElementById('quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
			}
			
			 if(examother!='--')
			  	$('#exam_other').val(examother);
			 if(classother!='--')
				 $('#class_other').val(classother);
			 if(deg_other!='--')
				 $('#quali_deg_other').val(deg_other);
			
			 
			
			  
			
			if(degree != '--') {
				$('#quali_degree').val(degree);
				
			}
			if(specialization != '--') $('#specialization').val(specialization);
			 if(spec_other!='--')
				 $('#quali_spec_other').val(spec_other);
			 fn_other_exam();
			 fn_other_class();
			 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
			 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
		}

		function deleteQualificationData(id) {
			var qualification_ch_id = id;
			$.post('qualification_delete_action?' + key + "=" + value, {
				qualification_ch_id: qualification_ch_id
			}, function(data) {
				if(data == '1') {
					getQualifications();
					alert("Data Deleted Successfully");
				} else {
					alert("Data not Deleted ");
				}
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
			});
		}

function showCheckboxes() {
	  var checkboxes = document.getElementById("checkboxes");
	  $("#checkboxes").toggle();
	  $("#quali_sub_search").val(''); 
	  
	  $('.quali_subjectlist').each(function () {       
	  $(this).show()
	})
	}



proex=1;
armyc=1;
proex_srno=1;
armyc_srno=1;
function promo_exam_add_fn(q){
	 if( $('#promo_exam_add'+q).length )         
	 {
			$("#promo_exam_add"+q).hide();
	 }
	 proex=q+1;
	if(q!=0)
		proex_srno+=1;
	$("table#promo_exam_table").append('<tr id="tr_promo_exam'+proex+'">'
			+'<td class="proex_srno" style="width: 2%;">'+proex_srno+'</td>'
			+'<td style="width: 10%;">'
			+'<select name="promo_exam'+proex+'" id="promo_exam'+proex+'" onchange="onPro_exam('+proex+')" class="form-control-sm form-control">'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getExamList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach> '
			+'</select></td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="exam_other'+proex+'" name="exam_other'+proex+'" readonly class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"/>'
			+'</td>'
			+'<td style="width: 10%;">'
			+'<input type="month" id="promo_exam_dop'+proex+'" max="${month}" name="promo_exam_dop'+proex+'"  class="form-control autocomplete" autocomplete="off" />'
			+'</td>'
			+'<td style="display:none;"><input type="text" id="promo_exam_ch_id'+proex+'" name="promo_exam_ch_id'+proex+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "promo_exam_save'+proex+'" onclick="promo_exam_save_fn('+proex+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "promo_exam_add'+proex+'" onclick="promo_exam_add_fn('+proex+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "promo_exam_remove'+proex+'" onclick="promo_exam_remove_fn('+proex+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
}

function promo_exam_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var promo_exam_ch_id=$('#promo_exam_ch_id'+R).val();
	  $.post('update_offr_promo_exam_delete_action?' + key + "=" + value, {promo_exam_ch_id:promo_exam_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_promo_exam"+R).remove(); 
							 if(R==proex){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#promo_exam_add'+i).length )         
								 {
									 $("#promo_exam_add"+i).show();
									 temp=false;
									 proex=i;
									 break;
								 }}
						 
							 if(temp){
								 promo_exam_add_fn(0);
								}
			  			 }
							 $('.proex_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									proex_srno=i+1;
									 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}
function promo_exam_save_fn(ps){
	var promo_exam=$('#promo_exam'+ps).val();
	var e_ot=$('#exam_other'+ps).val();
	var promo_exam_dop=$('#promo_exam_dop'+ps).val();
	var promo_exam_ch_id=$('#promo_exam_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	var promo_exam_authority=$('#promo_exam_authority').val();
	var promo_exam_doa=$('#promo_exam_date_of_authority').val();

	if (promo_exam_authority.trim()=="") {
		alert("Please Enter Authority");
		$("input#promo_exam_authority").focus();
		return false;
	}
	 if (promo_exam_doa.trim()=="" || promo_exam_doa == "DD/MM/YYYY") {
		alert("Please Enter Date Of Authority");
		$("input#promo_exam_date_of_authority").focus();
		return false;
	}
 	if (promo_exam == "0") {
 		alert("Please select Exam");
 		$("select#promo_exam"+ps).focus();
 		return false;
 	}	
 	var country_sel = $("#promo_exam"+ps+" option:selected").text();
	if (country_sel == "OTHERS" && e_ot.trim()=="") {
		alert("Please Enter Other Exam");
		$('#exam_other'+ps).focus();
		return false;
	} 

	
	if (promo_exam_dop.trim()=="") {
		alert("Please Enter Date Of Passing");
		$("#promo_exam_dop"+ps).focus();
		return false;
	}
	
	if($("input#promo_exam_dop"+ps).val() > ParseDateColumn(new Date())) {
		alert("Invalid Date Of Passing");
		$("#promo_exam_dop"+ps).focus();
		return false;
	}
 
	  $.post('update_offr_promo_exam_detail_action?' + key + "=" + value, {exam_other_select:country_sel,promo_exam:promo_exam,e_ot:e_ot,promo_exam_dop:promo_exam_dop,promo_exam_ch_id:promo_exam_ch_id,p_id:p_id,com_id:com_id,promo_exam_authority:promo_exam_authority,promo_exam_doa:promo_exam_doa }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#promo_exam_ch_id'+ps).val(data);
	        	 $("#promo_exam_add"+ps).show();
	        	 $("#promo_exam_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}



function get_promo_exam_details(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('update_offr_promo_exam_getData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){
		xpro=0;
		for(i;i<v;i++){
			
			xpro=xpro+1;
				if(xpro==1){
					$('#promo_examtbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				$("table#promo_exam_table").append('<tr id="tr_promo_exam'+xpro+'">'
						+'<td class="proex_srno" style="width: 2%;">'+xpro+'</td>'
						+'<td style="width: 10%;">'
						+'<select name="promo_exam'+xpro+'" id="promo_exam'+xpro+'" onchange="onPro_exam('+xpro+')" class="form-control-sm form-control">'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getExamList}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach> '
						+'</select></td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="exam_other'+xpro+'" name="exam_other'+xpro+'" value="'+j[i].exam_other+'" class="form-control autocomplete" maxlength="50" onkeypress="return onlyAlphabets(event);" autocomplete="off" />'
						+'</td>'
						+'<td style="width: 10%;">'
						+'<input type="month" id="promo_exam_dop'+xpro+'" max="${month}" name="promo_exam_dop'+xpro+'" value="'+j[i].date_of_passing+'" class="form-control autocomplete" autocomplete="off" />'
						+'</td>'
						+'<td style="display:none;"><input type="text" id="promo_exam_ch_id'+xpro+'" name="promo_exam_ch_id'+xpro+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "promo_exam_save'+xpro+'" onclick="promo_exam_save_fn('+xpro+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "promo_exam_add'+xpro+'" onclick="promo_exam_add_fn('+xpro+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "promo_exam_remove'+xpro+'" onclick="promo_exam_remove_fn('+xpro+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				 $('#promo_exam'+xpro).val(j[i].exam );
				 $('#promo_exam_authority').val(j[i].authority );
				 $('#promo_exam_date_of_authority').val(dauth);
				 onPro_exam(xpro);
			
		}
		if(xpro!=0){
			proex=xpro;
			proex_srno=xpro;
			$("#promo_exam_add"+xpro).show();
		}
	}
	  });
}
function onPro_exam(pro){
	
	var p_exam = $("#promo_exam"+pro+" option:selected").text();

	if(p_exam != "OTHERS"){
		
		$('#exam_other'+pro).val("");
		$('#exam_other'+pro).attr('readonly', true);
	}
	else
		$('#exam_other'+pro).attr('readonly', false);
}

function onDivGrade(ind){
	var army_course_div_grade = $("#army_course_div_grade"+ind+" option:selected").text();
	 
	if(army_course_div_grade != "OTHERS"){
		$('#ar_course_div_ot'+ind).val("");
		$('#ar_course_div_ot'+ind).attr('readonly', true);
	}
	else
		$('#ar_course_div_ot'+ind).attr('readonly', false);
}
function onCoursetype(ind){
	var army_course_type = $("#army_course_type"+ind+" option:selected").text();
	 
	if(army_course_type != "OTHERS"){
		$('#course_type_ot'+ind).val("");
		$('#course_type_ot'+ind).attr('readonly', true);
	}
	else
		$('#course_type_ot'+ind).attr('readonly', false);
}
 
function army_course_add_fn(q){
	 if( $('#army_course_add'+q).length )         
	 {
			$("#army_course_add"+q).hide();
	 }
	 armyc=q+1;
	if(q!=0)
		armyc_srno+=1;
	$("table#army_course_table").append('<tr id="tr_army_course'+armyc+'">'
			+'<td class="army_course_srno" style="width: 2%;">'+armyc_srno+'</td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="army_course_name'+armyc+'" name="army_course_name'+armyc+'" class="form-control autocomplete" onkeypress="AutoCompleteCourse(this,'+armyc+',0);" autocomplete="off" maxlength="20">'
			+'</td>'
			+'<td style="width: 10%;">'
			+'<input type="text" id="army_course_abbreviation'+armyc+'" name="army_course_abbreviation'+armyc+'" class="form-control autocomplete" onkeypress="AutoCompleteCourse(this,'+armyc+',1);" autocomplete="off" maxlength="20">'
			+'</td>'
			
			+'<td style="width: 10%;">'	
			+'<select  id="army_course_institute'+armyc+'" name="army_course_institute'+armyc+'" class="form-control autocomplete" onchange="onArmyCourseInstiChange('+armyc+')" >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getArmyCourseInstitute}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach></select></td>'
			+'<td style="width: 10%; " > <input type="text" id="army_course_institute_ot'+armyc+'" name="army_course_institute_ot'+armyc+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			  
			+'<td style="width: 10%;">'	
			+'<select  id="army_course_div_grade'+armyc+'" name="army_course_div_grade'+armyc+'" class="form-control autocomplete" onchange="onDivGrade('+armyc+')" >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getDivclass}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach></select></td>'
			
			+'<td style="width: 10%; " > <input type="text" id="ar_course_div_ot'+armyc+'" name="ar_course_div_ot'+armyc+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			  
			+'<td style="width: 10%;">'
			+'<select  id="army_course_type'+armyc+'" name="army_course_type'+armyc+'" class="form-control autocomplete"  >'
			+'<option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
			+'</c:forEach> </select>	</td>'
			
			+'<td style="width: 10%;display: none"> <input type="text" id="course_type_ot'+armyc+'" name="course_type_ot'+armyc+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
			
			
			+'<td style="width: 10%;">' 
			+' <input type="text" name="army_course_start_date'+armyc+'" id="army_course_start_date'+armyc+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
			+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
			+ '</td>'
			+'<td style="width: 10%;">' 
			+' <input type="text" name="army_course_date_of_completion'+armyc+'" id="army_course_date_of_completion'+armyc+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
			+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);">'
			+ '</td>'
			+'<td style="display:none;"><input type="text" id="army_course_ch_id'+armyc+'" name="army_course_ch_id'+armyc+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "army_course_save'+armyc+'" onclick="army_course_save_fn('+armyc+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "army_course_add'+armyc+'" onclick="army_course_add_fn('+armyc+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "army_course_remove'+armyc+'" onclick="army_course_remove_fn('+armyc+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
	 
	 datepicketDate('army_course_start_date'+armyc);
	 datepicketDate('army_course_date_of_completion'+armyc);
}


function army_course_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var army_course_ch_id=$('#army_course_ch_id'+R).val();
	  $.post('update_offr_army_course_delete_action?' + key + "=" + value, {army_course_ch_id:army_course_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_army_course"+R).remove(); 
							 if(R==armyc){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#army_course_add'+i).length )         
								 {
									 $("#army_course_add"+i).show();
									 temp=false;
									 armyc=i;
									 break;
								 }}
						 
							 if(temp){
								 army_course_add_fn(0);
								}
			  			 }
							 $('.army_course_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									armyc_srno=i+1;
									 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}
function army_course_save_fn(ps){
	var army_course_name=$('#army_course_name'+ps).val();
	var army_course_abbr=$('#army_course_abbreviation'+ps).val();
	var army_course_institue=$('#army_course_institute'+ps).val();
	var army_course_institue_ot=$('#army_course_institute_ot'+ps).val();
	
	var army_course_div_grade=$('#army_course_div_grade'+ps).val();
	var ar_course_div_ot=$('#ar_course_div_ot'+ps).val();
	var army_course_type=$('#army_course_type'+ps).val();
 	var course_type_ot=$('#course_type_ot'+ps).val();
	
	
	var army_course_start_date=$('#army_course_start_date'+ps).val();
	var army_course_date_of_completion=$('#army_course_date_of_completion'+ps).val();
	var army_course_ch_id=$('#army_course_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();
	var army_course_authority=$('#army_course_authority').val();
	var army_course_doa=$('#army_course_date_of_authority').val();
	
	var comm_date=$('#comm_date').val();
	
	if (army_course_authority.trim()=="") {
		alert("Please Enter Authority");
		$("input#army_course_authority").focus();
		return false;
	}
	if (army_course_doa.trim()=="" || army_course_doa == "DD/MM/YYYY") {
		alert("Please Enter Date Of Authority");
		$("input#army_course_date_of_authority").focus();
		return false;
	}
	if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#army_course_date_of_authority").val())) {
		alert("Authority Date should be Greater than Commission Date");
		return false;
  }
	
	if (army_course_name.trim()=="") {
		alert("Please Enter Course Name");
		$("#army_course_name"+ps).focus();
		return false;
	}
	if (army_course_abbr.trim()=="") {
		alert("Please Enter Course Abbreviation");
		$("#army_course_abbreviation"+ps).focus();
		return false;
	}
	if (army_course_institue == "0") {
		alert("Please Select Course Institute");
		$("#army_course_institute"+ps).focus();
		return false;
	}
	
	var army_course_institueV = $("#army_course_institute"+ps+" option:selected").text();
	if (army_course_institueV == "OTHERS" && army_course_institue_ot.trim()=="") {
		alert("Please Enter Other Course Institute");
		$('#army_course_institute_ot'+ps).focus();
		return false;
	}
	if (army_course_div_grade == "0") {
		alert("Please select Div/Grade");
		$("#army_course_div_grade"+ps).focus();
		return false;
	}	
	var army_course_div_gradeV = $("#army_course_div_grade"+ps+" option:selected").text();
	if (army_course_div_gradeV == "OTHERS" && ar_course_div_ot.trim()=="") {
		alert("Please Enter Other Div/Grade");
		$('#ar_course_div_ot'+ps).focus();
		return false;
	}
	if (army_course_type == "0") {
		alert("Please select Course Type");
		$("#army_course_type"+ps).focus();
		return false;
	}	
// 	var army_course_typeV = $("#army_course_type"+ps+" option:selected").text();
// 	if (army_course_typeV == "OTHERS" && course_type_ot.trim()=="") {
// 		alert("Please Enter Other Course Type");
// 		$('#course_type_ot'+ps).focus();
// 		return false;
// 	}
	if (army_course_authority.trim()=="") {
		alert("Please Enter Authority");
		$("#army_course_authority").focus();
		return false;
	}
	
	if (army_course_start_date.trim()=="" || army_course_start_date == "DD/MM/YYYY") {
		alert("Please Enter Start Date");
		$("#army_course_start_date"+ps).focus();
		return false;
	}
	if (army_course_date_of_completion.trim()=="" || army_course_date_of_completion == "DD/MM/YYYY") {
		alert("Please Enter Date Of Completion");
		$("#army_course_date_of_completion"+ps).focus();
		return false;
 	}
      if(getformatedDate(army_course_start_date) > getformatedDate(army_course_date_of_completion)) {
		alert("Completion Date should always be greater than Start Date");
		$("#army_course_date_of_completion"+ps).focus();
		return false;
	}
	  $.post('update_offr_army_course_detail_action?' + key + "=" + value, 
			  {comm_date:comm_date,army_course_name:army_course_name,army_course_div_grade:army_course_div_grade,ar_course_div_ot:ar_course_div_ot,
		  		army_course_type:army_course_type,course_type_ot:course_type_ot,army_course_start_date:army_course_start_date,
		  		army_course_date_of_completion:army_course_date_of_completion,army_course_ch_id:army_course_ch_id,
		  		p_id:p_id,com_id:com_id,army_course_authority:army_course_authority,army_course_doa:army_course_doa,army_course_institueV:army_course_institueV,army_course_abbr:army_course_abbr,army_course_institue:army_course_institue,army_course_institue_ot:army_course_institue_ot }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#army_course_ch_id'+ps).val(data);
	        	 $("#army_course_add"+ps).show();
	        	 $("#army_course_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}



function get_army_course_details(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('update_offr_army_course_getData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){
		xaco=0;
		for(i;i<v;i++){
			
			xaco=xaco+1;
				if(xaco==1){
					$('#army_coursetbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				var start_date=ParseDateColumncommission(j[i].start_date);
				var date_of_completion=ParseDateColumncommission(j[i].date_of_completion);
				$("table#army_course_table").append('<tr id="tr_army_course'+xaco+'">'
						+'<td class="army_course_srno" style="width: 2%;">'+xaco+'</td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="army_course_name'+xaco+'" name="army_course_name'+xaco+'" value="'+j[i].course_name+'" onkeypress="AutoCompleteCourse(this,'+xaco+',0);" class="form-control autocomplete" autocomplete="off" maxlength="20">'
						+'</td>'
						+'<td style="width: 10%;">'
						+'<input type="text" id="army_course_abbreviation'+xaco+'" name="army_course_abbreviation'+xaco+'" value="'+j[i].course_abbreviation+'" class="form-control autocomplete" onkeypress="AutoCompleteCourse(this,'+xaco+',1);" autocomplete="off" maxlength="20">'
						+'</td>'
						+'<td style="width: 10%;">'	
						+'<select  id="army_course_institute'+xaco+'" name="army_course_institute'+xaco+'" class="form-control autocomplete" onchange="onArmyCourseInstiChange('+xaco+')" >'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getArmyCourseInstitute}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach></select></td>'
						+'<td style="width: 10%; " > <input type="text" id="army_course_institute_ot'+xaco+'" value="'+j[i].course_institute_other+'" name="army_course_institute_ot'+xaco+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<select  id="army_course_div_grade'+xaco+'" name="army_course_div_grade'+xaco+'" onchange="onDivGrade('+xaco+')" class="form-control autocomplete"  >'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getDivclass}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach></select></td>'
						+'<td style="width: 10%;"> <input type="text" id="ar_course_div_ot'+xaco+'" name="ar_course_div_ot'+xaco+'" value="'+j[i].ar_course_div_ot+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
										
						+'<td style="width: 10%;">'
						+'<select  id="army_course_type'+xaco+'" name="army_course_type'+xaco+'"   class="form-control autocomplete"  >'
						+'<option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getCourseTypeList}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option> '
						+'</c:forEach> </select>	</td>'
						+'<td style="width: 10%; display: none"> <input type="text" id="course_type_ot'+xaco+'" name="course_type_ot'+xaco+'" value="'+j[i].course_type_ot+'" maxlength="50" onkeypress="return onlyAlphabets(event);" readonly  class="form-control autocomplete" autocomplete="off" ></td>'
						
						+'<td style="width: 10%;">' 
						+' <input type="text" name="army_course_start_date'+xaco+'" id="army_course_start_date'+xaco+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+start_date+'">'
						+ '</td>'
						+'<td style="width: 10%;">' 
						+' <input type="text" name="army_course_date_of_completion'+xaco+'" id="army_course_date_of_completion'+xaco+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+date_of_completion+'">'
						+ '</td>'
						+'<td style="display:none;"><input type="text" id="army_course_ch_id'+xaco+'" name="army_course_ch_id'+xaco+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "army_course_save'+xaco+'" onclick="army_course_save_fn('+xaco+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "army_course_add'+xaco+'" onclick="army_course_add_fn('+xaco+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "army_course_remove'+xaco+'" onclick="army_course_remove_fn('+xaco+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				 $('#army_course_div_grade'+xaco).val(j[i].div_grade );
				 $('#army_course_institute'+xaco).val(j[i].course_institute );
				 $('#army_course_type'+xaco).val(j[i].course_type );
				 $('#army_course_authority').val(j[i].authority );
				 $('#army_course_date_of_authority').val(dauth );
				 datepicketDate('army_course_start_date'+xaco);
				 datepicketDate('army_course_date_of_completion'+xaco);
					onDivGrade(xaco);
					onCoursetype(xaco);
					onArmyCourseInstiChange(xaco);
		}
		if(xaco!=0){
			armyc=xaco;
			armyc_srno=xaco;
			$("#army_course_add"+xaco).show();
		}
	}
	  });
}


function AutoCompleteCourse(ele,index,val){
	type="";
	var category="OFFICER";
	//var category="1";
	var abbr=[];
	var susval = [];
	if(val=='1'){
		type="abbr";
	}
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getArmyCourseNameList?" + key + "=" + value,
				data : {
					Course:code,
					type:type,
					category:category
				},
				success : function(data) {
					
					
					var length = data.length - 1;
					
					for (var i = 0; i < data.length; i++) {
						
						if(val=='1'){
							susval.push(data[i][2]);
							abbr.push(data[i][1]);
						}

						else{
							susval.push(data[i][1]);
							abbr.push(data[i][2]);
						}
					
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
				
				if(val=='1'){
					//alert("Please Enter Valid Abbreviation ");				
					$("#army_course_name"+index).val("");
				}

				else{
					
					//alert("Please Enter Valid Course Name ");
					$("#army_course_abbreviation"+index).val("");
				}
							
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {
			var arrindex=susval.indexOf(ui.item.value);
			var second=abbr[arrindex];
			if(val=='1'){
				$("#army_course_name"+index).val(second);
			}

			else{
				$("#army_course_abbreviation"+index).val(second);
			}
			
			
		}
	});
	
}
function onArmyCourseInstiChange(index){
	var val = $("#army_course_institute"+index+" option:selected").text();
	//alert("hiii---" + type_of_entry_sel);
	if(val != "OTHERS"){
		
		$("#army_course_institute_ot"+index).prop("readonly",true);
		$('#army_course_institute_ot'+index).val('');
	}
	else
		$("#army_course_institute_ot"+index).prop("readonly",false);

	
}
//***************** END QUALIFICATION DETAILS************************//
</Script>

<script>	
//***************** START CHANGE OF APPOINTEMENT DETAILS************************//
function get_Appointment(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('get_Appointment?' + key + "=" + value, {p_id:p_id, comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){

				$('#appoint_id').val(j[i].id);
				 $("#appt_authority").val(j[i].appt_authority);
				 $("#appt_date_of_authority").val(ParseDateColumncommission(j[i].appt_date_of_authority));
				 $("#appointment").val(j[i].appointment);
				 $("#date_of_appointment").val(ParseDateColumncommission(j[i].date_of_appointment));
// 				 appNamechng();
// 				 $("#x_sus_no").val(j[i].x_list_sus_no);
// 				 $("#x_list_loc").val(j[i].x_list_loc);
				 $("#parent_sus_no").val(j[i].parent_sus_no);
				 $("#parent_unit").val(j[i].parent_unit);
				 $("#x_list_loc").val(j[i].x_list_loc);
				 if (j[i].appointment == "9562" || j[i].appointment == "9565") {
						$("#att_dapu").show();
						$("#att_dapu1").show();
					} else {
						$("#att_dapu").hide();
						$("#att_dapu1").hide();
					}
				 
			}
	  });
}	

function  search_sus(sus_obj,unit_id){
// 	removeMinDate();
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);

	
	
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                var length = data.length-1;
                var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              susval.push(dec(enc,data[i]));
                      }
                      var dataCountry1 = susval.join("|");
                      var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  $('#'+unit_id).val('');
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
       }, function(j) {
              
       }).done(function(j) {
      	 var length = j.length-1;
           var enc = j[length].substring(0,16);
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
	
	
	}
	
function appointment_att() {

	if ($("#appointment").val() == "9562" || $("#appointment").val() == "9565") {
		$("#att_dapu").show();
		$("#att_dapu1").show();
		 $("div#Note").show();
	} else {
		$("#att_dapu").hide();
		$("#att_dapu1").hide();
		$("div#Note").hide();
	}
}

//Parent unit name
$("input#parent_unit").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#parent_unit");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
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
			        	  alert("Please Enter Approved  X-List Unit Name.");
			        	  document.getElementById("parent_unit").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#parent_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});
function validate_appointment_save(){
	var tos_date=$('#tos_date').val();
	var comm_id=$('#comm_id').val();
	var comm_date=$('#comm_date').val();
	var census_id=$('#census_id').val();
	var status=$('#status').val();
	var appoint_id=$('#appoint_id').val();
	var parent_sus_no =$('#parent_sus_no').val();
	var parent_unit=$('#parent_unit').val();
	var x_list_loc=$('#x_list_loc').val();
	
	var formdata=$('#form_change_of_appointment').serialize();
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date+"&tos_date="+tos_date;	
	  if ($("input#appt_authority").val().trim()=="") {
		alert("Please Enter Authority.");
		$("input#appt_authority").focus();
		return false;
	}
	  if ($("input#appt_date_of_authority").val() == "DD/MM/YYYY" || $("input#appt_date_of_authority").val().trim()=="") {
		alert("Please Enter Date Of Authority. ");
		$("input#appt_date_of_authority").focus();
		return false;
	} 
	  if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#appt_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
  if ($("select#appointment").val() == "0") {
		alert("Please Select Appointment. ");
		$("select#appointment").focus();
		return false;
	} 
   if ($("input#date_of_appointment").val() == "DD/MM/YYYY" || $("input#date_of_appointment").val().trim()=="") {
		alert("Please Enter Date Of Appointment. ");
		$("input#date_of_appointment").focus();
		return false;
	}  
   if($("input#tos_date").val()!="" && getformatedDate($("input#tos_date").val()) > getformatedDate($("#date_of_appointment").val())) {
		alert("Date of Appointment Date should be Greater than TOS Date");
		return false;
   }

 if($('#appointment').val() == "9562" || $('#appointment').val() == "9565" )
  {
		   if (($("#parent_sus_no").val().trim()== "") && ($("#parent_unit").val().trim()== "") && ($("#x_list_loc").val().trim()== "")) {
				alert("Please Enter X-List SUS No Or  X-List Unit Name Or  X-List Location.");
				$("input#parent_sus_no").focus();
				return false;
			}
  }
 
	   $.post('change_appointmentAction?' + key + "=" + value, formdata, function(data){		      
		   if(data=="update"){
			   alert("Data Updated Successfully");
		   }
		   else if(parseInt(data)>0){
	        	 $('#appoint_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}


//Parent SUS No

$("#parent_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#parent_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved  X-List SUS No.");
	        	  document.getElementById("parent_unit").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#parent_unit").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});


// Parent unit name
 $("input#parent_unit").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#parent_unit");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
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
			        	  alert("Please Enter Approved  X-List Unit Name.");
			        	  document.getElementById("parent_unit").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var unit_name = ui.item.value;
			    
				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#parent_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});


//***************** END QUALIFICATION DETAILS************************//
</Script>

<script>
$(document).ready(function() {
	
	childCB(1);
	 
	//alert("unit_sus_no6--"+'${unit_sus_no6}');
	//alert("comm_idV--"+'${comm_id}');
	$("#personnel_noV").val('${personnel_no6}');
	$("#statusV").val('${status6}');
	$("#rankV").val('${rank6}');
	$("#comm_idV").val('${comm_id6}');
	$("#unit_nameV").val('${unit_name6}');
	$("#unit_sus_noV").val('${unit_sus_no6}');
	 	
	$("#cr_byV").val('${cr_by6}');
	$("#cr_dateV").val('${cr_date6}');
	 	
	
	if ('${status}' == 1){
		$("#back_bt").show();
		$('#status').val('${status}');
	}
	
	if ('${status}' == 2){
		$("#back_bt_div").show();
		$('#status').val('${status}');
	}

	$('#personnel_no').val('${personnel_no_edit}');
	if ('${personnel_no_edit}' != ""){
		
		var personnel_no = '${personnel_no_edit}';
		 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
			 if(j!=""){
				 debugger;
			
		    	$("#comm_id").val(j[0][0]);
		    	$("select#inter_arm_old_arm_service").val(j[0][8]);
				$("select#inter_arm_old_regt").val(j[0][9]);
		    	var comm_id =j[0][0]; 
		    	 $.post('GetCensusDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		    		 if(k.length > 0)
		    		 {
		    			 $('#census_id').val(k[0][1]); 
		    			  $('#div_cda_acnt_no').show(); 
		    			  curr_marital_status=k[0][13];  

                    if(curr_marital_status!=8)
	    			  {
	    				  $("#marr_quali_radioDiv").remove();
	    			  }else{
	    				  $('#marital_eventDiv').hide();
	    			  }
	    			  

		    			  $('#marital_event').val('0');
		    			  $('#marriage_div').show();
		    			  
		    			   $("#cadet_name").text(k[0][2]);
		    			 	if(k[0][11]==null){
		    		    		$("#dob").text("");    
		    		    	}
		    		    	else{
		    		    		 $("#dob").text(convertDateFormate(k[0][11]));  
		    		    		 $("#dob_date").val(ParseDateColumncommission(k[0][11]));
		    		    	}
		    			 	$("#comm_date").val(ParseDateColumncommission(k[0][12]));
		    			    $("#marital_status_p").text(k[0][13]);
		    		    	$("#p_rank").text(k[0][3]);
		    		    	$("#hd_p_rank").val(k[0][3]);
		    		    	$("#p_app").text(k[0][4]);
		    		    	if(k[0][5]==null){
		    		    		$("#p_app_dt").text("");    
		    		    	}
		    		    	else{
		    		    		 $("#p_app_dt").text(convertDateFormate(k[0][5]));  
		    		    	}
		    		    	if(k[0][6]==null){
		    		    		$("#p_tos").text("");    
		    		    	}
		    		    	else{
		    		    		 $("#p_tos").text(convertDateFormate(k[0][6]));  
		    		    	}
		    		    	$("#tos_date").val(ParseDateColumncommission(k[0][6]));
		    		    	$("#app_sus_no").text(k[0][7]);
		    		    	$("#p_id_no").text(k[0][8]);
		    		    	$("#p_religion").text(k[0][9]);
		    		    	$("#app_parent_arm").text(k[0][10]);
		    		    	if(k[0][24] == '20'){
		    		    		$("#form_ta_status").show();
		    		    		}

		    		    	$("#p_cmd").text(k[0][16]);
		    		    	
		    		    	if(k[0][10] == "GORKHA" || k[0][10] == "INFANTRY"){
		    		 			$("#reg_div").show();
		    		 		}
		    		 	  else
		    		 		  {
		    		 		 $("#reg_div").hide();
		    		 		  }
		    		    	
		    		    	/* $('#inter_arm_regt_val').val(k[0][17]);
		    				$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text()); */
		    		    	if(k[0][17]!=0){
		    		    		$('#inter_arm_regt_val').val(k[0][17]);
		    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
		    		    	}
		    		    	else{
		    		    		$('#p_regt').text("");
		    		    	}
		    				
		    		    	var sus_no =k[0][7];
		    		    	getunit(sus_no);
		    		    	function getunit(val) {	
		    		            $.post("getTargetUnitNameList?"+key+"="+value, {
		    		            	sus_no : sus_no
		    		        }, function(j) {
		    		                //var json = JSON.parse(data);
		    		                //...

		    		        }).done(function(j) {
		    		    				   var length = j.length-1;
		    		    	                   var enc = j[length].substring(0,16); 
		    		    	                   //alert("unit---" + dec(enc,j[0]));
		    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
		    		        }).fail(function(xhr, textStatus, errorThrown) {
		    		        });
		    		    } 
		    		 }
		   });
		 		$.ajaxSetup({
						async : false
					});
		 		
		    	 $.post('GetServingStatus?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		    		 if(k.length > 0)
		    		 {
		    		    	$("#p_serving_status").text(k[0][2]);
		    		 }
		   });
			 }
	});
		 $("#div_update_data").show();          
		 $("#process_btn").hide();
		 
	}
	else{
		$("#div_update_data").hide();
		$("#process_btn").show();
		
	}
	
	addMaxLengthToInput(auth_length);
	
	try{
        if(window.location.href.includes("?"))
         {
             var url = window.location.href.split("?")[0];
            window.history.pushState({}, document.title, url);
        }
    }
    catch (e) {
        // TODO: handle exception
    }

 
}); 
$("a").click(function(){
	addMaxLengthToInput(auth_length);

});

</script>
<script>

//************************************* START DISCIPLINE *****************************//

function onTypeofEntry(){
	
	var type_of_entry_sel = $("#type_of_entry option:selected").text();
	//alert("hiii---" + type_of_entry_sel);
	if(type_of_entry_sel != "OTHER"){
		
		$("#type_of_entry_otherDiv").hide();
		$("#type_of_entry_other").hide();
		$('#type_of_entry_other').val('');
	}
	else{
		$("#type_of_entry_other").show();
		$("#type_of_entry_otherDiv").show();
	}
}

 function get_change_of_Discipline(){
	var census_id=$("#census_id").val();	
	var comm_id=$("#comm_id").val();
	 
	 var i=0;
	  $.post('getdisciplineData?' + key + "=" + value, {census_id:census_id,comm_id:comm_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#disi_id').val(j[i].id);
				//$("#army_no1").val(j[i].army_no);
				//$("#rank_d").val(j[i].rank);
				$("#army_act_sec").val(j[i].army_act_sec);
				$("#sub_clause").val(j[i].sub_clause);
				$("#description1").val(j[i].description);
				$("#type_of_entry").val(j[i].type_of_entry);
				//$("#unit_name").val(j[i].unit_name);
				 $("#dis_sus").val(j[i].unit_name);
				 fn_getUnitnamefromSus(j[i].unit_name, document.getElementById("unit_name"));
				 $("#trialed_by").val(j[i].trialed_by);
				 //$("#name_dis").val(j[i].name);
				 $("#award_dt").val(ParseDateColumncommission(j[i].award_dt));
				 $("#disi_authority").val(j[i].disi_authority);
				 $("#disi_authority_date").val(ParseDateColumncommission(j[i].disi_authority_date));
				 $("#type_of_entry_other").val(j[i].type_of_entry_other);
				 if(j[i].type_of_entry_other!=""){
					 onTypeofEntry();
				 }
				 
				// onTypeofEntry();
				
			}
	  });
} 

 function validate_Discipline_save(){
     var formvalues=$("#form_disipline").serialize();
     
      
      if ($("#disi_authority").val().trim()=="") {
                     alert("Please Enter Authority");
                     $("#disi_authority").focus();
                     return false;
      } 
      
      if ($("#disi_authority_date").val().trim()=="" || $("#disi_authority_date").val() =="DD/MM/YYYY") {
                     alert("Please Enter Authority Date");
                     $("#disi_authority_date").focus();
                     return false;
      } 
      if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#disi_authority_date").val())) {
                     alert("Authority Date should be Greater than Commission Date");
                     return false;
       }
      if ($("select#army_act_sec").val()=="0") {
          alert("Please Select Army Act Sec");
          $("select#army_act_sec").focus();
          return false;
		} 
      if ($("select#sub_clause").val()=="0") {
          alert("Please Select Sub Clause");
          $("select#sub_clause").focus();
          return false;
		} 
		if ($("select#trialed_by").val()=="0") {
			alert("Please Select Trialed By");
			$("select#trialed_by").focus();
			return false;
		} 
       if ($("#description1").val().trim()=="") {
                     alert("Please Enter Punishment Awarded ");
                     $("#description1").focus();
                     return false;
      } 
     
      if ($("#type_of_entry").val() == "0") {
                     alert("Please Select Type of Entry ");
                     $("#type_of_entry").focus();
                     return false;
      }
      
       var type_of_entry_sel = $("#type_of_entry option:selected").text();
             if(type_of_entry_sel == "OTHER"){
                     if ($("#type_of_entry_other").val().trim()=="") {
                             alert("Please Enter Type of Entry Other");
                             $("#type_of_entry_other").focus();
                             return false;
              }
             }
             
       if ($("#award_dt").val().trim()=="" || $("#award_dt").val() == "DD/MM/YYYY") {
                     alert("Please Enter Award Date");
                     $("#award_dt").focus();
                     return false;
      } 
       if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#award_dt").val())) {
			alert("Date Of Award should be Greater Or Equal  Commission Date");
			$("#award_dt").focus();
			return false;
	  }
      if ($("#unit_name").val().trim()=="") {
                     alert("Please Enter Unit Name");
                     $("#unit_name").focus();
                     return false;
      }
      

     var disi_id=$('#disi_id').val();
     var census_id=$("#census_id").val();        
     var comm_id=$('#comm_id').val();                
     var comm_date=$('#comm_date').val();
     formvalues+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;

        $.post('Change_of_Discipline_action?' + key + "=" + value,formvalues, function(data){
                if(data=="update"){
                        alert("Data Updated Successfully");
                }
                else if(parseInt(data)>0){
                      $('#disi_id').val(data);                         
                       alert("Data Saved Successfully")
               }
               else
                       alert(data);
                                }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                       });
}
$("input#unit_name").keypress(function() {
	var unit_name = this.value;
	var susNoAuto = $("#unit_name");
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "PSG_CADET_getTargetUnitsNameActiveList?" + key + "=" + value,
				data : {
					unit_name : unit_name
				},
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
				alert("Please Enter Approved Unit Name.");
				document.getElementById("unit_name").value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {
			var unit_name = ui.item.value;
			$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
				target_unit_name : unit_name
			}, function(data) {
			}).done(function(data) {
				var length = data.length - 1;
				var enc = data[length].substring(0, 16);
				$("#dis_sus").val(dec(enc, data[0]));
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
	});
});

//*************************************END DISCIPLINE *****************************//


//************************************* Change of tnai no*****************************//
function validate_Change_of_tnaino_save_fn(){
	 if ($("input#tnai_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#tnai_authority").focus();
			return false;
		}
	 	 if ($("input#tnai_date_of_authority").val() == "DD/MM/YYYY" || $("input#tnai_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#tnai_date_of_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#tnai_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 	if ($("input#tnaino").val().trim()=="") {
			alert("Please Enter TNAI No ");
			$("input#tnaino").focus();
			return false;
		}

	 	if ($("input#change_in_tnaino_date").val() == "DD/MM/YYYY" || $("input#change_in_tnaino_date").val().trim()=="") {
	 				alert("Please Select Date of Change in Tnai no ");
	 				$("input#change_in_tnaino_date").focus();
	 				return false;
	 			} 
	 	
	 			
	var comm_id_tnai=$('#comm_id').val();
	var census_id_tnai=$('#census_id').val(); 
	var tnaino=$('#tnaino').val();	
	var comm_date=$('#comm_date').val();
	var change_in_tnaino_date=$('#change_in_tnaino_date').val();
	var tnai_date_of_authority=$('#tnai_date_of_authority').val();
    var tnai_authority=$('#tnai_authority').val();
    var ch_tnai_id=$('#ch_tnai_id').val();

	  $.post('Change_of_TnaiNo_action?' + key + "=" + value, {comm_id_tnai:comm_id_tnai,census_id_tnai:census_id_tnai,
		  tnaino:tnaino,comm_date:comm_date,change_in_tnaino_date:change_in_tnaino_date,tnai_date_of_authority:tnai_date_of_authority
		  ,tnai_authority:tnai_authority,ch_tnai_id:ch_tnai_id}, function(data){
		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        
	        	  alert("Data Saved Successfully")
	          } else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			alert("fail to fetch")
	  		});
}

function getChangeOfTnai(){	
	var comm_id=$('#comm_id').val();
	var i=0;
	 $.post('getChangeOfTnaiNoData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			   $('#ch_tnai_id').val(j[0].id);
				$('#tnai_authority').val(j[0].authority);
				var dtA =ParseDateColumncommission(j[0].date_of_authority);		
				$('#tnai_date_of_authority').val(dtA);	
				$('#tnaino').val( j[0].tnai_no );				
				var dtR =ParseDateColumncommission(j[0].change_in_name_date);
				$('#change_in_tnaino_date').val(dtR);	
		 }
	 });
	}  

//*************************************end Change of tnai no*****************************//
///////////allergic///////////////
aller=1;
aller_srno=1;
function allergic_add_fn(q){
	 if( $('#allergic_add'+q).length )         
	 {
			$("#allergic_add"+q).hide();
	 }
	 aller=q+1;
	if(q!=0)
		aller_srno+=1;
	$("table#allergic_table").append('<tr id="tr_allergic'+aller+'">'
			+'<td class="aller_srno" style="width: 2%;">'+aller_srno+'</td>'
			+'<td><input type="text" id="allergic'+aller+'" name="allergic'+aller+'"' 
			+'class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="100">'
			+'</td>'
			+'<td style="display:none;"><input type="text" id="allergic_ch_id'+aller+'" name="allergic_ch_id'+aller+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save'+aller+'" onclick="allergic_save_fn('+aller+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+aller+'" onclick="allergic_add_fn('+aller+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+aller+'" onclick="allergic_remove_fn('+aller+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
}

function allergic_remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var allergic_ch_id=$('#allergic_ch_id'+R).val();
	  $.post('update_offr_allergic_delete_action?' + key + "=" + value, {allergic_ch_id:allergic_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_allergic"+R).remove(); 
							 if(R==aller){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#allergic_add'+i).length )         
								 {
									 $("#allergic_add"+i).show();
									 temp=false;
									 aller=i;
									 break;
								 }}
						 
							 if(temp){
								 allergic_add_fn(0);
								}
			  			 }
							 $('.aller_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									aller_srno=i+1;
									 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}
function allergic_save_fn(ps){
	var allergic=$('#allergic'+ps).val();
	var allergic_ch_id=$('#allergic_ch_id'+ps).val();
	var p_id=$('#census_id').val();
	var com_id=$('#comm_id').val();

	if (allergic.trim()=="") {
		alert("Please Enter Allergy");
		$("input#allergic"+ps).focus();
		return false;
	}
	
	  $.post('update_offr_allergic_detail_action?' + key + "=" + value, {allergic:allergic,allergic_ch_id:allergic_ch_id,p_id:p_id,com_id:com_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#allergic_ch_id'+ps).val(data);
	        	 $("#allergic_add"+ps).show();
	        	 $("#allergic_remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}

function get_allergic_details(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('update_offr_allergic_getData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){
		xaller=0;
		for(i;i<v;i++){
			
			xaller=xaller+1;
				if(xaller==1){
					$('#allergictbody').empty(); 
				}
				var dauth=ParseDateColumncommission(j[i].date_of_authority);
				$("table#allergic_table").append('<tr id="tr_allergic'+xaller+'">'
						+'<td class="aller_srno" style="width: 2%;">'+xaller+'</td>'
						+'<td><input type="text" id="allergic'+xaller+'" name="allergic'+xaller+'" value="'+j[i].medicine+'"'
						+'class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);">'
						+'</td>'
						+'<td style="display:none;"><input type="text" id="allergic_ch_id'+xaller+'" name="allergic_ch_id'+xaller+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "allergic_save'+xaller+'" onclick="allergic_save_fn('+xaller+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_add'+xaller+'" onclick="allergic_add_fn('+xaller+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_remove'+xaller+'" onclick="allergic_remove_fn('+xaller+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				 
			
			
		}
		if(xaller!=0){
			aller=xaller;
			aller_srno=xaller;
			$("#allergic_add"+xaller).show();
		}
	}
	  });
}



</script>
<script>

/////////////search subject////////////////////////



$("input[type='checkbox'][name='multisub']").click(function() {
  // access properties using this keyword
  var num=0;
  $("input[type='checkbox'][name='multisub']").each(function () {  
      if ( this.checked ) {     
          num=num+1
      }
      
  });
  if(num!=0)
      $("#sub_quali option:first").text('Subjects('+num+')');
  else
      $("#sub_quali option:first").text("Select Subjects");
});
$("#quali_sub_search").keyup(function () {
  var re = new RegExp($(this).val(), "i")
  $('.quali_subjectlist').each(function () {
      var text = $(this).text(),
          matches = !! text.match(re);
      $(this).toggle(matches)
  })
});


$("input[type='checkbox'][name='multisub']").click(function() {
    // access properties using this keyword
    var num=0;
    $('#quali_sub_list').empty()
    $("input[type='checkbox'][name='multisub']").each(function () {  
        if ( this.checked ) {   
        	$('#quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn("+this.value+")'></i><span> <br>");
            num=num+1;
        }
        
    });
    if(num!=0)
        $("#sub_quali option:first").text('Subjects('+num+')');
    else
        $("#sub_quali option:first").text("Select Subjects");
});



function removeSubFn(value){
	
	$("input[type='checkbox'][name='multisub'][value='" + value + "']").attr('checked', false);
	
	var num=0;
    $('#quali_sub_list').empty()
    $("input[type='checkbox'][name='multisub']").each(function () {  
        if ( this.checked ) {   
        	
        	$('#quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn("+this.value+")'></i><span> <br>");
            num=num+1;
        }
    });
    if(num!=0)
        $("#sub_quali option:first").text('Subjects('+num+')');
    else
        $("#sub_quali option:first").text("Select Subjects");
	
    
}


$("input[type='checkbox'][name='spouse_multisub']").click(function() {
  // access properties using this keyword
  var num=0;
  $("input[type='checkbox'][name='spouse_multisub']").each(function () {  
      if ( this.checked ) {     
          num=num+1
      }
      
  });
  if(num!=0)
      $("#spouse_sub_quali option:first").text('Subjects('+num+')');
  else
      $("#spouse_sub_quali option:first").text("Select Subjects");
});
$("#spouse_searchSubject").keyup(function () {
  var re = new RegExp($(this).val(), "i")
  $('.spouse_subjectlist').each(function () {
      var text = $(this).text(),
          matches = !! text.match(re);
      $(this).toggle(matches)
  })
});
</script>

<script>
  
/*///////////////// Start Deserter ////////////////////  */ 

function ArmType(){
	var arm_type=$("#arm_type").val();
 	if(arm_type =='1' ){
 		  $("#div_weapon").show(); 
	 }
	else{
 		$("#div_weapon").hide(); 
 		$('#arm_type_weapon').val("");
 	}
	
}
function DesertionCause(){
	var desertion_cause=$("#desertion_cause").val();
 	if(desertion_cause =='3' ){
 		  $("#div_cause").show(); 
	 }
	else{
 		$("#div_cause").hide(); 
 		$('#ot_desertion_cause').val("");
 	}
	
}

//save
function validate_Deserter_save_fn(){
	var deserter=$('#deserter').val();
	var arm_type=$('#arm_type').val();
	var arm_type_weapon=$('#arm_type_weapon').val();
	var dt_desertion=$('#dt_desertion').val();
	var dt_recovered=$('#dt_recovered').val();
	var desertion_cause=$('#desertion_cause').val();
	var indl_class=$('#indl_class').val();
	var ot_desertion_cause=$('#ot_desertion_cause').val();
	var recovered_arms=$('#recovered_arms').val();
	var des_ch_id=$('#des_ch_id').val();
	var census_id=$('#census_id').val();
	var comm_id=$('#comm_id').val();
	var comm_date=$('#comm_date').val(); 
	/* if(deserter == null || deserter=="0"){ 
			alert("Please Select The Deserter");
			$("select#deserter").focus();
			return false;
		}  */
	if(arm_type == null || arm_type=="0"){ 
		alert("Please Select The Arm Type");
		$("select#arm_type").focus();
		return false;
		} 
	if(arm_type=="1") { 
		if(arm_type_weapon == null || arm_type_weapon==""){
			alert( "Please Enter Weapon ");
			$("input#arm_type_weapon").focus();
			return false;
			}
		}
	
	
	if (dt_desertion.trim()=="" || dt_desertion == "DD/MM/YYYY") {
		alert("Please Select Date Of Desertion");
		$("input#dt_desertion").focus();
		return false;
	}
	 if(getformatedDate($("input#comm_date").val()) >= getformatedDate($("#dt_desertion").val())) {
			alert("Desertion Date should be Greater than Commission Date");
			return false;
	 }
	 if(getformatedDate($("input#dt_desertion").val()) >= getformatedDate($("#dt_recovered").val())) {
			alert("Recovered Date should be Greater than Desertion Date");
			return false;
	 }
	/* if (dt_recovered.trim()=="" || dt_recovered == "DD/MM/YYYY") {
			alert("Please Select Date Of Recovered");
			$("input#dt_recovered").focus();
			return false;
		} */
	if(desertion_cause == null || desertion_cause=="0"){ 
		alert("Please Select The Desertion Cause");
		$("select#desertion_cause").focus();
		return false;
	} 
	if(desertion_cause=="3") {
		if(ot_desertion_cause == null || ot_desertion_cause==""){
			alert( "Please Enter Other ");
			$("input#ot_desertion_cause").focus();
			return false;
			}
		}
	/* if(indl_class == null || indl_class==""){ 
		alert("Please Enter The Indl Class");
		$("input#indl_class").focus();
		return false;
	}  */
	
	    $.post('deserterAction?' + key + "=" + value, {
		   deserter:deserter,
		   arm_type:arm_type,
		   arm_type_weapon:arm_type_weapon,
		   dt_desertion:dt_desertion,
		   dt_recovered:dt_recovered,
		   desertion_cause:desertion_cause,
		   indl_class:indl_class,
		   ot_desertion_cause:ot_desertion_cause,
		   recovered_arms:recovered_arms,
		   des_ch_id:des_ch_id,census_id:census_id,comm_id:comm_id,comm_date:comm_date
		   }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#des_ch_id').val(data);
	        	 alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		}); 
}

//get
function get_deserter(){
	var census_id=$('#census_id').val();
	var comm_id=$('#comm_id').val();
	$.post('deserter_GetData?' + key + "=" + value,{census_id:census_id,comm_id:comm_id}, function(j){
		var v=j.length;
		if(v!=0){
			
			if (j[0].dt_recovered == "" || j[0].dt_recovered == null || j[0].dt_recovered == "null"){
				
		
				$("#div_dt_rec").hide();
				$("#des_ch_id").val(j[0].id);
				$("#deserter").val(j[0].deserter);
				$("#arm_type").val(j[0].arm_type);
				if(j[0].arm_type == 1){
					   $("#div_weapon").show(); 
					   $("#arm_type_weapon").val(j[0].arm_type_weapon);
				}else {
					    $("#div_weapon").hide(); 
						$("#arm_type_weapon").val("");
				}
				
				$("#dt_desertion").val(ParseDateColumncommission(j[0].dt_desertion));
				
				$("#desertion_cause").val(j[0].desertion_cause);
				
				if (j[0].desertion_cause == 3){
					$("#div_cause").show(); 
					$("#ot_desertion_cause").val(j[0].ot_desertion_cause);
				}else{
					$("#div_cause").hide(); 
			 		$('#ot_desertion_cause').val("");
				}
				$("#indl_class").val(j[0].indl_class);
				
				
				
			}else{
		
				if(j[0].arm_type == 1){
					$("#div_arms_rec").show();
					$("#recovered_arms").val(j[0].recovered_arms);
				}
				$("#div_dt_rec").show();
				$("#des_ch_id").val(j[0].id);
				$("#deserter").val(j[0].deserter);
				$("#arm_type").val(j[0].arm_type);
				 $("select#arm_type").attr('readonly', true);
				if(j[0].arm_type == 1){
					   $("#div_weapon").show(); 
					   $("#arm_type_weapon").val(j[0].arm_type_weapon);
					   $("input#arm_type_weapon").attr('readonly', true);
				}else {
					    $("#div_weapon").hide(); 
						$("#arm_type_weapon").val("");
				}
				
				$("#dt_desertion").val(ParseDateColumncommission(j[0].dt_desertion));
				 $("input#dt_desertion").attr('readonly', true);
				$("#dt_recovered").val(ParseDateColumncommission(j[0].dt_recovered));
				$("#desertion_cause").val(j[0].desertion_cause);
				 $("select#desertion_cause").attr('readonly', true);
				if (j[0].desertion_cause == 3){
					$("#div_cause").show(); 
					$("#ot_desertion_cause").val(j[0].ot_desertion_cause);
					 $("input#ot_desertion_cause").attr('readonly', true);
				}else{
					$("#div_cause").hide(); 
			 		$('#ot_desertion_cause').val("");
				}
				$("#indl_class").val(j[0].indl_class);
				 $("input#indl_class").attr('readonly', true);
				 
				 
				
				 
			}
			
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	
	///if(	$("#dt_recovered").val() != "null" || 	$("#dt_recovered").val() != "" || 	$("#dt_recovered").val() != null){}
	
	 $.post('deserter_GetDataA?' + key + "=" + value,{census_id:census_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){
				
				if(j[0].arm_type == 1){
					$("#div_arms_rec").show();
					$("#recovered_arms").val(j[0].recovered_arms);
				}
				
				$("#div_dt_rec").show();
				$("#des_ch_id").val(j[0].id);
				$("#deserter").val(j[0].deserter);
				$("#arm_type").val(j[0].arm_type);
				 $("select#arm_type").attr('readonly', true);
				if(j[0].arm_type == 1){
					   $("#div_weapon").show(); 
					   $("#arm_type_weapon").val(j[0].arm_type_weapon);
					   $("input#arm_type_weapon").attr('readonly', true);
				}else {
					    $("#div_weapon").hide(); 
						$("#arm_type_weapon").val("");
				}
				
				$("#dt_desertion").val(ParseDateColumncommission(j[0].dt_desertion));
				 $("input#dt_desertion").attr('readonly', true);
				$("#dt_recovered").val(ParseDateColumncommission(j[0].dt_recovered));
				
				$("#desertion_cause").val(j[0].desertion_cause);
				 $("select#desertion_cause").attr('readonly', true);
				if (j[0].desertion_cause == 3){
					$("#div_cause").show(); 
					$("#ot_desertion_cause").val(j[0].ot_desertion_cause);
					 $("input#ot_desertion_cause").attr('readonly', true);
				}else{
					$("#div_cause").hide(); 
			 		$('#ot_desertion_cause').val("");
				}
				$("#indl_class").val(j[0].indl_class);
				 $("input#indl_class").attr('readonly', true);
			}
		  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
	
	
}


/*/////////////////////////  End Deserter ////////////////// */ 
</script>

<script>
  
  //////////////////////// Date //////////////////////

jQuery(function($){ //on document.ready  
	 datepicketDate('r_date_of_authority');            
	 datepicketDate('date_of_rank');
	 datepicketDate('na_date_of_authority');
	 datepicketDate('appt_date_of_authority');
	 datepicketDate('date_of_appointment');
	 datepicketDate('issue_dt'); 
	 datepicketDate('rel_date_of_authority'); 
	 datepicketDate('sib_date_of_birth1');
	 datepicketDate('sib_adop_date1');
	 datepicketDate('date_authority');        
	 datepicketDate('addr_date_authority');  
	 datepicketDate('language_date_of_authority');  
	 datepicketDate('qualification_date_of_authority'); 
	 datepicketDate('promo_exam_date_of_authority'); 
	 datepicketDate('from_dt1'); 
	 datepicketDate('to_dt1'); 
	 datepicketDate('awardsNmedal_date_of_authority1');
	 datepicketDate('date_of_award1');
	 datepicketDate('date_of_casuality');
	 datepicketDate('batnpc_date_of_authority');
	 datepicketDate('date_of_informing');
	 datepicketDate('award_dt');
	 datepicketDate('inter_arm_date_of_authority');
	 datepicketDate('inter_arm_with_effect_from');
	 datepicketDate('coc_date_of_authority');
	 datepicketDate('coc_date_of_permanent_commission');
	 datepicketDate('coc_date_of_seniority');
	 datepicketDate('eff_coc_date_of_seniority');
	 datepicketDate('eoc_authority_date');
	 datepicketDate('eoc_from');
	 datepicketDate('eoc_to');
	 $( "#eoc_to" ).datepicker( "option", "maxDate", null);
	 datepicketDate('sec_date_of_authority');
	 datepicketDate('secondment_effect');
	 datepicketDate('date_of_authority_non_ef');
	 datepicketDate('date_of_non_effective');
	 datepicketDate('marriage_date_of_authority');
	 datepicketDate('marriage_date');
	 datepicketDate('marriage_date_of_birth');
	 datepicketDate('divorce_date');
	 
	 datepicketDate('army_course_date_of_authority');
	 datepicketDate('army_course_start_date1');
	 datepicketDate('army_course_date_of_completion1');
	 datepicketDate('disi_authority_date');
	 datepicketDate('tnai_date_of_authority');
	 datepicketDate('change_in_tnaino_date');
	 
	 
	 datepicketDate('ta_date_of_authority');
	 datepicketDate('ta_date');
	 //medical
	 
     datepicketDate('madical_date_of_authority');
	 
	 datepicketDate('s_from_date1');
	 datepicketDate('s_to_date1');
	 
	 datepicketDate('h_from_date1');
	 datepicketDate('h_to_date1');
	 
	 datepicketDate('a_from_date1');
	 datepicketDate('a_to_date1');
	 
	 datepicketDate('p_from_date1');
	 datepicketDate('p_to_date1');
	 
	 datepicketDate('e_from_date1');
	 datepicketDate('e_to_date1');
	 datepicketDate('change_in_name_date');
	 datepicketDate('change_in_rel_date');
	 
	 datepicketDate('1bx_from_date');
	 datepicketDate('1bx_to_date');
	// datepicketDate('date_of_birth1');//kajal
	 $( "#1bx_to_date" ).datepicker( "option", "maxDate", null);
	
	 $( "#s_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#h_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#a_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#p_to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#e_to_date1" ).datepicker( "option", "maxDate", null);
	 // medical ends
	 
	 datepicketDate('dt_desertion');
	 datepicketDate('dt_recovered');
	 
	 datepicketDate('separated_from_dt');
	 datepicketDate('separated_to_dt');
	/*  if(clickrecall )
	 var currentDate = new Date();  
	 $("#dt_recovered").datepicker("setDate",currentDate);
	  */
	 $("#circumstances").click(function(){
		 $('body').unbind();
	  });
	  
	 $("#circumstances").focusout(function(){
		 
	 
		  $('body').bind('cut copy paste', function (e) {
		        e.preventDefault();
		    });
		  });
});
  

</script>

<!-- /********  CSD DETAIL  keval*********/ -->

<script>


csdno=1;
CSD_srno=1;
function CSD_Card_Add_fn(q){
	 if( $('#CSD_Card_Add'+q).length )         
	 {
			$("#CSD_Card_Add"+q).hide();
	 }
	 if(q!=0)
		 CSD_srno+=1;
	
		 csdno=q+1;

	$("table#CSD_table").append('<tr id="tr_csd'+csdno+'">'
			+'<td class="CSD_srno" style="width: 2%;">'+CSD_srno+'</td>'
			+'<td style="width: 10%;">'
			+'<select  id="relation'+csdno+'" name="relation'+csdno+'" onchange="getNameAndDob('+csdno+');" class="form-control autocomplete" >'
			+' <option value="0">--Select--</option>'
			+'<c:forEach var="item" items="${getCSDCategoryList}" varStatus="num">'
			+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+'</c:forEach>'
			+'</select>'
			+'</td>'
			+'<td id="iname'+csdno+'"><label id="namelb'+csdno+'"></label> <input type="hidden" id="name'+csdno+'" name="name'+csdno+'"  class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
			
			+'<td id="sname'+csdno+'" style="width: 10%;display:none;">'
			+'<select  id="Dname'+csdno+'" name="name'+csdno+'" class="form-control autocomplete"onchange="getDob('+csdno+');" >'
			+' <option value="0">--Select--</option>'
			+'</select>'
			+'</td>'
			
			+'<td>' 
			+' <label id="date_of_birthlb'+csdno+'"></label> <input type="hidden" name="date_of_birth'+csdno+'" id="date_of_birth'+csdno+'" maxlength="10"  > '
			+ '</td>'
			+'<td>'
			+'<label for="type_of_card_grocery'+csdno+'"><input type="radio" class="from-control" id="type_of_card_grocery'+csdno+'" name= "type_of_card'+csdno+'" value="grocery"> Grocery </label>'
			+'<label for="type_of_card_liquor'+csdno+'"><input type="radio" class="from-control" id="type_of_card_liquor'+csdno+'" name="type_of_card'+csdno+'" value="liquor"> Liquor </label>'
			+'</td>'
			+'<td><input type="text" id="card_no'+csdno+'" name="card_no'+csdno+'" class="form-control autocomplete" maxlength="25"  onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"  autocomplete="off"></td>'
			+'<td style="display:none;"><input type="text" id="CSD_Card_ch_id'+csdno+'" name="CSD_Card_ch_id'+csdno+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="width: 10%;">'
			+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "CSD_save'+csdno+'" onclick="CSD_save_fn('+csdno+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "CSD_Card_Add'+csdno+'" onclick="CSD_Card_Add_fn('+csdno+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "CSD_Card_Remove'+csdno+'" onclick="CSD_Card_Remove_fn('+csdno+');"><i class="fa fa-trash"></i></a> '
			+'</td></tr>');
	
}


function CSD_Card_Remove_fn(R){
	if (R == '1') {
		alert("Can't Delete From Here!! \nPlease Ask Approver to Reject This Entry");
	}
	else{
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
		 
	 var CSD_Card_ch_id=$('#CSD_Card_ch_id'+R).val();
	  $.post('CSDCard_delete_action?' + key + "=" + value, {CSD_Card_ch_id:CSD_Card_ch_id }, function(data){ 
			   if(data=='1'){
					 $("tr#tr_csd"+R).remove(); 
							 if(R==csdno){
								 R = R-1;
								 var temp=true;
								 for(i=R;i>=1;i--){
								 if( $('#CSD_Card_Add'+i).length )         
								 {
									 $("#CSD_Card_Add"+i).show();
									 temp=false;
									 csdno=i;
									 break;
								 }}
						 
							 if(temp){
								 CSD_Card_Add_fn(0);
								}
			  			 }
							 $('.CSD_srno').each(function(i, obj) {
								 
									obj.innerHTML=i+1;
									CSD_srno=i+1;
									 });
							 alert("Data Deleted Successfully");
			   }
				 else{
					 alert("Data not Deleted ");
				 }
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
	}
}
function CSD_save_fn(ps){
	
	var census_id=$('#census_id').val();
	var comm_id=$('#comm_id').val();
	var name=$('#name'+ps).val();
	var Sname = $("#Dname"+ps).val(); 
	var date_of_birth=$('#date_of_birth'+ps).val();
	var relation=$('#relation'+ps).val();
	var card_no=$('#card_no'+ps).val();
	var type_of_card="";
	if(document.getElementById('type_of_card_grocery'+ps).checked){
		type_of_card=$('#type_of_card_grocery'+ps).val()
	}
	if(document.getElementById('type_of_card_liquor'+ps).checked){
		type_of_card=$('#type_of_card_liquor'+ps).val()	
	}
	
	var CSD_Card_ch_id=$('#CSD_Card_ch_id'+ps).val();
	
	if (relation == "0") {
		alert("Please Select Category");
		$("select#relation"+ps).focus();
		return false;
	}
	if (name.trim()=="" && Sname == "0") {
		alert("Please Enter Name");
		$("input#name"+ps).focus();
		return false;
	}
	/* if (date_of_birth.trim()=="" || date_of_birth.trim()=="DD/MM/YYYY") {
		alert("Please Select Date Of Birth");
		$("input#date_of_birth"+ps).focus();
		return false;
	} */
	 var r =  $('input[name="type_of_card'+ps+'"]:checked').val();
	  if(r == undefined)
	  {		 
		    alert("Please Select Type Of Card");
			return false;
	  } 
	
	if (card_no.trim()=="" || card_no.trim().length<10) {
		alert("Please Enter Card No");
		$("input#card_no"+ps).focus();
		return false;
	}
	  $.post('CSD_action?' + key + "=" + value, {census_id:census_id,comm_id:comm_id,name:name,Sname:Sname,
		  date_of_birth:date_of_birth,relation:relation,card_no:card_no,type_of_card:type_of_card,
		  CSD_Card_ch_id:CSD_Card_ch_id }, function(data){
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#CSD_Card_ch_id'+ps).val(data);
	        	 $("#CSD_Card_Add"+ps).show();
	        	 $("#CSD_Card_Remove"+ps).show();
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data)
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
}


function get_CSD_details(){//keval
	 var census_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('updateCSD_getData?' + key + "=" + value, {census_id:census_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){
				$('#CSDtbody').empty(); 
			
		x=0;
		for(i;i<v;i++){
			
			
			x=x+1;
			
		
				var td=ParseDateColumncommission(j[i].date_of_birth);
				$("table#CSD_table").append('<tr id="tr_csd'+x+'">'
						+'<td class="CSD_srno" style="width: 2%;">'+x+'</td>'
						
						+'<td><select  id="relation'+x+'" name="relation'+x+'" value='+j[i].relation+' onchange="getNameAndDob('+x+');" class="form-control autocomplete" >'
						+' <option value="0">--Select--</option>'
						+'<c:forEach var="item" items="${getCSDCategoryList}" varStatus="num">'
						+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'</c:forEach>'
						+'</select>'
						+'</td>'
						
						+'<td id="iname'+x+'"><label id="namelb'+x+'">'+j[i].name+'</label> <input type="hidden" id="name'+x+'" name="name'+x+'" value='+j[i].name+' class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"></td>'
						
						+'<td id="sname'+x+'" style="width: 10%;display:none;">'
						+'<select  id="Dname'+x+'" name="name'+x+'" class="form-control autocomplete"onchange="getDob('+x+');" >'
						+' <option value="0">--Select--</option>'
						+'</select>'
						+'</td>'
						
						
						+'<td>' 
						+' <label id="date_of_birthlb'+x+'">'+td+'</label> <input type="hidden" name="date_of_birth'+x+'" id="date_of_birth'+x+'" maxlength="10"  '
						+'	 value="'+td+'">'
						+ '</td>'
						+'<td>'
						+'<label for="type_of_card_grocery'+x+'"><input type="radio" class="from-control" id="type_of_card_grocery'+x+'" name= "type_of_card'+x+'" value="grocery"> Grocery </label>'
						+'<label for="type_of_card_liquor'+x+'"><input type="radio" class="from-control" id="type_of_card_liquor'+x+'" name="type_of_card'+x+'" value="liquor"> Liquor </label>'
						+'</td>'
						+'<td><input type="text" id="card_no'+x+'" name="card_no'+x+'" value='+j[i].card_no+' class="form-control autocomplete"  onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"  maxlength="25" autocomplete="off"></td>'
						+'<td style="display:none;"><input type="text" id="CSD_Card_ch_id'+x+'" name="CSD_Card_ch_id'+x+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
						+'<td style="width: 10%;">'
						+'<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "CSD_save'+x+'" onclick="CSD_save_fn('+x+');" ><i class="fa fa-save"></i></a>'
						+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "CSD_Card_Add'+x+'" onclick="CSD_Card_Add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						+'<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "CSD_Card_Remove'+x+'" onclick="CSD_Card_Remove_fn('+x+');"><i class="fa fa-trash"></i></a> ' 
						+'</td></tr>');
				$('#relation'+x).val(j[i].relation); 
				//$('#date_of_birth'+x).val(td);
			
				
				if($('#relation'+x).val() == 5 || $('#relation'+x).val() == 6){
					$.ajaxSetup({
						async : false
					});
					getNameAndDob(x);
					if($('#Dname'+x).is(":visible")){
						$('#Dname'+x).val(j[i].name); 
						$("#date_of_birth"+x).val(td)
						$("#date_of_birthlb"+x).text(td)
					}
 				}
				if(j[i].type_of_card =="grocery"){
					$('#type_of_card_grocery'+x).attr("checked",true); 
				}else if(j[i].type_of_card =="liquor"){
					$('#type_of_card_liquor'+x).attr("checked",true); 
				}
 				
				
		}
		csdno = v;
		CSD_srno = v;
		$('#CSD_Card_Add' + csdno).show();
			}
	  });
}

function getDob(val){
	 $("#date_of_birth"+val).val("")
	 $("#date_of_birthlb"+val).text("")
	var id = $("select#Dname"+val).val();
	 var comm_id=$('#comm_id').val(); 
	$.post('getChilddob?' + key + "=" + value, {id:id,comm_id:comm_id}, function(j){
			 $("#date_of_birth"+val).val(ParseDateColumncommission(j[0][0]))
			 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][0]))
	 });
	
}

function getNameAndDob(val){
	 $("#date_of_birth"+val).val("")
	 $("#name"+val).val("");
	 $("#namelb"+val).text("");
	 $("#Dname"+val).val("0");
	 $("#date_of_birth"+val).val("")
	  $("#date_of_birthlb"+val).text("");
	 
	 var options = '<option   value="0">' + "--Select--"
		+ '</option>';
	 $("select#Dname"+val).html(options);
	 var rela = $("#relation"+val).val();
	 var comm_id=$('#comm_id').val(); 
		if(rela=='1' || rela=='3' || rela=='4' || rela=='2'){ //self
			
			 $.post('getSelfMotFatName?' + key + "=" + value, {comm_id:comm_id}, function(j){				 
				// alert(j);
					 $("#iname"+val).show();
					 $("#sname"+val).hide();
					
					 $("#namelb"+val).show();
					 $("#Dname"+val).hide();
					 $("#Dname"+val).val('0');
				 if(rela =='1' || rela =='2'){
					 $("#date_of_birth"+val).val(ParseDateColumncommission(j[0][2]))
					 $("#name"+val).val(j[0][1]); 
					 $("#namelb"+val).text(j[0][1]);
					 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][2]));
					 
				 }else if (rela =='3'){
					 $("#date_of_birth"+val).val(ParseDateColumncommission(j[0][6]))
					 $("#name"+val).val(j[0][5]);
					 $("#namelb"+val).text(j[0][5]);
					 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][6]));
				 }else if (rela =='4'){
					 $("#date_of_birth"+val).val(ParseDateColumncommission(j[0][4]))
					 $("#name"+val).val(j[0][3]);
					 $("#namelb"+val).text(j[0][3]);
					 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][4]));
				 }				
				 
			 });
			
		}/* else if(rela=='2'){
			 $.post('getSpouseName?' + key + "=" + value, {comm_id:comm_id}, function(j){
				 
			 if(j.length <=0){
				 alert("Please Fill Marital Details");
				 $("select#relation"+val).val('0');
			 }
					 if(rela =='2'){
						 $("#iname"+val).show();
						 $("#sname"+val).hide();
						 $("#namelb"+val).show();
						 $("#Dname"+val).hide();
						 $("#Dname"+val).val('0');
						 $("#date_of_birth"+val).val(ParseDateColumncommission(j[0][2]))
						 $("#name"+val).val(j[0][1]); 
						 $("#namelb"+val).text(j[0][1]);
						 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][2]));
					 }
					
					 
				 });
		} */else if(rela=='5' || rela=='6'){
			$.post('getChildName?' + key + "=" + value, {comm_id:comm_id,rela:rela}, function(j){
				
				
					
				 if(j.length <=0){
					 alert("Please Fill Child Details");
					 $("select#relation"+val).val('0');
				 }
				if(j.length > 1){
					 $("#iname"+val).hide();
					 $("#sname"+val).show();
					 $("#namelb"+val).hide();
					 $("#name"+val).val('');
					 $("#namelb"+val).text('');
					 $("#Dname"+val).show();
				
				for (var i = 0; i < j.length; i++) {

					options += '<option   value="' + j[i][1] + '" name="'+j[i][1]+'" >'
							+ j[i][1] + '</option>';

				}
					 $("select#Dname"+val).html(options);
				}
				if(j.length == 1){
					 $("#date_of_birth"+val).val(ParseDateColumncommission(j[0][2]))
					 $("#name"+val).val(j[0][1]); 
					 $("#namelb"+val).text(j[0][1]);
					 $("#date_of_birthlb"+val).text(ParseDateColumncommission(j[0][2]));
					 $("#iname"+val).show();
					 $("#sname"+val).hide();
					 $("#namelb"+val).show();
					 $("#Dname"+val).hide();
					
				}
				 
			 });
		
		
		}
		
		 
		 
}

function validate_ta_save_fn(){
	 if ($("input#ta_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#ta_authority").focus();
			return false;
		}
	 	 if ($("input#ta_date_of_authority").val() == "DD/MM/YYYY" || $("input#ta_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#ta_date_of_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#ta_date").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 	if ($("select#ta_status").val()=="-1") {
			alert("Please select TA Status ");
			$("select#ta_status").focus();
			return false;
		}
	 	if ($("input#ta_date").val() == "DD/MM/YYYY" || $("input#ta_date").val().trim()=="") {
			alert("Please Enter Date of TA STATUS ");
			$("input#ta_date").focus();
			return false;
		}
	
	 		var comm_id= $('#comm_id').val();
	 		var census_id = $('#census_id').val();
	 		var ta_status = $('#ta_status').val();
	 		var ta_id = $('#ta_id').val();
	 		var comm_date = $('#comm_date').val();
	 		var ta_date_of_authority = $('#ta_date_of_authority').val();
	 		var ta_date = $('#ta_date').val();
	 		var ta_authority = $('#ta_authority').val();
	 		var formdata = $('#form_ta_status').serialize();
	 		
	 		
	 		   $.post('ta_status_action?' + key + "=" + value, {comm_id:comm_id,census_id:census_id,ta_status:ta_status,ta_id:ta_id,
	 			  comm_date:comm_date,ta_date_of_authority:ta_date_of_authority,ta_date:ta_date,ta_authority:ta_authority}, function(data){		      
	 		          if(data=="update")
	 		        	  alert("Data Updated Successfully");
	 		          else if(parseInt(data)>0){
	 		        	
	 		        	 $('#ta_id').val(data);
	 		        	  alert("Data Saved Successfully")
	 		          }
	 		          else
	 		        	  alert(data);
	 		    		          
	 		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	 		  		});	
	 	}



function get_ta_status(){
	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('ta_status_GetData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id}, function(j){
			var v=j.length;
			
			if(v!=0){
				$('#ta_id').val(j[i].id);
				$("#ta_authority").val(j[i].ta_status_authority);
				$("#ta_date_of_authority").val(ParseDateColumncommission(j[i].ta_authority_date));
				$("#ta_date").val(ParseDateColumncommission(j[i].date_of_ta_status));
				$("#ta_status").val(j[i].ta_status);
				
			}
	  });
}



</script>
<!-- /**********END CSD DETAIL *********/ -->
<script>
///////////////// Start  Pop UP History //////////

function Pop_Up_History(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	comm_id = $("#comm_id").val();
	census_id = $("#census_id").val();

	if(a == "idenity_card"){
		$('#ic_comm_id').val(comm_id);
		$('#ic_census_id').val(census_id);
		document.getElementById('Change_Identity_Card_Form').submit();
	}
	if(a == "TA_STATUS"){
		$('#ta_comm_id').val(comm_id);
		$('#ta_census_id').val(census_id);
		document.getElementById('Change_TA_Form').submit();
	}


	if(a == "rank"){
		$("#rank_comm_id").val(comm_id);
		$("#rank_census_id").val(census_id);
		document.getElementById('Change_Rank_Form').submit();
	}
	
	if(a == "update_child_dtl"){
		$("#uc_comm_id").val(comm_id);
		$("#uc_census_id").val(census_id);
		document.getElementById('update_child_dtlForm').submit();
	}
	
	if(a == "change_nok"){
		$("#nok_comm_id").val(comm_id);
		$("#nok_census_id").val(census_id);
		document.getElementById('nok_updateForm').submit();
	}
	
	if(a == "religion"){
		$("#rel_comm_id").val(comm_id);
		$("#rel_census_id").val(census_id);
		$("#Change_In_Religion_Form").submit();
		//document.getElementById('Change_In_Religion_Form').submit();
	}
	
	if(a == "Name"){
		$('#name_comm_id').val(comm_id);
		$('#name_census_id').val(census_id);
		document.getElementById('Change_Name_Form').submit();
	}
	
	if(a == "promotional_exam"){
		$("#pe_comm_id").val(comm_id);
		$("#pe_census_id").val(census_id);
		document.getElementById('Promotional_Exam_Form').submit();
	}
	
	if(a == "marital_status"){
		$("#ms_comm_id").val(comm_id);
		$("#ms_census_id").val(census_id);
		$("#Change_In_Marital_Status_Form").submit();
	}
	
	if(a == "Add_language"){
		$("#al_comm_id").val(comm_id);
		$("#al_census_id").val(census_id);
		document.getElementById('Add_Language_Form').submit();
	}
	if(a == "Update_Qualification"){
		$("#uq_comm_id").val(comm_id);
		$("#uq_census_id").val(census_id);
		document.getElementById('Update_Qualification_Form').submit();
	}
	if(a == "Appointment"){
		$('#appointment_comm_id').val(comm_id);
		$('#appointment_census_id').val(census_id);
		document.getElementById('Change_Appointment_Form').submit();
	}

   if(a == "contact_details"){
		$('#cd_comm_id').val(comm_id);
		$('#cd_census_id').val(census_id);
		document.getElementById('Change_Contact_Details_Form').submit();
	}
	
	if(a == "army_course"){
		$("#ac_comm_id").val(comm_id);
		$("#ac_census_id").val(census_id);
		document.getElementById('Army_Course_Form').submit();
	}

	if(a == "bpet_details"){
		$("#bpet_comm_id").val(comm_id);
		$("#bpet_census_id").val(census_id);
		document.getElementById('Update_BPETDetails_Form').submit();
	}
	
	if(a == "known_allergy"){
		$("#ka_comm_id").val(comm_id);
		$("#ka_census_id").val(census_id);
		document.getElementById('known_allergyForm').submit();
	}
	
	if(a == "change_in_address"){
		$("#ca_comm_id").val(comm_id);
		$("#ca_census_id").val(census_id);
		document.getElementById('change_in_addressForm').submit();
	}
	
	
	if(a == "battle_physical"){
		$("#bp_comm_id").val(comm_id);
		$("#bp_census_id").val(census_id);
		$("#Update_Battle_And_Physical_Casuality_Form").submit();
	}
   if(a == "discipline"){
		$("#ud_comm_id").val(comm_id);
		$("#ud_census_id").val(census_id);
		$("#Update_Discipline_Form").submit();
	}
   if(a == "inter_arm_service_transfer"){
		$("#as_comm_id").val(comm_id);
		$("#as_census_id").val(census_id);
		$("#Inter_Arm_Service_Transfer_Form").submit();
	}
   if(a == "permt_commission"){
		$("#ssc_comm_id").val(comm_id);
		$("#ssc_census_id").val(census_id);
		$("#Ssc_To_Permit_Commission_Form").submit();
	}
   if(a == "firing_standard"){
		$("#fs_comm_id").val(comm_id);
		$("#fs_census_id").val(census_id);
		document.getElementById('Firing_Standard_Form').submit();
	}
   
   if(a == "Deserter"){
		$('#deserter_comm_id').val(comm_id);
		$('#deserter_census_id').val(census_id);
		document.getElementById('Deserter_Form').submit();
	}
   if(a == "Secondment"){
		$('#Secondment_comm_id').val(comm_id);
		$('#Secondment_census_id').val(census_id);
		document.getElementById('Secondment_Form').submit();
	}
   
   if(a == "Extension_of_SSC"){
		$('#eos_comm_id').val(comm_id);
		$('#eos_census_id').val(census_id);
		document.getElementById('Extension_of_SSC_Form').submit();
	}

   if(a == "Update_Visit_Foriegn"){
   		$("#vf_comm_id").val(comm_id);
   		$("#vf_census_id").val(census_id);
   		document.getElementById('Update_Visit_Foriegn_Form').submit();
   	}
   	if(a == "Non_Effective_Status"){
   		$("#nes_comm_id").val(comm_id);
   		$("#nes_census_id").val(census_id);
   		document.getElementById('Non_Effective_Status_Form').submit();
   	}
   	
   	if(a == "update_awards_medal"){
		$("#am_comm_id").val(comm_id);
		$("#am_census_id").val(census_id);
		document.getElementById('update_awards_medalForm').submit();
	}
   	
   	if(a == "canteen_p"){
		$("#canteen_comm_id").val(comm_id);
		$("#canteen_census_id").val(census_id);
		document.getElementById('canteenForm').submit();
	}

   	if(a == "Change_Medicle")
   		{
   			$("#cim_comm_id").val(comm_id);
   			$("#cim_census_id").val(census_id);
   			document.getElementById('Change_In_Medicle_Form').submit();
   		}
   	
   	if(a == "TNAI"){
   		$('#comm_idTnai').val(comm_id);	
   		document.getElementById('popup_TnaiNo').submit();
   	}

}



///////////////// End  Pop UP History //////////

</script>
 	
<c:url value="Update_Visit_Foriegn_DetailsUrl" var="Update_Visit_Foriegn_DetailsUrl" />
<form:form action="${Update_Visit_Foriegn_DetailsUrl}" method="post" id="Update_Visit_Foriegn_Form" name="Update_Visit_Foriegn_Form"  target="result">
<input type="hidden" name="vf_comm_id" id="vf_comm_id" value="0"/>
 <input type="hidden" name="vf_census_id" id="vf_census_id" value="0"/> 
</form:form>

<c:url value="Non_Effective_Status_DetailsUrl" var="Non_Effective_Status_DetailsUrl" />
<form:form action="${Non_Effective_Status_DetailsUrl}" method="post" id="Non_Effective_Status_Form" name="Non_Effective_Status_Form"  target="result">
<input type="hidden" name="nes_comm_id" id="nes_comm_id" value="0"/>
 <input type="hidden" name="nes_census_id" id="nes_census_id" value="0"/> 
</form:form>
 <!-- /////////// Change_Identity_Card_Form //////////////// -->
 
<c:url value="Change_In_Identity_CardUrl" var="Change_In_Identity_CardUrl" />
<form:form action="${Change_In_Identity_CardUrl}" method="post" id="Change_Identity_Card_Form"
	name="Change_Identity_Card_Form" modelAttribute="id" target="result">
	<input type="hidden" name="ic_comm_id" id="ic_comm_id" value="0" />
	<input type="hidden" name="ic_census_id" id="ic_census_id" value="0" />
</form:form>


	
<!-- /////////////// Change Rank /////////////// -->
	
<c:url value="Change_Of_RankUrl" var="Change_Of_RankUrl" />
<form:form action="${Change_Of_RankUrl}" method="post" id="Change_Rank_Form" name="Change_Rank_Form"  target="result">
<input type="hidden" name="rank_comm_id" id="rank_comm_id" value="0"/>
 <input type="hidden" name="rank_census_id" id="rank_census_id" value="0"/> 
</form:form> 

<!-- /////////// Update_Child_Details_Form //////////////// -->
<c:url value="Update_Child_dtlUrl" var="Update_Child_dtlUrl" />
<form:form action="${Update_Child_dtlUrl}" method="post" id="update_child_dtlForm"
	name="update_child_dtlForm" target="result">
 <input type="hidden" name="uc_comm_id" id="uc_comm_id" value="0" />  
<input type="hidden" name="uc_census_id" id="uc_census_id" value="0" />
</form:form>

<c:url value="Change_NOKUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="nok_updateForm"
	name="nok_updateForm" target="result">
<input type="hidden" name="nok_comm_id" id="nok_comm_id" value="0" /> 
<input type="hidden" name="nok_census_id" id="nok_census_id" value="0" />
</form:form>

<!-- /////////// Change_In_Religion_Form //////////////// -->

<c:url value="Change_In_ReligionUrl" var="Change_In_ReligionUrl" />
<form:form action="${Change_In_ReligionUrl}" method="post" id="Change_In_Religion_Form"
	name="Change_In_Religion_Form" modelAttribute="id" target="result">
	<input type="hidden" name="rel_comm_id" id="rel_comm_id" value="0" />
	<input type="hidden" name="rel_census_id" id="rel_census_id" value="0" />
</form:form>
 
  <!-- /////////// Change_Name_Form //////////////// -->
 
<c:url value="Change_In_NameUrl" var="Change_In_NameUrl" />
<form:form action="${Change_In_NameUrl}" method="post" id="Change_Name_Form"
	name="Change_Name_Form" modelAttribute="id" target="result">
	<input type="hidden" name="name_comm_id" id="name_comm_id" value="0" />
	<input type="hidden" name="name_census_id" id="name_census_id" value="0" />
</form:form>

<!-- /////////////////// PROMOTIONAL EXAM//////////////// -->
<c:url value="Promotional_ExamUrl" var="Promotional_ExamUrl" />
<form:form action="${Promotional_ExamUrl}" method="post" id="Promotional_Exam_Form" name="Promotional_Exam_Form"  target="result">
<input type="hidden" name="pe_comm_id" id="pe_comm_id" value="0"/>
 <input type="hidden" name="pe_census_id" id="pe_census_id" value="0"/> 
</form:form>
<!-- /////////////////// MaritaL Status//////////////// -->
<c:url value="Change_In_Marital_StatusUrl" var="Change_In_Marital_StatusUrl" />
<form:form action="${Change_In_Marital_StatusUrl}" method="post" id="Change_In_Marital_Status_Form"
	name="Change_In_Marital_Status_Form" modelAttribute="id" target="result">
	<input type="hidden" name="ms_comm_id" id="ms_comm_id" value="0" />
	<input type="hidden" name="ms_census_id" id="ms_census_id" value="0" />
</form:form>

<!-- /////////////////// Add Language//////////////// -->
<c:url value="Add_LanguageUrl" var="Add_LanguageUrl" />
<form:form action="${Add_LanguageUrl}" method="post" id="Add_Language_Form" name="Add_Language_Form"  target="result">
<input type="hidden" name="al_comm_id" id="al_comm_id" value="0"/>
 <input type="hidden" name="al_census_id" id="al_census_id" value="0"/> 
</form:form>

<!-- /////////////////// Update Qualification//////////////// -->
<c:url value="Update_QualificationUrl" var="Update_QualificationUrl" />
<form:form action="${Update_QualificationUrl}" method="post" id="Update_Qualification_Form" name="Update_Qualification_Form"  target="result">
<input type="hidden" name="uq_comm_id" id="uq_comm_id" value="0"/>
 <input type="hidden" name="uq_census_id" id="uq_census_id" value="0"/> 
</form:form>

<c:url value="Change_In_AppointmentUrl" var="Change_In_AppointmentUrl" />
<form:form action="${Change_In_AppointmentUrl}" method="post" id="Change_Appointment_Form"
	name="Change_Appointment_Form" modelAttribute="id" target="result">
	<input type="hidden" name="appointment_comm_id" id="appointment_comm_id" value="0" />
	<input type="hidden" name="appointment_census_id" id="appointment_census_id" value="0" />
</form:form>

<c:url value="Change_In_Contact_DetailsUrl" var="Change_In_Contact_DetailsUrl" />
<form:form action="${Change_In_Contact_DetailsUrl}" method="post" id="Change_Contact_Details_Form"
	name="Change_Contact_Details_Form" modelAttribute="id" target="result">
	<input type="hidden" name="cd_comm_id" id="cd_comm_id" value="0" />
	<input type="hidden" name="cd_census_id" id="cd_census_id" value="0" />
</form:form>

<c:url value="Army_CourseUrl" var="Army_CourseUrl" />
<form:form action="${Army_CourseUrl}" method="post" id="Army_Course_Form" name="Army_Course_Form"  target="result">
<input type="hidden" name="ac_comm_id" id="ac_comm_id" value="0"/>
 <input type="hidden" name="ac_census_id" id="ac_census_id" value="0"/> 
</form:form>
 
<!-- ///////////////////////UPDATE BPET DETAILS///////////// -->
<c:url value="Update_BPET_DetailsUrl" var="Update_BPET_DetailsUrl" />
<form:form action="${Update_BPET_DetailsUrl}" method="post" id="Update_BPETDetails_Form" name="Update_BPETDetails_Form"  target="result">
<input type="hidden" name="bpet_comm_id" id="bpet_comm_id" value="0"/>
 <input type="hidden" name="bpet_census_id" id="bpet_census_id" value="0"/> 
</form:form>

<!-- /////////// Known_Allergy_Form //////////////// -->
<c:url value="Known_Allergy_HistoryUrl" var="Known_Allergy_HistoryUrl" />
<form:form action="${Known_Allergy_HistoryUrl}" method="post" id="known_allergyForm"
	name="known_allergyForm" target="result">
 <input type="hidden" name="ka_comm_id" id="ka_comm_id" value="0" />  
<input type="hidden" name="ka_census_id" id="ka_census_id" value="0" />
</form:form>

<!-- /////////// Change_In_Address_Form //////////////// -->
<c:url value="Change_In_AddressUrl" var="Change_In_AddressUrl" />
<form:form action="${Change_In_AddressUrl}" method="post" id="change_in_addressForm"
	name="change_in_addressForm" target="result">
 <input type="hidden" name="ca_comm_id" id="ca_comm_id" value="0" />  
<input type="hidden" name="ca_census_id" id="ca_census_id" value="0" />
</form:form>

<!-- /////////// Update_Battle_And_Physical_Casuality_Form //////////////// -->

<c:url value="Update_Battle_And_Physical_CasualityUrl" var="Update_Battle_And_Physical_CasualityUrl" />
<form:form action="${Update_Battle_And_Physical_CasualityUrl}" method="post" id="Update_Battle_And_Physical_Casuality_Form"
	name="Update_Battle_And_Physical_Casuality_Form" modelAttribute="id" target="result">
	<input type="hidden" name="bp_comm_id" id="bp_comm_id" value="0" />
	<input type="hidden" name="bp_census_id" id="bp_census_id" value="0" />
</form:form>

 <!-- /////////// Update_Discipline_Form //////////////// -->

<c:url value="Update_DisciplineUrl" var="Update_DisciplineUrl" />
<form:form action="${Update_DisciplineUrl}" method="post" id="Update_Discipline_Form"
	name="Update_Discipline_Form" modelAttribute="id" target="result">
	<input type="hidden" name="ud_comm_id" id="ud_comm_id" value="0" />
	<input type="hidden" name="ud_census_id" id="ud_census_id" value="0" />
</form:form>

 <!-- ///////////  Inter_Arm_Service_Transfer_Form //////////////// -->

<c:url value="Inter_Arm_Service_TransferUrl" var="Inter_Arm_Service_TransferUrl" />
<form:form action="${Inter_Arm_Service_TransferUrl}" method="post" id="Inter_Arm_Service_Transfer_Form"
	name="Inter_Arm_Service_Transfer_Form" modelAttribute="id" target="result">
	<input type="hidden" name="as_comm_id" id="as_comm_id" value="0" />
	<input type="hidden" name="as_census_id" id="as_census_id" value="0" />
</form:form>

 <!-- ///////////  Ssc_To_Permit_Commission_Form //////////////// -->

<c:url value="Ssc_To_Permit_CommissionUrl" var="Ssc_To_Permit_CommissionUrl" />
<form:form action="${Ssc_To_Permit_CommissionUrl}" method="post" id="Ssc_To_Permit_Commission_Form"
	name="Ssc_To_Permit_Commission_Form" modelAttribute="id" target="result">
	<input type="hidden" name="ssc_comm_id" id="ssc_comm_id" value="0" />
	<input type="hidden" name="ssc_census_id" id="ssc_census_id" value="0" />
</form:form>


<c:url value="Update_Firing_StandardUrl" var="Update_Firing_StandardUrl" />
<form:form action="${Update_Firing_StandardUrl}" method="post" id="Firing_Standard_Form" name="Firing_Standard_Form"  target="result">
<input type="hidden" name="fs_comm_id" id="fs_comm_id" value="0"/>
 <input type="hidden" name="fs_census_id" id="fs_census_id" value="0"/> 
</form:form>

<c:url value="Extension_of_SSCUrl" var="Extension_of_SSCUrl" />
<form:form action="${Extension_of_SSCUrl}" method="post" id="Extension_of_SSC_Form"
	name="Extension_of_SSC_Form" modelAttribute="id" target="result">
	<input type="hidden" name="eos_comm_id" id="eos_comm_id" value="0" />
	<input type="hidden" name="eos_census_id" id="eos_census_id" value="0" />
</form:form>

<c:url value="SecondmentUrl" var="SecondmentUrl" />
<form:form action="${SecondmentUrl}" method="post" id="Secondment_Form"
	name="Secondment_Form" modelAttribute="id" target="result">
	<input type="hidden" name="Secondment_comm_id" id="Secondment_comm_id" value="0" />
	<input type="hidden" name="Secondment_census_id" id="Secondment_census_id" value="0" />
</form:form>

<c:url value="DeserterUrl" var="DeserterUrl" />
<form:form action="${DeserterUrl}" method="post" id="Deserter_Form"
	name="Deserter_Form" modelAttribute="id" target="result">
	<input type="hidden" name="deserter_comm_id" id="deserter_comm_id" value="0" />
	<input type="hidden" name="deserter_census_id" id="deserter_census_id" value="0" />
</form:form>

<!-- /////////// Update_Award_Medal_Form //////////////// -->
<c:url value="Update_Awards_MedalUrl" var="Update_Awards_MedalUrl" />
<form:form action="${Update_Awards_MedalUrl}" method="post" id="update_awards_medalForm"
	name="update_awards_medalForm" target="result">
 <input type="hidden" name="am_comm_id" id="am_comm_id" value="0" />  
<input type="hidden" name="am_census_id" id="am_census_id" value="0" />
</form:form>


<!-- ///////////Canteen History //////////////// -->
<c:url value="Canteen_HistoryUrl" var="Canteen_HistoryUrl" />
<form:form action="${Canteen_HistoryUrl}" method="post" id="canteenForm"
	name="canteenForm" target="result">
 <input type="hidden" name="canteen_comm_id" id="canteen_comm_id" value="0" />  
<input type="hidden" name="canteen_census_id" id="canteen_census_id" value="0" />
</form:form>


	
<!-- ///////////////////////CHANGE IN MEDICLE DETAILS///////////// -->
<c:url value="Change_In_MedicleUrl" var="Change_In_MedicleUrl" />
<form:form action="${Change_In_MedicleUrl}" method="post" id="Change_In_Medicle_Form" name="Change_In_Medicle_Form"  target="result">
<input type="hidden" name="cim_comm_id" id="cim_comm_id" value="0"/>
 <input type="hidden" name="cim_census_id" id="cim_census_id" value="0"/> 
</form:form>
<!-- ///////////////////////TA STATUS///////////// -->
<c:url value="Change_In_TAUrl" var="Change_In_TAUrl" />
<form:form action="${Change_In_TAUrl}" method="post" id="Change_TA_Form"
	name="Change_TA_Form" modelAttribute="id" target="result">
	<input type="hidden" name="ta_comm_id" id="ta_comm_id" value="0" />
	<input type="hidden" name="ta_census_id" id="ta_census_id" value="0" />
</form:form>


<!-- Change  in tnaiNo -->

	<c:url value="Popup_TnaiNoUrl_3008" var="Popup_TnaiNoURL" />
		<form:form action="${Popup_TnaiNoURL}" method="post"
			id="popup_TnaiNo" name="popup_TnaiNo" modelAttribute="id"
			target="result">
			<input type="hidden" name="comm_idTnai" id="comm_idTnai" value="0" />
		</form:form>
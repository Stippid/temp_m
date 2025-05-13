<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<!-- bisag v2 190922(new screen) -->


<style type="text/css">
	.modal,.modalm {
	    display: none; 
	    position: fixed; 
	    z-index: 999999; 
	    padding-top: 10px; 
	    left: 0;
	    top: 0;
	    width: 100%; 
	    height: 100%; 
	    overflow: auto; 
	    background-color: rgb(0,0,0);
	    background-color: rgba(0,0,0,0.4); 
	}
	.modal-content1,.modal-content1m {
	    background-color: #fefefe;
	    margin: auto;
	    padding: 20px 20px 50px 20px;
	    border: 1px solid black;
	    border-radius: 5px;
	    width: 90%;
	    
	}
	/* The Close Button */
	.close,.closem {
	    color: maroon;
	    float: right;
	    font-size: 28px;
	    font-weight: bold;
	}
	
	.close:hover,
	.close:focus {
	    color: red;
	    text-decoration: none;
	    cursor: pointer;
	}
	
	.close1 {
	display:inline-block;padding:6px 12px;margin-bottom:0;font-size:14px;font-weight:400;line-height:1.42857143;text-align:center;
	cursor:pointer;border:1px solid #d43f3a;border-radius:4px; color:#fff;background-color:#d9534f;
	}
	
	.close1:hover,.close1:focus {
	    color:#333;text-decoration:none;
	    cursor: pointer;
	}
	
	.closem:hover,
	.closem:focus {
	    color: red;
	    text-decoration: none;
	    cursor: pointer;
	}
	
	.close1m {
	display:inline-block;padding:6px 12px;margin-bottom:0;font-size:14px;font-weight:400;line-height:1.42857143;text-align:center;
	cursor:pointer;border:1px solid #d43f3a;border-radius:4px; color:#fff;background-color:#d9534f;
	}
	
	.close1m:hover,.close1m:focus {
	    color:#333;text-decoration:none;
	    cursor: pointer;
	}
	.info-box{
	  cursor: pointer;
	}

.selectBox {
	position: relative;
}
element.style {
    width: 70%;
}


span.subspan {
	padding: 5px;
	background-color: #79cece54;
	border-radius: 20px;
	margin: 3px;
	display: block;
	width: fit-content;
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

</style>

		
				
				<div class="animated fadeIn">		
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>DATA UPDATION</h5></div>
	          			<div class="card-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
    						
				
									<label class=" form-control-label"> SUS No </label>
								</div>
								<div class="col-md-8">

									<input type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" >

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50"
										onkeyup="return specialcharecter(this)">

								</div>
							</div>
						</div>
					</div>
					


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd" name="cont_comd" class="form-control">
										${selectcomd}
										<c:forEach var="item" items="${getCommandList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class="form-control-label">Cont Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_corps" name="cont_corps" class="form-control">
										${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
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
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_div" name="cont_div" class="form-control">
										${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_bde" name="cont_bde" class="form-control">
										${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
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
               					<div class="col col-md-4">
                 					<label for="text-input"  >Role  </label> 
               					</div>
               				<div class="col-12 col-md-8">
                 					<select name="user_role" class="form-control" id ="user_role" onchange="getaccess_lev(this.value);">
                 						<option value="0">--Select--</option>
               							<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
               								<option value="${item.roleId}">${item.role}</option>
               							</c:forEach>					                
				                  	</select>
								</div> 
 							</div>
 						</div> 
 					</div>
					<%-- <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
    						
				
									<label class=" form-control-label"> Created By </label>
								</div>
								<div class="col-md-8">

									<select name="user_role_id" class="form-control" id ="user_role_id" onchange="getaccess_lev(this.value);">
                 						<option value="0">--Select--</option>
               							<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
               								<option value="${item.userId}">${item.userName}</option>
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
								<label class=" form-control-label">Created By</label>
							</div>
							
								<div class="col-md-8">

									<div class="selectBox" onclick="showCheckboxes()">
										<select name="sub_quali" id="sub_quali"
											class=" form-control">
											<option>Select UserName</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="user_role_id"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search UserName">
										</div>
										<div>
											<c:forEach var="item" items="${getRoleNameList_dash}" varStatus="num">
												<label for="one" class="quali_subjectlist"> <input
													type="checkbox" name="multisub" value="${item.userId}" />${item.userName}
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
									style="color: red;"></strong>Selected UserName</label>
							</div>
						<div class="col-12 col-md-8">
						 <div id="quali_sub_list"style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
                             </div>
            </div>
						</div>
						</div>
					</div>
					
					<div class="col-md-12">
  						 <div class="col-md-6">
	             		 <div class="row form-group">	
		                 <div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label">Date From </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			 <%--  <input type="date" name="from_date" id="from_date" value="${to_date}" class="form-control-sm form-control" min="1899-01-01" max="${date}" maxlength="10"> --%>
	             			  <input type="text" name="from_date" id="from_date" maxlength="10" value="${to_date}" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
	               		 </div>	
	               		 </div>
	               		 </div>
	               		   
	               		  <div class="col-md-6">
	             		 <div class="row form-group"> 
	               		  <div class="col-md-4">
	                 		  <label for="text-input" class="form-control-label">Date To </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			
	               		<input type="text" name="to_date" id="to_date" maxlength="10"  value="${date}" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}"  >
	       </div>
						            </div>
	              				</div>	              				
	              			</div>              			
						</div>
<div class="card-footer" align="center">
		<a href="psgDashboard" class="btn btn-success btn-sm" >Clear</a>  
	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search" /> 
<!-- 	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: red; text-align: right;"aria-hidden="true" title="EXPORT TO EXCEL"onclick="getExcelmain();"></i> -->
			</div>			      
					</div>
	        	</div>
				</div>
					 	<!-- <div class="col-md-12"><span ><B>OFFICER</B></span></div>					 -->
							<div class="col-md-12">	
								<div class="col-md-2" >
									<div class="info-box bg-darkgreen">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">OFFICER</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
												<label id="totalwe">${total}</label>
												</a>
											</div>
										</div>
									</div>
									&nbsp; &nbsp; &nbsp; &nbsp;	&nbsp;	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcelmain11();"></i>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 	<i class="fa fa-file-excel-o" id="btnExport1" style="font-size: x-large; color: red; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcelmain12();"></i>
									
								</div>
								<div class="col-md-2" >
									<div class="info-box bg-deep-purple">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">JCO</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
												<label id="totalpe">${total2}</label>
												</a>
											</div>
										</div>
									</div>
									&nbsp; &nbsp; &nbsp; &nbsp;	&nbsp;	<i class="fa fa-file-excel-o" id="btnExport2" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcelmain13();"></i>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 	<i class="fa fa-file-excel-o" id="btnExport3" style="font-size: x-large; color: red; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcelmain14();"></i>
									
								</div>
								<div class="col-md-2"  >
									<div class="info-box bg-darkyellow">
										<div class="content">
											<h5 style="font-weight: bold; font-size: 16px;">CIVILIAN</h5>
											<%-- <label id="totalactiveUnits" style="float: right; font-size: 18px;"><b>${getActiveUnits[0].total}</b></label> --%>
											<div class="number count-to">
												<a>
												<label id="totalfe">${total3}</label>
												</a>
											</div>
										</div>
									</div>
								
						
									&nbsp; &nbsp; &nbsp; &nbsp;	&nbsp;	<i class="fa fa-file-excel-o" id="btnExport4" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcelmain15();"></i>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 	<i class="fa fa-file-excel-o" id="btnExport5" style="font-size: x-large; color: red; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcelmain16();"></i>
</div>
							</div>						
				<div class="datatablediv">
			<div class="col-md-12" id="getSearch_held_a" style="display: block;">
			<div class="card-header">
				<h5>OFFICER COMMISSION</h5>
			</div>
		</div>	
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_a"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Command Name</th>
					<th style="font-size: 15px; text-align: center">Corps Name</th>
					<th style="font-size: 15px; text-align: center">Division name</th>
					<th style="font-size: 15px; text-align: center">Brigade  Name</th>
				    <th style="font-size: 15px; text-align: center">Unit Name</th>
				    <th style="font-size: 15px; text-align: center">Approved</th>
				    <th style="font-size: 15px; text-align: center">Pending</th>
					  <th style="font-size: 15px; text-align: center">TOTAL</th>

						</tr>
					</thead>

				</table>
				  	<i class="fa fa-file-excel-o" id="btnExport4" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcelmain7();"></i>
			</div>
			
<!-- 			////////////////////////////////////////////////////////////////of///////////////////////////////////////// -->
			
			
			<div class="datatablediv">
			<div class="col-md-12" id="getSearch_held_a" style="display: block;">
			<div class="card-header">
				<h5>OFFICER CENSUS</h5>
			</div>
		</div>	
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_cen"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Command Name</th>
					<th style="font-size: 15px; text-align: center">Corps Name</th>
					<th style="font-size: 15px; text-align: center">Division name</th>
					<th style="font-size: 15px; text-align: center">Brigade  Name</th>
				    <th style="font-size: 15px; text-align: center">Unit Name</th>
				    <th style="font-size: 15px; text-align: center">Approved</th>
				    <th style="font-size: 15px; text-align: center">Pending</th>
					  <th style="font-size: 15px; text-align: center">TOTAL</th>

						</tr>
					</thead>

				</table>
			</div>
			
			
<!-- 			////////////////////////////////////////////////////////////////end of//////////////////////////////////// -->
		<div class="col-md-12" ><span>
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcel1();"></i>
	</span></div>
			
   <div class="col-md-12" id="getSearch_held_b" style="display: block;">
			<div class="card-header">
				<h5>JCO</h5>
			</div>
		</div>
	<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_b"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Command Name</th>
					<th style="font-size: 15px; text-align: center">Corps Name</th>
					<th style="font-size: 15px; text-align: center">Division name</th>
					<th style="font-size: 15px; text-align: center">Brigade  Name</th>
				    <th style="font-size: 15px; text-align: center">Unit Name</th>
				    <th style="font-size: 15px; text-align: center">Approved</th>
				    <th style="font-size: 15px; text-align: center">Pending</th>
					  <th style="font-size: 15px; text-align: center">TOTAL</th>

						</tr>
					</thead>

				</table>
			</div>
		
<div class="col-md-12" ><span>
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcel2();"></i>
	</span></div>
			

		<div class="col-md-12" id="getSearch_held_c" style="display: block;">
			<div class="card-header">
				<h5>CIVILIAN</h5>
			</div>
		</div>
	

		<div class="col-md-12" id="getSearch_heldstr_c"
			style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_c"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
					<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Command Name</th>
					<th style="font-size: 15px; text-align: center">Corps Name</th>
					<th style="font-size: 15px; text-align: center">Division name</th>
					<th style="font-size: 15px; text-align: center">Brigade  Name</th>
				    <th style="font-size: 15px; text-align: center">Unit Name</th>
				    <th style="font-size: 15px; text-align: center">Approved</th>
				    <th style="font-size: 15px; text-align: center">Pending</th>
					  <th style="font-size: 15px; text-align: center">TOTAL</th>

						</tr>
					</thead>

				</table>
		
		
<div class="col-md-12" ><span>
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL"  onclick="getExcel3();"></i>
	</span></div>
			</div>

	

	

	
		</div>
	</div>
   


	
<style>
.amcharts-pie-slice {
  transform: scale(1);
  transform-origin: 40% 50%;
  transition-duration: 0.3s;
  transition: all .3s ease-out;
  -webkit-transition: all .3s ease-out;
  -moz-transition: all .3s ease-out;
  -o-transition: all .3s ease-out;
  cursor: pointer;
  box-shadow: 0 0 30px 0 #000;
}

.amcharts-pie-slice:hover {
  transform: scale(1.07);
  filter: url(#shadow);
}	

.amcharts-legend-div {
  overflow-y: auto!important;
  max-height: 400px;
  overflow-x: auto!important;
  max-width: 400px;
}	

.line {
    width: 100%;
    display: block;
    /* margin-top: 1rem; */
    margin-bottom: 1rem;
    border: 0;
    border-top-color: currentcolor;
    border-top-style: none;
    border-top-width: 0px;
    border-top: 2px solid #f0eaea; /* 1px solid #eee; */
}

</style>

	 <input type="hidden" name="quali_sub_listhid" id="quali_sub_listhid" />

<c:url value="Excel_data_updated" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	 <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	  <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0">
	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0">
	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0">
	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0">
	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" >
	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt">
	   <input type="hidden" name="cont_div_txt" id="cont_div_txt">
	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt">
	   <input type="hidden" name="unit_name_ex" id="unit_name_ex">
	   <input type="hidden" name="sus_no_ex" id="sus_no_ex">
</form:form> 

<!-- ///////////////////////////////////////OFFICER CENSUS//////////////////////////////////// -->
<c:url value="Excel_data_updatedk" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelFormk" name="ExcelFormk" modelAttribute="typeReport1">
	 <input type="hidden" name="typeReportk" id="typeReportk" value="0" />
	  <input type="hidden" name="cont_comd_exk" id="cont_comd_exk"  value="0">
	   <input type="hidden" name="cont_corps_exk" id="cont_corps_exk" value="0">
	   <input type="hidden" name="cont_div_exk" id="cont_div_exk" value="0">
	   <input type="hidden" name="cont_bde_exk" id="cont_bde_exk" value="0">
	   <input type="hidden" name="cont_comd_txtk" id="cont_comd_txtk" >
	   <input type="hidden" name="cont_corps_txtk" id="cont_corps_txtk">
	   <input type="hidden" name="cont_div_txtk" id="cont_div_txtk">
	   <input type="hidden" name="cont_bde_txtk" id="cont_bde_txtk">
	   <input type="hidden" name="unit_name_exk" id="unit_name_exk">
	   <input type="hidden" name="sus_no_exk" id="sus_no_exk">
</form:form> 

<!-- ///////////////////////////////////////END//////////////////////////////////// -->

<c:url value="Excel_data_updated2" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm1" name="ExcelForm1" modelAttribute="typeReport2">
	 <input type="hidden" name="typeReport2" id="typeReport2" value="0" />
	  <input type="hidden" name="cont_comd_ex1" id="cont_comd_ex1"  value="0">
	   <input type="hidden" name="cont_corps_ex1" id="cont_corps_ex1" value="0">
	   <input type="hidden" name="cont_div_ex1" id="cont_div_ex1" value="0">
	   <input type="hidden" name="cont_bde_ex1" id="cont_bde_ex1" value="0">
	   <input type="hidden" name="cont_comd_txt1" id="cont_comd_txt1" >
	   <input type="hidden" name="cont_corps_txt1" id="cont_corps_txt1">
	   <input type="hidden" name="cont_div_txt1" id="cont_div_txt1">
	   <input type="hidden" name="cont_bde_txt1" id="cont_bde_txt1">
	   <input type="hidden" name="unit_name_ex1" id="unit_name_ex1">
	   <input type="hidden" name="sus_no_ex1" id="sus_no_ex1">
</form:form> 
<c:url value="Excel_data_updated3" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm2" name="ExcelForm2" modelAttribute="typeReport3">
	 <input type="hidden" name="typeReport3" id="typeReport3" value="0" />
	  <input type="hidden" name="cont_comd_ex2" id="cont_comd_ex2"  value="0">
	   <input type="hidden" name="cont_corps_ex2" id="cont_corps_ex2" value="0">
	   <input type="hidden" name="cont_div_ex2" id="cont_div_ex2" value="0">
	   <input type="hidden" name="cont_bde_ex2" id="cont_bde_ex2" value="0">
	   <input type="hidden" name="cont_comd_txt2" id="cont_comd_txt2" >
	   <input type="hidden" name="cont_corps_txt2" id="cont_corps_txt2">
	   <input type="hidden" name="cont_div_txt2" id="cont_div_txt2">
	   <input type="hidden" name="cont_bde_txt2" id="cont_bde_txt2">
	   <input type="hidden" name="unit_name_ex2" id="unit_name_ex2">
	   <input type="hidden" name="sus_no_ex2" id="sus_no_ex2">
</form:form> 
<%-- <c:url value="Excel_Record_Service_querymain" var="excelUrlmain" /> --%>
<%-- <form:form action="${excelUrlmain}" method="post" id="ExcelFormmain" --%>
<%-- 	name="ExcelFormmain" modelAttribute="typeReportmain"> --%>
<!-- 	<input type="hidden" name="typeReportmain" id="typeReportmain" -->
<!-- 		value="0" /> -->
<%-- </form:form> --%>

<c:url value="Excel_data_updated7" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm7" name="ExcelForm7" modelAttribute="typeReport7">
	 <input type="hidden" name="typeReport7" id="typeReport7" value="0" />
	  <input type="hidden" name="cont_comd_ex7" id="cont_comd_ex7"  value="0">
	   <input type="hidden" name="cont_corps_ex7" id="cont_corps_ex7" value="0">
	   <input type="hidden" name="cont_div_ex7" id="cont_div_ex7" value="0">
	   <input type="hidden" name="cont_bde_ex7" id="cont_bde_ex7" value="0">
	   <input type="hidden" name="cont_comd_txt7" id="cont_comd_txt7" >
	   <input type="hidden" name="cont_corps_txt7" id="cont_corps_txt7">
	   <input type="hidden" name="cont_div_txt7" id="cont_div_txt7">
	   <input type="hidden" name="cont_bde_txt7" id="cont_bde_txt7">
	   <input type="hidden" name="unit_name_ex7" id="unit_name_ex7">
	   <input type="hidden" name="sus_no_ex7" id="sus_no_ex7">
</form:form>
<c:url value="Excel_Record_Service_querymain1" var="excelUrlmain1" />
<form:form action="${excelUrlmain1}" method="post" id="ExcelFormmain1" name="ExcelFormmain1" modelAttribute="typeReportmain11">
	   <input type="hidden" name="typeReportmain11" id="typeReportmain11" value="0" />
</form:form> 

<c:url value="Excel_Record_Service_querymain2" var="excelUrlmain2" />
<form:form action="${excelUrlmain2}" method="post" id="ExcelFormmain2" name="ExcelFormmain2" modelAttribute="typeReportmain12">
	   <input type="hidden" name="typeReportmain12" id="typeReportmain12" value="0" />
</form:form> 

<c:url value="Excel_Record_Service_querymain3" var="excelUrlmain3" />
<form:form action="${excelUrlmain3}" method="post" id="ExcelFormmain3" name="ExcelFormmain3" modelAttribute="typeReportmain13">
	   <input type="hidden" name="typeReportmain" id="typeReportmain13" value="0" />
</form:form> 

<c:url value="Excel_Record_Service_querymain4" var="excelUrlmain4" />
<form:form action="${excelUrlmain4}" method="post" id="ExcelFormmain4" name="ExcelFormmain4" modelAttribute="typeReportmain14">
	   <input type="hidden" name="typeReportmain" id="typeReportmain14" value="0" />
</form:form> 

<c:url value="Excel_Record_Service_querymain5" var="excelUrlmain5" />
<form:form action="${excelUrlmain5}" method="post" id="ExcelFormmain5" name="ExcelFormmain5" modelAttribute="typeReportmain15">
	   <input type="hidden" name="typeReportmain" id="typeReportmain15" value="0" />
</form:form> 

<c:url value="Excel_Record_Service_querymain6" var="excelUrlmain6" />
<form:form action="${excelUrlmain6}" method="post" id="ExcelFormmain6" name="ExcelFormmain6" modelAttribute="typeReportmain16">
	   <input type="hidden" name="typeReportmain" id="typeReportmain16" value="0" />
</form:form> 

<script type="text/javascript">
$(document).ready(function() {
	
	 

	$.ajaxSetup({
		async : false
	});
	
	$("#report_all").hide();

	if ('${month1}' != 0) {
		$("#month").val('${month1}');
		// $("#report_all").show() ;	
	} else {
		var d = new Date();
		var month = d.getMonth() + 1;
		$("#month").val(month);
	}

	if ('${year1}' != '') {
		$("#year").val('${year1}');
		//  $("#report_all").show() ;	
	} else {
		var d = new Date();
		var year = d.getFullYear();
		$("#year").val(year);
	}

		

	if ('${roleAccess}' == 'MISO') {
		
		if ('${month1}' != 0) {
			$("#month").val('${month1}');
			$("#report_all").show();
		}
		if ('${year1}' != '') {
			$("#year").val('${year1}');
			$("#report_all").show();
		}
	
	}
	if ('${roleAccess}' == 'Unit') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);
		$("#unit_sus_no").attr("disabled", true);
		$("#unit_name").attr("disabled", true);

		$("#unit_sus_no").val('${sus_no}');
		$("#unit_name").val('${unit_name}');

	
		if ('${cmd_sus}' != "") {
			
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
			$("#report_all").show();
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}
		if ('${bde_sus}' != "") {
			$("#hd_bde_sus").val('${bde_sus}');
		}
	}
	if ('${roleSubAccess}' == 'Brigade') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);

		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}
		if ('${bde_sus}' != "") {
			$("#hd_bde_sus").val('${bde_sus}');
		}

	}
	if ('${roleSubAccess}' == 'Division') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}

	}
	if ('${roleSubAccess}' == 'Corps') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);

		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}

	}
	if ('${roleSubAccess}' == 'Command') {
		$("#cont_comd").attr("disabled", true);
		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
	}
	
	
	if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter')
	{
		if('${cont_comd1}' != ""){
	    	$("#cont_comd").val('${cont_comd1}');
	    	getCONTCorps('${cont_comd1}');
    	}
    	if('${cont_corps1}' != ""){
    		 getCONTDiv('${cont_corps1}');
    	}
	    if('${cont_div1}' != ""){
	    	getCONTBde('${cont_div1}');
	    }
	}

	var select = '<option value="' + "0" + '">'
			+ "--Select--" + '</option>';
	$('select#cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorps(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_corps').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_corp_sus").val(fcode);
			getCONTDiv(fcode);
			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_div').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {

			$("select#cont_bde").html(select);
			$("#hd_div_sus").val(fcode);
			getCONTBde(fcode);
		}
	});

	$('select#cont_bde').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {
			$("#hd_bde_sus").val(fcode);
		}
	});

	if ('${sus_no}' != "") {
		$("#unit_sus_no").val('${sus_no}');

		$.post("getActiveUnitNameFromSusNo?" + key + "="
				+ value, {
			sus_no : '${sus_no}'
		}, function(j) {
			var length = j.length - 1;
			var enc = j[length].substring(0, 16);
			$("#unit_name").val(dec(enc, j[0]));
			//getOrbatDetailsFromUnitName(dec(enc,j[0]))
			//var a = dec(enc,j[0]);
			//getCommand(a);	
		});

		$("#unit_sus_no").attr("disabled", true);
		$("#unit_name").attr("disabled", true);
	}

/* 	$('#unit_sus_no').change(function(){
		var y = this.value;
		getCommand(y);
    }); */
	/* $('#unit_name').change(function(){
		var y = this.value;
		alert("fdfd----"+y);
		$.post("getTargetSUSFromUNITNAME?"+key+"="+value,{y:y},function(j) {
			alert("fdfd-gfghf---"+j);
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);		
		});
		
    }); */ 
	if ('${cmd_sus}' != "" && '${cmd_sus}' != "0") {
		$("#cont_comd").val('${cmd_sus}');
		$("#hd_cmd_sus").val('${cmd_sus}');
		$("#cont_comd").attr("disabled", true);
		getCONTCorps('${cmd_sus}');
	}
	if ('${corp_sus}' != "" && '${corp_sus}' != "0") {
		$("#cont_corps").val('${corp_sus}');
		$("#hd_corp_sus").val('${corp_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		getCONTDiv('${corp_sus}');
	}
	if ('${div_sus}' != "" && '${div_sus}' != "0") {
		$("#cont_div").val('${div_sus}');
		$("#hd_div_sus").val('${div_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		getCONTBde('${div_sus}');
	}
	if ('${bde_sus}' != "" && '${bde_sus}' != "0") {
		$("#cont_bde").val('${bde_sus}');
		$("#hd_bde_sus").val('${bde_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);
	}

	$("#unit_sus_no")
			.keyup(
					function() {
						var sus_no = this.value;
						var susNoAuto = $("#unit_sus_no");

						susNoAuto
								.autocomplete({
									source : function(
											request,
											response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetSUSNoList?"
															+ key
															+ "="
															+ value,
													data : {
														sus_no : sus_no
													},
													success : function(
															data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(
																		0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval
																	.push(dec(
																			enc,
																			data[i]));
														}
														var dataCountry1 = susval
																.join("|");
														var myResponse = [];
														var autoTextVal = susNoAuto
																.val();
														$
																.each(
																		dataCountry1
																				.toString()
																				.split(
																						"|"),
																		function(
																				i,
																				e) {
																			var newE = e
																					.substring(
																							0,
																							autoTextVal.length);
																			if (e
																					.toLowerCase()
																					.includes(
																							autoTextVal
																									.toLowerCase())) {
																				myResponse
																						.push(e);
																			}
																		});
														response(myResponse);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(
											event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit SUS No.");
											document
													.getElementById("unit_name").value = "";
											susNoAuto
													.val("");
											susNoAuto
													.focus();
											return false;
										}
									},
									select : function(
											event, ui) {
										var sus_no = ui.item.value;
										$
												.post(
														"getTargetUnitNameList?"
																+ key
																+ "="
																+ value,
														{
															sus_no : sus_no
														},
														function(
																j) {

														})
												.done(
														function(
																j) {
															var length = j.length - 1;
															var enc = j[length]
																	.substring(
																			0,
																			16);
															$("#unit_name").val(dec(enc,j[0]));
															getCommand(sus_no);

														})
												.fail(
														function(
																xhr,
																textStatus,
																errorThrown) {
														});
									}
								});
					});

	// unit name
	$("input#unit_name")
			.keypress(
					function() {
						var unit_name = this.value;
						var susNoAuto = $("#unit_name");
						susNoAuto
								.autocomplete({
									source : function(
											request,
											response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetUnitsNameActiveList?"
															+ key
															+ "="
															+ value,
													data : {
														unit_name : unit_name
													},
													success : function(
															data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(
																		0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval
																	.push(dec(
																			enc,
																			data[i]));
														}

														response(susval);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(
											event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit Name.");
											document
													.getElementById("unit_name").value = "";
											susNoAuto
													.val("");
											susNoAuto
													.focus();
											return false;
										}
									},
									select : function(
											event, ui) {
										var unit_name = ui.item.value;

										$
												.post(
														"getTargetSUSFromUNITNAME?"
																+ key
																+ "="
																+ value,
														{
															target_unit_name : unit_name
														},
														function(
																data) {
														})
												.done(
														function(
																data) {
															var length = data.length - 1;
															var enc = data[length].substring(0,16);
															$("#unit_sus_no").val(dec(enc,data[0]));
															var a = dec(enc,data[0]);
														getCommand(a);		
														})
												.fail(
														function(
																xhr,
																textStatus,
																errorThrown) {
														});
									}
								});
					});
	
	
	mockjax1('getheldstr_a');
	table = dataTable11('getheldstr_a');
	mockjax1('getheldstr_cen');
	table4 = dataTable11('getheldstr_cen');
	mockjax1('getheldstr_b');
	table2 = dataTable11('getheldstr_b');
	mockjax1('getheldstr_c');
	table3 = dataTable11('getheldstr_c');
	
	
	
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    	table2.ajax.reload();
    	table3.ajax.reload();
    	table4.ajax.reload();
    	
    });

	});

/* ////bisag v2 200922(converted in data table ) */
function data(tableName) {
	jsondata = [];
	var table = $('#' + tableName).DataTable();
	var info = table.page.info();
	
	var currentPage = info.page;
	
	var pageLength = -1;
    var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];
	
	var cont_comd = $("#cont_comd").val();
	var cont_corps = $("#cont_corps").val();
	var cont_div = $("#cont_div").val();
	var cont_bde = $("#cont_bde").val();
	var unit_name = $("#unit_name").val();
	var unit_sus_no = $("#unit_sus_no").val();
	//var user_role_id = $("#user_role_id").val();
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
	var user_role = $("#user_role").val();
	var sub_quali = $("#quali_sub_listhid").val();

	if (tableName == "getheldstr_a") {
	    $.post("search_officer_en_tablecount?" + key + "=" + value,{Search:Search,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
			//user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali
		 },  function(j) {
			FilteredRecords = j;
		});
		$.post("search_officer_en_table?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
			//user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali
		}, function(j) {

			var offf_ttl=0;
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
				offf_ttl = offf_ttl+j[i].total;
				jsondata.push([ sr, j[i].cmd_name, j[i].coprs_name, j[i].div_name,
					j[i].bde_name, j[i].unit_name, j[i].approved, j[i].pending,
					j[i].total]);

			}
// 			
			document.getElementById("totalwe").innerHTML=offf_ttl;
		});

	}

	else if (tableName == "getheldstr_cen") {
	    $.post("search_officer_en_tablecountcen?" + key + "=" + value,{Search:Search,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
			//user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali
		 },  function(j) {
			FilteredRecords = j;
		});
		$.post("search_officer_en_tablecen?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
			//user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali
		}, function(j) {

		
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
				
				jsondata.push([ sr, j[i].cmd_name, j[i].coprs_name, j[i].div_name,
					j[i].bde_name, j[i].unit_name, j[i].approved, j[i].pending,
					j[i].total]);

			}
// 			
			
		});

	}
	
	
	
else	if (tableName == "getheldstr_b") {
		
	

		$.post("search_jco_en_tablecount?" + key + "=" + value,{Search:Search,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
		//	user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali}, function(j) {
			FilteredRecords = j;
		});
		$.post("search_jco_en_table?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			orderType : orderType,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
			//user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali
		}, function(j) {
			var offf_ttl=0;
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
				offf_ttl = offf_ttl+j[i].total;
				jsondata.push([ sr, j[i].cmd_name, j[i].coprs_name, j[i].div_name,
					j[i].bde_name, j[i].unit_name, j[i].approved, j[i].pending,
					j[i].total]);

			}
			document.getElementById("totalpe").innerHTML=offf_ttl;
		});
	}

	else	if (tableName == "getheldstr_c") {
		
		$.post("search_civilian_en_tablecount?" + key + "=" + value,{Search:Search,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
			//user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali},function(j) {
			FilteredRecords = j;
		});
		$.post("search_civilian_en_table?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search:Search,
			orderColunm : orderColunm,
			orderType : orderType,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			unit_name : unit_name,
			unit_sus_no : unit_sus_no,
			//user_role_id : user_role_id,
			from_date : from_date,
			to_date : to_date,
			user_role:user_role,
			sub_quali:sub_quali
			
		}, function(j) {
			var offf_ttl=0;
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
				offf_ttl = offf_ttl+j[i].total;
				jsondata.push([ sr, j[i].cmd_name, j[i].coprs_name, j[i].div_name,
						j[i].bde_name, j[i].unit_name, j[i].approved, j[i].pending,
						j[i].total]);

			}
			document.getElementById("totalfe").innerHTML=offf_ttl;
		});
	}


}
function getCONTCorps(fcode) {
	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';

			for (var i = 0; i < length; i++) {
				if ('${corp_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_corps").html(options);
		}
	});
}
function getCONTDiv(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	$.post("getDivDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < length; i++) {
				if ('${div_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_div").html(options);
		}
	});
}
function getCONTBde(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
			+ fcode[5];
	$.post("getBdeDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			jQuery("select#cont_bde").html(options);
			for (var i = 0; i < length; i++) {
				if ('${bde_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected>' + dec(enc, j[i][1])
							+ '</option>';
					$("#cont_bname").val(dec(enc, j[i][1]));
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1]) + '">'
							+ dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_bde").html(options);
		}
	});
}
function getExcel1() {	
	
	var cont_comd_txt = $("#cont_comd option:selected").text();
 	var cont_corps_txt=$("#cont_corps option:selected").text();
 	var cont_div_txt=$("#cont_div option:selected").text();
 	var cont_bde_txt=$("#cont_bde option:selected").text();
 	
 	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
 	
	
 	if(cont_comd_txt == "--Select--") {
		cont_comd_txt = "";
	}
	if(cont_corps_txt == "--Select--") {
		cont_corps_txt = "";		
	}
	if(cont_div_txt == "--Select--") {
		cont_div_txt = "";
	}
	if(cont_bde_txt == "--Select--") {
		cont_bde_txt = "";
	}
	
 	$("#cont_comd_txt").val(cont_comd_txt);
	$("#cont_corps_txt").val(cont_corps_txt);
	$("#cont_div_txt").val(cont_div_txt);
	$("#cont_bde_txt").val(cont_bde_txt);
	
	
	$("#cont_comd_ex").val(cont_comd);
	$("#cont_corps_ex").val(cont_corps);
	$("#cont_div_ex").val(cont_div);
	$("#cont_bde_ex").val(cont_bde);
	$("#unit_name_ex").val(unit_name);
	$("#sus_no_ex").val(sus_no);
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}
function getExcel2() {	
	var cont_comd_txt1 = $("#cont_comd option:selected").text();
 	var cont_corps_txt1=$("#cont_corps option:selected").text();
 	var cont_div_txt1=$("#cont_div option:selected").text();
 	var cont_bde_txt1=$("#cont_bde option:selected").text();
 	
 	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
 	
	
 	if(cont_comd_txt1 == "--Select--") {
		cont_comd_txt1 = "";
	}
	if(cont_corps_txt1 == "--Select--") {
		cont_corps_txt1 = "";		
	}
	if(cont_div_txt1 == "--Select--") {
		cont_div_txt1 = "";
	}
	if(cont_bde_txt1 == "--Select--") {
		cont_bde_txt1 = "";
	}
	
 	$("#cont_comd_txt1").val(cont_comd_txt1);
	$("#cont_corps_txt1").val(cont_corps_txt1);
	$("#cont_div_txt1").val(cont_div_txt1);
	$("#cont_bde_txt1").val(cont_bde_txt1);
	
	
	$("#cont_comd_ex1").val(cont_comd);
	$("#cont_corps_ex1").val(cont_corps);
	$("#cont_div_ex1").val(cont_div);
	$("#cont_bde_ex1").val(cont_bde);
	$("#unit_name_ex1").val(unit_name);
	$("#sus_no_ex1").val(sus_no);
	
	document.getElementById('typeReport2').value = 'excelL';
	document.getElementById('ExcelForm1').submit();
}
function getExcel3() {	
	var cont_comd_txt2 = $("#cont_comd option:selected").text();
 	var cont_corps_txt2=$("#cont_corps option:selected").text();
 	var cont_div_txt2=$("#cont_div option:selected").text();
 	var cont_bde_txt2=$("#cont_bde option:selected").text();
 	
 	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
 	
	
 	if(cont_comd_txt2 == "--Select--") {
		cont_comd_txt2 = "";
	}
	if(cont_corps_txt2 == "--Select--") {
		cont_corps_txt2 = "";		
	}
	if(cont_div_txt2 == "--Select--") {
		cont_div_txt2 = "";
	}
	if(cont_bde_txt2 == "--Select--") {
		cont_bde_txt2 = "";
	}
	
 	$("#cont_comd_txt2").val(cont_comd_txt2);
	$("#cont_corps_txt2").val(cont_corps_txt2);
	$("#cont_div_txt2").val(cont_div_txt2);
	$("#cont_bde_txt2").val(cont_bde_txt2);
	
	
	$("#cont_comd_ex2").val(cont_comd);
	$("#cont_corps_ex2").val(cont_corps);
	$("#cont_div_ex2").val(cont_div);
	$("#cont_bde_ex2").val(cont_bde);
	$("#unit_name_ex2").val(unit_name);
	$("#sus_no_ex2").val(sus_no);
	
	document.getElementById('typeReport3').value = 'excelL';
	document.getElementById('ExcelForm2').submit();
}

jQuery(function($){ 
	// datepicketDate('date_of_commission');
	 datepicketDate('from_date');
	 datepicketDate('to_date');
});
function showCheckboxes() {
	var checkboxes = document.getElementById("checkboxes");
	$("#checkboxes").toggle();
	$("#user_role_id").val('');
	$('.quali_subjectlist').each(function() {
		$(this).show()
	})
}
$("input[type='checkbox'][name='multisub']").click(function() {
	
	var num = 0;
	 arr1 = [];
	$('#quali_sub_list').empty()
	$("input[type='checkbox'][name='multisub']").each(function() {
		if(this.checked) {
			$('#quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
			arr1.push(this.parentElement.innerText)
			$("input#quali_sub_listhid").val(arr1);
			num = num + 1;
		}
	});
	
	
	
	if(num != 0) $("#sub_quali option:first").text('UserName(' + num + ')');
	else $("#sub_quali option:first").text("Select UserName");
});

$("#user_role_id").keyup(function() {
	var re = new RegExp($(this).val(), "i")
	var search = $(this).val();
	var searchL = search.length;
	$('.quali_subjectlist').each(function() {
		var text = $(this).text(),
			matches = !!text.match(re);
		if (text.trim().substring(0,searchL).toUpperCase() == search.trim().toUpperCase()) {
			$(this).toggle(true);
		}
		else{
			$(this).toggle(false);
		}
	})
});


var access_lvl,sub_access_lvl,role_id;

function getaccess_lev(val) {
		$("#user_sus_no").val("");
		document.getElementById('sub_access_lve').value="";
		document.getElementById('formation_code').value=""; 
	 	role_id = val;
		<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
			if('${item.roleId}' == val){		
				$("#access_lve").val('${item.access_lvl}');
				$("#sub_access_lve").val('${item.sub_access_lvl}');
				access_lvl = '${item.access_lvl}';
				sub_access_lvl = '${item.sub_access_lvl}';
			}
		</c:forEach>
		abd();
	}
function removeSubFn(value) {
	$("input[type='checkbox'][name='multisub'][value='" + value + "']").attr('checked', false);
	var num = 0;
	$('#quali_sub_list').empty()
	$("input[type='checkbox'][name='multisub']").each(function() {
		if(this.checked) {
			$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
			num = num + 1;
		}
	});
	if(num != 0) $("#sub_quali option:first").text('UserName(' + num + ')');
	else $("#sub_quali option:first").text("Select UserName");
}

///BISAG V2 071122///(observation new screen)
/////////////////comd
function getCommand(y){
	//$("#cont_comd").attr('disabled',true);
	var FindWhat = "COMMAND";
	
	$.post("getpsg_comndSUS?"+key+"="+value,{FindWhat:FindWhat,a:y},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
			
		}
		var data=a[0].split(",");
		var datap;
		
// 		for(var i = 0; i < data.length-1; i++) {
			datap=data[0].split(":");
			$("#cont_comd").val(datap[0]);  
			getCONTCorps(datap[0]);
			$("#cont_corps").val(datap[1]);  
			getCONTDiv(datap[1]);
			$("#cont_div").val(datap[2]);  
			getCONTBde(datap[2]);
			$("#cont_bde").val(datap[3]);  
			
 		
// 		}	
	}); 
}
// function getExcelmain() {		
// 	document.getElementById('typeReportmain').value = 'excelL';
// 	document.getElementById('ExcelFormmain').submit();
// }


function getExcelmain11() {		
		document.getElementById('typeReportmain11').value = 'excelL';
		document.getElementById('ExcelFormmain1').submit();
	}
		function getExcelmain12() {		
			document.getElementById('typeReportmain12').value = 'excelL';
			document.getElementById('ExcelFormmain2').submit();
		}
			function getExcelmain13() {		
				document.getElementById('typeReportmain13').value = 'excelL';
				document.getElementById('ExcelFormmain3').submit();
			}
				function getExcelmain14() {		
					document.getElementById('typeReportmain14').value = 'excelL';
					document.getElementById('ExcelFormmain4').submit();
				}
					function getExcelmain15() {		
						document.getElementById('typeReportmain15').value = 'excelL';
						document.getElementById('ExcelFormmain5').submit();
					}
						function getExcelmain16() {		
							document.getElementById('typeReportmain16').value = 'excelL';
							document.getElementById('ExcelFormmain6').submit();
}
						
						
						function getExcelmain7() {	
							
							var cont_comd_txt = $("#cont_comd option:selected").text();
						 	var cont_corps_txt=$("#cont_corps option:selected").text();
						 	var cont_div_txt=$("#cont_div option:selected").text();
						 	var cont_bde_txt=$("#cont_bde option:selected").text();
						 	
						 	
							var cont_comd=$("#cont_comd").val();
						 	var cont_corps=$("#cont_corps").val();
						 	var cont_div=$("#cont_div").val();
						 	var cont_bde=$("#cont_bde").val();
						 	var unit_name=$("#unit_name").val();
						 	var sus_no=$("#sus_no").val();
						 	
							
						 	if(cont_comd_txt == "--Select--") {
								cont_comd_txt = "";
							}
							if(cont_corps_txt == "--Select--") {
								cont_corps_txt = "";		
							}
							if(cont_div_txt == "--Select--") {
								cont_div_txt = "";
							}
							if(cont_bde_txt == "--Select--") {
								cont_bde_txt = "";
							}
							
						 	$("#cont_comd_txt7").val(cont_comd_txt);
							$("#cont_corps_txt7").val(cont_corps_txt);
							$("#cont_div_txt7").val(cont_div_txt);
							$("#cont_bde_txt7").val(cont_bde_txt);
							
							
							$("#cont_comd_ex7").val(cont_comd);
							$("#cont_corps_ex7").val(cont_corps);
							$("#cont_div_ex7").val(cont_div);
							$("#cont_bde_ex7").val(cont_bde);
							$("#unit_name_ex7").val(unit_name);
							$("#sus_no_ex7").val(sus_no);
							
							document.getElementById('typeReport7').value = 'excelL';
							document.getElementById('ExcelForm7').submit();
						}						
		
						
									
						
						
</script>





















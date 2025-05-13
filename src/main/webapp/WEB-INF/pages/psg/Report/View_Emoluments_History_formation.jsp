<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

 <link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/scss/style.css"> 
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>



<form:form name="view_en" id="view_en" action="view_enAction" method="post" class="form-horizontal" commandName="">


<div class="" id="Add_LanguageUrl" style="display: block;">

<div class="card">
				<div class="card-header">
					<h5></h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">
                      <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                    <b><label id="personnel_no" name ="personnel_no" value=""></label></b>
<!-- 						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)" >  -->
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong> Rank</label>
						                </div>
						                <div class="col-md-8">
						                    <b><label id="rank" name ="rank" value=""></label></b>
<!-- 						                   <select name="rank" id="rank" class="form-control-sm form-control"   > -->
<!-- 												<option value="0">--Select--</option> -->
<%-- 												<c:forEach var="item" items="${getTypeofRankList}" varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 												</c:forEach> --%>
<!-- 											</select> -->
						                </div>
						            </div>
	              				</div>              				
	              			</div>
	              			
	              			<div class="col-md-12">
		          				<div class="col-md-6">
		          					<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Name </label>
										</div>
										<div class="col-12 col-md-8">	
										    <b><label id="name" name ="name" value=""></label></b>
<!-- 											<input type="text" id="name" name="name" maxlength="100" placeholder="Search Unit Name" class="form-control autocomplete" > -->
										</div>
									</div>
		          				</div>
		          			</div>
				          </div>
			        </div>
           <div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
                <table id="view_Emoluments_HistoryUrl" class="table no-margin table-striped  table-hover  table-bordered report_print" style="width:100%;">
		                 <thead style="text-align: center;">
		                        <tr>
									<th>Ser No</th>
                                    <th>Agency Name</th>
                                    <th>Benefits Name</th>
                                    <th id ="9">Amount Due(Rs)</th>
							       <th id ="9">Total Amount Release(Rs)</th>
							       <th id ="2">Reason For not Releasing</th>
							   <th id ="2">Update</th>
								 </tr>                                                        
		                  </thead> 
		                  <tbody >
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="9">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr style="text-align: center;">
										<td>${num.index+1}</td> 
										<td >${item[1]}</td>
										<td >${item[2]}</td>
										<td >${item[3]}</td>
										<td >${item[4]}</td>
										<td >${item[5]}</td>
										<td >${item[6]}</td>
										
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>

                              </div>
                          </div> 














			   
			   <div class="card-footer" align="center" class="form-control">
					<a href="SearchEmolumentsFormationOfficer_url" class="btn btn-success btn-sm">Back</a>
<!-- 					<input type="submit" class="btn btn-primary btn-sm" value="Approve"> -->
<!-- 					<input type="submit" class="btn btn-danger btn-sm" value="Reject"> -->
				</div>

			</form:form>   
<script>

$(document).ready(function() {	
	
    $.ajaxSetup({
       async : false
	}); 
	 
	$("#personnel_no").text(('${list[0][0]}'));
	$("#rank").text(('${list[0][7]}'));
	$("#name").text(('${list[0][8]}'));
	
		 colAdj("view_Emoluments_HistoryUrl");
	
		 
});

</script>			   
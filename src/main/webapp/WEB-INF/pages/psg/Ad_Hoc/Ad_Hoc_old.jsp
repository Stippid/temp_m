<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>AD HOC</h5> <h6 class="enter_by">
<!-- 	    		<span style="font-size:12px;color:red;">(To be entered by Unit)</span> -->
	    		</h6></div>
	          			<div class="card-body card-block">	
	              			<div class="col-md-12">	              					
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Type </label>
						                </div>
						                <div class="col-md-8">
						                   <select name="p_type" id="p_type" class="form-control-sm form-control" onchange="Type(this.value);"  >
												<option value="0">--Select--</option>
												<option value="rank">RANK</option>
												<option value="name">NAME</option>
												<option value="qualification">QUALIFICATION</option>
												<%-- <c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select> 
						                </div>
						            </div>
	              				</div>	    
	              				</div>
	              				
	              				<div id="div_rank" style="display: none;">
	              				<div class="col-md-12">
	              						<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"></strong> Rank Type </label>
						                </div>
						                <div class="col-md-8">
										          <select name="rk_type" id="rk_type" class="form-control"   >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getOFFTypeofRankList}" varStatus="num">
														<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select> 
						                </div>
						            </div>
	              				</div>	
	              				
	              						<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Rank</label>
						                </div>
						                <div class="col-md-8">
						                 <select name="rank_parameter" id="rank_parameter" class="form-control" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
														<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
									     </select>  
						                </div>
						            </div>
	              				</div>	
	              				</div>	 
	              			</div>
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="ad_hoc_Url" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
			            </div> 		
	        	</div>
			</div>
	</div>
	
	
		<div class="container" id="ad_hoc_div">
		<div class="">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="pay_level_search" class="table no-margin table-striped  table-hover  table-bordered report_print" width="100%">
					<thead style="text-align: center;">
						<tr>
							<th>Ser No</th>
							<th>Personal No</th>
							<th>Cadet No</th>
							<th>Name</th>
							<th>Rank</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td style="text-align: center;">${num.index+1}</td>
								<td>${item.personnel_no}</td>
								<td>${item.cadet_no}</td>
								<td>${item.per_name}</td>
								<td style="text-align: center;">${item.rank}</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;" colspan="3">Data Not Available</td>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
	</div>
	
	<c:url value="GetSearch_Pers" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="p_type1">
		<input type="hidden" name="p_type1" id="p_type1" />				
		<input type="hidden" name="rank_parameter1" id="rank_parameter1" />		
		<input type="hidden" name="rk_type1" id="rk_type1" />		
	</form:form>
	
	
	<Script>
	
	function Search(){
		
		$("#p_type1").val($("#p_type").val()) ;	
		$("#rk_type1").val($("#rk_type").val()) ;	
		$("#rank_parameter1").val($("#rank_parameter").val()) ;	
		document.getElementById('searchForm').submit();
	}
	
	function Type(a) {
		if(a == "rank"){
			$("#div_rank").show();
		}
		else{
			$("#div_rank").hide();
		}
	}
	
	
	$(document).ready(function() {
		
		
		 if('${p_type}' != ""){
			 
			 Type('${p_type}');
			 
			 $("#p_type").val('${p_type}') ;
			 
			 if('${p_type}' == "rank"){
				 
				 if('${rk_type}' != ""){
						$("#rk_type").val('${rk_type}') ;
					}
				 
				 if('${rank_parameter}' != ""){
						$("#rank_parameter").val('${rank_parameter}') ;
					}
			 }
				
			}
		 
		
		 
	});
	</Script>
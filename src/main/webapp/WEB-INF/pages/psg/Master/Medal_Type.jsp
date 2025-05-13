<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="Medal_Type1" id="Medal_Type1" action="Medal_TypeAction" method="post" class="form-horizontal" commandName="Medal_TypeCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE MEDAL </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> MEDAL TYPE </label>
						                </div>
								<div class="col-md-8">
									<select name="medal_type" id="medal_type" class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMedalTypeList1}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

						</div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> MEDAL NAME </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="medal_name" name="medal_name" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)"> 
						                   
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> MEDAL ABBRIVIATION </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="medal_abberivation" name="medal_abberivation" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
<!-- 												<option value="0">--Select--</option> -->
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
	             				<!-- <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Decoration </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="decoration" name="decoration" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div> -->	              				
	              			</div>
	              				              			              				              			
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Medal_TypeUrl" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validation();">
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">  
			            </div> 		
	        	</div>
			</div>
	</div>
	<div align="right" class="container">
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div><br>
	<div class="container" id="getmedal_type" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getmedal_type" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>
			                         <th style="text-align: center; width:15%;">Ser No</th>
			                         <th style="text-align: center;"> Medal Type </th>	
			                         <th style="text-align: center;"> Medal Name </th>	
			                         <th style="text-align: center;"> Medal Abberivation </th>			                         
			                         <th style="text-align: center;">Action</th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="5">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;text-align: center ;width: 15%;">${num.index+1}</td> 
										<td style="font-size: 15px;">${item[0]}</td>	
										<td style="font-size: 15px;">${item[1]}</td>	
										<td style="font-size: 15px">${item[2]}</td>									
										<td style="font-size: 15px;text-align: center ;width: 20%;">${item[3]} ${item[4]}</td> 
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div> 
</form:form>


<c:url value="GetSearch_Medal_Type" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="medal_type1">
		<input type="hidden" name="medal_type1" id="medal_type1" value="0"/>
		<input type="hidden" name="medal_name1" id="medal_name1" value="0"/>
		<input type="hidden" name="medal_abberivation1" id="medal_abberivation1" value="0"/>
		<input type="hidden" name="decoration1" id="decoration1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
	</form:form> 
	
<c:url value="Edit_Medal_TypeUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
</form:form>

<c:url value="Delete_Medal_Type" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>
<c:url value="MedalTypereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
				
<Script>
$(document).ready(function() {
	
	 if('${list.size()}' == ""){
		   $("div#getmedal_type ").hide();
		}	
	 if('${medal_type1}'!= ""){
			
			$("#medal_type").val('${medal_type1}') ;
		} 
	 if('${medal_name1}'!= ""){
			
			$("#medal_name").val('${medal_name1}') ;
		} 
	 if('${medal_abberivation1}'!= ""){
			
			$("#medal_abberivation").val('${medal_abberivation1}') ;
		}
	 if('${decoration1}'!= ""){
			
			$("#decoration").val('${decoration1}') ;
		}
		
		 if('${status1}' != ""){
			 
			 	$("Select#status").val('${status1}') ;
				}
		
});
function getExcel() {
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
} 
function Search(){
	
	$("#medal_type1").val($("#medal_type").val()) ;	
	$("#medal_name1").val($("#medal_name").val()) ;	
	$("#medal_abberivation1").val($("#medal_abberivation").val()) ;	
	$("#decoration1").val($("#decoration").val()) ;	
	$("#status1").val($("#status").val()) ;	
	
	document.getElementById('searchForm').submit();

}

function editData(id){	
	$("#id2").val(id);
	document.getElementById('updateForm').submit();
		 
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}  
	
</Script>

<script>
function Validation(){
	
	  if ($("select#medal_type").val() == 0) {
			alert("Please Select Medal Type");
			$("select#medal_type").focus();
			return false;
		}  
	  if ($("input#medal_name").val().trim() == "") {
			alert("Please Enter Medal Name");
			$("input#medal_name").focus();
			return false;
		}  
	  if ($("input#medal_abberivation").val().trim() == "") {
			alert("Please Enter Medal Abbreviation");
			$("input#medal_abberivation").focus();
			return false;
		}  
	  
	  if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
	  return true;
}
	  
</script>




<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
});
</script>

<script>

function viewDetails_Wep(id){
	var ruleName=[];
	
	 var ele = document.getElementsByName("idCheck_wep");
	 for(var i=0;i<ele.length;i++){
	     if( ele[i].checked)
	     {
	    	 ruleName.push("'"+ele[i].value+"'");	    
	     }
	   }
	document.getElementById('id_check_wep_hidden').value=ruleName;	
	
}
function viewDetailsWepfootnotes(id){
	var ruleName1=[];
	
	 var ele = document.getElementsByName("idCheck_wep_foot");
	 for(var i=0;i<ele.length;i++){
	     if( ele[i].checked)
	     {
	    	 ruleName1.push(ele[i].value);	    
	     }
	   }
	document.getElementById('id_wep_foot_hidden').value=ruleName1;	
	
}
</script>

<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"><h5>Edit LINK SUS No with WE/PE No</h5> </div>
	          			<div class="card-body card-block cue_text">
	            			
						<div class="col-md-12">	              					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Unit Name </label> <strong style="color: red;">*</strong>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="unit_name" name="unit_name" class="form-control" autocomplete="off" readonly="readonly">
								</div>
 							</div> 
  						</div>
  						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">SUS No</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="unit_sus_no_wep" name="sus_no" maxlength="8" readonly="readonly" class="form-control" value="${editLinkWepCmd.sus_no}">
								</div>
 							</div>
 						</div>
  					</div>
  					<div class="col-md-12">	
  							
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Type of Document </label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                	 <div class="form-check-inline form-check">
		                                <label for="inline-radio1" class="form-check-label ">
		                                  <input type="radio" id="radio_doc1" name="radio_doc" value="WE" class="form-check-input" disabled="disabled">WE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="radio_doc2" name="radio_doc" value="PE" class="form-check-input" disabled="disabled">PE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                 <label for="inline-radio3" class="form-check-label ">
							                 <input type="radio" id="radio_doc3" name="radio_doc" value="FE" class="form-check-input" disabled="disabled">FE
							            </label>&nbsp;&nbsp;&nbsp;
							            <label for="inline-radio4" class="form-check-label ">
							             	<input type="radio" id="radio_doc4" name="radio_doc" value="GSL" class="form-check-input" disabled="disabled">GSL
							           </label>      
		                             </div>
				                </div>
				            </div>	
				            								
  						</div>
  							<div class="col-md-6">
  									<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No </label> <strong style="color: red;">*</strong>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="We_pe_no" name="wepe_wep_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off" value="${editLinkWepCmd.wepe_weapon_no}" disabled="disabled">
	                					</div>
	                			</div>
	                		</div>
  						</div>
			</div>
	    	
	    	<div  class="card-footer" align="center" id="edit_tbl_wep" >
				<a href="Search_link_WEPE_Wep" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
			</div> 
       	</div>
	</div>
            
          	<div>
					<form:form action="edit_modification_link_wep_act" method='POST' commandName="editLinkWepCmd"> 
						<div  class="col-md-12" align="center" > 
						<input type="hidden" id="id_unit_hidden_wep" name="id_unit_hidden_wep">	
		 				<input type="hidden" id="id_we_pe_no_hidden_wep" name="id_we_pe_no_hidden_wep">	
		 				<input type="hidden" id="id_check_wep_hidden" name="id_check_wep_hidden">	 	
		 				<input type="hidden" id="id_wep_foot_hidden" name="id_wep_foot_hidden">	
		 				
						<c:if test="${fn:length(table_view_wep)>0}"><h3 class="heading_content_inner">Modification Details</h3>
		 				
						<table id="tblData" class="table no-margin table-striped  table-hover  table-bordered" >
	  						<thead style="background-color: #9c27b0; color: #fff;">
								<tr><th class="tableheadSer">Ser No</th><th>Mod</th><th>View</th><th></th></thead>
							 <tbody>
							 	<c:forEach var="item" items="${table_view_wep}" varStatus="num" >
		 							<tr>
		 								<td class="tableheadSer"><c:out value="${num.index+1}" /> </td><td>${item.modification}</td> 	
										<td><input type="checkbox" onclick="viewDetails_Wep('${item.modification}')" name="idCheck_wep" id="idCheck_wep" value="${item.modification}" ></td>
										<td><input onclick="moreModiweapon('${item.modification},${num.index+1}');" type="button" value="View More Details"></td>
									</tr>
									<tr>
									<td colspan="4">
									<table id="tblDatai${num.index+1}" class="table no-margin table-striped  table-hover  table-bordered" width="100%" style="display: none;">
			  							<thead style="background-color: #c93be0; color: #fff;">
										<tr><th>MISO WE/PE No</th><th>Nomenclature</th><th>PRF Group</th><th>Amt of Incr/Decr</th><th>Scenario</th><th>Loc/ Fmn/ Unit</th></thead>
										<tbody>								
										</tbody>									
									</table>									
									</td>
									</tr>
   		 						</c:forEach>    						
  							</tbody>
						</table>
						</c:if>
					</div>
					
						<div  class="col-md-12" align="center" > 
						<c:if test="${fn:length(table_view_wep_footnotes)>0}"><h3 class="heading_content_inner">General Notes Details</h3>
						<table id="tblData_foot" class="table no-margin table-striped  table-hover  table-bordered" width="100%" >
						
	  						<thead>
								<tr><th class="tableheadSer">Ser No</th><th>Nomenclature</th><th>PRF Group</th><th>Scenario</th><th>Loc/ Fmn/ Unit</th><th>Amt of Incr/Decr</th><th>Condition</th><th>View</th></thead>
							 <tbody>
							 	<c:forEach var="item" items="${table_view_wep_footnotes}" varStatus="num" >
		 							<tr>
		 								<td class="tableheadSer"><c:out value="${num.index+1}" /> </td><td><c:out value="${item.item_type}"/></td><td>${item.prf_group}</td><td>${item.scenario}</td><td>${item.loc_for_unit}</td><td>${item.amt_inc_dec}</td><td>${item.condition}</td>	
										<td><input type="checkbox" onclick="viewDetailsWepfootnotes(${item.id})" name="idCheck_wep_foot" id="idCheck_wep_foot" value="${item.id}"></td>
									</tr>
   		 						</c:forEach>
  							</tbody>
						
						</table>
						</c:if>
							</div>
							<div  class="col-md-12" align="center" >
							<table>
							<c:if test="${fn:length(table_view_wep)>0 || fn:length(table_view_wep_footnotes)>0}">
							<tr>
  
   		 						<td colspan="9"  align="center"><input type="submit" class="btn btn-primary btn-sm" value="Link SUS No" onclick="">  
   		 						<a href="Search_link_WEPE_Wep" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
   		 						 </td>
   		 				
   		 						</tr>
							</c:if>
							</table>
							
						</div> 
					</form:form>
</div>

<script>
$(document).ready(function() {
	
	
	
	var sus_no_hidden = document.getElementById("unit_sus_no_wep").value;
	var We_pe_no_hidden =  document.getElementById("We_pe_no").value;
	
	if( sus_no_hidden != "" && sus_no_hidden != null)
		{
		$.post("getCUESusNoDetailsList?"+key+"="+value, {sus_no : sus_no_hidden }).done(function(j) {
	    	 if(j!="" && j!=null)
				 document.getElementById("unit_name").value=j[0].unit_name;
			 else
				 document.getElementById("unit_name").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		}
	
	if( We_pe_no_hidden != "" && We_pe_no_hidden != null)
	{
		$.post("getCUEWERadiocatDetailsList?"+key+"="+value, {wepe_no : We_pe_no_hidden }).done(function(j) {
			$("input[name=radio_doc][value=" + j + "]").prop('checked', true);	
	      }).fail(function(xhr, textStatus, errorThrown) { }); 
	}
	 
	$('input#unit_name').change(function() {		
		var unit_name = $(this).val();
		 $.post("getCUEUnitDetailsList?"+key+"="+value, { unitName : unit_name }).done(function(j) {
	    	 if(j!="" && j!=null)
				 document.getElementById("unit_sus_no_wep").value=j[0].sus_no;
			 else
				 document.getElementById("unit_sus_no_wep").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { }); 
		
	});

	
	document.getElementById("id_unit_hidden_wep").value = document.getElementById("unit_sus_no_wep").value;
	document.getElementById("id_we_pe_no_hidden_wep").value =  document.getElementById("We_pe_no").value;
	
	
	$('input[type=radio][name=radio_doc]').change(function() {		
		var radio_doc = $(this).val();
		$.ajax({
			type: 'POST',
			url: 'getCUEWENameDetailsList?'+key+"="+value,
	        data: {raio_we:radio_doc },
	        success: function(response) {
	            response.sort();
	          
	          var countryArray = response;
	          var dataCountry = {};
	         
	          for (var i = 0; i < countryArray.length; i++) {
	            //console.log(countryArray[i]);
	            dataCountry[countryArray[i]] = countryArray[i]; //countryArray[i].flag or null
	          }
	          $('#We_pe_no').autocomplete({
	            data: dataCountry,
	            //limit: 5, // The max amount of results that can be shown at once. Default: Infinity.
	          });
	        }
	      }); 
		
	});
	
	 if($('#tblData_foot tbody tr').length == 0 && $('#tblData tbody tr').length == 0)
		 $('div#edit_tbl_wep').show();
	 else
		 $('div#edit_tbl_wep').hide();
	 
	try{
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}  
	}
	catch (e) {
		// TODO: handle exception
	}
	getLink_susno_wepmdfcheck();
	getLink_susno_wepcheck_foot();
});


var ruleName_c = [];
function getLink_susno_wepmdfcheck()
{
	var unit_sus_no = document.getElementById("unit_sus_no_wep").value;
	var we_pe = document.getElementById("We_pe_no").value;
	$.post("getLink_susno_wepmdf?"+key+"="+value, {unit_sus_no : unit_sus_no,wepe_wep_no:we_pe}).done(function(j) {
   	 for ( var i = 0; i < j.length; i++) 
			{
				 $("input[name=idCheck_wep][value=" + j[i].modification + "]").prop('checked', true);
				 ruleName_c.push("'"+j[i].modification+"'");
		
			}
		document.getElementById('id_check_wep_hidden').value=ruleName_c;
     }).fail(function(xhr, textStatus, errorThrown) { }); 
	
	}
var ruleName_f = [];	
function getLink_susno_wepcheck_foot()
{
	var unit_sus_no = document.getElementById("unit_sus_no_wep").value;
	var we_pe = document.getElementById("We_pe_no").value;
	 $.post("getLink_susno_wepfoot?"+key+"="+value, {unit_sus_no : unit_sus_no,wepe_wep_no:we_pe}).done(function(j) {
	    	for ( var i = 0; i < j.length; i++) 
			{
		
				$("input[name=idCheck_wep_foot][value=" + j[i].fk_weapon_foot + "]").prop('checked', true);
				ruleName_f.push("'"+j[i].fk_weapon_foot+"'");
			}
				document.getElementById('id_wep_foot_hidden').value=ruleName_f;
	     }).fail(function(xhr, textStatus, errorThrown) { });  
	
	}
	function moreModiweapon(mod)

	{
		var mod_i =null;
		var tbl= ";" 
		if(mod.toString().includes(","))
			{
			mod_i =mod.toString().split(",");
			tbl = "#tblDatai"+mod_i[1];
			}
		
		 if($(tbl+' tbody tr').length ==0)
		{
	
		var we_pe = document.getElementById("We_pe_no").value;
		$.post("viewMoreDetailModiWeap?"+key+"="+value, {mod : mod_i[0],wepe_pers_no:we_pe}).done(function(j) {
	    	if(j != "" && j != null)
			{
				$("table#tblDatai"+mod_i[1]).show();							
				drawTable1(j);
			}else{
				$("table#tblDatai"+mod_i[1]).hide();
				alert("Data not availble")
			}
	     }).fail(function(xhr, textStatus, errorThrown) { }); 
	    
		function drawTable1(data) {
			 
			 
			for (var i = 0; i < data.length; i++) {				 
				var row = $("<tr/>")				
				$(tbl).append(row);
				row.append($("<td>" + data[i].we_pe_no + "</td>"));
				row.append($("<td>" + data[i].item_type + "</td>"));
				row.append($("<td>" + data[i].prf_group + "</td>"));				
				row.append($("<td>" + data[i].amt_inc_dec + "</td>"));
				row.append($("<td>" + data[i].scenario + "</td>"));
				row.append($("<td>" + data[i].loc_for_unit + "</td>"));
				
			}
			$(tbl).DataTable();
		}
	
		}
		 else
		 {
		 $("table#tblDatai"+mod_i[1]).hide();
		 var tab = $("table#tblDatai"+mod_i[1]+" > tbody ");
		 	tab.empty(); 
		 
		 }
	} 
</script>
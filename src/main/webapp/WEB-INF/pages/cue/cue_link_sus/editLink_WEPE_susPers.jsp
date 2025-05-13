<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


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

function viewDetails(id){
	var ruleName=[];
	
	 var ele = document.getElementsByName("idCheck");
	 for(var i=0;i<ele.length;i++){
	     if( ele[i].checked)
	     {
	    	 ruleName.push("'"+ele[i].value+"'");	    
	     }
	   }
	document.getElementById('id_check_hidden').value=ruleName;	
	
}
function viewDetailsfootnotes(id)
{
	var ruleName1=[];
	 var ele = document.getElementsByName("idCheck_foot");
	 for(var i=0;i<ele.length;i++){
	     if( ele[i].checked)
	     {
	    	 ruleName1.push(ele[i].value);	    
	     }
	   }
	document.getElementById('id_check_foot_hidden').value=ruleName1;
	
		
}
</script>

<div class="container" align="center">
	        	<div class="card">
	          		<div class="card-header"> <h5>Edit LINK SUS No with WE/PE No</h5></div>
	          			<div class="card-body card-block cue_text">
	            			
						<div class="col-md-12">	              					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Unit Name</label> <strong style="color: red;">*</strong>
               					</div>
               					<div class="col-12 col-md-6">
               					<input type="hidden" name="id" value="${editLinkpersCmd.id}">
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
                 					<input  id="unit_sus_no" name="sus_no" readonly="readonly" class="form-control" value="${editLinkpersCmd.sus_no}">
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
		                                  <input type="radio" id="radio_doc2" name="radio_doc" value="PE" class="form-check-input"  disabled="disabled">PE
		                                </label>&nbsp;&nbsp;&nbsp;
		                                 <label for="inline-radio3" class="form-check-label ">
							                 <input type="radio" id="radio_doc3" name="radio_doc" value="FE" class="form-check-input"  disabled="disabled">FE
							            </label>&nbsp;&nbsp;&nbsp;
							            <label for="inline-radio4" class="form-check-label ">
							             	<input type="radio" id="radio_doc4" name="radio_doc" value="GSL" class="form-check-input"  disabled="disabled">GSL
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
              						<input  id="We_pe_no" name="wepe_pers_no" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" disabled="disabled" autocomplete="off" value="${editLinkpersCmd.wepe_pers_no}">
              					</div>
               			    </div>
               		   </div>
  				</div>
			</div>
	    	<div  class="card-footer" align="center" id="edit_tbl_pers" >	
				<a href="Search_link_WEPE_Pers" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
			</div> 
          </div>
</div>
					
					<form:form action="edit_modification_link_act" method='POST' commandName="editLinkpersCmd"  > 
						<div  class="col-md-12" align="center" > 
						<input type="hidden" id="id_unit_hidden" name="id_unit_hidden">
		 				<input type="hidden" id="id_we_pe_no_hidden" name="id_we_pe_no_hidden">	
		 				<input type="hidden" id="id_check_hidden" name="id_check_hidden">
		 				<input type="hidden" id="id_check_foot_hidden" name="id_check_foot_hidden">	
						<c:if test="${fn:length(table_view)>0}"><h3 class="heading_content_inner">Modification Details</h3>
						 
						<table id="tblData" class="table no-margin table-striped  table-hover  table-bordered"   >
						
	  						<thead>
								<tr><th class="tableheadSer">Ser No</th><th>Mod</th><th>View</th><th></th></thead>
							 <tbody>
							 	<c:forEach var="item" items="${table_view}" varStatus="num" >
		 							<tr>
		 								<td class="tableheadSer"><c:out value="${num.index+1}" /> </td><td>${item.modification}</td> 	
										 <td><input type="checkbox" onclick="viewDetails('${item.modification}')" name="idCheck" id="idCheck" value="${item.modification}" ></td>
										 <td><input onclick="moreModipers('${item.modification},${num.index+1}');" type="button" value="View More Details"> </td>
									</tr>
									<tr >
									<td colspan="4">
									<table id="tblDatai${num.index+1}" style="display: none;" class="table no-margin table-striped  table-hover  table-bordered" >
						
	  								<thead>
										<tr><th>MISO WE/PE No</th><th>Appt Trade11</th><th>Rank</th><th>Category</th><th>Amt of Incr/Decr</th><th>Scenario</th><th>Loc/ Fmn/ Unit</th><th>Pers Cat</th></thead>
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
						<c:if test="${fn:length(table_view_footnotes)>0}"><h3 class="heading_content_inner">General Notes Details</h3>
						<table id="tblData_foot" class="table no-margin table-striped  table-hover  table-bordered" >
						
	  						<thead>
								<tr><th class="tableheadSer">Ser No</th><th>MISO WE/PE No</th><th>Appt Trade</th><th>Rank</th><th>Category</th><th>Amt of Incr/Decr</th><th>Condition</th><th>Pers Cat</th><th>View</th></thead>
							 <tbody>
							 	<c:forEach var="item" items="${table_view_footnotes}" varStatus="num" >
		 							<tr>
		 								<td class="tableheadSer"><c:out value="${num.index+1}" /> </td><td><c:out value="${item.we_pe_no}"/></td><td>${item.appt}</td><td>${item.rank}</td><td>${item.rank_cat}</td><td>${item.amt_inc_dec}</td><td>${item.condition}</td>	<td>${item.person_cat}</td>	 	
										<td><input type="checkbox" onclick="viewDetailsfootnotes(${item.id})" name="idCheck_foot" id="idCheck_foot" value="${item.id}"></td>
									</tr>
   		 						</c:forEach>
   
    							
  							</tbody>
						</table>
						</c:if>
							</div>
							
							<div  class="col-md-12" align="center" >
							<table>
							<c:if test="${fn:length(table_view)>0 || fn:length(table_view_footnotes)>0}">
							<tr>
  
   		 						<td colspan="9"  align="center"><input type="submit" class="btn btn-primary btn-sm" value="Link SUS No" onclick=""> 
   		 					<a href="Search_link_WEPE_Pers" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
   		 						</td>
					
   		 						</tr>
							</c:if>
							</table>
							
						</div> 
					</form:form>


<script>
$(document).ready(function() {
	
	var sus_no_hidden = document.getElementById("unit_sus_no").value;
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
		$.post("getCUEUnitDetailsList?"+key+"="+value, {unitName : unit_name }).done(function(j) {
	    	if(j!="" && j!=null)
				 document.getElementById("unit_sus_no").value=j[0].sus_no;
			 else
				 document.getElementById("unit_sus_no").value="";
	      }).fail(function(xhr, textStatus, errorThrown) { }); 		
	});
	
	document.getElementById("id_unit_hidden").value = document.getElementById("unit_sus_no").value;
	document.getElementById("id_we_pe_no_hidden").value =  document.getElementById("We_pe_no").value;
	
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
		 {
		 $('div#edit_tbl_pers').show();
		 }
	 else
		 {
		 $('div#edit_tbl_pers').hide();
		 }
	 
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
	getLink_susno_permdfcheck();
	getLink_susno_percheck_foot();
});

var ruleName_c = [];
function getLink_susno_permdfcheck()
{
	var unit_sus_no = document.getElementById("unit_sus_no").value;
	var we_pe = document.getElementById("We_pe_no").value;
	$.post("getLink_susno_permdf?"+key+"="+value, {unit_sus_no : unit_sus_no, wepe_pers_no:we_pe}).done(function(j) {
		    	for ( var i = 0; i < j.length; i++) 
				{
					$("input[name=idCheck][value=" + j[i].modification + "]").prop('checked', true);
					ruleName_c.push("'"+j[i].modification+"'");
				}
	
				document.getElementById('id_check_hidden').value=ruleName_c;	
     }).fail(function(xhr, textStatus, errorThrown) { }); 
	
}
var ruleName_f = [];
function getLink_susno_percheck_foot()
{
	
	var unit_sus_no = document.getElementById("unit_sus_no").value;
	var we_pe = document.getElementById("We_pe_no").value;
	
	$.post("getLink_susno_perfoot?"+key+"="+value, {unit_sus_no : unit_sus_no,wepe_pers_no:we_pe}).done(function(j) {
		    	for ( var i = 0; i < j.length; i++) 
				{
					$("input[name=idCheck_foot][value=" + j[i].foot_fk + "]").prop('checked', true);
				    ruleName_f.push("'"+j[i].foot_fk+"'");
				}
				document.getElementById('id_check_foot_hidden').value=ruleName_f;
     }).fail(function(xhr, textStatus, errorThrown) { }); 
}

	function moreModipers(mod)
		
	{	 
		var mod_i =null;
		var tbl= ";" 
		if(mod.toString().includes(","))
			{
			mod_i =mod.toString().split(",");
			tbl = "table#tblDatai"+mod_i[1];
			}
		 if($(tbl+' tbody tr').length == 0)
			{	
		
		var we_pe = document.getElementById("We_pe_no").value;
		$.post("viewMoreDetailModiPers?"+key+"="+value, {mod : mod_i[0],wepe_pers_no:we_pe}).done(function(j) {
		    	 if(j != "" && j != null)
					{
					$("#tblDatai"+mod_i[1]).show();							
					drawTable1(j);
					
					}
				else{
					$("#tblDatai"+mod_i[1]).hide();
					alert("Data not availble")
				}
	      }).fail(function(xhr, textStatus, errorThrown) { });  
		function drawTable1(data) {
			 
			 
			for (var i = 0; i < data.length; i++) {				 
				var row = $("<tr/>")				
				$(tbl).append(row);
				
				row.append($("<td>" + data[i].we_pe_no + "</td>"));
				row.append($("<td>" + data[i].appt + "</td>"));
				row.append($("<td>" + data[i].rank + "</td>"));
				row.append($("<td>" + data[i].rank_cat + "</td>"));
				row.append($("<td>" + data[i].amt_inc_dec + "</td>"));
				row.append($("<td>" + data[i].scenario + "</td>"));
				row.append($("<td>" + data[i].loc_for_unit + "</td>"));
				row.append($("<td>" + data[i].person_cat + "</td>"));
				
			}
			$(tbl).DataTable();
		}
			}
		 else
		 {
		 $("tr#tblDatai"+mod_i[1]).hide();
		 var tab = $("tr#tblDatai"+mod_i[1]+" > tbody ");
		 	tab.empty(); 
		 
		 }
	}
</script>
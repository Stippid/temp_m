<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css">

<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<%
	String nPara=request.getParameter("Para");
	 
%>

<form:form name="new_eqpt_details_view" id="new_eqpt_details_view" action="" method="post" class="form-horizontal" commandName="new_eqpt_details_viewCMD"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	  
		            <div class="card-header mms-card-header">
					<% if (nPara.equalsIgnoreCase("VLS")) { %>
						  <b>VIEW DETAILS OF LOAN STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>						
					<% }  %>			
		            <% if (nPara.equalsIgnoreCase("VSS")) { %>
						  <b>VIEW DETAILS OF SECTOR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>
		            <% }  %>			
		            <% if (nPara.equalsIgnoreCase("VAS")) { %>
				          <b>VIEW DETAILS OF ACSFP STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>
		            <% }  %>
		            <% if (nPara.equalsIgnoreCase("ENG")) { %>
						  <b>VIEW DETAILS OF ENGR STORES</b><br><span class="mms-card-subheader">(To be used by All based on Role Access)</span>
		            <% }  %>
		                
		            </div>
		            	
	    			<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: left; background-color: white;"> <strong>Basic Details</strong></div>  
	    			
	    			<div class="card-body card-block">
	    			    <div class="col-md-12 row form-group">
	            			     <div class="col-md-3" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>SUS No :</strong></label>
	             		         </div>
	             		         <div class="col-md-3">
	             			         <label id="susId"></label>
	               		         </div>
	               		         
	               		         <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>Unit's Name :</strong></label>
	             		         </div>
	             		         <div class="col-md-4">
	             			         <label id="unitNm"></label>
	               		         </div>   
	            		</div>
	    			    
	    			    <div class="col-md-12 row form-group">
	            			     <div class="col-md-3" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>IV No :</strong></label>
	             		         </div>
	             		         <div class="col-md-3">
	             			         <label id="ivno"></label>
	               		         </div>
	               		         
	               		         <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>IV Date :</strong></label>
	             		         </div>
	             		         <div class="col-md-4">
	             			         <label id="ivdt"></label>
	               		         </div>   
	            		</div>
	            		
	            		<div class="col-md-12 row form-group">
	            			     <div class="col-md-3" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>Census No :</strong></label>
	             		         </div>
	             		         <div class="col-md-3">
	             			         <label id="cens"></label>
	               		         </div>
	               		         
	               		         <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>Nomenclature :</strong></label>
	             		         </div>
	             		         <div class="col-md-4">
	             			         <label id="nom"></label>
	               		         </div>   
	            		</div>
	            		
	            		<div class="col-md-12 row form-group">
	            			     <div class="col-md-3" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>Type of Holding :</strong></label>
	             		         </div>
	             		         <div class="col-md-3">
	             			         <label id="hldg"></label>
	               		         </div>
	               		         
	               		         <div class="col-md-2" style="text-align: left;"> 
	               			         <label class=" form-control-label"><strong>Type of Eqpt :</strong></label>
	             		         </div>
	             		         <div class="col-md-4">
	             			         <label id="eqpt"></label>
	               		         </div>   
	            		</div>
	    			    
	    			   
	    			    
	        	</div>
	        	
	        	<div class="card-footer" align="center"> 
	        		<c:if test="${m_1[0][7] == 0}">      
					   <input type="button" class="btn btn-success btn-sm" value="Approve All" onclick="Approved();" />
					</c:if>
					<input type="button" class="btn btn-danger btn-sm" value="Back" onclick="BackEqpt()"/>
			    </div>
			    	 
			</div>
		</div>
    </div>
  </div>
  
  <div class="card" id="ac" style="background: transparent;">
     <div class="card-body card-block">
         <div id="" class="nrTableMainDiv">
        
					 <table border="1" class="report_print" width="100%">
	                        			<thead style="text-align: center">
	                          				<tr class="nrTableDataHead" style="font-size: 12px" >
					               <th class="nrBox" width="10%">Eqpt Registration No</th>
					           </tr>
					       </thead>
					       
					       <tbody id="nrTable">
					           <c:if test="${m_1.size() == 0}">
						             <tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
						                <td colspan='8' align='center'>Data Not Available...</td>
						                <% ntotln=0; %>
						             </tr>
							   </c:if> 
							   
							   <c:if test="${m_1.size() != 0}">
							       <c:forEach var="item" items="${m_1}" varStatus="num">
							           <tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
							              <td style='text-align:center;'>${item[2]}</td>
							              <% ntotln++; %>
							           </tr>
							       </c:forEach>
							   </c:if>
					       </tbody>
					   </table>
                   </div>   
              
         </div>
     </div>

</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<c:url value="approve_store_data" var="backUrl" />
<form:form action="${backUrl}" method="post" id="a_store" name="a_store" modelAttribute="a_sus">
      <input type="hidden" name="a_sus" id="a_sus"/>
	  <input type="hidden" name="a_iv" id="a_iv"/>
	  <input type="hidden" name="a_cen" id="a_cen"/>
	  <input type="hidden" name="a_para" id="a_para"/>
</form:form>

<c:url value="back_store" var="backUrl" />
<form:form action="${backUrl}" method="post" id="b_store" name="b_store" modelAttribute="b_sus">
      <input type="hidden" name="b_sus" id="b_sus"/>
	  <input type="hidden" name="b_unit" id="b_unit"/>
	  <input type="hidden" name="b_para" id="b_para"/>
	  <input type="hidden" name="b_stat" id="b_stat"/>
	  <input type="hidden" name="b_frm" id="b_frm"/>
	  <input type="hidden" name="b_to" id="b_to"/>
</form:form>

<script>
$(document).ready(function() {
	//alert('${m_1[0][0]}');
	$("label#susId").text('${m_1[0][3]}');  
	$("label#unitNm").text('${m_1[0][4]}');  
	$("label#ivno").text('${v_4}'); 
	
	var ext = '${v_5}';
	var f_d = new Date(ext);
	var e_date = f_d.getDate()+"-"+("0" + (f_d.getMonth()+1)).slice(-2)+"-"+f_d.getFullYear();
	
	$("label#ivdt").text(e_date); 
	$("label#hldg").text('${m_1[0][5]}'); 
	$("label#eqpt").text('${m_1[0][6]}'); 
	$("label#cens").text('${v_6}'); 
	$("label#nom").text('${v_7}'); 
	
	$("#ntotln").text(<%=ntotln%>);
	
});
</script>

<script>
function Approved(){
	<% if (nPara.equalsIgnoreCase("VLS")) { %>
	    var paraA = "VLS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VSS")) { %>
	    var paraA = "VSS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VAS")) { %>
	    var paraA = "VAS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("ENG")) { %>
	    var paraA = "ENG";
	<% }  %>

	$("#a_sus").val('${m_1[0][3]}');
	$("#a_iv").val('${v_4}');
	$("#a_cen").val('${v_6}');
	$("#a_para").val(paraA);
	$("#a_store").submit();
}

function BackEqpt(){
	<% if (nPara.equalsIgnoreCase("VLS")) { %>
	    var paraA = "VLS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VSS")) { %>
	    var paraA = "VSS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("VAS")) { %>
	    var paraA = "VAS";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("ENG")) { %>
	    var paraA = "ENG";
	<% }  %>
	
	$("#b_sus").val('${v_1}');
	$("#b_unit").val('${v_2}');
	$("#b_para").val(paraA);
	$("#b_stat").val('${v_8}');
	$("#b_frm").val('${v_9}');
	$("#b_to").val('${v_10}');
	$("#b_store").submit();
}
</script>
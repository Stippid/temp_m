package com.dao.tms;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.models.TB_TMS_CONVERT_REQ;
import com.models.Tms_Banum_Req_Child;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Service
@Repository
public class tmsConversionRequestGstoSplVehDAOImp implements tmsConversionRequestGstoSplVehDAO {

	
	public Boolean ifExistBaNo(String old_ba_no)
	{		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select count(old_ba_no) from TB_TMS_CONVERT_REQ where old_ba_no=:old_ba_no");
		q.setParameter("old_ba_no", old_ba_no);
		Long count = (Long)q.uniqueResult();
		tx.commit();
		session.close();
		if(count == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public TB_TMS_CONVERT_REQ getTB_TMS_CONVERT_REQid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_CONVERT_REQ where id=:id");
		q.setParameter("id", id);
		TB_TMS_CONVERT_REQ list = (TB_TMS_CONVERT_REQ) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}
		
	public String UpdateConvertms(TB_TMS_CONVERT_REQ ItemMasterValue,String fname1,String fname2,String fname3,String fname4){
		if(ItemMasterValue.getNew_mct_number() == null)
		{
			ItemMasterValue.setNew_mct_number(BigInteger.ZERO);
		}
			
		Session session2 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx2 = session2.beginTransaction();
		String hql = "update TB_TMS_CONVERT_REQ  set sus_no=:sus_no , old_mct_number =:old_mct_number,old_ba_no=:old_ba_no,conv_modCarriedOut=:conv_modCarriedOut,newstdnomencltr_veh_aftrmod_as_auth_we=:newstdnomencltr_veh_aftrmod_as_auth_we,justification=:justification,financial_impact=:financial_impact , remarks =:remarks,auth_letter_no=:auth_letter_no,convrsn_done=:convrsn_done,new_nomencatre=:new_nomencatre,new_mct_number=:new_mct_number,appr_rej_remarks=:appr_rej_remarks,approved_by=:approved_by,vehicle_class_code=:vehicle_class_code,front_view_image = :front_view_image,back_view_image =:back_view_image,side_view_image = :side_view_image,top_view_image =:top_view_image, status ='0' where id=:id";
	    Query query = session2.createQuery(hql).setString("sus_no", ItemMasterValue.getSus_no()).setBigInteger("old_mct_number",ItemMasterValue.getOld_mct_number()).setString("old_ba_no",ItemMasterValue.getOld_ba_no()).setString("conv_modCarriedOut",ItemMasterValue.getConv_modCarriedOut()).setString("newstdnomencltr_veh_aftrmod_as_auth_we",ItemMasterValue.getNewstdnomencltr_veh_aftrmod_as_auth_we()).setString("justification",ItemMasterValue.getJustification()).
	   		setString("financial_impact",ItemMasterValue.getFinancial_impact()).setString("remarks",ItemMasterValue.getRemarks()).setString("auth_letter_no",ItemMasterValue.getAuth_letter_no()).setString("convrsn_done",ItemMasterValue.getConvrsn_done()).setString("new_nomencatre",ItemMasterValue.getNew_nomencatre()).setBigInteger("new_mct_number",ItemMasterValue.getNew_mct_number()).setString("appr_rej_remarks",ItemMasterValue.getAppr_rej_remarks()).setString("approved_by",ItemMasterValue.getApproved_by()).setString("vehicle_class_code",ItemMasterValue.getVehicle_class_code()).setString("front_view_image",fname1).setString("back_view_image",fname2).setString("side_view_image",fname3).setString("top_view_image",fname4).setInteger("id",ItemMasterValue.getId());
	    int rowCount = query.executeUpdate();
		tx2.commit();
		session2.close();
		if(rowCount > 0) {
			return "CONVERT REQ Update Successfully";
		}else {
			return "CONVERT REQ  Update not Successfully";
		}
	}
	
	
	public String setApprovedConvReqOfVeh(int appid,String newBa_no,String ba_no,String username,String date)
	{
		Session session3 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
		String hql1 = "update TB_TMS_MVCR_PARTA_DTL  set ba_no=:newBa_no where ba_no=:ba_no";
		Query updatedEntities = session3.createQuery( hql1 );
		updatedEntities.setParameter("ba_no", ba_no);
		updatedEntities.setParameter("newBa_no", newBa_no);
		updatedEntities.executeUpdate();
	    tx3.commit();
		session3.close();
	 
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		String hqlUpdate = "update TB_TMS_CONVERT_REQ c set c.status=:status ,approved_by=:approved_by,new_ba_no=:new_ba_no ,approve_date =:approve_date  where c.id = :id";
		int app = session1.createQuery( hqlUpdate ).setString( "status", "1" ).setString( "approved_by", username ).setInteger( "id", appid ).setDate("approve_date", new Date()).setParameter("new_ba_no", newBa_no).executeUpdate();
		tx.commit();
		session1.close();
		if(app > 0) {
			return "Approved Successfully.";
		}else {
			return "Data not Approved.";
		}
	}
	
	public String setRejectConvReqOfVeh(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update TB_TMS_CONVERT_REQ c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "2" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Rejected Successfully.";
		}else {
			return "Reject not Successfully";
		}
	}
		
	public String setDeleteConvReqOfVeh(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from TB_TMS_CONVERT_REQ where id = :id";
	    Query query = session.createQuery(hql);
	    query.setInteger("id",appid);
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Deleted Successfully.";
		}else {
			return "Delete not Successfully";
		}
		
	}

	public List<Tms_Banum_Req_Child> getImageFromBaNoChildReq(String ba_no)
	{	
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select front_view_image,back_view_image ,side_view_image,top_view_image from Tms_Banum_Req_Child where ba_no =:ba_no");
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<Tms_Banum_Req_Child> list = (List<Tms_Banum_Req_Child>) q.list();
	
		tx.commit();
		session.close();
		return list;
	}
		
	private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getImagePath(String ba_no,String fildname) {		
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="select * from tb_tms_convert_req where old_ba_no=?";	
			stmt = conn.prepareStatement(query);
			stmt.setString(1,ba_no);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
 	           whr=rs.getString(fildname);             	
 	        }
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}
	

    public  ArrayList<List<String>> getReqToCovGsToSpcl(String status,String sus_no,String dte_of_reqst,String received_from,String ba_no,String roleType)
  	{
    	ArrayList<List<String>> alist = new ArrayList<List<String>>();
    	Connection conn = null;
			try{	
			   conn = dataSource.getConnection();
			   PreparedStatement stmt=null;
			   String qry="";
				if(status != "") {
					qry += " d.status = ?";
				}
				if(sus_no != "")
				{
					qry += " and d.sus_no = ?";
				}
				if(dte_of_reqst != "" && received_from != "") {
					qry += " and  cast(d.dte_of_reqst as Date) >= cast(? as Date)  and cast(received_from as Date) <= cast(? as Date)";
				}
				else if(dte_of_reqst != "" && received_from == "")
				{
					qry += " and  cast(d.dte_of_reqst as Date) >= cast(? as Date)";
				}
				else if(received_from != "" && dte_of_reqst == "")
				{
					qry += " and cast(received_from as Date) <= cast(? as Date)";
				}
			   
				if(ba_no != "" && ba_no != "" && !ba_no.equals("0")) {
					qry += " and  d.old_ba_no = ?";
					
				}
				
			   String sql= "select "
			   			+ "distinct d.old_ba_no,"
			   			+ "m.std_nomclature,"
			   			+ "d.old_mct_number,"
			   			+ "d.conv_modCarriedOut,"
			   			+ "d.newstdnomencltr_veh_aftrmod_as_auth_we,"
			   			+ "d.id,"
			   			+ "d.remarks,"
			   			+ "d.new_ba_no,"
			   			+ "d.new_mct_number,"
			   			+ "(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.new_mct_number) as new_nomencatre "
			   		+ "from TB_TMS_CONVERT_REQ d,TB_TMS_MCT_MASTER m where d.old_mct_number = m.mct and "+qry +" order by m.std_nomclature"; 
				  
			   stmt=conn.prepareStatement(sql);
			   int j =1;
				if(status != "") {
					stmt.setString(j, status);
					j += 1;	
				}
				if(sus_no != "")
				{
					stmt.setString(j, sus_no);
					j += 1;
					
				}
				if(dte_of_reqst != "" && received_from != "") {
					stmt.setString(j, dte_of_reqst);
					j += 1;
					stmt.setString(j, received_from);
					j += 1;
					
				}
				else if(dte_of_reqst != "" && received_from == "")
				{
					stmt.setString(j, dte_of_reqst);
					j += 1;
					
				}
				else if(received_from != "" && dte_of_reqst == "")
				{
					stmt.setString(j, received_from);
					j += 1;
				}
				if(ba_no != "" && ba_no != "" && !ba_no.equals("0")) {
					stmt.setString(j, ba_no);
					j += 1;
				}
				
			   ResultSet rs = stmt.executeQuery();
			   while(rs.next()){
		    	  
				  List<String> list = new  ArrayList<String>();
		    	  list.add(rs.getString("old_ba_no"));
		    	  list.add(rs.getString("std_nomclature"));
		    	  list.add(rs.getString("old_mct_number"));
		    	  list.add(rs.getString("conv_modCarriedOut"));
		    	  list.add(rs.getString("newstdnomencltr_veh_aftrmod_as_auth_we"));
		    	  list.add(rs.getString("id"));
		    	  list.add(rs.getString("remarks"));
		    	  list.add(rs.getString("new_ba_no"));
		    	  list.add(rs.getString("new_mct_number"));
		    	  list.add(rs.getString("new_nomencatre"));
		    	  
						String Approved = "onclick=\"  if (confirm('I certify that the above data are true to the best of my knowledge.\\nAre You Sure you want to Approve this Conversion ?') ){Approved("+rs.getString("id")+ "," +rs.getString("new_mct_number")+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Conversion  ?') ){Reject("+rs.getString("id")+")}else{ return false;}\"";
						String rejectButton ="<i class='action_icons action_reject' "+Reject+" title='Reject Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Conversion ?') ){Delete1("+rs.getString("id")+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Conversion ?') ){Update("+rs.getString("id")+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						
						String f = "";
						if(status.equals("0")){
							if(roleType.equals("ALL")) {
								f += appButton;
								f += rejectButton;
								f += deleteButton;
								f += updateButton;
							}
							if(roleType.equals("APP")) {
								f += appButton;
								f += rejectButton;
							}
							if(roleType.equals("DEO")) {
								f += deleteButton;
								f += updateButton;
							}
						}else if(status.equals("1")){
							f += "Approved";
						}else if(status.equals("2")){
							if(roleType.equals("DEO") || roleType.equals("ALL")) {
								f += deleteButton;
								f += updateButton;
							}
						}
						
						list.add(f);
						alist.add(list);
					}
    	      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
		return alist;
  	}
}
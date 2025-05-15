package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class MMS_ReportsDAOImpl implements MMS_ReportsDAO {

	private DataSource dataSource;
	private DataSource dataSourceOLAP;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setDataSourceOLAP(DataSource dataSourceOLAP) {
		this.dataSourceOLAP = dataSourceOLAP;
	}

	/*public ArrayList<ArrayList<String>> mmsaih(String n_c1, String n_c2, String n_c3) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			String cnd = "N";
			nrSql = "select m.cos_sec,m.prf_code,prf.prf_group,a.census_no,m.nomen,m.cat_part_no,m.au,t1.label as au_n,m.item_status,t2.label as item_status_n,a.type_of_hldg,";
			nrSql = nrSql + " t3.label as type_of_hldg_n,a.type_of_eqpt,t4.label as type_of_eqpt_n,a.t from";
			nrSql = nrSql
					+ " (select census_no,type_of_hldg,type_of_eqpt,sum(tothol) as t from mms_tv_regn_mstr group by census_no,type_of_hldg,type_of_eqpt) a";
			nrSql = nrSql + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
			nrSql = nrSql + " left join cue_tb_miso_prf_group_mst prf on m.prf_code=prf.prf_group_code";
			nrSql = nrSql + " left join mms_domain_values t1 on m.au=t1.codevalue and t1.domainid='ACCOUNTINGUNITS'";
			nrSql = nrSql
					+ " left join mms_domain_values t2 on m.item_status=t2.codevalue and t2.domainid='ITEMSTATUS'";
			nrSql = nrSql
					+ " left join mms_domain_values t3 on a.type_of_hldg=t3.codevalue and t3.domainid='TYPEOFHOLDING'";
			nrSql = nrSql
					+ " left join mms_domain_values t4 on a.type_of_eqpt=t4.codevalue and t4.domainid='TYPEOFEQPT'";

			if (!n_c1.equalsIgnoreCase("ALL")) {
				nrSql = nrSql + " where prf_code=?";
				cnd = "Y";
			}

			if (!n_c2.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("Y")) {
					nrSql = nrSql + " and type_of_hldg=?";
				} else {
					nrSql = nrSql + " where type_of_hldg=?";
				}
			}

			nrSql = nrSql + "order by m.cos_sec,m.prf_code,a.census_no,a.type_of_hldg,a.type_of_eqpt";
		
			PreparedStatement stmt = conn.prepareStatement(nrSql);

			int g1 = 1;
			if (!n_c1.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, n_c1);
				g1++;
			}

			if (!n_c2.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, n_c2);
			}
			
			ResultSet rs = stmt.executeQuery();
			String nrStr = "";
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("cos_sec"));
					list.add(rs.getString("prf_code"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("cat_part_no"));
					list.add(rs.getString("au_n"));
					list.add(rs.getString("item_status_n"));
					list.add(rs.getString("type_of_hldg_n"));
					list.add(rs.getString("type_of_eqpt_n"));
					list.add(rs.getString("t"));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}*/
	
	public ArrayList<ArrayList<String>> mmsaih(String n_c1, String n_c2, String n_c3) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			String cnd = "N";
			nrSql = "select m.cos_sec,m.prf_code,prf.prf_group,a.census_no,m.nomen,m.cat_part_no,m.au,t1.label as au_n,m.item_status,t2.label as item_status_n,a.type_of_hldg,";
			nrSql = nrSql + " t3.label as type_of_hldg_n,a.type_of_eqpt,t4.label as type_of_eqpt_n,a.t,COALESCE( t5.label ,'CLASS-A')	AS class_category from";
			nrSql = nrSql
					+ " (select census_no,type_of_hldg,type_of_eqpt,sum(tothol) as t from mms_tv_regn_mstr group by census_no,type_of_hldg,type_of_eqpt) a";
			nrSql = nrSql + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
			nrSql = nrSql + " left join cue_tb_miso_prf_group_mst prf on m.prf_code=prf.prf_group_code";
			nrSql = nrSql + " left join mms_domain_values t1 on m.au=t1.codevalue and t1.domainid='ACCOUNTINGUNITS'";
			nrSql = nrSql
					+ " left join mms_domain_values t2 on m.item_status=t2.codevalue and t2.domainid='ITEMSTATUS'";
			nrSql = nrSql
					+ " left join mms_domain_values t3 on a.type_of_hldg=t3.codevalue and t3.domainid='TYPEOFHOLDING'";
			nrSql = nrSql
					+ " left join mms_domain_values t4 on a.type_of_eqpt=t4.codevalue and t4.domainid='TYPEOFEQPT'";
			nrSql = nrSql
					+ " left join  mms_domain_values t5 on m.class_category=t5.codevalue and t5.domainid='MMSCLASSA'";

			if (!n_c1.equalsIgnoreCase("ALL")) {
				nrSql = nrSql + " where prf_code=?";
				cnd = "Y";
			}

			if (!n_c2.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("Y")) {
					nrSql = nrSql + " and type_of_hldg=?";
				} else {
					nrSql = nrSql + " where type_of_hldg=?";
				}
			}

			nrSql = nrSql + "order by m.cos_sec,m.prf_code,a.census_no,a.type_of_hldg,a.type_of_eqpt";
		
			PreparedStatement stmt = conn.prepareStatement(nrSql);
			System.err.println("Query For Holding All India " + stmt);

			int g1 = 1;
			if (!n_c1.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, n_c1);
				g1++;
			}

			if (!n_c2.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, n_c2);
			}
			
			ResultSet rs = stmt.executeQuery();
			String nrStr = "";
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("cos_sec"));//0
					list.add(rs.getString("prf_code"));//1
					list.add(rs.getString("prf_group"));//2
					list.add(rs.getString("census_no"));//3
					list.add(rs.getString("nomen"));//4
					list.add(rs.getString("cat_part_no"));//5
					list.add(rs.getString("au_n"));//6
					list.add(rs.getString("item_status_n"));//7
					list.add(rs.getString("type_of_hldg_n"));//8
					list.add(rs.getString("type_of_eqpt_n"));//9
					list.add(rs.getString("t"));//10
					list.add(rs.getString("class_category"));//11
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}


	public ArrayList<ArrayList<String>> mms_list_e_assets_reg(String type_of_hldg, String formcodeType, String formcode,
			String p_code, String d_from, String d_to) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String cndValue = "";
			String nrSql = "";
			String nrSql1 = "";

			if (!p_code.equalsIgnoreCase("ALL")) {
				nrSql = " where census_no in (select distinct census_no from mms_tb_mlccs_mstr_detl where prf_code=?) ";
				nrSql1 = " where census_no in (select distinct census_no from mms_tb_mlccs_mstr_detl where prf_code=?) ";
			}

			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					cndValue = "sus_no=?)";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue = "substr(form_code_control,1,10)=?)";
					}
				}

				if (nrSql.contains("where")) {
					nrSql = nrSql
							+ " and sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue;
					nrSql1 = nrSql1
							+ " and to_sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue;
				} else {
					nrSql = nrSql
							+ "where sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue;
					nrSql1 = nrSql1
							+ "where to_sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue;
				}
			}

			sql1 = "select m.material_no,m.prf_code,max(prf.prf_group) as prf_group,a.census_no,max(m.nomen) as nomen,a.type_of_hldg,max(t1.label) as type_of_hldg_n,a.type_of_eqpt,max(t2.label) as type_of_eqpt_n,max(m.induc_year) as induc_year,";
			sql1 = sql1 + "'' as appx_depr,a.eqpt_batch_no,";
			sql1 = sql1
					+ "(case when max(a.unit_price)=0 then coalesce(Cast(max(m.cost) as integer),0) else coalesce(cast(max(a.unit_price) as integer),0) end) as cost,";
			sql1 = sql1 + "sum(a.tothol) as tothol from (";
			sql1 = sql1
					+ "select census_no,type_of_hldg,type_of_eqpt,eqpt_batch_no,count(regn_seq_no) as tothol,0 as unit_price from mms_tb_regn_mstr_detl ";
			if (nrSql.length() > 0) {
				sql1 = sql1 + nrSql;
			}
			sql1 = sql1 + " group by census_no,type_of_hldg,type_of_eqpt,eqpt_batch_no ";
			sql1 = sql1 + "union all ";
			sql1 = sql1
					+ "select census_no,type_of_hldg,type_of_eqpt,eqpt_batch_no,0 as tothol,max(unit_price) as unit_price from mms_tb_regn_mstr ";
			if (nrSql1.length() > 0) {
				sql1 = sql1 + nrSql1;
			}
			sql1 = sql1 + " group by census_no,type_of_hldg,type_of_eqpt,eqpt_batch_no";
			sql1 = sql1 + ") a ";
			sql1 = sql1 + "left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no ";
			sql1 = sql1 + "left join cue_tb_miso_prf_group_mst prf on m.prf_code=prf.prf_group_code ";
			sql1 = sql1
					+ "left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' ";
			sql1 = sql1 + "left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' ";
			sql1 = sql1 + "group by m.material_no,m.prf_code,a.census_no,a.type_of_hldg,a.type_of_eqpt,a.eqpt_batch_no";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			int f = 1;
			if (!p_code.equalsIgnoreCase("ALL")) {
				stmt.setString(f, p_code);
				f++;
			}
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					stmt.setString(f, formcode);
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(f, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(f, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(f, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(f, formcode.substring(0, 10));
					}
				}
				f++;
			}

			if (!p_code.equalsIgnoreCase("ALL")) {
				stmt.setString(f, p_code);
				f++;
			}

			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					stmt.setString(f, formcode);
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(f, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(f, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(f, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(f, formcode.substring(0, 10));
					}
				}
				f++;
			}
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("material_no"));
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("induc_year"));
					list.add(rs.getString("eqpt_batch_no"));
					list.add(rs.getString("cost"));
					list.add(rs.getString("tothol"));
					long cost = Integer.parseInt(rs.getString("cost").toString());
					long hol = Integer.parseInt(rs.getString("tothol").toString());
					long holQty = cost * hol;
					list.add(String.valueOf(holQty));
					list.add(rs.getString("appx_depr"));
					list.add(String.valueOf(holQty));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	
	
	//bisag 200623 V2(rectifying count of uh vs ue )
	//bisag 230623 V2(again rectifying count of uh vs ue )
	
	
		public ArrayList<ArrayList<String>> mms_ue_uh_list(String type_of_hldg, String formcodeType, String formcode, String p_code,
				String d_from, String d_to) {
			
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    	Connection conn = null;
			try{	  
				conn = dataSource.getConnection();
			    String Sql="";
			    String cnd="N";
			    String cndValue="";
			    String cndValue1="";
			    
			    if(formcode.length()>0){
			 		if(formcodeType.equalsIgnoreCase("UNIT")){
			 			cndValue=" a.sus_no=?";
			 			Sql =Sql+cndValue;
			 		} else {
			 			if(formcodeType.equalsIgnoreCase("COMMAND")){
			 				cndValue1="substr(form_code_control,1,1)=?)";
			 			}else if(formcodeType.equalsIgnoreCase("CORPS")) {
			 				cndValue1="substr(form_code_control,1,3)=?)";
			 			}else if(formcodeType.equalsIgnoreCase("DIV")) {
			 				cndValue1="substr(form_code_control,1,6)=?)";
			 			}else if(formcodeType.equalsIgnoreCase("BDE")) {
			 				cndValue1="substr(form_code_control,1,10)=?)";
			 			} else if(formcodeType.equalsIgnoreCase("LINE")) {
			 				cndValue1=" arm_code=?)";
			 			}
			 			cndValue =" a.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue1;
			 		}
		     	}

			    Sql = "select ab.short_form as comd,ac.coprs_name,ad.div_name,ae.bde_name,o1.unit_name,pr.prf_group,i.item_type as item_nomen,r.census_no,m.nomen,t1.label as type_of_hldg_n,r.totue,r.totuh  \n";
			    Sql = Sql + " from (select p.sus_no,p.prf_code,p.item_code,max(p.census_no) as census_no,p.type_of_hldg,p.type_of_eqpt,sum(cast (ueqty as int)) as totue,sum(cast (uhqty as int)) as totuh \n";
			    Sql = Sql + " from (select a.sus_no,b.prf_code,b.item_code,a.census_no,a.type_of_hldg,a.type_of_eqpt,0 as ueqty,a.tothol as uhqty\n";
			    Sql = Sql + " from mms_tv_regn_mstr a,mms_tb_mlccs_mstr_detl b where a.census_no=b.census_no \n ";
			    if (cndValue.length()>0){
			    	Sql=Sql + " and "+ cndValue +" \n";
			    }
			    
			    Sql = Sql + " union all \n";
			    Sql = Sql + " select a.sus_no,a.prf_group_code as prf_code,a.item_code,'00000000' as census_no,'A0' as type_of_hldg,upper(a.type) as type_of_eqpt,a.total_ue_qty as ueqty,0 as uhqty from mms_ue_mview a \n ";
			    if (cndValue.length()>0) {
			    	Sql=Sql + " where "+cndValue +" \n";
			    }
			    Sql = Sql + " ) p \n ";
			    Sql = Sql + " group by p.sus_no,p.prf_code,p.item_code,p.type_of_hldg,p.type_of_eqpt) r \n";
			    Sql = Sql + " left join mms_tb_mlccs_mstr_detl m on r.census_no=m.census_no \n";
			    Sql = Sql + " left join cue_tb_miso_mms_item_mstr i on r.item_code=i.item_code \n";
			    Sql = Sql + " left join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code \n";
			    Sql = Sql + " left join mms_domain_values t1 on r.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \n";
			    Sql = Sql + " left join tb_miso_orbat_unt_dtl o1 on r.sus_no=o1.sus_no and o1.status_sus_no='Active' \n";
				Sql = Sql + " left join nrv_hq hq on o1.form_code_control=hq.form_code_control \n";
				Sql = Sql + " LEFT JOIN orbat_view_cmd ab ON substr(o1.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"; 
				Sql = Sql + " LEFT JOIN orbat_view_corps ac ON substr(o1.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"; 
				Sql = Sql + " LEFT JOIN orbat_view_div ad ON substr(o1.form_code_control::text, 4, 3) = ad.div_code::text\r\n";
				Sql = Sql + " LEFT JOIN orbat_view_bde ae ON substr(o1.form_code_control::text, 7, 4)  = ae.bde_code::text \n ";
				 	
			 	if(!p_code.equalsIgnoreCase("ALL")){
			 		if(cnd.equalsIgnoreCase("N")){
			 			Sql =Sql +" where ";
		     		}else{
		     			Sql =Sql +" and ";
		     		}
		     		Sql =Sql +" (pr.prf_group_code=? or r.prf_code=?)";	
		     		cnd="Y";
		     	}
			 	
			 	if(!type_of_hldg.equalsIgnoreCase("ALL")){
		     		if(cnd.equalsIgnoreCase("N")){
		     			Sql = Sql +" where ";
		     		}else{
		     			Sql = Sql +" and ";
		     		}
		     		Sql = Sql +" r.type_of_hldg=?";	
		     		cnd="Y";
		     	}
			 	Sql = Sql + "  order by r.sus_no,r.prf_code,r.item_code,r.census_no,r.type_of_hldg,r.type_of_eqpt";
			    
			 	PreparedStatement stmt = conn.prepareStatement(Sql);
			    int g1=1;
			    if(formcode.length()>0){
			    	if(formcodeType.equalsIgnoreCase("UNIT")){
			 			stmt.setString(g1, formcode);
			 			g1++;
			 			stmt.setString(g1, formcode);
			 		} else {
				    	if(formcodeType.equalsIgnoreCase("COMMAND")){
				    		stmt.setString(g1, formcode.substring(0,1));
				    		g1++;
				    		stmt.setString(g1, formcode.substring(0,1));
			     		}else if(formcodeType.equalsIgnoreCase("CORPS")) {
			     			stmt.setString(g1, formcode.substring(0,3));
			     			g1++;
			     			stmt.setString(g1, formcode.substring(0,3));
			     		}else if(formcodeType.equalsIgnoreCase("DIV")) {
			     			stmt.setString(g1, formcode.substring(0,6));
			     			g1++;
			     			stmt.setString(g1, formcode.substring(0,6));
			     		}else if(formcodeType.equalsIgnoreCase("BDE")) {
			     			stmt.setString(g1, formcode.substring(0,10));
			     			g1++;
			     			stmt.setString(g1, formcode.substring(0,10));
			     		}else if(formcodeType.equalsIgnoreCase("LINE")) {
			     			stmt.setString(g1, formcode);
			     			g1++;
			     			stmt.setString(g1, formcode);
			     		}
			 		}
			    	g1++;
			    }
			    if(!p_code.equalsIgnoreCase("ALL")){
			    	stmt.setString(g1, p_code);
			    	g1++;
			    	stmt.setString(g1, p_code);
			    	g1++;
			    }
			    if(!type_of_hldg.equalsIgnoreCase("ALL")){
			    	stmt.setString(g1, type_of_hldg);
			    }
			    
			  System.out.println("===="+stmt);
			    ResultSet rs = stmt.executeQuery();   
				if(!rs.isBeforeFirst()) {	
					ArrayList<String> list = new ArrayList<String>();
					list.add("NIL");
					li.add(list); 
				}else{
					while(rs.next()){
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("prf_group")); 		//0
						list.add(rs.getString("item_nomen")); 		//1
						list.add(rs.getString("census_no")); 		//2
						list.add(rs.getString("nomen")); 			//3
						list.add(rs.getString("type_of_hldg_n"));	//4
						list.add("1");								//5
						int tue=Integer.parseInt(rs.getString("totue")); 	
			        	int tuh=Integer.parseInt(rs.getString("totuh"));	
			        	int tdefi= tue-tuh;								
						list.add(String.valueOf(tue));					//6
						list.add(String.valueOf(tuh));					//7
						if(tdefi == 0) {								
							list.add("NIL");							//8
						}else {
							list.add(String.valueOf(tdefi));			//8
						}
						list.add(rs.getString("unit_name"));			//9
						list.add("00000000");						//10
						list.add(rs.getString("comd"));				//11
						list.add(rs.getString("coprs_name"));			//12
						list.add(rs.getString("div_name"));			//13
						list.add(rs.getString("bde_name"));			//14
						li.add(list);  
					}
				}
				rs.close();
	     		stmt.close();
	     		conn.close();
	     }catch(SQLException e){
	    	 e.printStackTrace();
	     }	
	     return li;
	   }




	public List<String> mmsereg(String nParaValue) {
		List<String> list2 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = "select p.material_no,p.census_no,m.nomen,p.type_of_hldg,t1.label as type_of_hldg_n,p.type_of_eqpt,t2.label as type_of_eqpt_n,m.induc_year,'' as appx_depr,p.unit_price as cost,p.tothol,(p.unit_price*p.tothol) as gtot  from"
					+ " ("
					+ " select '1' as material_no,a.census_no,a.type_of_hldg,a.type_of_eqpt,b.unit_price,a.tothol from (select sus_no,census_no,type_of_hldg,type_of_eqpt,tothol from mms_tv_regn_mstr) a full outer join "
					+ " (select census_no,type_of_hldg,type_of_eqpt,eqpt_batch_no,max(unit_price) as unit_price from mms_tb_regn_mstr"
					+ " group by census_no,type_of_hldg,type_of_eqpt,eqpt_batch_no) b on (a.census_no=b.census_no and a.type_of_hldg=b.type_of_hldg and a.type_of_eqpt=b.type_of_eqpt)"
					+ " ) p" + " left join mms_tb_mlccs_mstr_detl m on p.census_no=m.census_no"
					+ " left join mms_domain_values t1 on p.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'"
					+ " left join mms_domain_values t2 on p.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			String nrStr = "";
			while (rs.next()) {
				nrStr = nrStr + rs.getString("material_no");
				nrStr = nrStr + ":" + rs.getString("census_no");
				nrStr = nrStr + ":" + rs.getString("nomen");
				nrStr = nrStr + ":" + rs.getString("induc_year");
				nrStr = nrStr + ":" + rs.getString("nomen");
				nrStr = nrStr + ":" + rs.getString("cost");
				nrStr = nrStr + ":" + rs.getString("tothol");
				nrStr = nrStr + ":" + rs.getString("gtot");
				nrStr = nrStr + ":" + rs.getString("appx_depr");
				nrStr = nrStr + ":" + rs.getString("gtot");
				nrStr = nrStr + ",";
			}
			list2.add(nrStr);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list2;
	}

	public ArrayList<ArrayList<String>> mms_ue_uh_summ(String type_of_hldg, String formcodeType, String formcode,
			String p_code, String d_from, String d_to) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			String cnd = "N";
			String cndValue = "";
			String cndValue1 = "";
			String ccndValue = "";
			String ccndValue1 = "";

			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
						ccndValue = ccndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
						ccndValue = ccndValue + " and ";
					}
					cndValue = cndValue + " a.sus_no=?";
					ccndValue = ccndValue + " q.sus_no=?";
					cnd = "Y";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue1 = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue1 = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue1 = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue1 = "substr(form_code_control,1,10)=?)";
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						cndValue1 = " arm_code=?)";
					}
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
						ccndValue = ccndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
						ccndValue = ccndValue + " and ";
					}
					cnd = "Y";
					cndValue = cndValue
							+ " a.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue1;
					ccndValue = ccndValue
							+ " q.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue1;
				}
			}

			if (!p_code.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
					ccndValue = ccndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
					ccndValue = ccndValue + " and ";
				}
				
				
				String add_q = "";

				for (int d = 0; d < p_code.substring(0, p_code.length() -1).split(",").length; d++) {
					add_q += "?,";
				}

			

				add_q = add_q.substring(0, add_q.length() - 1);
				
				
				cndValue = cndValue + " a.census_no in (select census_no from mms_tb_mlccs_mstr_detl where prf_code::integer in ("+add_q+"))";
				ccndValue = ccndValue + " q.prf_group_code::integer in ("+add_q+")";
				cnd = "Y";
			}

			if (!type_of_hldg.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
				}
				cndValue = cndValue + " a.type_of_hldg=?";
				cnd = "Y";
			}

			nrSql = "select distinct b.form_code_control,max(c.unit_name) as hq_name,a.prf_code,max(prf.prf_group) as prf_group,a.type_of_hldg,";
			nrSql = nrSql
					+ " max(t1.label) as type_of_hldg_n,a.type_of_eqpt,max(t2.label) as type_of_eqpt_n,sum(a.totue) as totue,sum(a.totuh) as totuh,max(upd_date) as upd_date from";
			nrSql = nrSql + " (";
			nrSql = nrSql
					+ " select p.sus_no,p.prf_code,p.item_code,p.type_of_hldg,p.type_of_eqpt,sum(p.ue) as totue,sum(p.uh) as totuh,max(upd_date) as upd_date from (";
			nrSql = nrSql
					+ " select a.sus_no,m.prf_code,m.item_code,a.type_of_hldg,a.type_of_eqpt,0 as ue,a.tothol as uh,a.upd_date from mms_tv_regn_mstr a ";
			nrSql = nrSql + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
			nrSql = nrSql + cndValue;
			if (type_of_hldg.equalsIgnoreCase("A0") || type_of_hldg.equalsIgnoreCase("ALL")) {
				nrSql = nrSql + " union all ";
				nrSql = nrSql
						+ " select q.sus_no,q.prf_group_code as prf_code,q.item_code,'A0' as type_of_hldg,(case when q.type='CES' then '2' else '1' end) as type_of_eqpt,q.total_ue_qty as ue,0 as uh,cast('2019-01-01' as date) as upd_date from mms_ue_mview q ";
				nrSql = nrSql + ccndValue;
			}
			nrSql = nrSql + " ) p group by p.sus_no,p.prf_code,p.item_code,p.type_of_hldg,p.type_of_eqpt";
			nrSql = nrSql + " ) a ";
			nrSql = nrSql + " left join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no and b.status_sus_no='Active'";
			nrSql = nrSql + " left join cue_tb_miso_prf_group_mst prf on a.prf_code=prf.prf_group_code";
			nrSql = nrSql
					+ " left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
			nrSql = nrSql
					+ " left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
			nrSql = nrSql + " left join nrv_hq c on b.form_code_control=c.form_code_control";
			nrSql = nrSql + " group by b.form_code_control,a.prf_code,a.type_of_hldg,a.type_of_eqpt";
			nrSql = nrSql + "  order by b.form_code_control,a.prf_code,a.type_of_hldg,a.type_of_eqpt";

			PreparedStatement stmt = conn.prepareStatement(nrSql);
		

			int g1 = 1;
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					stmt.setString(g1, formcode);
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(g1, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(g1, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(g1, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(g1, formcode.substring(0, 10));
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						stmt.setString(g1, formcode);
					}
				}
				g1++;
			}



			
			if (!p_code.equalsIgnoreCase("ALL")) {
                for (int d = 0; d < p_code.substring(0, p_code.length() - 1).split(",").length; d++) {
                        stmt.setInt(g1, Integer.parseInt(p_code.substring(0, p_code.length() ).split(",")[d]));
                        
                        g1++;
                }
                for (int d = 0; d < p_code.substring(0, p_code.length() - 1).split(",").length; d++) {
                        stmt.setInt(g1, Integer.parseInt(p_code.substring(0, p_code.length() ).split(",")[d]));
                       
                        g1++;

//                        g1++;
                }
                g1--;

//                stmt.setString(g1, p_code);
//                g1++;
        }
			
			
			
			
			
			
			
			if (!type_of_hldg.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, type_of_hldg);
				g1++;
			}

			if (type_of_hldg.equalsIgnoreCase("A0") || type_of_hldg.equalsIgnoreCase("ALL")) {
				if (formcode.length() > 0) {
					if (formcodeType.equalsIgnoreCase("UNIT")) {
						stmt.setString(g1, formcode);
					} else {
						if (formcodeType.equalsIgnoreCase("COMMAND")) {
							stmt.setString(g1, formcode.substring(0, 1));
						} else if (formcodeType.equalsIgnoreCase("CORPS")) {
							stmt.setString(g1, formcode.substring(0, 3));
						} else if (formcodeType.equalsIgnoreCase("DIV")) {
							stmt.setString(g1, formcode.substring(0, 6));
						} else if (formcodeType.equalsIgnoreCase("BDE")) {
							stmt.setString(g1, formcode.substring(0, 10));
						} else if (formcodeType.equalsIgnoreCase("LINE")) {
							stmt.setString(g1, formcode);
						}
					}
					g1++;
				}

//				if (!p_code.equalsIgnoreCase("ALL")) {
//					stmt.setString(g1, p_code);
//					g1++;
//				}
			}
			

			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("hq_name"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("type_of_hldg_n"));
					list.add(rs.getString("type_of_eqpt_n"));
					Double tue = Double.parseDouble(rs.getString("totue"));
					Double tuh = Double.parseDouble(rs.getString("totuh"));
					Double tdefi = tue - tuh;
					list.add(String.valueOf(tue));
					list.add(String.valueOf(tuh));
					if (tdefi > 0) {
						list.add("");
						list.add(String.valueOf(Math.abs(tdefi)));
					}
					if (tdefi <= 0) {
						list.add(String.valueOf(Math.abs(tdefi)));
						list.add("");
					}

					/*
					 * list.add(rs.getString("prf_code")); list.add(rs.getString("prf_group"));
					 * list.add(rs.getString("type_of_hldg_n"));
					 * list.add(rs.getString("type_of_eqpt_n")); Double
					 * tue=Double.parseDouble(rs.getString("totue")); Double
					 * tuh=Double.parseDouble(rs.getString("totuh")); Double tdefi= tue-tuh;
					 * list.add(String.valueOf(tue)); list.add(String.valueOf(tuh)); if(tdefi > 0) {
					 * list.add(String.valueOf(tdefi)); list.add(""); } if(tdefi <= 0){
					 * list.add(""); list.add(String.valueOf(tdefi)); }
					 * list.add(rs.getString("form_code_control"));
					 * list.add(rs.getString("hq_name")); list.add(rs.getString("upd_date"));
					 */
					li.add(list);
				}
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	/*
	 * public ArrayList<ArrayList<String>> wep_eqpt_status_details(String command,
	 * String type_wep_eqpt, String prf_code) { String type_wep_eqpt_whr ="";
	 * if(!type_wep_eqpt.equals("")) { type_wep_eqpt_whr
	 * =" and item.item_group = ? "; } String prf_whr =""; if(!prf_code.equals("0"))
	 * { prf_whr = " and b.prf_code=? "; } String fmn_whr ="";
	 * if(!command.equals("")) { fmn_whr = " and u.form_code_control like ? "; }
	 * ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	 * Connection conn = null; try { conn = dataSource.getConnection(); String sql =
	 * "";
	 * 
	 * sql = "select \r\n" + "u.unit_name,prf.prf_group,\r\n" +
	 * "c.short_form as comd,\r\n" + "	corps.coprs_name as corps,\r\n" +
	 * "	div.div_name as div,\r\n" + "	bde.bde_name as bde,"+
	 * "cast(sum(m.total_ue_qty) as decimal(15,2)) as ue,\r\n" +
	 * "COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text),0) as UH,\r\n"
	 * +
	 * "COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A14'::text),0) as LS,\r\n"
	 * +
	 * "COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A5'::text),0) as SS,\r\n"
	 * +
	 * "COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A16'::text),0) as AC,\r\n"
	 * +
	 * "COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'R0'::text and r.d_type = 'UH'),0) as wwr_unit,\r\n"
	 * +
	 * "COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'R0'::text and r.type_of_hldg = 'R6'::text and r.d_type = 'DH'),0) as wwr_depot,\r\n"
	 * + "COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text \r\n" +
	 * "	or r.type_of_hldg = 'A14'\r\n" + "	or r.type_of_hldg = 'A5'\r\n" +
	 * "	or r.type_of_hldg = 'A16'\r\n" +
	 * "	or (r.type_of_hldg = 'R0'::text and r.d_type = 'UH')\r\n" +
	 * "	or (r.type_of_hldg = 'R0'::text and r.type_of_hldg = 'R6'::text and r.d_type = 'DH')),0) as total_uh \r\n"
	 * + "from mms_tv_regn_mstr r\r\n" +
	 * "inner join mms_tb_mlccs_mstr_detl b on r.census_no=b.census_no "+ prf_whr +
	 * " \r\n " +
	 * "inner join mms_ue_mview m on r.sus_no=m.sus_no and b.prf_code=m.prf_group_code\r\n"
	 * +
	 * "inner join cue_tb_miso_mms_item_mstr item on b.prf_code=item.prf_group_code "
	 * +type_wep_eqpt_whr+" \r\n" +
	 * "inner join tb_miso_orbat_unt_dtl u ON r.sus_no = u.sus_no and u.status_sus_no ='Active' "
	 * +fmn_whr+" \r\n" +
	 * "inner join cue_tb_miso_prf_group_mst prf on b.prf_code = prf.prf_group_code\r\n"
	 * +
	 * "left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n"
	 * +
	 * "left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n"
	 * +
	 * "left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n"
	 * +
	 * "left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code \r\n"
	 * + "group by 1,2,3,4,5,6\r\n" + "order by 1,2";
	 * 
	 * 
	 * PreparedStatement stmt = conn.prepareStatement(sql); int flag = 0;
	 * if(!prf_code.equals("0")) { flag = flag + 1; stmt.setString(flag,prf_code); }
	 * if(!type_wep_eqpt.equals("")) { flag = flag + 1;
	 * stmt.setString(flag,type_wep_eqpt); } if(!command.equals("")) { flag = flag +
	 * 1; stmt.setString(flag, command+"%"); } ResultSet rs = stmt.executeQuery();
	 * while (rs.next()) { ArrayList<String> list1 = new ArrayList<String>();
	 * list1.add(rs.getString("unit_name")); list1.add(rs.getString("prf_group"));
	 * list1.add(rs.getString("comd")); list1.add(rs.getString("corps"));
	 * list1.add(rs.getString("div")); list1.add(rs.getString("bde"));
	 * list1.add(rs.getString("ue")); list1.add(rs.getString("UH"));
	 * list1.add(rs.getString("LS")); list1.add(rs.getString("SS"));
	 * list1.add(rs.getString("AC")); list1.add(rs.getString("wwr_unit"));
	 * list1.add(rs.getString("wwr_depot")); list1.add(rs.getString("total_uh"));
	 * list.add(list1); } rs.close(); stmt.close(); conn.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } finally { if (conn != null) { try {
	 * conn.close(); } catch (SQLException e) { } } } return list; }
	 */

	public ArrayList<ArrayList<String>> wep_eqpt_status_details(String command, String type_wep_eqpt, String prf_code,
			String line_dte_sus) {
		String prf_whr = "";
		String prf_whr1 = "";
		if (!prf_code.equals("0")) {
			prf_whr = " and b.prf_code=? ";
			prf_whr1 = " m.prf_group_code=? ";
		} else {
			prf_whr = " and b.prf_code in (select distinct prf_group_code,prf_group from cue_tb_miso_mms_item_mstr item where item_group=? and prf_group_code is not null)";
			prf_whr1 = " m.prf_group_code in (select distinct prf_group_code,prf_group from cue_tb_miso_mms_item_mstr item where item_group=? and prf_group_code is not null)";
		}
		String fmn_whr = "";
		String fmn_whr1 = "";
		if (!command.equals("")) {
			fmn_whr = " and rg.form_code_control like ? ";
			fmn_whr1 = " and m.form_code_control like ? ";
		}

		String whr = "";
		if (!line_dte_sus.equals("") & !line_dte_sus.equals("0")) {
			whr = " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
		}

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = " select c.short_form as comd, \r\n" + " corps.coprs_name as corps, \r\n"
					+ " div.div_name as div, \r\n" + " bde.bde_name as bde,r.sus_no,u.unit_name,prf.prf_group,\r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'UE'::text),0) as UE, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text),0) as UH, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A14'::text),0) as LS, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A5'::text),0) as SS, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A16'::text),0) as AC, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE (r.type_of_hldg = 'R0'::text or r.type_of_hldg = 'R1'::text) and r.d_type = 'UH'),0) as wwr_unit,\r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE (r.type_of_hldg ='R6' or r.type_of_hldg = 'R0'::text) and r.d_type = 'DH'),0) as wwr_depot,\r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text \r\n"
					+ " or r.type_of_hldg = 'A14' \r\n" + " or r.type_of_hldg = 'A5' \r\n"
					+ " or r.type_of_hldg = 'A16' \r\n" + " or (r.type_of_hldg = 'R0'::text and r.d_type = 'UH') \r\n"
					+ " or (r.type_of_hldg in ('R0','R6') and r.d_type = 'DH')),0) as total_uh\r\n" + " from \r\n"
					+ " (select rg.d_type,rg.sus_no,b.prf_code,rg.type_of_hldg,rg.tothol from mms_tv_regn_mstr rg \r\n"
					+ " inner join mms_tb_mlccs_mstr_detl b on rg.census_no=b.census_no " + prf_whr + fmn_whr
					+ " inner join tb_miso_orbat_unt_dtl u ON rg.sus_no = u.sus_no and u.status_sus_no ='Active' " + whr
					+ " union all \r\n"
					+ " select 'UE' as d_type,m.sus_no,m.prf_group_code as prf_code,'UE' as type_of_hldg,m.total_ue_qty from mms_ue_mview m\r\n"
					+ " inner join tb_miso_orbat_unt_dtl u ON m.sus_no = u.sus_no and u.status_sus_no ='Active' " + whr
					+ " where " + prf_whr1 + fmn_whr1 + ") r\r\n"
					+ " inner join tb_miso_orbat_unt_dtl u ON r.sus_no = u.sus_no and u.status_sus_no ='Active'\r\n"
					+ " inner join cue_tb_miso_prf_group_mst prf on r.prf_code = prf.prf_group_code \r\n"
					+ " left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code \r\n"
					+ " left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code \r\n"
					+ " left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code \r\n"
					+ " left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n"
					+ " group by 1,2,3,4,5,6,7 " + " order by 1,2,3,4,6,7";

			PreparedStatement stmt = conn.prepareStatement(sql);
			int flag = 0;
			if (!prf_code.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, prf_code);
			}
			if (!command.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, command + "%");
			}
			if (!line_dte_sus.equals("") & !line_dte_sus.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus);
			}
			if (!line_dte_sus.equals("") & !line_dte_sus.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus);
			}
			if (!prf_code.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, prf_code);
			}
			if (!command.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, command + "%");
			}

			
	

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("unit_name"));
				list1.add(rs.getString("prf_group"));
				list1.add(rs.getString("comd"));
				list1.add(rs.getString("corps"));
				list1.add(rs.getString("div"));
				list1.add(rs.getString("bde"));
				list1.add(rs.getString("ue"));
				list1.add(rs.getString("UH"));
				list1.add(rs.getString("LS"));
				list1.add(rs.getString("SS"));
				list1.add(rs.getString("AC"));
				list1.add(rs.getString("wwr_unit"));
				list1.add(rs.getString("wwr_depot"));
				list1.add(rs.getString("total_uh"));
				list.add(list1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}

	public List<Map<String, Object>> getPRFListbyItemGroup(String type, HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String qry = "";
			qry = "Select distinct prf.prf_group_code,prf.prf_group from \r\n" + 
					"cue_tb_miso_prf_group_mst prf  \r\n" + 
					"inner join T_Domain_Value t1 on  t1.codevalue= ? \r\n" + 
					"inner join cue_tb_miso_mms_item_mstr item\r\n" + 
					"on prf.prf_group_code=item.prf_group_code and item.item_group = t1.label\r\n" + 
					"where  t1.domainid = 'ITEMGROUP' \r\n" + 
					"order by prf.prf_group ";

			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, type);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}

	public List<Map<String, Object>> wep_eqpt_statusList(String command, String type_wep_eqpt, String prf_code,
			String line_dte_sus, String sus_no_list) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		try {
			String prf_whr = "";
			String prf_whr1 = "";
			if (!prf_code.equals("")) {

				String add_q = "";

				for (int d = 0; d < prf_code.substring(0, prf_code.length() - 1).split(",").length; d++) {
					add_q += "?,";
				}

				

				add_q = add_q.substring(0, add_q.length() - 1);

				prf_whr = " and b.prf_code::integer in ("+add_q+") ";
				prf_whr1 = " m.prf_group_code::integer in ("+add_q+") ";
			} else {
				prf_whr = " and b.prf_code in (select distinct prf_group_code,prf_group from cue_tb_miso_mms_item_mstr item where item_group=? and prf_group_code is not null)";
				prf_whr1 = " m.prf_group_code in (select distinct prf_group_code,prf_group from cue_tb_miso_mms_item_mstr item where item_group=? and prf_group_code is not null)";
			}
			String fmn_whr = "";
			String fmn_whr1 = "";
			if (!command.equals("")) {
				fmn_whr = " and rg.form_code_control like ? ";
				fmn_whr1 = " and m.form_code_control like ? ";
			}

			String whr = "";
			if (!line_dte_sus.equals("") & !line_dte_sus.equals("0")) {
				whr = " and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
			}

			if (!sus_no_list.equals("")) {
				String sus_noArray[] = sus_no_list.split(":");
				for (int i = 0; i < sus_noArray.length; i++) {
					if (i == 0) {
						whr += " and ( u.sus_no = ? ";
					} else {
						whr += " or u.sus_no = ? ";
					}

					if (i == (sus_noArray.length - 1)) {
						whr += " ) ";
					}
				}
			}

			conn = dataSource.getConnection();
			String sql = "";

			sql = " select c.short_form as comd, \r\n" + " corps.coprs_name as corps, \r\n"
					+ " div.div_name as div, \r\n" + " bde.bde_name as bde,r.sus_no,u.unit_name,prf.prf_group,\r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'UE'::text),0) as UE, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text),0) as UH, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A14'::text),0) as LS, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A5'::text),0) as SS, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A16'::text),0) as AC, \r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE (r.type_of_hldg = 'R0'::text or r.type_of_hldg = 'R1'::text) and r.d_type = 'UH'),0) as wwr_unit,\r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE (r.type_of_hldg ='R6' or r.type_of_hldg = 'R0'::text) and r.d_type = 'DH'),0) as wwr_depot,\r\n"
					+ " COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text \r\n"
					+ " or r.type_of_hldg = 'A14' \r\n" + " or r.type_of_hldg = 'A5' \r\n"
					+ " or r.type_of_hldg = 'A16' \r\n" + " or (r.type_of_hldg = 'R0'::text and r.d_type = 'UH') \r\n"
					+ " or (r.type_of_hldg in ('R0','R6') and r.d_type = 'DH')),0) as total_uh\r\n" + " from \r\n"
					+ " (select rg.d_type,rg.sus_no,b.prf_code,rg.type_of_hldg,rg.tothol from mms_tv_regn_mstr rg \r\n"
					+ " inner join mms_tb_mlccs_mstr_detl b on rg.census_no=b.census_no " + prf_whr + fmn_whr
					+ " inner join tb_miso_orbat_unt_dtl u ON rg.sus_no = u.sus_no and u.status_sus_no ='Active' " + whr
					+ " union all \r\n"
					+ " select 'UE' as d_type,m.sus_no,m.prf_group_code as prf_code,'UE' as type_of_hldg,m.total_ue_qty from mms_ue_mview m\r\n"
					+ " inner join tb_miso_orbat_unt_dtl u ON m.sus_no = u.sus_no and u.status_sus_no ='Active' " + whr
					+ " where " + prf_whr1 + fmn_whr1 + ") r\r\n"
					+ " inner join tb_miso_orbat_unt_dtl u ON r.sus_no = u.sus_no and u.status_sus_no ='Active'\r\n"
					+ " inner join cue_tb_miso_prf_group_mst prf on r.prf_code = prf.prf_group_code \r\n"
					+ " left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code \r\n"
					+ " left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code \r\n"
					+ " left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code \r\n"
					+ " left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n"
					+ " group by 1,2,3,4,5,6,7 " + " order by 1,2,3,4,6,7";
			
			
	

			PreparedStatement stmt = conn.prepareStatement(sql);
			int flag = 0;
			if (!prf_code.equals("")) {
				String[] prf_code_array = prf_code.split(",");
				for (int d = 0; d < prf_code_array.length; d++) {
					flag = flag + 1;
					stmt.setInt(flag, Integer.parseInt(prf_code_array[d]));
				}
			
			}
			if (!command.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, command + "%");
			}
			if (!line_dte_sus.equals("") & !line_dte_sus.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus);
			}
			if (!sus_no_list.equals("")) {
			String sus_noArray[] = sus_no_list.split(":");
			for (int i = 0; i < sus_noArray.length; i++) {
				flag = flag + 1;
				stmt.setString(flag, sus_noArray[i]);
			}
		}
			if (!line_dte_sus.equals("") & !line_dte_sus.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag, line_dte_sus);
			}
			if (!sus_no_list.equals("")) {
				String sus_noArray[] = sus_no_list.split(":");
				for (int i = 0; i < sus_noArray.length; i++) {
					flag = flag + 1;
					stmt.setString(flag, sus_noArray[i]);
				}
			}
			if (!prf_code.equals("")) {
				String[] prf_code_array = prf_code.split(",");
				for (int d = 0; d < prf_code_array.length; d++) {
					flag = flag + 1;
					stmt.setInt(flag, Integer.parseInt(prf_code_array[d]));
				}
			
			}
			
			if (!command.equals("")) {
				flag = flag + 1;
				stmt.setString(flag, command + "%");
			}


			

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	//RAJ NEW  22-10-2024 
	
	
public ArrayList<ArrayList<String>> cue_ue_list(String formcodeType, String formcode, String item_type, String m4_p_code,String arm_code) {
		
		
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
    	String Sql2= "";
    	String q = "";
    	
    	
    	try{	  
			conn = dataSource.getConnection();
		    String Sql="";
		    String cnd="N";
		    String cndValue="";
		    String cndValue1="";

			String cond = " where ";

		    
		    	
			
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
					}
					cndValue = cndValue + " a.sus_no=?";
					cnd = "Y";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue1 = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue1 = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue1 = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue1 = "substr(form_code_control,1,10)=?)";
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						cndValue1 = " left(armcode.arm_code,2) =  left(?,2))";
					}
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
					}
					cnd = "Y";
					cndValue = cndValue
							+ " a.sus_no in (select sus_no from tb_miso_orbat_unt_dtl armcode where status_sus_no='Active' and "
							+ cndValue1;
					
				}
			}
			

			
			if (!m4_p_code.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
					
				}
				cndValue = cndValue + "im.prf_group_code=?";
				cnd = "Y";
				
				}
			
			/*if(!item_type.equals("") && !item_type.equals("0") && !item_type.equals(0) ){
	     		if(cnd.equalsIgnoreCase("N")){
	     			cndValue +=" where ";
	     		}else{
	     			cndValue +=" and ";
	     		}
	     		cndValue += " im.item_code=?";	
	     		cnd="Y";	     		
	     	}*/
			
			if(!item_type.equals("") && !item_type.equals("0") && !item_type.equals(0) ){
	     		if(cnd.equalsIgnoreCase("N")){
	     			cndValue +=" where ";
	     		}else{
	     			cndValue +=" and ";
	     		}
	     		//cndValue += " im.item_code=?";	
	     		
	     		
	     		String itemList[] = item_type.split(",");
				String item = "";
				for (int i = 0; i < itemList.length; i++) {
					if (item.equals("")) {
						item = "'" + itemList[i] + "'";
					} else {
						item = item + ",'" + itemList[i] + "'";
					}
				}
				cndValue += " im.item_code in (" + item + ") ";
				cnd="Y";
	     	}
				
					
			if (!arm_code.equalsIgnoreCase("") && !arm_code.equalsIgnoreCase("0") && !arm_code.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
					
				}
				cndValue = cndValue + "left(armcode.arm_code,2) =  left(?,2)";
				cnd = "Y";
				
				}
		    
		     q ="SELECT udtl.form_code_control, ab.short_form as comd_name, ac.coprs_name as corps_name,ad.div_name, ae.bde_name,a.sus_no, udtl.unit_name,  \r\n" + 
					"	im.prf_group_code,prf.prf_group, a.item_seq_no AS item_code,  im.item_type,  ROUND(sum(a.auth_weapon)::decimal,2) AS total_ue_qty,   a.we_pe AS typeofauth,  a.authdoc as wepeno,  a.typeofmod AS type,\r\n" + 
					"	armcode.arm_code, armcode.arm_desc\r\n" + 
					"   FROM ( SELECT lsus.sus_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            wdet.we_pe_no,\r\n" + 
					"            wdet.item_seq_no,\r\n" + 
					"            wdet.auth_weapon,\r\n" + 
					"            'STD'::character varying AS modification,\r\n" + 
					"            wepe.we_pe, wepe.we_pe_no as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wdet.remarks\r\n" + 
					"           FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"             JOIN cue_tb_miso_wepe_weapon_det wdet ON wdet.we_pe_no::text = lsus.wepe_weapon_no::text\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"        UNION\r\n" + 
					"         SELECT smdfs.sus_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            smdfs.we_pe_no,\r\n" + 
					"            wmdfs.item_seq_no,\r\n" + 
					"            wmdfs.amt_inc_dec,\r\n" + 
					"            wmdfs.modification,\r\n" + 
					"            wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wmdfs.remarks\r\n" + 
					"           FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_weapon_mdfs smdfs ON smdfs.sus_no::text = lsus.sus_no::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_weapons_mdfs wmdfs ON smdfs.we_pe_no::text = wmdfs.we_pe_no::text AND smdfs.modification::text = wmdfs.modification::text\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"        UNION\r\n" + 
					"         SELECT lsus.sus_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            wfoot.item_seq_no,\r\n" + 
					"            wfoot.amt_inc_dec,\r\n" + 
					"            'FN'::character varying AS modification,\r\n" + 
					"            wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wfoot.condition AS remarks\r\n" + 
					"           FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_weapon_footnotes sfoot ON lsus.sus_no::text = sfoot.sus_no::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_weapon_footnotes wfoot ON wfoot.we_pe_no::text = sfoot.we_pe_no::text AND sfoot.fk_weapon_foot = wfoot.id\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"        UNION\r\n" + 
					"         SELECT lsus.sus_no,\r\n" + 
					"            wed.we_pe_no,\r\n" + 
					"            wetd.wet_pet_no,\r\n" + 
					"            wetd.item_seq_no,\r\n" + 
					"            wetd.\"authorization\",\r\n" + 
					"            'WET'::character varying AS modification,\r\n" + 
					"            wetd.wet_type, wed.wet_pet_no  as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wetd.remarks\r\n" + 
					"           FROM cue_tb_miso_mms_wet_pet_det wetd\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wed ON wed.wet_pet_no::text = wetd.wet_pet_no::text AND wed.type::text = '3'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon lsus ON lsus.wepe_weapon_no::text = wed.we_pe_no::text\r\n" + 
					"        UNION\r\n" + 
					"          SELECT wewetd.sus_no,  \r\n" + 
					"				 wewetd.wepe_weapon_no,ces.ces_cces_no,  ces.item_seq_no, \r\n" + 
					"				 ces.auth_proposed::double precision * wewetd.auth_weapon AS auth_weapon,  \r\n" + 
					"				 ces.ces_cces,'CES'::character varying AS ces1,  \r\n" + 
					"				  ces.ces_cces_no  as authqty,   \r\n" + 
					"				  'CES'::text AS typeofmod,  \r\n" + 
					"				concat(ces.ces_cces_name, ' Main Item ', ces.remarks) AS remarks  \r\n" + 
					"           FROM cue_tb_miso_ces ces\r\n" + 
					"             JOIN ( SELECT lsus.sus_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    wdet.we_pe_no,\r\n" + 
					"                    wdet.item_seq_no,\r\n" + 
					"                    wdet.auth_weapon,\r\n" + 
					"                    'STD'::character varying AS modification,\r\n" + 
					"                    wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wdet.remarks\r\n" + 
					"                   FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"                     JOIN cue_tb_miso_wepe_weapon_det wdet ON wdet.we_pe_no::text = lsus.wepe_weapon_no::text\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"                UNION\r\n" + 
					"                 SELECT smdfs.sus_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    smdfs.we_pe_no,\r\n" + 
					"                    wmdfs.item_seq_no,\r\n" + 
					"                    wmdfs.amt_inc_dec,\r\n" + 
					"                    wmdfs.modification,\r\n" + 
					"                    wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wmdfs.remarks\r\n" + 
					"                   FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"                     JOIN cue_tb_wepe_link_sus_weapon_mdfs smdfs ON smdfs.sus_no::text = lsus.sus_no::text\r\n" + 
					"                     JOIN cue_tb_miso_wepe_weapons_mdfs wmdfs ON smdfs.we_pe_no::text = wmdfs.we_pe_no::text AND smdfs.modification::text = wmdfs.modification::text\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"                UNION\r\n" + 
					"                 SELECT lsus.sus_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    wfoot.item_seq_no,\r\n" + 
					"                    wfoot.amt_inc_dec,\r\n" + 
					"                    'FN'::character varying AS modification,\r\n" + 
					"                    wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wfoot.condition AS remarks\r\n" + 
					"                   FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"                     JOIN cue_tb_wepe_link_sus_weapon_footnotes sfoot ON lsus.sus_no::text = sfoot.sus_no::text\r\n" + 
					"                     JOIN cue_tb_miso_wepe_weapon_footnotes wfoot ON wfoot.we_pe_no::text = sfoot.we_pe_no::text AND sfoot.fk_weapon_foot = wfoot.id\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"                UNION\r\n" + 
					"                 SELECT lsus.sus_no,\r\n" + 
					"                    wed.we_pe_no,\r\n" + 
					"                    wetd.wet_pet_no,\r\n" + 
					"                    wetd.item_seq_no,\r\n" + 
					"                    wetd.\"authorization\",\r\n" + 
					"                    'WET'::character varying AS modification,\r\n" + 
					"                    wetd.wet_type, wetd.wet_pet_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wetd.remarks\r\n" + 
					"                   FROM cue_tb_miso_mms_wet_pet_det wetd\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wed ON wed.wet_pet_no::text = wetd.wet_pet_no::text AND wed.type::text = '3'::text\r\n" + 
					"                     JOIN cue_tb_wepe_link_sus_perstransweapon lsus ON lsus.wepe_weapon_no::text = wed.we_pe_no::text) wewetd ON wewetd.item_seq_no::text = ces.ces_cces_name::text) a\r\n" + 
					"     JOIN tb_miso_orbat_unt_dtl udtl ON udtl.sus_no::text = a.sus_no::text AND udtl.status_sus_no::text = 'Active'::text\r\n" + 
					"     JOIN cue_tb_miso_mms_item_mstr im ON a.item_seq_no::text = im.item_code::text AND im.status_active::text = 'Active'::text AND im.status::text = '1'::text AND im.prf_group_code IS NOT NULL AND im.prf_group_code::text <> ''::text\r\n" + 
					"	 LEFT JOIN orbat_view_cmd ab ON substr(udtl.form_code_control::text, 1, 1) = ab.cmd_code::text \r\n" + 
					"	 LEFT JOIN orbat_view_corps ac ON substr(udtl.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
					"	 LEFT JOIN orbat_view_div ad ON substr(udtl.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
					"	 LEFT JOIN orbat_view_bde ae ON substr(udtl.form_code_control::text, 7, 4)  = ae.bde_code::text\r\n" + 
					"	 inner join cue_tb_miso_prf_group_mst prf on im.prf_group_code=prf.prf_group_code\r\n" + 
					"	 inner join tb_miso_orbat_arm_code armcode on armcode.arm_code= concat(left(udtl.arm_code,2),'00') \r\n"
					+ cndValue	+					
					" GROUP BY a.sus_no, udtl.unit_name, a.item_seq_no,im.item_type, a.we_pe, a.authdoc, a.typeofmod, \r\n" + 
					" udtl.form_code_control, ab.short_form , ac.coprs_name ,ad.div_name, ae.bde_name, \r\n" + 
					" im.prf_group_code,prf.prf_group,armcode.arm_desc,armcode.arm_code"  ;
		    		
			PreparedStatement stmt = conn.prepareStatement(q);
			
			
		    
		    int g1 = 1;
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					stmt.setString(g1, formcode);
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(g1, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(g1, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(g1, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(g1, formcode.substring(0, 10));
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						stmt.setString(g1, formcode);
					}
				}
				g1++;
			}
			if (!m4_p_code.equalsIgnoreCase("ALL")) {

	            stmt.setString(g1, m4_p_code);
	            g1++;
			}
			
			
			if(!item_type.equals("") && !item_type.equals("0") && !item_type.equals(0)){
		    	/*stmt.setString(g1, item_type);
		    	g1++; */  		    	
		    }
		    
		    
			if (!arm_code.equalsIgnoreCase("") && !arm_code.equalsIgnoreCase("0") && !arm_code.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, arm_code);
		    	g1++;   
				
				}
		  
		    
		    
		    
		    
		    
		    ResultSet rs = stmt.executeQuery();
		    System.err.println("===="+stmt);
    	
		    if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("prf_group")); 		//0
					list.add(rs.getString("prf_group_code")); 		//1
					list.add(rs.getString("unit_name")); 		//2
					list.add(rs.getString("sus_no")); 			//3
					list.add(rs.getString("typeofauth"));	//4
					list.add("1");								//5
					/*int tue=Integer.parseInt(rs.getString("totue")); 	
		        	int tuh=Integer.parseInt(rs.getString("totuh"));	
		        	int tdefi= tue-tuh;								
					list.add(String.valueOf(tue));					//
					list.add(String.valueOf(tuh));	*/				//
					list.add(rs.getString("wepeno"));			//6
					list.add("type");						//7
					list.add(rs.getString("comd_name"));				//8
					list.add(rs.getString("corps_name"));			//9
					list.add(rs.getString("div_name"));			//10
					list.add(rs.getString("bde_name"));		//11
					list.add(rs.getString("item_type"));   //12
					list.add(rs.getString("total_ue_qty")); //13
					li.add(list); 
					
				}
			}
			rs.close();
     		stmt.close();
     		conn.close();
     }catch(SQLException e){
    	 e.printStackTrace();
     }	
     return li;
}
	
	public ArrayList<ArrayList<String>> cue_summ_old( String formcodeType, String formcode,
			String p_code, String item_name) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			String cnd = "N";
			String cndValue = "";
			String cndValue1 = "";
			String ccndValue1 = "";

			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
					}
					cndValue = cndValue + " a.sus_no=?";
					cnd = "Y";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue1 = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue1 = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue1 = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue1 = "substr(form_code_control,1,10)=?)";
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						cndValue1 = " arm_code=?)";
					}
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
					}
					cnd = "Y";
					cndValue = cndValue
							+ " a.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue1;
					
				}
			}

			if (!p_code.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
					
				}
				cndValue = cndValue + "mview.prf_group_code=?";
				cnd = "Y";
				
				}
			
			
			/*if (!item_name.equalsIgnoreCase("ALL") && !item_name.equalsIgnoreCase("") && !item_name.equalsIgnoreCase("0")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
					
				}
				cndValue = cndValue + "mview.item_code=?";
				cnd = "Y";
				
				}*/
			
			if(!item_name.equals("") && !item_name.equals("0") && !item_name.equals(0) ){
	     		if(cnd.equalsIgnoreCase("N")){
	     			cndValue +=" where ";
	     		}else{
	     			cndValue +=" and ";
	     		}
	     		//cndValue += " im.item_code=?";	
	     		
	     		
	     		String itemList[] = item_name.split(",");
				String item = "";
				for (int i = 0; i < itemList.length; i++) {
					if (item.equals("")) {
						item = "'" + itemList[i] + "'";
					} else {
						item = item + ",'" + itemList[i] + "'";
					}
				}
				cndValue += " im.item_code in (" + item + ") ";
				cnd="Y";
	     	}
			
			/*nrSql = "SELECT\r\n"
					+ "     nc.item_code,\r\n"
					+ "    nc.item_type,ces.ces_cces_no,\r\n"
					+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)) FILTER (WHERE u.type_force = '0'), 0) AS ff,\r\n"
					+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)) FILTER (WHERE u.type_force = '1'), 0) AS nff,\r\n"
					+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)), 0) AS totalue\r\n"
					+ "FROM\r\n"
					+ "    mms_ue_mview mview\r\n"
					+ "INNER JOIN cue_tb_miso_prf_group_mst prf_m ON mview.prf_group_code = prf_m.prf_group_code\r\n"
					+ "INNER JOIN cue_tb_miso_mms_item_mstr nc ON nc.item_code = mview.item_code\r\n"
					+ "JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no = 'Active' AND mview.sus_no = u.sus_no\r\n"
					+ "JOIN tb_miso_orbat_codesform c ON c.formation_code = substr(mview.form_code_control, 1, 1)\r\n"
					+ "JOIN tb_miso_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active' AND c.level_in_hierarchy = 'Command'  \r\n"
					+ "join cue_tb_miso_ces ces on ces.item_seq_no=nc.item_code\r\n"
					+cndValue+ " GROUP BY nc.item_code,nc.item_type,ces.ces_cces_no";*/
			
			nrSql = "SELECT mview.item_code,nc.item_type, \r\n" + 
					
					" round(coalesce( sum(mview.total_ue_qty) filter (where a.type_force='0' and mview.type='MAIN') ,0)::decimal,2) AS FF, \r\n" + 
					" round(coalesce( sum(mview.total_ue_qty) filter (where a.type_force IN ('1','3') and mview.type='MAIN') ,0)::decimal,2) AS NFF, \r\n" + 
					" round(coalesce( sum(mview.total_ue_qty) filter (where mview.type='CES') ,0)::decimal,2) AS CES,\r\n" + 					
					" round(coalesce( sum(mview.total_ue_qty) filter (where a.type_force='0' and mview.type='MAIN') ,0)::decimal,2) + \r\n" + 
					" round(coalesce( sum(mview.total_ue_qty) filter (where a.type_force IN ('1','3') and mview.type='MAIN') ,0)::decimal,2) + \r\n" + 
					" round(coalesce( sum(mview.total_ue_qty) filter (where mview.type='CES') ,0)::decimal,2) AS TOTAL \r\n" + 					 
					"FROM mms_ue_mview mview\r\n" + 
					"left JOIN cue_tb_miso_mms_item_mstr nc ON nc.item_code = mview.item_code\r\n" + 
					"left JOIN cue_tb_miso_prf_group_mst prf_m ON mview.prf_group_code = prf_m.prf_group_code\r\n" + 
					"left JOIN tb_miso_orbat_codesform c ON c.formation_code = substr(mview.form_code_control, 1, 1)\r\n" + 
					"left JOIN tb_miso_orbat_unt_dtl a ON a.status_sus_no = 'Active' AND mview.sus_no = a.sus_no \r\n"  
					+ cndValue + "group by mview.item_code,nc.item_type";

			PreparedStatement stmt = conn.prepareStatement(nrSql);
			System.err.println(stmt);

			int g1 = 1;
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					stmt.setString(g1, formcode);
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(g1, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(g1, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(g1, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(g1, formcode.substring(0, 10));
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						stmt.setString(g1, formcode);
					}
				}
				g1++;
			}
			if (!p_code.equalsIgnoreCase("ALL")) {

	            stmt.setString(g1, p_code);
	            g1++;
			}
			
			if (!item_name.equalsIgnoreCase("ALL") && !item_name.equalsIgnoreCase("") && !item_name.equalsIgnoreCase("0")) {

	            /*stmt.setString(g1, item_name);
	            g1++;*/
			}

			

//				if (!p_code.equalsIgnoreCase("ALL")) {
//					stmt.setString(g1, p_code);
//					g1++;
//				}
			

			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("item_code"));
					list.add(rs.getString("item_type"));
					list.add(rs.getString("FF")); 
					list.add(rs.getString("NFF"));
					list.add(rs.getString("TOTAL"));
					list.add(rs.getString("CES"));
					li.add(list);
				}
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	
public ArrayList<ArrayList<String>> cue_summ(String formcodeType, String formcode, String item_type, String m4_p_code,String arm_code) {
		
		
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
    	String Sql2= "";
    	String q = "";
    	
    	
    	try{	  
			conn = dataSource.getConnection();
		    String Sql="";
		    String cnd="N";
		    String cndValue="";
		    String cndValue1="";

			String cond = " where ";

		    
		    	
			
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
					}
					cndValue = cndValue + " a.sus_no=?";
					cnd = "Y";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue1 = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue1 = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue1 = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue1 = "substr(form_code_control,1,10)=?)";
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						cndValue1 = " left(armcode.arm_code,2) =  left(?,2))";
					}
					if (cnd.equalsIgnoreCase("N")) {
						cndValue = cndValue + " where ";
					} else {
						cndValue = cndValue + " and ";
					}
					cnd = "Y";
					cndValue = cndValue
							+ " a.sus_no in (select sus_no from tb_miso_orbat_unt_dtl armcode where status_sus_no='Active' and "
							+ cndValue1;
					
				}
			}
			

			
			if (!m4_p_code.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
					
				}
				cndValue = cndValue + "im.prf_group_code=?";
				cnd = "Y";
				
				}
			
			/*if(!item_type.equals("") && !item_type.equals("0") && !item_type.equals(0) ){
	     		if(cnd.equalsIgnoreCase("N")){
	     			cndValue +=" where ";
	     		}else{
	     			cndValue +=" and ";
	     		}
	     		cndValue += " im.item_code=?";	
	     		cnd="Y";	     		
	     	}*/
			
			if(!item_type.equals("") && !item_type.equals("0") && !item_type.equals(0) ){
	     		if(cnd.equalsIgnoreCase("N")){
	     			cndValue +=" where ";
	     		}else{
	     			cndValue +=" and ";
	     		}
	     		//cndValue += " im.item_code=?";	
	     		
	     		
	     		String itemList[] = item_type.split(",");
				String item = "";
				for (int i = 0; i < itemList.length; i++) {
					if (item.equals("")) {
						item = "'" + itemList[i] + "'";
					} else {
						item = item + ",'" + itemList[i] + "'";
					}
				}
				cndValue += " im.item_code in (" + item + ") ";
				cnd="Y";
	     	}
				
					
			if (!arm_code.equalsIgnoreCase("") && !arm_code.equalsIgnoreCase("0") && !arm_code.equalsIgnoreCase("ALL")) {
				if (cnd.equalsIgnoreCase("N")) {
					cndValue = cndValue + " where ";
				} else {
					cndValue = cndValue + " and ";
					
				}
				cndValue = cndValue + "left(armcode.arm_code,2) =  left(?,2)";
				cnd = "Y";
				
				}
		    
		     q ="SELECT\r\n"
		     		+ "	a.item_seq_no AS item_code,\r\n"
		     		+ "	im.item_type,"
		     		+ "prf.prf_group,"
		     		+ "im.cos_sec_no, \r\n"
		     		
		     		+ " round(coalesce( sum(a.auth_weapon) filter (where udtl.type_force='0' and a.typeofmod='MAIN') ,0)::decimal,2) AS FF,\r\n"
		     		+ "	round(coalesce( sum(a.auth_weapon) filter (where udtl.type_force IN ('1','3') and a.typeofmod='MAIN') ,0)::decimal,2) AS NFF,\r\n"
		     		+ " round(coalesce( sum(a.auth_weapon) filter (where  a.typeofmod='CES') ,0)::decimal,2) AS CES	,\r\n"
		     		+ " round(coalesce( sum(a.auth_weapon) filter (where udtl.type_force='0' and  a.typeofmod='MAIN') ,0)::decimal,2) + \r\n"
		     		+ " round(coalesce( sum(a.auth_weapon) filter (where udtl.type_force IN ('1','3') and  a.typeofmod='MAIN') ,0)::decimal,2) + \r\n"
		     		+ " round(coalesce( sum(a.auth_weapon) filter (where  a.typeofmod='CES') ,0)::decimal,2) AS TOTAL   " + 
					"    FROM ( SELECT lsus.sus_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            wdet.we_pe_no,\r\n" + 
					"            wdet.item_seq_no,\r\n" + 
					"            wdet.auth_weapon,\r\n" + 
					"            'STD'::character varying AS modification,\r\n" + 
					"            wepe.we_pe, wepe.we_pe_no as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wdet.remarks\r\n" + 
					"           FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"             JOIN cue_tb_miso_wepe_weapon_det wdet ON wdet.we_pe_no::text = lsus.wepe_weapon_no::text\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"        UNION\r\n" + 
					"         SELECT smdfs.sus_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            smdfs.we_pe_no,\r\n" + 
					"            wmdfs.item_seq_no,\r\n" + 
					"            wmdfs.amt_inc_dec,\r\n" + 
					"            wmdfs.modification,\r\n" + 
					"            wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wmdfs.remarks\r\n" + 
					"           FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_weapon_mdfs smdfs ON smdfs.sus_no::text = lsus.sus_no::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_weapons_mdfs wmdfs ON smdfs.we_pe_no::text = wmdfs.we_pe_no::text AND smdfs.modification::text = wmdfs.modification::text\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"        UNION\r\n" + 
					"         SELECT lsus.sus_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            lsus.wepe_weapon_no,\r\n" + 
					"            wfoot.item_seq_no,\r\n" + 
					"            wfoot.amt_inc_dec,\r\n" + 
					"            'FN'::character varying AS modification,\r\n" + 
					"            wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wfoot.condition AS remarks\r\n" + 
					"           FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_weapon_footnotes sfoot ON lsus.sus_no::text = sfoot.sus_no::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_weapon_footnotes wfoot ON wfoot.we_pe_no::text = sfoot.we_pe_no::text AND sfoot.fk_weapon_foot = wfoot.id\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"        UNION\r\n" + 
					"         SELECT lsus.sus_no,\r\n" + 
					"            wed.we_pe_no,\r\n" + 
					"            wetd.wet_pet_no,\r\n" + 
					"            wetd.item_seq_no,\r\n" + 
					"            wetd.\"authorization\",\r\n" + 
					"            'WET'::character varying AS modification,\r\n" + 
					"            wetd.wet_type, wed.wet_pet_no  as authdoc,\r\n" + 
					"            'MAIN'::text AS typeofmod,\r\n" + 
					"            wetd.remarks\r\n" + 
					"           FROM cue_tb_miso_mms_wet_pet_det wetd\r\n" + 
					"             JOIN cue_tb_miso_wepeconditions wed ON wed.wet_pet_no::text = wetd.wet_pet_no::text AND wed.type::text = '3'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon lsus ON lsus.wepe_weapon_no::text = wed.we_pe_no::text\r\n" + 
					"        UNION\r\n" + 
					"          SELECT wewetd.sus_no,  \r\n" + 
					"				 wewetd.wepe_weapon_no,ces.ces_cces_no,  ces.item_seq_no, \r\n" + 
					"				 ces.auth_proposed::double precision * wewetd.auth_weapon AS auth_weapon,  \r\n" + 
					"				 ces.ces_cces,'CES'::character varying AS ces1,  \r\n" + 
					"				  ces.ces_cces_no  as authqty,   \r\n" + 
					"				  'CES'::text AS typeofmod,  \r\n" + 
					"				concat(ces.ces_cces_name, ' Main Item ', ces.remarks) AS remarks  \r\n" + 
					"           FROM cue_tb_miso_ces ces\r\n" + 
					"             JOIN ( SELECT lsus.sus_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    wdet.we_pe_no,\r\n" + 
					"                    wdet.item_seq_no,\r\n" + 
					"                    wdet.auth_weapon,\r\n" + 
					"                    'STD'::character varying AS modification,\r\n" + 
					"                    wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wdet.remarks\r\n" + 
					"                   FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"                     JOIN cue_tb_miso_wepe_weapon_det wdet ON wdet.we_pe_no::text = lsus.wepe_weapon_no::text\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"                UNION\r\n" + 
					"                 SELECT smdfs.sus_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    smdfs.we_pe_no,\r\n" + 
					"                    wmdfs.item_seq_no,\r\n" + 
					"                    wmdfs.amt_inc_dec,\r\n" + 
					"                    wmdfs.modification,\r\n" + 
					"                    wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wmdfs.remarks\r\n" + 
					"                   FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"                     JOIN cue_tb_wepe_link_sus_weapon_mdfs smdfs ON smdfs.sus_no::text = lsus.sus_no::text\r\n" + 
					"                     JOIN cue_tb_miso_wepe_weapons_mdfs wmdfs ON smdfs.we_pe_no::text = wmdfs.we_pe_no::text AND smdfs.modification::text = wmdfs.modification::text\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"                UNION\r\n" + 
					"                 SELECT lsus.sus_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    lsus.wepe_weapon_no,\r\n" + 
					"                    wfoot.item_seq_no,\r\n" + 
					"                    wfoot.amt_inc_dec,\r\n" + 
					"                    'FN'::character varying AS modification,\r\n" + 
					"                    wepe.we_pe, wepe.we_pe_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wfoot.condition AS remarks\r\n" + 
					"                   FROM cue_tb_wepe_link_sus_perstransweapon lsus\r\n" + 
					"                     JOIN cue_tb_wepe_link_sus_weapon_footnotes sfoot ON lsus.sus_no::text = sfoot.sus_no::text\r\n" + 
					"                     JOIN cue_tb_miso_wepe_weapon_footnotes wfoot ON wfoot.we_pe_no::text = sfoot.we_pe_no::text AND sfoot.fk_weapon_foot = wfoot.id\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wepe ON wepe.we_pe_no::text = lsus.wepe_weapon_no::text AND wepe.type::text = '3'::text\r\n" + 
					"                UNION\r\n" + 
					"                 SELECT lsus.sus_no,\r\n" + 
					"                    wed.we_pe_no,\r\n" + 
					"                    wetd.wet_pet_no,\r\n" + 
					"                    wetd.item_seq_no,\r\n" + 
					"                    wetd.\"authorization\",\r\n" + 
					"                    'WET'::character varying AS modification,\r\n" + 
					"                    wetd.wet_type, wetd.wet_pet_no  as authdoc,\r\n" + 
					"                    'MAIN'::text AS typeofmod,\r\n" + 
					"                    wetd.remarks\r\n" + 
					"                   FROM cue_tb_miso_mms_wet_pet_det wetd\r\n" + 
					"                     JOIN cue_tb_miso_wepeconditions wed ON wed.wet_pet_no::text = wetd.wet_pet_no::text AND wed.type::text = '3'::text\r\n" + 
					"                     JOIN cue_tb_wepe_link_sus_perstransweapon lsus ON lsus.wepe_weapon_no::text = wed.we_pe_no::text) wewetd ON wewetd.item_seq_no::text = ces.ces_cces_name::text) a\r\n" + 
					"     JOIN tb_miso_orbat_unt_dtl udtl ON udtl.sus_no::text = a.sus_no::text AND udtl.status_sus_no::text = 'Active'::text\r\n" + 
					"     JOIN cue_tb_miso_mms_item_mstr im ON a.item_seq_no::text = im.item_code::text AND im.status_active::text = 'Active'::text AND im.status::text = '1'::text AND im.prf_group_code IS NOT NULL AND im.prf_group_code::text <> ''::text\r\n" +				
					"	 inner join cue_tb_miso_prf_group_mst prf on im.prf_group_code=prf.prf_group_code\r\n" + 
					"	 inner join tb_miso_orbat_arm_code armcode on armcode.arm_code= concat(left(udtl.arm_code,2),'00') \r\n"
					+ cndValue	+					
					" GROUP BY\r\n"
					+ "a.item_seq_no,\r\n"					
					+ "prf.prf_group,\r\n"
					+ "	im.item_type,im.cos_sec_no  order by  im.cos_sec_no,prf.prf_group, a.item_seq_no ,\r\n" + 
					"	im.item_type \r\n"   ;
		    		
			PreparedStatement stmt = conn.prepareStatement(q);
			
			
		    
		    int g1 = 1;
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("UNIT")) {
					stmt.setString(g1, formcode);
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(g1, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(g1, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(g1, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(g1, formcode.substring(0, 10));
					} else if (formcodeType.equalsIgnoreCase("LINE")) {
						stmt.setString(g1, formcode);
					}
				}
				g1++;
			}
			if (!m4_p_code.equalsIgnoreCase("ALL")) {

	            stmt.setString(g1, m4_p_code);
	            g1++;
			}
			
			
			if(!item_type.equals("") && !item_type.equals("0") && !item_type.equals(0)){
		    	   		    	
		    }
		    
		    
			if (!arm_code.equalsIgnoreCase("") && !arm_code.equalsIgnoreCase("0") && !arm_code.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, arm_code);
		    	g1++;   
				
				}   		    
		    ResultSet rs = stmt.executeQuery();
		    System.out.println("===="+stmt);
    	
		    if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("item_code"));//0
					list.add(rs.getString("item_type"));//1
					list.add(rs.getString("prf_group"));//2
					list.add(rs.getString("cos_sec_no")); //3
					list.add(rs.getString("FF")); //4
					list.add(rs.getString("NFF"));//5
					list.add(rs.getString("CES"));//6
					list.add(rs.getString("TOTAL"));//7				
					li.add(list);
					
				}
			}
			rs.close();
     		stmt.close();
     		conn.close();
     }catch(SQLException e){
    	 e.printStackTrace();
     }	
     return li;
}


}
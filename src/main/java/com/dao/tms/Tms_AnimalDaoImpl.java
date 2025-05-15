package com.dao.tms;

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

import com.models.TB_ANIMALS_AWARD_MASTER;
import com.models.TB_TMS_ANIMAL_FITNESS_STATUS;
import com.models.TB_TMS_TYPE_DOG;
import com.models.TB_TMS_TYPE_OF_ANIMAL_MASTER;
import com.models.TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER;
import com.models.TMS_TB_MISO_BREED_MASTER;
import com.models.TMS_TB_MISO_COLOR_MASTER;
import com.models.TMS_TB_MISO_EMPLOYMENT_MASTER;
import com.models.TMS_TB_MISO_SOURCE_MASTER;
import com.models.TMS_TB_MISO_SPLZ_MASTER;
import com.models.TMS_TB_MISO_VACCINE_MASTER;
import com.models.TMS_TB_UE_MASTER;
import com.models.Tms_animals_details;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Tms_AnimalDaoImpl implements Tms_AnimalDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String setApprovedAnimal(String sus_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";
		String hqlUpdate = "update Tms_animals_details t set t.status = :status where status ='0' and t.sus_no = :sus_no";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setString("sus_no", sus_no).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			msg = " Approved Successfully.";
		} else {
			msg = " Approved not Successfully.";
		}
		return msg;
	}

	public String setDeleteAnimal(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from Tms_animals_details where id=:id";
		int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return " Delete Successfully";
		} else {
			return " Delete not Successfully";
		}
	}

	public String setRejectAnimal(String sus_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";
		String hqlUpdate = "update Tms_animals_details t set t.status=:status where status ='0' and t.sus_no = :sus_no";
		int app = session.createQuery(hqlUpdate).setString("status", "2").setString("sus_no", sus_no).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			msg = " Rejected Successfully.";
		} else {
			msg = " Rejected not Successfully.";
		}
		return msg;
	}

	public Tms_animals_details gettms_animals_detailsByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Query q = session.createQuery("from Tms_animals_details where id=:id");
			q.setParameter("id", id);
			Tms_animals_details list = (Tms_animals_details) q.list().get(0);
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public String UpdateAnimalAttValue(Tms_animals_details Doc1AttValues, String username) {

		String msg = null;
		Date date = new Date();

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			Doc1AttValues.setModify_by(username);
			Doc1AttValues.setModify_date(date);

			String hql = "update Tms_animals_details set sus_no=:sus_no,name_of_dog=:name_of_dog,remount_no=:remount_no,army_num=:army_num,microchip_no=:microchip_no,image=:image,specialization=:specialization,type_equines=:type_equines,type_dog=:type_dog,animal_purchase_cost=:animal_purchase_cost,animal_present_cost=:animal_present_cost,date_of_death=:date_of_death,date_admitted=:date_admitted,issue_to_unit_name=:issue_to_unit_name,disposal=:disposal,disposal_remarks=:disposal_remarks,"
					+ "medals_desc_details=:medals_desc_details,disptrans=:disptrans,date_of_birth=:date_of_birth,age=:age,details_of_sire=:details_of_sire,details_of_dam=:details_of_dam,sex=:sex,breed=:breed,colour=:colour,source=:source,fitness_status=:fitness_status,unit_where_awarded=:unit_where_awarded,authority=:authority,award_date=:award_date,awared_remarks=:awared_remarks,"
					+ "tos=:tos,tors=:tors,sos=:sos,sors=:sors,dis_date=:dis_date,issue_date=:issue_date,modify_by=:modify_by,modify_date=:modify_date,status ='0' where id=:id";

			Query query = session.createQuery(hql)

					.setString("sus_no", Doc1AttValues.getSus_no())
					.setString("remount_no", Doc1AttValues.getRemount_no())
					.setString("army_num", Doc1AttValues.getArmy_num()).setString("sex", Doc1AttValues.getSex())
					.setString("name_of_dog", Doc1AttValues.getName_of_dog())
					.setString("microchip_no", Doc1AttValues.getMicrochip_no())
					.setInteger("type_dog", Doc1AttValues.getType_dog())
					.setInteger("specialization", Doc1AttValues.getSpecialization())
					.setInteger("type_equines", Doc1AttValues.getType_equines())
					.setInteger("breed", Doc1AttValues.getBreed()).setInteger("colour", Doc1AttValues.getColour())
					.setInteger("source", Doc1AttValues.getSource())
					.setString("animal_purchase_cost", Doc1AttValues.getAnimal_purchase_cost())
					.setString("animal_present_cost", Doc1AttValues.getAnimal_present_cost())
					.setString("date_of_death", Doc1AttValues.getDate_of_death())
					.setString("date_of_birth", Doc1AttValues.getDate_of_birth())
					.setString("issue_to_unit_name", Doc1AttValues.getIssue_to_unit_name())
					.setString("disposal", Doc1AttValues.getDisposal())
					.setString("disposal_remarks", Doc1AttValues.getDisposal_remarks())
					.setString("medals_desc_details", Doc1AttValues.getMedals_desc_details())
					.setString("unit_where_awarded", Doc1AttValues.getUnit_where_awarded())
					.setString("awared_remarks", Doc1AttValues.getAwared_remarks())
					.setString("authority", Doc1AttValues.getAuthority())
					.setString("award_date", Doc1AttValues.getAward_date()).setString("tos", Doc1AttValues.getTos())
					.setString("tors", Doc1AttValues.getTors()).setString("sos", Doc1AttValues.getSos())
					.setString("sors", Doc1AttValues.getSors()).setString("image", Doc1AttValues.getImage())
					.setString("age", Doc1AttValues.getAge()).setString("disptrans", Doc1AttValues.getDisptrans())
					.setString("details_of_sire", Doc1AttValues.getDetails_of_sire())
					.setString("details_of_dam", Doc1AttValues.getDetails_of_dam())
					.setInteger("fitness_status", Doc1AttValues.getFitness_status())
					.setString("date_admitted", Doc1AttValues.getDate_admitted())
					.setString("dis_date", Doc1AttValues.getDis_date())
					.setString("issue_date", Doc1AttValues.getIssue_date())
					.setTimestamp("modify_date", Doc1AttValues.getModify_date())
					.setString("modify_by", Doc1AttValues.getModify_by()).setInteger("id", Doc1AttValues.getId());

			int rowCount = query.executeUpdate();
			tx.commit();

			if (rowCount > 0) {
				msg = " Data Update Successfully";
			} else {
				msg = " Data not Update Successfully";
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		finally {
			session.close();
		}
		return msg;
	}

	public List<Tms_animals_details> getANIMAL_EDITid(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {

			Query q = session.createQuery("from Tms_animals_details where id=:id");
			q.setParameter("id", id);
			@SuppressWarnings("unchecked")
			List<Tms_animals_details> list = (List<Tms_animals_details>) q.list();
			/*
			 * tx.commit(); session.close();
			 */
			return list;

		}

		catch (Exception e) {
			session.getTransaction().rollback();
		}

		finally {
			tx.commit();
			session.close();
		}
		return null;
	}

	public Tms_animals_details gettms_animals_loc_detailsByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tms_animals_details where id=:id");
		q.setParameter("id", id);
		Tms_animals_details list = (Tms_animals_details) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public ArrayList<ArrayList<String>> getdetailsUEList(String anml_type, String sus_no, String unit_name,
			String ue_of_dogs, int specialization, String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (anml_type != "") {
				qry += "  where a.anml_type = ?";

			}
			if (sus_no != "") {
				qry += " and a.sus_no = ?";

			}

			if (ue_of_dogs != "") {
				qry += " and a.ue_of_dogs = ?";

			}
			if (specialization != 0) {
				qry += " and a.specialization = ?";

			}

			String sql = "";
			sql = "   select distinct a.sus_no,d.unit_name,b.type_splztn,a.ue_of_dogs,a.id,a.specialization \r\n"
					+ "	from tb_tms_animals_ue_master a \r\n"
					+ "	left join tb_tms_specialization_master b  on cast(a.specialization as varchar)  = cast(b.id as varchar)\r\n"
					+ "	left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active'\r\n"
					+ "	 \r\n" + qry + "	order by b.type_splztn";

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (anml_type != "") {
				stmt.setString(j, anml_type);
				j += 1;
			}
			if (sus_no != "") {
				stmt.setString(j, sus_no);
				j += 1;
			}
			if (ue_of_dogs != "") {
				stmt.setString(j, ue_of_dogs);
				j += 1;
			}
			if (specialization != 0) {
				stmt.setInt(j, specialization);
				j += 1;
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("type_splztn"));
				list.add(rs.getString("ue_of_dogs"));
				list.add(rs.getString("id"));

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to Update?') ){editData("
						+ rs.getString("id") + ",'" + rs.getString("sus_no") + "','" + rs.getString("unit_name") + "','"
						+ rs.getString("ue_of_dogs") + "','" + rs.getString("specialization")
						+ "','','')}else{ return false;}\"";

				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete?') ){deleteData("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				if (roleType.equals("ALL")) {
					f += updateButton;
					f += deleteButton;
				} else if (roleType.equals("APP")) {
					/*
					 * f += updateButton; f += deleteButton;
					 */
				} else if (roleType.equals("DEO")) {
					f += updateButton;
					f += deleteButton;
				}
				list.add(f);
				aList.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getAttributeDataSearchAnimalUEArmyEqn(String anml_type, String sus_no,
			String unit_name, String ue_of_equins, int type_equines, String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (anml_type != "") {
				qry += "  a.anml_type = ?";

			}
			if (sus_no != "") {
				qry += " and a.sus_no = ?";
			}
			if (ue_of_equins != "") {
				qry += " and a.ue_of_equins = ?";
			}
			if (type_equines != 0) {
				qry += " and a.type_equines = ?";

			}

			String sql = "";

			sql = "select distinct a.sus_no,d.unit_name,c.type_of_animal,a.ue_of_equins,a.id,a.type_equines \r\n"
					+ "	from tb_tms_animals_ue_master a\r\n"
					+ "	left join tb_tms_type_of_animal_master c on cast(a.type_equines as varchar)  = cast(c.id as varchar)\r\n"
					+ "	left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active'\r\n"
					+ "	where \r\n" + qry + "	order by c.type_of_animal";

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (anml_type != "") {
				stmt.setString(j, anml_type);
				j += 1;
			}

			if (sus_no != "") {
				stmt.setString(j, sus_no);
				j += 1;
			}

			if (ue_of_equins != "") {
				stmt.setString(j, ue_of_equins);
				j += 1;
			}

			if (type_equines != 0) {
				stmt.setInt(j, type_equines);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("type_of_animal"));
				list.add(rs.getString("ue_of_equins"));
				list.add(rs.getString("id"));

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to Update?') ){editData("
						+ rs.getString("id") + ",'" + rs.getString("sus_no") + "','" + rs.getString("unit_name")
						+ "','','','" + rs.getString("ue_of_equins") + "','" + rs.getString("type_equines")
						+ "')}else{ return false;}\"";

				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete?') ){deleteData("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				if (roleType.equals("ALL")) {
					f += updateButton;
					f += deleteButton;
				} else if (roleType.equals("APP")) {
					/*
					 * f += updateButton; f += deleteButton;
					 */
				} else if (roleType.equals("DEO")) {
					f += updateButton;
					f += deleteButton;
				}
				list.add(f);
				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getpendinglist(String sus_no, String role) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (sus_no != "") {
				qry += " and sus_no = ?";
			}

			sql = "select \r\n" + "COALESCE(a.army_num,'') || COALESCE(a.remount_no,'') as num,\r\n" + "a.name_of_dog, "
					+ "COALESCE(CASE WHEN b.type_dog = 'NA' OR b.type_dog = '' THEN '' ELSE b.type_dog END ) || COALESCE(CASE WHEN c.type_of_animal = 'NA' OR c.type_of_animal = '' THEN '' ELSE c.type_of_animal END ) as name,"
					+ "a.microchip_no,\r\n" + "a.sex,\r\n" + "CASE WHEN tos != ''\r\n"
					+ "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')\r\n" + "END tos,\r\n"
					+ "CASE WHEN tors != ''\r\n" + "THEN ltrim(TO_CHAR(cast(tors as date),'dd-mm-yyyy'),'0')\r\n"
					+ "END tors,\r\n" + "CASE WHEN sos != ''\r\n"
					+ "THEN ltrim(TO_CHAR(cast(sos as date),'dd-mm-yyyy'),'0')\r\n" + "END sos,\r\n"
					+ "a.id from tb_tms_animal_details_tbl a left join tb_tms_dog_type b on cast(a.type_dog as integer) = cast(b.id as integer) \r\n"
					+ "left join tb_tms_type_of_animal_master c on cast(a.type_equines as integer) = cast(c.id as integer) where status = '0'"
					+ qry + "order by b.type_dog,c.type_of_animal";

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (sus_no != "") {
				stmt.setString(j, sus_no);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("num"));
				list.add(rs.getString("name_of_dog"));
				list.add(rs.getString("name"));
				list.add(rs.getString("microchip_no"));

				list.add(rs.getString("sex"));
				list.add(rs.getString("tos"));
				list.add(rs.getString("tors"));
				list.add(rs.getString("sos"));
				list.add(rs.getString("id"));

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to View?') ){open1("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_view' " + Update + " title='View Data'></i>";

				if (role.equals("ALL") || role.equals("DEO") || role.equals("APP")) {
					f += updateButton;
				}

				list.add(f);
				aList.add(list);
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
		return aList;
	}

	public ArrayList<ArrayList<String>> getanimallocstatus(String anml_type, String army_num, String microchip_no,
			String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (army_num != "") {
				qry += " army_num = ?";
			}

			if (qry == "") {

				sql = "select distinct a.army_num,\r\n" + "	a.microchip_no,\r\n" + "	a.sus_no,\r\n"
						+ "	d.unit_name,\r\n" + "	b.type_dog,\r\n" + "	a.age,\r\n" + "	c.type_splztn,\r\n"
						+ "	a.id \r\n" + "	from tb_tms_animal_details_tbl a\r\n"
						+ "	left join tb_tms_dog_type b on a.type_dog = b.id \r\n"
						+ "	left join tb_tms_specialization_master c on a.specialization = c.id\r\n"
						+ "	left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active'\r\n"
						+ "	where  a.anml_type = 'Army Dog' order by a.sus_no";

			} else {

				sql = "select distinct a.army_num,a.microchip_no,a.sus_no,d.unit_name,b.type_dog,a.age,c.type_splztn,a.id from tb_tms_animal_details_tbl a \r\n"
						+ "	left join tb_tms_dog_type b on a.type_dog = b.id \r\n"
						+ "	left join tb_tms_specialization_master c on a.specialization = c.id \r\n"
						+ "	left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active'\r\n"
						+ "	where " + qry + " order by a.sus_no";
			}

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (army_num != "") {
				stmt.setString(j, army_num);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("army_num"));
				list.add(rs.getString("microchip_no"));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("type_dog"));
				list.add(rs.getString("age"));
				list.add(rs.getString("type_splztn"));
				list.add(rs.getString("id"));

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to View?') ){View('"
						+ rs.getString("army_num") + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_view' " + Update + " title='View Data'></i>";

				if (roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("DEO")) {
					f += updateButton;
				}

				list.add(f);
				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getanimallocstatusequ(String anml_type, String remount_no, String microchip_no,
			String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (remount_no != "") {
				qry += " remount_no = ?";
			}

			if (qry == "") {

				sql = "select distinct a.remount_no,a.microchip_no,a.sus_no,d.unit_name,b.type_of_animal,a.age,a.id from tb_tms_animal_details_tbl a \r\n"
						+ " left join tb_tms_type_of_animal_master b on a.type_equines = b.id \r\n"
						+ " left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active' "
						+ "where a.anml_type = 'Army Equines' order by a.sus_no";

			} else {

				sql = ("\r\n"
						+ " select distinct a.remount_no,a.microchip_no,a.sus_no,d.unit_name,b.type_of_animal,a.age,a.id from tb_tms_animal_details_tbl a \r\n"
						+ " left join tb_tms_type_of_animal_master b on a.type_equines = b.id "
						+ " left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active' where "
						+ qry + " order by a.sus_no");
			}

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (remount_no != "") {
				stmt.setString(j, remount_no);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("remount_no"));
				list.add(rs.getString("microchip_no"));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("type_of_animal"));
				list.add(rs.getString("age"));
				list.add(rs.getString("id"));

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to View?') ){View1('"
						+ rs.getString("remount_no") + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_view' " + Update + " title='View Data'></i>";

				if (roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("DEO")) {
					f += updateButton;
				}

				list.add(f);
				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getAnimalapprovelocReportList(String army_num, String microchip_no,
			String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (army_num != "") {
				qry += " army_num = ?";
			}

			sql = ("select a.army_num," + "d.unit_name," + "a.microchip_no," + "a.name_of_dog," + "a.sex," + "a.age,"
					+ "b.type_dog," + "c.type_splztn," + "a.medals_desc_details," + "CASE WHEN tos != ''"
					+ "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')" + "END tos," + "CASE WHEN tors != '' "
					+ " THEN ltrim(TO_CHAR(cast(tors as date),'dd-mm-yyyy'),'0')" + "END tors,"

					+ "CASE WHEN sos != ''" + "THEN ltrim(TO_CHAR(cast(sos as date),'dd-mm-yyyy'),'0')" + "END sos,"
					+ "a.id from tb_tms_animal_details_tbl a left join tb_tms_dog_type b on a.type_dog = b.id \r\n"
					+ " left join tb_tms_specialization_master c on a.specialization = c.id"
					+ " left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active' where "
					+ qry + "order by d.unit_name");

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (army_num != "") {
				stmt.setString(j, army_num);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("army_num"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("microchip_no"));
				list.add(rs.getString("name_of_dog"));
				list.add(rs.getString("sex"));
				list.add(rs.getString("age"));
				list.add(rs.getString("type_dog"));
				list.add(rs.getString("type_splztn"));
				list.add(rs.getString("medals_desc_details"));
				list.add(rs.getString("tos"));
				list.add(rs.getString("tors"));
				list.add(rs.getString("sos"));
				list.add(rs.getString("id"));

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to View?') ){open1("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_view' " + Update + " title='View Data'></i>";

				if (roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("DEO")) {
					f += updateButton;
				}
				list.add(f);
				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getAnimalReportListequ(String remount_no, String microchip_no,
			String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (remount_no != "") {
				qry += " remount_no = ?";
			}

			sql = ("select a.remount_no," + "d.unit_name," + "a.microchip_no," + "a.sex," + "a.age,"
					+ "b.type_of_animal," + "a.medals_desc_details,"

					+ "CASE WHEN tos != ''" + "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')" + "END tos,"

					+ "CASE WHEN tors != ''" + "THEN ltrim(TO_CHAR(cast(tors as date),'dd-mm-yyyy'),'0')" + "END tors,"

					+ "CASE WHEN sos != ''" + "THEN ltrim(TO_CHAR(cast(sos as date),'dd-mm-yyyy'),'0')" + "END sos,"

					+ "a.id from tb_tms_animal_details_tbl a left join tb_tms_type_of_animal_master b on a.type_equines = b.id "
					+ "left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active' \r\n"
					+ " where " + qry + "order by d.unit_name");

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (remount_no != "") {
				stmt.setString(j, remount_no);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("remount_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("microchip_no"));
				list.add(rs.getString("sex"));
				list.add(rs.getString("age"));
				list.add(rs.getString("type_of_animal"));
				list.add(rs.getString("medals_desc_details"));
				list.add(rs.getString("tos"));
				list.add(rs.getString("tors"));
				list.add(rs.getString("sos"));
				list.add(rs.getString("id"));

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to View?') ){open1("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_view' " + Update + " title='View Data'></i>";

				if (roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("DEO")) {
					f += updateButton;
				}

				list.add(f);

				aList.add(list);
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
		return aList;
	}

	public ArrayList<ArrayList<String>> getAnimalHistory(String sus_no, String anml_type) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (sus_no != "") {
				qry += " sus_no = ?";
			}

			if (anml_type.equals("")) {

				sql = "select a.sus_no,a.army_num, a.remount_no,a.anml_type,e.type_dog,b.type_of_animal,a.id from tb_tms_animal_details_tbl a\r\n"
						+ " left join tb_tms_type_of_animal_master b on a.type_equines = b.id left join tb_tms_dog_type e on a.type_dog = e.id";

			} else {

				if (anml_type.equals("Army Dog")) {
					sql = ("select a.army_num,a.name_of_dog,a.age,a.tos,c.type_splztn,d.fitness_status,a.sus_no from tb_tms_animal_details_tbl a  \r\n"
							+ "left join tb_tms_specialization_master c on cast(a.specialization as integer) = cast(c.id as integer) left join tb_tms_animal_fitness_status d on cast(a.fitness_status as integer) = cast(d.id as integer) where"
							+ qry);

				} else {

					sql = ("select a.remount_no,b.type_of_animal,a.age,a.tos,d.fitness_status,a.sus_no from tb_tms_animal_details_tbl a left join tb_tms_type_of_animal_master b on cast(a.type_equines as integer) = cast(b.id as integer) \r\n"
							+ "left join tb_tms_animal_fitness_status d on cast(a.fitness_status as integer) = cast(d.id as integer) where"
							+ qry);
				}

				if (anml_type.equals("Army Dog")) {
					sql = ("select a.army_num,a.name_of_dog,a.age,a.tos,c.type_splztn,d.fitness_status,a.sus_no from tb_tms_animal_details_tbl a  \r\n"
							+ "left join tb_tms_specialization_master c on cast(a.specialization as integer) = cast(c.id as integer) left join tb_tms_animal_fitness_status d on cast(a.fitness_status as integer) = cast(d.id as integer) where a.anml_type = 'Army Dog'");
				}

				else {

					sql = ("select a.remount_no,b.type_of_animal,a.age,a.tos,d.fitness_status,a.sus_no from tb_tms_animal_details_tbl a left join tb_tms_type_of_animal_master b on cast(a.type_equines as integer) = cast(b.id as integer) \r\n"
							+ "left join tb_tms_animal_fitness_status d on cast(a.fitness_status as integer) = cast(d.id as integer) where a.anml_type = 'Army Equines'");
				}
			}

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				if (anml_type.equals("")) {

					list.add(rs.getString("sus_no"));
					list.add(rs.getString("army_num"));
					list.add(rs.getString("remount_no"));
					list.add(rs.getString("anml_type"));
					list.add(rs.getString("type_dog"));
					list.add(rs.getString("type_of_animal"));
					list.add(rs.getString("id"));
				}

				else {

					if (anml_type.equals("Army Dog")) {
						list.add(rs.getString("army_num"));

						list.add(rs.getString("name_of_dog"));
						list.add(rs.getString("age"));
						list.add(rs.getString("tos"));
						list.add(rs.getString("type_splztn"));
						list.add(rs.getString("fitness_status"));
						list.add(rs.getString("sus_no"));

					} else {

						list.add(rs.getString("remount_no"));
						list.add(rs.getString("type_of_animal"));
						list.add(rs.getString("age"));
						list.add(rs.getString("tos"));
						list.add(rs.getString("fitness_status"));
						list.add(rs.getString("sus_no"));

					}
				}

				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getAnimalAllData(String sus_no, String anml_type, String unit_name,
			String type_dog, String type_equines, String fdate, String tdate, String comd_sus_no,
			String comd_unit_name,String animal_status) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (anml_type.equals("")) {
				qry = "";

			}

			if (anml_type.equals("Army Dog")) {

				qry += " a.anml_type = 'Army Dog'";

				if (type_dog != "") {
					qry += " and a.type_dog = cast(? as integer)";
				}
			}

			if (anml_type.equals("Army Equines")) {

				qry += " a.anml_type = 'Army Equines'";

				if (type_equines != "" && !type_equines.equals("0")) {
					qry += " and a.type_equines = cast(? as integer)";
				}
			}

			if (!sus_no.equals("")) {
				qry += " and a.sus_no = ?";
			}

			if (fdate != "" && tdate != "") {

				qry += " and cast(a.created_date as date) between cast(? as date) and cast(? as date) + 1";
			}

			if (fdate != "" && tdate == "") {

				qry += " and cast(a.created_date as date) >= cast(? as date) ";
			}

			if (fdate == "" && tdate != "") {
				qry += " and cast(a.created_date as date) <= cast(? as date) ";

			}

			if (!comd_sus_no.equals("")) {
				qry += " and a.comd_sus_no = ?";
			}
			
			if (animal_status != null && !animal_status.equals("")) {
				if (animal_status.equals("live")) {
					qry += " and (a.date_of_death = '' or a.date_of_death is null) ";
				}
				else if (animal_status.equals("dead")) {
					qry += " and (a.date_of_death != '' and a.date_of_death is not null) ";
				}
			}

			if (anml_type.equals("")) {

				if (qry != "")
					qry = " where " + qry.substring(4, qry.length());

				sql = "select u.unit_name,\r\n" + "COALESCE(a.army_num,'') || COALESCE(a.remount_no,'') as num,\r\n"
						+ "\r\n"
						+ "COALESCE(a.name_of_dog,'')||CASE WHEN d.type_of_animal = 'NA' OR d.type_of_animal = '' THEN '' ELSE d.type_of_animal  END \"name\",\r\n"
						+ "\r\n" + "e.type_breed,f.type_color,a.sex,a.age,\r\n" + "a.microchip_no,"

						+ "CASE WHEN h.fitness_status = 'NA' THEN '' ELSE h.fitness_status END fitness_status," +

						"CASE WHEN tos != ''THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')END tos,a.created_date "
						+ "from tb_tms_animal_details_tbl a \r\n"
						+ " left join tb_miso_orbat_unt_dtl u on a.sus_no = u.sus_no and u.status_sus_no='Active'"
						+ " left join tb_tms_dog_type b on a.type_dog = b.id "
						+ "left join tb_tms_specialization_master c on a.specialization = c.id "
						+ "left join tb_tms_type_of_animal_master d on a.type_equines = d.id\r\n"
						+ " left join tb_tms_breed_master e on a.breed = e.id "
						+ "left join tb_tms_color_master f on a.colour = f.id "
						+ "left join tb_tms_animal_source_master g on a.source = g.id\r\n"
						+ "left join tb_tms_animal_fitness_status h on a.fitness_status = h.id" + qry;
			}

			else if (anml_type.equals("Army Dog")) {

				if (qry != "")
					qry = " where " + qry.substring(1, qry.length());

				sql = "select d.unit_name,a.army_num,a.microchip_no,a.name_of_dog,e.type_breed,a.age,a.sex,f.type_color,"
						+ "CASE WHEN h.fitness_status = 'NA' THEN '' ELSE h.fitness_status END fitness_status,"
						+ "CASE WHEN tos != ''" + "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')" + "END tos,"
						+ "a.created_date from tb_tms_animal_details_tbl a\r\n"
						+ "left join tb_tms_dog_type b on a.type_dog = b.id "
						+ "left join tb_tms_specialization_master c on a.specialization = c.id \r\n"
						+ " left join tb_miso_orbat_unt_dtl d on a.sus_no = d.sus_no and d.status_sus_no='Active'"
						+ "left join tb_tms_breed_master e on a.breed = e.id "
						+ "left join tb_tms_color_master f on a.colour = f.id "
						+ "left join tb_tms_animal_source_master g on a.source = g.id\r\n"
						+ "left join tb_tms_animal_fitness_status h on a.fitness_status = h.id " + qry;

			}

			else {
				if (qry != "")
					qry = " where " + qry.substring(1, qry.length());

				sql = "select u.unit_name,a.remount_no,a.microchip_no,d.type_of_animal,e.type_breed,a.age,a.sex,f.type_color,"
						+ "CASE WHEN h.fitness_status = 'NA' THEN '' ELSE h.fitness_status END fitness_status,"
						+ "CASE WHEN tos != ''" + "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')" + "END tos,"
						+ "a.created_date from tb_tms_animal_details_tbl a \r\n"
						+ "left join tb_miso_orbat_unt_dtl u on a.sus_no = u.sus_no and u.status_sus_no='Active'"
						+ "left join tb_tms_type_of_animal_master d on a.type_equines = d.id\r\n"
						+ "left join tb_tms_breed_master e on a.breed = e.id "
						+ "left join tb_tms_color_master f on a.colour = f.id "
						+ "left join tb_tms_animal_source_master g on a.source = g.id\r\n"
						+ "left join tb_tms_animal_fitness_status h on a.fitness_status = h.id " + qry;

			}

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (anml_type.equals("Army Dog")) {

				qry += " a.anml_type = 'Army Dog'";

				if (type_dog != "") {
					stmt.setString(j, type_dog);
					j += 1;

				}
			}

			if (anml_type.equals("Army Equines")) {

				qry += " a.anml_type = 'Army Equines'";

				if (type_equines != "" && !type_equines.equals("0")) {
					stmt.setString(j, type_equines);
					j += 1;

				}
			}

			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no);
				j += 1;
			}

			if (fdate != "" && tdate != "") {
				stmt.setString(j, fdate);
				j += 1;

				stmt.setString(j, tdate);
				j += 1;
			}

			if (fdate != "" && tdate == "") {
				stmt.setString(j, fdate);
				j += 1;
			}

			if (fdate == "" && tdate != "") {

				stmt.setString(j, tdate);
				j += 1;

			}

			if (!comd_sus_no.equals("")) {

				stmt.setString(j, comd_sus_no);
				j += 1;

			}

			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				if (anml_type.equals("")) {

					list.add(rs.getString("unit_name"));
					list.add(rs.getString("num"));
					list.add(rs.getString("name"));
					list.add(rs.getString("type_breed"));
					list.add(rs.getString("type_color"));
					list.add(rs.getString("sex"));
					list.add(rs.getString("age"));
					list.add(rs.getString("microchip_no"));
					list.add(rs.getString("fitness_status"));
					list.add(rs.getString("tos"));
					list.add(rs.getString("created_date"));
				}

				else {

					if (anml_type.equals("Army Dog")) {

						list.add(rs.getString("unit_name"));
						list.add(rs.getString("army_num"));
						list.add(rs.getString("microchip_no"));
						list.add(rs.getString("name_of_dog"));
						list.add(rs.getString("type_breed"));
						list.add(rs.getString("age"));
						list.add(rs.getString("sex"));
						list.add(rs.getString("type_color"));
						list.add(rs.getString("fitness_status"));
						list.add(rs.getString("tos"));
						list.add(rs.getString("created_date"));
					} else {

						list.add(rs.getString("unit_name"));
						list.add(rs.getString("remount_no"));
						list.add(rs.getString("microchip_no"));
						list.add(rs.getString("type_of_animal"));
						list.add(rs.getString("type_breed"));
						list.add(rs.getString("age"));
						list.add(rs.getString("sex"));
						list.add(rs.getString("type_color"));
						list.add(rs.getString("fitness_status"));
						list.add(rs.getString("tos"));
						list.add(rs.getString("created_date"));
					}
				}
				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getanimalstatus(String sus_no, String anml_type, String unit_name,
			String type_dog, String type_equines, String fdate, String tdate, String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			String sql = "";

			if (anml_type.equals("")) {
				qry = "";
			}

			if (anml_type.equals("Army Dog")) {

				qry += " a.anml_type = 'Army Dog'";

				if (type_dog != "" && !type_dog.equals("0")) {
					qry += " and a.type_dog = cast(? as integer)";
				}
			}

			if (anml_type.equals("Army Equines")) {

				qry += " a.anml_type = 'Army Equines'";

				if (type_equines != "" && !type_equines.equals("0")) {
					qry += " and a.type_equines = cast(? as integer)";
				}
			}

			if (!sus_no.equals("")) {
				qry += " and sus_no = ?";
			}

			if (fdate != "" && tdate != "") {
				qry += " and cast(a.created_date as date) between cast(? as date) and cast(? as date) + 1";
			}

			if (fdate != "" && tdate == "") {
				qry += " and cast(a.created_date as date) >= cast(? as date) ";
			}

			if (fdate == "" && tdate != "") {
				qry += " and cast(a.created_date as date) <= cast(? as date) ";
			}

			if (anml_type.equals("")) {

				if (qry != "")
					qry = " where " + qry.substring(4, qry.length());

				sql = "select \r\n" + "	COALESCE(a.army_num,'') || COALESCE(a.remount_no,'') as num,\r\n" + "	\r\n"
						+ "	COALESCE(a.name_of_dog,'')||CASE WHEN b.type_of_animal = 'NA' OR b.type_of_animal = '' THEN '' ELSE b.type_of_animal  END \"name\","
						+ "a.age, \r\n" + "	CASE  WHEN tos != ''\r\n"
						+ "		THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0') \r\n" + "		END tos,\r\n"
						+ "	CASE WHEN c.type_splztn = 'NA' THEN '' ELSE c.type_splztn END type_splztn," +

						"	CASE WHEN d.fitness_status = 'NA' THEN '' ELSE d.fitness_status END fitness_status,"
						+ "	a.id from \r\n" + "tb_tms_animal_details_tbl a \r\n"
						+ "left join tb_tms_type_of_animal_master b on cast(a.type_equines as integer) = cast(b.id as integer) \r\n"
						+ "left join tb_tms_specialization_master c on cast(a.specialization as integer) = cast(c.id as integer)\r\n"
						+ "left join tb_tms_animal_fitness_status d on cast(a.fitness_status as integer) = cast(d.id as integer) "
						+ qry + "order by a.name_of_dog,b.type_of_animal";

			} else {

				if (anml_type.equals("Army Dog")) {

					if (qry != "")
						qry = " where " + qry.substring(1, qry.length());

					sql = "select \r\n" + "a.army_num,\r\n" + "a.name_of_dog,\r\n" + "a.age,\r\n"
							+ "CASE WHEN tos != ''\r\n" + "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')\r\n"
							+ "END tos,\r\n"
							+ "CASE WHEN c.type_splztn = 'NA' THEN '' ELSE c.type_splztn END type_splztn,"
							+ "CASE WHEN d.fitness_status = 'NA' THEN '' ELSE d.fitness_status END fitness_status,"
							+ "a.id from tb_tms_animal_details_tbl a  \r\n"
							+ "left join tb_tms_specialization_master c on cast(a.specialization as integer) = cast(c.id as integer) left join \r\n"
							+ "tb_tms_animal_fitness_status d on cast(a.fitness_status as integer) = cast(d.id as integer) "
							+ qry + "order by a.name_of_dog";
				}

				else {
					if (qry != "")
						qry = " where " + qry.substring(1, qry.length());

					sql = "select \r\n" + "a.remount_no,\r\n" + "b.type_of_animal,\r\n" + "a.age,\r\n"
							+ "CASE WHEN tos != ''\r\n" + "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')\r\n"
							+ "END tos,\r\n"
							+ "CASE WHEN d.fitness_status = 'NA' THEN '' ELSE d.fitness_status END fitness_status,"
							+ "a.id from tb_tms_animal_details_tbl a left join tb_tms_type_of_animal_master b on cast(a.type_equines as integer) = cast(b.id as integer) \r\n"
							+ "left join tb_tms_animal_fitness_status d on cast(a.fitness_status as integer) = cast(d.id as integer)"
							+ qry + "order by b.type_of_animal";
				}
			}

			stmt = conn.prepareStatement(sql);

			int j = 1;
			if (anml_type.equals("Army Dog")) {

				qry += " a.anml_type = 'Army Dog'";

				if (type_dog != "") {
					stmt.setString(j, type_dog);
					j += 1;
				}
			}

			if (anml_type.equals("Army Equines")) {

				qry += " a.anml_type = 'Army Equines'";

				if (type_equines != "" && !type_equines.equals("0")) {

					stmt.setString(j, type_equines);
					j += 1;
				}
			}

			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no);
				j += 1;
			}

			if (fdate != "" && tdate != "") {

				stmt.setString(j, fdate);
				j += 1;

				stmt.setString(j, tdate);
				j += 1;
			}

			if (fdate != "" && tdate == "") {
				stmt.setString(j, fdate);
				j += 1;
			}

			if (fdate == "" && tdate != "") {

				stmt.setString(j, tdate);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				if (anml_type.equals("")) {

					list.add(rs.getString("num"));
					list.add(rs.getString("name"));
					list.add(rs.getString("age"));
					list.add(rs.getString("tos"));
					list.add(rs.getString("type_splztn"));
					list.add(rs.getString("fitness_status"));
					list.add(rs.getString("id"));
				}

				else {

					if (anml_type.equals("Army Dog")) {
						list.add(rs.getString("army_num"));
						list.add(rs.getString("name_of_dog"));
						list.add(rs.getString("age"));
						list.add(rs.getString("tos"));
						list.add(rs.getString("type_splztn"));
						list.add(rs.getString("fitness_status"));
						list.add(rs.getString("id"));
					} else {

						list.add(rs.getString("remount_no"));
						list.add(rs.getString("type_of_animal"));
						list.add(rs.getString("age"));
						list.add(rs.getString("tos"));
						list.add(rs.getString("fitness_status"));
						list.add(rs.getString("id"));
					}
				}

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure you want to Update?') ){Update("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				if (roleType.equals("ALL")) {
					f += updateButton;
				} else if (roleType.equals("APP")) {
					// f += updateButton;
				} else if (roleType.equals("DEO")) {
					f += updateButton;
				}

				list.add(f);

				aList.add(list);
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
		return aList;
	}

	public ArrayList<ArrayList<String>> getAnimalReportListappro(String sus_no) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";

			if (sus_no != "") {
				qry += " and sus_no = ?";
			}

			sql = "select \r\n" + "COALESCE(a.army_num,'') || COALESCE(a.remount_no,'') as num,\r\n"
					+ "COALESCE(CASE WHEN c.type_dog = 'NA' OR c.type_dog = '' THEN '' ELSE c.type_dog END ) || COALESCE(CASE WHEN b.type_of_animal = 'NA' OR b.type_of_animal = '' THEN '' ELSE b.type_of_animal END ) as name,"
					+ "a.microchip_no,a.name_of_dog,a.age,f.type_color,\r\n" + "a.sex,"
					+ "CASE WHEN g.type_splztn = 'NA' THEN '' ELSE g.type_splztn END type_splztn,\r\n"
					+ "CASE WHEN tos != '' \r\n" + "THEN ltrim(TO_CHAR(cast(tos as date),'dd-mm-yyyy'),'0')END tos,\r\n"
					+ "CASE WHEN i.fitness_status = 'NA' THEN '' ELSE i.fitness_status END fitness_status,"
					+ " a.id\r\n"
					+ "  from tb_tms_animal_details_tbl a left join tb_tms_type_of_animal_master b on a.type_equines = b.id\r\n"
					+ "  left join tb_tms_color_master f on a.colour = f.id\r\n"
					+ "  left join tb_tms_specialization_master g on a.specialization = g.id\r\n"
					+ "  left join tb_tms_animal_fitness_status i on a.fitness_status = i.id\r\n"
					+ "  left join tb_tms_dog_type c on a.type_dog = c.id where status ='1'" + qry
					+ " order by a.anml_type";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, sus_no);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("num"));
				list.add(rs.getString("name"));
				list.add(rs.getString("microchip_no"));
				list.add(rs.getString("name_of_dog"));
				list.add(rs.getString("age"));
				list.add(rs.getString("type_color"));
				list.add(rs.getString("sex"));
				list.add(rs.getString("type_splztn"));
				list.add(rs.getString("tos"));
				list.add(rs.getString("fitness_status"));
				list.add(rs.getString("id"));

				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> search_st(String sus_no, String unit_name, String status, String fdate,
			String tdate, String roleType) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			String sql = "";

			if (status != "") {
				qry += " status = ?";
			}

			if (sus_no != "") {
				qry += " and a.sus_no = ?";

			}

			if ((fdate != "") && (tdate != "")) {
				qry += " and cast(created_date as date) between cast(? as date) and cast(? as date) + 1";
			}

			if (fdate != "" && tdate == "") {

				qry += " and cast(created_date as date) >= cast(? as date) ";
			}

			if (fdate == "" && tdate != "") {

				qry += " and cast(created_date as date) <= cast(? as date) ";
			}

			if (qry == "") {
				sql = "select distinct a.sus_no,u.unit_name from tb_tms_animal_details_tbl a left join tb_miso_orbat_unt_dtl u on a.sus_no = u.sus_no and u.status_sus_no='Active' ";
			} else {
				sql = "select distinct a.sus_no,u.unit_name from tb_tms_animal_details_tbl a left join tb_miso_orbat_unt_dtl u on a.sus_no = u.sus_no and u.status_sus_no='Active'  where "
						+ qry;
			}
			stmt = conn.prepareStatement(sql);
			int j = 1;
			if (status != "") {
				stmt.setString(j, status);
				j += 1;
			}

			if (sus_no != "") {
				stmt.setString(j, sus_no);
				j += 1;

			}

			if (fdate != "" && tdate != "") {

				stmt.setString(j, fdate);
				j += 1;

				stmt.setString(j, tdate);
				j += 1;

			}

			if (fdate != "" && tdate == "") {
				stmt.setString(j, fdate);
				j += 1;
			}

			if (fdate == "" && tdate != "") {

				stmt.setString(j, tdate);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));

				String Pending = "onclick=\"  if (confirm('Are You Sure you want to View?') ){Pending('"
						+ rs.getString("sus_no") + "')}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_view' " + Pending + " title='Pending'></i>";

				String Approve = "onclick=\"  if (confirm('Are You Sure you want to View?') ){Approve('"
						+ rs.getString("sus_no") + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_view' " + Approve + " title='Approve'></i>";

				String f = "";
				if (status.equals("0")) {

					f += deleteButton;

				} else if (status.equals("1")) {
					if (roleType.equals("APP") || roleType.equals("ALL") || roleType.equals("DEO")) {

						f += updateButton;

					}

				}

				list.add(f);

				aList.add(list);
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
		return aList;
	}

	public ArrayList<ArrayList<String>> dog_sum_list(String sus_no, String unit_name, String anml_type) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String q = "";
			String qry = "";
			String sql = "";
			int total_dog = 0;

			if (sus_no != "") {
				qry += " sus_no = ?";
			}

			if (anml_type != "") {
				qry += " anml_type = ?";
			}

			if (qry == "") {
				sql = ("select count(age) from tb_tms_animal_details_tbl where age > '1-3' ");
			} else {

				sql = ("select distinct d.sus_no,b.type_dog,\r\n"
						+ "	(select count(id) as year1_below from tb_tms_animal_details_tbl e where e.year >= 0 and e.year < 1 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year1 from tb_tms_animal_details_tbl e where e.year >= 1 and e.year < 2 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year2 from tb_tms_animal_details_tbl e where e.year >= 2 and e.year < 3 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year3 from tb_tms_animal_details_tbl e where e.year >= 3 and e.year < 4 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year4 from tb_tms_animal_details_tbl e where e.year >= 4 and e.year < 5 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year5 from tb_tms_animal_details_tbl e where e.year >= 5 and e.year < 6 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year6 from tb_tms_animal_details_tbl e where e.year >= 6 and e.year < 7 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year7 from tb_tms_animal_details_tbl e where e.year >= 7 and e.year < 8 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year8 from tb_tms_animal_details_tbl e where e.year >= 8 and e.year < 9 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year9 from tb_tms_animal_details_tbl e where e.year >= 9 and e.year < 10 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year10 from tb_tms_animal_details_tbl e where e.year >= 10 and e.year < 11 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as year11 from tb_tms_animal_details_tbl e where e.year >= 11 and e.year < 12 and e.sus_no = ?"
						+ " and e.type_dog=d.type_dog),\r\n"
						+ "	(select count(id) as above_year12 from tb_tms_animal_details_tbl e where e.year >= 12   and sus_no = ?"
						+ " and e.type_dog=d.type_dog)	\r\n"
						+ "from tb_tms_animal_details_tbl d, tb_tms_dog_type b   where d.type_dog = b.id and d.anml_type=?"
						+ " and  d.sus_no = ?" + " group by d.sus_no,d.type_dog,b.type_dog order by b.type_dog ");

			}

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);
			stmt.setString(3, sus_no);
			stmt.setString(4, sus_no);
			stmt.setString(5, sus_no);
			stmt.setString(6, sus_no);
			stmt.setString(7, sus_no);
			stmt.setString(8, sus_no);
			stmt.setString(9, sus_no);
			stmt.setString(10, sus_no);
			stmt.setString(11, sus_no);
			stmt.setString(12, sus_no);
			stmt.setString(13, sus_no);
			stmt.setString(14, anml_type);
			stmt.setString(15, sus_no);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("type_dog"));
				list.add(rs.getString("year1_below"));
				list.add(rs.getString("year1"));
				list.add(rs.getString("year2"));
				list.add(rs.getString("year3"));
				list.add(rs.getString("year4"));
				list.add(rs.getString("year5"));
				list.add(rs.getString("year6"));
				list.add(rs.getString("year7"));
				list.add(rs.getString("year8"));
				list.add(rs.getString("year9"));
				list.add(rs.getString("year10"));
				list.add(rs.getString("year11"));
				list.add(rs.getString("above_year12"));

				total_dog = Integer.parseInt(rs.getString("year1_below")) + Integer.parseInt(rs.getString("year1"))
						+ Integer.parseInt(rs.getString("year2")) + Integer.parseInt(rs.getString("year3"))
						+ Integer.parseInt(rs.getString("year4")) + Integer.parseInt(rs.getString("year5"))
						+ Integer.parseInt(rs.getString("year6")) + Integer.parseInt(rs.getString("year7"))
						+ Integer.parseInt(rs.getString("year8")) + Integer.parseInt(rs.getString("year9"))
						+ Integer.parseInt(rs.getString("year10")) + Integer.parseInt(rs.getString("year11"))
						+ Integer.parseInt(rs.getString("above_year12"));

				list.add(String.valueOf(total_dog));

				aList.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> equ_sum_list(String sus_no, String unit_name, String anml_type) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			Query q = null;
			String qry = "";
			String sql = "";
			int total_equ = 0;

			if (sus_no != "") {
				qry += " sus_no = ?";
			}

			if (anml_type != "") {
				qry += " anml_type = ?";
			}

			if (qry == "") {
				sql = ("select count(age) from tb_tms_animal_details_tbl where age > '1-3' ");
			} else {

				sql = ("select distinct d.sus_no,b.type_of_animal,\r\n"
						+ "	(select count(id) as year1_below from tb_tms_animal_details_tbl e where e.year >= 0 and e.year < 1 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year1 from tb_tms_animal_details_tbl e where e.year >= 1 and e.year < 2 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year2 from tb_tms_animal_details_tbl e where e.year >= 2 and e.year < 3 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year3 from tb_tms_animal_details_tbl e where e.year >= 3 and e.year < 4 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year4 from tb_tms_animal_details_tbl e where e.year >= 4 and e.year < 5 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year5 from tb_tms_animal_details_tbl e where e.year >= 5 and e.year < 6 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year6 from tb_tms_animal_details_tbl e where e.year >= 6 and e.year < 7 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year7 from tb_tms_animal_details_tbl e where e.year >= 7 and e.year < 8 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year8 from tb_tms_animal_details_tbl e where e.year >= 8 and e.year < 9 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year9 from tb_tms_animal_details_tbl e where e.year >= 9 and e.year < 10 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year10 from tb_tms_animal_details_tbl e where e.year >= 10 and e.year < 11 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year11 from tb_tms_animal_details_tbl e where e.year >= 11 and e.year < 12 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year12 from tb_tms_animal_details_tbl e where e.year >= 12 and e.year < 13 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year13 from tb_tms_animal_details_tbl e where e.year >= 13 and e.year < 14 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year14 from tb_tms_animal_details_tbl e where e.year >= 14 and e.year < 15 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year15 from tb_tms_animal_details_tbl e where e.year >= 15 and e.year < 16 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year16 from tb_tms_animal_details_tbl e where e.year >= 16 and e.year < 17 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year17 from tb_tms_animal_details_tbl e where e.year >= 17 and e.year < 18 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year18 from tb_tms_animal_details_tbl e where e.year >= 18 and e.year < 19 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year19 from tb_tms_animal_details_tbl e where e.year >= 19 and e.year < 20 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year20 from tb_tms_animal_details_tbl e where e.year >= 20 and e.year < 21 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year21 from tb_tms_animal_details_tbl e where e.year >= 21 and e.year < 22 and e.sus_no = ?"
						+ " and e.type_equines=d.type_equines),\r\n"
						+ "	(select count(id) as year22 from tb_tms_animal_details_tbl e where e.year >= 22   and sus_no = ?"
						+ " and e.type_equines=d.type_equines)	\r\n"
						+ "from tb_tms_animal_details_tbl d, tb_tms_type_of_animal_master b where d.type_equines = b.id and  d.anml_type=?"
						+ " and  d.sus_no = ?"
						+ " group by d.sus_no,d.type_equines,b.type_of_animal order by b.type_of_animal");
			}

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);
			stmt.setString(3, sus_no);
			stmt.setString(4, sus_no);
			stmt.setString(5, sus_no);
			stmt.setString(6, sus_no);
			stmt.setString(7, sus_no);
			stmt.setString(8, sus_no);
			stmt.setString(9, sus_no);
			stmt.setString(10, sus_no);
			stmt.setString(11, sus_no);
			stmt.setString(12, sus_no);
			stmt.setString(13, sus_no);
			stmt.setString(14, sus_no);
			stmt.setString(15, sus_no);
			stmt.setString(16, sus_no);
			stmt.setString(17, sus_no);
			stmt.setString(18, sus_no);
			stmt.setString(19, sus_no);
			stmt.setString(20, sus_no);
			stmt.setString(21, sus_no);
			stmt.setString(22, sus_no);
			stmt.setString(23, sus_no);
			stmt.setString(24, anml_type);
			stmt.setString(25, sus_no);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("type_of_animal"));
				list.add(rs.getString("year1_below"));
				list.add(rs.getString("year1"));
				list.add(rs.getString("year2"));
				list.add(rs.getString("year3"));
				list.add(rs.getString("year4"));
				list.add(rs.getString("year5"));
				list.add(rs.getString("year6"));
				list.add(rs.getString("year7"));
				list.add(rs.getString("year8"));
				list.add(rs.getString("year9"));
				list.add(rs.getString("year10"));
				list.add(rs.getString("year11"));
				list.add(rs.getString("year12"));
				list.add(rs.getString("year13"));
				list.add(rs.getString("year14"));
				list.add(rs.getString("year15"));
				list.add(rs.getString("year16"));
				list.add(rs.getString("year17"));
				list.add(rs.getString("year18"));
				list.add(rs.getString("year19"));
				list.add(rs.getString("year20"));
				list.add(rs.getString("year21"));
				list.add(rs.getString("year22"));

				total_equ = Integer.parseInt(rs.getString("year1_below")) + Integer.parseInt(rs.getString("year1"))
						+ Integer.parseInt(rs.getString("year2")) + Integer.parseInt(rs.getString("year3"))
						+ Integer.parseInt(rs.getString("year4")) + Integer.parseInt(rs.getString("year5"))
						+ Integer.parseInt(rs.getString("year6")) + Integer.parseInt(rs.getString("year7"))
						+ Integer.parseInt(rs.getString("year8")) + Integer.parseInt(rs.getString("year9"))
						+ Integer.parseInt(rs.getString("year10")) + Integer.parseInt(rs.getString("year11"))
						+ Integer.parseInt(rs.getString("year12")) + Integer.parseInt(rs.getString("year13"))
						+ Integer.parseInt(rs.getString("year14")) + Integer.parseInt(rs.getString("year15"))
						+ Integer.parseInt(rs.getString("year16")) + Integer.parseInt(rs.getString("year17"))
						+ Integer.parseInt(rs.getString("year18")) + Integer.parseInt(rs.getString("year19"))
						+ Integer.parseInt(rs.getString("year20")) + Integer.parseInt(rs.getString("year21"))
						+ Integer.parseInt(rs.getString("year22"));

				list.add(String.valueOf(total_equ));
				aList.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}

	public String updateBreed(TMS_TB_MISO_BREED_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_MISO_BREED_MASTER set type_breed=:type_breed,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("type_breed", obj.getType_breed())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateColor(TMS_TB_MISO_COLOR_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_MISO_COLOR_MASTER set type_color=:type_color,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("type_color", obj.getType_color())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateSpz(TMS_TB_MISO_SPLZ_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_MISO_SPLZ_MASTER set type_splztn=:type_splztn,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("type_splztn", obj.getType_splztn())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateSource(TMS_TB_MISO_SOURCE_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_MISO_SOURCE_MASTER set source=:source,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("source", obj.getSource())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateTypeDog(TB_TMS_TYPE_DOG obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TB_TMS_TYPE_DOG set type_dog=:type_dog,anml_type=:anml_type,modify_by=:modify_by,modify_on=:modify_on where id=:id";
			Query query = session.createQuery(hql).setString("type_dog", obj.getType_dog())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_on", obj.getModify_on()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateFitness(TB_TMS_ANIMAL_FITNESS_STATUS obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TB_TMS_ANIMAL_FITNESS_STATUS set fitness_status=:fitness_status,anml_type=:anml_type,modify_by=:modify_by,modify_on=:modify_on where id=:id";
			Query query = session.createQuery(hql).setString("fitness_status", obj.getFitness_status())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_on", obj.getModify_on()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateTypeEqu(TB_TMS_TYPE_OF_ANIMAL_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TB_TMS_TYPE_OF_ANIMAL_MASTER set type_of_animal=:type_of_animal,modify_by=:modify_by,modify_on=:modify_on where id=:id";
			Query query = session.createQuery(hql).setString("type_of_animal", obj.getType_of_animal())
					.setString("modify_by", obj.getModify_by()).setTimestamp("modify_on", obj.getModify_on())
					.setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateUeDog(TMS_TB_UE_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_UE_MASTER set sus_no=:sus_no,specialization=:specialization,ue_of_dogs=:ue_of_dogs,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("sus_no", obj.getSus_no())
					.setString("ue_of_dogs", obj.getUe_of_dogs()).setInteger("specialization", obj.getSpecialization())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}

	public String updateUeEqu(TMS_TB_UE_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_UE_MASTER set sus_no=:sus_no,type_equines=:type_equines,ue_of_equins=:ue_of_equins,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("sus_no", obj.getSus_no())
					.setString("ue_of_equins", obj.getUe_of_equins()).setInteger("type_equines", obj.getType_equines())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}
	
	
	public ArrayList<ArrayList<String>> all_data_print_ue_uh(String sus_no, String anml_type, String unit_name,
			String type_dog, String type_equines, String comd_sus_no,
			String cont_corps,String cont_div,String cont_bde) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String sql = "";
			String qry2 = "";
			String qry3 = "";
			
			String[] type_dog_array = type_dog.split(",");
			
			String[] type_equines_array = type_equines.split(",");
			
			

			if (anml_type.equals("")) {
				qry = "";
			}

			
			
			if (anml_type.equals("Army Dog")) {

				qry += " a.anml_type = 'Army Dog'";
				
				qry2 += "where b.anml_type = 'Army Dog'";

				
				if (!sus_no.equals("")) {
					qry += " and a.sus_no = ?";
				}
				
				
				if(type_dog_array.length>0) {
					qry +=" and ( ";
				}
				for(int i=0;i<type_dog_array.length;i++) {
					if(i==0) {
						qry +=" a.specialization = cast(? as integer) ";	
					}else {
						qry +=" or a.specialization = cast(? as integer) and (a.date_of_death = '' or a.date_of_death is null) ";
					}
				}
				if(type_dog_array.length>0) {
					qry +=" ) ";
				}
			}

			if (anml_type.equals("Army Equines")) {

				qry += " a.anml_type = 'Army Equines'";
				
				qry2 += "where b.anml_type = 'Army Equines'";

				if (!sus_no.equals("")) {
					qry += " and a.sus_no = ?";
				}
				
				
				if(type_equines_array.length>0) {
					qry +=" and ( ";
				}
				
				
				for(int i=0;i<type_equines_array.length;i++) {
					if(i==0) {
						qry +=" a.type_equines = cast(? as integer) ";	
					}else {
						qry +=" or a.type_equines = cast(? as integer) and (a.date_of_death = '' or a.date_of_death is null) ";
					}
				}
				if(type_equines_array.length>0) {
					qry +=" ) ";
				}
				
			}

			
			
			if (!sus_no.equals("")) {
				
				if (!sus_no.equals("") && !type_equines.equals("") && !type_equines.equals("0")) {
					
					if(type_equines_array.length>0) {
						qry2 +=" and b.sus_no = ? and ( ";
					}
					
					for(int i=0;i<type_equines_array.length;i++) {
						if(i==0) {
							qry2 +=" b.type_equines = cast(? as integer) ";	
						}else {
							qry2 +=" or b.type_equines = cast(? as integer) ";
						}
					}
					if(type_equines_array.length>0) {
						qry2 +=" ) ";
					}
					
					
				}
				else if (!sus_no.equals("") && !type_dog.equals("") && !type_dog.equals("0")) {
					
					if (!sus_no.equals("")) {
						
						
						if(type_dog_array.length>0) {
							qry2 +=" and b.sus_no = ? and ( ";
						}
						for(int i=0;i<type_dog_array.length;i++) {
							if(i==0) {
								qry2 +=" b.specialization = cast(? as integer) ";	
							}else {
								qry2 +=" or b.specialization = cast(? as integer) ";
							}
						}
						if(type_dog_array.length>0) {
							qry2 +=" ) ";
						}
						
					}
					
				}
			}
			
            if (sus_no.equals("") && !type_dog.equals("") && !type_dog.equals("0")) {
            	
            	if(type_dog_array.length>0) {
					qry2 +=" and ( ";
				}
				for(int i=0;i<type_dog_array.length;i++) {
					if(i==0) {
						qry2 +=" b.specialization = cast(? as integer) ";	
					}else {
						qry2 +=" or b.specialization = cast(? as integer) ";
					}
				}
				if(type_dog_array.length>0) {
					qry2 +=" ) ";
				}
					
			}
            
          if (sus_no.equals("") && !type_equines.equals("") && !type_equines.equals("0")) {
        	  
            	if(type_equines_array.length>0) {
					qry2 +=" and ( ";
				}
				for(int i=0;i<type_equines_array.length;i++) {
					if(i==0) {
						qry2 +=" b.type_equines = cast(? as integer) ";	
					}else {
						qry2 +=" or b.type_equines = cast(? as integer) ";
					}
				}
				if(type_equines_array.length>0) {
					qry2 +=" ) ";
				}
					
			}
			if(!comd_sus_no.equals("")) {
				qry3 += " where orb.form_code_control like ?  ";	
				}
				
			
			if( !cont_corps.equals("0")) {
				
				qry3 += " and  orb.form_code_control like ?  ";	
			
			}
			if( !cont_div.equals("0")) {
				
				qry3 += " and  orb.form_code_control like ? ";
				}
			
			if( !cont_bde.equals("0")) {
				
				qry3 += " and  orb.form_code_control like ? ";
				}

				
				if (anml_type.equals("Army Dog")) {

				if (qry != "")
					qry = " where " + qry.substring(1, qry.length());

				sql = "select distinct c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name,nkk.sus_no,nkk.type_splztn,nkk.ue,nk.uh  from \r\n" + 
						"\r\n" + 
						"(select \r\n" + 
						"  b.sus_no,m.type_splztn,case when (b.ue_of_dogs='0' OR B.ue_of_dogs is null) then '0' else b.ue_of_dogs END as \r\n" + 
						"  UE\r\n" + 
						"  from tb_tms_animals_ue_master b \r\n" + 
						"  left join tb_tms_specialization_master m on m.id=b.specialization \r\n" + 
						"   "+qry2+"   ) nkk\r\n" + 
						"   left join  \r\n" + 
						"   (select a.sus_no,m.type_splztn, CAST(count(a.specialization)  as character varying) as UH  from \r\n" + 
						"   tb_tms_animal_details_tbl a 			\r\n" + 
						"   left join tb_tms_specialization_master m on m.id=a.specialization\r\n" + 
						"    "+qry+"\r\n" + 
						"   group by a.sus_no,m.type_splztn) nk on nk.type_splztn = nkk.type_splztn\r\n" + 
						"  left join orbat_all_details_view c on c.sus_no = nkk.sus_no and c.status_sus_no = 'Active'\r\n" + 
						"  left join tb_miso_orbat_unt_dtl orb on orb.sus_no = nkk.sus_no and orb.status_sus_no='Active'							\r\n" + 
						"  left join all_fmn_view fv  on orb.sus_no = nkk.sus_no \r\n" + 
						"  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"  left join all_fmn_view fvm  on orb.sus_no = nkk.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"  left join all_fmn_view div  on orb.sus_no = nkk.sus_no and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"  left join all_fmn_view bde  on orb.sus_no = nkk.sus_no\r\n" + 
						"  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'" + qry3;
				
			}

			else {
				if (qry != "")
					qry = " where " + qry.substring(1, qry.length());

				sql = "select distinct c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name,nkk.sus_no,nkk.type_of_animal,nkk.ue,nk.uh  from \r\n" + 
						"\r\n" + 
						"(select \r\n" + 
						"  b.sus_no,m.type_of_animal,case when (b.ue_of_equins='0' OR B.ue_of_equins is null) then '0' else b.ue_of_equins END as \r\n" + 
						"  UE\r\n" + 
						"  from tb_tms_animals_ue_master b \r\n" + 
						"  left join tb_tms_type_of_animal_master m on m.id=b.type_equines \r\n" + 
						"   "+qry2+"  ) nkk\r\n" + 
						"   left join  \r\n" + 
						"   (select a.sus_no,m.type_of_animal, CAST(count(a.type_equines)  as character varying) as UH  from \r\n" + 
						"   tb_tms_animal_details_tbl a 			\r\n" + 
						"   left join tb_tms_type_of_animal_master m on m.id=a.type_equines\r\n" + 
						"   "+qry+"\r\n" + 
						"   group by a.sus_no,m.type_of_animal) nk on nk.type_of_animal = nkk.type_of_animal\r\n" + 
						"  left join orbat_all_details_view c on c.sus_no = nkk.sus_no and c.status_sus_no = 'Active'\r\n" + 
						"  left join tb_miso_orbat_unt_dtl orb on orb.sus_no = nkk.sus_no and orb.status_sus_no='Active'							\r\n" + 
						"  left join all_fmn_view fv  on orb.sus_no = nkk.sus_no \r\n" + 
						"  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"  left join all_fmn_view fvm  on orb.sus_no = nkk.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"  left join all_fmn_view div  on orb.sus_no = nkk.sus_no and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"  left join all_fmn_view bde  on orb.sus_no = nkk.sus_no\r\n" + 
						"  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'" + qry3;
			}

			stmt = conn.prepareStatement(sql);

			int j = 1;
			
			
			if (anml_type.equals("Army Dog")) {

				qry += " a.anml_type = 'Army Dog'";
				
				if (!sus_no.equals("")) {
					stmt.setString(j, sus_no);
					j += 1;
				}
				
				
				for(int i=0;i<type_dog_array.length;i++) {
					stmt.setString(j, type_dog_array[i]);
					j = j + 1;
				}
			}

			if (anml_type.equals("Army Equines")) {

				qry += " a.anml_type = 'Army Equines'";

				if (!sus_no.equals("")) {
					stmt.setString(j, sus_no);
					j += 1;
				}
				
				
				for(int i=0;i<type_equines_array.length;i++) {
					stmt.setString(j, type_equines_array[i]);
					j = j + 1;
				}
				
			}

			
			if (!sus_no.equals("")) {
				
				if (!sus_no.equals("") && !type_equines.equals("") && !type_equines.equals("0")) {
					stmt.setString(j, sus_no);
					j += 1;
					
					for(int i=0;i<type_equines_array.length;i++) {
						stmt.setString(j, type_equines_array[i]);
						j = j + 1;
					}
				}
				
				else if (!sus_no.equals("") && !type_dog.equals("") && !type_dog.equals("0")) {
					
					stmt.setString(j, sus_no);
					j += 1;
					
					for(int i=0;i<type_dog_array.length;i++) {
						stmt.setString(j, type_dog_array[i]);
						j = j + 1;
					}
				}
			}
			
			if (sus_no.equals("") && !type_dog.equals("") && !type_dog.equals("0")) {
				for(int i=0;i<type_dog_array.length;i++) {
					stmt.setString(j, type_dog_array[i]);
					j = j + 1;
				}
			}
			
			if (sus_no.equals("") && !type_equines.equals("") && !type_equines.equals("0")) {
                   for(int i=0;i<type_equines_array.length;i++) {
					stmt.setString(j, type_equines_array[i]);
					j = j + 1;
				}
				
			}

			
			
			if(!comd_sus_no.equals("")) {
				stmt.setString(j, comd_sus_no.toUpperCase()+"%");
				j += 1;		
			}
			if(!cont_corps.equals("0")) {
				
				stmt.setString(j, cont_corps.toUpperCase()+"%");
				j += 1;		
			}
			if(!cont_div.equals("0")) {
				
				stmt.setString(j, cont_div.toUpperCase()+"%");
				j += 1;		
			}
			if(!cont_bde.equals("0")) {
				
				stmt.setString(j, cont_bde.toUpperCase()+"%");
				j += 1;		
			}

			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				if (anml_type.equals("")) {

					list.add(rs.getString("unit_name"));
					list.add(rs.getString("num"));
					list.add(rs.getString("name"));
					list.add(rs.getString("type_breed"));
					list.add(rs.getString("type_color"));
					list.add(rs.getString("sex"));
					list.add(rs.getString("age"));
					list.add(rs.getString("microchip_no"));
					list.add(rs.getString("fitness_status"));
					list.add(rs.getString("tos"));
					list.add(rs.getString("created_date"));
				}

				else {

					if (anml_type.equals("Army Dog")) {

						list.add(rs.getString("cmd_name"));
						list.add(rs.getString("coprs_name"));
						list.add(rs.getString("div_name"));
						list.add(rs.getString("bde_name"));
						list.add(rs.getString("unit_name"));
						list.add(rs.getString("sus_no"));
						list.add(rs.getString("type_splztn"));
						list.add(rs.getString("ue"));
						list.add(rs.getString("uh"));
					} else {

						list.add(rs.getString("cmd_name"));
						list.add(rs.getString("coprs_name"));
						list.add(rs.getString("div_name"));
						list.add(rs.getString("bde_name"));
						list.add(rs.getString("unit_name"));
						list.add(rs.getString("sus_no"));
						list.add(rs.getString("type_of_animal"));
						list.add(rs.getString("ue"));
						list.add(rs.getString("uh"));
					}
				}
				aList.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return aList;
	}


	public String updateHospital(TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER set type_hospital=:type_hospital,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("type_hospital", obj.getType_hospital())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}
	
	public String updateVaccine(TMS_TB_MISO_VACCINE_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_MISO_VACCINE_MASTER set vaccine_name=:vaccine_name,frequency=:frequency,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("vaccine_name", obj.getVaccine_name())
					.setString("anml_type", obj.getAnml_type()).setInteger("frequency", obj.getFrequency()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}
	
	//employement 
	
	public String updateEmployment(TMS_TB_MISO_EMPLOYMENT_MASTER obj) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = "";

		try {
			String hql = "update TMS_TB_MISO_EMPLOYMENT_MASTER set emp_type=:emp_type,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
			Query query = session.createQuery(hql).setString("emp_type", obj.getEmp_type())
					.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
					.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			session.close();
		}
		return msg;
	}
	
	// Added by Mitesh (20-03-2025)
		public String updateAward(TB_ANIMALS_AWARD_MASTER obj) {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String msg = "";

			try {
				String hql = "update TB_ANIMALS_AWARD_MASTER set award_type=:award_type,anml_type=:anml_type,modify_by=:modify_by,modify_date=:modify_date where id=:id";
				Query query = session.createQuery(hql).setString("award_type", obj.getAward_type())
						.setString("anml_type", obj.getAnml_type()).setString("modify_by", obj.getModify_by())
						.setTimestamp("modify_date", obj.getModify_date()).setInteger("id", obj.getId());
				msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
				tx.commit();
			} catch (Exception e) {
				msg = "Data Not Updated.";
				tx.rollback();
			} finally {
				session.close();
			}
			return msg;
		}
	



	
}
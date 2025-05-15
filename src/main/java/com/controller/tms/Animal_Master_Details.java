package com.controller.tms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Tms_AnimalDao;
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
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Animal_Master_Details {

	@Autowired
	Tms_AnimalDao animaldao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	Animals_DetailsController a = new Animals_DetailsController();

	ValidationController validation = new ValidationController();

	/////////////////// Breed Master ////////////////

	@RequestMapping(value = "/Animal_Breed_Master", method = RequestMethod.GET)
	public ModelAndView Animal_Breed_Master(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Breed_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Add_Animals_BreedTile", "Add_Breed_Cmd", new TMS_TB_MISO_BREED_MASTER());
	}

	@RequestMapping(value = "/Add_Breed_Act", method = RequestMethod.POST)
	public ModelAndView Add_Breed_Act(@ModelAttribute("Add_Breed_Cmd") TMS_TB_MISO_BREED_MASTER bm,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Breed_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (bm.getType_breed().equals("") || bm.getType_breed().equals("null") || bm.getType_breed().equals(null)) {
			model.put("msg", "Please Enter Type Of Breed");
			return new ModelAndView("redirect:Animal_Breed_Master");
		} else if (validation.checkAnimalMasterLength(bm.getType_breed()) == false) {
			model.put("msg", validation.type_breedMSG);
			return new ModelAndView("redirect:Animal_Breed_Master");
		} else {

			int id = bm.getId() > 0 ? bm.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String type_breed = request.getParameter("type_breed");
			String animal_type = request.getParameter("anml_type");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {
				Query q0 = session0.createQuery(
						"select count(id) from TMS_TB_MISO_BREED_MASTER where upper(trim(type_breed))=upper(trim(:type_breed)) and anml_type=:animal_type and id !=:id");
				q0.setParameter("type_breed", type_breed);
				q0.setParameter("animal_type", animal_type);
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();

				/* CREATION OF NEW ANIMAL BREED MASTER */
				if (id == 0) {

					bm.setCreated_by(username);
					bm.setCreated_date(date);

					if (c == 0) {
						session0.save(bm);
						tx0.commit();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				/* UPDATION OF ANIMAL BREED MASTER */
				else {

					bm.setModify_by(username);
					bm.setModify_date(date);

					if (c == 0) {
						String msg1 = animaldao.updateBreed(bm);
						model.put("msg", msg1);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				session0.close();
			}

			return new ModelAndView("redirect:Animal_Breed_Master");
		}

	}

	@RequestMapping(value = "/admin/search_breed_master1", method = RequestMethod.POST)
	public ModelAndView search_breed_master1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "anml_type3", required = false) String anml_type,
			@RequestParam(value = "type_breed1", required = false) String type_breed) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Breed_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkAnimalMasterLength(type_breed) == false) {
			Mmap.put("msg", validation.type_breedMSG);
			return new ModelAndView("redirect:Animal_Breed_Master");
		}

		String roleType = session.getAttribute("roleType").toString();

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();
		Mmap.put("type_breed1", type_breed);
		Mmap.put("anml_type3", anml_type);

		Query q = null;
		String qry = "";
		if (!type_breed.equals("") && !anml_type.equals("undefined")) {
			qry += " UPPER(type_breed) like :type_breed ";
			q = sessionPA.createQuery("from TMS_TB_MISO_BREED_MASTER where id > 0 and anml_type=:anml_type and " + qry
					+ "order by type_breed");

		} else {
			q = sessionPA.createQuery(
					"from TMS_TB_MISO_BREED_MASTER where  id > 0 and anml_type=:anml_type " + "order by type_breed");
		}

		if (type_breed != "") {
			q.setString("type_breed", "%" + type_breed.toUpperCase() + "%");
		}

		q.setParameter("anml_type", anml_type);

		@SuppressWarnings("unchecked")
		List<TMS_TB_MISO_BREED_MASTER> list = (List<TMS_TB_MISO_BREED_MASTER>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {

			String f = "";

			String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Type of Breed ?') ){editData("
					+ list.get(i).getId() + ",'" + list.get(i).getType_breed() + "')}else{ return false;}\"";
			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Type of Breed ?') ){deleteData("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			if (roleType.equals("ALL")) {
				f += deleteButton;
				f += updateButton;
			}

			else if (roleType.equals("DEO")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("App")) {

			}

			list.get(i).setModify_by(f);
		}
		Mmap.put("anml_type3", anml_type);
		Mmap.put("list", list);
		return new ModelAndView("Add_Animals_BreedTile");
	}
	//////////////////////////////////////////

	//////////////// Color Master /////////////////

	@RequestMapping(value = "/Animal_Colour_Master", method = RequestMethod.GET)
	public ModelAndView Animal_Colour_Master(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Colour_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Add_Animals_ColorTile", "Add_Breed_Cmd", new TMS_TB_MISO_COLOR_MASTER());
	}

	@RequestMapping(value = "/Add_Color_Act", method = RequestMethod.POST)
	public ModelAndView Add_Color_Act(@ModelAttribute("Add_Color_Cmd") TMS_TB_MISO_COLOR_MASTER cm,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Colour_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (cm.getType_color().equals("") || cm.getType_color().equals("null") || cm.getType_color().equals(null)) {
			model.put("msg", "Please Enter Type Of Color");
			return new ModelAndView("redirect:Animal_Colour_Master");
		}

		else if (validation.check30Length(cm.getType_color()) == false) {
			model.put("msg", validation.type_colorMSG);
			return new ModelAndView("redirect:Animal_Colour_Master");
		}

		else {
			int id = cm.getId() > 0 ? cm.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String type_color = request.getParameter("type_color");
			String animal_type = request.getParameter("anml_type");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {

				Query q0 = session0.createQuery(
						"select count(id) from TMS_TB_MISO_COLOR_MASTER where upper(trim(type_color))=upper(trim(:type_color)) and anml_type=:animal_type and id !=:id");
				q0.setParameter("type_color", type_color);
				q0.setParameter("animal_type", animal_type);
				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				/* CREATION OF NEW ANIMAL COLOR MASTER */
				if (id == 0) {

					cm.setCreated_by(username);
					cm.setCreated_date(date);

					if (c.equals(0) || c == 0) {

						session0.save(cm);
						tx0.commit();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				/* UPDATION OF ANIMAL COLOR MASTER */
				else {

					cm.setModify_by(username);
					cm.setModify_date(date);

					if (c.equals(0) || c == 0) {
						String msg = animaldao.updateColor(cm);
						model.put("msg", msg);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				session0.close();
			}

			return new ModelAndView("redirect:Animal_Colour_Master");
		}
	}

	@RequestMapping(value = "/admin/search_color_master1", method = RequestMethod.POST)
	public ModelAndView search_color_master1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "anml_type3", required = false) String anml_type,
			@RequestParam(value = "type_color1", required = false) String type_color) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Colour_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.check30Length(type_color) == false) {
			Mmap.put("msg", validation.type_colorMSG);
			return new ModelAndView("redirect:Animal_Colour_Master");
		}

		String roleType = session.getAttribute("roleType").toString();

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();
		Mmap.put("type_color1", type_color);
		Mmap.put("anml_type3", anml_type);

		Query q = null;
		String qry = "";
		if (!type_color.equals("") && !anml_type.equals("undefined")) {
			qry += " UPPER(type_color) like :type_color ";
			q = sessionPA.createQuery("from TMS_TB_MISO_COLOR_MASTER where id > 0 and anml_type=:anml_type and " + qry
					+ "order by type_color");

		} else {
			q = sessionPA.createQuery(
					"from TMS_TB_MISO_COLOR_MASTER where  id > 0 and anml_type=:anml_type " + "order by type_color");

		}

		if (type_color != "") {
			q.setString("type_color", "%" + type_color.toUpperCase() + "%");
		}

		q.setParameter("anml_type", anml_type);

		@SuppressWarnings("unchecked")
		List<TMS_TB_MISO_COLOR_MASTER> list = (List<TMS_TB_MISO_COLOR_MASTER>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {

			String f = "";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Type of Color ?') ){editData("
					+ list.get(i).getId() + ",'" + list.get(i).getType_color() + "')}else{ return false;}\"";

			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Type of Color ?') ){deleteData("
					+ list.get(i).getId() + ")}else{ return false;}\"";

			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			if (roleType.equals("ALL")) {
				f += deleteButton;
				f += updateButton;
			}

			else if (roleType.equals("DEO")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("App")) {
			}

			list.get(i).setModify_by(f);
		}
		Mmap.put("anml_type3", anml_type);
		Mmap.put("list", list);
		return new ModelAndView("Add_Animals_ColorTile");
	}
	/////////////////////////////////////////////////////////////

	///////////////// Specialization Master //////////////////////

	@RequestMapping(value = "/Animal_Specialization_Master", method = RequestMethod.GET)
	public ModelAndView Animal_Specialization_Master(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Specialization_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Add_Animals_SplzTile", "Add_Splz_Cmd", new TMS_TB_MISO_SPLZ_MASTER());
	}

	@RequestMapping(value = "/Add_Splz_Act", method = RequestMethod.POST)
	public ModelAndView Add_Splz_Act(@ModelAttribute("Add_Splz_Cmd") TMS_TB_MISO_SPLZ_MASTER sm,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Specialization_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (sm.getType_splztn().equals("") || sm.getType_splztn().equals("null") || sm.getType_splztn().equals(null)) {
			model.put("msg", "Please Enter Type of Specilization");
			return new ModelAndView("redirect:Animal_Specialization_Master");
		}

		else if (validation.checkAnimalMasterLength(sm.getType_splztn()) == false) {
			model.put("msg", validation.type_splztnMSG);
			return new ModelAndView("redirect:Animal_Specialization_Master");
		}

		else {

			int id = sm.getId() > 0 ? sm.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String type_splztn = request.getParameter("type_splztn");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {

				Query q0 = session0.createQuery(
						"select count(id) from TMS_TB_MISO_SPLZ_MASTER where upper(trim(type_splztn))=upper(trim(:type_splztn)) and id !=:id");
				q0.setParameter("type_splztn", type_splztn);
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();

				sm.setAnml_type("Army Dog");
				/* CREATION OF ANIMAL SPZ MASTER */
				if (id == 0) {

					sm.setCreated_by(username);
					sm.setCreated_date(date);

					if (c.equals(0) || c == 0) {
						int did = (Integer) session0.save(sm);
						tx0.commit();
						model.put("msg", "Data Saved Successfully.");
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

				/* UPDATION OF ANIMAL SPZ MASTER */
				else {

					sm.setModify_by(username);
					sm.setModify_date(date);

					if (c.equals(0) || c == 0) {
						String msg = animaldao.updateSpz(sm);
						model.put("msg", msg);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}
			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			} finally {
				session0.close();
			}
		}

		return new ModelAndView("redirect:Animal_Specialization_Master");
	}

	@RequestMapping(value = "/admin/search_spz_master1", method = RequestMethod.POST)
	public ModelAndView search_spz_master1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "type_splztn1", required = false) String type_splztn) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Specialization_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkAnimalMasterLength(type_splztn) == false) {
			Mmap.put("msg", validation.type_splztnMSG);
			return new ModelAndView("redirect:Animal_Specialization_Master");
		}

		String roleType = session.getAttribute("roleType").toString();

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();

		Mmap.put("type_splztn1", type_splztn);

		Query q = null;
		String qry = "";
		if (type_splztn != "") {
			qry += " and UPPER(type_splztn) like :type_splztn ";

			q = sessionPA.createQuery("from TMS_TB_MISO_SPLZ_MASTER where id > 0  " + qry + "order by type_splztn");

		} else {
			q = sessionPA.createQuery("from TMS_TB_MISO_SPLZ_MASTER where id > 0 order by type_splztn");
		}

		if (type_splztn != "") {
			q.setString("type_splztn", "%" + type_splztn.toUpperCase() + "%");
		}

		@SuppressWarnings("unchecked")
		List<TMS_TB_MISO_SPLZ_MASTER> list = (List<TMS_TB_MISO_SPLZ_MASTER>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {

			String f = "";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Type of Specialisation ?') ){editData("
					+ list.get(i).getId() + ",'" + list.get(i).getType_splztn() + "')}else{ return false;}\"";

			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Type of Specialisation ?') ){deleteData("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			if (roleType.equals("ALL")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("DEO")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("App")) {
			}

			list.get(i).setModify_by(f);
		}
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Add_Animals_SplzTile");
	}
	////////////////////////////////////////////////

	@RequestMapping(value = "/Add_Source_Act", method = RequestMethod.POST)
	public ModelAndView Add_Source_Act(@ModelAttribute("Add_Source_Cmd") TMS_TB_MISO_SOURCE_MASTER sm,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		if (sm.getSource().equals("") || sm.getSource().equals("null") || sm.getSource().equals(null)) {
			model.put("msg", "Please Enter Type Of Source");
			return new ModelAndView("redirect:Animal_Source_Master");
		}

		else if (validation.checkAnimalMasterLength(sm.getSource()) == false) {
			model.put("msg", validation.sourceMSG);
			return new ModelAndView("redirect:Animal_Source_Master");
		}

		else {

			int id = sm.getId() > 0 ? sm.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String source = request.getParameter("source").toString();
			String animal_type = request.getParameter("anml_type");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {
				Query q0 = session0.createQuery(
						"select count(id) from TMS_TB_MISO_SOURCE_MASTER where upper(trim(source))=upper(trim(:source)) and anml_type=:animal_type and id !=:id");
				q0.setParameter("source", source);
				q0.setParameter("animal_type", animal_type);
				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				/* CREATION OF NEW ANIMAL Source MASTER */

				if (id == 0) {

					sm.setCreated_by(username);
					sm.setCreated_date(date);

					if (c.equals(0) || c == 0) {

						session0.save(sm);

						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				/* UPDATION OF ANIMAL source MASTER */
				else {

					sm.setModify_by(username);
					sm.setModify_date(date);

					if (c.equals(0) || c == 0) {
						String msg = animaldao.updateSource(sm);
						model.put("msg", msg);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				tx0.commit();
				session0.close();
			}
			return new ModelAndView("redirect:Animal_Source_Master");
		}
	}

	@RequestMapping(value = "/admin/search_source_master1", method = RequestMethod.POST)
	public ModelAndView search_source_master1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "anml_type3", required = false) String anml_type,
			@RequestParam(value = "source1", required = false) String source) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Source_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkAnimalMasterLength(source) == false) {
			Mmap.put("msg", validation.sourceMSG);
			return new ModelAndView("redirect:Animal_Source_Master");
		}

		String roleType = session.getAttribute("roleType").toString();

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();

		Mmap.put("source1", source);
		Mmap.put("anml_type3", anml_type);

		Query q = null;
		String qry = "";
		if (source != "") {
			qry += " UPPER(source) like :source ";
			q = sessionPA.createQuery("from TMS_TB_MISO_SOURCE_MASTER where id > 0 and anml_type=:anml_type and " + qry
					+ "order by source");

		} else {
			q = sessionPA.createQuery(
					"from TMS_TB_MISO_SOURCE_MASTER where  id > 0 and anml_type=:anml_type " + "order by source");
		}

		if (source != "") {
			q.setString("source", "%" + source.toUpperCase() + "%");
		}
		q.setParameter("anml_type", anml_type);

		@SuppressWarnings("unchecked")
		List<TMS_TB_MISO_SOURCE_MASTER> list = (List<TMS_TB_MISO_SOURCE_MASTER>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {

			String f = "";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Type of Source ?') ){editData("
					+ list.get(i).getId() + ",'" + list.get(i).getSource() + "')}else{ return false;}\"";

			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Type of Source ?') ){deleteData("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			if (roleType.equals("ALL")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("DEO")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("App")) {
			}

			list.get(i).setModify_by(f);
		}
		Mmap.put("anml_type3", anml_type);
		Mmap.put("list", list);
		return new ModelAndView("Animal_Source_MasterTiles");
	}
	///////////////////////////////////////

	//// TYPE DOG
	@RequestMapping(value = "/Add_Type_of_Dog", method = RequestMethod.GET)
	public ModelAndView Add_Type_of_Dog(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Type_of_Dog", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AddTypeofDogTiles", "Add_Type_of_DogCmd", new TB_TMS_TYPE_DOG());
	}

	@RequestMapping(value = "/Add_Type_of_DogAct", method = RequestMethod.POST)
	public ModelAndView Add_Type_of_DogAct(@ModelAttribute("Add_Type_of_DogCmd") TB_TMS_TYPE_DOG td,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Type_of_Dog", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		td.setUserid(userid);

		if (td.getType_dog().equals("") || td.getType_dog().equals("null") || td.getType_dog().equals(null)) {
			model.put("msg", "Please Enter Type Of Dog");
			return new ModelAndView("redirect:Add_Type_of_Dog");
		}

		else if (validation.check30Length(td.getType_dog()) == false) {
			model.put("msg", validation.type_dogMSG);
			return new ModelAndView("redirect:Add_Type_of_Dog");
		}

		else {
			int id = td.getId() > 0 ? td.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String type_dog = request.getParameter("type_dog");
			td.setAnml_type("Army Dog");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {
				Query q0 = session0.createQuery(
						"select count(id) from TB_TMS_TYPE_DOG where upper(trim(type_dog))=upper(trim(:type_dog)) and id !=:id");
				q0.setParameter("type_dog", type_dog);
				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				/* CREATION OF ANIMAL TYPE OF DOG MASTER */
				if (id == 0) {

					td.setCreated_by(username);
					td.setCreated_on(date);
					td.setAnml_type("Army Dog");

					if (c.equals(0) || c == 0) {
						int did = (Integer) session0.save(td);
						model.put("msg", "Data Saved Successfully.");
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

				/* UPDATION OF ANIMAL TYPE OF DOG MASTER */
				else {

					td.setModify_by(username);
					td.setModify_on(date);

					if (c.equals(0) || c == 0) {
						String msg1 = animaldao.updateTypeDog(td);
						model.put("msg", msg1);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}
			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				tx0.commit();
				session0.close();
			}
		}
		return new ModelAndView("redirect:Add_Type_of_Dog");
	}

	@RequestMapping(value = "/admin/search_type_dog_master1", method = RequestMethod.POST)
	public ModelAndView search_type_dog_master1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "type_dog1", required = false) String type_dog) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Type_of_Dog", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.check30Length(type_dog) == false) {
			Mmap.put("msg", validation.type_dogMSG);
			return new ModelAndView("redirect:Add_Type_of_Dog");
		}

		String roleType = session.getAttribute("roleType").toString();

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();

		Mmap.put("type_dog1", type_dog);

		Query q = null;
		String qry = "";
		if (type_dog != "") {
			qry += " and UPPER(type_dog) like :type_dog ";

			q = sessionPA.createQuery("from TB_TMS_TYPE_DOG where id > 0 " + qry + "order by type_dog");
		} else {
			q = sessionPA.createQuery("from TB_TMS_TYPE_DOG where id > 0 order by type_dog");
		}

		if (type_dog != "") {
			q.setString("type_dog", "%" + type_dog.toUpperCase() + "%");
		}

		@SuppressWarnings("unchecked")
		List<TB_TMS_TYPE_DOG> list = (List<TB_TMS_TYPE_DOG>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {

			String f = "";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Type of Dog ?') ){editData("
					+ list.get(i).getId() + ",'" + list.get(i).getType_dog() + "')}else{ return false;}\"";

			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Type of Dog ?') ){deleteData("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			if (roleType.equals("ALL")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("DEO")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("App")) {
			}
			list.get(i).setModify_by(f);
		}

		Mmap.put("list", list);
		return new ModelAndView("AddTypeofDogTiles");
	}
	//////////////////////////////////////////////////////////////

	//////// END TYPE DOG

	////////// FITNESS Master //////////////

	@RequestMapping(value = "/Add_Animal_Fitness_Status", method = RequestMethod.GET)
	public ModelAndView Add_Animal_Fitness_Status(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Animal_Fitness_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AddAnimalFitnessStatusTiles", "Add_Animal_Fitness_StatusCmd",
				new TB_TMS_ANIMAL_FITNESS_STATUS());
	}

	@RequestMapping(value = "/Add_Animal_Fitness_StatusAct", method = RequestMethod.POST)
	public ModelAndView Add_Animal_Fitness_StatusAct(
			@ModelAttribute("Add_Animal_Fitness_StatusCmd") TB_TMS_ANIMAL_FITNESS_STATUS td, HttpServletRequest request,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Animal_Fitness_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		td.setUserid(userid);

		if (td.getFitness_status().equals("") || td.getFitness_status().equals("null")
				|| td.getFitness_status().equals(null)) {
			model.put("msg", "Please Enter Fitness Status");
			return new ModelAndView("redirect:Add_Animal_Fitness_Status");
		}

		else if (validation.check35Length(td.getFitness_status()) == false) {
			model.put("msg", validation.fitness_statusMSG);
			return new ModelAndView("redirect:Add_Animal_Fitness_Status");
		}

		else {

			int id = td.getId() > 0 ? td.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String fitness_status = request.getParameter("fitness_status");
			String animal_type = request.getParameter("anml_type");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {
				Query q0 = session0.createQuery(
						"select count(id) from TB_TMS_ANIMAL_FITNESS_STATUS where upper(trim(fitness_status))=upper(trim(:fitness_status)) and anml_type=:animal_type and id !=:id");
				q0.setParameter("fitness_status", fitness_status);
				q0.setParameter("animal_type", animal_type);
				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				/* CREATION OF NEW ANIMAL FITNESS MASTER */
				if (id == 0) {

					td.setCreated_by(username);
					td.setCreated_on(date);

					if (c.equals(0) || c == 0) {
						session0.save(td);
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				/* UPDATION OF ANIMAL FITNESS MASTER */
				else {

					td.setModify_by(username);
					td.setModify_on(date);

					if (c.equals(0) || c == 0) {
						String msg1 = animaldao.updateFitness(td);
						model.put("msg", msg1);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				tx0.commit();
				session0.close();
			}
			return new ModelAndView("redirect:Add_Animal_Fitness_Status");
		}
	}

	@RequestMapping(value = "/admin/search_fitness_master1", method = RequestMethod.POST)
	public ModelAndView search_fitness_master1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "anml_type3", required = false) String anml_type,
			@RequestParam(value = "fitness_status1", required = false) String fitness_status) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Animal_Fitness_Status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.check35Length(fitness_status) == false) {
			Mmap.put("msg", validation.fitness_statusMSG);
			return new ModelAndView("redirect:Add_Animal_Fitness_Status");
		}

		String roleType = session.getAttribute("roleType").toString();

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();

		Mmap.put("fitness_status1", fitness_status);
		Mmap.put("anml_type3", anml_type);

		Query q = null;
		String qry = "";
		if (fitness_status != "") {
			qry += " UPPER(fitness_status) like :fitness_status ";

			q = sessionPA.createQuery("from TB_TMS_ANIMAL_FITNESS_STATUS where id > 0 and anml_type=:anml_type and "
					+ qry + "order by fitness_status");

		} else {
			q = sessionPA.createQuery("from TB_TMS_ANIMAL_FITNESS_STATUS where  id > 0 and anml_type=:anml_type "
					+ "order by fitness_status");

		}

		if (fitness_status != "") {
			q.setString("fitness_status", "%" + fitness_status.toUpperCase() + "%");
		}

		q.setParameter("anml_type", anml_type);

		@SuppressWarnings("unchecked")
		List<TB_TMS_ANIMAL_FITNESS_STATUS> list = (List<TB_TMS_ANIMAL_FITNESS_STATUS>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {

			String f = "";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Fitness Status ?') ){editData("
					+ list.get(i).getId() + ",'" + list.get(i).getFitness_status() + "')}else{ return false;}\"";

			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Fitness Status ?') ){deleteData("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			if (roleType.equals("ALL")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("DEO")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("App")) {
			}

			list.get(i).setModify_by(f);
		}
		Mmap.put("anml_type3", anml_type);
		Mmap.put("list", list);
		return new ModelAndView("AddAnimalFitnessStatusTiles");
	}
	///////////////////////////////////////////////////////

	/////// END ANIMAL FITNESS

	//////////////////// Type of Animal Master /////////////////

	@RequestMapping(value = "/Add_Type_of_Animal_Master", method = RequestMethod.GET)
	public ModelAndView Add_Type_of_Animal_Master(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Type_of_Animal_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AddTypeofAnimalMasterTiles", "Add_Type_of_Animal_MasterCmd",
				new TB_TMS_TYPE_OF_ANIMAL_MASTER());
	}

	@RequestMapping(value = "/Add_Type_of_Animal_MasterAct", method = RequestMethod.POST)
	public ModelAndView Add_Type_of_Animal_MasterAct(
			@ModelAttribute("Add_Type_of_Animal_MasterCmd") TB_TMS_TYPE_OF_ANIMAL_MASTER td, HttpServletRequest request,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Type_of_Animal_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		td.setUserid(userid);

		if (td.getType_of_animal().equals("") || td.getType_of_animal().equals("null")
				|| td.getType_of_animal().equals(null)) {
			model.put("msg", "Please Enter Type of Equines");
			return new ModelAndView("redirect:Add_Type_of_Animal_Master");
		}

		else if (validation.checkAnimalMasterLength(td.getType_of_animal()) == false) {
			model.put("msg", validation.type_of_animalMSG);
			return new ModelAndView("redirect:Animal_Specialization_Master");
		}

		else {

			int id = 0;

			if (td.getId() > 0) {
				id = td.getId();
			}

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String type_of_animal = request.getParameter("type_of_animal");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {

				Query q0 = session0.createQuery(
						"select count(id) from TB_TMS_TYPE_OF_ANIMAL_MASTER where upper(trim(type_of_animal))=upper(trim(:type_of_animal)) and id !=:id");
				q0.setParameter("type_of_animal", type_of_animal);
				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				/* CREATION OF ANIMAL TYPE OF ANIMAL MASTER */
				if (id == 0) {

					td.setCreated_by(username);
					td.setCreated_on(date);

					if (c.equals(0) || c == 0) {
						int did = (Integer) session0.save(td);
						model.put("msg", "Data Saved Successfully.");
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

				/* UPDATION OF ANIMAL TYPE OF ANIMAL MASTER */
				else {

					td.setModify_by(username);
					td.setModify_on(date);

					if (c.equals(0) || c == 0) {
						String msg1 = animaldao.updateTypeEqu(td);
						model.put("msg", msg1);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}
			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				tx0.commit();
				session0.close();
			}
		}
		return new ModelAndView("redirect:Add_Type_of_Animal_Master");
	}

	@RequestMapping(value = "/admin/search_type_equ_master1", method = RequestMethod.POST)
	public ModelAndView search_type_equ_master1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "type_of_animal1", required = false) String type_of_animal) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Add_Type_of_Animal_Master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (validation.checkAnimalMasterLength(type_of_animal) == false) {
			Mmap.put("msg", validation.type_of_animalMSG);
			return new ModelAndView("redirect:Add_Type_of_Animal_Master");
		}

		String roleType = session.getAttribute("roleType").toString();

		Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
		sessionPA.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionPA.beginTransaction();

		Mmap.put("type_of_animal1", type_of_animal);

		Query q = null;
		String qry = "";
		if (type_of_animal != "") {

			qry += " and UPPER(type_of_animal) like :type_of_animal ";

			q = sessionPA
					.createQuery("from TB_TMS_TYPE_OF_ANIMAL_MASTER where id > 0 " + qry + "order by type_of_animal");
		} else {
			q = sessionPA.createQuery("from TB_TMS_TYPE_OF_ANIMAL_MASTER where id > 0 order by type_of_animal");
		}

		if (type_of_animal != "") {
			q.setString("type_of_animal", "%" + type_of_animal.toUpperCase() + "%");
		}

		@SuppressWarnings("unchecked")
		List<TB_TMS_TYPE_OF_ANIMAL_MASTER> list = (List<TB_TMS_TYPE_OF_ANIMAL_MASTER>) q.list();
		tx.commit();
		sessionPA.close();

		for (int i = 0; i < list.size(); i++) {

			String f = "";

			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Type of Equines ?') ){editData("
					+ list.get(i).getId() + ",'" + list.get(i).getType_of_animal() + "')}else{ return false;}\"";

			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Type of Equines ?') ){deleteData("
					+ list.get(i).getId() + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

			if (roleType.equals("ALL")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("DEO")) {
				f += deleteButton;
				f += updateButton;
			} else if (roleType.equals("App")) {

			}
			list.get(i).setModify_by(f);
		}
		Mmap.put("list", list);
		return new ModelAndView("AddTypeofAnimalMasterTiles");
	}
	///////////////////////////////////////////////////////////
	
	//----------hospital master          Pranay (21-03-24)
	
		@RequestMapping(value = "/Animal_Hospital_Master", method = RequestMethod.GET)
		public ModelAndView Animal_Hospital_Master(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

			/*String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Hospital_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}*/
			Mmap.put("msg", msg);
			return new ModelAndView("Add_Animals_HospitalTile", "Add_Hospital_Cmd", new TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER());
		}

		@RequestMapping(value = "/Add_Hospital_Act", method = RequestMethod.POST)
		public ModelAndView Add_Hospital_Act(@ModelAttribute("Add_Hospital_Cmd") TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER bm,
				HttpServletRequest request, ModelMap model, HttpSession session) {

			/*String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Hospital_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/

			if (bm.getType_hospital().equals("") || bm.getType_hospital().equals("null") || bm.getType_hospital().equals(null)) {
				model.put("msg", "Please Enter Hospital");
				return new ModelAndView("redirect:Animal_Hospital_Master");
			} else if (validation.checkAnimalMasterLength(bm.getType_hospital()) == false) {
				model.put("msg", validation.type_breedMSG);
				return new ModelAndView("redirect:Animal_Hospital_Master");
			} else {

				int id = bm.getId() > 0 ? bm.getId() : 0;

				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String type_hospital = request.getParameter("type_hospital");
				String animal_type = request.getParameter("anml_type");

				Session session0 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx0 = session0.beginTransaction();

				try {
					Query q0 = session0.createQuery(
							"select count(id) from TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER where upper(trim(type_hospital))=upper(trim(:type_hospital)) and anml_type=:animal_type and id !=:id");
					q0.setParameter("type_hospital", type_hospital);
					q0.setParameter("animal_type", animal_type);
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();

					
					if (id == 0) {

						bm.setCreated_by(username);
						bm.setCreated_date(date);

						if (c == 0) {
							session0.save(bm);
							tx0.commit();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					 
					else {

						bm.setModify_by(username);
						bm.setModify_date(date);

						if (c == 0) {
							String msg1 = animaldao.updateHospital(bm);
							model.put("msg", msg1);
						} else {
							model.put("msg", "Data already Exist.");
						}
					}

				} catch (Exception e) {
					session0.getTransaction().rollback();
					return null;
				}

				finally {
					session0.close();
				}

				return new ModelAndView("redirect:Animal_Hospital_Master");
			}

		}

		@RequestMapping(value = "/admin/search_hospital_master1", method = RequestMethod.POST)
		public ModelAndView search_hospital_master1(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "anml_type3", required = false) String anml_type,
				@RequestParam(value = "type_hospital1", required = false) String type_hospital) {

			/*String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Hospital_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/

			if (validation.checkAnimalMasterLength(type_hospital) == false) {
				Mmap.put("msg", validation.type_breedMSG);
				return new ModelAndView("redirect:Animal_Hospital_Master");
			}

			String roleType = session.getAttribute("roleType").toString();

			Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
			sessionPA.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = sessionPA.beginTransaction();
			Mmap.put("type_hospital1", type_hospital);
			Mmap.put("anml_type3", anml_type);

			Query q = null;
			String qry = "";
			if (!type_hospital.equals("") && !anml_type.equals("undefined")) {
				qry += " UPPER(type_hospital) like :type_hospital ";
				q = sessionPA.createQuery("from TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER where id > 0 and anml_type=:anml_type and " + qry
						+ "order by type_hospital");

			} else {
				q = sessionPA.createQuery(
						"from TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER where  id > 0 and anml_type=:anml_type " + "order by type_hospital");
			}

			if (type_hospital != "") {
				q.setString("type_hospital", "%" + type_hospital.toUpperCase() + "%");
			}

			q.setParameter("anml_type", anml_type);

			@SuppressWarnings("unchecked")
			List<TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER> list = (List<TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER>) q.list();
			tx.commit();
			sessionPA.close();

			for (int i = 0; i < list.size(); i++) {

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Hospital ?') ){editData("
						+ list.get(i).getId() + ",'" + list.get(i).getType_hospital() + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Hospital ?') ){deleteData("
						+ list.get(i).getId() + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				if (roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}

				else if (roleType.equals("DEO")) {
					f += deleteButton;
					f += updateButton;
				} else if (roleType.equals("App")) {

				}

				list.get(i).setModify_by(f);
			}
			Mmap.put("anml_type3", anml_type);
			Mmap.put("list", list);
			return new ModelAndView("Add_Animals_HospitalTile");
		}
		
		//vaccinatiom master
		
		@RequestMapping(value = "/Animal_Vaccine_Master", method = RequestMethod.GET)
		public ModelAndView Animal_Vaccination_Master(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

			String roleid = session.getAttribute("roleid").toString();
			/*Boolean val = roledao.ScreenRedirect("Animal_Vaccine_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("Add_Animals_VaccinationTile", "Add_Vaccine_Cmd", new TMS_TB_MISO_VACCINE_MASTER());
		}
		
		@RequestMapping(value = "/Add_Vaccine_Act", method = RequestMethod.POST)
		public ModelAndView Add_Vaccine_Act(@ModelAttribute("Add_Vaccine_Cmd") TMS_TB_MISO_VACCINE_MASTER bm,
				HttpServletRequest request, ModelMap model, HttpSession session) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Vaccine_Master", roleid);
			/*if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/

			if (bm.getVaccine_name().trim().equals("") || bm.getVaccine_name().equals("null") || bm.getVaccine_name().equals(null)) {
				model.put("msg", "Please Enter Vaccine");
				return new ModelAndView("redirect:Animal_Vaccine_Master");
			} else if (validation.checkAnimalMasterLength(bm.getVaccine_name()) == false) {
				model.put("msg", validation.vaccine_MSG);
				return new ModelAndView("redirect:Animal_Vaccine_Master");
			}else if (bm.getFrequency() == 0 ) {
				model.put("msg","Please Enter Frequency.");
				return new ModelAndView("redirect:Animal_Vaccine_Master");
			}
			
			else {

				int id = bm.getId() > 0 ? bm.getId() : 0;

				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String vaccine_name = request.getParameter("vaccine_name");
				String animal_type = request.getParameter("anml_type");

				Session session0 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx0 = session0.beginTransaction();

				try {
					Query q0 = session0.createQuery(
							"select count(id) from TMS_TB_MISO_VACCINE_MASTER where upper(trim(vaccine_name))=upper(trim(:vaccine_name)) and anml_type=:animal_type and id !=:id");
					q0.setParameter("vaccine_name", vaccine_name.trim());
					q0.setParameter("animal_type", animal_type);
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();

					/* CREATION OF NEW ANIMAL BREED MASTER */
					if (id == 0) {

						bm.setCreated_by(username);
						bm.setCreated_date(date);

						if (c == 0) {
							session0.save(bm);
							tx0.commit();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					/* UPDATION OF ANIMAL BREED MASTER */
					else {

						bm.setModify_by(username);
						bm.setModify_date(date);

						if (c == 0) {
							String msg1 = animaldao.updateVaccine(bm);
							model.put("msg", msg1);
						} else {
							model.put("msg", "Data already Exist.");
						}
					}

				} catch (Exception e) {
					session0.getTransaction().rollback();
					return null;
				}

				finally {
					session0.close();
				}

				return new ModelAndView("redirect:Animal_Vaccine_Master");
			}

		}
		
		
		
		@RequestMapping(value = "/admin/search_vaccine_master1", method = RequestMethod.POST)
		public ModelAndView search_vaccine_master1(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "anml_type3", required = false) String anml_type,
				@RequestParam(value = "vaccine_name1", required = false) String vaccine_name,
				@RequestParam(value = "frequency1", required = false) String frequency) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Vaccine_Master", roleid);
			/*if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			System.err.println("val of vaccine_name" + vaccine_name + "Val of anml_type " + anml_type + "Val Of frequency " + frequency);
			if (validation.checkAnimalMasterLength(vaccine_name) == false) {
				Mmap.put("msg", validation.vaccine_MSG);
				return new ModelAndView("redirect:Animal_Vaccine_Master");
			}
			

			String roleType = session.getAttribute("roleType").toString();

			Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
			sessionPA.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = sessionPA.beginTransaction();
			Mmap.put("vaccine_name1", vaccine_name);
			Mmap.put("anml_type3", anml_type);
			Mmap.put("frequency1", frequency);

			Query q = null;
			String qry = "";
			
			if (!frequency.trim().equals("") && !frequency.equals("0")) {
				qry += " and frequency = :frequency ";
			} 
			if (!vaccine_name.equals("") && !anml_type.equals("undefined")) {
				qry += " and UPPER(vaccine_name) like :vaccine_name ";
				q = sessionPA.createQuery("from TMS_TB_MISO_VACCINE_MASTER where id > 0 and anml_type=:anml_type " + qry
						+ "order by vaccine_name");

			} else {
				q = sessionPA.createQuery(
						"from TMS_TB_MISO_VACCINE_MASTER where  id > 0 and anml_type=:anml_type " + "order by vaccine_name");
			}

			if (vaccine_name != "") {
				q.setString("vaccine_name", "%" + vaccine_name.toUpperCase() + "%");
			}
			if (frequency != "" && !frequency.trim().equals("")) {
				q.setLong("frequency",Integer.parseInt(frequency));
			}

			q.setParameter("anml_type", anml_type);

			@SuppressWarnings("unchecked")
			List<TMS_TB_MISO_VACCINE_MASTER> list = (List<TMS_TB_MISO_VACCINE_MASTER>) q.list();
			tx.commit();
			sessionPA.close();

			for (int i = 0; i < list.size(); i++) {

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Vaccine ?') ){editData("
						+ list.get(i).getId() + ",'" + list.get(i).getVaccine_name() + "','" + list.get(i).getFrequency() + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Vaccine?') ){deleteData("
						+ list.get(i).getId() + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				if (roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}

				else if (roleType.equals("DEO")) {
					f += deleteButton;
					f += updateButton;
				} else if (roleType.equals("App")) {

				}

				list.get(i).setModify_by(f);
			}
			Mmap.put("anml_type3", anml_type);
			Mmap.put("list", list);
			return new ModelAndView("Add_Animals_VaccinationTile");
		}
		
		
		@RequestMapping(value = "/deleteVaccine", method = RequestMethod.POST)
		public @ResponseBody List<String> deleteVaccine(int id, HttpServletRequest request, HttpSession sessionA,
				Authentication authentication) {

			List<String> liststr = new ArrayList<String>();
			String roleType = sessionA.getAttribute("roleType").toString();
			if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
				liststr.add("You do not have permission to access This Operation.");
				return liststr;
			}
			try {

				Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "delete from TMS_TB_MISO_VACCINE_MASTER where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				session.close();

				if (app > 0) {
					liststr.add("Deleted Successfully.");
				} else {
					liststr.add("Data not Delete.");
				}
				return liststr;

			} catch (Exception e) {

				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				return liststr;
			}

		}
		
		// EMPLOYMENT TYPE MASTER
		@RequestMapping(value = "/Animal_Employment_Master", method = RequestMethod.GET)
		public ModelAndView Animal_Employment_Master(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

			String roleid = session.getAttribute("roleid").toString();
			/*Boolean val = roledao.ScreenRedirect("Animal_Employment_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("Add_Animals_EmploymentTile", "Add_Employment_Cmd", new TMS_TB_MISO_EMPLOYMENT_MASTER());
		}
		
		
		@RequestMapping(value = "/Add_Employment_Act", method = RequestMethod.POST)
		public ModelAndView Add_Employment_Act(@ModelAttribute("Add_Employment_Cmd") TMS_TB_MISO_EMPLOYMENT_MASTER bm,
				HttpServletRequest request, ModelMap model, HttpSession session) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Employment_Master", roleid);
			/*if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/

			if (bm.getEmp_type().trim().equals("") || bm.getEmp_type().equals("null") || bm.getEmp_type().equals(null)) {
				model.put("msg", "Please Enter Employment.");
				return new ModelAndView("redirect:Animal_Employment_Master");
			} else if (validation.checkAnimalMasterLength(bm.getEmp_type()) == false) {
				model.put("msg", validation.Employment_MSG);
				return new ModelAndView("redirect:Animal_Employment_Master");
			}
			
			else {

				int id = bm.getId() > 0 ? bm.getId() : 0;

				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String emp_type = request.getParameter("emp_type");
				String animal_type = request.getParameter("anml_type");

				Session session0 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx0 = session0.beginTransaction();

				try {
					Query q0 = session0.createQuery(
							"select count(id) from TMS_TB_MISO_EMPLOYMENT_MASTER where upper(trim(emp_type))=upper(trim(:emp_type)) and anml_type=:animal_type and id !=:id");
					q0.setParameter("emp_type", emp_type.trim());
					q0.setParameter("animal_type", animal_type);
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();

					/* CREATION OF NEW ANIMAL BREED MASTER */
					if (id == 0) {

						bm.setCreated_by(username);
						bm.setCreated_date(date);

						if (c == 0) {
							session0.save(bm);
							tx0.commit();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					/* UPDATION OF ANIMAL BREED MASTER */
					else {

						bm.setModify_by(username);
						bm.setModify_date(date);

						if (c == 0) {
							String msg1 = animaldao.updateEmployment(bm);
							model.put("msg", msg1);
						} else {
							model.put("msg", "Data already Exist.");
						}
					}

				} catch (Exception e) {
					session0.getTransaction().rollback();
					return null;
				}

				finally {
					session0.close();
				}

				return new ModelAndView("redirect:Animal_Employment_Master");
			}

		}
		
		//search employment
		
		@RequestMapping(value = "/admin/search_employment_master1", method = RequestMethod.POST)
		public ModelAndView search_employment_master1(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "anml_type3", required = false) String anml_type,
				@RequestParam(value = "emp_type1", required = false) String emp_type) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Employment_Master", roleid);
			/*if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/
			System.err.println("val of emp_type" + emp_type + "Val of anml_type " + anml_type);
			if (validation.checkAnimalMasterLength(emp_type) == false) {
				Mmap.put("msg", validation.vaccine_MSG);
				return new ModelAndView("redirect:Animal_Vaccine_Master");
			}
			

			String roleType = session.getAttribute("roleType").toString();

			Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
			sessionPA.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = sessionPA.beginTransaction();
			Mmap.put("emp_type1", emp_type);
			Mmap.put("anml_type3", anml_type);

			Query q = null;
			String qry = "";
			
		
			if (!emp_type.equals("") && !anml_type.equals("undefined")) {
				qry += " UPPER(emp_type) like :emp_type ";
				q = sessionPA.createQuery("from TMS_TB_MISO_EMPLOYMENT_MASTER where id > 0 and anml_type=:anml_type AND " + qry
						+ "order by emp_type");

			} else {
				q = sessionPA.createQuery(
						"from TMS_TB_MISO_EMPLOYMENT_MASTER where  id > 0 and anml_type=:anml_type " + "order by emp_type");
			}

			if (emp_type != "") {
				q.setString("emp_type", "%" + emp_type.toUpperCase() + "%");
			}
			

			q.setParameter("anml_type", anml_type);

			@SuppressWarnings("unchecked")
			List<TMS_TB_MISO_EMPLOYMENT_MASTER> list = (List<TMS_TB_MISO_EMPLOYMENT_MASTER>) q.list();
			tx.commit();
			sessionPA.close();

			for (int i = 0; i < list.size(); i++) {

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Employment ?') ){editData("
						+ list.get(i).getId() + ",'" + list.get(i).getEmp_type() + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Employment?') ){deleteData("
						+ list.get(i).getId() + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				if (roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}

				else if (roleType.equals("DEO")) {
					f += deleteButton;
					f += updateButton;
				} else if (roleType.equals("App")) {

				}

				list.get(i).setModify_by(f);
			}
			Mmap.put("anml_type3", anml_type);
			Mmap.put("list", list);
			return new ModelAndView("Add_Animals_EmploymentTile");
		}
		
		@RequestMapping(value = "/deleteEmployment", method = RequestMethod.POST)
		public @ResponseBody List<String> deleteEmployment(int id, HttpServletRequest request, HttpSession sessionA,
				Authentication authentication) {

			List<String> liststr = new ArrayList<String>();
			String roleType = sessionA.getAttribute("roleType").toString();
			if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
				liststr.add("You do not have permission to access This Operation.");
				return liststr;
			}
			try {

				Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "delete from TMS_TB_MISO_EMPLOYMENT_MASTER where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				session.close();

				if (app > 0) {
					liststr.add("Deleted Successfully.");
				} else {
					liststr.add("Data not Delete.");
				}
				return liststr;

			} catch (Exception e) {

				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				return liststr;
			}

		}
		
		// CREATED BY MITESH(20-03-2025)
		////////////////////////////////// AWARD MASTER ////////////////////////////

		@RequestMapping(value = "/Animal_Award_Master", method = RequestMethod.GET)
		public ModelAndView Animal_Award_Master(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			/*String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Award_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}*/
			Mmap.put("msg", msg);
			return new ModelAndView("Add_Animals_AwardTile", "Add_Award_Cmd", new TB_ANIMALS_AWARD_MASTER());
		}

		@RequestMapping(value = "/Add_Award_Act", method = RequestMethod.POST)
		public ModelAndView Add_Award_Act(@ModelAttribute("Add_Award_Cmd") TB_ANIMALS_AWARD_MASTER bm,
				HttpServletRequest request, ModelMap model, HttpSession session) {

			/*String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Award_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/

			int id = bm.getId() > 0 ? bm.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String type_award = request.getParameter("award_type");

			System.err.println("type_award--" + type_award);
			String animal_type = request.getParameter("anml_type");

			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();

			try {
				Query q0 = session0.createQuery(
						"select count(id) from TB_ANIMALS_AWARD_MASTER where upper(trim(award_type))=upper(trim(:award_type)) and anml_type=:animal_type and id !=:id");
				q0.setParameter("award_type", type_award);
				q0.setParameter("animal_type", animal_type);
				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();

				/* CREATION OF NEW ANIMAL BREED MASTER */
				if (id == 0) {

					bm.setCreated_by(username);
					bm.setCreated_date(date);

					if (c == 0) {
						session0.save(bm);
						tx0.commit();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				/* UPDATION OF ANIMAL BREED MASTER */
				else {

					bm.setModify_by(username);
					bm.setModify_date(date);

					if (c == 0) {
						String msg1 = animaldao.updateAward(bm);
						model.put("msg", msg1);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}

			} catch (Exception e) {
				session0.getTransaction().rollback();
				return null;
			}

			finally {
				session0.close();
			}

			return new ModelAndView("redirect:Animal_Award_Master");
		}

		@RequestMapping(value = "/admin/search_award_master1", method = RequestMethod.POST)
		public ModelAndView search_award_master1(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "anml_type3", required = false) String anml_type,
				@RequestParam(value = "award_type1", required = false) String type_award) {

			/*String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Animal_Award_Master", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/

			if (validation.checkAnimalMasterLength(type_award) == false) {
				Mmap.put("msg", validation.type_awardMSG);
				return new ModelAndView("redirect:Animal_Award_Master");
			}

			String roleType = session.getAttribute("roleType").toString();

			Session sessionPA = HibernateUtilNA.getSessionFactory().openSession();
			sessionPA.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = sessionPA.beginTransaction();
			Mmap.put("award_type1", type_award);
			Mmap.put("anml_type3", anml_type);

			Query q = null;
			String qry = "";
			if (!type_award.equals("") && !anml_type.equals("undefined")) {
				qry += " UPPER(award_type) like :award_type ";
				q = sessionPA.createQuery("from TB_ANIMALS_AWARD_MASTER where id > 0 and anml_type=:anml_type and " + qry
						+ "order by award_type");

			} else {
				q = sessionPA.createQuery(
						"from TB_ANIMALS_AWARD_MASTER where  id > 0 and anml_type=:anml_type " + "order by award_type");
			}

			if (type_award != "") {
				q.setString("award_type", "%" + type_award.toUpperCase() + "%");
			}

			q.setParameter("anml_type", anml_type);

			@SuppressWarnings("unchecked")
			List<TB_ANIMALS_AWARD_MASTER> list = (List<TB_ANIMALS_AWARD_MASTER>) q.list();
			tx.commit();
			sessionPA.close();

			for (int i = 0; i < list.size(); i++) {

				String f = "";

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Type of Award ?') ){editData("
						+ list.get(i).getId() + ",'" + list.get(i).getAward_type() + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Type of Award ?') ){deleteData("
						+ list.get(i).getId() + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				if (roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}

				else if (roleType.equals("DEO")) {
					f += deleteButton;
					f += updateButton;
				} else if (roleType.equals("App")) {

				}

				list.get(i).setModify_by(f);
			}
			Mmap.put("anml_type3", anml_type);
			Mmap.put("list", list);
			return new ModelAndView("Add_Animals_AwardTile");
		}

}

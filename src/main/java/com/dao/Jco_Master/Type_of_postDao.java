package com.dao.Jco_Master;

import java.util.List;
import java.util.Map;

import com.model.Jco_Master.TB_Type_Of_Post;
import com.models.psg.Master.TB_LANGUAGE;

public interface Type_of_postDao {

	public List<Map<String, Object>>search_type_of_postRepo(String type_of_post1,String status);
	public TB_Type_Of_Post gettype_of_postByid(int id) ;
	public Long checkExitstingtype_of_post(String type_of_post1,int id1) ;
	public String Update_type_of_post(TB_Type_Of_Post obj,String username);
}

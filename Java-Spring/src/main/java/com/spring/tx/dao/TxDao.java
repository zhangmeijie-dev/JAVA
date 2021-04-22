package com.spring.tx.dao;

import com.spring.tx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TxDao  extends JdbcDaoSupport {

  @Autowired
  private DataSource dataSource;

  @PostConstruct
  public void init(){
    setDataSource(dataSource);
  }

  public List selectUserByID(Integer id) {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT * ")
        .append(" FROM user ")
        .append(" WHERE id =:id ");
    Map<String, Object> param = new HashMap();
    param.put("id",id);
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());
    return jdbcTemplate.queryForList(sql.toString(),param);
  }
  public int insertUserByID(User user) {
    StringBuffer sql = new StringBuffer();
    sql.append(" INSERT INTO user ")
        .append(" ( username , birthday , sex , address ) ")
        .append(" VALUES ")
        .append(" (:username,:birthday,:sex,:address) ");
    Map<String, Object> param = new HashMap();
    param.put("username",user.getUsername());
    param.put("birthday",user.getBirthday());
    param.put("sex",user.getSex());
    param.put("address",user.getAddress());
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());
    return jdbcTemplate.update(sql.toString(),param);
  }
  public int deleteUserByID(Integer id) {
    StringBuffer sql = new StringBuffer();
    sql.append(" DELETE FROM user WHERE id=:id ");
    Map<String, Object> param = new HashMap();
    param.put("id",id);
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());
    return jdbcTemplate.update(sql.toString(),param);
  }

}

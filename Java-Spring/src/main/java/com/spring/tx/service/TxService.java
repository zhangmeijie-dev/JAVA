package com.spring.tx.service;

import com.spring.tx.dao.TxDao;
import com.spring.tx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TxService {
  @Autowired
  private TxDao txDao;

  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
  public void testTxAop(Integer id) {
    List list = selectUserByID(id);
    Map<String, Object> user = (Map<String, Object>) list.get(0);
    insertUserByID(user);
    deleteUserByID((Integer) user.get("id"));
  }

  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
  public List selectUserByID(Integer id) {
    return txDao.selectUserByID(id);
  }

  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
  public int insertUserByID(Map<String, Object> userMap) {
    User user = new User();
    user.setUsername(userMap.get("username").toString());
    user.setBirthday((Date) userMap.get("birthday"));
    user.setSex(userMap.get("sex").toString());
    user.setAddress(userMap.get("address").toString());
    return txDao.insertUserByID(user);
  }

  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
  public int deleteUserByID(Integer id) {
    return txDao.deleteUserByID(id);
  }

  public TxDao getTxDao() {
    return txDao;
  }

  public void setTxDao(TxDao txDao) {
    this.txDao = txDao;
  }
}

package com.jc.dao.impl;

import com.jc.dao.FirstDao;
import com.jc.domain.Team;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-24
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FirstDaoImpl extends SqlMapClientTemplate implements FirstDao {
    public Team getTeamByid(Map map){
//           Team t =  (Team)queryForObject("First.getTeamByid",map);
       return (Team) queryForObject("First.getTeamById",map);

//        return null;
    }
}

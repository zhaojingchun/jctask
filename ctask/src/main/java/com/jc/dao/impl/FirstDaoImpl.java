package com.jc.dao.impl;

import com.jc.dao.FirstDao;
import com.jc.domain.Team;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-24
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
public class FirstDaoImpl extends SqlMapClientTemplate implements FirstDao {
    public Team getTeamByid(Map map){
//           Team t =  (Team)queryForObject("First.getTeamByid",map);
       return (Team) queryForObject("First.getTeamById",123l);

//        return null;
    }
}

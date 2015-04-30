package com.jc.dao.impl;

import com.jc.dao.BaseDao;
import com.jc.dao.FirstDao;
import com.jc.domain.Team;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-24
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
@Component("firstDao")
public class FirstDaoImpl extends BaseDao implements FirstDao {
    public Team getTeamByid(Map map){
       return (Team) queryForObject("First.getTeamById",map);
    }
}

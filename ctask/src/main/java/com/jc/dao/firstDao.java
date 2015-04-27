package com.jc.dao;

import com.jc.domain.Team;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-23
 * Time: 下午6:52
 * To change this template use File | Settings | File Templates.
 */
public interface FirstDao {
    public Team getTeamByid(Map map);
}

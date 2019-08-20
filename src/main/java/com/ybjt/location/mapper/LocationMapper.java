package com.ybjt.location.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ybjt.location.bean.Location;

/**
 * $mapper接口定义功能
 * @author Administrator
 *
 */
public interface LocationMapper {
	/**
	 * $查询全部
	 */
	public List<Location> selectAll();
	
	/**
	 * $根据id查询
	 */
	public Location selectById(String id);
	
	/**
	 * $修改城市信息
	 */
	public void saveInfo(Location location);
	
	/**
	 * $添加根节点
	 */
	public void addRoot(Location location);
	
	/**
	 * 删除节点
	 */
	public void deleteNotes(String id);
	
	/**
	 * 查询一级location
	 */
	public List<Location> selectRootLocation();
	
	/**
	 * $根据pid查询
	 */
	public List<Location> twoLocation(String pid);

	public List<Location> selectAlls(String page, String limit);
}
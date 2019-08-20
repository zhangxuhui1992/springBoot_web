package com.ybjt.location.service;

import java.util.List;

import com.ybjt.location.bean.Location;
import com.ybjt.tree.dto.BasicTree;

/**
 * $service层
 * @author Administrator
 *
 */
public interface LocationService {
	
	/**
	 * $查询全部
	 */
	public List<Location> selectAll();
	
	/**
	 * $根据id查询
	 */
	public Location selectById(String id);
	
	/**
	 * $查询结果树，返回list
	 */
	public List<BasicTree> loadTreeData();
	
	/**
	 * $修改城市信息
	 */
	public void saveInfo(Location location);
	
	/**
	 * $添加根节点
	 */
	public void addRoot(Location location);
	
	/**
	 * $添加子节点
	 */
	public void addchild(Location location);
	
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

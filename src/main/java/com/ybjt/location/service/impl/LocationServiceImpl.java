package com.ybjt.location.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ybjt.location.bean.Location;
import com.ybjt.location.mapper.LocationMapper;
import com.ybjt.location.service.LocationService;
import com.ybjt.tree.dto.BasicTree;

/**
 * $service层的实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationMapper locationMapper;
	
	/**
	 * $查询全部
	 */
	@Override
	public List<Location> selectAll() {
		return locationMapper.selectAll();
	}
	
	/**
	 * $根据id查询
	 */
	@Override
	public Location selectById(String id) {
		return locationMapper.selectById(id);
	}
	
	/**
	 * $查询树菜单
	 */
	@Override
	public List<BasicTree> loadTreeData() {
		List<Location> list = locationMapper.selectAll();
		List<BasicTree> treeList = new ArrayList<BasicTree>();
		if(list != null && list.size()>0) {
			for(Location location:list) {
				BasicTree tree = new BasicTree();
				tree.setId(location.getLOCATION_ID());
				tree.setPId(location.getLOCATION_PID());
				tree.setName(location.getLOCATION_NAME());
				tree.setOpen(true);
				treeList.add(tree);
			}
		}
		
		return treeList;
	}
	
	/**
	 * $修改城市信息
	 */
	@Override
	public void saveInfo(Location location) {
		locationMapper.saveInfo(location);
	}

	@Override
	public void addRoot(Location location) {
		location.setLOCATION_ID(UUID.randomUUID().toString());
		location.setLOCATION_CREAETIME(new Date());
		location.setLOCATION_EDITTIME(new Date());
		location.setLOCATION_PID("0");
		locationMapper.addRoot(location);
	}

	@Override
	public void addchild(Location location) {
		location.setLOCATION_ID(UUID.randomUUID().toString());
		location.setLOCATION_CREAETIME(new Date());
		location.setLOCATION_EDITTIME(new Date());
		locationMapper.addRoot(location);
	}

	@Override
	public void deleteNotes(String id) {
		locationMapper.deleteNotes(id);
	}

	@Override
	public List<Location> selectRootLocation() {
		return locationMapper.selectRootLocation();
	}

	@Override
	public List<Location> twoLocation(String pid) {
		return locationMapper.twoLocation(pid);
	}
	
	
	
	
	
}

package cn.zhang.mapper;

import java.util.List;

import cn.zhang.model.City;

public interface CityMapper {

	
	public City getById(int id);
	
	
	
	public List<City> selectAll();
	
}

package com.zy.logic;

import com.zy.service.EarthService;
import com.zy.service.FertilizeService;
import com.zy.service.FoodService;
import com.zy.service.PlantService;
import com.zy.service.SellService;
import com.zy.service.SprayService;
import com.zy.service.UserService;
import com.zy.service.WaterService;

public class FactoryService {
	private UserService userService ;
	private EarthService earthService;
	private PlantService plantService;
	private WaterService waterService;
	private FertilizeService fertilizeService;
	private SprayService sprayService ;
	private FoodService foodService;
	private SellService sellService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EarthService getEarthService() {
		return earthService;
	}

	public void setEarthService(EarthService earthService) {
		this.earthService = earthService;
	}

	public PlantService getPlantService() {
		return plantService;
	}

	public void setPlantService(PlantService plantService) {
		this.plantService = plantService;
	}

	public WaterService getWaterService() {
		return waterService;
	}

	public void setWaterService(WaterService waterService) {
		this.waterService = waterService;
	}

	public FertilizeService getFertilizeService() {
		return fertilizeService;
	}

	public void setFertilizeService(FertilizeService fertilizeService) {
		this.fertilizeService = fertilizeService;
	}

	public SprayService getSprayService() {
		return sprayService;
	}

	public void setSprayService(SprayService sprayService) {
		this.sprayService = sprayService;
	}

	public FoodService getFoodService() {
		return foodService;
	}

	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	public SellService getSellService() {
		return sellService;
	}

	public void setSellService(SellService sellService) {
		this.sellService = sellService;
	}
}

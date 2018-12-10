package com.postal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.postal.bean.CarPosition;
import com.postal.bean.Etcar;
import com.postal.repository.CarPositionRepository;
import com.postal.repository.CarRepository;
import com.postal.util.MapUtil;

@EnableDiscoveryClient
@RestController
@RequestMapping("position")
@EnableAsync // 开启异步调用
@EntityScan(basePackages = "com.postal.bean")
@EnableJpaRepositories(basePackages = "com.postal.repository")
@SpringBootApplication
public class PostalPositionApplication {
	private static Logger log = LogManager.getLogger(PostalPositionApplication.class);

	@Autowired
	private CarPositionRepository carPositionRepository;
	@Autowired
	private CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(PostalPositionApplication.class, args);
	}

	/**
	 * GPS位置信息保存
	 * 
	 * @author LXY
	 * @param request
	 * @throws JSONException
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestBody String data, HttpServletRequest request) {

		Map<String, Object> msg = new HashMap<>(2);
		msg.put("return_code", "0");
		try {
			JSONObject jobs = new JSONObject(data).getJSONObject("upload");
			String carNo = MapUtil.getJsonString("bikeId", jobs);
			Etcar car = carRepository.findByCarNo(carNo);
			// 车辆不存在不做保存
			if (null == car) {
				msg.put("return_code", "1001");
				msg.put("return_msg", "该车牌没有备案！");
				return MapUtil.returnMap("upload", msg);
			} else {
				Future<Integer> future = saveData(jobs, car);
				if (future.get() == 0) {
					msg.put("return_msg", "上传成功");
					return MapUtil.returnMap("upload", msg);
				}
				// log.info(future.get());
			}
		} catch (Exception e) {

		}
		msg.put("return_code", "1001");
		msg.put("return_msg", "数据格式异常或参数不全");
		return MapUtil.returnMap("upload", msg);

	}

	/**
	 * GPS位置信息批量上报
	 * 
	 * @author LXY
	 * @param request
	 * @throws JSONException
	 */
	@RequestMapping(value = "batchUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object batchUpload(@RequestBody String data, HttpServletRequest request) {

		Map<String, Object> msg = new HashMap<>(2);
		msg.put("return_code", "0");
		try {
			JSONObject datas = new JSONObject(data).getJSONObject("upload");
			String carNo = MapUtil.getJsonString("bikeId", datas);
			JSONArray gpsList = datas.getJSONArray("dataList");
			Etcar car = carRepository.findByCarNo(carNo);
			// 车辆不存在不做保存
			if (null == car) {
				msg.put("return_code", "1001");
				msg.put("return_msg", "该车牌没有备案！");
				return MapUtil.returnMap("upload", msg);
			}
			for (int i = 0; i <= gpsList.length() - 1; i++) {
				JSONObject jobs = gpsList.getJSONObject(i);
				String uptime = MapUtil.getJsonString("uptime", datas);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date upDate = null;
				try {
					upDate = sdf.parse(uptime);
				} catch (ParseException e) {
					upDate = new Date();
				}
				Double lat = Double.parseDouble(
						MapUtil.getJsonString("lat", jobs).equals("") ? "0" : MapUtil.getJsonString("lat", jobs));
				Double lng = Double.parseDouble(
						MapUtil.getJsonString("lng", jobs).equals("") ? "0" : MapUtil.getJsonString("lng", jobs));
				String speed = MapUtil.getJsonString("speed", jobs);
				if (i == gpsList.length() - 1) {
					Etcar carObj = car;
					log.info("位置信息上传" + data);
					carObj.setLat(lat);
					carObj.setLng(lng);
					carObj.setGpstime(upDate);
					carObj.setSpeed(Integer.parseInt(speed == null ? "0" : speed));
					// 保存最后位置信息
					carRepository.save(carObj);
				}
				CarPosition carPosition = new CarPosition();
				carPosition.setIntime(upDate);
				carPosition.setPlateNo(car.getPlateNo());
				carPosition.setLat(lat);
				carPosition.setLng(lng);
				carPosition.setDirection(MapUtil.getJsonString("derection", jobs));
				carPosition.setHight(MapUtil.getJsonString("hight", jobs));
				carPosition.setSpeed(MapUtil.getJsonString("speed", jobs));
				carPosition.setStatus(MapUtil.getJsonString("status", jobs));
				carPosition.setExtend(MapUtil.getJsonString("extends", jobs));
				carPositionRepository.save(carPosition);

			}
		} catch (Exception e) {
			msg.put("return_code", "1001");
			msg.put("return_msg", "数据格式异常或参数不全");
			return MapUtil.returnMap("upload", msg);
		}
		msg.put("return_msg", "上传成功");
		return MapUtil.returnMap("upload", msg);

	}

	@Async
	public Future<Integer> saveData(JSONObject jobs, Etcar carObj) {
		Future<Integer> future = null;
		Double lat = Double
				.parseDouble(MapUtil.getJsonString("lat", jobs).equals("") ? "0" : MapUtil.getJsonString("lat", jobs));
		Double lng = Double
				.parseDouble(MapUtil.getJsonString("lng", jobs).equals("") ? "0" : MapUtil.getJsonString("lng", jobs));
		String speed = MapUtil.getJsonString("speed", jobs);
		Date upDate = new Date();
		String uptime = MapUtil.getJsonString("uptime", jobs);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			upDate = sdf.parse(uptime);
			log.info("位置信息上传" + jobs);
			carObj.setLat(lat);
			carObj.setLng(lng);
			carObj.setGpstime(upDate);
			carObj.setSpeed(Integer.parseInt(speed == null ? "0" : speed));
			// 保存最后位置信息
			carRepository.save(carObj);

			CarPosition carPosition = new CarPosition();
			carPosition.setIntime(upDate);
			carPosition.setPlateNo(carObj.getPlateNo());
			carPosition.setLat(lat);
			carPosition.setLng(lng);
			carPosition.setDirection(MapUtil.getJsonString("derection", jobs));
			carPosition.setHight(MapUtil.getJsonString("hight", jobs));
			carPosition.setSpeed(MapUtil.getJsonString("speed", jobs));
			carPosition.setStatus(MapUtil.getJsonString("status", jobs));
			carPosition.setExtend(MapUtil.getJsonString("extends", jobs));
			carPositionRepository.save(carPosition);

			future = new AsyncResult<Integer>(0);
		} catch (Exception e) {

			future = new AsyncResult<Integer>(1);
		}
		return future;
	}

	@RequestMapping("/hello")
	public String hello() {
		// 打印服务的服务id
		return "position_3,this is position_3-service";
	}
	/*
	 * public static void main(String[] args) { Date upDate = new Date();
	 * //车辆不存在不做保存
	 * 
	 * String uptime = "2018-10-22 12:23:10"; SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * 
	 * try { upDate = sdf.parse(uptime); } catch (ParseException e) { //upDate = new
	 * Date(); } System.out.println(upDate); }
	 */
}

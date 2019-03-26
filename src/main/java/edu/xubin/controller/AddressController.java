package edu.xubin.controller;

import com.alibaba.fastjson.JSON;
import edu.xubin.bean.AddressEntity;
import edu.xubin.bean.MeetingAddressEntity;
import edu.xubin.service.AddressService;
import edu.xubin.service.MeetingAddressService;
import edu.xubin.util.AvailableAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private MeetingAddressService meetingAddressService;

    @ResponseBody
    @RequestMapping(value = "/findMeeting", method = RequestMethod.POST)
    public List<AvailableAddress> findAvailableAddress(AvailableAddress addressTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
        Date stringToTime = null;
//        Map<String,Object> map=JSON.parseObject(date,Map.class);
//        date = date.replaceAll("=",":");
//        date = date.replaceAll("&",",");
        Date meetingDate = null;
        meetingDate = addressTime.getMeetingDate();
        try {
            //转换时间格式
            stringToTime = sdftime.parse(addressTime.getBeginTime());
            Time beginTime = new Time(stringToTime.getTime());
            stringToTime = sdftime.parse(addressTime.getEndTime());
            Time endTime = new Time(stringToTime.getTime());
            //查询所有地址列
            List<AddressEntity> allAddress = addressService.findAll();
            //所有不可用地址列
            List<MeetingAddressEntity> unavailableAddressList = findUnavailableAddress(meetingDate, beginTime, endTime);
            //当天使用情况的地址列
            List<AvailableAddress> availableAddresses = new ArrayList<>();
            //假设所有地址都可以用，默认所有地址状态为1
            for (AddressEntity addressEntity:allAddress) {
                availableAddresses.add(new AvailableAddress(addressEntity.getAddressid(), addressEntity.getAddressname(), 1));
            }
            /*
            如不可用地址列为空则所有地址都可以用
            如不可用地址列不为空则找到对应的地址ID
            将不可用的地址状态改为0
            状态为1的是可用地址
            状态为0的是不可用地址
            根据所有地址的顺序列添加到可用地址列中并判断是否与会议时间冲突
            */
            if(unavailableAddressList.size() != 0) {
                for (AvailableAddress availableAddress:availableAddresses) {
                    for (MeetingAddressEntity meetingAddressEntity:unavailableAddressList) {
                        if(meetingAddressEntity.getAddressEntity().getAddressid().equals(availableAddress.getAddressid())){
                            availableAddress.setStatus(0);
                        }
                    }
                }
            }
            return availableAddresses;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
    /*
    地址的会议结束时间大于开始时间 或 地址的会议开始时间小于结束时间
    则该地址可用
    */
    public List<MeetingAddressEntity> findUnavailableAddress(Date meetingDate, Time beginTime
            , Time endTime){
        List<MeetingAddressEntity> meetingAddressEntityList = meetingAddressService.findAllByDate(meetingDate);
        for(int i=0; i<meetingAddressEntityList.size(); i++){
            if(meetingAddressEntityList.get(i).getBeginTime().after(endTime) || meetingAddressEntityList.get(i).getEndTime().before(beginTime)){
                meetingAddressEntityList.remove(i);
            };
        }
        return meetingAddressEntityList;
    }



}

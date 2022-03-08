package com.example.whb_demo.dao.daoimpl;

import com.alibaba.fastjson.JSONObject;
import com.example.whb_demo.dao.ExcelServiceDao;
import com.example.whb_demo.dto.WmsUserClientDto;
import com.example.whb_demo.dto.WmsUserStockroomDto;
import com.example.whb_demo.entity.*;
import com.example.whb_demo.enumo.WmsstockEnum;
import com.example.whb_demo.mapper.ExcelMapper;
import com.example.whb_demo.utils.IdGenerator;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import com.example.whb_demo.vo.WmsUserExcelVo;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.whb_demo.utils.StringUtils.isPhoneNumber;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/21
 * \* Time: 13:54
 * \* Description:
 * \
 */
@Slf4j
@Component
public class ExcelServiceDaoImpl implements ExcelServiceDao {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ExcelMapper excelMapper;

    @Override
    public List<WmsUserExcelVo> repetitionRepetition(List<WmsUserExcelVo> excelListl) {

        //对list数据 根据某个字段去掉重复数据 这里用的根据vin去除重复值，两个结果相同值去最后一条
        List<WmsUserExcelVo> newList = excelListl.stream().collect(Collectors.collectingAndThen
                (Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing
                        (WmsUserExcelVo::getUsername))), ArrayList::new));

        return newList;

    }

    @Override
    public List<String> errorNull(WmsUserExcelVo excelVo, int index) {

        List<String> error = new ArrayList<>();

        if (StringUtils.isEmpty(excelVo.getUsername())) {
            error.add(index + "行:登录账户为空");
        }

        if (StringUtils.isEmpty(excelVo.getMobile())) {
            error.add(index + "行:手机号为空");
        }

        if (!StringUtils.isEmpty(excelVo.getMobile()) && !isPhoneNumber(excelVo.getMobile())) {
            error.add(index + "行:手机号格式有误");
        }

        if (StringUtils.isEmpty(excelVo.getName())) {
            error.add(index + "行:姓名为空");

        }

        if (StringUtils.isEmpty(excelVo.getRole())) {
            error.add(index + "行:角色为空");
        }

        if (StringUtils.isEmpty(excelVo.getStockroomCode())){
            error.add(index + "行:仓库编码为空");
        }

        if (StringUtils.isEmpty(excelVo.getClienteleCode())){
            error.add(index + "行:客户编码为空");
        }

        return error;
    }


    @Override
    public WmsUser isNotData(WmsUserExcelVo excelVo) {

        String password = "cjkj123456";

        WmsUser wmsUser = new WmsUser();

        if (!StringUtils.isEmpty(excelVo.getUsername()) && !StringUtils.isEmpty(excelVo.getMobile()) && !StringUtils.isEmpty(excelVo.getName())
                && !StringUtils.isEmpty(excelVo.getRole())) {

            wmsUser.setUserId(IdGenerator.getIdStr());
            wmsUser.setUsername(excelVo.getUsername());
            wmsUser.setPassword(passwordEncoder.encode(password));
            log.info("getPassword-----:{}", JSONObject.toJSONString(wmsUser.getPassword()));

            wmsUser.setMobile(excelVo.getMobile());
            wmsUser.setName(excelVo.getName());
            wmsUser.setRole(excelVo.getRole());
            wmsUser.setStockroomCode(excelVo.getStockroomCode());
            wmsUser.setClienteleCode(excelVo.getClienteleCode());
            wmsUser.setCreateUser("系统");
            wmsUser.setTenantId("1452572477019402241");
            wmsUser.setErpCustomerId("1452572477019402241");

            log.info("wmsUser-----:{}", JSONObject.toJSONString(wmsUser));

        }

        return wmsUser;
    }

    /**
     * 向wms用户-角色关联表插入数据，wms角色权限表插入数据，wms用户-仓库关联表
     * wms用户-客户关联表插入数据
     * @param userList
     * @return
     */
    @Override
    public String insertWmsrole(List<WmsUser> userList) {

        // TODO 向wms用户-角色关联表插入数据，wms角色权限表插入数据
        int userRolecount = this.insertuserRolecount(userList);

        // TODO 向wms用户-仓库关联表wms_user_stockroom插入数据
        int userStockroomCount = this.insertuserStockroomCount(userList);

        // TODO 向wms用户-客户关联表wms_user_client插入数据
        int userClientCount = this.insertuserClientCount(userList);

        if (userRolecount > 0 && userStockroomCount > 0 && userClientCount > 0){
            return "0";
        }

        return "";
    }


    private int insertuserClientCount(List<WmsUser> userList) {

        List<WmsUserClientDto> dtoList = new ArrayList<>();

        for (WmsUser user : userList) {

            String code = user.getClienteleCode();

            String str[] = code.split("、");

            List<String> list = Arrays.asList(str);

            List<String> quchong = list.stream().distinct().collect(Collectors.toList());

            for (String s : quchong) {
                // TODO 根据客户code查询客户表wms_client_information，查询数据是否存在
                WmsUserClientDto clientDto = excelMapper.queryClient(s);

                if (clientDto != null && StringUtils.isNotBlank(clientDto.getClienteleId())
                        && StringUtils.isNotBlank(clientDto.getClienteleName())){

                    WmsUserClientDto dto = new WmsUserClientDto();

                    dto.setClienteleId(clientDto.getClienteleId());
                    dto.setClienteleName(clientDto.getClienteleName());
                    dto.setTenantTd(user.getTenantId());
                    dto.setUserId(user.getUserId());
                    dto.setCreateUser(user.getCreateUser());
                    dto.setUserClientId(IdGenerator.getIdStr());

                    dtoList.add(dto);
                }
            }

        }

        if (dtoList != null && !dtoList.isEmpty()){

            return excelMapper.insertWmsUserClient(dtoList);
        }

        return 0;
    }

    private int insertuserStockroomCount(List<WmsUser> userList) {

        List<WmsUserStockroomDto> dtoList = new ArrayList<>();
        List<String> list = new ArrayList<>();

        for (WmsUser user : userList) {

            String code = user.getStockroomCode();

            String str[] = code.split("、");

            list = Arrays.asList(str);

            List<String> quchong = list.stream().distinct().collect(Collectors.toList());

            for (String s : quchong) {

                WmsStockroom stockroomData = excelMapper.queryStockroom(s);
                // 根据仓库编码查询仓库表是否存在
                if (stockroomData != null && StringUtils.isNotBlank(stockroomData.getStockroomId())
                        && StringUtils.isNotBlank(stockroomData.getCompanyId())){

                    WmsUserStockroomDto stockroomDto = new WmsUserStockroomDto();

                    stockroomDto.setId(IdGenerator.getIdStr());
                    stockroomDto.setTenantId(user.getTenantId());
                    stockroomDto.setUserId(user.getUserId());
                    stockroomDto.setStockroomId(stockroomData.getStockroomId());
                    stockroomDto.setCompanyId(stockroomData.getCompanyId());

                    dtoList.add(stockroomDto);
                }

            }

        }

        if (dtoList != null && !dtoList.isEmpty()){
            return excelMapper.insertWmsUserStockroom(dtoList);
        }

        return 0;
    }

    private int insertuserRolecount(List<WmsUser> userList) {

        List<String> list = new ArrayList<>();

        for (WmsUser user : userList) {

            WmsRole role = new WmsRole();
            WmsUserRole userRole = new WmsUserRole();

            String roles = user.getRole();

            String str[] = roles.split("、");

            list = Arrays.asList(str);

            List<String> quchong = list.stream().distinct().collect(Collectors.toList());

            for (String s : quchong) {

                // TODO 根据角色名称去查询角色表wms_role 是否存在，存在的话不用新建主键id,不存在就新建主键id
                String flag = excelMapper.getWmsRoleId(s);
                //插入wms_role表数据
                if (StringUtils.isEmpty(flag)){
                    role.setWmsRoleId(IdGenerator.getIdStr());
                    role.setWmsRoleName(s);
                    role.setErpCustomerId("1452572477019402241");
                    int rolecount = excelMapper.insertWmsrole(role);
                }

                // TODO 根据userId查询 wms_user_role表，查看是否存在改用户，存在逻辑删除，再插入
                //插入wms_user_role表数据
                userRole.setUserRoleId(IdGenerator.getIdStr());
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(StringUtils.isEmpty(flag) ? role.getWmsRoleId() : flag);
                userRole.setRoleName(role.getWmsRoleName());

                return excelMapper.insertWmsuserrole(userRole);

                /*if (userrolecount > 0) {

                    return 1;
                }*/
            }

        }

        return 0;
    }

    @Override
    public List<WmsStockroomMemory> repetitionRepetitions(List<WmsStockroomMemory> excelListl) {

        //对list数据 根据某个字段去掉重复数据 这里用的根据vin去除重复值，两个结果相同值去最后一条
        List<WmsStockroomMemory> newList = excelListl.stream().collect(Collectors.collectingAndThen
                (Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing
                        (WmsStockroomMemory::getVin))), ArrayList::new));
        return newList;
    }


    @Override
    public List<WmsStockroomMemory> correctData(List<WmsMemoryExcelVo> excelVos) {

        List<WmsStockroomMemory> insertData = new ArrayList<>();

        List<String> errorList = new ArrayList<>();

        int index = 1;
        for (WmsMemoryExcelVo excelVo : excelVos) {

            if (StringUtils.isNotBlank(excelVo.getVin()) && StringUtils.isNotBlank(excelVo.getCompanyName()) &&
                    StringUtils.isNotBlank(excelVo.getStockroomName()) && StringUtils.isNotBlank(excelVo.getStockroomPartitionName()) &&
                    StringUtils.isNotBlank(excelVo.getStockroomPositionCode()) && StringUtils.isNotBlank(excelVo.getBrandName()) &&
                    StringUtils.isNotBlank(excelVo.getVehicleModel()) && StringUtils.isNotBlank(excelVo.getVehicleSeries()) &&
                    StringUtils.isNotBlank(excelVo.getVehicleColor()) && StringUtils.isNotBlank(excelVo.getClienteleName()) &&
                    StringUtils.isNotBlank(excelVo.getClienteleType()) && excelVo.getStockroomInDate() != null &&
                    StringUtils.isNotBlank(excelVo.getStockroomMemoryDays()) && StringUtils.isNotBlank(excelVo.getQualityStatus())) {

                index = index + 1;

                WmsStockroomMemory memory = new WmsStockroomMemory();
                memory.setVin(excelVo.getVin());

                //根据公司名称查询对应的公司id
                String companyId = excelMapper.getCompanyId(excelVo.getCompanyName());
                if (StringUtils.isNotBlank(companyId)) {
                    memory.setCompanyId(companyId);
                } else {
                    errorList.add(index + "行：" + excelVo.getCompanyName() + "不存在");
                }

                memory.setCompanyName(excelVo.getCompanyName());

                //根据仓库名称查询对应的仓库编码仓库id
                WmsStockroomMemory stockroomidData = excelMapper.getStockroomidandcode(excelVo);

                memory.setStockroomName(excelVo.getStockroomName());

                if (stockroomidData != null) {

                    if (StringUtils.isNotBlank(stockroomidData.getStockroomId())) {
                        memory.setStockroomId(stockroomidData.getStockroomId());
                    } else {
                        errorList.add(index + "行：" + excelVo.getStockroomName() + "不存在");
                    }

                    if (StringUtils.isNotBlank(stockroomidData.getStockroomCode())) {
                        memory.setStockroomCode(stockroomidData.getStockroomCode());
                    } else {
                        errorList.add(index + "行：" + excelVo.getStockroomName() + " 编码不存在");
                    }

                } else {
                    errorList.add(index + "行：" + excelVo.getStockroomName() + " 不存在");
                }

                WmsStockroomMemory partitionidData = excelMapper.getPartitionidData(excelVo);
                memory.setStockroomPartitionName(excelVo.getStockroomPartitionName());
                memory.setStockroomPositionCode(excelVo.getStockroomPositionCode());

                if (partitionidData != null) {

                    if (StringUtils.isNotBlank(partitionidData.getStockroomPartitionId())) {
                        memory.setStockroomPartitionId(partitionidData.getStockroomPartitionId());
                    } else {
                        errorList.add(index + "行：" + excelVo.getStockroomPartitionName() + " 不存在");
                    }

                    if (StringUtils.isNotBlank(partitionidData.getStockroomPositionId())) {
                        memory.setStockroomPositionId(partitionidData.getStockroomPositionId());
                    } else {
                        errorList.add(index + "行：" + excelVo.getStockroomPositionCode() + " 库位不存在");
                    }

                } else {
                    errorList.add(index + "行：" + excelVo.getStockroomPartitionName() + " 不存在");
                }

                //根据客户名称，品牌名称查询对应的id
                WmsStockroomMemory clienteleBrandid = excelMapper.getClienteleBrandid(excelVo);

                memory.setClienteleName(excelVo.getClienteleName());
                memory.setBrandName(excelVo.getBrandName());

                if (clienteleBrandid != null) {

                    if (StringUtils.isNotBlank(clienteleBrandid.getClienteleId())) {
                        memory.setClienteleId(clienteleBrandid.getClienteleId());
                    } else {
                        errorList.add(index + "行：" + excelVo.getClienteleName() + " 客户不存在");
                    }

                    if (StringUtils.isNotBlank(clienteleBrandid.getBrandId())) {
                        memory.setBrandId(clienteleBrandid.getBrandId());
                    } else {
                        errorList.add(index + "行：" + excelVo.getBrandName() + " 品牌不存在");
                    }

                    if (StringUtils.isNotBlank(clienteleBrandid.getBrandCode())) {
                        memory.setBrandCode(clienteleBrandid.getBrandCode());
                    } else {
                        errorList.add(index + "行：" + excelVo.getBrandName() + " 品牌编码不存在");
                    }

                } else {
                    errorList.add(index + "行：" + excelVo.getClienteleName() + " 客户不存在、"
                            + excelVo.getBrandName() + "品牌不存在");
                }

                memory.setStockroomMemoryId(IdGenerator.getIdStr());
                memory.setVehicleModel(excelVo.getVehicleModel());
                memory.setVehicleSeries(excelVo.getVehicleSeries());
                memory.setVehicleColor(excelVo.getVehicleColor());
                memory.setStockroomInDate(excelVo.getStockroomInDate());
                memory.setStockroomMemoryDays(excelVo.getStockroomMemoryDays());
                memory.setTenantId("1452572477019402241");
                memory.setCreateUser("系统");
                memory.setQualityStatus(WmsstockEnum.QualityStatus.getCode(excelVo.getQualityStatus()));

                if (WmsstockEnum.ClientProfile.getCode(excelVo.getClienteleType()) > 0) {
                    memory.setClienteleType(WmsstockEnum.ClientProfile.getCode(excelVo.getClienteleType()));
                } else {
                    memory.setClienteleType(4);
                }

                if (errorList != null && !errorList.isEmpty()) {
                    memory.setError(errorList);
                }

                insertData.add(memory);
            }
        }


        return insertData;
    }


    @Override
    public List<String> errorMemoryNull(WmsMemoryExcelVo excelVo, int index) {

        List<String> errpr = new ArrayList<>();

        if (StringUtils.isBlank(excelVo.getVin())) {
            errpr.add(index + "行:vin为空");
        }

        if (StringUtils.isBlank(excelVo.getCompanyName())) {
            errpr.add(index + "行:省公司名称为空");
        }

        if (StringUtils.isBlank(excelVo.getStockroomName())) {
            errpr.add(index + "行:仓库名称为空");
        }

        if (StringUtils.isBlank(excelVo.getStockroomPartitionName())) {
            errpr.add(index + "行:库区名称为空");
        }

        if (StringUtils.isBlank(excelVo.getStockroomPositionCode())) {
            errpr.add(index + "行:库位号为空");
        }

        if (StringUtils.isBlank(excelVo.getBrandName())) {
            errpr.add(index + "行:品牌名称为空");
        }

        if (StringUtils.isBlank(excelVo.getVehicleModel())) {
            errpr.add(index + "行:车型为空");
        }

        if (StringUtils.isBlank(excelVo.getVehicleSeries())) {
            errpr.add(index + "行:车系为空");
        }

        if (StringUtils.isBlank(excelVo.getVehicleColor())) {
            errpr.add(index + "行:车辆颜色为空");
        }

        if (StringUtils.isBlank(excelVo.getClienteleName())) {
            errpr.add(index + "行:客户名称为空");
        }

        if (StringUtils.isBlank(excelVo.getClienteleType())) {
            errpr.add(index + "行:客户类型为空");
        }

        if (excelVo.getStockroomInDate() == null) {
            errpr.add(index + "行:实际入库日期为空");
        }

        if (StringUtils.isBlank(excelVo.getStockroomMemoryDays())) {
            errpr.add(index + "行:在库天数为空");
        }

        if (StringUtils.isBlank(excelVo.getQualityStatus())) {
            errpr.add(index + "行:质量状态为空");
        }

        return errpr;
    }

}

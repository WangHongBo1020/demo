package com.example.whb_demo.dao.daoimpl;

import com.alibaba.fastjson.JSONObject;
import com.example.whb_demo.dao.ExcelServiceDao;
import com.example.whb_demo.entity.WmsRole;
import com.example.whb_demo.entity.WmsStockroomMemory;
import com.example.whb_demo.entity.WmsUser;
import com.example.whb_demo.entity.WmsUserRole;
import com.example.whb_demo.enumo.WmsstockEnum;
import com.example.whb_demo.mapper.ExcelMapper;
import com.example.whb_demo.utils.IdGenerator;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import com.example.whb_demo.vo.WmsUserExcelVo;
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
        List<WmsUserExcelVo> newList = excelListl.stream().distinct().collect(Collectors.toList());
        return newList;
    }

    @Override
    public List<String> errorNull(WmsUserExcelVo excelVo, int index) {

        List<String> error = new ArrayList<>();

        if (StringUtils.isEmpty(excelVo.getUsername())) {
            error.add(index + "行:登录账户为空");
        }
        if (StringUtils.isEmpty(excelVo.getEmail())) {
            error.add(index + "行:邮箱为空");

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

        return error;
    }


    @Override
    public WmsUser isNotData(WmsUserExcelVo excelVo) {

        String password = "cjkj123456";

        WmsUser wmsUser = new WmsUser();

        if (!StringUtils.isEmpty(excelVo.getUsername()) && !StringUtils.isEmpty(excelVo.getEmail())
                && !StringUtils.isEmpty(excelVo.getMobile()) && !StringUtils.isEmpty(excelVo.getName())
                && !StringUtils.isEmpty(excelVo.getRole())) {

            wmsUser.setUserId(IdGenerator.getIdStr());
            wmsUser.setUsername(excelVo.getUsername());
            wmsUser.setPassword(passwordEncoder.encode(password));
            log.info("getPassword-----:{}", JSONObject.toJSONString(wmsUser.getPassword()));

            wmsUser.setEmail(excelVo.getEmail());
            wmsUser.setMobile(excelVo.getMobile());
            wmsUser.setName(excelVo.getName());
            wmsUser.setRole(excelVo.getRole());
            wmsUser.setCreateUser("系统");
            wmsUser.setTenantId("1452572477019402241");
            wmsUser.setErpCustomerId("1452572477019402241");

            log.info("wmsUser-----:{}", JSONObject.toJSONString(wmsUser));

        }
        return wmsUser;
    }

    @Override
    public String insertWmsrole(List<WmsUser> userList) {

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

                //插入wms_user_role表数据
                userRole.setUserRoleId(IdGenerator.getIdStr());
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(StringUtils.isEmpty(flag) ? role.getWmsRoleId() : flag);
                userRole.setRoleName(role.getWmsRoleName());

                int userrolecount = excelMapper.insertWmsuserrole(userRole);

                if (userrolecount > 0) {

                    return "1";
                }
            }

        }

        return "";
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

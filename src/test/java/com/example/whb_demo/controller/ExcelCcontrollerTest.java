package com.example.whb_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.whb_demo.entity.WmsUser;
import com.example.whb_demo.utils.CheckUtil;
import com.example.whb_demo.utils.Md5Util;
import com.example.whb_demo.utils.VinUtil;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class ExcelCcontrollerTest {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void jiami() {
        System.out.println("1");
    }

    @Test
    public void Base() {
        String password = Md5Util.getMD5("cjkj123456");
        System.out.println(password);

        String passwords = Md5Util.getMD5(password);

        System.out.println(passwords);
    }

    @Test
    public void ArraysasList() {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();

        String role = "韵车系统订单修改审批流程--林娟娟-2022-02-18";
        String str[] = role.split("-");
        list1.add(role);
        list = Arrays.asList(str);


        System.out.println(list.get(0));


        /*for (String s : list) {
            String sss = s;
            System.out.println(sss);
        }

        System.out.println(list1);
        System.out.println(list);*/
    }

    @Test
    public void userListstream() {

        WmsMemoryExcelVo wmsMemoryExcelVo = new WmsMemoryExcelVo();
        wmsMemoryExcelVo.setVin("111");
        wmsMemoryExcelVo.setBrandName("1");
        WmsMemoryExcelVo wmsMemoryExcelVo1 = new WmsMemoryExcelVo();
        WmsMemoryExcelVo wmsMemoryExcelVo3 = new WmsMemoryExcelVo();
        wmsMemoryExcelVo1.setVin("111");
        wmsMemoryExcelVo1.setBrandName("2");

        wmsMemoryExcelVo3.setVin("1111");
        wmsMemoryExcelVo3.setBrandName("22");
        List<WmsMemoryExcelVo> userList = new ArrayList<>();

        userList.add(wmsMemoryExcelVo1);
        userList.add(wmsMemoryExcelVo);
        userList.add(wmsMemoryExcelVo3);

        //对list数据 根据某个字段去掉重复数据
        List<WmsMemoryExcelVo> userLists = userList.stream().
                collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing(WmsMemoryExcelVo::getVin))),
                        ArrayList::new));

        WmsMemoryExcelVo wmsMemoryExcelVo2 = new WmsMemoryExcelVo();
        for (WmsMemoryExcelVo list : userLists) {
            wmsMemoryExcelVo2.setVin(list.getVin());
            wmsMemoryExcelVo2.setBrandName(list.getBrandName());
        }

        System.out.println(JSONObject.toJSONString(wmsMemoryExcelVo2));
        System.out.println(JSONObject.toJSONString(userLists));

    }

    @Test
    public void getDuplicateElements() {

        WmsMemoryExcelVo wmsMemoryExcelVo = new WmsMemoryExcelVo();
        wmsMemoryExcelVo.setVin("111");
        wmsMemoryExcelVo.setBrandName("1");
        wmsMemoryExcelVo.setClienteleType("2");

        WmsMemoryExcelVo wmsMemoryExcelVo1 = new WmsMemoryExcelVo();
        wmsMemoryExcelVo1.setVin("111");
        wmsMemoryExcelVo1.setBrandName("1");
        wmsMemoryExcelVo1.setClienteleType("2");

        List<WmsMemoryExcelVo> userList = new ArrayList<>();

        userList.add(wmsMemoryExcelVo1);
        userList.add(wmsMemoryExcelVo);

        List list1 = userList.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(list1));
    }

    @Test
    public void passwordEncoder() {
        String password = "cjkj123456";

        String str = passwordEncoder.encode(password);

        System.out.println(str);
    }

    @Test
    public void createSheet() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("创建sheet页");
        Row row = sheet.createRow(0);//创建行
        Cell cell = row.createCell(0);//创建一个单元格， 第一列
        cell.setCellValue(1);  //给单元格设置值

        row.createCell(1).setCellValue(1.2); //创建一个单元格，第三列，值为1.2
        row.createCell(2).setCellValue("这是一个字符串");
        row.createCell(3).setCellValue(false); //4列 创建一个Boolean类型

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\3.xls");
        wb.write(fileOutputStream);
        fileOutputStream.close();
    }

    @Test
    public  void isLegalVin() {

        if (CheckUtil.vinCheck("UU6JA69691D713820")){
            System.out.println("true");
        }

        if (!CheckUtil.vinCheck("LSGDC82C11S10203O")){
            System.out.println("flae");
        }

            /*System.out.println(isLegalVin("UU6JA69691D713820"));

            System.out.println(isLegalVin("LFV3A21K7D4262398"));

            System.out.println(isLegalVin("LFV3A23C793062656"));

            System.out.println(isLegalVin("LSGDC82C11S10203O"));

            System.out.println(isLegalVin("LSGDC82C11S102030"));

            System.out.println(isLegalVin("LSGUD84X2BE041557"));

            System.out.println(isLegalVin("WDDBF4CB2EJ143048"));

            System.out.println(isLegalVin("LFP83ACCXD1D99699"));*/

    }

    @Test
    public void StringJSONtoString(){
        List<String> list = new ArrayList<>();

        String str = "6666";
        list.add(str);
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void meMoryDays() throws ParseException {

        //JDK8 时间格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(LocalDate.now().format(formatter));
        //JDK8 时间格式化


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String ss = "2022-03-10 10:57:38";
        Date sss = sdf.parse(ss);
        System.out.println("sss" + sss);
        Date date = new Date();
        Date stockroomInDate=sdf.parse(sdf.format(sss));
        date=sdf.parse(sdf.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(stockroomInDate);
        long time1 = cal.getTimeInMillis();

        cal.setTime(date);

        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24) + 1;

        //TODO 这俩方法jdk8 都可以获取到上个月的时间
        //获取上一个月时间
        LocalDateTime startTime = LocalDateTime.now();
        startTime = startTime.minus(90, ChronoUnit.DAYS);
        startTime = startTime.plusDays(+1);

        //获取当前时间
        LocalDate endDate = LocalDate.now();
        //获取当前时间前一个月时间
        LocalDate beginDate = endDate.plusMonths(-1);

        System.out.println(startTime);

        System.out.println(between_days);
    }


    /**
     * 将list转成map 然后可以用key做判断
     * Map<String, WmsUserClient> clientMap  = userClientList.stream().
     * collect(Collectors.toMap(WmsUserClient::getClienteleId, x->x ));
     *
     * WmsUserClient client = clientMap.get(carData.getClienteleId());
     */




    @Test
    public  void maopaopaixu(){
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        sort(array);
        System.out.println(Arrays.toString(array));

        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(1652976000000L);

        System.out.println(currentTime);

    }


    private  void sort(int array[]) {
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }


    // TODO JDBC 链接上数据库sql编写
    //  PreparedStatement stmt = connection.prepareStatement("这里写sql语句")：
    //  按照类型赋值，这里使用的 long类型 stmt.setLong(1, requestId)：
    //  ResultSet rs = stmt.executeQuery() 注入sql;

    @Test
    public void BeanUtils(){

        // TODO  BeanUtils.copyProperties(a,b);
        //  BeanUtils.mcopyProperties注：如果User和UserActionForm 间存在名称不相同的属性，
        //  则BeanUtils不对这些属性进行处理，需要手动处理。例如User类里面有个StockroomName 创建时间字段，
        //  而UserActionForm里面无此字段。BeanUtils.copyProperties()不会对此字段做任何处理。必须要自己手动处理;

        WmsUser user = new WmsUser();

        user.setStockroomName("1233");

        WmsUser wmsUser = new WmsUser();

        BeanUtils.copyProperties(user,wmsUser);
        System.out.println(wmsUser);


        List<WmsUser> list = new ArrayList<>();
        List<WmsUser> list1 = new ArrayList<>();
        WmsUser user1 = new WmsUser();
        WmsUser user2 = new WmsUser();
        WmsUser user3 = new WmsUser();
        WmsUser user4 = new WmsUser();
        WmsUser user6 = new WmsUser();

        user1.setStockroomName("12333");
        user2.setStockroomName("12334");

        user3.setStockroomName("12335");
        user4.setStockroomName("12336");
        user6.setStockroomName("12333");


        list.add(user1);
        list.add(user2);

        list1.add(user3);
        list1.add(user4);
        list1.add(user6);

        List<WmsUser> filterList = list1.stream().filter(a -> !list.contains(a))
                .collect(Collectors.toList());


        List<WmsUser> filterList1 = list1.stream().filter(a -> !list.contains(a))
                .collect(Collectors.toList());


        System.out.println("filterList:++++++" + JSONObject.toJSONString(filterList));

        /*List<String> reduce = list2.stream().filter(item -> !list1.contains(item)).collect(toList());*/
        List<WmsUser> list2 = new ArrayList<>();

       /* list2 = list.stream().map(e -> {
            WmsUser user3 = new WmsUser();

            BeanUtils.copyProperties(e, user3);
            return user3;
        }).collect(Collectors.toList());

        list2 = list1.stream().map(e -> {
            WmsUser user3 = new WmsUser();

            BeanUtils.copyProperties(e, user3);
            return user3;
        }).collect(Collectors.toList());

        //只输出了list1
        System.out.println("1111" + list2);*/

        list2 = list.stream().map(e -> {
            WmsUser user5 = new WmsUser();

            BeanUtils.copyProperties(e, user5);
            return user5;
        }).collect(Collectors.toList());

        list2.addAll(list1);

        List<WmsUser> deleteAccount = list2.stream().collect(Collectors.collectingAndThen
                (Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing
                        (WmsUser::getStockroomName))), ArrayList::new));

/*
        System.out.println("1111" + deleteAccount);
*/

    }

    @Test
    public void MapString(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("1", "11");
        map.put("2", "22");
        map.put("3", "33");
        map.put("4", "44");
        map.put("5", "https://img0.baidu.com/it/u=2862534777,914942650&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");

        JSONArray jsonArray = JSONArray.fromObject(map);

        String string = jsonArray.toString();
        String string1 = JSONObject.toJSONString(map);
        System.out.println(string);
        System.out.println(string1);

        JSONObject beforejson = JSONObject.parseObject(string1);

        String str = beforejson.getString("5");
        System.out.println(str);

        List<String> list = new ArrayList<>();
        list.add("https://img0.baidu.com/it/u=2862534777,914942650&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");
        list.add("https://img0.baidu.com/it/u=2862534777,914942650&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");
        System.out.println(list);

        String stri = JSONObject.toJSONString(list);
        System.out.println(stri);
    }


    @Test
    public void haomiaozhuanhuan () {

        Calendar calendar = Calendar.getInstance();

        String string = "1649210283057";

        Long s = Long.valueOf(string);

        if (calendar.getTimeInMillis() == s){

            System.out.println("666666666666666");
        }
        System.out.println(s);
        Date date = new Date(s);
        java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestring = f.format(date);

        System.out.println(timestring);
    }

    @Test
    public void streamFilter(){

        List<WmsUser> list = new ArrayList<>();
        WmsUser user1 = new WmsUser();
        WmsUser user2 = new WmsUser();
        WmsUser user3 = new WmsUser();
        WmsUser user4 = new WmsUser();
        WmsUser user6 = new WmsUser();
        user1.setMain(1);
        user2.setMain(2);
        user3.setMain(3);
        user4.setMain(4);
        user6.setMain(5);

        user1.setStockroomName("12333");
        user2.setStockroomName("12334");

        user3.setStockroomName("12335");
        user4.setStockroomName("12336");
        user6.setStockroomName("12333");

        user1.setIdNumber("12333");
        user2.setIdNumber("12334");

        user3.setIdNumber("12335");
        user4.setIdNumber("12336");
        user6.setIdNumber("12333");

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user6);
        List<WmsUser> list1 = new ArrayList<>();

        list1.addAll(list);
        List<WmsUser> list2 = new ArrayList<>();
        for (int i = 0; i <list.size()-1; i++) {

            for (int j = list.size() - 1; j > i; j--) {

                if(list.get(i).getStockroomName().equals(list.get(j).getStockroomName())
                    && list.get(i).getIdNumber().equals(list.get(j).getIdNumber())){
                    /*WmsUser user = new WmsUser();

                    BeanUtils.copyProperties(list.get(j),user);
                    *//*user.setStockroomName(list.get(i).getStockroomName());*//*
                    user.setMain(list.get(j).getMain() + list.get(i).getMain());
                    list2.add(user);*/
                    list.get(i).setMain(list.get(j).getMain() + list.get(i).getMain());
                    list.remove(j);

                }

            }
        }

        System.out.println(JSONObject.toJSONString(list));

        /*List<WmsUser> filterList = list.stream().filter(a -> !list.contains(a))
                .collect(Collectors.toList());*/
    }

}


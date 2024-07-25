package com.example.whb_demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.whb_demo.dto.BillingRuleContent;
import com.example.whb_demo.entity.WmsUser;
import com.example.whb_demo.utils.*;
import com.example.whb_demo.vo.WmsMemoryExcelVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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
    public void isLegalVin() {

        if (CheckUtil.vinCheck("UU6JA69691D713820")) {
            System.out.println("true");
        }

        if (!CheckUtil.vinCheck("LSGDC82C11S10203O")) {
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
    public void StringJSONtoString() {
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


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ss = "2022-03-10 10:57:38";
        Date sss = sdf.parse(ss);
        System.out.println("sss" + sss);
        Date date = new Date();
        Date stockroomInDate = sdf.parse(sdf.format(sss));
        date = sdf.parse(sdf.format(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(stockroomInDate);
        long time1 = cal.getTimeInMillis();

        cal.setTime(date);

        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24) + 1;

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
     * <p>
     * WmsUserClient client = clientMap.get(carData.getClienteleId());
     */


    @Test
    public void maopaopaixu() {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        sort(array);
        System.out.println(Arrays.toString(array));

        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(1652976000000L);

        System.out.println(currentTime);

    }


    private void sort(int array[]) {
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
    public void BeanUtils() {

        // TODO  BeanUtils.copyProperties(a,b);
        //  BeanUtils.mcopyProperties注：如果User和UserActionForm 间存在名称不相同的属性，
        //  则BeanUtils不对这些属性进行处理，需要手动处理。例如User类里面有个StockroomName 创建时间字段，
        //  而UserActionForm里面无此字段。BeanUtils.copyProperties()不会对此字段做任何处理。必须要自己手动处理;

        WmsUser user = new WmsUser();

        user.setStockroomName("1233");

        WmsUser wmsUser = new WmsUser();

        BeanUtils.copyProperties(user, wmsUser);
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
    public void MapString() {
        Map<String, String> map = new HashMap<String, String>();
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
    public void haomiaozhuanhuan() {

        Calendar calendar = Calendar.getInstance();

        String string = "1649210283057";

        Long s = Long.valueOf(string);

        if (calendar.getTimeInMillis() == s) {

            System.out.println("666666666666666");
        }
        System.out.println(s);
        Date date = new Date(s);
        java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestring = f.format(date);

        System.out.println(timestring);
    }

    @Test
    public void streamFilter() {

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
        for (int i = 0; i < list.size() - 1; i++) {

            for (int j = list.size() - 1; j > i; j--) {

                if (list.get(i).getStockroomName().equals(list.get(j).getStockroomName())
                        && list.get(i).getIdNumber().equals(list.get(j).getIdNumber())) {
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

    @Test
    public void BigDecimaldivide() {
        BigDecimal amount = BigDecimal.valueOf(834.52);
        BigDecimal rate = new BigDecimal(0);
        rate = BigDecimal.valueOf(6.7898);
        amount = amount.divide(rate, 2, BigDecimal.ROUND_HALF_UP);

        System.out.println(amount);
    }


    @Test
    public void Listnull() {
        List<WmsUser> wmsUsers = new ArrayList<>();
        List<WmsUser> wmsUsers1 = new ArrayList<>();

        WmsUser wmsUser = new WmsUser();

        wmsUser.setStockroomName("sdsd");

        wmsUsers.add(wmsUser);

        wmsUsers = wmsUsers1.stream().map(e -> {
            WmsUser wms = new WmsUser();
            BeanUtils.copyProperties(e, wms);
            return wms;
        }).collect(Collectors.toList());

        System.out.println(JSONObject.toJSONString(wmsUsers));


        Object obj1 = 666;
        Object obj2 = 6677;
        obj1 = obj2;
        System.out.println(obj1);

        long startTime = System.nanoTime();

        //下面是一些测试代码
        for (int i = 0; i < 10000; i++) {
            System.out.println("当前是：" + i);

        }

        long endTime = System.nanoTime();

        System.out.println("当前程序耗时：" + (endTime - startTime) + "ns");

    }

    @Test
    public void ceshi() {

        String a = "1";
        String b = "2";
        String c = "3";
        String d = "4";

        if ((a.equals("2") && b.equals("2")) || (c.equals("3") && d.equals("4"))) {

            System.out.println("chengong");
        }
    }


    @Test
    public void duanyan() {
        /*WmsUser user = new WmsUser();
        Asserts.assertNotBlank(user.getUserId(), "库位与客户无绑定关系");*/

        int count = 1;
        int counts = 11;
        //Asserts.assertTrue(count == 0, "库位与客户无绑定关系");
        Asserts.assertTrue(counts != 1, "车辆存在质损，不允许出库");


    }

    @Test
    public void BigDecimal() {

        BigDecimal ss = BigDecimal.valueOf(0);

        if (ss.compareTo(BigDecimal.ZERO) == 1) {
            System.out.println(11111);
        } else {
            System.out.println(2222);
        }
    }

    @Test
    public void Collectors() {
        List<String> list = new ArrayList<>();
        String se = "a";
        String se1 = "a";
        String se2 = "a";
        list.add(se);
        list.add(se1);
        list.add(se2);
        String sse = "s";
        list.add(sse);
        String sss = "e";
        list.add(sss);
        String ssss = "f";
        list.add(ssss);
        String sssss = "b";
        list.add(sssss);

        List<WmsUser> userList = new ArrayList<>();
        WmsUser user = new WmsUser();
        user.setUserId("1");
        user.setName("2");
        userList.add(user);
        String s = list.stream().collect(Collectors.joining("\n"));
        String ss = String.join("\n", list);
        /*System.out.println(s);*/
        System.out.println(ss);
        List<WmsUser> list1 = userList.stream().filter(s1 -> s1.getUserId().equals("1") && s1.getName().equals("2")).collect(Collectors.toList());
        System.out.println(list1);
    }

    @Test
    public void itercuowuList() {

        List<String> stringList = new ArrayList<>();
        stringList.add("1111");
        stringList.add("2222");
        stringList.add("3333");
        System.out.println(stringList.stream().collect(Collectors.toList()));
        List<WmsUser> userList = new ArrayList<>();
        List<WmsUser> userList1 = new ArrayList<>();
        WmsUser user = new WmsUser();
        user.setUserId("");
        user.setName("");
        userList.add(user);
        int rowNum = 1;
        for (WmsUser s : userList) {
            rowNum++;
            if (StringUtils.isBlank(s.getUserId())) {
                s.setStockroomName("kong");
                s.setMain(rowNum);
                userList1.add(s);
            }
            if (StringUtils.isBlank(s.getName())) {
                s.setStockroomName("kong1");
                s.setMain(rowNum);
                userList1.add(s);
            }
        }

        System.out.println(JSONObject.toJSONString(userList1));
    }

    @Test
    public void test() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = "2023-01-05";
        String datas = "2023-01-06";
        String s5 = data.replaceAll("\\pL", "");

        Date date = sdf.parse(data);
        Date datess = sdf.parse(datas);

        if (date.getTime() > datess.getTime()) {
            System.out.println("111");
        } else {
            System.out.println("2222");
        }

        System.out.println(date);
        System.out.println(s5);

        AtomicBoolean item = new AtomicBoolean(false);

        item.set(true);
        System.out.println(item);

        String date1 = "1";
        String dates = "2";


        System.out.println("1" + date1 + "-" + "-" + dates);

        List<String> list = new ArrayList<>();

        list.add(date1);
        list.add(dates);

        int rowNum = 0;

        for (int i = 0; i < list.size(); i++) {
            rowNum++;
            System.out.println(rowNum);
        }
    }

    @Test
    public void listContains() {
        List<WmsUser> list = new ArrayList<>();
        WmsUser user = new WmsUser();
        WmsUser user2 = new WmsUser();
        WmsUser user3 = new WmsUser();
        user.setStockroomName("1");
        user2.setStockroomName("2");
        user3.setStockroomName("1");

        list.add(user);
        list.add(user2);
        list.add(user3);

        List<WmsUser> excelErrorList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            WmsUser s = list.get(i);

            if (!excelErrorList.contains(s)) {
                excelErrorList.add(s);
            }

        }

        System.out.println(excelErrorList);
    }


    @Test
    public void Stringcover() {

        List<WmsUser> list = new ArrayList<>();
        WmsUser user = new WmsUser();
        WmsUser user2 = new WmsUser();
        WmsUser user3 = new WmsUser();
        user.setStockroomName("1");
        user2.setStockroomName("2");
        user3.setStockroomName("3");

        list.add(user);
        list.add(user2);
        list.add(user3);
        String str;
        List<String> excelErrorList = new ArrayList<>();

        for (WmsUser wmsUser : list) {

            if ("1".equals(wmsUser.getStockroomName())) {
                str = "1";
                excelErrorList.add(str);
            }

            if ("2".equals(wmsUser.getStockroomName())) {
                str = "2";
                excelErrorList.add(str);
            }

            if ("3".equals(wmsUser.getStockroomName())) {
                str = "3";
                excelErrorList.add(str);
            }
        }

        System.out.println(excelErrorList);
    }

    @Test
    public void sudstring() {

        Date date = new Date();

        String bidDate = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        //String s = "PSF202211141427";
        //
        //String bid = s; //取出ID，也就是业务号
        //bid = bid.substring(12, 15); // 取出后三位数，也就是自动生成的三位数 001
        //System.out.println(bid);
        //
        //int num = Integer.valueOf(bid);
        //num++;
        ////String bidNum = String.format("%03d", num);//%03d 只是三位，不足补0
        ////String code = "pfe" + bidDate + bidNum;
        //
        //String bidNum = "00" + 1;
        //String code = "PFE" + bidDate + bidNum;


        int number = 1;
        String bidNum = "0" + number;
        String code = "PFE" + bidDate + bidNum;

        System.out.println(code);

        System.out.println(code);
    }

    @Test
    public void fileDownloadSupp() throws IOException {
        String fileName = "requestId.xls";
        String savePath = "/Users/wanghongbo/Desktop/sql";
        String url = "http://oa-test.changjiu56.com/weavernorth/loginSSobk.jsp?loginName=3874&url=http://oa-test.changjiu56.com//weaver/weaver.file.FileDownload?fileid=a5cfea6a6c72d174cc1b1a4e12cd346fc0bf825d2094ab4d0e2d26994ed02ca2fa4ea67e5814d74767b40dd576f670b822971a68eea332bb9&download=1&requestid=&ddcode=89a67125d99e2ccb6e8662b6d7f37c27";
        URL urlStr = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlStr.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //从输入流中获取字节数组
        byte[] getData = readInputStreamSupp(inputStream);
        //文件保存位置
        File saveDir = new File(savePath);

        if (!saveDir.exists()) {
            saveDir.mkdir();
        }

        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

    }

    private byte[] readInputStreamSupp(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    @Test
    public void quchong() {
        List<WmsUser> lists = new ArrayList<>();
        WmsUser user1 = new WmsUser();
        user1.setUserId("1");
        user1.setName("张三");
        user1.setCreateUser("北京");
        user1.setStockroomName("1");
        user1.setClienteleCode("1");
        user1.setIdNumber("1");
        user1.setPassword("");


        WmsUser user2 = new WmsUser();
        user2.setUserId("1");
        user2.setName("张三");
        user2.setCreateUser("北京");
        user2.setStockroomName("1");
        user2.setClienteleCode("1");
        user2.setIdNumber("1");
        user2.setPassword("");

        WmsUser user3 = new WmsUser();
        user3.setUserId("2");
        user3.setName("小芳");
        user3.setCreateUser("深圳");
        user3.setStockroomName("1");
        user3.setClienteleCode("1");
        user3.setIdNumber("1");
        user3.setPassword("");

        WmsUser user4 = new WmsUser();
        user4.setUserId("3");
        user4.setName("小花");
        user4.setCreateUser("深圳");
        user4.setStockroomName("1");
        user4.setClienteleCode("1");
        user4.setIdNumber("1");
        user4.setPassword("");


        WmsUser user5 = new WmsUser();
        user5.setUserId("1");
        user5.setName("张三");
        user5.setCreateUser("北京");
        user5.setStockroomName("1");
        user5.setClienteleCode("1");
        user5.setIdNumber("1");
        user5.setPassword("1");

        lists.add(user1);
        lists.add(user2);
        lists.add(user3);
        lists.add(user4);

        List<WmsUser> list1 = new ArrayList<>();
        list1 = lists.stream().
                collect(Collectors.collectingAndThen
                        (Collectors.toCollection(() -> new TreeSet<>
                                (Comparator.comparing(
                                        o -> o.getUserId() + o.getName() + o.getStockroomName() + o.getClienteleCode()
                                                + o.getIdNumber() + o.getPassword() + o.getEmail()))), ArrayList::new));

        for (WmsUser user : list1) {
            System.out.println(user);

        }

        System.out.println("--------------------------------");

        list1.forEach(l -> {
            lists.forEach(l1 -> {

                if (l.getUserId().equals(l1.getUserId()) && l.getName().equals(l1.getName())
                        && l.getStockroomName().equals(l1.getStockroomName())
                        && l.getClienteleCode().equals(l1.getClienteleCode()) && l.getIdNumber().equals(l1.getIdNumber())
                        && l.getPassword().equals(l1.getPassword())) {
                    System.out.println(l1);
                }
            });
        });
    }

    @Test
    public void taxAmount() {
        BigDecimal big = new BigDecimal(3450);
        BigDecimal big1 = new BigDecimal(1.09);
        BigDecimal big2 = new BigDecimal(0.09);
        BigDecimal big3 = big1.multiply(big2).setScale(2, RoundingMode.CEILING);
        System.out.println(big3);
        //  总金额 /1。09 x0。09

        BigDecimal taxAmount = big.divide(big1, 2, RoundingMode.CEILING);


        BigDecimal amount = taxAmount.multiply(big2).setScale(2, RoundingMode.CEILING);
        System.out.println(amount);
    }

    @Test
    public void isValidDates() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");


        Date date = new Date();

        String str = format.format(date);

        Date date1 = format.parse(str);

        System.out.println("str--------------------------------" + str);

        Date date2 = format.parse(str);
        System.out.println(date2 + "2222232323");

        //Date str = format.parse(strs);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.HOUR_OF_DAY, 23); //时
        cal.add(Calendar.MINUTE, 59);// 分
        cal.add(Calendar.SECOND, 59);// 秒
        date = cal.getTime();
        System.out.println("date111111:" + date);

        System.out.println("after:" + format.format(date));
        String format1 = format.format(date);
        Date parse = format.parse(format1);
        System.out.println("parse:" + parse);
        System.out.println("parsesss:" + format.format(parse));

        JSONObject jsonIsCompleteCalc = new JSONObject();
        jsonIsCompleteCalc.put("erpCustomerId", "N");
        System.out.println(jsonIsCompleteCalc.toJSONString());
        WmsUser user = new WmsUser();
        user = JSONObject.parseObject(String.valueOf(jsonIsCompleteCalc), WmsUser.class);
        System.out.println(user);
        //System.out.println(str);
        //System.out.println(string);
    }

    @Test
    public void isEffectiveTime() {
        String nowStartDate = "2023-04-01 00:00:00";
        String nowEndDate = "2023-08-31 00:00:00";
        boolean flag = true;

        Long timeFm = null;
        Long timeTo = null;
        try {

            timeFm = new SimpleDateFormat("yyyy-MM-dd").parse(nowStartDate).getTime();
            timeTo = new SimpleDateFormat("yyyy-MM-dd").parse(nowEndDate).getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (timeFm != null && timeTo != null) {

            if (timeFm > timeTo) {
                flag = false;
            }

        } else {

            flag = false;
        }


        System.out.println(flag);

    }

    public static void main(String[] args) throws ParseException {
        String ss = isWithinOneYear();
        System.out.println(ss);
    }


    public static String isWithinOneYear() throws ParseException {

        String nowStartDate = "2023-05-01 00:00:00";
        String nowEndDate = "2023-09-01 00:00:00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = format.parse(nowStartDate);

        Date endDate = format.parse(nowEndDate);

        Instant instant = startDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

        Instant instants = endDate.toInstant();
        ZoneId zoneIds = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDates = instants.atZone(zoneIds).toLocalDate();

        Period period = Period.between(localDate, localDates);
        int diffInDays = period.getDays();
        int diffInMonths = period.getMonths();
        int diffInYears = period.getYears();
        // 如果年份差跨度大于1年，返回 false
        if (diffInYears > 1) {

            return "1";
        }
        // 如果年份差跨度等于1年
        if (diffInYears == 1) {
            // 判断结束日期往前推一年后是否在开始日期之前
            LocalDate nextYearEndDate = localDates.minusYears(1);
            if (nextYearEndDate.isBefore(localDate)) {
                return "2";
            } else if (nextYearEndDate.equals(localDate) && (diffInMonths > 0 || diffInDays > 0)) {
                // 如果结束日期往前推一年后与开始日期相等且月份或天数有差距，返回 false
                return "3";
            }
        }
        // 如果年份差跨度小于1年，直接判断月份或天数差距
        if (diffInMonths > 0 || diffInDays > 0) {
            return "4";
        }

        return "0";
    }

    @Test
    public void datajiajian() throws ParseException {

        String nowStartDate = "2023-04-30";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date date = format.parse(nowStartDate);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();

        System.out.println(format.format(date));
    }

    @Test
    public void snowflakeldWorker() {
        int count = 1;
        for (int i = 0; i < count; i++) {
            long n = SnowflakeIdWorkerUtil.generateId();
            System.out.println("RDN" + n);
        }

    }

    @Test
    public void mockBilling() {
        String str = "{\n" +
                " \"obj\":[\n" +
                " \t{\n" +
                " \t\t\"code\":\"if\",\n" +
                " \t\t\"name\":\"如果\",\n" +
                " \t\t\"type\":\"1\"\n" +
                " \t},{\n" +
                " \t\t\"code\":\"(\",\n" +
                " \t\t\"name\":\"(\"\n" +
                " \t},{\n" +
                " \t\t\"code\":\"brandName\",\n" +
                " \t\t\"name\":\"品牌名称\"\n" +
                " \t},{\n" +
                " \t\t\"code\":\"==\",\n" +
                " \t\t\"name\":\"等于\"\n" +
                " \t},{\n" +
                " \t\t\"code\":\"北京现代\",\n" +
                " \t\t\"name\":\"文本框\"\n" +
                " \t},{\n" +
                " \t\t\"code\":\")\",\n" +
                " \t\t\"name\":\"右括号\"\n" +
                " \t}\n" +
                " ],\n" +
                " childList:[\n" +
                " \t{\n" +
                " \t\t\"obj\":[{\n" +
                " \t\t\t\"code\":\"if\",\n" +
                " \t\t\t\"name\":\"如果\"\n" +
                " \t\t}],\n" +
                " \t\tchildList:[]\n" +
                " \t},\n" +
                " \t{\n" +
                " \t\t\"obj\":[{\n" +
                " \t\t\t\"code\":\"else\",\n" +
                " \t\t\t\"name\":\"否则\"\n" +
                " \t\t}],\n" +
                " \t\tchildList:[]\n" +
                " \t}\n" +
                " ]\n" +
                "}\n";

        System.out.println(str);

        //  获取计费公式
        ObjectMapper objectMapper = new ObjectMapper();

        // 将JSON字符串转换为Map对象
        try {
            Map<String, Object> map = objectMapper.readValue(str, new TypeReference<Map<String, Object>>() {
            });

            List<BillingRuleContent> ruleContentList = Lists.newArrayList();

            for (String strs : map.keySet()) {
                BillingRuleContent ruleContent = new BillingRuleContent();
                //  逻辑处理

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void doubleiiii() throws ParseException {
        //Double d = 1.2;
        //
        //Double e = -5.0;
        //
        //d = d +e;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String str = "2023-12-08";
        String str1 = "2023-12-17";
        String str2 = "2023-08-11";

        Date date = formatter.parse(str);
        Date date1 = formatter.parse(str1);
        Date date2 = formatter.parse(str2);

        if (date1.getTime() < date.getTime()) {
            System.out.println("11111");
        } else {
            System.out.println("2222");
        }

        //System.out.println(d);


        //String sss = " if   (  obj.document.billingCount  <  3  )   {\n" +
        //        "            obj.settlement.attributeNumber1  =  obj.detail.attributeNumber1  *  obj.detail.attributeNumber2  *  1.1 ;\n" +
        //        "        }  else if   (  obj.document.billingCount  >=  3  &&  obj.document.billingCount  <=  5  )   {\n" +
        //        "            obj.settlement.attributeNumber1  =  obj.detail.attributeNumber1  *  obj.detail.attributeNumber2  *  1.2 ;\n" +
        //        "        }  else   (  obj.document.billingCount  >  5  )   {\n" +
        //        "            obj.settlement.attributeNumber1  =  obj.detail.attributeNumber1  *  obj.detail.attributeNumber2  *  1.5 ;\n" +
        //        "        }";
        //
        //List<String> list = new ArrayList<String>();
        //
        // Pattern p = Pattern.compile("obj[.]document[.](\\w+)");
        // Matcher matcher = p.matcher(sss);
        // while (matcher.find()) {
        //     list.add(matcher.group(1));
        // }
        //
        // Pattern ps = Pattern.compile("obj[.]settlement[.](\\w+)");
        // Matcher matchers= ps.matcher(sss);
        // while (matchers.find()) {
        //     list.add(matchers.group(1));
        // }
        //
        // List<String> distinctList = list.stream().distinct().collect(Collectors.toList());
        //
        // System.out.println(distinctList);
        //

    }

    @Test
    public void testShowField() {
        //List<String> showField = Lists.newArrayList();
        //showField.add("enterprise_id");
        //showField.add("settlement_type_code");
        //showField.add("document_code");
        //showField.add("business_type_code");
        //showField.add("fee_type_code");
        //
        //System.out.println(String.join(",",showField));
        //
        //
        //System.out.println(Year.now().getValue());
        //System.out.println(LocalDate.now());
        //
        //String sss = "666666,55555";
        //List<String> list = Arrays.stream(sss.split(",")).collect(Collectors.toList());
        BigDecimal getNumericalValue1 = new BigDecimal(40);
        BigDecimal getNumericalValue2 = new BigDecimal(60);
        BigDecimal sumNumerical = getNumericalValue1.add(getNumericalValue2);
        BigDecimal targetValue = new BigDecimal("100");
        if (sumNumerical.compareTo(targetValue) != 0) {
            //throw new BusinessException("汇票,电汇总数不等于100%");
            System.out.println("汇票,电汇总数不等于100%");
        }
        System.out.println("汇票,电汇总数等于100%");

    }

    /**
     * 获取月份最后一天
     */
    @Test
    public void zuihou() {
        //// 获取当前日期
        //LocalDate currentDate = LocalDate.now();
        //
        //// 获取当前月份的最后一天
        //LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        //
        //System.out.println("当前月最后一天：" + lastDayOfMonth);
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = "2022-12-01";
            Date date2 = format2.parse(date);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date2);
            //calendar.add(Calendar.DATE, 1);
            calendar.add(Calendar.MONTH, 1); // 增加一个月
            date2 = calendar.getTime();
            System.out.println(JSONObject.toJSONString(date2));
            Date date3 = date2;
            String date4 = format2.format(date3);
            System.out.println(JSONObject.toJSONString(date4));
        } catch (Exception e) {
            ExceptionUtils.getMessage(e);
        }
        System.out.println("");
    }

    @Test
    public void json() {
        BigDecimal billingSuccessCount = new BigDecimal("4166");
        BigDecimal total = new BigDecimal("4177");
        //int totalCompletion = Math.round((float) billingSuccessCount / total) * 100;
        BigDecimal totalCompletion = billingSuccessCount.divide(total, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
        System.out.println(totalCompletion);
        //while (count < total ){
        //    int progress = (int) (count * 100 / total);
        //    System.out.println(progress);
        //    count += 5000;
        //}
        //int progress = (int) (count * 100 / total);
        //System.out.println(progress);
    }

    @Test
    public void jiequ() {
        String ap = "北京市";
        List<String> commaList = Lists.newArrayList(ap.split(","));
        ////String str = String.join(",",commaList);
        //System.out.println(commaList.stream().map(String::valueOf).collect(Collectors.toList()));
        //List<String> strList = Lists.newArrayList();
        //for (String str : commaList) {
        //    if (str.contains("市")) {
        //        strList.add(str.substring(0, str.length() - 1));
        //    }
        //}

        System.out.println(JSONObject.toJSONString(""));
    }

    @Test
    public void Comparator() {
        String str = "1,2";
        List<String> list = Lists.newArrayList();
        list.add(str);
        List<String> list1 = Lists.newArrayList();
        //list1.addAll(list);
        String str1 = "1,2,3";
        String str2 = "4,6,7";
        list1.add(str1);
        list1.add(str2);

        for (String s : list1) {
            if (s.contains(str)) {
                System.out.println("包含");
            }
        }

        //List<String> lis3 = new ArrayList<>(new HashSet<>(list1));
        //System.out.println(JSONObject.toJSONString(lis3));


    }

    @Test
    public void regularExpression() {
        //String regex = "[a-zA-Z0-9]{11}";
        //String input = "HZW-JX202405080002";
        //String regex = "\\d{3}_\\d{11}";
        //String regex = "\\b2\\d{11}\\b";
        //String regex = "([a-zA-Z0-9]{19})|(\\d{4}[a-zA-Z]\\d{7})";
        //String regex = "2(\\d{11})";
        //String regex = "[LWlw][a-zA-Z0-9]{16}";
        //String regex = "[a-zA-Z]{2}_\\d{13}";
        //String regex = "\\d+[_]\\d+";
        //String regex = "[0-9]{10}";
        //String regex = "[a-zA-Z0-9]{14}-\\d{2}";
        //String regex = "[LWHlwh][a-zA-Z0-9]{16}";
        //String regex = "^[A-HJ-NPR-Z0-9]{17}$";
        //String regex = "[A-Za-z]{3}\\d{15}$";
        //String regex = "[A-Za-z]{2}\\d{11}[-]\\d{4}";
        //String regex = "VIN\\+s([YLWylw][a-zA-Z0-9]{17})";
        //String regex = "VIN\\s+([A-HJ-NPR-Z0-9]{17})";
        //String regex = "([A-Za-z]{3}\\d{15}$)|([A-Za-z]{3}[_][A-Za-z]{2}\\d{14}$)";
        // String regex = "([a-zA-Z0-9]{19})|(\\d{4}[a-zA-Z]\\d{7})";
        //String input = "YV1LFL1F4R1236247";
        //String regex = "[A-Za-z]{1}\\d{14}";
        //String regex = "\\d{11}[Y]{1}\\d{1}";
        //String regex = "[S]{2}\\d{16}";
        //String regex = "[a-zA-Z0-9]{12}|NO\\.[a-zA-Z]{2}\\d{8}";
        //String regex = "HZW-[a-zA-Z]{2}\\d{12}";
        //String regex = "[a-zA-Z0-9]{12}|NO\\.[a-zA-Z]{2}\\d{8}|[0-9]{10}|[a-zA-Z]{3}\\d{12}";
        String regex = "[a-zA-Z0-9]{12}|NO\\.[a-zA-Z]{2}\\d{8}|[0-9]{10}|[a-zA-Z]{3}\\d{12}|[a-zA-Z]{2}\\d{11}";
        //16
        String input = "092 2024061780001";
//|([a-zA-Z]{2}\d{18}
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println(input);
        }


    }

    @Test
    public void gongshi() {
        String input = "L2CB83BX8RG337993";
        //String str = input.substring(0,3);
        //String string = input.substring(4,17);
        if (input.length() == 17) {
            String str = input.substring(4,5);
            System.out.println(str);
            if ("8".equals(str)){
                String before = input.substring(0,4);
                System.out.println(before);
                String after = input.substring(5,17);
                System.out.println(after);
                String value = before +'B' + after;
                System.out.println(value);
            }

        }
        System.out.println(input);

        //String str = "Hello, WOrld! This is an example.";
        //String replacedStr = str.replaceAll("[Oo]", "0");
        //System.out.println("Original String: " + str);
        //System.out.println("Replaced String: " + replacedStr);
    }

    @Test
    public void listSizeAddSize() {
        List<String> list = Lists.newArrayList();
        List<String> list1 = Lists.newArrayList("1");
        List<String> list3 = Lists.newArrayList("1");
        if (list.size() == list3.size()) {
            System.out.println("111");
        } else {
            System.out.println("222");
        }




    }

    /**
     * 时间戳转日期
     */
    @Test
    public void shijianchuo() {
        //Instant instant = Instant.ofEpochSecond(Long.parseLong("1716790307924"));
        Instant instant = Instant.ofEpochMilli(1716790307924L);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        // 设置日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
    }

    @Test
    public void listeq() {
        String strss = "1,2,3";
        List<String> strings = Lists.newArrayList(strss.split(","));
        System.out.println(strings);
        List<String> str = Lists.newArrayList("1", "3", "2");
        List<String> str1 = Lists.newArrayList("3", "1", "2");

        if (str.containsAll(str1)) {
            System.out.println("一致");
        } else {
            System.out.println("不一致");
        }

        List<String> st2 = Lists.newArrayList("1", "2", "3");
        String string = "4";
        if (!st2.contains(string)) {
            System.out.println("111");
        }
    }

    @Test
    public void dateAfertAndBefore() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date datestartTime = df.parse("2024-06-23 00:00:00");
            Date dateendTime = df.parse("2024-06-24 23:59:59");

            Date e = new Date();
            Calendar newDate = Calendar.getInstance();
            newDate.setTime(e);
//            newDate.add(Calendar.YEAR, -1);
            newDate.add(Calendar.DATE, -120);
            Date b = newDate.getTime();
            System.out.println("------" + b);

            if (datestartTime.after(b) && dateendTime.after(b)) {// hbase查询
                System.out.println("之后");
            } else if (datestartTime.before(b) && dateendTime.before(b)) {//start<end<b 去file查询
                System.out.println("之前");
            } else {
                System.out.println();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tif() {
        String zip = "/Users/wanghongbo/Desktop/sql/未命名文件夹.zip";
        String filePath = "/Users/wanghongbo/Desktop/sql";
        String outputFolder = "/Users/wanghongbo/Desktop/sql/outputFolder";

        try {
            // 创建输出目录
            File destDirFile = new File(filePath);
            if (!destDirFile.exists()) {
                destDirFile.mkdirs();
            }
            File imageOutputDirFile = new File(outputFolder);
            if (!imageOutputDirFile.exists()) {
                imageOutputDirFile.mkdirs();
            }

            // 打开ZIP文件
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
            ZipEntry zipEntry = zis.getNextEntry();

            // 逐个条目处理
            while (zipEntry != null) {
                String entryName = zipEntry.getName();
                File newFile = new File(filePath + File.separator + entryName);

                // 创建所有必要的父目录
                new File(newFile.getParent()).mkdirs();

                if (!zipEntry.isDirectory()) {
                    // 如果条目不是文件夹，则提取文件
                    FileOutputStream fos = new FileOutputStream(newFile);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();

                    // 检查文件是否是图片文件
                    if (isImageFile(newFile)) {
                        // 将图片文件移动到输出目录
                        //File destImageFile = new File(outputFolder + File.separator + newFile.getName());
                        //newFile.renameTo(destImageFile);
                        // 构建 .tif 文件对象
                        File tifFile = new File(newFile.getName());

                        // 读取 .tif 文件为 BufferedImage
                        BufferedImage image = ImageIO.read(tifFile);
                        String jpgFilePath = newFile.getName().replaceAll("\\.tif$", ".jpg");
                        File pgFile = new File(outputFolder + File.separator + jpgFilePath);
                        ImageIO.write(image, "jpg", pgFile);
                        System.out.println("转换完成。");


                    }
                }

                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("解压和提取图片完成。");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TiffToJpgConverter() {

        String filePath = "/Users/wanghongbo/Desktop/sql/MX-M2658NV_20240704_155827_001.tif";
        String outputFolder = "/Users/wanghongbo/Desktop/sql/outputFolder";

        try {
            // TIFF 文件路径
            File inputFile = new File(filePath);
            // JPEG 输出文件路径
            File outputFile = new File(outputFolder);

            // 使用 ImageIO 读取 TIFF 文件
            BufferedImage image = ImageIO.read(inputFile);
            if (image != null) {
                // 将 BufferedImage 写入为 JPEG 文件
                ImageIO.write(image, "jpg", outputFile);
                System.out.println("TIFF to JPEG conversion completed successfully.");
            } else {
                System.out.println("Failed to read TIFF image using ImageIO.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //@Test
    //public static void ZipFile(String[] args) {
    //    String zipFilePath = "path/to/your/archive.zip";
    //    String destDirectory = "output/directory/";
    //
    //    try {
    //        // 创建输出文件夹
    //        File destDir = new File(destDirectory);
    //        if (!destDir.exists()) {
    //            destDir.mkdirs();
    //        }
    //
    //        // 使用 Apache Commons Compress 解压ZIP文件
    //        ZipFile zipFile = new ZipFile(zipFilePath);
    //        zipFile.stream()
    //                .filter(entry -> !entry.isDirectory() && entry.getName().toLowerCase().endsWith(".tif"))
    //                .forEach(entry -> {
    //                    try {
    //                        File outputFile = new File(destDirectory + File.separator + entry.getName());
    //                        FileOutputStream fos = new FileOutputStream(outputFile);
    //
    //                        // 将ZIP文件条目内容写入文件
    //                        zipFile.getInputStream(entry).transferTo(fos);
    //                        fos.close();
    //
    //                        // 将.tif文件转换成.jpg文件
    //                        if (outputFile.getName().toLowerCase().endsWith(".tif")) {
    //                            File jpgFile = new File(destDirectory + File.separator +
    //                                    getFileNameWithoutExtension(outputFile.getName()) + ".jpg");
    //
    //                            // 使用 JAI API 转换图像格式
    //                            RenderedOp image = JAI.create("fileload", outputFile.getAbsolutePath());
    //                            BufferedImage bufferedImage = image.getAsBufferedImage();
    //                            ImageIO.write(bufferedImage, "jpg", jpgFile);
    //
    //                            // 删除原始的.tif文件
    //                            outputFile.delete();
    //                        }
    //                    } catch (IOException e) {
    //                        e.printStackTrace();
    //                    }
    //                });
    //
    //        zipFile.close();
    //        System.out.println("ZIP文件解压并图片转换完成。");
    //
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    // 获取文件名去除后缀
    private static String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, dotIndex);
    }
    // 方法：检查文件是否是图片文件
    private static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return (name.endsWith(".jpg") || name.endsWith(".jpeg")
                || name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".tif"));
    }

    @Test
    public void StringBuilderTest(){
        StringBuilder builder = new StringBuilder();
        builder.append("未识别");
        //builder.append("\n");
        builder.append(System.lineSeparator());
        builder.append("已解析");
        System.out.println(builder.toString());
        String str = builder.toString();
        System.out.println(str.replace("未识别","已识别"));
        if (str.contains("已解析")){
            System.out.println("包含");
        }
    }

    @Test
    public void StringHuoTest(){
        String str = "3";
        if (!"3".equals(str) && !"0".equals(str)){
            System.out.println("1111");
        }
    }
}


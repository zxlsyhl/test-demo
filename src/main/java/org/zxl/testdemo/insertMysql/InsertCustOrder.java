package org.zxl.testdemo.insertMysql;

import org.zxl.testdemo.sequence.SnowFlakeUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class InsertCustOrder {
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        final String url = "jdbc:mysql://192.168.1.102:3306/demo_db?useUnicode=true&characterEncoding=utf8&useSSL=false";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "sleuth";
        final String password = "Sleuth0901!";
        Connection conn = null;
        Class.forName(name);//指定连接类型
        conn = DriverManager.getConnection(url, user, password);//获取连接
        if (conn != null)
        {
            System.out.println("获取连接成功");
            insertCustomer(conn);
        }
        else
        {
            System.out.println("获取连接失败");
        }

    }
    public static void insertCustomer(Connection conn)
    {
        int order_id = 1;
        // 开始时间
        Long begin = new Date().getTime();
        // sql前缀
        String prefix = "INSERT INTO cust_ord (`order_id`, `order_num`, `handle_cust_id`, `own_cust_id`, `bill_cust_id`, `status_cd`, `order_desc`, `order_date`) VALUES ";
        try
        {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement  pst = (PreparedStatement) conn.prepareStatement(" ");//准备执行语句

            // 外层循环，总提交事务次数
            for (int i = 1; i <= 1000; i++)
            {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 10000; j++)
                {
                    // 构建SQL后缀
                    String string = "";
                    for (int k = 0; k < 10; k++)
                    {
                        char c = (char) ((Math.random() * 26) + 97);
                        string += (c + "");
                    }
                    String order_num =  orderNumTop[random.nextInt(10)]+ SnowFlakeUtils.get().getContractNo();
                    int handle_cust_id = random.nextInt(1000000)+1;
                    int own_cust_id = handle_cust_id;
                    int bill_cust_id = random.nextInt(1000000)+1;
                    String status_cd = statusCds[random.nextInt(5)];
                    String order_desc = getDesc(order_num,order_id);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");


                    //``order_id`, `order_num`, `handle_cust_id`, `own_cust_id`, `bill_cust_id`, `status_cd`, `order_desc`, `order_date`
                    suffix.append("(" + order_id + ",'" + order_num + "'," + handle_cust_id + ","+ own_cust_id + ","+ bill_cust_id + ",'" + status_cd+
                            "','" + order_desc + "','"+ sdf.format(new Date()) + "'),");

                    order_id++;
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                System.out.println("第"+i+"次提交");
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("1000万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }

    static String[] orderNumTop = {"CRM","YSL","ODS","DMT","BBS","BILL","CTS","PPT","GGD","NNS","CMD"};

    static String[] outsizeStr = {"浓眉大眼","一表人才","慈眉善目","青面獠牙","相貌堂堂","贼眉鼠眼","眉清目秀","明眸皓齿","蓬头历齿","面如土色"};//外貌
    static String[] bodyStr = {"柳腰花态","柳亸花娇","秀色可餐","花枝招展","梨花带雨","仙姿玉色","雍荣华贵","曲眉丰颊","娉婷袅娜","妍姿艳质"}; //身材
    static String[] characterStr = {"小心谨慎","和蔼可亲","光明磊落","表里如一","自以为是","大智若愚","刁钻古怪","刚正不阿","斤斤计较","助人为乐"}; //性格
    static String[] desc1Str = {"形容词是英语中最重要的词类之一","北国风光，千里冰封，万里雪飘。","望长城内外，惟余莽莽；大河上下，顿失滔滔。","山舞银蛇，原驰蜡象，欲与天公试比高。","须晴日，看红装素裹，分外妖娆。"
            ,"江山如此多娇，引无数英雄竞折腰。","惜秦皇汉武，略输文采；唐宗宋祖，稍逊风骚。","一代天骄，成吉思汗，只识弯弓射大雕。","俱往矣，数风流人物，还看今朝。","俱往矣，数风流人物，还看今朝。"}; //附加1
    static String[] desc2Str = {"春眠不觉晓，处处闻啼鸟。","夜来风雨声，花落知多少。","床前明月光，疑是地上霜。","举头望明月，低头思故乡。","日照香炉生紫烟，遥看瀑布挂前川。"
            ,"飞流直下三千尺，疑是银河落九天。","好雨知时节，当春乃发生。","随风潜入夜，润物细无声。"," 野径云俱黑，江船火独明。","晓看红湿处，花重锦官城。"}; //附加2
    static String[] desc3Str = {"朝辞白帝彩云间，千里江陵一日还。","两岸猿声啼不住，轻舟已过万重山。","茅檐低小，溪上青青草。","醉里吴音相媚好，白发谁家翁媪。","大儿锄豆溪东，中儿正织鸡笼。"
            ,"寻寻觅觅，冷冷清清，凄凄惨惨戚戚。","乍暖还寒时候，最难将息。","三杯两盏淡酒，怎敌他、晚来风急！","满地黄花堆积，憔悴损，如今有谁堪摘？","这次第，怎一个愁字了得！"}; //附加3

    public static String getDesc(String cust_name, int cust_id){
        StringBuilder cust_desc = new StringBuilder();
        cust_desc.append(cust_name).append(":").append(outsizeStr[random.nextInt(10)]).append(";").append(bodyStr[random.nextInt(10)])
                .append(characterStr[random.nextInt(10)]).append("。");
        if(cust_id%3 ==0){
            cust_desc.append(desc1Str[random.nextInt(10)]);
        }if(cust_id%3 ==1){
            cust_desc.append(desc2Str[random.nextInt(10)]);
        }if(cust_id%3 ==2){
            cust_desc.append(desc3Str[random.nextInt(10)]);
        }
        return cust_desc.toString();

    }

    static String[] addresss = {"上海市浦东新区三林路","上海市浦东新区上南路","上海市浦东新区上浦路","上海市浦东新区长清路","上海市浦东新区灵岩南路","上海市浦东新区凌兆路","上海市浦东新区永泰路"
            ,"上海市浦东新区云台路","上海市浦东新区张江路","上海市浦东新区金科路"};


    static String[] statusCds = {"1000","1001","1002","1003","1004"}; //5

    static String[] idCardOnes = {"420","301","411","520","120","625","405","220","303","520"}; //10
    static String[] idCardTwos = {"301","304","234","142","421","673","261","394","160","700"}; //10
    static String[] idCardLasts = {"3511","2304","341X","1578","4256","2421","2615","3945","160X","7003"
            ,"7123","7313","7443","7553","7773","9003","9093","7666","744","743X"}; //20
    public static String getIdCard(){
        StringBuilder idCard = new StringBuilder();
        idCard.append(idCardOnes[random.nextInt(10)]).append(idCardTwos[random.nextInt(10)]).append("19").append(49+random.nextInt(88)+11);

        int month = random.nextInt(12)+1;
        if(month<10){
            idCard.append("0").append(month);
        }
        else {
            idCard.append(month);
        }

        int day = random.nextInt(28)+1;
        if(day<10){
            idCard.append("0").append(day);
        }
        else {
            idCard.append(day);
        }
        idCard.append(idCardLasts[random.nextInt(20)]);
        return idCard.toString();
    }

    static String[] custTypes = {"公客","商客","大客"};

    static String[] firstName = {"张","曾","谢","勾","鲍","李","王","姜","胡","杨","周","欧阳","诸葛","曹","刘","郑","秦","朱","柯","宋"}; //20个姓
    static String[] secondName = {"小","庆","贤","亦","威","强","燕","艳","黎","龙","晓","轩","虎","驰","峰","荣","华","富","贵","梅","兰","竹","菊","雷","宇","娇","佳","义","毅","菲","凡"}; //30个名
    static String[] thirdName = {"龙","欢","文","丰","源","清","鑫","民","富","国","琴","悠","昊","天","驰","宝","颖","聪","慧","辉",};//20个名

    static Random random = new Random();
    public static String getName(int cust_id){
        StringBuilder cust_name = new StringBuilder();
        cust_name.append(firstName[random.nextInt(20)]).append(secondName[random.nextInt(30)]);
        if(cust_id%3 ==0){
            cust_name.append(thirdName[random.nextInt(20)]);
        }
        return cust_name.toString();
    }


}

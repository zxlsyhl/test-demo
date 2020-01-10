package org.zxl.testdemo.sequence;

import java.util.UUID;

/**
 * ID生成系统的类型
 * UUID
 * UUID是指在一台机器在同一时间中生成的数字在所有机器中都是唯一的。按照开放软件基金会(OSF)制定的标准计算，用到了以太网卡地址、纳秒级时间、芯片ID码和许多可能的数字
 * UUID由以下几部分的组合：
 * （1）当前日期和时间。
 * （2）时钟序列。
 * （3）全局唯一的IEEE机器识别号，如果有网卡，从网卡MAC地址获得，没有网卡以其他方式获得。
 * 标准的UUID格式为：xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx (8-4-4-4-12)，以连字号分为五段形式的36个字符，示例：550e8400-e29b-41d4-a716-446655440000
 * Java标准类库中已经提供了UUID的API。
 *
 * UUID.randomUUID()
 * 优点
 *
 * 性能非常高：本地生成，没有网络消耗。
 * 缺点
 *
 * 不易存储：UUID太长，16字节128位，通常以36长度的字符串表示，很多场景不适用。
 * 信息不安全：基于MAC地址生成UUID的算法可能会造成MAC地址泄露，这个漏洞曾被用于寻找梅丽莎病毒的制作者位置。
 * ID作为主键时在特定的环境会存在一些问题，比如做DB主键的场景下，UUID就非常不适用。
 *
 */
public class UUIDUtil {
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println(UUID.randomUUID());
        }
    }
}

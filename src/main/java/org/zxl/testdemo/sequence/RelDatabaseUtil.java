package org.zxl.testdemo.sequence;

/**
 * 使用数据库自增id，
 * 主要思路是采用数据库自增ID + replace_into实现唯一ID的获取。
 *
 * create table t_global_id(
 *     id bigint(20) unsigned not null auto_increment,
 *     stub char(1) not null default '',
 *     primary key (id),
 *     unique key stub (stub)
 * ) engine=MyISAM;
 * # 每次业务可以使用以下SQL读写MySQL得到ID号
 * replace into t_golbal_id(stub) values('a');
 * select last_insert_id();
 * replace into跟insert功能类似，不同点在于：replace into首先尝试插入数据列表中，如果发现表中已经有此行数据（根据主键或唯一索引判断）则先删除，再插入。否则直接插入新数据。
 * 当然为了避免数据库的单点故障，最少需要两个数据库实例，通过区分auto_increment的起始值和步长来生成奇偶数的ID。如下：
 *
 * Server1：
 * auto-increment-increment = 2
 * auto-increment-offset = 1
 *
 * Server2：
 * auto-increment-increment = 2
 * auto-increment-offset = 2
 * 优点
 *
 * 简单。充分借助数据库的自增ID机制，可靠性高，生成有序的ID。
 * 缺点
 *
 * ID生成依赖数据库单机的读写性能。
 * 依赖数据库，当数据库异常时整个系统不可用。
 */
public class RelDatabaseUtil {
}

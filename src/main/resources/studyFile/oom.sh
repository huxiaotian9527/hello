#!/bin/sh

pid=$1
dir_path=/ap_log/gc/oom/$pid

# 创建现场目录
rm -rf dir_path;

mkdir $dir_path;

# top
top -b -n 1 >$dir_path/top.txt;

# jvm线程使用cpu情况
top -Hp $pid >$dir_path/topHp.txt;

# 磁盘
df -h >$dir_path/df.txt;

# IO
iostat -d -k 1 10 >$dir_path/iostat.txt;

# JVM
jstat -gccause $pid >$dir_path/jstat_gccause.txt;

jstack -F $pid >$dir_path/jstack_1.txt ;

jmap -heap $pid >$dir_path/jmap_heap.txt;

jmap -F -dump:live,file=$dir_path/jmap.hprof $pid;

jstack -F $pid >$dir_path/jstack_2.txt ;

zip $dir_path/$pid.zip $dir_path/*

kill -9 $1

sh /home/p4spc/csot-spc-web/bin/web-service.sh --method start --ppid 23456

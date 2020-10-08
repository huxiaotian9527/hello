package com.hu.base.util.draw;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class ChangeJar {
    public static void main(String[] args) throws Exception{
        ClassPool.getDefault().insertClassPath("D:\\tools\\apache-maven-3.6.3\\repository\\com\\aspose\\aspose-cells\\20.4\\aspose-cells-20.4.jar");
        CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.cells.zasj");
        CtMethod zzv = zzZJJClass.getDeclaredMethod("a");
        zzv.setBody("{return 1;}");
        zzZJJClass.writeFile("C:\\Users\\hutiantian\\Desktop\\123\\");

    }
}

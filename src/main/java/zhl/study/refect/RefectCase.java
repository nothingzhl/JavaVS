package zhl.study.refect;

import java.lang.reflect.Method;

/**
 * Refect 的相关概念
 */
public class RefectCase {

    public static void main(String[] args) {

        Method[] methods = CaseClass.class.getMethods();

        for (Method method : methods) {
            System.out.println(String.format("%s=>%s", method.getName(), method.getDeclaringClass()));
        }

    }

    /**
     * 用来测试的类
     */
    class CaseClass extends Object {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}

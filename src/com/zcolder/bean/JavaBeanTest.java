package com.zcolder.bean;

/**
 * 用来测试jsp中的javabean动作的普通java类
 * @author 张朝锋
 */
public class JavaBeanTest {
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 年龄
	 */
	private int age;
	
	public JavaBeanTest() {
		name = "";
		age = 0;
	}

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

package com.zcolder.bean;

/**
 * ��������jsp�е�javabean��������ͨjava��
 * @author �ų���
 */
public class JavaBeanTest {
	
	private String name;
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
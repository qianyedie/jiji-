package com.briup.ch13;

import java.util.Scanner;
public class Tms {
	private Teacher[] teas;
	private int index;		
	public Tms(){
		this.teas = new Teacher[3];
		this.index = 0;
	}
		public void save(Teacher tea){
				if(index >= teas.length){
			Teacher[] demo = new Teacher[teas.length+3];
			System.arraycopy(teas,0,demo,0,index);
			teas = demo;
		}
		teas[index++] = tea;
	}
		public Teacher[] findAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}
		public Teacher findById(long id){
		int num = findIndexById(id);
		
		return num==-1?null:teas[num];
	}
	public int findIndexById(long id){
		int num = -1;
		for(int i=0;i<index;i++){
			if(teas[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}


	/**
	  通过id删除学生
	  @author zhaoliu
	  @param id

	  teas = {
		{1001,Miss Li,28},
		{1003,Mr Wang,25},
		{1004,Mrs.Zhao,27},
		null,
		null,
		null
	  }
	  index = 3;
	  id = 1002
	  num = 1
	  teas[1] = teas[1+1]
	  teas[2] = teas[2+1]

	*/
	public void deleteById(long id){
		int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
			teas[i] = teas[i+1];
		}
		teas[--index] = null;
	}
		public void update(Teacher neaTea){
		for(int i=0;i<index;i++){
			if(teas[i].getId() == neaTea.getId()){
				teas[i].setName(neaTea.getName());
				teas[i].setAge(neaTea.getAge());
			}
		}
	}

	/**
		菜单
		@auhor
	*/
	public void menu(){
		System.out.println("***********教师信息管理系统***********");
		System.out.println("*1. 查询所有教师信息");
		System.out.println("*2. 录入教师信息");
		System.out.println("*3. 删除教师信息");
		System.out.println("*4. 查看教师信息");
		System.out.println("*5. 更改教师信息");
		System.out.println("*help. 帮助");
		System.out.println("*exit. 退出");
		System.out.println("**************************************");
	}

	public static void main(String[] args){
		Tms sms = new Tms();	
		Scanner sc = new Scanner(System.in);
		sms.menu();
		while(true){
			System.out.print("请输入功能编号：");
			String option = sc.nextLine();
			switch(option){
				case "1":{//查询所有教师信息
					System.out.println("以下是所有教师的信息：");
					//调用方法查询所有教师信息
					Teacher[] arr = tms.findAll();
					//遍历
					for(Teacher tea : arr){
						System.out.println(tea);
					}
					System.out.println("总计 "+tms.index+"人");
					break;
				}
				case "2":{//录入教师信息
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入【break】返回主菜单");
						String teaStr = sc.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split("#");
						//将用户的输入进行处理
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//封装对象
						Teacher tea = new Teacher(id,name,age);
						//调用保存方法
						tms.save(tea);
						System.out.println("录入成功！");
						
					}
					break;
				}
				case "3":{//删除教师信息
					while(true){
						System.out.println("请输入要删除教师的【id】或者输入【break】返回主目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//将字符串id转换为长整型
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
							System.out.println("该教师不存在！");
							continue;
						}
						sms.deleteById(id);
						System.out.println("删除成功！");
					}

					break;
				}
				case "4":{//查看教师信息
					while(true){
						System.out.println("请输入要查找教师的【id】或者输入【break】返回主目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//将字符串id转换为长整型
						long id = Long.parseLong(idStr);
						Teacher tea = sms.findById(id);
						if(tea == null){
							System.out.println("该教师不存在！");
							continue;
						}
						System.out.println(tea);
					}
					break;
				}
				case "5":{
					while(true){
						System.out.println("请输入要修改教师的【id】或者输入【break】返回主目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//将字符串id转换为长整型
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
							System.out.println("您要修改的教师信息不存在！");
							continue;
						}

						System.out.println("您要修改的教师信息为："+tea);
						System.out.println("请输入该教师的新信息【name#age】");
						String teaStr = sc.nextLine();
						String[] teaArr = teaStr.split("#");
						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);
						Teacher neaTea = new Teacher(id,name,age);
						tms.update(neaTea);
						System.out.println("修改成功");
					}
					break;
				}
				case "exit":{
					System.out.println("bye bye!欢迎再次使用！");
					System.exit(0);
				}
				case "help":{
					tms.menu();
					break;
				}
				default:
					System.out.println("输入有误！");
			}
		}
	}
}
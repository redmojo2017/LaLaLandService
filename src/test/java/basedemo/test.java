package basedemo;

import com.cjl.ljz.basedemo.util.RedisCache;


public class test {
public static void main(String[] args) {
	String string="{messageCode=123478, errorCode=}";
	string=string.replaceAll("[{]", "");
	string=string.replaceAll("[}]", "");
	String[] split = string.split(",");
	for (int i = 0; i < split.length; i++) {
		String[] split2 = split[i].split("=");
	}
	System.out.println(string);
	System.out.println(split[0]);
	System.out.println(split[1]);
}
}

/**
 * Copyright © leaf. All Rights Reserved.
 */
package generator;


import org.mybatis.generator.api.ShellRunner;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leaf
 * @since 2013-12-271
 */
public class Mybatis3CodeGenerator {
	

	/**
	 * @author leaf
	 * @since 2013-12-27
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> path = new ArrayList<String>();
		path.add("/target/resources_xmlmapper");
		path.add("/target/src_model");
		path.add("/target/src_javamapper");
		System.out.println(System.getProperty("user.dir"));
		for(String s:path){
			File file = new File(System.getProperty("user.dir")+s);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		generate();
	}
	
	public static void generate() {
		try{
			String path = "/generator/orcl.xml";
        	URL config = Mybatis3CodeGenerator.class.getResource(path);
			System.out.println(config.getFile());
			String[] arg = { "-configfile", config.getFile(), "-overwrite" };
			ShellRunner.main(arg);
		}catch (Exception e){
			e.printStackTrace();
		}

    }  

}

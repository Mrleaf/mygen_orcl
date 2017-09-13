/**
 * Copyright © leaf. All Rights Reserved.
 */
package generator;


import org.mybatis.generator.api.ShellRunner;

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
		generate();
	}
	
	public static void generate() {  
		String path = "/generator/orcl.xml";
        String config = Mybatis3CodeGenerator.class.getResource(path).getFile();
        String[] arg = { "-configfile", config, "-overwrite" };  
        ShellRunner.main(arg);
    }  

}

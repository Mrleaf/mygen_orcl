/**
 * Copyright © leaf. All Rights Reserved.
 */
package generator;


import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.config.xml.ParserEntityResolver;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

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

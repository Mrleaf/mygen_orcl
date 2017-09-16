package org.mybatis.generator.codegen.mybatis3.javamapper.elements;


import org.mybatis.generator.api.Constants;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

public class SelectMapByExampleMethodGenerator extends
		AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
		FullyQualifiedJavaType javaUtilMapJavaType=new FullyQualifiedJavaType("java.util.Map<String,Object>");
		FullyQualifiedJavaType javaUtilListJavaType=new FullyQualifiedJavaType("java.util.List<Map<String,Object>>");
		
		FullyQualifiedJavaType dbInfoAnnotationJavaType=new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param");
		
		
		interfaze.addImportedType(javaUtilMapJavaType);
		interfaze.addImportedType(javaUtilListJavaType);
		interfaze.addImportedType(dbInfoAnnotationJavaType);
		
		{
			//List<Map<String,Object>> selectMapByExample
			String name = "example";
			Method method = new Method("selectMapByExample");
			
	        if(introspectedTable.getFullyQualifiedTableNameAtRuntime().contains("${"+Constants.T_TO+"}")){
	        	method.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), Constants.T_TO, "@Param(\""+Constants.T_TO+"\")"));
	        }
			
			method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), name, "@Param(\"example\")"));
			method.setVisibility(JavaVisibility.PUBLIC);
			method.setReturnType(javaUtilListJavaType);
			interfaze.addMethod(method);
		}

	}

}

package org.mybatis.generator.codegen.mybatis3.javamapper.elements;


import java.util.List;

import org.mybatis.generator.api.Constants;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.TableConfiguration;

public class SelectMapByPrimaryKeyMethodGenerator extends
		AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
		
		FullyQualifiedJavaType javaUtilMapJavaType=new FullyQualifiedJavaType("java.util.Map<String,Object>");
		FullyQualifiedJavaType javaUtilListJavaType=new FullyQualifiedJavaType("java.util.List<Map<String,Object>>");
		
		FullyQualifiedJavaType dbInfoAnnotationJavaType=new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param");
		
		
		interfaze.addImportedType(javaUtilMapJavaType);
		interfaze.addImportedType(javaUtilListJavaType);
		interfaze.addImportedType(dbInfoAnnotationJavaType);
		
		if(introspectedTable.hasPrimaryKeyColumns()){
			//Map<String,Object> selectMapByPrimaryKey
			String name = "id";
			Method method = new Method("selectMapByPrimaryKey");
			
	        if(introspectedTable.getFullyQualifiedTableNameAtRuntime().contains("${"+Constants.T_TO+"}")){
	        	method.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), Constants.T_TO, "@Param(\""+Constants.T_TO+"\")"));
	        }
			
			List<IntrospectedColumn> keys=introspectedTable.getPrimaryKeyColumns();
			
			if(keys.size()==1){
				method.addParameter(new Parameter(keys.get(0).getFullyQualifiedJavaType(), 
						keys.get(0).getJavaProperty(), "@Param(\""+keys.get(0).getJavaProperty()+"\")"));
			}else{
				TableConfiguration tableConfiguration = introspectedTable.getTableConfiguration();
				if(tableConfiguration.getModelType() == ModelType.FLAT){
					for(IntrospectedColumn key : keys){
						Parameter p = new Parameter(key.getFullyQualifiedJavaType(), key.getJavaProperty());
						p.addAnnotation("@Param(\""+key.getJavaProperty()+"\")");
						method.addParameter(p);
					}
				}else{
					method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType()), "key"));
				}
			}
			method.setVisibility(JavaVisibility.PUBLIC);
			method.setReturnType(javaUtilMapJavaType);
			interfaze.addMethod(method);
		}

	}

}

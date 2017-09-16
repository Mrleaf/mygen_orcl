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

public class SelectByExampleForOneMethodGenerator extends
		AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
			//selectByExampleForOne
			String name = "example";
			Method method = new Method("selectByExampleForOne");
			
	        if(introspectedTable.getFullyQualifiedTableNameAtRuntime().contains("${"+Constants.T_TO+"}")){
	        	method.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), Constants.T_TO, "@Param(\""+Constants.T_TO+"\")"));
	        }
			
			method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), name, "@Param(\"example\")"));
			method.setVisibility(JavaVisibility.PUBLIC);
			
			
			List<IntrospectedColumn> keys = introspectedTable.getPrimaryKeyColumns();
			List<IntrospectedColumn> nonkeys = introspectedTable.getNonPrimaryKeyColumns();
			
			List<IntrospectedColumn> bases = introspectedTable.getBaseColumns();
			List<IntrospectedColumn> blobs = introspectedTable.getBLOBColumns();
			
			int keysSize = keys.size(), nonkeysSize=nonkeys.size(), basesSize=bases.size(), blobsSzie=blobs.size();
			
			System.out.println("PrimaryKeyColumns.size: "+ keysSize);
			System.out.println("NonPrimaryKeyColumns.size: "+ nonkeysSize);
			System.out.println("+--BaseColumns.size: "+ basesSize);
			System.out.println("+--BLOBColumns.size: "+ blobsSzie);
			
			
			
			TableConfiguration tableConfiguration = introspectedTable.getTableConfiguration();
			
			if(tableConfiguration.getModelType() == ModelType.FLAT){
				method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
				
			}else if(tableConfiguration.getModelType() == ModelType.CONDITIONAL){
				
				if(blobsSzie>1){
					method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType()));
				}else{
					if(keysSize<=1){
						method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
					}else{
						method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType()));
					}
				}
				
			}else if(tableConfiguration.getModelType() == ModelType.HIERARCHICAL){
				
				if(blobsSzie>0){
					method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType()));
				}else{
					if(basesSize>0){
						method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
					}else{
						method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType()));
					}
				}
			}
			interfaze.addMethod(method);

	}

}

package tool;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtNewConstructor;
import javassist.NotFoundException;

public class ClassTransformer {
	public static Class<?> transform() throws NotFoundException, CannotCompileException, IOException {
		//�������� ������� ��� ������ � ������
		ClassPool pool = ClassPool.getDefault(); //�� ������ �� ����� �� � � ������ .. ��� ����� ����� ������� ���� ���������
		//.appendclassPath
		
		//ctClass -type fromJavaasist, it is wrapper for class
		CtClass cc = pool.get("original.Box");
		
		//add default constraction logic
		/*CtConstructor cstructor = CtNewConstructor.make(
				"public " + "Box(int id)" + "{"
				+ "this.id = 100;"
				+ "}", cc );
	
	*/	
		//cc.addConstructor(cstructor);
		CtConstructor ccon= cc.getDeclaredConstructor(null);
		cc.removeConstructor(ccon);

	    // create a new default constructor
		CtConstructor cstructor = CtNewConstructor.defaultConstructor( cc	) ;
	    // modify the constructor behaviour
		cstructor.setBody( "this.id = 100;");
		// add the constructor to the class
		cc.addConstructor(cstructor);
		
		//save changed class
		//cc.writeFile();
		return cc.toClass();
		

	}
}

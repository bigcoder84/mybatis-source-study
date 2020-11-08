package mybatis.reflection;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.junit.Test;

/**
 * @Auther: TJD
 * @Date: 2020-04-09
 * @DESCRIPTION:
 **/
public class MetaObjectTest {
    /**
     *  使用MetaObject工具进行多级赋值测试
     */
    @Test
    public void testMetaObjectForChildBean() {
        //初始化对象工厂
        DefaultObjectFactory objectFactory = new DefaultObjectFactory();
        //通过对象工程创建 Area实例
        Area area = objectFactory.create(Area.class);
        //创建包装类工厂
        DefaultObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
        //创建反射器工厂
        DefaultReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        //创建MetaObject
        MetaObject metaObject = MetaObject.forObject(area, objectFactory, objectWrapperFactory, reflectorFactory);
        //赋值
        metaObject.setValue("name","湖北省");
        metaObject.setValue("child.name","武汉市");
        metaObject.setValue("child.child.name","洪山区");
    }

    /**
     * MetaData测试方法，该类内部封装了很多set和get方法，方便对原始对象进行赋值操作
     */
    @Test
    public void testMetaObjectForChildList() {
        DefaultObjectFactory objectFactory = new DefaultObjectFactory();
        Area2 area = objectFactory.create(Area2.class);
        DefaultObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
        DefaultReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaObject metaObject = MetaObject.forObject(area, objectFactory, objectWrapperFactory, reflectorFactory);
        metaObject.setValue("name","湖北省");
        metaObject.setValue("childs[1].name","武汉市");
        metaObject.setValue("childs[1].childs[1].name","洪山区");
    }
}
